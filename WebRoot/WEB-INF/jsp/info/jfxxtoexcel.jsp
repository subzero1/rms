<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<div>
	<form method="post" action="info/jfxxtoexcel.do">
	<p><label>导出类型</label>
	<input name="excelversion" type="radio" value="97-03" checked="checked"/>97-03EXCEL文档
	  <input name="excelversion" type="radio" value="07" />07EXCEL文档
	</p>
	<input type="hidden" name="ssqy" value="${param.ssqy }" />
	<input type="hidden" name="jdxz" value="${param.jdxz }" />
	<input type="hidden" name="keywords" value="${param.keywords }" />
	<input type="submit" value="导出"/>
	</form>
</div>
