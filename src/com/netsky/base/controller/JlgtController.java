package com.netsky.base.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta06_module;
import com.netsky.base.dataObjects.Ta07_formfield;
import com.netsky.base.dataObjects.Te01_slave;
import com.netsky.base.dataObjects.Te09_jlgt;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.utils.StringFormatUtil;
import com.netsky.base.utils.convertUtil;
import com.rms.dataObjects.base.Tc02_area;
import com.rms.dataObjects.base.Tc08_jlgtpzb;
import com.rms.dataObjects.base.Tc09_jlgttzr;
import com.rms.dataObjects.wxdw.Tf01_wxdw;

@Controller
public class JlgtController {
	@Autowired
	private Dao dao;
	@Autowired
	private ExceptionService exceptionService;

	/**
	 * 交流沟通页面(上下两部分 上部分显示表单相关信息，下部分显示交流沟通)
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/jlgt/jlgtView.do")
	public ModelAndView jlgtView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		Long module_id = convertUtil.toLong(request.getParameter("module_id"));
		Long doc_id = convertUtil.toLong(request.getParameter("doc_id"));
		// 表单相关信息读取哪些字段？由TC08表决定
		List<Tc08_jlgtpzb> tc08List = (List<Tc08_jlgtpzb>) dao
				.search("select tc08 from Tc08_jlgtpzb tc08 where tc08.lx=0 and tc08.module_id=" + module_id
						+ " order by tc08.xh asc");
		if (tc08List == null || tc08List.isEmpty()) {
			throw new Exception("不知该显示哪些关键字段");
		}
		String tableName = tc08List.get(0).getTablename();
		StringBuffer hsql = new StringBuffer();
		hsql.append("select ");
		for (Tc08_jlgtpzb tc08 : tc08List) {
			hsql.append(tc08.getFieldname() + ",");
		}
		hsql.append("'1' from " + tableName);
		hsql.append(" where id=" + doc_id);
		List<Object[]> tmpList = (List<Object[]>) dao.search(hsql.toString());
		if (tmpList == null || tmpList.isEmpty()) {
			throw new Exception("找不到该表单");
		}
		List<Map<String, Object>> bdxxList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < tc08List.size(); i++) {
			Map<String, Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("key", tc08List.get(i).getComments());
			tmpMap.put("value", tmpList.get(0)[i]);
			bdxxList.add(tmpMap);
		}
		modelMap.put("bdxxList", bdxxList);
		// 获取交流沟通信息
		// 获取一级信息
		List<Object[]> yjJlgtTmpList = (List<Object[]>) dao
				.search("select te09,ta03 from Te09_jlgt te09,Ta03_user ta03 where ta03.id=te09.fbr_id and module_id="
						+ module_id + " and doc_id=" + doc_id + " and up_id is null order by fbsj");
		// 获得一级信息附件
		List<Object[]> yjJlgtList = new ArrayList<Object[]>();
		for (Object[] objs : yjJlgtTmpList) {
			Te09_jlgt te09 = (Te09_jlgt) objs[0];
			List<Te01_slave> slaveList = (List<Te01_slave>) dao.search("from Te01_slave where module_id=80 and doc_id="
					+ te09.getId() + " and project_id=" + te09.getId() + " order by ftp_date");
			Object[] objects = new Object[3];
			objects[0] = objs[0];
			objects[1] = objs[1];
			objects[2] = slaveList;
			yjJlgtList.add(objects);
		}
		// 获取二级信息
		Map<Te09_jlgt, List<Object[]>> ejJlgtMap = new HashMap<Te09_jlgt, List<Object[]>>();
		for (Object[] objs : yjJlgtList) {
			Te09_jlgt yjJlgt = (Te09_jlgt) objs[0];
			List<Object[]> ejJlgtTmpList = (List<Object[]>) dao
					.search("select te09,ta03 from Te09_jlgt te09,Ta03_user ta03 where ta03.id=te09.fbr_id and module_id="
							+ module_id + " and doc_id=" + doc_id + " and up_id=" + yjJlgt.getId() + " order by fbsj");
			List<Object[]> ejJlgtList = new ArrayList<Object[]>();
			for (Object[] o : ejJlgtTmpList) {
				Te09_jlgt erJlgt = (Te09_jlgt) o[0];
				List<Te01_slave> slaveList = (List<Te01_slave>) dao
						.search("from Te01_slave where module_id=80 and doc_id=" + erJlgt.getId() + " and project_id="
								+ erJlgt.getId() + " order by ftp_date");
				Object[] objects = new Object[3];
				objects[0] = o[0];
				objects[1] = o[1];
				objects[2] = slaveList;
				ejJlgtList.add(objects);
			}
			ejJlgtMap.put(yjJlgt, ejJlgtList);
		}
		modelMap.put("yjJlgtList", yjJlgtList);
		modelMap.put("ejJlgtMap", ejJlgtMap);
		return new ModelAndView("/WEB-INF/jsp/jlgt/jlgtView.jsp", modelMap);
	}

	/**
	 * 当前人交流沟通相关列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/jlgt/jlgtList.do")
	public ModelAndView jlgtList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		Ta03_user ta03 = (Ta03_user) request.getSession().getAttribute("user");
		// 分页
		Integer totalPages = 1;
		Integer totalCount = 0;
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request.getParameter("orderField"), "fbsj");
		if (orderField.equals("")) {
			orderField = "fbsj";
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

		StringBuffer hsql = new StringBuffer();
		hsql.append("select jlgt,ta06 from Te09_jlgt jlgt,Ta06_module ta06 where ta06.id=jlgt.module_id");
		// where条件
		hsql.append(" and (fbr_id=" + ta03.getId() + " or tzr_names like '%" + ta03.getName() + "%')");
		// 类别
		// order排序
		// orderField
		hsql.append(" order by " + orderField);
		// orderDirection
		hsql.append(" " + orderDirection);

		ResultObject ro = dao.searchByPage(hsql.toString(), pageNum, numPerPage);
		// 获取结果集
		List<Object[]> jlgtList = new ArrayList<Object[]>();
		while (ro.next()) {
			Object[] o = new Object[2];
			o[0] = (Te09_jlgt) ro.get("jlgt");
			o[1] = (Ta06_module) ro.get("ta06");
			jlgtList.add(o);
		}
		modelMap.put("jlgtList", jlgtList);
		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		// 页面所需内容
		// 类别
		return new ModelAndView("/WEB-INF/jsp/jlgt/jlgtList.jsp", modelMap);
	}

	/**
	 * 发表回复或新的发言
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/jlgt/newJlgt.do")
	public ModelAndView newJlgt(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		Long module_id = convertUtil.toLong(request.getParameter("module_id"));
		Long doc_id = convertUtil.toLong(request.getParameter("doc_id"));
		// 获取默认通知人
		List<Tc09_jlgttzr> tc09List = (List<Tc09_jlgttzr>) dao.search("from Tc09_jlgttzr where module_id=" + module_id);
		StringBuffer hsql = new StringBuffer();
		if (tc09List != null && !tc09List.isEmpty()) {
			String tablename = tc09List.get(0).getTablename();
			hsql.append("select ");

			for (Tc09_jlgttzr tc09 : tc09List) {
				hsql.append(tc09.getNamefield() + "," + tc09.getTelfield() + ",");
			}
			hsql.append("'1' from " + tablename);
			hsql.append(" where id=" + doc_id);
			List<Object[]> tmpList = (List<Object[]>) dao.search(hsql.toString());
			List<Object[]> mrtzrList = new ArrayList<Object[]>();
			String reader_name = "";
			String reader_tel = "";
			for (int i = 0; i < tc09List.size(); i++) {
				Object[] o = new Object[2];
				o[0] = tmpList.get(0)[i * 2];
				o[1] = tmpList.get(0)[i * 2 + 1];
				if (convertUtil.toString(o[1]) != "") {
					mrtzrList.add(o);
					reader_name += "；" + o[0];
					reader_tel += "," + o[1];
				}
			}
			if (reader_name != "") {
				reader_name = reader_name.substring(1);
				reader_tel = reader_tel.substring(1);
			}
			modelMap.put("reader_name", reader_name);
			modelMap.put("reader_tel", reader_tel);
			modelMap.put("mrtzrList", mrtzrList);

			// 部门人员列表

			QueryBuilder queryBuilder = null;
			try {
				Ta03_user user = (Ta03_user) (request.getSession().getAttribute("user"));
				// user_dept_id 用户所在部门ID
				Long user_dept_id = user.getDept_id();
				modelMap.put("user_dept_id", user_dept_id);

				// 查询用户所在部门信息
				StringBuffer sql = new StringBuffer();

				sql.append("select t1.id,t1.name ");
				sql.append("from Ta01_dept t1 ");
				sql.append("where t1.area_name=(select area_name from Ta01_dept where id=");
				sql.append(user_dept_id + ")");
				List<?> user_dept_list = dao.search(sql.toString());
				modelMap.put("user_dept_list", user_dept_list);

				// 查询地区
				queryBuilder = new HibernateQueryBuilder(Tc02_area.class);
				queryBuilder.like("type", "[3]", MatchMode.ANYWHERE);
				List<?> areaList = dao.search(queryBuilder);
				modelMap.put("areaList", areaList);

				// 查询部门下所有的人
				queryBuilder = new HibernateQueryBuilder(Ta03_user.class);
				queryBuilder.eq("dept_id", user_dept_id);
				queryBuilder.addOrderBy(Order.asc("login_id"));
				List<?> user_list = dao.search(queryBuilder);
				modelMap.put("user_list", user_list);

			} catch (Exception e) {
				return exceptionService.exceptionControl(this.getClass().getName(), "短消息发送中的初始化人员、地区、部门  －错误", e);
			}
			String phonenum = "";
			String onlinename = "";
			if (request.getParameter("name") != null) {
				onlinename = StringFormatUtil.format(java.net.URLDecoder.decode(request.getParameter("name"), "UTF-8"),
						"");
				phonenum = StringFormatUtil.format(request.getParameter("phonenum"), "");

			}
			// phonenum = phonenum.replaceAll(";", ",");
			if (!"".equals(onlinename) && !"".equals(phonenum)) {
				String tmp = onlinename;
				for (int i = 1; i < phonenum.split(",").length; i++) {
					onlinename += "；" + tmp;
				}
				modelMap.put("reader_name1", tmp);
				modelMap.put("reader_name", onlinename);
				modelMap.put("reader_tel", phonenum);
			}

		}

		return new ModelAndView("/WEB-INF/jsp/jlgt/newJlgt.jsp", modelMap);
	}
}
