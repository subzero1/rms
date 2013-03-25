<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<form id="pagerForm" method="post" action="search/queryForBoss.do">
	<input type="hidden" name="keyword" value="${param.keyword}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="search/queryTemplate.do" method="post"onsubmit="return navTabSearch(this);">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>
						<input type="text" style="display:none"/>
						关键字：<input id="keyword" name="keyword" value="${param.keyword}" type="text" size="25" /></td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" onClick="javascript:searchOrExcelExport(this,'search/queryTemplate.do',navTabSearch);">检 索</button></div></div></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		 
		<table class="table" width="100%" layouth="116">
			<thead>
				<tr> 
					<th style="width: 40px;">序号</th>
					<th style="width: 250px;">名称</th>
					<th style="width:60px;">登录数</th> 
					<th></th> 
				</tr>
			</thead>
			<tbody>
				<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${dlList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr> 
						<td style="text-align:center">${offset }</td>
						<td>${obj[0] }</td>
						<td>${obj[1] }</td> 
						<td>&nbsp;</td> 
					</tr>
				</c:forEach>
				
				<c:forEach var="obj2" items="${dlList2}">
				<c:set var="offset" value="${offset+1}"/>
					<tr> 
						<td style="text-align:center">${offset }</td>
						<td>${obj2 }</td>
						<td>0</td> 
						<td>&nbsp;</td> 
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