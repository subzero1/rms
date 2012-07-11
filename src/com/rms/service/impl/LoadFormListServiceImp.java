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
import com.rms.dataObjects.form.Td01_xmxx;
import com.rms.dataObjects.form.Td00_gcxx;

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
			queryBuilder.like("type", "[1]");
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
			
			// 获取专业大类和专业小类，获取工程年度下所有专业，默认当前年
			if (module_id == 101 || module_id == 102) {
				if (doc_id != -1) {
					if(module_id==101){
						clazz = Td01_xmxx.class;
					}else{
						clazz = Td00_gcxx.class;
					}
					queryBuilder = new HibernateQueryBuilder(clazz);
					queryBuilder.eq("id", doc_id);
					ro = queryService.search(queryBuilder);
					if (ro.next()) {
						tmp_nd = (Long) ro.get("cjrq");
					}
				}

				if (tmp_nd == null) {
					tmp_nd = cur_nd;
				}
				
				// 获取专业
				queryBuilder = new HibernateQueryBuilder(Tc03_gczy.class);
				queryBuilder.eq("yxnd", tmp_nd);
				queryBuilder.addOrderBy(Order.asc("id"));
				tmpList = queryService.searchList(queryBuilder);
				if (tmpList != null) {
					List<Tc03_gczy> zydlList = new LinkedList<Tc03_gczy>();
					for (java.util.Iterator<?> itr = tmpList.iterator(); itr
							.hasNext();) {
						zydlList.add((Tc03_gczy) itr.next());
					}
					request.setAttribute("zydlList", zydlList);
				}
				
	
				
				// 获取专业小类：Tc04_zyxx
				if(doc_id != -1){
					hsql.delete(0, hsql.length());
					hsql.append("select tc04.id,tc04.mc ");
					hsql.append(" from Tc04_zyxx tc04,Tc03_gczy tc03,");
					if(module_id==101)
						hsql.append("Td01_xmxx");
					else
						hsql.append("Td00_gcxx");
					
					hsql.append(" td00 ");
					hsql.append(" where tc03.id = tc04.gczy_id ");
					hsql.append(" and td00.zydl = tc03.zymc ");
					hsql.append(" and tc03.yxnd = '");
					hsql.append(t_year);
					hsql.append("' and td00.id = ");
					hsql.append(doc_id);
					hsql.append(" order by tc04.mc ");
	
					ro = queryService.search(hsql.toString());
					List<Tc04_zyxx> zyxxList = new LinkedList<Tc04_zyxx>();
					while (ro.next()) {
						Tc04_zyxx o_tc04 = new Tc04_zyxx();
						o_tc04.setMc((String) ro.get("tc04.mc"));
						o_tc04.setId((Long) ro.get("tc04.id"));
						zyxxList.add(o_tc04);
					}
					request.setAttribute("zyxxList", zyxxList);
				}
			}
			
			if(module_id==101){
				
				// 获取切块大类：Tc06_tzqk
				queryBuilder = new HibernateQueryBuilder(Tc06_tzqk.class);
				queryBuilder.eq("nd", tmp_nd);
				queryBuilder.eq("flag", 1L);
				queryBuilder.addOrderBy(Order.asc("id"));
				tmpList = queryService.searchList(queryBuilder);
				request.setAttribute("qkdlList", tmpList);
				
				if(doc_id != -1){
					// 获取切块小类：Tc07_qkxl
					hsql.delete(0, hsql.length());
					hsql.append("select tc07.id,tc07.mc ");
					hsql
							.append("from Tc06_tzqk tc06,Tc07_qkxx tc07,Td01_xmxx td01 ");
					hsql.append("where tc06.id = tc07.qk_id ");
					hsql.append("and td01.qkdl = tc06.qkmc ");
					hsql.append(" and tc06.nd = '");
					hsql.append(t_year);
					hsql.append("' and td01.id = ");
					hsql.append(doc_id);
					hsql.append(" order by tc07.mc ");
					ro = queryService.search(hsql.toString());
					List<Tc07_qkxx> qkxl_list = new LinkedList<Tc07_qkxx>();
					while (ro.next()) {
						Tc07_qkxx o_tc07 = new Tc07_qkxx();
						o_tc07.setMc((String) ro.get("tc07.mc"));
						o_tc07.setId((Long) ro.get("tc07.id"));
						qkxl_list.add(o_tc07);
					}
					request.setAttribute("qkxlList", qkxl_list);
				}
			}
			
			if(module_id==102){
				/*
				 * 新建关联工程时把原来工程的数据写过去
				 * 1.假设三个工程 A、B、C ，如果通过A生成B、C,则B、C的关联工程就是A；
				 * 2.假设三个工程 A、B、C ，如果通过A生成B,再通过B生成C,则B、C的关联工程还是A；
				 * 以上方式主要是为了查询方便。
				 */
				Long glgc_id = convertUtil.toLong(request.getParameter("glgc_id"),-1l);
				Long fact_glgc_id = null; //记录描述中A的ID。
				if(glgc_id != null && glgc_id != -1){
					Td00_gcxx glgc = (Td00_gcxx)queryService.searchById(Td00_gcxx.class,glgc_id);
					if(glgc == null){
						glgc = new Td00_gcxx();
					}
					fact_glgc_id = glgc.getGlgc_id();
					if(fact_glgc_id == null || fact_glgc_id == -1){
						fact_glgc_id = glgc_id;
					}
					glgc.setId(null);
					glgc.setGlgc_id(fact_glgc_id);
					request.setAttribute("td00_gcxx", glgc);
				}
			}

			// end if
		} catch (Exception e) {
			throw new Exception(
					"com.rms.serviceImpl.form.LoadFormListServiceImp:"
							+ e);
		}
	}

}
