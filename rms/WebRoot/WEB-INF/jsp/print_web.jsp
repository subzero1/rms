<?xml version="1.0" encoding="GBK"?>

<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<page>
	<page-header font="Simhei" font-size="8pt" color="black"></page-header>
	<page-footer font="Simhei" font-size="8pt" align="right"></page-footer>
	<total-pages></total-pages>

	<c:forEach var="print" items="${module_map}">
		<c:if test="${print.module_id == 101 || print.module_id == 102}">
			<c:forEach begin="1" end="${print.pages}">
				<c:set var="td11_jfpmsq" value="${print.td11_jfpmsq}" scope="request"/>
				<c:set var="td12_gljf" value="${print.td12_gljf}" scope="request"/>
				<jsp:include page="print/p_jfpmsq.jsp" flush="true"></jsp:include>
			</c:forEach>
		</c:if>
	</c:forEach>
	<cover show="false"></cover>
</page>
