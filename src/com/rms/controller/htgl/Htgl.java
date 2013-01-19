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
import com.rms.dataObjects.form.Td01_xmxx;
import com.netsky.base.dataObjects.Ta03_user;

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
		
		Ta03_user user = (Ta03_user)session.getAttribute("user");
		
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
		modelMap.put("htlbmc", getHtlbmc(htlb));

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
		sql.append(" from Td01_xmxx td01");
		sql.append(" where xmgly = '"+user.getName()+"' ");
		//sql.append(" where xmgly = '"+user.getName()+"' and " + htlb +"htbh is null ");
				
		 //关键字
		if(!keywords.equals("")){
			
			sql.append(" and (xmbh like '%");
			sql.append(keywords);
			sql.append("%' or xmmc like '%");
			sql.append(keywords);
			sql.append("%') ");
		}
		
		 //排序
		if(!orderField.equals("")){
			sql.append(" order by ");
			sql.append(orderField);
			sql.append(" ");
			sql.append(orderDirection);
		}
		
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
			Td01_xmxx td01 = (Td01_xmxx)ro.get("td01");
			if("sj".equals(htlb)){	//设计			
				td01.setSjhtbh(td01.getSjhtbh());
				td01.setSjhtje(td01.getSjhtje());
				td01.setSjhtqdrq(td01.getSjhtqdrq());
				
			}else if("sg".equals(htlb)){	//施工			
				td01.setSghtbh(td01.getSghtbh());
				td01.setSghtje(td01.getSghtje());
				td01.setSghtqdrq(td01.getSghtqdrq());
							
			}else if("jl".equals(htlb)){	//监理			
				td01.setJlhtbh(td01.getJlhtbh());
				td01.setJlhtje(td01.getJlhtje());
				td01.setJlhtqdrq(td01.getJlhtqdrq());
						
			}
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
		//数据库相关变量
		StringBuffer sql = new StringBuffer("");
		ModelMap modelMap = new ModelMap();
		Ta03_user user = (Ta03_user)session.getAttribute("user");
		
		//分页变量
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"),1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"),20);
		Integer totalCount = 0;
		Integer pageNumShown  = 0;
		
		//排序变量
		String orderField = convertUtil.toString(request.getParameter("orderField"),"id");
		String orderDirection = convertUtil.toString(request.getParameter("orderDirection"),"asc");
		
		//查询变量
		String keywords = convertUtil.toString(request.getParameter("keywords"),"");
		
		//获得待送审项目列表，施工合同已签
		sql.delete(0, sql.length());
		sql.append(" from Td01_xmxx td01");
		sql.append(" where xmgly = '"+user.getName()+"' and sghtbh is not null ");
		sql.append(" and ssrq is null");
				
		 //关键字
		if(!keywords.equals("")){
			
			sql.append(" and (xmbh like '%");
			sql.append(keywords);
			sql.append("%' or xmmc like '%");
			sql.append(keywords);
			sql.append("%') ");
		}
		
		 //排序
		if(!orderField.equals("")){
			sql.append(" order by ");
			sql.append(orderField);
			sql.append(" ");
			sql.append(orderDirection);
		}
		
		ResultObject ro = queryService.searchByPage(sql.toString(), pageNum, numPerPage);
		totalCount = ro.getTotalRows();
		pageNumShown = ro.getTotalPages();
		
		modelMap.put("totalCount", totalCount);
		modelMap.put("pageNumShown", pageNumShown);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		
		List<Object> wssList = new LinkedList<Object>();
		while (ro.next()) {
			Td01_xmxx td01 = (Td01_xmxx)ro.get("td01");
			wssList.add(td01);
		}		
		modelMap.put("wssList", wssList);
		
		return new ModelAndView("/WEB-INF/jsp/htgl/wssList.jsp", modelMap);
		
	}
	
	/**
	 * 待挂账列表
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/htgl/dgzList.do")
	public ModelAndView dgzList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		//数据库相关变量
		StringBuffer sql = new StringBuffer("");
		ModelMap modelMap = new ModelMap();
		
		//分页变量
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"),1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"),20);
		Integer totalCount = 0;
		Integer pageNumShown  = 0;
		Ta03_user user = (Ta03_user)session.getAttribute("user");
		
		//排序变量
		String orderField = convertUtil.toString(request.getParameter("orderField"),"id");
		String orderDirection = convertUtil.toString(request.getParameter("orderDirection"),"asc");
		
		//查询变量
		String keywords = convertUtil.toString(request.getParameter("keywords"),"");
		
		//获得待挂账信息列表
		sql.delete(0, sql.length());
		sql.append(" from Td01_xmxx td01");
		sql.append(" where xmgly = '"+user.getName()+"' and ssrq is not null");
		sql.append(" and gzrq is null");
				
		 //关键字
		if(!keywords.equals("")){
			
			sql.append(" and (xmbh like '%");
			sql.append(keywords);
			sql.append("%' or xmmc like '%");
			sql.append(keywords);
			sql.append("%') ");
		}
		
		 //排序
		if(!orderField.equals("")){
			sql.append(" order by ");
			sql.append(orderField);
			sql.append(" ");
			sql.append(orderDirection);
		}
		
		ResultObject ro = queryService.searchByPage(sql.toString(), pageNum, numPerPage);
		totalCount = ro.getTotalRows();
		pageNumShown = ro.getTotalPages();
		
		modelMap.put("totalCount", totalCount);
		modelMap.put("pageNumShown", pageNumShown);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		
		List<Object> dgzList = new LinkedList<Object>();
		while (ro.next()) {
			Td01_xmxx td01 = (Td01_xmxx)ro.get("td01");
			dgzList.add(td01);
		}		
		modelMap.put("dgzList", dgzList);
		
		return new ModelAndView("/WEB-INF/jsp/htgl/dgzList.jsp", modelMap);
		
	}
	
	/**
	 * 合同信息编辑\送审信息录入\挂账信息录入
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/htgl/htEdit.do")
	public ModelAndView htEdit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		Long id = convertUtil.toLong(request.getParameter("xm_id"));
		String htlb = convertUtil.toString(request.getParameter("htlb"));
		Td01_xmxx td01 = (Td01_xmxx)queryService.searchById(Td01_xmxx.class, id);
		
		if("sj".equals(htlb)){	//设计			
			td01.setSjhtbh(td01.getSjhtbh());
			td01.setSjhtje(td01.getSjhtje());
			td01.setSjhtqdrq(td01.getSjhtqdrq());
			
		}else if("sg".equals(htlb)){	//施工			
			td01.setSghtbh(td01.getSghtbh());
			td01.setSghtje(td01.getSghtje());
			td01.setSghtqdrq(td01.getSghtqdrq());
						
		}else if("jl".equals(htlb)){	//监理			
			td01.setJlhtbh(td01.getJlhtbh());
			td01.setJlhtje(td01.getJlhtje());
			td01.setJlhtqdrq(td01.getJlhtqdrq());
					
		}
		modelMap.put("td01", td01);
		modelMap.put("htlb", htlb);
		modelMap.put("htlbmc", getHtlbmc(htlb));
		
		return new ModelAndView("/WEB-INF/jsp/htgl/htEdit.jsp", modelMap);
		
	}
	
	private String getHtlbmc(String htlb){
		if("sj".equals(htlb)){
			return "设计";	
		}else if("sg".equals(htlb)){
			return "施工";				
		}else if("jl".equals(htlb)){
			return "监理";			
		}else{
			return "";	
		}
	}
}
