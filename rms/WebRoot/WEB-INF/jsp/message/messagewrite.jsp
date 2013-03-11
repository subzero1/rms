<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script>
	function setdept(obj){
		if(obj.value!=""){
			var url = 'MessageAjax.do';
	   		var pars = "area_id="+obj.value;
			var myAjax = new Ajax.Request(url,{method: 'post', parameters: pars, onComplete: showdeptResponse});
		}
	}function showdeptResponse(originalRequest){
		var result = originalRequest.responseText;
		var doc = new ActiveXObject("MSxml2.DOMDocument")
        doc.loadXML(result);
        var options = doc.getElementsByTagName("option");
		var dept = $("dept");
		dept.options.length=0;
		if(options!=null){
			var option = document.createElement("option");
			option.innerText = "选择部门";
			option.value = "";
			dept.appendChild(option);
			for(var i=0; i<options.length; i++){
				var option = document.createElement("option");
				option.innerText = options[i].childNodes[1].childNodes[0].nodeValue;
				option.value = options[i].childNodes[0].childNodes[0].nodeValue;
				dept.appendChild(option);
			}
		}
	}function setuser(obj){
		if(obj.value!=""){
			var url = 'MessageAjax.do';
	   		var pars = "dept_id="+obj.value;
			var myAjax = new Ajax.Request(url,{method: 'post', parameters: pars, onComplete: showUserResponse});
		}
	}function showUserResponse(originalRequest){
		var result = originalRequest.responseText;
		var doc = new ActiveXObject("MSxml2.DOMDocument")
        doc.loadXML(result);
        var users = doc.getElementsByTagName("user");
		var user_list = $("user_list");
		var addText="<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;\" class=\"data-table2\">";		
		var id = "";
		var name = "";
		var login_id = "";
		if(users!=null){
			for(var i=0; i<users.length; i++){
				id = users[i].childNodes[0].childNodes[0].nodeValue;
				name = users[i].childNodes[1].childNodes[0].nodeValue;
				login_id = users[i].childNodes[3].childNodes[0].nodeValue;
				addText += "<tr>"
				addText += "<td style=\"height:20px;\" onclick=\"javascript:chooseUser("+id+",'"+name+"')\">&nbsp;"+login_id+"--"+name+"</td>";
				addText += "</tr>"
			}
		}
		addText += "</table>";
		user_list.innerHTML = addText;
		initTable();
	}function addFj(tableId){
		var addtr = document.getElementById(tableId).insertRow(4);
		var rownum = document.getElementById(tableId).rows.length;
		addtr.insertCell(0).innerHTML = ""; 
		var Text = "<input type=\"file\" name=\"the_file1" + rownum + "\" size=\"24\"/>";
		addtr.insertCell(1).innerHTML = Text;
	}function delrow(obj){
		var num = obj.parentNode.parentNode.rowIndex;
		document.getElementById("send_message").deleteRow(num);
	}	
	function del(obj){
	 document.messagewrite.reader_name.value='';
	 document.messagewrite.reader_id.value='';
	 $(".read_div").html("");
	 jilian('user_list','Ta03_user.dept_id',$("#dept").val(),'id','name');
	}
	function selectToUser(){
		var selectO = $("#user_list option:selected");
		var $read_div=$(".read_div");
		selectO.each(function(){
		if($("#reader_name").val()==""){
			$("#reader_name").val($(this).text());
			$("#reader_id").val($(this).val());
		}else{
			$("#reader_name").val($("#reader_name").val() + "；" + $(this).text());
			$("#reader_id").val($("#reader_id").val() + "," + $(this).val());
		}
		$read_div.append("<span>"+$(this).text()+";&nbsp;</span>");
		$(this).remove();
		});
	}
	function selectToUserMutiple(){
		
	}
	$(function(){
	//及连菜单
	$("#area").change(function(){
		jilian('dept','Ta01_dept.area_name',$("#area").val(),'id','name');
		$("#dept").change();
	})
	$("#dept").change(function(){
		jilian('user_list','Ta03_user.dept_id',$("#dept").val(),'id','name');
	})
	
	$("#submitbutton").click(function(){
		if($("#reader_id").val()==""||$("#reader_id").val()==null){
			alertMsg.info('收件人不能为空!');
			return;
		}if($("#title").val()==""||$("#title").val()==null){
			alertMsg.info('主题不能为空!');
			return;
		}
		$("#send_flag").val("1");
		$("#messagewrite").submit();
	})	
	$("#caogaobutton").click(function(){
		$("#send_flag").val("0");
		$("#messagewrite").submit();
		
	})	
	})
