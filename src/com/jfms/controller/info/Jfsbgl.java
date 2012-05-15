package com.jfms.controller.info;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.jfms.dataObjects.base.Tc01_property;
import com.jfms.dataObjects.info.Td00_jfxx;
import com.jfms.dataObjects.info.Td01_sbxx;
import com.jfms.dataObjects.info.Td11_jfpmsq;
import com.jfms.dataObjects.info.Td12_gljf;
import com.jfms.dataObjects.info.Td13_jfsbmx;
import com.netsky.base.baseDao.Dao;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.controller.OperFile;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.service.others.GeneralService;
import com.netsky.base.utils.convertUtil;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Tb02_node;

@Controller
public class Jfsbgl {

	/**
	 * 数据服务
	 */
	@Autowired
	private Dao dao;

	@Autowired
	private ExceptionService exceptionService;

	@Autowired
	private SaveService saveService;

	/**
	 * 查询服务
	 */
	@Autowired
	private QueryService queryService;
	/**
	 * 通用服务
	 */
	@Autowired
	private GeneralService generalService;

	/**
	 * 机房申请/变更设备明细列表 参数：jfxx_id,doc_id,project_id,module_id
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/pmsqSbmx.do")
	public ModelAndView pmsqSbmx(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		// 数据库相关变量
		StringBuffer hsql = new StringBuffer("");
		ModelMap modelMap = new ModelMap();
		ResultObject ro = null;

		// 分页变量
		Integer pageNum = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer numPerPage = convertUtil.toInteger(request.getParameter("numPerPage"), 20);
		String orderField = convertUtil.toString(request.getParameter("orderField"), "id");
		String orderDirection = convertUtil.toString(request.getParameter("orderDirection"), "desc");
		Integer totalCount = 0;
		Integer pageNumShown = 0;

		Long doc_id = convertUtil.toLong(request.getParameter("doc_id"), -1L);
		Long project_id = convertUtil.toLong(request.getParameter("project_id"), -1L);
		Long module_id = convertUtil.toLong(request.getParameter("module_id"), -1L);
		Long node_id = convertUtil.toLong(request.getParameter("node_id"),-1L);
		Long jfxx_id = convertUtil.toLong(request.getParameter("jfxx_id"), -1L);

		/*
		 * 获得机房申请/变更设备明细列表
		 */
		hsql.delete(0, hsql.length());
		hsql.append(" from Td13_jfsbmx ");
		hsql.append(" where jfxx_id=");
		hsql.append(jfxx_id);
		hsql.append(" and sqd_id=");
		hsql.append(doc_id);

		hsql.append(" order by ");
		hsql.append(orderField);
		hsql.append(" ");
		hsql.append(orderDirection);

		ro = queryService.searchByPage(hsql.toString(), pageNum, numPerPage);
		totalCount = ro.getTotalRows();
		pageNumShown = ro.getTotalPages();

		modelMap.put("totalCount", totalCount);
		modelMap.put("pageNumShown", pageNumShown);
		modelMap.put("numPerPage", numPerPage);
		modelMap.put("orderField", orderField);
		modelMap.put("orderDirection", orderDirection);

		List<?> pmsqSbmxList = ro.getList();
		Td00_jfxx td00 = (Td00_jfxx) dao.getObject(Td00_jfxx.class, jfxx_id);

		modelMap.put("pmsqSbmxList", pmsqSbmxList);
		modelMap.put("jfmc", td00.getJfmc());
		
		/*
		 * 操作权限
		 */
		Tb02_node tb02 = (Tb02_node)queryService.searchById(Tb02_node.class, node_id);
		String t_desc = convertUtil.toString(tb02.getRemark());
		if(t_desc.indexOf("[设备录入]") != -1){//shebeiluru
			modelMap.put("canOperSb", "yes");
		}

		return new ModelAndView("/WEB-INF/jsp/form/pmsqSbmx.jsp", modelMap);
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
	@RequestMapping("/pmsqSbxx.do")
	public ModelAndView sbxx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = convertUtil.toLong(request.getParameter("id"), -1L);
		ModelMap modelMap = new ModelMap();
		StringBuffer hsql = new StringBuffer();

		// 获取专业
		hsql.delete(0, hsql.length());
		hsql.append(" from Tc01_property ");
		hsql.append(" where type='所属专业'");
		hsql.append(" order by id ");
		List<Tc01_property> zyList = (List<Tc01_property>) dao.search(hsql.toString());
		modelMap.put("zyList", zyList);

		// 获取建设性质
		hsql.delete(0, hsql.length());
		hsql.append(" from Tc01_property ");
		hsql.append(" where type='建设性质'");
		hsql.append(" order by id ");
		List<Tc01_property> jsxzList = (List<Tc01_property>) dao.search(hsql.toString());
		modelMap.put("jsxzList", jsxzList);

