package com.rms.controller.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.Cell;
import jxl.read.biff.BiffException;

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
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Ta14_group_user;
import com.netsky.base.baseObject.PropertyInject;

import com.rms.dataObjects.gcjs.Te03_gcgys_b1;
import com.rms.dataObjects.gcjs.Te03_gcgys_b2;
import com.rms.dataObjects.gcjs.Te03_gcgys_b3j;
import com.rms.dataObjects.gcjs.Te03_gcgys_b3y;
import com.rms.dataObjects.gcjs.Te03_gcgys_b3b;
import com.rms.dataObjects.gcjs.Te03_gcgys_b4j;
import com.rms.dataObjects.gcjs.Te03_gcgys_b5j;
import com.rms.dataObjects.gcjs.Te03_gcgys_zhxx;

@Controller
public class AuxFunction {

	@Autowired
	private Dao dao;

	@Autowired
	private QueryService queryService;
	
	@Autowired
	private SaveService saveService;
	
	/**
	 * 日志处理类
	 */
	private Logger log = Logger.getLogger(this.getClass());

	@RequestMapping("/form/xzgcForDblx.do")
	public ModelAndView xzgcForDblx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelMap modelMap = new ModelMap();
		StringBuffer sql = new StringBuffer("");
		Long user_id = null;
		Long xm_id = convertUtil.toLong(request.getParameter("xm_id"));
		HttpSession session = request.getSession();
		if(session != null){
			Ta03_user ta03 = (Ta03_user)session.getAttribute("user");
			user_id = ta03.getId();
		}
		
		/*
		 * 获得未选工程列表
		 */
		sql.delete(0, sql.length());
		sql.append("select td00 ");
		sql.append("from Td00_gcxx td00 ");
		sql.append("where exists ( ");
		sql.append("select 'x' from Tb15_docflow tb15 ");
		sql.append("where td00.id = tb15.project_id and tb15.node_id = 10201 and tb15.user_id = ");
		sql.append(user_id.toString());
		sql.append(")");
		sql.append("and xm_id is null  order by gcmc ");
		List list = queryService.searchList(sql.toString()) ;
		modelMap.put("unselect_groups", list);
		
		/*
		 * 获得已选工程列表
		 */
		sql.delete(0, sql.length());
		sql.append("select td00 ");
		sql.append("from Td00_gcxx td00 ");
		sql.append("where xm_id = ");
		sql.append(xm_id);
		list = queryService.searchList(sql.toString()) ;
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
			if(groups != null){
				for (int i = 0; i < groups.length; i++) {
					sql.delete(0, sql.length());
					sql.append("update Td00_gcxx set xm_id = ");
					sql.append(xm_id);
					sql.append(" where id = ");
					sql.append(groups[i]);
					saveService.updateByHSql(sql.toString());
				}
			}
			
