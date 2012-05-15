package com.jfms.controller.info;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Sheet;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.jfms.controller.base.ExcelRead;
import com.jfms.controller.base.ExcelWrite;
import com.jfms.dataObjects.base.Tc01_property;
import com.jfms.dataObjects.base.Tc03_area;
import com.jfms.dataObjects.info.Td00_jfxx;
import com.jfms.dataObjects.info.Td01_sbxx;
import com.jfms.dataObjects.info.Td11_jfpmsq;
import com.jfms.dataObjects.info.Td12_gljf;
import com.jfms.flowTrigger.FlowSendTrigger;
import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.PropertyInject;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.baseObject.SharedCfgData;
import com.netsky.base.controller.OperFile;
import com.netsky.base.dataObjects.Ta07_formfield;
import com.netsky.base.dataObjects.Te01_slave;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.OperProperties;
import com.netsky.base.utils.convertUtil;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta01_dept;

@Controller
public class Jfpmgl {
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
	 * 机房信息列表
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/info/jfpmList.do")
	public ModelAndView jfpmList(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		// 数据库相关变量
		StringBuffer sql = new StringBuffer("");
		ModelMap modelMap = new ModelMap();

		// 分页变量
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 20);
		Integer totalCount = 0;
		Integer pageNumShown = 0;

		// 排序变量
		String orderField = convertUtil.toString(request.getParameter("orderField"), "id");
		String orderDirection = convertUtil.toString(request.getParameter("orderDirection"), "desc");

		// 查询变量
		String ssqy = convertUtil.toString(request.getParameter("ssqy"), "");
		String jdxz = convertUtil.toString(request.getParameter("jdxz"), "");
		String keywords = convertUtil.toString(
				request.getParameter("keywords"), "");

		// 获取地区列表
		modelMap.put("ssqyList", dao.getObjects(Tc03_area.class));

		// 获取局点性质：Tc01_property type="局点性质"
		sql.delete(0, sql.length());
		sql.append(" from Tc01_property ");
		sql.append(" where type='局点性质'");
		sql.append(" order by id ");
		List<Tc01_property> jdxzList = (List<Tc01_property>) dao.search(sql
				.toString());
		modelMap.put("jdxzList", jdxzList);
		Integer test = jdxzList.size();

		// 获得机房信息列表
		sql.delete(0, sql.length());
		sql.append(" from Td00_jfxx");
		sql.append(" where");

		sql.append(" (jfmc like '%");
		sql.append(keywords);
		sql.append("%'");

		sql.append(" or jdmc like '%");
		sql.append(keywords);
		sql.append("%' )");

		if (!"".equals(ssqy)) {
			sql.append(" and ssqy = '");
			sql.append(ssqy);
			sql.append("'");
		}
		if (!"".equals(jdxz)) {
			sql.append(" and jdxz = '");
			sql.append(jdxz);
			sql.append("'");
		}

		sql.append(" order by ");
		sql.append(orderField);
		sql.append(" ");
		sql.append(orderDirection);

		ResultObject ro = queryService.searchByPage(sql.toString(), pageNum,
				numPerPage);
		totalCount = ro.getTotalRows();
		pageNumShown = ro.getTotalPages();

