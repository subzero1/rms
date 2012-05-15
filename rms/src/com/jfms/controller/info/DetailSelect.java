package com.jfms.controller.info;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jfms.dataObjects.base.Tc01_property;
import com.jfms.dataObjects.info.Td00_jfxx;
import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.utils.convertUtil;

/**
 * @description:
 * 明细选项
 * @class name:com.netsky.base.controller.Main
 * @author Administrator Jul 21, 2011
 */
@Controller
public class DetailSelect {
	/**
	 * 数据库操作服务，自动注入
	 */
	@Autowired
	private Dao dao;
	
	/**
	 * 查询服务
	 */
	@Autowired
	private QueryService queryService;	
	
	/**
	 * 异常处理服务
	 */
	@Autowired
	private ExceptionService exceptionService;
		
	/**
	 * 属性下拉选项
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/info/propertySelect.do")
	public void propertySelect(HttpServletRequest request, HttpServletResponse response, HttpSession session)  throws Exception{
		response.setCharacterEncoding(request.getCharacterEncoding());
		PrintWriter out = null;
		response.setContentType("text/html");

		//获得参数
		String type = convertUtil.toString(request.getParameter("type"),"所属专业");
		String inputName = convertUtil.toString(request.getParameter("inputName"));
		String enumName = convertUtil.toString(request.getParameter("enumName"));
		
		StringBuffer printStr =new StringBuffer();
		printStr.append("<select name=\"" + inputName + "\" style=\"width:0px;\">");

		try {
			StringBuffer sql =new StringBuffer();
			sql.append("select tc01 from Tc01_property tc01"); 
			sql.append(" where type=? order by id");
			List<Tc01_property> tmpList = (List<Tc01_property>)dao.search(sql.toString(), new Object[]{type.toString()});
			
			for(Tc01_property tc01:tmpList){
				printStr.append("<option value='" + tc01.getName() + "'>" + tc01.getName()+ "</option>");
			}

			printStr.append("<option value=\"\"></option>");
			printStr.append("</select>");
			
			out = response.getWriter();
			out.print(printStr);
			
		} catch (IOException e) {
			exceptionService.exceptionControl("com.jfms.controller.info.DetailSelect", "获取明细选项失败", e);
		}
		
	}
	
	/**
	 * 新建申请选择机房信息
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/selectJfxx.do")
	public ModelAndView selectJfxx(HttpServletRequest request, HttpServletResponse response, HttpSession session)  throws Exception{
		//数据库相关变量
		StringBuffer sql = new StringBuffer("");
		ModelMap modelMap = new ModelMap();
		
		//分页变量
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"),1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"),10);
		Integer totalCount = 0;
		Integer pageNumShown  = 0;
		
		//排序变量
		String orderField = convertUtil.toString(request.getParameter("orderField"),"id");
		String orderDirection = convertUtil.toString(request.getParameter("orderDirection"),"desc");
		
		//查询变量
		String keyword = convertUtil.toString(request.getParameter("keyword"),"");
		
		//获得机房信息列表
		sql.append(" from Td00_jfxx");
		sql.append(" where");
		
		sql.append(" jfmc like '%");
		sql.append(keyword);
		sql.append("%'");
		
		sql.append(" or jdmc like '%");
		sql.append(keyword);
		sql.append("%' ");
		
		sql.append(" order by ");
		sql.append(orderField);
		sql.append(" ");
		sql.append(orderDirection);
		
		ResultObject ro = queryService.searchByPage(sql.toString(), pageNum, numPerPage);
		totalCount = ro.getTotalRows();
		pageNumShown = ro.getTotalPages();
		
		modelMap.put("totalCount", totalCount);
		modelMap.put("pageNumShown", pageNumShown);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		
		List<Object> jfxxList = new LinkedList<Object>();
		while(ro.next()){
			jfxxList.add(ro.get("Td00_jfxx"));
		}
		
		modelMap.put("jfxxList", jfxxList);

		return new ModelAndView("/WEB-INF/jsp/form/selectJfxx.jsp",modelMap);		
	}
	
	/**
	 * 根据输入局点或机房名称自动获取一条机房信息ajax实现
	 * 
	 * @param reqeust
	 * @param response
	 * @return xml
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/ajaxSelectJfxx.do")
	public void ajaxSelectJfxx(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("GBK");
		response.setContentType("text/xml");
		PrintWriter out = null;
		out = response.getWriter();
		
		
		String keyword = convertUtil.toString(request.getParameter("keyword"));
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
		String returstr = "";
		if(tmpList.size()> 0){
			Td00_jfxx td00 =(Td00_jfxx) tmpList.get(0);
			out.print("<?xml version=\"1.0\" encoding=\"GBK\"?>");
			out.print("<root>");
			out.println("<jf_id>");
			out.println(td00.getId());
			out.println("</jf_id>");
			out.println("<jdmc>");
			out.println(td00.getJdmc());
			out.println("</jdmc>");
			out.println("<jfmc>");
			out.println(td00.getJdmc());
			out.println("</jfmc>");
			out.print("</root>");
		}else{
			out.print("<?xml version=\"1.0\" encoding=\"GBK\"?>");
			out.print("<root>");
			out.print("</root>");
		}
		out.flush();
		out.close();
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
	@RequestMapping("/ajaxAutocompleteJfxx.do")
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
				outStr.append("\"Td12_gljf.JF_ID\":\"");
				outStr.append(td00.getId());
				outStr.append("\", \"Td12_gljf.JDMC\":\"");
				outStr.append(td00.getJdmc());
				outStr.append("\", \"Td12_gljf.JFMC\":\"");
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
	
	/**
	 * 新建申请选择基础设备
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/selectJcsb.do")
	public ModelAndView selectJcsb(HttpServletRequest request, HttpServletResponse response, HttpSession session)  throws Exception{
		//数据库相关变量
		StringBuffer sql = new StringBuffer("");
		ModelMap modelMap = new ModelMap();
		
		//分页变量
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"),1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"),10);
		Integer totalCount = 0;
		Integer pageNumShown  = 0;
		
		//排序变量
		String orderField = convertUtil.toString(request.getParameter("orderField"),"id");
		String orderDirection = convertUtil.toString(request.getParameter("orderDirection"),"desc");
		
		//查询变量
		String keyword = convertUtil.toString(request.getParameter("keyword"),"");
		
		//获得机房信息列表
		sql.append(" from Tc04_jcsb");
		sql.append(" where");
		
		sql.append(" sbmc like '%");
		sql.append(keyword);
		sql.append("%'");
		
		sql.append(" or sbxh like '%");
		sql.append(keyword);
		sql.append("%'");

		sql.append(" or cj like '%");
		sql.append(keyword);
		sql.append("%'");
		
		
		sql.append(" order by ");
		sql.append(orderField);
		sql.append(" ");
		sql.append(orderDirection);
		
		ResultObject ro = queryService.searchByPage(sql.toString(), pageNum, numPerPage);
		totalCount = ro.getTotalRows();
		pageNumShown = ro.getTotalPages();
		
		modelMap.put("totalCount", totalCount);
		modelMap.put("pageNumShown", pageNumShown);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		
		List<Object> jcsbList = new LinkedList<Object>();
		while(ro.next()){
			jcsbList.add(ro.get("Tc04_jcsb"));
		}
		
		modelMap.put("jcsbList", jcsbList);

		return new ModelAndView("/WEB-INF/jsp/form/selectJcsb.jsp",modelMap);		
	}
	
	/**
	 * 新建机房选择局点信息
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/selectJdxx.do")
	public ModelAndView selectJdxx(HttpServletRequest request, HttpServletResponse response, HttpSession session)  throws Exception{
		//数据库相关变量
		StringBuffer sql = new StringBuffer("");
		ModelMap modelMap = new ModelMap();
		
		//分页变量
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"),1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"),10);
		Integer totalCount = 0;
		Integer pageNumShown  = 0;
		
		//排序变量
		String orderField = convertUtil.toString(request.getParameter("orderField"),"id");
		String orderDirection = convertUtil.toString(request.getParameter("orderDirection"),"desc");
		
		//查询变量
		String jdmc = convertUtil.toString(request.getParameter("jdmc"),"");
		
		//获得机房信息列表
		sql.append(" from Tc02_bureau");
		sql.append(" where");
				
		sql.append(" name like '%");
		sql.append(jdmc);
		sql.append("%' ");
		
		sql.append(" order by ");
		sql.append(orderField);
		sql.append(" ");
		sql.append(orderDirection);
		
		ResultObject ro = queryService.searchByPage(sql.toString(), pageNum, numPerPage);
		totalCount = ro.getTotalRows();
		pageNumShown = ro.getTotalPages();
		
		modelMap.put("totalCount", totalCount);
		modelMap.put("pageNumShown", pageNumShown);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		
		List<Object> jdxxList = new LinkedList<Object>();
		while(ro.next()){
			jdxxList.add(ro.get("Tc02_bureau"));
		}
		
		modelMap.put("jdxxList", jdxxList);

		return new ModelAndView("/WEB-INF/jsp/info/selectJdxx.jsp",modelMap);		
	}
}
