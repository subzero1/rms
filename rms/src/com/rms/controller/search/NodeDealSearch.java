package com.rms.controller.search;


 
import java.util.ArrayList; 
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseObject.PropertyInject;
import com.netsky.base.baseObject.ResultObject; 
import com.netsky.base.dataObjects.Ta06_module; 
import com.netsky.base.dataObjects.Ta08_reportfield;
import com.netsky.base.dataObjects.Tb11_operflow;
import com.netsky.base.dataObjects.Tb15_docflow;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService; 
import com.rms.dataObjects.form.Td00_gcxx;

/**
 * 文档处理情况分析
 * @author CT
 * @create 2010-06-23
 */
@Controller
public class NodeDealSearch {
	
	/**
	 * 查询服务
	 */
	@Autowired
	private QueryService queryService;
	

	@Autowired
	private ExceptionService exceptionService;
	
//	@RequestMapping("/search/docDealSearch.do")
//	public ModelAndView docDealSearchRequest(HttpServletRequest request,HttpServletResponse response) throws Exception {
//		request.setCharacterEncoding("GBK");
//		
//		Ta03_user user = null;
//		user = (Ta03_user) request.getSession().getAttribute("user");
//		if (user == null) {
//			return exceptionService.exceptionControl(this.getClass().getName(), "用户未登录或登录超时", new Exception("用户未登录"));
//		}
//		String search_area = StringFormatUtil.format(request.getParameter("search_area"),user.getArea_id().toString());
//		request.setAttribute("search_area", search_area);
//		request.setAttribute("search_level", user.getSearch_level());
//		List<?> area_list= queryService.searchList("select tc01.id,tc01.name from Tc01_area tc01 where id not in(6,7,8) order by id ");
//		request.setAttribute("area_list", area_list);
//		
//		StringBuffer area_hql = new StringBuffer(" select tc01 from Tc01_area tc01 where 1=1 and id not in(6,7,8) ");
//		if(user.getSearch_level().intValue()!=1){
//			area_hql.append(" and id="+ user.getArea_id());
//		}else{
//			if(!"0".equals(search_area)){
//				area_hql.append(" and id = "+search_area);
//			}
//		}
//		area_hql.append(" order by id ");
//		ResultObject area_ro= queryService.search(area_hql.toString());
//		List<List<?>> list_doc = new ArrayList<List<?>>();
//		while(area_ro.next()){
//			Tc01_area tc01 = (Tc01_area)area_ro.get("tc01");
//			
//			StringBuffer hql = new StringBuffer(" select ta03.area_name as area_name,ta03.area_id as area_id,ta01.name as dept_name,");
//					hql.append(" ta03.dept_id as dept_id,sum(doc.need_work) as dbs,sum(doc.on_work) as zbs, ");
//					hql.append(" sum(doc.wait_work) as dfs,sum(doc.reply_work) as hfs,sum(doc.off_work) as bjs ");
//					hql.append(" from DocOperting doc,Ta03_user ta03,Ta01_dept ta01 ");
//					hql.append(" where doc.user_id = ta03.id and ta03.dept_id=ta01.id and ta03.area_id='"+tc01.getId()+"'");
//					hql.append(" group by ta03.area_name,ta03.area_id,ta01.name,ta03.dept_id  order by ta03.area_id,ta03.dept_id ");
//					
//			ResultObject ro = queryService.search(hql.toString());
//			List list = new ArrayList();
//			while(ro.next()){
//				Map<String,Object> mo = ro.getMap();
//				mo.put("list_size", ro.getLength());
//				list.add(mo);
//			}
//			list_doc.add(list);
//		}
//		
//		request.setAttribute("list_doc", list_doc);
//		return new ModelAndView("/WEB-INF/jsp/search/docDealSearch.jsp");
//	}
//	/**
//	 * 文档处理情况明细
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping("/search/docDealmx.do")
//	public ModelAndView docDealmxRequest(HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		request.setCharacterEncoding("GBK");
//		Long area_id = new Long(request.getParameter("area_id"));
//		Long dept_id = new Long(request.getParameter("dept_id"));
//		request.setAttribute("type", request.getParameter("type"));
//		
//		StringBuffer  hql = new StringBuffer("select doc.login_id,doc.name,ta01.name,doc.need_work,doc.on_work,doc.wait_work,doc.reply_work,doc.off_work ");
//			hql.append(" from DocOperting doc,Ta03_user ta03,Ta01_dept ta01");
//			hql.append(" where doc.user_id = ta03.id and ta03.dept_id=ta01.id  and ta03.area_id="+area_id+" and ta03.dept_id="+dept_id);
//		
//		List<?> list = queryService.searchList(hql.toString());
//		
//		request.setAttribute("detail_list", list);
//		
//		return new ModelAndView("/WEB-INF/jsp/search/docDealmx.jsp");
//	}
//	
//	@RequestMapping("/search/docDealExcel.do")
//	public ModelAndView docDealExcelRequest(HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		request.setCharacterEncoding("GBK");
//		
//		Ta03_user user = null;
//		user = (Ta03_user) request.getSession().getAttribute("user");
//		if (user == null) {
//			return exceptionService.exceptionControl(this.getClass().getName(), "用户未登录或登录超时", new Exception("用户未登录"));
//		}
//		
//		try{
//			response.reset();
//			response.setContentType("application/vnd.ms-excel;charset=GBK;filename=export.xls");
//			jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
//			jxl.write.WritableSheet ws = wwb.createSheet("文档处理情况分析", 0);
//			
//			Label label;
//
//			//设置标题 
//			String[] titlestr={"地区","部门","待办文档","在办文档","待复文档","回复文档","办结文档"};
//			for(int i=0;i<titlestr.length;i++){
//				label = new Label(i,0,titlestr[i],getTitleFormat()); 
//				ws.addCell(label); 
//				ws.setColumnView(i, titlestr[i].getBytes().length*2);
//			}
//			
//			StringBuffer area_hql = new StringBuffer(" select tc01 from Tc01_area tc01 where  id not in(6,7,8) ");
//			if(request.getParameter("search_area")!=null && !request.getParameter("search_area").equals("")){
//				area_hql.append(" and tc01.id="+new Long(request.getParameter("search_area")));
//			}
//			if(user.getSearch_level().intValue()!=1){
//				area_hql.append(" and id="+ user.getArea_id());
//			}
//			area_hql.append(" order by id ");
//			ResultObject area_ro= queryService.search(area_hql.toString());
//			int j=0;
//			int a=0;
//			while(area_ro.next()){
//				Tc01_area tc01 = (Tc01_area)area_ro.get("tc01");
//				StringBuffer hql = new StringBuffer(" select ta03.area_name as area_name,ta03.area_id as area_id,ta01.name as dept_name,");
//						hql.append(" ta03.dept_id as dept_id,sum(doc.need_work) as dbs,sum(doc.on_work) as zbs, ");
//						hql.append(" sum(doc.wait_work) as dfs,sum(doc.reply_work) as hfs,sum(doc.off_work) as bjs ");
//						hql.append(" from DocOperting doc,Ta03_user ta03,Ta01_dept ta01 ");
//						hql.append(" where doc.user_id = ta03.id and ta03.dept_id=ta01.id and ta03.area_id='"+tc01.getId()+"'");
//						hql.append(" group by ta03.area_name,ta03.area_id,ta01.name,ta03.dept_id  order by ta03.area_id,ta03.dept_id ");
//						
//				ResultObject ro = queryService.search(hql.toString());
//				while(ro.next()){
//					j++;
//					Map<String,Object> mo = ro.getMap();
//					label = new Label(0,j,mo.get("area_name").toString(),getTextCellAlignLeftFormat()); 
//					ws.addCell(label); 
//					label = new Label(1,j,mo.get("dept_name").toString(),getTextCellAlignLeftFormat());
//					ws.addCell(label); 
//					label = new Label(2,j,mo.get("dbs").toString(),getTitleFormat());
//					ws.addCell(label); 
//					label = new Label(3,j,mo.get("zbs").toString(),getTitleFormat());
//					ws.addCell(label); 
//					label = new Label(4,j,mo.get("dfs").toString(),getTitleFormat());
//					ws.addCell(label); 
//					label = new Label(5,j,mo.get("hfs").toString(),getTitleFormat());
//					ws.addCell(label); 
//					label = new Label(6,j,mo.get("bjs").toString(),getTitleFormat());
//					ws.addCell(label); 
//				}
//				ws.mergeCells(0, a+1, 0, a+ro.getLength());
//				a += ro.getLength();
//			}
//			
//			wwb.write();
//			wwb.close();
//			response.getOutputStream().flush();
//			response.getOutputStream().close();
//			
//		}catch (Exception e){
//			return exceptionService.exceptionControl(this.getClass().getName(), "文档处理情况分析导出出错：", e);
//		}
//		return null;
//	}
//	
//	/**
//	 * 设定大标题格式，有边框，居中对齐
//	 */
//	private static WritableCellFormat getTitleFormat() throws WriteException {
//		WritableFont wf = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.NO_BOLD, false);
//		WritableCellFormat wcf_Text = new WritableCellFormat(wf);
//		wcf_Text.setAlignment(Alignment.CENTRE);
//		wcf_Text.setVerticalAlignment(VerticalAlignment.CENTRE);
//		wcf_Text.setBorder(Border.ALL, BorderLineStyle.THIN);
//		return wcf_Text;
//	}
//	/**
//	 * 设定文本域格式wcf_Text，有边框，居中对齐
//	 */
//	private WritableCellFormat getTextCellAlignLeftFormat() throws WriteException {
//		WritableFont wf = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.NO_BOLD, false);
//		WritableCellFormat wcf_Text = new WritableCellFormat(wf);
//		wcf_Text.setAlignment(Alignment.LEFT);
//		wcf_Text.setVerticalAlignment(VerticalAlignment.CENTRE);
//		wcf_Text.setBorder(Border.ALL, BorderLineStyle.THIN);
//		return wcf_Text;
//	}
	
