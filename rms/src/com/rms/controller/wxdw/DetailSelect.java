package com.rms.controller.wxdw;

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

import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.utils.convertUtil;
import com.rms.dataObjects.wxdw.Tf06_clb;
import com.rms.dataObjects.wxdw.Tf07_kcb;
import com.rms.dataObjects.wxdw.Tf08_clmxb;

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
		
//	/**
//	 * 属性下拉选项
//	 * 
//	 * @param request
//	 * @param response
//	 * @param session
//	 * @return ModelAndView
//	 */
//	@RequestMapping("/info/propertySelect.do")
//	public void propertySelect(HttpServletRequest request, HttpServletResponse response, HttpSession session)  throws Exception{
//		response.setCharacterEncoding(request.getCharacterEncoding());
//		PrintWriter out = null;
//		response.setContentType("text/html");
//
//		//获得参数
//		String type = convertUtil.toString(request.getParameter("type"),"所属专业");
//		String inputName = convertUtil.toString(request.getParameter("inputName"));
//		String enumName = convertUtil.toString(request.getParameter("enumName"));
//		
//		StringBuffer printStr =new StringBuffer();
//		printStr.append("<select name=\"" + inputName + "\" style=\"width:0px;\">");
//
//		try {
//			StringBuffer sql =new StringBuffer();
//			sql.append("select tc01 from Tc01_property tc01"); 
//			sql.append(" where type=? order by id");
//			List<Tc01_property> tmpList = (List<Tc01_property>)dao.search(sql.toString(), new Object[]{type.toString()});
//			
//			for(Tc01_property tc01:tmpList){
//				printStr.append("<option value='" + tc01.getName() + "'>" + tc01.getName()+ "</option>");
//			}
//
//			printStr.append("<option value=\"\"></option>");
//			printStr.append("</select>");
//			
//			out = response.getWriter();
//			out.print(printStr);
//			
//		} catch (IOException e) {
//			exceptionService.exceptionControl("com.jfms.controller.info.DetailSelect", "获取明细选项失败", e);
//		}
//		
//	}
	
	/**
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/selectClxx.do")
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
		sql.append(" from Tf06_clb");
		sql.append(" where");
		
		sql.append(" clbm like '%");
		sql.append(keyword);
		sql.append("%'");
		
		sql.append(" or clmc like '%");
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
		
		List<Object> clxxList = new LinkedList<Object>();
		while(ro.next()){
			clxxList.add(ro.get("Tf06_clb"));
		}
		
		modelMap.put("clxxList", clxxList);

		return new ModelAndView("/WEB-INF/jsp/wxdw/selectClxx.jsp",modelMap);		
	}
	
	/**
	 * 根据输入材料编码或材料名称自动获取一条材料信息ajax实现
	 * 
	 * @param reqeust
	 * @param response
	 * @return xml
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/ajaxSelectClxx.do")
	public void ajaxSelectClxx(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("GBK");
		response.setContentType("text/xml");
		PrintWriter out = null;
		out = response.getWriter();
		
		
		String keyword = convertUtil.toString(request.getParameter("keyword"));
		StringBuffer hsql = new StringBuffer("");
		ResultObject ro = null;

		// 获取机房信息
		hsql.append(" from Tf06_clb");
		hsql.append(" where");
		
		hsql.append(" clmc like '%");
		hsql.append(keyword);
		hsql.append("%'");
		
		hsql.append(" or clbm like '%");
		hsql.append(keyword);
		hsql.append("%' ");
		
		List tmpList  = queryService.searchList(hsql.toString());
		String returstr = "";
		if(tmpList.size()> 0){
			Tf06_clb tf06 =(Tf06_clb) tmpList.get(0);
			out.print("<?xml version=\"1.0\" encoding=\"GBK\"?>");
			out.print("<root>");
			out.println("<clbm>");
			out.println(tf06.getId());
			out.println("</clbm>");
			out.println("<clmc>");
			out.println(tf06.getClmc());
			out.println("</clmc>");
			out.println("<xh>");
			out.println(tf06.getXh());
			out.println("</xh>");
			out.println("<gg>");
			out.println(tf06.getGg());
			out.println("</gg>");
			out.println("<dw>");
			out.println(tf06.getDw());
			out.println("</dw>");
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
	@RequestMapping("/ajaxAutocompleteClxx.do")
	public void ajaxAutocompleteClxx(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		out = response.getWriter();
		
		
		String keyword = convertUtil.toString(request.getParameter("inputValue"));
		StringBuffer hsql = new StringBuffer("");
		ResultObject ro = null;

		// 获取机房信息
		hsql.append(" from Tf06_clb");
		hsql.append(" where");
		
		hsql.append(" clmc like '%");
		hsql.append(keyword);
		hsql.append("%'");
		
		hsql.append(" or clbm like '%");
		hsql.append(keyword);
		hsql.append("%' ");
		
		List tmpList  = queryService.searchList(hsql.toString());
		StringBuffer outStr = new StringBuffer("");
		outStr.append("[");
		if (tmpList != null) {
			Integer num = tmpList.size()>15?15:tmpList.size();
			for (Integer i=0; i<num; i++) {
				Tf06_clb tf06 =(Tf06_clb) tmpList.get(i);
				outStr.append("{");
				outStr.append("\"Tf08_clmxb.CLBM\":\"");
				outStr.append(tf06.getClbm());
				outStr.append("\", \"Tf08_clmxb.CLMC\":\"");
				outStr.append(tf06.getClmc());
				outStr.append("\", \"Tf08_clmxb.XH\":\"");
				outStr.append(tf06.getXh());
				outStr.append("\", \"Tf08_clmxb.GG\":\"");
				outStr.append(tf06.getGg());
				outStr.append("\", \"Tf08_clmxb.DW\":\"");
				outStr.append(tf06.getDw());
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
