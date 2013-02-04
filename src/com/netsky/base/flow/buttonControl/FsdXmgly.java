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
public class FsdXmgly extends ButtonControl {

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

		StringBuffer hsql = new StringBuffer();
	
		List list = new LinkedList();
		hsql.delete(0, hsql.length());
		hsql.append("select ta03 ");
		hsql.append("from Td00_gcxx td00,Ta03_user ta03 ");
		hsql.append("where td00.xmgly = ta03.name ");
		hsql.append("and td00.id = ");
		hsql.append(MapUtil.getLong(paraMap,"project_id"));
		list = queryService.searchList(hsql.toString());
		if(list == null || list.size() == 0){
			hsql.delete(0, hsql.length());
			hsql.append("select ta03 ");
			hsql.append("from Td01_xmxx td01,Ta03_user ta03 ");
			hsql.append("where td01.xmgly = ta03.name ");
			hsql.append("and td00.id = ");
			hsql.append(MapUtil.getLong(paraMap,"project_id"));
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
