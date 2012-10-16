<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script type="text/javascript">
	$(function(){
		$("#importbutton",$.pdialog.getCurrent()).click(function(){
			if ($("#file",$.pdialog.getCurrent()).val()==""){
				alertMsg.info("请选择导入EXCEL文件！");
				return false;
			}
			alertMsg.confirm("确定导入数据吗？", {
				okCall: function(){
					$("#importform",$.pdialog.getCurrent()).submit();
				}
			});
		});
	});
	
	function dcxmgc(module_id){
		navTab.openTab('searchList', 'search/searchList.do?module_id='+module_id, {title:'项目信息'});
		$.pdialog.closeCurrent();
	}
</script>

<div class="page">
	<div class="pageContent">
		<form method="post" action="import.do" enctype="multipart/form-data" id="importform" onsubmit="return iframeCallback(this,dialogAjaxDone);">
			<input type="hidden" name="packgePath" value="com.rms.dataObjects.mbk"/>
			<input type="hidden" name="_callbackType" value="closeCurrent" />
			<input type="hidden" name="_navTabId" value="" />
			<input type="hidden" name="_forwardUrl" value="" />
			<input type="hidden" name="perproty" value=" " />
			<input type="hidden" name="config" value="mbk_source"/>
			<div class="pageFormContent" layoutH="56">
				<p style="">
					<label>EXCEL文件</label>
					<input id="file" type="file" name="file" size="20"/>
				</p>
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