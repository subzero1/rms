<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>


<form id="pagerForm">
	<input type="hidden" name="keyword" value="${param.keyword}"/>
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="selectJcsb.do" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>设备关键字:</label>
				<input class="textInput" name="keyword" value="${param.keyword }" type="text"/>
			</li>
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="118" targettype="dialog" width="100%">
		<thead>
			<tr>
				<th orderfield="sbmc">设备名称</th>
				<th width="100" orderfield="sbxh">设备型号</th>
				<th width="100" orderfield="cj">厂家</th>
				<th width="60">查找带回</th>
			</tr>
		</thead>				
		<tbody>
			<c:forEach var="i" begin="0" end="${numPerPage}">
			<tr>
				<td>${jcsbList[i].sbmc }</td>
				<td>${jcsbList[i].sbxh }</td>
				<td>${jcsbList[i].cj }</td>
				<td>
					<c:if test="${not empty jcsbList[i].id }">
						<a class="btnSelect" href="javascript:$.bringBack({'Td13_jfsbmx.SBMC':'${jcsbList[i].sbmc }', 'Td13_jfsbmx.SBXH':'${jcsbList[i].sbxh }', 'Td13_jfsbmx.CJ':'${jcsbList[i].cj }'})" title="查找带回">
					</c:if>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="panelBar">
		<div class="pages">
			<span>每页</span>
			<select class="combox" name="numPerPage" onchange="dialogPageBreak({numPerPage:this.value})" selectValue="${numPerPage}">
				<option value="10">10</option>
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
			</select>
			<span>条，共${totalCount}条</span>
		</div>
		<div class="pagination" targettype="dialog" totalCount="${totalCount}" numPerPage="${numPerPage}" orderField="${orderField }" currentPage="${param.pageNum}"></div>
	</div>
</div>