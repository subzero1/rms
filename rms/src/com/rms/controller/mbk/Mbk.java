package com.rms.controller.mbk;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
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
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta01_dept;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta04_role;
import com.netsky.base.dataObjects.Ta05_group;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.rms.dataObjects.mbk.Td21_mbk;
import com.rms.dataObjects.mbk.Td22_mbk_lzjl;
import com.rms.dataObjects.wxdw.Tf01_wxdw;

@Controller
public class Mbk {
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
	 * 目标库信息列表
	 */
	/**
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/mbk/mbkList.do")
	public ModelAndView mbkList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		// 分页
		Integer totalPages = 1;
		Integer totalCount = 0;
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request.getParameter("orderField"), "zymc");
		if (orderField.equals("")) {
			orderField = "zymc";
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
		String zymc = convertUtil.toString(request.getParameter("zymc"));
		String lb = convertUtil.toString(request.getParameter("lb"));
		String ssdq = convertUtil.toString(request.getParameter("ssdq"));
		String zt = convertUtil.toString(request.getParameter("zt"));

		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Td21_mbk mbk where 1=1");
		// where条件
		//非20101角色
		Map<String, Ta04_role> rolesMap = (Map<String, Ta04_role>)request.getSession().getAttribute("rolesMap");
		if (rolesMap.get("20101") == null){
			Ta03_user user = (Ta03_user)request.getSession().getAttribute("user");
			hsql.append(" and ((tdr_id=" + user.getId()+" and zt='开始谈点') or (zt='新建' and hdfs='派发'))");
		}
		// 类别
		if (!lb.equals("")) {
			hsql.append(" and lb='" + lb + "'");
		}
		// 所属地区
		if (!ssdq.equals("")) {
			hsql.append(" and ssdq='" + ssdq + "'");
		}
		// 状态
		if (!zt.equals("")) {
			hsql.append(" and zt='" + zt + "'");
		}
		// 资源名称
		if (!zymc.equals("")) {
			hsql.append(" and zymc like '%" + zymc + "%'");
		}
		// order排序
		// orderField
		hsql.append(" order by " + orderField);
		// orderDirection
		hsql.append(" " + orderDirection);
		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum, numPerPage);
		// 获取结果集
		List<Td21_mbk> mbkList = new ArrayList<Td21_mbk>();
		while (ro.next()) {
			mbkList.add((Td21_mbk) ro.get("mbk"));
		}
		modelMap.put("mbkList", mbkList);
		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		// 页面所需内容
		// 类别
		List<String> lbList = (List<String>) queryService
				.searchList("select name from Tc01_property where type='目标库类别'");
		modelMap.put("lbList", lbList);
		List<String> dqList = (List<String>) queryService.searchList("select name from Tc02_area");
		modelMap.put("dqList", dqList);
		List<String> ztList = (List<String>) queryService
				.searchList("select name from Tc01_property where type='目标库状态'");
		modelMap.put("ztList", ztList);
		return new ModelAndView("/WEB-INF/jsp/mbk/mbkList.jsp", modelMap);

	}

	/**
	 * 目标库信息
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/mbk/mbkEdit.do")
	public ModelAndView mbkEdit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		Long id = convertUtil.toLong(request.getParameter("id"));
		Td21_mbk mbk = (Td21_mbk) queryService.searchById(Td21_mbk.class, id);
		modelMap.put("Td21_mbk", mbk);
		List<String> jsxzList = (List<String>) queryService
				.searchList("select name from Tc01_property where type='建设性质'");
		modelMap.put("jsxzList", jsxzList);
		List<String> lbList = (List<String>) queryService
				.searchList("select name from Tc01_property where type='目标库类别'");
		modelMap.put("lbList", lbList);
		List<String> jsfsList = (List<String>) queryService
				.searchList("select name from Tc01_property where type='建设方式'");
		modelMap.put("jsfsList", jsfsList);
		List<String> dqList = (List<String>) queryService.searchList("select name from Tc02_area");
		modelMap.put("dqList", dqList);
		List<String> tdbmList = (List<String>) queryService
				.searchList("select name from Tc01_property where type='谈点部门'");
		modelMap.put("tdbmList", tdbmList);
		if (mbk != null) {
			modelMap.put("lzjlList", queryService.searchList("from Td22_mbk_lzjl where mbk_id="+id+" order by id asc"));
		}
		return new ModelAndView("/WEB-INF/jsp/mbk/mbkEdit.jsp", modelMap);
	}

	@RequestMapping("/mbk/getZybh.do")
	public void getZybh(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding(request.getCharacterEncoding());
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String jsxz = convertUtil.toString(request.getParameter("jsxz"));
		String s = "";
		if ("室分".equals(jsxz)) {
			s = "SF";
		} else if ("基站".equals(jsxz)) {
			s = "JZ";
		}
		Calendar calendar = Calendar.getInstance();
		s += calendar.get(Calendar.YEAR);
		Session session = queryService.getHibernateTemplate().getSessionFactory().openSession();
		try {
			Long no = ((BigDecimal) (session.createSQLQuery("select source_num.nextval from dual").uniqueResult()))
					.longValue();
			s += String.format("%06d", no);
		} finally {
			session.close();
		}
		out.print(s);
	}

	@RequestMapping("/mbk/ajaxMbkDel.do")
	public void ajaxMbkDel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = convertUtil.toLong(request.getParameter("id"));
		PrintWriter out = null;

		response.setContentType("text/html;charset=UTF-8");
		Session session = saveService.getHiberbateSession();
		Transaction tx = session.beginTransaction();
		tx.begin();
		// 获取用户对象
		try {
			out = response.getWriter();
			session.createQuery("delete from Td21_mbk where id=" + id).executeUpdate();
			session.createQuery("delete from Td22_mbk_lzjl where mbk_id=" + id).executeUpdate();
			out.print("{\"statusCode\":\"200\", \"message\":\"删除成功\", \"callbackType\":\"forward\"}");
			session.flush();
			tx.commit();
		} catch (IOException e) {
			tx.rollback();
			out.print("{\"statusCode\":\"300\", \"message\":\"删除失败\"}");
		} finally {
			session.close();
		}
	}

	@RequestMapping("/mbk/mbkLz.do")
	public void mbkLz(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		Session session = saveService.getHiberbateSession();
		Transaction tx = session.beginTransaction();
		Long id = convertUtil.toLong(request.getParameter("id"));
		Td21_mbk td21 = (Td21_mbk) queryService.searchById(Td21_mbk.class, id);
		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		Ta01_dept dept = (Ta01_dept) queryService.searchById(Ta01_dept.class, user.getDept_id());
		tx.begin();
		String word = "";
		Date now = new Date();
		String type = convertUtil.toString(request.getParameter("type"));
		// 获取用户对象
		try {
			out = response.getWriter();
			if ("zdrl".equals(type)) {// 派发
				word = "派发";
				td21.setHdfs("派发");
			} else if ("rl".equals(type)) {// 认领
				word = "认领";
				td21.setZt("开始谈点");
				td21.setTdr(user.getName());
				td21.setTdr_id(user.getId());
				td21.setTdrdh(user.getMobile_tel());
				td21.setTdbm(dept.getName());
				Td22_mbk_lzjl td22 = new Td22_mbk_lzjl();
				td22.setSm(user.getName() + "开始谈点");
				td22.setKssj(now);
				td22.setMbk_id(id);
				td22.setXgr(user.getName());
				td22.setXgr_id(user.getId());
				td22.setXgr_bm(dept.getName());
				session.save(td22);
			} else if ("zdxf".equals(type)) {// 指定下发
				word = "指定下发";
				td21.setHdfs("指定下发");
				td21.setZt("开始谈点");
				Td22_mbk_lzjl td22 = new Td22_mbk_lzjl();
				td22.setSm(td21.getTdr() + "开始谈点");
				td22.setKssj(now);
				td22.setMbk_id(id);
				td22.setXgr(td21.getTdr());
				td22.setXgr_id(td21.getTdr_id());
				td22.setXgr_bm(td21.getTdbm());
				session.save(td22);
			} else if ("cxtd".equals(type)) {// 重新谈点
				word = "重新谈点";
				td21.setHdfs(null);
				td21.setZt("新建");
				td21.setTdr(null);
				td21.setTdr_id(null);
				td21.setTdrdh(null);
				td21.setTdbm(null);
				session.createQuery(
						"update Td22_mbk_lzjl set Jssj=sysdate,sm='" + user.getName() + "从'||xgr||'处收回"
								+ "' where mbk_id=" + id + " and jssj is null").executeUpdate();
			} else if ("dcxy".equals(type)) {// 达成协议
				word = "达成协议";
				td21.setZt("达成协议");
				session.createQuery("update Td22_mbk_lzjl set jssj=sysdate where jssj is null and mbk_id=" + id)
						.executeUpdate();
			} else if ("sfkc".equals(type)) {// 四方勘察
				word = "四方勘察";
				td21.setZt("四方勘察");
				String[] ids = convertUtil.toString(request.getParameter("ids")).split(",");
				for (String string : ids) {
					Ta03_user ta03 = (Ta03_user) queryService.searchById(Ta03_user.class, convertUtil.toLong(string));
					Ta01_dept ta01 = (Ta01_dept) queryService.searchById(Ta01_dept.class, ta03.getDept_id());
					Td22_mbk_lzjl td22 = new Td22_mbk_lzjl();
					td22.setSm("四方勘察");
					td22.setKssj(now);
					td22.setXgr(ta03.getName());
					td22.setXgr_bm(ta01.getName());
					td22.setXgr_id(ta03.getId());
					td22.setMbk_id(id);
					session.save(td22);
				}
			} else if ("kcjs".equals(type)) {// 勘察结束
				word = "勘察结束";
				td21.setZt("勘察结束");
				session.createQuery("update Td22_mbk_lzjl set jssj=sysdate where jssj is null and mbk_id=" + id)
						.executeUpdate();
			} else if ("fahs".equals(type)) {// 方案会审
				word = "方案会审";
				td21.setZt("方案会审");
				String[] ids = convertUtil.toString(request.getParameter("ids")).split(",");
				for (String string : ids) {
					Ta03_user ta03 = (Ta03_user) queryService.searchById(Ta03_user.class, convertUtil.toLong(string));
					Ta01_dept ta01 = (Ta01_dept) queryService.searchById(Ta01_dept.class, ta03.getDept_id());
					Td22_mbk_lzjl td22 = new Td22_mbk_lzjl();
					td22.setSm("方案会审");
					td22.setKssj(now);
					td22.setXgr(ta03.getName());
					td22.setXgr_bm(ta01.getName());
					td22.setXgr_id(ta03.getId());
					td22.setMbk_id(id);
					session.save(td22);
				}
			} else if ("hswc".equals(type)) {// 会审完成
				word = "会审完成";
				td21.setZt("会审完成");
				session.createQuery("update Td22_mbk_lzjl set jssj=sysdate where jssj is null and mbk_id=" + id)
						.executeUpdate();
			} else if ("zjs".equals(type)) {// 转建设
				word = "转建设";
				td21.setZt("转建设");
				Long xmgly_id = convertUtil.toLong(request.getParameter("xmgly_id"));
				// 下面这行是测试用的!!
				xmgly_id = 1L;
				Ta03_user ta03 = (Ta03_user) queryService.searchById(Ta03_user.class, xmgly_id);
				Ta01_dept ta01 = (Ta01_dept) queryService.searchById(Ta01_dept.class, ta03.getDept_id());
				Td22_mbk_lzjl td22 = new Td22_mbk_lzjl();
				td22.setSm("转建设");
				td22.setKssj(now);
				td22.setXgr(ta03.getName());
				td22.setXgr_bm(ta01.getName());
				td22.setXgr_id(ta03.getId());
				td22.setMbk_id(id);
				session.save(td22);
			} else if ("jsz".equals(type)) {// 建设中
				word = "建设中";
				td21.setZt("建设中");
				// 打开‘新建需求’表单，起草需求
			}
			session.update(td21);
			out.print("{\"statusCode\":\"200\", \"message\":\"" + word + "成功\", \"callbackType\":\"forward\"}");
			session.flush();
			tx.commit();
		} catch (IOException e) {
			tx.rollback();
			out.print("{\"statusCode\":\"300\", \"message\":\"" + word + "失败\"}");
		} finally {
			session.close();
		}
	}

	@RequestMapping("/mbk/getTdbm.do")
	public void getTdbm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		Long id = convertUtil.toLong(request.getParameter("id"));
		String s = "";
		List<Tf01_wxdw> wxdwList = (List<Tf01_wxdw>) queryService
				.searchList("from Tf01_wxdw where id in(select wxdw_id from Tf04_wxdw_user where user_id=" + id + ")");
		if (wxdwList != null && wxdwList.size() != 0) {
			s = wxdwList.get(0).getMc();
		} else {
			s = convertUtil.toString(queryService.searchList(
					"select name from Ta01_dept where id=(select dept_id from Ta03_user where id=" + id + ")").get(0));
		}
		out.print(s);
	}

	@RequestMapping("/mbk/getTdr.do")
	public ModelAndView getTdr(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 10);
		Integer totalCount = 0;
		Integer pageNumShown = 0;

		// 排序变量
		String orderField = convertUtil.toString(request.getParameter("orderField"), "user.id");
		String orderDirection = convertUtil.toString(request.getParameter("orderDirection"), "asc");

		String name = convertUtil.toString(request.getParameter("name"));
		StringBuffer hsql = new StringBuffer();
		hsql
				.append("select user from Ta03_user user where id in (select user_id from Ta11_sta_user where station_id in(select station_id from Ta12_sta_role where role_id=20102)) and name like '%"
						+ name + "%'");
		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum, numPerPage);
		totalCount = ro.getTotalRows();
		pageNumShown = ro.getTotalPages();
		modelMap.put("tdrList", ro.getList());
		modelMap.put("totalCount", totalCount);
		modelMap.put("pageNumShown", pageNumShown);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		return new ModelAndView("/WEB-INF/jsp/mbk/selectTdr.jsp", modelMap);
	}
	@RequestMapping("/mbk/getKcry.do")
	public ModelAndView getKcry(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 10);
		Integer totalCount = 0;
		Integer pageNumShown = 0;

		// 排序变量
		String orderField = convertUtil.toString(request.getParameter("orderField"), "user.id");
		String orderDirection = convertUtil.toString(request.getParameter("orderDirection"), "asc");

		String name = convertUtil.toString(request.getParameter("name"));
		StringBuffer hsql = new StringBuffer();
		hsql
				.append("select user from Ta03_user user where id in (select user_id from Ta11_sta_user where station_id in(select station_id from Ta12_sta_role where role_id=20103)) and name like '%"
						+ name + "%'");
		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum, numPerPage);
		totalCount = ro.getTotalRows();
		pageNumShown = ro.getTotalPages();
		modelMap.put("kcryList", ro.getList());
		modelMap.put("totalCount", totalCount);
		modelMap.put("pageNumShown", pageNumShown);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		return new ModelAndView("/WEB-INF/jsp/mbk/selectKcry.jsp", modelMap);
	}
	@RequestMapping("/mbk/getHsry.do")
	public ModelAndView getHsry(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 10);
		Integer totalCount = 0;
		Integer pageNumShown = 0;

		// 排序变量
		String orderField = convertUtil.toString(request.getParameter("orderField"), "user.id");
		String orderDirection = convertUtil.toString(request.getParameter("orderDirection"), "asc");

		String name = convertUtil.toString(request.getParameter("name"));
		StringBuffer hsql = new StringBuffer();
		hsql
				.append("select user from Ta03_user user where id in (select user_id from Ta11_sta_user where station_id in(select station_id from Ta12_sta_role where role_id=20104)) and name like '%"
						+ name + "%'");
		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum, numPerPage);
		totalCount = ro.getTotalRows();
		pageNumShown = ro.getTotalPages();
		modelMap.put("hsryList", ro.getList());
		modelMap.put("totalCount", totalCount);
		modelMap.put("pageNumShown", pageNumShown);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		return new ModelAndView("/WEB-INF/jsp/mbk/selectHsry.jsp", modelMap);
	}
}
