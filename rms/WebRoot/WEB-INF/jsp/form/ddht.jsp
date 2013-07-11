<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="now" class="java.util.Date" />
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head> 

		<title>定单回单</title>

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
				$.pdialog.closeCurrent();
			}
		});
	});
	
	function remindSecond(obj){
		if(obj.value == '是'){
			alertMsg.info("请在<font color=red><b>回单备注</b></font>中填写二次回单原因!");	
		}
	}
	</script>
	</head>

	<body>

	<form  id="ddhtForm" action="save.do" enctype="multipart/form-data" method="post" onsubmit="return iframeCallback(this, dialogAjaxDone);">
		<div style="float:left;padding:5px !important;display:inline; overflow-x:hidden;overflow-y:auto;" layoutH="25">
		 <input type="hidden" name="tableInfomation" value="noFatherTable:com.rms.dataObjects.form.Td00_gcxx" />
		 <input type="hidden" name="tableInfomation" value="Td00_gcxx,id,project_id:com.rms.dataObjects.form.Td09_ddhdxx" />
		 <input type="hidden" name="slaveTable" value="com.netsky.base.dataObjects.Te01_slave"/>
		 <input type="hidden" name="slaveType" value="ftp"/>
		 
		 <input type="hidden" name="ServiceName" value="setIomDdztService"/>
		 <input type="hidden" name="ServiceFunction" value="load"/>
		 <input type="hidden" name="ServicePerproty" value="Td00_gcxx.ID"/> 
			
		 <input type="hidden" name="_callbackType" value="forward" />
		 <input type="hidden" name="_message" value="保存" />
		 <input type="hidden" name="_forwardUrl" value="openForm.do?project_id=${param.project_id }&module_id=114&doc_id=${param.doc_id }&user_id=${user.id }&limit=&node_id=${param.node_id }" />
		 <input type="hidden" name="_navTabId" value="ddxx" />
		 <input type="hidden" name="Td00_gcxx.ID" value="${param.project_id }" />
		 <c:if test="${param.node_id==11401}">
		 	<input  type="hidden" name="Td00_gcxx.WCSJ" value="<fmt:formatDate value="${now }" pattern="yyyy-MM-dd"/>" />
		 	<input type="hidden" name="Td00_gcxx.SFCQ" id="sfcq" value="${Td00_gcxx.sfcq}" readonly/>
		 </c:if>
		 <input  type="hidden" name="cq_flag" id="cq_flag" value="${cq_flag }" />
		 	<div class="pageFormContent" style="border: 0px;">
				<p>
					<label>分光器编码：</label>
					<input class="required" type="text" style="width:200px;" name="Td00_gcxx.WCFGQBM" value="${Td00_gcxx.wcfgqbm}" />
				</p>
				<p>
					<label>工单状态：</label>
					<netsky:htmlSelect name="Td00_gcxx.GDZTZT" objectForOption="gdztztList" style="width:127px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true"  value="${Td00_gcxx.gdztzt}" htmlClass="td-select"/>
				</p>
				<div style="height:0px;"></div>
				
				<c:if test="${param.node_id==11401}">
				<p>
					<label>二次来单&nbsp;&nbsp;&nbsp;&nbsp;：</label>
					<select   style="width:200px;" name="Td00_gcxx.SFTS" value="${Td00_gcxx.sfts}" onchange="javascript:remindSecond(this)">
						<option value="否">否</option>
						<option value="是">是</option>
					</select>
				</p>
				<p>
					<select name="Td09_ddhdxx.ISHT" style="margin-left:44px;">
					<option value="前进"></option>
					<option value="回退设计">回退设计</option>
					<option value="回退施工">回退施工</option>
					</select>
				</p>
				</c:if>
				<div style="height:0px;"></div>
				<p>
					<label>回单备注&nbsp;&nbsp;&nbsp;&nbsp;：</label>
					<textarea name="Td00_gcxx.HDBZ" id="bz"
						style='width: 425px; height: 40px' >${Td00_gcxx.hdbz }</textarea>
				</p>
	</div>
		 <table   id="ddhd" style="margin-left: 30px;">
			<tr>
				<th>
					回单内容：&nbsp;&nbsp;&nbsp;&nbsp;
				</th>
				<td>
					<textarea name="Td09_ddhdxx.NR" id="nr"
						style='width: 425px;height: 93px'></textarea>
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
					<input type="file" name="the_file" id="the_file" size="29" />
				</td>
			</tr>
		</table>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" id="submitbutton">
								保 存
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
		
		</div>
		</form>
	</body>
</html>
<script type="">
	var sfcq = $("#sfcq");
	if(sfcq.size() > 0 && sfcq.val() == '是'){
		alertMsg.info("本次回单已超期，请在<font color=red><b>回单备注</b></font>中填写超期原因!");
	}	
</script>
