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
				<li><a class="exportexcel" href="wxdwkh/xmList.do?toExcel=yes&id=${param.id }" target="dwzExport" targetType="navTab"><span>导EXCEL</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layouth="138" style="table-layout:fixed;">
			<thead>
				<tr>
					<th style="width:20px;"></th>
					<th style="width:35px;"></th>
					<th style="width: 100px;" orderField="xmbh">项目编号</th>
					<th orderField="xmmc">项目名称</th>
					<th style="width: 80px;" orderField="gclb">工程类别</th>
					<th style="width: 80px;" orderField="jsxz">建设性质</th>
					<th style="width: 80px;" orderField="zydl">专业大类</th>
					<th style="width: 80px;" orderField="zyxx">专业小项</th>
					<th style="width: 80px;" orderField="qkdl">切块大类</th>
					<th style="width: 80px;" orderField="qkxl">切块小类</th>
				</tr>
			</thead>
			<tbody>
			<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${xmList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr target="rckh_id" rel="${obj.id}">
						<td>
								<a href="javascript:openFlowForm('{project_id:${obj.id },doc_id:${obj.id},module_id:101,opernode_id:-1,node_id:-1,user_id:-1}');" title="表单[${obj.id}]"><img border="0" src="Images/form.gif" style="cursor:pointer"/></a>
						</td>
						<td>
								<a href="showTree.do?project_id=${obj.id }&doc_id=${obj.id }&module_id=101" target="navTab" rel="showTree" title="流程图[${obj.id}]"><img border="0" src="Images/node.gif" style="cursor:pointer"/></a>
						</td>
						<td>${obj.xmbh }</td>
						<td>${obj.xmmc }</td>
						<td>${obj.jsxz }</td>
						<td>${obj.gclb }</td>
						<td>${obj.zydl }</td>
						<td>${obj.zyxx }</td>
						<td>${obj.qkdl }</td>
						<td>${obj.qkxl }</td>
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