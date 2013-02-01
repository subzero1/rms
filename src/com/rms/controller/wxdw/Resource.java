package com.rms.controller.wxdw;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.utils.convertUtil;
import com.netsky.base.service.QueryService;
import com.rms.base.util.ConfigXML;
import com.rms.base.util.ConfigXMLImpl;
import com.rms.dataObjects.wxdw.Tf31_zytl;

/**
 * @description:资源录入
 * 
 * @class name:com.rms.controller.infoManage.Resource
 * @author net Jan 29, 2013
 */
@Controller
public class Resource {

	@Autowired
	private QueryService queryService;
	
	@Autowired
	private Dao dao;

	/**
	 * 填录人信息修改
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ModelAndView
	 */
	@RequestMapping("/wxdw/zytlEdit.do")
	public ModelAndView zytlEdit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String view = "/WEB-INF/jsp/wxdw/zytlEdit.jsp";
		ModelMap modelMap = new ModelMap();
		Tf31_zytl tf31_zytl = null;
		Long zytl_id = convertUtil.toLong(request.getParameter("zytl_id"));

		if (zytl_id != -1) {
			tf31_zytl = (Tf31_zytl) queryService.searchById(Tf31_zytl.class,
					zytl_id);
		}
		modelMap.put("Tf31_zytl", tf31_zytl);
		return new ModelAndView(view, modelMap);
	}

	/**
	 * 填录人列表
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/wxdw/zytlrList.do")
	public ModelAndView zytlrList(HttpServletRequest request,
			HttpServletResponse response) {
		String view = "/WEB-INF/jsp/wxdw/zytlrList.jsp";
		ModelMap modelMap = new ModelMap();
		StringBuffer hql = new StringBuffer();
		Integer pageNum = convertUtil.toInteger(
				request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request
				.getParameter("orderField"), "tlrxm");
		String orderDirection = convertUtil.toString(request
				.getParameter("orderDirection"), "asc");
		String keyword = convertUtil.toString(request.getParameter("keyword"));
		Integer totalCount = 0;
		List<Tf31_zytl> tf31_zytlrList = null;

		hql.append("select t from Tf31_zytl t where 1=1 ");
		if (keyword != "") {
			hql.append(" and t.tlrxm like '%");
			hql.append(keyword);
			hql.append("%' ");
		}
		hql.append("order by t.");
		hql.append(orderField);
		hql.append(" ");
		hql.append(orderDirection);

		ResultObject ro = queryService.searchByPage(hql.toString(), pageNum,
				numPerPage);
		if (ro != null) {
			totalCount = ro.getTotalRows();
			tf31_zytlrList = new LinkedList<Tf31_zytl>();
			while (ro.next()) {
				tf31_zytlrList.add((Tf31_zytl) ro.get("t"));
			}
		}
		modelMap.put("Tf31_zytlList", tf31_zytlrList);
		modelMap.put("keywork", keyword);
		modelMap.put("totalCount", totalCount);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("pageNum", pageNum);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		return new ModelAndView(view, modelMap);
	}

	/**
	 * 删除填录人信息
	 * @param request
	 * @param response void
	 * @throws IOException,Exception 
	 */
	@RequestMapping("/wxdw/tlrAjaxDel.do")
	public void tlrAjaxDel(HttpServletRequest request,
			HttpServletResponse response) throws IOException,Exception{
		Long zytl_id=convertUtil.toLong(request.getParameter("zytl_id"));
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		if (zytl_id!=-1) {
			try {
				dao.removeObject(Tf31_zytl.class, zytl_id);
				out.print("{\"statusCode\":\"200\", \"message\":\"删除人员信息成功!\"}");
			} catch (RuntimeException e) {
				e.printStackTrace();
				out.print("{\"statusCode\":\"300\", \"message\":\"删除人员信息失败!\"}");
			}
		} 
	}

	@RequestMapping("/wxdw/tlrToExcel.do")
	public ModelAndView tlrToExcel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String keyword=convertUtil.toString(request.getParameter("keyword"));
		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		String config = convertUtil.toString(request.getParameter("config"));
		String orderField = convertUtil.toString(request
				.getParameter("orderField"), "tlrxm");
		String orderDirection = convertUtil.toString(request
				.getParameter("orderDirection"), "desc");

		int k = 0;
		ConfigXML configXML = new ConfigXMLImpl();// 读取mbk配置文档
		ResultObject ro = null;
		StringBuffer hql = new StringBuffer("");
		List titleList = new LinkedList(); // 标题列表
		List colList = new LinkedList();// 列的字段值
		List docList = new LinkedList();// 表单数据
		Map<String, List> sheetMap = new HashMap<String, List>();
		List sheetList = new LinkedList();

		// 读取配置文件的标题列表
		String webinfpath = request.getSession().getServletContext()
				.getRealPath("WEB-INF");
		titleList = configXML.getTagListByConfig(config, webinfpath, "name");
		colList = configXML.getTagListByConfig(config, webinfpath,
				"columnName");
		Iterator it = colList.iterator();
		hql.append("select ");
		while (it.hasNext()) {
			if (k == 0)
				hql.append(" zytl." + ((it.next().toString()).toLowerCase()));
			else
				hql.append(" ,zytl." + ((it.next().toString()).toLowerCase()));
			k++;
		}
		hql.append(" from Tf31_zytl zytl where 1=1 ");
		hql.append("order by zytl.");
		hql.append(orderField);
		hql.append(" ");
		hql.append(orderDirection); 
		docList = queryService.searchList(hql.toString());

		sheetList.add(titleList);
		sheetList.add(docList);
		sheetMap.put("form_title", sheetList);
		request.setAttribute("ExcelName", "资源填录人信息.xls");
		request.setAttribute("sheetMap", sheetMap);
		return new ModelAndView("/export/toExcelWhithList.do");

	}

	/**
	 * 选择所属单位
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/wxdw/selectWxdw.do")
	public ModelAndView selectWxdw(HttpServletRequest request,
			HttpServletResponse response) {
		String view = "/WEB-INF/jsp/wxdw/baseBringBack.jsp";
		ModelMap modelMap = new ModelMap();
		StringBuffer hql = new StringBuffer();
		Integer pageNum = convertUtil.toInteger(
				request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request
				.getParameter("orderField"), "mc");
		String orderDirection = convertUtil.toString(request
				.getParameter("orderDirection"), "asc");
		String keyword = convertUtil.toString(request.getParameter("keyword"));
		Integer totalCount = 0;

		List objList = new ArrayList();
		hql.append("select t.id,t.mc from Tf01_wxdw t where 1=1 ");
		hql.append("and t.lb='施工' ");
		if (!keyword.equals("")) {
			hql.append(" and t.mc like '%");
			hql.append(keyword);
			hql.append("%' ");
		}
		hql.append("order by t.");
		hql.append(orderField);
		hql.append(" ");
		hql.append(orderDirection);
		ResultObject ro = queryService.searchByPage(hql.toString(), pageNum,
				numPerPage);

		if (ro != null) {
			objList = ro.getList();
			totalCount=ro.getTotalRows();
		}

		modelMap.put("objList", objList);
		modelMap.put("keywork", keyword);
		modelMap.put("totalCount", totalCount);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("pageNum", pageNum);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		return new ModelAndView(view, modelMap);
	}
}
