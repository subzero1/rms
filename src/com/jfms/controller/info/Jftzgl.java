package com.jfms.controller.info;

import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

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
import sun.rmi.transport.proxy.HttpReceiveSocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jfms.dataObjects.info.Td00_jfxx;
import com.jfms.dataObjects.info.Td01_sbxx;
import com.jfms.dataObjects.info.Td11_jfpmsq;
import com.jfms.dataObjects.info.Td12_gljf;
import com.jfms.dataObjects.info.Td13_jfsbmx;
import com.jfms.flowTrigger.FlowSendTrigger;
import com.netsky.base.baseObject.PropertyInject;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.baseObject.SharedCfgData;
import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Te01_slave;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.service.others.GeneralService;
import com.netsky.base.utils.OperProperties;
import com.netsky.base.utils.convertUtil;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Tb02_node;
import com.jfms.dataObjects.info.Td00_jfxx;

@Controller
public class Jftzgl {
	
	private  Logger log = Logger.getLogger(this.getClass());
	
	private static String ftpConfigFile = "/ftpConfig/ftpConfig.xml";
	/**
	 * 数据服务
	 */
	@Autowired
	private Dao dao;

	@Autowired
	private ExceptionService exceptionService;

	/**
	 * 查询服务
	 */
	@Autowired
	private QueryService queryService;
	
	/**
	 * 查询服务
	 */
	@Autowired
	private SaveService saveService;
	/**
	 * 通用服务
	 */
	@Autowired
	private GeneralService generalService;
	
	/**
	 * 机房图纸申请明细列表
	 * 参数：jfxx_id,doc_id,project_id,module_id
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/pmsqDetail.do")
	public ModelAndView pmsqDetail(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		// 数据库相关变量
		StringBuffer hsql = new StringBuffer("");
		ModelMap modelMap = new ModelMap();
		ResultObject ro = null;

		// 分页变量
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request.getParameter("orderField"), "cjrq");
		String orderDirection = convertUtil.toString(request.getParameter("orderDirection"), "desc");
		Integer totalCount = 0;
		Integer pageNumShown = 0;

		Long doc_id = convertUtil.toLong(request.getParameter("doc_id"),-1L);
		Long node_id = convertUtil.toLong(request.getParameter("node_id"),-1L);
		Long project_id = convertUtil.toLong(request.getParameter("project_id"),-1L);
		Long module_id = convertUtil.toLong(request.getParameter("module_id"),-1L);
		Long jfxx_id  =convertUtil.toLong(request.getParameter("jfxx_id"),-1L);
		
		// 获取机房图纸
		Te01_slave jfght = null;
		Te01_slave jfxzt = null;
		hsql.delete(0, hsql.length());
		hsql = hsql.append("from Te01_slave where module_id=200 and doc_id = "
				+ jfxx_id);
		List<Te01_slave> slaveList = (List<Te01_slave>) dao.search(hsql
				.toString());
		for (Te01_slave slave : slaveList) {
			if ("机房规划图".equals(slave.getSlave_type()))
				jfght = slave;

			if ("机房现状图".equals(slave.getSlave_type()))
				jfxzt = slave;
		}
		modelMap.put("jfght", jfght);
		modelMap.put("jfxzt", jfxzt);
		
		/*
		 * 获取当前机房上传的设计图
		 */
		hsql.delete(0, hsql.length());
		hsql.append("from Te01_slave te01 where module_id=");
		hsql.append(module_id);
		hsql.append(" and doc_id = ");
		hsql.append(doc_id);
		hsql.append(" and project_id = ");
		hsql.append(project_id);
		hsql.append(" and slave_type like '%设计图'");
		ro = queryService.search(hsql.toString());
		Long sqdpmid = null;
		if(ro.next()){
			Te01_slave te01 = (Te01_slave)ro.get("te01");
			sqdpmid = te01.getId();
		}
		modelMap.put("sqdpmid", sqdpmid);
		
		/*
		 * 获得机房申请明细列表
		 */ 
		hsql.delete(0, hsql.length());
		hsql.append(" from Td11_jfpmsq td11,Td12_gljf td12, Te01_slave te01,Ta03_user ta03  ");
		hsql.append(" where td12.id = te01.doc_id ");
		hsql.append(" and td11.sjry = ta03.name ");
		hsql.append(" and td11.project_id = td12.project_id ");
		hsql.append(" and te01.slave_type like '%设计图'");
		hsql.append(" and te01.module_id = ");
		hsql.append(module_id);
		hsql.append(" and td12.jf_id = ");
		hsql.append(jfxx_id);
		hsql.append(" order by ");
		hsql.append(orderField);
		hsql.append(" ");
		hsql.append(orderDirection);
		ro = queryService.searchByPage(hsql.toString(), pageNum,numPerPage);
		totalCount = ro.getTotalRows();
		pageNumShown = ro.getTotalPages();

