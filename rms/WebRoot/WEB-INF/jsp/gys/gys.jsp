<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>工程概预算</title>
<meta http-equiv="content-type" content="text/html; charset=GBK" />
<link rel="stylesheet" media="screen,projection" type="text/css" href="../css/main.css" />
<!--[if lte IE 6]><link rel="stylesheet" type="text/css" href="../css/main-msie.css" /><![endif]-->
<link rel="stylesheet" media="print" type="text/css" href="../css/print.css" />
<script type="text/javascript" src="../js/prototype.js"></script>
<script type="text/javascript" src="../js/main.js"></script>
<script type="text/javascript" src="../js/appCommon.js"></script>

</head>
<body>
   	<div id="auto-in">
   		<div class="in-l in-m"> <a href="../exportGys.do?project_id=${project_id }" >Excel导出</a></div><div class="in-r"></div>
   		<div class="in-l in-m" onclick="javascript:doClose();" >关 闭</div><div class="in-r"></div>
  	</div>
	<div style="height:35px"></div>
	<div class="title01">
		<h1 class="f-left">工程名称：${td00_name }</h1>
		<h2 class="f-right">
		<div id="search-area" class="f-right">
		选择表格：
			<select onchange="javascript:$('data').src=this.value">
				<c:forEach var="obj" items="${selectvalue}">
					<option value="${obj.value }" >${obj.name }</option>
				</c:forEach>
			</select>
		</div>
		 </h2>
	</div> 
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td><hr></hr></td>
		</tr>
		<tr>
	    	<td bgcolor="#ffffff" id="myheight"><iframe frameborder="0" id="data" name="data" scrolling="yes" src="${defult_src }" style="height:100%;visibility: inherit;WIDTH:100%; Z-INDEX: 1;background:#ffffff"></iframe></td>
		</tr>
	</table>
	<script type="text/javascript">
		$("myheight").style.height=parent.getOperHeight()-80;
	</script>
</body>
</html>	