	@RequestMapping("/search/jdcltj.do")
	public ModelAndView jdcltj(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("GBK");
		ModelMap modelMap = new ModelMap();
//		返回的VIEW
		String view = "/WEB-INF/jsp/search/jdcltj.jsp";
		
		// 获取搜索条件及结果显示参数
		String bdmc_id = convertUtil.toString(request.getParameter("bdmc_id"), "");
		String doc_status = request.getParameter("doc_status");
		
		modelMap.put("doc_status", doc_status);
		/*
		String ssdq = convertUtil.toString(request.getParameter("5"), convertUtil.toString(request.getParameter("ssdq"), ""));
		String zydl = convertUtil.toString(request.getParameter("9"), convertUtil.toString(request.getParameter("zydl"), ""));
		String qkdl = convertUtil.toString(request.getParameter("11"), convertUtil.toString(request.getParameter("qkdl"), ""));
		String qkxl = convertUtil.toString(request.getParameter("12"), convertUtil.toString(request.getParameter("qkxl"), ""));
		String zyxx = convertUtil.toString(request.getParameter("9"), convertUtil.toString(request.getParameter("zyxx"), ""));
		String tzlb = convertUtil.toString(request.getParameter("18"), convertUtil.toString(request.getParameter("tzlb"), ""));
		String gclb = convertUtil.toString(request.getParameter("8"), convertUtil.toString(request.getParameter("gclb"), ""));
		
		modelMap.put("ssdq", ssdq);
		modelMap.put("zydl", zydl);
		modelMap.put("qkdl", qkdl);
		modelMap.put("qkxl", qkxl);
		modelMap.put("zyxx", zyxx);
		modelMap.put("tzlb", tzlb);
		modelMap.put("gclb", gclb);
		ssdq = paramUtil(ssdq, ",","'");
		zydl = paramUtil(zydl, ",","'");
		
		qkxl = paramUtil(qkxl, ",","'");
		zyxx = paramUtil(zyxx, ",","'");
		
		qkdl = paramUtil(qkdl, ",","'");
		tzlb = paramUtil(tzlb, ",","'");
		gclb = paramUtil(gclb, ",","'");
		*/
		//保存即时参数
		List<String> params=new ArrayList<String>();
		params.add(bdmc_id);
		params.add(doc_status);
		
		// 是否具体到人
		boolean toperson = "yes".equals(convertUtil.toString(request
				.getParameter("toperson"), "no"));
		
		// 分页条件
		Integer page = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer pageRowSize = convertUtil.toInteger(request
				.getAttribute("numPerPage"),convertUtil.toInteger(request
						.getParameter("numPerPage")));
		if(pageRowSize==null||pageRowSize<=0)
			pageRowSize=18;
		int totalPages = 1;
		int totalRows = 0; 
		// 获取结果
		StringBuffer hsql = new StringBuffer("");
		List<String> docColsList = new ArrayList<String>();
		docColsList.add("表单名称");
		docColsList.add("节点名称");
		docColsList.add("待办数量");
		if (toperson){
			docColsList.add("帐号");
			docColsList.add("姓名");
			docColsList.add("电话");
		}
		
		
		
		
		hsql.append("select ta06.name as bdmc,tb12.node_name as jdmc");
		if (toperson) {
			hsql.append(",u.login_id as zh,u.name as xm,u.mobile_tel as dh");
		}
		hsql
				.append(", count(doc.id) as wdsl from Tb15_docflow doc,Ta03_user u ,Tb12_opernode tb12,Ta06_module ta06,Td00_gcxx td00");
		hsql
				.append(" where doc.user_id = u.id and ta06.id = doc.module_id and td00.id = doc.project_id  and doc.opernode_id = tb12.id");
		hsql.append(" and doc.module_id = " + bdmc_id);
		/*
		if (!"''".equals(ssdq))
		hsql.append(" and td00.ssdq in(" + ssdq + ")");
		if (!"''".equals(zydl))
		hsql.append(" and td00.zydl in(" + zydl + ")");
		if (!"''".equals(qkdl))
		hsql.append(" and td00.qkdl in(" + qkdl + ")");
		if (!"''".equals(tzlb))
		hsql.append(" and td00.tzlb in(" + tzlb + ")");
		if (!"''".equals(qkxl))
			hsql.append(" and td00.qkxl in(" + qkxl + ")");
		if (!"''".equals(zyxx))
			hsql.append(" and td00.zyxx in(" + zyxx + ")");
		if (!"''".equals(gclb))
		hsql.append(" and td00.gclb in(" + gclb + ")");
		
		*/
		if (!"".equals(doc_status))
		hsql.append(" and doc.doc_status in(" + doc_status + ")");
		hsql
				.append(" group by ta06.name,tb12.node_name ");
		if (toperson){
			hsql.append(",u.login_id,u.name,u.mobile_tel");
		} 
		ResultObject ro = null;
		
		
		// 是否EXCEL
		if ("yes".equals(request.getParameter("toexcel"))) {
			ro = queryService.search(hsql.toString());
		} else if (bdmc_id.length() != 0){
			try {
				ro = queryService.searchByPage(hsql.toString(), page, pageRowSize);
				totalPages = ro.getTotalPages();
				totalRows = ro.getTotalRows();
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				System.out.println("没有结果");
			}
		} 
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		while(ro != null && ro.next()){
			Map<String, Object> map = new HashMap<String, Object>();
			String bdmc = (String)ro.get("bdmc");
			String jdmc = (String)ro.get("jdmc");
			String zh = (String)ro.get("zh");
			String xm = (String)ro.get("xm");
			String dh= (String)ro.get("dh");
			Long wdsl = (Long)ro.get("wdsl");
			Long module_id = (Long)ro.get("module_id");
			Long node_id = (Long)ro.get("node_id");
			map.put("bdmc", bdmc);
			map.put("jdmc", jdmc);
			if (toperson){
				map.put("zh", zh);
				map.put("xm", xm);
				map.put("dh", dh);
			}
			map.put("wdsl", wdsl);
			map.put("module_id", module_id);
			map.put("node_id", node_id);
			resultList.add(map);
		}
		if ("yes".equals(request.getParameter("toexcel"))){
			Map<String,List> sheetMap = new HashMap<String,List>();
			List sheetList = new ArrayList();
			List<List<String>> contentList = new ArrayList<List<String>>();
			for (Map map : resultList) {
				List<String> tmpList = new ArrayList<String>();
				tmpList.add(""+(map.get("bdmc")==null?"":map.get("bdmc")));
				tmpList.add(""+(map.get("jdmc")==null?"":map.get("jdmc")));
				tmpList.add(""+(map.get("wdsl")==null?"":map.get("wdsl")));
				if (toperson){
				tmpList.add(""+(map.get("zh")==null?"":map.get("zh")));
				tmpList.add(""+(map.get("xm")==null?"":map.get("xm")));
				tmpList.add(""+(map.get("dh")==null?"":map.get("dh")));
				}
				contentList.add(tmpList);
			}
			//同时将明细导出
			List<String> blankLine = new ArrayList<String>();
			blankLine.add("");
			List<Ta08_reportfield> entityList = (List<Ta08_reportfield>)queryService.searchList("from Ta08_reportfield where module_id=100 and showflag=1");
			List<String> titleList1 = new ArrayList<String>();
			for (Ta08_reportfield ta08 : entityList) {
				titleList1.add(ta08.getComments());
			}
			for (Map map : resultList) {
				contentList.add(blankLine);
				List<String> tmpList = new ArrayList<String>();
				String s = "";
				s+=("表单名称:"+(map.get("bdmc")==null?"":map.get("bdmc")));
				s+=(" 节点名称:"+(map.get("jdmc")==null?"":map.get("jdmc")));
				if (toperson){
					s+=(" 帐号:"+(map.get("zh")==null?"":map.get("zh")));
					s+=(" 姓名:"+(map.get("xm")==null?"":map.get("xm")));
				}
				s += " 明细";
				tmpList.add(s);
				contentList.add(tmpList);
				contentList.add(titleList1);
				//要具体数据
				hsql = hsql.delete(0, hsql.length());
				hsql.append("select td00,tb11,doc from Tb11_operflow tb11,Tb15_docflow doc,Ta03_user u ,Tb12_opernode tb12,Ta06_module ta06,Td00_gcxx td00");
				hsql
						.append(" where "+(toperson?"u.login_id='"+map.get("zh")+"' and":"")+"  doc.node_id in (select node_id from tb12 where node_name='"+map.get("jdmc")+"') and "+" td00.id = tb11.project_id and doc.user_id = u.id and ta06.id = doc.module_id and td00.id = doc.project_id  and doc.opernode_id = tb12.id");
				hsql.append(" and doc.module_id = " + bdmc_id);
				
				/*
				if (!"''".equals(ssdq))
				hsql.append(" and td00.ssdq in(" + ssdq + ")");
				if (!"''".equals(zydl))
				hsql.append(" and td00.zydl in(" + zydl + ")");
				if (!"''".equals(qkdl))
				hsql.append(" and td00.qkdl in(" + qkdl + ")");
				if (!"''".equals(tzlb))
				hsql.append(" and td00.tzlb in(" + tzlb + ")");
				if (!"''".equals(qkxl))
					hsql.append(" and td00.qkxl in(" + qkxl + ")");
				if (!"''".equals(zyxx))
					hsql.append(" and td00.zyxx in(" + zyxx + ")");
				if (!"''".equals(gclb))
				hsql.append(" and td00.gclb in(" + gclb + ")");
				*/
				if (!"".equals(doc_status))
				hsql.append(" and doc.doc_status in(" + doc_status + ")");
				ro = queryService.search(hsql.toString());
				List<Object[]> resultList1 = new ArrayList<Object[]>();
				while (ro.next()){
					Td00_gcxx td00 = (Td00_gcxx)ro.get("td00");
					Tb11_operflow tb11 = (Tb11_operflow)ro.get("tb11");
					Tb15_docflow doc = (Tb15_docflow)ro.get("doc");
//					tmpList.add(td00);
				
				List<Object> list = new ArrayList<Object>();
				Object[] o = new Object[2];
				
				for (Ta08_reportfield ta08: entityList) {
					String string = ta08.getName();
					list.add(PropertyInject.getProperty(td00, string));
				}
				o[0] = list;
				Object[] obj = new Object[3];
				obj[0] = tb11.getName().substring(0,1);
				obj[1] = tb11.getDisplay();
				obj[2] = "?project_id="+tb11.getProject_id()+"&module_id="+doc.getModule_id()+"&doc_id="+doc.getDoc_id();
//				System.out.println(obj[2]);
				o[1] = obj;
				resultList1.add(o);
				}
				for (Object[] o : resultList1) {
					List list = (List)o[0];
					List<String> tempList = new ArrayList<String>();
					for (Object object : list) {
						tempList.add(""+(object==null?"":object));
					}
					contentList.add(tempList);
				}
			}
			
			
			
			
			List<String> titleList = new ArrayList<String>();
			titleList.add("表单名称");
			titleList.add("节点名称");
			titleList.add("文档数量");
			if(toperson){
			titleList.add("帐号");
			titleList.add("姓名");
			titleList.add("电话");
			}
			
			
			sheetList.add(titleList);
			sheetList.add(contentList);
			sheetMap.put("文档处理统计", sheetList);
			
			request.setAttribute("ExcelName", "文档处理统计" );
			request.setAttribute("sheetMap", sheetMap);
			view = "/export/toExcelWhithList.do";
		}
		// 获取搜索所需内容
//		String searchhsql = "from Ta08_reportfield where module_id = 100 and name in ('ssdq','zydl','zyxx','qkdl','qkxl','gclb','tzlb')";
		//100得到的是空值
		String searchhsql = "from Ta08_reportfield where module_id = 101 ";
		List<Ta08_reportfield> searchList = (List<Ta08_reportfield>)queryService.searchList(searchhsql);
		List<Ta06_module> bdmcList = (List<Ta06_module>)queryService.searchList("from Ta06_module where type = 1 and id like '1__' order by id");
		modelMap.put("bdmcList",bdmcList);
		modelMap.put("resultList",resultList);
		modelMap.put("docColsList", docColsList);
		modelMap.put("searchList",searchList);
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalRows", totalRows);
		modelMap.put("page", page);
		modelMap.put("pageRowSize", pageRowSize);
		modelMap.put("params", params);
		return new ModelAndView(view, modelMap);
	}
	
