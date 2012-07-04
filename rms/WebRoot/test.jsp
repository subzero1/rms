<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>My JSP 'test.jsp' starting page</title>
		<script type="text/javascript" src="js/jquery/jquery-1.7.1.js"></script>
		<script type="text/javascript" src="js/src/netsky.cascade.js"></script>
		<script type="text/javascript">
    	$(function(){
    		$("#parentSelect").cascade({
			childSelect:$("#childSelect"),
			tableName:'Ta03_user',
			conditionColumn:'dept_id',
			valueForOption:'id',
			extendColumns:{
							login_id:'login_id',
							passwd:'passwd',
							title:'name'
			},
			showForOption:{
							pattern:'name(login_id)',
							name:'name',
							login_id:'login_id'
			}
		});
    	});
    </script>
	</head>

	<body>
		<select id="parentSelect">
			<option></option>
			<option value="4">
				4
			</option>
		</select>
		<br />
		<select id="childSelect">
		</select>
	</body>
</html>
