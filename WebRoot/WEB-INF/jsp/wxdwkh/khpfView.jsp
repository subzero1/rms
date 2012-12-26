<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<div class="page">
	<div class="pageHeader">
		<span style="font-size:14px;font-weight:bold;">${tf19.khmc }</span>
		&nbsp;&nbsp;<span style="color:#888;font-weight:bold;">[ <fmt:formatDate value="${tf19.khkssj }" pattern="yyyy年MM月dd日"/> —— <fmt:formatDate value="${tf19.khjssj }" pattern="yyyy年MM月dd日"/> ]</span>
	</div>
	<div class="pageContent">	
		<table class="table" layouth="53">
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
						<c:set var="hzdw_id" ><c:out value="${hzdw.id}"/></c:set>
						<c:set var="khx_id" ><c:out value="${khx.id}"/></c:set>								
						<td text-align: center; color:blue;">	
							${khpfMap[hzdw_id][khx_id].fz }
						</td>
					</c:forEach>
				</tr>
				</c:forEach>
			</tbody>
		</table>	
	</div>
</div>
