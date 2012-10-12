package com.rms.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Vector;

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
import com.rms.dataObjects.wxdw.Tf01_wxdw;

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
				
	
				boolean tmp_doc_id = false;//关联工程时解决专业小类取不出来的问题
				if(doc_id == -1){
					Long glgc_id = convertUtil.toLong(request.getParameter("glgc_id"),-1l);
					if(glgc_id != -1){
						doc_id = glgc_id;
						tmp_doc_id = true;
					}
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
				if(tmp_doc_id){
					doc_id = -1l;
				}
				
				//获取设计单位和监理单位列表
				hsql.delete(0, hsql.length());
				hsql.append("select tf01.id,tf01.mc,tf01.lb from Tf01_wxdw tf01 where lb='设计' or lb='监理' order by id");
				ro = queryService.search(hsql.toString());
				List<Tf01_wxdw> sjdwList = new LinkedList<Tf01_wxdw>();
				List<Tf01_wxdw> jldwList = new LinkedList<Tf01_wxdw>();
				while (ro.next()) {
					Tf01_wxdw o_tf01 = new Tf01_wxdw();
					o_tf01.setMc((String) ro.get("tf01.mc"));
					o_tf01.setId((Long) ro.get("tf01.id"));
					
					if(ro.get("tf01.lb").equals("设计"))
						sjdwList.add(o_tf01);
					else
						jldwList.add(o_tf01);
				}
				request.setAttribute("sjdwList", sjdwList);
				request.setAttribute("jldwList", jldwList);	
				
			}
			
			// 获取变更类别、变更种类
			if (module_id == 103) {
				
				// 获取变更类别：Tc01_property type="变更类别"
				queryBuilder = new HibernateQueryBuilder(Tc01_property.class);
				queryBuilder.eq("type", "变更类别");
				queryBuilder.addOrderBy(Order.asc("name"));
				tmpList = queryService.searchList(queryBuilder);
				if (tmpList != null) {
					List<Tc01_property> bglbList = new LinkedList<Tc01_property>();
					for (java.util.Iterator<?> itr = tmpList.iterator(); itr
							.hasNext();) {
						bglbList.add((Tc01_property) itr.next());
					}
					request.setAttribute("bglbList", bglbList);
				}
				
				// 获取变更种类：Tc01_property type="变更种类"
				queryBuilder = new HibernateQueryBuilder(Tc01_property.class);
				queryBuilder.eq("type", "变更种类");
				queryBuilder.addOrderBy(Order.asc("name"));
				tmpList = queryService.searchList(queryBuilder);
				if (tmpList != null) {
					List<Tc01_property> bgzlList = new LinkedList<Tc01_property>();
					for (java.util.Iterator<?> itr = tmpList.iterator(); itr
							.hasNext();) {
						bgzlList.add((Tc01_property) itr.next());
					}
					request.setAttribute("bgzlList", bgzlList);
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
					
					//获取项目下的所有相关工程列表
					queryBuilder = new HibernateQueryBuilder(Td00_gcxx.class);
					queryBuilder.eq("xm_id", doc_id);
					queryBuilder.addOrderBy(Order.asc("id"));
					tmpList = queryService.searchList(queryBuilder);
					request.setAttribute("glgcList", tmpList);
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
					glgc.setGcmc(glgc.getGcmc()+"【关联工程......】");
					glgc.setGlgc_id(fact_glgc_id);
					request.setAttribute("td00_gcxx", glgc);
				}
				
				//获取关联工程
				if(doc_id != -1){
					Td00_gcxx gcxx = (Td00_gcxx)queryService.searchById(Td00_gcxx.class,doc_id);
					hsql.delete(0, hsql.length());
					hsql.append(" from Td00_gcxx glgc ");
					
					if(gcxx.getGlgc_id()==null){
						hsql.append(" where glgc_id = ");
						hsql.append(doc_id);
					}else{
						hsql.append(" where id = ");
						hsql.append(gcxx.getGlgc_id());
						hsql.append(" or glgc_id = ");
						hsql.append(gcxx.getGlgc_id());
					}
					hsql.append(" order by id");
					ro = queryService.search(hsql.toString());
					List<Td00_gcxx> glgc_list = new LinkedList<Td00_gcxx>();
					while (ro.next()) {
						glgc_list.add((Td00_gcxx)ro.get("glgc"));
					}
					request.setAttribute("glgcList", glgc_list);
				}
			}
			
			if(module_id==104){
				if(doc_id != -1){
					//获取项目下的所有相关工程列表
					queryBuilder = new HibernateQueryBuilder(Td00_gcxx.class);
					queryBuilder.eq("xm_id", project_id);
					queryBuilder.addOrderBy(Order.asc("id"));
					tmpList = queryService.searchList(queryBuilder);
					request.setAttribute("glgcList", tmpList);
				}
			}
			
			
			/**
			 * 以下为表单附件区域@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			 */

			Vector<HashMap<String, String>> v_slave = new Vector<HashMap<String, String>>();

			/**
			 * 项目信息单和工程信息单中的公共附件******************************************************
			 */

			if (module_id == 102) {
				
				/**
				 * 施工进度
				 */
				hsql.delete(0, hsql.length());
				hsql.append("select count(id) from Td00_gcxx where sgdw is not null and id =");
				hsql.append(project_id);
				ro = queryService.search(hsql.toString());
				ro.next();
				if (ro.get("count(id)") != null && (Long) ro.get("count(id)") != 0) {
					HashMap<String, String> tmp_gcsm_slave = new HashMap<String, String>();
					tmp_gcsm_slave.put("slave_name", "施工进度");
					tmp_gcsm_slave.put("formurl", "javascript:navTab.openTab('sgjd', 'wxdw/jdfkxx.do?id="+ t_project_id +"', {title:'施工进度'});");
					tmp_gcsm_slave.put("rw", "r");
					v_slave.add(tmp_gcsm_slave);
				}
				
				/**
				 * 工程施工进度
				 */
				boolean haveSgdw = false;
				boolean haveGlgc = false;
				hsql.delete(0, hsql.length());
				hsql.append("select count(id) from Td00_gcxx where sgdw is not null and id =");
				hsql.append(project_id);
				ro = queryService.search(hsql.toString());
				ro.next();
				if (ro.get("count(id)") != null && (Long) ro.get("count(id)") != 0) {
					haveSgdw = true;
				}
				hsql.delete(0, hsql.length());
				hsql.append("select count(id) from Td00_gcxx where ((glgc_id is not null and id =");
				hsql.append(project_id);
				hsql.append(") or glgc_id = ");
				hsql.append(project_id);
				hsql.append(")");
				ro = queryService.search(hsql.toString());
				ro.next();
				if (ro.get("count(id)") != null && (Long) ro.get("count(id)") != 0) {
					haveGlgc = true;
				}
				
				if (haveSgdw && haveGlgc) {
					HashMap<String, String> tmp_gcsm_slave = new HashMap<String, String>();
					tmp_gcsm_slave.put("slave_name", "工程施工进度");
					tmp_gcsm_slave.put("formurl", "javascript:navTab.openTab('gcsgjd', 'wxdw/gcsgjd.do?id="+ t_project_id +"', {title:'工程施工进度'});");
					tmp_gcsm_slave.put("rw", "r");
					v_slave.add(tmp_gcsm_slave);
				}
			}
			
			
			if (module_id == 101) {

				/**
				 * 监理日记
				 */
				hsql.delete(0, hsql.length());
				hsql.append("select count(id) from Td01_xmxx where jldw is not null and id =");
				hsql.append(project_id);
				ro = queryService.search(hsql.toString());
				ro.next();
				if (ro.get("count(id)") != null && (Long) ro.get("count(id)") != 0) {
					HashMap<String, String> tmp_gcsm_slave = new HashMap<String, String>();
					tmp_gcsm_slave.put("slave_name", "监理日记");
					tmp_gcsm_slave.put("formurl", "javascript:navTab.openTab('jlzj', 'wxdw/jlrjxx.do?id="+ t_project_id +"', {title:'监理日记'});");
					tmp_gcsm_slave.put("rw", "r");
					v_slave.add(tmp_gcsm_slave);
				}
				
				/**
				 * 项目施工进度
				 */
				hsql.delete(0, hsql.length());
				hsql.append("select count(id) from Td01_xmxx where sgdw is not null and id =");
				hsql.append(project_id);
				ro = queryService.search(hsql.toString());
				ro.next();
				if (ro.get("count(id)") != null && (Long) ro.get("count(id)") != 0) {
					HashMap<String, String> tmp_gcsm_slave = new HashMap<String, String>();
					tmp_gcsm_slave.put("slave_name", "项目施工进度");
					tmp_gcsm_slave.put("formurl", "javascript:navTab.openTab('xmsgjd', 'wxdw/xmsgjd.do?id="+ t_project_id +"', {title:'项目施工进度'});");
					tmp_gcsm_slave.put("rw", "r");
					v_slave.add(tmp_gcsm_slave);
				}
			}
			
			if (module_id == 101 || module_id == 102 || module_id == 104) {
				/**
				 * 竣工资料
				 */
				hsql.delete(0, hsql.length());
				hsql.append("select count(id) from Te01_slave where slave_type = '竣工资料' and project_id =");
				hsql.append(project_id);
				ro = queryService.search(hsql.toString());
				ro.next();
				if (ro.get("count(id)") != null && (Long) ro.get("count(id)") != 0) {
					HashMap<String, String> tmp_gcsm_slave = new HashMap<String, String>();
					tmp_gcsm_slave.put("slave_name", "竣工资料");
					tmp_gcsm_slave.put("formurl", "javascript:projectSlaveShow("+t_project_id+","+t_module_id+","+t_doc_id+",'"+cansave+"','jgzl')");
					tmp_gcsm_slave.put("rw", "r");
					v_slave.add(tmp_gcsm_slave);
				}
				
				/**
				 * 工程预算(单机)
				 */
				hsql.delete(0, hsql.length());
				hsql.append("select count(id) from Te03_gcgys_zhxx where gc_id = ");
				hsql.append(project_id);
				ro = queryService.search(hsql.toString());
				ro.next();
				if (ro.get("count(id)") != null && (Long) ro.get("count(id)") != 0) {
					HashMap<String, String> tmp_gcysdj_slave = new HashMap<String, String>();
					tmp_gcysdj_slave.put("slave_name", "工程预算");
					tmp_gcysdj_slave.put("formurl", "javascript:djbck(" + project_id + ")");
					tmp_gcysdj_slave.put("rw", "r");
					v_slave.add(tmp_gcysdj_slave);
				}

				/**
				 * 工程图纸
				 */
				hsql.delete(0, hsql.length());
				hsql.append("select count(id) from Te01_slave where slave_type = '工程图纸' and project_id =");
				hsql.append(project_id);
				ro = queryService.search(hsql.toString());
				ro.next();
				if (ro.get("count(id)") != null && (Long) ro.get("count(id)") != 0) {
					HashMap<String, String> tmp_gctz_slave = new HashMap<String, String>();
					tmp_gctz_slave.put("slave_name", "工程图纸");
					tmp_gctz_slave.put("formurl", "javascript:projectSlaveShow("+t_project_id+","+t_module_id+","+t_doc_id+",'"+cansave+"','gctz')");
					tmp_gctz_slave.put("rw", "r");
					v_slave.add(tmp_gctz_slave);
				}

				/**
				 * 设计说明
				 */
				hsql.delete(0, hsql.length());
				hsql.append("select count(id) from Te01_slave where slave_type = '设计说明' and project_id =");
				hsql.append(project_id);
				ro = queryService.search(hsql.toString());
				ro.next();
				if (ro.get("count(id)") != null && (Long) ro.get("count(id)") != 0) {
					HashMap<String, String> tmp_gcsm_slave = new HashMap<String, String>();
					tmp_gcsm_slave.put("slave_name", "设计说明");
					tmp_gcsm_slave.put("formurl", "javascript:projectSlaveShow("+t_project_id+","+t_module_id+","+t_doc_id+",'"+cansave+"','sjsm')");
					tmp_gcsm_slave.put("rw", "r");
					v_slave.add(tmp_gcsm_slave);
				}
				
			}
			if (v_slave.size() > 0) {
				request.setAttribute("extslave", v_slave);
			}

			// end if
		} catch (Exception e) {
			throw new Exception(
					"com.rms.serviceImpl.form.LoadFormListServiceImp:"
							+ e);
		}
	}

}
