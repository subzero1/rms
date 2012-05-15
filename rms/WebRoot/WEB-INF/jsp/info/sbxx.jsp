<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>


<div class="page">
	<div class="pageContent">
		<form method="post" action="save.do" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
			<input type="hidden" name="tableInfomation"	value="noFatherTable:com.jfms.dataObjects.info.Td01_sbxx" />
			<input type="hidden" name="Td01_sbxx.ID" value="${td01.id}" />
			<input type="hidden" name="Td01_sbxx.JFXX_ID" value="<c:out value="${td01.jfxx_id}" default="${param.jfxx_id }"/>" />
			<input type="hidden" name="_callbackType" value=""/>
			<input type="hidden" name="_message" value="设备信息保存" />
			<input type="hidden" name="_navTabId" value="jfpm"/>
			<div class="pageFormContent" layoutH="56">
				<p>
					<label>设备名称：</label>
					<input class="required" name="Td01_sbxx.SBMC" type="text" style="width:420px" value="${td01.sbmc }"  maxlength="100"/>
				</p>
				<p>
					<label>设备型号：</label>
					<input class="required" name="Td01_sbxx.SBXH" type="text" style="width:420px" value="${td01.sbxh }"  maxlength="100"/>
				</p>
				<p>
					<label>机架尺寸：</label>
					<input class="" name="Td01_sbxx.JJCC" type="text" style="width:155px" value="${td01.jjcc }"  maxlength="25"/>
				</p>
				<p>
					<label>厂 家：</label>
					<input class="required" name="Td01_sbxx.CJ" type="text" style="width:155px" value="${td01.cj }"  maxlength="100"/>
				</p>
				<p>
					<label>供电方式：</label>
					<netsky:htmlSelect name="Td01_sbxx.GDFS" objectForOption="gdfsList" style="width:160px" valueForOption="name" showForOption="name" value="${td01.gdfs}"/>
				</p>
				<p>
					<label>所属专业：</label>
					<netsky:htmlSelect name="Td01_sbxx.SSZY" objectForOption="zyList" style="width:160px" valueForOption="name" showForOption="name" value="${td01.sszy}" htmlClass="td-select"/>
				</p>
				<p>
					<label>安装位置：</label>
					<input class="" name="Td01_sbxx.AZWZ" type="text"  style="width:155px" value="${td01.azwz }"  maxlength="100"/>
				</p>
				<p>
					<label>建设性质：</label>
					<netsky:htmlSelect name="Td01_sbxx.JSXZ" objectForOption="jsxzList" style="width:160px" valueForOption="name" showForOption="name" value="${td01.jsxz}" htmlClass="td-select"/>
				</p>
				<p>
					<label>安装日期：</label>
					<input class="date" name="Td01_sbxx.AZRQ" type="text"  style="width:155px" value="<fmt:formatDate value="${td01.azrq }" pattern="yyyy-MM-dd" />"  maxlength="150"/>
				</p>
				<p>
					<label>施工单位：</label>
					<input class="" name="Td01_sbxx.SGDW" type="text"  style="width:155px" value="${td01.sgdw }"  maxlength="100"/>
				</p>
				<p>
					<label>负责人：</label>
					<input class="" name="Td01_sbxx.FZR" type="text"  style="width:155px" value="${td01.fzr }"  maxlength="10"/>
				</p>
				<p>
					<label>联系电话：</label>
					<input class="" name="Td01_sbxx.LXDH" type="text"  style="width:155px" value="${td01.lxdh }"  maxlength="50"/>
				</p>
				<p>
					<label>备注：</label>
					<textarea class="td-textarea" style="height:60px;width:420px;" type="text" name="Td01_sbxx.BZ">${td01.bz}</textarea>
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