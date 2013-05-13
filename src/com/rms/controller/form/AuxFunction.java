package com.rms.controller.form;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.Cell;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseDao.JdbcSupport;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.utils.DateGetUtil;
import com.netsky.base.utils.convertUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.dataObjects.Ta01_dept;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.baseObject.PropertyInject;

import com.rms.dataObjects.gcjs.Te03_gcgys_zhxx;
import com.rms.dataObjects.wxdw.Tf01_wxdw;
import com.rms.dataObjects.wxdw.Tf05_wxdw_dygx;
import com.rms.dataObjects.base.Tmp_zdxp;
import com.rms.dataObjects.form.Td00_gcxx;
import com.rms.dataObjects.form.Td01_xmxx;

@Controller
public class AuxFunction {

	@Autowired
	private Dao dao;

	@Autowired
	private QueryService queryService;

	@Autowired
	private SaveService saveService;
	
	@Autowired
	private ExceptionService exceptionService;
	
	@Autowired
	private JdbcSupport jdbcSupport;

	/**
	 * 日志处理类
	 */
	private Logger log = Logger.getLogger(this.getClass());

	@RequestMapping("/form/xzgcForDblx.do")
	public ModelAndView xzgcForDblx(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelMap modelMap = new ModelMap();
		StringBuffer sql = new StringBuffer("");
		String user_name = null;
		Long xm_id = convertUtil.toLong(request.getParameter("xm_id"));
		HttpSession session = request.getSession();
		if (session != null) {
			Ta03_user ta03 = (Ta03_user) session.getAttribute("user");
			user_name = ta03.getName();
		}

		/*
		 * 获得未选工程列表
		 */
		sql.delete(0, sql.length());
		sql.append("select td00 ");
		sql.append("from Td00_gcxx td00 ");
		sql.append("where xmgly = '");
		sql.append(user_name);
		sql.append("' and xm_id is null  order by gcmc ");
		List list = queryService.searchList(sql.toString());
		modelMap.put("unselect_groups", list);
		/*
		 * 获得已选工程列表
		 */
		sql.delete(0, sql.length());
		sql.append("select td00 ");
		sql.append("from Td00_gcxx td00 ");
		sql.append("where xm_id = ");
		sql.append(xm_id);
		list = queryService.searchList(sql.toString());
		modelMap.put("select_groups", list);

		return new ModelAndView("/WEB-INF/jsp/form/xzgcForDblx.jsp", modelMap);
	}

	/**
	 * 打包立项选择工程保存
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param ModelAndView
	 */
	@RequestMapping("/form/saveXzgcForDblx.do")
	public ModelAndView saveXzgcForDblx(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		response.setCharacterEncoding(request.getCharacterEncoding());
		String[] groups = request.getParameterValues("t_group");
		Long xm_id = convertUtil.toLong(request.getParameter("xm_id"), -1L);
		String forwardUrl = "-1";

		try {
			// 获取岗位的对象
			StringBuffer sql = new StringBuffer();
			sql.delete(0, sql.length());
			sql.append("update Td00_gcxx set xm_id = null where xm_id=");
			sql.append(xm_id);
			saveService.updateByHSql(sql.toString());

			// 对配置的角色进行保存
			if (groups != null) {
				for (int i = 0; i < groups.length; i++) {
					sql.delete(0, sql.length());
					sql.append("update Td00_gcxx set xm_id = ");
					sql.append(xm_id);
					sql.append(" where id = ");
					sql.append(groups[i]);
					saveService.updateByHSql(sql.toString());
				}
			}

			/*
			 * 同步预算金额及工日
			 */
			sql.delete(0, sql.length());
			sql.append("select ");
			sql
					.append("sum(ys_je) as ys_je,sum(ys_jaf) as ys_jaf,sum(ys_clf) as ys_clf,sum(ys_rgf) as ys_rgf,");
			sql
					.append("sum(ys_jggr) as ys_jggr,sum(ys_pggr) as ys_pggr,sum(ys_sbf) as ys_sbf,");
			sql
					.append("sum(ys_jxf) as ys_jxf,sum(ys_ybf) as ys_ybf,sum(ys_sjf) as ys_sjf,");
			sql.append("sum(ys_jlf) as ys_jlf,sum(ys_qtf) as ys_qtf ");
			sql.append("from Td00_gcxx ");
			sql.append("where xm_id = ");
			sql.append(xm_id);
			ResultObject ro = queryService.search(sql.toString());
			if (ro.next()) {
				Td01_xmxx td01 = (Td01_xmxx) queryService.searchById(
						Td01_xmxx.class, xm_id);
				td01.setYs_je(convertUtil.toDouble(ro.get("ys_je"), 0d));
				td01.setYs_jaf(convertUtil.toDouble(ro.get("ys_jaf"), 0d));
				td01.setYs_clf(convertUtil.toDouble(ro.get("ys_clf"), 0d));
				td01.setYs_rgf(convertUtil.toDouble(ro.get("ys_rgf"), 0d));
				td01.setYs_jggr(convertUtil.toDouble(ro.get("ys_jggr"), 0d));
				td01.setYs_pggr(convertUtil.toDouble(ro.get("ys_pggr"), 0d));
				td01.setYs_sbf(convertUtil.toDouble(ro.get("ys_sbf"), 0d));
				td01.setYs_jxf(convertUtil.toDouble(ro.get("ys_jxf"), 0d));
				td01.setYs_ybf(convertUtil.toDouble(ro.get("ys_ybf"), 0d));
				td01.setYs_sjf(convertUtil.toDouble(ro.get("ys_sjf"), 0d));
				td01.setYs_jlf(convertUtil.toDouble(ro.get("ys_jlf"), 0d));
				td01.setYs_qtf(convertUtil.toDouble(ro.get("ys_qtf"), 0d));
				saveService.save(td01);
			}

			response.getWriter().print(
					"{\"statusCode\":\"200\", \"message\":\"操作成功\", \"navTabId\":\"autoform101"
							+ xm_id
							+ "\",\"forwardUrl\":\"\", \"callbackType\":\"\"}");
		} catch (Exception e) {
			log.error("saveXzgcForDblx.do[com.rms.controller.form.AuxFunction]"
					+ e.getMessage() + e);
			response.getWriter().print(
					"{\"statusCode\":\"300\", \"message\":\"操作失败\"}");
		}
		return null;
	}

	/**
	 * 打包立项选择工程检测（工程类别、施工单位）
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param ModelAndView
	 */
	@RequestMapping("/form/chkXzgcForDblx.do")
	public ModelAndView chkXzgcForDblx(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		response.setCharacterEncoding(request.getCharacterEncoding());
		String[] groups = request.getParameterValues("t_group");
		String json = "{\"statusCode\":\"200\", \"message\":\"成功\"}";

		try {
			// 获取岗位的对象
			StringBuffer sql = new StringBuffer();
			String ids = "";
			// 对配置的角色进行保存
			if (groups != null) {
				for (int i = 0; i < groups.length; i++) {
					ids += groups[i] + ",";
				}
				ids += "-1";
			}

			/*
			 * 判断工程类别是否不一致
			 */
			sql.delete(0, sql.length());
			sql.append("select distinct gclb from Td00_gcxx where id in (");
			sql.append(ids);
			sql.append(")");
			List list = queryService.searchList(sql.toString());
			if (list != null && list.size() > 1) {
				json = "{\"statusCode\":\"300\", \"message\":\"以上工程【工程类别】不一致，打包失败\"}";
			}

			/*
			 * 判断施工单位是否不一致
			 */
			sql.delete(0, sql.length());
			sql.append("select distinct sgdw from Td00_gcxx where id in (");
			sql.append(ids);
			sql.append(") and sgdw is not null ");
			list = queryService.searchList(sql.toString());
			if (list != null && list.size() > 1) {
				json = "{\"statusCode\":\"300\", \"message\":\"以上工程【施工单位】不一致，打包失败\"}";
			}

			response.getWriter().print(json);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("chkXzgcForDblx.do[com.rms.controller.form.AuxFunction]"
					+ e.getMessage() + e);
			response.getWriter().print(
					"{\"statusCode\":\"300\", \"message\":\"操作失败\"}");
		}
		return null;
	}