</script>


<div class="page">
	<div class="pageContent">
		<form name="messagewrite" id="messagewrite" action="MessageSend.do" enctype="multipart/form-data" method="post" onsubmit="return iframeCallback(messagewrite, dialogAjaxDone);">
			<input type="hidden" id="send_flag" name="send_flag"/>
			<input type="hidden" name="_callbackType" value=""/>
			<input type="hidden" name="_navTabId" value=""/>
			<input type="hidden" name="_forwardUrl" value=""/>
			<input type="hidden" name="messageState" value="${param.messageState }"/>
			<!-- left -->
			<div style="width:430px;float:left;padding:5px !important;display:inline; overflow-x:hidden;overflow-y:auto;" layoutH="50">
				<table width="400" class="read" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;" id="send_message">
					<tr>
						<th width="80px">发件人：</th>
						<td>${user.name}&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" value="1" id="repeat_flag" name="repeat_flag"/>&nbsp;需要回复</td>
					</tr>
					<tr>
						<th>收件人：</th>
						<td><div style="width:100%;height: auto;" class='read_div'></div></td>
						<td><input type="hidden" style="width:100%" id="reader_name" name="reader_name" readOnly value="${reader_name }"/><input type="hidden" id="reader_id" name="reader_id" value="${reader_id }"/><img src="Images/trash.gif" onclick="javascript:del(this);" style="cursor:pointer;" title="清空内容" /></td>
					</tr>
					<tr>
						<th>主&nbsp;&nbsp;题：</th>
						<td><input type="text" id="title" name="title" value="${title }" style="width:98%"/></td>
					</tr>
					<tr>
						<th>附&nbsp;&nbsp;件：<img src="Images/add.png" onclick="javascript:addFj('send_message')" style="cursor:pointer" title="添加附件" /></th>
						<td><input type="file" name="the_file1" size="24"/></td>
					</tr>
					<c:forEach var="fj_list" items="${fj_list}">
					<tr>
						<td rowsplan="${rowsnum}"><input type="hidden" id="zffjid" name="zffjid" value="${fj_list.id }"></td>
						<td><a href="download.do?formurl=${fj_list.ftp_url}&ext_name=${fj_list.ext_name}&slave_name=${fj_list.file_name}">${fj_list["file_name"] }</a>  
							<a href="download.do?formurl=${fj_list.ftp_url}&ext_name=${fj_list.ext_name}&slave_name=${fj_list.file_name}"><b>下载</b></a>
							<a href="#" onclick="javascript:delrow(this)"><b>移除</b></a></td>
					</tr>
					</c:forEach>
					<tr>
						<th>内&nbsp;&nbsp;容：</th>
						<td><textarea name="content" id="content" style='width:98%;height:120px'>${content }</textarea></td>
					</tr>
				</table>
				<div  align="right"><input type="button" class="button-td" style="width:20px;height:20px;" onclick="javascript:selectToUser();" value="∧
				" /></div>
			</div>
			<!-- right -->
			<div style="width:200px;text-align:right;padding:5px; 5px;">
				<select name="area" id="area" style="width:80px;">
			    	<c:forEach var="area" items="${areaList}">
						<c:choose>
							<c:when test="${user.area_name == area.name}">
								<option selected value="${area.name}">${area.name}</option>
							</c:when>
							<c:otherwise>
								<option value="${area.name}">${area.name}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
			    </select>
			    
			    <select name="dept" id="dept" style="width:115px;">
			    	<c:forEach var="dept" items="${user_dept_list}">
						<c:choose>
							<c:when test="${user_dept_id == dept[0]}">
								<option selected value="${dept[0]}">${dept[1]}</option>
							</c:when>
							<c:otherwise>
								<option value="${dept[0]}">${dept[1]}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
			    </select>
			    <select id="user_list" name="user_list" multiple="1" type="select-multiple" style="width:198px;height:240px;" ondblclick="javascript:selectToUser();">
				<c:forEach var="user" items="${user_list}">
					<option value="${user.id }">${user.name }</option>
				</c:forEach>
				</select>
			</div>
			<div class="formBar">
				<div class="button" style="float:left;"><div class="buttonContent"><button type="Button" id="caogaobutton">存草稿</button></div></div>
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="submitbutton">发 送</button></div></div></li>	
					<li><div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div></li>
				</ul>
			</div>
		</form>
	</div>
</div>