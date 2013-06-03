package com.rms.service.impl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
			
			sql.delete(0, sql.length());
			sql.append("select hdgw,isht from td09_ddhdxx where project_id = ");
			sql.append(project_id);
			sql.append(" order by id desc ");
			ResultObject ro = queryService.search(sql.toString());
			if(ro.next()){
				hdgw = convertUtil.toString(ro.get("hdgw"));
				isht = convertUtil.toString(ro.get("isht"));
			}
			
			Td00_gcxx td00 = (Td00_gcxx)queryService.searchById(Td00_gcxx.class,project_id);
			ddzt = convertUtil.toString(td00.getDdzt());
			if(hdgw.equals("设计单位") && !ddzt.equals("工单已回复")){
				ddzt = "设计已回复";
			}
			else if(hdgw.equals("施工单位") && !ddzt.equals("工单已回复")){
				ddzt = "施工已回复";
			}
			else if(hdgw.equals("项目管理员")){
				if(isht.equals("")){
					ddzt = "项目管理员已回复";
				}
				else if(isht.equals("回退设计")){
					ddzt = "设计已派发";
				}
				else if(isht.equals("回退施工")){
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
