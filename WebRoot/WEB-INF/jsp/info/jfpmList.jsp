<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script type="text/javascript">
	function toexcel(){
		$.pdialog.open('info/jfxxtoexceljsp.do','jfimport','EXCEL导入机房信息',{data:{keywords:$("#keywords").val(),jdxz:$("#jdxz").val(),ssqy:$("#ssqy").val()}});
	}
</script>

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
		<form action="info/jfpmList.do" method="post">
			<input type="hidden" id="selectedId_demo" />
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>所属区域：</td>
						<td><netsky:htmlSelect name="ssqy" id="ssqy" objectForOption="ssqyList" valueForOption="name" showForOption="name" value="${param.ssqy}" extend="" extendPrefix="true" /></td>
						<td>局点性质：</td>
						<td><netsky:htmlSelect name="jdxz" id="jdxz" objectForOption="jdxzList" valueForOption="name" showForOption="name" value="${param.jdxz}" extend=""  extendPrefix="true" /></td>
						<td>局点或机房名称：</td>
						<td><input id="keywords" name="keywords" value="${param.keywords}" type="text" size="25" /></td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" onClick="javascript:searchOrExcelExport(this,'info/jfpmList.do',navTabSearch);">检 索</button></div></div></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="info/jfpm.do?limit=1" target="navTab" rel="jfpm" title="机房平面信息"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="info/ajaxJfpmDel.do?id={jfpm_id}" target="ajaxTodo" title="确认删除吗？"><span>删除</span></a></li>
				<li class="line">line</li>
				<li><a class="edit" href="info/jfpm.do?id={jfpm_id}&limit=1" target="navTab" rel="jfpm" title="机房平面信息"><span>修改</span></a></li>
				<li class="line">line</li>
				<li><a class="icon" href="info/exceltojfxxjsp.do" target="dialog" rel="jfimport" width="360" height="200" title="导入机房信息"><span>导入</span></a></li>
				<li class="line">line</li>
				<li><a class="exportexcel" href="info/jfxxtoexcel.do?excelversion=97-03" target="dwzExport" targetType="navTab"><span>导出</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
					<th orderField="jdmc">局点名称</th>
					<th orderField="jfmc">机房名称</th>
					<th style="width: 120px;" orderField="zldd">坐落地点</th>
					<th style="width: 100px;" orderField="jsrq">建设日期</th>
					<th style="width: 80px;" orderField="glry">管理人员</th>
					<th style="width: 80px;" orderField="glylxdh">联系电话</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="obj" items="${jfpmList}">
					<tr target="jfpm_id" rel="${obj.id}">
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

			<div class="pagination" targetType="navTab"	totalCount="${totalCount}" numPerPage="${numPerPage}" currentPage="${param.pageNum}"></div>

		</div>
	</div>
</div>