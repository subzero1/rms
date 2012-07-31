package com.rms.controller.htgl;

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
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.rms.dataObjects.base.Tc04_zyxx;
import com.rms.dataObjects.form.Td01_xmxx;

@Controller
public class Htgl {
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
	 * 待/已签订合同列表
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/htgl/htList.do")
	public ModelAndView htList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		//数据库相关变量
		StringBuffer sql = new StringBuffer("");
		ModelMap modelMap = new ModelMap();
		//Class<?> clazz = null;
		//QueryBuilder queryBuilder = null;
		
		//分页变量
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"),1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"),20);
		Integer totalCount = 0;
		Integer pageNumShown  = 0;
		
		//排序变量
		String orderField = convertUtil.toString(request.getParameter("orderField"),"id");
		String orderDirection = convertUtil.toString(request.getParameter("orderDirection"),"asc");
		
		//查询变量
		String htlb = convertUtil.toString(request.getParameter("htlb"),"sj");
		String keywords = convertUtil.toString(request.getParameter("keywords"),"");
		modelMap.put("htlb", htlb);

		//购建合同类别下拉列表
		List<Object> htlbList = new LinkedList<Object>();
		Properties p = new Properties();
		p.setProperty("show", "设计");
		p.setProperty("value", "sj");
		htlbList.add(p);
		p = new Properties();
		p.setProperty("show", "施工");
		p.setProperty("value", "sg");
		htlbList.add(p);
		p = new Properties();
		p.setProperty("show", "监理");
		p.setProperty("value", "jl");
		htlbList.add(p);
		modelMap.put("htlbList", htlbList);
		
		
		//获得待签合同信息列表
		sql.delete(0, sql.length());
		sql.append("select id,xmbh,xmmc,lxsj");
		sql.append(","+ htlb +"htbh as htbh");
		sql.append(","+ htlb +"htje as htje");
		sql.append(","+ htlb +"htqdrq as htqdrq");
		sql.append(" from Td01_xmxx ");
		sql.append(" where " + htlb +"htbh is null ");
				
		 //关键字
		if(!keywords.equals("")){
			
			sql.append("and (xmbh like '%");
			sql.append(keywords);
			sql.append("%' or xmmc like '%");
			sql.append(keywords);
			sql.append("%') ");
		}
		
		sql.append(" order by ");
		sql.append(orderField);
		sql.append(" ");
		sql.append(orderDirection);
		
		ResultObject ro = queryService.searchByPage(sql.toString(), pageNum, numPerPage);
		totalCount = ro.getTotalRows();
		pageNumShown = ro.getTotalPages();
		
		modelMap.put("totalCount", totalCount);
		modelMap.put("pageNumShown", pageNumShown);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		
		List<Object> htList = new LinkedList<Object>();
		while (ro.next()) {
			Td01_xmxx td01 = new Td01_xmxx();
			td01.setId((Long)ro.get("id"));
			td01.setXmbh((String)ro.get("xmbh"));
			td01.setXmmc((String)ro.get("xmmc"));
			td01.setLxsj((Date)ro.get("lxsj"));
			td01.setHtbh((String)ro.get("htbh"));
			td01.setHtje((Long)ro.get("htje"));
			td01.setHtqdrq((Date)ro.get("htqdrq"));
			htList.add(td01);
		}		
		modelMap.put("htList", htList);
		
		return new ModelAndView("/WEB-INF/jsp/htgl/htList.jsp", modelMap);
	}
	
	/**
	 * 已签合同待送审列表
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/htgl/wssList.do")
	public ModelAndView wssList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		
		return new ModelAndView("/WEB-INF/jsp/htgl/wssList.jsp", modelMap);
		
	}
	

	/**
	 * 待挂账列表
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/htgl/dgzList.do")
	public ModelAndView dgzList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		
		return new ModelAndView("/WEB-INF/jsp/htgl/dgzList.jsp", modelMap);
		
	}
	
	/**
	 * 合同信息编辑\送审信息录入、挂账信息录入
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/htgl/htEdit.do")
	public ModelAndView htEdit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		
		return new ModelAndView("/WEB-INF/jsp/htgl/htEdit.jsp", modelMap);
		
	}
}
