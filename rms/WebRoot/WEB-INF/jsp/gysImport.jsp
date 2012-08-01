<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="now" class="java.util.Date" />

<script language="javascript">
function uploadSlave(butt){
	var up_form = $(butt).closest("form");
	if($("#the_file",up_form).val()==""){
		alertMsg.error("请选择文件");
	}else{
		up_form.submit();
	}
}
</script>

<div class="page">
	<div class="pageContent">
		<form enctype="multipart/form-data" action="form/gysImport.do" method="post" onsubmit="return iframeCallback(this, <c:out value="${param.callback }" default="dialogAjaxDone"/>);">
			<div class="pageFormContent" layoutH="56">
				<div id="fileQueue" class="fileQueue"></div>
				<p>
					<label>附件选择：</label>
					<input type="file" name="the_file" id="the_file" size="23"/> 
					<input type="hidden" name="project_id" value="${param.project_id}"/>
				</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="javascript:uploadSlave(this);">上传文件</button>
					<!-- <button type="Button" onclick="javascript:setAndUpload(this);">上传文件</button> -->
				</div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
	</div>
</div>	