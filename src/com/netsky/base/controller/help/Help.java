package com.netsky.base.controller.help;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.LinkedList;
import java.util.Vector;
import java.util.Properties;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.DateGetUtil;
import com.netsky.base.utils.StringFormatUtil;
import com.netsky.base.utils.convertUtil;
import com.netsky.base.utils.RegExp;
import com.netsky.base.controller.OperFile;
import com.netsky.base.dataObjects.Tz06_help;
import com.netsky.base.dataObjects.Ta03_user;

/**
 * @description: 知识库管理
 * @class name:com.netsky.base.controller.help.Help
 * @author lee.xiangyu Sep. 2, 2012
 */
@Controller
public class Help  {

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
	 * 查询服务
	 */
	@Autowired
	private SaveService saveService;


	/**
	 * 知识库列表
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/help/helpList.do")
	public ModelAndView repositoryList(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		// 分页变量
		Integer pageNum = convertUtil.toInteger(
				request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 20);
		Integer totalCount = 0;
		Integer pageNumShown = 0;

		// 排序变量
		String orderField = convertUtil.toString(request.getParameter("orderField"), "id");
		String orderType = convertUtil.toString(request.getParameter("orderDirection"), "asc");

		// 查询变量
		String keywords = convertUtil.toString(request.getParameter("keywords"), "");

		// 数据库相关变量
		StringBuffer sql = new StringBuffer("");
		ModelMap modelMap = new ModelMap();

		// 获得在线帮助列表
		sql.delete(0, sql.length());
		sql.append("from Tz06_help obj ");
		sql.append("where 1 = 1 ");

		// 关键字、问题描述、解决方案
		if (!keywords.equals("")) {
			sql.append("and (keys like '%");
			sql.append(keywords);
			sql.append("%' or title like '%");
			sql.append(keywords);
			sql.append("%') ");
		}

		sql.append(" order by ");
		sql.append(orderField);
		sql.append(" ");
		sql.append(orderType);

		ResultObject ro = queryService.searchByPage(sql.toString(), pageNum,numPerPage);
		totalCount = ro.getTotalRows();
		pageNumShown = ro.getTotalPages();

		modelMap.put("totalCount", totalCount);
		modelMap.put("pageNumShown", pageNumShown);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderType", orderType);

		List<Object> helpList = new LinkedList<Object>();
		while (ro.next()) {
			helpList.add(ro.get("obj"));
		}

		modelMap.put("helpList", helpList);
		return new ModelAndView("/WEB-INF/jsp/help/helpList.jsp",modelMap);
	}

	/**
	 * 知识库编辑
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/help/helpEdit.do")
	public ModelAndView repositoryEdit(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		// 数据库相关变量
		ModelMap modelMap = new ModelMap();
		Class<?> clazz = null;

		// 查询变量
		Long id = convertUtil.toLong(request.getParameter("id"), new Long(-1));

		// 当前时间
		modelMap.put("now", DateGetUtil.getCurTime());

		// 获取知识库对象
		Tz06_help tz06 = null;
		clazz = Tz06_help.class;
		tz06 = (Tz06_help) dao.getObject(clazz, id);
		modelMap.put("tz06", tz06);

		return new ModelAndView("/WEB-INF/jsp/help/helpEdit.jsp", modelMap);
	}

	/**
	 * 知识库删除ajax实现
	 * 
	 * @param reqeust
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/help/ajaxHelpDel.do")
	public void ajaxRepositoryDel(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding(request.getCharacterEncoding());
		PrintWriter out = null;
		response.setContentType("text/xml");

		Long id = convertUtil.toLong(request.getParameter("id"), -1L);

		// 获取用户对象
		try {
			out = response.getWriter();
			
			dao.removeObject(Tz06_help.class, id);

			/**
			 * 删除附件
			 */
			OperFile of = new OperFile();
			of.setQueryService(queryService);
			of.setSaveService(saveService);

			String sql = "select id from Te01_slave where doc_id = " + id;
			ResultObject ro = queryService.search(sql);
			while (ro.next()) {
				request.setAttribute("slave_id", ro.get("id"));
				of.delfile(request, response);
				dao.update("delete from Te01_slave where doc_id = "
						+ (Long) ro.get("id"));
			}

