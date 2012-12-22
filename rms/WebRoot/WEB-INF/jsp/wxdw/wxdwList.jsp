<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script type="text/javascript">
	$(function(){
		$("#mc").keyup(function(e){
			if (e.which == 13){
				$("#searchButton",navTab.getCurrentPanel()).click();
			}
		});
	});
</script>

<form id="pagerForm" method="post" action="">
	<input type="hidden" name="mc" value="${param.mc}">
	<input type="hidden" name="lb" value="${param.lb}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="wxdw/wxdwList.do" method="post">
			<input type="hidden" id="selectedId_demo" />
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>类别：<netsky:htmlSelect name="lb" id="lb" objectForOption="lbList" valueForOption="name" showForOption="name" value="${param.lb}" extend=""  extendPrefix="true" /></td>
						<td>单位名称：<input id="mc" name="mc" value="${param.mc}" type="text" size="25" />
						<input type="text" style="display:none;"/>
						</td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="searchButton" onClick="javascript:searchOrExcelExport(this,'wxdw/wxdwList.do',navTabSearch);">检 索</button></div></div></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="wxdw/wxdwEdit.do" target="navTab" rel="wxdw" title="合作单位维护"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="edit" href="wxdw/wxdwEdit.do?id={wxdw_id}" target="navTab" rel="wxdw" title="合作单位维护"><span>修改</span></a></li>
				<li class="line">line</li>
				<li><a class="exportexcel"	href="wxdw/wxdwList.do?toExcel=yes" target="dwzExport" targetType="navTab"><span>导出</span></a></li>
				<li class="line">line</li>
				<li><a class="helponline"	href="javascript:enterHelp('wxdw')"><span>在线帮助</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
					<th style="width: 80px;" orderField="lb">类别</th>
					<th orderField="mc">单位名称</th>
					<th style="width: 120px;" orderField="dwdz">地址</th>
					<th style="width: 80px;" orderField="zt">状态</th>
				</tr>
			</thead>
			<tbody>
			<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${wxdwList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr target="wxdw_id" rel="${obj.id}">
						<td>${obj.lb }</td>
						<td><a href="wxdw/wxdwEdit.do?id=${obj.id}" target="navTab" rel="wxdw" title="合作单位维护">${obj.mc }</a></td>
						<td>${obj.dwdz }</td>
						<td>${obj.zt }</td>
					</tr>
				</c:forEach>
				<c:if test="${offset<numPerPage}">
				<c:forEach begin="${offset}" end="${numPerPage-1}">
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</c:forEach>
				</c:if>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span>
				<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})" selectValue="${numPerPage}">
					<option value="20">20</option>
					<option value="50">50</option>
					<option value="100">100</option>
					<option value="200">200</option>
				</select>
				<span>共${totalCount}条 </span>
			</div>

			<div class="pagination" targetType="navTab"	totalCount="${totalCount}" numPerPage="${numPerPage}" currentPage="${param.pageNum}"></div>

		</div>
	</div>
</div>