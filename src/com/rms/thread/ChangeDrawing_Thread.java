package com.rms.thread;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.criterion.Order;

import com.rms.dataObjects.info.Td00_jfxx;
import com.rms.dataObjects.info.Td01_sbxx;
import com.rms.dataObjects.info.Td12_gljf;
import com.rms.dataObjects.info.Td13_jfsbmx;
import com.rms.flowTrigger.FlowSendTrigger;
import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.baseObject.SharedCfgData;
import com.netsky.base.dataObjects.Te01_slave;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.OperProperties;
import com.netsky.base.service.others.GeneralService;
import com.netsky.base.baseObject.PropertyInject;

/**
 * @description:
 * 
 * @class name:com.rms.thread.ChangeDrawing_Thread
 * @author lee.xiangyu Jan 16, 2012
 */
@Service("cd_thread")
public class ChangeDrawing_Thread extends Thread {

	private  Logger log = Logger.getLogger(this.getClass());
	
	private String ftpConfigFile = "/ftpConfig/ftpConfig.xml";
	
	private String webRoot = null;
	
	private Long drawingBakNum = null;
	
	/**
	 * 检测新消息间隔时间，秒
	 */
	private static int sleep_time = 60;

	/**
	 * 数据库查询操作服务
	 */
	@Autowired
	private QueryService queryService;

	/**
	 * 数据库保存操作服务
	 */
	@Autowired
	private SaveService saveService;
	
	/**
	 * 数据服务
	 */
	@Autowired
	private Dao dao;

	@Autowired
	GeneralService generalService;


	@SuppressWarnings("unchecked")
	public void run() {
		
		ResultObject ro = null;
		StringBuffer sql = new StringBuffer("");
		
		try{
			/*
			 * 获得WEB根目录
			 */
			Map<String,String> cfgMap = SharedCfgData.getMap();
			String gys_cfg_uri = cfgMap.get("baseConfig.properties");
			OperProperties op = new OperProperties();
			op.load(gys_cfg_uri);
			this.webRoot = op.getValue("webRoot");
			this.drawingBakNum = convertUtil.toLong(op.getValue("drawingBakNum"),10L);
		}
		catch(Exception e){
			this.webRoot = null;
			log.error("[webRoot] not found in /WEB-INF/autoConfig/baseConfig.properties");
		}
		
		while (true) {
			try {
				log.debug("update drawing thread now sleep...");
				Thread.sleep(1000 * sleep_time);
				log.debug("update drawing  thread now weakup...");
				
				String randoms = Math.round(Math.random() * 10000000) + "";
				sql.delete(0, sql.length());
				sql.append("update Tz05_thread_queue set oper_batchs = '");
				sql.append(randoms);
				sql.append("' where type = 'GHXZT' and (status is null or status = '未处理') ");
				saveService.updateByHSql(sql.toString());
				
				/*
				 * 扫描线程表
				 */
				sql.delete(0, sql.length());
				sql.append("select id,paras ");
				sql.append("from Tz05_thread_queue ");
				sql.append("where type = 'GHXZT' and (status is null or status = '未处理') ");
				sql.append("and oper_batchs = '");
				sql.append(randoms);
				sql.append("'");
				ro = queryService.search(sql.toString());
				while(ro.next()){
					try{
						Long tz05_id = convertUtil.toLong(ro.get("id"));
						String paras = convertUtil.toString(ro.get("paras"));
						OperProperties op = new OperProperties();
						op.loadFromString(paras, "&", "=");
						Hashtable paramMap = op.getContainer();
						paramMap.put("tz05_id", tz05_id);
						
						//更换现状图
						boolean status = changeDrawing(paramMap);
						if(status){
							saveService.updateByHSql("update Tz05_thread_queue set oper_date = sysdate,status = '已处理',remark = '更换成功' where id = "+tz05_id);
						}
						else{
							saveService.updateByHSql("update Tz05_thread_queue set oper_date = sysdate,status = '未处理',remark = '更换失败' where id = "+tz05_id);
						}
					}
					catch(Exception e){
						log.error("find error when update drawing [com.rms.thread.ChangeDrawing_thread.java][5]:" + e);
					}	
				}
				
			} catch (InterruptedException e) {
				log.error("find error when update drawing [com.rms.thread.ChangeDrawing_thread.java][1]:" + e);
			} catch (Exception e) {
				log.error("find error when update drawing [com.rms.thread.ChangeDrawing_thread.java][2]:" + e);
			}
		}
	} 
	
