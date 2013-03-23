package com.rms.controller.jk;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.w3c.dom.*;

import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Tb03_relation;
import com.netsky.base.dataObjects.Tb12_opernode;
import com.netsky.base.flow.component.Send;
import com.netsky.base.flow.service.FlowService;
import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveFormCodeService;
import com.netsky.base.service.SaveService; 
import com.rms.dataObjects.jk.Ti03_xqly;
import com.rms.dataObjects.form.Td00_gcxx;
import com.rms.serviceImpl.jk.CcatsidaWSC;
import com.rms.serviceImpl.jk.CcatsidaXMLParser;

/**
 * @description: PSS与综合调度中心接口
 * @class name:com.netsky.pss.controller.jk.PssCcatsida
 * @author Administrator May 27, 2010
 */
@Controller
public class RmsCcatsida {

	/**
	 * 数据对应操作类
	 */
	@Autowired
	private SaveService saveService;

	@Autowired
	private QueryService queryService;

	/**
	 * 流程服务
	 */
	@Autowired
	private FlowService flowService;

	@Autowired
	private Send send;

	/**
	 * 创建表单编号服务
	 */
	@Autowired
	SaveFormCodeService saveFormCodeService;

	/**
	 * 日志处理类
	 */
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * 接口处理方法
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws UnsupportedEncodingException
	 *             void
	 */
	@RequestMapping("/jk/ccatsida_rms.do")
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html");
		response.setCharacterEncoding("GBK");
		String projectcode = request.getParameter("bh");
		String target = request.getParameter("target");
		PrintWriter out = response.getWriter();
		
		/**
		 * 判断当前工单是否已新建工程
		 */
		Long project_id = 0L;
		List tmpList = null;

		tmpList = queryService.searchList("select 'x' from Ti03_xqly where xqbs ='" + projectcode + "'");
		if (tmpList.size() > 0) {
			out.println("<script language=\"javascript\">");
			out.println("alert(\"工单已发送！\");");
			out.println("window.opener=window.self;");
			out.println("window.close();");
			out.println("</script>");
			return;
		}
		/**
		 * 获得接口数据
		 */
		Element project = null;
		Element projectrow = null;

		try {
			CcatsidaWSC wsc = new CcatsidaWSC();
			CcatsidaXMLParser parser = new CcatsidaXMLParser();
			project = parser.parseProject(wsc.processRequest("1", projectcode, target));
			projectrow = (Element) project.getElementsByTagName("ROW").item(0);
			log.error("工单接口调试："+projectrow.toString()+"^^^^^^^^^^^^^^^^^^^^^^^^^^");
		} catch (Exception e) {
			log.error("获取工单数据失败！工单号＝" + projectcode, e);
		}

		if (projectrow == null) {
			// 发送失败
			out.println("<script language=\"javascript\">");
			out.println("alert(\"工单发送失败，请重发！\");");
			out.println("window.opener=window.self;");
			out.println("window.close();");
			out.println("</script>");
			return;
		}

		/**
		 * 创建工程
		 */
		try {

			Session session = null; // 事务相关
			Transaction tx = null;// 事务相关
			Td00_gcxx td00 = null;

			try {
				session = saveService.getHiberbateSession();
				tx = session.beginTransaction();
				tx.begin(); // 开始事务
				System.out.println(convertUtil.toString(projectrow.getAttribute("CUSTOMERMANAGENAME")));
				/**
				 * 获取客户端提出数据。构建td00 并保存到数据库中
				 */
				
				td00 = new Td00_gcxx();
				td00.setGcbh(projectcode);
				/*
				td00.setGcmc(gcmc);
				td00.setSsdq(ssdq);
				td00.setGclb(gclb);
				td00.setGcsm(gcsm);
				td00.setCjr(cjr);
				td00.setCjrq(cjrq);
				td00.setXmgly(xmgly);
				td00.setXqwcsj(xqwcsj);
				saveService.save(td00);
				
				td11 = new Td11_xqs();

				td11.setFwbm("[苏州市]客户响应中心");
				td11.setCjr(convertUtil.toString(projectrow.getAttribute("CUSTOMERMANAGENAME")));// 项目经理
				td11.setCjrdh(convertUtil.toString(projectrow.getAttribute("CUSTOMERMANAGERPHONE")));// 项目经理电话
				td11.setCjrq(new Date());
				td11.setGcmc("[响][" + projectcode.substring(projectcode.length() - 5) + "]"+convertUtil.toString(projectrow.getAttribute("CUSTOMERNAMEA")));// 工程名称
				td00.setGcmc(td11.getGcmc());
				td00.setXqtcsj(new Date());
				td11.setJsdd(convertUtil.toString(projectrow.getAttribute("INSTALLADDRESSA"))); // 建设地点
				td00.setJsdd(td11.getJsdd());
				td11.setSsdq("苏州市");
				td11.setTzdq("苏州市");
				td00.setSsdq(td11.getSsdq());
				td00.setTzdq(td11.getTzdq());
				*/
				

//				td11.setYwxq(convertUtil.toString(projectrow.getAttribute("CUSTOMERMANAGERREMARK")) + "\n"
//						+ "本需求来自综合调度系统，定单号：" + projectcode); // 工程说明
//				td00.setYwxqdl(td11.getYwxqdl());
//				td00.setYwxqxl(td11.getYwxqxl());
//				td00.setGcsm(td11.getYwxq());
//
//				//设置工程状态
//				td00.setGczt("需求提出");
//				td00.setGcjd("需求阶段");
//				
//				saveService.save(td00);
//				td11.setProject_id(td00.getId());
//				saveService.save(td11);
//
				Ti03_xqly ti03 = new Ti03_xqly();
				ti03.setXqbs(projectcode);
				ti03.setProject_id(td00.getId());
				if (target != null && target.length() > 8) {
					ti03.setBz("综合调度系统定单");
					ti03.setLyxt("江苏省综合调度系统");
					ti03.setUrl("http://132.228.176.109/IOMPROJ/yccustorder/szOrderDetailJudge.htm?order_code="
							+ projectcode);
				} else {
					ti03.setBz("来源苏州市综合调度系统,定单号：" + projectcode);
					ti03.setLyxt("苏州市综合调度系统");
					ti03.setUrl("http://132.232.112.12/ccatsida/order/orderDetails.do?operID=sa&hashCode=C2F9D3C094DF274AA8084EC30772A717&i_vBusinessID="
									+ projectcode);
				}
				saveService.save(ti03);

				session.flush();
				tx.commit(); // 提交事务;
			} catch (Exception e) {
				if (tx != null) {
					session.flush();
					tx.rollback(); // 回滚事务;
				}
				log.error("综合调度接口，创建工程失败！", e);
				// 发送失败
				out.println("<script language=\"javascript\">");
				out.println("alert(\"工单发送失败，请重发！\");");
				out.println("window.opener=window.self;");
				out.println("window.close();");
				out.println("</script>");
				return;
			} finally {
				if (session != null) {
					session.close();
				}
			}


			// 发送成功
			out.println("<script language=\"javascript\">");
			out.println("alert(\"工单发送成功！\");");
			out.println("window.opener=window.self;");
			out.println("window.close();");
			out.println("</script>");
		} catch (Exception ex) {
			// 发送失败
			out.println("<script language=\"javascript\">");
			out.println("alert(\"工单发送失败，请重发！\");");
			out.println("window.opener=window.self;");
			out.println("window.close();");
			out.println("</script>");
		}
	}
}
