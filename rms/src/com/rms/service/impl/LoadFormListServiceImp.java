package com.rms.service.impl;

import java.util.Iterator;
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
import com.netsky.base.flow.buttonControl.Button;
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
import com.netsky.base.dataObjects.Ta01_dept;
import com.rms.dataObjects.form.Td00_gcxx;
import com.rms.dataObjects.form.Td02_xmbgd;
import com.rms.dataObjects.form.Td06_xqs;
import com.rms.dataObjects.wxdw.Tf01_wxdw;
import com.rms.dataObjects.mbk.Td21_mbk;
import com.netsky.base.flow.utils.MapUtil;

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
			queryBuilder.like("type", "[1]",MatchMode.ANYWHERE);
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
			
			
			if(module_id == 101 || module_id == 102){
				
				/*
				 * 处理按钮
				 */
				List buttonList = (List)request.getAttribute("buttons");
				
				String urlParas = MapUtil.getUrl(paraMap, new String[] { "project_id", "doc_id", "module_id", "node_id","opernode_id","user_id"});
				Button btn = new Button("附 件");
				btn.comment = "上传附件";
				btn.picUri = "attach";
				btn.url = "javascript:docSlave('slave.do?" +urlParas +"');";
				buttonList.add(btn);
				
				if(doc_id != -1){
					if(node_id == 10101 || node_id == 10103 || node_id == 10201 || node_id == 10203){
						Long bg_module_id = -1L;
						Long bg_node_id = -1L;
						Long ys_module_id = -1L;
						Long ys_node_id = -1L;
						switch(node_id.intValue())	{
							case 10101:{//项目经理起草项目变更单、项目验收单
								bg_module_id = 103L;
								bg_node_id = 10304L;
								ys_module_id = 104L;
								ys_node_id = 10402L;
								break;
							}
							case 10103:{//施工单位起草项目变更单、项目验收单
								bg_module_id = 103L;
								bg_node_id = 10301L;
								ys_module_id = 104L;
								ys_node_id = 10401L;
								break;
							}
							case 10201:{//项目经理起草工程变更单、工程验收单
								bg_module_id = 106L;
								bg_node_id = 10604L;
								ys_module_id = 107L;
								ys_node_id = 10702L;
								break;
							}
							case 10203:{//施工单位起草工程变更单、工程验收单
								bg_module_id = 106L;
								bg_node_id = 10601L;
								ys_module_id = 107L;
								ys_node_id = 10701L;
								break;
							}
						}
						btn = new Button("起草变更");
						btn.url = "javascript:docNew('flowForm.do?module_id=" + bg_module_id + "&node_id=" + bg_node_id  + "&project_id=" + project_id + "&preOpernode_id=-1&user_id=" + user_id	+ "');";
						btn.comment = "新建变更流程";
						btn.picUri = "newform";
						buttonList.add(btn);
						
						/*
						 * 资源不确认不能起草验收单
						 */
						boolean isAlreadyZyConfirm = false;
						if(module_id == 101){
							Td01_xmxx td01 = (Td01_xmxx)queryService.searchById(Td01_xmxx.class,project_id);
							isAlreadyZyConfirm = (td01.getZyqrsj() != null);
						}
						else{
							Td00_gcxx td00 = (Td00_gcxx)queryService.searchById(Td00_gcxx.class,project_id);
							isAlreadyZyConfirm = (td00.getZyqrsj() != null);
						}
						if(isAlreadyZyConfirm){
							btn = new Button("起草验收");
							btn.url = "javascript:docNew('flowForm.do?module_id=" + ys_module_id + "&node_id=" + ys_node_id  + "&project_id=" + project_id + "&preOpernode_id=-1&user_id=" + user_id	+ "');";
							btn.comment = "新建变更流程";
							btn.picUri = "newform";
							buttonList.add(btn);
						}
					}
					
					if(node_id == 10101){//项目管理员起草立项资料送审单
						btn = new Button("资料送审");
						btn.url = "javascript:docNew('flowForm.do?module_id=108&node_id=10801&project_id=" + project_id + "&preOpernode_id=-1&user_id=" + user_id	+ "');";
						btn.comment = "立项资料送审流程";
						btn.picUri = "newform";
						buttonList.add(btn);
					}
					
					if(node_id == 10103 || node_id == 10203){
						Long zy_module_id = null;
						Long zy_node_id = null;
						btn = new Button("资源确认");
						if(node_id == 10103){
							zy_module_id = 110L;
							zy_node_id = 11001L;
						}
						else{
							zy_module_id = 111L;
							zy_node_id = 11101L;
						}
						btn.url = "javascript:docNew('flowForm.do?module_id="+zy_module_id+"&node_id="+zy_node_id+"&project_id=" + project_id + "&preOpernode_id=-1&user_id=" + user_id	+ "');";
						btn.comment = "资源确认流程";
						btn.picUri = "newform";
						buttonList.add(btn);
					}
				}
				
				request.setAttribute("buttons", buttonList);
				
				// 获取专业大类和专业小类，获取工程年度下所有专业，默认当前年
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
				
				// 获取预算类型：Tc01_property type="预算类型"
				queryBuilder = new HibernateQueryBuilder(Tc01_property.class);
				queryBuilder.eq("type", "预算类型");
				queryBuilder.addOrderBy(Order.asc("id"));
				tmpList = queryService.searchList(queryBuilder);
				if (tmpList != null) {
					List<Tc01_property> yslxList = new LinkedList<Tc01_property>();
					for (java.util.Iterator<?> itr = tmpList.iterator(); itr
							.hasNext();) {
						yslxList.add((Tc01_property) itr.next());
					}
					request.setAttribute("yslxList", yslxList);
				}
				
				//获取需求部门
				queryBuilder = new HibernateQueryBuilder(Ta01_dept.class);
				queryBuilder.notEq("name", "合作单位");
				queryBuilder.addOrderBy(Order.asc("id"));
				tmpList = queryService.searchList(queryBuilder);
				if (tmpList != null) {
					List<Ta01_dept> deptList = new LinkedList<Ta01_dept>();
					for (java.util.Iterator<?> itr = tmpList.iterator(); itr
							.hasNext();) {
						deptList.add((Ta01_dept) itr.next());
					}
					request.setAttribute("deptList", deptList);
				}
				
				//获取项目状态
				queryBuilder = new HibernateQueryBuilder(Tc01_property.class);
				queryBuilder.eq("type", "工程状态");
				queryBuilder.addOrderBy(Order.asc("name"));
				tmpList = queryService.searchList(queryBuilder);
				if (tmpList != null) {
					List<Tc01_property> xmztList = new LinkedList<Tc01_property>();
					for (java.util.Iterator<?> itr = tmpList.iterator(); itr
							.hasNext();) {
						xmztList.add((Tc01_property) itr.next());
					}
					request.setAttribute("xmztList", xmztList);
				}
				
				/*
				 * 保存施工、监理、设计的受理情况
				 */
				switch(node_id.intValue())	{
					case 10102:{//项目信息单，设计
						dao.update("update Td01_xmxx set sjysl = 1 where id = "+project_id);
						break;
					}
					case 10103:{//项目信息单，施工
						dao.update("update Td01_xmxx set sgysl = 1 where id = "+project_id);
						break;
					}
					case 10104:{//项目信息单，监理
						dao.update("update Td01_xmxx set jlysl = 1 where id = "+project_id);
						break;
					}
					case 10202:{//工程信息单，设计
						dao.update("update Td00_gcxx set sjysl = 1 where id = "+project_id);
						break;
					}
					case 10203:{//工程信息单，施工
						dao.update("update Td00_gcxx set sgysl = 1 where id = "+project_id);
						break;
					}
					case 10204:{//工程信息单，监理
						dao.update("update Td00_gcxx set jlysl = 1 where id = "+project_id);
						break;
					}
				}	
			}
			
			// 获取变更类别、变更种类
			if (module_id == 103 || module_id == 106) {
				
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
				
				// 获取当前项目下所有变更单
				queryBuilder = new HibernateQueryBuilder(Td02_xmbgd.class);
				queryBuilder.eq("project_id", project_id);
				queryBuilder.addOrderBy(Order.desc("id"));
				tmpList = queryService.searchList(queryBuilder);
				if (tmpList != null) {
					request.setAttribute("bgList", tmpList);
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

				/*
				 * 新建工程时如果来自需求书则默认将需求书的信息写过去
				 */
				Long xqs_id = convertUtil.toLong(request.getParameter("xqs_id"),-1l);
				if(xqs_id != null && xqs_id != -1){
					Td06_xqs td06 = (Td06_xqs)queryService.searchById(Td06_xqs.class, xqs_id);
					if(td06 == null){
						td06 = new Td06_xqs();
					}
					Td01_xmxx t_td01 = new Td01_xmxx();
					t_td01.setId(null);
					t_td01.setXmmc(td06.getXqmc()+"【来自需求】");
					t_td01.setXqs_id(xqs_id);
					t_td01.setXmsm(td06.getBz());
					t_td01.setSsdq(td06.getSsdq());
					t_td01.setJsxz(td06.getJsxz());
					request.setAttribute("td01_xmxx", t_td01);
				}
			}
			
			if(module_id==102){
				
				Long mbk_id = convertUtil.toLong(request.getParameter("mbk_id"),-1l);
				Td21_mbk mbk = (Td21_mbk)queryService.searchById(Td21_mbk.class, mbk_id);
				request.setAttribute("mbk", mbk);
				
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
				
				/*
				 * 新建工程时如果来自需求书则默认将需求书的信息写过去
				 */
				Long xqs_id = convertUtil.toLong(request.getParameter("xqs_id"),-1l);
				if(xqs_id != null && xqs_id != -1){
					Td06_xqs td06 = (Td06_xqs)queryService.searchById(Td06_xqs.class, xqs_id);
					if(td06 == null){
						td06 = new Td06_xqs();
					}
					Td00_gcxx t_td00 = new Td00_gcxx();
					t_td00.setId(null);
					t_td00.setGcmc(td06.getXqmc()+"【来自需求】");
					t_td00.setXqs_id(xqs_id);
					t_td00.setGcsm(td06.getBz());
					t_td00.setSsdq(td06.getSsdq());
					t_td00.setJsxz(td06.getJsxz());
					request.setAttribute("td00_gcxx", t_td00);
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
			
			if(module_id==104 || module_id == 107){
				if(doc_id != -1){
					//获取项目下的所有相关工程列表
					queryBuilder = new HibernateQueryBuilder(Td00_gcxx.class);
					queryBuilder.eq("xm_id", project_id);
					queryBuilder.addOrderBy(Order.asc("id"));
					tmpList = queryService.searchList(queryBuilder);
					request.setAttribute("glgcList", tmpList);
				}
			}
			
			
			if(module_id == 109){
				/*
				 * 处理需求书按钮
				 */
				List buttonList = (List)request.getAttribute("buttons");
				
				if(doc_id != -1){
					if(node_id == 10902){//
						Button btn = new Button("转目标库");
						btn.url = "javascript:navTab.reload('mbk/mbkEdit.do?xqs_id=" + project_id + "&user_id=" + user_id	+ "');";
						btn.comment = "转入目标库";
						btn.picUri = "newform";
						buttonList.add(btn);
						
						hsql.delete(0, hsql.length());
						hsql.append("select id from Td21_mbk where xqs_id = ");
						hsql.append(project_id);
						List list = queryService.searchList(hsql.toString());
						
						/*
						 * 如果没有转入目标库就可以起草项目和工程
						 */
						//if(list == null || list.size() == 0){
						btn = new Button("新建工程");
						btn.url = "javascript:docNew('flowForm.do?module_id=102&node_id=10201&xqs_id=" + project_id + "&preOpernode_id=-1&user_id=" + user_id	+ "');";
						btn.comment = "新建单项工程";
						btn.picUri = "newform";
						buttonList.add(btn);
						
						btn = new Button("新建项目");
						btn.url = "javascript:docNew('flowForm.do?module_id=101&node_id=10101&xqs_id=" + project_id + "&preOpernode_id=-1&user_id=" + user_id	+ "');";
						btn.comment = "新建建设项目";
						btn.picUri = "newform";
						buttonList.add(btn);
						//}
					}
				}
				request.setAttribute("buttons", buttonList);
			
			   // 获取建设性质：Tc01_property type="建设性质"
				queryBuilder = new HibernateQueryBuilder(Tc01_property.class);
				queryBuilder.eq("type", "建设性质");
				queryBuilder.addOrderBy(Order.asc("name"));
				tmpList = queryService.searchList(queryBuilder);
				if (tmpList != null) {
					request.setAttribute("jsxzList", tmpList);
				}
				
				// 获取建设方式：Tc12_jsfs
				hsql.delete(0, hsql.length());
				hsql.append("select tc12 ");
				hsql.append("from Tc12_jsfs tc12,Tc01_property tc01,Td06_xqs td06 ");
				hsql.append("where tc01.id = tc12.jsxz_id ");
				hsql.append("and td06.jsxz = tc01.name ");
				hsql.append("and td06.project_id = ");
				hsql.append(project_id);
				tmpList = queryService.searchList(hsql.toString());
				if (tmpList != null) {
					request.setAttribute("jsfsList", tmpList);
				}
				
				// 获取覆盖属性：Tc01_property type="覆盖属性"
				queryBuilder = new HibernateQueryBuilder(Tc01_property.class);
				queryBuilder.eq("type", "覆盖属性");
				queryBuilder.addOrderBy(Order.asc("name"));
				tmpList = queryService.searchList(queryBuilder);
				if (tmpList != null) {
					request.setAttribute("fgsxList", tmpList);
				}
				
				/*
				 * 获取工程信息、项目信息、目标库信息
				 */
				hsql.delete(0, hsql.length());
				hsql.append("from Td21_mbk where xqs_id = ");
				hsql.append(t_doc_id);
				List mbkList = queryService.searchList(hsql.toString());
				if(mbkList != null && mbkList.size() > 0){
					request.setAttribute("mbkList", mbkList);
					request.setAttribute("mbkLength", mbkList.size());
					
				}
				
				hsql.delete(0, hsql.length());
				hsql.append("from Td00_gcxx where xqs_id = ");
				hsql.append(t_doc_id);
				List gcxxList = queryService.searchList(hsql.toString());
				if(gcxxList != null && gcxxList.size() > 0){
					request.setAttribute("gcxxList", gcxxList);
					request.setAttribute("gcxxLength", gcxxList.size());
					
				}
				
				hsql.delete(0, hsql.length());
				hsql.append("from Td01_xmxx where xqs_id = ");
				hsql.append(t_doc_id);
				List xmxxList = queryService.searchList(hsql.toString());
				if(xmxxList != null && xmxxList.size() > 0){
					request.setAttribute("xmxxList", xmxxList);
					request.setAttribute("xmxxLength", xmxxList.size());
					
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
				 * 现场照片
				 */
				hsql.delete(0, hsql.length());
				hsql.append("select count(id) from Te01_slave where slave_type = '现场照片' and project_id =");
				hsql.append(project_id);
				ro = queryService.search(hsql.toString());
				ro.next();
				if (ro.get("count(id)") != null && (Long) ro.get("count(id)") != 0) {
					HashMap<String, String> tmp_gcsm_slave = new HashMap<String, String>();
					tmp_gcsm_slave.put("slave_name", "现场照片");
					tmp_gcsm_slave.put("formurl", "javascript:projectSlaveShow("+t_project_id+","+t_module_id+","+t_doc_id+",'"+cansave+"','xczp')");
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
				
				/**
				 * 材料出入库情况
				 */
				HashMap<String, String> tmp_clmx_slave = new HashMap<String, String>();
				tmp_clmx_slave.put("slave_name", "材料出入库情况");
				tmp_clmx_slave.put("formurl", "javascript:projectClmx("+t_project_id+")");
				tmp_clmx_slave.put("rw", "r");
				v_slave.add(tmp_clmx_slave);
				
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
