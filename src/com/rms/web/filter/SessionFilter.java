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
		String[] notFilter = new String[] { "index.jsp","login.do"};
		String[] filters =new String[]{"wxdw","search","Manage","workList.do","htgl","download.do"};
		boolean b_filter=false;
		
		for (String string : filters) {
			if (uri.indexOf(string)!=-1) {
				b_filter=true;
				break;
			}
		}
		if (b_filter) { 
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
					request.setCharacterEncoding("UTF-8");
					response.setCharacterEncoding("UTF-8");
					PrintWriter out = response.getWriter();
					String loginPage = "";
					StringBuilder builder = new StringBuilder();
					builder.append("<script type=\"text/javascript\">");
					builder.append("alert('网页已经过期，请您重新登录！');");
					builder.append("window.top.location.href='");
					builder.append(loginPage);
					builder.append("';");
					builder.append("</script>");
					out.print(builder.toString());
				} else {
					filterChain.doFilter(request, response);
				}
			} else {
				filterChain.doFilter(request, response);
			}
		} else {
			filterChain.doFilter(request, response);
		}
	}

}
