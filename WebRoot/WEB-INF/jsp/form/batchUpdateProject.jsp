<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script type="text/javascript">
	$(function(){
		$("#importbutton",$.pdialog.getCurrent()).click(function(){
			if ($("#file",$.pdialog.getCurrent()).val()==""){
				alertMsg.error("请选择导入EXCEL文件！");
				return false;
			}
			alertMsg.confirm("该操作将删除所有原有信息，并进行全新的导入。建议您首先导出当前的基础材料信息到EXCEL文件后再进行导入操作。导入前请您先行下载EXCEL模板，根据模板格式进行数据录入，以避免因导入数据产生的问题。现在继续吗？", {
				okCall: function(){
					$("#importform",$.pdialog.getCurrent()).submit();
				}
			});
		});
	});
</script>

<div class="page">
	<div class="pageContent">
		<form method="post" action="wxdw/jcclImport.do" enctype="multipart/form-data" id="importform" onsubmit="return iframeCallback(this,dialogAjaxDone);">
			<input type="hidden" name="dispatchStr" value="dispath.do?url=excel/importSuccess.jsp"/>
			<input type="hidden" name="packgePath" value="com.rms.dataObjects.form"/>
			<div class="pageFormContent" layoutH="56">
				<p style="">
					<label>EXCEL文件</label>
					<input id="file" type="file" name="file" size="20"/>
				</p>
				<p style="">
					<label>更新对象</label>
					<select name="config">
						<option value="batch_update_xm">建设项目</option>
						<option value="batch_update_gc">单项工程</option>
					</select>
				</p>
				<div class="divider"></div>
			</div>	
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="importbutton">导 入</button></div></div></li>	
					<li><div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div></li>
				</ul>
			</div>
		</form>
	</div>
</div>