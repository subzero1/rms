<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" /> 
<div class="page">
	<div class="pageContent">
		<form method="post" action="wxdw/ajaxSaveWxdwUser.do" class="pageForm required-validate" onsubmit="return validateCallback(this,loadFileAreaAjaxDone);">
			<input type="hidden" name="tableInfomation" value="noFatherTable:com.netsky.base.dataObjects.Ta03_user" />
			<input type="hidden" name="Ta03_user.ID" value="${user.id}" />
			
			<div class="pageFormContent" layoutH="53">
				<p>
					<label>登&nbsp;&nbsp;录&nbsp;&nbsp;名：</label>
					<input  type="text" name="Ta03_user.LOGIN_ID" style="width:120px;" value="" readonly="readonly"/>
				</p>
				<p>
					<label>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</label>
					<input type="text" name="Ta03_user.NAME" style="width:120px;" value="" class="required" />
				</p>
				<p>
					<label>移动电话：</label>
					<input type="text" name="Ta03_user.MOBILE_TEL" style="width:120px;" value="" class="required" />
				</p>				
				<p>
					<label>性&nbsp;&nbsp;&nbsp;&nbsp;别：</label>
					<input style="width: 30px" type="radio" name="Ta03_user.SEX" value="男" />
					男
					<input style="width: 30px" type="radio" name="Ta03_user.SEX" value="女"/>
					女
				</p>
				<p>
					<label>固定电话：</label>
					<input type="text" name="Ta03_user.FIX_TEL" style="width:120px;" value="" />
				</p>
				<p>
					<label>电子邮件：</label>
					<input type="text" name="Ta03_user.EMAIL" style="width:120px;" value="" />
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>对应岗位：</label>
					<input type="checkbox" name="userStation" value=""/>岗位1
					<input type="checkbox" name="userStation" value=""/>岗位2
					<input type="checkbox" name="userStation" value=""/>岗位3
				</p>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button id="submitbutton" type="submit">保存设置</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
		</div>
	</div>	