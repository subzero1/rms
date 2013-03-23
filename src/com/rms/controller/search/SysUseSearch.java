package com.rms.controller.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rms.dataObjects.base.Tc02_area;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta01_dept;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.export.ExportExcel;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.utils.StringFormatUtil;
/**
 * 用户查询
 * @author CT
 * @create 2010-03-17
 */
@Controller
public class SysUseSearch {

	/**
	 * 查询服务
	 */
	@Autowired
	private QueryService queryService;
	

	@Autowired
	private ExceptionService exceptionService;
	
	@RequestMapping("/search/xmglyDownAndTimeout.do")
	public ModelAndView xmglyDownAndTimeout(HttpServletRequest request,
			HttpServletResponse response,HttpSession session) throws Exception {
		
		Ta03_user user = null;
		user = (Ta03_user) session.getAttribute("user");
		if (user == null) {
			return exceptionService.exceptionControl(this.getClass().getName(), "用户未登录或登录超时", new Exception("用户未登录"));
		}
		String lxsj1 = convertUtil.toString(request.getParameter("lxsj1"),"");
		String lxsj2 = convertUtil.toString(request.getParameter("lxsj2"),"");
		String pdsj1 = convertUtil.toString(request.getParameter("pdsj1"),"");
		String pdsj2 = convertUtil.toString(request.getParameter("pdsj2"),"");
		String dwlb = convertUtil.toString(request.getParameter("dwlb"),"sg");
		
		return null;
		//return new ModelAndView(returnurl);
	}
	
	@RequestMapping("/search/wxdwReceiveAndTimeout.do")
	public ModelAndView wxdwReceiveAndTimeout(HttpServletRequest request,
			HttpServletResponse response,HttpSession session) throws Exception {
		return null;
	}
	
	@RequestMapping("/search/userLogin.do")
	public ModelAndView userLogin(HttpServletRequest request,
			HttpServletResponse response,HttpSession session) throws Exception {
			return null;
	}
	
	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}
	

}