		modelMap.put("totalCount", totalCount);
		modelMap.put("pageNumShown", pageNumShown);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);

		List<Object> jfpmList = new LinkedList<Object>();
		while (ro.next()) {
			jfpmList.add(ro.get("Td00_jfxx"));
		}

		modelMap.put("jfpmList", jfpmList);

		return new ModelAndView("/WEB-INF/jsp/info/jfpmList.jsp", modelMap);
	}

	/**
	 * 机房图纸备份列表
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/info/jfpmDrawingBakList.do")
	public ModelAndView jfpmDrawingBakList(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		// 数据库相关变量
		StringBuffer sql = new StringBuffer("");
		ModelMap modelMap = new ModelMap();

		// 查询变量
		Long jf_id = convertUtil.toLong(request.getParameter("doc_id"), -1l);

		sql.delete(0, sql.length());
		sql.append(" from Te01_slave ");
		sql.append("where slave_type = '机房现状图备份' ");
		sql.append("and doc_id = ");
		sql.append(jf_id);
		sql.append(" order by id desc ");
		List<Te01_slave> jfpmDrawingBakList = (List<Te01_slave>) dao.search(sql.toString());
		modelMap.put("jfpmDrawingBakList", jfpmDrawingBakList);

		return new ModelAndView("/WEB-INF/jsp/info/jfpmDrawingBakList.jsp", modelMap);
	}
	
	/**
	 * 通过ID获取机房平面信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/info/jfpm.do")
	public ModelAndView jfpm(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = convertUtil.toLong(request.getParameter("id"), -1L);
		ModelMap modelMap = new ModelMap();
		StringBuffer hsql = new StringBuffer();

		// 获取机房平面对象
		Td00_jfxx jfpm = null;
		jfpm = (Td00_jfxx) dao.getObject(Td00_jfxx.class, id);
		modelMap.put("Td00_jfxx", jfpm);

		// 获取字段有效性
		StringBuffer validate = new StringBuffer();
		validate.append("{Td00_jfxx:{");

		hsql.delete(0, hsql.length());
		hsql.append(" from Ta07_formfield where module_id=200");
		List<Ta07_formfield> fieldList = (List<Ta07_formfield>) dao.search(hsql
				.toString());
		for (Ta07_formfield field : fieldList) {
			validate.append(field.getName().toUpperCase() + ":{");
			validate.append("comments:'" + field.getComments() + "',");
			validate.append("datatype:'" + field.getDatatype() + "',");
			validate.append("datalength:'" + field.getDatalength() + "',");
			validate.append("nullable:'" + field.getNullable() + "'");
			validate.append("},");
		}
		validate.deleteCharAt(validate.length() - 1);
		validate.append("}}");
		modelMap.put("validate", validate.toString());

		// 获取机房设备明细
		hsql.delete(0, hsql.length());
		hsql.append(" from Td01_sbxx ");
		hsql.append(" where jfxx_id=");
		hsql.append(id);
		hsql.append(" order by id ");
		List<Td01_sbxx> sbxxList = (List<Td01_sbxx>) dao
				.search(hsql.toString());
		modelMap.put("sbxxList", sbxxList);

		// 获取机房图纸
		Te01_slave jfght = null;
		Te01_slave jfxzt = null;
		hsql.delete(0, hsql.length());
		hsql = hsql.append("from Te01_slave where module_id=200 and doc_id = "
				+ id);
		List<Te01_slave> slaveList = (List<Te01_slave>) dao.search(hsql
				.toString());
		for (Te01_slave slave : slaveList) {
			if ("机房规划图".equals(slave.getSlave_type()))
				jfght = slave;

			if ("机房现状图".equals(slave.getSlave_type()))
				jfxzt = slave;
		}
		modelMap.put("jfght", jfght);
		modelMap.put("jfxzt", jfxzt);

		return new ModelAndView("/WEB-INF/jsp/info/jfpm.jsp", modelMap);
	}

	/**
	 * 机房信息删除ajax实现
	 * 
	 * @param reqeust
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/info/ajaxJfpmDel.do")
	public void ajaxJfpmDel(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding(request.getCharacterEncoding());
		PrintWriter out = null;
		response.setContentType("text/xml");

		Long id = convertUtil.toLong(request.getParameter("id"), -1L);

		try {
			out = response.getWriter();
			dao.removeObject(Td00_jfxx.class, id);

			/**
			 * 删除设备明细
			 */
			dao.update("delete from Td01_sbxx where jfxx_id=" + id);

			/**
			 * 删除附件
			 */
			OperFile of = new OperFile();
			of.setQueryService(queryService);
			of.setSaveService(saveService);

			String sql = "select id from Te01_slave where module_id=200 and doc_id = "
					+ id;
			ResultObject ro = queryService.search(sql);
			while (ro.next()) {
				request.setAttribute("slave_id", ro.get("id"));
				of.delfile(request, response);
			}

			out
					.print("{\"statusCode\":\"200\", \"message\":\"机房平面信息删除成功\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");
		} catch (Exception e) {
			out
					.print("{\"statusCode\":\"300\", \"message\":\"机房平面信息删除失败\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");
			exceptionService.exceptionControl(
					"com.crht.controller.business.Repository", "机房平面信息删除异常", e);
		}
	}

	/**
	 * 通过ID获取设备信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/info/sbxx.do")
	public ModelAndView sbxx(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = convertUtil.toLong(request.getParameter("id"), -1L);
		ModelMap modelMap = new ModelMap();
		StringBuffer hsql = new StringBuffer();

		// 获取专业
		hsql.delete(0, hsql.length());
		hsql.append(" from Tc01_property ");
		hsql.append(" where type='所属专业'");
		hsql.append(" order by id ");
		List<Tc01_property> zyList = (List<Tc01_property>) dao.search(hsql
				.toString());
		modelMap.put("zyList", zyList);

		// 获取建设性质
		hsql.delete(0, hsql.length());
		hsql.append(" from Tc01_property ");
		hsql.append(" where type='建设性质'");
		hsql.append(" order by id ");
		List<Tc01_property> jsxzList = (List<Tc01_property>) dao.search(hsql
				.toString());
		modelMap.put("jsxzList", jsxzList);

		// 获取供电方式
		hsql.delete(0, hsql.length());
		hsql.append(" from Tc01_property ");
		hsql.append(" where type='供电方式'");
		hsql.append(" order by id ");
		List<Tc01_property> gdfsList = (List<Tc01_property>) dao.search(hsql
				.toString());
		modelMap.put("gdfsList", gdfsList);

		// 获取设备对象
		Td01_sbxx sbxx = null;
		sbxx = (Td01_sbxx) dao.getObject(Td01_sbxx.class, id);
		modelMap.put("td01", sbxx);
		return new ModelAndView("/WEB-INF/jsp/info/sbxx.jsp", modelMap);
	}

	/**
	 * 设备信息删除ajax实现
	 * 
	 * @param reqeust
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/info/ajaxSbxxDel.do")
	public void ajaxSbxxDel(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding(request.getCharacterEncoding());
		PrintWriter out = null;
		response.setContentType("text/xml");

		Long id = convertUtil.toLong(request.getParameter("id"), -1L);

		// 获取用户对象
		try {
			out = response.getWriter();
			dao.removeObject(Td01_sbxx.class, id);

			out
					.print("{\"statusCode\":\"200\", \"message\":\"设备信息删除成功\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");
		} catch (Exception e) {
			exceptionService.exceptionControl(
					"com.crht.controller.business.Repository", "设备信息删除失败", e);
		}
	}

	@RequestMapping("/info/sbxxtoexcel.do")
	public void sbxxtoexcel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long jfxx_id = convertUtil.toLong(request.getParameter("jfxx_id"),-1L);
		StringBuffer hsql = new StringBuffer("");
		hsql.append(" from Td01_sbxx ");
		hsql.append(" where jfxx_id=");
		hsql.append(jfxx_id);
		hsql.append(" order by id ");
		List<Td01_sbxx> sbxxList = (List<Td01_sbxx>) dao
				.search(hsql.toString());
		Td00_jfxx td00_jfxx = (Td00_jfxx) queryService.searchById(
				Td00_jfxx.class, jfxx_id);
		String excelversion = request.getParameter("excelversion");
		ExcelWrite excelWrite = null;
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=UTF-8;");
		if ("97-03".equals(excelversion)) {
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(("设备信息表" + new SimpleDateFormat(
							"yyyy年MM月dd日 HH时mm分").format(new Date())+".xls")
							.getBytes("GBK"), "iso8859-1"));
			excelWrite = new ExcelWrite(new HSSFWorkbook());
		} else if ("07".equals(excelversion)) {
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(("设备信息表" + new SimpleDateFormat(
							"yyyy年MM月dd日 HH时mm分").format(new Date())+".xlsx")
							.getBytes("GBK"), "iso8859-1"));
			excelWrite = new ExcelWrite(new XSSFWorkbook());
		} else
			throw new Exception("对不起 还没有这个版本的EXCEL");
		excelWrite.setTitle(td00_jfxx.getJfmc()
				+ "(ID:"
				+ td00_jfxx.getId()
				+ ")设备列表 "
				+ new SimpleDateFormat("yyyy年MM月dd日 HH时mm分 制")
						.format(new Date()));
		List<String> titleList = new ArrayList<String>();
		titleList.add("设备名称");
		titleList.add("设备型号");
		titleList.add("机架尺寸");
		titleList.add("厂家");
		titleList.add("供电方式");
		titleList.add("所属专业");
		titleList.add("安装位置");
		titleList.add("建设性质");
		titleList.add("安装日期");
		titleList.add("施工单位");
		titleList.add("负责人");
		titleList.add("联系电话");
		excelWrite.setTitleList(titleList);
		List<List<Object>> rowList = new ArrayList<List<Object>>();
		for (Td01_sbxx td01_sbxx : sbxxList) {
			List<Object> tmpList = new ArrayList<Object>();
			tmpList.add(td01_sbxx.getSbmc() == null ? "" : td01_sbxx.getSbmc());
			tmpList.add(td01_sbxx.getSbxh() == null ? "" : td01_sbxx.getSbxh());
			tmpList.add(td01_sbxx.getJjcc() == null ? "" : td01_sbxx.getJjcc());
			tmpList.add(td01_sbxx.getCj() == null ? "" : td01_sbxx.getCj());
			tmpList.add(td01_sbxx.getGdfs() == null ? "" : td01_sbxx.getGdfs());
			tmpList.add(td01_sbxx.getSszy() == null ? "" : td01_sbxx.getSszy());
			tmpList.add(td01_sbxx.getAzwz() == null ? "" : td01_sbxx.getAzwz());
			tmpList.add(td01_sbxx.getJsxz() == null ? "" : td01_sbxx.getJsxz());
			tmpList.add(td01_sbxx.getAzrq() == null ? ""
					: new SimpleDateFormat("yyyy-MM-dd HH:mm").format(td01_sbxx
							.getAzrq()));
			tmpList.add(td01_sbxx.getSgdw() == null ? "" : td01_sbxx.getSgdw());
			tmpList.add(td01_sbxx.getFzr() == null ? "" : td01_sbxx.getFzr());
			tmpList.add(td01_sbxx.getLxdh() == null ? "" : td01_sbxx.getLxdh());
			rowList.add(tmpList);
		}
		excelWrite.setRowList(rowList);
		Workbook workbook = excelWrite.write();
		try {
			workbook.write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/info/sbxxtoexceljsp.do")
	public String sbxxtoexceljsp() {
		return "/WEB-INF/jsp/info/sbxxtoexcel.jsp";
	}

	@RequestMapping("/info/exceltosbxxjsp.do")
	public String exceltosbxxjsp() {
		return "/WEB-INF/jsp/info/exceltosbxx.jsp";
	}

	@RequestMapping("/info/exceltosbxx.do")
	public void exceltosbxx(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("GBK");
		String json = "{\"statusCode\":\"200\", \"message\":\"导入成功\", \"navTabId\":\""
				+ "jfpm"
				+ "\", \"forwardUrl\":\""
				+ ""
				+ "\", \"callbackType\":\"" + "" + "\"}";
		try {
			MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
			Iterator<?> it = mrequest.getFileNames();
			while (it.hasNext()) {
				String fileDispath = (String) it.next();
				MultipartFile file = mrequest.getFile(fileDispath);
				if (file.getName() != null && !file.getName().equals("")
						&& file.getInputStream().available() > 0) {
					List<List<String>> rowlist = (List<List<String>>) ExcelRead
							.readEcelFilebyStream(file.getInputStream(), file
									.getOriginalFilename(), 0, 1);
					// 遍历全表之前 需要的标记 定义在此
					Long jfxx_id = Long.parseLong(request
							.getParameter("jfxx_id"));
					List<Td01_sbxx> td01_list = (List<Td01_sbxx>) queryService
							.searchList("from Td01_sbxx where jfxx_id='"
									+ jfxx_id + "'");
					List<String> titleList = null;

					// 遍历全表
					Session session = saveService.getHiberbateSession();
					Transaction transaction = session.beginTransaction();
					transaction.begin();
					try {
						for (int j = 0; j < rowlist.size(); j++) {
							// 遍历单行之前需要的标记定义于此
							boolean rowendflag = false;
							List<String> tmpList = rowlist.get(j);// 获得行LIST
							Td01_sbxx td01_sbxx = null;
							if (titleList != null) {
								td01_sbxx = new Td01_sbxx();
								td01_sbxx.setJfxx_id(jfxx_id);
							}
							int number = 0;
							boolean notnullflag = false;
							// 遍历行
							for (String string : tmpList) {
								// 首先判断是否包括TITLE TITLE内是否包含NAME 及 ID 等信息
								// if (string.indexOf("设备列表") != -1) {
								// titleList = null;
								// rowendflag = true;
								// String td00_jfmc = "";
								// String td00_id = "";
								// if (string.indexOf("(") != -1) {
								// td00_jfmc = string.substring(0, string
								// .indexOf("("));
								// }
								// if (string.toLowerCase().indexOf("id:") != -1
								// && string.indexOf(")") != -1) {
								// td00_id =
								// string.substring(string.toLowerCase()
								// .indexOf("id:") + 3, string
								// .indexOf(")"));
								// }
								// // System.out.println(td00_jfmc + td00_id);
								// if (td00_id == null) {
								// List tmplist = queryService
								// .searchList("from Td00_jfxx where jfmc='"
								// + td00_jfmc + "'");
								// if (tmplist.size() != 0) {
								// jfxx_id = ((Td00_jfxx) tmplist.get(0))
								// .getId();
								// } else {
								// jfxx_id = Long.parseLong(request
								// .getParameter("jfxx_id"));
								// }
								// } else {
								// jfxx_id = Long.parseLong(td00_id);
								// }
								// td01_list = (List<Td01_sbxx>) queryService
								// .searchList("from Td01_sbxx where jfxx_id='"
								// + jfxx_id + "'");
								// }
								if (rowendflag == true) {
									continue;
								}
								// 判断是否有列头
								else if (string.trim().equals("设备名称")
										|| string.trim().equals("序号")
										|| string.trim().equals("设备型号")
										|| string.trim().equals("机架尺寸")
										|| string.trim().equals("厂家")
										|| string.trim().equals("供电方式")
										|| string.trim().equals("所属专业")
										|| string.trim().equals("安装位置")
										|| string.trim().equals("建设性质")
										|| string.trim().equals("施工单位")
										|| string.trim().equals("负责人")
										|| string.trim().equals("联系电话")
										|| string.trim().equals("安装日期")) {
									titleList = tmpList;
									rowendflag = true;
								}
								// titleList是否为NULL 决定了下边是否是主数据
								else if (titleList != null) {
									if (titleList.get(0).trim().equals("序号")
											&& number == 0) {
										number++;
										continue;
									} else {
										String tmpstring = titleList
												.get(number);
										string = string.trim();
										if (tmpstring.equals("设备型号")) {
											td01_sbxx.setSbxh(string);
										} else if (tmpstring.equals("设备名称")) {
											td01_sbxx.setSbmc(string);
											notnullflag = true;
										} else if (tmpstring.equals("机架尺寸")) {
											td01_sbxx.setJjcc(string);
											notnullflag = true;
										} else if (tmpstring.equals("厂家")) {
											td01_sbxx.setCj(string);
											notnullflag = true;
										} else if (tmpstring.equals("供电方式")) {
											td01_sbxx.setGdfs(string);
											notnullflag = true;
										} else if (tmpstring.equals("所属专业")) {
											td01_sbxx.setSszy(string);
											notnullflag = true;
										} else if (tmpstring.equals("安装位置")) {
											td01_sbxx.setAzwz(string);
											notnullflag = true;
										} else if (tmpstring.equals("建设性质")) {
											td01_sbxx.setJsxz(string);
											notnullflag = true;
										} else if (tmpstring.equals("施工单位")) {
											td01_sbxx.setSgdw(string);
											notnullflag = true;
										} else if (tmpstring.equals("负责人")) {
											td01_sbxx.setFzr(string);
											notnullflag = true;
										} else if (tmpstring.equals("联系电话")) {
											td01_sbxx.setLxdh(string);
											notnullflag = true;
										} else if (tmpstring.equals("安装日期")) {
											Date tmpdate = null;
											try {
												tmpdate = new SimpleDateFormat(
														"yyyy-MM-dd HH:mm")
														.parse(string);
											} catch (ParseException e) {
												try {
													tmpdate = new SimpleDateFormat(
															"yyyy-MM-dd")
															.parse(string);
												} catch (ParseException e1) {
												}
											}
											td01_sbxx.setAzrq(tmpdate);
											notnullflag = true;
										}
										if (number == tmpList.size() - 1) {
											if (notnullflag
													&& td01_list != null) {
												boolean repeatflag = false;
												for (Td01_sbxx td01 : td01_list) {
													if (td01
															.getSbxh()
															.equals(
																	td01_sbxx
																			.getSbxh())
															&& td01
																	.getAzwz()
																	.equals(
																			td01_sbxx
																					.getAzwz())) {
														repeatflag = true;
														json = "{\"statusCode\":\"300\", \"message\":\"导入失败!原因:(设备型号'"
																+ td01
																		.getSbxh()
																+ "',安装位置'"
																+ td01
																		.getAzwz()
																+ "')重复,请核对后重新导入\", \"navTabId\":\""
																+ "jfpm"
																+ "\", \"forwardUrl\":\""
																+ ""
																+ "\", \"callbackType\":\""
																+ "" + "\"}";
														throw new Exception();
													}
												}
												if (!repeatflag) {
													session.save(td01_sbxx);
												}
											}
											rowendflag = true;
										}
										number++;
									}
								}
							}
						}
						session.flush();
						transaction.commit();
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						transaction.rollback();
					} finally {
						session.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			json = "{\"statusCode\":\"300\", \"message\":\"导入失败\", \"navTabId\":\""
					+ "jfpm"
					+ "\", \"forwardUrl\":\""
					+ ""
					+ "\", \"callbackType\":\"" + "" + "\"}";
		}
		response.getWriter().print(json);
	}

	@RequestMapping("/info/excelmodel.do")
	public void excelmodel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String jfxx_id = request.getParameter("jfxx_id");
		Td00_jfxx td00_jfxx = (Td00_jfxx) queryService.searchById(
				Td00_jfxx.class, convertUtil.toLong(Long.parseLong(jfxx_id),
						-1L));
		String excelversion = request.getParameter("excelversion");
		ExcelWrite excelWrite = null;
		response.setContentType("application/vnd.ms-excel;charset=UTF-8;");
		if ("97-03".equals(excelversion)) {
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(("设备信息表" + new SimpleDateFormat(
							"yyyy年MM月dd日 HH时mm分").format(new Date())+".xls")
							.getBytes("GBK"), "iso8859-1"));
			excelWrite = new ExcelWrite(new HSSFWorkbook());
		} else if ("07".equals(excelversion)) {
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(("设备信息表" + new SimpleDateFormat(
							"yyyy年MM月dd日 HH时mm分").format(new Date())+".xlsx")
							.getBytes("GBK"), "iso8859-1"));
			excelWrite = new ExcelWrite(new XSSFWorkbook());
		} else
			throw new Exception("对不起 还没有这个版本的EXCEL");
		excelWrite.setTitle(td00_jfxx.getJfmc()
				+ "(ID:"
				+ td00_jfxx.getId()
				+ ")设备列表 "
				+ new SimpleDateFormat("yyyy年MM月dd日 HH时mm分 制")
						.format(new Date()));
		List<String> titleList = new ArrayList<String>();
		titleList.add("设备名称");
		titleList.add("设备型号");
		titleList.add("机架尺寸");
		titleList.add("厂家");
		titleList.add("供电方式");
		titleList.add("所属专业");
		titleList.add("安装位置");
		titleList.add("建设性质");
		titleList.add("安装日期");
		titleList.add("施工单位");
		titleList.add("负责人");
		titleList.add("联系电话");
		excelWrite.setTitleList(titleList);
		List<List<Object>> rowList = new ArrayList<List<Object>>();
		excelWrite.setRowList(rowList);
		Workbook workbook = excelWrite.write();
//		response.reset();
		try {
			workbook.write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/info/jfxxtoexceljsp.do")
	public String jfxxtoexceljsp() {
		return "/WEB-INF/jsp/info/jfxxtoexcel.jsp";
	}

	@RequestMapping("/info/exceltojfxxjsp.do")
	public String exceltojfxxjsp() {
		return "/WEB-INF/jsp/info/exceltojfxx.jsp";
	}

	@RequestMapping("/info/sqdExcelJfxxJsp.do")
	public String sqdExcelJfxxJsp(HttpServletRequest request) {
		return "/WEB-INF/jsp/info/sqdExcelJfxx.jsp";
	}
	
	@RequestMapping("/info/exceltojfxx.do")
	public void exceltojfxx(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("GBK");
		String json = "{\"statusCode\":\"200\", \"message\":\"导入成功\", \"navTabId\":\""
				+ "jfpmList"
				+ "\", \"forwardUrl\":\""
				+ ""
				+ "\", \"callbackType\":\"" + "" + "\"}";
		try {
			MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
			Iterator<?> it = mrequest.getFileNames();
			while (it.hasNext()) {
				String fileDispath = (String) it.next();
				MultipartFile file = mrequest.getFile(fileDispath);
				if (file.getName() != null && !file.getName().equals("")
						&& file.getInputStream().available() > 0) {
					List<List<String>> rowlist = (List<List<String>>) ExcelRead
							.readEcelFilebyStream(file.getInputStream(), file
									.getOriginalFilename(), 0, 1);
					// 遍历全表之前 需要的标记 定义在此
					List<Td00_jfxx> td00_list = (List<Td00_jfxx>) queryService
							.searchList("from Td00_jfxx");
					List<String> titleList = null;
					// -----------
					// 遍历全表
					Session session = saveService.getHiberbateSession();
					Transaction transaction = session.beginTransaction();
					transaction.begin();
					try {
						for (int j = 0; j < rowlist.size(); j++) {
							// 遍历单行之前需要的标记定义于此
							boolean rowendflag = false;
							List<String> tmpList = rowlist.get(j);// 获得行LIST
							Td00_jfxx td00_jfxx = null;
							if (titleList != null) {
								td00_jfxx = new Td00_jfxx();
							}
							int number = 0;
							boolean notnullflag = false;
							// 遍历行
							for (String string : tmpList) {
								// 首先判断是否包括TITLE TITLE内是否包含NAME 及 ID 等信息
								// if (string.indexOf("设备列表") != -1) {
								// titleList = null;
								// rowendflag = true;
								// String td00_jfmc = "";
								// String td00_id = "";
								// if (string.indexOf("(") != -1) {
								// td00_jfmc = string.substring(0, string
								// .indexOf("("));
								// }
								// if (string.toLowerCase().indexOf("id:") != -1
								// && string.indexOf(")") != -1) {
								// td00_id =
								// string.substring(string.toLowerCase()
								// .indexOf("id:") + 3, string
								// .indexOf(")"));
								// }
								// // System.out.println(td00_jfmc + td00_id);
								// if (td00_id == null) {
								// List tmplist = queryService
								// .searchList("from Td00_jfxx where jfmc='"
								// + td00_jfmc + "'");
								// if (tmplist.size() != 0) {
								// jfxx_id = ((Td00_jfxx) tmplist.get(0))
								// .getId();
								// } else {
								// jfxx_id = Long.parseLong(request
								// .getParameter("jfxx_id"));
								// }
								// } else {
								// jfxx_id = Long.parseLong(td00_id);
								// }
								// td01_list = (List<Td01_sbxx>) queryService
								// .searchList("from Td01_sbxx where jfxx_id='"
								// + jfxx_id + "'");
								// }
								if (rowendflag == true) {
									continue;
								}
								// 判断是否有列头
								else if (string.trim().equals("机房名称")
										|| string.trim().equals("登记部门")
										|| string.trim().equals("登记日期")
										|| string.trim().equals("登记人员")
										|| string.trim().equals("联系电话")
										|| string.trim().equals("局点名称")
										|| string.trim().equals("局点性质")
										|| string.trim().equals("所属区域")
										|| string.trim().equals("坐落地点")
										|| string.trim().equals("经纬度")
										|| string.trim().equals("建设日期")
										|| string.trim().equals("机房面积")
										|| string.trim().equals("机房用途")
										|| string.trim().equals("管理人员")
										|| string.trim().equals("管理人员电话")
										|| string.trim().equals("备注")
										|| string.trim().equals("机房所在楼层")) {
									titleList = tmpList;
									rowendflag = true;
								}
								// titleList是否为NULL 决定了下边是否是主数据
								else if (titleList != null) {
									if (titleList.get(0).trim().equals("序号")
											&& number == 0) {
										number++;
										continue;
									} else {
										String tmpstring = titleList
												.get(number);
										string = string.trim();
										if (tmpstring.equals("机房名称")) {
											td00_jfxx.setJfmc(string);
										} else if (tmpstring.equals("登记部门")) {
											td00_jfxx.setDjbm(string);
											notnullflag = true;
										} else if (tmpstring.equals("登记日期")) {
											Date tmpdate = null;
											try {
												tmpdate = new SimpleDateFormat(
														"yyyy-MM-dd HH:mm")
														.parse(string);
											} catch (ParseException e) {
												try {
													tmpdate = new SimpleDateFormat(
															"yyyy-MM-dd")
															.parse(string);
												} catch (ParseException e1) {
												}
											}
											td00_jfxx.setDjrq(tmpdate);
											notnullflag = true;
										} else if (tmpstring.equals("登记人员")) {
											td00_jfxx.setDjry(string);
											notnullflag = true;
										} else if (tmpstring.equals("联系电话")) {
											td00_jfxx.setLxdh(string);
											notnullflag = true;
										} else if (tmpstring.equals("局点名称")) {
											td00_jfxx.setJdmc(string);
											notnullflag = true;
										} else if (tmpstring.equals("局点性质")) {
											td00_jfxx.setJdxz(string);
											notnullflag = true;
										} else if (tmpstring.equals("所属区域")) {
											td00_jfxx.setSsqy(string);
											notnullflag = true;
										} else if (tmpstring.equals("坐落地点")) {
											td00_jfxx.setZldd(string);
											notnullflag = true;
										} else if (tmpstring.equals("经纬度")) {
											td00_jfxx.setJwd(string);
											notnullflag = true;
										} else if (tmpstring.equals("建设日期")) {
											Date tmpdate = null;
											try {
												tmpdate = new SimpleDateFormat(
														"yyyy-MM-dd HH:mm")
														.parse(string);
											} catch (ParseException e) {
												try {
													tmpdate = new SimpleDateFormat(
															"yyyy-MM-dd")
															.parse(string);
												} catch (ParseException e1) {
												}
											}
											td00_jfxx.setJsrq(tmpdate);
											notnullflag = true;
										} else if (tmpstring.equals("机房面积")) {
											td00_jfxx.setJfmj(string);
											notnullflag = true;
										} else if (tmpstring.equals("机房所在楼层")) {
											td00_jfxx.setJfszlc(string);
											notnullflag = true;
										} else if (tmpstring.equals("机房用途")) {
											td00_jfxx.setJfyt(string);
											notnullflag = true;
										} else if (tmpstring.equals("管理人员")) {
											td00_jfxx.setGlry(string);
											notnullflag = true;
										} else if (tmpstring.equals("管理人员电话")) {
											td00_jfxx.setGlylxdh(string);
											notnullflag = true;
										} else if (tmpstring.equals("备注")) {
											td00_jfxx.setBz(string);
											notnullflag = true;
										}
										if (number == tmpList.size() - 1) {
											if (notnullflag
													&& td00_list != null) {
												boolean repeatflag = false;
												for (Td00_jfxx td00 : td00_list) {
													if (td00
															.getJfmc()
															.equals(
																	td00_jfxx
																			.getJfmc())
															&& td00
																	.getJdmc()
																	.equals(
																			td00_jfxx
																					.getJdmc())) {
														repeatflag = true;
														json = "{\"statusCode\":\"300\", \"message\":\"导入失败!原因:(机房名称'"
																+ td00
																		.getJfmc()
																+ "',局点名称'"
																+ td00
																		.getJdmc()
																+ "')重复,请核对后重新导入\", \"navTabId\":\""
																+ "jfpmList"
																+ "\", \"forwardUrl\":\""
																+ ""
																+ "\", \"callbackType\":\""
																+ "" + "\"}";
														throw new Exception();
													}
												}
												if (!repeatflag) {
													session.save(td00_jfxx);
												}
											}
											rowendflag = true;
										}
										number++;
									}
								}
							}
						}
						session.flush();
						transaction.commit();
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						transaction.rollback();
					} finally {
						session.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			json = "{\"statusCode\":\"300\", \"message\":\"导入失败\", \"navTabId\":\""
					+ "jfpm"
					+ "\", \"forwardUrl\":\""
					+ ""
					+ "\", \"callbackType\":\"" + "" + "\"}";
		}
		response.getWriter().print(json);
	}

	/**
	 * 申请单中导入机房明细信息
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/info/sqdExcelJfxx.do")
	public void sqdExcelJfxx(HttpServletRequest arg0,
			HttpServletResponse response) throws Exception {
		
		response.setCharacterEncoding("GBK");
		Long project_id = -1l;
		Long module_id = -1l;
		Integer v_jdmc = -1;
		Integer v_jfmc = -1;
		Integer v_xzjjsl = -1;
		Integer v_jjcc = -1;
		Integer v_sbgdfs = -1;
		Integer v_bz = -1;
	
		Session session = null;
		Transaction tx = null;
		StringBuffer sql = new StringBuffer("");

		try {
			session = saveService.getHiberbateSession();
			tx = session.beginTransaction();
			tx.begin();

			MultipartHttpServletRequest request = (MultipartHttpServletRequest) arg0;
			project_id = convertUtil.toLong(request.getParameter("project_id"));
			module_id = convertUtil.toLong(request.getParameter("module_id"));
			Iterator<?> it = request.getFileNames();
			while (it.hasNext()) {
				String fileDispath = (String) it.next();
				MultipartFile file = request.getFile(fileDispath);
				if (file.getName() != null && !file.getName().equals("")
						&& file.getInputStream().available() > 0) {
					jxl.Workbook wb = jxl.Workbook.getWorkbook(file.getInputStream());
					Sheet st = wb.getSheet(0);
					
					int n_columns = st.getColumns();
					for(int i = 0;i < n_columns;i++){
						String t_value = convertUtil.toString(st.getCell(i, 0).getContents());
						if(t_value.equals("局点名称")){
							v_jdmc = new Integer(i);
						}
						else if(t_value.equals("机房名称")){
							v_jfmc = new Integer(i);
						}
						else if(t_value.indexOf("机架数量") != -1){
							v_xzjjsl = new Integer(i);
						}
						else if(t_value.indexOf("机架尺寸") != -1){
							v_jjcc = new Integer(i);
						}
						else if(t_value.indexOf("供电方式") != -1){
							v_sbgdfs = new Integer(i);
						}
						else if(t_value.indexOf("备") != -1 && t_value.indexOf("注") != -1){
							v_bz = new Integer(i);
						}
					}
					
					if(v_jdmc == -1)
						throw new Exception("标题中缺少【局点名称】列");
					if(v_jfmc == -1)
						throw new Exception("标题中缺少【机房名称】列");
					if(v_xzjjsl == -1)
						throw new Exception("标题中缺少【新增机架数量】列");
					if(v_jjcc == -1)
						throw new Exception("标题中缺少【机架尺寸】列");
					if(v_sbgdfs == -1)
						throw new Exception("标题中缺少【供电方式】列");
					if(v_bz == -1)
						throw new Exception("标题中缺少【备注】列");
					
					Map<String, Integer> columnIndex = new HashMap<String, Integer>();
					columnIndex.put("jdmc".toUpperCase(), v_jdmc);
					columnIndex.put("jfmc".toUpperCase(), v_jfmc);
					columnIndex.put("xzjjsl".toUpperCase(), v_xzjjsl);
					columnIndex.put("jjcc".toUpperCase(), v_jjcc);
					columnIndex.put("sbgdfs".toUpperCase(), v_sbgdfs);
					columnIndex.put("bz".toUpperCase(), v_bz);
					
					int row = 1;
					while (row < st.getRows()
							&& !"".equalsIgnoreCase(st.getCell(0, row)
									.getContents().trim())
							&& st.getCell(0, row).getContents() != null) {
						Td12_gljf td12 = new Td12_gljf();
						PropertyInject.injectFromExcel(td12, columnIndex, st,row);
						/*
						 * 判断机房在系统中是否存在,如果存在获取JFID；
						 */
						String t_jfmc = convertUtil.toString(td12.getJfmc());
						sql.delete(0, sql.length());
						sql.append("select id from Td00_jfxx where jfmc = '");
						
						sql.append("'");
						td12.setParent_id(project_id);
						td12.setProject_id(project_id);
						session.save(td12);
						row++;
					}
				}
			}

			session.flush();
			tx.commit();
			response
					.getWriter()
					.print(
							"{\"statusCode\":\"200\",\"message\":\"导入成功\",\"navTabId\":\"autoform"+module_id+project_id+"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");
			
		} catch (Exception e) {
			tx.rollback();

			String msg = "";
			String e_msg = e.getMessage();
			if (e_msg != null && e_msg.indexOf("recognize OLE stream") != -1) {
				msg = "导入失败，Excel格式非法，请将Excel另存为<font color=red>2003版</font>的<font color=red>标准</font>的Excel后再导入";
			}  else {
				msg = "导入失败," + e_msg;
			}
			response
					.getWriter()
					.print(
							"{\"statusCode\":\"300\",\"message\":\""
									+ msg
									+ "\",\"navTabId\":\"remitInfoList\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");
		} finally {
			session.close();
		}
	}
	
	@RequestMapping("/info/sqd_jf_excelmodel.do")
	public void sqd_jf_excelmodel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String excelversion = request.getParameter("excelversion");
		ExcelWrite excelWrite = null;
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=UTF-8;");
		
		response.setHeader("Content-disposition", "attachment; filename="
				+ new String(("项目关联机房" + new SimpleDateFormat(
						"yyyy年MM月dd日 HH时mm分").format(new Date())+".xls")
						.getBytes("GBK"), "iso8859-1"));
		excelWrite = new ExcelWrite(new HSSFWorkbook());
		
		List<String> titleList = new ArrayList<String>();
		titleList.add("局点名称");
		titleList.add("机房名称");
		titleList.add("新增机架数量");
		titleList.add("机架尺寸");
		titleList.add("设备供电方式");
		titleList.add("备注");
		excelWrite.setTitleList(titleList);
		List<List<Object>> rowList = new ArrayList<List<Object>>();
		excelWrite.setRowList(rowList);
		Workbook workbook = excelWrite.write();
		try {
			workbook.write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/info/jfexcelmodel.do")
	public void jfexcelmodel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String excelversion = request.getParameter("excelversion");
		ExcelWrite excelWrite = null;
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=UTF-8;");
		if ("97-03".equals(excelversion)) {
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(("机房信息表" + new SimpleDateFormat(
							"yyyy年MM月dd日 HH时mm分").format(new Date())+".xls")
							.getBytes("GBK"), "iso8859-1"));
			excelWrite = new ExcelWrite(new HSSFWorkbook());
		} else if ("07".equals(excelversion)) {
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(("机房信息表" + new SimpleDateFormat(
							"yyyy年MM月dd日 HH时mm分").format(new Date())+".xlsx")
							.getBytes("GBK"), "iso8859-1"));
			excelWrite = new ExcelWrite(new XSSFWorkbook());
		} else
			throw new Exception("对不起 还没有这个版本的EXCEL");
		excelWrite.setTitle("机房信息表 "
				+ new SimpleDateFormat("yyyy年MM月dd日 HH时mm分 制")
						.format(new Date()));
		List<String> titleList = new ArrayList<String>();
		titleList.add("机房名称");
		titleList.add("登记部门");
		titleList.add("登记日期");
		titleList.add("登记人员");
		titleList.add("联系电话");
		titleList.add("局点名称");
		titleList.add("局点性质");
		titleList.add("所属区域");
		titleList.add("坐落地点");
		titleList.add("经纬度");
		titleList.add("建设日期");
		titleList.add("机房面积");
		titleList.add("机房所在楼层");
		titleList.add("机房用途");
		titleList.add("管理人员");
		titleList.add("管理人员电话");
		titleList.add("备注");
		excelWrite.setTitleList(titleList);
		List<List<Object>> rowList = new ArrayList<List<Object>>();
		excelWrite.setRowList(rowList);
		Workbook workbook = excelWrite.write();
		try {
			workbook.write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/info/jfxxtoexcel.do")
	public void jfxxtoexcel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StringBuffer hsql = new StringBuffer("");
		String keywords = convertUtil.toString(
				request.getParameter("keywords"), "");
		String ssqy = convertUtil.toString(request.getParameter("ssqy"), "");
		String jdxz = convertUtil.toString(request.getParameter("jdxz"), "");
		hsql.append(" from Td00_jfxx ");
		hsql.append(" where ssqy like '%" + ssqy + "%' ");
		hsql.append(" and jdxz like '%" + jdxz + "%' ");
		hsql.append(" and (jfmc like '%" + keywords + "%' or jdmc like '%"
				+ keywords + "%') ");
		hsql.append(" order by id ");
		List<Td00_jfxx> jfxxList = (List<Td00_jfxx>) dao
				.search(hsql.toString());
		String excelversion = request.getParameter("excelversion");
		ExcelWrite excelWrite = null;
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=UTF-8;");
		if ("97-03".equals(excelversion)) {
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(("机房信息表" + new SimpleDateFormat(
							"yyyy年MM月dd日 HH时mm分").format(new Date())+".xls")
							.getBytes("GBK"), "iso8859-1"));
			excelWrite = new ExcelWrite(new HSSFWorkbook());

		} else if ("07".equals(excelversion)) {
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(("机房信息表.xlsx" + new SimpleDateFormat(
							"yyyy年MM月dd日 HH时mm分").format(new Date())+".xlsx")
							.getBytes("GBK"), "iso8859-1"));
			excelWrite = new ExcelWrite(new XSSFWorkbook());
		} else
			throw new Exception("对不起 还没有这个版本的EXCEL");
		excelWrite.setTitle("机房信息表 "
				+ new SimpleDateFormat("yyyy年MM月dd日 HH时mm分 制")
						.format(new Date()));
		List<String> titleList = new ArrayList<String>();
		titleList.add("机房名称");
		titleList.add("登记部门");
		titleList.add("登记日期");
		titleList.add("登记人员");
		titleList.add("联系电话");
		titleList.add("局点名称");
		titleList.add("局点性质");
		titleList.add("所属区域");
		titleList.add("坐落地点");
		titleList.add("经纬度");
		titleList.add("建设日期");
		titleList.add("机房面积");
		titleList.add("机房所在楼层");
		titleList.add("机房用途");
		titleList.add("管理人员");
		titleList.add("管理人员电话");
		titleList.add("备注");
		excelWrite.setTitleList(titleList);
		List<List<Object>> rowList = new ArrayList<List<Object>>();
		for (Td00_jfxx td00_jfxx : jfxxList) {
			List<Object> tmpList = new ArrayList<Object>();
			tmpList.add(td00_jfxx.getJfmc() == null ? "" : td00_jfxx.getJfmc());
			tmpList.add(td00_jfxx.getDjbm() == null ? "" : td00_jfxx.getDjbm());
			tmpList.add(td00_jfxx.getDjrq() == null ? ""
					: new SimpleDateFormat("yyyy-MM-dd HH:mm").format(td00_jfxx
							.getDjrq()));
			tmpList.add(td00_jfxx.getDjry() == null ? "" : td00_jfxx.getDjry());
			tmpList.add(td00_jfxx.getLxdh() == null ? "" : td00_jfxx.getLxdh());
			tmpList.add(td00_jfxx.getJdmc() == null ? "" : td00_jfxx.getJdmc());
			tmpList.add(td00_jfxx.getJdxz() == null ? "" : td00_jfxx.getJdxz());
			tmpList.add(td00_jfxx.getSsqy() == null ? "" : td00_jfxx.getSsqy());
			tmpList.add(td00_jfxx.getZldd() == null ? "" : td00_jfxx.getZldd());
			tmpList.add(td00_jfxx.getJwd() == null ? "" : td00_jfxx.getJwd());
			tmpList.add(td00_jfxx.getJsrq() == null ? ""
					: new SimpleDateFormat("yyyy-MM-dd HH:mm").format(td00_jfxx
							.getJsrq()));
			tmpList.add(td00_jfxx.getJfmj() == null ? "" : td00_jfxx.getJfmj());
			tmpList.add(td00_jfxx.getJfszlc() == null ? "" : td00_jfxx
					.getJfszlc());
			tmpList.add(td00_jfxx.getJfyt() == null ? "" : td00_jfxx.getJfyt());
			tmpList.add(td00_jfxx.getGlry() == null ? "" : td00_jfxx.getGlry());
			tmpList.add(td00_jfxx.getGlylxdh() == null ? "" : td00_jfxx
					.getGlylxdh());
			tmpList.add(td00_jfxx.getBz() == null ? "" : td00_jfxx.getBz());
			rowList.add(tmpList);
		}
		excelWrite.setRowList(rowList);
		Workbook workbook = excelWrite.write();
		try {
			workbook.write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获得设计人员ajax实现
	 * 
	 * @param reqeust
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/info/ajaxGetDesigner.do")
	public void ajaxGetDesigner(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		response.setCharacterEncoding(request.getCharacterEncoding());
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = null;
		out = response.getWriter();
		
		String sjdw = convertUtil.toString(request.getParameter("sjdw"));
		Long doc_id = convertUtil.toLong(request.getParameter("doc_id"),-1l);
		StringBuffer hsql = new StringBuffer("");
		ResultObject ro = null;

		Ta03_user user = (Ta03_user)request.getSession().getAttribute("user");
		// 获取有效地区
		String t_area = null;
		if(doc_id == -1){
			t_area = user.getArea_name();
		}
		else{
			hsql=new StringBuffer("select ta03.area_name as area_name ");
			hsql.append("from Td11_jfpmsq td11,Ta03_user ta03 ");
			hsql.append("where td11.cjr = ta03.name ");
			hsql.append("and td11.id = ");
			hsql.append(doc_id);
			ro = queryService.search(hsql.toString());
			if(ro.next()){
				t_area = (String)ro.get("area_name");
			}
		}
		if(t_area == null || t_area.equals("")){
			t_area = user.getArea_name();;
		}
		if(sjdw.indexOf("内部设计") != -1){
			hsql.delete(0, hsql.length());
			hsql.append("select ta03.name "); 
			hsql.append("from Ta02_station ta02, Ta11_sta_user ta11,Ta03_user ta03 ");
			hsql.append("where ta02.id = ta11.station_id ");
			hsql.append("and ta11.user_id = ta03.id ");
			hsql.append("and ta02.name like '%设计岗%' ");
			hsql.append("and ta03.area_name like '%");
			hsql.append(t_area);
			hsql.append("%' ");
		}
		else{
			hsql.delete(0, hsql.length());
			hsql.append("select ta03.name ");
			hsql.append("from Ta03_user ta03,Ta01_dept ta01 ");
			hsql.append("where ta01.id = ta03.dept_id ");
			hsql.append("and ta01.name = '");
			hsql.append(sjdw);
			hsql.append("' ");
		}
		List list = null;
		try {
			list = dao.search(hsql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String s = "";
		for (Object o : list) {
			s += "<option value='" + o.toString() + "'>" + o.toString()
					+ "</option>";
		}
		out.print(s);
		out.flush();
		out.close();
	}
	
	/**
	 * 机房信息删除ajax实现
	 * 
	 * @param reqeust
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/ajaxStopFlow.do")
	public void ajaxStopFlow(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding(request.getCharacterEncoding());
		PrintWriter out = null;

		Long project_id = convertUtil.toLong(request.getParameter("project_id"), -1L);

		try {
			out = response.getWriter();
			if(project_id == -1){
				throw new Exception("没找到工程信息");
			}
			dao.update("update Tb15_docflow set doc_status = 8 where doc_status <> 8 and project_id = " + project_id);
			dao.update("update Tb12_opernode set node_status = 8 where node_status <> 8 and project_id = " + project_id);
			dao.update("update Td11_jfpmsq set sfzz = '已中止' where project_id = " + project_id);
			System.out.print("{\"statusCode\":\"200\", \"message\":\"流程已经中止\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");
			out.print("{\"statusCode\":\"200\", \"message\":\"流程已经中止\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");
		} catch (Exception e) {
			out.print("{\"statusCode\":\"300\", \"message\":\"操作失败，请重试！\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");
			exceptionService.exceptionControl(
					"com.jfms.controller.info.Jfpmgl", "流程已经中止异常", e);
		}
	}
	
	/**
	 * 修改设计人员
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/info/goModifySjry.do")
	public ModelAndView goModifySjry(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		// 数据库相关变量
		StringBuffer hsql = new StringBuffer("");
		ModelMap modelMap = new ModelMap();
		ResultObject ro = null;

		// 查询变量
		Long doc_id = convertUtil.toLong(request.getParameter("doc_id"), -1l);
		Ta03_user user = null;
		Long user_id = -1l;
		if(session != null){
			user = (Ta03_user)session.getAttribute("user");
			user_id = user.getId();
		}
		
		/*
		 * 获取当前设计人员
		 */
		Td11_jfpmsq td11 = (Td11_jfpmsq)queryService.searchById(Td11_jfpmsq.class, doc_id);
		String t_sjdw = convertUtil.toString(td11.getSjdw());
		
		/*
		 * 判断是内部设计还是外协设计
		 */
		hsql.delete(0, hsql.length());
		hsql.append("select ta03.id ");
		hsql.append("from Ta03_user ta03,Ta01_dept ta01 "); 
		hsql.append("where ta01.id = ta03.dept_id ");
		hsql.append("and ta01.area_name = '外协' ");
		hsql.append("and ta03.id = ");
		hsql.append(user_id);
		List list = queryService.searchList(hsql.toString());
		
		List sjryList = null;
		List sjdwList = null;
		if(list.size() == 0){
			/*
			 * 内部设计人员列表
			 */
			hsql.delete(0, hsql.length());
			hsql.append("select ta03 "); 
			hsql.append("from Ta02_station ta02, Ta11_sta_user ta11,Ta03_user ta03 ");
			hsql.append("where ta02.id = ta11.station_id ");
			hsql.append("and ta11.user_id = ta03.id ");
			hsql.append("and ta02.name like '%设计岗%' ");
			hsql.append("and ta03.area_name not like '%外协%' ");
			sjryList = queryService.searchList(hsql.toString());
			
			/*
			 * 外协设计单位列表
			 */
			hsql.delete(0, hsql.length());
			hsql.append("select distinct ta01 as ta01 ");
			hsql.append("from Ta01_dept ta01,Ta03_user ta03 "); 
			hsql.append("where ta01.id = ta03.dept_id ");
			hsql.append("and ta01.area_name = '外协' ");
			sjdwList = queryService.searchList(hsql.toString());
			
			/*
			 * 设计人员和外协单位对应关系
			 */
			String sjMatchList = "";
			hsql.delete(0, hsql.length());
			hsql.append("select case when ta01.area_name='外协' then ta01.name else '内部设计' end as sjdw,ta03.name as sjry,ta03.id as id ");
			hsql.append("from Ta02_station ta02, Ta11_sta_user ta11,Ta03_user ta03,Ta01_dept ta01 ");
			hsql.append("where ta02.id = ta11.station_id "); 
			hsql.append("and ta03.dept_id = ta01.id "); 
			hsql.append("and ta11.user_id = ta03.id "); 
			hsql.append("and ta02.name like '%设计岗%' ");
			ro = queryService.search(hsql.toString());
			while(ro.next()){
				sjMatchList += ";"+convertUtil.toString(ro.get("sjdw"))+"::"+convertUtil.toString(ro.get("id")+"::"+convertUtil.toString(ro.get("sjry")));
			}
			sjMatchList = sjMatchList.substring(1,sjMatchList.length());
			
			modelMap.put("sjdwList", sjdwList);
			modelMap.put("sjMatchList", sjMatchList);
			modelMap.put("sjryRole", "nb");
			modelMap.put("default_sjdw", td11.getSjdw());
		}
		else{
			/*
			 * 外协设计人员
			 */
			hsql.delete(0, hsql.length());
			hsql.append("select ta03 ");
			hsql.append("from Ta03_user ta03,Ta01_dept ta01 ");
			hsql.append("where ta01.id = ta03.dept_id ");
			hsql.append("and ta01.name = '");
			hsql.append(t_sjdw);
			hsql.append("' ");
			sjryList = queryService.searchList(hsql.toString());
			modelMap.put("sjryRole", "wx");
		}
	
		modelMap.put("sjryList", sjryList);
		modelMap.put("default_sjry", td11.getSjry());

		return new ModelAndView("/WEB-INF/jsp/info/xgsjry.jsp", modelMap);
	}

	/**
	 * 修改设计人员
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/info/modifySjry.do")
	public void modifySjry(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)throws Exception {

		response.setCharacterEncoding(request.getCharacterEncoding());
		PrintWriter out = null;

		try {
			out = response.getWriter();
			Long doc_id = convertUtil.toLong(request.getParameter("doc_id"), -1L);
			Long opernode_id = convertUtil.toLong(request.getParameter("opernode_id"), -1L);
			Long project_id = convertUtil.toLong(request.getParameter("project_id"), -1L);
			Long module_id = convertUtil.toLong(request.getParameter("module_id"), -1L);
			if(doc_id == -1){
				throw new Exception("没找到工程信息");
			}
			
			Long sjry_id = convertUtil.toLong(request.getParameter("T_SJRY"), -1L);
			if(sjry_id==-1){
				throw new Exception("no sjryid");
			}
			
			Ta03_user ta03 = (Ta03_user)queryService.searchById(Ta03_user.class, sjry_id);
			String sjry = convertUtil.toString(ta03.getName());
			Long dept_id = convertUtil.toLong(ta03.getDept_id());
			Ta01_dept ta01 = (Ta01_dept)queryService.searchById(Ta01_dept.class, dept_id);
			String dept_name = ta01.getName();
			if(!"外协".equals(ta01.getArea_name())){
				dept_name = "内部设计";
			}
			
			dao.update("update Tb15_docflow set user_id = "+sjry_id+" where opernode_id = " + opernode_id);
			dao.update("update Td11_jfpmsq set sjdw = '"+ta01.getName()+"',sjry = '"+sjry+"' where id = " + doc_id);

			out.print("{\"statusCode\":\"200\", \"message\":\"设计人员已经修改\", \"navTabId\":\"autoform"+module_id+project_id+"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");
		} catch (Exception e) {
			out.print("{\"statusCode\":\"300\", \"message\":\"操作失败，请重试！\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");
			exceptionService.exceptionControl(
					"com.jfms.controller.info.Jfpmgl", "设计人员修改异常", e);
		}
	}
}
