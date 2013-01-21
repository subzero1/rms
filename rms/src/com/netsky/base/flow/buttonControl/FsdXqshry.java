package com.netsky.base.flow.buttonControl;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;

import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Tb03_relation;
import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.utils.convertUtil;
import com.rms.dataObjects.form.Td06_xqs;

/**
 * @description:
 * 合作单位派工，派发到所选择的合作单位中去
 * @class name:com.netsky.base.flow.buttonControl.FsdWxdwSgd
 * @author Administrator Mar 22, 2012
 */
public class FsdXqshry extends ButtonControl {

	/**
	 *　重载方法：checkCondition
	 * (non-Javadoc)
	 * @see com.netsky.base.flow.buttonControl.ButtonControl#checkCondition(java.util.Map)
	 */
	public String checkCondition(Map paraMap) {
		return "OK";
	}

	@SuppressWarnings("unchecked")
	public List<Ta03_user> getUsers(Map paraMap) {
		Td06_xqs td06 = (Td06_xqs)queryService.searchById(Td06_xqs.class, MapUtil.getLong(paraMap,"project_id"));
		StringBuffer hsql = new StringBuffer();
		if(td06 == null){
			return null;
		}

		List list = new LinkedList();
		String sswg = convertUtil.toString(td06.getSswg(),"xxx");
		hsql.delete(0, hsql.length());
		hsql.append("select ta03 ");
		hsql.append("from Ta03_user ta03,Ta11_sta_user ta11,Ta13_sta_node ta13 ");
		hsql.append("where ta03.id = ta11.user_id "); 
		hsql.append("and ta11.station_id = ta13.station_id ");
		hsql.append("and ta13.node_id = 10902 ");
		hsql.append("and ta03.remark like '%");
		hsql.append(sswg);
		hsql.append("%' ");
		ResultObject ro = queryService.search(hsql.toString());
		while(ro.next()){
			Ta03_user ta03 = (Ta03_user)ro.get("ta03");
			String t_sswg = convertUtil.toString(ta03.getRemark());
			if((","+t_sswg+",").indexOf(","+sswg+",") != -1){
				list.add(ta03);
			}
		}
		if(list == null || list.size() == 0){
			hsql.delete(0, hsql.length());
			hsql.append("select ta03 ");
			hsql.append("from Ta03_user ta03,Ta11_sta_user ta11,Ta13_sta_node ta13 ");
			hsql.append("where ta03.id = ta11.user_id "); 
			hsql.append("and ta11.station_id = ta13.station_id ");
			hsql.append("and ta13.node_id = 10902 ");
			list = queryService.searchList(hsql.toString());
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
		return true;
	}

}
