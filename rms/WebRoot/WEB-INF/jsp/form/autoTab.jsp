<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>生成數據庫表數據字典頁面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="js/jquery/jquery-1.7.1.js" type="text/javascript"></script>
<script type="text/javascript">
function autoTab(){
	$tableName=$("input[name='tableName']");
	alert($tableName.val());
	$.ajax({
		type:'post',
		dataType:'html',
		url:'form/autoGenerateTab.do',
		data:{tableName:$tableName.val()},
		async:false,
		success:function(msg){
			alert("sfsf"); 
		}
	});
} 
</script>
  </head>
  
  <body>
  <div align="center">
  <form action="">
    <table>
    <tr><td colspan="2">填写表的信息</td></tr>
    	<tr><td>表名</td><td><input type="text" name="tableName" value=""/></td></tr>
    	<tr><td></td><td></td></tr>
    	<tr><td></td><td></td></tr>
    	<tr><td></td><td></td></tr>
    	<tr><td><input type="reset" value="清空"/></td><td><input type="button" value="提交" onclick="autoTab()"/></td></tr>
    </table> <br>
    
    </div>
  </body>
</html>
