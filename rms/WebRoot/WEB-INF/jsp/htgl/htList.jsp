<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<form id="pagerForm" method="post" action="">
	<input type="hidden" name="keywords" value="${param.keywords}">
	<input type="hidden" name="htlb" value="${param.htlb}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="htgl/htList.do" method="post">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>类别：</td>
						<td><netsky:htmlSelect name="htlb" id="htlb" objectForOption="htlbList" valueForOption="name" showForOption="name" value="${param.htlb}"/></td>
						<td>项目编号或名称：</td>
						<td><input name="keywords" value="${param.keywords}" type="text" size="25" />
						<input type="text" style="display:none;"/>
						</td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="searchButton" onClick="javascript:searchOrExcelExport(this,'htgl/htList.do',navTabSearch);">检 索</button></div></div></li>
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
					<th style="width: 80px;" orderField="htlb">合同类型</th>
					<th style="width: 100px;" orderField="lxsj">立项日期</th>
					<th style="width: 120px;" orderField="htbh">合同编号</th>
					<th style="width: 100px;" orderField="htqdrq">签订日期</th>
					<th style="width: 80px;" orderField="htje">合同金额</th>
					<th style="width: 80px;">指导金额</th>
				</tr>
			</thead>
			<tbody>
			<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${htList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr>
						<td>${obj.xmbh }</td>
						<td><a href="htEdit.do?xm_id=${obj.xm_id }&act=ht" target="dialog" width="450" height="300" rel="htEdit" title="合同信息">${obj.xmmc }</a></td>
						<td>${obj.htlb}</td>
						<td>${obj.lxsj }</td>
						<td>${obj.htbh }</td>
						<td>${obj.htqdrq }</td>
						<td>${obj.htje }</td>
						<td>${obj.zdje }</td>
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