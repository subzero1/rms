<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script language="javascript">
$(function(){
	$("#dfrId",navTab.getCurrentPanel()).change(function(){
		$(this).closest("form").submit();
	});
});
</script>

<div class="page">
	<div class="pageHeader">
		<form action="wxdwkh/khpfMx.do" method="post" onsubmit="return navTabSearch(this);">
			<input type="hidden" name="khxx_id" value="${param.khxx_id }"/>
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>
						<input type="text" style="display:none"/>
						打分人：<netsky:htmlSelect name="dfr_id" id="dfrId" objectForOption="dfrList" valueForOption="id" showForOption="name" value="${dfr_id}" extendPrefix="true" /></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<table class="table" layouth="82">
			<thead>
				<tr>
					<th style="width: 240px;">合作单位</th>
					<c:forEach items="${khxList}" var="khx">
						<th style="width: 120px;">${khx.khx }</th>
					</c:forEach>
				</tr>
			</thead>
			<tbody id="pfbody">
				<c:forEach items="${hzdwList}" var="hzdw">
				<tr>
					<td>${hzdw.mc }</td>
					<c:forEach items="${khxList}" var="khx">
						<td style="text-align: center">
							<c:set var="hzdw_id" ><c:out value="${hzdw.id}"/></c:set>
							<c:set var="khx_id" ><c:out value="${khx.id}"/></c:set>	
							<c:if  test="${not empty khpfMap[hzdw_id][khx_id] }">
								${khpfMap[hzdw_id][khx_id].fz }
							</c:if>
						</td>
					</c:forEach>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
