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
		String relatioin_name = convertUtil.toString(tb03.getName());
		
		/**
		 * 不选择设计单位不能继续走流程
		 */
		if (module_id == 101) {
			
			if(relatioin_name.trim().indexOf("下发设计") != -1){
				hsql.delete(0, hsql.length());
				hsql.append("select id ");
				hsql.append("from Td01_xmxx td01 ");
				hsql.append("where td01.sjdw is null ");
				hsql.append("and td01.id = ");
				hsql.append(project_id);
				
				List list = queryService.searchList(hsql.toString());
				
				if(list != null && list.size() > 0 ){
					returnStr = "请先选择【设计单位】，并点上方【保存】按钮!";
				} else {
					returnStr ="OK";
				}
			}
			
			else if(relatioin_name.trim().indexOf("下发施工") != -1){
				hsql.delete(0, hsql.length());
				hsql.append("select id ");
				hsql.append("from Td01_xmxx td01 ");
				hsql.append("where td01.sgdw is null ");
				hsql.append("and td01.id = ");
				hsql.append(project_id);
				
				List list = queryService.searchList(hsql.toString());
				
				if(list != null && list.size() > 0 ){
					returnStr = "请先选择【施工单位】，并点上方【保存】按钮!";
				} else {
					returnStr ="OK";
				}
			}
			
			else if(relatioin_name.trim().indexOf("下发监理") != -1){
				hsql.delete(0, hsql.length());
				hsql.append("select id ");
				hsql.append("from Td01_xmxx td01 ");
				hsql.append("where td01.jldw is null ");
				hsql.append("and td01.id = ");
				hsql.append(project_id);
				
				List list = queryService.searchList(hsql.toString());
				
				if(list != null && list.size() > 0 ){
					returnStr = "请先选择【监理单位】，并点上方【保存】按钮!";
				} else {
					returnStr ="OK";
				}
			}
		}
		
		/**
		 * 不选择设计单位不能继续走流程
		 */
		if (module_id == 102 && relatioin_name.trim().indexOf("下发设计") != -1) {
			
			if(relatioin_name.trim().indexOf("下发设计") != -1){
				hsql.delete(0, hsql.length());
				hsql.append("select id ");
				hsql.append("from Td00_gcxx td00 ");
				hsql.append("where td00.sjdw is null ");
				hsql.append("and td00.id = ");
				hsql.append(project_id);
				
				List list = queryService.searchList(hsql.toString());
				
				if(list != null && list.size() > 0 ){
					returnStr = "请先选择【设计单位】，并点上方【保存】按钮!";
				} else {
					returnStr ="OK";
				}
			}
			
			else if(relatioin_name.trim().indexOf("下发施工") != -1){
				hsql.delete(0, hsql.length());
				hsql.append("select id ");
				hsql.append("from Td00_gcxx td00 ");
				hsql.append("where td00.sgdw is null ");
				hsql.append("and td00.id = ");
				hsql.append(project_id);
				
				List list = queryService.searchList(hsql.toString());
				
				if(list != null && list.size() > 0 ){
					returnStr = "请先选择【施工单位】，并点上方【保存】按钮!";
				} else {
					returnStr ="OK";
				}
			}
			
			else if(relatioin_name.trim().indexOf("下发监理") != -1){
				hsql.delete(0, hsql.length());
				hsql.append("select id ");
				hsql.append("from Td00_gcxx td00 ");
				hsql.append("where td00.jldw is null ");
				hsql.append("and td00.id = ");
				hsql.append(project_id);
				
				List list = queryService.searchList(hsql.toString());
				
				if(list != null && list.size() > 0 ){
					returnStr = "请先选择【监理单位】，并点上方【保存】按钮!";
				} else {
					returnStr ="OK";
				}
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
