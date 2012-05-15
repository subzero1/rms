package com.jfms.controller.info;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
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

import com.jfms.dataObjects.info.Td00_jfxx;
import com.jfms.dataObjects.info.Td21_jcsq;
import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.utils.convertUtil;

/**
 * @description: 纠错申请
 * @class name:com.jfms.controller.info.Jcsq
 * @author Administrator Mar 8, 2011
 */
@Controller
public class Jcsq {
	/**
	 * 数据服务
	 */
	@Autowired
	private Dao dao;

	/**
	 * 异常处理服务
	 */
	@Autowired
	private ExceptionService exceptionService;

	/**
	 * 查询服务
	 */
	@Autowired
	private QueryService queryService;


	/**
	 * 申请列表
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/info/jcsqList.do")
	public ModelAndView bureauList(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		
		Ta03_user user=(Ta03_user)session.getAttribute("user");
		
		// 数据库相关变量
		StringBuffer sql = new StringBuffer("");
		ModelMap modelMap = new ModelMap();

		// 分页变量
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 20);
		Integer totalCount = 0;
		Integer pageNumShown = 0;

		// 排序变量
		String orderField = convertUtil.toString(request.getParameter("orderField"), "id");
		String orderDirection = convertUtil.toString(request.getParameter("orderDirection"), "desc");

		// 查询变量
		String type = convertUtil.toString(request.getParameter("type"), "");
		String keywords = convertUtil.toString(request.getParameter("keywords"), "");

		// 获得机房信息列表
		sql.delete(0, sql.length());
		sql.append(" from Td21_jcsq");
		sql.append(" where");

		sql.append(" (jfmc like '%");
		sql.append(keywords);
		sql.append("%'");

		sql.append(" or jdmc like '%");
		sql.append(keywords);
		sql.append("%' )");
		
		if("".equals(type))
			sql.append(" and sqr='"+ user.getName() +"'");

		sql.append(" order by ");
		sql.append(orderField);
		sql.append(" ");
		sql.append(orderDirection);

		ResultObject ro = queryService.searchByPage(sql.toString(), pageNum,
				numPerPage);
		totalCount = ro.getTotalRows();
		pageNumShown = ro.getTotalPages();

		modelMap.put("totalCount", totalCount);
		modelMap.put("pageNumShown", pageNumShown);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);

		List<Object> jcsqList = new LinkedList<Object>();
		while (ro.next()) {
			jcsqList.add(ro.get("Td21_jcsq"));
		}

		modelMap.put("jcsqList", jcsqList);

		return new ModelAndView("/WEB-INF/jsp/info/jcsqList.jsp", modelMap);
	}

	@RequestMapping("/info/jcsqEdit.do")
	public ModelAndView jcsqEdit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = convertUtil.toLong(request.getParameter("id"), -1L);
		ModelMap modelMap = new ModelMap();
		Td21_jcsq td21 = (Td21_jcsq) dao.getObject(Td21_jcsq.class, id);
		modelMap.put("td21", td21);
		
		return new ModelAndView("/WEB-INF/jsp/info/jcsqEdit.jsp", modelMap);
	}

	@RequestMapping("/info/ajaxjcsqDel.do")
	public ModelAndView ajaxjcsqDel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding(request.getCharacterEncoding());
		Long id = convertUtil.toLong(request.getParameter("id"), -1L);
		ModelMap modelMap = new ModelMap();
		PrintWriter out = null;

		// 获取用户对象
		try {
			out = response.getWriter();
			dao.removeObject(Td21_jcsq.class, id);
			out
					.print("{\"statusCode\":\"200\", \"message\":\"删除成功\", \"navTabId\":\"jcsqList\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");
		} catch (IOException e) {
			exceptionService.exceptionControl(
					"com.crht.controller.sysManage.User", "删除失败", e);
		}
		return null;
	}
	
	/**
	 * 根据输入局点或机房名称自动获取一条机房信息ajax实现
	 * 
	 * @param reqeust
	 * @param response
	 * @return	json
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/info/ajaxGetJfxx.do")
	public void ajaxAutocompleteJfxx(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		out = response.getWriter();
		
		
		String keyword = convertUtil.toString(request.getParameter("inputValue"));
		StringBuffer hsql = new StringBuffer("");
		ResultObject ro = null;

		// 获取机房信息
		hsql.append(" from Td00_jfxx");
		hsql.append(" where");
		
		hsql.append(" jfmc like '%");
		hsql.append(keyword);
		hsql.append("%'");
		
		hsql.append(" or jdmc like '%");
		hsql.append(keyword);
		hsql.append("%' ");
		
		List tmpList  = queryService.searchList(hsql.toString());
		StringBuffer outStr = new StringBuffer("");
		outStr.append("[");
		if (tmpList != null) {
			Integer num = tmpList.size()>15?15:tmpList.size();
			for (Integer i=0; i<num; i++) {
				Td00_jfxx td00 =(Td00_jfxx) tmpList.get(i);
				outStr.append("{");
				outStr.append("\"Td21_jcsq.JF_ID\":\"");
				outStr.append(td00.getId());
				outStr.append("\", \"Td21_jcsq.JDMC\":\"");
				outStr.append(td00.getJdmc());
				outStr.append("\", \"Td21_jcsq.JFMC\":\"");
				outStr.append(td00.getJfmc());
				outStr.append("\"");
				outStr.append("},");
			}
		}
		outStr.append("{}");
		outStr.append("]");
		out.print(outStr);
		out.flush();
		out.close();
	}
}