	/**
	 * 更换申请单（变更单）现状图【批量】
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	private boolean changeDrawing(Hashtable paramMap) throws Exception{
		
		StringBuffer sql = new StringBuffer("");
		ResultObject ro = null;
		boolean status = true;
		
		Long doc_id = convertUtil.toLong(paramMap.get("doc_id"));
		try{
			sql.delete(0, sql.length());
			sql.append("select id ");
			sql.append("from Td12_gljf td12 ");
			sql.append("where td12.zhgxsj is null ");
			sql.append("and td12.scsj is not null ");
			sql.append("and parent_id = ");
			sql.append(doc_id);
			ro = queryService.search(sql.toString());
			while(ro.next()){
				try{
					Long td12_id = convertUtil.toLong(ro.get("id"));
					Hashtable t_map = new Hashtable();
					t_map.put("td12_id", td12_id);
					status = status && changeDrawingForSingle(t_map);
				}
				catch(Exception e){
					log.error("find error when update drawing [com.rms.thread.ChangeDrawing_thread.java][6]:" + e + e.getMessage());
				}
			}
		}
		catch(Exception e){
			log.error("find error when update drawing [com.rms.thread.ChangeDrawing_thread.java][3]:" + e + e.getMessage());
			throw new Exception(e + "[3]");
		}
		
		/*
		 * 发短信给项目管理员和设计人员
		 */
		if(status){
			FlowSendTrigger flowSendTrigger = new FlowSendTrigger();
			flowSendTrigger.setQueryService(queryService);
			flowSendTrigger.setSaveService(saveService);
			flowSendTrigger.informDesignerOver(paramMap);
		}
		
