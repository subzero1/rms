package com.jfms.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.jfms.thread.ChangeDrawing_Thread;

/**
 * 线程监听器
 * 
 * @author lee.xiangyu
 * 
 */
public class Thread_Listener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
		
		/*
		 * 更换现状图线程
		 */
		ChangeDrawing_Thread cd_Thread = (ChangeDrawing_Thread) ctx.getBean("cd_thread");
		if (cd_Thread != null) {
			Thread th = new Thread(cd_Thread);
			th.start();
		}
	}
}