		modelMap.put("totalCount", totalCount);
		modelMap.put("pageNumShown", pageNumShown);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);

		List<?> pmsqDetailList = ro.getList();
		Td00_jfxx td00 = (Td00_jfxx)dao.getObject(Td00_jfxx.class, jfxx_id);
		
		modelMap.put("pmsqDetailList", pmsqDetailList);
		modelMap.put("jfmc", td00.getJfmc());
		modelMap.put("slave_type", module_id==101?4:5);//4:申请设计图 5:变更设计图
		
		/*
		 * 获得申请单中当前机房申请信息
		 */
		Td12_gljf td12 = (Td12_gljf)queryService.searchById(Td12_gljf.class, doc_id);
		modelMap.put("td12", td12);
		
		/*
		 * 操作权限
		 */
		Tb02_node tb02 = (Tb02_node)queryService.searchById(Tb02_node.class, node_id);
		String t_desc = null;
		if(tb02 != null){
			t_desc = convertUtil.toString(tb02.getRemark());
		}
		
		if(t_desc != null && t_desc.indexOf("[上传]") != -1){//shangchuang
			modelMap.put("canUpload", "yes");
		}
		if(t_desc != null && t_desc.indexOf("[替换]") != -1){//tihuan
			modelMap.put("canReplace", "yes");
		}

		return new ModelAndView("/WEB-INF/jsp/info/pmsqDetail.jsp", modelMap);
	}
	
	
	/**
	 * 设置下载时间
	 * 传入参数 td12_id
	 * @param request
	 * @param response
	 * @param session void
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/info/ajaxSetDownloadTime.do")
	public void ajaxSetDownloadTime(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		// 数据库相关变量 td12_gljf.parent_id
		Ta03_user user = null;
		user = (Ta03_user)session.getAttribute("user");
		Long td12_id = convertUtil.toLong(request.getParameter("td12_id"),-1L);
		Td12_gljf td12 = (Td12_gljf)queryService.searchById(Td12_gljf.class, td12_id);
		Td11_jfpmsq td11 = (Td11_jfpmsq)queryService.searchById(Td11_jfpmsq.class, td12.getParent_id());
		if(td11 != null && td11.getSjry() != null && td11.getSjry().equals(user.getName())){
			td12.setXzsj(new Date());
			saveService.save(td12);
		}
	}
	
	/**
	 * 上传条件,td12_id做为doc_id 传入
	 * @param request
	 * @param response
	 * @param session
	 * @throws Exception void
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/info/ajaxUploadCondition.do")
	public void ajaxUploadCondition(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {

		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 数据库相关变量
		Long project_id = convertUtil.toLong(request.getParameter("project_id"),-1L);
		Long module_id = convertUtil.toLong(request.getParameter("module_id"),-1L);
		Long slave_type = convertUtil.toLong(request.getParameter("slave_type"),-1L);
		Long doc_id = convertUtil.toLong(request.getParameter("doc_id"),-1L);
		Long spdpmid = convertUtil.toLong(request.getParameter("spdpmid"),-1L);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		Td12_gljf td12 = (Td12_gljf)queryService.searchById(Td12_gljf.class, doc_id);
		Date xzsj = td12.getXzsj();//下载时间
		Long jf_id = td12.getJf_id();
		
		Td00_jfxx td00 = (Td00_jfxx)queryService.searchById(Td00_jfxx.class, jf_id);
		Date xzt_gxsj = td00.getXzt_gxsj();//现状图更新时间
		if(xzsj == null){
			String msg = "你还未下载过本机房现状图，请先从系统执行【下载】操作！";
			out.print("{\"statusCode\":\"300\",\"message\":\""+msg+"\"}");
		}
		else if(xzsj != null && xzt_gxsj != null && xzsj.before(xzt_gxsj)){
			String msg = "你上传的图纸与最新现状图冲突，请先下载最新的图纸修改后上传！<br>你下载现状图时间："+sdf.format(xzsj)+"<br>图纸最新上传时间："+sdf.format(xzt_gxsj);
			out.print("{\"statusCode\":\"300\",\"message\":\""+msg+"\"}");
		}
		else{
			out.print("{\"statusCode\":\"200\",\"project_id\":\""+project_id+"\", \"doc_id\":\""+doc_id+"\", \"module_id\":\""+module_id+"\", \"slave_type\":\""+slave_type+"\", \"spdpmid\":\""+spdpmid+"\"}");
		}
	}
	
	@RequestMapping("/info/sessionTimeout.do")
	public void sessionTimeout(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception{
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Long id = convertUtil.toLong(request.getParameter("id"));
		Long td12_id = convertUtil.toLong(request.getParameter("td12_id"));
		
		if(session == null){
			out.print("{\"statusCode\":\"300\",\"message\":\"连接断开，请重新登录后再下载\"}");
		}
		else{
			Td12_gljf td12 = (Td12_gljf)queryService.searchById(Td12_gljf.class, td12_id);
			Long jf_id = td12.getJf_id();
			String msgBox = "";
			String msgBox2 = "";
			
			/*
			 * 如果此机房图纸是第一次被申请，则提示勘察机房
			 * 0 或 null 为第一次申请；如果为1，则已经被申请过
			 */
			Td00_jfxx td00 = (Td00_jfxx)queryService.searchById(Td00_jfxx.class, jf_id);
			Date xzt_gxsj = td00.getXzt_gxsj();
			if(xzt_gxsj == null){
				msgBox = "此机房图纸较旧，建议去实际机房勘察后再修改！！！   ";
			}
			
			/*
			 * 如果其他人员正在修改此图纸，则提示冲突
			 */
			StringBuffer sql = new StringBuffer("select td11.sjry as sjry ,ta03.mobile_tel as mobile_tel ");
			sql.append("from Td11_jfpmsq td11,Td12_gljf td12,Ta03_user ta03 ");
			sql.append("where td11.id = td12.parent_id ");
			sql.append("and td11.sjry = ta03.name ");
			sql.append("and td12.id <> ");
			sql.append(td12_id);
			sql.append(" and td12.jf_id = ");
			sql.append(jf_id);
			sql.append(" and td12.xzsj is not null "); 
			sql.append("and td12.zhgxsj is null ");
			ResultObject ro = queryService.search(sql.toString());
			while(ro.next()){
				String sjry = convertUtil.toString(ro.get("sjry"));
				String mobile_tel = convertUtil.toString(ro.get("mobile_tel"));
				msgBox2 = msgBox2 + "（" + sjry + "  " + mobile_tel + "） ";
			}
			if(!msgBox2.equals("")){
				msgBox2 = "其他人员正在修改此图纸，为了避免冲突，请与以下设计人员沟通后再修改——" + msgBox2;
			}
			msgBox = msgBox +msgBox2;
			
			if(msgBox.equals("")){
				out.print("{\"statusCode\":\"200\", \"id\":\""+id+"\", \"td12_id\":\""+td12_id+"\"}");
			}
			else{
				out.print("{\"statusCode\":\"200\", \"id\":\""+id+"\", \"td12_id\":\""+td12_id+"\",\"message\":\""+msgBox+"\"}");
			}
		}
	}
	/**
	 * 更新现状图
	 * 参数：td11.id 
	 * 更新机房现状图te01.ftp_url,更新td00.Xzt_gxsj ,td12.zhgxsj
	 * @param request
	 * @param response
	 * @param session
	 * @throws Exception void
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/ghxzt.do")
	public void ghxzt(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		boolean status = false;
		StringBuffer hsql = new StringBuffer("");
		QueryBuilder queryBuilder = null;
		ResultObject ro = null;
		HashMap paraMap = new HashMap();
		try {
			Long td12_id = convertUtil.toLong(request.getParameter("td12_id"), -1L);
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
					paraMap.put("project_id", newtz.getProject_id());
					paraMap.put("module_id", newtz.getModule_id());
					
					String filename = "";
					filename = oldtz.getId() + "Slave" + newtz.getExt_name();
					generalService.deleteFtpFile(oldtz.getFtp_url(), request.getSession().getServletContext().getRealPath("/"));
					oldtz.setFtp_url(readandwrite(newtz.getFtp_url(), filename, request));
					oldtz.setFile_name(newtz.getFile_name());
					oldtz.setExt_name(newtz.getExt_name());
					Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
					oldtz.setUser_id(user.getId());
					oldtz.setUser_name(user.getName());
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
			e.printStackTrace();
		}
		if (status) {
			/*
			 * 更新现状图成功后给设计人员发短信
			 */
			FlowSendTrigger flowSendTrigger = new FlowSendTrigger();
			flowSendTrigger.setQueryService(queryService);
			flowSendTrigger.setSaveService(saveService);
			flowSendTrigger.informDesignerOver(paraMap);
			out.print("{\"statusCode\":\"200\", \"message\":\"更新现状图成功\", \"navTabId\":\"_current\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");
		} else {
			out.print("{\"statusCode\":\"300\", \"message\":\"更新失败\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");
		}
	}
	
	private String readandwrite(String url, String filename, HttpServletRequest request) throws Exception {
		String returnurl = "";
		FTPClient ftp = new FTPClient();
		InputStream is = null;
		try {
			Map<String, String> result = new HashMap<String, String>();
			String filePath = request.getSession().getServletContext().getRealPath("WEB-INF") + ftpConfigFile;
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
			returnurl = folder + "/" + filename;
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
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return returnurl;
	}
	

	/**
	 * 从机房现状图备份中更换
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@RequestMapping("/changeDrawingFromBak.do")
	private String changeDrawingFromBak(HttpServletRequest request,HttpServletResponse response ,HttpSession session) throws Exception{
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		/*
		 * 定义变量
		 */
		boolean status = false;
		StringBuffer hsql = new StringBuffer("");
		QueryBuilder queryBuilder = null;
		ResultObject ro = null;
		HashMap paraMap = new HashMap();
		
		try {
			Long slave_id = convertUtil.toLong(request.getParameter("slave_id"), -1L);
			Long jf_id = convertUtil.toLong(request.getParameter("jf_id"), -1L);
			
			Td00_jfxx td00 = (Td00_jfxx) queryService.searchById(Td00_jfxx.class, jf_id);
			if (td00 != null) {
				Te01_slave oldtz = null;
				Te01_slave newtz = null;
				
				hsql.delete(0, hsql.length());
				hsql.append("from Te01_slave where module_id=200 and doc_id=");
				hsql.append(jf_id);
				hsql.append(" and slave_type='机房现状图'");
				List<Te01_slave> te01_tmpList = (List<Te01_slave>) queryService.searchList(hsql.toString());
				if (te01_tmpList != null && te01_tmpList.size() == 1) {
					oldtz = te01_tmpList.get(0);
				}
				
				hsql.delete(0, hsql.length());
				hsql.append("from Te01_slave where id = ");
				hsql.append(slave_id);
				te01_tmpList = (List<Te01_slave>) queryService.searchList(hsql.toString());
				if (te01_tmpList != null && te01_tmpList.size() == 1) {
					newtz = te01_tmpList.get(0);
				}
				if (newtz != null && oldtz != null) {
					
					/*
					 * 发短信参数
					 */
					paraMap.clear();
					paraMap.put("jf_id", jf_id);
					
					/*
					 * 替换前备份原来的现状图
					 */
					Te01_slave baktz = new Te01_slave();
					PropertyInject.copyProperty(oldtz, baktz, new String[]{"serialVersionUID"});
					baktz.setId(null);
					baktz.setSlave_type("机房现状图备份");
					dao.saveObject(baktz);
					log.error("aaa");
					/*
					 * 更换现状图
					 */
					String filename = "";
					filename = oldtz.getId() + "Slave" + newtz.getExt_name();
					log.error("bbb");
					oldtz.setFtp_url(readandwrite(newtz.getFtp_url(), filename, request));
					log.error("ccc");
					oldtz.setFile_name(newtz.getFile_name());
					oldtz.setExt_name(newtz.getExt_name());
					oldtz.setFtp_date(new Date());
					td00.setXzt_gxsj(new Date());
					dao.saveObject(oldtz);
					dao.saveObject(td00);
					status = true;
					log.error("ddd");
				}
			}
		} catch (Exception e) {
			log.error("find error when update drawing [com.jfms.thread.ChangeDrawing_thread.java][5]:" + e + e.getMessage());
		}
		if (status) {
				/*
				 * 发短信给已经下载但还没有上传的其它设计人员
				 */
//				FlowSendTrigger flowSendTrigger = new FlowSendTrigger();
//				flowSendTrigger.setQueryService(queryService);
//				flowSendTrigger.setSaveService(saveService);
//				flowSendTrigger.informOtherDesignerOver(paraMap);
				out.print("{\"statusCode\":\"200\", \"message\":\"更新现状图成功\", \"navTabId\":\"_current\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");
		} else {
			out.print("{\"statusCode\":\"300\", \"message\":\"更新失败\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");
		}
		return null;
	}
}
