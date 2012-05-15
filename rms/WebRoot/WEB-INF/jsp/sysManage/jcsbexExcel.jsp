<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<div>
	<form method="post" action="sysManage/jcsbList.do?toexcel=yes">
	<p><label>导出类型</label>
	<input name="excelversion" type="radio" value="97-03" checked="checked"/>97-03EXCEL文档
	  <input name="excelversion" type="radio" value="07" />07EXCEL文档
	</p>
	<input type="hidden" name="keyword" value="<%=URLDecoder.decode(request.getParameter("keyword")) %>" />
	<input type="submit" value="导出"/>
	</form>
</div>
