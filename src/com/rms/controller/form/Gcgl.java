package com.rms.controller.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.QueryService;
import com.rms.dataObjects.form.Td00_gcxx;
import com.rms.dataObjects.form.Td01_xmxx;

@Controller
public class Gcgl {
	/**
	 * 查询服务
	 */
	@Autowired
	private QueryService queryService;
	
	
	/**
	 *  项目信息列表
	 */
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/form/xmxxList.do")
	public ModelAndView xmxxList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		// 分页
		Integer totalPages = 1;
		Integer totalCount = 0;
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request.getParameter("orderField"), "id");
		if (orderField.equals("")) {
			orderField = "id";
		}
		String orderDirection = convertUtil.toString(request.getParameter("orderDirection"), "desc");
		if (orderDirection.equals("")) {
			orderDirection = "desc";
		}
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		
		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		String user_name=user.getName();
		String user_dept = user.getDept_name();
		
		// 查询条件
		String keyword = convertUtil.toString(request.getParameter("keyword"));
		
		StringBuffer hsql = new StringBuffer();
		hsql.append("select xmxx from Td01_xmxx xmxx where ");
		
		//工程和项目显示条件，【项目管理员=自己 或 施工单位=自己单位 或 监理单位=自己单位 或 设计单位=自己单位】
		hsql.append("(");
		hsql.append("xmgly = '" + user_name + "'");
		hsql.append(" or sgdw = '" + user_dept + "'");
		hsql.append(" or sjdw = '" + user_dept + "'");
		hsql.append(" or jldw = '" + user_dept + "'");
		hsql.append(")");		
		
		// 关键字
		if (!keyword.equals("")) {
			hsql.append(" and (xmmc like '%" + keyword + "%' or xmbh like '%" + keyword + "%')");
		}		
				
		// order排序
		hsql.append(" order by " + orderField);
		hsql.append(" " + orderDirection);
		
		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum, numPerPage);
		
		// 获取结果集
		List<Td01_xmxx> xmxxList = new ArrayList<Td01_xmxx>();
		String limit = "";
		while (ro.next()) {	
			Td01_xmxx td01 = (Td01_xmxx) ro.get("xmxx");
			
			if("".equals(limit)){
				if(user_dept.equals(td01.getSgdw()))
					limit = "sgdw";
				else if(user_dept.equals(td01.getJldw()))
					limit = "jldw";
				else if(user_dept.equals(td01.getSjdw()))
					limit = "sjdw";
				else
					limit = "xmgly";
			}
			
			xmxxList.add(td01);
		}
		modelMap.put("limit", limit);
		modelMap.put("xmxxList", xmxxList);
		
		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		
		return new ModelAndView("/WEB-INF/jsp/form/xmxxList.jsp", modelMap);

	}
	
	/**
	 *  工程信息列表
	 */
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/form/gcxxList.do")
	public ModelAndView gcxxList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		// 分页
		Integer totalPages = 1;
		Integer totalCount = 0;
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request.getParameter("orderField"), "id");
		if (orderField.equals("")) {
			orderField = "id";
		}
		String orderDirection = convertUtil.toString(request.getParameter("orderDirection"), "desc");
		if (orderDirection.equals("")) {
			orderDirection = "desc";
		}
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		
		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		String user_name=user.getName();
		String user_dept = user.getDept_name();
		
		// 查询条件
		String keyword = convertUtil.toString(request.getParameter("keyword"));
		
		StringBuffer hsql = new StringBuffer();
		hsql.append("select gcxx from Td00_gcxx gcxx where ");
		
		//工程和项目显示条件，【项目管理员=自己 或 施工单位=自己单位 或 监理单位=自己单位 或 设计单位=自己单位】
		hsql.append("(");
		hsql.append("xmgly = '" + user_name + "'");
		hsql.append(" or sgdw = '" + user_dept + "'");
		hsql.append(" or sjdw = '" + user_dept + "'");
		hsql.append(" or jldw = '" + user_dept + "'");
		hsql.append(")");		
		
		// 关键字
		if (!keyword.equals("")) {
			hsql.append(" and (gcmc like '%" + keyword + "%' or gcbh like '%" + keyword + "%')");
		}		
				
		// order排序
		hsql.append(" order by " + orderField);
		hsql.append(" " + orderDirection);
		
		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum, numPerPage);
		// 获取结果集
		List<Td00_gcxx> gcxxList = new ArrayList<Td00_gcxx>();
		String limit="";
		while (ro.next()) {
			Td00_gcxx td00 = (Td00_gcxx) ro.get("gcxx");
			if("".equals(limit)){
				if(user_dept.equals(td00.getSgdw()))
					limit = "sgdw";
				else if(user_dept.equals(td00.getJldw()))
					limit = "jldw";
				else if(user_dept.equals(td00.getSjdw()))
					limit = "sjdw";
				else
					limit = "xmgly";
			}
			
			gcxxList.add(td00);
		}
		modelMap.put("limit", limit);
		modelMap.put("gcxxList", gcxxList);
		
		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		
		return new ModelAndView("/WEB-INF/jsp/form/gcxxList.jsp", modelMap);

	}
}
