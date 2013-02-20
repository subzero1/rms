package com.rms.base.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class XMLTableWrite {

	private List datas;

	/**
	 * 
	 * @param path
	 * @param tableName
	 * @throws IOException 
	 * @throws ClassNotFoundException
	 *             void
	 */
	public void autoGenerateTableXML(String path, String tableName)
			throws ClassNotFoundException, IOException {
		this.datas = this.getDatas(tableName);
		Element element;
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("excel");
		root.addAttribute("xmlns", "http://www.tjnetsky.com.cn");
		root.addAttribute("xmlns:xsi",
				"http://www.w3.org/2001/XMLSchema-instance");
		root.addAttribute("xsi:schemaLocation",
				"http://www.tjnetsky.com.cn excel-imp.xsd");
		root.addComment("定义导入文件中表格信息");
		element = root.addElement("tableInfo");
		element.addComment("表格信息所在sheet位于excel文件中位置");
		element.addElement("sheetNum").setText("0");
		element.addElement("startRow").setText("1");
		element.addElement("endRow");
		element.addElement("endFlag");
		element.addElement("tableName");
		element.addElement("tableNameShow").setText("Td13_rwrw");
		element.addElement("fatherTables");

		element = element.addElement("columns");
		element.addComment("当前表字段信息");
		element.addElement("type").setText("byName");
		element.addElement("titleRow").setText("0");
		for (Object data : datas) { 
			element=element.addElement("column");
			element.addElement("columnName").setText(data.toString());
			element.addElement("index");
			element.addElement("name");
			element.addElement("colName");
			element = element.getParent();
		}
		OutputFormat format=new OutputFormat();
		OutputStream os=new FileOutputStream(path);
		XMLWriter writer=new XMLWriter(os,format);
		writer.write(doc);
		writer.close();
		os.close();
		
		JOptionPane.showMessageDialog(null, "文件已生成");

	}

	/**
	 * 
	 * @param tableName
	 * @return
	 * @throws ClassNotFoundException
	 *             List
	 */
	public List getDatas(String tableName) throws ClassNotFoundException {
		this.datas = new ArrayList();
		Class dataClass = Class.forName(tableName);
		Field field[] = dataClass.getDeclaredFields();
		for (Field field2 : field) {
			if (!field2.toString().contains("serialVersionUID")) {
				datas.add(field2.toString().substring(
						field2.toString().lastIndexOf(".") + 1));
			}

		}
		return this.datas;
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		XMLTableWrite xTableWrite = new XMLTableWrite();
		xTableWrite.autoGenerateTableXML("D://xml.xml",
				"com.rms.dataObjects.base.Tc01_property");
	}
}
