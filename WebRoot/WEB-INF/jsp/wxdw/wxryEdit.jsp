<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" /> 
<script language="javascript">
	function upPic(){
		$.pdialog.open("personalHead.do",'_upPic','上传头像',{width:600,height:380});
		var dialog = $("body").data('_upPic');
		setTimeout(function(){$.pdialog.switchDialog(dialog);}, 100);
	}
</script>
<div class="page">
	<div class="pageContent">
		<form method="post" action="wxdw/wxryAjaxSave.do" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
			<input type="hidden" name="ID" value="${wxry.id}" />
			<input type="hidden" name="WXDW_ID" value="${param.wxdw_id}" />
			<div class="pageFormContent" layoutH="53">
				<div style="width:200px;height:130px;float:right;text-align:center">
					<div style="width:100px;height:120px;border:dotted 2px black;"><br><br>照<br><br><br>片
					</div>
				</div>
				<p>
					<label>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</label>
					<input type="text" name="NAME" style="width:120px;" value="${wxry.name }" class="required" />
				</p>
				<p>
					<label>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</label>
					<input style="width: 30px" type="radio" name="SEX" <c:if test="${wxry.sex == '男' || empty wxry.sex}">checked="checked"</c:if> value="男" />
					男
					<input style="width: 30px" type="radio" name="SEX" <c:if test="${wxry.sex == '女' }">checked="checked"</c:if> value="女"/>
					女
				</p>
				<p>
					<label>移动电话：</label>
					<input type="text" name="MOBILE" style="width:120px;" value="${wxry.mobile}" class="required" />
				</p>
				<p>
					<label>状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：</label>
					<input type="radio" name="STATUS" value="在职" <c:if test="${wxry.status=='在职' ||empty wxry }">checked</c:if> />
					在职
					<input type="radio" name="STATUS" value="离职" <c:if test="${wxry.status=='离职' }">checked</c:if> />
					离职
				</p>
				<p>
					<label>身份证号：</label>
					<input  type="text" name="SFZ" style="width:330px;" value="${wxry.sfz}" />
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>住&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：</label>
					<input type="text" name="ADDRESS" style="width:330px;" value="${wxry.address}" />
				</p>
				<div style="height:0px;"></div>
				
				<p>
					<label>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
					 <textarea rows="3" cols="45" name="BZ">${wxry.bz }</textarea>
				</p>
				<div style="height:0px;"></div>
				 
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button id="submitbutton" type="submit">保存设置</button></div></div></li>
					<li><div class="buttonActive"><div class="buttonContent"><button id="submitbutton" onclick="javascript:upPic()">上传照片</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
		</div>
	</div>	