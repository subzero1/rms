package com.rms.service.impl;

import java.util.Date;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Tz05_thread_queue;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.convertUtil;
import com.rms.dataObjects.form.Td00_gcxx;

@Service("executeWhenSaveService")
public class ExecuteWhenSaveServiceImp{
	@Autowired
	private SaveService saveService;

	@Autowired
	private QueryService queryService;
	
	private  Logger log = Logger.getLogger(this.getClass());

	/**
	 *　重载方法：exec
	 * (non-Javadoc)
	 * @see com.netsky.pss.service.form.ExecuteWhenSaveService#exec(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String)
	 */
	public void exec(Long project_id,Long doc_id,Long module_id,Long node_id,Long user_id,String user_name) throws Exception{
		
		StringBuffer sql = new StringBuffer("");
		ResultObject ro  = null;
		try{
			if(module_id == 114){
				Td00_gcxx td00 = (Td00_gcxx)queryService.searchById(Td00_gcxx.class, project_id);
				if(td00 != null){
					String sjdw = convertUtil.toString(td00.getSjdw());
					String sgdw = convertUtil.toString(td00.getSgdw());
					Long dxtzhzdw = convertUtil.toLong(td00.getDxtzhzdw(),0L);
					Long dxtzsjdwwc = convertUtil.toLong(td00.getDxtzsjdwwc(),0L);
					Long dxtzsgdwwc = convertUtil.toLong(td00.getDxtzsgdwwc(),0L);
					/*
					 * 短信通知设计单位
					 */
					if(!sjdw.equals("") && dxtzhzdw == 1 && dxtzsjdwwc == 0){
						sql.delete(0, sql.length());
						sql.append("select ta03.name as jsr,ta03.mobile_tel as mobile_tel ");
						sql.append("from Tf01_wxdw tf01,Tf04_wxdw_user tf04,Ta03_user ta03 ");
						sql.append("where tf01.id = tf04.wxdw_id ");
						sql.append("and tf04.user_id = ta03.id ");
						sql.append("and tf01.mc = '");
						sql.append(sjdw);
						sql.append("'");
						ro = queryService.search(sql.toString());
						while(ro.next()){
							String tel = convertUtil.toString(ro.get("mobile_tel"));
							String jsr = convertUtil.toString(ro.get("jsr"));
							
							Tz05_thread_queue thread = new Tz05_thread_queue();
							thread.setInserttime(new Date());
							thread.setServicename("messageToPhoneService");
							JSONObject jo = new JSONObject();
							
							jo.put("content", "你好，你收到一条新定单：《"+td00.getGcmc()+"》请及时处理  ");
							jo.put("sender_name", "管理员");
							jo.put("additionTel", "");
							jo.put("reader_tel", tel);
							jo.put("reader_name", jsr);
							
							thread.setRemark("通知设计单位");
							thread.setStatus("未处理");
							thread.setType("sendTelMsg");
							thread.setParameters(jo.toString());
							saveService.save(thread);
						}
						td00.setDxtzsjdwwc(1L);
						saveService.save(td00);
					}
					
					/*
					 * 短信通知施工单位
					 */
					if(!sgdw.equals("") && dxtzhzdw == 1 && dxtzsgdwwc == 0){

						sql.delete(0, sql.length());
						sql.append("select ta03.name as jsr,ta03.mobile_tel as mobile_tel ");
						sql.append("from Tf01_wxdw tf01,Tf04_wxdw_user tf04,Ta03_user ta03 ");
						sql.append("where tf01.id = tf04.wxdw_id ");
						sql.append("and tf04.user_id = ta03.id ");
						sql.append("and tf01.mc = '");
						sql.append(sgdw);
						sql.append("'");
						ro = queryService.search(sql.toString());
						while(ro.next()){
							String tel = convertUtil.toString(ro.get("mobile_tel"));
							String jsr = convertUtil.toString(ro.get("jsr"));
							
							Tz05_thread_queue thread = new Tz05_thread_queue();
							thread.setInserttime(new Date());
							thread.setServicename("messageToPhoneService");
							JSONObject jo = new JSONObject();
							
							jo.put("content", "你好，你收到一条新定单：《"+td00.getGcmc()+"》请及时处理  ");
							jo.put("sender_name", "管理员");
							jo.put("additionTel", "");
							jo.put("reader_tel", tel);
							jo.put("reader_name", jsr);
							
							thread.setRemark("通知施工单位");
							thread.setStatus("未处理");
							thread.setType("sendTelMsg");
							thread.setParameters(jo.toString());
							saveService.save(thread);
						}
						td00.setDxtzsgdwwc(1L);
						saveService.save(td00);
					}
				}
			}
		}
		catch(Exception e){
			log.error("executeWhenSaveService.exec() Error="+e+e.getMessage());
		}
	}
}