			response
					.getWriter()
					.print(
							"{\"statusCode\":\"200\", \"message\":\"操作成功\", \"navTabId\":\"\",\"forwardUrl\":\"\", \"callbackType\":\"\"}");
		} catch (Exception e) {
			log.error("saveXzgcForDblx.do[com.rms.controller.form.AuxFunction]"+e.getMessage()+e);
			response.getWriter().print("{\"statusCode\":\"300\", \"message\":\"操作失败\"}");
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
		response.setCharacterEncoding(request.getCharacterEncoding());

		Workbook wb = null;  
		Sheet sheet = null;
		try {
			MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
			Long project_id = convertUtil.toLong(request.getParameter("project_id"), -1L);
			
			saveService.updateByHSql("delete from Te03_gcgys_b1 where gc_id = "+project_id);
			saveService.updateByHSql("delete from Te03_gcgys_b2 where gc_id = "+project_id);
			saveService.updateByHSql("delete from Te03_gcgys_b3j where gc_id = "+project_id);
			saveService.updateByHSql("delete from Te03_gcgys_b3y where gc_id = "+project_id);
			saveService.updateByHSql("delete from Te03_gcgys_b3b where gc_id = "+project_id);
			saveService.updateByHSql("delete from Te03_gcgys_b4j where gc_id = "+project_id);
			saveService.updateByHSql("delete from Te03_gcgys_b5j where gc_id = "+project_id);
			saveService.updateByHSql("delete from Te03_gcgys_zhxx where gc_id = "+project_id);
			
			Iterator<?> it = mrequest.getFileNames();
			if (it.hasNext()) {
				String file_name = (String)it.next();
				MultipartFile file = mrequest.getFile(file_name);
				wb = Workbook.getWorkbook(file.getInputStream());  
				Sheet[] sheets = wb.getSheets();
				for(int i = 0;i < sheets.length;i ++){
					String sheetName = sheets[i].getName(); 
					sheet = wb.getSheet(sheetName);  
					
					/*
					 * 获得导入的起始单元格的横、纵坐标
					 */
					int x = 0,y = 0; 
					Cell cell = sheet.findCell("序号");
					if(cell != null){
						y = cell.getRow();
						x = cell.getColumn() + 2;
					}
					else{
						cell = sheet.findCell("Ⅰ");
						if(cell == null)
							throw new Exception("Excel格式非法，请按标准模板上传");
						y = cell.getRow();
						x = cell.getColumn() + 1;
					}
					
					
					int rows = sheet.getRows();  //rows取得当前工作蒲一共有几行
					int cols = sheet.getColumns();  //rows取得当前工作蒲一共有几列
					
					Map<String, Integer> columnIndex = null;
					String className = null;
					String[] colName = null;
					
					if(sheetName.indexOf("表一") != -1){
						
						//{序号、表格编号、费用名称、小型建筑工程费、需安设备费、不需安设备费、建筑安装工程费、其它费、预算费、人民币总价、外币总价}
						colName = new String[]{"xh","bgbh","fymc","jzgcf","xasbf","bxasbf","azgcf","qtfy","ybf","rmbzj","wbzj"};
						className = "com.rms.dataObjects.gcjs.Te03_gcgys_b1";
					}
					else if(sheetName.indexOf("表二") != -1){
						
						//{序号、费用名称、依据算法、技工费、普工费、合计。。。}
						colName = new String[]{"xh1","fymc1","yjsf1","jgf1","pgf1","hj1","xh2","fymc2","yjsf2","jgf2","pgf2","hj2"};
						className = "com.rms.dataObjects.gcjs.Te03_gcgys_b2";
					}
					else if(sheetName.indexOf("表三") != -1 && sheetName.indexOf("甲") != -1){
						
					
					}
					else if(sheetName.indexOf("表三") != -1 && sheetName.indexOf("乙") != -1){
						
						
					}
					if(sheetName.indexOf("表三") != -1 && sheetName.indexOf("丙") != -1){
						
						
					}
					else if(sheetName.indexOf("表四") != -1){
						
						
					}
					else if(sheetName.indexOf("表五") != -1){
							
						
					}
					
					if(className != null){
						columnIndex = new HashMap<String, Integer>();
						for(int j = 0;j < colName.length;j ++){
							columnIndex.put(colName[j].toUpperCase(), new Integer(x + j));
						}
						
						int t_row = y;
						while(t_row < rows - 1){
							Object o = Class.forName(className).newInstance();
							PropertyInject.setProperty(o, "gc_id", project_id);
							PropertyInject.injectFromExcel(o, columnIndex, sheet, t_row);
							saveService.save(o);
						}
					}
				}
			}
			
			response.getWriter().print("{\"statusCode\":\"200\", \"message\":\"导入成功\", \"navTabId\":\"\",\"forwardUrl\":\"\", \"callbackType\":\"\"}");
		} catch (Exception e) {
			log.error("gysImport.do[com.rms.controller.form.AuxFunction]"+e.getMessage()+e);
			response.getWriter().print("{\"statusCode\":\"300\", \"message\":\"操作失败\"}");
		}
		return null;
	}
}
