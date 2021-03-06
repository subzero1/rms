<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<form id="pagerForm" method="post" action="">
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
	<input type="hidden" name="project_id" value="${param.project_id}" />
	<input type="hidden" name="module_id" value="${param.module_id}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form rel="pagerForm" method="post" action="" onsubmit="return dwzSearch(this, 'dialog');">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>文件名称：<input name="keywords" value="${param.keywords}" type="text" size="25" />
						<input type="text" style="display:none;"/>
						</td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="submit" id="searchButton">检 索</button></div></div></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
			</ul>
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
					<th style="width:20px;"></th>
					<th style="width:100px;" orderField="slave_type">附件类型</th>
					<th orderField="file_name">附件名称</th>
					<th style="width: 100px;" orderField="ftp_date">上传时间</th>
					<th style="width: 100px;"></th>
				</tr>
			</thead>
			<tbody>
			<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${projectSlaveList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr class="maintr">
						<td>${offset }</td>
						<td>${obj.slave_type }</td>
						<td>${obj.file_name }</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${obj.ftp_date }"/></td>
						<td style="text-align:center;" class="finishdown"><a href="download.do?slave_id=${obj.id }" target="_blank"><img border="0" src="Images/down.png" style="cursor:pointer" alt="下载"></a>
							&nbsp;&nbsp;&nbsp;&nbsp;
						   <c:if test="${(param.canDel == 'yes' && curUserId == obj.user_id) || admin == 'true'}">	
							<a href="javascript:del_slave(${obj.id }, ${offset - 1})"><img border="0" src="Images/trash.gif" style="cursor:pointer" alt="删除"></a>
						   </c:if>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${offset<numPerPage}">
				<c:forEach begin="${offset}" end="${numPerPage-1}">
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
</div>