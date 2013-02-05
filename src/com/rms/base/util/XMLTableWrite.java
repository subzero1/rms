package com.rms.base.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import javax.swing.JOptionPane;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

 

public class XMLTableWrite {
	
	public void autoGenerateTableXML(String path,List data) throws FileNotFoundException {
		Element element;
		Document doc=DocumentHelper.createDocument();
		Element root=doc.addElement("excel");
		root.addAttribute("xmlns", "http://www.tjnetsky.com.cn");
		root.addAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
		root.addAttribute("xsi:schemaLocation", "http://www.tjnetsky.com.cn excel-imp.xsd");
		element=root.addElement("tableInfo");
		element.addElement("sheetNum");
		element.setText("0");
		String xmlString=doc.asXML();
		System.out.println(xmlString);
		File file=new File("D:/xml.xml");
		PrintWriter out=new PrintWriter(file);
		out.write(xmlString);
		out.close();
		JOptionPane.showMessageDialog(null, "文件已生成");
		
	}
	
	public static void main(String []args) throws FileNotFoundException {
		XMLTableWrite xTableWrite=new XMLTableWrite();
		xTableWrite.autoGenerateTableXML(null, null);
	}
}