	/**
	 * 概预算导入
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param ModelAndView
	 */
	@RequestMapping("/form/gysImport.do")
	public ModelAndView gysImport(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		response.setCharacterEncoding("GBK");

		Workbook wb = null;
		Sheet sheet = null;
		StringBuffer sql = new StringBuffer();
		ResultObject ro = null;
		try {
			MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
			Long project_id = convertUtil.toLong(request
					.getParameter("project_id"), -1L);
			Long module_id = convertUtil.toLong(request
					.getParameter("module_id"), -1L);

			saveService.updateByHSql("delete from Te03_gcgys_b1 where gc_id = "
					+ project_id);
			saveService.updateByHSql("delete from Te03_gcgys_b2 where gc_id = "
					+ project_id);
			saveService
					.updateByHSql("delete from Te03_gcgys_b3j where gc_id = "
							+ project_id);
			saveService
					.updateByHSql("delete from Te03_gcgys_b3y where gc_id = "
							+ project_id);
			saveService
					.updateByHSql("delete from Te03_gcgys_b3b where gc_id = "
							+ project_id);
			saveService
					.updateByHSql("delete from Te03_gcgys_b4j where gc_id = "
							+ project_id);
			saveService
					.updateByHSql("delete from Te03_gcgys_b5j where gc_id = "
							+ project_id);
			saveService
					.updateByHSql("delete from Te03_gcgys_zhxx where gc_id = "
							+ project_id);

			Iterator<?> it = mrequest.getFileNames();
			if (it.hasNext()) {
				String file_name = (String) it.next();
				MultipartFile file = mrequest.getFile(file_name);
				wb = Workbook.getWorkbook(file.getInputStream());
				Sheet[] sheets = wb.getSheets();
				for (int i = 0; i < sheets.length; i++) {

					try {
						String sheetName = sheets[i].getName();
						sheet = wb.getSheet(sheetName);

						/*
						 * 获得导入的起始单元格的横、纵坐标
						 */
						int x = 0, y = 0;
						Cell cell = sheet.findCell("Ⅰ");
						if (cell == null)
							throw new Exception("Excel格式非法，请按标准模板上传");
						y = cell.getRow() + 1;
						x = cell.getColumn();

						int rows = sheet.getRows(); // rows取得当前工作蒲一共有几行
						int cols = sheet.getColumns(); // rows取得当前工作蒲一共有几列

						Map<String, Integer> columnIndex = null;
						String className = null;
						String[] colName = null;
						String keyColName = null;
						String notImportCol = null;
						String notImportColValue = null;

						if (sheetName.indexOf("表一") != -1) {

							// {序号、表格编号、费用名称、小型建筑工程费、需安设备费、不需安设备费、建筑安装工程费、其它费、预算费、人民币总价、外币总价}
							colName = new String[] { "xh", "bgbh", "fymc",
									"jzgcf", "xasbf", "bxasbf", "azgcf",
									"qtfy", "ybf", "rmbzj", "wbzj" };
							className = "com.rms.dataObjects.gcjs.Te03_gcgys_b1";
							keyColName = "fymc";
						} else if (sheetName.indexOf("表二") != -1) {

							// {序号、费用名称、依据算法、技工费、普工费、合计。。。}
							colName = new String[] { "xh1", "fymc1", "yjsf1",
									"hj1", "jg1", "xh2", "fymc2", "yjsf2",
									"hj2" };
							className = "com.rms.dataObjects.gcjs.Te03_gcgys_b2";
							keyColName = "fymc1";
						} else if (sheetName.indexOf("表三") != -1
								&& sheetName.indexOf("甲") != -1) {

							// {序号、定额编号、定额名称、单位、数量、单位技工、单位普工、技工合计、普工合计}
							colName = new String[] { "xh", "debh", "xmmc",
									"dw", "sl", "dwjg", "dwpg", "jghj", "pghj" };
							className = "com.rms.dataObjects.gcjs.Te03_gcgys_b3j";
							keyColName = "xmmc";
						} else if (sheetName.indexOf("表三") != -1
								&& sheetName.indexOf("乙") != -1) {

							// {序号、定额编号、定额名称、机械名称、单位、数量、数量、单位数量、单价、数量合计、金额合计、备注}
							colName = new String[] { "xh", "debh", "xmmc",
									"dw", "sl", "jxmc", "dwsl", "dj", "slhj",
									"jehj" };
							className = "com.rms.dataObjects.gcjs.Te03_gcgys_b3y";
							keyColName = "xmmc";
						} else if (sheetName.indexOf("表三") != -1
								&& sheetName.indexOf("丙") != -1) {

							// {序号、定额编号、定额名称、单位、数量、仪表名称、单位数量、单价、数量合计、金额合计}
							colName = new String[] { "xh", "debh", "xmmc",
									"dw", "sl", "ybmc", "dwsl", "dj", "slhj",
									"jehj" };
							className = "com.rms.dataObjects.gcjs.Te03_gcgys_b3b";
							keyColName = "xmmc";
						} else if (sheetName.indexOf("表四") != -1
								&& sheetName.indexOf("材料") != -1) {

							// {序号、名称、型号规格、单位、数量、单价、合计、备注}
							colName = new String[] { "xh", "mc", "xhgg", "dw",
									"sl", "dj", "hj", "bz" };
							className = "com.rms.dataObjects.gcjs.Te03_gcgys_b4j";
							keyColName = "mc";
							notImportCol = "bgbh";
							notImportColValue = "ZC";
						} else if (sheetName.indexOf("表四") != -1
								&& sheetName.indexOf("设备") != -1
								&& sheetName.indexOf("需") != -1) {

							// {序号、名称、型号规格、单位、数量、单价、合计、备注}
							colName = new String[] { "xh", "mc", "xhgg", "dw",
									"sl", "dj", "hj", "bz" };
							className = "com.rms.dataObjects.gcjs.Te03_gcgys_b4j";
							keyColName = "mc";
							notImportCol = "bgbh";
							notImportColValue = "XA";
						} else if (sheetName.indexOf("表五") != -1) {

							// {序号、费用名称、单位、数量、单价、合计、备注、计算依据和方法}
							colName = new String[] { "xh", "fymc", "yjsf",
									"hj", "hj", "hj", "bz" };
							className = "com.rms.dataObjects.gcjs.Te03_gcgys_b5j";
							keyColName = "fymc";
						}

						if (className != null) {
							columnIndex = new HashMap<String, Integer>();
							for (int j = 0; j < colName.length; j++) {
								columnIndex.put(colName[j].toUpperCase(),
										new Integer(x + j));
							}

							int t_row = y;
							while (t_row < rows - 1) {
								Object o = Class.forName(className)
										.newInstance();
								PropertyInject.setProperty(o, "gc_id",
										project_id);
								/*
								 * 处理表四中的表格编号（主材ZC 还是需安设备XA）
								 */
								if (notImportCol != null) {
									PropertyInject.setProperty(o, notImportCol,
											notImportColValue);
								}
								PropertyInject.injectFromExcel(o, columnIndex,
										sheet, t_row);
								String t_value = (String) PropertyInject
										.getProperty(o, keyColName);
								if (t_value != null && !t_value.equals("")
										&& t_value.indexOf("审核") == -1)
									saveService.save(o);
								t_row = t_row + 1;
							}
						}
					} catch (Exception ee) {
						if ((ee + "").indexOf("NumberFormatException") != -1)
							;
						else {
							throw ee;
						}
					}
				}
			}

			/**
			 * 计算综合信息
			 */
			/*
			 * 技工工日、普工工日
			 */
			Double jggr = 0d, pggr = 0d;
			sql.delete(0, sql.length());
			sql.append("select jghj,pghj from Te03_gcgys_b3j ");
			sql
					.append("where xmmc like '%总%' and xmmc like '%计%' and gc_id = ");
			sql.append(project_id);
			ro = queryService.search(sql.toString());
			if (ro.next()) {
				jggr = (Double) ro.get("jghj");
				pggr = (Double) ro.get("pghj");
			}

			/*
			 * 设计费、监理费
			 */
			Double sjf = 0d, jlf = 0d;
			sql.delete(0, sql.length());
			sql.append("select hj from Te03_gcgys_b5j ");
			sql.append("where fymc like '%设计费%' and gc_id = ");
			sql.append(project_id);
			ro = queryService.search(sql.toString());
			if (ro.next()) {
				sjf = (Double) ro.get("hj");
			}
			sql.delete(0, sql.length());
			sql.append("select hj from Te03_gcgys_b5j ");
			sql.append("where fymc like '%监理费%' and gc_id = ");
			sql.append(project_id);
			ro = queryService.search(sql.toString());
			if (ro.next()) {
				jlf = (Double) ro.get("hj");
			}

			/*
			 * 人工费、材料费、机械费、仪表费、建安费
			 */
			Double rgf = 0d, clf = 0d, jxf = 0d, ybf = 0d, jaf = 0d;
			sql.delete(0, sql.length());
			sql.append("select hj1 from Te03_gcgys_b2 ");
			sql.append("where gc_id = ");
			sql.append(project_id);
			sql.append(" and fymc1 = ");
			ro = queryService.search(sql.toString() + "'人工费'");
			if (ro.next()) {
				rgf = (Double) ro.get("hj1");
			}
			ro = queryService.search(sql.toString() + "'材料费'");
			if (ro.next()) {
				clf = (Double) ro.get("hj1");
			}
			ro = queryService.search(sql.toString() + "'仪表费'");
			if (ro.next()) {
				ybf = (Double) ro.get("hj1");
			}
			ro = queryService.search(sql.toString() + "'机械费'");
			if (ro.next()) {
				jxf = (Double) ro.get("hj1");
			}
			ro = queryService.search(sql.toString() + "'建筑安装工程费'");
			if (ro.next()) {
				jaf = (Double) ro.get("hj1");
			}

			/*
			 * 其它费
			 */
			Double qtf = 0d;
			sql.delete(0, sql.length());
			sql.append("select rmbzj from Te03_gcgys_b1 ");
			sql.append("where gc_id = ");
			sql.append(project_id);
			sql.append(" and fymc = '工程建设其他费'");
			ro = queryService.search(sql.toString());
			if (ro.next()) {
				qtf = convertUtil.toDouble(ro.get("rmbzj"), 0d);
			}

			/*
			 * 预算总金额、设备费
			 */
			Double ysje = 0d, sbf = 0d;
			sql.delete(0, sql.length());
			sql.append("select xasbf,rmbzj from Te03_gcgys_b1 ");
			sql.append("where gc_id = ");
			sql.append(project_id);
			sql.append(" and fymc like '%总%' and fymc like '%计%'");
			ro = queryService.search(sql.toString());
			if (ro.next()) {
				ysje = convertUtil.toDouble(ro.get("rmbzj"), 0d);
				sbf = convertUtil.toDouble(ro.get("xasbf"), 0d);
			}

			/*
			 * 保存综合信息
			 */
			Te03_gcgys_zhxx te03_zhxx = null;
			sql.delete(0, sql.length());
			sql.append("from Te03_gcgys_zhxx ");
			sql.append("where gc_id = ");
			sql.append(project_id);
			ro = queryService.search(sql.toString());
			if (ro.next()) {
				te03_zhxx = (Te03_gcgys_zhxx) ro.get(Te03_gcgys_zhxx.class
						.getName());
			} else {
				te03_zhxx = new Te03_gcgys_zhxx();
			}
			te03_zhxx.setGc_id(project_id);
			te03_zhxx.setJggr(jggr);
			te03_zhxx.setPggr(pggr);
			te03_zhxx.setRgf(rgf);
			te03_zhxx.setClf(clf);
			te03_zhxx.setJxf(jxf);
			te03_zhxx.setYbf(ybf);
			te03_zhxx.setJzazgcf(jaf);
			te03_zhxx.setGcjsqtf(qtf);
			te03_zhxx.setSjf(sjf);
			te03_zhxx.setJlf(jlf);
			saveService.save(te03_zhxx);

			/*
			 * 同步工程信息
			 */
			Td00_gcxx td00 = (Td00_gcxx) queryService.searchById(
					Td00_gcxx.class, project_id);
			if (td00 != null) {
				td00.setYs_jggr(jggr);
				td00.setYs_pggr(pggr);
				td00.setYs_jaf(jaf);
				td00.setYs_rgf(rgf);
				td00.setYs_clf(clf);
				td00.setYs_jxf(jxf);
				td00.setYs_ybf(ybf);
				td00.setYs_qtf(qtf);
				td00.setYs_sjf(sjf);
				td00.setYs_jlf(jlf);
				td00.setYs_sbf(sbf);
				td00.setYs_je(ysje);
				saveService.save(td00);
			} else {
				Td01_xmxx td01 = (Td01_xmxx) queryService.searchById(
						Td01_xmxx.class, project_id);
				if (td01 != null) {
					td01.setYs_jggr(jggr);
					td01.setYs_pggr(pggr);
					td01.setYs_jaf(jaf);
					td01.setYs_rgf(rgf);
					td01.setYs_clf(clf);
					td01.setYs_jxf(jxf);
					td01.setYs_ybf(ybf);
					td01.setYs_qtf(qtf);
					td01.setYs_sjf(sjf);
					td01.setYs_jlf(jlf);
					td01.setYs_sbf(sbf);
					td01.setYs_je(ysje);
					saveService.save(td01);
				}
			}

			response.getWriter().print(
					"{\"statusCode\":\"200\", \"message\":\"导入成功\", \"navTabId\":\"autoform"
							+ module_id + project_id
							+ "\",\"forwardUrl\":\"\", \"callbackType\":\"\"}");
		} catch (Exception e) {
			String msg = "";
			String e_msg = e.getMessage();
			if (e_msg != null && e_msg.indexOf("recognize OLE stream") != -1) {
				msg = "导入失败，Excel格式非法，请将Excel另存为<font color=red>2003版</font>的<font color=red>标准</font>的Excel后再导入";
			} else {
				msg = "导入失败,Excel格式非法,请参考导入模板";
			}
			log.error("gysImport.do[com.rms.controller.form.AuxFunction]"
					+ e.getMessage() + e);
			response.getWriter().print(
					"{\"statusCode\":\"300\", \"message\":\"" + msg + "\"}");
		}
		return null;
	}

