<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>���̽��������Ѹ�Ԥ������壩��</title>
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
 		<th colspan="8" class="t-center"><h2>���̽��������Ѹ�Ԥ������壩��</h2></th>
 	</tr>
    <tr>
        <th width="92">������Ŀ���ƣ�</th>
        <td width="180">${jsxmmc }</td>
        <th width="92">��������ƣ�</th>
	    <td width="180">${dxgcmc }</td>
	    <th width="92">���赥λ���ƣ�</th>
	    <td width="180">${jsdw }</td>
	    <th width="72">����ţ�</th>
	    <td width="90">${bgbh }</td>
    </tr>
  </table>
   <table border="1" cellpadding="0" cellspacing="0" width="978" style="border-collapse:collapse;" class="black-box">
    <tr>
      <th height="19" width="30">���</th>
      <th height="19" width="200">��������</th>
      <th height="19" width="188">�������ݼ�����</th>
      <th height="19" width="90">�ϼ�</th>
      <th height="19" width="200">��ע</th>
    </tr>
    <tr>
      <th>1</th>
      <th>2</th>
      <th>3</th>
      <th>4</th>
      <th>5</th>
    </tr>
    
     <c:set var="offset" scope="page" value="0"/>
     <c:forEach var="obj" items="${gysList}">
     <tr>
        <c:set var="offset" scope="page" value="${offset + 1}"/>
     	<td class="t-center">${obj.xh }</td>
     	<td>${obj.fymc }</td>
     	<td>${obj.yjsf }</td>
     	<td class="t-right">${obj.hj }</td>
     	<td>${obj.bz }</td>
     </tr>
     </c:forEach>
     <c:forEach begin="1" end="${15-(offset>15?15:offset)}">
      <tr>
     	<td class="t-right">&nbsp;</td>
     	<td class="t-right">&nbsp;</td>
     	<td class="t-right">&nbsp;</td>
     	<td class="t-right">&nbsp;</td>
     	<td class="t-right">&nbsp;</td>
     </tr>
     </c:forEach>
  </table>
 </center>
</body>

</html>