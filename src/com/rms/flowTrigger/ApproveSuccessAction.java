package com.rms.flowTrigger;

import java.io.Serializable;
import java.util.Map;

import com.netsky.base.flow.component.Turnback;
import com.netsky.base.flow.component.BaseUtil;
import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Tb15_docflow;
import com.rms.dataObjects.form.Td01_xmxx;
import com.rms.dataObjects.form.Td08_pgspd;

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
				 * 资源确认单 资源确认岗审结同意
				 */
				if((module_id == 110 || module_id == 111) && node_name.indexOf("资源确认") != -1){
					Ta03_user user = (Ta03_user)queryService.searchById(Ta03_user.class, user_id);
					saveService.updateByHSql("update Td00_gcxx set zyqrsj = sysdate,zyyszt = '通过',zygly='"+user.getName()+"' where id = "+project_id);
					saveService.updateByHSql("update Td01_xmxx set zyqrsj = sysdate,zyyszt = '通过',zygly='"+user.getName()+"' where id = "+project_id);
					
					saveService.updateByHSql("update Td00_gcxx set ycystg = '是' where ycystg is null and id = "+project_id);
					saveService.updateByHSql("update Td01_xmxx set ycystg = '是' where ycystg is null and id = "+project_id);
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
				
				/**
				 * 派工审批单审结同意
				 */
				if((module_id == 112 || module_id == 113) && node_name.indexOf("派工审核") != -1){
					StringBuffer hql=new StringBuffer();
					Td01_xmxx xmxx; 
					xmxx=(Td01_xmxx) queryService.searchById(Td01_xmxx.class, project_id);
					
					hql.append("select td08 from Td08_pgspd td08 where 1=1 ");
					hql.append("and id=");
					hql.append(doc_id);
					hql.append(" and project_id =");
					hql.append(project_id);
					ro = queryService.search(hql.toString()); 
					if(ro.next()){
						Td08_pgspd td08 = (Td08_pgspd)ro.get("td08");
						String sjxzdw = td08.getSjxzdw();
						String sdxpyy = td08.getSdxpyy();
						if (td08.getSplb().equals("更改合同额")) {  
							xmxx.setSjhtje(td08.getGgsjhte());
							xmxx.setSghtje(td08.getGgsghte());
							xmxx.setJlhtje(td08.getGgjlhte());
							saveService.save(xmxx);
							}else  {
								if(module_id == 112){
									saveService.updateByHSql("update Td01_xmxx set sgdw = '"+sjxzdw+"',sdpgyy = '"+sdxpyy+"' where id = "+project_id);
								}
								else{ 
									saveService.updateByHSql("update Td00_gcxx set sgdw = '"+sjxzdw+"',sdpgyy = '"+sdxpyy+"' where id = "+project_id);
									}
								saveService.updateByHSql("update Td08_pgspd set sp_flag=1 where id = "+doc_id+" and project_id="+project_id);
							}
						}
				}
			}
			
			/**
			 * 审结修改
			 */
			if(approve_result == 5){
					
				/**
				 * 资源确认单 资源确认岗审结同意
				 */
				if((module_id == 110 || module_id == 111) && node_name.indexOf("资源确认") != -1){
					Ta03_user user = (Ta03_user)queryService.searchById(Ta03_user.class, user_id);
					saveService.updateByHSql("update Td00_gcxx set zyyszt = '回退',zygly='"+user.getName()+"' where id = "+project_id);
					saveService.updateByHSql("update Td01_xmxx set zyyszt = '回退',zygly='"+user.getName()+"' where id = "+project_id);
					
					saveService.updateByHSql("update Td00_gcxx set ycystg = '否' where ycystg is null and id = "+project_id);
					saveService.updateByHSql("update Td01_xmxx set ycystg = '否' where ycystg is null and id = "+project_id);
				}
				
//				Map<String, Object> paraMap = new HashMap();
//				paraMap.put("project_id", project_id);
//				paraMap.put("doc_id", doc_id);
//				paraMap.put("module_id", module_id);
//				paraMap.put("node_id", node_id);
//				paraMap.put("user_name", user_name);
//				paraMap.put("user_id", user_id);
//				paraMap.put("opernode_id", opernode_id);
				BaseUtil baseUtil = new BaseUtil();
				baseUtil.setQueryService(this.queryService);
				baseUtil.setSaveService(this.saveService);
				
				Turnback turnback = new Turnback();
				turnback.setQueryService(this.queryService);
				turnback.setSaveService(this.saveService);
				turnback.setBaseUtil(baseUtil);
				turnback.handleRequest(paraMap);
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
