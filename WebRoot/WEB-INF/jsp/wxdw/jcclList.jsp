<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script type="text/javascript">
	$(function(){
		$("#clmc").keyup(function(e){
			if (e.which == 13){
				$("#searchButton",navTab.getCurrentPanel()).click();
			}
		});
	});
</script>

<form id="pagerForm" method="post" action="">
	<input type="hidden" name="clmc" value="${param.clmc}">
	<input type="hidden" name="cllx" value="${param.cllx}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="wxdw/jcclList.do" method="post">
			<input type="hidden" id="selectedId_demo" />
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>名称：</td>
						<td><input id="clmc" name="clmc" value="${param.clmc}" type="text" size="25" />
						<input type="text" style="display:none;"/>
						</td>
						<td>类别：</td>
						<td><netsky:htmlSelect name="cllx" id="cllx" objectForOption="cllxList" valueForOption="name" showForOption="name" value="${param.cllx}" extend=""  extendPrefix="true" /></td>
						
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="searchButton" onClick="javascript:searchOrExcelExport(this,'wxdw/jcclList.do',navTabSearch);">检 索</button></div></div></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="wxdw/jcclEdit.do" target="dialog" rel="jccl" width="500" height="200" title="基础材料维护"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="edit" href="wxdw/jcclEdit.do?id={clb_id}" target="dialog" rel="jccl" width="500" height="200" title="基础材料维护"><span>修改</span></a></li>
				<li class="line">line</li>
				<li><a class="exportexcel"	href="wxdw/jcclList.do?toExcel=yes" target="dwzExport" targetType="navTab"><span>导出</span></a></li>
				<li class="line">line</li>
				<li><a class="icon" href="dispath.do?url=wxdw/jcclImport.jsp" target="dialog" width="360" height="200"><span>导入</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
					<th orderField="clmc">名称</th>
					<th style="width: 250px;" orderField="gg">规格</th>
					<th style="width: 100px;" orderField="xh">型号</th>
					<th style="width: 100px;" orderField="dw">单位</th>
					<th style="width: 150px;" orderField="cllx">类别</th>
				</tr>
			</thead>
			<tbody>
			<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${clbList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr target="clb_id" rel="${obj.id}">
						
						<td><a href="wxdw/jcclEdit.do?id=${obj.id}" target="dialog" rel="clb" width="500" height="200" title="基础材料维护">${obj.clmc }</a></td>
						<td>${obj.gg }</td>
						<td>${obj.xh }</td>
						<td>${obj.dw }</td>
						<td>${obj.cllx }</td>
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