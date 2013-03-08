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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
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
	 * 
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

		String ssdw = convertUtil.toString(request.getParameter("ssdw"));
		Integer totalCount = 0;
		List tf31_zytlrList = null;

		hql
				.append("select t,(select w.mc from Tf01_wxdw w where t.ssdw=w.mc) as mc from Tf31_zytl t ");
		hql.append("where 1=1 ");
		if (keyword != "") {
			hql.append(" and t.tlrxm like '%");
			hql.append(keyword);
			hql.append("%' ");
		}
		if (ssdw != "") {
			hql.append(" and t.ssdw='");
			hql.append(ssdw);
			hql.append("'");
		}
		hql.append("order by t.");
		hql.append(orderField);
		hql.append(" ");
		hql.append(orderDirection);

		ResultObject ro = queryService.searchByPage(hql.toString(), pageNum,
				numPerPage);
		if (ro != null) {
			totalCount = ro.getTotalRows();
			tf31_zytlrList = new LinkedList();
			while (ro.next()) {
				Object obj[] = new Object[2];
				obj[0] = (Tf31_zytl) ro.get("t");
				obj[1] = ro.get("w.mc");
				tf31_zytlrList.add(obj);
			}
		}

		// 所属单位列表
		hql.delete(0, hql.length());
		hql.append("select distinct(l.ssdw) from Tf31_zytl l ");
		List wxdwList = queryService.searchList(hql.toString());

		modelMap.put("ssdw", ssdw);
		modelMap.put("wxdwList", wxdwList);
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
	 * 
	 * @param request
	 * @param response
	 *            void
	 * @throws IOException,Exception
	 */
	@RequestMapping("/wxdw/tlrAjaxDel.do")
	public void tlrAjaxDel(HttpServletRequest request,
			HttpServletResponse response) throws IOException, Exception {
		String zytl_ids = convertUtil
				.toString(request.getParameter("zytl_ids"));
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		StringBuffer hql = new StringBuffer();
		hql.append("delete from Tf31_zytl zytl where zytl.id in (");
		hql.append(zytl_ids);
		hql.append(")");
		if (!zytl_ids.equals("")) {
			try {
				dao.update(hql.toString());
				out
						.print("{\"statusCode\":\"200\", \"message\":\"删除人员信息成功!\"}");
			} catch (RuntimeException e) {
				e.printStackTrace();
				out
						.print("{\"statusCode\":\"300\", \"message\":\"删除人员信息失败!\"}");
			}
		} else {
			out.print("{\"statusCode\":\"300\", \"message\":\"人员信息未选择!\"}");
		}
	}

	@RequestMapping("/wxdw/tlrToExcel.do")
	public ModelAndView tlrToExcel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String keyword = convertUtil.toString(request.getParameter("keyword"));
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
		colList = configXML
				.getTagListByConfig(config, webinfpath, "columnName");
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
	 * 
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
			totalCount = ro.getTotalRows();
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

	/**
	 * 
	 * @param request
	 * @param response
	 *            void
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping("/wxdw/getCompanyAjax.do")
	public void getCompanyAjax(HttpServletRequest request,
			HttpServletResponse response) throws IOException, JSONException {
		response.setCharacterEncoding("utf-8");
		String ssdw = convertUtil.toString(request.getParameter("ssdw"));
		StringBuffer hql = new StringBuffer();
		PrintWriter out = null;
		List ssdwList = null;
		hql.append("select w.mc from Tf01_wxdw w where 1=1 and rownum<10 ");
		if (!ssdw.equals("") && ssdw != null) {
			hql.append("and w.mc like '%");
			char[] ssdwChar = ssdw.toString().toCharArray();
			for (int i = 0; i < ssdwChar.length; i++) {
				hql.append(ssdwChar[i]);
				hql.append("%");
			}
			hql.append("' ");
			hql.append("order by w.mc");
			ssdwList = queryService.searchList(hql.toString());
		}

		if (ssdwList != null && ssdwList.size() != 0) {
			JSONArray json = new JSONArray();
			for (Object object : ssdwList) {
				JSONObject jo = new JSONObject();
				jo.put("ssdw", object);
				json.put(jo);
			}
			out = response.getWriter();
			out.print(json.toString());
		}

	}
}
