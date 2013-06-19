<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

 
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
					<c:forEach begin="1" end="13" var="i">
						<td style="width: 70px; text-align: center">${gdcqtj[i] }</td>
					</c:forEach>
					<td style="width: 70px; text-align: center"> 
						${gdcqtj[2]+gdcqtj[3] +gdcqtj[4] +gdcqtj[5] +gdcqtj[6] +gdcqtj[7] +gdcqtj[8] 
						+gdcqtj[9] +gdcqtj[10] +gdcqtj[11] +gdcqtj[12] +gdcqtj[13]} 
					</td>
					<td style="width: 70px; text-align: center"></td>
					<td style="width: 70px; text-align: center"></td>
				</tr>
			</c:forEach>	
				<tr>
						<td style="width: 70px; text-align: center">小计</td>
					<c:forEach begin="2" end="13" var="i">
						<td style="width: 70px; text-align: center">
						0
						</td>
					</c:forEach>
				</tr> 
			</tbody>
		</table>
