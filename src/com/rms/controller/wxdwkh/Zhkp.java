package com.rms.controller.wxdwkh;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.rms.dataObjects.form.Td01_xmxx;
import com.rms.dataObjects.wxdw.Tf01_wxdw;
import com.rms.dataObjects.wxdw.Tf04_wxdw_user;
import com.rms.dataObjects.wxdw.Tf17_rckh;
import com.rms.dataObjects.wxdw.Tf18_zhkp;

/**
 * @description:综合考评
 * 
 * @class name:com.rms.controller.wxdwkh.Zhkp
 * @author Chiang Aug 10, 2012
 */
@Controller
public class Zhkp {
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

	@Autowired
	private Dao dao;

	@RequestMapping("/wxdwkh/zhkpController.do")
	public ModelAndView zhkpController() throws Exception {
		Session session = dao.getHibernateSession();
		Transaction tx = session.beginTransaction();
		tx.begin();
		try {
			// 判断是否需要执行该动作
			Date date = new Date();
			String now = new SimpleDateFormat("yyyy-MM-dd").format(date);
			List<String> tmpList = (List<String>) dao.search("select name from Tc01_property where type='考评接转分值'");
			Double defaulta = convertUtil.toDouble(tmpList.isEmpty() ? null : tmpList.get(0), 80D);
			tmpList = (List<String>) dao.search("select name from Tc01_property where type='最后考评时间'");
			String zhkpsj = tmpList.isEmpty() ? (date.getYear() + "-01-01") : tmpList.get(0);
			tmpList = (List<String>) dao.search("select name from Tc01_property where type='考评间隔'");
			Long kpjg = convertUtil.toLong(tmpList.isEmpty() ? null : tmpList.get(0), 90L);
			if (kpjg > (date.getTime() - new SimpleDateFormat("yyyy-MM-dd").parse(zhkpsj).getTime()) / 1000 / 3600 / 24) {
				return null;
			}
			List<Object[]> ObjectsList = (List<Object[]>) session
					.createSQLQuery(
							"select tf01.id,tf01.mc,tf01.lb,"
									+ "t2.sum1 as b,"
									+ "(select t3.zhdf from Tf18_zhkp t3,(select max(dfsj) as dfsj, wxdw_id from Tf18_zhkp group by wxdw_id) t4 where t3.wxdw_id = t4.wxdw_id and t3.dfsj = t4.dfsj and t3.wxdw_id = tf01.id) as a,"
									+ "(select sum(tf17.jkfz) from Tf17_rckh tf17 where tf17.wxdw_id=tf01.id and qrsj>=to_date('"
									+ zhkpsj
									+ "','yyyy-MM-dd') and qrsj<to_date('"
									+ now
									+ "','yyyy-MM-dd')) as c,"
									+ "(trim(' ' from getzyqybyid(tf01.id))) as dq,"
									+ "(trim(' ' from getzyzybyid(tf01.id))) as zy"
									+ " from tf01_wxdw tf01 "
									+ "left join (select sum(t1.sum1)/count(*) as sum1, t1.sgdw as sgdw from "
									+ "(select td01.sgdw as sgdw, t0.sum1 as sum1 from td01_xmxx td01 left join "
									+ "(select sum(zdfz * jgfz) as sum1, project_id, lb from tf16_xmkhdf where lb = 'sg' and pfsj>=to_date('"
									+ zhkpsj
									+ "','yyyy-MM-dd') and pfsj<to_date('"
									+ now
									+ "','yyyy-MM-dd') group by project_id, lb) t0 on t0.project_id = td01.id) t1 group by t1.sgdw) t2 on t2.sgdw = tf01.mc where tf01.lb = '施工'")
					.list();
			for (Object[] o : ObjectsList) {
				Tf18_zhkp tf18 = new Tf18_zhkp();
				tf18.setWxdw_id(((BigDecimal) o[0]).longValue());
				tf18.setWxdw_mc(convertUtil.toString(o[1]));
				tf18.setWxdw_lb(convertUtil.toString(o[2]));
				tf18.setZyqy(convertUtil.toString(o[6]));
				tf18.setZylb(convertUtil.toString(o[7]));
				Double a = convertUtil.toDouble(o[4], defaulta);
				tf18.setAscore(a);
				if (o[4] == null) {
					tf18.setIsdefaulta(1L);
				}
				Double b = convertUtil.toDouble(o[3], defaulta);
				tf18.setBscore(b);
				if (o[3] == null) {
					tf18.setIsdefaultb(1L);
				}
				Double c = convertUtil.toDouble(o[5], 0D);
				tf18.setCscore(c);
				tf18.setDfsj(date);
				tf18.setZhdf(a * 0.3 + b * 0.7 - c);
				List<Td01_xmxx> td01List = (List<Td01_xmxx>) dao.search("from Td01_xmxx td01 where sgdw='"
						+ convertUtil.toString(o[1]) + "' and" + " cjrq>=to_date('"
						+ new SimpleDateFormat("yyyy").format(date) + "-01-01" + "','yyyy-MM-dd') and cjrq<to_date('"
						+ now + "','yyyy-MM-dd')");
				Long xms = new Long(td01List.size());
				tf18.setXms(xms);
				if (xms == 0L) {
					tf18.setHte(0D);
					tf18.setJse(0D);
					tf18.setWgl(0D);
					tf18.setCql(0D);
					tf18.setJsl(0D);
				} else {
					Double hte = 0D;
					Double jse = 0D;
					int wgs = 0;
					int cqs = 0;
					int jss = 0;
					for (Td01_xmxx td01 : td01List) {
						hte += convertUtil.toDouble(td01.getSghtje(), 0D);
						jse += convertUtil.toDouble(td01.getSs_je(), 0D);
						if (td01.getSjjgsj() != null) {
							wgs++;
						}
						if (td01.getJhkgsj() != null
								&& td01.getJhjgsj() != null
								&& td01.getSjkgsj() != null
								&& (td01.getJhjgsj().getTime() - td01.getJhkgsj().getTime()) < ((td01.getSjjgsj() == null ? date
										: td01.getSjjgsj()).getTime() - td01.getSjkgsj().getTime())) {
							cqs++;
						}
						if (td01.getJssj() != null) {
							jss++;
						}
					}
					int size = td01List.size();
					tf18.setWgl((double) wgs / size);
					tf18.setCql((double) cqs / size);
					tf18.setJsl((double) jss / size);
				}
				tf18.setLastdfsj(new SimpleDateFormat("yyyy-MM-dd").parse(zhkpsj));
				session.save(tf18);
			}
			session.createQuery("update Tc01_property set name='" + now + "' where type='最后考评时间'").executeUpdate();
			session.flush();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@RequestMapping("/wxdwkh/zhkpList.do")
	public ModelAndView zhkpList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		// 分页
		Integer totalPages = 1;
		Integer totalCount = 0;
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request.getParameter("orderField"), "dfsj");
		if (orderField.equals("")) {
			orderField = "dfsj";
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
		String date1 = convertUtil.toString(request.getParameter("date1"));
		String date2 = convertUtil.toString(request.getParameter("date2"));
		if (!date1.equals("") && !date2.equals("") && date1.compareTo(date2) > 0) {
			String tmp = date2;
			date2 = date1;
			date1 = tmp;
		}
		modelMap.put("date1", date1);
		modelMap.put("date2", date2);
		StringBuffer hsql = new StringBuffer();
		hsql.append("select tf18 from Tf18_zhkp tf18 where 1=1");
		// where条件
		// 考核人员OR外协单位人员
		String type = convertUtil.toString(request.getParameter("type"));
		String whereClause = "";
		if (type.equals("khry")) {
			String wxdw_lb = convertUtil.toString(request.getParameter("wxdw_lb"), "");
			String wxdw_mc = convertUtil.toString(request.getParameter("wxdw_mc"));
			if (!wxdw_lb.equals("")) {
				whereClause += " and wxdw_lb='" + wxdw_lb + "'";
			}
			if (!wxdw_mc.equals("")) {
				whereClause += " and wxdw_mc like'%" + wxdw_lb + "%'";
			}
		} else if (type.equals("wxdw")) {
			Ta03_user user = null;
			Tf04_wxdw_user tf04 = null;
			try {
				user = (Ta03_user) request.getSession().getAttribute("user");
				tf04 = (Tf04_wxdw_user) ((dao.search("from Tf04_wxdw_user where user_id=" + user.getId())).get(0));
			} catch (Exception e) {
				return exceptionService.exceptionControl("com.rms.controller.wxdwkh.Zhkp", "用户登录超时或不是外协单位人员", e);
			}
			whereClause = " and wxdw_id=" + tf04.getWxdw_id();
		}
		hsql.append(whereClause);
		// 考核日期
		if (!"".equals(date1)) {
			hsql.append(" and dfsj>=to_date('" + date1 + " 00:00:00','yyyy-MM-dd hh24:mi:ss')");
		}
		if (!"".equals(date2)) {
			hsql.append(" and dfsj<=to_date('" + date2 + " 23:59:59','yyyy-MM-dd hh24:mi:ss')");
		}
		// order排序
		// orderField
		hsql.append(" order by " + orderField);
		// orderDirection
		hsql.append(" " + orderDirection);
		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum, numPerPage);
		// 获取结果集
		List<Tf18_zhkp> zhkpList = new ArrayList<Tf18_zhkp>();
		while (ro.next()) {
			zhkpList.add((Tf18_zhkp) ro.get("tf18"));
		}
		modelMap.put("zhkpList", zhkpList);
		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		// 页面所需内容
		// 外协单位类别
		List<String> lbList = new ArrayList<String>();
		lbList.add("设计");
		lbList.add("施工");
		lbList.add("监理");
		modelMap.put("lbList", lbList);
		return new ModelAndView("/WEB-INF/jsp/wxdwkh/zhkpList.jsp", modelMap);
	}

