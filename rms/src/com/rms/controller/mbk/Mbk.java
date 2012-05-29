package com.rms.controller.mbk;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"),1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"),20);
		StringBuffer hsql = new StringBuffer();

		int totalPages = 1;
		int totalCount = 1;

		ModelMap modelMap = new ModelMap();

		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);

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

