package com.rms.controller.wxdwkh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.rms.dataObjects.wxdw.Tf01_wxdw;

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
	 * 日常考核
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/wxdwkh/rckhList.do")
	public ModelAndView rckhList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
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
}
