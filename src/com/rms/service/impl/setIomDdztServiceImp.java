package com.rms.service.impl;
import java.util.Date;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netsky.base.dataObjects.Tz05_thread_queue;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.service.QueryService;
import com.rms.dataObjects.form.Td00_gcxx;

@Service("setIomDdztService")
public class setIomDdztServiceImp {
	/**
	 * 数据服务
	 */
	@Autowired
	private Dao dao;
	
	/**
	 * 数据库查询操作服务
	 */
	@Autowired
	private QueryService queryService;
	
	private  Logger log = Logger.getLogger(this.getClass());

	public void load(Long project_id) throws Exception {
		
		StringBuffer sql = new StringBuffer("");
		try{
			String hdgw  = "";
			String isht = "";
			String ddzt = "";
			String sjdw = "";
			String sgdw = "";
			String xmgly = "";
			String ddgly = "";
			
			sql.delete(0, sql.length());
			sql.append("select hdgw,isht from Td09_ddhdxx where project_id = ");
			sql.append(project_id);
			sql.append(" order by id desc ");
			ResultObject ro = queryService.search(sql.toString());
			if(ro.next()){
				hdgw = convertUtil.toString(ro.get("hdgw"));
				isht = convertUtil.toString(ro.get("isht"));
			}
			
			Td00_gcxx td00 = (Td00_gcxx)queryService.searchById(Td00_gcxx.class,project_id);
			ddzt = convertUtil.toString(td00.getDdzt());
			sjdw = convertUtil.toString(td00.getSjdw());
			sgdw = convertUtil.toString(td00.getSgdw());
			xmgly = convertUtil.toString(td00.getXmgly());
			ddgly = convertUtil.toString(td00.getDdgly());
			String tel = null;
			if(hdgw.equals("设计单位") && !ddzt.equals("工单已回复")){
				ddzt = "设计已回复";
				
				sql.delete(0, sql.length());
				sql.append("select name,mobile_tel from Ta03_user where name = '"+xmgly+"'");
				ro = queryService.search(sql.toString());
				if(ro.next()){
					tel = convertUtil.toString(ro.get("mobile_tel"));
				}
				Tz05_thread_queue thread = new Tz05_thread_queue();
				thread.setInserttime(new Date());
				thread.setServicename("messageToPhoneService");
				JSONObject jo = new JSONObject();
				
				//jo.put("content", "您好，定单《"+td00.getGcmc()+"》设计已经回复，请及时处理  【RMS系统】");
				jo.put("content", "您好，定单《"+td00.getGcmc()+"》设计已经回复，请及时处理  ");
				jo.put("sender_name", "管理员");
				jo.put("additionTel", "");
				jo.put("reader_tel", tel);
				jo.put("reader_name", xmgly);
				
				thread.setRemark("设计已回复定单");
				thread.setStatus("未处理");
				thread.setType("sendTelMsg");
				thread.setParameters(jo.toString());
				dao.saveObject(thread);
			}
			else if(hdgw.equals("施工单位") && !ddzt.equals("工单已回复")){
				ddzt = "施工已回复";
				
				sql.delete(0, sql.length());
				sql.append("select name,mobile_tel from Ta03_user where name = '"+xmgly+"'");
				ro = queryService.search(sql.toString());
				if(ro.next()){
					tel = convertUtil.toString(ro.get("mobile_tel"));
				}
				Tz05_thread_queue thread = new Tz05_thread_queue();
				thread.setInserttime(new Date());
				thread.setServicename("messageToPhoneService");
				JSONObject jo = new JSONObject();
				
				//jo.put("content", "您好，定单《"+td00.getGcmc()+"》施工已经回复，请及时处理  【RMS系统】");
				jo.put("content", "您好，定单《"+td00.getGcmc()+"》施工已经回复，请及时处理  ");
				jo.put("sender_name", "管理员");
				jo.put("additionTel", "");
				jo.put("reader_tel", tel);
				jo.put("reader_name", xmgly);
				
				thread.setRemark("施工已回复定单");
				thread.setStatus("未处理");
				thread.setType("sendTelMsg");
				thread.setParameters(jo.toString());
				dao.saveObject(thread);
			}
			else if(hdgw.equals("项目管理员")){
				if(isht.equals("") || isht.equals("前进")){
					ddzt = "项目管理员已回复";
					
					sql.delete(0, sql.length());
					sql.append("select name,mobile_tel from Ta03_user where name = '"+ddgly+"'");
					ro = queryService.search(sql.toString());
					if(ro.next()){
						tel = convertUtil.toString(ro.get("mobile_tel"));
					}
					Tz05_thread_queue thread = new Tz05_thread_queue();
					thread.setInserttime(new Date());
					thread.setServicename("messageToPhoneService");
					JSONObject jo = new JSONObject();
					
					jo.put("content", "您好，定单《"+td00.getGcmc()+"》项目管理员已经回复，请及时处理。");
					jo.put("sender_name", "管理员");
					jo.put("additionTel", "");
					jo.put("reader_tel", tel);
					jo.put("reader_name", ddgly);
					
					thread.setRemark("施工已回复定单");
					thread.setStatus("未处理");
					thread.setType("sendTelMsg");
					thread.setParameters(jo.toString());
					dao.saveObject(thread);
				}
				else if(isht.indexOf("退设计") != -1 && !sjdw.equals("")){
					ddzt = "设计已派发";
				}
				else if(isht.indexOf("退施工") != -1 && !sgdw.equals("")){
					ddzt = "施工已派发";
				}
			}
			if(!ddzt.equals("")){
				td00.setDdzt(ddzt);
				dao.saveObject(td00);
			}
		}
		catch(Exception e){
			log.error("com.rms.service.impl.setIomDdztServiceImp:"+e.getMessage()+e);
		}
	}
}