	@RequestMapping("/wxdwkh/zhkp.do")
	public ModelAndView zhkp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		Long id = convertUtil.toLong(request.getParameter("id"));
		Tf18_zhkp tf18 = (Tf18_zhkp) dao.getObject(Tf18_zhkp.class, id);
		modelMap.put("tf18", tf18);
		return new ModelAndView("/WEB-INF/jsp/wxdwkh/zhkp.jsp", modelMap);
	}

	@RequestMapping("/wxdwkh/khkf.do")
	public ModelAndView khkf(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		// 分页
		Integer totalPages = 1;
		Integer totalCount = 0;
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request.getParameter("orderField"), "khsj");
		if (orderField.equals("")) {
			orderField = "khsj";
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
		Long id = convertUtil.toLong(request.getParameter("id"));
		Tf18_zhkp tf18 = (Tf18_zhkp) dao.getObject(Tf18_zhkp.class, id);
		StringBuffer hsql = new StringBuffer();
		hsql.append("from Tf17_rckh rckh where 1=1 and qrsj is not null and wxdw_mc='" + tf18.getWxdw_mc()
				+ "' and wxdw_lb='" + tf18.getWxdw_lb() + "'");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		hsql.append(" and qrsj>=to_date('" + sdf.format(tf18.getLastdfsj()) + "','yyyy-MM-dd') and qrsj<to_date('"
				+ sdf.format(tf18.getDfsj()) + "','yyyy-MM-dd')");
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
		List<Tf17_rckh> rckhList = new ArrayList<Tf17_rckh>();
		// 导EXCEL
		if ("yes".equals(request.getParameter("toExcel"))) {
			Map<String, List> sheetMap = new HashMap<String, List>();
			List sheetList = new LinkedList();
			List titleList = new LinkedList();
			String form_title = tf18.getWxdw_mc() + "-日常考核扣分.xls";
			titleList.add("考核时间");
			titleList.add("考核人员");
			titleList.add("考核原因");
			titleList.add("考核类别");
			titleList.add("罚款");
			titleList.add("加扣分");
			titleList.add("考核结果");
			sheetList.add(titleList);
			List<List> docList = new LinkedList<List>();
			while (ro.next()) {
				List row = new LinkedList();
				Tf17_rckh rckh = (Tf17_rckh) ro.get("rckh");
				row.add(sdf.format(rckh.getKhsj()));
				row.add(convertUtil.toString(rckh.getKhry_name()));
				row.add(convertUtil.toString(rckh.getKhyy()));
				row.add(convertUtil.toString(rckh.getKhlb()));
				row.add(convertUtil.toString(new BigDecimal(rckh.getFkje()).setScale(2, BigDecimal.ROUND_HALF_UP)
						.toString()));
				row.add(convertUtil.toString(new BigDecimal(rckh.getJkfz()).setScale(2, BigDecimal.ROUND_HALF_UP)
						.toString()));
				row.add(convertUtil.toString(rckh.getKhjg()));
				docList.add(row);
			}
			sheetList.add(docList);
			sheetMap.put(form_title, sheetList);
			request.setAttribute("ExcelName", form_title);
			request.setAttribute("sheetMap", sheetMap);
			return new ModelAndView("/export/toExcelWhithList.do");
		}
		while (ro.next()) {
			rckhList.add((Tf17_rckh) ro.get("rckh"));
		}
		modelMap.put("rckhList", rckhList);
		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		// 页面所需内容
		return new ModelAndView("/WEB-INF/jsp/wxdwkh/khkf.jsp", modelMap);
	}
	
	@RequestMapping("/wxdwkh/xmList.do")
	public ModelAndView xmList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		// 分页
		Integer totalPages = 1;
		Integer totalCount = 0;
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request.getParameter("orderField"), "xmbh");
		if (orderField.equals("")) {
			orderField = "xmbh";
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
		Long id = convertUtil.toLong(request.getParameter("id"));
		Tf18_zhkp tf18 = (Tf18_zhkp) dao.getObject(Tf18_zhkp.class, id);
		StringBuffer hsql = new StringBuffer();
		hsql.append("from Td01_xmxx xmxx where sgdw='"+tf18.getWxdw_mc()+"'");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		hsql.append(" and cjrq>=to_date('" + new SimpleDateFormat("yyyy").format(tf18.getDfsj()) + "-01-01','yyyy-MM-dd') and cjrq<to_date('"
				+ sdf.format(tf18.getDfsj()) + "','yyyy-MM-dd')");
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
		List<Td01_xmxx> xmList = new ArrayList<Td01_xmxx>();
		// 导EXCEL
		if ("yes".equals(request.getParameter("toExcel"))) {
			Map<String, List> sheetMap = new HashMap<String, List>();
			List sheetList = new LinkedList();
			List titleList = new LinkedList();
			String form_title = tf18.getWxdw_mc() + "关联项目.xls";
			titleList.add("项目编号");
			titleList.add("项目名称");
			titleList.add("工程类别");
			titleList.add("建设性质");
			titleList.add("专业大类");
			titleList.add("专业小项");
			titleList.add("切块大类");
			titleList.add("切块小类");
			sheetList.add(titleList);
			List<List> docList = new LinkedList<List>();
			while (ro.next()) {
				List row = new LinkedList();
				Td01_xmxx xmxx = (Td01_xmxx) ro.get("xmxx");
				row.add(convertUtil.toString(xmxx.getXmbh()));
				row.add(convertUtil.toString(xmxx.getXmmc()));
				row.add(convertUtil.toString(xmxx.getGclb()));
				row.add(convertUtil.toString(xmxx.getJsxz()));
				row.add(convertUtil.toString(xmxx.getZydl()));
				row.add(convertUtil.toString(xmxx.getZyxx()));
				row.add(convertUtil.toString(xmxx.getQkdl()));
				row.add(convertUtil.toString(xmxx.getQkxl()));
				docList.add(row);
			}
			sheetList.add(docList);
			sheetMap.put(form_title, sheetList);
			request.setAttribute("ExcelName", form_title);
			request.setAttribute("sheetMap", sheetMap);
			return new ModelAndView("/export/toExcelWhithList.do");
		}
		while (ro.next()) {
			xmList.add((Td01_xmxx) ro.get("xmxx"));
		}
		modelMap.put("xmList", xmList);
		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		// 页面所需内容
		return new ModelAndView("/WEB-INF/jsp/wxdwkh/xmList.jsp", modelMap);
	}
}
