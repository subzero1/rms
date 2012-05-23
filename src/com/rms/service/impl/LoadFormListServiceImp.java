package com.rms.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.rms.dataObjects.base.*;
import com.rms.dataObjects.info.*;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.service.LoadFormListService;
import com.netsky.base.service.QueryService;
import com.netsky.base.utils.StringFormatUtil;
import com.netsky.base.utils.DateGetUtil;
import com.netsky.base.dataObjects.Ta01_dept;

@Service("loadFormListService")
public class LoadFormListServiceImp implements LoadFormListService {
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

	/**
	 * @return the queryService
	 */
	public QueryService getQueryService() {
		return queryService;
	}

	/**
	 * @param queryService
	 *            the queryService to set
	 */
	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}

	public void load(HttpServletRequest request, Map paraMap) throws Exception {

		Long module_id = null;
		Long project_id = null;
		Long doc_id = null;
		Long node_id = null;
		Long user_id = null;
		Long flow_id = null;
		List<?> tmpList = null;
		Long cur_nd = new Long(DateGetUtil.getYear());
		Long tmp_nd = null;
		ResultObject ro = null;
		Class<?> clazz = null;
		StringBuffer hsql = null;
		String curArea = null;

		QueryBuilder queryBuilder = null;
		try {
			String t_module_id = StringFormatUtil.format((String) paraMap
					.get("module_id"), "-1");
			String t_flow_id = StringFormatUtil.format((String) paraMap
					.get("flow_id"), "-1");
			String t_project_id = StringFormatUtil.format((String) paraMap
					.get("project_id"), "-1");
			String t_doc_id = StringFormatUtil.format((String) paraMap
					.get("doc_id"), "-1");
			String t_node_id = StringFormatUtil.format((String) paraMap
					.get("node_id"), "-1");
			String t_user_id = StringFormatUtil.format((String) paraMap
					.get("user_id"), "-1");
			String t_year = StringFormatUtil.format((String) paraMap
					.get("year"), "-1");
			String cansave = StringFormatUtil.format((String) paraMap
					.get("cansave"), "no");
			curArea = StringFormatUtil.format((String) paraMap
					.get("curArea"), "无锡市");

			module_id = convertUtil.toLong(t_module_id);
			project_id = convertUtil.toLong(t_project_id);
			doc_id = convertUtil.toLong(t_doc_id);
			node_id = convertUtil.toLong(t_node_id);
			flow_id = convertUtil.toLong(t_flow_id);
			user_id = convertUtil.toLong(t_user_id);
			hsql = new StringBuffer("");

			// 获取专业：Tc01_property type="所属专业"
			queryBuilder = new HibernateQueryBuilder(Tc01_property.class);
			queryBuilder.eq("type", "所属专业");
			queryBuilder.addOrderBy(Order.asc("name"));
			tmpList = queryService.searchList(queryBuilder);
			if (tmpList != null) {
				List<Tc01_property> zyList = new LinkedList<Tc01_property>();
				for (java.util.Iterator<?> itr = tmpList.iterator(); itr
						.hasNext();) {
					zyList.add((Tc01_property) itr.next());
				}
				request.setAttribute("zyList", zyList);
			}
			
			// 获取建设性质：Tc01_property type="建设性质"
			queryBuilder = new HibernateQueryBuilder(Tc01_property.class);
			queryBuilder.eq("type", "建设性质");
			queryBuilder.addOrderBy(Order.asc("id"));
			tmpList = queryService.searchList(queryBuilder);
			if (tmpList != null) {
				List<Tc01_property> jsxzList = new LinkedList<Tc01_property>();
				for (java.util.Iterator<?> itr = tmpList.iterator(); itr
						.hasNext();) {
					jsxzList.add((Tc01_property) itr.next());
				}
				request.setAttribute("jsxzList", jsxzList);
			}
						
			// 获取供电方式：Tc01_property type="供电方式"
			queryBuilder = new HibernateQueryBuilder(Tc01_property.class);
			queryBuilder.eq("type", "供电方式");
			queryBuilder.addOrderBy(Order.asc("id"));
			tmpList = queryService.searchList(queryBuilder);
			if (tmpList != null) {
				List<Tc01_property> gdfsList = new LinkedList<Tc01_property>();
				for (java.util.Iterator<?> itr = tmpList.iterator(); itr
						.hasNext();) {
					gdfsList.add((Tc01_property) itr.next());
				}
				request.setAttribute("gdfsList", gdfsList);
			}
			
			if(module_id == 101 || module_id == 102){
				
				// 获取有效地区
				String t_area = null;
				if(doc_id == -1){
					t_area = curArea;
				}
				else{
					hsql=new StringBuffer("select ta03.area_name as area_name ");
					hsql.append("from Td11_jfpmsq td11,Ta03_user ta03 ");
					hsql.append("where td11.cjr = ta03.name ");
					hsql.append("and td11.id = ");
					hsql.append(doc_id);
					ro = queryService.search(hsql.toString());
					if(ro.next()){
						t_area = (String)ro.get("area_name");
					}
				}
				if(t_area == null || t_area.equals("")){
					t_area = curArea;
				}
				
				// 获取外协单位：Ta01_dept area_name="外协" remark like '%$有效地区%'
				queryBuilder = new HibernateQueryBuilder(Ta01_dept.class);
				queryBuilder.eq("area_name", "外协");
				queryBuilder.like("remark", t_area, MatchMode.ANYWHERE);
				queryBuilder.addOrderBy(Order.asc("id"));
				tmpList = queryService.searchList(queryBuilder);
				if (tmpList != null) {
					List<Ta01_dept> sjdwList = new LinkedList<Ta01_dept>();
					for (java.util.Iterator<?> itr = tmpList.iterator(); itr.hasNext();) {
						sjdwList.add((Ta01_dept) itr.next());
					}
					request.setAttribute("sjdwList", sjdwList);
				}
				
				//获取设计人员
				List sjryList = null;
				if(doc_id == -1){
					hsql.delete(0, hsql.length());
					hsql.append("select ta03.name "); 
					hsql.append("from Ta02_station ta02, Ta11_sta_user ta11,Ta03_user ta03 ");
					hsql.append("where ta02.id = ta11.station_id ");
					hsql.append("and ta11.user_id = ta03.id ");
					hsql.append("and ta02.name like '%设计岗%' ");
					hsql.append("and (ta03.area_name like '%");
					hsql.append(t_area);
					hsql.append("%' or ta03.area_name = '全区') ");
					sjryList = queryService.searchList(hsql.toString());
				}
				else{
					Td11_jfpmsq td11 = (Td11_jfpmsq)queryService.searchById(Td11_jfpmsq.class, doc_id);
					String t_sjdw = convertUtil.toString(td11.getSjdw());
					if(t_sjdw.trim().equals("") || t_sjdw.indexOf("内部设计") != -1){
						hsql.delete(0, hsql.length());
						hsql.append("select ta03.name "); 
						hsql.append("from Ta02_station ta02, Ta11_sta_user ta11,Ta03_user ta03 ");
						hsql.append("where ta02.id = ta11.station_id ");
						hsql.append("and ta11.user_id = ta03.id ");
						hsql.append("and ta02.name like '%设计岗%' ");
						hsql.append("and (ta03.area_name like '%");
						hsql.append(t_area);
						hsql.append("%'  or ta03.area_name = '全区') ");
						sjryList = queryService.searchList(hsql.toString());
					}
					else{
						hsql.delete(0, hsql.length());
						hsql.append("select ta03.name ");
						hsql.append("from Ta03_user ta03,Ta01_dept ta01 ");
						hsql.append("where ta01.id = ta03.dept_id ");
						hsql.append("and ta01.name = '");
						hsql.append(t_sjdw);
						hsql.append("' ");
						sjryList = queryService.searchList(hsql.toString());
					}
				}
				if(sjryList != null){
					request.setAttribute("sjryList", sjryList);
				}
				
				//获取图纸
				Map drawMap = new HashMap();
				String sql = " from Td12_gljf where project_id = "  + project_id + " order by id " ;
				List<Td12_gljf> td12List =(List<Td12_gljf>) dao.search(sql);
				
//				for(Td12_gljf td12: td12List){
//					sql = "select te01 from Te01_slave te01 where te01.doc_id=" + td12.getJf_id() 
//					+ " and te01.module_id=200"
//					+ " and (te01.slave_type='机房规划图' or te01.slave_type='机房现状图')";
//		
//					List<Te01_slave> drawingList = (List<Te01_slave>) dao.search(sql);
//					
//					sql = " from Te01_slave where module_id="+module_id+" and doc_id=" + td12.getJf_id() 
//						+" and (slave_type='申请设计图' or slave_type='变更设计图')";
//					
//					drawingList.addAll( (List<Te01_slave>) dao.search(sql));
//					drawMap.put(td12.getJf_id(), drawingList);
//				}
//				request.setAttribute("drawMap", drawMap);
				
				request.setAttribute("jfList", td12List);
				
				
				//判断表单当前走到哪个阶段（申请还是上报审核）
				boolean isUpLevel = true;
				if(node_id == null || node_id == -1){
					//在查询中进入
					isUpLevel = true;
				}
				else{
					hsql.delete(0, hsql.length());
					hsql.append("select id from Tb02_node where trim(name) = '设计人员' and id like '");
					hsql.append(module_id);
					hsql.append("%'");
					ro = queryService.search(hsql.toString());
					if(ro.next()){
						Long _node_id = convertUtil.toLong(ro.get("id"));
						if(_node_id.longValue() == node_id.longValue()){
							isUpLevel = true;
						}
						else{
							isUpLevel = getIsUpLevel(node_id,_node_id);
						}
					}
					else{
						isUpLevel = true;
					}
				}
				request.setAttribute("isUpLevel", isUpLevel);
			}		

			// end if
		} catch (Exception e) {
			throw new Exception(
					"com.rms.serviceImpl.form.LoadFormListServiceImp:"
							+ e);
		}
	}
	
	/**
	 * 判断当前节点是否处于上报过程。。
	 * 通过中间的设计人员节点向上回溯，
	 * 如果不属于申请阶段，那则是上报阶段
	 * @param node_id 要判断的节点、当前节点
	 * @param _node_id 临时变量
	 * @return boolean
	 */
	private boolean getIsUpLevel(Long node_id,Long _node_id){
		
		StringBuffer hsql = new StringBuffer("");
		ResultObject ro = null;
		int i = 0;
		
		hsql.delete(0, hsql.length());
		hsql.append("select source_id from Tb03_relation where dest_id = ");
		hsql.append(_node_id);
		hsql.append(" order by source_id desc ");
		ro = queryService.search(hsql.toString());
		while(ro.next()){
			i = i + 1;
			_node_id = convertUtil.toLong(ro.get("source_id"));
			if(node_id.longValue() == _node_id.longValue()){
				return false;
			}
			else{
				return getIsUpLevel(node_id,_node_id);
			}
		}
		
		return true;
	}
}
