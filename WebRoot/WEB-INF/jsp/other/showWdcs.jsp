<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<div class="tabs" >
	<div class="tabsContent" style="height:512px;">
			<div style="text-align: right;">
			<c:forEach var="obj" items="${wdcsList}">
				<p class="slaveList" style="float:left">
					${obj.czr}&nbsp;&nbsp;
					<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${obj.czsj}"/>&nbsp;&nbsp;
				</p>
			</c:forEach>
			</div> 
	</div>
</div>