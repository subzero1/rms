<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script type="text/javascript">
	$(function(){
	});
</script>

<form id="pagerForm" method="post" action="">
	<input type="hidden" name="wxdw_id" value="${param.wxdw_id}">
	<input type="hidden" name="xh" value="${param.xh}">
	<input type="hidden" name="clmc" value="${param.clmc}">
	<input type="hidden" name="gg" value="${param.gg}">
	<input type="hidden" name="cz" value="${param.cz}">
	<input type="hidden" name="type" value="${param.type}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="wxdw/clmxcx.do" method="post">
			<input type="hidden" name="type" value="${param.type}">
			<input type="hidden" id="selectedId_demo" />
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<c:if test="${param.type == 'xmgly'}"><td>外协单位：<netsky:htmlSelect name="wxdw_id" id="lb" objectForOption="tf01List" valueForOption="id" showForOption="mc" value="${param.wxdw_id}"/></td></c:if>
						<td>材料名称：<input id="clmc" name="clmc" value="${param.clmc}" type="text" size="25" /></td>
						<td>规格：<input style="width:80px;" id="gg" name="gg" value="${param.gg}" type="text" size="25" /></td>
						<td>型号：<input style="width:80px;" id="xh" name="xh" value="${param.xh}" type="text" size="25" /></td>
						<td>操作：
						<select id="cz" name="cz">
							<option></option>
							<option value="0" <c:if test="${param.cz == '0'}"></c:if>>入库</option>
							<option value="1" <c:if test="${param.cz == '1'}"></c:if>>出库</option>
							<option value="2" <c:if test="${param.cz == '2'}"></c:if>>缴料</option>
						</select>
						
						</td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="searchButton" onClick="javascript:searchOrExcelExport(this,'wxdw/clmxcx.do?type=${param.type }',navTabSearch);">检 索</button></div></div></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="exportexcel"	href="javascript:enterHelp('clmxcx')"><span>在线帮助</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
					<th style="width:50px;">序号</th>
					<th style="width: 200px;" orderField="clmc">材料名称</th>
					<th style="width: 60px;" orderField="dw">单位</th>
					<th style="width: 60px;" orderField="gg">规格</th>
					<th style="width: 60px;" orderField="xh">型号</th>
					<th style="width: 60px;" orderField="dz">操作</th>
					<th style="width: 60px;" orderField="sl">数量</th>
					<th style="width: 250px;" orderField="gcmc">工程名称</th>
					<th style="width: 120px;" orderField="gcbh">工程编号</th>
					<th style="width: 100px;" orderField="czsj">时间</th>
				</tr>
			</thead>
			<tbody>
			<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${clmxbList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr>
						<td>${offset }</td>
						<td>${obj[0].clmc }</td>
						<td>${obj[0].dw }</td>
						<td>${obj[0].gg }</td>
						<td>${obj[0].xh }</td>
						<td><c:if test="${obj[0].dz == 0 }">入库</c:if>
							<c:if test="${obj[0].dz == 1 }">出库</c:if>
							<c:if test="${obj[0].dz == 2 }">缴料</c:if></td>
						<td>${obj[0].sl }</td>
						<td>${obj[1].gcmc }</td>
						<td>${obj[1].gcbh }</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${obj[0].czsj }"/></td>
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