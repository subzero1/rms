<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>My JSP 'test.jsp' starting page</title>
		<script type="text/javascript" src="js/jquery/jquery-1.7.1.js"></script>
		<script type="text/javascript" src="js/src/netsky.cascade.js"></script>
		<script type="text/javascript">
    	$(function(){
    		$("#parentSelect1").cascade({
				childSelect:$("#chlidSelect1"),
				tableName:'Ta03_user',
				conditionColumn:'dept_id',
				valueForOption:'id',
				extendColumns:{
								login_id:'login_id',
								passwd:'passwd',
								title:'name'
				},
				showForOption:{
								pattern:'[name]\[[login_id]\]',
								name:'name',
								login_id:'login_id'
				}
			});
    	
    		$("#parentSelect").cascade({
				childSelect:$("#childSelect"),
				tableName:'Ta03_user ta03,Ta01_dept dept',
				conditionColumn:'ta03.dept_id',
				valueForOption:'ta03.id',
				whereClause:'ta03.dept_id=dept.id',
				extendColumns:{
								login_id:'ta03.login_id',
								passwd:'ta03.passwd',
								title:'ta03.name'
				},
				showForOption:{
								pattern:'[name]\[[login_id]\],[testname]',
								name:'ta03.name',
								login_id:'ta03.login_id',
								testname:'dept.name'
				}
			});	
    	});
    </script>
	</head>

	<body>
	单表：<select id="parentSelect1">
			<option></option>
			<option value="4">
				4
			</option>
		</select>
		<select id="chlidSelect1">
		</select>
		<br/>
		多表：<select id="parentSelect">
			<option></option>
			<option value="4">
				4
			</option>
		</select>
		<select id="childSelect">
		</select>
	</body>
</html>
