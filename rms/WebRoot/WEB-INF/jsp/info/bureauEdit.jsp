<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script type="text/javascript">
$(document).ready(function(){
	initSysManageWeb();　
	$("#delBureau").click(
			function(){
				alertMsg.confirm("删除后，数据将不可恢复，确认删除？", {
                okCall: function(){
                               $("#delBureauForm").submit();
                }});
			}
		);
});
</script>
<div class="panel" defH="260" style="width:96%;float:left;margin:10px">
		<h1>局点信息</h1>
		<div>
		<form method="post" action="save.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="tableInfomation" value="noFatherTable:com.jfms.dataObjects.base.Tc02_bureau"/>
			<input type="hidden" name="Tc02_bureau.ID" value="${bureauObj.id}"/>
			<input type="hidden" name="_callbackType" value="forward"/>
			<input type="hidden" name="_forwardUrl" value="sysManage/bureauList.do?bureau_id=${bureauObj.id}"/>
			<input type="hidden" name="_navTabId" value="bureauList"/>
			<div class="pageFormContent" style="height:185px;">
				<p>
					<label>局点名称：</label>
					<input type="text" name="Tc02_bureau.NAME"  value="${bureauObj.name}" class="required" style="width:120px;" />
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>所属区域：</label>
					<netsky:htmlSelect name="Tc02_bureau.P_AREA" style="width:125px;" objectForOption="areaList" valueForOption="name" showForOption="name" value="${bureauObj.p_area}" extend="" extendPrefix="true"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>局点性质：</label>
					<netsky:htmlSelect name="Tc02_bureau.TYPE" style="width:125px;" objectForOption="jdxzList" valueForOption="name" showForOption="name" value="${bureauObj.type }" />
				</p>
				
				<div class="divider"></div>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保 存</button></div></div></li>
					<li><div class="button"><div class="buttonContent"><button type="Button" class="divFileReload" loadfile="sysManage/bureauEdit.do">新 建</button></div></div></li>
					<c:if test="${not empty bureauObj}">
					<li><div class="buttonActive"><div class="buttonContent" ><button type="Button" id="delBureau" >删 除</button></div></div></li>
					</c:if>
				</ul>
			</div> 
			
		</form>
		<form action="sysManage/ajaxbureauDel.do?id=${bureauObj.id}" id="delBureauForm" onsubmit="return validateCallback(this, navTabAjaxDone);" method="post"/>
		</div>
	</div>	