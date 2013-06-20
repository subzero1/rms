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
	<table class="table" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;">
			<thead>
				<tr>
					<th style="width: 80px;">&nbsp;</th>
					<c:forEach begin="1" end="12" var="month">
						<th style="width: 50px;text-align: center">${month }月</th>
					</c:forEach>
					<th style="width: 70px;text-align: center">小计</th>
					<th style="width: 70px;text-align: center">区域工单量</th>
					<th style="width: 70px;text-align: center">区域占比</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${gdcqtjList}" var="gdcqtj">
				<tr>
					<c:forEach begin="1" end="15" var="i">
						<td style="width: 70px; text-align: center">${gdcqtj[i] }</td>
					</c:forEach>  
					<td style="width: 70px; text-align: center">
					<c:if test="${gdcqtj[16]=='00.00'||gdcqtj[16]=='0' }">0</c:if>
					<c:if test="${!empty gdcqtj[16]&&gdcqtj[16]!='00.00'&&gdcqtj[16]!='0' }">${gdcqtj[16] }%</c:if>
					</td>
				</tr>
			</c:forEach> 
			</tbody>
		</table>
</div>
</div>