<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />


<div class="page">
	<div class="pageContent">
		<form method="post" action="save.do" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
			<input type="hidden" name="tableInfomation"	value="noFatherTable:com.jfms.dataObjects.info.Td21_jcsq" />
			<input type="hidden" name="Td21_jcsq.ID" value="${td21.id}" />
			<input type="hidden" id="jcjf.dwz_jcjfLookup.Td21_jcsq.JF_ID" name="Td21_jcsq.JF_ID" value="<c:out value="${td01.jfxx_id}" default="${param.jfxx_id }"/>" />
			<input type="hidden" name="_callbackType" value=""/>
			<input type="hidden" name="_message" value="纠错申请保存" />
			<input type="hidden" name="_navTabId" value="jcsqList"/>
			<div class="pageFormContent" layoutH="56">
				<p>
					<label>机房名称：</label>
					<input class="required" id="jcjf.dwz_jcjfLookup.Td21_jcsq.JFMC" name="Td21_jcsq.JFMC" type="text" style="width:350px" value="${td21.jfmc }"  maxlength="100" lookupGroup="jcjf"  lookupName="jcjfLookup" suggestUrl="info/ajaxGetJfxx.do" suggestFields="Td21_jcsq.JF_ID,Td21_jcsq.JFMC,Td21_jcsq.JDMC" targetType="dialog" autocomplete="off"/>
				</p>
				<p>
					<label>局点名称：</label>
					<input class="required" id="jcjf.dwz_jcjfLookup.Td21_jcsq.JDMC" name="Td21_jcsq.JDMC" type="text" style="width:350px" value="${td21.jdmc }"  maxlength="100"/>
				</p>
				<p>
					<label>申请人：</label>
					<input class="" name="Td21_jcsq.SQR" type="text" style="width:110px" value="<c:out value="${td21.sqr}" default="${user.name}"/>"  maxlength="25"/>
				</p>
				<p>
					<label>申请时间：</label>
					<input class="required" name="Td21_jcsq.SQSJ" type="text" style="width:132px" value="<c:choose><c:when test="${empty td21}"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm"/></c:when><c:otherwise><fmt:formatDate value="${td21.sqsj}" pattern="yyyy-MM-dd HH:mm"/></c:otherwise></c:choose>"  maxlength="100"/>
				</p>
				<p>
					<label>错误描述：</label>
					<textarea class="td-textarea" style="height:120px;width:350px;" type="text" name="Td21_jcsq.CWSM">${td21.cwsm}</textarea>
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