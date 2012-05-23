package com.rms.flowTrigger;

import java.io.Serializable;
import java.util.*;

import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.utils.NumberFormatUtil;
import com.netsky.base.utils.PHSService;
import com.netsky.base.baseObject.ResultObject;

/**
 * @author leexiangyu
 *
 */
public class FlowSendTrigger extends com.netsky.base.flow.trigger.Trigger implements Serializable{

	private StringBuffer errMessage ;

	/**
	 * @流程中调用：下发变更单或申请单到设计人员时发短信给项目管理
	 */
	public String process(Map map){

		ResultObject ro = null;
		QueryBuilder queryBuilder = null;
		StringBuffer sql = new StringBuffer();
		Long project_id = convertUtil.toLong((String)map.get("project_id"));
		Long module_id = convertUtil.toLong((String)map.get("module_id"));
		try{
			sql.append("select tb15.user_id from Tb15_docflow tb15,Tb02_node tb02 where tb15.project_id="+project_id);
			sql.append(" and tb02.remark like '%[项目管理岗]%' ");
			sql.append(" and tb15.node_id=tb02.id order by tb15.id desc ");
			List<Long> tmpList = (List<Long>)queryService.searchList(sql.toString());
			Long user_id = -1L;
			if (tmpList != null && tmpList.size() > 0){
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
						sql.append("select xmmc from Td11_jfpmsq where project_id="+project_id);
						List<Object[]> tmpList2 = (List<Object[]>)queryService.searchList(sql.toString());
						String xmmc = "";
						String jfmc = "";
						if (tmpList2!=null && tmpList2.size()!=0){
							jfmc = (String)tmpList2.get(0)[0];
							xmmc = (String)tmpList2.get(0)[1];
						}
						message_phone.append(sheetname);
						message_phone.append("已经审批同意，并送至设计人员。项目名称：");
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
	
	/**
	 * @描述：更新现状图时通知设计人员
	 */
	public String informDesignerOver(Map map){

		ResultObject ro = null;
		QueryBuilder queryBuilder = null;
		StringBuffer sql = new StringBuffer();
		Long project_id = convertUtil.toLong(map.get("project_id"));
		Long module_id = convertUtil.toLong(map.get("module_id"));
		try{
			sql.append("select distinct tb15.user_id from Tb15_docflow tb15,Tb02_node tb02 where tb15.project_id="+project_id);
			sql.append(" and (tb02.remark like '%[设计人员]%' or tb02.remark like '%[项目管理岗]%') ");
			sql.append(" and tb15.node_id=tb02.id ");
			List<Long> tmpList = (List<Long>)queryService.searchList(sql.toString());
			
			for(Object obj:tmpList) {
				Long user_id = (Long)obj;

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
					sql.append("select xmmc from Td11_jfpmsq where project_id="+project_id);
					List<Object> tmpList2 = (List<Object>)queryService.searchList(sql.toString());
					String xmmc = "";
					if (tmpList2!=null && tmpList2.size()!=0){
						xmmc = (String)tmpList2.get(0);
					}
					message_phone.append(sheetname);
					message_phone.append("现状图已更新。项目名称：");
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
		catch(Exception e){
			e.printStackTrace();
			return sql.toString();
		}
		return "success";
	}
	
	/**
	 * @描述：更新现状图时，通知其它已经下载了本机房现状图但尚未
	 * 上传的设计人员重新下载，并在此张图纸上重新修改
	 */
	public String informOtherDesignerOver(Map map){

		ResultObject ro = null;
		StringBuffer sql = new StringBuffer();
		Long jf_id = convertUtil.toLong(map.get("jf_id"));
		
		try{
			/*
			 * 预定义消息内容
			 */
			PHSService phs = new PHSService();
			phs.setSaveService(saveService);
			StringBuffer message_phone = new StringBuffer();
			message_phone.append("$user,你好，【$xmmc】中，【$jfmc】现状图已被更新,");
			message_phone.append("请及时下载最新的现状图，并在此基础上修改，");
			message_phone.append("否则会导致此机房现状图冲突");
			message_phone.append("  【rms系统】");

			sql.delete(0, sql.length());
			sql.append("select ta03.name as name ,ta03.mobile_tel as tel ,td11.xmmc as xmmc ,td12.jfmc as jfmc ");
			sql.append("from Td11_jfpmsq td11,Td12_gljf td12,Ta03_user ta03 ");
			sql.append("where td11.id = td12.parent_id ");
			sql.append("and td11.sjry = ta03.name ");
			sql.append("and xzsj is not null "); 
			sql.append("and scsj is null ");
			sql.append("and td12.jf_id = ");
			sql.append(jf_id);
			ro = this.queryService.search(sql.toString());
			while(ro.next()){

				String t_name = (String)ro.get("name"); //接收人姓名
				String tel = convertUtil.toString(ro.get("tel")); //接收人电话
				String t_xmmc = (String)ro.get("xmmc"); //项目名称
				String t_jfmc = (String)ro.get("jfmc"); //机房名称
				if (NumberFormatUtil.isNumeric(tel) && tel.length() == 11){
					
					String t_msg = message_phone.toString();
					t_msg = t_msg.replace("$user", t_name);
					t_msg = t_msg.replace("$xmmc", t_xmmc);
					t_msg = t_msg.replace("$jfmc", t_jfmc);
					
					//发送短信
					phs.setMsg(t_msg);
					phs.setRecvNum(tel);
					String state = phs.sendSMS();
					phs.dxjl("管理员",tel, "手机短信", message_phone.toString(), state);// 短信发送记录
				}else{
					throw new Exception("电话不是11位纯数字");
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return sql.toString();
		}
		return "success";
	}
}
