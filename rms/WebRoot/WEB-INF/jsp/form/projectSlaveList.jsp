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
					<th style="width: 50px;"></th>
					<th style="width: 50px;"></th>
				</tr>
			</thead>
			<tbody>
			<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${projectSlaveList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr>
						<td>${offset }</td>
						<td>${obj.slave_type }</td>
						<td>${obj.file_name }</td>
						<td>${obj.ftp_date }</td>
						<td style="text-align:center;"><a href="download.do?slave_id=${obj.id }" target="_blank">下载</a></td>
						<td style="text-align:center;">
						   <c:if test="${param.canDel == 'yes'}">	
							<a href="javascript:del_slave(${obj.id }, ${offset - 1})" target="_blank">删除</a>
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
						<td></td>
					</tr>
				</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
</div>