package com.netsky.base.flow.buttonControl;

import java.util.List;
import java.util.Map;

import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Tb03_relation;
import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.baseObject.ResultObject;

/**
 * @description:
 * 派发设计人员
 * @class name:com.netsky.base.flow.buttonControl.FsdSjgl
 * @author lee.xiangyu Sep. 11, 2011
 */
public class FsdSjgl extends ButtonControl {

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
	 *  派发设计人员
	 * (non-Javadoc)
	 * @see com.netsky.base.flow.buttonControl.ButtonControl#getUsers(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List<Ta03_user> getUsers(Map paraMap) {
		
		StringBuffer hsql = new StringBuffer();
		hsql.append("select ta03.area_name as area_name ");
		hsql.append("from Tb15_docflow tb15,Tb02_node tb02,Ta03_user ta03 ");
		hsql.append("where tb02.id = tb15.node_id ");
		hsql.append("and tb15.user_id = ta03.id ");
		hsql.append("and tb02.name = '表单起草' ");
		hsql.append("and tb15.project_id = ");
		hsql.append(MapUtil.getLong(paraMap,"project_id"));
		String area_name = null;
		ResultObject ro = queryService.search(hsql.toString());
		if(ro.next()){
			area_name = convertUtil.toString(ro.get("area_name"));
		}

		hsql = new StringBuffer();
		hsql.append("select ta03 ");
		hsql.append("from Ta03_user ta03,Ta11_sta_user ta11,Ta02_station ta02 ");
		hsql.append("where ta03.id = ta11.user_id ");
		hsql.append("and ta11.station_id = ta02.id  ");
		hsql.append("and ta02.name like '%设计管理%' ");
		if(area_name != null && !area_name.equals("")){
			hsql.append("and ta03.area_name like '%");
			hsql.append(area_name);
			hsql.append("%' ");
		}
		return (List<Ta03_user>)queryService.searchList(hsql.toString());	
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
