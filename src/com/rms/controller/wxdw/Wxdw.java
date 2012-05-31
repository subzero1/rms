package com.rms.controller.wxdw;

import java.io.PrintWriter;
import java.util.ArrayList;
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

import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta04_role;
import com.netsky.base.dataObjects.Ta22_user_idea;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.rms.dataObjects.wxdw.Tf01_wxdw;

@Controller
public class Wxdw {
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
	 * 外协单位列表
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/wxdwList.do")
	public ModelAndView wxdwList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		// 权限控制
		Map<String, Ta04_role> rolesMap = (Map<String, Ta04_role>) request.getSession().getAttribute("rolesMap");
		if (rolesMap.containsKey("30101")) {
			modelMap.put("canedit", "yes");
		}
		// 分页
		Integer totalPages = 1;
		Integer totalCount = 0;
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request.getParameter("orderField"), "mc");
		if (orderField.equals("")) {
			orderField = "mc";
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
		String mc = convertUtil.toString(request.getParameter("mc"));
		String lb = convertUtil.toString(request.getParameter("lb"));

		StringBuffer hsql = new StringBuffer();
		hsql.append("select wxdw from Tf01_wxdw wxdw where 1=1");
		// where条件
		// 类别
		if (!lb.equals("")) {
			hsql.append(" and lb='" + lb.substring(0, 2) + "'");
		}
		// 名称
		if (!mc.equals("")) {
			hsql.append(" and mc like '%" + mc + "%'");
		}
		// order排序
		// orderField
		hsql.append(" order by " + orderField);
		// orderDirection
		hsql.append(" " + orderDirection);
		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum, numPerPage);
		// 获取结果集
		List<Tf01_wxdw> wxdwList = new ArrayList<Tf01_wxdw>();
		while (ro.next()) {
			wxdwList.add((Tf01_wxdw) ro.get("wxdw"));
		}
		modelMap.put("wxdwList", wxdwList);
		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		// 页面所需内容
		// 类别
		List<String> lbList = new ArrayList<String>();
		lbList.add("设计单位");
		lbList.add("施工单位");
		lbList.add("监理单位");
		lbList.add("审计单位");
		modelMap.put("lbList", lbList);
		return new ModelAndView("/WEB-INF/jsp/wxdw/wxdwList.jsp", modelMap);
	}

	/**
	 * 外协单位信息
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/wxdwEdit.do")
	public ModelAndView wxdwEdit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView("/WEB-INF/jsp/wxdw/wxdwEdit.jsp", modelMap);
	}

	/**
	 *用户配置列表
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/wxdwUserList.do")
	public ModelAndView wxdwUserList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView("/WEB-INF/jsp/wxdw/wxdwUserList.jsp", modelMap);
	}
	
	/**
	 *用户配置
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/wxdwUserEdit.do")
	public ModelAndView wxdwUserEdit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView("/WEB-INF/jsp/wxdw/wxdwUserEdit.jsp", modelMap);
	}

	/**
	 * 用户配置保存
	 * 系统自动获得账号前缀，自动生成账号和密码，初始密码与账号相同，自动配置用户对应的岗位和所属部门
	 */
	@RequestMapping("/wxdw/ajaxSaveWxdwUser.do")
	public void ajaxSaveWxdwUser(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
	}
	
	/**
	 * 区域专业
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/qyZyEdit.do")
	public ModelAndView qyZyEdit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView("/WEB-INF/jsp/wxdw/qyZyEdit.jsp", modelMap);
	}

	/**
	 * 配置保存
	 * 区域专业\份额占比\最大在建工程数\关联交易额
	 */
	@RequestMapping("/wxdw/ajaxSaveWxdwConfig.do")
	public void ajaxSaveWxdwConfig(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
	}
	
	/**
	 * 份额占比
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/fezbEdit.do")
	public ModelAndView fezbEdit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView("/WEB-INF/jsp/wxdw/fezbEdit.jsp", modelMap);
	}

	/**
	 * 最大在建工程数
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/zjgcsEdit.do")
	public ModelAndView zjgcsEdit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView("/WEB-INF/jsp/wxdw/zjgcsEdit.jsp", modelMap);
	}

	/**
	 * 关联交易额
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/gljyeEdit.do")
	public ModelAndView gljyeEdit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView("/WEB-INF/jsp/wxdw/gljyeEdit.jsp", modelMap);
	}

	/**
	 * 施工队
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/sgdEdit.do")
	public ModelAndView sgdEdit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView("/WEB-INF/jsp/wxdw/sgdEdit.jsp", modelMap);
	}
	
	/**
	 * 保存施工队
	 */
	@RequestMapping("/wxdw/ajaxSaveSgd.do")
	public void ajaxSaveSgd(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
	}
	
	/**
	 * 删除施工队
	 */
	@RequestMapping("/wxdw/ajaxDelSgd.do")
	public void ajaxDelSgd(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
	}
}
