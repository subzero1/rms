<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>


<form id="pagerForm" method="post" action="">
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
	<div class="pageHeader">
		<form action="mbk/mbkList.do" method="post">
			<input type="hidden" id="selectedId_demo" />
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>地区：</td>
						<td><netsky:htmlSelect name="ssdq" id="ssdq" objectForOption="dqList" valueForOption="name" showForOption="name" value="${param.ssdq}" extend="" extendPrefix="true" /></td>
						<td>类别：</td>
						<td><netsky:htmlSelect name="lb" id="lb" objectForOption="lbList" valueForOption="name" showForOption="name" value="${param.lb}" extend=""  extendPrefix="true" /></td>
						<td>状态：</td>
						<td><netsky:htmlSelect name="zt" id="zt" objectForOption="ztList" valueForOption="name" showForOption="name" value="${param.zt}" extend="" extendPrefix="true" /></td>
						<td>资源名称：</td>
						<td><input id="zymc" name="zymc" value="${param.zymc}" type="text" size="25" /></td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" onClick="javascript:searchOrExcelExport(this,'mbk/mbkList.do',navTabSearch);">检 索</button></div></div></li>
						<li><a class="button" href="mbk/advancedSearch.do" target="dialog" width="800" height="500" rel="advancedSearch" title="高级查询条件"><span>高级查询</span></a></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="mbk/mbkEdit.do" target="navTab" rel="jfpm" title="目标库信息"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="mbk/ajaxMbkDel.do?id={mbk_id}" target="ajaxTodo" title="确认删除吗？"><span>删除</span></a></li>
				<li class="line">line</li>
				<li><a class="edit" href="mbk/mbkEdit.do?id={mbk_id}" target="navTab" rel="mbk" title="目标库信息"><span>修改</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
					<th orderField="zymc">资源名称</th>
					<th orderField="ssdq">地区</th>
					<th style="width: 120px;" orderField="jsxx">建设性质</th>
					<th style="width: 100px;" orderField="lb">类别</th>
					<th style="width: 80px;" orderField="tdr">谈点人</th>
					<th style="width: 80px;" orderField="zt">状态</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="obj" items="${mbkList}">
					<tr target="mbk_id" rel="${obj.id}">
						<td>${obj.zymc }</td>
						<td>${obj.ssdq }&nbsp;</td>
						<td>${obj.jsxx }</td>
						<td>${obj.lb }</td>
						<td>${obj.tdr }</td>
						<td>${obj.zt }</td>
					</tr>
				</c:forEach>
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