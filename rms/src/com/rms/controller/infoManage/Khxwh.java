package com.rms.controller.infoManage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.rms.dataObjects.wxdw.Tf15_khxwh;

@Controller
public class Khxwh {
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
	 * 保存考核
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/infoManage/khxsave.do")
	public ModelAndView khedit(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding(request.getCharacterEncoding());
		String khx = request.getParameter("khx");
		String ms = request.getParameter("ms");
		Long fz = convertUtil.toLong(request.getParameter("fz"));
		String jsfs = request.getParameter("jsfs");
		String lb = request.getParameter("lb");
		Tf15_khxwh tf15_khxwh = new Tf15_khxwh();
		tf15_khxwh.setKhx(khx);
		tf15_khxwh.setMs(ms);
		tf15_khxwh.setFz(fz);
		tf15_khxwh.setLb(lb);
		tf15_khxwh.setJsfs(jsfs);
		dao.saveObject(tf15_khxwh);
		return new ModelAndView("/WEB-INF/jsp/infoManage/khxwh.jsp");
	}

	/**
	 * 转到考核项维护页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/infoManage/khxwh.do")
	public ModelAndView khunsert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String orderField = convertUtil.toString(request.getParameter("orderField"), "lb");
		String orderDirection = convertUtil.toString(request.getParameter("sort"), "desc");
		List<Tf15_khxwh> tf15List = (List<Tf15_khxwh>) queryService.searchList("from Tf15_khxwh order by "+orderField + " " + orderDirection);
		request.setAttribute("tf15List", tf15List);
		request.setAttribute("sort", "ASC");
		return new ModelAndView("/WEB-INF/jsp/infoManage/khxwh.jsp");

	}

	/**
	 * 按类别排序
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/infoManage/sortbyl.do")
	public ModelAndView sortByLB(HttpServletRequest request,HttpServletResponse response){ 
		String sort=request.getParameter("sort"); 
		List<Tf15_khxwh> tf15List = (List<Tf15_khxwh>) queryService.searchList("from Tf15_khxwh order by lb "+sort);
		request.setAttribute("tf15List", tf15List);
		if(sort.equals("ASC"))
			request.setAttribute("sort", "DESC");
		else if(sort.equals("DESC"))
			request.setAttribute("sort", "ASC");
		return new ModelAndView("/WEB-INF/jsp/infoManage/khxwh.jsp");
	}
	
	/**
	 * 按分值排序
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/infoManage/sortbyfz.do")
	public ModelAndView sortByFZ(HttpServletRequest request,HttpServletResponse response){ 
		String sort=request.getParameter("sort"); 
		List<Tf15_khxwh> tf15List = (List<Tf15_khxwh>) queryService.searchList("from Tf15_khxwh order by lb "+sort);
		request.setAttribute("tf15List", tf15List);
		if(sort.equals("ASC"))
			request.setAttribute("sort", "DESC");
		else if(sort.equals("DESC"))
			request.setAttribute("sort", "ASC");
		return new ModelAndView("/WEB-INF/jsp/infoManage/khxwh.jsp");
	}

}