	@RequestMapping("/form/gysShow.do")
	public ModelAndView gysShow(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelMap modelMap = new ModelMap();
		StringBuffer sql = new StringBuffer("");
		Long user_id = null;
		Long project_id = convertUtil
				.toLong(request.getParameter("project_id"));
		String bgmc = convertUtil.toString(request.getParameter("bgmc"));
		String bgbh = convertUtil.toString(request.getParameter("bgbh"));

		/*
		 * 获得项目信息
		 */
		Td00_gcxx td00 = (Td00_gcxx) queryService.searchById(Td00_gcxx.class,
				project_id);
		if (td00 != null) {
			modelMap.put("gcxx", td00);
		} else {
			Td01_xmxx td01 = (Td01_xmxx) queryService.searchById(
					Td01_xmxx.class, project_id);
			if (td01 != null) {
				modelMap.put("xmxx", td01);
			}
		}

		if (bgmc.equals("")) {
			return new ModelAndView("/WEB-INF/jsp/gys/gysShow.jsp", modelMap);
		}

		sql.delete(0, sql.length());
		sql.append("");
		sql.append("from ");
		sql.append(bgmc);
		sql.append(" where gc_id = ");
		sql.append(project_id);
		if (!bgbh.equals("")) {
			sql.append(" and bgbh = '");
			sql.append(bgbh);
			sql.append("'");
		}
		sql.append(" order by id ");
		List list = queryService.searchList(sql.toString());
		modelMap.put("gysList", list);

		return new ModelAndView("/WEB-INF/jsp/gys/" + bgmc + ".jsp", modelMap);
	}

	@RequestMapping("/form/designDocShow.do")
	public ModelAndView designDocShow(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelMap modelMap = new ModelMap();
		StringBuffer sql = new StringBuffer("");
		Long project_id = convertUtil
				.toLong(request.getParameter("project_id"));
		Long module_id = convertUtil.toLong(request.getParameter("module_id"));
		String keywords = convertUtil
				.toString(request.getParameter("keywords"));
		String lb = convertUtil.toString(request.getParameter("lb"));
		Ta03_user user = (Ta03_user) request.getSession().getAttribute("user");
		Object put = modelMap.put("curUserId", user.getId());

		/*
		 * 获得项目信息
		 */
		Td00_gcxx td00 = (Td00_gcxx) queryService.searchById(Td00_gcxx.class,
				project_id);
		if (td00 != null) {
			modelMap.put("gcxx", td00);
		} else {
			Td01_xmxx td01 = (Td01_xmxx) queryService.searchById(
					Td01_xmxx.class, project_id);
			if (td01 != null) {
				modelMap.put("xmxx", td01);
			}
		}

		sql.delete(0, sql.length());
		sql.append("");
		sql.append("from Te01_slave ");
		sql.append("where project_id = ");
		sql.append(project_id);
		sql.append(" and module_id = ");
		sql.append(module_id);
		if (lb.equals("jgzl")) {
			sql.append(" and slave_type in('竣工资料')");
		} else if (lb.equals("xczp")) {
			sql.append(" and slave_type in('现场照片')");
		} else {
			sql.append(" and slave_type in('工程图纸','设计说明')");
		}
		if (!keywords.equals("")) {
			sql.append(" and file_name like '%");
			sql.append(keywords);
			sql.append("%'");
		}
		sql.append(" order by id ");
		List list = queryService.searchList(sql.toString());
		modelMap.put("projectSlaveList", list);

		return new ModelAndView("/WEB-INF/jsp/form/projectSlaveList.jsp",
				modelMap);
	}

