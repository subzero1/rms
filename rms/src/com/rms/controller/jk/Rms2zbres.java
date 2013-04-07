package com.rms.controller.jk;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

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
import com.rms.webservice.client.zbres.ResComServiceCallbackHandler;
import com.rms.webservice.client.zbres.ResComServiceStub;

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
			String xmlx = convertUtil.toString(td01.getXmlx(),"FTTH");
			String xmlb = convertUtil.toString(td01.getGclb());
			String ssdq = convertUtil.toString(td01.getSsdq());
			String xmgly = convertUtil.toString(td01.getXmgly());
			String sgdw = convertUtil.toString(td01.getSgdw());
			String sgfzr = convertUtil.toString(td01.getSgfzr());
			String sgfzrdh = convertUtil.toString(td01.getSgfzrdh());
			String xmsm = convertUtil.toString(td01.getXmsm());
			String fjdz = "";

//			String classpath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
//			String classname = this.getClass().getSimpleName();
//			classpath = classpath.replaceAll(classname + ".class", "rms2zbres.xml");
//			String src_xml_doc = XmlTool.getXmlModule(classpath);
//
//			/*
//			 * 生成远程WEBSERVICE入参
//			 * 替换模板内容中的参数
//			 */
//			String inParam = src_xml_doc;
//			inParam.replace("[xmbh]", xmbh);
//			inParam.replace("[xmmc]", xmmc);
//			inParam.replace("[xmlx]", xmlx);
//			inParam.replace("[xmlb]", xmlb);
//			inParam.replace("[ssdq]", ssdq);
//			inParam.replace("[xmgly]", xmgly);
//			inParam.replace("[sgdw]", sgdw);
//			inParam.replace("[sgfzr]", sgfzr);
//			inParam.replace("[sgfzrdh]", sgfzrdh);
//			inParam.replace("[xmsm]", xmsm);
//			inParam.replace("[fjdz]", fjdz);
			
			/*
			 * 调用远程WEBSERVICE接口，获得出参
			 */
			//1	电缆  2	管道    3	市政   4	户线   5	光缆  6	传输  7	交换  8	数据      9	多媒体      10	平台    11	无线   12	能源
			Map gclbMap = new HashMap();
			gclbMap.put("电缆", "1");
			gclbMap.put("管道", "2");
			gclbMap.put("市政", "3");
			gclbMap.put("户线", "4");
			gclbMap.put("光缆", "5");
			gclbMap.put("传输", "6");
			gclbMap.put("交换", "7");
			gclbMap.put("数据", "8");
			gclbMap.put("多媒体", "9");
			gclbMap.put("平台", "10");
			gclbMap.put("无线", "11");
			gclbMap.put("能源", "12");
			
			//1	鼓楼区2	白下区3	玄武区4	秦淮区5	建邺区6	下关区7	雨花台区8	栖霞区10	浦口区9	江宁区11	六合区12	高淳县13	溧水县
			Map ssdqMap = new HashMap();
			ssdqMap.put("鼓楼区", "1");
			ssdqMap.put("白下区", "2");
			ssdqMap.put("玄武区", "3");
			ssdqMap.put("秦淮区", "4");
			ssdqMap.put("建邺区", "5");
			ssdqMap.put("下关区", "6");
			ssdqMap.put("雨花台区", "7");
			ssdqMap.put("栖霞区", "8");
			ssdqMap.put("浦口区", "9");
			ssdqMap.put("江宁区", "10");
			ssdqMap.put("六合区", "11");
			ssdqMap.put("高淳县", "12");
			ssdqMap.put("溧水县", "13");
			
			ResComServiceStub rs = new ResComServiceStub();
			ResComServiceStub.InsertResCom rsirc = new ResComServiceStub.InsertResCom();
			rsirc.setIn0(xmbh);//项目编号
			rsirc.setIn1(convertUtil.toString(xmmc,"无"));//项目名称
			rsirc.setIn2(convertUtil.toString(xmlx,"FTTH"));//工程类型
			rsirc.setIn3(convertUtil.toString(gclbMap.get(xmlb),"-1"));//工程种类
			rsirc.setIn4(convertUtil.toString(ssdqMap.get(ssdq),"-1"));//工程区域
			rsirc.setIn5(convertUtil.toString(xmgly,"无"));//工程管理人（项目管理员）
			rsirc.setIn6(convertUtil.toString(sgdw,"无"));//施工单位名称
			rsirc.setIn7(convertUtil.toString(sgfzr,"无"));//施工人员
			rsirc.setIn8(convertUtil.toString(sgfzrdh,"025-"));//施工电话
			rsirc.setIn9(convertUtil.toString(xmsm,"无"));//备注（项目描述）
			ResComServiceStub.InsertResComResponse rsircr = rs.insertResCom(rsirc);
			String outParam = rsircr.toString();
			
//			SAXBuilder builder = null;
//			Document doc = null;
//			Element root = null;
//			builder = new SAXBuilder();
//			Reader in = new StringReader(outParam);
//			try {
//				doc = builder.build(in);
//			} catch (Exception e) {
//				throw new Exception(e);
//			} 
//			in.close();
//			
//			root = doc.getRootElement();
//			Element retCode = root.getChild("retCode");
//			if(retCode == null){
//				throw new Exception("not find retCode node in outParam");
//			}
//			Element retDesc = root.getChild("retDesc");
//			if(retDesc == null){
//				throw new Exception("not find retDesc node in outParam");
//			}
//			String returnCode = convertUtil.toString(retCode.getText());
//			String returnDesc = convertUtil.toString(retDesc.getText());
			
			String returnCode = "0";
			String returnDesc = "111";
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
			String msg = convertUtil.toString(e.getMessage());
			if(msg.length() > 10){
				msg = "发送失败，请联系管理员";
			}
			
			response.getWriter().print("{\"statusCode\":\"300\", \"message\":\""+msg+"\"}");
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
