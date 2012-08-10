package com.rms.controller.wxdwkh;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.rms.dataObjects.wxdw.Tf15_khxwh;
import com.rms.dataObjects.wxdw.Tf16_xmkhdf;
import com.netsky.base.dataObjects.Ta03_user;

@Controller
public class Gckh {
	/**
	 * 数据服务
	 */
	@Autowired
	private Dao dao;

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
	 * 日志处理类
	 */
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * 三种类别的项目评价
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/wxdwkh/gcdf.do")
	public ModelAndView gckh(HttpServletRequest request, HttpServletResponse response) {

		// int result = 0;
		Long project_id = convertUtil.toLong(request.getParameter("project_id"));
		String lb = request.getParameter("lb");

	    String sql1 = " select tf15.khx as khx,tf15.ms as ms,tf15.fz as zdfz,tf15.jsfs as jsfs,tf15.lb as lb from Tf15_khxwh tf15"
			+ "  where not exists " + "(select 'x' from Tf16_xmkhdf tf16" + " where tf15.khx = tf16.khx"
			+ " and tf15.lb = tf16.lb " + " and project_id = "+project_id+")" + "and lb = '"+lb+"'";   
		String sql2 = 
			"from Tf16_xmkhdf tf16 where project_id = "+project_id+" and lb = '"+lb+"' ";
			 
		List gckhList1 =  dao.search(sql1);
		List <Tf16_xmkhdf>gckhList2 =  (List<Tf16_xmkhdf>) dao.search(sql2);
		request.setAttribute("gc1", gckhList1);
		request.setAttribute("gc2", gckhList2);
		request.setAttribute("curDate", new Date());
		request.setAttribute("project_id", project_id); 
		return new ModelAndView("/WEB-INF/jsp/wxdwkh/gckh.jsp");
	}

}
