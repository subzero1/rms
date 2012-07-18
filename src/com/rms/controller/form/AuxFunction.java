package com.rms.controller.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.netsky.base.dataObjects.Ta03_user;


@Controller
public class AuxFunction {

	@Autowired
	private Dao dao;

	@Autowired
	private QueryService queryService;

	@RequestMapping("/form/xzgcForDblx.do")
	public ModelAndView xzgcForDblx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelMap modelMap = new ModelMap();
		StringBuffer sql = new StringBuffer("");
		Long user_id = null;
		Long xm_id = convertUtil.toLong(request.getParameter("xm_id"));
		HttpSession session = request.getSession();
		if(session != null){
			Ta03_user ta03 = (Ta03_user)session.getAttribute("user");
			user_id = ta03.getId();
		}
		
		/*
		 * 获得未选工程列表
		 */
		sql.delete(0, sql.length());
		sql.append("select td00 ");
		sql.append("from Td00_gcxx td00 ");
		sql.append("where exists ( ");
		sql.append("select 'x' from Tb15_docflow tb15 ");
		sql.append("where td00.id = tb15.project_id and tb15.node_id = 10201 and tb15.user_id = ");
		sql.append(user_id.toString());
		sql.append(")");
		sql.append("and xm_id is null  order by gcmc ");
		List list = queryService.searchList(sql.toString()) ;
		modelMap.put("unselect_groups", list);
		
		/*
		 * 获得已选工程列表
		 */
		sql.delete(0, sql.length());
		sql.append("select td00 ");
		sql.append("from Td00_gcxx td00 ");
		sql.append("where xm_id = ");
		sql.append(xm_id);
		list = queryService.searchList(sql.toString()) ;
		modelMap.put("select_groups", list);
		
		return new ModelAndView("/WEB-INF/jsp/form/xzgcForDblx.jsp", modelMap);
	}
}
