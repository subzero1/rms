package com.rms.controller.wxdw;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Te01_slave;
import com.netsky.base.dataObjects.Te02_jlfk;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.service.others.GeneralService;
import com.rms.dataObjects.wxdw.Tf01_wxdw;
import com.rms.dataObjects.wxdw.Tf09_wxgg;

@Controller
public class Wxgg {
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

	@Autowired
	private GeneralService generalService;
	
	/**
	 * 外协公告列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/wxdw/wxggList.do")
	public ModelAndView wxggList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		modelMap.put("role_id", request.getParameter("role_id"));
		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		if (user == null) {
			return exceptionService.exceptionControl(this.getClass().getName(), "用户未登录或登录超时",
					new Exception("用户未登录"));
		}
		// 分页
		Integer totalPages = 1;
		Integer totalCount = 0;
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request.getParameter("orderField"), "id");
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
		// 查询条件
		String fbr_search = convertUtil.toString(request.getParameter("fbr_search"));
		StringBuffer hsql = new StringBuffer();
		String sql = "select tf01.mc from Tf01_wxdw tf01,Tf04_wxdw_user tf04 where tf01.id=tf04.wxdw_id and tf04.user_id="
				+ user.getId();
		ResultObject ro1 = queryService.search(sql);
		boolean isWxdw = false;
		String wxdw_mc = "";
		hsql.append("");
		if (ro1.next()) {
			isWxdw = true;
			wxdw_mc = ro1.get("tf01.mc").toString();
			hsql.append("select tf09 from Tf09_wxgg tf09 where mbdw like '%" + wxdw_mc + "%' and (zt=1 or fbr_id="
					+ user.getId() + ") ");
		} else {
			hsql.append("select tf09 from Tf09_wxgg tf09 where (zt=1 or fbr_id=" + user.getId() + ") ");
		}
		modelMap.put("isWxdw", isWxdw);
		if (!"".equals(fbr_search)) {
			hsql.append(" and (tf09.fbr_mc like '%" + fbr_search + "%' or tf09.ggzt like '%" + fbr_search + "%') ");
		}
		// orderBy
		hsql.append(" order by " + orderField);
		// orderDirection
		hsql.append(" " + orderDirection);
		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum, numPerPage);
		// 获取结果集
		List<Object[]> wxggList = new ArrayList<Object[]>();
		while (ro.next()) {
			Object[] o = new Object[5];
			Tf09_wxgg tf09 = (Tf09_wxgg) ro.get("tf09");
			// 已读未读
			if (isWxdw) {
				o[1] = convertUtil.toLong(tf09.getMbdw().indexOf(wxdw_mc) != -1 ? "1" : "0");
			}
			// 发送数
			o[2] = convertUtil.toString(tf09.getMbdw()).split("\\,").length;
			// 已阅数
			o[3] = convertUtil.toString(tf09.getCydw()).split("\\,").length;
			// 回复数
			String sql4 = "select count(*) as num from Te02_jlfk where module_id=3007 and project_id=" + tf09.getId();
			ResultObject ro4 = queryService.search(sql4);
			if (ro4.next()) {
				o[4] = ro4.get("num").toString();
			}
			o[0] = tf09;
			wxggList.add(o);
		}
		modelMap.put("wxggList", wxggList);
		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		return new ModelAndView("/WEB-INF/jsp/wxdw/wxggList.jsp", modelMap);
	}

	/**
	 * 外协公告
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/wxdw/wxgg.do")
	public ModelAndView wxgg(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		String role_id = convertUtil.toString(request.getParameter("role_id"));
		modelMap.put("role_id", role_id);
		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");

		Long id = convertUtil.toLong(request.getParameter("id"));
		Tf09_wxgg wxgg = (Tf09_wxgg) queryService.searchById(Tf09_wxgg.class, id);

		String lb = "";
		String sql_tj = "and tf01.lb='施工'";
		if (wxgg != null && !"全部".equals(wxgg.getWxdw_lb())) {
			sql_tj = " and tf01.lb='" + wxgg.getWxdw_lb() + "'";
			lb = wxgg.getWxdw_lb();
		} else {
			lb = "施工";
		}
		modelMap.put("lb", lb);
		if (wxgg != null) {
			//
			Map<String, Object> mbdw_map = new HashMap<String, Object>();
			Map<String, Object> cydw_map = new HashMap<String, Object>();
			List<String> mbdw_list = new ArrayList<String>();
			String mbdw_str = wxgg.getMbdw();
			if (mbdw_str != null && !"".equals(mbdw_str)) {
				String[] mbdw_sz = mbdw_str.split(",");
				for (int m = 0; m < mbdw_sz.length; m++) {
					mbdw_map.put(mbdw_sz[m], mbdw_sz[m]);
					mbdw_list.add(mbdw_sz[m]);
				}
			}

			String cydw_str = wxgg.getCydw();
			if (cydw_str != null && !"".equals(cydw_str)) {
				String[] cydw_sz = cydw_str.split(",");
				for (int m = 0; m < cydw_sz.length; m++) {
					cydw_map.put(cydw_sz[m], cydw_sz[m]);
				}
			}
			modelMap.put("mbdw_list", mbdw_list);
			modelMap.put("mbdw", mbdw_map);
			modelMap.put("cydw", cydw_map);
			modelMap.put("wxgg", wxgg);
			//
			if ("1".equals(wxgg.getZt())) {
				role_id = "-1";
			}
			// 如果是目标单位 修改已读单位
			String sql3 = "select tf01.mc as mc from Tf01_wxdw tf01,Tf04_wxdw_user tf04 where tf01.id=tf04.wxdw_id and tf04.user_id="
					+ user.getId();
			ResultObject ro3 = queryService.search(sql3);
			// 如果是合作单位，判断是否属于目标单位
			if (ro3.next()) {
				if ((wxgg.getCydw() != null && wxgg.getCydw().indexOf((String) ro3.get("mc")) == -1)
						|| wxgg.getCydw() == null) {
					String sql5 = "";
					if (wxgg.getCydw() == null || wxgg.getCdfw().equals("")) {
						sql5 = "update Tf09_wxgg set cydw='" + ro3.get("mc") + "' where id=" + id;
					} else {
						sql5 = "update Tf09_wxgg set cydw=cydw||'," + ro3.get("mc") + "' where id=" + id;
					}
					saveService.updateByHSql(sql5);
				}
			}

			// 附件
			modelMap.put("list_fj", queryService.searchList("from Te01_slave where doc_id=" + id
					+ " and module_id=3007 order by ftp_date"));

			// 交流反馈
			List list_jlfk = new ArrayList();
			String sql_jlfk = "select te02.id as id,te02.user_id as user_id,ta03.name as name,te02.time as time,te02.yj as yj from Te02_jlfk te02,Ta03_user ta03 where te02.user_id=ta03.id and te02.module_id=3007 and te02.document_id="
					+ id;
			ResultObject ro_jlfk = queryService.search(sql_jlfk);
			while (ro_jlfk.next()) {
				Map<String, Object> mo = ro_jlfk.getMap();
				ResultObject ro = queryService
						.search("select tf01.mc as mc from Tf04_wxdw_user tf04,Tf01_wxdw tf01 where tf04.wxdw_id=tf01.id and tf04.user_id="
								+ mo.get("user_id").toString());
				if (ro.next())
					mo.put("wxdw", ro.get("mc").toString());
				else
					mo.put("wxdw", "");

				list_jlfk.add(mo);
			}
			modelMap.put("list_jlfk", list_jlfk);
		}
		// 获取目标单位 --- 合作单位

		List<Tf01_wxdw> list_mbdw = (List<Tf01_wxdw>) queryService
				.searchList("from Tf01_wxdw tf01 where 1=1 " + sql_tj);
		modelMap.put("list_mbdw", list_mbdw);
		// 是否是修改
		modelMap.put("modify", "no");
		// 加上了修改权限
		if (!"20312".equals(role_id)) {
			modelMap.put("ggLimit", "disabled");
		}
		return new ModelAndView("/WEB-INF/jsp/wxdw/wxgg.jsp", modelMap);
	}

	/**
	 * 获得合作单位AJAX
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *             void
	 */
	@RequestMapping("/wxdw/getWxdwAjax.do")
	public void getWxdwAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		StringBuffer msg = new StringBuffer();
		String lb = convertUtil.toString(request.getParameter("lb"), "施工");
		String ggLimit = convertUtil.toString(request.getParameter("ggLimit"), "");
		List<Tf01_wxdw> tf01List = (List<Tf01_wxdw>) queryService.searchList("from Tf01_wxdw where lb='" + lb + "'");
		msg.append("<div style=\"width: 100%; display: inline-block; margin-left: 100px;\">");
		for (int i = 0; i < tf01List.size(); i++) {
			Tf01_wxdw wxdw = tf01List.get(i);
			msg.append("<p style=\"width: 220px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;\""
					+ " title=\"" + wxdw.getMc() + "\">" + "<input " + ggLimit
					+ "  type=\"checkbox\" id=\"mbdw\" name=\"mbdw\"" + " value=\"" + wxdw.getMc() + "\""
					+ " onClick=\"set_c('mbdw','Tf09_wxgg.MBDW')\">" + "&nbsp;"
					+ "<span onclick=\"javascript:spanclick(this);\" style=\"cursor: pointer\">" + wxdw.getMc()
					+ "</span>" + "</p>");
			if (i % 3 == 2) {
				msg.append("</div><div style=\"width: 100%; display: inline-block; margin-left: 100px;\">");
			}
		}
		msg.append("</div>");
		out.print(msg);
		out.flush();
	}
	
	/**
	 * 删除外协公告AJAX
	 * @param request
	 * @param response
	 * @throws Exception void
	 */
	@RequestMapping("/wxdw/delWxgg.do")
	public void delWxgg(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		Long id = convertUtil.toLong(request.getParameter("id"));
		Tf09_wxgg wxgg = (Tf09_wxgg)queryService.searchById(Tf09_wxgg.class, id);
		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		String err = "";
		if (user != null){
			if (wxgg != null){
				if (wxgg.getFbr_id().equals(user.getId())){
					saveService.updateByHSql("delete Tf09_wxgg where id="+id);
					saveService.updateByHSql("delete Te02_jlfk where module_id=3007 and project_id="+id);
					List<Te01_slave> te01List = (List<Te01_slave>)queryService.searchList("from Te01_slave where module_id=3007 and project_id="+id);
					for (Te01_slave slave : te01List) {
						generalService.deleteFtpFile(slave.getFtp_url(), request.getSession().getServletContext().getRealPath("/"));
					}
					saveService.updateByHSql("delete Te01_slave where module_id=3007 and project_id="+id);
					out
					.print("{\"statusCode\":\"200\", \"message\":\"删除成功\", \"navTabId\":\"\", \"forwardUrl\":\"wxdw/wxggList.do\", \"callbackType\":\"forward\"}");
				} else {
					err = "不能删除他人发布的公告";
				} 
			} else {
				err = "外协公告不存在";
			}
		} else {
			err = "用户登录超时";
		}
		if (!err.equals("")){
			out.print("{\"statusCode\":\"300\", \"message\":\""+err+"\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");
		}
		
		
	
		
	}
}
