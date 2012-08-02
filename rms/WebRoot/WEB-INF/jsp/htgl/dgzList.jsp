<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<form id="pagerForm" method="post" action="htgl/dgzList.do">
	<input type="hidden" name="keywords" value="${param.keywords}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="htgl/dgzList.do" method="post">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>项目编号或名称：<input name="keywords" value="${param.keywords}" type="text" size="25" />
						<input type="text" style="display:none;"/>
						</td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="searchButton" onClick="javascript:searchOrExcelExport(this,'htgl/dgzList.do',navTabSearch);">检 索</button></div></div></li>
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
					<th style="width: 120px;" orderField="xmbh">项目编号</th>
					<th orderField="xmmc">项目名称</th>
					<th style="width: 120px;" orderField="sghtbh">施工合同编号</th>
					<th style="width: 80px;" orderField="sghtje">施工合同金额</th>
					<th style="width: 80px;" orderField="ss_je">送审金额</th>
					<th style="width: 100px;" orderField="ssrq">送审日期</th>
					<th style="width: 80px;" orderField="sd_je">审定金额</th>
					<th style="width: 100px;" orderField="sjjssj">审定日期</th>
					<th style="width: 100px;" orderField="kprq">开票日期</th>
					<th style="width: 100px;" orderField="gzrq">挂账日期</th>
				</tr>
			</thead>
			<tbody>
			<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${dgzList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr>
						<td>${obj.xmbh }</td>
						<td><a href="htgl/htEdit.do?xm_id=${obj.id }&act=dgz&htlb=sg" target="dialog" width="380" height="260" rel="dgzEdit" title="录入挂账信息">${obj.xmmc }</a></td>
						<td>${obj.sghtbh }</td>
						<td>${obj.sghtje }</td>
						<td>${obj.ss_je }</td>
						<td><fmt:formatDate value="${obj.ssrq }" pattern="yyyy-MM-dd"/></td>
						<td>${obj.sd_je }</td>
						<td><fmt:formatDate value="${obj.sjjssj }" pattern="yyyy-MM-dd"/></td>
						<td><fmt:formatDate value="${obj.kprq }" pattern="yyyy-MM-dd"/></td>
						<td><fmt:formatDate value="${obj.gzrq }" pattern="yyyy-MM-dd"/></td>
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