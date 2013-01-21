package com.rms.flowTrigger;

import java.io.Serializable;
import java.util.Map;
import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Tb15_docflow;

/**
 * 概预算接口
 * @author lee.xiangyu
 * @create 2010-02-04
 */
public class ApproveSuccessAction extends com.netsky.base.flow.trigger.Trigger implements Serializable {
	
	public String process(Map paraMap) {
				
		
		StringBuffer sql = new StringBuffer("");
		
		
		Long project_id = MapUtil.getLong(paraMap,"project_id");
		Long node_id = MapUtil.getLong(paraMap,"node_id");
		Long module_id = MapUtil.getLong(paraMap,"module_id");
		Long user_id = MapUtil.getLong(paraMap,"user_id");
		Long doc_id = MapUtil.getLong(paraMap,"doc_id");
		String node_name = null;
		Integer approve_result = MapUtil.getInteger(paraMap,"approve_result");

		ResultObject ro = null;
		
		try{
			/**
			 * 获得节点名称
			 */
			sql.delete(0, sql.length());
			sql.append("select name from Tb02_node where id = ");
			sql.append(node_id);
			ro = queryService.search(sql.toString());
			if(ro.next()){
				node_name = convertUtil.toString((String)ro.get("name"));
			}

			/**
			 * 审结同意
			 */
			if(approve_result == 4){
					
				/**
				 * 设计时限调整单集中设计岗审结同意
				 */
				if((module_id == 110 || module_id == 111) && node_name.indexOf("资源确认") != -1){
					Ta03_user user = (Ta03_user)queryService.searchById(Ta03_user.class, user_id);
					saveService.updateByHSql("update Td00_gcxx set zyqrsj = sysdate,zygly='"+user.getName()+"' where id = "+project_id);
					saveService.updateByHSql("update Td01_xmxx set zyqrsj = sysdate,zygly='"+user.getName()+"' where id = "+project_id);
				}
				
				/**
				 * 需求书审核岗审结同意
				 */
				if(module_id == 109 && node_name.indexOf("需求审核") != -1){
					Ta03_user user = (Ta03_user)queryService.searchById(Ta03_user.class, user_id);
					saveService.updateByHSql("update Td06_xqs set xqshsj = sysdate,xqshr='"+user.getName()+"' where id = "+project_id);
					ro = queryService.search("select tb15 from Tb15_docflow tb15 where node_id = 10901 and project_id = "+project_id); 
					if(ro.next()){
						Tb15_docflow tb15 = (Tb15_docflow)ro.get("tb15");
						Long tb15_id = tb15.getId();
						Long opernode_id = tb15.getOpernode_id();
						saveService.updateByHSql("update Tb15_docflow set doc_status = 0 where id = "+tb15_id);
						saveService.updateByHSql("update Tb12_opernode set node_status = 0 where id = "+opernode_id);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	public String unProcess(Map paraMap) {	

		return null;
	}
}
