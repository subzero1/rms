<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>������װ���̷��ø�Ԥ��������</title>
<meta http-equiv="content-type" content="text/html; charset=GBK" />
<link rel="stylesheet" media="screen,projection" type="text/css" href="../css/main.css" />
<!--[if lte IE 6]><link rel="stylesheet" type="text/css" href="css/main-msie.css" /><![endif]-->
<link rel="stylesheet" media="print" type="text/css" href="../css/print.css" />
<script type="text/javascript" src="../js/prototype.js"></script>
<script type="text/javascript" src="../js/main.js"></script>
<script type="text/javascript" src="../js/appCommon.js"></script>
<script type="text/javascript" src="../js/prototype-date-extensions.js"></script>
<script type="text/javascript" src="../js/datepicker.js"></script>
</head>
<body>
<center>
<table border="0" cellpadding="0" cellspacing="0" width="978" style="border-collapse:collapse;" class="black-title">
  	<tr>
 		<td colspan="8" class="t-center"><h2>������װ���̷��ø�Ԥ��������</h2></td>
 	</tr>
    <tr>
        <th width="92">��������ƣ�</th>
        <td width="180">${dxgcmc }</td>
        <th width="92">���赥λ���ƣ�</th>
	    <td width="180">${jsdw }</td>
	    <th width="80">����ţ�</th>
	    <td width="294">${bgbh }</td>
	    <th width="60">��ȫҳ</th>
    </tr>
  </table>
 
  <table border="1" cellpadding="0" cellspacing="0" width="978" style="border-collapse:collapse;" class="black-box">
     <tr>
      <th width="39">���</th>
      <c:if test="${gys_flag=='old'}">
      <th width="100">��������</th>
      <th width="120">���ݺ��㷨</th>
      <th width="80">������(Ԫ)</th>
      <th width="80">�չ���(Ԫ)</th>
      </c:if>
      <c:if test="${gys_flag!='old'}">
      <th width="180">��������</th>
      <th width="200">���ݺ��㷨</th>     
      </c:if>
      <th width="70">�ϼ�(Ԫ)</th>
      
      <th width="39">���</th>
      <c:if test="${gys_flag=='old'}">
      <th width="100">��������</th>
      <th width="120">���ݺ��㷨</th>
      <th width="80">������(Ԫ)</th>
      <th width="80">�չ���(Ԫ)</th>
      </c:if>
      <c:if test="${gys_flag!='old'}">
      <th width="180">��������</th>
      <th width="200">���ݺ��㷨</th>     
      </c:if>
      <th width="70">�ϼ�(Ԫ)</th>
    </tr>
    <tr>
      <th>1</th>
      <th>2</th>
      <th>3</th>
      <c:if test="${gys_flag=='old'}">
      <th>4</th>
      <th>5</th>
      </c:if>
      <th><c:choose><c:when test="${gys_flag=='old'}">6</c:when><c:otherwise>4</c:otherwise></c:choose></th>
      <th>1</th>
      <th>2</th>
      <th>3</th>
      <c:if test="${gys_flag=='old'}">
      <th>4</th>
      <th>5</th>
      </c:if>
      <th><c:choose><c:when test="${gys_flag=='old'}">6</c:when><c:otherwise>4</c:otherwise></c:choose></th>
    </tr>
     <c:forEach var="obj" items="${gysList}">
     <tr>
     	<td class="t-center">${obj.xh1 }</td>
     	<td>${obj.fymc1 }</td>
     	<td>${obj.yjsf1 }</td>
     	<c:if test="${gys_flag=='old'}">
     	<td>${obj.jgf1 }</td>
     	<td>${obj.pgf1 }</td>
     	</c:if>
     	<td class="t-right">${obj.hj1 }</td>
     	<td class="t-center">${obj.xh2 }</td>
     	<td>${obj.fymc2 }</td>
     	<td>${obj.yjsf2 }</td>
     	<c:if test="${gys_flag=='old'}">
     	<td>${obj.jgf2 }</td>
     	<td>${obj.pgf2 }</td>
     	</c:if>
     	<td class="t-right">${obj.hj2 }</td>
     </tr>
     </c:forEach>
 </table>
</center>
</body>

</html>