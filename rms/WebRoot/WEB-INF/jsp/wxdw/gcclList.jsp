<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script type="text/javascript">
	$(function(){
		$("#gcmc").keyup(function(e){
			if (e.which == 13){
				$("#searchButton",navTab.getCurrentPanel()).click();
			}
		});
	});
</script>

<form id="pagerForm" method="post" action="">
	<input type="hidden" name="gcmc" value="${param.gcmc}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="wxdw/gcclList.do" method="post">
			<input type="hidden" id="selectedId_demo" />
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>名称：</td>
						<td><input id="gcmc" name="gcmc" value="${param.gcmc}" type="text" size="25" />
						<input type="text" style="display:none;"/>
						</td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="searchButton" onClick="javascript:searchOrExcelExport(this,'wxdw/gcclList.do',navTabSearch);">检 索</button></div></div></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
			</ul>
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
					<th orderField="gcmc">工程名称</th>
					<th style="width: 150px;" orderField="gcbh">工程编号</th>
					<th style="width: 100px;" orderField="sjkgsj">开工日期</th>
					<th style="width: 100px;" orderField="sjjgsj">竣工日期</th>
					<th style="width: 360px;">动作</th>
				</tr>
			</thead>
			<tbody>
			<c:set var="offset" value="1"/>
			<c:if test="${pageNum == 1}">
			<c:set var="offset" value="${offset+1}"/>
				<tr>
						<td style="color:blue">预领料工程</td>
						<td></td>
						<td></td>
						<td></td>
						<td>
						<a style="margin-left:3px;color:darkblue" href="wxdw/crkEdit.do?dz=0" target="navTab" rel="crk" title="入库">入库</a>
						<a style="margin-left:3px;color:darkblue" href="wxdw/crkMxList.do?dz=0" target="navTab" rel="crkMx" title="入库明细">入库明细</a>
						<a style="margin-left:3px;color:darkblue" href="wxdw/crkMxList.do?dz=1" target="navTab" rel="crkMx" title="出库明细">出库明细</a>
						<a style="margin-left:3px;color:darkblue" href="wxdw/crkMxList.do?dz=2" target="navTab" rel="crkMx" title="缴料明细">缴料明细</a>
						<a style="margin-left:3px;color:darkblue" href="wxdw/gcKcList.do" target="navTab" rel="gcKc" title="材料信息">材料信息</a>
						</td>
					</tr>
			</c:if>
				<c:forEach var="obj" items="${gcxxList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr>
						<td>${obj.gcmc }</td>
						<td>${obj.gcbh }</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${obj.sjkgsj }"/></td>
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${obj.sjjgsj }"/></td>
						<td>
						<c:if test="${empty obj.sjjgsj }">
						<a style="margin-left:3px;color:darkblue" href="wxdw/crkEdit.do?dz=0&project_id=${obj.id }" target="navTab" rel="crk" title="入库">入库</a>
						<a style="margin-left:3px;color:darkblue" href="wxdw/crkEdit.do?dz=1&project_id=${obj.id }" target="navTab" rel="crk" title="出库">出库</a>
						</c:if>
						<a style="margin-left:3px;color:darkblue" href="wxdw/crkEdit.do?dz=2&project_id=${obj.id }" target="navTab" rel="crk" title="缴料">缴料</a>
						<a style="margin-left:3px;color:darkblue" href="wxdw/crkMxList.do?dz=0&project_id=${obj.id }" target="navTab" rel="crkMx" title="入库明细">入库明细</a>
						<a style="margin-left:3px;color:darkblue" href="wxdw/crkMxList.do?dz=1&project_id=${obj.id }" target="navTab" rel="crkMx" title="出库明细">出库明细</a>
						<a style="margin-left:3px;color:darkblue" href="wxdw/crkMxList.do?dz=2&project_id=${obj.id }" target="navTab" rel="crkMx" title="缴料明细">缴料明细</a>
						<a style="margin-left:3px;color:darkblue" href="wxdw/gcKcList.do&project_id=${obj.id }" target="navTab" rel="gcKc" title="材料信息">材料信息</a>
						</td>
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
				<span>共${totalCount+1}条 </span>
			</div>

			<div class="pagination" targetType="navTab"	totalCount="${totalCount}" numPerPage="${numPerPage}" currentPage="${pageNum}"></div>

		</div>
	</div>
</div>