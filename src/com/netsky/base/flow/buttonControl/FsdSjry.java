package com.netsky.base.flow.buttonControl;

import java.util.List;
import java.util.Map;

import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Tb03_relation;
import com.netsky.base.flow.utils.MapUtil;

/**
 * @description:
 * 派发设计人员
 * @class name:com.netsky.base.flow.buttonControl.FsdSjry
 * @author lee.xiangyu Sep. 11, 2011
 */
public class FsdSjry extends ButtonControl {

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
		hsql.append(" select ta03 from Td11_jfpmsq td11,Ta03_user ta03 ");
		hsql.append(" where td11.sjry = ta03.name ");
		hsql.append(" and td11.id = ");
		hsql.append(MapUtil.getLong(paraMap,"doc_id"));
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
