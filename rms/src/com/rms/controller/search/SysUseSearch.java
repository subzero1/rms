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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import com.rms.dataObjects.base.Tc02_area;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta01_dept;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.export.ExportExcel;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.utils.NumberFormatUtil;
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
	
	private  Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping("/search/wxdwReceiveAndTimeout.do")
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
		
		StringBuffer sql = new StringBuffer();
		ResultObject ro = null;
		ResultObject ro2 = null;
		List list = new LinkedList();
		ModelMap modelMap = new ModelMap();
		try{
			sql.delete(0, sql.length());
			sql.append("select mc from Tf01_wxdw where 1 = 1 ");
			sql.append("and dwlb = '");
			sql.append(dwlb);
			sql.append("'");
			ro = queryService.search(sql.toString());
			while(ro.next()){
				Map<String,Object> map = new HashMap<String,Object>();
				String mc = (String)ro.get("mc");
				map.put("mc", mc);
				
				/*
				 * 派单数
				 */
				sql.delete(0, sql.length());
				sql.append("select count(id) as pds ");
				sql.append("from Td01_xmxx ");
				sql.append("where ");
				sql.append(dwlb);
				sql.append("dw = '");
				sql.append(mc);
				sql.append("'");
				ro2 = queryService.search(sql.toString());
				Long pds = 0L;
				if(ro2.next()){
					pds = convertUtil.toLong(ro2.get("pds"));
				}
				map.put("pds", pds);
				
				/*
				 * 接单数
				 */
				sql.delete(0, sql.length());
				sql.append("select count(id) as jds ");
				sql.append("from Td01_xmxx where ");
				sql.append(dwlb);
				sql.append("dw = '");
				sql.append(mc);
				sql.append("' and ");
				sql.append(dwlb);
				sql.append("ysl is not null ");
				ro2 = queryService.search(sql.toString());
				Long jds = 0L;
				if(ro2.next()){
					jds = convertUtil.toLong(ro2.get("jds"));
				}
				map.put("jds", jds);
				
				/*
				 * 超期数
				 */
				sql.delete(0, sql.length());
				sql.append("select count(id) as cqs ");
				sql.append("from Td01_xmxx where ");
				sql.append(dwlb);
				sql.append("dw = '");
				sql.append(mc);
				sql.append("' and (sjkgsj + yqgq < sjjgsj or (sjjgsj is null and sjkgsj + yqgq < sysdate)) ");
				ro2 = queryService.search(sql.toString());
				Long cqs = 0L;
				if(ro2.next()){
					cqs = convertUtil.toLong(ro2.get("cqs"));
				}
				map.put("cqs", cqs);
				
				/*
				 * 超期率
				 */
				Double cql = 0d;
				if(pds != 0){
					cql = NumberFormatUtil.divToDouble(cqs, pds);
				}
				map.put("cql", cql);
				
				/*
				 * 决算数
				 */
				sql.delete(0, sql.length());
				sql.append("select count(id) as jss ");
				sql.append("from Td01_xmxx where ");
				sql.append(dwlb);
				sql.append("dw = '");
				sql.append(mc);
				sql.append("' and jssj is not null ");
				ro2 = queryService.search(sql.toString());
				Long jss = 0L;
				if(ro2.next()){
					jss = convertUtil.toLong(ro2.get("jss"));
				}
				map.put("jss", jss);
				
				/*
				 * 决算率
				 */
				Double jsl = 0d;
				if(pds != 0){
					jsl = NumberFormatUtil.divToDouble(jss, pds);
				}
				map.put("jsl", jsl);
				list.add(map);
			}
			modelMap.put("jdcqList", list);
		}
		catch(Exception e){
			return exceptionService.exceptionControl(this.getClass().getName(), "系统出错，请联系管理员", new Exception(e+e.getMessage()));
		}
		
		return new ModelAndView("/WEB-INF/jsp/search/wxdwReceiveAndTimeout.jsp",modelMap);
	}
	
	@RequestMapping("/search/xmglyDownAndTimeout.do")
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
