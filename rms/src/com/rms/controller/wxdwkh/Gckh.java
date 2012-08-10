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
//		String sql = "select t.khx as khx,t.ms as ms,t.zdfz as zdfz,t.jgxx as jgxx,t.jgfz as jgfz,t.jsfs as jsfs from ("
//				+ "select khx,ms,zdfz,jgxx,jgfz,jsfs from Tf16_xmkhdf where project_id = 28 and lb = '"+lb+"' "
//				+ "union all" + " select khx,ms,fz zdfz,null as jgxx,null as jgfz,jsfs from Tf15_khxwh tf15"
//				+ "  where not exists " + "(select 'x' from Tf16_xmkhdf tf16" + " where tf15.khx = tf16.khx"
//				+ " and tf15.lb = tf16.lb " + " and project_id = "+project_id+")" + "and lb = '"+lb+"') t";
		

		String sql = "select tf16.khx as khx,tf16.ms as ms,tf16.zdfz as zdfz,tf16.jgxx as jgxx,tf16.jgfz as jgfz,tf16.jsfs as jsfs "
			+ "from Tf16_xmkhdf tf16 where project_id = "+project_id+" and lb = '"+lb+"' "
			+ "union all" 
			+ " select tf15.khx as khx,tf15.ms as ms,tf15.fz as zdfz,null as jgxx,null as jgfz,tf15.jsfs as jsfs from Tf15_khxwh tf15"
			+ "  where not exists " + "(select 'x' from Tf16_xmkhdf tf16" + " where tf15.khx = tf16.khx"
			+ " and tf15.lb = tf16.lb " + " and project_id = "+project_id+")" + "and lb = '"+lb+"'";
		// Ta03_user user =
		// (Ta03_user)request.getSession().getAttribute("user");
		// user.getId();
		// user.getName()
		// List <Tf15_khxwh>tf15List=(List<Tf15_khxwh>)
		// queryService.searchList("from
		// Tf15_khxwh as k where k.lb='"+lb+"'");
		// List <Tf16_xmkhdf>tf16List=(List<Tf16_xmkhdf>)
		// queryService.searchList("from
		// Tf16_xmkhdf as x where x.lb='"+lb+"'" );
		// if (tf15List!=null&&tf15List.size()!=0) {
		// request.setAttribute("gc", tf15List);
		// result=1;
		// } else if((tf15List.size()==0||tf15List==null)&&tf16List!=null){
		// request.setAttribute("gc", tf16List);
		// result=2;
		// }else{
		// result=3;
		// }
		// System.out.println(result);
		List gckhList = dao.search(sql);
//		request.setAttribute("project_id", project_id);
		request.setAttribute("curDate", new Date());
		// request.setAttribute("result", result);
		return new ModelAndView("/WEB-INF/jsp/wxdwkh/gckh.jsp");
	}

	@RequestMapping("/wxdwkh/deleteTf15.do")
	public void deleteTf15(HttpServletRequest request, HttpServletResponse response) {

	}
}