		return status;
	}
	
	/**
	 * 更换单个机房现状图
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private boolean changeDrawingForSingle(Hashtable paramMap) throws Exception{
		
		/*
		 * 定义变量
		 */
		boolean status = false;
		StringBuffer hsql = new StringBuffer("");
		QueryBuilder queryBuilder = null;
		ResultObject ro = null;
		HashMap paraMap = new HashMap();
		
		/*
		 * 获得WEB根目录和现状图保留数量
		 */
		String t_webRoot = this.webRoot;
		Long t_drawingBakNum = this.drawingBakNum;
		
		/*
		 * 如果这两个值为空，需要从配置文件中取一下，
		 * 这种情况一般出现在单独调用更换现状图时。
		 */
		if(t_webRoot == null || t_drawingBakNum == null){
			
			Map<String,String> cfgMap = SharedCfgData.getMap();
			String base_cfg_uri = cfgMap.get("baseConfig.properties");
			OperProperties op = new OperProperties();
			op.load(base_cfg_uri);
			if(t_webRoot == null){
				t_webRoot = op.getValue("webRoot");
			}
			if(t_drawingBakNum == null){
				t_drawingBakNum = convertUtil.toLong(op.getValue("drawingBakNum"),10L);
			}
		}
		
		try {
			Long td12_id = convertUtil.toLong(paramMap.get("td12_id"), -1L);
			Td12_gljf td12 = (Td12_gljf) queryService.searchById(Td12_gljf.class, td12_id);
			Long td00_id = -1L;
			if (td12 != null) {
				td00_id = td12.getJf_id();
			}
			Td00_jfxx td00 = (Td00_jfxx) queryService.searchById(Td00_jfxx.class, td00_id);
			if (td00 != null) {
				Te01_slave oldtz = null;
				Te01_slave newtz = null;
				
				hsql.delete(0, hsql.length());
				hsql.append("from Te01_slave where module_id=200 and doc_id=");
				hsql.append(td00_id);
				hsql.append(" and slave_type='机房现状图'");
				List<Te01_slave> te01_tmpList = (List<Te01_slave>) queryService.searchList(hsql.toString());
				if (te01_tmpList != null && te01_tmpList.size() == 1) {
					oldtz = te01_tmpList.get(0);
				}
				
				hsql.delete(0, hsql.length());
				hsql.append("from Te01_slave where module_id in (101,102) ");
				hsql.append("and slave_type like '%设计图' and doc_id=");
				hsql.append(td12_id);
				te01_tmpList = (List<Te01_slave>) queryService.searchList(hsql.toString());
				if (te01_tmpList != null && te01_tmpList.size() == 1) {
					newtz = te01_tmpList.get(0);
				}
				if (newtz != null && oldtz != null) {
					
					/*
					 * 发短信参数
					 */
					paraMap.clear();
					paraMap.put("jf_id", td00_id);
					
					/*
					 * 替换前备份原来的现状图
					 */
					Te01_slave baktz = new Te01_slave();
					PropertyInject.copyProperty(oldtz, baktz, new String[]{"serialVersionUID"});
					baktz.setId(null);
					baktz.setSlave_type("机房现状图备份");
					dao.saveObject(baktz);
					
					/*
					 * 机房现状图备份只保留[t_drawingBakNum]份,此值默认为10
					 * 在WEB-INF/autoConfig/baseConfig.properties 中
					 * 可以设置drawingBakNum=数字
					 */
					queryBuilder = new HibernateQueryBuilder(Te01_slave.class);
					queryBuilder.eq("project_id", oldtz.getProject_id());
					queryBuilder.eq("doc_id", oldtz.getDoc_id());
					queryBuilder.eq("module_id", oldtz.getModule_id());
					queryBuilder.eq("slave_type", "机房现状图备份");
					queryBuilder.addOrderBy(Order.asc("id"));
					List list = dao.search(queryBuilder);
					if(list != null && list.size() > t_drawingBakNum.intValue()){
						long needDelNums = list.size() - t_drawingBakNum;
						for(int i = 0;i < needDelNums;i ++){
							Te01_slave needDelTz = (Te01_slave)list.get(i);
							if(generalService.deleteFtpFile(needDelTz.getFtp_url(), t_webRoot)){
								saveService.updateByHSql("delete from Te01_slave where id = "+needDelTz.getId());
							}
						}
					}
					
					/*
					 * 更换现状图
					 */
					String filename = "";
					filename = oldtz.getId() + "Slave" + newtz.getExt_name();
					oldtz.setFtp_url(readandwrite(newtz.getFtp_url(), filename, t_webRoot));
					oldtz.setFile_name(newtz.getFile_name());
					oldtz.setExt_name(newtz.getExt_name());
					oldtz.setFtp_date(new Date());
					td00.setXzt_gxsj(new Date());
					td12.setZhgxsj(new Date());
					
					hsql.delete(0, hsql.length());
					hsql.append("from Td13_jfsbmx where sqd_id=");
					hsql.append(td12.getId());
					List<Td13_jfsbmx> td13_list = (List<Td13_jfsbmx>) queryService.searchList(hsql.toString());
					
					hsql.delete(0, hsql.length());
					hsql.append("from Td01_sbxx where jfxx_id=");
					hsql.append(td00.getId());
					List<Td01_sbxx> td01_list = (List<Td01_sbxx>) queryService.searchList(hsql.toString());
					
					List<Td01_sbxx> new_td01_list = new ArrayList<Td01_sbxx>();
					for (Td13_jfsbmx td13 : td13_list) {
					
						queryBuilder = new HibernateQueryBuilder(Td01_sbxx.class);
						queryBuilder.eq("jfxx_id", td12.getJf_id());
						queryBuilder.eq("azwz", td13.getAzwz());
						queryBuilder.eq("sbmc", td13.getSbmc());
						queryBuilder.eq("sbxh", td13.getSbxh());
						queryBuilder.eq("sbgg", td13.getSbgg());
						ro = queryService.search(queryBuilder);
						if(ro.next()){continue;}
						Td01_sbxx sbxx = new Td01_sbxx();
						sbxx.setAzrq(td13.getAzrq());
						sbxx.setAzwz(td13.getAzwz());
						sbxx.setBz(td13.getBz());
						sbxx.setCj(td13.getCj());
						sbxx.setFzr(td13.getFzr());
						sbxx.setGdfs(td13.getGdfs());
						sbxx.setJfxx_id(td00_id);
						sbxx.setJjcc(td13.getJjcc());
						sbxx.setJsxz(td13.getJsxz());
						sbxx.setLxdh(td13.getLxdh());
						sbxx.setSbmc(td13.getSbmc());
						sbxx.setSbxh(td13.getSbxh());
						sbxx.setSbgg(td13.getSbgg());
						sbxx.setSgdw(td13.getSgdw());
						sbxx.setSszy(td13.getSszy());
						new_td01_list.add(sbxx);
					}
					dao.saveObject(oldtz);
					dao.saveObject(td00);
					dao.saveObject(td12);
					for (Td01_sbxx td01_sbxx : new_td01_list) {
						dao.saveObject(td01_sbxx);
					}
					status = true;
				}
			}
		} catch (Exception e) {
			log.error("find error when update drawing [com.rms.thread.ChangeDrawing_thread.java][4]:" + e + e.getMessage());
			throw new Exception(e + "[4]");
		}
		if (status) {
				/*
				 * 发短信给已经下载但还没有上传的其它设计人员
				 */
				FlowSendTrigger flowSendTrigger = new FlowSendTrigger();
				flowSendTrigger.setQueryService(queryService);
				flowSendTrigger.setSaveService(saveService);
				flowSendTrigger.informOtherDesignerOver(paraMap);
			}
		return status;
	}

	
	private String readandwrite(String url, String filename,String t_webRoot) throws Exception {
		String returnurl = "";
		FTPClient ftp = new FTPClient();
		InputStream is = null;
		try {
			Map<String, String> result = new HashMap<String, String>();
			String filePath = t_webRoot + System.getProperty("file.separator") + "WEB-INF" + ftpConfigFile;
			File file = new File(filePath);
			if (!file.exists()) {
				throw new Exception("FTP配置文件不存在" + filePath);
			}
			try {
				SAXReader reader = new SAXReader();
				Document doc = reader.read(file);
				Element root = doc.getRootElement();
				result.put("folder", root.elementText("folder"));
				result.put("address", root.elementText("address"));
				result.put("username", root.elementText("username"));
				result.put("password", root.elementText("password"));
				Iterator<?> j;
				for (j = root.elementIterator("typeFolder"); j != null && j.hasNext();) {
					Element element = (Element) j.next();
					result.put(element.elementText("type"), element.elementText("folder"));
				}
			} catch (DocumentException ex) {
				throw new Exception("读取FTP配置文件失败" + filePath + ":" + ex);
			}
			ftp.connect(result.get("address"));
			ftp.login(result.get("username"), result.get("password"));
			ftp.enterLocalPassiveMode();
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			String folder = url.substring(0, url.lastIndexOf("/"));
			url = url.substring(url.lastIndexOf("/") + 1);
			String folders[] = folder.split("/");
			for (int j = 0; j < folders.length; j++) {
				if (!ftp.changeWorkingDirectory(folders[j])) {
					if (!ftp.makeDirectory(folders[j])) {
						try {
							throw new Exception("创建目录失败");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					ftp.changeWorkingDirectory(folders[j]);
				}
			}
			is = ftp.retrieveFileStream(url);
			ftp.disconnect();
			ftp.connect(result.get("address"));
			ftp.login(result.get("username"), result.get("password"));
			ftp.enterLocalPassiveMode();
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			folder = result.get("机房现状图");
			returnurl = folder + System.getProperty("file.separator") + filename;
			folders = folder.split("/");
			for (int j = 0; j < folders.length; j++) {
				if (!ftp.changeWorkingDirectory(folders[j])) {
					if (!ftp.makeDirectory(folders[j])) {
						try {
							throw new Exception("创建目录失败");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					ftp.changeWorkingDirectory(folders[j]);
				}
			}
			ftp.storeFile(filename, is);
			ftp.disconnect();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(is != null){
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return returnurl;
	}
}
