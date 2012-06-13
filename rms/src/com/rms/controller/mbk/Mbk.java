package com.rms.controller.mbk;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.rms.dataObjects.mbk.Td21_mbk;
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
			if (zt.equals("空")){
				hsql.append(" and (zt is null or zt='')");
			}
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
		List<String> lbList = (List<String>)queryService.searchList("select name from Tc01_property where type='目标库类别'");
		modelMap.put("lbList", lbList);
		List<String> dqList = (List<String>)queryService.searchList("select name from Tc02_area");
		modelMap.put("dqList", dqList);
		List<String> ztList = (List<String>)queryService.searchList("select name from Tc01_property where type='目标库状态'");
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
		return new ModelAndView("/WEB-INF/jsp/mbk/mbkEdit.jsp", modelMap);
	}

}

