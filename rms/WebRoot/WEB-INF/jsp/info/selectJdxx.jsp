<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>


<form id="pagerForm">
	<input type="hidden" name="jdmc" value="${param.jdmc}"/>
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="selectJdxx.do" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>局点名称:</label>
				<input class="textInput" name="jdmc" value="${param.jdmc }" type="text"/>
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
				<th orderfield="jdmc">局点名称</th>
				<th width="60" orderfield="ssqy">所属区域</th>
				<th width="60" orderfield="jdxz">局点性质</th>
				<th width="60">查找带回</th>
			</tr>
		</thead>				
		<tbody>
			<c:forEach var="i" begin="0" end="${numPerPage}">
			<tr>
				<td>${jdxxList[i].name }</td>
				<td>${jdxxList[i].p_area }</td>
				<td>${jdxxList[i].type }</td>
				<td>
					<c:if test="${not empty jdxxList[i].id }">
						<a class="btnSelect" href="javascript:$.bringBack({'Td00_jfxx.SSQY':'${jdxxList[i].p_area }', 'Td00_jfxx.JDXZ':'${jdxxList[i].type }', 'Td00_jfxx.JDMC':'${jdxxList[i].name }'})" title="查找带回">
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