			out.print("{\"statusCode\":\"200\", \"message\":\"知识库删除成功\", \"navTabId\":\"\", \"forwardUrl\":\"help/helpList.do\", \"callbackType\":\"\"}");
		} catch (Exception e) {
			exceptionService.exceptionControl(
					"com.crht.controller.business.Repository", "知识库删除失败", e);
		}
	}

	/**
	 * 知识库保存ajax实现
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/help/ajaxHelpSave.do")
	public void ajaxRepositorySave(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		out = response.getWriter();
		StringBuffer hsql = new StringBuffer("");
		Ta03_user user = (Ta03_user) session.getAttribute("user");

		try {
			if (user != null) {
				Long id = convertUtil.toLong(request.getParameter("Tz06_help.ID"), -1L);
				String title = request.getParameter("Tz06_help.TITLE");
				String keys = request.getParameter("Tz06_help.KEYS");
				String content = request.getParameter("Tz06_help.CONTENT");
				String recordor = request.getParameter("Tz06_help.RECORDOR");
				String record_date = request.getParameter("Tz06_help.RECORD_DATE");
				Transaction tx = null;

				/**
				 * 保存知识库
				 */
				Long tz06_id = null;
				Tz06_help tz06 = (Tz06_help) dao.getObject(Tz06_help.class, id);
				if (tz06 == null) {
					tz06 = new Tz06_help();
				}
				tz06.setTitle(title);
				tz06.setKeys(keys);
				tz06.setRecord_date(new SimpleDateFormat("yyyy-MM-dd").parse(record_date));
				tz06.setRecordor(recordor);
				
				/*
				 * 处理特殊字符
				 */
				content = content.replaceAll("</p><p\\s*[^>]*>", "<br>");// 回车
				content = content.replaceAll("</p>", "");// 分段
				content = content.replaceAll("<p\\s*[^>]*>", "");// 分段
				content = content.replaceAll("<\\s*a\\s+[^>]*>", "");// 链接
				content = content.replaceAll("<a>", "");// 链接
				content = content.replaceAll("</a>", "");// 链接
				
				tz06.setContent(content);
				dao.saveObject(tz06);
				tz06_id = tz06.getId();

				out.print("{\"statusCode\":\"200\",\"message\":\"保存在线帮助成功\",\"navTabId\":\"helpList\",\"callbackType\":\"forward\",\"forwardUrl\":\"help/helpEdit.do?id="+ tz06_id + "\"}");
			} else {
				out.print("{\"statusCode\":\"301\",\"message\":\"会话超时，重新登录\",\"navTabId\":\"\",\"callbackType\":\"\",\"forwardUrl\":\"\"}");
			}

		} catch (Exception e) {
			e.printStackTrace();
			out.print("{\"statusCode\":\"300\",\"message\":\"保存在线帮助失败\",\"navTabId\":\"\",\"callbackType\":\"\",\"forwardUrl\":\"\"}");
		} finally {
			// session.flush();
			// tx.commit();
			// session.close();
		}
	}

	/**
	 * 知识库信息显示
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/help/helpDisp.do")
	public ModelAndView repositoryDisp(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		// 数据库相关变量
		QueryBuilder queryBuilder = null;
		StringBuffer sql = new StringBuffer("");
		ModelMap modelMap = new ModelMap();
		Class<?> clazz = null;

		// 查询变量
		Long id = convertUtil.toLong(request.getParameter("id"), new Long(-1));

		// 获取知识库对象
		Tz06_help tz06 = null;
		clazz = Tz06_help.class;
		tz06 = (Tz06_help) dao.getObject(clazz, id);
		modelMap.put("tz06", tz06);

		return new ModelAndView("/WEB-INF/jsp/help/helpDisp.jsp", modelMap);
	}
	
	/**
	 * 显示在线提问
	 * 
	 * @param reqeust
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/help/ajaxGetHelp.do")
	public void ajaxGetHelp(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding(request.getCharacterEncoding());
		PrintWriter out = null;
		response.setContentType("text/xml");

		String module_name = convertUtil.toString(request.getParameter("module_name"));
		StringBuffer sql = new StringBuffer("");
		HttpSession session = request.getSession();
		String adminRole = null;

		// 获取用户对象
		try {
			Long help_id = -1l;
			out = response.getWriter();
			sql.delete(0, sql.length());
			sql.append("select help_id from Tz08_help_map where module_name = '");
			sql.append(module_name);
			sql.append("'");
			ResultObject ro = queryService.search(sql.toString());
			if (ro.next()) {
				help_id = (Long)ro.get("help_id");
			}
			if(session == null){
				adminRole = "false";
			}else{
				adminRole = (String)session.getAttribute("admin");
			}
			
			
			out.print("{\"statusCode\":\"200\", \"message\":\"成功\", \"help_id\":\""+help_id+"\", \"adminRole\":\""+adminRole+"\"}");
			//out.print("{\"statusCode\":\"200\", \"message\":\"成功\", \"navTabId\":\"\", \"forwardUrl\":\"help/helpList.do\", \"callbackType\":\"\"}");
		} catch (Exception e) {
			exceptionService.exceptionControl(
					"com.rms.controller.help.Help.openHelpDisp()", "打开在线帮助失败", e);
		}
	}
}
