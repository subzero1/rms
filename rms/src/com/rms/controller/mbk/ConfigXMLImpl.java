package com.rms.controller.mbk;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ConfigXMLImpl implements ConfigXML{
	private static String CONFIG_FILE="/importConfig/import.xml";
	public String getConfigFilePath(String config, String webinfpath) throws Exception {
		/**
		 * 获取基本配置文件
		 */
		File f = new File(webinfpath + CONFIG_FILE);
		if (!f.exists()) {
			throw new Exception("未找到基础配置文件");
		}
		SAXReader reader = new SAXReader();
		Document doc = reader.read(f);
		Element root = doc.getRootElement();
		Element foo;
		Iterator i;
		for (i = root.elementIterator("config"); i.hasNext();) {
			foo = (Element) i.next();
			String configName = foo.elementText("name");
			if (configName.equals(config)) {
				return foo.elementText("fileName");
			}
		}
		return null;
	}

	/**
	 *　重载方法：getConfigFilePath
	 * (non-Javadoc)
	 * @see com.rms.controller.mbk.ConfigXML#getConfigFilePath(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getConfigFilePath(String config, String webinfpath,
			String importXML) throws Exception {
		/**
		 * 获取基本配置文件
		 */
		if(importXML==null||importXML.equals(""))
			importXML=this.CONFIG_FILE;
		File f = new File(webinfpath +importXML);
		if (!f.exists()) {
			throw new Exception("未找到基础配置文件");
		}
		SAXReader reader = new SAXReader();
		Document doc = reader.read(f);
		Element root = doc.getRootElement();
		Element foo;
		Iterator i;
		for (i = root.elementIterator("config"); i.hasNext();) {
			foo = (Element) i.next();
			String configName = foo.elementText("name");
			if (configName.equals(config)) {
				return foo.elementText("fileName");
			}
		}
		return null;
	}

	/**
	 *　重载方法：getTagList
	 * (non-Javadoc)
	 * @see com.rms.controller.mbk.ConfigXML#getTagList(java.lang.String, java.lang.String)
	 */
	public List getTagList(String configFilePath, String webinfpath) throws Exception {
		List list=new LinkedList();
		File file=new File(webinfpath+configFilePath);
		if(!file.exists()){
			throw new Exception("未找到用户配置文件");
		}
		SAXReader reader=new SAXReader();
		Document doc=reader.read(file);
		Element root=doc.getRootElement();
		Element foo;
		Iterator i;
		Iterator j;
		for(i=root.elementIterator("tableInfo"); i.hasNext();){
			foo=(Element) i.next(); 
			for(Iterator it=foo.element("columns").elementIterator("column");it.hasNext();){ 
				Element element=(Element) it.next();  
				list.add(element.elementText("name"));
			}
		}
		return list;
	}

	/**
	 *　重载方法：getTagList
	 * (non-Javadoc)
	 * @see com.rms.controller.mbk.ConfigXML#getTagList(java.lang.String, java.lang.String, java.lang.String)
	 */
	public List getTagList(String configFilePath, String webinfpath,
			String tagName) throws Exception {
		List list=new LinkedList();
		File file=new File(webinfpath+configFilePath);
		if(!file.exists()){
			throw new Exception("未找到用户配置文件");
		}
		SAXReader reader=new SAXReader();
		Document doc=reader.read(file);
		Element root=doc.getRootElement();
		Element foo;
		Iterator i;
		Iterator j;
		for(i=root.elementIterator("tableInfo"); i.hasNext();){
			foo=(Element) i.next(); 
			for(Iterator it=foo.element("columns").elementIterator("column");it.hasNext();){ 
				Element element=(Element) it.next();  
				list.add(element.elementText(tagName));
			}
		}
		return list;
	}

	/**
	 *　重载方法：getTagListByConfig
	 * (non-Javadoc)
	 * @see com.rms.controller.mbk.ConfigXML#getTagListByConfig(java.lang.String, java.lang.String, java.lang.String)
	 */
	public List getTagListByConfig(String config, String webinfpath,
			String tagName) throws Exception {
		List list=new LinkedList();
		File file=new File(webinfpath+this.getConfigFilePath(config, webinfpath));
		if(!file.exists()){
			throw new Exception("未找到用户配置文件");
		}
		SAXReader reader=new SAXReader();
		Document doc=reader.read(file);
		Element root=doc.getRootElement();
		Element foo;
		Iterator i;
		Iterator j;
		for(i=root.elementIterator("tableInfo"); i.hasNext();){
			foo=(Element) i.next(); 
			for(Iterator it=foo.element("columns").elementIterator("column");it.hasNext();){ 
				Element element=(Element) it.next();  
				list.add(element.elementText(tagName));
			}
		}
		return list;
	}

}
