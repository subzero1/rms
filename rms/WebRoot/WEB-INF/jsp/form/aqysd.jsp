<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>安全验收单</title>
		<style>
#safeForm table tbody tr input {
	width: 100%;
}

#safeForm table tbody tr td {
	width: 12%;
}

#safeForm table {
	width: 90%;
}
</style>
		<script type="text/javascript">
			function saveForm(){
				$("#safeForm",navTab.getCurrentPanel()).submit();
			}
			function delComments(obj){
				var $tr = $(obj).closest("tr");
				if($tr){	
					var valid = false;
					$tr.find(":input").each(function(){
					if(this.name != "Td52_aqys.ID"){
					$(this).val("");
					$(this).removeClass('required');
					}else{
						if($(this).val()!="")
						valid = true;
					}
					}); 
				if(valid){
					$tr.hide(); 
				}
				
				else{
					$tr.remove();
				}
				}
			}
			//追加
			function addComments(){
				$("#aqys_tbody").append("<tr>"+ 
					"<td><input  type='hidden' name='Td52_aqys.ID' value='${Td52_aqys.id}' />\
					<input type='text' name='Td52_aqys.PROJECT_ID' value='${param.project_id}' />\
					<input type='text' name='Td52_aqys.IPA' value='${Td52_aqys.ipa}' class='validateIp' onblur='checkIP(this)' title='IP地址'/></td>\
					<td><input type='text' name='Td52_aqys.PORT_NUM' value='${Td52_aqys.port_num}' /></td>\
					<td><input type='text' name='Td52_aqys.LOGIN_PROTOCOL' value='${Td52_aqys.login_protocol}'/></td>\
					<td><input type='text' name='Td52_aqys.USERNAME' value='${Td52_aqys.username}' /></td>\
					<td><input type='text' name='Td52_aqys.USERPWD' value='${Td52_aqys.userpwd}' /> </td>\
					<td><input type='text' name='Td52_aqys.SUP_USERNAME' value='${Td52_aqys.sup_username}' /></td>\
					<td><input type='text' name='Td52_aqys.SUP_USERPWD' value='${Td52_aqys.sup_username}' /></td>\
					<td><input type='text' name='Td52_aqys.DEVICE_TYPE' value='${Td52_aqys.device_type}' /></td>\
					<td><a href='#' onclick='javascript:delComments(this);' class='btnDel'><span>删除</span></a></td>\
				</tr>");
			}
			
			function checkIP(_this){
				var IPPattern=/((?:(?:25[0-5]|2[0-4]\d|((1\d{2})|([1-9]?\d)))\.){3}(?:25[0-5]|2[0-4]\d|((1\d{2})|([1-9]?\d))))/;
 				if(!IPPattern.test($(_this).val())){ 
 					$(_this).css("color","red");
 					return false;
 				} 
 				$(_this).css("color","black");
 				return true;
			}
			
			function checkForm(_this){ 
				var $tr=$("#aqys_tbody tr",$(_this));
				var flag=true;
				$tr.each(function(k,v){ 
					flag=false;
					var $input=$("input[name!='Td52_aqys\.PROJECT_ID'][name!='Td52_aqys\.ID']",$(v));
					var $Td52_aqys_PROJECT_ID=$("input[name='Td52_aqys\.PROJECT_ID']",$(v));
					$input.each(function(i,j){
						 if($(j).val()!=""){
						 	falg=true;
						 } 
					});
					if(!flag){
						//$Td52_aqys_PROJECT_ID.val("");
					} 
					
				});
				return validateCallback(_this,navTabAjaxDone);
			}
 		</script>
	</head>
	<body>
		<div class="page">
			<!-- 表单头 -->
			<div class="pageHeader">
				<div class="searchBar">
					<!-- 表单名称 -->
					<h1>
						安全验收
					</h1>

				</div>
			</div>
			<!-- 主操作按钮 -->
			<div class="panelBar">
				<ul class="toolBar">
					<c:if test="${param.canSave=='yes'}">
						<li>
							<a class="add" href="javascript:addComments();"><span>增&nbsp;&nbsp;加</span>
							</a>
						</li>
						<li class="line">
							line
						</li>
						<li>
							<a class="save dis_a" href="javascript:saveForm();"><span>保&nbsp;&nbsp;存</span>
							</a>
						</li>
						<li class="line">
							line
						</li>
					</c:if>
				</ul>
			</div>
			<form method="post" action="save.do" id="safeForm"
				class="pageForm required-validate" onsubmit="return checkForm(this)">
				<input type="hidden" name="tableInfomation"
					value="noFatherTable:com.rms.dataObjects.form.Td52_aqys" />
				<input type="hidden" name="_callbackType" value="forward" />
				<input type="hidden" name="_message" value="保存" />
				<input type="hidden" name="_forwardUrl" value="form/aqysEdit.do?project_id=${param.project_id}&canSave=${param.canSave}" />
				<input type="hidden" name="_navTabId" value="aqys" />
				<table class="table" width="80%">
					<thead>
						<tr>
							<th style="width: 100px;">
								IP地址
							</th>
							<th style="width: 100px;">
								端口号
							</th>
							<th style="width: 100px;">
								登录协议
							</th>
							<th style="width: 100px;">
								用户名
							</th>
							<th style="width: 100px;">
								密码
							</th>
							<th style="width: 100px;">
								超级用户账号
							</th>
							<th style="width: 100px;">
								超级用户密码
							</th>
							<th style="width: 100px;">
								设备类型
							</th>
							<th style="width: 30px;">

							</th>
						</tr>
					</thead>
					<tbody id="aqys_tbody">
						<c:forEach var="Td52_aqys" items="${Td52_aqysList}">
							<tr>
								<td>
									<input type="hidden" name="Td52_aqys.ID"
										value="${Td52_aqys.id}" />
									<input type="text" name="Td52_aqys.PROJECT_ID"
										value="${param.project_id}" />
									<input type="text" name="Td52_aqys.IPA"
										value="${Td52_aqys.ipa}" class="validateIp"
										onblur="checkIP(this)" title="IP地址" />
								</td>
								<td>
									<input type="text" name="Td52_aqys.PORT_NUM"
										value="${Td52_aqys.port_num}" />
								</td>
								<td>
									<input type="text" name="Td52_aqys.LOGIN_PROTOCOL"
										value="${Td52_aqys.login_protocol}" />
								</td>
								<td>
									<input type="text" name="Td52_aqys.USERNAME"
										value="${Td52_aqys.username}" />
								</td>
								<td>
									<input type="text" name="Td52_aqys.USERPWD"
										value="${Td52_aqys.userpwd}" />
								</td>
								<td>
									<input type="text" name="Td52_aqys.SUP_USERNAME"
										value="${Td52_aqys.sup_username}" />
								</td>
								<td>
									<input type="text" name="Td52_aqys.SUP_USERPWD"
										value="${Td52_aqys.sup_username}" />
								</td>
								<td>
									<input type="text" name="Td52_aqys.DEVICE_TYPE"
										value="${Td52_aqys.device_type}" />
								</td>
								<td>
									<a href="#" onclick="javascript:delComments(this);"
										class="btnDel"><span>删除</span> </a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
		</div>
	</body>
</html>
