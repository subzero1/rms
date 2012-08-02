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
			Long module_id = convertUtil.toLong(request.getParameter("module_id"), -1L);
			
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
					
					try{
						String sheetName = sheets[i].getName(); 
						sheet = wb.getSheet(sheetName);  
						
						/*
						 * 获得导入的起始单元格的横、纵坐标
						 */
						int x = 0,y = 0; 
						Cell cell = sheet.findCell("Ⅰ");
						if(cell == null)
							throw new Exception("Excel格式非法，请按标准模板上传");
						y = cell.getRow() + 1;
						x = cell.getColumn();
						
						int rows = sheet.getRows();  //rows取得当前工作蒲一共有几行
						int cols = sheet.getColumns();  //rows取得当前工作蒲一共有几列
						
						Map<String, Integer> columnIndex = null;
						String className = null;
						String[] colName = null;
						String keyColName = null;
						String notImportCol = null;
						String notImportColValue = null;
						
						if(sheetName.indexOf("表一") != -1){
							
							//{序号、表格编号、费用名称、小型建筑工程费、需安设备费、不需安设备费、建筑安装工程费、其它费、预算费、人民币总价、外币总价}
							colName = new String[]{"xh","bgbh","fymc","jzgcf","xasbf","bxasbf","azgcf","qtfy","ybf","rmbzj","wbzj"};
							className = "com.rms.dataObjects.gcjs.Te03_gcgys_b1";
							keyColName = "fymc";
						}
						else if(sheetName.indexOf("表二") != -1){
							
							//{序号、费用名称、依据算法、技工费、普工费、合计。。。}
							colName = new String[]{"xh1","fymc1","yjsf1","hj1","jg1","xh2","fymc2","yjsf2","hj2"};
							className = "com.rms.dataObjects.gcjs.Te03_gcgys_b2";
							keyColName = "fymc1";
						}
						else if(sheetName.indexOf("表三") != -1 && sheetName.indexOf("甲") != -1){
							
							//{序号、定额编号、定额名称、单位、数量、单位技工、单位普工、技工合计、普工合计}
							colName = new String[]{"xh","debh","xmmc","dw","sl","dwjg","dwpg","jghj","pghj"};
							className = "com.rms.dataObjects.gcjs.Te03_gcgys_b3j";
							keyColName = "xmmc";
						}
						else if(sheetName.indexOf("表三") != -1 && sheetName.indexOf("乙") != -1){
							
							//{序号、定额编号、定额名称、机械名称、单位、数量、数量、单位数量、单价、数量合计、金额合计、备注}
							colName = new String[]{"xh","debh","xmmc","dw","sl","jxmc","dwsl","dj","slhj","jehj"};
							className = "com.rms.dataObjects.gcjs.Te03_gcgys_b3y";
							keyColName = "xmmc";
						}
						else if(sheetName.indexOf("表三") != -1 && sheetName.indexOf("丙") != -1){
							
							//{序号、定额编号、定额名称、单位、数量、仪表名称、单位数量、单价、数量合计、金额合计}
							colName = new String[]{"xh","debh","xmmc","dw","sl","ybmc","dwsl","dj","slhj","jehj"};
							className = "com.rms.dataObjects.gcjs.Te03_gcgys_b3b";
							keyColName = "xmmc";
						}
						else if(sheetName.indexOf("表四") != -1 && sheetName.indexOf("材料") != -1){
							
							//{序号、名称、型号规格、单位、数量、单价、合计、备注}
							colName = new String[]{"xh","mc","xhgg","dw","sl","dj","hj","bz"};
							className = "com.rms.dataObjects.gcjs.Te03_gcgys_b4j";
							keyColName = "mc";
							notImportCol = "bgbh";
							notImportColValue = "ZC";
						}
						else if(sheetName.indexOf("表四") != -1 && sheetName.indexOf("设备") != -1 && sheetName.indexOf("需") != -1){
							
							//{序号、名称、型号规格、单位、数量、单价、合计、备注}
							colName = new String[]{"xh","mc","xhgg","dw","sl","dj","hj","bz"};
							className = "com.rms.dataObjects.gcjs.Te03_gcgys_b4j";
							keyColName = "mc";
							notImportCol = "bgbh";
							notImportColValue = "XA";
						}
						else if(sheetName.indexOf("表五") != -1){
								
							//{序号、费用名称、单位、数量、单价、合计、备注、计算依据和方法}
							colName = new String[]{"xh","fymc","yjsf","hj","hj","hj","bz"};
							className = "com.rms.dataObjects.gcjs.Te03_gcgys_b5j";
							keyColName = "fymc";
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
								/*
								 * 处理表四中的表格编号（主材ZC 还是需安设备XA）
								 */
								if(notImportCol != null){
									PropertyInject.setProperty(o, notImportCol, notImportColValue);
								}
								PropertyInject.injectFromExcel(o, columnIndex, sheet, t_row);
								String t_value = (String)PropertyInject.getProperty(o, keyColName);
								if(t_value != null && !t_value.equals("") && t_value.indexOf("审核") == -1)
									saveService.save(o);
								t_row = t_row + 1;
							}
						}
					}
					catch(Exception ee){
						if((ee+"").indexOf("NumberFormatException") != -1);
						else{
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
			sql.append(" and fymc1 = ");
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
			sql.append(" and fymc = '工程建设其他费'");
			ro = queryService.search(sql.toString());
			if(ro.next()){
				qtf = convertUtil.toDouble(ro.get("rmbzj"),0d);
			}
			
			/*
			 * 预算总金额、设备费
			 */
			Double ysje = 0d,sbf = 0d;
			sql.delete(0, sql.length());
			sql.append("select xasbf,rmbzj from Te03_gcgys_b1 ");
			sql.append("where gc_id = ");
			sql.append(project_id);
			sql.append(" and fymc like '%总%' and fymc like '%计%'");
			ro = queryService.search(sql.toString());
			if(ro.next()){
				ysje = convertUtil.toDouble(ro.get("rmbzj"),0d);
				sbf = convertUtil.toDouble(ro.get("xasbf"),0d);
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
			
			/*
			 * 同步工程信息
			 */
			Td00_gcxx td00 = (Td00_gcxx)queryService.searchById(Td00_gcxx.class, project_id);
			if(td00 != null){
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
			}
			else{
				Td01_xmxx td01 = (Td01_xmxx)queryService.searchById(Td01_xmxx.class, project_id);
				if(td01 != null){
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
			
			
			response.getWriter().print("{\"statusCode\":\"200\", \"message\":\"导入成功\", \"navTabId\":\"autoform"+module_id+project_id+"\",\"forwardUrl\":\"\", \"callbackType\":\"\"}");
		} catch (Exception e) {
			String msg = "";
			String e_msg = e.getMessage();
			if (e_msg != null && e_msg.indexOf("recognize OLE stream") != -1) {
				msg = "导入失败，Excel格式非法，请将Excel另存为<font color=red>2003版</font>的<font color=red>标准</font>的Excel后再导入";
			} else {
				msg = "导入失败,Excel格式非法,请参考导入模板";
			}
			log.error("gysImport.do[com.rms.controller.form.AuxFunction]"+e.getMessage()+e);
			response.getWriter().print("{\"statusCode\":\"300\", \"message\":\""+msg+"\"}");
		}
		return null;
	}
	
	@RequestMapping("/form/gysShow.do")
	public ModelAndView gysShow(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelMap modelMap = new ModelMap();
		StringBuffer sql = new StringBuffer("");
		Long user_id = null;
		Long project_id = convertUtil.toLong(request.getParameter("project_id"));
		String bgmc = convertUtil.toString(request.getParameter("bgmc"));
		String bgbh = convertUtil.toString(request.getParameter("bgbh"));
		
		/*
		 * 获得项目信息
		 */
		Td00_gcxx td00 = (Td00_gcxx)queryService.searchById(Td00_gcxx.class, project_id);
		if(td00 != null){
			modelMap.put("gcxx", td00);
		}
		else{
			Td01_xmxx td01 = (Td01_xmxx)queryService.searchById(Td01_xmxx.class, project_id);
			if(td01 != null){
				modelMap.put("xmxx", td01);
			}
		}
		
		if(bgmc.equals("")){
			return new ModelAndView("/WEB-INF/jsp/gys/gysShow.jsp", modelMap);
		}
		
		sql.delete(0, sql.length());
		sql.append("");
		sql.append("from ");
		sql.append(bgmc);
		sql.append(" where gc_id = ");
		sql.append(project_id);
		if(!bgbh.equals("")){
			sql.append(" and bgbh = '");
			sql.append(bgbh);
			sql.append("'");
		}
		sql.append(" order by id ");
		List list = queryService.searchList(sql.toString()) ;
		modelMap.put("gysList", list);
		
		return new ModelAndView("/WEB-INF/jsp/gys/"+bgmc+".jsp", modelMap);
	}
	

	@RequestMapping("/form/designDocShow.do")
	public ModelAndView designDocShow(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelMap modelMap = new ModelMap();
		StringBuffer sql = new StringBuffer("");
		Long project_id = convertUtil.toLong(request.getParameter("project_id"));
		Long module_id = convertUtil.toLong(request.getParameter("module_id"));
		String keywords  = convertUtil.toString(request.getParameter("keywords"));
		Ta03_user user = (Ta03_user)request.getSession().getAttribute("user");
		modelMap.put("curUserId", user.getId());
		
		/*
		 * 获得项目信息
		 */
		Td00_gcxx td00 = (Td00_gcxx)queryService.searchById(Td00_gcxx.class, project_id);
		if(td00 != null){
			modelMap.put("gcxx", td00);
		}
		else{
			Td01_xmxx td01 = (Td01_xmxx)queryService.searchById(Td01_xmxx.class, project_id);
			if(td01 != null){
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
		sql.append(" and slave_type in('工程图纸','设计说明','竣工资料')");
		if(!keywords.equals("")){
			sql.append(" and file_name like '%");
			sql.append(keywords);
			sql.append("%'");
		}
		sql.append(" order by id ");
		List list = queryService.searchList(sql.toString()) ;
		modelMap.put("projectSlaveList", list);
		
		return new ModelAndView("/WEB-INF/jsp/form/projectSlaveList.jsp", modelMap);
	}
}
