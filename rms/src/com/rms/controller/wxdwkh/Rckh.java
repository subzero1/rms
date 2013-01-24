package com.rms.controller.wxdwkh;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.rms.base.util.ConfigXML;
import com.rms.base.util.ConfigXMLImpl;
import com.rms.dataObjects.wxdw.Tf04_wxdw_user;
import com.rms.dataObjects.wxdw.Tf17_rckh;

@Controller
public class Rckh {
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

	/**
	 * 日常考核列表
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/wxdwkh/rckhList.do")
	public ModelAndView rckhList(HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		// 分页
		Ta03_user user = null;
		Integer totalPages = 1;
		Integer totalCount = 0;
		boolean isManager = false;
		user = (Ta03_user) request.getSession().getAttribute("user");
		Map rolesMap = (Map)request.getSession().getAttribute("rolesMap");
		if(rolesMap.get("50100") == null){
			isManager = false;
		}
		else{
			isManager = true;
		}
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
		String khlb = convertUtil.toString(request.getParameter("khlb"));
		String khry = convertUtil.toString(request.getParameter("khry"));
		String date1 = convertUtil.toString(request.getParameter("date1"));
		String date2 = convertUtil.toString(request.getParameter("date2"));
		String qrzt = convertUtil.toString(request.getParameter("qrzt"));
		if (!date1.equals("") && !date2.equals("") && date1.compareTo(date2) > 0) {
			String tmp = date2;
			date2 = date1;
			date1 = tmp;
		}
		modelMap.put("date1", date1);
		modelMap.put("date2", date2);
		StringBuffer hsql = new StringBuffer();
		hsql.append("select rckh from Tf17_rckh rckh where 1=1");
		// where条件
		// 确认状态
		if (qrzt.equals("未确认")) {
			hsql.append(" and (qrsj is null)");
		} else if (qrzt.equals("已确认")) {
			hsql.append(" and qrsj is not null");
		}
		// 考核类别
		if (!khlb.equals("")) {
			hsql.append(" and khlb='" + khlb + "'");
		}
		
		// 考核人员OR合作单位人员
		String type = convertUtil.toString(request.getParameter("type"));
		String whereClause = "";
		if (type.equals("khry")) {
			String wxdw_lb = convertUtil.toString(request.getParameter("wxdw_lb"), "");
			String wxdw_mc = convertUtil.toString(request.getParameter("wxdw_mc"));
			if(!isManager){
				whereClause += " and khry_name='" + user.getName() + "'";
			}
			else{
				// 考核人员
				if (!khry.equals("")) {
					hsql.append(" and khry_name='" + khry + "'");
				}
			}
			if (!wxdw_lb.equals("")) {
				whereClause += " and wxdw_lb='" + wxdw_lb + "'";
			}
			if (!wxdw_mc.equals("")) {
				whereClause += " and wxdw_mc like'%" + wxdw_lb + "%'";
			}
		} else if (type.equals("wxdw")) {
			
			Tf04_wxdw_user tf04 = null;
			try {
				tf04 = (Tf04_wxdw_user) ((dao.search("from Tf04_wxdw_user where user_id=" + user.getId())).get(0));
			} catch (Exception e) {
				return exceptionService.exceptionControl("com.rms.controller.wxdwkh.Rckh", "用户登录超时或不是合作单位人员", e);
			}
			whereClause = " and wxdw_id=" + tf04.getWxdw_id();
		}
		hsql.append(whereClause);
		// 考核日期
		if (!"".equals(date1)) {
			hsql.append(" and khsj>=to_date('" + date1 + " 00:00:00','yyyy-MM-dd hh24:mi:ss')");
		}
		if (!"".equals(date2)) {
			hsql.append(" and khsj<=to_date('" + date2 + " 23:59:59','yyyy-MM-dd hh24:mi:ss')");
		}
		// order排序
		// orderField
		hsql.append(" order by " + orderField);
		// orderDirection
		hsql.append(" " + orderDirection);
		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum, numPerPage);
		// 获取结果集
		List<Tf17_rckh> rckhList = new ArrayList<Tf17_rckh>();
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
		// 合作单位类别
		List<String> lbList = new ArrayList<String>();
		lbList.add("设计");
		lbList.add("施工");
		lbList.add("监理");
		modelMap.put("lbList", lbList);
		// 考核类别
		modelMap.put("khlbList", dao.search("from Tc01_property where type='考核类别'"));
		// 确认状态列表
		List<String> qrztList = new ArrayList<String>();
		qrztList.add("已确认");
		qrztList.add("未确认");
		modelMap.put("qrztList", qrztList);
		
		hsql.delete(0, hsql.length());
		hsql.append("select ta03 from Ta03_user ta03,Ta11_sta_user ta11 ");
		hsql.append("where ta03.id = ta11.user_id ");
		hsql.append("and ta11.station_id = 6 ");
		hsql.append("order by ta03.name");
		List khryList = queryService.searchList(hsql.toString());
		modelMap.put("khryList", khryList);
		
		return new ModelAndView("/WEB-INF/jsp/wxdwkh/rckhList.jsp", modelMap);
	}

	/**
	 * 日常考核修改
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/wxdwkh/rckhEdit.do")
	public ModelAndView rckhEdit(HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		Long id = convertUtil.toLong(request.getParameter("id"));
		Tf17_rckh tf17 = (Tf17_rckh) dao.getObject(Tf17_rckh.class, id);
		modelMap.put("tf17", tf17);
		String canedit = convertUtil.toString(request.getParameter("canedit"));
		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		if (tf17 != null && (tf17.getQrsj() != null || !tf17.getKhry_id().equals(user.getId()))) {
			canedit = "false";
		}
		String[] khdz = {"奖励","通报"};
		modelMap.put("khdzList", khdz);
		// 考核类别
		modelMap.put("khlbList", dao.search("from Tc01_property where type='考核类别' order by id "));
		return new ModelAndView("/WEB-INF/jsp/wxdwkh/rckhEdit.jsp?canedit=" + canedit, modelMap);
	}

	/**
	 * 合作单位查找带回
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/wxdwkh/selectWxdw.do")
	public ModelAndView selectWxdw(HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 10);
		Integer totalCount = 0;
		Integer pageNumShown = 0;

		// 排序变量
		String orderField = convertUtil.toString(request.getParameter("orderField"), "mc");
		String orderDirection = convertUtil.toString(request.getParameter("orderDirection"), "asc");

		String mc = convertUtil.toString(request.getParameter("mc"));
		String lb = convertUtil.toString(request.getParameter("lb"));
		StringBuffer hsql = new StringBuffer();
		hsql.append("select tf01 from Tf01_wxdw tf01 where mc like '%" + mc + "%' and lb like '%" + lb + "%'");
		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum, numPerPage);
		totalCount = ro.getTotalRows();
		pageNumShown = ro.getTotalPages();
		modelMap.put("wxdwList", ro.getList());
		modelMap.put("totalCount", totalCount);
		modelMap.put("pageNumShown", pageNumShown);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		// 合作单位类别
		List<String> lbList = new ArrayList<String>();
		lbList.add("设计");
		lbList.add("施工");
		lbList.add("监理");
		modelMap.put("lbList", lbList);
		return new ModelAndView("/WEB-INF/jsp/wxdwkh/selectWxdw.jsp", modelMap);
	}
	
	/**
	 * 删除施工队
	 */
	@RequestMapping("/wxdwkh/ajaxDelRckh.do")
	public void ajaxDelRckh(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setCharacterEncoding(request.getCharacterEncoding());
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		Long kh_id = convertUtil.toLong(request.getParameter("id"));
		try {
			dao.update("delete from Tf17_rckh where id=" + kh_id);
			out.print("{\"statusCode\":\"200\",\"message\":\"删除成功！\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");
		} catch (Exception e) {
			e.printStackTrace();
			out.print("{\"statusCode\":\"300\",\"message\":\"删除失败！\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");

		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 *            void
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/wxdwkh/rckhToExcel.do")
	public ModelAndView mbkToExcel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		String wxdw_lb = convertUtil.toString(request.getParameter("wxdw_lb"), "");
		String wxdw_mc = convertUtil.toString(request.getParameter("wxdw_mc"), "");
		String khlb = convertUtil.toString(request.getParameter("khlb"), "");
		String date1 = convertUtil.toString(request.getParameter("date1"), "");
		String date2 = convertUtil.toString(request.getParameter("date2"), "");
		String config = convertUtil.toString(request.getParameter("config"));

		int k = 0;
		ConfigXML configXML = new ConfigXMLImpl();// 读取rckh配置文档
		ResultObject ro = null;
		StringBuffer hql = new StringBuffer("");
		List rckhTitleList = new LinkedList(); // 标题列表
		List rckhColList = new LinkedList();// 列的字段值
		List rckhDocList = new LinkedList();// 表单数据
		Map<String, List> sheetMap = new HashMap<String, List>();
		List sheetList = new LinkedList();

		// 读取配置文件的标题列表
		String webinfpath = request.getSession().getServletContext().getRealPath("WEB-INF");
		rckhTitleList = configXML.getTagListByConfig(config, webinfpath, "name");
		rckhColList = configXML.getTagListByConfig(config, webinfpath,"columnName");
		Iterator it = rckhColList.iterator();
		Object rckh = null;
		hql.append("select ");
		while (it.hasNext()) {
			if (k == 0)
				hql.append(" rckh." + ((it.next().toString()).toLowerCase()));
			else
				hql.append(" ,rckh." + ((it.next().toString()).toLowerCase()));
			k++;
		}
		hql.append(" from Tf17_rckh rckh ");
		// 条件
		hql.append("where 1=1 ");
		if (!wxdw_lb.equals("")) {
			hql.append(" and rckh.wxdw_lb='" + wxdw_lb+"'");
		}
		if (!wxdw_mc.equals("")) {
			hql.append(" and rckh.wxdw_mc like '%" + wxdw_mc+"%'");
		}
		if (!khlb.equals("")) {
			hql.append(" and rckh.khlb=" + khlb);
		}
		if (!date1.equals("")) {
			hql.append(" and rckh.khsj > to_date('" + date1 + "','yyyy-mm-dd') ");
		}
		if (!date2.equals("")) {
			hql.append(" and rckh.khsj < to_date('" + date2 + "','yyyy-mm-dd') ");
		}
		hql.append(" order by id ");

		rckhDocList = queryService.searchList(hql.toString());

		sheetList.add(rckhTitleList);
		sheetList.add(rckhDocList);
		sheetMap.put("form_title", sheetList);
		request.setAttribute("ExcelName", "日常考核.xls");
		request.setAttribute("sheetMap", sheetMap);
		return new ModelAndView("/export/toExcelWhithList.do");

	}
}
