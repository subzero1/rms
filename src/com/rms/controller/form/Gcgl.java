package com.rms.controller.form;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import org.hibernate.criterion.MatchMode;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta04_role;
import com.netsky.base.utils.convertUtil;
import com.netsky.base.service.QueryService;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.service.SaveService;
import com.rms.dataObjects.base.Tc01_property;
import com.rms.dataObjects.form.Td00_gcxx;
import com.rms.dataObjects.form.Td01_xmxx;
import com.rms.dataObjects.form.Td06_xqs;

@Controller
public class Gcgl {
	/**
	 * 查询服务
	 */
	@Autowired
	private QueryService queryService;

	@Autowired
	private SaveService saveService;

	/**
	 * 项目信息列表
	 */

	/**
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/form/xmxxList.do")
	public ModelAndView xmxxList(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		// 分页
		Integer totalPages = 1;
		Integer totalCount = 0;
		Integer pageNum = convertUtil.toInteger(
				request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request
				.getParameter("orderField"), "id");
		if (orderField.equals("")) {
			orderField = "id";
		}
		String orderDirection = convertUtil.toString(request
				.getParameter("orderDirection"), "desc");
		if (orderDirection.equals("")) {
			orderDirection = "desc";
		}
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);

		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		String user_name = user.getName();
		String user_dept = user.getDept_name();
		String user_zy = user.getZys();

		// 查询条件
		String keyword = convertUtil.toString(request.getParameter("keyword"));

		StringBuffer hsql = new StringBuffer();
		hsql.append("select xmxx from Td01_xmxx xmxx where ");

		// 工程和项目显示条件，【项目管理员=自己 或 施工单位=自己单位 或 监理单位=自己单位 或 设计单位=自己单位】
		hsql.append("(");
		hsql.append("xmgly = '" + user_name + "'");
		hsql.append(" or xmjl = '" + user_name + "'");
		hsql.append(" or (sgdw = '" + user_dept + "' and gclb in (" + user_zy
				+ "))");
		hsql.append(" or sjdw = '" + user_dept + "'");
		hsql.append(" or jldw = '" + user_dept + "'");
		hsql.append(")");

		// 关键字
		if (!keyword.equals("")) {
			hsql.append(" and (xmmc like '%" + keyword + "%' or xmbh like '%"
					+ keyword + "%')");
		}

		// order排序
		hsql.append(" order by " + orderField);
		hsql.append(" " + orderDirection);

		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum,
				numPerPage);

		// 获取结果集
		List<Td01_xmxx> xmxxList = new ArrayList<Td01_xmxx>();
		String limit = "";
		Long node_id = -1L;
		while (ro.next()) {
			Td01_xmxx td01 = (Td01_xmxx) ro.get("xmxx");

			if ("".equals(limit)) {
				if (user_dept.equals(td01.getSgdw())) {
					limit = "sgdw";
					node_id = 10103L;
				} else if (user_dept.equals(td01.getJldw())) {
					limit = "jldw";
					node_id = 10104L;
				} else if (user_dept.equals(td01.getSjdw())) {
					limit = "sjdw";
					node_id = 10102L;
				} else {
					limit = "xmgly";
					node_id = 10101L;
				}
			}

			xmxxList.add(td01);
		}

		hsql.delete(0, hsql.length());
		hsql.append("from Ta11_sta_user ta11,Ta13_sta_node ta13 ");
		hsql.append("where ta11.station_id = ta13.station_id ");
		hsql.append("and ta13.node_id = 10101 ");
		hsql.append("and ta11.user_id =");
		hsql.append(user.getId());
		List nodeList = queryService.searchList(hsql.toString());
		if (nodeList != null && nodeList.size() > 0) {
			node_id = 10101L;
		}

		modelMap.put("node_id", node_id);
		modelMap.put("limit", limit);
		modelMap.put("xmxxList", xmxxList);

		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);

		return new ModelAndView("/WEB-INF/jsp/form/xmxxList.jsp", modelMap);

	}

	/**
	 * 合作单位登录 显示未处理的项目
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/form/xmxxListForNeed.do")
	public ModelAndView xmxxListForNeed(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		// 分页
		Integer totalPages = 1;
		Integer totalCount = 0;
		Integer pageNum = convertUtil.toInteger(
				request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request
				.getParameter("orderField"), "id");
		if (orderField.equals("")) {
			orderField = "id";
		}
		String orderDirection = convertUtil.toString(request
				.getParameter("orderDirection"), "desc");
		if (orderDirection.equals("")) {
			orderDirection = "desc";
		}
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);

		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		String user_name = user.getName();
		String user_dept = user.getDept_name();
		String user_zy = user.getZys();

		// 查询条件
		String keyword = convertUtil.toString(request.getParameter("keyword"));

		StringBuffer hsql = new StringBuffer();
		hsql.append("select xmxx from Td01_xmxx xmxx where ");

		// 工程和项目显示条件，【项目管理员=自己 或 施工单位=自己单位 或 监理单位=自己单位 或 设计单位=自己单位】
		hsql.append("(");
		hsql.append(" (sgdw = '" + user_dept + "' and gclb in (" + user_zy
				+ ") and sgysl is null)");
		hsql.append(" or (sjdw = '" + user_dept + "' and sjysl is null)");
		hsql.append(" or (jldw = '" + user_dept + "' and jlysl is null)");
		hsql.append(")");

		// 关键字
		if (!keyword.equals("")) {
			hsql.append(" and (xmmc like '%" + keyword + "%' or xmbh like '%"
					+ keyword + "%')");
		}

		// order排序
		hsql.append(" order by " + orderField);
		hsql.append(" " + orderDirection);

		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum,
				numPerPage);

		// 获取结果集
		List<Td01_xmxx> xmxxList = new ArrayList<Td01_xmxx>();
		String limit = "";
		Long node_id = -1L;
		while (ro.next()) {
			Td01_xmxx td01 = (Td01_xmxx) ro.get("xmxx");

			if ("".equals(limit)) {
				if (user_dept.equals(td01.getSgdw())) {
					limit = "sgdw";
					node_id = 10103L;
				} else if (user_dept.equals(td01.getJldw())) {
					limit = "jldw";
					node_id = 10104L;
				} else if (user_dept.equals(td01.getSjdw())) {
					limit = "sjdw";
					node_id = 10102L;
				} else {
					limit = "xmgly";
					node_id = 10101L;
				}
			}

			xmxxList.add(td01);
		}

		modelMap.put("node_id", node_id);
		modelMap.put("limit", limit);
		modelMap.put("xmxxList", xmxxList);

		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);

		return new ModelAndView("/WEB-INF/jsp/form/xmxxList.jsp", modelMap);

	}

	/**
	 * 工程信息列表
	 */

