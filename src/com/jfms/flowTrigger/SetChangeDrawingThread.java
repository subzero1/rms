package com.jfms.flowTrigger;

import java.util.Map;
import java.util.List;
import java.util.Date;

import org.apache.log4j.Logger;
import com.netsky.base.flow.trigger.Trigger;
import com.netsky.base.utils.convertUtil;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Tb17_approve;
import com.netsky.base.dataObjects.Tb02_node;
import com.jfms.dataObjects.info.Td11_jfpmsq;
import com.netsky.base.dataObjects.Tz05_thread_queue;

public class SetChangeDrawingThread extends Trigger {

	private  Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public String process(Map paraMap) throws Exception {
		
		/*
		 * 审批同意
		 */
		final Long isYES = 4L;
		
		/*
		 * 审批修改后再报
		 */
		final Long isNO = 5L;
		
		Long user_id = convertUtil.toLong(paraMap.get("user_id"));
		Long module_id = convertUtil.toLong(paraMap.get("module_id"));
		String user_name = convertUtil.toString(paraMap.get("user_name"));
		Long doc_id = convertUtil.toLong(paraMap.get("doc_id"));
		Long node_id = convertUtil.toLong(paraMap.get("node_id"));
		Long project_id = convertUtil.toLong(paraMap.get("project_id"));
		Long opernode_id = convertUtil.toLong(paraMap.get("opernode_id"));
		StringBuffer sql = null;
		ResultObject ro = null;
		
		try{
			/*
			 * 获得身份标识
			 */
			Tb02_node tb02 = (Tb02_node)queryService.searchById(Tb02_node.class, node_id);
			String node_remark = convertUtil.toString(tb02.getRemark()); 
			String identifier = null;
			if(node_remark.indexOf("设计管理岗") != -1 && node_remark.indexOf("上报审批") != -1){
				identifier = "DesignManager";
			}
			else if(node_remark.indexOf("规划管理岗") != -1 && node_remark.indexOf("上报审批") != -1){
				identifier = "PlanManager";
			}
			else{
				identifier = "";
				log.error("identifier is null ,please check !!!");
			}
			
			/*
			 * 获得审批结果
			 */
			QueryBuilder queryBuilder = new HibernateQueryBuilder(Tb17_approve.class);
			queryBuilder.eq("opernode_id", opernode_id);
			ro = queryService.search(queryBuilder);
			Integer check_result = null;
			if(ro.next()){
				Tb17_approve tb17 = (Tb17_approve)ro.get(Tb17_approve.class.getName());
				check_result = tb17.getCheck_result();
			}
			
			
			/**
			 * 判断流程是否经过规划岗，拆除流程不走规划岗
			 * 但后来流程有变化，拆除流程又需要报规划岗，为了防止以后修改起来简单，
			 * 所以将判断条件改成了‘拆除2’
			 * 如果以后拆除流程又不走规划岗，则再将‘拆除2’改成‘拆除’；
			 */
			boolean isRouteToPlanManager = false;
			Td11_jfpmsq td11 = (Td11_jfpmsq)queryService.searchById(Td11_jfpmsq.class, doc_id);
			if(convertUtil.toString(td11.getJsxz()).equals("拆除2")){
				isRouteToPlanManager = false;
			}
			else{
				sql = new StringBuffer("");
				sql.append("select id ");
				sql.append("from Td12_gljf td12 ");
				sql.append("where exists "); 
				sql.append("(select 'x' from Td00_jfxx td00 ,Tc01_property tc01 ,Tc02_bureau tc02 ");
				sql.append("where td00.id = td12.jf_id ");
				sql.append("and trim(tc02.type) = trim(tc01.name) ");
				sql.append("and trim(td00.jdmc) = trim(tc02.name) ");
				sql.append("and tc01.flag like '%[2]%') ");
				sql.append("and parent_id = ");
				sql.append(doc_id);
				List list  = queryService.searchList(sql.toString());
				if(list != null && list.size() > 0){
					isRouteToPlanManager = true;
				}
				else{
					isRouteToPlanManager = false;
				}
				
				/*
				 * 判断是否更新现状图（添加线程队列，具体的替换工作在线程中完成）
				 */
				if(check_result != null && check_result.intValue() == isYES.intValue()){
					if((identifier.equals("DesignManager") && !isRouteToPlanManager) 
							|| (identifier.equals("PlanManager") && isRouteToPlanManager)){
						Tz05_thread_queue tz05 = new Tz05_thread_queue();
						tz05.setType("GHXZT");
						tz05.setInsert_date(new Date());
						tz05.setStatus("未处理");
						String paras = "project_id="+project_id+"&module_id="+module_id+"&doc_id="+doc_id;
						tz05.setParas(paras);
						saveService.save(tz05);
					}
				}
			}
		}
		catch(Exception e){
			log.error("found error when add change drawing thread[com.jfms.flowTrigger.SetChangeDrawingThread]"+e+e.getMessage());
		}
		return null;
	}

	@Override
	public String unProcess(Map paraMap) throws Exception {
		return null;
	}

}