	/**
	 * 施工进度统计图
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping("/wxdw/xmsgjd.do")
	public ModelAndView xmsgjd(HttpServletRequest request,
			HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		String project_id = request.getParameter("id");
		StringBuffer hql = new StringBuffer("");
		List<Object> paramList = new ArrayList<Object>();// 参数列表
		String params = "";

		hql.append("from Td00_gcxx as td00 ");
		if (project_id != null && project_id.length() != 0) {
			hql.append("where td00.xm_id=" + project_id);
			Td01_xmxx project = (Td01_xmxx) dao.getObject(Td01_xmxx.class,
					convertUtil.toLong(project_id));
			modelMap.put("project", project);
		}
		List<Td00_gcxx> projectList = (List<Td00_gcxx>) queryService
				.searchList(hql.toString());
		if (projectList.size() == 0) {
			projectList.add(new Td00_gcxx());
			projectList.add(new Td00_gcxx());
		}

		for (int i = 0; i < projectList.size(); i++) {
			Td00_gcxx td00 = projectList.get(i);
			params = "javascript:openFlowForm('{project_id:" + td00.getId()
					+ ",doc_id:" + td00.getId() + ",module_id:102,"
					+ "node_id:-1}');";
			paramList.add(params);
		}
		modelMap.put("projectList", projectList);
		modelMap.put("paramList", paramList);
		String view = "/WEB-INF/jsp/form/xmsgjd.jsp";
		return new ModelAndView(view, modelMap);
	}

	/**
	 * 工程及关联工程进度统计图
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping("/wxdw/gcsgjd.do")
	public ModelAndView gcsgjd(HttpServletRequest request,
			HttpServletResponse response) {
		String view = "/WEB-INF/jsp/form/gcsgjd.jsp";
		ModelMap modelMap = new ModelMap();
		Long glgc_id;// 关联工程id
		String params = "";
		List<Td00_gcxx> engineerList = null;
		List<Object> paramList = new ArrayList<Object>();
		String engineer_id = convertUtil.toString(request.getParameter("id"),
				"");
		StringBuffer hql = new StringBuffer("from Td00_gcxx as td00 ");
		hql.append("where td00.id=" + engineer_id);

		if (engineer_id != null && engineer_id.length() != 0) {
			Td00_gcxx td00 = (Td00_gcxx) dao.getObject(Td00_gcxx.class,
					convertUtil.toLong(engineer_id));
			glgc_id = td00.getGlgc_id();
			if (glgc_id == null || glgc_id == 0 || glgc_id == -1) {
				hql.append(" or td00.glgc_id=" + engineer_id);
			} else {
				hql.append(" or td00.id=" + glgc_id + " and td00.glgc_id="
						+ glgc_id);
			}
			engineerList = (List<Td00_gcxx>) queryService.searchList(hql
					.toString());

		}
		for (int i = 0; i < engineerList.size(); i++) {
			Td00_gcxx td00 = engineerList.get(i);
			params = "javascript:openFlowForm('{project_id:" + td00.getId()
					+ ",doc_id:" + td00.getId() + ",module_id:102,"
					+ "node_id:-1}');";
			paramList.add(params);
		}
		modelMap.put("paramList", paramList);
		modelMap.put("engineerList", engineerList);
		return new ModelAndView(view, modelMap);
	}

	/**
	 * 工程及项目的出入库明细///////////////////////////////////////////////////////
	 * 
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wxdw/projectClmx.do")
	public ModelAndView projectClmx(HttpServletRequest request,
			HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		ResultObject ro = null;

		int totalCount = 0;
		int pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		int numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 20);
		String orderDirection = convertUtil.toString(request
				.getParameter("orderDirection"), "asc");
		String orderField = convertUtil.toString(request
				.getParameter("orderField"), "dz");
		Long project_id = convertUtil
				.toLong(request.getParameter("project_id"));

		Td00_gcxx td00 = (Td00_gcxx) queryService.searchById(Td00_gcxx.class,
				project_id);
		if (td00 == null) {
			Td01_xmxx td01 = (Td01_xmxx) queryService.searchById(
					Td01_xmxx.class, project_id);
			if (td01 != null) {
				modelMap.put("gcmc", td01.getXmmc());
			}
		} else {
			modelMap.put("gcmc", td00.getGcmc());
		}

		StringBuffer hql = new StringBuffer("select tf08,tf01 ");
		hql.append("from Tf08_clmxb tf08,Tf01_wxdw tf01 ");
		hql.append("where tf08.sgdw_id = tf01.id and tf08.zhxx_id = ");
		hql.append(project_id);
		hql.append("order by ");
		hql.append(orderField);
		hql.append(" ");
		hql.append(orderDirection);
		ro = queryService.searchByPage(hql.toString(), pageNum, numPerPage);
		List clmxList = null;
		if (ro != null) {
			totalCount = ro.getTotalRows();
			clmxList = new LinkedList();
			while (ro.next()) {
				Object[] o = new Object[2];
				o[0] = ro.get("tf08");
				o[1] = ro.get("tf01");
				clmxList.add(o);
			}
		}

		modelMap.put("clmxList", clmxList);
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);
		modelMap.put("totalCount", totalCount);
		return new ModelAndView("/WEB-INF/jsp/form/projectClmx.jsp", modelMap);
	}

	/**
	 * 工程及关联工程进度统计图
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping("/form/gcsgjdForMbk.do")
	public ModelAndView gcsgjdForMbk(HttpServletRequest request,
			HttpServletResponse response) {
		String view = "/WEB-INF/jsp/form/gcsgjd.jsp";
		ModelMap modelMap = new ModelMap();
		String params = "";
		List<Td00_gcxx> engineerList = null;
		List<Object> paramList = new ArrayList<Object>();
		String mbk_id = convertUtil
				.toString(request.getParameter("mbk_id"), "");
		StringBuffer hql = new StringBuffer("from Td00_gcxx as td00 ");
		hql.append("where td00.mbk_id=" + mbk_id);
		engineerList = (List<Td00_gcxx>) queryService
				.searchList(hql.toString());

		for (int i = 0; i < engineerList.size(); i++) {
			Td00_gcxx td00 = engineerList.get(i);
			params = "javascript:openFlowForm('{project_id:" + td00.getId()
					+ ",doc_id:" + td00.getId() + ",module_id:102,"
					+ "node_id:-1}');";
			paramList.add(params);
		}
		modelMap.put("paramList", paramList);
		modelMap.put("engineerList", engineerList);
		return new ModelAndView(view, modelMap);
	}

	/**
	 * 选择部门 查找带回
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/form/selectDept.do")
	public ModelAndView selectDept(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String view = "/WEB-INF/jsp/form/selectDept.jsp";
		ModelMap modelMap = new ModelMap();
		List deptList = null;
		Ta01_dept dept = null;
		ResultObject ro = null;
		int totalCount = 0;
		int pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		int numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 20);
		String orderDirection = convertUtil.toString(request
				.getParameter("orderDirection"), "asc");
		String orderField = convertUtil.toString(request
				.getParameter("orderField"), "name");
		String searchStr = convertUtil.toString(request
				.getParameter("searchStr"));
		StringBuffer hql = new StringBuffer();
		hql.append("select dept from Ta01_dept dept where 1=1 ");
		// 条件
		if (!searchStr.equals(""))
			hql.append("and dept.name like '%" + searchStr + "%' ");
		hql.append(" order by dept." + orderField);
		hql.append(" " + orderDirection);
		ro = queryService.searchByPage(hql.toString(), pageNum, numPerPage);
		if (ro != null) {
			totalCount = ro.getTotalRows();
			deptList = new LinkedList();
			while (ro.next()) {
				dept = (Ta01_dept) ro.get("dept");
				deptList.add(dept);
			}
		}
		modelMap.put("deptList", deptList);
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("searchStr", searchStr);
		modelMap.put("orderDirection", orderDirection);
		modelMap.put("totalCount", totalCount);

		modelMap.put("deptList", deptList);
		return new ModelAndView(view, modelMap);
	}

	/**
	 * 选择验收人员 查找带回
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/form/selectYsry.do")
	public ModelAndView selectYsry(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String view = "/WEB-INF/jsp/form/selectYsry.jsp";
		ModelMap modelMap = new ModelMap();
		List ysryList = null;
		Ta03_user ysry = null;
		ResultObject ro = null;
		int totalCount = 0;
		int pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		int numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 20);
		String orderDirection = convertUtil.toString(request
				.getParameter("orderDirection"), "asc");
		String orderField = convertUtil.toString(request
				.getParameter("orderField"), "name");
		String searchStr = convertUtil.toString(request
				.getParameter("searchStr"));
		String dept_ids = convertUtil
				.toString(request.getParameter("dept_ids"));

		StringBuffer hql = new StringBuffer();
		hql.append("select ysry from Ta03_user ysry where 1=1 ");
		// 条件
		if (!searchStr.equals(""))
			hql.append("and ysry.name like '%" + searchStr + "%' ");
		if (!dept_ids.equals(""))
			hql.append(" and ysry.dept_id in(" + dept_ids + ")");
		hql.append(" order by ysry." + orderField);
		hql.append(" " + orderDirection);
		ro = queryService.searchByPage(hql.toString(), pageNum, numPerPage);
		if (ro != null) {
			totalCount = ro.getTotalRows();
			ysryList = new LinkedList();
			while (ro.next()) {
				ysry = (Ta03_user) ro.get("ysry");
				ysryList.add(ysry);
			}
		}
		modelMap.put("ysryList", ysryList);
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("searchStr", searchStr);
		modelMap.put("orderDirection", orderDirection);
		modelMap.put("totalCount", totalCount);
		modelMap.put("ysryList", ysryList);
		return new ModelAndView(view, modelMap);
	}

	@RequestMapping("/form/aqysEdit.do")
	public ModelAndView aqysEdit(HttpServletRequest request,
			HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		String view = "/WEB-INF/jsp/form/aqysd.jsp";
		StringBuffer hql = new StringBuffer();
		List td52List = null;
		Long project_id = convertUtil
				.toLong(request.getParameter("project_id"));
		String canSave = convertUtil.toString(request.getParameter("canSave"));

		hql.append("select t from Td52_aqys t where t.project_id=");
		hql.append(project_id);
		hql.append(" order by t.ipa");

		td52List = queryService.searchList(hql.toString());

		modelMap.put("canSave", canSave);
		modelMap.put("Td52_aqysList", td52List);
		modelMap.put("project_id", project_id);
		return new ModelAndView(view, modelMap);
	}

	@RequestMapping("/form/pgsp.do")
	public ModelAndView pgsp(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelMap modelMap = new ModelMap();
		StringBuffer sql = new StringBuffer("");
		String user_name = null;
		Long user_id = null;
		Long module_id = convertUtil.toLong(request.getParameter("module_id"));
		Long project_id = convertUtil
				.toLong(request.getParameter("project_id"));
		Long sys_wxdw_id = convertUtil.toLong(request
				.getParameter("sys_wxdw_id"));
		Long man_wxdw_id = convertUtil.toLong(request
				.getParameter("man_wxdw_id"));
		String splb = convertUtil.toString(request.getParameter("splb"),"xmpg");
		Long doc_id = convertUtil.toLong(request.getParameter("doc_id"));
		Long id=convertUtil.toLong(request.getParameter("id"));
		Map splbMap = new HashMap();
		splbMap.put("gghte", "更改合同额");
		splbMap.put("xmpg", "项目派工");

		HttpSession session = request.getSession();
		if (session != null) {
			Ta03_user ta03 = (Ta03_user) session.getAttribute("user");
			user_name = ta03.getName();
			user_id = ta03.getId();
		}

		sql.delete(0, sql.length());
		sql.append("select id from Td08_pgspd ");
		sql.append("where project_id = ");
		sql.append(project_id);
		sql.append(" and sp_flag is null ");
		if (!splb.equals("")) {
			sql.append(" and splb='");
			sql.append(splbMap.get(splb));
			sql.append("' ");
		}
		if (doc_id!=-1) {
			sql.append(" and id=");
			sql.append(doc_id);
		}
		sql.append("and cjr = '");
		sql.append(user_name);
		sql.append("' order by id desc ");
		List list = queryService.searchList(sql.toString());
		if (list != null && list.size() > 0) {
			Long opernode_id = -1L;
			sql.delete(0, sql.length());
			sql.append("select id from Tb12_opernode ");
			sql.append("where project_id = ");
			sql.append(project_id);
			sql.append(" and doc_id = ");
			sql.append(convertUtil.toLong(list.get(0)));
			sql.append(" and node_id = ");
			sql.append(module_id + "01");
			sql.append(" order by id desc ");
			List t_list = queryService.searchList(sql.toString());
			if (t_list != null && t_list.size() > 0) {
				opernode_id = (Long) (t_list.get(0));
			}
			return new ModelAndView("../openForm.do?project_id=" + project_id
					+ "&module_id=" + module_id + "&doc_id="
					+ (Long) (list.get(0)) + "&opernode_id=" + opernode_id
					+ "&node_id=" + module_id + "01&user_id=" + user_id
					+ "&sys_wxdw_id=" + sys_wxdw_id + "&man_wxdw_id="
					+ man_wxdw_id);
		} else {
			return new ModelAndView("../flowForm.do?user_id=" + user_id
					+ "&node_id=" + module_id + "01&project_id=" + project_id
					+ "&sys_wxdw_id=" + sys_wxdw_id + "&man_wxdw_id="
					+ man_wxdw_id);
		}
	}

	@RequestMapping("/sysManage/ajaxCascadeMenu.do")
	public void ajaxCascadeMenu(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding(request.getCharacterEncoding());
		response.setContentType("text/html;charset=GBK");
		String var1 = request.getParameter("var1");
		String var2 = request.getParameter("var2");
		String var3 = request.getParameter("var3");
		String var4 = request.getParameter("var4");
		String var5 = convertUtil.toString(request.getParameter("var5"), "id");
		StringBuffer hql = new StringBuffer();
		if (!var2.equals("null")) {
			var1 = var1.replace('.', '/');
			String[] str_arr = var1.split("/");
			if (str_arr.length != 2) {
				throw new Exception("请确认'" + var1 + "'符合类名.列名的形式!");
			}
			String classname = str_arr[0];
			String columnname = str_arr[1];
			// 核心sql
			hql.append("select distinct(c.");
			hql.append(var3);
			hql.append("),c.");
			hql.append(var4);
			hql.append(" from ");
			hql.append(classname);
			hql.append(" c,");
			hql.append(" Tf04_wxdw_user wu,");
			hql.append(" Ta03_user u ");
			hql.append(" where ");
			hql.append("c.");
			hql.append(columnname);
			hql.append("=wu.wxdw_id ");
			hql.append(" and wu.user_id=");
			hql.append("u.id and u.dept_id=");
			hql.append(var2);
			hql.append(" order by ");
			hql.append("c.");
			hql.append(var5);
			// String hsql = "select " + var3 + "," + var4 + " from " +
			// classname
			// + " where " + columnname + "='" + var2 + "'"
			// + " order by "+var5;
			List<Object[]> list = null;
			try {
				list = (List<Object[]>) dao.search(hql.toString());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(hql.toString());
			}
			String s = "";
			for (Object[] o : list) {
				s += "<option value='" + o[0].toString() + "'>"
						+ o[1].toString() + "</option>";
			}
			PrintWriter out = response.getWriter();
			out.print(s);
			out.flush();
			out.close();
		}
	}

	@RequestMapping("/sysManage/ajaxUserMenu.do")
	public void ajaxUserMenu(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding(request.getCharacterEncoding());
		response.setContentType("text/html;charset=GBK");
		String var1 = request.getParameter("var1");
		String var2 = request.getParameter("var2");
		String var3 = request.getParameter("var3");
		String var4 = request.getParameter("var4");
		String var5 = convertUtil.toString(request.getParameter("var5"), "id");
		StringBuffer hql = new StringBuffer();
		if (!var2.equals("null")) {
			var1 = var1.replace('.', '/');
			String[] str_arr = var1.split("/");
			if (str_arr.length != 2) {
				throw new Exception("请确认'" + var1 + "'符合类名.列名的形式!");
			}
			String classname = str_arr[0];
			String columnname = str_arr[1];
			hql.append("select distinct(c.");
			hql.append(var3);
			hql.append("),c.");
			hql.append(var4);
			hql.append(" from ");
			hql.append(classname);
			hql.append(" c,");
			hql.append(" Tf04_wxdw_user wu,");
			hql.append(" Tf01_wxdw w ");
			hql.append(" where ");
			hql.append("c.");
			hql.append(columnname);
			hql.append("=wu.user_id ");
			hql.append(" and wu.wxdw_id=");
			hql.append("w.id and c.dept_id=4 ");
			hql.append(" and w.id=");
			hql.append(var2);
			hql.append(" order by ");
			hql.append("c.");
			hql.append(var5);
			List<Object[]> list = null;
			try {
				list = (List<Object[]>) dao.search(hql.toString());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(hql.toString());
			}
			String s = "";
			for (Object[] o : list) {
				s += "<option value='" + o[0].toString() + "'>"
						+ o[1].toString() + "</option>";
			}
			PrintWriter out = response.getWriter();
			out.print(s);
			out.flush();
			out.close();
		}
	}

	/**
	 * 项目统计明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 *             ModelAndView
	 */
	@RequestMapping("/aux/xmmxList.do")
	public ModelAndView xmmxList(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String view = "/WEB-INF/jsp/form/xmmxList.jsp";
		ModelMap modelMap = new ModelMap();
		StringBuffer hql = new StringBuffer();
		String hqlStr;
		Integer pageNum = convertUtil.toInteger(
				request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 20);
		Integer totalCount = 0;
		Integer totalPages = 0;
		Integer op = convertUtil.toInteger(request.getParameter("op"), 0);
		String keyword = convertUtil.toString(request.getParameter("keyword"));

		String orderField = convertUtil.toString(request
				.getParameter("orderField"), "id");
		String orderType = convertUtil.toString(request
				.getParameter("orderDirection"), "asc");
		String xmgly = convertUtil.toString(request.getParameter("xmgly"));
		if (!xmgly.equals("")) {
			xmgly = new String(xmgly.getBytes("iso-8859-1"), "gbk");
		}
		String jssj = convertUtil.toString(request.getParameter("jssj"));
		String sjjgsj = convertUtil.toString(request.getParameter("sjjgsj"));

		String lxsj1 = convertUtil.toString(request.getParameter("lxsj1"), "");
		String lxsj2 = convertUtil.toString(request.getParameter("lxsj2"), "");
		String pdsj1 = convertUtil.toString(request.getParameter("pdsj1"), "");
		String pdsj2 = convertUtil.toString(request.getParameter("pdsj2"), "");
		String dwlb = convertUtil.toString(request.getParameter("dwlb"), "sg");
		String ywxm = convertUtil.toString(request.getParameter("ywxm"), "");
		String mc = convertUtil.toString(request.getParameter("mc"));
		if (!mc.equals("")) {
			mc = new String(mc.getBytes("iso-8859-1"), "gbk");
		}

		String sql_tmp = "";
		if (!lxsj1.equals("") && !lxsj2.equals("")) {
			sql_tmp += "and lxsj >= to_date('" + lxsj1 + "','yyyy-mm-dd') ";
			sql_tmp += "and lxsj <= to_date('" + lxsj2 + "','yyyy-mm-dd') ";
		}
		if (!pdsj1.equals("") && !pdsj2.equals("")) {
			if (dwlb.equals("sj")) {
				sql_tmp += "and sjpgsj >= to_date('" + pdsj1
						+ "','yyyy-mm-dd') ";
				sql_tmp += "and sjpgsj <= to_date('" + pdsj2
						+ "','yyyy-mm-dd') ";
			}
			if (dwlb.equals("sg")) {
				sql_tmp += "and sgpfsj >= to_date('" + pdsj1
						+ "','yyyy-mm-dd') ";
				sql_tmp += "and sgpfsj <= to_date('" + pdsj2
						+ "','yyyy-mm-dd') ";
			}
			if (dwlb.equals("jl")) {
				sql_tmp += "and jlpfsj >= to_date('" + pdsj1
						+ "','yyyy-mm-dd') ";
				sql_tmp += "and jlpfsj <= to_date('" + pdsj2
						+ "','yyyy-mm-dd') ";
			}
		}

		String sql_tmp2 = "";
		if (!lxsj1.equals("") && !lxsj2.equals("")) {
			sql_tmp2 += "and lxsj >= to_date('" + lxsj1 + "','yyyy-mm-dd') ";
			sql_tmp2 += "and lxsj <= to_date('" + lxsj2 + "','yyyy-mm-dd') ";
		}
		if (!pdsj1.equals("") && !pdsj2.equals("")) {
			sql_tmp2 += "and sgpfsj >= to_date('" + pdsj1 + "','yyyy-mm-dd') ";
			sql_tmp2 += "and sgpfsj <= to_date('" + pdsj2 + "','yyyy-mm-dd') ";
		}

		hql.append("select xmmx from Td01_xmxx xmmx where 1=1 ");
		if (!xmgly.equals("")) {
			hql.append(" and xmmx.xmgly='");
			hql.append(xmgly);
			hql.append("' ");
		}

		if (!keyword.equals("")) {
			hql.append("and xmmx.xmmc like'%");
			hql.append(keyword);
			hql.append("%' ");
			hql.append(" or xmmx.xmbh like'%");
			hql.append(keyword);
			hql.append("' ");
		}

		if (op == 0) {// 项目数
			if (!lxsj1.equals("") && !lxsj2.equals("")) {
				hql
						.append("and lxsj >= to_date('" + lxsj1
								+ "','yyyy-mm-dd') ");
				hql
						.append("and lxsj <= to_date('" + lxsj2
								+ "','yyyy-mm-dd') ");
			}
			if (!pdsj1.equals("") && !pdsj2.equals("")) {
				hql.append("and ((sjpgsj >= to_date('" + pdsj1
						+ "','yyyy-mm-dd') ");
				hql.append("and sjpgsj <= to_date('" + pdsj2
						+ "','yyyy-mm-dd')) ");
				hql.append("or (sgpfsj >= to_date('" + pdsj1
						+ "','yyyy-mm-dd') ");
				hql.append("and sgpfsj <= to_date('" + pdsj2
						+ "','yyyy-mm-dd')) ");
				hql.append("or (jlpfsj >= to_date('" + pdsj1
						+ "','yyyy-mm-dd') ");
				hql.append("and jlpfsj <= to_date('" + pdsj2
						+ "','yyyy-mm-dd'))) ");
			}
		}
		if (op == 2 || op == 3 || op == 4) {// 派工数
			hql.append(" and [dw] is not null ");
			if (!lxsj1.equals("") && !lxsj2.equals("")) {
				hql
						.append("and lxsj >= to_date('" + lxsj1
								+ "','yyyy-mm-dd') ");
				hql
						.append("and lxsj <= to_date('" + lxsj2
								+ "','yyyy-mm-dd') ");
			}
			if (!pdsj1.equals("") && !pdsj2.equals("")) {
				hql.append("and [pdsj] >= to_date('" + pdsj1
						+ "','yyyy-mm-dd') ");
				hql.append("and [pdsj] <= to_date('" + pdsj2
						+ "','yyyy-mm-dd') ");
			}
		}
		if (op == 2) {// 派设计
			hqlStr = hql.toString();
			hqlStr = hqlStr.replace("[dw]", "sjdw").replace("[pdsj]", "sjpgsj");
			hql.delete(0, hql.length());
			hql.append(hqlStr);
		}
		if (op == 3) {// 派施工
			hqlStr = hql.toString();
			hqlStr = hqlStr.replace("[dw]", "sgdw").replace("[pdsj]", "sgpfsj");
			hql.delete(0, hql.length());
			hql.append(hqlStr);
		}
		if (op == 4) {// 派监理
			hqlStr = hql.toString();
			hqlStr = hqlStr.replace("[dw]", "jldw").replace("[pdsj]", "jlpfsj");
			hql.delete(0, hql.length());
			hql.append(hqlStr);
		}
		if (op == 5 && dwlb.equals("sg")) {// 超期数
			hql.append(" and ");
			hql.append(dwlb);
			hql.append("dw = '");
			hql.append(mc);
			hql.append("'");
			hql
					.append(" and (sjkgsj + yqgq < sjjgsj or (sjjgsj is null and sjkgsj + yqgq < sysdate)) ");
			hql.append(sql_tmp2);
		}
		if (op == 6) {// 决算数
			hql.append(" and ");
			hql.append(dwlb);
			hql.append("dw = '");
			hql.append(mc);
			hql.append("' and jssj is not null ");
			hql.append(sql_tmp2);
		}
		if (op == 7) {// 派单数
			hql.append(" and ");
			hql.append(dwlb);
			hql.append("dw='");
			hql.append(mc);
			hql.append("' ");
			hql.append(sql_tmp);
		}
		if (op == 8) {// 接单数
			hql.append(" and ");
			hql.append(dwlb);
			hql.append("dw = '");
			hql.append(mc);
			hql.append("' and ");
			hql.append(dwlb);
			hql.append("ysl is not null ");
			hql.append(sql_tmp);
		}
		if (op == 9) {// 以人为单位的超期
			hql
					.append(" and (sjkgsj + yqgq < sjjgsj or (sjjgsj is null and sjkgsj + yqgq < sysdate)) ");
		}
		if (op == 10) {// 以人为单位的决算
			hql.append(" and jssj is not null ");
		}
		if (op==11) {//实际选择单位的项目
			hql.delete(0, hql.length());
			hql.append("select a from Td01_xmxx a,Td08_pgspd b where 1=1 ");
			hql.append("and a.id=b.project_id and a.sgdw=b.sjxzdw ");
			hql.append(" and ");
			hql.append(dwlb);
			hql.append("dw='");
			hql.append(mc);
			hql.append("' ");
			hql.append(" and b.splb='");
			hql.append("项目派工");
			hql.append("' ");
			hql.append(" and b.sp_flag=1 ");
			hql.append(sql_tmp);
			orderField="a.id";
		}

		if (!jssj.equals("")) {
			hql.append("and x.jssj=to_date('");
			hql.append(jssj);
			hql.append("','yyyy-mm-dd'");
			hql.append(") ");
		}
		if (!sjjgsj.equals("")) {
			hql.append("and x.sjjgsj=to_date('");
			hql.append(sjjgsj);
			hql.append("','yyyy-mm-dd') ");
		}

		hql.append("order by ");
		hql.append(orderField);
		ResultObject ro = queryService.searchByPage(hql.toString(), pageNum,
				numPerPage);
		List xmxxList = ro.getList();
		// 地区
		List areaList = queryService
				.searchList("select distinct(a.name) from Tc02_area a");
		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		modelMap.put("op", op);
		modelMap.put("xmgly", xmgly);
		modelMap.put("dwlb", dwlb);
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("xmxxList", xmxxList);
		modelMap.put("areaList", areaList);
		return new ModelAndView(view, modelMap);
	}

