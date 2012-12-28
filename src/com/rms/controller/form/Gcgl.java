package com.rms.controller.form;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.rms.dataObjects.form.Td00_gcxx;
import com.rms.dataObjects.form.Td01_xmxx;
import com.rms.dataObjects.form.Td06_xqs;

@Controller
public class Gcgl {
	/**
	 * 查询服务
	 */
	@Autowired
	private QueryService queryService;
	
	@Autowired
	private SaveService saveService;
	
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
		hsql.append(" or xmjl = '" + user_name + "'");
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
		Long node_id = -1L;
		while (ro.next()) {	
			Td01_xmxx td01 = (Td01_xmxx) ro.get("xmxx");
			
			if("".equals(limit)){
				if(user_dept.equals(td01.getSgdw())){
					limit = "sgdw";
					node_id = 10103L;
				}
				else if(user_dept.equals(td01.getJldw())){
					limit = "jldw";
					node_id = 10104L;
				}
				else if(user_dept.equals(td01.getSjdw())){
					limit = "sjdw";
					node_id = 10102L;
				}
				else{
					limit = "xmgly";
					node_id = 10101L;
				}
			}
			
			xmxxList.add(td01);
		}
		
		modelMap.put("node_id", node_id);
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
		Long node_id = -1L;
		while (ro.next()) {
			Td00_gcxx td00 = (Td00_gcxx) ro.get("gcxx");
			if("".equals(limit)){
				if(user_dept.equals(td00.getSgdw())){
					limit = "sgdw";
					node_id = 10203L;
				}
				else if(user_dept.equals(td00.getJldw())){
					limit = "jldw";
					node_id = 10204L;
				}
				else if(user_dept.equals(td00.getSjdw())){
					limit = "sjdw";
					node_id = 10202L;
				}
				else{
					limit = "xmgly";
					node_id = 10201L;
				}
			}
			
			gcxxList.add(td00);
		}
		modelMap.put("node_id", node_id);
		modelMap.put("limit", limit);
		modelMap.put("gcxxList", gcxxList);
		
		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		
		return new ModelAndView("/WEB-INF/jsp/form/gcxxList.jsp", modelMap);

	}
	
	@RequestMapping("/form/ajaxGcxxDel.do")
	public void ajaxGcxxDel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = convertUtil.toLong(request.getParameter("id"));
		PrintWriter out = null;

		response.setContentType("text/html;charset=UTF-8");
		Session session = saveService.getHiberbateSession();
		Transaction tx = session.beginTransaction();
		tx.begin();
		// 获取用户对象
		try {
			out = response.getWriter();
			session.createQuery("delete from Td00_gcxx where id=" + id).executeUpdate();
			session.createQuery("update Td00_gcxx set glgc_id = null where glgc_id = " + id).executeUpdate();
			out.print("{\"statusCode\":\"200\", \"message\":\"删除成功\", \"callbackType\":\"forward\"}");
			session.flush();
			tx.commit();
		} catch (IOException e) {
			tx.rollback();
			out.print("{\"statusCode\":\"300\", \"message\":\"删除失败\"}");
		} finally {
			session.close();
		}
	}
	
	@RequestMapping("/form/ajaxXmxxDel.do")
	public void ajaxXmxxDel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = convertUtil.toLong(request.getParameter("id"));
		PrintWriter out = null;

		response.setContentType("text/html;charset=UTF-8");
		Session session = saveService.getHiberbateSession();
		Transaction tx = session.beginTransaction();
		tx.begin();
		// 获取用户对象
		try {
			out = response.getWriter();
			session.createQuery("delete from Td01_xmxx where id=" + id).executeUpdate();
			session.createQuery("update Td00_gcxx set xm_id = null where xm_id = " + id).executeUpdate();
			out.print("{\"statusCode\":\"200\", \"message\":\"删除成功\", \"callbackType\":\"forward\"}");
			session.flush();
			tx.commit();
		} catch (IOException e) {
			tx.rollback();
			out.print("{\"statusCode\":\"300\", \"message\":\"删除失败\"}");
		} finally {
			session.close();
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/form/xqxxList.do")
	public ModelAndView xqxxList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
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
		
		// 查询条件
		String keyword = convertUtil.toString(request.getParameter("keyword"));
		
		StringBuffer hsql = new StringBuffer();
		hsql.append("select xqxx from Td06_xqs ta06 gcxx where ");
		hsql.append(" exists (select 'x' from Tb15_docflow tb15 where module_id = 109 and user_id = ");
		hsql.append(user.getId());
		hsql.append(" and ta06.project_id = tb15.project_id ");	
		
		// 关键字
		if (!keyword.equals("")) {
			hsql.append(" and (xqmc like '%" + keyword + "%')");
		}		
				
		// order排序
		hsql.append(" order by " + orderField);
		hsql.append(" " + orderDirection);
		
		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum, numPerPage);
		// 获取结果集
		List<Td06_xqs> xqxxList = new ArrayList<Td06_xqs>();
		while (ro.next()) {
			Td06_xqs td06 = (Td06_xqs) ro.get("xqxx");
			xqxxList.add(td06);
		}
		modelMap.put("xqxxList", xqxxList);
		
		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		
		return new ModelAndView("/WEB-INF/jsp/form/gcxxList.jsp", modelMap);

	}
}
