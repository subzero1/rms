<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="dms"%>
<div class="panel"  style="width:96%;float:left;margin:10px">
		<h1>基本属性</h1>
		<div>
		<form method="post" action="save.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" id="tableInfomation" name="tableInfomation" value="noFatherTable:com.rms.dataObjects.base.Tc01_property" keep="true"/>
			<input type="hidden" name="Tc01_property.ID" value="${property_type.id}" keep="true"/>
			<input type="hidden" name="_callbackType" value="forward" keep="true"/>
			<input type="hidden" name="_forwardUrl" value="sysManage/propertySettingList.do?property_id=${property_type.id}" keep="true"/>
			<input type="hidden" name="_navTabId" value="propertySetting" keep="true"/>
			<div class="pageFormContent" style="height:200px">
				<p>
					<label>属性名称：</label>
					<input type="text" name="Tc01_property.NAME" value="${property_type.name}" class="required" style="width:146px;"/>
				</p>
				<p>
					<label>属性分类：</label>
					<dms:htmlSelect name="Tc01_property.TYPE" objectForOption="sxfl" showForOption="" valueForOption=""  value="${property_type.type}" />
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>扩展标识：</label>
					<input type="text" name="Tc01_property.FLAG" value="${property_type.flag}"  style="width:146px;"/>
				</p>
				<p>
					<label>上级类别：</label>
					<input type="text" name="Tc01_property.EXT_COL" value="${property_type.ext_col}"  style="width:146px;"/>
				</p>
				<div class="divider"></div>
				<div class="remark" style="color:#888;height:80px;">
				【注】：属性分类系统定制；<br>
				&nbsp;勘察反馈内容：扩展标识为[1]时，反馈时不需要录入个数， 为[2]时，需要录入个数<br>
				&nbsp;工程类别：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;扩展标识为[1]时，不参与自动派工
				</div>
			</div>
			<div class="formBar">
				<ul>
				   <c:if test="${empty property_type.flag || fn:indexOf(property_type.flag,'[r]') == -1}">
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保 存</button></div></div></li>
					<li><div class="button"><div class="buttonContent"><button class="divFileReload" type="Button" loadfile="sysManage/propertyEdit.do">添 加</button></div></div></li>
					<c:if test="${not empty property_type.id}">
						<li><div class="button"><div class="buttonContent"><button class="formDataClear" type="Button">删 除</button></div></div></li>
					</c:if>
				   </c:if>
				</ul>
			</div>
		</form>
		</div>
	</div>