package com.rms.controller.mbk;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.PropertyInject;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta01_dept;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta04_role;
import com.netsky.base.dataObjects.Te01_slave;
import com.netsky.base.utils.convertUtil;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.StringFormatUtil;
import com.netsky.base.utils.DateGetUtil;
import com.rms.base.util.ConfigXML;
import com.rms.base.util.ConfigXMLImpl;
import com.rms.dataObjects.form.Td06_xqs;
import com.rms.dataObjects.mbk.Td21_mbk;
import com.rms.dataObjects.mbk.Td22_mbk_lzjl;
import com.rms.dataObjects.mbk.Td23_kcsqb;
import com.rms.dataObjects.mbk.Td24_kcfkb;
import com.rms.dataObjects.mbk.Td25_kcfkmx;
import com.rms.dataObjects.wxdw.Tf01_wxdw;

@Controller
public class Mbk {
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
	 * 表单模块配置表
	 */
	private String moduleTable = "com.netsky.base.dataObjects.Ta06_module";
	/**
	 * 附件表
	 */
	private String slaveTable;

	/**
	 * 日志处理
	 */
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * 目标库信息列表
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/mbk/mbkList.do")
	public ModelAndView mbkList(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		Ta03_user ta03 = (Ta03_user) session.getAttribute("user");
		String user_name = null;
		Long user_id = null;
		if (ta03 == null) {
			ta03 = new Ta03_user();
		}
		user_name = ta03.getName();
		user_id = ta03.getId();

		ModelMap modelMap = new ModelMap();
		// 分页
		Integer totalPages = 1;
		Integer totalCount = 0;
		Integer pageNum = convertUtil.toInteger(
				request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request
				.getParameter("orderField"), "id");
		String listType = convertUtil
				.toString(request.getParameter("listType"));
		if (orderField.equals("")) {
			orderField = "id";
		}
		String orderDirection = convertUtil.toString(request
				.getParameter("orderDirection"), "desc");
		if (orderDirection.equals("")) {
			orderDirection = "desc";
		}
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		// 查询条件
		String zymc = convertUtil.toString(request.getParameter("zymc"));
		String lb = convertUtil.toString(request.getParameter("lb"));
		String ssdq = convertUtil.toString(request.getParameter("ssdq"));
		String zt = convertUtil.toString(request.getParameter("zt"));

		StringBuffer hsql = new StringBuffer();
		hsql.append("select mbk from Td21_mbk mbk where 1=1");
		// where条件
		// 非20101角色
		Map<String, Ta04_role> rolesMap = (Map<String, Ta04_role>) request
				.getSession().getAttribute("rolesMap");
		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		String roleSql = "1=0";
		if (rolesMap.get("20101") != null) {
			roleSql += " or cjr_id = " + user_id + " ";

		}
		if (rolesMap.get("20102") != null) {
			roleSql += " or (tdr_id=" + user.getId()
					+ ") or (zt='新建' and hdfs='派发')";

		}
		if (rolesMap.get("20103") != null) {
			roleSql += " or ( id in (select mbk_id from Td22_mbk_lzjl where xgr_id="
					+ user.getId() + " and sm like '%四方勘察%'))";
		}
		if (rolesMap.get("20104") != null) {
			roleSql += " or (  id in (select mbk_id from Td22_mbk_lzjl where xgr_id="
					+ user.getId() + " and sm='方案会审'))";
		}
		if (rolesMap.get("20105") != null) {
			roleSql += " or ((zt='转建设' or zt='建设中') and id in (select mbk_id from Td22_mbk_lzjl where xgr_id="
					+ user.getId()
					+ " and (sm='建设中' or sm='转建设') and jssj is null))";
		}
		if (rolesMap.get("20118") != null) {
			roleSql += " or ( zt in('发现问题','资源共享'))";
		}
		hsql.append(" and (" + roleSql + ")");
		// 类别
		if (!lb.equals("")) {
			hsql.append(" and lb='" + lb + "'");
		}
		// 所属地区
		if (!ssdq.equals("")) {
			hsql.append(" and ssdq='" + ssdq + "'");
		}
		// 状态
		if (!zt.equals("")) {
			hsql.append(" and zt='" + zt + "'");
		}
		// 资源名称
		if (!zymc.equals("")) {
			hsql.append(" and (zymc like '%" + zymc + "%' or zybh like '%"+zymc+"%')");
		}

		/*
		 * 待认领列表
		 */
		if (listType.equals("drl")) {
			hsql.append(" and zt = '新建' and hdfs = '派发'");
		}

		/*
		 * 退回列表
		 */
		if (listType.equals("ht")) {
			hsql
					.append(" and zt='新建' and (bz like '%回退%' or bz = '重新谈点')");
		}

		/*
		 * 反馈超期列表 zhfksj 最后反馈时间 zypfsj 资源派发时间
		 */
		if (listType.equals("fkcq")) {
			hsql
					.append(" and zt='开始谈点' and (case when (zhfksj is null or zhfksj < zypfsj ) then zypfsj else zhfksj end) + (case when fkzq is null then 5 else fkzq end) < sysdate");
		}
		
		/*
		 * 需要延期列表
		 */
		if (listType.equals("xyq")) {
			hsql.append(" and zt='开始谈点' and trunc(case when zhfksj is null then zypfsj else zhfksj end + case when fkzq is null then 5 else fkzq end ) - 1 = trunc(sysdate)");
		}

		/*
		 * 谈点超期列表
		 */
		if (listType.equals("tdcq")) {
			hsql
					.append(" and zt='开始谈点' and zypfsj + (case when tdzq is null then 15 else tdzq end) < sysdate");
		}

		/*
		 * 待四方勘察列表
		 */
		if (listType.equals("dkc")) {
			hsql.append(" and zt = '勘察申请'");
		}

		/*
		 * 待四方勘察列表(给四方勘察人员使用)
		 */
		if (listType.equals("dkcForKcry")) {
			hsql.append(" and zt = '四方勘察'");
		}

		/*
		 * 可施工列表(给项目管理员使用)
		 */
		if (listType.equals("ksg")) {
			hsql.append(" and zt = '转建设'");
		}

		/*
		 * 四方勘察申请列表
		 */
		if (listType.equals("kcsq")) {
			hsql.append(" and zt = '开始谈点' and instr(jsxz,'室分',1) = 0");
		}
		
		/*
		 * 勘察已反馈列表(给谈点管理员使用)
		 */
		if (listType.equals("kcyfk")) {
			hsql.append(" and zt = '四方勘察' and kcfksj is not null and fksj is null ");
		}
		
		/*
		 * 会审已反馈列表(给谈点管理员使用)
		 */
		if (listType.equals("hsyfk")) {
			hsql.append(" and zt = '四方勘察' and fksj is not null ");
		}
		
		/*
		 * 问题或共享列表
		 */
		if (listType.equals("wthgx")) {
			hsql.append(" and wtclr = '"+user_name+"' and zt in('发现问题','资源共享')");
		}
		
		/*
		 * 已删除列表(给谈点管理员使用)
		 */
		if (listType.equals("tdysc")) {
			hsql.append(" and delflag = '1' ");
		}else{
			hsql.append(" and (delflag is null or delflag != '1') ");
		}

		// order排序
		// orderField
		hsql.append(" order by " + orderField);
		// orderDirection
		hsql.append(" " + orderDirection);
		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum,
				numPerPage);
		// 获取结果集
		List<Object[]> mbkList = new ArrayList<Object[]>();
		while (ro.next()) {
			Object[] obj = new Object[2];
			obj[0] = ro.get("mbk");
			Map map = new HashMap();
			if (!listType.equals("")) {
				map.put("listType", listType);
			}
			obj[1] = map;
			mbkList.add(obj);
		}
		modelMap.put("mbkList", mbkList);
		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		// 页面所需内容
		// 类别
		List<String> lbList = (List<String>) queryService
				.searchList("select name from Tc01_property where type='目标库类别'");
		modelMap.put("lbList", lbList);
		List<String> dqList = (List<String>) queryService
				.searchList("select name from Tc02_area");
		modelMap.put("dqList", dqList);
		List<String> ztList = (List<String>) queryService
				.searchList("select name from Tc01_property where type='目标库状态'");
		modelMap.put("ztList", ztList);
		return new ModelAndView("/WEB-INF/jsp/mbk/mbkList.jsp", modelMap);

	}

	/**
	 * 目标库信息
	 * 
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/mbk/mbkEdit.do")
	public ModelAndView mbkEdit(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws ClassNotFoundException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		ModelMap modelMap = new ModelMap();
		Long id = convertUtil.toLong(request.getParameter("id"));
		Long xqs_id = convertUtil.toLong(request.getParameter("xqs_id"));
		Td21_mbk mbk = null;
		StringBuffer hsql = new StringBuffer();

		if (xqs_id != null && xqs_id != -1) {
			Td06_xqs td06 = (Td06_xqs) queryService.searchById(Td06_xqs.class,
					xqs_id);
			if (td06 == null) {
				td06 = new Td06_xqs();
			}
			mbk = new Td21_mbk();
			mbk.setId(null);
			mbk.setZymc(td06.getXqmc() + "【来自需求】");
			mbk.setXqs_id(xqs_id);
			mbk.setBz(td06.getBz());
			mbk.setSsdq(td06.getSsdq());
			mbk.setSswg(td06.getSswg());
			mbk.setJsxz(td06.getJsxz());
			mbk.setJsfs(td06.getJsfs());
			mbk.setJscj(td06.getJscj());
			mbk.setZldd(td06.getZldd());
			mbk.setJd(td06.getJd());
			mbk.setWd(td06.getWd());
			mbk.setFgsx(td06.getFgsx());
			mbk.setZs(td06.getZs());
			mbk.setHs(td06.getHs());
			mbk.setCs(td06.getCs());
		} else {
			mbk = (Td21_mbk) queryService.searchById(Td21_mbk.class, id);
		}
		if(mbk == null){
			mbk = new Td21_mbk();
		}
		modelMap.put("Td21_mbk", mbk);

		List<String> jsxzList = (List<String>) queryService.searchList("from Tc01_property where type='建设性质'");
		modelMap.put("jsxzList", jsxzList);
		List<String> lbList = (List<String>) queryService.searchList("select name from Tc01_property where type='目标库类别'");
		modelMap.put("lbList", lbList);
		// 获取建设方式：Tc12_jsfs
		hsql.delete(0, hsql.length());
		hsql.append("select tc12 ");
		hsql.append("from Tc12_jsfs tc12,Tc01_property tc01 ");
		hsql.append("where tc01.id = tc12.jsxz_id ");
		hsql.append("and tc01.name = '");
		hsql.append(mbk.getJsxz());
		hsql.append("'");
		List jsfsList = queryService.searchList(hsql.toString());
		if (jsfsList != null) {
			modelMap.put("jsfsList", jsfsList);
		}
		List<String> fgsxList = (List<String>) queryService.searchList("select name from Tc01_property where type='覆盖属性'");
		modelMap.put("fgsxList", fgsxList);
		List<String> sswgList = (List<String>) queryService.searchList("select name from Tc01_property where type='所属网格'");
		modelMap.put("sswgList", sswgList);
		List<String> jscjList = (List<String>) queryService.searchList("select name from Tc01_property where type='建设场景'");
		modelMap.put("jscjList", jscjList);
		List<String> dqList = (List<String>) queryService.searchList("select name from Tc02_area where type like '%[2]%'");
		modelMap.put("dqList", dqList);
		List<String> tdbmList = (List<String>) queryService.searchList("select name from Tc01_property where type='谈点部门'");
		modelMap.put("tdbmList", tdbmList);
		List<String> gjzdList = (List<String>) queryService.searchList("select name from Tc01_property where type='关键站点'");
		modelMap.put("gjzdList", gjzdList);
		String[] sfList = {"是","否"};
		modelMap.put("sfList", sfList);

		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		Long user_id = user.getId();

		/**
		 * 附件上传
		 */
		Long project_id = null;
		Long doc_id = null;
		Long module_id = new Long("90");
		String loginClient = StringFormatUtil.format((String) request
				.getSession().getAttribute("loginClient"), "");
		Class<?> clazz = null;

		String slaveModules = null;
		String[] slaveModuleArray = null;
		QueryBuilder queryBuilder = null;
		ResultObject ro = null;
		Vector<HashMap<String, String>> v_slave = null;
		boolean canSave = false;

		/**
		 * 读Ta06_module表,得到表名信息
		 */
		moduleTable = "com.netsky.base.dataObjects.Ta06_module";
		clazz = Class.forName(moduleTable);
		Object o_module = queryService.searchById(clazz, module_id);
		request.setAttribute("module", o_module);
		/**
		 * 获取表单类型附件module_id
		 */
		slaveModules = StringFormatUtil.format((String) PropertyInject
				.getProperty(o_module, "slave_module"));
		if (!slaveModules.equals("")) {
			slaveModuleArray = slaveModules.split(",");
		}

		/**
		 * 获得表单附件信息
		 */
		v_slave = new Vector<HashMap<String, String>>();
		if (slaveModuleArray != null) {
			for (int i = 0; i < slaveModuleArray.length; i++) {

				hsql.delete(0, hsql.length());
				hsql.append("select distinct tb15.doc_id ,ta06.name ");
				hsql.append("from Ta06_module ta06,Tb15_docflow tb15 ");
				hsql.append("where ta06.id = tb15.module_id ");
				hsql.append("and tb15.project_id = ");
				hsql.append(project_id);
				hsql.append(" and tb15.module_id = ");
				hsql.append(new Long(slaveModuleArray[i]));
				ro = queryService.search(hsql.toString());
				while (ro.next()) {
					HashMap<String, String> tmp_hm_slave = new HashMap<String, String>();
					String tmp_slave_name = (String) ro.get("ta06.name");
					Long t_doc_id = (Long) ro.get("tb15.doc_id");
					String tmp_formurl = "javascript:parent.popOperWeb('openForm.do?project_id="
							+ project_id
							+ "&module_id="
							+ slaveModuleArray[i]
							+ "&doc_id=" + t_doc_id + "')";

					if (!tmp_slave_name.equals("")) {
						tmp_hm_slave.put("slave_name", tmp_slave_name);
						tmp_hm_slave.put("formurl", tmp_formurl);
						tmp_hm_slave.put("rw", "r");
						v_slave.add(tmp_hm_slave);
						request.setAttribute("formslave", v_slave);
						request
								.setAttribute("length_formslave", v_slave
										.size());
					}
				}
			}
		}

		/**
		 * 获得上传附件信息
		 */

		if (project_id == null) {
			project_id = id;
			doc_id = project_id;
		}
		slaveTable = "com.netsky.base.dataObjects.Te01_slave";
		v_slave = new Vector<HashMap<String, String>>();
		clazz = Class.forName(slaveTable);
		queryBuilder = new HibernateQueryBuilder(clazz);
		queryBuilder.eq("doc_id", doc_id);
		queryBuilder.eq("module_id", module_id);
		queryBuilder.eq("project_id", project_id);
		queryBuilder.eq("slave_type", "目标库");
		ro = queryService.search(queryBuilder);
		while (ro.next()) {
			HashMap<String, String> tmp_hm_slave = new HashMap<String, String>();

			Object t_slave_obj = ro.get(clazz.getName());
			Long tmp_slave_id = (Long) PropertyInject.getProperty(t_slave_obj,
					"id");
			String tmp_slave_name = StringFormatUtil.format(
					(String) PropertyInject.getProperty(t_slave_obj,
							"file_name"), "");
			String tmp_slave_ext = StringFormatUtil.format(
					(String) PropertyInject
							.getProperty(t_slave_obj, "ext_name"), "");
			String tmp_ftp_url = StringFormatUtil
					.format((String) PropertyInject.getProperty(t_slave_obj,
							"ftp_url"), "");
			String tmp_slave_remark = StringFormatUtil.format(
					(String) PropertyInject.getProperty(t_slave_obj, "remark"),
					"");
			Long tmp_user_id = (Long) PropertyInject.getProperty(t_slave_obj,
					"user_id");

			tmp_hm_slave.put("slave_id", tmp_slave_id.toString());
			tmp_hm_slave.put("slave_name", tmp_slave_name);
			tmp_hm_slave.put("ftp_url", tmp_ftp_url);
			tmp_hm_slave.put("slave_remark", tmp_slave_remark);
			if (mbk.getZt() != "转建设" && mbk.getZt() != "建设中"
					&& user_id.equals(tmp_user_id)) {
				tmp_hm_slave.put("rw", "w");
			} else {
				tmp_hm_slave.put("rw", "r");
			}
			v_slave.add(tmp_hm_slave);
		}
		request.setAttribute("uploadslave", v_slave);
		request.setAttribute("length_uploadslave", v_slave.size());

		/**
		 * 获得交流反馈信息
		 */
		List<Map> jlfkList = new LinkedList<Map>();
		hsql.delete(0, hsql.length());
		hsql
				.append("select te02.project_id,te02.id,ta03.name,ta03.id,te02.yj,te02.time ");
		hsql.append("from Te02_jlfk te02,Ta03_user ta03 ");
		hsql.append("where te02.user_id = ta03.id ");
		hsql.append("and project_id = ");
		hsql.append(id);
		hsql.append(" and module_id = ");
		hsql.append(90);
		hsql.append(" and document_id =  ");
		hsql.append(id);
		hsql.append(" order by te02.time ");
		ro = queryService.search(hsql.toString());
		while (ro.next()) {
			HashMap<String, Object> jlfk = new HashMap<String, Object>();
			Long tmp_user_id = (Long) ro.get("ta03.id");
			jlfk.put("name", ro.get("ta03.name"));
			jlfk.put("project_id", ro.get("te02.id"));
			jlfk.put("yj", ro.get("te02.yj"));
			jlfk.put("time", ro.get("te02.time"));
			if (user_id.equals(tmp_user_id)) {
				jlfk.put("rw", "w");
			} else {
				jlfk.put("rw", "r");
			}

			Long te02_project_id = new Long(ro.get("te02.project_id")
					.toString());
			Long te02_id = new Long(ro.get("te02.id").toString());
			QueryBuilder queryBuilder99 = new HibernateQueryBuilder(
					Te01_slave.class);
			queryBuilder99.eq("doc_id", te02_id);
			queryBuilder99.eq("project_id", te02_project_id);
			queryBuilder99.eq("module_id", new Long(9003));
			ResultObject ro99 = queryService.search(queryBuilder99);
			if (ro99.next()) {
				Te01_slave te01 = (Te01_slave) ro99.get(Te01_slave.class
						.getName());
				jlfk.put("slave_id", te01.getId());
			}
			jlfkList.add(jlfk);
		}
		if (jlfkList != null && jlfkList.size() > 0) {
			request.setAttribute("jlfk", jlfkList);
		}
		request.setAttribute("length_jlfk", jlfkList.size());

		if (mbk != null) {
			modelMap.put("lzjlList", queryService
					.searchList("from Td22_mbk_lzjl where mbk_id=" + id
							+ " order by id asc"));

			/*
			 * 查看此目标库是否已经有关联工程
			 */
			List glgcList = (List<String>) queryService
					.searchList("select id from Td00_gcxx where mbk_id=" + id);
			if (glgcList == null || glgcList.size() == 0) {
				modelMap.put("haveGlgc", "no");
			} else {
				modelMap.put("haveGlgc", "yes");
			}
		}
		modelMap.put("project_id", id);

		/*
		 * 判断当前人可以在哪个节点起草工程
		 */
		hsql.delete(0, hsql.length());
		hsql.append("select  distinct ta13.node_id as node_id ");
		hsql
				.append("from Ta11_sta_user,Ta11_sta_user ta11,Ta13_sta_node ta13 ");
		hsql.append("where ta11.station_id = ta13.station_id ");
		hsql.append("and ta13.node_id in(10202,10201) ");
		hsql.append("and ta11.user_id = " + user_id + " ");
		hsql.append("order by ta13.node_id desc");
		ro = queryService.search(hsql.toString());
		if (ro.next()) {
			modelMap.put("firstNode", ro.get("node_id"));
		} else {
			modelMap.put("firstNode", new Long(10201));
		}
		// while()

		/**
		 * 处理表单链接显示问题
		 */
		hsql.delete(0, hsql.length());
		hsql
				.append("select td00.id,td00.gcmc from Td00_gcxx td00 where td00.mbk_id="
						+ id);
		ro = queryService.search(hsql.toString());
		v_slave = new Vector<HashMap<String, String>>();
		while (ro.next()) {
			HashMap<String, String> tmp_hm_slave = new HashMap<String, String>();
			String tmp_slave_name = (String) ro.get("td00.gcmc");
			Long t_doc_id = (Long) ro.get("td00.id");
			String tmp_formurl = "javascript:navTab.openTab('autoform', 'flowForm.do?project_id="
					+ t_doc_id
					+ "&module_id=102&doc_id="
					+ t_doc_id
					+ "', {title:'表单'});";

			if (!tmp_slave_name.equals("")) {
				tmp_hm_slave.put("slave_name", tmp_slave_name);
				tmp_hm_slave.put("formurl", tmp_formurl);
				tmp_hm_slave.put("rw", "r");
				v_slave.add(tmp_hm_slave);
				request.setAttribute("_formslink", v_slave);
				request.setAttribute("length_formlink", v_slave.size());
			}
		}

		/*
		 * 判断是否显示延期按钮
		 */
		hsql.delete(0, hsql.length());
		hsql.append("select id from Td21_mbk "); 
		hsql.append("where trunc(case when zhfksj is null then zypfsj else zhfksj end + case when fkzq is null then 5 else fkzq end ) - 1 = trunc(sysdate) ");
		hsql.append("and zt = '开始谈点' and id = ");
		hsql.append(id);
		List addDayList = queryService.searchList(hsql.toString());
		if(addDayList != null && addDayList.size() > 0){
			modelMap.put("needAddDay", "yes");
		}
		// 建设驱动原因
		List reasonList=queryService.searchList("select a.name from Tc01_property a where a.type='建设驱动原因'");
		// 机房共享属性
		List jfgxsxList=queryService.searchList("select a.name from Tc01_property a where a.type='共享属性'");
		//塔桅共享属性
		List  twgxsxList=queryService.searchList("select a.name from Tc01_property a where a.type='共享属性' and a.flag='[1]'");
		//规划区域
		List ghqyList=queryService.searchList("select a.name from Tc01_property a where a.type='规划区域'");
		//规划塔型
		List ghtxList=queryService.searchList("select a.name from Tc01_property a where a.type='规划塔型'");
		
		modelMap.put("reasonList", reasonList);
		modelMap.put("ghtxList", ghtxList);
		modelMap.put("jfgxsxList", jfgxsxList);
		modelMap.put("twgxsxList", twgxsxList);
		modelMap.put("ghqyList", ghqyList);
		return new ModelAndView("/WEB-INF/jsp/mbk/mbkEdit.jsp", modelMap);
	}

	@RequestMapping("/mbk/getZybh.do")
	public void getZybh(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setCharacterEncoding(request.getCharacterEncoding());
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String jsxz = convertUtil.toString(request.getParameter("jsxz"));
		String s = "";
		if ("室分".equals(jsxz)) {
			s = "SF";
		} else if ("基站".equals(jsxz)) {
			s = "JZ";
		}
		Calendar calendar = Calendar.getInstance();
		s += calendar.get(Calendar.YEAR);
		Session session = queryService.getHibernateTemplate()
				.getSessionFactory().openSession();
		try {
			Long no = ((BigDecimal) (session
					.createSQLQuery("select source_num.nextval from dual")
					.uniqueResult())).longValue();
			s += String.format("%06d", no);
		} finally {
			session.close();
		}
		out.print(s);
	}

	@RequestMapping("/mbk/ajaxMbkDel.do")
	public void ajaxMbkDel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = convertUtil.toLong(request.getParameter("id"));
		String del = convertUtil.toString(request.getParameter("del"));
		PrintWriter out = null;

		response.setContentType("text/html;charset=UTF-8");
		Session session = saveService.getHiberbateSession();
		Transaction tx = session.beginTransaction();
		tx.begin();
		// 获取用户对象
		try {
			out = response.getWriter();
			if(del.equals("yes")){
				session.createQuery("delete from Td21_mbk where id=" + id).executeUpdate();
				session.createQuery("delete from Td22_mbk_lzjl where mbk_id=" + id).executeUpdate();
			}
			else if(del.equals("resume")){
				session.createQuery("update Td21_mbk set delflag = null where id=" + id).executeUpdate();
			}
			else{
				session.createQuery("update Td21_mbk set delflag = 1 where id=" + id).executeUpdate();
			}
			out.print("{\"statusCode\":\"200\", \"message\":\"操作成功\", \"callbackType\":\"\",\"navTabId\":\"mbkList\"}");
			session.flush();
			tx.commit();
		} catch (IOException e) {
			tx.rollback();
			out.print("{\"statusCode\":\"300\", \"message\":\"操作失败\"}");
		} finally {
			session.close();
		}
	}

	@RequestMapping("/mbk/mbkLz.do")
	public void mbkLz(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		Session session = saveService.getHiberbateSession();
		Transaction tx = session.beginTransaction();
		Long id = convertUtil.toLong(request.getParameter("id"));
		String sm = convertUtil.toString(request.getParameter("sm"));
		String kcsjStr = convertUtil.toString(request.getParameter("kcsj"));

		Td21_mbk td21 = (Td21_mbk) queryService.searchById(Td21_mbk.class, id);
		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		Ta01_dept dept = (Ta01_dept) queryService.searchById(Ta01_dept.class,
				user.getDept_id());
		tx.begin();
		String word = "";
		Date now = new Date();
		String type = convertUtil.toString(request.getParameter("type"));
		// 获取用户对象
		try {
			out = response.getWriter();
			if ("zdrl".equals(type)) {// 派发
				word = "派发";
				td21.setTdr_id(null);
				td21.setTdrdh(null);
				td21.setTdbm(null);
				td21.setTdr(null);
				td21.setHdfs("派发");
			} else if ("rl".equals(type)) {// 认领
				word = "认领";
				td21.setZt("开始谈点");
				td21.setTdr(user.getName());
				td21.setTdr_id(user.getId());
				td21.setTdrdh(user.getMobile_tel());
				td21.setTdbm(user.getDept_name());
				td21.setZypfsj(now);
				Td22_mbk_lzjl td22 = new Td22_mbk_lzjl();
				td22.setSm(user.getName() + "开始谈点");
				td22.setKssj(now);
				td22.setMbk_id(id);
				td22.setXgr(user.getName());
				td22.setXgr_id(user.getId());
				td22.setXgr_bm(dept.getName());
				session.save(td22);
			} else if ("zdxf".equals(type)) {// 指定下发
				word = "指定下发";
				td21.setHdfs("指定下发");
				td21.setZt("开始谈点");
				td21.setZypfsj(now);
				Td22_mbk_lzjl td22 = new Td22_mbk_lzjl();
				td22.setSm(td21.getTdr() + "开始谈点");
				td22.setKssj(now);
				td22.setMbk_id(id);
				td22.setXgr(td21.getTdr());
				td22.setXgr_id(td21.getTdr_id());
				td22.setXgr_bm(td21.getTdbm());
				session.save(td22);
			} else if ("ht".equals(type)) {// 回退
				word = "回退";

				Td22_mbk_lzjl td22 = new Td22_mbk_lzjl();
				td22.setSm(td21.getTdr() + "回退");
				td22.setKssj(now);
				td22.setMbk_id(id);
				session.save(td22);

				td21.setHdfs(null);
				td21.setZt("新建");
				td21.setTdr(null);
				td21.setTdr_id(null);
				td21.setTdrdh(null);
				td21.setTdbm(null);
				td21.setBz(word);
			} else if ("cxtd".equals(type)) {// 重新谈点
				word = "重新谈点";
				td21.setHdfs(null);
				td21.setZt("新建");
				td21.setTdr(null);
				td21.setTdr_id(null);
				td21.setTdrdh(null);
				td21.setTdbm(null);
				td21.setZypfsj(null);
				td21.setBz(word);
				session.createQuery(
						"update Td22_mbk_lzjl set Jssj=sysdate,sm='"
								+ user.getName() + "从'||xgr||'处收回"
								+ "' where mbk_id=" + id + " and jssj is null")
						.executeUpdate();
			} else if ("ycqx".equals(type)) {// 延长期限
				Long ycqx = convertUtil.toLong(request.getParameter("ycqx"));
				word = "延长期限";
				td21.setTdzq(td21.getTdzq() == null ? 15 + ycqx : td21
						.getTdzq()
						+ ycqx);
				td21.setBz(word);

				Td22_mbk_lzjl td22 = new Td22_mbk_lzjl();
				td22
						.setSm(user.getName() + "延长谈点期限【谈点人：" + td21.getTdr()
								+ "】");
				td22.setKssj(now);
				td22.setJssj(now);
				td22.setMbk_id(id);
				session.save(td22);
			}
			else if ("sqyq".equals(type)) {// 申请延期
				word = "申请延期";
				td21.setBz(word);
				Date zhfksj = td21.getZhfksj();
				if(zhfksj == null)
					zhfksj = td21.getZypfsj();
				td21.setZhfksj(DateGetUtil.addDay(zhfksj , convertUtil.toLong(td21.getFkzq()).intValue()));

				Td22_mbk_lzjl td22 = new Td22_mbk_lzjl();
				td22.setSm(user.getName() + "申请延期【谈点人：" + td21.getTdr() + "】");
				td22.setKssj(now);
				td22.setJssj(now);
				td22.setMbk_id(id);
				session.save(td22);
			}
			else if ("zdwkcsq".equals(type)) {// 勘察申请
				String sqkcsm = convertUtil.toString(request
						.getParameter("sqkcsm"));
				word = "勘察申请";
				td21.setZt("勘察申请");
				td21.setSqkcsm(sqkcsm);
				td21.setSqkcsj(new Date());
				td21.setBz(word);

				Td22_mbk_lzjl td22 = new Td22_mbk_lzjl();
				td22.setSm(user.getName() + "发起四方勘察申请");
				td22.setKssj(now);
				td22.setXgr(user.getName());
				td22.setXgr_bm(dept.getName());
				td22.setXgr_id(user.getId());
				td22.setMbk_id(id);
				session.save(td22);
			} else if ("qywc".equals(type)) {// 签约完成
				word = "签约完成";
				td21.setZt("签约完成");
				session.createQuery(
						"update Td22_mbk_lzjl set jssj=sysdate where jssj is null and mbk_id="
								+ id).executeUpdate();
			} else if ("sfkc".equals(type)) {// 四方勘察
				word = "四方勘察";
				td21.setZt("四方勘察");
				td21.setKcsj(new SimpleDateFormat("yyyy-MM-dd").parse(kcsjStr));
				
				String sfkc_ids = convertUtil.toString(request.getParameter("ids")); 
				sfkc_ids = sfkc_ids + "," + td21.getTdr_id();//下发时也要给谈点人
				String[] ids = sfkc_ids.split(",");
				
				for (String string : ids) {
					Ta03_user ta03 = (Ta03_user) queryService.searchById(
							Ta03_user.class, convertUtil.toLong(string));
					Ta01_dept ta01 = (Ta01_dept) queryService.searchById(
							Ta01_dept.class, ta03.getDept_id());

					session
							.createQuery(
									"update Td22_mbk_lzjl set jssj=sysdate where jssj is null and sm like '%四方勘察申请%' and mbk_id="
											+ id).executeUpdate();

					Td22_mbk_lzjl td22 = new Td22_mbk_lzjl();
					td22.setSm("四方勘察" + " [时间：" + kcsjStr + "," + sm + "]");
					td22.setKssj(now);
					td22.setXgr(ta03.getName());
					td22.setXgr_bm(ta01.getName());
					td22.setXgr_id(ta03.getId());
					td22.setMbk_id(id);
					session.save(td22);
				}
			} else if ("kcjs".equals(type)) {// 勘察结束
				word = "勘察结束";
				td21.setZt("勘察结束");
				session.createQuery(
						"update Td22_mbk_lzjl set jssj=sysdate where jssj is null and mbk_id="
								+ id).executeUpdate();
			}
			// else if ("fahssq".equals(type)) {// 会审申请
			// word = "会审申请";
			// td21.setZt("会审申请");
			// Td22_mbk_lzjl td22 = new Td22_mbk_lzjl();
			//				
			// td22.setSm(user.getName()+"发起方案会审申请" );
			// td22.setKssj(now);
			// td22.setXgr(user.getName());
			// td22.setXgr_bm(dept.getName());
			// td22.setXgr_id(user.getId());
			// td22.setMbk_id(id);
			// session.save(td22);
			// }
			// else if ("fahs".equals(type)) {// 方案会审
			// word = "方案会审";
			// td21.setZt("方案会审");
			// String[] ids =
			// convertUtil.toString(request.getParameter("ids")).split(",");
			// for (String string : ids) {
			// Ta03_user ta03 = (Ta03_user)
			// queryService.searchById(Ta03_user.class,
			// convertUtil.toLong(string));
			// Ta01_dept ta01 = (Ta01_dept)
			// queryService.searchById(Ta01_dept.class, ta03.getDept_id());
			//					
			// session.createQuery("update Td22_mbk_lzjl set jssj=sysdate where
			// jssj is null and (sm like '%方案会审申请%' or sm like '%勘察%') and
			// mbk_id=" + id)
			// .executeUpdate();
			//					
			// Td22_mbk_lzjl td22 = new Td22_mbk_lzjl();
			// td22.setSm("方案会审");
			// td22.setKssj(now);
			// td22.setXgr(ta03.getName());
			// td22.setXgr_bm(ta01.getName());
			// td22.setXgr_id(ta03.getId());
			// td22.setMbk_id(id);
			// session.save(td22);
			// }
			// } else if ("hswc".equals(type)) {// 会审完成
			// word = "会审完成";
			// td21.setZt("会审完成");
			// session.createQuery("update Td22_mbk_lzjl set jssj=sysdate where
			// jssj is null and mbk_id=" + id)
			// .executeUpdate();
			// }
			else if ("jxtd".equals(type)) {// 继续谈点
				word = "继续谈点";
				td21.setZhfksj(new Date());
			} else if ("zjs".equals(type)) {// 转建设
				word = "转建设";
				td21.setZt("转建设");
				Long xmgly_id = convertUtil.toLong(request
						.getParameter("xmgly_id"));
				Ta03_user ta03 = (Ta03_user) queryService.searchById(
						Ta03_user.class, xmgly_id);
				Ta01_dept ta01 = (Ta01_dept) queryService.searchById(
						Ta01_dept.class, ta03.getDept_id());
				Td22_mbk_lzjl td22 = new Td22_mbk_lzjl();
				td22.setSm("转建设");
				td22.setKssj(now);
				td22.setXgr(ta03.getName());
				td22.setXgr_bm(ta01.getName());
				td22.setXgr_id(ta03.getId());
				td22.setMbk_id(id);
				session.save(td22);
			} else if ("wtcl".equals(type)) {// 问题处理
				Long wtclr_id = convertUtil.toLong(request
						.getParameter("wtclr_id"));
				Ta03_user ta03 = (Ta03_user) queryService.searchById(
						Ta03_user.class, wtclr_id);
				Ta01_dept ta01 = (Ta01_dept) queryService.searchById(
						Ta01_dept.class, ta03.getDept_id());
				
				word = "发现问题";
				td21.setZt("发现问题");
				td21.setWtclr(ta03.getName());
				Td22_mbk_lzjl td22 = new Td22_mbk_lzjl();
				td22.setSm("发现问题");
				td22.setKssj(now);
				td22.setXgr(ta03.getName());
				td22.setXgr_bm(ta01.getName());
				td22.setXgr_id(ta03.getId());
				td22.setMbk_id(id);
				session.save(td22);
			} else if ("wtycl".equals(type)) {// 问题已处理
				word = "问题已处理";
				td21.setZt("新建");
				session.createQuery(
						"update Td22_mbk_lzjl set jssj=sysdate where jssj is null and mbk_id="
								+ id).executeUpdate();
			}else if ("jsz".equals(type)) {// 建设中
				word = "建设中";
				td21.setZt("建设中");
				// 打开‘新建需求’表单，起草需求
			}
			session.update(td21);
			out.print("{\"statusCode\":\"200\", \"message\":\"" + word
					+ "成功\", \"callbackType\":\"forward\"}");
			session.flush();
			tx.commit();
		} catch (IOException e) {
			tx.rollback();
			out.print("{\"statusCode\":\"300\", \"message\":\"" + word
					+ "失败\"}");
		} finally {
			session.close();
		}
	}

	@RequestMapping("/mbk/getTdbm.do")
	public void getTdbm(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		Long id = convertUtil.toLong(request.getParameter("id"));
		String s = "";
		List<Tf01_wxdw> wxdwList = (List<Tf01_wxdw>) queryService
				.searchList("from Tf01_wxdw where id in(select wxdw_id from Tf04_wxdw_user where user_id="
						+ id + ")");
		if (wxdwList != null && wxdwList.size() != 0) {
			s = wxdwList.get(0).getMc();
		} else {
			s = convertUtil.toString(queryService.searchList(
					"select name from Ta01_dept where id=(select dept_id from Ta03_user where id="
							+ id + ")").get(0));
		}
		out.print(s);
	}

	@RequestMapping("/mbk/getTdr.do")
	public ModelAndView getTdr(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		Integer pageNum = convertUtil.toInteger(
				request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 10);
		Integer totalCount = 0;
		Integer pageNumShown = 0;

		// 排序变量
		String orderField = convertUtil.toString(request
				.getParameter("orderField"), "user.id");
		String orderDirection = convertUtil.toString(request
				.getParameter("orderDirection"), "asc");

		String name = convertUtil.toString(request.getParameter("name"));
		String dept_remark=convertUtil.toString(request.getParameter("dept_remark"));
		
		StringBuffer hsql = new StringBuffer();
		hsql.append("select ta03 ");
		hsql.append("from V_ta03 ta03 ,Ta11_sta_user ta11,Ta12_sta_role ta12 ");
		hsql.append("where ta11.station_id = ta12.station_id ");
		hsql.append("and ta11.user_id = ta03.id ");
		hsql.append("and ta12.role_id=20102 ");
		hsql.append("and (ta03.dept_name like '%");
		hsql.append(name);
		hsql.append("%' or ta03.name like '%");
		hsql.append(name);
		hsql.append("%') ");
		hsql.append(" and ta03.dept_remark like '%");
		hsql.append(dept_remark);
		hsql.append("%' ");

		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum,
				numPerPage);
		totalCount = ro.getTotalRows();
		pageNumShown = ro.getTotalPages();

		List list = ro.getList();

		// List<Object[]> resultList = new LinkedList<Object[]>();
		// for(int i = 0;i < list.size();i++){
		// Object[] obj = new Object[2];
		// Ta03_user ta03 = (Ta03_user)list.get(i);
		// obj[0] = ta03;
		//			
		// Map<String,String> map = new HashMap<String,String>();
		// List<Ta01_dept> deptList = (List<Ta01_dept>)
		// queryService.searchList("from Ta01_dept where id =" +
		// ta03.getDept_id());
		// if(deptList == null || deptList.size() == 0){
		// map.put("dept_name", "部门未配置");
		// }
		// else{
		// String dept_name = deptList.get(0).getName();
		// if(convertUtil.toString(dept_name).equals("合作单位")){
		// List<Tf01_wxdw> wxdwList = (List<Tf01_wxdw>)
		// queryService.searchList("from Tf01_wxdw where id in(select wxdw_id
		// from Tf04_wxdw_user where user_id=" + ta03.getId() + ")");
		// if(list != null && list.size() > 0){
		// map.put("dept_name",wxdwList.get(0).getMc());
		// }
		// else{
		// map.put("dept_name", "单位未配置");
		// }
		// }
		// else{
		// map.put("dept_name",dept_name);
		// }
		// }
		// obj[1] = map;
		//			
		// if(!name.equals("")){
		// if(ta03.getName().indexOf(name) != -1 ||
		// ((String)map.get("dept_name")).indexOf(name) != -1){
		// resultList.add(obj);
		// }
		// }
		// else{
		// resultList.add(obj);
		// }
		//			
		// }
		
		modelMap.put("dept_remark", dept_remark);
		modelMap.put("tdrList", list);
		modelMap.put("totalCount", totalCount);
		modelMap.put("pageNumShown", pageNumShown);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		return new ModelAndView("/WEB-INF/jsp/mbk/selectTdr.jsp", modelMap);
	}
	
	@RequestMapping("/mbk/getWtclr.do")
	public ModelAndView getWtclr(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		Integer pageNum = convertUtil.toInteger(
				request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 10);
		Integer totalCount = 0;
		Integer pageNumShown = 0;

		// 排序变量
		String orderField = convertUtil.toString(request
				.getParameter("orderField"), "user.id");
		String orderDirection = convertUtil.toString(request
				.getParameter("orderDirection"), "asc");

		String name = convertUtil.toString(request.getParameter("name"));
		String dept_remark=convertUtil.toString(request.getParameter("dept_remark"));
		
		StringBuffer hsql = new StringBuffer();
		hsql.append("select ta03 ");
		hsql.append("from V_ta03 ta03 ,Ta11_sta_user ta11,Ta12_sta_role ta12 ");
		hsql.append("where ta11.station_id = ta12.station_id ");
		hsql.append("and ta11.user_id = ta03.id ");
		hsql.append("and ta12.role_id=20118 ");
		hsql.append("and (ta03.dept_name like '%");
		hsql.append(name);
		hsql.append("%' or ta03.name like '%");
		hsql.append(name);
		hsql.append("%') ");
		hsql.append(" and ta03.dept_remark like '%");
		hsql.append(dept_remark);
		hsql.append("%' ");

		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum,
				numPerPage);
		totalCount = ro.getTotalRows();
		pageNumShown = ro.getTotalPages();

		List list = ro.getList();

		modelMap.put("dept_remark", dept_remark);
		modelMap.put("wtclrList", list);
		modelMap.put("totalCount", totalCount);
		modelMap.put("pageNumShown", pageNumShown);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		return new ModelAndView("/WEB-INF/jsp/mbk/selectWtclr.jsp", modelMap);
	}

	@RequestMapping("/mbk/getKcry.do")
	public ModelAndView getKcry(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		Integer pageNum = convertUtil.toInteger(
				request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 10);
		Integer totalCount = 0;
		Integer pageNumShown = 0;

		// 排序变量
		String orderField = convertUtil.toString(request
				.getParameter("orderField"), "user.id");
		String orderDirection = convertUtil.toString(request
				.getParameter("orderDirection"), "asc");

		String name = convertUtil.toString(request.getParameter("name"));
		StringBuffer hsql = new StringBuffer();

		hsql
				.append("select user from V_ta03 user where user.id in (select user_id from Ta11_sta_user where station_id in(select station_id from Ta12_sta_role where role_id=20103)) and user.name like '%"
						+ name + "%' ");
		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum,
				numPerPage);
		totalCount = ro.getTotalRows();
		pageNumShown = ro.getTotalPages();

		List userList = new LinkedList();

		while (ro.next()) {
			userList.add(ro.get("user"));
		}
		modelMap.put("kcryList", userList);
		modelMap.put("totalCount", totalCount);
		modelMap.put("pageNumShown", pageNumShown);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		return new ModelAndView("/WEB-INF/jsp/mbk/selectKcry.jsp", modelMap);
	}

	@RequestMapping("/mbk/getHsry.do")
	public ModelAndView getHsry(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		Integer pageNum = convertUtil.toInteger(
				request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 10);
		Integer totalCount = 0;
		Integer pageNumShown = 0;

		// 排序变量
		String orderField = convertUtil.toString(request
				.getParameter("orderField"), "user.id");
		String orderDirection = convertUtil.toString(request
				.getParameter("orderDirection"), "asc");

		String name = convertUtil.toString(request.getParameter("name"));
		StringBuffer hsql = new StringBuffer();
		hsql
				.append("select user from Ta03_user user where id in (select user_id from Ta11_sta_user where station_id in(select station_id from Ta12_sta_role where role_id=20104)) and name like '%"
						+ name + "%'");
		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum,
				numPerPage);
		totalCount = ro.getTotalRows();
		pageNumShown = ro.getTotalPages();
		modelMap.put("hsryList", ro.getList());
		modelMap.put("totalCount", totalCount);
		modelMap.put("pageNumShown", pageNumShown);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		return new ModelAndView("/WEB-INF/jsp/mbk/selectHsry.jsp", modelMap);
	}

	@RequestMapping("/mbk/getXmgly.do")
	public ModelAndView getXmgly(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		Integer pageNum = convertUtil.toInteger(
				request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 10);
		Integer totalCount = 0;
		Integer pageNumShown = 0;

		// 排序变量
		String orderField = convertUtil.toString(request
				.getParameter("orderField"), "user.id");
		String orderDirection = convertUtil.toString(request
				.getParameter("orderDirection"), "asc");

		String name = convertUtil.toString(request.getParameter("name"));
		StringBuffer hsql = new StringBuffer();
		hsql
				.append("select user from Ta03_user user where id in (select user_id from Ta11_sta_user where station_id in(select station_id from Ta12_sta_role where role_id=20105)) and name like '%"
						+ name + "%'");
		ResultObject ro = queryService.searchByPage(hsql.toString(), pageNum,
				numPerPage);
		totalCount = ro.getTotalRows();
		pageNumShown = ro.getTotalPages();
		modelMap.put("xmglyList", ro.getList());
		modelMap.put("totalCount", totalCount);
		modelMap.put("pageNumShown", pageNumShown);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		return new ModelAndView("/WEB-INF/jsp/mbk/selectXmgly.jsp", modelMap);
	}

	/**
	 * 目标库信息
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/mbk/lzjl.do")
	public ModelAndView lzjl(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ModelMap modelMap = new ModelMap();
		Long id = convertUtil.toLong(request.getParameter("mbk_id"));
		if (id != -1) {
			modelMap.put("lzjlList", queryService
					.searchList("from Td22_mbk_lzjl where mbk_id=" + id
							+ " order by id asc"));
		}
		return new ModelAndView("/WEB-INF/jsp/mbk/lzjl.jsp", modelMap);
	}

	/**
	 * @hibernate.property column="moduleTable"
	 * @return Returns the moduleTable.
	 */
	public String getModuleTable() {
		return moduleTable;
	}

	/**
	 * @param moduleTable
	 *            The moduleTable to set.
	 */
	public void setModuleTable(String moduleTable) {
		this.moduleTable = moduleTable;
	}

	/**
	 * @hibernate.property column="slaveTable"
	 * @return Returns the slaveTable.
	 */
	public String getSlaveTable() {
		return slaveTable;
	}

	/**
	 * @param slaveTable
	 *            The slaveTable to set.
	 */
	public void setSlaveTable(String slaveTable) {
		this.slaveTable = slaveTable;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 *            void
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/mbk/mbkToExcel.do")
	public ModelAndView mbkToExcel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		String zymc = convertUtil.toString(request.getParameter("zymc"), "");
		String ssdq = convertUtil.toString(request.getParameter("ssdq"), "");
		String lb = convertUtil.toString(request.getParameter("lb"), "");
		String zt = convertUtil.toString(request.getParameter("zt"), "");
		String config = convertUtil.toString(request.getParameter("config"));
		String orderField = convertUtil.toString(request
				.getParameter("orderField"), "cjsj");
		String orderDirection = convertUtil.toString(request
				.getParameter("orderDirection"), "desc");

		int k = 0;
		ConfigXML configXML = new ConfigXMLImpl();// 读取mbk配置文档
		ResultObject ro = null;
		StringBuffer hql = new StringBuffer("");
		List mbkTitleList = new LinkedList(); // 标题列表
		List mbkColList = new LinkedList();// 列的字段值
		List mbkDocList = new LinkedList();// 表单数据
		Map<String, List> sheetMap = new HashMap<String, List>();
		List sheetList = new LinkedList();

		// 读取配置文件的标题列表
		String webinfpath = request.getSession().getServletContext()
				.getRealPath("WEB-INF");
		mbkTitleList = configXML.getTagListByConfig(config, webinfpath, "name");
		mbkColList = configXML.getTagListByConfig(config, webinfpath,
				"columnName");
		Iterator it = mbkColList.iterator();
		Object mbk = null;
		hql.append("select ");
		while (it.hasNext()) {
			if (k == 0)
				hql.append(" mbk." + ((it.next().toString()).toLowerCase()));
			else
				hql.append(" ,mbk." + ((it.next().toString()).toLowerCase()));
			k++;
		}
		hql.append(" from Td21_mbk mbk ");
		// 条件
		hql.append("where 1=1 and mbk.cjr='" + user.getName() + "' ");
		if (!ssdq.equals("")) {
			hql.append(" and mbk.ssdq=" + ssdq);
		}
		if (!lb.equals("")) {
			hql.append(" and mbk.lb=" + lb);
		}
		if (!zt.equals("")) {
			hql.append(" and mbk.zt=" + zt);
		}
		if (!zymc.equals("")) {
			hql.append(" and mbk.zymc like '%" + zymc + "%' ");
		}
		hql.append(" order by " + orderField + " ");
		hql.append(orderDirection);

		mbkDocList = queryService.searchList(hql.toString());

		sheetList.add(mbkTitleList);
		sheetList.add(mbkDocList);
		sheetMap.put("form_title", sheetList);
		request.setAttribute("ExcelName", "目标库信息.xls");
		request.setAttribute("sheetMap", sheetMap);
		return new ModelAndView("/export/toExcelWhithList.do");

	}

	@RequestMapping("/mbk/kcsqEdit.do")
	public ModelAndView kcsqEidt(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		ModelMap modelMap = new ModelMap();
		String view = "/WEB-INF/jsp/mbk/kcsq.jsp";
		Long mbk_id = convertUtil.toLong(request.getParameter("mbk_id"));
		String role = convertUtil.toString(request.getParameter("role"),"tdr");
		StringBuffer hql = null;
		Ta03_user user = (Ta03_user) session.getAttribute("user");
		Td23_kcsqb td23_kcsqb = null;
		Td21_mbk td21_mbk = null;
		try {
			if (mbk_id != -1 && user != null) {
				hql = new StringBuffer();
				td21_mbk = (Td21_mbk) queryService.searchById(Td21_mbk.class,
						mbk_id);
				hql.append("select td23 from Td23_kcsqb td23");
				hql.append(" where td23.mbk_id=");
				hql.append(mbk_id);
				if(role.equals("tdr")){
					hql.append(" and td23.cjr='");
					hql.append(user.getName() + "'");
				}
				hql.append(" order by td23.id desc ");
				List td23List = queryService.searchList(hql.toString());
				if (td23List != null && td23List.size() > 0)
					td23_kcsqb = (Td23_kcsqb) td23List.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.warn(e.getMessage());
		}
		modelMap.put("Td23_kcsqb", td23_kcsqb);
		modelMap.put("Td21_mbk", td21_mbk);
		return new ModelAndView(view, modelMap);
	}

	/**
	 * 勘察申请上报
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws IOException
	 *             void
	 */
	@RequestMapping("/mbk/reportKcsq.do")
	public void reportKcsq(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String navTabId = convertUtil.toString(
				request.getParameter("navTabId"), "");
		String forwardUrl = convertUtil.toString(request
				.getParameter("forwardUrl"), "");
		String callbackType = convertUtil.toString(request
				.getParameter("callbackType"), "");
		Long mbk_id = convertUtil.toLong(request.getParameter("mbk_id"));
		Long kcsqb_id = convertUtil.toLong(request.getParameter("kcsqb_id"));
		Ta03_user user = (Ta03_user) session.getAttribute("user");

		if (kcsqb_id == -1) {
			out
					.print("{\"statusCode\":\"300\", \"message\":\"上报失败!请您先保存申请单!\"}");

		} else if (mbk_id != -1 && user != null) {
			try {
				Date date = new Date();
				Td21_mbk td21_mbk = (Td21_mbk) queryService.searchById(Td21_mbk.class, mbk_id);
				td21_mbk.setSqkcsj(new Date());
				td21_mbk.setSqkcsj(date);
				td21_mbk.setZt("勘察申请");
				td21_mbk.setSqkcsm("新的~勘察申请单~");

				Td23_kcsqb td23_kcsqb = (Td23_kcsqb) queryService.searchById(Td23_kcsqb.class, kcsqb_id);
				td23_kcsqb.setSqsbsj(date);
				saveService.save(td21_mbk);
				saveService.save(td23_kcsqb);
				out
						.print("{\"statusCode\":\"200\", \"message\":\"勘察申请上报成功!\",\"callbackType\":\"closeCurrent\"}");
			} catch (RuntimeException e) {
				e.printStackTrace();
				log.warn(e.getMessage());
				out
						.print("{\"statusCode\":\"300\", \"message\":\"上报失败请联系管理员!\", \"navTabId\":\""
								+ navTabId
								+ "\", \"forwardUrl\":\""
								+ forwardUrl
								+ "\", \"callbackType\":\""
								+ callbackType + "\"}");

			}
		}

	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/mbk/kcfkEdit.do")
	public ModelAndView kcfkEdit(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String view = "/WEB-INF/jsp/mbk/kcfkEdit.jsp";
		ModelMap modelMap = new ModelMap();
		Long mbk_id = convertUtil.toLong(request.getParameter("mbk_id"));
		Long kcfkb_id = convertUtil.toLong(request.getParameter("kcfkb_id"));
		String role = convertUtil.toString(request.getParameter("role"),"tdr");
		String fklb = convertUtil.toString(request.getParameter("fklb"),"KC");
		Td24_kcfkb td24_kcfkb = null;
		Td25_kcfkmx td25_kcfkmx = null;
		List tableList = null;
		List kcfkbList = null;
		List td25_kcfkmxList = null;
		List te01List=null;

		StringBuffer hql = new StringBuffer();
		Ta03_user user = (Ta03_user) session.getAttribute("user");

		// 取勘察反馈单
		hql.append("select t from Td24_kcfkb t where t.mbk_id=");
		hql.append(mbk_id);
		hql.append(" and t.fklb = '");
		hql.append(fklb);
		hql.append("'");
		if(role.equals("tdr")){
			hql.append(" and t.cjr='");
			hql.append(user.getName());
			hql.append("'");
		}
		hql.append("order by t.id desc ");

		kcfkbList = queryService.searchList(hql.toString());
		if (kcfkbList.size() != 0 && kcfkbList != null) {
			td24_kcfkb = (Td24_kcfkb) kcfkbList.get(0);
			// 取名细表
			hql.delete(0, hql.length());
			hql.append("select td25,tc01.flag from Td25_kcfkmx td25,Tc01_property tc01 ");
			hql.append("where 1=1 ");
			hql.append("and td25.fkx=tc01.name ");
			hql.append("and tc01.type= '");
			hql.append("勘察反馈内容' ");
			hql.append("and td25.kcfk_id=");
			hql.append(td24_kcfkb.getId());
			td25_kcfkmxList = queryService.searchList(hql.toString());

		}  
		// 取tc01列表,td24为空或者不完整的情况
		hql.delete(0, hql.length());
		hql.append("select tc01 from Tc01_property tc01 ");
		hql.append("where tc01.type='勘察反馈内容' ");
		hql.append("and tc01.name not in");
		hql.append("(select mx.fkx from Td25_kcfkmx mx where mx.kcfk_id=");
		if (td24_kcfkb != null)
			hql.append(td24_kcfkb.getId());
		else
			hql.append("-1");
		hql.append(") ");
		hql.append("order by tc01.flag,tc01.name");
		tableList = queryService.searchList(hql.toString());
		
		
		if(td24_kcfkb!=null){
			hql.delete(0, hql.length());
			hql.append("select t from Te01_slave t where 1=1 ");
			hql.append("and t.project_id=");
			hql.append(td24_kcfkb.getId());
			te01List=queryService.searchList(hql.toString());
		}
		
		modelMap.put("Td24_kcfkb", td24_kcfkb);
		modelMap.put("mbk_id", mbk_id);
		modelMap.put("Td25_kcfkmxList", td25_kcfkmxList);
		modelMap.put("TableList", tableList);
		modelMap.put("slaveList", te01List);
		return new ModelAndView(view, modelMap);
	}

	/**
	 * 反馈
	 * 
	 * @param request
	 * @param response
	 *            void
	 * @throws IOException
	 */
	@RequestMapping("/mbk/kcfk.do")
	public void kcfk(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Long mbk_id = convertUtil.toLong(request.getParameter("mbk_id"));
		Long kcfk_id = convertUtil.toLong(request.getParameter("kcfk_id"));
		String fklb = convertUtil.toString(request.getParameter("fklb"),"KC");
		Ta03_user user = (Ta03_user) session.getAttribute("user");
		Td24_kcfkb td24_kcfkb = null;
		Td21_mbk td21_mbk = null;
		if (kcfk_id == -1) {
			out
					.print("{\"statusCode\":\"300\", \"message\":\"反馈失败!请您先保存反馈单!\"}");
		} else if (mbk_id != -1) {

			try {
				Date date = new Date();
				td21_mbk = (Td21_mbk) queryService.searchById(Td21_mbk.class,mbk_id);
				if(fklb.equals("KC"))
					td21_mbk.setKcfksj(date);
				else
					td21_mbk.setFksj(date);
				
				td24_kcfkb = (Td24_kcfkb) queryService.searchById(
						Td24_kcfkb.class, kcfk_id);
				td24_kcfkb.setFksj(date);

				saveService.save(td21_mbk);
				saveService.save(td24_kcfkb);
				out.print("{\"statusCode\":\"200\", \"message\":\"反馈成功!\",\"callbackType\":\"closeCurrent\"}");
			} catch (RuntimeException e) {
				e.printStackTrace();
				log.warn(e.getMessage());
				out
						.print("{\"statusCode\":\"300\", \"message\":\"反馈失败!请联系管理员!\"}");

			}

		}
	}
	
	
	/**
	 * 工程施工进度
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/mbk/gcsgjdForMbk.do")
	public ModelAndView gcsgjdForMbk(HttpServletRequest request,HttpServletResponse response) {
		String view="/WEB-INF/jsp/mbk/gcsgjd.jsp";
		ModelMap modelMap=new ModelMap();
		String mbk_id=convertUtil.toString(request.getParameter("mbk_id"));
		StringBuffer hql=new StringBuffer();
		hql.append("select d.jd_name,d.jd_status from Td53_gzjd d,Td00_gcxx x where 1=1 ");
		hql.append("and d.gcxx_id=x.id "); 
		hql.append("and x.mbk_id=");
		hql.append(mbk_id);
		hql.append(" order by d.id ");
		List objList=queryService.searchList(hql.toString());
		modelMap.put("objList", objList);
		return new ModelAndView(view,modelMap);
	}
}
