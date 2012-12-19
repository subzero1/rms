<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<form id="pagerForm" method="post" action="form/xmxxList.do">
	<input type="hidden" name="keyword" value="${keyword}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="form/xmxxList.do" method="post"onsubmit="return navTabSearch(this);">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>
						<input type="text" style="display:none"/>
						关键字：<input id="keyword" name="keyWord" value="${param.keyWord}" type="text" size="25" /></td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" onClick="javascript:searchOrExcelExport(this,'form/xmxxList.do',navTabSearch);">检 索</button></div></div></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<!-- 
			<ul class="toolBar">
				<li><a class="helponline"	href="javascript:enterHelp('xmgl')"><span>在线帮助</span></a></li>
				<li class="line">line</li>
			</ul>
			 -->
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
					<th style="width:40px;"></th>
					<th orderField="xmmc">项目名称</th>
					<th style="width: 120px;" orderField="xmbh">项目编号</th>
					<th style="width: 65px;" orderField="ssdq">所属地区</th>
					<th style="width: 65px;" orderField="gclb">工程类别</th>
					<th style="width: 200px;" orderField="sgdw">施工单位</th>
					<th style="width: 140px;" orderField="sghtbh">施工合同编号</th>
					<th style="width: 70px;" orderField="lxje">立项金额</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${xmxxList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr target="xm_id" rel="${obj.id}">
						<td style="text-align:center;">
						
						</td>
						<td><a href="openForm.do?project_id=${obj.id }&module_id=101&doc_id=${obj.id }&user_id=${user.id }&limit=${limit }" target="navTab" rel="xmxx" title="项目信息单">${obj.xmmc }</a></td>
						<td>${obj.xmbh }&nbsp;</td>
						<td>${obj.ssdq }</td>
						<td>${obj.gclb }</td>
						<td>${obj.sgdw }</td>
						<td>${obj.sghtbh }</td>
						<td>${obj.lxje }</td>
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