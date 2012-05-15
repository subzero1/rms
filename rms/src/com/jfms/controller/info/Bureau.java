package com.jfms.controller.info;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jfms.dataObjects.base.Tc01_property;
import com.jfms.dataObjects.base.Tc02_bureau;
import com.jfms.dataObjects.base.Tc03_area;
import com.netsky.base.baseDao.Dao;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.utils.convertUtil;

import com.netsky.base.dataObjects.Ta01_dept;
import com.netsky.base.dataObjects.Ta02_station;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Te03_online;

/**
 * @description: 部门维护
 * @class name:com.netsky.base.controller.Dept
 * @author Administrator Mar 8, 2011
 */
@Controller
public class Bureau {
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
	 * 日志处理类
	 */
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * 部门列表
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/sysManage/bureauList.do")
	public ModelAndView bureauList(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		ModelMap modelMap = new ModelMap();
		Map bureauListMap = new HashMap();
		List<Tc02_bureau> bureauList = (List<Tc02_bureau>) dao
				.search("from Tc02_bureau where p_area in (select area.name from Tc03_area area where area.flag like'%[2]%') order by p_area,name");
		List tmpList = null;// 前序遍历结果节点列表
		String p_area = "地区";
		for (Tc02_bureau tc02 : bureauList) {
			if (!tc02.getP_area().equals(p_area)) {
				if (tmpList != null && tmpList.size() > 0) {
					bureauListMap.put(p_area, tmpList);
				}
				tmpList = new LinkedList();
				p_area = tc02.getP_area();
			}
			tmpList.add(tc02);
		}
		bureauListMap.put(p_area, tmpList);
		modelMap.put("bureauListMap", bureauListMap);
		List list = dao
		.search("  from Tc03_area where flag like'%[2]%' order by id ");
		modelMap.put("areaList", list);
		return new ModelAndView("/WEB-INF/jsp/info/bureauList.jsp", modelMap);
	}

	@RequestMapping("/sysManage/bureauEdit.do")
	public ModelAndView bureauEdit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = convertUtil.toLong(request.getParameter("id"), -1L);
		ModelMap modelMap = new ModelMap();
		modelMap.put("areaList",dao.search("from Tc03_area where flag like'%[2]%'"));
		Tc02_bureau bureau = (Tc02_bureau) dao.getObject(Tc02_bureau.class, id);
		modelMap.put("bureauObj", bureau);
		List<Tc01_property> tc01List = (List<Tc01_property>)queryService.searchList("from Tc01_property where type='局点性质'");
		modelMap.put("jdxzList", tc01List);
		return new ModelAndView("/WEB-INF/jsp/info/bureauEdit.jsp", modelMap);
	}

	@RequestMapping("/sysManage/ajaxbureauDel.do")
	public ModelAndView ajaxbureauDel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding(request.getCharacterEncoding());
		Long id = convertUtil.toLong(request.getParameter("id"), -1L);
		ModelMap modelMap = new ModelMap();
		PrintWriter out = null;

		// 获取用户对象
		try {
			out = response.getWriter();
			dao.removeObject(Tc02_bureau.class, id);
			out
					.print("{\"statusCode\":\"200\", \"message\":\"局点删除成功\", \"navTabId\":\"\", \"forwardUrl\":\"sysManage/bureauList.do\", \"callbackType\":\"forward\"}");
		} catch (IOException e) {
			exceptionService.exceptionControl(
					"com.crht.controller.sysManage.User", "局点删除失败", e);
		}
		return null;
	}
}
