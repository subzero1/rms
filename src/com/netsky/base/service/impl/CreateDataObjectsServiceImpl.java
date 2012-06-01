package com.netsky.base.service.impl;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netsky.base.baseObject.SharedCfgData;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.convertUtil;
import com.netsky.base.utils.OperProperties;
import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.dataObjects.Ta06_module;
import com.netsky.base.dataObjects.Ta07_formfield;
//import com.netsky.base.dataObjects.Tz06_ext_log;
import com.netsky.base.baseObject.PropertyInject;

/**
 * @author lee.xiangyu
 * @desc create dataObjects[java] from ta06,ta07
 * 		 create hibernate configure file[java.hbm] from dataObjects
 * 		 write [java.hbm] to applicationContext-hibernate.xml
 */
@Service("createDoService")
public class CreateDataObjectsServiceImpl {
	
	/**
	 * 日志处理类
	 */
	private Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 数据库查询操作服务
	 */
	@Autowired
	private QueryService queryService;

	/**
	 * 数据库保存操作服务
	 */
	@Autowired
	private SaveService saveService;
	
	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}

	public void setSaveService(SaveService saveService) {
		this.saveService = saveService;
	}

	/**
	 * @param paramMap
	 * @desc create dataObjects[java] and hibernate *.hbm.xml from ta06,ta07
	 */
	public void createJavaAndXml(Map<String, Object> paramMap) throws Exception{
		/*
		 *获得相关参数 
		 */
		String t_webRoot = convertUtil.toString(paramMap.get("webRoot"));
		String t_extDataObjectPackage = null;

		Map<String,String> cfgMap = SharedCfgData.getMap();
		String auto_cfg_uri = cfgMap.get("baseConfig.properties");
		OperProperties op = new OperProperties();
		op.load(auto_cfg_uri);
		if(t_webRoot == null){
			t_webRoot = op.getValue("webRoot");
			if(t_webRoot == null || t_webRoot.equals("")){
				throw new Exception("not found value [webRoot=XXX] in autoConfig/baseConfig.properties");
			}
		}

		t_extDataObjectPackage = op.getValue("extDataObjectPackage");
		if(t_extDataObjectPackage == null || t_extDataObjectPackage.equals("")){
			throw new Exception("not found value [extDataObjectPackage=XXX] in autoConfig/baseConfig.properties");
		}
		
		paramMap.put("webRoot", t_webRoot);
		paramMap.put("extDataObjectPackage", t_extDataObjectPackage);
		createDoJava(paramMap);
		java2Xml(paramMap);
	}
	/**
	 * @param paramMap
	 * @desc create dataObjects[java] from ta06,ta07
	 */
	public void createDoJava(Map<String, Object> paramMap) throws Exception{
		
		QueryBuilder queryBuilder = null;
		Long ta06_id = (Long)paramMap.get("ta06_id");
		if(ta06_id == null){
			throw new Exception("no ta06 params");
		}
		String t_webRoot = convertUtil.toString(paramMap.get("webRoot"));
		String t_extDataObjectPackage = convertUtil.toString(paramMap.get("extDataObjectPackage"));

		/*
		 * 获得WEB根目录和生成文件存放位置
		 */
		if(t_webRoot.equals("") || t_extDataObjectPackage.equals("")){
			
			Map<String,String> cfgMap = SharedCfgData.getMap();
			String auto_cfg_uri = cfgMap.get("baseConfig.properties");
			OperProperties op = new OperProperties();
			op.load(auto_cfg_uri);
			t_webRoot = op.getValue("webRoot");
			if(t_webRoot == null || t_webRoot.equals("")){
				throw new Exception("not found value [webRoot=XXX] in autoConfig/baseConfig.properties");
			}
			
			t_extDataObjectPackage = op.getValue("extDataObjectPackage");
			if(t_extDataObjectPackage == null || t_extDataObjectPackage.equals("")){
				throw new Exception("not found value [extDataObjectPackage=XXX] in autoConfig/baseConfig.properties");
			}
		}
		
		/*
		 * 获得持久化对象模板
		 */
		StringBuffer t_container  = new StringBuffer(t_webRoot);
		t_container.append(System.getProperty("file.separator"));
		t_container.append("WEB-INF");
		t_container.append(System.getProperty("file.separator"));
		t_container.append("extConfig");
		t_container.append(System.getProperty("file.separator"));
		t_container.append("createDoModel.model");
		String t_modelContent = getModelContent(t_container.toString());
		if(t_modelContent == null){
			throw new Exception("not found DataObjects model[extConfig/createDoModel.model]");
		}
		
		/*
		 * 替换模板中的内容[$package,$date,$obj_name,$serializable,$sequence,$field_list]
		 */
		t_modelContent = t_modelContent.replace("$package", t_extDataObjectPackage);
		t_modelContent = t_modelContent.replace("$date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		Long t_random = Math.round(Math.random() * 1000000000000000000L);
		t_modelContent = t_modelContent.replace("$serializable", t_random + "L");
		
		/*
		 * 根据TA06获得类名
		 */
		Ta06_module ta06 = (Ta06_module)queryService.searchById(Ta06_module.class, ta06_id);
		if(ta06 == null){
			throw new Exception("not found infomation in ta06_module ,ta06_id="+ta06_id);
		}
		String objName = null;
		String tableName = null;
		String objDesc = null;
		if(ta06 != null){
			objName = convertUtil.toString(ta06.getForm_table());
			objDesc = convertUtil.toString(ta06.getName());
			if(objName == null){
				throw new Exception("not found Object name[form_table] in ta06_module,ta06_id="+ta06_id);
			}
			tableName = objName.substring(objName.lastIndexOf(".") + 1 , objName.length());
			tableName = tableName.toUpperCase().substring(0,1) + tableName.toLowerCase().substring(1,tableName.length());
			t_modelContent = t_modelContent.replaceAll("\\$obj_name", tableName);
			t_modelContent = t_modelContent.replaceAll("\\$sequence", tableName+"_num");
		}

		/*
		 * 根据TA07获得字段名
		 */
		StringBuffer t_fieldlist = new StringBuffer("");
		queryBuilder = new HibernateQueryBuilder(Ta07_formfield.class);
		queryBuilder.eq("module_id", ta06_id);
		ResultObject ro = queryService.search(queryBuilder);
		while(ro.next()){
			Ta07_formfield ta07 = (Ta07_formfield)ro.get(Ta07_formfield.class.getName());
			String t_name = convertUtil.toString(ta07.getName());
			String tt_name = t_name.toUpperCase().substring(0,1) + t_name.toLowerCase().substring(1,t_name.length());
			String t_comment = convertUtil.toString(ta07.getComments());
			String t_datatype = convertUtil.toString(ta07.getDatatype());
			if(t_datatype.toLowerCase().indexOf("date") != -1){
				t_datatype = "Date";
			}
			else if(t_datatype.toLowerCase().indexOf("number") != -1){
				t_datatype = "Long";
			}
			else{
				t_datatype = "String";
			}
			
			if(!t_name.equals("") || !t_name.toLowerCase().equals("id")){
				t_fieldlist.append(" /**\n");
				t_fieldlist.append(" * " + t_comment + "\n");
				t_fieldlist.append(" */\n");
				t_fieldlist.append(" private " + t_datatype + " " + t_name + ";\n\n");
				t_fieldlist.append(" /**\n");
				t_fieldlist.append(" * @hibernate.property column=\"" + t_name + "\"\n");
				t_fieldlist.append(" * @return Returns the " + t_name + ".\n");
				t_fieldlist.append(" */\n");
				t_fieldlist.append(" public " + t_datatype +" get" + tt_name + "() {\n");
				t_fieldlist.append("    return " + t_name + ";\n");
				t_fieldlist.append(" }\n\n");
				t_fieldlist.append(" public void set" + tt_name + "(" + t_datatype + " " + t_name + ") {\n");
				t_fieldlist.append("    this." + t_name + " = " + t_name + ";\n");
				t_fieldlist.append("  }\n\n");
			}
		}
		if(t_fieldlist.toString().equals("")){
			throw new Exception("not found infomation in ta07_formfield ,module_id="+ta06_id);
		}
		t_modelContent = t_modelContent.replace("$field_list", t_fieldlist.toString());
		
		/*
		 * 生成文件
		 */
		t_container.delete(0, t_container.length());
		t_container.append(t_webRoot);
		t_container.append(System.getProperty("file.separator"));
		t_container.append("WEB-INF");
		t_container.append(System.getProperty("file.separator"));
		t_container.append("classes");
		t_container.append(System.getProperty("file.separator"));
		t_container.append(t_extDataObjectPackage.replaceAll("\\.", "/"));
		t_container.append(System.getProperty("file.separator"));
		t_container.append(tableName+".java");
		FileWriter out = new FileWriter(t_container.toString());
		out.write(t_modelContent);
		out.close();
		
		/*
		 * 记录日志文件
		 */
		Map<String,Object> log_map = new HashMap<String,Object>();
		log_map.put("module_id", ta06_id);
		log_map.put("object_name", objName);
		log_map.put("desc_name", objDesc);
		log_map.put("create_java", "success");
		//recordLog(log_map);
		
		String java_home = "D:\\Java\\jdk1.5.0_22";
		//String isJavac = convertUtil.toString(paramMap.get("isJavac"));
		String isJavac = "yes";
		if(isJavac.toLowerCase().equals("yes")){
			
			StringBuffer shellCommand = new StringBuffer(java_home);
			shellCommand.append(System.getProperty("file.separator"));
			shellCommand.append("bin");
			shellCommand.append(System.getProperty("file.separator"));
			shellCommand.append("javac -d ");
			shellCommand.append(t_webRoot);
			shellCommand.append(System.getProperty("file.separator"));
			shellCommand.append("WEB-INF");
			shellCommand.append(System.getProperty("file.separator"));
			shellCommand.append("classes ");
			shellCommand.append(t_container.toString());
			shellCommand.append(" -encoding GBK");
			
			try{
				Process process = Runtime.getRuntime().exec(shellCommand.toString());
				int exitVal = process.waitFor();
				/*
				 * 执行结果 0 成功，1失败
				 */
				if(exitVal == 0){
					/*
					 * 记录日志文件
					 */
					log_map.clear();
					log_map.put("module_id", ta06_id);
					log_map.put("object_name", objName);
					log_map.put("desc_name", objDesc);
					log_map.put("create_class", "success");
					//recordLog(log_map);
				}
			}
			catch(Exception e){
				throw new Exception("持久化对象编译失败，命令如下： 【" + shellCommand.toString() + "】");
			}
		}
	}
	
	/**
	 * @desc create hibernate configure file[java.hbm] from dataObjects
	 */
	public void java2Xml(Map<String, Object> paramMap) throws Exception{
		
		QueryBuilder queryBuilder = null;
		Long ta06_id = (Long)paramMap.get("ta06_id");
		if(ta06_id == null){
			throw new Exception("no ta06 params");
		}
		String t_webRoot = convertUtil.toString(paramMap.get("webRoot"));
		String t_extDataObjectPackage = convertUtil.toString(paramMap.get("extDataObjectPackage"));

		/*
		 * 获得WEB根目录和生成文件存放位置
		 */
		if(t_webRoot.equals("") || t_extDataObjectPackage.equals("")){
		
			Map<String,String> cfgMap = SharedCfgData.getMap();
			String auto_cfg_uri = cfgMap.get("baseConfig.properties");
			OperProperties op = new OperProperties();
			op.load(auto_cfg_uri);
			t_webRoot = op.getValue("webRoot");
			if(t_webRoot == null || t_webRoot.equals("")){
				throw new Exception("not found value [webRoot=XXX] in autoConfig/baseConfig.properties");
			}
			
			t_extDataObjectPackage = op.getValue("extDataObjectPackage");
			if(t_extDataObjectPackage == null || t_extDataObjectPackage.equals("")){
				throw new Exception("not found value [extDataObjectPackage=XXX] in autoConfig/baseConfig.properties");
			}
		}
		/*
		 * 获得持久化对象配置文件模板
		 */
		StringBuffer t_container  = new StringBuffer(t_webRoot);
		t_container.append(System.getProperty("file.separator"));
		t_container.append("WEB-INF");
		t_container.append(System.getProperty("file.separator"));
		t_container.append("extConfig");
		t_container.append(System.getProperty("file.separator"));
		t_container.append("createDoModelXml.model");
		String t_modelContent = getModelContent(t_container.toString());
		if(t_modelContent == null){
			throw new Exception("not found DataObjects model[extConfig/createDoModel.model]");
		}
		/*
		 * 替换模板中的内容[$obj_name,$sequence,$field_list]
		 */
		
		/*
		 * 根据TA06获得类名
		 */
		Ta06_module ta06 = (Ta06_module)queryService.searchById(Ta06_module.class, ta06_id);
		if(ta06 == null){
			throw new Exception("not found infomation in ta06_module ,ta06_id="+ta06_id);
		}
		String objName = null;
		String tableName = null;
		String objDesc = null;
		if(ta06 != null){
			objName = convertUtil.toString(ta06.getForm_table());
			objDesc = convertUtil.toString(ta06.getName());
			if(objName == null){
				throw new Exception("not found Object name[form_table] in ta06_module,ta06_id="+ta06_id);
			}
			tableName = objName.substring(objName.lastIndexOf(".") + 1 , objName.length());
			tableName = tableName.toUpperCase().substring(0,1) + tableName.toLowerCase().substring(1,tableName.length());
			t_modelContent = t_modelContent.replaceAll("\\$obj_name", objName);
			t_modelContent = t_modelContent.replaceAll("\\$tableName", tableName);
			t_modelContent = t_modelContent.replaceAll("\\$sequence", objName+"_num");
		}

		/*
		 * 根据TA07获得字段名
		 */
		StringBuffer t_fieldlist = new StringBuffer("");
		queryBuilder = new HibernateQueryBuilder(Ta07_formfield.class);
		queryBuilder.eq("module_id", ta06_id);
		ResultObject ro = queryService.search(queryBuilder);
		while(ro.next()){
			Ta07_formfield ta07 = (Ta07_formfield)ro.get(Ta07_formfield.class.getName());
			String t_name = convertUtil.toString(ta07.getName());
			String t_datatype = convertUtil.toString(ta07.getDatatype());
			if(t_datatype.toLowerCase().indexOf("date") != -1){
				t_datatype = "java.util.Date";
			}
			else if(t_datatype.toLowerCase().indexOf("number") != -1){
				t_datatype = "java.lang.Long";
			}
			else{
				t_datatype = "java.lang.String";
			}
			
			if(!t_name.equals("") || !t_name.toLowerCase().equals("id")){
			
				t_fieldlist.append("  <property\n");
				t_fieldlist.append("  name=\"" + t_name + "\"\n");
				t_fieldlist.append("  type=\"" + t_datatype + "\"\n");
				t_fieldlist.append("  update=\"true\"\n");
				t_fieldlist.append("  insert=\"true\"\n");
				t_fieldlist.append("  column=\"" + t_name + "\"\n");
				t_fieldlist.append("  />\n\n");
			}
		}
		if(t_fieldlist.toString().equals("")){
			throw new Exception("not found infomation in ta07_formfield ,module_id="+ta06_id);
		}
		t_modelContent = t_modelContent.replace("$field_list", t_fieldlist.toString());
		
		/*
		 * 生成文件
		 */
		t_container.delete(0, t_container.length());
		t_container.append(t_webRoot);
		t_container.append(System.getProperty("file.separator"));
		t_container.append("WEB-INF");
		t_container.append(System.getProperty("file.separator"));
		t_container.append("classes");
		t_container.append(System.getProperty("file.separator"));
		t_container.append(t_extDataObjectPackage.replaceAll("\\.", "/"));
		t_container.append(System.getProperty("file.separator"));
		t_container.append(tableName + ".hbm.xml");
		FileWriter out = new FileWriter(t_container.toString());
		out.write(t_modelContent);
		out.close();
		
		/*
		 * 记录日志文件
		 */
		Map<String,Object> log_map = new HashMap<String,Object>();
		log_map.put("module_id", ta06_id);
		log_map.put("object_name", objName);
		log_map.put("desc_name", objDesc);
		log_map.put("create_hbm_xml", "success");
		//recordLog(log_map);
	}
	
	/**
	 * @desc create hibernate configure file[java.hbm] from dataObjects
	 */
	public void java2Class(Map<String, Object> paramMap) throws Exception{
		
	}
	public static String getModelContent(String pathname) {
		StringBuffer modelContent = new StringBuffer();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(pathname), "UTF-8"));
			String line = "";
			try {
				while ((line = br.readLine()) != null) {
					modelContent.append(line + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return modelContent.toString();
	}
	
	/*
	public void recordLog(Map<String,Object> paramMap)throws Exception{
		
		QueryBuilder queryBuilder = null;
		ResultObject ro = null;
		
		Long module_id = convertUtil.toLong(paramMap.get("module_id"));
		String object_name = convertUtil.toString(paramMap.get("object_name"));
		String desc_name = convertUtil.toString(paramMap.get("desc_name"));
		String create_table = convertUtil.toString(paramMap.get("create_table"));
		String create_java = convertUtil.toString(paramMap.get("create_java"));
		String create_hbm_xml = convertUtil.toString(paramMap.get("create_hbm_xml"));
		String create_class = convertUtil.toString(paramMap.get("create_class"));
		
		 //记录日志文件
		 
		Tz06_ext_log tz06 = null;
		queryBuilder = new HibernateQueryBuilder(Tz06_ext_log.class);
		queryBuilder.eq("module_id", module_id);
		ro = queryService.search(queryBuilder);
		if(ro.next()){
			tz06 = (Tz06_ext_log)ro.get(Tz06_ext_log.class.getName());
			if(!create_table.equals("")){
				PropertyInject.setProperty(tz06, "create_table", create_table);
			}
			if(!create_java.equals("")){
				PropertyInject.setProperty(tz06, "create_java", create_java);
			}
			if(!create_hbm_xml.equals("")){
				PropertyInject.setProperty(tz06, "create_hbm_xml", create_hbm_xml);
			}
			if(!create_class.equals("")){
				PropertyInject.setProperty(tz06, "create_class", create_class);
			}
		}
		if(tz06 == null){
			tz06 = new Tz06_ext_log();
			tz06.setModule_id(module_id);
			tz06.setCreate_hbm_xml(create_hbm_xml);
			tz06.setCreate_table(create_table);
			tz06.setCreate_java(create_java);
			tz06.setCreate_class(create_class);
			tz06.setObject_name(object_name);
			tz06.setDesc_name(desc_name);
			tz06.setOper_date(new Date());
		}
		saveService.save(tz06);
	} 
*/
}
