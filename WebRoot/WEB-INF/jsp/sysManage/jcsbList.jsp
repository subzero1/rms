<%@ page language="java" pageEncoding="GBK" %>
<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
function jcsbexExcel(){
	var keyword = encodeURI(encodeURI($("#keyword").val()));
	var action = "dispath.do?url=sysManage/jcsbexExcel.jsp?keyword="+keyword;
	$.pdialog.open(action,'jcsbexExcel','导 出',{});
}
</script>
<div class="page">
<form id="pagerForm"
			action="sysManage/jcsbList.do" method="post">
	<div class="pageHeader">
			<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
			<div class="searchBar">
				<table class="searchContent">
				<tr>
					<td>查询关键字：<input id="keyword" type="text" name="keyword" value="${param.keyword }" title="设备名称/设备型号/厂家 的任意关键字"/></td>
				</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" onClick="javascript:searchOrExcelExport(this,'',navTabSearch);">搜 索</button></div></div></li>
					</ul>
				</div>
			</div>
		
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li>
					<a class="add" href="javascript:$.pdialog.open('sysManage/jcsb.do', 'jcsb', '基础设备信息',{mask:true, width:500, height:220});"><span>新建</span></a></li>
				<li class="line">line</li>
				<li><a class="edit" href="sysManage/jcsb.do?id={jcsb_id}" target="dialog" width="500" height="220" title="修改"><span>修改</span></a></li>
				<li class="line">line</li>
				<li>
					<a class="delete" href="sysManage/deljcsb.do?id={jcsb_id}" target="ajaxTodo" title="确认删除吗？"><span>删除</span></a></li>
				<li class="line">line</li>
				<li>
					<a class="icon" href="dispath.do?url=sysManage/jcsbimExcel.jsp" target="dialog" width="360" height="200"><span>导入</span></a></li>
				<li class="line">line</li>
				<li>
					<a class="exportexcel" href="sysManage/jcsbList.do?toexcel=yes&excelversion=97-03" target="dwzExport" targetType="navTab"><span>导出</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
					<!-- 初始化标题名称 -->
				<th width="40">序号</th>
				<th orderField="sbmc" width="400">设备名称</th>
				<th width="100" orderField="sbxh">设备型号</th>
				<th width="100" orderField="cj">厂家</th>
				<th></th>
				</tr>
			</thead>
			<tbody>			
				<c:set var="offset" scope="page" value="0"/>	
					<c:forEach var="jcsb" items="${jcsb_list}">
					<tr target="jcsb_id" rel="${jcsb.id}">
					    <c:set var="offset" scope="page" value="${offset + 1}"/>
					    <td>${offset }</td>
						<td>${jcsb.sbmc}</td>
						<td>${jcsb.sbxh}</td>
						<td>${jcsb.cj}</td>
						<td></td>
					</tr>
				</c:forEach>
				<c:forEach begin="1" end="${numPerPage-offset}">
					<tr target="jcsb_id" rel="">	
						<td>&nbsp;</td>
						<td>&nbsp;</td>
	                    <td>&nbsp;</td>
						<td>&nbsp;</td>		
						<td>&nbsp;</td>				
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
	</form>
</div>


