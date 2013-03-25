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
	public ModelAndView wxdwReceiveAndTimeout(HttpServletRequest request,
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
		String ywxm = convertUtil.toString(request.getParameter("ywxm"),"");
		
		StringBuffer sql = new StringBuffer();
		ResultObject ro = null;
		ResultObject ro2 = null;
		List list = new LinkedList();
		ModelMap modelMap = new ModelMap();
		try{
			sql.delete(0, sql.length());
			sql.append("select ta03.name as name ");
			sql.append(" from Ta03_user ta03,Ta11_sta_user ta11,Ta02_station ta02 ");
			sql.append("where ta03.id = ta11.user_id ");
			sql.append("and ta02.id = ta11.station_id ");
			sql.append("and ta02.name like '%项目管理岗%' ");
			sql.append("and ta03.dept_id = ");
			sql.append(user.getDept_id());
			sql.append(" order by ta03.name");
			ro = queryService.search(sql.toString());
			while(ro.next()){
				Map<String,Object> map = new HashMap<String,Object>();
				String name = (String)ro.get("name");
				map.put("name", name);
				
				/*
				 * 项目数
				 */
				sql.delete(0, sql.length());
				sql.append("select count(id) as xms ");
				sql.append("from Td01_xmxx ");
				sql.append("where xmgly = '");
				sql.append(name);
				sql.append("' ");
				if(!lxsj1.equals("") && !lxsj2.equals("")){
					sql.append("and lxsj >= to_date('"+lxsj1+"','yyyy-mm-dd') ");
					sql.append("and lxsj <= to_date('"+lxsj2+"','yyyy-mm-dd') ");
				}
				if(!pdsj1.equals("") && !pdsj2.equals("")){
					sql.append("and ((sjpgsj >= to_date('"+pdsj1+"','yyyy-mm-dd') ");
					sql.append("and sjpgsj <= to_date('"+pdsj2+"','yyyy-mm-dd')) ");
					sql.append("or (sgpfsj >= to_date('"+pdsj1+"','yyyy-mm-dd') ");
					sql.append("and sgpfsj <= to_date('"+pdsj2+"','yyyy-mm-dd')) ");
					sql.append("or (jlpfsj >= to_date('"+pdsj1+"','yyyy-mm-dd') ");
					sql.append("and jlpfsj <= to_date('"+pdsj2+"','yyyy-mm-dd'))) ");
				}
				ro2 = queryService.search(sql.toString());
				Long xms = 0L;
				if(ro2.next()){
					xms = convertUtil.toLong(ro2.get("xms"));
				}
				map.put("xms", xms);
				
				/*
				 * 派工数
				 */
				sql.delete(0, sql.length());
				sql.append("select count(id) as pgs ");
				sql.append("from Td01_xmxx ");
				sql.append("where xmgly = '");
				sql.append(name);
				sql.append("' and [dw] is not null ");
				if(!lxsj1.equals("") && !lxsj2.equals("")){
					sql.append("and lxsj >= to_date('"+lxsj1+"','yyyy-mm-dd') ");
					sql.append("and lxsj <= to_date('"+lxsj2+"','yyyy-mm-dd') ");
				}
				if(!pdsj1.equals("") && !pdsj2.equals("")){
					sql.append("and [pdsj] >= to_date('"+pdsj1+"','yyyy-mm-dd') ");
					sql.append("and [pdsj] <= to_date('"+pdsj2+"','yyyy-mm-dd') ");
				}
				/*
				 * 派设计
				 */
				ro2 = queryService.search(sql.toString().replace("[dw]", "sjdw").replace("[pdsj]", "sjpgsj"));
				Long psjs = 0L;
				if(ro2.next()){
					psjs = convertUtil.toLong(ro2.get("pgs"),0L);
				}
				map.put("psjs", psjs);
				/*
				 * 派施工
				 */
				ro2 = queryService.search(sql.toString().replace("[dw]", "sgdw").replace("[pdsj]", "sgpfsj"));
				Long psgs = 0L;
				if(ro2.next()){
					psgs = convertUtil.toLong(ro2.get("pgs"),0L);
				}
				map.put("psgs", psgs);
				/*
				 * 派监理
				 */
				ro2 = queryService.search(sql.toString().replace("[dw]", "jldw").replace("[pdsj]", "jlpfsj"));
				Long pjls = 0L;
				if(ro2.next()){
					pjls = convertUtil.toLong(ro2.get("pgs"),0L);
				}
				map.put("pjls", pjls);
				
				/*
				 * 超期数
				 */
				sql.delete(0, sql.length());
				sql.append("select count(id) as cqs ");
				sql.append("from Td01_xmxx ");
				sql.append("where xmgly = '");
				sql.append(name);
				sql.append("' and (sjkgsj + yqgq < sjjgsj or (sjjgsj is null and sjkgsj + yqgq < sysdate)) ");
				if(!lxsj1.equals("") && !lxsj2.equals("")){
					sql.append("and lxsj >= to_date('"+lxsj1+"','yyyy-mm-dd') ");
					sql.append("and lxsj <= to_date('"+lxsj2+"','yyyy-mm-dd') ");
				}
				if(!pdsj1.equals("") && !pdsj2.equals("")){
					sql.append("and sgpfsj >= to_date('"+pdsj1+"','yyyy-mm-dd') ");
					sql.append("and sgpfsj <= to_date('"+pdsj2+"','yyyy-mm-dd') ");
				}
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
				if(xms != 0){
					cql = NumberFormatUtil.divToDouble(cqs, xms);
				}
				map.put("cql", cql);
				
				/*
				 * 决算数
				 */
				sql.delete(0, sql.length());
				sql.append("select count(id) as jss ");
				sql.append("from Td01_xmxx ");
				sql.append("where xmgly = '");
				sql.append(name);
				sql.append("' and jssj is not null ");
				if(!lxsj1.equals("") && !lxsj2.equals("")){
					sql.append("and lxsj >= to_date('"+lxsj1+"','yyyy-mm-dd') ");
					sql.append("and lxsj <= to_date('"+lxsj2+"','yyyy-mm-dd') ");
				}
				if(!pdsj1.equals("") && !pdsj2.equals("")){
					sql.append("and sgpfsj >= to_date('"+pdsj1+"','yyyy-mm-dd') ");
					sql.append("and sgpfsj <= to_date('"+pdsj2+"','yyyy-mm-dd') ");
				}
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
				if(xms != 0){
					jsl = NumberFormatUtil.divToDouble(jss, xms);
				}
				map.put("jsl", jsl);
				if((ywxm.equals("有项目") && xms > 0) || (ywxm.equals("无项目") && xms == 0) || (ywxm.equals(""))){
					list.add(map);
				}
			}
			modelMap.put("pdcqList", list);
			
			String[] ywxmList = {"有项目","无项目"};
			modelMap.put("ywxmList", ywxmList);
		}
		catch(Exception e){
			return exceptionService.exceptionControl(this.getClass().getName(), "系统出错，请联系管理员", new Exception(e+e.getMessage()));
		}
		
		return new ModelAndView("/WEB-INF/jsp/search/xmglyDownAndTimeout.jsp",modelMap);
	
	}
	
	@RequestMapping("/search/userLogin.do")
	public ModelAndView userLogin(HttpServletRequest request,
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
		String dwlb = convertUtil.toString(request.getParameter("dwlb"),"");
		
		StringBuffer sql = new StringBuffer();
		StringBuffer sql2 = new StringBuffer();
		ResultObject ro = null;
		ResultObject ro2 = null;
		List list = new LinkedList();
		ModelMap modelMap = new ModelMap();
		try{
			if(dwlb.equals("xmgly")){
				sql.delete(0, sql.length());
				sql.append("select ta03.name name ,count(Tz03.id) ");
				sql.append(" from Ta03_user ta03,Ta11_sta_user ta11,Ta02_station ta02,Tz03_login_log tz03 ");
				sql.append("where ta03.id = ta11.user_id ");
				sql.append("and tz03.login_id = ta03.login_id ");
				sql.append("and ta02.id = ta11.station_id ");
				sql.append("and ta02.name like '%项目管理员%' ");
				sql.append("and ta03.dept_id = ");
				sql.append(user.getDept_id());
				sql.append(" group by ta03.name ");
				
				sql2.delete(0, sql.length());
				sql2.append("select ta03.name name ");
				sql2.append(" from Ta03_user ta03,Ta11_sta_user ta11,Ta02_station ta02 ");
				sql2.append("where ta03.id = ta11.user_id ");
				sql2.append("and ta02.id = ta11.station_id ");
				sql2.append("and ta02.name like '%项目管理员%' ");
				sql2.append("not exists(select 'x' from Tz03_login_log tz03 where tz03.login_id = ta03.login_id)");
				sql2.append("and ta03.dept_id = ");
				sql2.append(user.getDept_id());
				
			}
			else{
				sql.delete(0, sql.length());
				sql.append("select tf01.mc as mc ,count(tz03.id) dls ");
				sql.append(" from Ta03_user ta03,Tf04_wxdw_user tf04,Tf01_wxdw tf01,Tz03_login_log tz03 ");
				sql.append("where ta03.id = tf04.user_id ");
				sql.append("and tf04.wxdw_id = tf01.id ");
				sql.append(" group by tf01.mc ");
				
				sql2.delete(0, sql.length());
				sql2.append("select tf011.mc from Tf01_wxdw tf011 where not exists(select 'x' from (");
				sql2.append("select tf01.mc as mc  ");
				sql2.append(" from Ta03_user ta03,Tf04_wxdw_user tf04,Tf01_wxdw tf01,Tz03_login_log tz03 ");
				sql2.append("where ta03.id = tf04.user_id ");
				sql2.append("and tf04.wxdw_id = tf01.id ) ");
				sql2.append("where tf011.mc = t.mc ");
			}	
			ro = queryService.search(sql.toString());
			while(ro.next()){
				Map<String,Object> map = new HashMap<String,Object>();
				String mc = (String)ro.get("mc");
				Long dls = convertUtil.toLong(ro.get("dls"));
				map.put("mc", mc);
				map.put("dls", dls);
				list.add(map);
			}
			modelMap.put("dlList", list);
			
			List list2 = queryService.searchList(sql2.toString());
			modelMap.put("dlList2", list2);
		}
		catch(Exception e){
			return exceptionService.exceptionControl(this.getClass().getName(), "系统出错，请联系管理员", new Exception(e+e.getMessage()));
		}
		
		return new ModelAndView("/WEB-INF/jsp/search/userLogin.jsp",modelMap);
	
	}
	
	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}
	

}
