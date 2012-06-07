package com.rms.controller.wxdw;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta02_station;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta04_role;
import com.netsky.base.dataObjects.Ta07_formfield;
import com.netsky.base.dataObjects.Ta11_sta_user;
import com.netsky.base.dataObjects.Ta22_user_idea;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.rms.dataObjects.base.Tc02_area;
import com.rms.dataObjects.wxdw.Tf01_wxdw;
import com.rms.dataObjects.wxdw.Tf02_sgd;
import com.rms.dataObjects.wxdw.Tf04_wxdw_user;
import com.rms.dataObjects.wxdw.Tf05_wxdw_dygx;
import com.rms.dataObjects.wxdw.Tf06_clb;

@Controller
public class Wxdw {
	/**
	 * 异常捕捉
	 */
	@Autowired
	private ExceptionService exceptionService;

	/**
	 * 查询服务
	 */
	@Autowired
	private QueryService queryService;

	/**
	 * 保存服务
	 */
	@Autowired
	private SaveService saveService;

	/**
	 * 外协单位列表
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/wxdwList.do")
	public ModelAndView wxdwList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		// 分页
		Integer totalPages = 1;
		Integer totalCount = 0;
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request.getParameter("orderField"), "mc");
		if (orderField.equals("")) {
			orderField = "mc";
		}
		String orderDirection = convertUtil.toString(request.getParameter("orderDirection"), "desc");
		if (orderDirection.equals("")) {
			orderDirection = "desc";
		}
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		// 查询条件
		String mc = convertUtil.toString(request.getParameter("mc"));
		String lb = convertUtil.toString(request.getParameter("lb"));

		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Tf01_wxdw wxdw where 1=1");
		// where条件
		// 类别
		if (!lb.equals("")) {
			hsql.append(" and lb='" + lb.substring(0, 2) + "'");
		}
		// 名称
		if (!mc.equals("")) {
			hsql.append(" and mc like '%" + mc + "%'");
		}
		// order排序
		// orderField
		hsql.append(" order by " + orderField);
		// orderDirection
		hsql.append(" " + orderDirection);
		if ("yes".equals(request.getParameter("toExcel"))) {
			totalCount = convertUtil.toInteger(queryService.searchList(" select count(*) " + hsql.toString()).get(0));
			numPerPage = totalCount == 0 ? 1 : totalCount;
			pageNum = 1;
		}

		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum, numPerPage);
		// 获取结果集
		List<Tf01_wxdw> wxdwList = new ArrayList<Tf01_wxdw>();
		// 导EXCEL
		if ("yes".equals(request.getParameter("toExcel"))) {
			Map<String, List> sheetMap = new HashMap<String, List>();
			List sheetList = new LinkedList();
			List titleList = new LinkedList();
			String form_title = "外协单位信息.xls";
			titleList.add("类别");
			titleList.add("单位名称");
			titleList.add("地址");
			titleList.add("状态");
			sheetList.add(titleList);
			List<List> docList = new LinkedList<List>();
			while (ro.next()) {
				List row = new LinkedList();
				Tf01_wxdw tf01 = (Tf01_wxdw) ro.get("wxdw");
				row.add(tf01.getLb());
				row.add(tf01.getMc());
				row.add(tf01.getDwdz());
				row.add(tf01.getZt());
				docList.add(row);
			}
			sheetList.add(docList);
			sheetMap.put(form_title, sheetList);
			request.setAttribute("ExcelName", form_title);
			request.setAttribute("sheetMap", sheetMap);
			return new ModelAndView("/export/toExcelWhithList.do");
		}
		while (ro.next()) {
			wxdwList.add((Tf01_wxdw) ro.get("wxdw"));
		}
		modelMap.put("wxdwList", wxdwList);
		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		// 页面所需内容
		// 类别
		List<String> lbList = new ArrayList<String>();
		lbList.add("设计单位");
		lbList.add("施工单位");
		lbList.add("监理单位");
		modelMap.put("lbList", lbList);
		return new ModelAndView("/WEB-INF/jsp/wxdw/wxdwList.jsp", modelMap);
	}

	/**
	 * 外协单位信息
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/wxdwEdit.do")
	public ModelAndView wxdwEdit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		Long id = convertUtil.toLong(request.getParameter("id"));
		if (id != -1L) {
			modelMap.put("Tf01_wxdw", queryService.searchById(Tf01_wxdw.class, id));
		}
		return new ModelAndView("/WEB-INF/jsp/wxdw/wxdwEdit.jsp", modelMap);
	}

	/**
	 * 新建外协单位时获取NO字段(LOGIN_ID前三位)
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/getWxdwNOAjax.do")
	public void getWxdwNOAjax(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		response.setCharacterEncoding(request.getCharacterEncoding());
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String lb = convertUtil.toString(URLDecoder.decode(request.getParameter("lb"), "UTF-8"));
		String no_start = "";
		if (lb.equals("施工")) {
			no_start = "8";
		} else if (lb.equals("设计")) {
			no_start = "7";
		} else if (lb.equals("监理")) {
			no_start = "9";
		}
		String no = convertUtil.toString(queryService.searchList(
				"select max(no) from Tf01_wxdw where no like'" + no_start + "%'").get(0));
		if (no.equals("")) {
			if (lb.equals("施工")) {
				no = "801";
			} else if (lb.equals("设计")) {
				no = "701";
			} else if (lb.equals("监理")) {
				no = "901";
			}
		} else {
			no = "" + (convertUtil.toLong(no) + 1L);
		}
		out.print(no);
	}

	/**
	 * 用户配置列表
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/wxdwUserList.do")
	public ModelAndView wxdwUserList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		Long wxdw_id = convertUtil.toLong(request.getParameter("wxdw_id"));
		List<Ta03_user> wxdwUserList = (List<Ta03_user>) queryService
				.searchList("from Ta03_user ta03 where ta03.id in (select tf04.user_id from Tf04_wxdw_user tf04 where tf04.wxdw_id="
						+ wxdw_id + ")");
		modelMap.put("wxdwUserList", wxdwUserList);
		Map<Ta03_user, List<Ta02_station>> user_staMap = new HashMap<Ta03_user, List<Ta02_station>>();
		for (Ta03_user ta03 : wxdwUserList) {
			user_staMap
					.put(
							ta03,
							(List<Ta02_station>) queryService
									.searchList("from Ta02_station ta02 where ta02.id in (select station_id from Ta11_sta_user where user_id="
											+ ta03.getId() + ")"));
		}
		modelMap.put("user_staMap", user_staMap);
		if ("yes".equals(request.getParameter("toExcel"))) {
			Tf01_wxdw wxdw = (Tf01_wxdw) queryService.searchById(Tf01_wxdw.class, wxdw_id);
			Map<String, List> sheetMap = new HashMap<String, List>();
			List sheetList = new LinkedList();
			List titleList = new LinkedList();
			String form_title = wxdw.getMc() + "-用户信息.xls";
			titleList.add("工号");
			titleList.add("姓名");
			titleList.add("移动电话");
			titleList.add("性别");
			titleList.add("邮箱");
			titleList.add("相关岗位");
			sheetList.add(titleList);
			List<List> docList = new LinkedList<List>();
			for (Ta03_user ta03 : wxdwUserList) {
				List row = new LinkedList();
				row.add(ta03.getLogin_id());
				row.add(ta03.getName());
				row.add(ta03.getMobile_tel());
				row.add(ta03.getSex());
				row.add(ta03.getEmail());
				List<Ta02_station> staList = user_staMap.get(ta03);
				String xggw = "";
				for (Ta02_station ta02 : staList) {
					xggw += " " + ta02.getName();
				}
				row.add(xggw.trim());
				docList.add(row);
			}
			sheetList.add(docList);
			sheetMap.put(form_title, sheetList);
			request.setAttribute("ExcelName", form_title);
			request.setAttribute("sheetMap", sheetMap);
			return new ModelAndView("/export/toExcelWhithList.do");
		}
		return new ModelAndView("/WEB-INF/jsp/wxdw/wxdwUserList.jsp", modelMap);
	}

	/**
	 * 用户配置
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/wxdwUserEdit.do")
	public ModelAndView wxdwUserEdit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		Long wxdw_id = convertUtil.toLong(request.getParameter("wxdw_id"));
		Tf01_wxdw wxdw = (Tf01_wxdw) queryService.searchById(Tf01_wxdw.class, wxdw_id);
		String lb = convertUtil.toString(wxdw.getLb());
		if (lb.equals("施工")) {// 8
			lb = "[1]";
		} else if (lb.equals("设计")) {// 7
			lb = "[2]";
		} else if (lb.equals("监理")) {// 9
			lb = "[3]";
		}
		List<Ta02_station> ta02List = (List<Ta02_station>) queryService
				.searchList("from Ta02_station where flag like '%" + lb + "%'");
		modelMap.put("ta02List", ta02List);
		Long id = convertUtil.toLong(request.getParameter("id"));
		if (id != -1L) {
			modelMap.put("ta03", queryService.searchById(Ta03_user.class, id));
			List<Ta11_sta_user> ta11List = (List<Ta11_sta_user>) queryService
					.searchList("from Ta11_sta_user where user_id=" + id);
			Map<Long, Ta11_sta_user> ta11Map = new HashMap<Long, Ta11_sta_user>();
			for (Ta11_sta_user ta11 : ta11List) {
				ta11Map.put(ta11.getStation_id(), ta11);
			}
			modelMap.put("ta11Map", ta11Map);
		}
		Long dept_id = 4L;
		try {
			dept_id = convertUtil.toLong(queryService.searchList("select id from Ta01_dept where name ='外协单位'").get(0));
		} catch (Exception e) {
		}
		modelMap.put("dept_id", dept_id);
		return new ModelAndView("/WEB-INF/jsp/wxdw/wxdwUserEdit.jsp", modelMap);
	}

	/**
	 * 用户配置保存 系统自动获得账号前缀，自动生成账号和密码，初始密码与账号相同，自动配置用户对应的岗位和所属部门
	 */
	@RequestMapping("/wxdw/ajaxSaveWxdwUser.do")
	public void ajaxSaveWxdwUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding(request.getCharacterEncoding());
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		Session session = queryService.getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Long id = convertUtil.toLong(request.getParameter("ID"));
			Long wxdw_id = convertUtil.toLong(request.getParameter("WXDW_ID"));
			Long dept_id = convertUtil.toLong(request.getParameter("DEPT_ID"));
			String name = convertUtil.toString(request.getParameter("NAME"));
			String mobile_tel = convertUtil.toString(request.getParameter("MOBILE_TEL"));
			String sex = convertUtil.toString(request.getParameter("SEX"));
			String fix_tel = convertUtil.toString(request.getParameter("FIX_TEL"));
			String email = convertUtil.toString(request.getParameter("EMAIL"));
			String area_name = convertUtil.toString(request.getParameter("AREA_NAME"), "南京市");
			if (area_name.equals("")) {
				area_name = "南京市";
			}
			Long useflag = convertUtil.toLong(request.getParameter("USEFLAG"), 1L);
			String[] STATION_IDs = request.getParameterValues("STATION_ID");
			// 保存用户表 TA03
			Ta03_user ta03 = null;
			if (id != -1L) {
				ta03 = (Ta03_user) queryService.searchById(Ta03_user.class, id);
			} else {
				ta03 = new Ta03_user();
				// 制造LOGIN_ID
				Tf01_wxdw wxdw = (Tf01_wxdw) queryService.searchById(Tf01_wxdw.class, wxdw_id);
				String login_id = wxdw.getNo();
				List<String> ta03List = (List<String>) queryService
						.searchList("select substr(ta03.login_id,4,3) from Ta03_user ta03 where substr(ta03.login_id,1,3)='"
								+ login_id + "' order by login_id desc");
				if (ta03List != null && ta03List.size() != 0) {
					String s = ta03List.get(0);
					Long l = new Long(s);
					login_id += String.format("%03d", ++l);
				} else {
					login_id += "001";
				}
				ta03.setLogin_id(login_id);
				ta03.setPasswd(login_id);
			}
			ta03.setDept_id(dept_id);
			ta03.setName(name);
			ta03.setMobile_tel(mobile_tel);
			ta03.setSex(sex);
			ta03.setFix_tel(fix_tel);
			ta03.setEmail(email);
			ta03.setUseflag(useflag);
			ta03.setArea_name(area_name);
			session.saveOrUpdate(ta03);
			if (id == -1L) {
				Tf04_wxdw_user tf04 = new Tf04_wxdw_user();
				tf04.setUser_id(ta03.getId());
				tf04.setWxdw_id(wxdw_id);
				session.save(tf04);
			}
			// 保存用户岗位表 TA11
			session.createQuery("delete from Ta11_sta_user where user_id=" + ta03.getId()).executeUpdate();
			if (STATION_IDs != null) {
				for (String station_id : STATION_IDs) {
					Ta11_sta_user ta11 = new Ta11_sta_user();
					ta11.setStation_id(convertUtil.toLong(station_id));
					ta11.setUser_id(ta03.getId());
					session.save(ta11);
				}
			}
			session.flush();
			tx.commit();
			out
					.print("{\"statusCode\":\"200\",\"message\":\"保存成功！\", \"navTabId\":\"_current\", \"forwardUrl\":\"\", \"callbackType\":\"closeCurrentDialog\"}");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			out
					.print("{\"statusCode\":\"300\",\"message\":\"保存失败！\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");
		} finally {
			session.close();
		}
	}

	/**
	 * 区域专业
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/qyZyEdit.do")
	public ModelAndView qyZyEdit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		Long wxdw_id = convertUtil.toLong(request.getParameter("wxdw_id"));
		modelMap.put("qyList", queryService.searchList(Tc02_area.class));
		modelMap.put("zyList", queryService.searchList("from Tc01_property where type='工程类别'"));
		List<Tf05_wxdw_dygx> tf05List = (List<Tf05_wxdw_dygx>) queryService
				.searchList("from Tf05_wxdw_dygx where lb='qyzy' and wxdw_id=" + wxdw_id + " order by zy,dq");
		String zy = "";
		Map<String, Map<String, Tf05_wxdw_dygx>> dygxMap = new HashMap<String, Map<String, Tf05_wxdw_dygx>>();
		Map<String, Tf05_wxdw_dygx> dqMap = new HashMap<String, Tf05_wxdw_dygx>();
		for (Tf05_wxdw_dygx tf05 : tf05List) {
			if (!zy.equals(tf05.getZy())) {
				if (!dqMap.isEmpty()) {
					dygxMap.put(zy, dqMap);
					dqMap = new HashMap<String, Tf05_wxdw_dygx>();
				}
				zy = tf05.getZy();
			}
			dqMap.put(tf05.getDq(), tf05);
		}
		if (!dqMap.isEmpty()) {
			dygxMap.put(zy, dqMap);
		}
		modelMap.put("dygxMap", dygxMap);
		return new ModelAndView("/WEB-INF/jsp/wxdw/qyZyEdit.jsp", modelMap);
	}

	/**
	 * 配置保存 区域专业\份额占比\最大在建工程数\关联交易额
	 */
	@RequestMapping("/wxdw/ajaxSaveWxdwConfig.do")
	public void ajaxSaveWxdwConfig(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding(request.getCharacterEncoding());
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String[] qy = request.getParameterValues("qy");
		String[] zy = request.getParameterValues("zy");
		String lb = convertUtil.toString(request.getParameter("lb"));
		Long wxdw_id = convertUtil.toLong(request.getParameter("wxdw_id"));
		Long nd = convertUtil.toLong(request.getParameter("nd"), null);
		String[] nds = request.getParameterValues("nds");
		String[] v1 = request.getParameterValues("v1");
		String[] v2 = request.getParameterValues("v2");
		Session session = queryService.getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			String delsql = "delete from Tf05_wxdw_dygx where lb='" + lb + "' and wxdw_id=" + wxdw_id;
			if (nd != null) {
				delsql += " and nd=" + nd;
			}
			session.createQuery(delsql).executeUpdate();
			if (qy != null) {
				if (zy != null && qy.length == zy.length)
					for (int i = 0; i < qy.length; i++) {
						Tf05_wxdw_dygx tf05 = new Tf05_wxdw_dygx();
						tf05.setLb(lb);
						tf05.setWxdw_id(wxdw_id);
						tf05.setDq(qy[i]);
						tf05.setZy(zy[i]);
						tf05.setNd(nd);
						if (nds != null) {
							tf05.setNd(convertUtil.toLong(nds[i]));
						}
						if (v1 != null) {
							tf05.setV1(convertUtil.toDouble(v1[i]));
						}
						if (v2 != null) {
							tf05.setV2(v2[i]);
						}
						session.save(tf05);
					}
				else {
					throw new Exception("检查前台传参,ZY QY个数不一致");
				}
			}
			session.flush();
			tx.commit();
			out
					.print("{\"statusCode\":\"200\",\"message\":\"保存成功！\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			out
					.print("{\"statusCode\":\"300\",\"message\":\"保存失败！\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");

		} finally {
			session.close();
		}

	}

	/**
	 * 份额占比
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/fezbEdit.do")
	public ModelAndView fezbEdit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		Long wxdw_id = convertUtil.toLong(request.getParameter("wxdw_id"));
		int startyear = 2012;
		Calendar now = Calendar.getInstance();
		int currentyear = now.get(Calendar.YEAR) + 1;
		List<String> years = new ArrayList<String>();
		while (startyear <= currentyear) {
			years.add("" + startyear++);
		}
		modelMap.put("years", years);
		Long nd = convertUtil.toLong(request.getParameter("nd"), new Long(currentyear));
		modelMap.put("nd", nd);
		modelMap.put("qyList", queryService.searchList(Tc02_area.class));
		modelMap.put("zyList", queryService.searchList("from Tc01_property where type='工程类别'"));

		List<Tf05_wxdw_dygx> tf05List = (List<Tf05_wxdw_dygx>) queryService
				.searchList("from Tf05_wxdw_dygx where lb='qyzy' and wxdw_id=" + wxdw_id + " order by zy,dq");
		String zy = "";
		Map<String, Map<String, Tf05_wxdw_dygx>> dygxMap = new HashMap<String, Map<String, Tf05_wxdw_dygx>>();
		Map<String, Tf05_wxdw_dygx> dqMap = new HashMap<String, Tf05_wxdw_dygx>();
		for (Tf05_wxdw_dygx tf05 : tf05List) {
			if (!zy.equals(tf05.getZy())) {
				if (!dqMap.isEmpty()) {
					dygxMap.put(zy, dqMap);
					dqMap = new HashMap<String, Tf05_wxdw_dygx>();
				}
				zy = tf05.getZy();
			}
			dqMap.put(tf05.getDq(), tf05);
		}
		if (!dqMap.isEmpty()) {
			dygxMap.put(zy, dqMap);
		}
		modelMap.put("dygxMap", dygxMap);

		tf05List = (List<Tf05_wxdw_dygx>) queryService.searchList("from Tf05_wxdw_dygx where lb='fezb' and wxdw_id="
				+ wxdw_id + " and nd=" + nd + " order by zy,dq");
		zy = "";
		Map<String, Map<String, Tf05_wxdw_dygx>> fezbMap = new HashMap<String, Map<String, Tf05_wxdw_dygx>>();
		dqMap = new HashMap<String, Tf05_wxdw_dygx>();
		for (Tf05_wxdw_dygx tf05 : tf05List) {
			if (!zy.equals(tf05.getZy())) {
				if (!dqMap.isEmpty()) {
					fezbMap.put(zy, dqMap);
					dqMap = new HashMap<String, Tf05_wxdw_dygx>();
				}
				zy = tf05.getZy();
			}
			dqMap.put(tf05.getDq(), tf05);
		}
		if (!dqMap.isEmpty()) {
			fezbMap.put(zy, dqMap);
		}
		modelMap.put("fezbMap", fezbMap);
		return new ModelAndView("/WEB-INF/jsp/wxdw/fezbEdit.jsp", modelMap);
	}

	/**
	 * 最大在建工程数
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/zjgcsEdit.do")
	public ModelAndView zjgcsEdit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		int startyear = 2012;
		Calendar now = Calendar.getInstance();
		int currentyear = now.get(Calendar.YEAR) + 1;
		List<String> years = new ArrayList<String>();
		while (startyear <= currentyear) {
			years.add("" + startyear++);
		}
		modelMap.put("years", years);
		Long nd = convertUtil.toLong(request.getParameter("nd"), new Long(currentyear));
		modelMap.put("nd", nd);
		Long wxdw_id = convertUtil.toLong(request.getParameter("wxdw_id"));
		modelMap.put("zyList", queryService.searchList("from Tc01_property where type='工程类别'"));
		List<Tf05_wxdw_dygx> tf05List = (List<Tf05_wxdw_dygx>) queryService
				.searchList("from Tf05_wxdw_dygx where lb='zdgcs' and wxdw_id=" + wxdw_id + " order by zy");
		Map<String, Tf05_wxdw_dygx> zjgcsMap = new HashMap<String, Tf05_wxdw_dygx>();
		for (Tf05_wxdw_dygx tf05 : tf05List) {
			zjgcsMap.put(tf05.getZy(), tf05);
		}
		modelMap.put("zjgcsMap", zjgcsMap);
		return new ModelAndView("/WEB-INF/jsp/wxdw/zjgcsEdit.jsp", modelMap);
	}

	/**
	 * 关联交易额
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/gljyeEdit.do")
	public ModelAndView gljyeEdit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		int startyear = 2012;
		Calendar now = Calendar.getInstance();
		int currentyear = now.get(Calendar.YEAR) + 1;
		List<String> years = new ArrayList<String>();
		while (startyear <= currentyear) {
			years.add("" + startyear++);
		}
		modelMap.put("years", years);
		Long wxdw_id = convertUtil.toLong(request.getParameter("wxdw_id"));
		List<Tf05_wxdw_dygx> tf05List = (List<Tf05_wxdw_dygx>) queryService
				.searchList("from Tf05_wxdw_dygx where lb='gljye' and wxdw_id=" + wxdw_id + " order by nd");
		Map<String, Tf05_wxdw_dygx> gljyeMap = new HashMap<String, Tf05_wxdw_dygx>();
		for (Tf05_wxdw_dygx tf05 : tf05List) {
			gljyeMap.put(convertUtil.toString(tf05.getNd()), tf05);
		}
		modelMap.put("gljyeMap", gljyeMap);
		return new ModelAndView("/WEB-INF/jsp/wxdw/gljyeEdit.jsp", modelMap);
	}

	/**
	 * 施工队
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/sgdEdit.do")
	public ModelAndView sgdEdit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		Long wxdw_id = convertUtil.toLong(request.getParameter("wxdw_id"));
		// 获得施工队LIST(TF02)
		List<Tf02_sgd> sgdList = (List<Tf02_sgd>) queryService.searchList("from Tf02_sgd sgd where sgd.sgdw_id="
				+ wxdw_id + " order by sgd.id");
		modelMap.put("sgdList", sgdList);
		Map<Tf02_sgd, Map<String, Map<String, Tf05_wxdw_dygx>>> dygxMap = null;
		if (!sgdList.isEmpty()) {
			// 获得关联关系LIST(TF05 order by 施工队id)
			String glgxSql = "from Tf05_wxdw_dygx where lb='sgd' and wxdw_id in(select sgd.id from Tf02_sgd sgd where sgd.sgdw_id="
					+ wxdw_id + ") order by wxdw_id";
			List<Tf05_wxdw_dygx> tf05List = (List<Tf05_wxdw_dygx>) queryService.searchList(glgxSql);
			// 制造MAP<tf02,MAP<>>
			dygxMap = new HashMap<Tf02_sgd, Map<String, Map<String, Tf05_wxdw_dygx>>>();
			int i = 0;
			Tf02_sgd tf02 = sgdList.get(i);
			Map<String, Map<String, Tf05_wxdw_dygx>> tf05Map = new HashMap<String, Map<String, Tf05_wxdw_dygx>>();
			for (Tf05_wxdw_dygx tf05 : tf05List) {
				if (tf05.getWxdw_id().longValue() != tf02.getId()) {
					if (!tf05Map.isEmpty()) {
						dygxMap.put(tf02, tf05Map);
						tf05Map = new HashMap<String, Map<String, Tf05_wxdw_dygx>>();
						tf02 = sgdList.get(++i);
					}
				}
				Map<String, Tf05_wxdw_dygx> tmpMap = new HashMap<String, Tf05_wxdw_dygx>();
				tmpMap.put(tf05.getZy(), tf05);
				tf05Map.put(tf05.getDq(), tmpMap);
			}
			if (!tf05Map.isEmpty()) {
				dygxMap.put(tf02, tf05Map);
			}
		}
		modelMap.put("dygxMap", dygxMap);
		// 获得页面信息(该外协单位关联的业务类型)
		List<Tf05_wxdw_dygx> dygxList = (List<Tf05_wxdw_dygx>) queryService
				.searchList("from Tf05_wxdw_dygx where lb='qyzy' and wxdw_id=" + wxdw_id + " order by zy,dq");
		modelMap.put("dygxList", dygxList);
		return new ModelAndView("/WEB-INF/jsp/wxdw/sgdEdit.jsp", modelMap);
	}

	/**
	 * 保存施工队
	 */
	@RequestMapping("/wxdw/ajaxSaveSgd.do")
	public void ajaxSaveSgd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding(request.getCharacterEncoding());
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		Long wxdw_id = convertUtil.toLong(request.getParameter("wxdw_id"));
		String mc = convertUtil.toString(request.getParameter("mc"));
		String bz = convertUtil.toString(request.getParameter("bz"));
		Long id = convertUtil.toLong(request.getParameter("id"), null);
		String[] fps = request.getParameterValues("qyzyfp");
		Session session = queryService.getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Tf02_sgd sgd = new Tf02_sgd();
			sgd.setBz(bz);
			sgd.setId(id);
			sgd.setMc(mc);
			sgd.setSgdw_id(wxdw_id);
			session.saveOrUpdate(sgd);
			if (id != null) {
				session.createQuery("delete from Tf05_wxdw_dygx where lb='sgd' and wxdw_id=" + sgd.getId())
						.executeUpdate();
			}
			if (fps != null) {
				for (String string : fps) {
					String[] strArr = string.split("-");
					Tf05_wxdw_dygx tf05 = new Tf05_wxdw_dygx();
					tf05.setDq(strArr[0]);
					tf05.setZy(strArr[1]);
					tf05.setWxdw_id(sgd.getId());
					tf05.setLb("sgd");
					session.saveOrUpdate(tf05);
				}
			}
			session.flush();
			tx.commit();
			out
					.print("{\"statusCode\":\"200\",\"message\":\"保存成功！\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			out
					.print("{\"statusCode\":\"300\",\"message\":\"保存失败！\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");

		} finally {
			session.close();
		}

	}

	/**
	 * 删除施工队
	 */
	@RequestMapping("/wxdw/ajaxDelSgd.do")
	public void ajaxDelSgd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long sgd_id = convertUtil.toLong(request.getParameter("sgd_id"));
		response.setCharacterEncoding(request.getCharacterEncoding());
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		Session session = queryService.getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.createQuery("delete from Tf02_sgd where id=" + sgd_id).executeUpdate();
			session.createQuery("delete from Tf05_wxdw_dygx where lb='sgd' and wxdw_id=" + sgd_id).executeUpdate();
			session.flush();
			tx.commit();
			out
					.print("{\"statusCode\":\"200\",\"message\":\"删除成功！\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			out
					.print("{\"statusCode\":\"300\",\"message\":\"删除失败！\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");

		} finally {
			session.close();
		}
	}

	/**
	 * 基础材料列表
	 */
	@RequestMapping("/wxdw/jcclList.do")
	public ModelAndView jcclList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		// 分页
		Integer totalPages = 1;
		Integer totalCount = 0;
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request.getParameter("orderField"), "clmc");
		if (orderField.equals("")) {
			orderField = "clmc";
		}
		String orderDirection = convertUtil.toString(request.getParameter("orderDirection"), "desc");
		if (orderDirection.equals("")) {
			orderDirection = "desc";
		}
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		// 查询条件
		String clmc = convertUtil.toString(request.getParameter("clmc"));
		String cllx = convertUtil.toString(request.getParameter("cllx"));

		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Tf06_clb clb where 1=1");
		// where条件
		// 类别
		if (!cllx.equals("")) {
			hsql.append(" and cllx='" + cllx + "'");
		}
		// 名称
		if (!clmc.equals("")) {
			hsql.append(" and clmc like '%" + clmc + "%'");
		}
		// order排序
		// orderField
		hsql.append(" order by " + orderField);
		// orderDirection
		hsql.append(" " + orderDirection);
		if ("yes".equals(request.getParameter("toExcel"))) {
			totalCount = convertUtil.toInteger(queryService.searchList(" select count(*) " + hsql.toString()).get(0));
			numPerPage = totalCount == 0 ? 1 : totalCount;
			pageNum = 1;
		}

		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum, numPerPage);
		// 获取结果集
		List<Tf06_clb> clbList = new ArrayList<Tf06_clb>();
		// 导EXCEL
		if ("yes".equals(request.getParameter("toExcel"))) {
			Map<String, List> sheetMap = new HashMap<String, List>();
			List sheetList = new LinkedList();
			List titleList = new LinkedList();
			String form_title = "基础材料信息.xls";
			titleList.add("名称");
			titleList.add("规格");
			titleList.add("型号");
			titleList.add("单位");
			titleList.add("类别");
			sheetList.add(titleList);
			List<List> docList = new LinkedList<List>();
			while (ro.next()) {
				List row = new LinkedList();
				Tf06_clb tf06 = (Tf06_clb) ro.get("clb");
				row.add(tf06.getClmc());
				row.add(tf06.getGg());
				row.add(tf06.getXh());
				row.add(tf06.getDw());
				row.add(tf06.getCllx());
				docList.add(row);
			}
			sheetList.add(docList);
			sheetMap.put(form_title, sheetList);
			request.setAttribute("ExcelName", form_title);
			request.setAttribute("sheetMap", sheetMap);
			return new ModelAndView("/export/toExcelWhithList.do");
		}
		while (ro.next()) {
			clbList.add((Tf06_clb) ro.get("clb"));
		}
		modelMap.put("clbList", clbList);
		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		// 页面所需内容
		// 类别
		List<String> cllxList = (List<String>)queryService.searchList("from Tc01_property where type='材料类型'");
		modelMap.put("cllxList", cllxList);
		return new ModelAndView("/WEB-INF/jsp/wxdw/jcclList.jsp", modelMap);
	}

	/**
	 * 基础材料信息
	 */
	@RequestMapping("/wxdw/jcclEdit.do")
	public ModelAndView jcclEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		Long id = convertUtil.toLong(request.getParameter("id"));
		modelMap.put("tf06", queryService.searchById(Tf06_clb.class, id));
		List<String> cllxList = (List<String>)queryService.searchList("from Tc01_property where type='材料类型'");
		modelMap.put("cllxList", cllxList);
		return new ModelAndView("/WEB-INF/jsp/wxdw/jcclEdit.jsp", modelMap);
	}

	/**
	 * 工程材料出入库列表
	 */
	@RequestMapping("/wxdw/gcclList.do")
	public ModelAndView gcclList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView("/WEB-INF/jsp/wxdw/gcclList.jsp", modelMap);
	}

	/**
	 * 工程材料入库/出库/缴料
	 */
	@RequestMapping("/wxdw/crkList.do")
	public ModelAndView crkList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView("/WEB-INF/jsp/wxdw/crkList.jsp", modelMap);
	}

	/**
	 * 工程材料入库/出库/缴料明细
	 */
	@RequestMapping("/wxdw/crkMxList.do")
	public ModelAndView crkMxList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView("/WEB-INF/jsp/wxdw/crkMxList.jsp", modelMap);
	}

	/**
	 * 材料信息
	 */
	@RequestMapping("/wxdw/gcKcList.do")
	public ModelAndView gcKcList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView("/WEB-INF/jsp/wxdw/gcKcList.jsp", modelMap);
	}

	public static void main(String[] args) {
	}
}
