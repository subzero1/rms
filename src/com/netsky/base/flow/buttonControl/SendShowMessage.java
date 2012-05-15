package com.netsky.base.flow.buttonControl;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Tb03_relation;
import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.utils.convertUtil;
import com.netsky.base.utils.FtpUtil;
import com.netsky.base.baseObject.ResultObject;

/**
 * @description:
 * 判断发送是否满足条件，如果不满足，给予提醒或不显示按扭
 * @class name:com.netsky.base.flow.buttonControl.SendShowMessage
 * @author leexiangyu Oct 09, 2011
 */
public class SendShowMessage extends ButtonControl {
	
	/**
	 * 判断发送是否满足条件，如果不满足，给予提醒
	 * 参数中需包含：project_id,relation_id
	 *　重载方法：checkCondition
	 * (non-Javadoc)
	 * @see com.netsky.base.flow.buttonControl.ButtonControl#checkCondition(java.util.Map)
	 */
	private  Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public String checkCondition(Map paraMap) {
		
		String returnStr = "OK";
		Long relation_id = MapUtil.getLong(paraMap,"relation_id");
		Long project_id = MapUtil.getLong(paraMap,"project_id");
		Long module_id = MapUtil.getLong(paraMap,"module_id");
		Long doc_id = MapUtil.getLong(paraMap,"doc_id");
		StringBuffer hsql = new StringBuffer();
		
		Tb03_relation tb03 = (Tb03_relation)queryService.searchById(Tb03_relation.class, relation_id);
		String relation_desc = convertUtil.toString(tb03.getDescription());
		
		/**
		 * 机房如果没有现状图不能走申请流程
		 */
		if ((module_id == 101 || module_id == 102) && relation_desc.trim().indexOf("设计管理 → 设计人员") != -1) {
			hsql.delete(0, hsql.length());
			hsql.append("select td12.jfmc as jfmc ");
			hsql.append("from Td12_gljf td12 ");
			hsql.append("where not exists (select 'x' from Te01_slave te01 ");
			hsql.append("where te01.module_id = 200 ");
			hsql.append("and td12.jf_id = te01.doc_id ");
			hsql.append("and slave_type='机房现状图') ");
			hsql.append("and td12.parent_id = ");
			hsql.append(doc_id);
			List list = queryService.searchList(hsql.toString());
			
			if(list != null && list.size() > 0 ){
				String jfmcs = "";
				for(Object obj:list){
					jfmcs += (String)obj+"<br>";
				}
				returnStr = "以下机房无现状图，不能进行后续操作！<br>请从【机房平面管理】上传现状图！<br>"+jfmcs;
			} else {
				returnStr ="OK";
			}
		}
		
		/**
		 * 不上传图纸不能上报审核
		 */
		if ((module_id == 101 || module_id == 102) && relation_desc.trim().indexOf("设计人员 → 项目管理") != -1) {
			
			hsql.delete(0, hsql.length());
			hsql.append("select td12.jfmc as jfmc ");
			hsql.append("from Td12_gljf td12 ");
			hsql.append("where not exists (select 'x' from Te01_slave te01 ");
			hsql.append("where te01.module_id = ");
			hsql.append(module_id);
			hsql.append(" and td12.id = te01.doc_id ");
			hsql.append("and slave_type like '%设计图') ");
			hsql.append("and td12.parent_id = ");
			hsql.append(doc_id);
			List list = queryService.searchList(hsql.toString());
			
			if(list != null && list.size() > 0 ){
				String jfmcs = "";
				for(Object obj:list){
					jfmcs += (String)obj+"<br>";
				}
				returnStr = "以下机房您还未上传图纸!<br>请点右边【图纸】执行操作！<br><b>"+jfmcs+"</b>";
			}
			
			/**
			 * 判断是否图纸版本是否符合规范
			*/ 
			String url = null;	
			String jfmc = null;
			hsql.delete(0, hsql.length());
			hsql.append("select td12.jfmc as jfmc,te01.ftp_url as ftp_url ");
			hsql.append("from Td12_gljf td12,Te01_slave te01 ");
			hsql.append("where td12.project_id = te01.project_id ");
			hsql.append("and td12.id = te01.doc_id ");
			hsql.append("and te01.slave_type like '%设计图' ");
			hsql.append("and te01.project_id = ");
			hsql.append(doc_id);
			
			ResultObject ro = queryService.search(hsql.toString());
			if(ro.next()){
				url = (String)ro.get("ftp_url");
				jfmc = (String)ro.get("jfmc");
				
				InputStream is = null;
				try {
					FtpUtil fu = new FtpUtil();
					fu.ftpConnect();
					String file_path = url.substring(0, url.lastIndexOf("/"));
					String file_name = url.substring(url.lastIndexOf("/") + 1);
					fu.changeDirectory(file_path);
					is = fu.retrieveFileStream(file_name);
					byte[] b = new byte[5];
					is.read(b);
					fu.disconnect();
					
					/*
					 * 对应16进制分别为 41，43，31，30，31    对应10进制分别为 65，67，49，48，49
					 * 使用UltraEdit打开图纸查看前几个字节。
					 */
					if(b[0] == 65 && b[1] == 67 && b[2] == 49 && b[3] == 48 && b[4] == 49);
					else{
						returnStr = "以下机房上传的图纸版本不对，请重新上传<br><b>"+jfmc+"</b>";
					}
				}
				catch(Exception e){
					log.error(e + e.getMessage());
					returnStr = "程序异常，请重试";
				}
			}
			
		}
		
		/**
		 * 不选择设计人员不能继续走流程
		 */
		if (module_id == 102 && relation_desc.trim().indexOf("项目管理 → 设计管理") != -1) {
			
			hsql.delete(0, hsql.length());
			hsql.append("select id ");
			hsql.append("from Td11_jfpmsq td11 ");
			hsql.append("where td11.sjry is null ");
			hsql.append("and td11.project_id = ");
			hsql.append(project_id);
			hsql.append(" and td11.id = ");
			hsql.append(doc_id);
			List list = queryService.searchList(hsql.toString());
			
			if(list != null && list.size() > 0 ){
				returnStr = "请先指派设计人员，并点上方【保存】按钮!";
			} else {
				returnStr ="OK";
			}
		}
		
		return returnStr;	
	}

	/**
	 *　重载方法：isShow
	 * @see com.netsky.base.flow.buttonControl.ButtonControl#getUsers(java.util.Map)
	 */
	@Override
	public boolean isShow(Map paraMap) {
		
		return true;
	}

	
	/**
	 *　重载方法：getUsers
	 * (non-Javadoc)
	 * @see com.netsky.base.flow.buttonControl.ButtonControl#getUsers(java.util.Map)
	 */
	@Override
	public List<Ta03_user> getUsers(Map paraMap) {
		
		return null;
	}

}
