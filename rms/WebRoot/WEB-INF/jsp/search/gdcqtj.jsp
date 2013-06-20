<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<div class="page">
 <div class="pageContent" style="overflow: scroll;">
	<div class="panelBar">
		<ul class="toolBar">
		</ul>
	</div>
	<form id="fezb_form" action="wxdw/ajaxSaveWxdwConfig.do">
	年度：<netsky:htmlSelect id="nd" name="nd" objectForOption="years" style="width:70px" valueForOption="" showForOption="" value="${nd}"/>
		<input type="hidden" name="lb" value="${param.lb }"/>
	</form>
	<table class="report" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;">
			<thead>
				<tr>
					<th style="width: 80px;">&nbsp;</th>
					<c:forEach begin="1" end="12" var="month">
						<th style="width: 50px;">${month }月</th>
					</c:forEach>
					<th style="width: 70px;">小计</th>
					<th style="width: 70px;">区域工单量</th>
					<th style="width: 70px;">区域占比</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${gdcqtjList}" var="gdcqtj">
				<tr>
					<c:forEach begin="1" end="16" var="i">
						<td style="width: 70px; text-align: center">${gdcqtj[i] }</td>
					</c:forEach>  
				</tr>
			</c:forEach> 
			</tbody>
		</table>
</div>
</div>