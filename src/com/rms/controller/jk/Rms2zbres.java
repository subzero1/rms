package com.rms.controller.jk;

import java.io.Reader;
import java.io.StringReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.utils.convertUtil;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.XmlTool;
import com.rms.dataObjects.form.Td01_xmxx;

@Controller
public class Rms2zbres {

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

	/**
	 * 打包立项选择工程保存
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param ModelAndView
	 */
	@RequestMapping("/jk/yssq.do")
	public void saveXzgcForDblx(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		response.setCharacterEncoding(request.getCharacterEncoding());
		Long project_id = convertUtil.toLong(request.getParameter("project_id"), -1L);

		try {
			// 获取项目信息
			Td01_xmxx td01 = (Td01_xmxx)queryService.searchById(Td01_xmxx.class, project_id);
			String xmbh = convertUtil.toString(td01.getXmbh());
			String xmmc = convertUtil.toString(td01.getXmmc());
			String xmlx = convertUtil.toString(td01.getXmlx());
			String xmlb = convertUtil.toString(td01.getGclb());
			String ssdq = convertUtil.toString(td01.getSsdq());
			String xmgly = convertUtil.toString(td01.getXmgly());
			String sgdw = convertUtil.toString(td01.getSgdw());
			String sgfzr = convertUtil.toString(td01.getSgfzr());
			String sgfzrdh = convertUtil.toString(td01.getSgfzrdh());
			String xmsm = convertUtil.toString(td01.getXmsm());
			String fjdz = "";

			String classpath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
			String classname = this.getClass().getSimpleName();
			classpath = classpath.replaceAll(classname + ".class", "rms2zbres.xml");
			String src_xml_doc = XmlTool.getXmlModule(classpath);

			/*
			 * 生成远程WEBSERVICE入参
			 * 替换模板内容中的参数
			 */
			String inParam = src_xml_doc;
			inParam.replace("[xmbh]", xmbh);
			inParam.replace("[xmmc]", xmmc);
			inParam.replace("[xmlx]", xmlx);
			inParam.replace("[xmlb]", xmlb);
			inParam.replace("[ssdq]", ssdq);
			inParam.replace("[xmgly]", xmgly);
			inParam.replace("[sgdw]", sgdw);
			inParam.replace("[sgfzr]", sgfzr);
			inParam.replace("[sgfzrdh]", sgfzrdh);
			inParam.replace("[xmsm]", xmsm);
			inParam.replace("[fjdz]", fjdz);
			
			/*
			 * 调用远程WEBSERVICE接口，获得出参
			 */
			String outParam = "";
			SAXBuilder builder = null;
			Document doc = null;
			Element root = null;
			builder = new SAXBuilder();
			Reader in = new StringReader(outParam);
			try {
				doc = builder.build(in);
			} catch (Exception e) {
				throw new Exception(e);
			} 
			in.close();
			
			root = doc.getRootElement();
			Element retCode = root.getChild("retCode");
			if(retCode == null){
				throw new Exception("not find retCode node in outParam");
			}
			Element retDesc = root.getChild("retDesc");
			if(retDesc == null){
				throw new Exception("not find retDesc node in outParam");
			}
			String returnCode = convertUtil.toString(retCode.getText());
			String returnDesc = convertUtil.toString(retDesc.getText());
			
			if(returnCode.equals("0")){
				td01.setYscs(convertUtil.toLong(td01.getYscs(),0L) + 1);//验收次数加1
				td01.setXmzt("验收申请");
				saveService.save(td01);
				response.getWriter().print("{\"statusCode\":\"200\", \"message\":\"发送成功\", \"navTabId\":\"autoform101"
						+ project_id
						+ "\",\"forwardUrl\":\"\", \"callbackType\":\"\"}");
			}
			else{
				throw new Exception(returnDesc);
			}
			
		} catch (Exception e) {
			log.error("jk/yssq.do[com.rms.controller.jk.Rms2zbres]" + e.getMessage() + e);
			response.getWriter().print("{\"statusCode\":\"300\", \"message\":\"操作失败:"+e.getMessage()+"\"}");
		}
	}

	/**
	 * 打包立项选择工程检测（工程类别、施工单位）
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param ModelAndView
	 */
//	@RequestMapping("/form/chkXzgcForDblx.do")
//	public ModelAndView chkXzgcForDblx(HttpServletRequest request,
//			HttpServletResponse response, HttpSession session) throws Exception {
//		response.setCharacterEncoding(request.getCharacterEncoding());
//		String[] groups = request.getParameterValues("t_group");
//		String json = "{\"statusCode\":\"200\", \"message\":\"成功\"}";
//
//		try {
//			// 获取岗位的对象
//			StringBuffer sql = new StringBuffer();
//			String ids = "";
//			// 对配置的角色进行保存
//			if (groups != null) {
//				for (int i = 0; i < groups.length; i++) {
//					ids += groups[i] + ",";
//				}
//				ids += "-1";
//			}
//
//			/*
//			 * 判断工程类别是否不一致
//			 */
//			sql.delete(0, sql.length());
//			sql.append("select distinct gclb from Td00_gcxx where id in (");
//			sql.append(ids);
//			sql.append(")");
//			List list = queryService.searchList(sql.toString());
//			if (list != null && list.size() > 1) {
//				json = "{\"statusCode\":\"300\", \"message\":\"以上工程【工程类别】不一致，打包失败\"}";
//			}
//
//			/*
//			 * 判断施工单位是否不一致
//			 */
//			sql.delete(0, sql.length());
//			sql.append("select distinct sgdw from Td00_gcxx where id in (");
//			sql.append(ids);
//			sql.append(") and sgdw is not null ");
//			list = queryService.searchList(sql.toString());
//			if (list != null && list.size() > 1) {
//				json = "{\"statusCode\":\"300\", \"message\":\"以上工程【施工单位】不一致，打包失败\"}";
//			}
//
//			response.getWriter().print(json);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.error("chkXzgcForDblx.do[com.rms.controller.form.AuxFunction]"
//					+ e.getMessage() + e);
//			response.getWriter().print(
//					"{\"statusCode\":\"300\", \"message\":\"操作失败\"}");
//		}
//		return null;
//	}

	
}
