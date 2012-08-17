<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script type="text/javascript">
	$(function(){
	});
</script>

<form id="pagerForm" method="post" action="">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="wxdwkh/khkf.do" method="post">
			<input type="hidden" id="selectedId_demo" />
			<div class="searchBar">
				<table class="searchContent">
					<tr>
					<td></td>
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
				<li><a class="edit" href="wxdwkh/rckhEdit.do?id={rckh_id}&canedit=false" target="dialog" width="800" height="480" rel="rckh" title="日常考核"><span>查看</span></a></li>
				<li><a class="exportexcel" href="wxdwkh/khkf.do?toExcel=yes&id=${param.id }" target="dwzExport" targetType="navTab"><span>导EXCEL</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layouth="138" style="table-layout:fixed;">
			<thead>
				<tr>
					<th style="width: 80px;" orderField="khsj">考核时间</th>
					<th style="width: 80px;" orderField="qrsj">确认时间</th>
					<th style="width: 80px;" orderField="khry_name">考核人员</th>
					<th style="width: 80px;" orderField="khyy">考核原因</th>
					<th style="width: 80px;" orderField="khlb">考核类别</th>
					<th style="width: 80px;" orderField="fkje">罚款金额</th>
					<th style="width: 80px;" orderField="jkfz">加扣分值</th>
					<th style="width: 80px;" orderField="khjg">考核结果</th>
				</tr>
			</thead>
			<tbody>
			<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${rckhList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr target="rckh_id" rel="${obj.id}">
						<td><fmt:formatDate value="${obj.khsj}" pattern="yyyy-MM-dd"/></td>
						<td><fmt:formatDate value="${obj.qrsj}" pattern="yyyy-MM-dd"/></td>
						<td>${obj.khry_name }</td>
						<td>${obj.khyy }</td>
						<td>${obj.khlb }</td>
						<td><fmt:formatNumber pattern="0.00" value="${obj.fkje }"/></td>
						<td><fmt:formatNumber pattern="0.00" value="${obj.jkfz }"/></td>
						<td style="white-space:nowrap;overflow:hidden;">${obj.khjg }</td>
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