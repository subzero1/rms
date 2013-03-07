package com.rms.serviceImpl.jk;
import java.io.*;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

import javax.xml.parsers.*;

public class CcatsidaXMLParser {
	
	/**
	 * ������ͷ����������
	 * @param type
	 * @return
	 */
	public String getParameterXML(String type,String code) throws Exception{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();
		Element paramset = doc.createElement("paramset");
		doc.appendChild(paramset);
		
		Element querytype = doc.createElement("param");
		querytype.setAttribute("name","i_querytype");
		querytype.setAttribute("type","12");
		querytype.setAttribute("tag","2");
		querytype.setAttribute("value",type);
		paramset.appendChild(querytype);
		
		Element businessid = doc.createElement("param");
		businessid.setAttribute("name","i_businessid");
		businessid.setAttribute("type","12");
		businessid.setAttribute("tag","2");
		businessid.setAttribute("value",code);
		paramset.appendChild(businessid);
		
		Element cur_col = doc.createElement("param");
		cur_col.setAttribute("name","cur_col");
		cur_col.setAttribute("type","-10");
		cur_col.setAttribute("tag","1");
		cur_col.setAttribute("value","");
		paramset.appendChild(cur_col);
		

		
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		DOMSource dsource = new DOMSource(doc);
		transformer.setOutputProperty(OutputKeys.ENCODING,"GBK");
		transformer.setOutputProperty(OutputKeys.INDENT,"yes");
		transformer.setOutputProperty(OutputKeys.VERSION,"1.0");
		StringWriter stringwriter = new StringWriter();
		PrintWriter pw = new PrintWriter(stringwriter);
		StreamResult result = new StreamResult(pw);
		transformer.transform(dsource, result);
		
		String resultstring = stringwriter.toString(); 
		stringwriter.close();

		return resultstring;
	}
	
	public Element parseProject(String project) throws Exception{

		ByteArrayInputStream bis = new ByteArrayInputStream(project.getBytes("GBK"));
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(bis);

		Element root = doc.getDocumentElement();
		Element expand = (Element)root.getElementsByTagName("EXPAND").item(0);
		String result = expand.getAttribute("RESULT");
		String  totalrows = expand.getAttribute("TOTALROWS");
		if(Integer.parseInt(totalrows) <= 0){
			throw new Exception("Remote not return any rows");
		}
		Element resultset = (Element)root.getElementsByTagName("RESULTSET").item(0);
		Element resultrows = (Element)resultset.getElementsByTagName("ROWDATA").item(0);
		return resultrows;
	}

	public static void main(String[] args) {
		try{
			CcatsidaXMLParser xp = new CcatsidaXMLParser();
			System.out.println(xp.getParameterXML("1","2"));
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
}
