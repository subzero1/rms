package com.rms.flowTrigger;

import java.io.Serializable;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.QueryService;
import com.netsky.base.utils.NumberFormatUtil;
import com.netsky.base.utils.PHSService;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.baseObject.PropertyInject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta27_user_remind;
import com.netsky.base.dataObjects.Tb11_operflow;
import com.netsky.base.dataObjects.Tb13_operrelation;
import com.netsky.base.dataObjects.Tb15_docflow;

/**
 * @author zhangyi
 *
 */
public class FlowSendMsg extends com.netsky.base.flow.trigger.Trigger implements Serializable{

	private StringBuffer errMessage ;

	/**
	 * @param args
	 */
	public String process(Map map){

		ResultObject ro = null;
		QueryBuilder queryBuilder = null;
		StringBuffer sql = new StringBuffer();
		Long project_id = convertUtil.toLong((String)map.get("project_id"));
		Long opernode_id = convertUtil.toLong((String)map.get("opernode_id"));
		Long module_id = convertUtil.toLong((String)map.get("module_id"));
		Long node_id = convertUtil.toLong((String)map.get("node_id"));
		try{
			sql.append("select tb15.user_id from Tb13_operrelation tb13,Tb15_docflow tb15 where tb13.project_id="+project_id);
			sql.append(" and tb13.source_id="+opernode_id);
			sql.append(" and tb15.opernode_id=tb13.dest_id order by tb15.id");
			List<Long> tmpList = (List<Long>)queryService.searchList(sql.toString());
			Long user_id = -1L;
			if (tmpList != null && tmpList.size() == 1){
				user_id =tmpList.get(0);
			}
			if(user_id != -1L) {
				sql = sql.delete(0, sql.length());
				sql.append("select 1 from Ta27_user_remind where user_id="+user_id);
				sql.append(" and remind_type=2 and mobile_flag=0");
				tmpList = (List<Long>)queryService.searchList(sql.toString());
				if (tmpList == null || tmpList.size()==0){
					sql = sql.delete(0, sql.length());
					sql.append("select mobile_tel from Ta03_user where id="+user_id);
					List<String> tmpList1 = (List<String>)queryService.searchList(sql.toString());
					if (tmpList1 == null || tmpList1.size() == 0){
						throw new Exception("没这个人或这个人没电话");
					}else if (NumberFormatUtil.isNumeric(tmpList1.get(0)) && (tmpList1.get(0)).length() == 11){
						PHSService phs = new PHSService();
						phs.setSaveService(saveService);
						phs.setRecvNum(tmpList1.get(0));
						StringBuffer message_phone = new StringBuffer();
						message_phone.append("一张");
						String sheetname = "";
						if (module_id == 101L)
							sheetname = "机房平面申请单";
						else if (module_id == 102L) 
							sheetname = "机房平面变更单";
						else 
							throw new Exception("MODULE_ID不是101或102");
						sql = sql.delete(0, sql.length());
						String xmmc = "";
						/*
						sql.append("select xmmc from Td11_jfpmsq where project_id="+project_id);
						List tmpList2 = (List)queryService.searchList(sql.toString());
						
						if (tmpList2!=null && tmpList2.size()!=0){
							xmmc = (String)tmpList2.get(0);
						}
						*/
						message_phone.append(sheetname);
						message_phone.append("已发至您待办，请及时处理。项目名称：");
						message_phone.append(xmmc);
						message_phone.append("  【rms系统】");
						phs.setMsg(message_phone.toString());
						String state = phs.sendSMS();
						String fsr = "管理员";
						phs.dxjl(fsr,tmpList1.get(0), "手机短信", message_phone.toString(), state);// 短信发送记录
					}else{
						throw new Exception("电话不是11位纯数字");
					}
					
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return sql.toString();
		}
		return "success";
	}
	
	public String unProcess(Map map){
		return null;
	}
}
