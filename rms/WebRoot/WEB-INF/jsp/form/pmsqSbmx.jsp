<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script language="javascript">
function sqsbDelCallback(){
	var dialog = $("body").data("sqsbmx");
	if (dialog){
		var url = dialog.data("url");
		$.pdialog.reload(url);
	}
}
</script>

<form id="pagerForm" method="post" action="">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="pmsqDetail.do" method="post">
			<input type="hidden" id="selectedId_demo" />
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td colspan="2"><span style="font-size:14px;font-weight:bold;">机房名称：${jfmc }</span></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
			<li><c:if test="${canOperSb=='yes'}">
					<li><a class="add" href="pmsqSbxx.do?module_id=${param.module_id }&project_id=${param.project_id }&doc_id=${param.doc_id }&jfxx_id=${param.jfxx_id }&node_id=${param.node_id }&limit=${param.limit }" target="dialog" rel="sqsbmx" title="机房设备信息"><span>添加</span></a></li>
					<li class="line">line</li>
					<li><a class="delete" href="ajaxSqsbDel.do?id={jfsbmx_id}" target="ajaxTodo" callback="sqsbDelCallback" title="确认删除吗？"><span>删除</span></a></li>
					<li class="line">line</li>
					<li><a class="edit" href="pmsqSbxx.do?id={jfsbmx_id}&module_id=${param.module_id }&project_id=${param.project_id }&doc_id=${param.doc_id }&jfxx_id=${param.jfxx_id }&node_id=${param.node_id }&limit=${param.limit }" target="dialog" rel="sqsbmx" title="机房设备信息"><span>修改</span></a></li>
					<li class="line">line</li>
					<li><a class="icon" href="exceltosbjsp.do?module_id=${param.module_id }&project_id=${param.project_id }&doc_id=${param.doc_id }&jfxx_id=${param.jfxx_id }&node_id=${param.node_id }" target="dialog" rel="jfimport" width="360" height="230" title="导入机房信息"><span>表七导入</span></a></li>
					<li class="line">line</li>
				</c:if>
			</li>
			</ul>
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
					<th orderField="sbmc">设备名称</th>
					<th style="width: 110px;" orderField="sbxh">设备型号</th>
					<th style="width: 60px;" orderField="sbgg">设备规格</th>
					<th style="width: 100px;" orderField="dw">单位</th>
					<th style="width: 40px;" orderField="sl">数量</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="obj" items="${pmsqSbmxList}">
					<tr target="jfsbmx_id" rel="${obj.id}">
						<td>${obj.sbmc }</td>
						<td>${obj.sbxh }</td>
						<td>${obj.sbgg }</td>
						<td>${obj.dw }</td>
						<td>${obj.sl }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span>
				<select class="combox" name="numPerPage" onchange="dialogPageBreak({numPerPage:this.value})" selectValue="${numPerPage}">
					<option value="20">20</option>
					<option value="50">50</option>
					<option value="100">100</option>
					<option value="200">200</option>
				</select>
				<span>共${totalCount}条 </span>
			</div>

			<div class="pagination" targetType="dialog"	totalCount="${totalCount}" numPerPage="${numPerPage}" currentPage="${param.pageNum}"></div>

		</div>
	</div>
</div>
<iframe height="0" width="0" name="ifra"></iframe>