	/**
	 * 用户登录详情
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 *             ModelAndView
	 */
	@RequestMapping("/aux/userLoginDetail.do")
	public ModelAndView userLoginDetail(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String view = "/WEB-INF/jsp/search/userLoginDetail.jsp";
		ModelMap modelMap = new ModelMap();
		StringBuffer hql = new StringBuffer();
		ResultObject ro = null;
		Integer pageNum = convertUtil.toInteger(
				request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request
				.getParameter("numPerPage"), 20);
		Integer totalCount = 0;
		Integer totalPages = 0;
		String orderField = convertUtil.toString(request
				.getParameter("orderField"), "a.login_date");
		String orderType = convertUtil.toString(request
				.getParameter("orderDirection"), "desc");
		String login_name = convertUtil.toString(request
				.getParameter("login_name"));
		String tjlb = convertUtil
				.toString(request.getParameter("tjlb"), "hzdw");
		String login_date1 = convertUtil.toString(request
				.getParameter("login_date1"));
		String login_date2 = convertUtil.toString(request
				.getParameter("login_date2"));

		if (!login_name.equals("")) {
			login_name = new String(login_name.getBytes("iso-8859-1"), "gbk");
		}
		if (tjlb.equals("xmgly")) {
			hql
					.append("select a.user_name,b.mobile_tel,c.name,b.area_name,a.login_ip,a.login_date,a.logout_date,a.systeminfo ");
			hql.append("from Tz03_login_log a,Ta03_user b ,Ta01_dept c ");
			hql.append("where a.login_id=b.login_id and b.dept_id=c.id ");
			if (!login_name.equals("")) {
				hql.append(" and a.login_id='");
				hql.append(login_name);
				hql.append("' ");
			}

		} else if (tjlb.equals("hzdw")) {
			hql
					.append("select a.user_name,b.mobile_tel,e.name,b.area_name,a.login_ip,a.login_date,a.logout_date,a.systeminfo from Tz03_login_log a ,Ta03_user b,Tf01_wxdw c,Tf04_wxdw_user d,Ta01_dept e ");
			hql
					.append(" where a.login_id=b.login_id and b.id=d.user_id and c.id=d.wxdw_id and b.dept_id=e.id ");
			if (!login_name.equals("")) {
				hql.append(" and c.mc='");
				hql.append(login_name);
				hql.append("' ");
			}
		}
		if (!login_date1.equals("")) {
			hql.append(" and login_date>=to_date('");
			hql.append(login_date1);
			hql.append("','yyyy-mm-dd') ");
		}
		if (!login_date2.equals("")) {
			hql.append(" and login_date<=to_date('");
			hql.append(login_date2);
			hql.append("','yyyy-mm-dd') ");
		}

		hql.append(" order by ");
		hql.append(orderField);
		hql.append(" ");
		hql.append(orderType);
		ro = queryService.searchByPage(hql.toString(), pageNum, numPerPage);
		List userLoginList = ro.getList();
		// 获取总条数和总页数
		totalPages = ro.getTotalPages();
		totalCount = ro.getTotalRows();

		modelMap.put("login_name", login_name);
		modelMap.put("login_date1", login_date1);
		modelMap.put("login_date2", login_date2);
		modelMap.put("tjlb", tjlb);
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalCount", totalCount);
		modelMap.put("orderDirection", orderType);
		modelMap.put("orderField", orderField);
		modelMap.put("pageNum", pageNum);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("userLoginList", userLoginList);
		return new ModelAndView(view, modelMap);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/aux/pdqk.do")
	public ModelAndView pdqk(HttpServletRequest request,
			HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		Long project_id = convertUtil.toLong(
				request.getParameter("project_id"), -10L);
		Td00_gcxx td00 = (Td00_gcxx) dao.getObject(Td00_gcxx.class, project_id);
		Long xm_id = convertUtil.toLong(request.getParameter("xm_id"));
		Td01_xmxx td01 = (Td01_xmxx) dao.getObject(Td01_xmxx.class, xm_id);
		Integer id = convertUtil.toInteger(request.getParameter("id"));
		StringBuffer hql = new StringBuffer();
		String gclb = "";
		String dq = "";
		int ssnd = -1;
		if (td00 != null) {
			gclb = td00.getGclb();
			dq = td00.getSsdq();
			Date lxsj = td00.getCjrq();
			if (lxsj == null) {
				ssnd = DateGetUtil.getYear();
			} else {
				ssnd = DateGetUtil.getYear(lxsj);
			}
		} else if (td01 != null) {
			gclb = td01.getGclb();
			dq = td01.getSsdq();
			Date lxsj = td01.getLxsj();
			if (lxsj == null) {
				ssnd = DateGetUtil.getYear();
			} else {
				ssnd = DateGetUtil.getYear(lxsj);
			}
		} else {
			// System.out.println("找不到工程或项目");
			return new ModelAndView(
					"/WEB-INF/jsp/search/pdqk.jsp?errormsg=tdnotfound");
		}
		// 获得 所有相关地区专业 未停工 类别为施工的合作单位
		hql
				.append("select tf01,tf05 from Tf01_wxdw tf01,Tf05_wxdw_dygx tf05 where tf01.id=tf05.wxdw_id and tf05.zy='");
		hql.append(gclb);
		hql.append("' and tf05.dq='");
		hql.append(dq);
		hql.append("' and tf05.lb='fezb' and tf05.v1>0 and tf05.nd='");
		hql.append(ssnd);
		hql.append("' and tf01.lb='施工' and tf01.zt<>'停工'");
		List<Object[]> wxdwList = (List<Object[]>) dao.search(hql.toString());
		if (wxdwList == null || wxdwList.size() == 0) {
			// System.out.println("没有符合的合作单位");
			return new ModelAndView(
					"/WEB-INF/jsp/search/pdqk.jsp?errormsg=tfnotfound");
		}
		// 建立数组o[11]
		// o[0]:tf01;o[1]:tf05;o[2]:zhdf(综合得分);o[3]:决算率;o[4]:综合得分排名;o[5]:决算率排名;o[6]:计划份额;o[7]:实际份额;o[8]:份额偏差率;o[9]:份额偏差率档级
		List<Object[]> objectsList = new ArrayList<Object[]>();
		for (Object[] objects : wxdwList) {
			Object[] o = new Object[11];
			o[0] = objects[0];
			o[1] = objects[1];
			objectsList.add(o);
		}
		// 判断计划份额占比与实际份额占比
		// 相关地区相关工程类别的所有工程的总工日 zgr 默认为0
		// 黄钢强修改：占比暂由td00.(ys_pggr + ys_jggr)改td01.ys_sgf 为计算依据
		double zgr = convertUtil
				.toDouble(
						dao
								.search(
										"select sum(case when sghtje is null then nvl(ys_rgf,0) else sghtje end ) from Td01_xmxx where sgdw is not null and ssdq='"
												+ dq
												+ "' and gclb='"
												+ gclb
												+ "' and to_char(lxsj,'yyyy') = '"
												+ ssnd + "'").get(0), 0D);
		// flag:未通过检测的个数
		int flag = 0;
		// passedList 通过条件的合作单位 暂存入该LIST
		for (Object[] objects : objectsList) {
			Tf01_wxdw tf01 = (Tf01_wxdw) objects[0];
			Tf05_wxdw_dygx tf05 = (Tf05_wxdw_dygx) objects[1];
			// fezb:实际份额占比
			Double fezb = 0D;
			if (zgr != 0) {
				double gr = convertUtil
						.toDouble(
								dao
										.search(
												"select sum(case when sghtje is null then nvl(ys_rgf,0) else sghtje end ) from Td01_xmxx where sgdw='"
														+ tf01.getMc()
														+ "' and ssdq='"
														+ dq
														+ "' and gclb='"
														+ gclb
														+ "' and to_char(lxsj,'yyyy') = '"
														+ ssnd + "'").get(0),
								0D);
				fezb = gr / zgr;
			}
			// tf05.getV1():预定份额占比
			objects[6] = tf05.getV1();
			objects[7] = fezb * 100;
			if (tf05.getV1() <= fezb * 100) {
				objects[10] = 1;
				flag++;
			}
		}
		if (flag == objectsList.size()) {
			for (Object[] objects : objectsList) {
				objects[10] = null;
			}
			flag = 0;
		}
		// 判断在建工程数和最大工程数
		int flag2 = 0;
		for (Object[] objects : objectsList) {
			if (objects[10] != null) {
				continue;
			}
			// 在建工程数
			Long zjgcs = convertUtil.toLong(dao.search(
					"select count(*) from Td01_xmxx where sgdw='"
							+ ((Tf01_wxdw) objects[0]).getMc()
							+ "' and sjjgsj is null").get(0));
			// 最大工程数
			Double zdgcs = 0D;
			List<Double> zdgcsList = (List<Double>) dao
					.search("select v1 from Tf05_wxdw_dygx tf05 where tf05.wxdw_id="
							+ ((Tf01_wxdw) objects[0]).getId()
							+ " and tf05.zy='"
							+ gclb
							+ "' and tf05.lb='zdgcs' and tf05.v1>0 and tf05.nd='"
							+ ssnd + "'");
			if (zdgcsList != null && !zdgcsList.isEmpty()) {
				zdgcs = convertUtil.toDouble(zdgcsList.get(0), 0D);
			}
			if (zjgcs >= zdgcs) {
				objects[10] = 2;
				flag++;
			}
		}
		if (flag2 == objectsList.size() - flag) {
			for (Object[] objects : objectsList) {
				if ((Integer) objects[10] == 2)
					objects[10] = null;
			}
		}
		// 置施工单位综合评分和决算率
		for (Object[] objects : objectsList) {
			Tf01_wxdw tf01 = (Tf01_wxdw) objects[0];
			List<Long> tmpList = (List<Long>) dao
					.search("select zhdf from Tf27_wxdwzhpf where wxdw_id="
							+ tf01.getId() + " order by cjrq desc");
			if (!tmpList.isEmpty()) {
				objects[2] = convertUtil.toLong(tmpList.get(0));
			} else {
				objects[2] = 0L;
			}
			// objs[3] 决算率
			// 决算率默认100%
			objects[3] = 0D;
			// 总项目数量
			long xmsl = ((List<Long>) dao
					.search("select count(*) from Td01_xmxx where sgdw='"
							+ tf01.getMc() + "'")).get(0);
			if (xmsl != 0) {
				// 决算项目数量
				long jssl = ((List<Long>) dao
						.search("select count(*) from Td01_xmxx where jssj is not null and sgdw='"
								+ tf01.getMc() + "'")).get(0);
				// 决算率=决算数/总数
				objects[3] = (double) jssl / (double) xmsl;
			}
		}
		// 置偏差率和偏差率档级
		for (Object[] o : objectsList) {
			o[8] = ((Double) o[6] - (Double) o[7]) / (Double) o[6] * 100;
			o[9] = convertUtil.toString(dao.search(
					"select dj from Tf11_fepcl where (qzsx>" + o[8]
							+ " and (qzxx<" + o[8]
							+ " or qzxx is null) or (qzsx=" + o[8]
							+ " and qzxx=" + o[8] + "))").get(0));
		}
		List<Object[]> allList = new ArrayList<Object[]>(objectsList);
		modelMap.put("allList", allList);
		for (int i = objectsList.size() - 1; i >= 0; i--) {
			if (objectsList.get(i)[10] != null) {
				objectsList.remove(i);
			}
		}
		// 将objs[2]objs[3]分别冒泡排序
		// 先按objs[2]排 从低到高
		Object[] temp;
		for (int i = 0; i < objectsList.size(); i++) {
			for (int j = objectsList.size() - 1; j > i; j--) {
				if ((Long) objectsList.get(j)[2] > (Long) objectsList
						.get(j - 1)[2]) {
					temp = objectsList.get(j);
					objectsList.set(j, objectsList.get(j - 1));
					objectsList.set(j - 1, temp);
				}
			}
		}
		// 综合评分排名
		for (int i = 1; i <= objectsList.size(); i++) {
			if (i == 1
					|| !objectsList.get(i - 2)[2]
							.equals(objectsList.get(i - 1)[2]))
				objectsList.get(i - 1)[4] = i;
			else {
				objectsList.get(i - 1)[4] = objectsList.get(i - 2)[4];
			}
		}
		// 决算率 从低到高
		for (int i = 0; i < objectsList.size(); i++) {
			for (int j = objectsList.size() - 1; j > i; j--) {
				if ((Double) objectsList.get(j)[3] > (Double) objectsList
						.get(j - 1)[3]) {
					temp = objectsList.get(j);
					objectsList.set(j, objectsList.get(j - 1));
					objectsList.set(j - 1, temp);
				}
			}
		}
		// 决算率排名
		for (int i = 1; i <= objectsList.size(); i++) {
			if (i == 1
					|| !objectsList.get(i - 2)[3]
							.equals(objectsList.get(i - 1)[3])) {
				objectsList.get(i - 1)[5] = i;
			} else {
				objectsList.get(i - 1)[5] = objectsList.get(i - 2)[5];
			}
		}
		// 利用数据库做排序
		Session session = dao.getHibernateSession();
		Transaction tx = session.beginTransaction();
		Long nextval = -1L;
		try {

			// o[0]:tf01;o[1]:tf05;o[2]:zhdf(综合得分);o[3]:决算率;o[4]:综合得分排名;o[5]:决算率排名;o[6]:计划份额;o[7]:实际份额;o[8]:份额偏差率;o[9]:份额偏差率档级
			tx.begin();
			nextval = ((BigDecimal) (session
					.createSQLQuery("select batch_num.nextval from dual")
					.uniqueResult())).longValue();
			for (Object[] o : objectsList) {
				Tmp_zdxp zdxp = new Tmp_zdxp();
				zdxp.setDj((String) o[9]);
				zdxp.setPm((Integer) o[4] * 0.6 + (Integer) o[5] * 0.4);
				zdxp.setZhdf((Long) o[2]);
				zdxp.setJsl((Double) o[3]);
				zdxp.setJhfezb((Double) o[6]);
				zdxp.setWxdw_id((Long) ((Tf01_wxdw) o[0]).getId());
				zdxp.setBatch_no(nextval);
				session.save(zdxp);
			}
			session.flush();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
		List<Long> wxdw_ids = (List<Long>) dao
				.search("select wxdw_id from Tmp_zdxp where batch_no="
						+ nextval
						+ " order by dj asc,pm asc,zhdf desc,jsl desc,jhfezb desc");
		// dao.update("delete from Tmp_zdxp where batch_no=" + nextval);
		List<Object[]> tmpList = new ArrayList<Object[]>();
		int i = 0;
		// 只保留前3名
		for (Long wxdw_id : wxdw_ids) {
			for (Object[] o : objectsList) {
				if (((Tf01_wxdw) o[0]).getId().longValue() == wxdw_id
						.longValue()) {
					tmpList.add(o);
					break;
				}
			}
			if (++i == 3) {
				break;
			}
		}
		objectsList = new ArrayList<Object[]>(tmpList);
		// objectsList已经是排前三名的施工单位了
		// 判断有否关联交易额 交易额度是否超过关联交易额
		Tf01_wxdw result = null;
		for (Object[] o : objectsList) {
			// gljye:关联交易额
			List<Double> tmpGljyeList = (List<Double>) dao
					.search("select v1 from Tf05_wxdw_dygx tf05 where wxdw_id="
							+ ((Tf01_wxdw) o[0]).getId()
							+ " and tf05.lb='gljye' and tf05.v1>0 and tf05.nd=to_char(sysdate,'yyyy')");
			Double gljye = 0D;
			if (!tmpGljyeList.isEmpty()) {
				gljye = convertUtil.toDouble(tmpGljyeList.get(0), 0D);
			}
			// jyed:交易额度
			List<Double> tmpJyedList = (List<Double>) dao
					.search("select sum(ys_sgf) from Td01_xmxx td00 where sgdw='"
							+ ((Tf01_wxdw) o[0]).getMc()
							+ "' and ssnd=to_char(sysdate,'yyyy')");
			Double jyed = 0D;
			if (!tmpJyedList.isEmpty()) {
				jyed = convertUtil.toDouble(tmpJyedList.get(0), 0D);
			}
			if (gljye > jyed) {
				result = (Tf01_wxdw) o[0];
				break;
			}
		}
		if (result == null) {
			result = (Tf01_wxdw) objectsList.get(0)[0];
		}
		// 查找实际选择单位
		hql.delete(0, hql.length());
		hql
				.append("select distinct(b.id),a.sjxzdw from Td08_pgspd a,Tf01_wxdw b where 1=1 ");
		hql.append("and a.sjxzdw=b.mc ");
		hql.append("and a.id=");
		hql.append(id);
		List sjxzdw = queryService.searchList(hql.toString());
		Integer wxdw_id = 0;
		for (Object object : sjxzdw) {
			Object obj[] = (Object[]) object;
			wxdw_id = convertUtil.toInteger(obj[0]);
		}
		List pxsjxzdw = this.getSjxzdw(request, response, wxdw_id);
		modelMap.put("sjxzdw", sjxzdw);
		modelMap.put("pxsjxzdw", pxsjxzdw);
		modelMap.put("zdxp", result);
		modelMap.put("project_id", xm_id);
		return new ModelAndView("/WEB-INF/jsp/search/pdqk.jsp", modelMap);
	}

	/**
	 * 实际选择单位
	 * 
	 * @param request
	 * @param response
	 * @return List
	 */
	public List getSjxzdw(HttpServletRequest request,
			HttpServletResponse response, Integer wxdw_id_) {
		ModelMap modelMap = new ModelMap();
		Long project_id = convertUtil.toLong(
				request.getParameter("project_id"), -10L);
		Td00_gcxx td00 = (Td00_gcxx) dao.getObject(Td00_gcxx.class, project_id);
		Long xm_id = convertUtil.toLong(request.getParameter("xm_id"));
		Td01_xmxx td01 = (Td01_xmxx) dao.getObject(Td01_xmxx.class, xm_id);
		Integer id = convertUtil.toInteger(request.getParameter("id"));
		StringBuffer hql = new StringBuffer();
		String gclb = "";
		String dq = "";
		int ssnd = -1;
		if (td00 != null) {
			gclb = td00.getGclb();
			dq = td00.getSsdq();
			Date lxsj = td00.getCjrq();
			if (lxsj == null) {
				ssnd = DateGetUtil.getYear();
			} else {
				ssnd = DateGetUtil.getYear(lxsj);
			}
		} else if (td01 != null) {
			gclb = td01.getGclb();
			dq = td01.getSsdq();
			Date lxsj = td01.getLxsj();
			if (lxsj == null) {
				ssnd = DateGetUtil.getYear();
			} else {
				ssnd = DateGetUtil.getYear(lxsj);
			}
		}
		// 获得 所有相关地区专业 未停工 类别为施工的合作单位
		hql
				.append("select tf01,tf05 from Tf01_wxdw tf01,Tf05_wxdw_dygx tf05 where tf01.id=tf05.wxdw_id and tf05.zy='");
		hql.append(gclb);
		hql.append("' and tf05.dq='");
		hql.append(dq);
		hql.append("' and tf05.lb='fezb' and tf05.v1>0 and tf05.nd='");
		hql.append(ssnd);
		hql.append("' and tf01.lb='施工' and tf01.zt<>'停工' ");
		hql.append("and tf01.id=");
		hql.append(wxdw_id_);
		List<Object[]> wxdwList = (List<Object[]>) dao.search(hql.toString());

		// 建立数组o[11]
		// o[0]:tf01;o[1]:tf05;o[2]:zhdf(综合得分);o[3]:决算率;o[4]:综合得分排名;o[5]:决算率排名;o[6]:计划份额;o[7]:实际份额;o[8]:份额偏差率;o[9]:份额偏差率档级
		List<Object[]> objectsList = new ArrayList<Object[]>();
		for (Object[] objects : wxdwList) {
			Object[] o = new Object[11];
			o[0] = objects[0];
			o[1] = objects[1];
			objectsList.add(o);
		}
		// 判断计划份额占比与实际份额占比
		// 相关地区相关工程类别的所有工程的总工日 zgr 默认为0
		// 黄钢强修改：占比暂由td00.(ys_pggr + ys_jggr)改td01.ys_sgf 为计算依据
		double zgr = convertUtil
				.toDouble(
						dao
								.search(
										"select sum(case when sghtje is null then nvl(ys_rgf,0) else sghtje end ) from Td01_xmxx where sgdw is not null and ssdq='"
												+ dq
												+ "' and gclb='"
												+ gclb
												+ "' and to_char(lxsj,'yyyy') = '"
												+ ssnd + "'").get(0), 0D);
		// flag:未通过检测的个数
		int flag = 0;
		// passedList 通过条件的合作单位 暂存入该LIST
		for (Object[] objects : objectsList) {
			Tf01_wxdw tf01 = (Tf01_wxdw) objects[0];
			Tf05_wxdw_dygx tf05 = (Tf05_wxdw_dygx) objects[1];
			// fezb:实际份额占比
			Double fezb = 0D;
			if (zgr != 0) {
				double gr = convertUtil
						.toDouble(
								dao
										.search(
												"select sum(case when sghtje is null then nvl(ys_rgf,0) else sghtje end ) from Td01_xmxx where sgdw='"
														+ tf01.getMc()
														+ "' and ssdq='"
														+ dq
														+ "' and gclb='"
														+ gclb
														+ "' and to_char(lxsj,'yyyy') = '"
														+ ssnd + "'").get(0),
								0D);
				fezb = gr / zgr;
			}
			// tf05.getV1():预定份额占比
			objects[6] = tf05.getV1();
			objects[7] = fezb * 100;
			if (tf05.getV1() <= fezb * 100) {
				objects[10] = 1;
				flag++;
			}
		}
		if (flag == objectsList.size()) {
			for (Object[] objects : objectsList) {
				objects[10] = null;
			}
			flag = 0;
		}
		// 判断在建工程数和最大工程数
		int flag2 = 0;
		for (Object[] objects : objectsList) {
			if (objects[10] != null) {
				continue;
			}
			// 在建工程数
			Long zjgcs = convertUtil.toLong(dao.search(
					"select count(*) from Td01_xmxx where sgdw='"
							+ ((Tf01_wxdw) objects[0]).getMc()
							+ "' and sjjgsj is null").get(0));
			// 最大工程数
			Double zdgcs = 0D;
			List<Double> zdgcsList = (List<Double>) dao
					.search("select v1 from Tf05_wxdw_dygx tf05 where tf05.wxdw_id="
							+ ((Tf01_wxdw) objects[0]).getId()
							+ " and tf05.zy='"
							+ gclb
							+ "' and tf05.lb='zdgcs' and tf05.v1>0 and tf05.nd='"
							+ ssnd + "'");
			if (zdgcsList != null && !zdgcsList.isEmpty()) {
				zdgcs = convertUtil.toDouble(zdgcsList.get(0), 0D);
			}
			if (zjgcs >= zdgcs) {
				objects[10] = 2;
				flag++;
			}
		}
		if (flag2 == objectsList.size() - flag) {
			for (Object[] objects : objectsList) {
				if ((Integer) objects[10] == 2)
					objects[10] = null;
			}
		}
		// 置施工单位综合评分和决算率
		for (Object[] objects : objectsList) {
			Tf01_wxdw tf01 = (Tf01_wxdw) objects[0];
			List<Long> tmpList = (List<Long>) dao
					.search("select zhdf from Tf27_wxdwzhpf where wxdw_id="
							+ tf01.getId() + " order by cjrq desc");
			if (!tmpList.isEmpty()) {
				objects[2] = convertUtil.toLong(tmpList.get(0));
			} else {
				objects[2] = 0L;
			}
			// objs[3] 决算率
			// 决算率默认100%
			objects[3] = 0D;
			// 总项目数量
			long xmsl = ((List<Long>) dao
					.search("select count(*) from Td01_xmxx where sgdw='"
							+ tf01.getMc() + "'")).get(0);
			if (xmsl != 0) {
				// 决算项目数量
				long jssl = ((List<Long>) dao
						.search("select count(*) from Td01_xmxx where jssj is not null and sgdw='"
								+ tf01.getMc() + "'")).get(0);
				// 决算率=决算数/总数
				objects[3] = (double) jssl / (double) xmsl;
			}
		}
		// 置偏差率和偏差率档级
		for (Object[] o : objectsList) {
			o[8] = ((Double) o[6] - (Double) o[7]) / (Double) o[6] * 100;
			o[9] = convertUtil.toString(dao.search(
					"select dj from Tf11_fepcl where (qzsx>" + o[8]
							+ " and (qzxx<" + o[8]
							+ " or qzxx is null) or (qzsx=" + o[8]
							+ " and qzxx=" + o[8] + "))").get(0));
		}
		List<Object[]> allList = new ArrayList<Object[]>(objectsList);
		modelMap.put("allList", allList);
		for (int i = objectsList.size() - 1; i >= 0; i--) {
			if (objectsList.get(i)[10] != null) {
				objectsList.remove(i);
			}
		}
		// 将objs[2]objs[3]分别冒泡排序
		// 先按objs[2]排 从低到高
		Object[] temp;
		for (int i = 0; i < objectsList.size(); i++) {
			for (int j = objectsList.size() - 1; j > i; j--) {
				if ((Long) objectsList.get(j)[2] > (Long) objectsList
						.get(j - 1)[2]) {
					temp = objectsList.get(j);
					objectsList.set(j, objectsList.get(j - 1));
					objectsList.set(j - 1, temp);
				}
			}
		}
		// 综合评分排名
		for (int i = 1; i <= objectsList.size(); i++) {
			if (i == 1
					|| !objectsList.get(i - 2)[2]
							.equals(objectsList.get(i - 1)[2]))
				objectsList.get(i - 1)[4] = i;
			else {
				objectsList.get(i - 1)[4] = objectsList.get(i - 2)[4];
			}
		}
		// 决算率 从低到高
		for (int i = 0; i < objectsList.size(); i++) {
			for (int j = objectsList.size() - 1; j > i; j--) {
				if ((Double) objectsList.get(j)[3] > (Double) objectsList
						.get(j - 1)[3]) {
					temp = objectsList.get(j);
					objectsList.set(j, objectsList.get(j - 1));
					objectsList.set(j - 1, temp);
				}
			}
		}
		// 决算率排名
		for (int i = 1; i <= objectsList.size(); i++) {
			if (i == 1
					|| !objectsList.get(i - 2)[3]
							.equals(objectsList.get(i - 1)[3])) {
				objectsList.get(i - 1)[5] = i;
			} else {
				objectsList.get(i - 1)[5] = objectsList.get(i - 2)[5];
			}
		}
		// 利用数据库做排序
		Session session = dao.getHibernateSession();
		Transaction tx = session.beginTransaction();
		Long nextval = -1L;
		try {

			// o[0]:tf01;o[1]:tf05;o[2]:zhdf(综合得分);o[3]:决算率;o[4]:综合得分排名;o[5]:决算率排名;o[6]:计划份额;o[7]:实际份额;o[8]:份额偏差率;o[9]:份额偏差率档级
			tx.begin();
			nextval = ((BigDecimal) (session
					.createSQLQuery("select batch_num.nextval from dual")
					.uniqueResult())).longValue();
			for (Object[] o : objectsList) {
				Tmp_zdxp zdxp = new Tmp_zdxp();
				zdxp.setDj((String) o[9]);
				zdxp.setPm((Integer) o[4] * 0.6 + (Integer) o[5] * 0.4);
				zdxp.setZhdf((Long) o[2]);
				zdxp.setJsl((Double) o[3]);
				zdxp.setJhfezb((Double) o[6]);
				zdxp.setWxdw_id((Long) ((Tf01_wxdw) o[0]).getId());
				zdxp.setBatch_no(nextval);
				session.save(zdxp);
			}
			session.flush();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
		List<Long> wxdw_ids = (List<Long>) dao
				.search("select wxdw_id from Tmp_zdxp where batch_no="
						+ nextval
						+ " order by dj asc,pm asc,zhdf desc,jsl desc,jhfezb desc");
		// dao.update("delete from Tmp_zdxp where batch_no=" + nextval);
		List<Object[]> tmpList = new ArrayList<Object[]>();
		int i = 0;
		// 只保留前3名
		for (Long wxdw_id : wxdw_ids) {
			for (Object[] o : objectsList) {
				if (((Tf01_wxdw) o[0]).getId().longValue() == wxdw_id
						.longValue()) {
					tmpList.add(o);
					break;
				}
			}
			if (++i == 3) {
				break;
			}
		}
		objectsList = new ArrayList<Object[]>(tmpList);
		// objectsList已经是排前三名的施工单位了
		// 判断有否关联交易额 交易额度是否超过关联交易额
		Tf01_wxdw result = null;
		for (Object[] o : objectsList) {
			// gljye:关联交易额
			List<Double> tmpGljyeList = (List<Double>) dao
					.search("select v1 from Tf05_wxdw_dygx tf05 where wxdw_id="
							+ ((Tf01_wxdw) o[0]).getId()
							+ " and tf05.lb='gljye' and tf05.v1>0 and tf05.nd=to_char(sysdate,'yyyy')");
			Double gljye = 0D;
			if (!tmpGljyeList.isEmpty()) {
				gljye = convertUtil.toDouble(tmpGljyeList.get(0), 0D);
			}
			// jyed:交易额度
			List<Double> tmpJyedList = (List<Double>) dao
					.search("select sum(ys_sgf) from Td01_xmxx td00 where sgdw='"
							+ ((Tf01_wxdw) o[0]).getMc()
							+ "' and ssnd=to_char(sysdate,'yyyy')");
			Double jyed = 0D;
			if (!tmpJyedList.isEmpty()) {
				jyed = convertUtil.toDouble(tmpJyedList.get(0), 0D);
			}
			if (gljye > jyed) {
				result = (Tf01_wxdw) o[0];
				break;
			}
		}
		if (result == null) {
			result = (Tf01_wxdw) objectsList.get(0)[0];
		}
		return allList;
	}
}
