package com.rms.controller.form;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Te02_jlfk;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.netsky.base.utils.DateFormatUtil;
import com.netsky.base.utils.StringFormatUtil;

@Controller
public class SaveFormAux {
	/**
	 * 保存服务
	 */
	@Autowired
	private SaveService saveService;
	
	@Autowired
	private QueryService queryservice;
	
	@Autowired
	private ExceptionService exceptionService;
	
	
	/**
	 * 利用AJAX保存交流反馈意见
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/jlfk.do")
	public ModelAndView jlfk (HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		Long project_id = null; 
		Long module_id = null;
		Long document_id = null;
		Long user_id = null;
		String yj = null;
		try{
			project_id = convertUtil.toLong(request.getParameter("project_id"), -1L);
			module_id = convertUtil.toLong(request.getParameter("module_id"), -1L);
			document_id = convertUtil.toLong(request.getParameter("document_id"), -1L);
			user_id = convertUtil.toLong(request.getParameter("user_id"), -1L);
			yj = StringFormatUtil.format(request.getParameter("yj"), "-1");
			Ta03_user user = (Ta03_user)(request.getSession().getAttribute("user"));
			
			//yj = new String(yj.getBytes("utf-8"), "utf-8");
			
			Date time = new Date();
			Te02_jlfk te02 = new Te02_jlfk();
			te02.setProject_id(project_id);
			te02.setModule_id(module_id);
			te02.setDocument_id(document_id);
			te02.setTime(time);
			te02.setYj(yj);
			te02.setUser_id(user_id);

			saveService.save(te02);

			/*
			out.println("<dl class=\"cat\">");
			out.println("<dt>"+user.getName()+"  "+DateFormatUtil.Format(time,"yyyy-MM-dd HH:mm")+"</dt>");
			out.println("<dd>"+yj+"</dd>");
			out.println("</dl>");
			*/
			out.println("<tr>");
			out.println("<td class=\"hf-msg\">"+user.getName()+"  &nbsp;"+DateFormatUtil.Format(time,"yyyy-MM-dd HH:mm") + "</td>");
			out.println("</tr>");
			out.println("<tr><td>"+yj+"</td></tr>");
			
			
			out.flush();
			out.close();
		}catch (Exception e){
			System.out.println(e);	
			return exceptionService.exceptionControl(this.getClass().getName(), "表单列表错误", e);
		}
		return null;	
	}
	
}

