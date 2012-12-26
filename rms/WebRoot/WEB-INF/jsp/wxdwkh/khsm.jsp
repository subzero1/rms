<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<div class="page">
	<div class="pageHeader">
		<span style="font-size:14px;font-weight:bold;">${tc10.mc }</span>
	</div>
	<div class="pageContent">	
		<table width="580" class="report" layouth="38" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;">
			<thead>
				<tr>
					<th style="width:100px;">考核项</th>
					<th style="width:300px;">评估内容</th>
					<th style="width:80px;">最高分值</th>
					<th style="width:100px;">考核办法</th>
				</tr>
			</thead>
			<tbody id="pfbody">
				<c:forEach var="obj" items="${khxList}">
				<tr>
					<td>${obj.khx }</td>
					<td>${obj.pgnr }</td>
					<td>${obj.zgfz }</td>
					<td>${obj.pgbf }</td>
				</tr>
				</c:forEach>
				<tr style="background-color:#F0EFF0">
					<td style="text-align:center;">备 注</td>
					<td colspan="3">${tc10.bz }</td>
				</tr>
			</tbody>
		</table>	
	</div>
</div>
