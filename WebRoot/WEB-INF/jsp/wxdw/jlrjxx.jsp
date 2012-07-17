<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />
<script type="text/javascript">
</script>
<div class="page">

	<!-- 表单头 -->
	<div class="pageHeader">
		<div class="searchBar">
			<!-- 表单名称 -->
			<h1>资源信息单</h1>
			
		</div>
	</div>
	
	<!-- 主操作按钮 -->
	<div class="panelBar">
	<!-- 
		<ul class="toolBar">
		 	<li><a class="save"	href="javascript:saveMbk();"><span>保 存</span></a></li>
			<li class="line">line</li>
		</ul>
	 -->
	</div>
	
	
	<div class="pageContent" layouth="48">
			<div class="pageFormContent">
				<p>
					<label>工程编号：</label>
					<input readonly="readonly" type="text" style="width:150px;" value="${gcxx.gcbh}"/>
				</p>
				<p>
					<label>工程名称：</label>
					<input readonly="readonly" type="text" style="width:376px;" value="${gcxx.gcmc}" />
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>填报周期：</label>
					<input readonly="readonly" type="text" style="width:150px;" value="${empty gcxx.jlrjtbzq ? '默认3' : gcxx.jlrjtbzq}天"/>
				</p>
				<p>
					<label>最新记录日期：</label>
					<input readonly="readonly" type="text" style="width:135px;" value="<fmt:formatDate value="${gcxx.create_date }" pattern="yyyy-MM-dd"/>" />
				</p>
				<p>
					<label>工程状态：</label>
					<input readonly="readonly" type="text" style="width:135px;" value="${gcxx.zt }" />
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>最新现场描述：</label>
					<textarea readonly="readonly" style="width:632px;height:100px;">${gcxx.xcms }</textarea>
				</p>
		</div>
		<div style="text-align:left;color:blue;"><h3>&nbsp;&nbsp;监理日志记录</h3></div><div class="divider" style="height:1px;"></div>
		<table class="table" width="100%">
		<thead>
			<tr>
				<th style="width: 100px;">记录日期</th>
				<th style="width: 80px;">记录人</th>
				<th style="width: 80px;">工程状态</th>
				<th>现场描述</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${tf14List}" var="obj">
				<tr>
					<td><fmt:formatDate value="${obj[0].create_date }" pattern="yyyy-MM-dd"/></td>
					<td>${obj[1]}</td>
					<td>${obj[0].gczt }</td>
					<td>${obj[0].xcms }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
		
		
	</div>
</div>
