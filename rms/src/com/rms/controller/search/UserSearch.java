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
public class UserSearch {

	/**
	 * 查询服务
	 */
	@Autowired
	private QueryService queryService;
	

	@Autowired
	private ExceptionService exceptionService;
	
	@RequestMapping("/search/userSearch.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		Ta03_user user = null;
		user = (Ta03_user) request.getSession().getAttribute("user");
		if (user == null) {
			return exceptionService.exceptionControl(this.getClass().getName(), "用户未登录或登录超时", new Exception("用户未登录"));
		}
		
		String orderField = StringFormatUtil.format(request.getParameter("orderField"),"login_id");
		String orderDirection = StringFormatUtil.format(request.getParameter("orderDirection"),"asc");
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"),1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"),20);
		int totalPages = 1;
		int totalCount = 1;
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		
		String hql = "select ta03.id as id,ta03.login_id as login_id,ta03.name as name,ta03.sex as sex, ta03.fix_tel as tel,ta03.mobile_tel as mobile,ta03.email as email,ta03.dept_id as dept_id  " +
				"from Ta03_user ta03,Ta01_dept ta01 where ta03.dept_id=ta01.id ";
		String hql_sum= "select count(ta03.id) from Ta03_user ta03,Ta01_dept ta01 where  ta03.dept_id=ta01.id ";
		
		if( request.getParameter("search_name") != null && !request.getParameter("search_name").equals("")){
			request.setAttribute("search_name", request.getParameter("search_name"));
			
				hql += " and (ta03.name like '%"+request.getParameter("search_name")+"%'"+" or ta03.login_id like '%"+request.getParameter("search_name")+"%')";
				hql_sum += " and (ta03.name like '%"+request.getParameter("search_name")+"%'"+" or ta03.login_id like '%"+request.getParameter("search_name")+"%')";
			
		}
		
		if(request.getParameter("search_dept")!=null && !request.getParameter("search_dept").equals("")){
			request.setAttribute("search_dept", request.getParameter("search_dept"));
			hql += " and ta03.dept_id = "+request.getParameter("search_dept");
			hql_sum += " and ta03.dept_id = "+request.getParameter("search_dept");
		}
		if(user.getSearch_level().intValue()==2){
				hql += " and ta03.area_name='"+user.getArea_name()+"'";
				hql_sum += " and ta03.area_name='"+user.getArea_name()+"'";
			
		}else if(user.getSearch_level().intValue()==3){
			hql += " and ta03.login_id = '"+user.getLogin_id()+"'";
			hql_sum += " and ta03.login_id = '"+user.getLogin_id()+"'";
		}
		
		//设置排序
		StringBuffer order = new StringBuffer();
		order.append(" order by ");
		order.append(orderField+" ");
		order.append(orderDirection);
		hql += new String(order);
		
		List rohql_sum=  queryService.searchList(hql_sum);
		totalCount = new Integer(rohql_sum.get(0).toString());
		if(totalCount > 0 ){
			totalPages = totalCount%numPerPage==0 ? totalCount/numPerPage : totalCount/numPerPage + 1;
		}
		if(pageNum < 1 || pageNum > totalPages){
			pageNum = 1;
		}	
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("totalPages", totalPages);
	
		String toExcel = request.getParameter("toExcel");
		//导EXCEL 取所有行
		if ("yes".equals(toExcel)) {
			pageNum = 1;
			numPerPage = totalCount == 0 ? 1 : totalCount;
		}
		//取列表数据
		ResultObject ro  =  queryService.searchByPage(hql, pageNum, numPerPage);
		List user_list = new ArrayList();
		List<List> docList = new ArrayList<List>();
		while(ro.next()){
			List list1 = new ArrayList();
			Map<String,Object> mo = ro.getMap();
			Long dept_id = new Long(mo.get("dept_id").toString());
			QueryBuilder queryBuilder = new HibernateQueryBuilder(Ta01_dept.class);
			queryBuilder.eq("id",dept_id);
			ResultObject ro99 = queryService.search(queryBuilder);
			Ta01_dept ta01 = new Ta01_dept();
			if(ro99.next()){
				 ta01 = (Ta01_dept)ro99.get(Ta01_dept.class.getName());
				 }
			mo.put("deptname", "("+(""+ta01.getArea_name()).toString()+")"+ta01.getName());
			Set<Entry<String, Object>> tmpSet = mo.entrySet();
			for (Entry<String, Object> entry : tmpSet) {
				if (!"dept_id".equals(entry.getKey())&&!"id".equals(entry.getKey())){
					list1.add(entry.getValue());
				}
			}
			docList.add(list1);
			user_list.add(mo);
		}
		request.setAttribute("user_list", user_list);
		
		ResultObject ro_dept = queryService.search("select ta01.id as id,ta01.name as name,ta01.area_name as area_name from Ta01_dept ta01 where useflag=1 order by ta01.area_name ");
		
		List dept_list = new ArrayList();
		while(ro_dept.next()){
			Map<String,Object> mo = ro_dept.getMap();	
			dept_list.add(mo);
		}
		request.setAttribute("dept_list", dept_list);
		String form_title = "文档";
		String returnurl = "/WEB-INF/jsp/search/userSearch.jsp";
		if ("yes".equals(request.getParameter("toExcel"))) {
			Map<String, List> sheetMap = new HashMap<String, List>();
			List sheetList = new LinkedList();
			List titleList = new LinkedList();
			titleList.add("性别");
			titleList.add("工号");
			titleList.add("移动电话");
			titleList.add("所属部门");
			titleList.add("邮箱");
			titleList.add("办公电话");
			titleList.add("姓名");
			sheetList.add(titleList);
			sheetList.add(docList);
			sheetMap.put(form_title, sheetList);
			request.setAttribute("ExcelName", form_title);
			request.setAttribute("sheetMap", sheetMap);
			returnurl = "/export/toExcelWhithList.do";
		}
		return new ModelAndView(returnurl);
	}
	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}
	

}
