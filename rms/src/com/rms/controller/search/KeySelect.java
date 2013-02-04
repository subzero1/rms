package com.rms.controller.search;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.PropertyInject;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.utils.convertUtil;
import com.rms.controller.search.KeySelect;
import com.rms.dataObjects.base.Tc06_tzqk;
import com.rms.dataObjects.base.Tc07_qkxx;
import com.rms.dataObjects.base.Tc03_gczy;
import com.rms.dataObjects.base.Tc04_zyxx;
/**
 * 工程查询与报表各类选择处理类
 * 
 * @author lee wang
 * 
 */
@Controller
public class KeySelect {

	@Autowired
	private QueryService queryService;

	@Autowired
	private ExceptionService exceptionService;

	/**
	 * @return the queryService
	 */
	public QueryService getQueryService() {
		return queryService;
	}

	/**
	 * @param queryService
	 *            the queryService to set
	 */
	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}

	/**
	 * @return the exceptionService
	 */
	public ExceptionService getExceptionService() {
		return exceptionService;
	}

	/**
	 * @param exceptionService
	 *            the exceptionService to set
	 */
	public void setExceptionService(ExceptionService exceptionService) {
		this.exceptionService = exceptionService;
	}


	@RequestMapping("/search/selectZy.do")
	public ModelAndView selectZy(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<String> result = new ArrayList<String>();
			String HSql = "select distinct yxnd as yxnd from Tc03_gczy order by yxnd desc";

			ResultObject ro = queryService.search(HSql);
			while (ro.next()) {
				result.add(ro.get("yxnd") + "");
			}
			request.setAttribute("result", result);
		} catch (Exception e) {
			return exceptionService.exceptionControl(KeySelect.class.getName(), "专业多选项", e);
		}
		return new ModelAndView("/WEB-INF/jsp/search/selectZy.jsp");
	}

	@RequestMapping("/search/getGczy.do")
	public void getGczy(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("GBK");
		Tc03_gczy tc03;
		Long zynd = Long.valueOf(request.getParameter("zynd"));
		QueryBuilder queryBuilder = new HibernateQueryBuilder(Tc03_gczy.class);
		queryBuilder.eq("yxnd", zynd);
		ResultObject ro = queryService.search(queryBuilder);
		response.setCharacterEncoding("GBK");
		response.setContentType("text/xml");
		response.getWriter().print("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		response.getWriter().print("<root>");
		while (ro.next()) {
			tc03 = (Tc03_gczy) ro.get(Tc03_gczy.class.getName());
			response.getWriter().print("<zydl>");
			response.getWriter().print("<id>");
			response.getWriter().print(tc03.getId());
			response.getWriter().print("</id>");
			response.getWriter().print("<mc>");
			response.getWriter().print(tc03.getZymc());
			response.getWriter().print("</mc>");
			response.getWriter().print("</zydl>");
		}
		response.getWriter().print("</root>");
	}

	@RequestMapping("/search/getZyxx.do")
	public void getZyxx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("GBK");
		response.setCharacterEncoding("GBK");
		response.setContentType("text/xml");
		response.getWriter().print("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		response.getWriter().print("<root>");
		
		String zydl = convertUtil.toString(request.getParameter("dl"), "-1");
		
		String HSql = "select tc04 from Tc04_zyxx tc04 where tc04.gczy_id = " + zydl ;
		ResultObject ro = queryService.search(HSql);
		while (ro.next()) {
			Tc04_zyxx tc04 = (Tc04_zyxx) ro.get("tc04");
			response.getWriter().print("<zyxx>");
			response.getWriter().print(tc04.getMc());
			response.getWriter().print("</zyxx>");
		}
		response.getWriter().print("</root>");
	}

	@RequestMapping("/search/selectQk.do")
	public ModelAndView selectQk(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<String> result = new ArrayList<String>();
			String HSql = "select distinct nd as nd from Tc06_tzqk order by nd desc";

			ResultObject ro = queryService.search(HSql);
			while (ro.next()) {
				result.add(ro.get("nd") + "");
			}
			request.setAttribute("result", result);
		} catch (Exception e) {
			return exceptionService.exceptionControl(KeySelect.class.getName(), "切块多选项", e);
		}
		return new ModelAndView("/WEB-INF/jsp/search/selectQk.jsp");
	}

	@RequestMapping("/search/getTzqk.do")
	public void getTzqk(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("GBK");
		Tc06_tzqk tc06;
		Long nd = Long.valueOf(request.getParameter("qknd"));
		QueryBuilder queryBuilder = new HibernateQueryBuilder(Tc06_tzqk.class);
		queryBuilder.eq("nd", nd);
		ResultObject ro = queryService.search(queryBuilder);
		response.setCharacterEncoding("GBK");
		response.setContentType("text/xml");
		response.getWriter().print("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		response.getWriter().print("<root>");
		while (ro.next()) {
			tc06 = (Tc06_tzqk) ro.get(Tc06_tzqk.class.getName());
			response.getWriter().print("<qkdl>");
			response.getWriter().print("<id>");
			response.getWriter().print(tc06.getId());
			response.getWriter().print("</id>");
			response.getWriter().print("<name>");
			response.getWriter().print(tc06.getQkmc());
			response.getWriter().print("</name>");
			response.getWriter().print("</qkdl>");
		}
		response.getWriter().print("</root>");
	}

	@RequestMapping("/search/getQkxx.do")
	public void getQkxx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("GBK");
		response.setCharacterEncoding("GBK");
		response.setContentType("text/xml");
		response.getWriter().print("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		response.getWriter().print("<root>");
		String qkdl = convertUtil.toString(request.getParameter("dl"), "-1");
		
		String HSql = "select tc07 from Tc07_qkxx tc07 where tc07.qk_id = " + qkdl;
		ResultObject ro = queryService.search(HSql);
		while (ro.next()) {
			Tc07_qkxx tc07 = (Tc07_qkxx) ro.get("tc07");
			response.getWriter().print("<qkxx>");
			response.getWriter().print(tc07.getMc());
			response.getWriter().print("</qkxx>");
		}
		response.getWriter().print("</root>");
	}
	
	@RequestMapping("/search/keySelect.do")
	public ModelAndView keySelect(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<String> result = new ArrayList<String>();
			String type = convertUtil.toString(request.getParameter("type"));
			if (type.equals("gclb")) {
				/*
				 * 工程类别
				 */
				String HSql = "select tc01 from Tc01_property tc01 where type='工程类别' order by id";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add(PropertyInject.getProperty(ro.get("tc01"), "name") + "");
				}
			}
			else if(type.equals("ssdq")){
				/*
				 * 所属区域
				 */
				String HSql = "select tc02 from Tc02_area tc02 order by id ";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add(PropertyInject.getProperty(ro.get("tc02"), "name") + "");
				}
			}
			else if(type.equals("dept")){
				/*
				 *部门
				 */
				String HSql = "select ta01 from Ta01_dept ta01 order by id ";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add(PropertyInject.getProperty(ro.get("ta01"), "name") + "");
				}
			}
			
			else if(type.equals("yslx")){
				/*
				 * 预算类型
				 */
				String HSql = "select tc01 from Tc01_property tc01 where type='预算类型' order by id";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add(PropertyInject.getProperty(ro.get("tc01"), "name") + "");
				}
			}
			else if(type.equals("sgdw")){
				/*
				 * 施工单位
				 */
				String HSql = "select mc from Tf01_wxdw where lb = '施工' ";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add((String)ro.get("mc"));
				}
			}
			else if(type.equals("sjdw")){
				/*
				 * 设计单位
				 */
				String HSql = "select mc from Tf01_wxdw where lb = '设计' ";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add((String)ro.get("mc"));
				}
			}
			else if(type.equals("jldw")){
				/*
				 * 监理单位
				 */
				String HSql = "select mc from Tf01_wxdw where lb = '监理' ";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add((String)ro.get("mc"));
				}
			}
			else if(type.equals("gcjd")){
				/*
				 * 工程状态
				 */
				String HSql = "select distinct gcjd as gcjd from Td00_gcxx ";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add((String)ro.get("gcjd"));
				}
			}
			else if(type.equals("gczt")){
				/*
				 * 工程状态
				 */
				String HSql = "select distinct gczt as gczt from Td00_gcxx ";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add((String)ro.get("gczt"));
				}
			}else if (type.equals("jsxz")) {//建设性质
				String HSql = "select name from Tc01_property tc01 where type='建设性质'";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add((String)ro.get("name"));
				}
			}else if (type.equals("lb")) {//类别
				String HSql = "select name from Tc01_property tc01 where type='目标库类别'";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add((String)ro.get("name"));
				}
			}
			else if (type.equals("jsfs")) {//建设方式
				String HSql = "select name from Tc01_property tc01 where type='建设方式'";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add((String)ro.get("name"));
				}
			}else if (type.equals("zt")) {//目标库状态
				String HSql = "select name from Tc01_property tc01 where type='目标库状态'";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add((String)ro.get("name"));
				}
			}else if (type.equals("jsdj")) {//建设等级
					result.add("A");
					result.add("B");
					result.add("C");
			}
			else if (type.equals("zyyszt")) {//资源验收状态
				result.add("通过");
				result.add("回退");
			}
			else if (type.equals("ycystg")) {//一次资源验收通过
				result.add("是");
				result.add("否");
			}
			request.setAttribute("result", result);
		} catch (Exception e) {
			return exceptionService.exceptionControl(KeySelect.class.getName(), "选择基础多选项", e);
		}
		return new ModelAndView("/WEB-INF/jsp/search/keySelect.jsp");
	}
}