		// 获取供电方式
		hsql.delete(0, hsql.length());
		hsql.append(" from Tc01_property ");
		hsql.append(" where type='供电方式'");
		hsql.append(" order by id ");
		List<Tc01_property> gdfsList = (List<Tc01_property>) dao.search(hsql.toString());
		modelMap.put("gdfsList", gdfsList);

		// 获取设备对象
		Td13_jfsbmx sbxx = null;
		sbxx = (Td13_jfsbmx) dao.getObject(Td13_jfsbmx.class, id);
		modelMap.put("td13", sbxx);
		return new ModelAndView("/WEB-INF/jsp/form/sqsbmx.jsp", modelMap);
	}

	@RequestMapping("/exceltosbjsp.do")
	public String exceltosbjsp() {
		return "/WEB-INF/jsp/form/exceltosb.jsp";
	}

	@RequestMapping("/exceltosb.do")
	public void exceltosbxx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("GBK");
		String json = "{\"statusCode\":\"200\", \"message\":\"导入成功\", \"navTabId\":\"" + ""
				+ "\", \"forwardUrl\":\"\", \"callbackType\":\"\"}";
		try {
			MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
			Iterator<?> it = mrequest.getFileNames();
			while (it.hasNext()) {
				String fileDispath = (String) it.next();
				MultipartFile file = mrequest.getFile(fileDispath);
				if (file.getName() != null && !file.getName().equals("") && file.getInputStream().available() > 0) {
					List<List<String>> rowlist = (List<List<String>>) ExcelRead.readEcelFilebyStream(file
							.getInputStream(), file.getOriginalFilename(), 0, 1);
					// 遍历全表之前 需要的标记 定义在此
					Long jfxx_id = convertUtil.toLong(request.getParameter("jfxx_id"), -1L);
					Long project_id = convertUtil.toLong(request.getParameter("project_id"), -1L);
					Long sqd_id = convertUtil.toLong(request.getParameter("sqd_id"), -1L);
					Long delFlag =  convertUtil.toLong(request.getParameter("delFlag"), -1L);
					List<String> titleList = null;

					//删除原设备信息
					if(delFlag == 1){
						dao.update("delete from Td13_jfsbmx where jfxx_id=" + jfxx_id + " and sqd_id=" + sqd_id + " and project_id=" + project_id);
					}
					
					// 遍历全表
					Session session = saveService.getHiberbateSession();
					Transaction transaction = session.beginTransaction();
					transaction.begin();
					try {
						for (int j = 0; j < rowlist.size(); j++) {
							List<String> cellList = rowlist.get(j);// 获得cellLIST
							if (j == 0) {
								titleList = cellList;
							} else {
								Td13_jfsbmx td13 = new Td13_jfsbmx();
								if (cellList.get(0) == null || cellList.get(0).trim().length() == 0) {
									continue;
								}
								for (int i = 0; i < cellList.size(); i++) {
									td13.setProject_id(project_id);
									td13.setSqd_id(sqd_id);
									td13.setJfxx_id(jfxx_id);
									String str = cellList.get(i);
									if ("产品名称".equals(titleList.get(i))) {
										td13.setSbmc(str);
									} else if ("产品型号".equals(titleList.get(i))) {
										td13.setSbxh(str);
									} else if ("产品规格".equals(titleList.get(i))) {
										td13.setSbgg(str);
									} else if ("计量单位".equals(titleList.get(i))) {
										td13.setDw(str);
									} else if ("需求数量".equals(titleList.get(i))) {
										td13.setSl(new Double(str));
									} else if ("备注".equals(titleList.get(i))) {
										td13.setBz(str);
									}
								}
								session.save(td13);
							}
						}
						session.flush();
						transaction.commit();
					} catch (Exception e) {
						transaction.rollback();
						throw e;
					} finally {
						session.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			json = "{\"statusCode\":\"300\", \"message\":\"导入失败\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}";
		}
		response.getWriter().print(json);
	}
	
	/**
	 * 设备明细删除ajax实现
	 * 
	 * @param reqeust
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("/ajaxSqsbDel.do")
	public void ajaxJfpmDel(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding(request.getCharacterEncoding());
		PrintWriter out = null;
		response.setContentType("text/xml");

		Long id = convertUtil.toLong(request.getParameter("id"), -1L);

		try {
			out = response.getWriter();
			dao.removeObject(Td13_jfsbmx.class, id);
			out
					.print("{\"statusCode\":\"200\", \"message\":\"设备删除成功\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");
		} catch (Exception e) {
			out
					.print("{\"statusCode\":\"300\", \"message\":\"设备删除失败\", \"navTabId\":\"\", \"forwardUrl\":\"\", \"callbackType\":\"\"}");
			exceptionService.exceptionControl(
					"com.crht.controller.business.Repository", "设备删除异常", e);
		}
	}
}