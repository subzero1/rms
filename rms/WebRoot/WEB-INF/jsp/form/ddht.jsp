<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="now" class="java.util.Date" />


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head> 

		<title>订单回单</title>

 <script type="text/javascript">
	function addFj(tableId){
		var $table=$("#"+tableId,$.pdialog.getCurrent());
		var rownum = $("#"+tableId,$.pdialog.getCurrent()).find("tr").length;
		var Text = "<input type=\"file\" name=\"the_file1" + rownum + "\" size=\"24\"/>"; 
		$table.append("<tr><th></th><td>"+Text+"</td></tr>");
		
	}
	$(function(){
		var $submitButton=$("#submitbutton",$.pdialog.getCurrent());
		$submitButton.click(function(){
			var $form=$("#ddhtForm",$.pdialog.getCurrent());
			var $textarea=$("#nr",$.pdialog.getCurrent()); 
			if($textarea.val()==''){
				alertMsg.info("内容不能为空!");
			}else{
				$form.submit();
			}
		});
	});
	</script>
	</head>

	<body>
	<form  id="ddhtForm" action="save.do" enctype="multipart/form-data" method="post" onsubmit="return iframeCallback(this, dialogAjaxDone);">
		<div style="float:left;padding:5px !important;display:inline; overflow-x:hidden;overflow-y:auto;" layoutH="50">
		 <input type="hidden" name="tableInfomation" value="noFatherTable:com.rms.dataObjects.form.Td09_ddhdxx" />
		 <input type="hidden" name="slaveTable" value="com.netsky.base.dataObjects.Te01_slave"/>
		 <input type="hidden" name="slaveType" value="ftp"/>
		 <input type="hidden" name="_callbackType" value="forward" />
		 <input type="hidden" name="_message" value="保存" />
		 <input type="hidden" name="_forwardUrl" value="openForm.do?project_id=${param.project_id }&module_id=${param.module_id}&doc_id=${param.project_id }&user_id=${user.id }&limit=&node_id=11401" />
		 <input type="hidden" name="_navTabId" value="ddht" />
		 <table   id="ddhd">
			<tr>
				<th>
					回单内容：
				</th>
				<td>
					<textarea name="Td09_ddhdxx.NR" id="nr"
						style='width: 98%; height: 120px'></textarea>
				 <input type="hidden" name="Td09_ddhdxx.PROJECT_ID" value="${param.project_id }"/>
				 <input type="hidden" name="Td09_ddhdxx.ID" value=""/>
				 <input type="hidden" name="Td09_ddhdxx.HDR" value="${user.name }"/>
				 <input type="hidden" name="Td09_ddhdxx.HDRDH" value="${user.mobile_tel }"/>
				 <input type="hidden" name="Td09_ddhdxx.HDBM" value="${user.dept_name}"/>
				 <input type="hidden" name="Td09_ddhdxx.HDSJ" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
				 <input type="hidden" name="Td09_ddhdxx.HDGW" 
				 <c:choose>
				 	<c:when test="${param.node_id==11401}">value='项目管理员'</c:when>
				 	<c:when test="${param.node_id==11402}">value='设计单位'</c:when>
				 	<c:when test="${param.node_id==11403}">value='施工单位'</c:when>
				 	<c:otherwise></c:otherwise>
				 </c:choose> /> 
				</td>
			</tr>
			<tr>
				<th>
					附&nbsp;&nbsp;件：
					<img src="Images/add.png"
						onclick="javascript:addFj('ddhd')" style="cursor: pointer"
						title="添加附件" />
					<input type="hidden" name="Te01_slave.ID" value="${param.slave_id}"/>
					<input type="hidden" name="Te01_slave.PROJECT_ID" value="${param.project_id}"/>
					<input type="hidden" name="Te01_slave.DOC_ID" value="${param.doc_id }"/>
					<input type="hidden" name="Te01_slave.MODULE_ID" value="${param.module_id}"/>
					<input type="hidden" name="Te01_slave.USER_ID" value="${user.id}"/>
					<input type="hidden" name="Te01_slave.USER_NAME" value="${user.name}"/>
					<input type="hidden" name="Te01_slave.FTP_DATE" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
					<input type="hidden" name="Te01_slave.FILE_NAME" value="1"/>
					<input type="hidden" name="Te01_slave.EXT_NAME" value="1"/>
					<input type="hidden" name="Te01_slave.FTP_URL" value="1"/>
					<input type="hidden" name="Te01_slave.SLAVE_TYPE" value="其他" />
				</th>
				<td>
					<input type="file" name="the_file" id="the_file" size="24" />
				</td>
			</tr>
		</table>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" id="submitbutton">
								保存
							</button>
						</div>
					</div>
				</li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="Button" class="close">
								取 消
							</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
		</form>
	</body>
</html>
