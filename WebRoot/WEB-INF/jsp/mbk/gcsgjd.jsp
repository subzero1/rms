<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />
<style>
	#printTable tr{
		
	}
</style>
 <form id="pagerForm" method="post" action="mbk/gcsgjdForMbk.do">
	<input type="hidden" name="zymc" value="${param.zymc}">
	<input type="hidden" name="ssdq" value="${param.ssdq}">
	<input type="hidden" name="lb" value="${param.lb}">
	<input type="hidden" name="zt" value="${param.zt}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>
<div class="page">

	<div class="pageContent" layouth="48" style="overflow-x:hidden;">
		
		<table class="table" width="100%">
		<thead>
			<tr>
				<th style="width: 30px;">序号</th>
				<th style="width: 30px;" orderField="jdx_name">进度项</th>
				<th style="width: 30px;" orderField="jdx_status">状态</th> 
			</tr>
		</thead>
		<tbody>
		<c:set var="offset" value="0"/>
			<c:forEach items="${objList}" var="obj">
			<c:set var="offset" value="${offset+1}"/>
				<tr>
					<td>${offset }</td>
					<td>${obj[0] }</td>
					<td>${obj[1]}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>	 
	
</div>
 

