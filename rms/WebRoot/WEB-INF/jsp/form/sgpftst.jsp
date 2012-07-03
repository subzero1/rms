<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script type="text/javascript">
	$(function(){
		$("#mc").keyup(function(e){
			if (e.which == 13){
				$("#searchButton",navTab.getCurrentPanel()).click();
			}
		});
	});
</script>

<form id="pagerForm" method="post" action="">
	<input type="hidden" name="mc" value="${param.mc}">
	<input type="hidden" name="dq" value="${param.dq}">
	<input type="hidden" name="zy" value="${param.zy}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="sgpftst.do" method="post">
			<input type="hidden" id="selectedId_demo" />
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>地区：<netsky:htmlSelect name="dq" id="dq" objectForOption="dqList" valueForOption="name" showForOption="name" value="${param.dq}" extend=""  extendPrefix="true" /></td>
						<td>专业：<netsky:htmlSelect name="zy" id="zy" objectForOption="zyList" valueForOption="name" showForOption="name" value="${param.zy}" extend=""  extendPrefix="true" /></td>
						<td>单位名称：<input id="mc" name="mc" value="${param.mc}" type="text" size="25" />
						<input type="text" style="display:none;"/>
						</td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="searchButton" onClick="javascript:searchOrExcelExport(this,'sgpftst.do',navTabSearch);">检 索</button></div></div></li>
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
					
					<th orderField="mc">单位名称</th>
					<th style="width: 50px;" orderField="zy">专业</th>
					<th style="width: 50px;" orderField="dq">地区</th>
					<th style="width: 60px;" orderField="fezb_val">计划份额</th>
					<th style="width: 60px;" orderField="sjfe">实际份额</th>
					<th style="width: 70px;" orderField="pcl">份额偏差率</th>
					<th style="width: 70px;" orderField="pcldj">偏差率档级</th>
					<th style="width: 60px;" orderField="zhdf">综合得分</th>
					<th style="width: 70px;" orderField="zjgcs">在建工程数</th>
					<th style="width: 70px;" orderField="zdgcs_val">最大工程数</th>
					<th style="width: 100px;" orderField="jyed">交易额</th>
					<th style="width: 100px;" orderField="gljye_val">关联交易额</th>
				</tr>
			</thead>
			<tbody>
			<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${vc1List}">
				<c:set var="offset" value="${offset+1}"/>
					<tr target="vc1" rel="${obj.id}">
						<td>${obj.mc }</td>
						<td>${obj.zy }</td>
						<td>${obj.dq }</td>
						<td><fmt:formatNumber pattern="0.00%" value="${obj.fezb_val/100 }"/></td>
						<td><fmt:formatNumber pattern="0.00%" value="${obj.sjfe }"/></td>
						<td><fmt:formatNumber pattern="0.00%" value="${obj.pcl }"/></td>
						<td>${obj.pcldj }</td>
						<td>${obj.zhdf }</td>
						<td><fmt:formatNumber pattern="0" value="${obj.zjgcs }"/></td>
						<td><fmt:formatNumber pattern="0" value="${obj.zdgcs_val }"/></td>
						<td><fmt:formatNumber pattern="0.00" value="${obj.jyed }"/></td>
						<td><fmt:formatNumber pattern="0.00" value="${obj.gljye_val }"/></td>
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