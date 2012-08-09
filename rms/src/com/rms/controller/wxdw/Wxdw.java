package com.rms.controller.wxdw;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta02_station;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta11_sta_user;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.flow.vo.Vc2_gcxx_gzltb;
import com.netsky.base.flow.vo.Vc3_gcxx_jlrj;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.rms.controller.base.ExcelRead;
import com.rms.dataObjects.base.Tc01_property;
import com.rms.dataObjects.base.Tc02_area;
import com.rms.dataObjects.form.Td00_gcxx;
import com.rms.dataObjects.wxdw.Tf01_wxdw;
import com.rms.dataObjects.wxdw.Tf02_sgd;
import com.rms.dataObjects.wxdw.Tf04_wxdw_user;
import com.rms.dataObjects.wxdw.Tf05_wxdw_dygx;
import com.rms.dataObjects.wxdw.Tf06_clb;
import com.rms.dataObjects.wxdw.Tf07_kcb;
import com.rms.dataObjects.wxdw.Tf08_clmxb;
import com.rms.dataObjects.wxdw.Tf10_gzltb;
import com.rms.dataObjects.wxdw.Tf14_jlrj;
import com.sun.java_cup.internal.sym;

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
		String lb = convertUtil.toString(request.getParameter("lb"));
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
		List<String> dqList = (List<String>) queryService
				.searchList("select distinct(dq) from Tf05_wxdw_dygx where lb='qyzy' and wxdw_id=" + wxdw_id);
		modelMap.put("dqList", dqList);
		if (id != -1L) {
			modelMap.put("ta03", queryService.searchById(Ta03_user.class, id));
			List<Ta11_sta_user> ta11List = (List<Ta11_sta_user>) queryService
					.searchList("from Ta11_sta_user where user_id=" + id);
			Map<Long, Ta11_sta_user> ta11Map = new HashMap<Long, Ta11_sta_user>();
			for (Ta11_sta_user ta11 : ta11List) {
				ta11Map.put(ta11.getStation_id(), ta11);
			}
			modelMap.put("ta11Map", ta11Map);

			List<Tf04_wxdw_user> tf04TmpList = (List<Tf04_wxdw_user>) queryService
					.searchList("from Tf04_wxdw_user where wxdw_id=" + wxdw_id + " and user_id=" + id);
			if (tf04TmpList != null && !tf04TmpList.isEmpty()) {
				Tf04_wxdw_user tf04 = tf04TmpList.get(0);
				String area = convertUtil.toString(tf04.getArea());
				String[] areas = area.split(" ");
				Map<String, Object> dqMap = new HashMap<String, Object>();
				for (String string : areas) {
					dqMap.put(string, new Object());
				}
				modelMap.put("dqMap", dqMap);
			}
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
		tx.begin();
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
			String[] DQs = request.getParameterValues("DQ");
			String dq = "";
			for (String string : DQs) {
				dq+=" "+string;
			}
			dq = dq.trim();
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
			Tf04_wxdw_user tf04 = null;
			if (id == -1L) {
				tf04 = new Tf04_wxdw_user();
				tf04.setUser_id(ta03.getId());
				tf04.setWxdw_id(wxdw_id);
			}
			else {
				tf04 = ((List<Tf04_wxdw_user>)queryService.searchList("from Tf04_wxdw_user where wxdw_id="+wxdw_id+" and user_id="+id)).get(0);
			}
			tf04.setArea(dq);
			session.save(tf04);
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
		tx.begin();
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
		Long nd = convertUtil.toLong(request.getParameter("nd"), new Long(currentyear - 1));
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
		Long nd = convertUtil.toLong(request.getParameter("nd"), new Long(currentyear - 1));
		modelMap.put("nd", nd);
		Long wxdw_id = convertUtil.toLong(request.getParameter("wxdw_id"));
		modelMap.put("zyList", queryService.searchList("from Tc01_property where type='工程类别'"));
		List<Tf05_wxdw_dygx> tf05List = (List<Tf05_wxdw_dygx>) queryService
				.searchList("from Tf05_wxdw_dygx where lb='zdgcs' and wxdw_id=" + wxdw_id + " and nd='" + nd
						+ "' order by zy");
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
		tx.begin();
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
		tx.begin();
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
			if (!"yes".equals(request.getParameter("model"))) {

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
		List<String> cllxList = (List<String>) queryService.searchList("from Tc01_property where type='材料类型'");
		modelMap.put("cllxList", cllxList);
		return new ModelAndView("/WEB-INF/jsp/wxdw/jcclList.jsp", modelMap);
	}

	/**
	 * 基础材料导入
	 */
	@RequestMapping("/wxdw/jcclImport.do")
	public void jcclImport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String json = "{\"statusCode\":\"200\", \"message\":\"导入成功\", \"navTabId\":\"" + "jcclList"
				+ "\", \"forwardUrl\":\"" + "" + "\", \"callbackType\":\"" + "" + "\"}";
		Session session = saveService.getHiberbateSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
			Iterator<?> it = mrequest.getFileNames();
			while (it.hasNext()) {
				String fileDispath = (String) it.next();
				MultipartFile file = mrequest.getFile(fileDispath);
				if (file.getName() != null && !file.getName().equals("") && file.getInputStream().available() > 0) {
					List<List<String>> rowlist = (List<List<String>>) ExcelRead.readEcelFilebyStream(file
							.getInputStream(), file.getOriginalFilename(), 0, 1);
					// 遍历全表

					session.createQuery("delete from Tf06_clb where id>342").executeUpdate();
					rowlist.remove(0);
					for (List<String> row : rowlist) {
						Tf06_clb tf06 = new Tf06_clb();
						tf06.setClmc(row.get(0));
						tf06.setGg(row.get(1));
						tf06.setXh(row.get(2));
						tf06.setDw(row.get(3));
						tf06.setCllx(row.get(4));
						session.save(tf06);
					}
					session.flush();
					transaction.commit();

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			json = "{\"statusCode\":\"300\", \"message\":\"导入失败\", \"navTabId\":\"" + "" + "\", \"forwardUrl\":\"" + ""
					+ "\", \"callbackType\":\"" + "" + "\"}";
			transaction.rollback();
		} finally {
			session.close();
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(json);
	}

	/**
	 * 基础材料信息
	 */
	@RequestMapping("/wxdw/jcclEdit.do")
	public ModelAndView jcclEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		Long id = convertUtil.toLong(request.getParameter("id"));
		modelMap.put("tf06", queryService.searchById(Tf06_clb.class, id));
		List<String> cllxList = (List<String>) queryService.searchList("from Tc01_property where type='材料类型'");
		modelMap.put("cllxList", cllxList);
		return new ModelAndView("/WEB-INF/jsp/wxdw/jcclEdit.jsp", modelMap);
	}

	/**
	 * 工程材料出入库列表
	 */
	@RequestMapping("/wxdw/gcclList.do")
	public ModelAndView gcclList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		// 分页
		Integer totalPages = 1;
		Integer totalCount = 0;
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request.getParameter("orderField"), "gcmc");
		if (orderField.equals("")) {
			orderField = "gcmc";
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
		String gcmc = convertUtil.toString(request.getParameter("gcmc"));

		StringBuffer hsql = new StringBuffer();
		hsql
				.append("select distinct(gcxx) as gcxx from Td00_gcxx gcxx where exists (select tb15 from Tb15_docflow tb15 where tb15.project_id=gcxx.id and tb15.user_id="
						+ ((Ta03_user) request.getSession().getAttribute("user")).getId() + ")");
		// where条件
		// 名称
		if (!gcmc.equals("")) {
			hsql.append(" and gcxx.gcmc like '%" + gcmc + "%'");
		}
		// order排序
		// orderField
		hsql.append(" order by " + orderField);
		// orderDirection
		hsql.append(" " + orderDirection);
		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum, numPerPage);
		// 获取结果集
		List<Td00_gcxx> gcxxList = new ArrayList<Td00_gcxx>();
		while (ro.next()) {
			gcxxList.add((Td00_gcxx) ro.get("gcxx"));
		}
		modelMap.put("gcxxList", gcxxList);
		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		return new ModelAndView("/WEB-INF/jsp/wxdw/gcclList.jsp", modelMap);
	}

	/**
	 * 工程材料入库/出库/缴料
	 */
	@RequestMapping("/wxdw/crkEdit.do")
	public ModelAndView crkEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		Tf04_wxdw_user tf04 = (Tf04_wxdw_user) queryService.searchList(
				"from Tf04_wxdw_user where user_id=" + user.getId()).get(0);
		modelMap.put("sgdw_id", tf04.getWxdw_id());
		Long project_id = convertUtil.toLong(request.getParameter("project_id"));
		Td00_gcxx gcxx = (Td00_gcxx) queryService.searchById(Td00_gcxx.class, project_id);
		if (gcxx == null) {
			gcxx = new Td00_gcxx();
			gcxx.setGcmc("预领料工程");
			gcxx.setId(-1L);
			gcxx.setGcbh("--");
		}
		Long dz = convertUtil.toLong(request.getParameter("dz"));
		String type = "";
		if (dz == 0L) {
			type = "入库";
		} else if (dz == 1L) {
			type = "出库";
		} else if (dz == 2L) {
			type = "缴料";
		}
		modelMap.put("type", type);
		modelMap.put("dz", dz);
		modelMap.put("gcxx", gcxx);
		List<Tf08_clmxb> tf08List = (List<Tf08_clmxb>) queryService.searchList("from Tf08_clmxb where zhxx_id="
				+ project_id + " and dz=" + dz + " and (flag is null or flag<>1) and sgdw_id=" + tf04.getWxdw_id());
		modelMap.put("tf08List", tf08List);
		List<String> cllxList = null;
		if (dz == 0L) {
			StringBuffer sql = new StringBuffer();
			sql.append("select name from Tc01_property tc01");
			sql.append(" where type='材料类型' order by id");
			cllxList = (List<String>) queryService.searchList(sql.toString());
		} else if (dz == 1L || dz == 2L) {
			cllxList = (List<String>) queryService
					.searchList("select distinct(cllx) as cllx from Tf07_kcb where project_id=" + project_id
							+ " and kcsl<>0");
		}
		modelMap.put("cllxList", cllxList);
		List<List> clmcselectList = new ArrayList<List>();
		for (Tf08_clmxb tf08 : tf08List) {
			if (dz == 0L) {// 入库
				List<Tf06_clb> clbList = (List<Tf06_clb>) queryService.searchList("from Tf06_clb where cllx='"
						+ tf08.getCllx() + "'");
				clmcselectList.add(clbList);
			} else if (dz == 1L || dz == 2L) {// 出库 缴料
				List<Tf07_kcb> kcbList = (List<Tf07_kcb>) queryService.searchList("from Tf07_kcb where cllx='"
						+ tf08.getCllx() + "' and project_id=" + project_id + " and kcsl<>0");
				// for (Tf07_kcb kcb : kcbList) {
				// s += "<option flag=\"" + kcb.getId() + "\" value=\""
				// + kcb.getClmc() + "\">" + kcb.getClmc() + "—"
				// + convertUtil.toString(kcb.getGg(), "（无规格说明）") + "—"
				// + convertUtil.toString(kcb.getXh(), "（无型号说明）")
				// + "</option>";
				// }
				clmcselectList.add(kcbList);
			}
		}
		modelMap.put("clmcselectList", clmcselectList);
		return new ModelAndView("/WEB-INF/jsp/wxdw/crkEdit.jsp", modelMap);
	}

	/**
	 * 工程材料入库/出库/缴料明细
	 */
	@RequestMapping("/wxdw/crkMxList.do")
	public ModelAndView crkMxList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		Long project_id = convertUtil.toLong(request.getParameter("project_id"));
		Td00_gcxx gcxx = (Td00_gcxx) queryService.searchById(Td00_gcxx.class, project_id);
		if (gcxx == null) {
			gcxx = new Td00_gcxx();
			gcxx.setGcmc("预领料工程");
			gcxx.setId(-1L);
			gcxx.setGcbh("--");
		}
		Long dz = convertUtil.toLong(request.getParameter("dz"));
		String type = "";
		if (dz == 0L) {
			type = "入库";
		} else if (dz == 1L) {
			type = "出库";
		} else if (dz == 2L) {
			type = "缴料";
		}
		// 分页
		Integer totalPages = 1;
		Integer totalCount = 0;
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request.getParameter("orderField"), "czsj");
		if (orderField.equals("")) {
			orderField = "czsj";
		}
		String orderDirection = convertUtil.toString(request.getParameter("orderDirection"), "desc");
		if (orderDirection.equals("")) {
			orderDirection = "desc";
		}
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);

		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Tf08_clmxb clmxb where zhxx_id=" + project_id + " and dz=" + dz);
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
		List<Tf08_clmxb> clmxbList = new ArrayList<Tf08_clmxb>();
		// 导EXCEL
		if ("yes".equals(request.getParameter("toExcel"))) {
			Map<String, List> sheetMap = new HashMap<String, List>();
			List sheetList = new LinkedList();
			List titleList = new LinkedList();
			String form_title = gcxx.getGcmc() + type + "明细.xls";
			// 材料类别 材料名称 规格 型号 单位 数量 操作时间
			titleList.add("材料类别");
			titleList.add("材料名称");
			titleList.add("规格");
			titleList.add("型号");
			titleList.add("单位");
			titleList.add("数量");
			titleList.add("操作时间");
			sheetList.add(titleList);
			List<List> docList = new LinkedList<List>();
			while (ro.next()) {
				List row = new LinkedList();
				Tf08_clmxb tf08 = (Tf08_clmxb) ro.get("clmxb");
				row.add(tf08.getCllx());
				row.add(tf08.getClmc());
				row.add(tf08.getGg());
				row.add(tf08.getXh());
				row.add(tf08.getDw());
				row.add(tf08.getSl());
				row.add(new SimpleDateFormat("yyyy-MM-dd").format(tf08.getCzsj()));
				docList.add(row);
			}
			sheetList.add(docList);
			sheetMap.put(form_title, sheetList);
			request.setAttribute("ExcelName", form_title);
			request.setAttribute("sheetMap", sheetMap);
			return new ModelAndView("/export/toExcelWhithList.do");
		}
		while (ro.next()) {
			clmxbList.add((Tf08_clmxb) ro.get("clmxb"));
		}
		modelMap.put("clmxbList", clmxbList);
		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		// 页面所需内容
		modelMap.put("gcxx", gcxx);
		return new ModelAndView("/WEB-INF/jsp/wxdw/crkMxList.jsp", modelMap);
	}

	/**
	 * 材料信息
	 */
	@RequestMapping("/wxdw/gcKcList.do")
	public ModelAndView gcKcList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		Long project_id = convertUtil.toLong(request.getParameter("project_id"));
		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		Tf04_wxdw_user tf04 = (Tf04_wxdw_user) queryService.searchList(
				"from Tf04_wxdw_user where user_id=" + user.getId()).get(0);
		Td00_gcxx gcxx = (Td00_gcxx) queryService.searchById(Td00_gcxx.class, project_id);
		if (gcxx == null) {
			gcxx = new Td00_gcxx();
			gcxx.setGcmc("预领料工程");
			gcxx.setId(-1L);
			gcxx.setGcbh("--");
		}
		modelMap.put("gcxx", gcxx);
		// 分页
		Integer totalPages = 1;
		Integer totalCount = 0;
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request.getParameter("orderField"), "cllx");
		if (orderField.equals("")) {
			orderField = "cllx";
		}
		String orderDirection = convertUtil.toString(request.getParameter("orderDirection"), "desc");
		if (orderDirection.equals("")) {
			orderDirection = "desc";
		}
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);

		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Tf07_kcb kcb where kcsl>0 and project_id=" + project_id + " and sgdw_id="
				+ tf04.getWxdw_id());
		// where条件
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
		List<Tf07_kcb> kcbList = new ArrayList<Tf07_kcb>();
		// 导EXCEL
		if ("yes".equals(request.getParameter("toExcel"))) {
			Map<String, List> sheetMap = new HashMap<String, List>();
			List sheetList = new LinkedList();
			List titleList = new LinkedList();
			String form_title = gcxx.getGcmc() + "库存情况.xls";
			// 材料类别 材料名称 规格 型号 单位 数量
			titleList.add("材料类别");
			titleList.add("材料名称");
			titleList.add("规格");
			titleList.add("型号");
			titleList.add("单位");
			titleList.add("数量");
			sheetList.add(titleList);
			List<List> docList = new LinkedList<List>();
			while (ro.next()) {
				List row = new LinkedList();
				Tf07_kcb tf07 = (Tf07_kcb) ro.get("kcb");
				row.add(tf07.getCllx());
				row.add(tf07.getClmc());
				row.add(tf07.getGg());
				row.add(tf07.getXh());
				row.add(tf07.getDw());
				row.add(tf07.getKcsl());
				docList.add(row);
			}
			sheetList.add(docList);
			sheetMap.put(form_title, sheetList);
			request.setAttribute("ExcelName", form_title);
			request.setAttribute("sheetMap", sheetMap);
			return new ModelAndView("/export/toExcelWhithList.do");
		}
		while (ro.next()) {
			kcbList.add((Tf07_kcb) ro.get("kcb"));
		}
		modelMap.put("kcbList", kcbList);
		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		// 页面所需内容
		return new ModelAndView("/WEB-INF/jsp/wxdw/gcKcList.jsp", modelMap);
	}

	/**
	 * 材料类型选项
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/wxdw/cllxSelect.do")
	public void cllxSelect(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		response.setCharacterEncoding(request.getCharacterEncoding());
		PrintWriter out = null;
		response.setContentType("text/html");

		// 获得参数
		Long dz = convertUtil.toLong(request.getParameter("dz"));
		Long project_id = convertUtil.toLong(request.getParameter("project_id"));
		String inputName = convertUtil.toString(request.getParameter("inputName"));

		StringBuffer printStr = new StringBuffer();
		printStr.append("<select name=\"" + inputName + "\" style=\"width:0px;\">");
		printStr.append("<option value=\"\"></option>");
		try {
			if (dz == 0L) {
				StringBuffer sql = new StringBuffer();
				sql.append("select tc01 from Tc01_property tc01");
				sql.append(" where type='材料类型' order by id");
				List<Tc01_property> tmpList = (List<Tc01_property>) queryService.searchList(sql.toString());
				for (Tc01_property tc01 : tmpList) {
					printStr.append("<option title=\"" + tc01.getName() + "\" value='" + tc01.getName() + "'>"
							+ tc01.getName() + "</option>");
				}

			} else if (dz == 1L || dz == 2L) {
				List<String> tmpList = (List<String>) queryService
						.searchList("select distinct(cllx) as cllx from Tf07_kcb where project_id=" + project_id
								+ " and kcsl<>0");
				for (String string : tmpList) {
					printStr.append("<option value='" + string + "'>" + string + "</option>");
				}
			}
			printStr.append("</select>");
			out = response.getWriter();
			out.print(printStr);

		} catch (IOException e) {
			exceptionService.exceptionControl("com.rms.controller.info.DetailSelect", "获取明细选项失败", e);
		}

	}

	/**
	 * 材料名称下拉选项
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/wxdw/clmcSelect.do")
	public void clmcSelect(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		response.setCharacterEncoding(request.getCharacterEncoding());
		PrintWriter out = null;
		response.setContentType("text/html");
		Long dz = convertUtil.toLong(request.getParameter("dz"));
		// 获得参数
		String inputName = convertUtil.toString(request.getParameter("inputName"));

		StringBuffer printStr = new StringBuffer();
		printStr.append("<select name=\"" + inputName + "\"  class=\"clmc\" style=\"width:0px;\">");
		printStr.append("</select>");
		out = response.getWriter();
		out.print(printStr);
	}

	@RequestMapping("/wxdw/getClmc.do")
	public void getClmc(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String s = "<option></option>";
		String cllx = convertUtil.toString(request.getParameter("cllx"));
		Long dz = convertUtil.toLong(request.getParameter("dz"));
		Long project_id = convertUtil.toLong(request.getParameter("project_id"));
		if (dz == 0L) {// 入库
			List<Tf06_clb> clbList = (List<Tf06_clb>) queryService
					.searchList("from Tf06_clb where cllx='" + cllx + "'");
			for (Tf06_clb clb : clbList) {
				s += "<option title=\"" + clb.getClmc() + "—" + convertUtil.toString(clb.getGg(), "（无规格说明）") + "—"
						+ convertUtil.toString(clb.getXh(), "（无型号说明）") + "\" flag=\"" + clb.getId() + "\" value=\""
						+ clb.getClmc() + "\">" + clb.getClmc() + "—" + convertUtil.toString(clb.getGg(), "（无规格说明）")
						+ "—" + convertUtil.toString(clb.getXh(), "（无型号说明）") + "</option>";
			}
		} else if (dz == 1L || dz == 2L) {// 出库 缴料
			List<Tf07_kcb> kcbList = (List<Tf07_kcb>) queryService.searchList("from Tf07_kcb where cllx='" + cllx
					+ "' and project_id=" + project_id + " and kcsl<>0");
			for (Tf07_kcb kcb : kcbList) {
				s += "<option flag=\"" + kcb.getId() + "\" value=\"" + kcb.getClmc() + "\">" + kcb.getClmc() + "—"
						+ convertUtil.toString(kcb.getGg(), "（无规格说明）") + "—"
						+ convertUtil.toString(kcb.getXh(), "（无型号说明）") + "</option>";
			}
		}

		PrintWriter out = response.getWriter();
		out.print(s);
		out.flush();
		out.close();
	}

	@RequestMapping("/wxdw/setClxx.do")
	public void setClxx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Long dz = convertUtil.toLong(request.getParameter("dz"));
		Long id = convertUtil.toLong(request.getParameter("id"));
		String s = "";
		if (id != -1) {
			if (dz == 0L) {// 入库
				Tf06_clb clb = (Tf06_clb) queryService.searchById(Tf06_clb.class, id);
				s += convertUtil.toString(clb.getGg()) + "@#$" + convertUtil.toString(clb.getXh()) + "@#$"
						+ convertUtil.toString(clb.getDw()) + "@#$";
			} else if (dz == 1L || dz == 2L) {// 出库 缴料
				Tf07_kcb kcb = (Tf07_kcb) queryService.searchById(Tf07_kcb.class, id);
				s += convertUtil.toString(kcb.getGg()) + "@#$" + convertUtil.toString(kcb.getXh()) + "@#$"
						+ convertUtil.toString(kcb.getDw()) + "@#$" + convertUtil.toString(kcb.getKcsl());
			}
		} else {
			s = "@#$@#$@#$";
		}
		PrintWriter out = response.getWriter();
		out.print(s);
		out.flush();
		out.close();
	}

	@RequestMapping("/wxdw/crkEditAjax.do")
	public void crkEditAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Long dz = convertUtil.toLong(request.getParameter("dz"));
		Long project_id = convertUtil.toLong(request.getParameter("project_id"));
		Session session = saveService.getHiberbateSession();
		Transaction tx = session.beginTransaction();
		PrintWriter out = response.getWriter();
		try {
			Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
			Tf04_wxdw_user tf04 = (Tf04_wxdw_user) queryService.searchList(
					"from Tf04_wxdw_user where user_id=" + user.getId()).get(0);
			List<Tf08_clmxb> tf08List = (List<Tf08_clmxb>) queryService.searchList("from Tf08_clmxb where zhxx_id="
					+ project_id + " and dz=" + dz + " and (flag is null or flag<>1) and sgdw_id=" + tf04.getWxdw_id());
			for (Tf08_clmxb tf08 : tf08List) {
				List<Tf07_kcb> tmpList = (List<Tf07_kcb>) session.createQuery(
						"from Tf07_kcb where cllx='" + tf08.getCllx() + "' and clmc='" + tf08.getClmc() + "' and dw"
								+ (tf08.getDw() == null ? " is null" : ("='" + tf08.getDw() + "'")) + " and gg"
								+ (tf08.getGg() == null ? " is null" : ("='" + tf08.getGg() + "'")) + " and xh"
								+ (tf08.getXh() == null ? " is null" : ("='" + tf08.getXh() + "'"))).list();
				Tf07_kcb tf07 = null;
				if (tmpList.size() != 0) {
					tf07 = tmpList.get(0);
				} else {
					tf07 = new Tf07_kcb();
					tf07.setCllx(tf08.getCllx());
					tf07.setClmc(tf08.getClmc());
					tf07.setDw(tf08.getDw());
					tf07.setGg(tf08.getGg());
					tf07.setXh(tf08.getXh());
					tf07.setCksl(0L);
					tf07.setRksl(0L);
					tf07.setJlsl(0L);
					tf07.setKcsl(0L);
					tf07.setProject_id(project_id);
					tf07.setSgdw_id(tf04.getWxdw_id());
				}
				if (dz == 0L) {
					tf07.setKcsl(tf07.getKcsl() + tf08.getSl());
					tf07.setRksl(tf07.getRksl() + tf08.getSl());
				} else if (dz == 1L) {
					if (tf08.getSl() > tf07.getKcsl()) {
						throw new Exception("超出库存数量");
					}
					tf07.setKcsl(tf07.getKcsl() - tf08.getSl());
					tf07.setCksl(tf07.getCksl() + tf08.getSl());
				} else if (dz == 2L) {
					if (tf08.getSl() > tf07.getKcsl()) {
						throw new Exception("超出库存数量");
					}
					tf07.setKcsl(tf07.getKcsl() - tf08.getSl());
					tf07.setJlsl(tf07.getJlsl() + tf08.getSl());
				}
				session.saveOrUpdate(tf07);
			}
			session.createQuery(
					"update Tf08_clmxb set flag=1 where zhxx_id=" + project_id + " and dz=" + dz
							+ " and (flag is null or flag<>1) and sgdw_id=" + tf04.getWxdw_id()).executeUpdate();
			session.flush();
			tx.commit();
			out.print("{\"statusCode\":\"200\",\"message\":\"保存成功！\"}");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			out.print("{\"statusCode\":\"300\",\"message\":\"保存失败！\"}");
		} finally {
			session.close();
		}
		out.flush();
		out.close();
	}

	/**
	 * 外协单位列表
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/zhuanchuList.do")
	public ModelAndView zhuanchuList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		List<Td00_gcxx> gcxxList = (List<Td00_gcxx>) queryService
				.searchList("select distinct(gcxx) as gcxx from Td00_gcxx gcxx where exists (select tb15 from Tb15_docflow tb15 where tb15.project_id=gcxx.id and tb15.user_id="
						+ ((Ta03_user) request.getSession().getAttribute("user")).getId() + ")");
		modelMap.put("gcxxList", gcxxList);
		Long id = convertUtil.toLong(request.getParameter("id"));
		Tf07_kcb tf07 = (Tf07_kcb) queryService.searchById(Tf07_kcb.class, id);
		modelMap.put("tf07", tf07);
		return new ModelAndView("/WEB-INF/jsp/wxdw/zhuanchuList.jsp", modelMap);
	}

	@RequestMapping("/wxdw/zhuanchu.do")
	public void zhuanchu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Long tf07_id = convertUtil.toLong(request.getParameter("tf07_id"));
		Long project_id = convertUtil.toLong(request.getParameter("project_id"));
		Long sl = convertUtil.toLong(request.getParameter("sl"));
		Td00_gcxx gcxx = (Td00_gcxx) queryService.searchById(Td00_gcxx.class, project_id);
		Tf07_kcb kcb = (Tf07_kcb) queryService.searchById(Tf07_kcb.class, tf07_id);
		Session session = saveService.getHiberbateSession();
		Transaction tx = session.beginTransaction();
		PrintWriter out = response.getWriter();
		try {
			Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
			Tf04_wxdw_user tf04 = (Tf04_wxdw_user) queryService.searchList(
					"from Tf04_wxdw_user where user_id=" + user.getId()).get(0);
			// 预领料工程TF08加一条记录 TF07核减
			Tf08_clmxb tf08 = new Tf08_clmxb();
			tf08.setBz("转出到" + gcxx.getGcmc());
			tf08.setCllx(kcb.getCllx());
			tf08.setClmc(kcb.getClmc());
			tf08.setCzry(user.getName());
			tf08.setCzsj(new Date());
			tf08.setDw(kcb.getDw());
			tf08.setDz(1L);
			tf08.setFlag(1L);
			tf08.setGg(kcb.getGg());
			tf08.setSgdw_id(tf04.getWxdw_id());
			tf08.setSl(sl);
			tf08.setXh(kcb.getXh());
			tf08.setZhxx_id(-1L);
			session.save(tf08);
			kcb.setCksl(kcb.getCksl() + sl);
			kcb.setKcsl(kcb.getKcsl() - sl);
			session.update(kcb);
			// 转入工程TF08加一条记录 TF07核加
			tf08 = new Tf08_clmxb();
			tf08.setBz("从预领料工程转入");
			tf08.setCllx(kcb.getCllx());
			tf08.setClmc(kcb.getClmc());
			tf08.setCzry(user.getName());
			tf08.setCzsj(new Date());
			tf08.setDw(kcb.getDw());
			tf08.setDz(1L);
			tf08.setFlag(1L);
			tf08.setGg(kcb.getGg());
			tf08.setSgdw_id(tf04.getWxdw_id());
			tf08.setSl(sl);
			tf08.setXh(kcb.getXh());
			tf08.setZhxx_id(project_id);
			session.save(tf08);

			List<Tf07_kcb> tmpList = (List<Tf07_kcb>) session.createQuery(
					"from Tf07_kcb where project_id=" + project_id + " and cllx='" + tf08.getCllx() + "' and clmc='"
							+ tf08.getClmc() + "' and dw"
							+ (tf08.getDw() == null ? " is null" : ("='" + tf08.getDw() + "'")) + " and gg"
							+ (tf08.getGg() == null ? " is null" : ("='" + tf08.getGg() + "'")) + " and xh"
							+ (tf08.getXh() == null ? " is null" : ("='" + tf08.getXh() + "'"))).list();
			Tf07_kcb tf07 = null;
			if (tmpList.size() != 0) {
				tf07 = tmpList.get(0);
			} else {
				tf07 = new Tf07_kcb();
				tf07.setCllx(tf08.getCllx());
				tf07.setClmc(tf08.getClmc());
				tf07.setDw(tf08.getDw());
				tf07.setGg(tf08.getGg());
				tf07.setXh(tf08.getXh());
				tf07.setCksl(0L);
				tf07.setRksl(0L);
				tf07.setJlsl(0L);
				tf07.setKcsl(0L);
				tf07.setProject_id(project_id);
				tf07.setSgdw_id(tf04.getWxdw_id());
			}
			tf07.setKcsl(tf07.getKcsl() + tf08.getSl());
			tf07.setRksl(tf07.getRksl() + tf08.getSl());
			session.saveOrUpdate(tf07);
			session.flush();
			tx.commit();
			out.print("{\"statusCode\":\"200\",\"message\":\"保存成功！\",\"navTabId\":\"_current\"}");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			out.print("{\"statusCode\":\"300\",\"message\":\"保存失败！\"}");
		} finally {
			session.close();
		}
		out.flush();
		out.close();
	}

	/**
	 * 进度反馈提醒列表(施工负责人)
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/wxdw/jdfktxListforSgfzr.do")
	public ModelAndView jdfktxListforSgfzr(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		modelMap.put("url", "wxdw/jdfktxListforSgfzr.do");
		// 分页
		Integer totalPages = 1;
		Integer totalCount = 0;
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request.getParameter("orderField"), "gcmc");
		if (orderField.equals("")) {
			orderField = "gcmc";
		}
		String gcmc = convertUtil.toString(request.getParameter("gcmc"));
		String orderDirection = convertUtil.toString(request.getParameter("orderDirection"), "desc");
		if (orderDirection.equals("")) {
			orderDirection = "desc";
		}
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		StringBuffer hsql = new StringBuffer();
		hsql
				.append("select distinct(gcxx) as gcxx,sysdate-(case when tbrq is null then sjkgsj else tbrq end)-(case when sgjdtbzq is null then 3 else sgjdtbzq end) as a1,((case when tbrq is null then sjkgsj else tbrq end)+(case when sgjdtbzq is null then 3 else sgjdtbzq end)) as a2,((sysdate-sgpfsj)/(jhjgsj-sgpfsj)) as a3 from Vc2_gcxx_gzltb gcxx where ");
		if (!"".equals(gcmc)) {
			hsql.append("(gcxx.gcmc like '%" + gcmc + "%') and ");
		}
		hsql
				.append("exists (select id from Tb15_docflow tb15 where tb15.project_id=gcxx.id and node_id in (10107,10209) and tb15.user_id="
						+ ((Ta03_user) request.getSession().getAttribute("user")).getId() + ") and sjjgsj is null");
		// hsql
		// .append(" and (case when tbrq is null then sjkgsj else tbrq
		// end)<=sysdate-(case when sgjdtbzq is null then 3 else sgjdtbzq
		// end)");
		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum, numPerPage);
		// 获取结果集
		List<Object[]> gcxxList = new ArrayList<Object[]>();
		while (ro.next()) {
			Object[] o = new Object[4];
			Vc2_gcxx_gzltb gcxx = (Vc2_gcxx_gzltb) ro.get("gcxx");
			o[0] = gcxx;
			Long d = new BigDecimal(convertUtil.toDouble(ro.get("a1"))).setScale(0, BigDecimal.ROUND_FLOOR).longValue();
			o[1] = d >= convertUtil.toLong(((Vc2_gcxx_gzltb) o[0]).getSgjdtbzq(), 3L) ? "red"
					: d.equals(0L) ? "lightgreen" : d > 0L ? "yellow" : "";
			o[2] = ro.get("a2");
			o[3] = ro.get("a3");
			gcxxList.add(o);
		}
		modelMap.put("gcxxList", gcxxList);
		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		return new ModelAndView("/WEB-INF/jsp/wxdw/jdfktxList.jsp?canedit=true", modelMap);
	}

	/**
	 * 进度反馈提醒列表(项目管理员)
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/wxdw/jdfktxListforXmgly.do")
	public ModelAndView jdfktxListforXmgly(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		modelMap.put("url", "wxdw/jdfktxListforXmgly.do");
		// 分页
		Integer totalPages = 1;
		Integer totalCount = 0;
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request.getParameter("orderField"), "gcmc");
		if (orderField.equals("")) {
			orderField = "gcmc";
		}
		String gcmc = convertUtil.toString(request.getParameter("gcmc"));
		String orderDirection = convertUtil.toString(request.getParameter("orderDirection"), "desc");
		if (orderDirection.equals("")) {
			orderDirection = "desc";
		}
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		boolean allproject = request.getParameter("allproject") != null;
		StringBuffer hsql = new StringBuffer();
		hsql
				.append("select distinct(gcxx) as gcxx,sysdate-(case when tbrq is null then sjkgsj else tbrq end)-(case when sgjdtbzq is null then 3 else sgjdtbzq end) as a1,((case when tbrq is null then sjkgsj else tbrq end)+(case when sgjdtbzq is null then 3 else sgjdtbzq end)) as a2,((sysdate-sgpfsj)/(jhjgsj-sgpfsj)) as a3 from Vc2_gcxx_gzltb gcxx where ");
		if (!"".equals(gcmc)) {
			hsql.append("(gcxx.gcmc like '%" + gcmc + "%') and ");
		}
		hsql
				.append("exists (select id from Tb15_docflow tb15 where tb15.project_id=gcxx.id and node_id in (10101,10202) and tb15.user_id="
						+ ((Ta03_user) request.getSession().getAttribute("user")).getId() + ")");
		if (!allproject) {
			hsql.append(" and sjjgsj is null");
		}
		// hsql
		// .append(" and (case when tbrq is null then sjkgsj end)<=sysdate-(case
		// when sgjdtbzq is null then 3 else sgjdtbzq end)");
		// orderField
		hsql.append(" order by " + orderField);
		// orderDirection
		hsql.append(" " + orderDirection);
		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum, numPerPage);
		// 获取结果集
		List<Object[]> gcxxList = new ArrayList<Object[]>();
		while (ro.next()) {
			Object[] o = new Object[4];
			Vc2_gcxx_gzltb gcxx = (Vc2_gcxx_gzltb) ro.get("gcxx");
			o[0] = gcxx;
			Long d = new BigDecimal(convertUtil.toDouble(ro.get("a1"))).setScale(0, BigDecimal.ROUND_FLOOR).longValue();
			o[1] = d >= convertUtil.toLong(((Vc2_gcxx_gzltb) o[0]).getSgjdtbzq(), 3L) ? "red"
					: d.equals(0L) ? "lightgreen" : d > 0L ? "yellow" : "";
			o[2] = ro.get("a2");
			o[3] = ro.get("a3");
			gcxxList.add(o);
		}
		modelMap.put("gcxxList", gcxxList);
		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		return new ModelAndView("/WEB-INF/jsp/wxdw/jdfktxList.jsp", modelMap);
	}

	/**
	 * 进度反馈页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/wxdw/jdfk.do")
	public ModelAndView jdfk(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		Long project_id = convertUtil.toLong(request.getParameter("project_id"));
		Td00_gcxx gcxx = (Td00_gcxx) queryService.searchById(Td00_gcxx.class, project_id);
		modelMap.put("gcxx", gcxx);
		Long id = convertUtil.toLong(request.getParameter("id"));
		if (!id.equals(-1L)) {
			Tf10_gzltb tf10 = (Tf10_gzltb) queryService.searchById(Tf10_gzltb.class, id);
			modelMap.put("gzltb", tf10);
		}
		return new ModelAndView("/WEB-INF/jsp/wxdw/jdfk.jsp", modelMap);
	}

	/**
	 * 进度反馈信息页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/wxdw/jdfkxx.do")
	public ModelAndView jdfkxx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		Long id = convertUtil.toLong(request.getParameter("id"));
		Vc2_gcxx_gzltb gcxx = (Vc2_gcxx_gzltb) queryService.searchById(Vc2_gcxx_gzltb.class, id);
		modelMap.put("gcxx", gcxx);
		List<Tf10_gzltb> tf10List = (List<Tf10_gzltb>) queryService.searchList("from Tf10_gzltb where project_id=" + id
				+ " order by id asc");
		modelMap.put("tf10List", tf10List);
		String categories = "";
		String sjjddata = "";
		String tbjddata = "";
		for (Tf10_gzltb tf10 : tf10List) {
			categories += new SimpleDateFormat("yyyy-MM-dd").format(tf10.getTbrq()) + ",";
			sjjddata += new BigDecimal(tf10.getSjjd() * 100).setScale(2, BigDecimal.ROUND_HALF_UP) + ",";
			tbjddata += new BigDecimal(tf10.getTbjd() * 100).setScale(2, BigDecimal.ROUND_HALF_UP) + ",";
		}
		if (!tf10List.isEmpty()) {
			categories = categories.substring(0, categories.length() - 1);
			sjjddata = sjjddata.substring(0, sjjddata.length() - 1);
			tbjddata = tbjddata.substring(0, tbjddata.length() - 1);
		}
		modelMap.put("categories", categories);
		modelMap.put("sjjddata", sjjddata);
		modelMap.put("tbjddata", tbjddata);
		return new ModelAndView("/WEB-INF/jsp/wxdw/jdfkxx.jsp", modelMap);
	}

	/**
	 * 监理工程列表(监理工程师)
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/wxdw/jlgcListforJlgcs.do")
	public ModelAndView jlgcListforJlgcs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		modelMap.put("url", "wxdw/jlgcListforJlgcs.do");
		// 分页
		Integer totalPages = 1;
		Integer totalCount = 0;
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request.getParameter("orderField"), "gcmc");
		if (orderField.equals("")) {
			orderField = "gcmc";
		}
		String gcmc = convertUtil.toString(request.getParameter("gcmc"));
		String orderDirection = convertUtil.toString(request.getParameter("orderDirection"), "desc");
		if (orderDirection.equals("")) {
			orderDirection = "desc";
		}
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		StringBuffer hsql = new StringBuffer();
		hsql
				.append("select distinct(gcxx) as gcxx,sysdate-((case when create_date is null then sjkgsj else create_date end))-((case when jlrjtbzq is null then 3 else jlrjtbzq end)) as a1,(((case when create_date is null then sjkgsj else create_date end))+((case when jlrjtbzq is null then 3 else jlrjtbzq end))) as a2 from Vc3_gcxx_jlrj gcxx where ");
		if (!"".equals(gcmc)) {
			hsql.append("(gcxx.gcmc like '%" + gcmc + "%') and ");
		}
		hsql
				.append("exists (select id from Tb15_docflow tb15 where tb15.project_id=gcxx.id and node_id in (10109,10211) and tb15.user_id="
						+ ((Ta03_user) request.getSession().getAttribute("user")).getId() + ") and sjjgsj is null");
		// hsql
		// .append(" and (case when create_date is null then sjkgsj else
		// create_date end)<=sysdate-(case when jlrjtbzq is null then 3 else
		// jlrjtbzq end)");
		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum, numPerPage);
		// 获取结果集
		List<Object[]> gcxxList = new ArrayList<Object[]>();
		while (ro.next()) {
			Object[] o = new Object[4];
			Vc3_gcxx_jlrj gcxx = (Vc3_gcxx_jlrj) ro.get("gcxx");
			o[0] = gcxx;
			Long d = new BigDecimal(convertUtil.toDouble(ro.get("a1"))).setScale(0, BigDecimal.ROUND_FLOOR).longValue();
			o[1] = d >= convertUtil.toLong(((Vc3_gcxx_jlrj) o[0]).getJlrjtbzq(), 3L) ? "red"
					: d.equals(0L) ? "lightgreen" : d > 0L ? "yellow" : "";
			o[2] = ro.get("a2");
			gcxxList.add(o);
		}
		modelMap.put("gcxxList", gcxxList);
		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		return new ModelAndView("/WEB-INF/jsp/wxdw/jlgcList.jsp?canedit=true", modelMap);
	}

	/**
	 * 监理工程列表(项目管理员)
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/wxdw/jlgcListforXmgly.do")
	public ModelAndView jlgcListforXmgly(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelMap modelMap = new ModelMap();
		modelMap.put("url", "wxdw/jlgcListforXmgly.do");
		// 分页
		Integer totalPages = 1;
		Integer totalCount = 0;
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request.getParameter("orderField"), "gcmc");
		if (orderField.equals("")) {
			orderField = "gcmc";
		}
		String gcmc = convertUtil.toString(request.getParameter("gcmc"));
		String orderDirection = convertUtil.toString(request.getParameter("orderDirection"), "desc");
		if (orderDirection.equals("")) {
			orderDirection = "desc";
		}
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		StringBuffer hsql = new StringBuffer();
		boolean allproject = request.getParameter("allproject") != null;
		hsql
				.append("select distinct(gcxx) as gcxx,sysdate-((case when create_date is null then sjkgsj else create_date end))-((case when jlrjtbzq is null then 3 else jlrjtbzq end)) as a1,(((case when create_date is null then sjkgsj else create_date end))+((case when jlrjtbzq is null then 3 else jlrjtbzq end))) as a2 from Vc3_gcxx_jlrj gcxx where ");
		if (!"".equals(gcmc)) {
			hsql.append("(gcxx.gcmc like '%" + gcmc + "%') and ");
		}
		hsql
				.append("exists (select id from Tb15_docflow tb15 where tb15.project_id=gcxx.id and node_id in (10101,10202) and tb15.user_id="
						+ ((Ta03_user) request.getSession().getAttribute("user")).getId() + ")");
		if (!allproject) {
			hsql.append(" and sjjgsj is null");
		}
		// hsql
		// .append(" and (case when create_date is null then sjkgsj else
		// create_date end)<=sysdate-(case when jlrjtbzq is null then 3 else
		// jlrjtbzq end)");
		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum, numPerPage);
		// 获取结果集
		List<Object[]> gcxxList = new ArrayList<Object[]>();
		while (ro.next()) {
			Object[] o = new Object[4];
			Vc3_gcxx_jlrj gcxx = (Vc3_gcxx_jlrj) ro.get("gcxx");
			o[0] = gcxx;
			Long d = new BigDecimal(convertUtil.toDouble(ro.get("a1"))).setScale(0, BigDecimal.ROUND_FLOOR).longValue();
			o[1] = d >= convertUtil.toLong(((Vc3_gcxx_jlrj) o[0]).getJlrjtbzq(), 3L) ? "red"
					: d.equals(0L) ? "lightgreen" : d > 0L ? "yellow" : "";
			o[2] = ro.get("a2");
			gcxxList.add(o);
		}
		modelMap.put("gcxxList", gcxxList);
		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		return new ModelAndView("/WEB-INF/jsp/wxdw/jlgcList.jsp", modelMap);

	}

	/**
	 * 监理日志页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/wxdw/jlrj.do")
	public ModelAndView jlrj(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		Long project_id = convertUtil.toLong(request.getParameter("project_id"));
		Td00_gcxx gcxx = (Td00_gcxx) queryService.searchById(Td00_gcxx.class, project_id);
		modelMap.put("gcxx", gcxx);
		Long id = convertUtil.toLong(request.getParameter("id"));
		if (!id.equals(-1L)) {
			Tf14_jlrj tf14 = (Tf14_jlrj) queryService.searchById(Tf14_jlrj.class, id);
			modelMap.put("jlrj", tf14);
		}
		return new ModelAndView("/WEB-INF/jsp/wxdw/jlrj.jsp", modelMap);
	}

	public static void main(String[] args) {
	}

	/**
	 * 监理日志信息页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/wxdw/jlrjxx.do")
	public ModelAndView jlrjxx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		Long id = convertUtil.toLong(request.getParameter("id"));
		Vc3_gcxx_jlrj gcxx = (Vc3_gcxx_jlrj) queryService.searchById(Vc3_gcxx_jlrj.class, id);
		modelMap.put("gcxx", gcxx);
		List<Object[]> tf14List = (List<Object[]>) queryService
				.searchList("select jlrj,user.name as user_name from Tf14_jlrj jlrj,Ta03_user user where user.id=jlrj.user_id and project_id="
						+ id + " order by jlrj.id asc");
		modelMap.put("tf14List", tf14List);
		return new ModelAndView("/WEB-INF/jsp/wxdw/jlrjxx.jsp", modelMap);
	}

	/**
	 * 库存查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/wxdw/kccx.do")
	public ModelAndView kccx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		// 权限判断 是项目管理员还是施工单位
		String type = convertUtil.toString(request.getParameter("type"));
		Long wxdw_id = convertUtil.toLong(request.getParameter("wxdw_id"));
		if ("xmgly".equals(type)) {
			List<Tf01_wxdw> tf01List = (List<Tf01_wxdw>) queryService.searchList("from Tf01_wxdw where lb='施工'");
			modelMap.put("tf01List", tf01List);
			if (tf01List != null && !tf01List.isEmpty() && wxdw_id == -1L) {
				wxdw_id = tf01List.get(0).getId();
			}
		} else if ("wxdw".equals(type)) {
			Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
			List<Tf01_wxdw> tmpList = (List<Tf01_wxdw>) queryService
					.searchList("from Tf01_wxdw where id=(select wxdw_id from Tf04_wxdw_user where user_id="
							+ user.getId() + ")");
			if (tmpList != null && !tmpList.isEmpty() && wxdw_id == -1L) {
				wxdw_id = tmpList.get(0).getId();
			}
		}

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
		String gg = convertUtil.toString(request.getParameter("gg"));
		String xh = convertUtil.toString(request.getParameter("xh"));

		StringBuffer hsql = new StringBuffer();
		hsql.append("select clmc,xh,dw,gg,sum(kcsl) as kcsl,sgdw_id,cllx from Tf07_kcb kcb where sgdw_id=" + wxdw_id);
		// where条件
		// 材料名称
		if (!clmc.equals("")) {
			hsql.append(" and clmc like '%" + clmc + "%'");
		}
		// 规格
		if (!gg.equals("")) {
			hsql.append(" and gg like '%" + gg + "%'");
		}
		// 型号
		if (!xh.equals("")) {
			hsql.append(" and xh like '%" + xh + "%'");
		}
		// group by
		hsql.append(" group by clmc,xh,dw,gg,sgdw_id,cllx ");
		// order排序
		// orderField
		hsql.append(" order by " + orderField);
		// orderDirection
		hsql.append(" " + orderDirection);

		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum, numPerPage);
		// 获取结果集
		List<Tf07_kcb> kcbList = new ArrayList<Tf07_kcb>();
		// 导EXCEL
		while (ro.next()) {
			Tf07_kcb tf07 = new Tf07_kcb();
			tf07.setClmc(convertUtil.toString(ro.get("clmc")));
			tf07.setXh(convertUtil.toString(ro.get("xh")));
			tf07.setDw(convertUtil.toString(ro.get("dw")));
			tf07.setGg(convertUtil.toString(ro.get("gg")));
			tf07.setKcsl(convertUtil.toLong(ro.get("kcsl")));
			tf07.setCllx(convertUtil.toString(ro.get("cllx")));
			tf07.setSgdw_id(wxdw_id);
			kcbList.add(tf07);
		}
		modelMap.put("kcbList", kcbList);
		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		// 页面所需内容
		return new ModelAndView("/WEB-INF/jsp/wxdw/kccx.jsp", modelMap);
	}

	/**
	 * 库存统计明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/wxdw/kctjmx.do")
	public ModelAndView kctjmx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		// 权限判断 是项目管理员还是施工单位
		Long wxdw_id = convertUtil.toLong(request.getParameter("wxdw_id"));
		modelMap.put("sgdw", queryService.searchById(Tf01_wxdw.class, wxdw_id));
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
		String gg = convertUtil.toString(request.getParameter("gg"));
		String cllx = convertUtil.toString(request.getParameter("cllx"));
		String dw = convertUtil.toString(request.getParameter("dw"));
		StringBuffer hsql = new StringBuffer();
		hsql
				.append("select gcxx,kcb,(select max(czsj) as cjsj from Tf08_clmxb clmxb where clmxb.zhxx_id=kcb.project_id and kcb.clmc=clmxb.clmc and kcb.gg=clmxb.gg and kcb.cllx=clmxb.cllx and kcb.cllx=clmxb.cllx and kcb.dw=clmxb.dw and dz=0) as cjsj from Tf07_kcb kcb,Td00_gcxx gcxx where gcxx.id=kcb.project_id and sgdw_id="
						+ wxdw_id);
		// where条件
		// 材料名称
		if (!clmc.equals("")) {
			hsql.append(" and kcb.clmc = '" + clmc + "'");
		} else {
			hsql.append(" and (kcb.clmc = '' or kcb.clmc is null)");
		}
		// 规格
		if (!gg.equals("")) {
			hsql.append(" and kcb.gg = '" + gg + "'");
		} else {
			hsql.append(" and (kcb.gg = '' or kcb.gg is null)");
		}
		// 型号
		if (!cllx.equals("")) {
			hsql.append(" and kcb.cllx = '" + cllx + "'");
		} else {
			hsql.append(" and (kcb.cllx = '' or kcb.cllx is null)");
		}
		// 单位
		if (!dw.equals("")) {
			hsql.append(" and kcb.dw = '" + dw + "'");
		} else {
			hsql.append(" and (kcb.dw = '' or kcb.dw is null)");
		}
		// order排序
		// orderField
		hsql.append(" order by " + orderField);
		// orderDirection
		hsql.append(" " + orderDirection);
		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum, numPerPage);
		// 获取结果集
		List<Object[]> kcbList = new ArrayList<Object[]>();

		while (ro.next()) {
			Object[] o = new Object[3];
			o[0] = ro.get("kcb");
			o[1] = ro.get("cjsj");
			o[2] = ro.get("gcxx");
			kcbList.add(o);
		}
		modelMap.put("kcbList", kcbList);
		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		// 页面所需内容
		return new ModelAndView("/WEB-INF/jsp/wxdw/kctjmx.jsp", modelMap);
	}

	/**
	 * 材料明细查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/wxdw/clmxcx.do")
	public ModelAndView clmxcx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		String type = convertUtil.toString(request.getParameter("type"));
		Long wxdw_id = convertUtil.toLong(request.getParameter("wxdw_id"));
		if ("xmgly".equals(type)) {
			List<Tf01_wxdw> tf01List = (List<Tf01_wxdw>) queryService.searchList("from Tf01_wxdw where lb='施工'");
			modelMap.put("tf01List", tf01List);
			if (tf01List != null && !tf01List.isEmpty() && wxdw_id == -1L) {
				wxdw_id = tf01List.get(0).getId();
			}
		} else if ("wxdw".equals(type)) {
			Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
			List<Tf01_wxdw> tmpList = (List<Tf01_wxdw>) queryService
					.searchList("from Tf01_wxdw where id=(select wxdw_id from Tf04_wxdw_user where user_id="
							+ user.getId() + ")");
			if (tmpList != null && !tmpList.isEmpty() && wxdw_id == -1L) {
				wxdw_id = tmpList.get(0).getId();
			}
		}
		String clmc = convertUtil.toString(request.getParameter("clmc"));
		String gg = convertUtil.toString(request.getParameter("gg"));
		String xh = convertUtil.toString(request.getParameter("xh"));
		String cz = convertUtil.toString(request.getParameter("cz"));
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

		StringBuffer hsql = new StringBuffer();
		hsql.append("select clmxb,gcxx from Tf08_clmxb clmxb,Td00_gcxx gcxx where gcxx.id=clmxb.zhxx_id and sgdw_id="
				+ wxdw_id);
		// where条件
		// 材料名称
		if (!clmc.equals("")) {
			hsql.append(" and clmc like '%" + clmc + "%'");
		}
		// 规格
		if (!gg.equals("")) {
			hsql.append(" and gg like '%" + gg + "%'");
		}
		// 型号
		if (!xh.equals("")) {
			hsql.append(" and xh like '%" + xh + "%'");
		}
		// 操作
		if (!cz.equals("")) {
			hsql.append(" and dz=" + cz);
		}
		// order排序
		// orderField
		hsql.append(" order by " + orderField);
		// orderDirection
		hsql.append(" " + orderDirection);

		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum, numPerPage);
		// 获取结果集
		List<Object[]> clmxbList = new ArrayList<Object[]>();
		while (ro.next()) {
			Object[] o = new Object[2];
			o[0] = ro.get("clmxb");
			o[1] = ro.get("gcxx");
			clmxbList.add(o);
		}
		modelMap.put("clmxbList", clmxbList);
		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		return new ModelAndView("/WEB-INF/jsp/wxdw/clmxcx.jsp", modelMap);
	}

	/**
	 * 项目材料分类统计
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/wxdw/xmclfltj.do")
	public ModelAndView xmclfltj(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		Long project_id = convertUtil.toLong(request.getParameter("project_id"));
		modelMap.put("gcxx", queryService.searchById(Td00_gcxx.class, project_id));
		// 分页
		Integer totalPages = 1;
		Integer totalCount = 0;
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 20);
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);

		StringBuffer hsql = new StringBuffer();
		hsql.append("select clmc,dw,gg,xh,sum(sl) as sl,dz from Tf08_clmxb where zhxx_id = " + project_id
				+ " group by clmc, dw, gg, xh, dz order by clmc,gg,dz");
		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum, numPerPage);
		// 获取结果集
		List<Map<String, Object>> clmxbList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		Tf08_clmxb clmxb = new Tf08_clmxb();
		String tmp_clmc = "";
		String tmp_gg = "";
		while (ro.next()) {
			String gg = convertUtil.toString(ro.get("gg"));
			String clmc = convertUtil.toString(ro.get("clmc"));
			if (!tmp_clmc.equals(clmc) || !tmp_gg.equals(gg)) {
				if (map != null) {
					clmxbList.add(map);
				}
				map = new HashMap<String, Object>();
				clmxb = new Tf08_clmxb();
				clmxb.setClmc(clmc);
				clmxb.setDw(convertUtil.toString(ro.get("dw")));
				clmxb.setGg(gg);
				clmxb.setXh(convertUtil.toString(ro.get("xh")));
				tmp_clmc = clmc;
				tmp_gg = gg;
			}
			map.put("clmxb", clmxb);
			map.put(convertUtil.toString(ro.get("dz")), convertUtil.toLong(ro.get("sl")));
		}
		if (map != null) {
			clmxbList.add(map);
		}
		modelMap.put("clmxbList", clmxbList);
		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		return new ModelAndView("/WEB-INF/jsp/wxdw/xmclfltj.jsp", modelMap);
	}

	/**
	 * 材料明细查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/wxdw/xmkcfltjmx.do")
	public ModelAndView xmkcfltjmx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		Long project_id = convertUtil.toLong(request.getParameter("project_id"));
		modelMap.put("gcxx", queryService.searchById(Td00_gcxx.class, project_id));
		String clmc = convertUtil.toString(request.getParameter("clmc"));
		String gg = convertUtil.toString(request.getParameter("gg"));
		String xh = convertUtil.toString(request.getParameter("xh"));
		String dz = convertUtil.toString(request.getParameter("dz"));
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

		StringBuffer hsql = new StringBuffer();
		hsql.append("select clmxb from Tf08_clmxb clmxb where zhxx_id=" + project_id);
		// where条件
		// 材料名称
		if (!clmc.equals("")) {
			hsql.append(" and clmc like '%" + clmc + "%'");
		}
		// 规格
		if (!gg.equals("")) {
			hsql.append(" and gg like '%" + gg + "%'");
		}
		// 型号
		if (!xh.equals("")) {
			hsql.append(" and xh like '%" + xh + "%'");
		}
		// 操作
		if (!dz.equals("")) {
			hsql.append(" and dz=" + dz);
		}
		// order排序
		// orderField
		hsql.append(" order by " + orderField);
		// orderDirection
		hsql.append(" " + orderDirection);

		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum, numPerPage);
		// 获取结果集
		List<Tf08_clmxb> clmxbList = new ArrayList<Tf08_clmxb>();
		while (ro.next()) {
			clmxbList.add((Tf08_clmxb) ro.get("clmxb"));
		}
		modelMap.put("clmxbList", clmxbList);
		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		dz = "0".equals(dz) ? "入库" : "1".equals(dz) ? "出库" : "缴料";
		modelMap.put("dz", dz);
		return new ModelAndView("/WEB-INF/jsp/wxdw/xmkcfltjmx.jsp", modelMap);
	}
}
