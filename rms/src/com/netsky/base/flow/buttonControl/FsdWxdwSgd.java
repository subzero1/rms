package com.netsky.base.flow.buttonControl;

import java.util.List;
import java.util.Map;

import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Tb03_relation;
import com.netsky.base.flow.utils.MapUtil;
import com.rms.dataObjects.form.Td00_gcxx;

/**
 * @description:
 * 外协单位派工，派发到所选择的外协单位中去
 * @class name:com.netsky.base.flow.buttonControl.FsdWxdwSgd
 * @author Administrator Mar 22, 2012
 */
public class FsdWxdwSgd extends ButtonControl {

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
		//td00_gcxx 信息
		Td00_gcxx td00 = (Td00_gcxx)queryService.searchById(Td00_gcxx.class, MapUtil.getLong(paraMap,"project_id"));
		if(td00 == null){
			return null;
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
		//为Wxdw赋值
		String wxdw = td00.getSgdw();
		String zyqy = td00.getSsdq();
		StringBuffer hsql = new StringBuffer();
		hsql.append(" select ta03 from Tf09_wxdw_user tf09,Ta03_user ta03,Tf01_wxdw tf01,Ta13_sta_node ta13,Ta11_sta_user ta11");
		hsql.append(" where tf09.user_id = ta03.id and tf09.wxdw_id = tf01.id ");
		hsql.append(" and ta13.station_id = ta11.station_id");
		hsql.append(" and ta11.user_id = tf09.user_id");
		hsql.append(" and ta13.node_id = ");
		hsql.append(tb03.getDest_id().longValue());
		hsql.append(" and tf01.mc  = '");
		hsql.append(wxdw + "'");
		hsql.append(" and tf09.area like '%");
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

	/**
	 *　重载方法：isShow
	 *	送监理单位，或发送项目经理是否显示
	 * (non-Javadoc)
	 * @see com.netsky.base.flow.buttonControl.ButtonControl#isShow(java.util.Map)
	 */
	public boolean isShow(Map paraMap) {
		//本项目是否委托监理
		boolean isWtjl = false;
		List tmpList = queryService.searchList(" select 'x' from Tb15_docflow tb15 where tb15.module_id = 129 and tb15.project_id = ?",
				new Object[] { MapUtil.getLong(paraMap, "project_id") });
		isWtjl = tmpList != null &&tmpList.size() > 0;
		//发送关系
		if("外协施工 → 外协监理".equals(paraMap.get("tb03_desc"))){
			return isWtjl;
		} else {
			return !isWtjl;
		}
	}

}
