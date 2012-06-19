<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" /> 

<div class="page">
	<div class="pageContent">
		<form method="post" action="save.do" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
			<input type="hidden" name="ID" value="${ta03.id}" />
			<div class="pageFormContent" layoutH="53">
				<c:choose>				
					<c:when test="${param.act=='ht'}">	
						<p>
							<label>合同编号：</label>
							<input  type="text" name="LOGIN_ID" style="width:120px;" value="${ta03.login_id }" readonly="readonly"/>
						</p>
						<p>
							<label>签订日期：</label>
							<input type="text" name="NAME" style="width:120px;" value="${ta03.name }" class="required" />
						</p>
						<p>
							<label>合同金额：</label>
							<input type="text" name="MOBILE_TEL" style="width:120px;" value="${ta03.mobile_tel }" class="required" />
						</p>
					</c:when>
					<c:when test="${param.act=='ss'}">
						<p>
							<label>送审金额：</label>
							<input type="text" name="EMAIL" style="width:120px;" value="${ta03.email }" />
						</p>
						<p>
							<label>送审日期：</label>
							<input type="text" name="FIX_TEL" style="width:120px;" value="${ta03.fix_tel }" />
						</p>
					</c:when>
					<c:otherwise>
						<p>
							<label>审定金额：</label>
							<input type="text" name="EMAIL" style="width:120px;" value="${ta03.email }" />
						</p>
						<p>
							<label>审定日期：</label>
							<input type="text" name="EMAIL" style="width:120px;" value="${ta03.email }" />
						</p>
						<p>
							<label>开票日期：</label>
							<input type="text" name="EMAIL" style="width:120px;" value="${ta03.email }" />
						</p>
						<p>
							<label>挂账日期：</label>
							<input type="text" name="EMAIL" style="width:120px;" value="${ta03.email }" />
						</p>
					</c:otherwise>
				</c:choose>				
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button id="submitbutton" type="submit">提 交</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
		</div>
	</div>	