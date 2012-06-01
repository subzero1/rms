package com.rms.controller.wxdw;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta02_station;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta04_role;
import com.netsky.base.dataObjects.Ta11_sta_user;
import com.netsky.base.dataObjects.Ta22_user_idea;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.rms.dataObjects.base.Tc02_area;
import com.rms.dataObjects.wxdw.Tf01_wxdw;
import com.rms.dataObjects.wxdw.Tf04_wxdw_user;
import com.rms.dataObjects.wxdw.Tf05_wxdw_dygx;

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
		// 权限控制
		Map<String, Ta04_role> rolesMap = (Map<String, Ta04_role>) request.getSession().getAttribute("rolesMap");
		if (rolesMap.containsKey("30101")) {
			modelMap.put("canedit", "yes");
		}
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
		hsql.append("select wxdw from Tf01_wxdw wxdw where 1=1");
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
		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum, numPerPage);
		// 获取结果集
		List<Tf01_wxdw> wxdwList = new ArrayList<Tf01_wxdw>();
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
		String no = convertUtil.toString(queryService.searchList("select max(no) from Tf01_wxdw where lb='" + lb + "'")
				.get(0));
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
		return new ModelAndView("/WEB-INF/jsp/wxdw/fezbEdit.jsp", modelMap);
	}

	/**
	 * 最大在建工程数
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/zjgcsEdit.do")
	public ModelAndView zjgcsEdit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView("/WEB-INF/jsp/wxdw/zjgcsEdit.jsp", modelMap);
	}

	/**
	 * 关联交易额
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/gljyeEdit.do")
	public ModelAndView gljyeEdit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView("/WEB-INF/jsp/wxdw/gljyeEdit.jsp", modelMap);
	}

	/**
	 * 施工队
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/sgdEdit.do")
	public ModelAndView sgdEdit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView("/WEB-INF/jsp/wxdw/sgdEdit.jsp", modelMap);
	}

	/**
	 * 保存施工队
	 */
	@RequestMapping("/wxdw/ajaxSaveSgd.do")
	public void ajaxSaveSgd(HttpServletRequest request, HttpServletResponse response) throws Exception {

	}

	/**
	 * 删除施工队
	 */
	@RequestMapping("/wxdw/ajaxDelSgd.do")
	public void ajaxDelSgd(HttpServletRequest request, HttpServletResponse response) throws Exception {

	}

	public static void main(String[] args) {
		String s = "001";
		Long l = new Long(s);
		System.out.println(l);
		s = String.format("%03d", l++);
		System.out.println(s);
	}
}
