package com.rms.web.filter;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class SessionFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String[] notFilter = new String[] {"login.do",
				"logout.do","download.do" };
		boolean doFilter = true;
		for (String s : notFilter) {
			if (uri.indexOf(s) != -1) {
				doFilter = false;
				break;
			}
		} 
		if (doFilter) {
			Object obj = request.getSession().getAttribute("user");
			if (null == obj) {
				this.doAction(request, response);
			} else {
				filterChain.doFilter(request, response);
			}
		} else {
			filterChain.doFilter(request, response); 
		} 
	}

	/**
	 * 
	 * @return String
	 * @throws IOException
	 */
	private void doAction(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		StringBuilder builder = new StringBuilder();
		String loginPage = "";
		builder.append("<script type=\"text/javascript\">");
		builder.append("alert('网页已经过期，请您重新登录！');");
		builder.append("window.top.location.href='");
		builder.append(loginPage);
		builder.append("';");
		builder.append("</script>");
		out.print(builder.toString());
	} 

}
