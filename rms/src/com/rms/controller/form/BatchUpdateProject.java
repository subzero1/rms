package com.rms.controller.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;


@Controller
public class BatchUpdateProject {

	@Autowired
	private Dao dao;

	@Autowired
	private QueryService queryService;

	@SuppressWarnings("unchecked")
	@RequestMapping("/form/importBatchProjects.do")
	public String sgpd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return null;
	}

	@RequestMapping("/form/downloadExcelModel.do")
	public String sgpftst(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//return new ModelAndView("/WEB-INF/jsp/form/sgpftst.jsp", modelMap);
		return null;
	}
}
