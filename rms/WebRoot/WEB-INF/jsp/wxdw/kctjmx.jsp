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
	<input type="hidden" name="cllx" value="${param.cllx}">
	<input type="hidden" name="clmc" value="${param.clmc}">
	<input type="hidden" name="gg" value="${param.gg}">
	<input type="hidden" name="dw" value="${param.dw}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="wxdw/wxdwList.do" method="post">
	<input type="hidden" name="wxdw_id" value="${param.wxdw_id}">
	<input type="hidden" name="cllx" value="${param.cllx}">
	<input type="hidden" name="clmc" value="${param.clmc}">
	<input type="hidden" name="gg" value="${param.gg}">
	<input type="hidden" name="dw" value="${param.dw}">
			<input type="hidden" id="selectedId_demo" />
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>
						施工单位：</td><td>${sgdw.mc }
						</td>
						<td>
						材料名称：</td><td>${param.clmc }
						</td>
						<td>
						规格：</td><td>${param.gg }
						</td>
					</tr>
				</table>
				<div class="subBar">
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
					<th style="width: 200px;" orderField="gcxx.gcmc">工程名称</th>
					<th style="width:120px;" orderField="gcxx.gcbh">工程编号</th>
					<th style="width: 120px;" orderField="kcsl">数量</th>
					<th style="width: 120px;">领料时间</th>
				</tr>
			</thead>
			<tbody>
			<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${kcbList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr>
						<td>${offset }</td>
						<td>${obj[2].gcmc }</td>
						<td>${obj[2].gcbh }</td>
						<td>${obj[0].kcsl }</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${obj[1] }" /></td>
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