<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>��Ԥ���ܱ���һ��</title>
<meta http-equiv="content-type" content="text/html; charset=GBK" />
<link rel="stylesheet" media="screen,projection" type="text/css" href="../css/main.css" />
<!--[if lte IE 6]><link rel="stylesheet" type="text/css" href="css/main-msie.css" /><![endif]-->
<link rel="stylesheet" media="print" type="text/css" href="../css/print.css" />
<script type="text/javascript" src="../js/prototype.js"></script>
<script type="text/javascript" src="../js/main.js"></script>
<script type="text/javascript" src="../js/appCommon.js"></script>
</head>
<body>
<center>
 <table border="0" cellpadding="0" cellspacing="0" width="978" style="border-collapse:collapse;" class="black-title">
 	<tr>
 		<td colspan="8" class="t-center"><h2>��Ԥ���ܱ���һ��</h2></td>
 	</tr>
 	<!-- 
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
     -->
  </table>

  <table border="1" cellpadding="0" cellspacing="0" width="978" style="border-collapse:collapse;" class="black-box">
    <tr>
      <th width="30" rowspan="3">���</th>
      <th width="90" rowspan="3">��Ԥ�����</th>
      <th width="218" rowspan="3">���̻��������</th>
      <th width="" height="20" colspan="8">��Ԥ���ֵ(Ԫ)</th>
    </tr>
    <tr>
      <th rowspan="2">С�ͽ���<br/>���̷�</th>
      <th rowspan="2">��Ҫ��װ<br/>���豸��</th>
      <th rowspan="2">����Ҫ��װ��<br/>�豸�������߷�</th>
      <th rowspan="2">������װ<br/>���̷�</th>
      <th rowspan="2">��������</th>
      <th rowspan="2">Ԥ����</th>
      <th colspan="2" height="15">�ܼ�ֵ</th>
    </tr>
    <tr>
      <th height="15">�����</th>
      <th height="15">���</th>
    </tr>
    <tr>
      <th height="19" width="30">1</th>
      <th height="19" width="90">2</th>
      <th height="19" width="218">3</th>
      <th height="19" width="80">4</th>
      <th height="19" width="80">5</th>
      <th height="19" width="80">6</th>
      <th height="19" width="80">7</th>
      <th height="19" width="80">8</th>
      <th height="19" width="80">9</th>
      <th height="19" width="80">10</th>
      <th height="19" width="80">11</th>
     </tr>
     <c:forEach var="obj" items="${gysList}">
     <tr>
     	<td class="t-center">${obj.xh }</td>
     	<td>${obj.bgbh }</td>
     	<td>${obj.fymc }</td>
     	<td class="t-right">${obj.jzgcf }</td>
     	<td class="t-right">${obj.xasbf }</td>
     	<td class="t-right">${obj.bxasbf }</td>
     	<td class="t-right">${obj.azgcf }</td>
     	<td class="t-right">${obj.qtfy }</td>
     	<td class="t-right">${obj.ybf }</td>
     	<td class="t-right">${obj.rmbzj }</td>
     	<td class="t-right">${obj.wbzj }</td>
     </tr>
     </c:forEach>
 </table>
</center>
</body>

</html>