<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
 
<div class="pageContent">
	<table class="table" layoutH="70" targettype="dialog" width="100%">
		<thead>
			<tr>
				<th width="70">等级</th>
				<th width="70">取值上限</th>
				<th width="70">取值下限</th> 
			</tr>
		</thead>				
		<tbody>
			<c:forEach items="${fepclList }" var="o">
			<tr>
			<td>${o.dj }</td>
			<td>${o.qzsx }</td>
			<td>${o.qzxx }</td>
			</tr>
	 </c:forEach>
		</tbody>
	</table>

</div>