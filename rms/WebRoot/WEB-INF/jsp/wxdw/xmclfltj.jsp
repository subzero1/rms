<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script type="text/javascript">
	$(function(){
	});
</script>

<form id="pagerForm" method="post" action="">
	<input type="hidden" name="project_id" value="${param.project}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="wxdw/wxdwList.do" method="post">
	<input type="hidden" name="project_id" value="${param.project}">
			<input type="hidden" id="selectedId_demo" />
			<div class="searchBar">
				<h1>工程名称：${gcxx.gcmc } 工程编号：${gcxx.gcbh }</h1>
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
					<th>材料名称</th>
					<th style="width:80px;">单位</th>
					<th style="width:80px;">规格</th>
					<th style="width: 80px;">型号</th>
					<th style="width: 80px;">入库数量</th>
					<th style="width: 80px;">出库数量</th>
					<th style="width: 80px;">缴料数量</th>
				</tr>
			</thead>
			<tbody>
			<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${clmxbList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr>
						<td>${offset }</td>
						<td>${obj['clmxb'].clmc }</td>
						<td>${obj['clmxb'].dw }</td>
						<td>${obj['clmxb'].gg }</td>
						<td>${obj['clmxb'].xh }</td>
						<td><a data="{'clmc':'${obj['clmxb'].clmc }','dw':'${obj['clmxb'].dw }','gg':'${obj['clmxb'].gg }','xh':'${obj['clmxb'].xh }'}" target="navTab" rel="xmkcfltjmx" title="项目库存分类统计明细" href="wxdw/xmkcfltjmx.do?dz=0&project_id=${param.project_id }">${obj['0'] }</a></td>
						<td><a data="{'clmc':'${obj['clmxb'].clmc }','dw':'${obj['clmxb'].dw }','gg':'${obj['clmxb'].gg }','xh':'${obj['clmxb'].xh }'}" target="navTab" rel="xmkcfltjmx" title="项目库存分类统计明细" href="wxdw/xmkcfltjmx.do?dz=1&project_id=${param.project_id }">${obj['1'] }</a></td>
						<td><a data="{'clmc':'${obj['clmxb'].clmc }','dw':'${obj['clmxb'].dw }','gg':'${obj['clmxb'].gg }','xh':'${obj['clmxb'].xh }'}" target="navTab" rel="xmkcfltjmx" title="项目库存分类统计明细" href="wxdw/xmkcfltjmx.do?dz=2&project_id=${param.project_id }">${obj['2'] }</a></td>
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