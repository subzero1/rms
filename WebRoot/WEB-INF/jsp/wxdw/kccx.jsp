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
	<input type="hidden" name="wxdw_id" value="${param.wxdw_id}">
	<input type="hidden" name="xh" value="${param.xh}">
	<input type="hidden" name="clmc" value="${param.clmc}">
	<input type="hidden" name="gg" value="${param.gg}">
	<input type="hidden" name="type" value="${param.type}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="wxdw/wxdwList.do" method="post">
			<input type="hidden" name="type" value="${param.type}">
			<input type="hidden" id="selectedId_demo" />
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<c:if test="${param.type == 'xmgly'}"><td>外协单位：<netsky:htmlSelect name="wxdw_id" id="lb" objectForOption="tf01List" valueForOption="id" showForOption="mc" value="${param.wxdw_id}"/></td></c:if>
						<td>材料名称：<input id="clmc" name="clmc" value="${param.clmc}" type="text" size="25" />
						<td>规格：<input id="gg" name="gg" value="${param.gg}" type="text" size="25" />
						<td>型号：<input id="xh" name="xh" value="${param.xh}" type="text" size="25" />
						</td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="searchButton" onClick="javascript:searchOrExcelExport(this,'wxdw/kccx.do',navTabSearch);">检 索</button></div></div></li>
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
					<th style="width:50px;">序号</th>
					<th style="width: 200px;" orderField="clmc">材料名称</th>
					<th style="width:120px;" orderField="dw">单位</th>
					<th style="width: 120px;" orderField="gg">规格</th>
					<th style="width: 120px;" orderField="xh">型号</th>
					<th style="width: 120px;">数量</th>
				</tr>
			</thead>
			<tbody>
			<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${kcbList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr>
						<td>${offset }</td>
						<td>${obj.clmc }</td>
						<td>${obj.dw }</td>
						<td>${obj.gg }</td>
						<td>${obj.xh }</td>
						<td><a href="wxdw/wxdwEdit.do?id=${obj.kcsl}" target="navTab" rel="clmxcx" title="材料明细查询">${obj.kcsl }</a></td>
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