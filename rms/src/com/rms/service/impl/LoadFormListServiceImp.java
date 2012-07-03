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
					.get("curArea"), "南京市");

			module_id = convertUtil.toLong(t_module_id);
			project_id = convertUtil.toLong(t_project_id);
			doc_id = convertUtil.toLong(t_doc_id);
			node_id = convertUtil.toLong(t_node_id);
			flow_id = convertUtil.toLong(t_flow_id);
			user_id = convertUtil.toLong(t_user_id);
			hsql = new StringBuffer("");

			// 获取工程类别：Tc01_property type="工程类别"
			queryBuilder = new HibernateQueryBuilder(Tc01_property.class);
			queryBuilder.eq("type", "工程类别");
			queryBuilder.addOrderBy(Order.asc("name"));
			tmpList = queryService.searchList(queryBuilder);
			if (tmpList != null) {
				List<Tc01_property> gclbList = new LinkedList<Tc01_property>();
				for (java.util.Iterator<?> itr = tmpList.iterator(); itr
						.hasNext();) {
					gclbList.add((Tc01_property) itr.next());
				}
				request.setAttribute("gclbList", gclbList);
			}
			
			// 获取地区
			queryBuilder = new HibernateQueryBuilder(Tc02_area.class);
			queryBuilder.eq("type", "1");
			queryBuilder.addOrderBy(Order.asc("seq"));
			tmpList = queryService.searchList(queryBuilder);
			if (tmpList != null) {
				List<Tc02_area> ssdqList = new LinkedList<Tc02_area>();
				for (java.util.Iterator<?> itr = tmpList.iterator(); itr
						.hasNext();) {
					ssdqList.add((Tc02_area) itr.next());
				}
				request.setAttribute("ssdqList", ssdqList);
			}
			
				

			// end if
		} catch (Exception e) {
			throw new Exception(
					"com.rms.serviceImpl.form.LoadFormListServiceImp:"
							+ e);
		}
	}

}
