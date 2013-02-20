package com.netsky.base.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;


/**
 * @description:自動生成表的數據字典
 * 
 * @class name:com.rms.base.util.XMLTableWrite
 * @author net Feb 20, 2013
 */
public class XMLTableWrite {

	private List datas;
 

	/**
	 * 
	 * @param path
	 * @param tableName
	 * @throws IOException
	 * @throws SQLException 
	 * @throws ClassNotFoundException
	 *             void
	 */
	public void autoGenerateTableXML(String path, String tableName)
			throws ClassNotFoundException, IOException, SQLException {
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
			Object obj[]=(Object[]) data;
			element = element.addElement("column");
			element.addElement("columnName").setText(obj[0].toString());
			element.addElement("index");
			element.addElement("name").setText(obj[1].toString());
			element.addElement("colName").setText(obj[1].toString());
			element = element.getParent();
		}
		OutputFormat format = new OutputFormat();
		OutputStream os = new FileOutputStream(path);
		XMLWriter writer = new XMLWriter(os, format);
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
	 * @throws SQLException
	 */
	private List getDatas(String tableName) throws ClassNotFoundException,
			SQLException {
		StringBuffer hql = new StringBuffer();
		datas=new ArrayList();
		hql
				.append("select u.column_name,u.comments from user_col_comments u where u.table_name='");
		hql.append(tableName.toUpperCase());
		hql.append("'");
		
		
		Statement st = this.createStatement();
		ResultSet rs=st.executeQuery(hql.toString());
		while (rs.next()) {
			Object object[]=new Object[2];
			object[0]=rs.getString(1);
			object[1]=rs.getString(2);
			datas.add(object);
		}
		
		return this.datas;
	}
	
	/**
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException Connection
	 */
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection conn=null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url="jdbc:oracle:thin:@132.229.154.215:1521:rms";
		String user="pss_nj";
		String password="netsky";
		conn=DriverManager.getConnection(url, user, password);
		return conn;
	}
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException Statement
	 */
	private Statement createStatement() throws SQLException, ClassNotFoundException {
		Statement st=null;
		st=this.getConnection().createStatement();
		return st;
	}

	public static void main(String[] args) throws ClassNotFoundException,
			IOException, SQLException {
		XMLTableWrite xTableWrite = new XMLTableWrite();
		xTableWrite.autoGenerateTableXML("D://xml.xml",
				"TD01_XMXX");
	}
}