	private String paramUtil(String input,String split,String add){
		String[] tmp_arr = input.split(split);
		String result = "";
		if (tmp_arr!=null && tmp_arr.length!=0){
			for (String string : tmp_arr) {
				result+=add+string+add+split;
			}
			result = result.substring(0,result.length()-split.length());
		}
		return result.equals("")?input:result;
	}
	public static void main(String[] args) {
		Td00_gcxx td00 = new Td00_gcxx();
		Class<?> clazz = td00.getClass();
		
		
	}
	@RequestMapping("/search/wdclqk.do")
	public ModelAndView wdclqk(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
//		request.setCharacterEncoding("GBK");
		String view = "/WEB-INF/jsp/search/wdclqk.jsp";
		String node_name = request.getParameter("node_name");
		String zh = request.getParameter("zh");
//		String ssdq = convertUtil.toString(request.getParameter("5"), convertUtil.toString(request.getParameter("ssdq"), ""));
//		String zydl = convertUtil.toString(request.getParameter("9"), convertUtil.toString(request.getParameter("zydl"), ""));
//		String qkdl = convertUtil.toString(request.getParameter("11"), convertUtil.toString(request.getParameter("qkdl"), ""));
//		String qkxl = convertUtil.toString(request.getParameter("12"), convertUtil.toString(request.getParameter("qkxl"), ""));
//		String zyxx = convertUtil.toString(request.getParameter("9"), convertUtil.toString(request.getParameter("zyxx"), ""));
//		String tzlb = convertUtil.toString(request.getParameter("18"), convertUtil.toString(request.getParameter("tzlb"), ""));
//		String gclb = convertUtil.toString(request.getParameter("8"), convertUtil.toString(request.getParameter("gclb"), ""));
//		ssdq = paramUtil(ssdq, ",","'");
//		zydl = paramUtil(zydl, ",","'");
//		
//		qkxl = paramUtil(qkxl, ",","'");
//		zyxx = paramUtil(zyxx, ",","'");
//		
//		qkdl = paramUtil(qkdl, ",","'");
//		tzlb = paramUtil(tzlb, ",","'");
//		gclb = paramUtil(gclb, ",","'");
		String bdmc_id = request.getParameter("bdmc_id");
		String doc_status = request.getParameter("doc_status");
		boolean toperson = "yes".equals(convertUtil.toString(request
				.getParameter("toperson"), "no"));
		StringBuffer hsql = new StringBuffer("");
		hsql.append("select td00,tb11,doc from Tb11_operflow tb11,Tb15_docflow doc,Ta03_user u ,Tb12_opernode tb12,Ta06_module ta06,Td00_gcxx td00");
		hsql
				.append(" where "+(toperson?"u.login_id='"+zh+"' and":"")+"  doc.node_id in (select node_id from tb12 where node_name='"+node_name+"') and "+" td00.id = tb11.project_id and doc.user_id = u.id and ta06.id = doc.module_id and td00.id = doc.project_id  and doc.opernode_id = tb12.id");
		hsql.append(" and doc.module_id = " + bdmc_id);
//		if (!"''".equals(ssdq))
//		hsql.append(" and td00.ssdq in(" + ssdq + ")");
//		if (!"''".equals(zydl))
//		hsql.append(" and td00.zydl in(" + zydl + ")");
//		if (!"''".equals(qkdl))
//		hsql.append(" and td00.qkdl in(" + qkdl + ")");
//		if (!"''".equals(tzlb))
//		hsql.append(" and td00.tzlb in(" + tzlb + ")");
//		if (!"''".equals(qkxl))
//			hsql.append(" and td00.qkxl in(" + qkxl + ")");
//		if (!"''".equals(zyxx))
//			hsql.append(" and td00.zyxx in(" + zyxx + ")");
//		if (!"''".equals(gclb))
//		hsql.append(" and td00.gclb in(" + gclb + ")");
		if (!"".equals(doc_status))
		hsql.append(" and doc.doc_status in(" + doc_status + ")");
		System.out.println(hsql);
		
		
		Integer page = convertUtil.toInteger(request.getParameter("pageNum"), 1);
		Integer pageRowSize = convertUtil.toInteger(request
				.getAttribute("pageRowSize"),convertUtil.toInteger(request
						.getParameter("pageRowSize")));
		if(pageRowSize==null||pageRowSize<=0)
			pageRowSize=18;
		int totalPages = 1;
		int totalRows = 0;
		List<String> params=new ArrayList<String>();
		params.add(bdmc_id);
		params.add(doc_status);
		params.add(node_name);
		ResultObject ro = null;
		
		//是否excel
		if ("yes".equals(request.getParameter("toexcel"))) {
			ro = queryService.search(hsql.toString());
		} else
		ro = queryService.searchByPage(hsql.toString(), page, pageRowSize);
		totalPages = ro.getTotalPages();
		totalRows = ro.getTotalRows();
//		List<Td00_gcxx> tmpList = new ArrayList<Td00_gcxx>();
		List<Object[]> resultList = new ArrayList<Object[]>();
		List<Ta08_reportfield> entityList = (List<Ta08_reportfield>)queryService.searchList("from Ta08_reportfield where module_id=101  and name in ('ssdq','zydl','zyxx','qkdl','qkxl','gclb','tzlb')");
		while (ro.next()){
			Td00_gcxx td00 = (Td00_gcxx)ro.get("td00");
			Tb11_operflow tb11 = (Tb11_operflow)ro.get("tb11");
			Tb15_docflow doc = (Tb15_docflow)ro.get("doc");
//			tmpList.add(td00);
		
		List<Object> list = new ArrayList<Object>();
		Object[] o = new Object[2];
		
		for (Ta08_reportfield ta08: entityList) {
			String string = ta08.getName();
			list.add(PropertyInject.getProperty(td00, string));
		}
		o[0] = list;
		Object[] obj = new Object[10];
		obj[0] = tb11.getName().substring(0,1);
		obj[1] = tb11.getDisplay();
		obj[2] = "?project_id="+tb11.getProject_id()+"&module_id="+doc.getModule_id()+"&doc_id="+doc.getDoc_id();
		//附加参数
		obj[3]=String.valueOf(tb11.getProject_id());
		obj[4]=String.valueOf(doc.getModule_id());
		obj[5]=String.valueOf(doc.getDoc_id());
		obj[6]=String.valueOf(doc.getOpernode_id());
		obj[7]=String.valueOf(doc.getNode_id());
		obj[8]=String.valueOf(doc.getUser_id());
		System.out.println(obj[2]);
		o[1] = obj;
		resultList.add(o);
		}
//		if ("yes".equals(request.getParameter("toexcel"))){
//			Map<String,List> sheetMap = new HashMap<String,List>();
//			List sheetList = new LinkedList();
//			List<List<String>> contentList = new ArrayList<List<String>>();
//			for (Object[] o : resultList) {
//				List list = (List)o[0];
//				List<String> tempList = new ArrayList<String>();
//				for (Object object : list) {
//					tempList.add(""+(object==null?"":object));
//				}
//				contentList.add(tempList);
//			}
//			List<String> titleList = new ArrayList<String>();
//			for (Ta08_reportfield ta08 : entityList) {
//				titleList.add(ta08.getComments());
//			}
//			sheetList.add(titleList);
//			sheetList.add(contentList);
//			sheetMap.put("文档处理情况", sheetList);
//			
//			request.setAttribute("ExcelName", "文档处理情况" );
//			request.setAttribute("sheetMap", sheetMap);
//			view = "/export/toExcelWhithList.do";
//		}
		modelMap.put("entityList", entityList);
		modelMap.put("resultList", resultList);
		modelMap.put("totalPages", totalPages);
		modelMap.put("totalRows", totalRows);
		modelMap.put("page", page);
		modelMap.put("pageRowSize", pageRowSize);
		modelMap.put("params", params);
		return new ModelAndView(view, modelMap);
	}
	
	
	
	
	 
	
	
}
