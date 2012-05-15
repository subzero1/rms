<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>


<form id="pagerForm" method="post" action="">
	<input type="hidden" name="type" value="${param.type}" />
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="info/jcsqList.do" method="post">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>局点或机房名称：</td>
						<td><input id="keywords" name="keywords" value="${param.keywords}" type="text" size="25" /></td>
						<td>
							<select name="type">
								<option value="" <c:if test="${empty param.type}">selected</c:if>>个人</option>
								<option value="all" <c:if test="${not empty param.type && param.type=='all'}">selected</c:if>>全部</option>
							</select>
						</td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" onClick="javascript:searchOrExcelExport(this,'info/jcsqList.do',navTabSearch);">检 索</button></div></div></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="info/jcsqEdit.do?limit=1" target="dialog" width="500" height="350" rel="jcsq" title="图纸纠错申请"><span>添加</span></a></li>
				<li class="line">line</li>
				<c:if test="${empty param.type}">
					<li><a class="delete" href="info/ajaxjcsqDel.do?id={jcsq_id}" target="ajaxTodo" title="确认删除吗？"><span>删除</span></a></li>
					<li class="line">line</li>
				</c:if>
				<li><a class="edit" href="info/jcsqEdit.do?id={jcsq_id}" target="dialog" width="500" height="350" rel="jcsq" title="图纸纠错申请"><span>修改</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
					<th style="width: 200px;" orderField="jfmc">机房名称</th>
					<th orderField="zldd">错误描述</th>
					<th style="width: 100px;" orderField="jsrq">申请人</th>
					<th style="width: 80px;" orderField="glry">申请时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="obj" items="${jcsqList}">
					<tr target="jcsq_id" rel="${obj.id}">
						<td>${obj.jfmc }&nbsp;</td>
						<td>${obj.cwsm }</td>
						<td>${obj.sqr }</td>
						<td><fmt:formatDate value="${obj.sqsj }" pattern="yyyy-MM-dd hh:mm" /></td>
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