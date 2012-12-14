<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>


<form id="pagerForm" method="post" action="">
	<input type="hidden" name="keyword" value="${param.keyword}"/>
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="wxdwkh/selectWxdw.do" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label style="width:95px;">合作单位名称:</label>
				<input class="textInput" name="mc" value="${param.mc }" type="text"/>
			<label style="width:95px;">合作单位类别:</label>
				<netsky:htmlSelect name="lb" id="lb" objectForOption="lbList" valueForOption="" showForOption="" value="${param.lb}" extend=""  extendPrefix="true" />
			</li>
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="118" targettype="dialog" width="100%">
		
		<thead>
			<tr>
				<th width="80" orderfield="lb">单位类别</th>
				<th orderfield="mc">合作单位名称</th>
				<th width="60">查找带回</th>
			</tr>
		</thead>				
		<tbody>
			<c:forEach items="${wxdwList}" var="obj">
			<tr>
				<td>${obj.lb }</td>
				<td>${obj.mc }</td>
				<td>
					<a class="btnSelect" href="javascript:$.bringBack({'WXDW_LB':'${obj.lb }', 'WXDW_ID':'${obj.id }', 'WXDW_MC':'${obj.mc }'})" title="查找带回">
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="panelBar">
		<div class="pages">
			<span>每页</span>
			<select class="combox" name="numPerPage" onchange="javascript:dialogPageBreak({numPerPage:this.value});" selectValue="${numPerPage}">
				<option value="10">10</option>
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
			</select>
			<span>条，共${totalCount}条</span>
		</div>
		<div class="pagination" targettype="dialog" totalCount="${totalCount}" numPerPage="${numPerPage}" orderField="${orderField }" currentPage="${param.pageNum}"></div>
	</div>
</div>