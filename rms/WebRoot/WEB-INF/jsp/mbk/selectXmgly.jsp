<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<script type="text/javascript">
</script>

<form id="pagerForm" action="">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="mbk/getxmgly.do" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>姓名:</label>
				<input class="textInput" name="name" value="${param.name }" type="text"/>
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
				<th orderfield="name">姓名</th>
				<th width="60">查找带回</th>
			</tr>
		</thead>				
		<tbody>
			<c:forEach items="${xmglyList }" var="xmgly">
			<tr>
				<td>${xmgly.name }</td>
				<td>
						<a class="btnSelect" href="javascript:$.bringBack({'Xmgly':${xmgly.id }});" title="查找带回">
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