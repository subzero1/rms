<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<form id="pagerForm" method="post" action="">
	<input type="hidden" name="jdxz" value="${param.jdxz}">
	<input type="hidden" name="ssqy" value="${param.ssqy}">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form
			action="business/feeApplyList.do" method="post">
			<input type="hidden" id="selectedId_demo" />
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>所属区域：<input type="hidden" name="act" value="${act}" /></td>
						<td><netsky:htmlSelect name="ssqy" id="ssqy" objectForOption="ssqyList" value="${param.ssqy}" /></td>
						<td>局点性质：</td>
						<td><netsky:htmlSelect name="jdxz" id="jdxz" objectForOption="jdxzList" value="${param.jdxz}" /></td>
						<td>局点名称：</td>
						<td><input name="keywords" value="${param.keywords}" type="text" size="25" /></td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" onClick="javascript:searchOrExcelExport(this,'business/feeApplyList.do',navTabSearch);">检 索</button></div></div></li>
						<li><div class="button"><div class="buttonContent"><button type="button" onClick="javascript:searchOrExcelExport(this,'business/feeApplyListExport.do');">EXCEL导出</button></div></div></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="info/jdxx.do" target="dialog" width="400" height="300" title="添加局点"><span>添加</span></a></li>
				<li><a class="delete" href="info/jdxxDel.do?id={jdxx_id}" target="ajaxTodo" title="确认删除吗？"><span>删除</span></a></li>
				<li><a class="edit" href="info/jdxx.do?id={jdxx_id}" target="dialog" width="400" height="300" title=局点信息"><span>修改</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
					<th style="width: 200px;" orderField="name">局点名称</th>
					<th style="width: 100px;" orderField="p_area">所属区域</th>
					<th style="width: 100px;" orderField="type">局点性质</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="obj" items="${jdxxList}">
					<tr target="jdxx_id" rel="${obj.id}">
						<td>${obj.jdmc }</td>
						<td>${obj.jfmc }&nbsp;</td>
						<td>${obj.zldd }</td>
						<td><fmt:formatDate value="${obj.jsrq }" pattern="yyyy-MM-dd" /></td>
						<td>${obj.glry }</td>
						<td>${obj.glylxdh }</td>
					</tr>
				</c:forEach>
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