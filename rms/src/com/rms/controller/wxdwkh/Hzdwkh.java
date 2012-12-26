package com.rms.controller.wxdwkh;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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

import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.QueryService;
import com.rms.dataObjects.base.Tc10_hzdw_khpz;
import com.rms.dataObjects.wxdw.Tf19_khxx;
import com.rms.dataObjects.wxdw.Tf20_khxxmx;

@Controller
public class Hzdwkh {
	/**
	 * 查询服务
	 */
	@Autowired
	private QueryService queryService;
	
	
	/**
	 *  考核信息列表
	 */
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdwkh/hzdwkhList.do")
	public ModelAndView hzdwkhList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		// 分页
		Integer totalPages = 1;
		Integer totalCount = 0;
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request.getParameter("orderField"), "tf19.id");
		if (orderField.equals("")) {
			orderField = "id";
		}
		String orderDirection = convertUtil.toString(request.getParameter("orderDirection"), "desc");
		if (orderDirection.equals("")) {
			orderDirection = "desc";
		}
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		
		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		String user_name=user.getName();
		String user_dept = user.getDept_name();
		
		// 查询条件
		Long khnr_id = convertUtil.toLong(request.getParameter("khnr"),-1L);
		
		StringBuffer hsql = new StringBuffer();
		hsql.append("select tf19,tc10 from Tf19_khxx tf19,Tc10_hzdw_khpz tc10 where tf19.kh_id=tc10.id");
				
		// 关键字
		if (khnr_id!=-1) {
			hsql.append(" and tf19.kh_id=" + khnr_id);
		}		
				
		// order排序
		hsql.append(" order by " + orderField);
		hsql.append(" " + orderDirection);
		
		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum, numPerPage);
		
		// 获取结果集
		List<Object> khxxList = new ArrayList<Object>();
		String limit = "";
		Long node_id = -1L;
		while (ro.next()) {	
			Map<String, Object> khxxMap = new HashMap<String, Object>();
			khxxMap.put("tf19", (Tf19_khxx) ro.get("tf19"));
			khxxMap.put("tc10", (Tc10_hzdw_khpz) ro.get("tc10"));	
			khxxList.add(khxxMap);
		}
		
		modelMap.put("khxxList", khxxList);
		
		// 考核内容
		List<String> khnrList = (List<String>) queryService
				.searchList("select id,mc from Tc10_hzdw_khpz where useflag=1 order by id");
		modelMap.put("khnrList", khnrList);
		
		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		
		return new ModelAndView("/WEB-INF/jsp/wxdwkh/hzdwkhList.jsp", modelMap);

	}
	
	/**
	 *  考核评分
	 */
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdwkh/khpf.do")
	public ModelAndView khpf(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		ModelMap modelMap = new ModelMap();
		Long khxx_id = convertUtil.toLong(request.getParameter("khxx_id"),-1L); //tf19.id
		
		//获取tf19信息
		Tf19_khxx tf19 = (Tf19_khxx) queryService.searchById(Tf19_khxx.class, khxx_id);
		modelMap.put("tf19", tf19);
		
		Long kh_id = tf19==null?-1L:tf19.getKh_id();
		//获取tc10信息
		Tc10_hzdw_khpz tc10 = (Tc10_hzdw_khpz) queryService.searchById(Tc10_hzdw_khpz.class, kh_id);
		String wxdw_lb = tc10.getDwlb();//设计，施工，监理，审计
		
		//用户信息
		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		Long user_id = user.getId();
		
		// 考核项tc11
		List<String> khxList = (List<String>) queryService
				.searchList("select tc11 from Tc11_khpzmx tc11 where kh_id=" + kh_id + " order by id");
		modelMap.put("khxList", khxList);
		
		// 合作单位
		List<String> hzdwList = (List<String>) queryService
				.searchList("select tf01 from Tf01_wxdw tf01 where lb='"+ wxdw_lb +"' order by id");
		modelMap.put("hzdwList", hzdwList);
				
		List<Tf20_khxxmx> khxxmxList = (List<Tf20_khxxmx>) queryService
				.searchList("from Tf20_khxxmx where kh_id=" + khxx_id + " and user_id=" + user_id + " order by khx_id,wxdw_id");
		Long wxdw = -1L;
		Map<String, Map<String, Tf20_khxxmx>> khpfMap = new HashMap<String, Map<String, Tf20_khxxmx>>();
		Map<String, Tf20_khxxmx> khMap = new HashMap<String, Tf20_khxxmx>();
		for (Tf20_khxxmx tf20 : khxxmxList) {
			if (wxdw != tf20.getWxdw_id()) {
				if (!khMap.isEmpty()) {
					khpfMap.put(wxdw.toString(), khMap);
					khMap = new HashMap<String, Tf20_khxxmx>();
				}
				wxdw = tf20.getWxdw_id();
			}
			khMap.put(tf20.getKhx_id().toString(), tf20);
		}
		if (!khMap.isEmpty()) {
			khpfMap.put(wxdw.toString(), khMap);
		}
		modelMap.put("khpfMap", khpfMap);
		
		return new ModelAndView("/WEB-INF/jsp/wxdwkh/khpf.jsp", modelMap);

	}
	
	/**
	 *  查看评分结果
	 */
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdwkh/khpfView.do")
	public ModelAndView khpfView(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		Long khxx_id = convertUtil.toLong(request.getParameter("khxx_id"),-1L); //tf19.id
		
		//获取tf19信息
		Tf19_khxx tf19 = (Tf19_khxx) queryService.searchById(Tf19_khxx.class, khxx_id);
		modelMap.put("tf19", tf19);
		
		Long kh_id = tf19==null?-1L:tf19.getKh_id();
		//获取tc10信息
		Tc10_hzdw_khpz tc10 = (Tc10_hzdw_khpz) queryService.searchById(Tc10_hzdw_khpz.class, kh_id);
		String wxdw_lb = tc10.getDwlb();//设计，施工，监理，审计
		
		//用户信息
		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		Long user_id = user.getId();
		
		// 考核项tc11
		List<String> khxList = (List<String>) queryService
				.searchList("select tc11 from Tc11_khpzmx tc11 where kh_id=" + kh_id + " order by id");
		modelMap.put("khxList", khxList);
		
		// 合作单位
		List<String> hzdwList = (List<String>) queryService
				.searchList("select tf01 from Tf01_wxdw tf01 where lb='"+ wxdw_lb +"' order by id");
		modelMap.put("hzdwList", hzdwList);
				
		ResultObject rs =  queryService
					.search("select khx_id,wxdw_id,sum(fz) as fzhj from Tf20_khxxmx where kh_id=" + khxx_id + " group by khx_id,wxdw_id");
		Long wxdw = -1L;
		Map<String, Map<String, Tf20_khxxmx>> khpfMap = new HashMap<String, Map<String, Tf20_khxxmx>>();
		Map<String, Tf20_khxxmx> khMap = new HashMap<String, Tf20_khxxmx>();
		while (rs.next()) {
			Tf20_khxxmx tf20 = new Tf20_khxxmx();
			tf20.setWxdw_id(convertUtil.toLong(rs.get("wxdw_id")));
			tf20.setKhx_id(convertUtil.toLong(rs.get("khx_id")));
			tf20.setFz(convertUtil.toLong(rs.get("fzhj")));
			
			if (wxdw != tf20.getWxdw_id()) {
				if (!khMap.isEmpty()) {
					khpfMap.put(wxdw.toString(), khMap);
					khMap = new HashMap<String, Tf20_khxxmx>();
				}
				wxdw = tf20.getWxdw_id();
			}
			khMap.put(tf20.getKhx_id().toString(), tf20);
		}
		if (!khMap.isEmpty()) {
			khpfMap.put(wxdw.toString(), khMap);
		}
		modelMap.put("khpfMap", khpfMap);
		
		return new ModelAndView("/WEB-INF/jsp/wxdwkh/khpfView.jsp", modelMap);

	}
	
	/**
	 *  考核说明
	 */
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdwkh/khsm.do")
	public ModelAndView khsm(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		Long kh_id = convertUtil.toLong(request.getParameter("kh_id"),-1L); //tf19.id
		
		//获取tc10信息
		Tc10_hzdw_khpz tc10 = (Tc10_hzdw_khpz) queryService.searchById(Tc10_hzdw_khpz.class, kh_id);
		modelMap.put("tc10", tc10);		
		
		// 考核项tc11
		List<String> khxList = (List<String>) queryService
				.searchList("select tc11 from Tc11_khpzmx tc11 where kh_id=" + kh_id + " order by id");
		modelMap.put("khxList", khxList);		
		
		return new ModelAndView("/WEB-INF/jsp/wxdwkh/khsm.jsp", modelMap);

	}
}
