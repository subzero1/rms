<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script type="text/javascript">
	$(function(){
		$("#gcmc").keyup(function(e){
			if (e.which == 13){
				$("#searchButton",navTab.getCurrentPanel()).click();
			}
		});
	});
</script>

<form id="pagerForm" method="post" action="">
	<input type="hidden" name="gcmc" value="${param.gcmc}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="wxdw/gcclList.do" method="post">
			<input type="hidden" id="selectedId_demo" />
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>名称：<input id="gcmc" name="gcmc" value="${param.gcmc}" type="text" size="25" />
						<input type="text" style="display:none;"/>
						</td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="searchButton" onClick="javascript:searchOrExcelExport(this,'wxdw/jdfktxList.do',navTabSearch);">检 索</button></div></div></li>
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
					<th style="width: 150px;" orderField="gcbh">工程编号</th>
					<th orderField="gcmc">工程名称</th>
					<th style="width: 100px;" orderField="sgdw">施工单位</th>
					<th style="width: 100px;" orderField="jhjgsj">计划竣工</th>
					<th style="width: 70px;" orderField="sgjdtbzq">填报周期</th>
					<th style="width: 100px;">应填报日期</th>
					<th style="width: 70px;">时间进度</th>
					<th style="width: 70px;">填报进度</th>
				</tr>
			</thead>
			<tbody>
			<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${gcxxList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr style="background-color: ${obj[1] }">
						<td>${obj[0].gcbh }</td>
						<td>${obj[0].gcmc }</td>
						<td>${obj[0].sgdw }</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${obj[0].jhjgsj }"/></td>
						<td>${empty obj[0].sgjdtbzq ? '默认3' : obj[0].sgjdtbzq}天</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${obj[2] }"/></td>
						<td><a href="wxdw/jdfk.do?id=${obj[0].id}&jhjd=${obj[3] }" target="dialog" rel="jdfk" width="528" height="297" title="进度反馈"><fmt:formatNumber pattern="0.00%" value="${obj[3] }"/></a></td>
						<td><fmt:formatNumber pattern="0.00%" value="${obj[0].tbjd }"/></td>
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

			<div class="pagination" targetType="navTab"	totalCount="${totalCount}" numPerPage="${numPerPage}" currentPage="${pageNum}"></div>

		</div>
	</div>
</div>