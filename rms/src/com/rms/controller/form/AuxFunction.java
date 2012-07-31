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
		response.setCharacterEncoding("GBK");

		Workbook wb = null;  
		Sheet sheet = null;
		StringBuffer sql = new StringBuffer();
		ResultObject ro = null;
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
						y = cell.getRow() + 2;
						x = cell.getColumn();
					}
					else{
						cell = sheet.findCell("Ⅰ");
						if(cell == null)
							throw new Exception("Excel格式非法，请按标准模板上传");
						y = cell.getRow() + 1;
						x = cell.getColumn();
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
						colName = new String[]{"xh1","fymc1","yjsf1","hj1","jg1","xh2","fymc2","yjsf2","hj2"};
						className = "com.rms.dataObjects.gcjs.Te03_gcgys_b2";
					}
					else if(sheetName.indexOf("表三") != -1 && sheetName.indexOf("甲") != -1){
						
						//{序号、定额编号、定额名称、单位、数量、单位技工、单位普工、技工合计、普工合计}
						colName = new String[]{"xh","debh","xmmc","dw","sl","dwjg","dwpg","jghj","pghj"};
						className = "com.rms.dataObjects.gcjs.Te03_gcgys_b3j";
					}
					else if(sheetName.indexOf("表三") != -1 && sheetName.indexOf("乙") != -1){
						
						//{序号、定额编号、定额名称、机械名称、单位、数量、数量、单位数量、单价、数量合计、金额合计、备注}
						colName = new String[]{"xh","debh","xmmc","dw","sl","jxmc","dwsl","dj","slhj","jehj"};
						className = "com.rms.dataObjects.gcjs.Te03_gcgys_b3y";
					}
					else if(sheetName.indexOf("表三") != -1 && sheetName.indexOf("丙") != -1){
						
						//{序号、定额编号、定额名称、单位、数量、仪表名称、单位数量、单价、数量合计、金额合计}
						colName = new String[]{"xh","debh","xmmc","dw","sl","ybmc","dwsl","dj","slhj","jehj"};
						className = "com.rms.dataObjects.gcjs.Te03_gcgys_b3b";
					}
					else if(sheetName.indexOf("表四") != -1){
						
						//{序号、名称、型号规格、单位、数量、单价、合计、备注}
						colName = new String[]{"xh","mc","xhgg","dw","sl","dj","hj","bz"};
						className = "com.rms.dataObjects.gcjs.Te03_gcgys_b4j";
					}
					else if(sheetName.indexOf("表五") != -1){
							
						//{序号、费用名称、单位、数量、单价、合计、备注、计算依据和方法}
						colName = new String[]{"xh","fymc","yjsf","hj","bz"};
						className = "com.rms.dataObjects.gcjs.Te03_gcgys_b5j";
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
							t_row = t_row + 1;
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
			Double jggr = 0d,pggr = 0d;
			sql.delete(0, sql.length());
			sql.append("select jghj,pghj from Te03_gcgys_b3j ");
			sql.append("where xmmc like '%总%' and xmmc like '%计%' and gc_id = ");
			sql.append(project_id);
			ro = queryService.search(sql.toString());
			if(ro.next()){
				jggr = (Double)ro.get("jghj");
				pggr = (Double)ro.get("pghj");
			}
			
			/*
			 * 设计费、监理费
			 */
			Double sjf = 0d,jlf = 0d;
			sql.delete(0, sql.length());
			sql.append("select hj from Te03_gcgys_b5j ");
			sql.append("where fymc like '%设计费%' and gc_id = ");
			sql.append(project_id);
			ro = queryService.search(sql.toString());
			if(ro.next()){
				sjf = (Double)ro.get("hj");
			}
			sql.delete(0, sql.length());
			sql.append("select hj from Te03_gcgys_b5j ");
			sql.append("where fymc like '%监理费%' and gc_id = ");
			sql.append(project_id);
			ro = queryService.search(sql.toString());
			if(ro.next()){
				jlf = (Double)ro.get("hj");
			}
			
			/*
			 * 人工费、材料费、机械费、仪表费、建安费
			 */
			Double rgf = 0d,clf = 0d,jxf = 0d,ybf = 0d,jaf = 0d;
			sql.delete(0, sql.length());
			sql.append("select hj1 from Te03_gcgys_b2 ");
			sql.append("where gc_id = ");
			sql.append(project_id);
			sql.append(" fymc1 = ");
			ro = queryService.search(sql.toString() + "'人工费'");
			if(ro.next()){
				rgf = (Double)ro.get("hj1");
			}
			ro = queryService.search(sql.toString() + "'材料费'");
			if(ro.next()){
				clf = (Double)ro.get("hj1");
			}
			ro = queryService.search(sql.toString() + "'仪表费'");
			if(ro.next()){
				ybf = (Double)ro.get("hj1");
			}
			ro = queryService.search(sql.toString() + "'机械费'");
			if(ro.next()){
				jxf = (Double)ro.get("hj1");
			}
			ro = queryService.search(sql.toString() + "'建筑安装工程费'");
			if(ro.next()){
				jaf = (Double)ro.get("hj1");
			}
			
			/*
			 * 其它费
			 */
			Double qtf = 0d;
			sql.delete(0, sql.length());
			sql.append("select rmbzj from Te03_gcgys_b1 ");
			sql.append("where gc_id = ");
			sql.append(project_id);
			sql.append(" fymc = '工程建设其他费'");
			ro = queryService.search(sql.toString());
			if(ro.next()){
				qtf = (Double)ro.get("rmbzj");
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
			if(ro.next()){
				te03_zhxx = (Te03_gcgys_zhxx)ro.get(Te03_gcgys_zhxx.class.getName());
			}
			else{
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
			
			response.getWriter().print("{\"statusCode\":\"200\", \"message\":\"导入成功\", \"navTabId\":\"\",\"forwardUrl\":\"\", \"callbackType\":\"\"}");
		} catch (Exception e) {
			log.error("gysImport.do[com.rms.controller.form.AuxFunction]"+e.getMessage()+e);
			response.getWriter().print("{\"statusCode\":\"300\", \"message\":\"操作失败\"}");
		}
		return null;
	}
}
