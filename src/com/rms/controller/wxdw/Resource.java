package com.rms.controller.wxdw;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.utils.convertUtil;
import com.netsky.base.service.QueryService;
import com.rms.dataObjects.wxdw.Tf31_zytl;

/**
 * @description:
 * 
 * @class name:com.rms.controller.infoManage.Resource
 * @author net Jan 29, 2013
 */
@Controller
public class Resource {

	@Autowired
	private QueryService queryService;

	@RequestMapping("/infoManage/zytlEdit.do")
	public ModelAndView zytlEdit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String view = "/WEB-INF/jsp/infoManage/zytlEdit.jsp";
		ModelMap modelMap = new ModelMap();
		Tf31_zytl tf31_zytl = null;
		Long zytl_id = convertUtil.toLong(request.getParameter("zytl_id"));

		if (zytl_id != -1) {
			tf31_zytl = (Tf31_zytl) queryService.searchById(Tf31_zytl.class,
					zytl_id);
		}
		modelMap.put("Tf31_zytl", tf31_zytl);
		return new ModelAndView(view, modelMap);
	}

	@RequestMapping("/infoManage/zytlrList.do")
	public ModelAndView zytlrList(HttpServletRequest request,
			HttpServletResponse response) {
		String view = "/WEB-INF/jsp/infoManage/zytlrList.jsp";
		ModelMap modelMap = new ModelMap();
		StringBuffer hql = new StringBuffer();
		Integer pageNum = convertUtil.toInteger(
				request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request
				.getParameter("orderField"), "tlrxm");
		String orderDirection = convertUtil.toString(request
				.getParameter("orderDirection"), "asc");
		String keyword = convertUtil.toString(request.getParameter("keyword"));
		Integer totalCount = 0;
		List<Tf31_zytl> tf31_zytlrList = null;

		hql.append("select t from Tf31_zytl t where 1=1 ");
		if (keyword != "") {
			hql.append(" and t.tlrxm like '%");
			hql.append(keyword);
			hql.append("%' ");
		}
		hql.append("order by t.");
		hql.append(orderField);
		hql.append(" ");
		hql.append(orderDirection);

		ResultObject ro = queryService.searchByPage(hql.toString(), pageNum,
				numPerPage);
		if (ro != null) {
			totalCount = ro.getTotalRows();
			tf31_zytlrList = new LinkedList<Tf31_zytl>();
			while (ro.next()) {
				tf31_zytlrList.add((Tf31_zytl) ro.get("t"));
			}
		}
		modelMap.put("Tf31_zytlList", tf31_zytlrList);
		modelMap.put("keywork", keyword);
		modelMap.put("totalCount", totalCount);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("pageNum", pageNum);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		return new ModelAndView(view, modelMap);
	}

	@RequestMapping("/infoManage/tlrAjaxDel.do")
	public void tlrAjaxDel(HttpServletRequest request,
			HttpServletResponse response) {

	}

	@RequestMapping("/infoManage/tlrAjaxDel.do")
	public ModelAndView tlrExcelImport(HttpServletRequest request,
			HttpServletResponse response) {
		String view = "";
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(view, modelMap);
	}

	@RequestMapping("/infoManage/selectWxdw.do")
	public ModelAndView selectWxdw(HttpServletRequest request,
			HttpServletResponse response) {
		String view = "/WEB-INF/jsp/wxdw/baseBringBack.jsp";
		ModelMap modelMap = new ModelMap();
		StringBuffer hql = new StringBuffer();
		Integer pageNum = convertUtil.toInteger(
				request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request
				.getParameter("orderField"), "mc");
		String orderDirection = convertUtil.toString(request
				.getParameter("orderDirection"), "asc");
		String keyword = convertUtil.toString(request.getParameter("keyword"));
		Integer totalCount = 0;

		List objList = new ArrayList();
		hql.append("select t.id,t.mc from Tf01_wxdw t where 1=1 ");
		hql.append("and t.lb='施工' ");
		if (!keyword.equals("")) {
			hql.append(" and t.mc like '%");
			hql.append(keyword);
			hql.append("%' ");
		}
		hql.append("order by t.");
		hql.append(orderField);
		hql.append(" ");
		hql.append(orderDirection);
		ResultObject ro = queryService.searchByPage(hql.toString(), pageNum,
				numPerPage);

		if (ro != null) {
			objList = ro.getList();
			totalCount=ro.getTotalRows();
		}

		modelMap.put("objList", objList);
		modelMap.put("keywork", keyword);
		modelMap.put("totalCount", totalCount);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("pageNum", pageNum);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		return new ModelAndView(view, modelMap);
	}
}
