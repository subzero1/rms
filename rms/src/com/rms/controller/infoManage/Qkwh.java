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
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.convertUtil;
import com.rms.dataObjects.base.Tc07_qkxx;
import com.rms.dataObjects.base.Tc06_tzqk;


/**
 * @description
 * 
 * @class com.rms.controller.infoManage.Qkwh
 * 
 * @author
 */
@Controller
public class Qkwh {
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
	 * 保存服务
	 */
	@Autowired
	private SaveService saveService;

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
	@RequestMapping("/infoManage/qkwhList.do")
	public ModelAndView qkwhList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		Integer year = convertUtil.toInteger(request.getParameter("year"));
		year = year < 1900 ? Calendar.getInstance().get(Calendar.YEAR) : year;
		QueryBuilder queryBuilder = new HibernateQueryBuilder(Tc06_tzqk.class);
		if(request.getParameter("searchStr") != null && request.getParameter("searchStr").length() > 0){
			queryBuilder.like("qkmc", request.getParameter("searchStr"), MatchMode.ANYWHERE);
		}
		queryBuilder.eq("nd", new Long(year.intValue()));
		List<?> list = queryService.searchList(queryBuilder);
		request.setAttribute("tzqk_list", list);
		request.setAttribute("curYear", Calendar.getInstance().get(Calendar.YEAR));
		request.setAttribute("year", year);		
		
		return new ModelAndView("/WEB-INF/jsp/infoManage/qkwhList.jsp");
	}

	/**
	 * 通过ID获取切块信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/infoManage/qkwhEdit.do")
	public ModelAndView qkwhEdit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Long id = convertUtil.toLong(request.getParameter("id"), -1L);
		Tc06_tzqk tc06 = (Tc06_tzqk) queryService.searchById(Tc06_tzqk.class, id);
			if (tc06 != null) {
				
				List<?> qkxx_list;
				QueryBuilder queryBuilder = new HibernateQueryBuilder(Tc07_qkxx.class);
				queryBuilder.eq("qk_id", id);
				queryBuilder.addOrderBy(Order.asc("id"));
				qkxx_list = queryService.searchList(queryBuilder);
				request.setAttribute("qkxx_list", qkxx_list);
				
				request.setAttribute("tc06", tc06);
			} 

		return new ModelAndView("/WEB-INF/jsp/infoManage/qkwhEdit.jsp");
	}

	/**
	 * 删除切块信息ajax实现
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/infoManage/ajaxDelQkdl.do")
	public void ajaxDelQkdl(HttpServletRequest request,	HttpServletResponse response) {
		response.setCharacterEncoding(request.getCharacterEncoding());
		PrintWriter out = null;
		response.setContentType("text/xml");

		Long id = convertUtil.toLong(request.getParameter("id"), -1L);


		// 获取对象
		try {
			out = response.getWriter();
			
			String HSql = "delete from Tc07_qkxx where qk_id =" + id;
			saveService.updateByHSql(HSql);
			HSql = "delete from Tc06_tzqk where id =" + id;
			saveService.updateByHSql(HSql);
			
			out
					.print("{\"statusCode\":\"200\", \"message\":\"删除成功\", \"navTabId\":\"\", \"forwardUrl\":\"infoManage/qkwhList.do\", \"callbackType\":\"forward\"}");
		} catch (IOException e) {
			exceptionService.exceptionControl(
					"com.crht.controller.sysManage.User", "删除失败", e);
		}
	}
	
}
