package com.netsky.base.flow.buttonControl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Tb03_relation;
import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.utils.convertUtil;
import com.netsky.base.baseObject.PropertyInject;
import com.rms.dataObjects.form.Td00_gcxx;
import com.rms.dataObjects.form.Td01_xmxx;


/**
 * @description:
 * 外协单位派工，派发到所选择的外协单位中去
 * @class name:com.netsky.base.flow.buttonControl.FsdWxdw
 * @author Administrator Mar 22, 2010
 */
public class FsdWxdw extends ButtonControl {

	/**
	 * 日志处理类
	 */
	private Logger log = Logger.getLogger(this.getClass());
	
	/**
	 *　重载方法：checkCondition
	 * (non-Javadoc)
	 * @see com.netsky.base.flow.buttonControl.ButtonControl#checkCondition(java.util.Map)
	 */
	public String checkCondition(Map paraMap) {
		return "OK";
	}

	/**
	 *　重载方法：getUsers
	 *  派发工单至外协单位，获得本项目对应外协单位接单人员
	 * (non-Javadoc)
	 * @see com.netsky.base.flow.buttonControl.ButtonControl#getUsers(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List<Ta03_user> getUsers(Map paraMap) {

		try{
			/*
			 * 获得项目信息
			 */
			Object obj = queryService.searchById(Td00_gcxx.class, MapUtil.getLong(paraMap,"project_id"));
			if(obj == null){
				obj = queryService.searchById(Td01_xmxx.class, MapUtil.getLong(paraMap,"project_id"));
				if(obj == null){
					return null;
				}
			}
			
			//发送关系
			Tb03_relation tb03 = null;
			if(paraMap.containsKey("tb03")){
				tb03 = (Tb03_relation)paraMap.get("tb03");
			} else {
				return null;
			}
			//获得Moudle_id 
			int module_id = MapUtil.getInteger(paraMap, "module_id");
			String tb03_remark = convertUtil.toString(tb03.getDescription());
			int x1 = tb03_remark.indexOf("→");
			int x2 = tb03_remark.indexOf("外协施工");
			int x3 = tb03_remark.indexOf("外协设计");
			int x4 = tb03_remark.indexOf("外协监理");
			int x5 = tb03_remark.indexOf("施工负责人");
			int x6 = tb03_remark.indexOf("设计人员");
			
			//为Wxdw赋值
			String wxdw = "";
			String zyqy = (String)PropertyInject.getProperty(obj, "ssdq");
			if(x2 > x1){
				wxdw = (String)PropertyInject.getProperty(obj, "sgdw");
			}
			else if(x3 > x1){
				wxdw = (String)PropertyInject.getProperty(obj, "sjdw");
			}
			else if(x4 > x1){
				wxdw = (String)PropertyInject.getProperty(obj, "jldw");
			}
			else if(x5 > x1){
				wxdw = (String)PropertyInject.getProperty(obj, "sgdw");
			}
			else if(x6 > x1){
				wxdw = (String)PropertyInject.getProperty(obj, "sjdw");
			}
			else{
				wxdw = "";
			}
			StringBuffer hsql = new StringBuffer();
			hsql.append(" select ta03 from tf04_wxdw_user tf04,Ta03_user ta03,Tf01_wxdw tf01,Ta13_sta_node ta13,Ta11_sta_user ta11");
			hsql.append(" where tf04.user_id = ta03.id and tf04.wxdw_id = tf01.id ");
			hsql.append(" and ta13.station_id = ta11.station_id");
			hsql.append(" and ta11.user_id = tf04.user_id");
			hsql.append(" and ta13.node_id = ");
			hsql.append(tb03.getDest_id().longValue());
			hsql.append(" and tf01.mc  = '");
			hsql.append(wxdw + "'");
			hsql.append(" and tf04.area like '%");
			hsql.append(zyqy + "%'");
			List<Ta03_user> list = (List<Ta03_user>)queryService.searchList(hsql.toString());
			if(list == null || list.size() == 0){
				hsql = new StringBuffer();
				hsql.append(" select ta03 from Tf09_wxdw_user tf09,Ta03_user ta03,Tf01_wxdw tf01,Ta13_sta_node ta13,Ta11_sta_user ta11");
				hsql.append(" where tf09.user_id = ta03.id and tf09.wxdw_id = tf01.id ");
				hsql.append(" and ta13.station_id = ta11.station_id");
				hsql.append(" and ta11.user_id = tf09.user_id");
				hsql.append(" and ta13.node_id = ");
				hsql.append(tb03.getDest_id().longValue());
				hsql.append(" and tf01.mc  = '");
				hsql.append(wxdw + "'");
				list = (List<Ta03_user>)queryService.searchList(hsql.toString());
			}
			return list;	
		}
		catch(Exception e){
			log.error(e + e.getMessage());
			return null;
		}
	}

	/**
	 *　重载方法：isShow
	 *	送监理单位，或发送项目经理是否显示
	 * (non-Javadoc)
	 * @see com.netsky.base.flow.buttonControl.ButtonControl#isShow(java.util.Map)
	 */
	public boolean isShow(Map paraMap) {
		return true;
	}

}
