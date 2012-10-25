<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
	
<div style="text-align:left;color:blue;"><h3>&nbsp;&nbsp;目标库流转记录</h3></div><div class="divider" style="height:1px;"></div>
<div style="overflow:auto;height:300px;position:relative;">	
	 <table class="table" width="100%">
		<thead>
			<tr>
				<th style="width: 30px;">序号</th>
				<th style="width: 130px;">开始时间</th>
				<th style="width: 130px;">结束时间</th>
				<th style="width: 80px;">相关人</th>
				<th style="width: 80px;">相关人部门</th>
				<th>说明</th>
			</tr>
		</thead>
		<tbody>
		<c:set var="offset" value="0"/>
			<c:forEach items="${lzjlList}" var="lzjl">
			<c:set var="offset" value="${offset+1}"/>
				<tr>
					<td>${offset }</td>
					<td><fmt:formatDate value="${lzjl.kssj }" pattern="yyyy-MM-dd HH:mm"/></td>
					<td><fmt:formatDate value="${lzjl.jssj }" pattern="yyyy-MM-dd HH:mm"/></td>
					<td>${lzjl.xgr }</td>
					<td>${lzjl.xgr_bm }</td>
					<td>${lzjl.sm }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>