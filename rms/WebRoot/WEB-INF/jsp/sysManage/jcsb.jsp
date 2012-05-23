<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>


<div class="page">
	<div class="pageContent">
		<form method="post" action="save.do" class="pageForm  required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
			<input type="hidden" name="_callbackType" value=""/>
			<input type="hidden" name="_navTabId" value="jcsbList"/>
			<input type="hidden" name="_forwardUrl" value=""/>
			<input type="hidden" name="tableInfomation"	value="noFatherTable:com.rms.dataObjects.base.Tc04_jcsb" />
			<input type="hidden" name="Tc04_jcsb.ID" value="${tc04.id}" />
			<input type="hidden" name="_message" value="基础设备信息保存" />
			<div class="pageFormContent" layoutH="56">
				<p>
					<label>设备名称：</label>
					<input class="required" name="Tc04_jcsb.SBMC" type="text" style="width:300px" value="${tc04.sbmc }"  maxlength="100"/>
				</p>
				<p>
					<label>设备型号：</label>
					<input class="required" name="Tc04_jcsb.SBXH" type="text" style="width:300px" value="${tc04.sbxh }"  maxlength="100"/>
				</p>
				<p>
					<label>厂 家：</label>
					<input class="required" name="Tc04_jcsb.CJ" type="text" style="width:300px" value="${tc04.cj }"  maxlength="100"/>
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