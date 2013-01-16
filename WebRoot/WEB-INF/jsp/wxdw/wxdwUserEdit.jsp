<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" /> 
<div class="page">
	<div class="pageContent">
		<form method="post" action="wxdw/ajaxSaveWxdwUser.do" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
			<input type="hidden" name="ID" value="${ta03.id}" />
			<input type="hidden" name="WXDW_ID" value="${param.wxdw_id}" />
			<input type="hidden" name="DEPT_ID" value="${dept_id}" />
			<input type="hidden" name="AREA_NAME" value="${ta03.area_name}" />
			<div class="pageFormContent" layoutH="53">
				<p>
					<label>登&nbsp;&nbsp;录&nbsp;&nbsp;名：</label>
					<input  type="text" name="LOGIN_ID" style="width:120px;" value="${ta03.login_id }" readonly="readonly"/>
				</p>
				<p>
					<label>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</label>
					<input type="text" name="NAME" style="width:120px;" value="${ta03.name }" class="required" />
				</p>
				<p>
					<label>移动电话：</label>
					<input type="text" name="MOBILE_TEL" style="width:120px;" value="${ta03.mobile_tel }" class="required" />
				</p>				
				<p>
					<label>性&nbsp;&nbsp;&nbsp;&nbsp;别：</label>
					<input style="width: 30px" type="radio" name="SEX" <c:if test="${ta03.sex == '男' || empty ta03.sex}">checked="checked"</c:if> value="男" />
					男
					<input style="width: 30px" type="radio" name="SEX" <c:if test="${ta03.sex == '女' }">checked="checked"</c:if> value="女"/>
					女
				</p>
				<p>
					<label>固定电话：</label>
					<input type="text" name="FIX_TEL" style="width:120px;" value="${ta03.fix_tel }" />
				</p>
				<p>
					<label>电子邮件：</label>
					<input type="text" name="EMAIL" style="width:120px;" value="${ta03.email }" />
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>是否可用：</label>
					<input type="radio" name="USEFLAG" value="1" <c:if test="${ta03.useflag==1 ||empty ta03 }">checked</c:if> />
					是
					<input type="radio" name="USEFLAG" value="0" <c:if test="${ta03.useflag==0 }">checked</c:if> />
					否
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>对应岗位：</label>
					<c:forEach items="${ta02List}" var="ta02">
						<input type="checkbox" name="STATION_ID" <c:if test="${not empty ta11Map[ta02.id] }">checked="checked"</c:if> value="${ta02.id }"/>${ta02.name }
					</c:forEach>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>作业区域：</label>
					<c:forEach items="${dqList}" var="dq">
						<input type="checkbox" name="DQ" <c:if test="${not empty dqMap[dq] }">checked="checked"</c:if> value="${dq }"/>${dq }
					</c:forEach>
				</p>
				<p>
					<label>专业：</label>
					<c:forEach items="${dqList}" var="dq">
						<input type="checkbox" name="DQ" <c:if test="${not empty dqMap[dq] }">checked="checked"</c:if> value="${dq }"/>${dq }
					</c:forEach>
				</p>
			</div>
			<div class="formBar">
			<c:if test="${empty param.role || param.role != 'wxdw'}">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button id="submitbutton" type="submit">保存设置</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div>
					</li>
				</ul>
			</c:if>
			</div>
		</form>
		</div>
	</div>	