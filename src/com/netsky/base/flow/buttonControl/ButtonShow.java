package com.netsky.base.flow.buttonControl;

import java.util.List;
import java.util.Map;

import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Tb03_relation;
import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.utils.convertUtil;
/**
 * @description:
* @class name:com.netsky.base.flow.buttonControl.ButtonShow
 * @author Administrator Mar 22, 2010
 */
public class ButtonShow extends ButtonControl {

	/**
	 * (non-Javadoc)
	 * @see com.netsky.base.flow.buttonControl.ButtonControl#checkCondition(java.util.Map)
	 */
	public String checkCondition(Map paraMap) {
		return "OK";
	}

	/**
	  * (non-Javadoc)
	 * @see com.netsky.base.flow.buttonControl.ButtonControl#getUsers(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List<Ta03_user> getUsers(Map paraMap) {
		return null;
	}

	/**
	 * (non-Javadoc)
	 * @see com.netsky.base.flow.buttonControl.ButtonControl#isShow(java.util.Map)
	 */
	public boolean isShow(Map paraMap) {
		
		QueryBuilder queryBuilder = null;
		StringBuffer sql = new StringBuffer("");
		ResultObject ro = null;
		
		try{
			Long node_id = convertUtil.toLong(paraMap.get("node_id"));
			Long relation_id = convertUtil.toLong(paraMap.get("relation_id"));
			Long project_id = convertUtil.toLong(paraMap.get("project_id"));
			Long module_id = convertUtil.toLong(paraMap.get("module_id"));
			
			Tb03_relation tb03 = (Tb03_relation)queryService.searchById(Tb03_relation.class, relation_id);
			String relation_desc = convertUtil.toString(tb03.getDescription());
			
			/**
			 * 拆除流程不走规划岗
			 * 但后来流程有变化，拆除流程又需要报规划岗，为了防止以后修改起来简单，
			 * 所以将判断条件改成了‘拆除2’
			 * 如果以后拆除流程又不走规划岗，则再将‘拆除2’改成‘拆除’；
			 */
			if(module_id == 101 && relation_desc.indexOf("项目管理 → 设计管理") != -1){
				
				sql.delete(0,sql.length());
				sql.append("select count(td11.id) as nums from Td11_jfpmsq td11 ");
				sql.append("where jsxz = '拆除2' and project_id = ");
				sql.append(project_id);
				ro = queryService.search(sql.toString());
				if(ro.next()){
					Long nums = convertUtil.toLong(ro.get("nums"));
					if(nums > 0){
						return true;
					}
					else{
						/*
						 * 控制机房平面申请单或变更单是否直接送设计管理岗【第一次发送、申请时发送】
						 */
						sql.delete(0, sql.length());
						sql.append("select count(tc01.id) as nums ");
						sql.append("from Td12_gljf td12,Tc02_bureau tc02,Tc01_property tc01 ");
						sql.append("where td12.jdmc = tc02.name ");
						sql.append("and tc02.type = tc01.name ");
						sql.append("and tc01.type = '局点性质' ");
						sql.append("and tc01.flag like '%[1]%' ");
						sql.append("and td12.project_id = ");
						sql.append(project_id);
						ro = queryService.search(sql.toString());
						if(ro.next()){
							nums = convertUtil.toLong(ro.get("nums"));
							if(nums > 0){
								return false;
							}
							else{
								return true;
							}
						}
					}
				}
			}
			else if(module_id == 101 && relation_desc.indexOf("项目管理 → 规划管理") != -1){
				
				sql.delete(0,sql.length());
				sql.append("select count(td11.id) as nums from Td11_jfpmsq td11 ");
				sql.append("where jsxz = '拆除2' and project_id = ");
				sql.append(project_id);
				ro = queryService.search(sql.toString());
				if(ro.next()){
					Long nums = convertUtil.toLong(ro.get("nums"));
					if(nums > 0){
						return false;
					}
					else{
						/*
						 * 控制机房平面申请单或变更是否需要送规划管理岗【第一次发送、申请时发送】
						 */
						sql.delete(0, sql.length());
						sql.append("select count(tc01.id) as nums ");
						sql.append("from Td12_gljf td12,Tc02_bureau tc02,Tc01_property tc01 ");
						sql.append("where td12.jdmc = tc02.name ");
						sql.append("and tc02.type = tc01.name ");
						sql.append("and tc01.type = '局点性质' ");
						sql.append("and tc01.flag like '%[1]%' ");
						sql.append("and td12.project_id = ");
						sql.append(project_id);
						ro = queryService.search(sql.toString());
						if(ro.next()){
							nums = convertUtil.toLong(ro.get("nums"));
							if(nums > 0){
								return true;
							}
							else{
								return false;
							}
						}
					}
				}
			}
			
			else if((module_id == 101 || module_id == 102) && relation_desc.indexOf("设计管理 → 规划管理") != -1){
				
				sql.delete(0,sql.length());
				sql.append("select count(td11.id) as nums from Td11_jfpmsq td11 ");
				sql.append("where jsxz = '拆除2' and project_id = ");
				sql.append(project_id);
				ro = queryService.search(sql.toString());
				if(ro.next()){
					Long nums = convertUtil.toLong(ro.get("nums"));
					if(nums > 0){
						return false;
					}
					else{
						/*
						 * 控制机房平面申请单或变更是否需要送规划管理岗【第二次发送、上报审核时发送】
						 */
						sql.delete(0, sql.length());
						sql.append("select count(tc01.id) as nums ");
						sql.append("from Td12_gljf td12,Tc02_bureau tc02,Tc01_property tc01 ");
						sql.append("where td12.jdmc = tc02.name ");
						sql.append("and tc02.type = tc01.name ");
						sql.append("and tc01.type = '局点性质' ");
						sql.append("and tc01.flag like '%[2]%' ");
						sql.append("and td12.project_id = ");
						sql.append(project_id);
						ro = queryService.search(sql.toString());
						if(ro.next()){
							nums = convertUtil.toLong(ro.get("nums"));
							if(nums > 0){
								return true;
							}
							else{
								return false;
							}
						}
					}	
				}	
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}
}
