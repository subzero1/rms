package com.rms.controller.infoManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.utils.convertUtil;
import com.rms.dataObjects.base.Tc04_zyxx;
import com.rms.dataObjects.base.Tc03_gczy;


/**
 * @description
 * 
 * @class com.rms.controller.infoManage.Zywh
 * 
 * @author
 */
@Controller
public class Zywh {
	/**
	 * 数据服务
	 */
	@Autowired
	private Dao dao;

	@Autowired
	private ExceptionService exceptionService;

	/**
	 * 查询服务
	 */
	@Autowired
	private QueryService queryService;

	/**
	 * 日志处理类
	 */
	private Logger log = Logger.getLogger(this.getClass());
	
	
	/**
	 * 专业维护列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/infoManage/zywhList.do")
	public ModelAndView ZywhList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		Integer year = convertUtil.toInteger(request.getParameter("year"));
		year = year < 1900 ? Calendar.getInstance().get(Calendar.YEAR) : year;
		QueryBuilder queryBuilder = new HibernateQueryBuilder(Tc03_gczy.class);
		if(request.getParameter("searchStr") != null && request.getParameter("searchStr").length() > 0){
			queryBuilder.like("zymc", request.getParameter("searchStr"), MatchMode.ANYWHERE);
		}
		queryBuilder.eq("yxnd", new Long(year.intValue()));
		List<?> list = queryService.searchList(queryBuilder);
		request.setAttribute("gczy_list", list);
		request.setAttribute("curYear", Calendar.getInstance().get(Calendar.YEAR));
		request.setAttribute("year", year);		
		
		return new ModelAndView("/WEB-INF/jsp/infoManage/zywhList.jsp");
	}

	/**
	 * 通过ID获取专业信息及专业细项
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/infoManage/zywhEdit.do")
	public ModelAndView zywhEdit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Long id = convertUtil.toLong(request.getParameter("id"), -1L);
		Tc03_gczy tc03 = (Tc03_gczy) queryService.searchById(Tc03_gczy.class, id);
			if (tc03 != null) {
				
				List<?> zyxx_list;
				QueryBuilder queryBuilder = new HibernateQueryBuilder(Tc04_zyxx.class);
				queryBuilder.eq("gczy_id", id);
				queryBuilder.addOrderBy(Order.asc("id"));
				zyxx_list = queryService.searchList(queryBuilder);
				request.setAttribute("zyxx_list", zyxx_list);
				
				request.setAttribute("tc03", tc03);
			} 

		return new ModelAndView("/WEB-INF/jsp/infoManage/zywhEdit.jsp");
	}

	/**
	 * 删除专业ajax实现
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/infoManage/ajaxDelZydl.do")
	public void ajaxDelZydl(HttpServletRequest request,	HttpServletResponse response) {
		response.setCharacterEncoding(request.getCharacterEncoding());
		PrintWriter out = null;
		response.setContentType("text/xml");

		Long id = convertUtil.toLong(request.getParameter("id"), -1L);
		String act = convertUtil.toString(request.getParameter("act"));
		//System.out.println(act + ":act");

		ModelMap modelMap = new ModelMap();

		// 获取对象
		try {
			out = response.getWriter();
			dao.removeObject(Tc03_gczy.class, id);
			out
					.print("{\"statusCode\":\"200\", \"message\":\"专业删除成功\", \"navTabId\":\"\", \"forwardUrl\":\"infoManage/zywhList.do\", \"callbackType\":\"forward\"}");
		} catch (IOException e) {
			exceptionService.exceptionControl(
					"com.crht.controller.sysManage.User", "专业删除失败", e);
		}
	}
	
}
