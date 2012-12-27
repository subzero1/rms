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
		<table width="780" class="report" layouth="38" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;">
			<thead>
				<tr>
					<th style="width:100px;">考核项</th>
					<th style="width:300px;">评估内容</th>
					<th style="width:80px;">最高分值</th>
					<th style="width:300px;">考核办法</th>
				</tr>
			</thead>
			<tbody id="pfbody">
				<c:forEach var="obj" items="${khxList}">
				<tr>
					<td>${obj.khx }</td>
					<td><textarea style="width:295px;overflow-y:hidden;border:0px;background-color:#fff;" readOnly onpropertychange='this.style.posHeight=this.scrollHeight' onfocus='textarea.style.posHeight=this.scrollHeight'>${obj.pgnr }</textarea></td>
					<td>${obj.zgfz }</td>
					<td><textarea style="width:295px;overflow-y:hidden;border:0px;background-color:#fff;" readOnly onpropertychange='this.style.posHeight=this.scrollHeight' onfocus='textarea.style.posHeight=this.scrollHeight'>${obj.pgbf }</textarea></td>
				</tr>
				</c:forEach>
				<tr style="height:60px;">
					<td>备 注</td>
					<td colspan="3"><textarea style="width:650px;overflow-y:hidden;border:0px;background-color:#fff;" readOnly onpropertychange='this.style.posHeight=this.scrollHeight' onfocus='textarea.style.posHeight=this.scrollHeight'>${tc10.bz }</textarea></td>
				</tr>
			</tbody>
		</table>	
	</div>
</div>
