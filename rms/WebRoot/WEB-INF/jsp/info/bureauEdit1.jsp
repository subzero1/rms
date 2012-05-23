<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page">
	<div class="pageContent">
		<form method="post" action="save.do" class="pageForm" onsubmit="return validateCallback(this,dialogAjaxDone);">
			<input type="hidden" name="tableInfomation"	value="noFatherTable:com.rms.dataObjects.base.Tc02_bureau" />
			<input type="hidden" name="Tc02_bureau.ID" value="${td01.id}" />
			<div class="pageFormContent" layoutH="56">
				<p>
					<label>局点名称：</label>
					<input class="required" name="Td01_sbxx.NAME" type="text" style="width:200px" value="${tc02.name }"  maxlength="50"/>
				</p>
				<p>
					<label>所属地区：</label>
					<netsky:htmlSelect name="Td01_sbxx.P_AREA" objectForOption="areaList" valueForOption="name" showForOption="name" value="${tc02.p_area}"/>
				</p>
				<p>
					<label>局点性质：</label>
					<netsky:htmlSelect name="Td01_sbxx.TYPE" objectForOption="typeList" valueForOption="name" showForOption="name" value="${tc02.type}"/>
				</p>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit" >保 存</button></div></div></li>				
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>