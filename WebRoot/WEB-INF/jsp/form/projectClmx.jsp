<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<form id="pagerForm" method="post" action="wxdw/projectClmx.do?project_id=${param.project_id }">
	<input type="hidden" name="keyword" value="${param.keyword}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="wxdw/projectClmx.do?project_id=${param.project_id }" method="post"onsubmit="return navTabSearch(this);">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>名称：${gcmc }</td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						
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
					<th style="width:40px;"></th>
					<th style="width: 200px;" orderField="tf08.clmc">材料名称</th>
					<th style="width: 120px;" orderField="tf08.xh">规格</th>
					<th style="width: 120px;" orderField="tf08.gg">型号</th>
					<th style="width: 70px;" orderField="tf08.dw">单位</th>
					<th style="width: 70px;" orderField="tf08.sl">数量</th>
					<th style="width: 100px;" orderField="tf08.czsj">操作时间</th>
					<th style="width: 250px;" orderField="tf01.mc">施工单位</th>
					<th style="width: 80px;" orderField="tf08.dz">动作</th>
					<th>&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${clmxList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr>
						<td style="text-align:center;">${offset}</td>
						<td>${obj[0].clmc }</td>
						<td>${obj[0].gg }&nbsp;</td>
						<td>${obj[0].xh }</td>
						<td>${obj[0].dw }</td>
						<td>${obj[0].sl }</td>
						<td><fmt:formatDate value="${obj[0].czsj }" pattern="yyyy-MM-dd"/></td>
						<td>${obj[1].mc }</td>
						<td>${obj[0].dz == 0 ? "入库" : "出库"}</td>
						<td>&nbsp;</td>
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