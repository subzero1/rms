package com.jfms.controller.sysManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.jfms.controller.base.ExcelRead;
import com.jfms.controller.base.ExcelWrite;
import com.jfms.dataObjects.base.Tc03_area;
import com.jfms.dataObjects.base.Tc04_jcsb;
import com.jfms.dataObjects.info.Td00_jfxx;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta01_dept;
import com.netsky.base.dataObjects.Ta02_station;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Te01_slave;
import com.netsky.base.dataObjects.Te04_message;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.imagecut.FtpService;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.DateGetUtil;
import com.netsky.base.utils.NumberFormatUtil;
import com.netsky.base.utils.PHSService;
import com.netsky.base.utils.StringFormatUtil;

@Controller
public class Jcsb {

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

	@SuppressWarnings("unchecked")
	@RequestMapping("/sysManage/jcsbList.do")
	public ModelAndView jcsblist(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		ResultObject rs = null;

		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 20);
		String orderField = StringFormatUtil.format(request.getParameter("orderField"), "id");
		String orderDirection = StringFormatUtil.format(request.getParameter("orderDirection"), "desc");

		StringBuffer HSql = new StringBuffer();
		int totalPages = 1;
		int totalCount = 1;
		try {
			HSql.append("select id,sbmc,sbxh,cj from Tc04_jcsb ");
			String keyword = request.getParameter("keyword");
			if (keyword != null && !"".equals(keyword)) {
				HSql.append(" where sbmc like '%");
				HSql.append(keyword);
				HSql.append("%' or sbxh like '%");
				HSql.append(keyword);
				HSql.append("%' or cj like '%");
				HSql.append(keyword);
				HSql.append("%' ");
			}
			StringBuffer order = new StringBuffer();
			order.append(" order by ");
			order.append(orderField + " ");
			order.append(orderDirection);
			HSql.append(order.toString());
			// 取列表数据
			if ("yes".equals(request.getParameter("toexcel"))) {
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
				List<String> titleList = new ArrayList<String>();
				titleList.add("设备名称");
				titleList.add("设备型号");
				titleList.add("厂家");
				excelWrite.setTitleList(titleList);
				List<List<Object>> rowList = new ArrayList<List<Object>>();
				if (!"yes".equals(request.getParameter("model"))){
					rs = queryService.search(HSql.toString());
					while (rs.next()){
						List line = new ArrayList<Object>();
						line.add(rs.get("sbmc"));
						line.add(rs.get("sbxh"));
						line.add(rs.get("cj"));
						rowList.add(line);
					}
				}
				excelWrite.setRowList(rowList);
				Workbook workbook = excelWrite.write();
				try {
					workbook.write(response.getOutputStream());
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			} else {
				rs = queryService.searchByPage(HSql.toString(), pageNum, numPerPage);
				totalPages = rs.getTotalPages();
				totalCount = rs.getTotalRows();
				if (pageNum < 1 || pageNum > totalPages) {
					pageNum = 1;
				}
				modelMap.put("numPerPage", numPerPage);
				modelMap.put("totalCount", totalCount);
				modelMap.put("totalPages", totalPages);
				modelMap.put("pageNum", pageNum);
				modelMap.put("orderField", orderField);
				modelMap.put("orderDirection", orderDirection);
				List jcsb_list = new ArrayList();
				while (rs.next()) {
					Map<String, Object> mo = rs.getMap();
					jcsb_list.add(mo);
				}
				request.setAttribute("jcsb_list", jcsb_list);
			}

		} catch (Exception e) {

		}
		return new ModelAndView("/WEB-INF/jsp/sysManage/jcsbList.jsp", modelMap);
	}
	
		@SuppressWarnings("unchecked")
		@RequestMapping("/sysManage/jcsb.do")
		public String jcsb(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			Long id = convertUtil.toLong(request.getParameter("id"),-1L);
			if (id != -1L) {
				Tc04_jcsb tc04_jcsb = (Tc04_jcsb)queryService.searchById(Tc04_jcsb.class, id);
				request.setAttribute("tc04", tc04_jcsb);
			}
			return "/WEB-INF/jsp/sysManage/jcsb.jsp";
		}
		
		@RequestMapping("/sysManage/deljcsb.do")
		public void deljcsb(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			response.setCharacterEncoding(request.getCharacterEncoding());
			PrintWriter out = response.getWriter();
			try {
				Long id = convertUtil.toLong(request.getParameter("id"),-1L);
				if (id != -1L) {
					saveService.removeObject(Tc04_jcsb.class, id);
				}
				out.print("{\"statusCode\":\"200\", \"message\":\"删除成功\", \"navTabId\":\"" + "jcsbList" + "\", \"forwardUrl\":\"" + "" + "\", \"callbackType\":\"" + "" + "\"}");
			}catch(Exception e){
				e.printStackTrace();
				out.print("{\"statusCode\":\"300\", \"message\":\"删除失败\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");
			}
		}
		/**
		 * 基础设备EXCEL导入
		 * */
		@RequestMapping("/sysManage/jcsbimExcel.do")
		public void jcsbimExcel(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			response.setCharacterEncoding("GBK");
			String json = "{\"statusCode\":\"200\", \"message\":\"导入成功\", \"navTabId\":\""
					+ "jcsbList"
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
						List<Tc04_jcsb> tc04_list = (List<Tc04_jcsb>) queryService
								.searchList("from Tc04_jcsb");
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
								Tc04_jcsb tc04_jcsb = null;
								if (titleList != null) {
									tc04_jcsb = new Tc04_jcsb();
								}
								int number = 0;
								boolean notnullflag = false;
								// 遍历行
								for (String string : tmpList) {
									if (rowendflag == true) {
										continue;
									}
									// 判断是否有列头
									else if (string.trim().equals("设备名称")
											|| string.trim().equals("设备型号")
											|| string.trim().equals("厂家")) {
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
											if (tmpstring.equals("设备名称")) {
												tc04_jcsb.setSbmc(string);
											} else if (tmpstring.equals("设备型号")) {
												tc04_jcsb.setSbxh(string);
												notnullflag = true;
											} else if (tmpstring.equals("厂家")) {
												tc04_jcsb.setCj(string);
												notnullflag = true;
											}
											if (number == tmpList.size() - 1) {
												if (notnullflag
														&& tc04_list != null) {
													boolean repeatflag = false;
													for (Tc04_jcsb tc04 : tc04_list) {
														if (tc04
																.getSbmc()
																.equals(
																		tc04_jcsb
																				.getSbmc())
																&& tc04.getCj().equals(tc04_jcsb.getCj())
																&& tc04
																		.getSbxh()
																		.equals(
																				tc04_jcsb
																						.getSbxh())) {
															repeatflag = true;
														}
													}
													if (!repeatflag) {
														session.save(tc04_jcsb);
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
						+ ""
						+ "\", \"forwardUrl\":\""
						+ ""
						+ "\", \"callbackType\":\"" + "" + "\"}";
			}
			response.getWriter().print(json);
		}
}
