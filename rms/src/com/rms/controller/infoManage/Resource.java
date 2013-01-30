package com.rms.controller.infoManage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description:
 * 
 * @class name:com.rms.controller.infoManage.Resource
 * @author net Jan 29, 2013
 */
@Controller
public class Resource {
	@RequestMapping("/infoManage/zytlEdit.do")
	public ModelAndView zytlEdit(HttpServletRequest request,HttpServletResponse response) {
		String view="/WEB-INF/jsp/infoManage/zytlEdit.jsp";
		ModelMap modelMap=new ModelMap();
		return new ModelAndView(view,modelMap);
	}
}