	/**
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/form/gcxxList.do")
	public ModelAndView gcxxList(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		// 分页
		Integer totalPages = 1;
		Integer totalCount = 0;
		Integer pageNum = convertUtil.toInteger(
				request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request
				.getParameter("orderField"), "id");
		if (orderField.equals("")) {
			orderField = "id";
		}
		String orderDirection = convertUtil.toString(request
				.getParameter("orderDirection"), "desc");
		if (orderDirection.equals("")) {
			orderDirection = "desc";
		}
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);

		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		String user_name = user.getName();
		String user_dept = user.getDept_name();
		String user_zy = user.getZys();

		// 查询条件
		String keyword = convertUtil.toString(request.getParameter("keyword"));

		StringBuffer hsql = new StringBuffer();
		hsql.append("select gcxx from Td00_gcxx gcxx where ");

		// 工程和项目显示条件，【项目管理员=自己 或 施工单位=自己单位 或 监理单位=自己单位 或 设计单位=自己单位】
		hsql.append("not exists(select 'x' from Ti03_xqly ti03 where gcxx.id = ti03.project_id) ");
		hsql.append("and (");
		hsql.append("xmgly = '" + user_name + "'");
		hsql.append(" or (sgdw = '" + user_dept + "' and gclb in (" + user_zy
				+ "))");
		hsql.append(" or sjdw = '" + user_dept + "'");
		hsql.append(" or jldw = '" + user_dept + "'");
		hsql.append(")");

		// 关键字
		if (!keyword.equals("")) {
			hsql.append(" and (gcmc like '%" + keyword + "%' or gcbh like '%"
					+ keyword + "%')");
		}

		// order排序
		hsql.append(" order by " + orderField);
		hsql.append(" " + orderDirection);

		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum,
				numPerPage);
		// 获取结果集
		List<Td00_gcxx> gcxxList = new ArrayList<Td00_gcxx>();
		String limit = "";
		Long node_id = -1L;
		while (ro.next()) {
			Td00_gcxx td00 = (Td00_gcxx) ro.get("gcxx");
			if ("".equals(limit)) {
				if (user_dept.equals(td00.getSgdw())) {
					limit = "sgdw";
					node_id = 10203L;
				} else if (user_dept.equals(td00.getJldw())) {
					limit = "jldw";
					node_id = 10204L;
				} else if (user_dept.equals(td00.getSjdw())) {
					limit = "sjdw";
					node_id = 10202L;
				} else {
					limit = "xmgly";
					node_id = 10201L;
				}
			}

			gcxxList.add(td00);
		}

		hsql.delete(0, hsql.length());
		hsql.append("from Ta11_sta_user ta11,Ta13_sta_node ta13 ");
		hsql.append("where ta11.station_id = ta13.station_id ");
		hsql.append("and ta13.node_id = 10201 ");
		hsql.append("and ta11.user_id =");
		hsql.append(user.getId());
		List nodeList = queryService.searchList(hsql.toString());
		if (nodeList != null && nodeList.size() > 0) {
			node_id = 10201L;
		}

		modelMap.put("node_id", node_id);
		modelMap.put("limit", limit);
		modelMap.put("gcxxList", gcxxList);

		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);

		return new ModelAndView("/WEB-INF/jsp/form/gcxxList.jsp", modelMap);

	}

	/**
	 * 合作单位登录 显示未处理的工程
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/form/gcxxListForNeed.do")
	public ModelAndView gcxxListForNeed(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		// 分页
		Integer totalPages = 1;
		Integer totalCount = 0;
		Integer pageNum = convertUtil.toInteger(
				request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request
				.getParameter("orderField"), "id");
		if (orderField.equals("")) {
			orderField = "id";
		}
		String orderDirection = convertUtil.toString(request
				.getParameter("orderDirection"), "desc");
		if (orderDirection.equals("")) {
			orderDirection = "desc";
		}
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);

		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		String user_name = user.getName();
		String user_dept = user.getDept_name();
		String user_zy = user.getZys();

		// 查询条件
		String keyword = convertUtil.toString(request.getParameter("keyword"));

		StringBuffer hsql = new StringBuffer();
		hsql.append("select gcxx from Td00_gcxx gcxx where ");

		// 工程和项目显示条件，【项目管理员=自己 或 施工单位=自己单位 或 监理单位=自己单位 或 设计单位=自己单位】
		hsql.append("not exists(select 'x' from Ti03_xqly ti03 where gcxx.id = ti03.project_id) ");
		hsql.append("and (");
		hsql.append(" (sgdw = '" + user_dept + "' and gclb in (" + user_zy
				+ ") and sgysl is null)");
		hsql.append(" or (sjdw = '" + user_dept + "' and sjysl is null)");
		hsql.append(" or (jldw = '" + user_dept + "' and jlysl is null)");
		hsql.append(")");

		// 关键字
		if (!keyword.equals("")) {
			hsql.append(" and (gcmc like '%" + keyword + "%' or gcbh like '%"
					+ keyword + "%')");
		}

		// order排序
		hsql.append(" order by " + orderField);
		hsql.append(" " + orderDirection);

		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum,
				numPerPage);
		// 获取结果集
		List<Td00_gcxx> gcxxList = new ArrayList<Td00_gcxx>();
		String limit = "";
		Long node_id = -1L;
		while (ro.next()) {
			Td00_gcxx td00 = (Td00_gcxx) ro.get("gcxx");
			if ("".equals(limit)) {
				if (user_dept.equals(td00.getSgdw())) {
					limit = "sgdw";
					node_id = 10203L;
				} else if (user_dept.equals(td00.getJldw())) {
					limit = "jldw";
					node_id = 10204L;
				} else if (user_dept.equals(td00.getSjdw())) {
					limit = "sjdw";
					node_id = 10202L;
				} else {
					limit = "xmgly";
					node_id = 10201L;
				}
			}

			gcxxList.add(td00);
		}
		modelMap.put("node_id", node_id);
		modelMap.put("limit", limit);
		modelMap.put("gcxxList", gcxxList);

		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);

		return new ModelAndView("/WEB-INF/jsp/form/gcxxList.jsp", modelMap);

	}

	@RequestMapping("/form/ajaxGcxxDel.do")
	public void ajaxGcxxDel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = convertUtil.toLong(request.getParameter("id"));
		PrintWriter out = null;

		response.setContentType("text/html;charset=UTF-8");
		Session session = saveService.getHiberbateSession();
		Transaction tx = session.beginTransaction();
		tx.begin();
		// 获取用户对象
		try {
			out = response.getWriter();
			session.createQuery("delete from Td00_gcxx where id=" + id)
					.executeUpdate();
			session
					.createQuery(
							"update Td00_gcxx set glgc_id = null where glgc_id = "
									+ id).executeUpdate();
			out
					.print("{\"statusCode\":\"200\", \"message\":\"删除成功\", \"callbackType\":\"forward\"}");
			session.flush();
			tx.commit();
		} catch (IOException e) {
			tx.rollback();
			out.print("{\"statusCode\":\"300\", \"message\":\"删除失败\"}");
		} finally {
			session.close();
		}
	}

	@RequestMapping("/form/ajaxXmxxDel.do")
	public void ajaxXmxxDel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = convertUtil.toLong(request.getParameter("id"));
		PrintWriter out = null;

		response.setContentType("text/html;charset=UTF-8");
		Session session = saveService.getHiberbateSession();
		Transaction tx = session.beginTransaction();
		tx.begin();
		// 获取用户对象
		try {
			out = response.getWriter();
			session.createQuery("delete from Td01_xmxx where id=" + id)
					.executeUpdate();
			session.createQuery(
					"update Td00_gcxx set xm_id = null where xm_id = " + id)
					.executeUpdate();
			out
					.print("{\"statusCode\":\"200\", \"message\":\"删除成功\", \"callbackType\":\"forward\"}");
			session.flush();
			tx.commit();
		} catch (IOException e) {
			tx.rollback();
			out.print("{\"statusCode\":\"300\", \"message\":\"删除失败\"}");
		} finally {
			session.close();
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/form/xqxxList.do")
	public ModelAndView xqxxList(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		// 分页
		Integer totalPages = 1;
		Integer totalCount = 0;
		Integer pageNum = convertUtil.toInteger(
				request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request
				.getParameter("orderField"), "id");
		if (orderField.equals("")) {
			orderField = "id";
		}
		String orderDirection = convertUtil.toString(request
				.getParameter("orderDirection"), "desc");
		if (orderDirection.equals("")) {
			orderDirection = "desc";
		}
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);

		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");

		// 查询条件
		String keyword = convertUtil.toString(request.getParameter("keyword"));

		StringBuffer hsql = new StringBuffer();
		hsql.append("select xqxx from Td06_xqs xqxx where ");
		hsql
				.append(" exists (select 'x' from Tb15_docflow tb15 where tb15.module_id = 109 and tb15.user_id = ");
		hsql.append(user.getId());
		hsql.append(" and xqxx.project_id = tb15.project_id) ");

		// 关键字
		if (!keyword.equals("")) {
			hsql.append(" and (xqxx.xqmc like '%" + keyword + "%')");
		}

		// order排序
		hsql.append(" order by " + orderField);
		hsql.append(" " + orderDirection);

		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum,
				numPerPage);
		// 获取结果集
		List<Td06_xqs> xqxxList = new ArrayList<Td06_xqs>();
		while (ro.next()) {
			Td06_xqs td06 = (Td06_xqs) ro.get("xqxx");
			xqxxList.add(td06);
		}
		modelMap.put("xqxxList", xqxxList);

		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);

		return new ModelAndView("/WEB-INF/jsp/form/xqxxList.jsp", modelMap);

	}

	@RequestMapping("/search/xmxxList.do")
	public ModelAndView xmxxList(HttpServletRequest request,
			HttpServletResponse response) {
		String view = "/WEB-INF/jsp/search/xmxxList.jsp";
		ModelMap modelMap = new ModelMap();
		StringBuffer hql = new StringBuffer();
		List objList = new LinkedList();
		ResultObject ro = null;
		Ta03_user user=(Ta03_user) request.getSession().getAttribute("user");
		Integer totalCount = 0;
		Integer totalPages = 0;
		Integer pageNum = convertUtil.toInteger(
				request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request
				.getParameter("orderField"), "xmmc");
		String orderDirection = convertUtil.toString(request
				.getParameter("orderDirection"), "asc");
		String keyword = convertUtil.toString(request.getParameter("keyword"));
		String jssj = convertUtil.toString(request.getParameter("jssj"));
		String xmzt = convertUtil.toString(request.getParameter("xmzt"));
		String ssdq=convertUtil.toString(request.getParameter("ssdq"));
		String xmgly=convertUtil.toString(request.getParameter("xmgly"));
		String sjjgsj=convertUtil.toString(request.getParameter("sjjgsj"));

		hql.append("select x from Td01_xmxx x,Ta01_dept d,Ta03_user u ");

		hql.append("where 1=1 ");
		hql.append("and d.id=u.dept_id "); 
		hql.append("and u.name=x.xmgly ");
		hql.append("and (u.dept_id=");
		hql.append(user.getDept_id());
		hql.append(" or d.parent_dept=");
		hql.append(user.getDept_id());
		hql.append(")");
		if (!keyword.equals("")) {
			hql.append("and (x.xmmc like '%");
			hql.append(keyword);
			hql.append("%' ");
			hql.append("or x.xmbh like '%");
			hql.append(keyword);
			hql.append("%') ");
		}
		if (!jssj.equals("")) {
			hql.append("and x.jssj=to_date('");
			hql.append(jssj); 
			hql.append("','yyyy-mm-dd'");
			hql.append(") ");
		}
		if (!xmzt.equals("")) {
			hql.append("and x.xmzt='");
			hql.append(xmzt);
			hql.append("' ");
		}
		if (!ssdq.equals("")) {
			hql.append("and x.ssdq='");
			hql.append(ssdq);
			hql.append("' ");
		}
		if (!xmgly.equals("")) {
			hql.append("and x.xmgly='");
			hql.append(xmgly); 
			hql.append("' ");
		}
		if (!sjjgsj.equals("")) {
			hql.append("and x.sjjgsj=to_date('");
			hql.append(sjjgsj);
			hql.append("','yyyy-mm-dd') ");
		}
		hql.append("order by x.");
		hql.append(orderField);
		hql.append(" ");
		hql.append(orderDirection);

		ro = queryService.searchByPage(hql.toString(), pageNum, numPerPage);
		while (ro.next()) {
			objList.add(ro.get("x"));
		}
		totalCount = ro.getTotalRows();
		totalPages = ro.getTotalPages();

		//地区
		List areaList=queryService.searchList("select distinct(a.name) from Tc02_area a");
		
		//项目经理
		StringBuffer managerHql=new StringBuffer();
		managerHql.append("select distinct(u.name) ");
		managerHql.append("from Ta03_user u,Ta11_sta_user su,Ta02_station s,Ta01_dept d ");
		managerHql.append("where 1=1 ");
		managerHql.append("and u.id=su.user_id and su.station_id=s.id and u.dept_id=d.id ");
		managerHql.append("and (d.id=");
		managerHql.append(user.getDept_id());
		managerHql.append(" or d.parent_dept =");
		managerHql.append(user.getDept_id());
		managerHql.append(") ");
		managerHql.append(" and s.name like '%项目管理岗%' ");
		managerHql.append("order by u.name asc");
		List xmglyList=queryService.searchList(managerHql.toString());
		
		modelMap.put("areaList", areaList);
		modelMap.put("xmxxList", objList);
		modelMap.put("xmglyList", xmglyList);
		modelMap.put("xmgly", xmgly);
		modelMap.put("jssj", jssj);
		modelMap.put("xmzt", xmzt);
		modelMap.put("ssdq", ssdq);
		modelMap.put("sjjgsj", sjjgsj);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("pageNum", pageNum);
		modelMap.put("totalCount", totalCount);
		modelMap.put("totalPages", totalPages);
		return new ModelAndView(view, modelMap);
	}
	
	@RequestMapping("/form/orderList.do")
	public ModelAndView  orderList(HttpServletRequest request,HttpServletResponse response) {
		String view="/WEB-INF/jsp/form/orderList.jsp";
		ModelMap modelMap=new ModelMap(); 
		StringBuffer hql = new StringBuffer();
		List objList = new LinkedList();
		ResultObject ro = null; 
		Integer totalCount = 0;
		Integer totalPages = 0;
		Integer pageNum = convertUtil.toInteger(
				request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request
				.getParameter("orderField"), "td00.id");
		String orderDirection = convertUtil.toString(request
				.getParameter("orderDirection"), "asc");
		Ta03_user user=(Ta03_user) request.getSession().getAttribute("user");
		String keyword = convertUtil.toString(request.getParameter("keyword"));
		String ddzt = convertUtil.toString(request.getParameter("ddzt"));
		
		String curRole = null;
		Long node_id = -1L;
		
		Map<String, Ta04_role> rolesMap = (Map<String, Ta04_role>) request.getSession().getAttribute("rolesMap");
		String login_id = convertUtil.toString(user.getLogin_id());
		if (rolesMap.get("100106") != null) {
			curRole = "ddgly";
			node_id = 11404L;
		}
		else if(login_id.substring(0,1).equals("7")){
			curRole = "sjdw";
			node_id = 11402L;
		}
		else if(login_id.substring(0,1).equals("8")){
			curRole = "sgdw";
			node_id = 11403L;
		}
		else {
			curRole = "xmgly";
			node_id = 11401L;
		}

		hql.append("select td00 ");
		hql.append("from Td00_gcxx td00,Ti03_xqly ti03 ");
		hql.append("where td00.id = ti03.project_id "); 
		if (curRole.equals("ddgly")) {
			hql.append("and ddgly = '");
			hql.append(user.getName());
			hql.append("' ");
		}
		else if(curRole.equals("xmgly")){
			hql.append("and xmgly = '");
			hql.append(user.getName());
			hql.append("' ");
		}
		else if(curRole.equals("sgdw")){
			hql.append("and sgdw = '");
			hql.append(user.getDept_name());
			hql.append("' ");
		}
		else{
			hql.append("and sjdw = '");
			hql.append(user.getDept_name());
			hql.append("' ");
		}
		
		if (!keyword.equals("")) {
			hql.append("and (td00.gcmc like '%");
			hql.append(keyword);
			hql.append("%' or td00.gcbh like '%");
			hql.append(keyword);
			hql.append("%' or td00.lxxx like '%");
			hql.append(keyword);
			hql.append("%')");
		}
		
		if (!ddzt.equals("")) {
			hql.append(" and td00.ddzt = '");
			hql.append(ddzt);
			hql.append("' ");
		}
		hql.append("order by ");
		hql.append(orderField);
		hql.append(" ");
		hql.append(orderDirection);
		
		ro=queryService.searchByPage(hql.toString(), pageNum, numPerPage);
		while (ro.next()) {
			objList.add(ro.get("td00"));
		}
		
		totalCount=ro.getTotalRows();
		totalPages=ro.getTotalPages();
		
		//获取定单状态
		QueryBuilder queryBuilder = new HibernateQueryBuilder(Tc01_property.class);
		queryBuilder.eq("type", "定单状态");
		queryBuilder.like("flag",curRole,MatchMode.ANYWHERE);
		queryBuilder.addOrderBy(Order.asc("id"));
		List tmpList = queryService.searchList(queryBuilder);
		if (tmpList != null) {
			List<Tc01_property> ddztList = new LinkedList<Tc01_property>();
			for (java.util.Iterator<?> itr = tmpList.iterator(); itr.hasNext();) {
				ddztList.add((Tc01_property) itr.next());
			}
			request.setAttribute("ddztList", ddztList);
		}
		
		modelMap.put("node_id", node_id);
		modelMap.put("objList", objList);
		modelMap.put("keyword", keyword);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("pageNum", pageNum);
		modelMap.put("totalCount", totalCount);
		modelMap.put("totalPages", totalPages);
		return new ModelAndView(view,modelMap);
	}
	
	@RequestMapping("/form/orderListForNeed.do")
	public ModelAndView  orderListForNeed(HttpServletRequest request,HttpServletResponse response) {
		String view="/WEB-INF/jsp/form/orderList.jsp";
		ModelMap modelMap=new ModelMap(); 
		StringBuffer hql = new StringBuffer();
		List objList = new LinkedList();
		ResultObject ro = null; 
		Integer totalCount = 0;
		Integer totalPages = 0;
		Integer pageNum = convertUtil.toInteger(
				request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request
				.getParameter("orderField"), "td00.id");
		String orderDirection = convertUtil.toString(request
				.getParameter("orderDirection"), "asc");
		String keyword = convertUtil.toString(request.getParameter("keyword"));
		
		Ta03_user user=(Ta03_user) request.getSession().getAttribute("user");
		Map<String, Ta04_role> rolesMap = (Map<String, Ta04_role>) request.getSession().getAttribute("rolesMap");
		String user_zy = user.getZys();
		
		hql.append("select td00 ");
		hql.append("from Td00_gcxx td00,Ti03_xqly ti03 ");
		hql.append("where td00.id = ti03.project_id "); 
		if (rolesMap.get("100106") == null) {
			hql.append("and ((xmgly = '");
			hql.append(user.getName());
			hql.append("' and sgdw is null) or (sjdw = '");
			hql.append(user.getDept_name());
			hql.append("' and sjysl is null) ");
			hql.append("or (sgdw = '");
			hql.append(user.getDept_name());
			hql.append("' and gclb in (" + user_zy + ") and sgysl is null)) ");
		}
		if (!keyword.equals("")) {
			hql.append("and (td00.gcmc like '%");
			hql.append(keyword);
			hql.append("%' or td00.gcbh like '%");
			hql.append(keyword);
			hql.append("%' or td00.lxxx like '%");
			hql.append(keyword);
			hql.append("%')");
		}
		hql.append("order by ");
		hql.append(orderField);
		hql.append(" ");
		hql.append(orderDirection);
		
		ro=queryService.searchByPage(hql.toString(), pageNum, numPerPage);
		while (ro.next()) {
			objList.add(ro.get("td00"));
		}
		
		Long node_id = -1L;
		String login_id = convertUtil.toString(user.getLogin_id());
		if(login_id.substring(0,1).equals("7")){
			node_id = 11402L;
		}
		else if(login_id.substring(0,1).equals("8")){
			node_id = 11403L;
		}
		else{
			node_id = 11401L;
		}
		
		totalCount=ro.getTotalRows();
		totalPages=ro.getTotalPages();
		
		/*
		 * 派单状态（派项目管理员）
		 */
		List<Object> pdlbList = new LinkedList<Object>();
		Properties p = new Properties();
		p.setProperty("show", "已派单");
		p.setProperty("value", "ypd");
		pdlbList.add(p);
		p = new Properties();
		p.setProperty("show", "未派单");
		p.setProperty("value", "wpd");
		pdlbList.add(p);
		modelMap.put("pdlbList", pdlbList);
		
		//获取项目状态
		QueryBuilder queryBuilder = new HibernateQueryBuilder(Tc01_property.class);
		queryBuilder.eq("type", "工程状态");
		queryBuilder.addOrderBy(Order.asc("id"));
		List tmpList = queryService.searchList(queryBuilder);
		if (tmpList != null) {
			List<Tc01_property> xmztList = new LinkedList<Tc01_property>();
			for (java.util.Iterator<?> itr = tmpList.iterator(); itr.hasNext();) {
				xmztList.add((Tc01_property) itr.next());
			}
			request.setAttribute("xmztList", xmztList);
		}
		
		modelMap.put("node_id", node_id);
		modelMap.put("objList", objList);
		modelMap.put("keyword", keyword);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("pageNum", pageNum);
		modelMap.put("totalCount", totalCount);
		modelMap.put("totalPages", totalPages);
		return new ModelAndView(view,modelMap);
	}
}
