package com.rms.controller.wxdw;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;


@Controller
public class Wxdw {
	/**
	 * 异常捕捉
	 */
	@Autowired
	private ExceptionService exceptionService;

	/**
	 * 查询服务
	 */
	@Autowired
	private QueryService queryService;

	/**
	 * 保存服务
	 */
	@Autowired
	private SaveService saveService;

	/**
	 * 外协单位列表
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/wxdwList.do")
	public ModelAndView wxdwList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		return new ModelAndView("/WEB-INF/jsp/wxdw/wxdwList.jsp");
	}
	
	/**
	 * 外协单位信息
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/wxdwEdit.do")
	public ModelAndView wxdwEdit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView("/WEB-INF/jsp/wxdw/wxdwEdit.jsp", modelMap);
	}
	
	/**
	 *用户配置
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/wxdwUserEdit.do")
	public ModelAndView wxdwUserEdit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView("/WEB-INF/jsp/wxdw/wxdwUserEdit.jsp", modelMap);
	}
	
	/**
	 * 区域专业
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/qyZyUserEdit .do")
	public ModelAndView qyZyUserEdit (HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView("/WEB-INF/jsp/wxdw/qyZyUserEdit .jsp", modelMap);
	}
	
	/**
	 * 份额占比
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/fezbEdit.do")
	public ModelAndView fezbEdit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView("/WEB-INF/jsp/wxdw/fezbEdit.jsp", modelMap);
	}
	
	/**
	 * 最大在建工程数
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/zjgcsUserEdit .do")
	public ModelAndView zjgcsUserEdit (HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView("/WEB-INF/jsp/wxdw/zjgcsUserEdit .jsp", modelMap);
	}
	
	/**
	 * 关联交易额
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/gljyeEdit.do")
	public ModelAndView gljyeEdit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView("/WEB-INF/jsp/wxdw/gljyeEdit.jsp", modelMap);
	}
	
	/**
	 * 施工队
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/sgdEdit.do")
	public ModelAndView sgdEdit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView("/WEB-INF/jsp/wxdw/sgdEdit.jsp", modelMap);
	}

}
