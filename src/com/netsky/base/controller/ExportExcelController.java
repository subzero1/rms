package com.netsky.base.controller;

import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.export.ExportExcel;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.QueryService;

/**
 * @description:
 * 导出Excel 
 * @class name:com.netsky.base.controller.ExportExcelController
 * @author Administrator May 7, 2010
 */
@Controller  //注释1  
public class ExportExcelController {
	
	@Autowired
	private QueryService queryService;
	
	/**
	 * 参数说明
	 * fileName 导出Excel的名字
	 * sheetMap 导出Excel的各sheet的名称及取sheet数据的hsql
	 * @param request
	 * @param response
	 * @throws Exception void
	 */
	@RequestMapping("/export/toExcelWhithHsql.do")
	public void toExcelWhithHsql(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("GBK");
		ResultObject ro = null;
		
		String fileName = convertUtil.toString(request.getAttribute("ExcelName"),"export.xls");
		Map<String,String> sheetMap = (Map<String,String>) request.getAttribute("sheetMap");
		
		response.reset();
		response.setHeader("Content-Disposition",
				"attachment;filename="+URLEncoder.encode(fileName,"UTF-8"));
//		response.setContentType("application/vnd.ms-excel;charset=GBK;filename=" + fileName);
		jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		Iterator itr = sheetMap.keySet().iterator();
		int i = 0;
		while(itr.hasNext()){
			String sheetName = convertUtil.toString(itr.next());
			jxl.write.WritableSheet ws0 = wwb.createSheet(sheetName, i++);
			ro = queryService.search(sheetMap.get(sheetName));
			ExportExcel.Ro2Excel(ro, ws0);
		}
		wwb.write();
		wwb.close();
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	
	@RequestMapping("/export/toExcelWhithList.do")
	public void toExcelWhithList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("GBK");
		ResultObject ro = null;
		
		String fileName = convertUtil.toString(request.getAttribute("ExcelName"),"export.xls");
		Map<String,List> sheetMap = (Map<String,List>) request.getAttribute("sheetMap");
		
		response.reset();
		response.setHeader("Content-Disposition",
				"attachment;filename="+URLEncoder.encode(fileName,"UTF-8"));
//		response.reset();
//		response.setContentType("application/vnd.ms-excel;charset=GBK;filename=" + fileName);
		jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		Iterator itr = sheetMap.keySet().iterator();
		int i = 0;
		while(itr.hasNext()){
			String sheetName = convertUtil.toString(itr.next());
			jxl.write.WritableSheet ws0 = wwb.createSheet(sheetName, i++);
			List tmp = sheetMap.get(sheetName);
			ExportExcel.List2Excel((List)tmp.get(0),(List)tmp.get(1), ws0);
		}
		wwb.write();
		wwb.close();
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
}
