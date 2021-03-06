<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>


<form id="pagerForm" action="">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
	<input type="hidden" name="ids" value="${param.ids }"/>
	<input type="hidden" name="names" value="${param.names }"/>
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="selectClxx.do" onsubmit="return dwzSearch(this, 'dialog');">
	<input type="hidden" name="ids" id="ids" value="${param.ids }"/>
	<input type="hidden" name="names" id="names" value="${param.names }"/>
	<div class="searchBar" >
		<ul class="searchContent">
			<li>
				<label>关键字:</label>
				<input class="textInput" name="keyword" style="width:200px;" value="${param.keyword }" type="text"/>
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

	<table class="table" layoutH="110" targettype="dialog" width="100%">
		
		<thead>
			<tr>
				<th orderfield="clbm">材料编码</th>
				<th orderfield="clmc">材料名称</th>
				<th orderfield="gg">规格</th>
				<th orderfield="xh">型号</th>
				<th orderfield="dw">单位</th>
				<th width="30">选择</th>
			</tr>
		</thead>				
		<tbody>
			<c:forEach items="${clxxList}" var="clxx"> 
			<tr>
				<td>${clxx.clbm }</td>
				<td>${clxx.clmc }</td>
				<td>${clxx.gg }</td>
				<td>${clxx.xh }</td>
				<td>${clxx.dw }</td>
				<td>
					<a class="btnSelect" href="javascript:$.bringBack({'Tf08_clmxb.CLBM':'${clxx.clbm }', 'Tf08_clmxb.CLMC':'${clxx.clmc }', 'Tf08_clmxb.XH':'${clxx.xh }', 'Tf08_clmxb.GG':'${clxx.gg }', 'Tf08_clmxb.DW':'${clxx.dw }'})" title="查找带回">
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