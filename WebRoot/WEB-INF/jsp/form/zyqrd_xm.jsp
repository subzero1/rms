<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />

<input type="hidden" name="configType" value="byxml"/>
<input type="hidden" name="profile" value="zyqrd_gc.xml"/>
<input type="hidden" name="Td07_zyqrd.ID" value="${param.doc_id}">
<input type="hidden" name="Td07_zyqrd.PROJECT_ID" value="${param.project_id}">

<div class="pageFormContent">
	<p>
		<label>发文部门：</label>
		<input type="text" readOnly name="Td07_zyqrd.FWBM" value="<c:out value="${td07_zyqrd.fwbm}" default="${user.dept_name}"/>" style="width:407px;"/>
	</p>
	<p>
		<label>表单编号：</label>
		<input type="text" name="Td07_zyqrd.BDBH" value="${td07_zyqrd.bdbh}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>起 草 人：</label> 
		<input type="text" readOnly name="Td07_zyqrd.CJR" value="<c:out value="${td07_zyqrd.cjr}" default="${user.name}"/>" style="width:150px;"/>
	</p>
	<p>
		<label>电话：</label>
		<input type="text" readOnly name="Td07_zyqrd.CJRDH" value="<c:out value="${td07_zyqrd.cjrdh}" default="${user.mobile_tel}"/>" style="width:150px;"/>
	</p>
	<p>
		<label>提出日期：</label>
		<input readonly type="text" name="Td07_zyqrd.CJRQ" style="width:120px;" value="<c:choose><c:when test="${empty param.doc_id}"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/></c:when><c:otherwise><fmt:formatDate value="${td07_zyqrd.cjrq}" pattern="yyyy-MM-dd"/></c:otherwise></c:choose>"/>
	</p>
	<div class="divider"></div>
	<p>
		<label> 工程名称：</label>
		<input class="required" type="text" name="Td01_xmxx.GCMC" style="width:376px;" value="${td01_xmxx.gcmc}" />
	</p>
	<p>
		<label>工程编号：</label>
		<input class="required" type="text" name="Td01_xmxx.GCBH" style="width:376px;" value="${td01_xmxx.gcbh}" />
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>资源填录人：</label>
		<input class="required" type="text" name="Td01_xmxx.ZYLRY" style="width:376px;" value="${td01_xmxx.zylry}" />
	</p>
	<p>
		<label>资源填录人电话：</label>
		<input type="text" class="required" name="Td01_xmxx.ZYLRYDH" style="width:376px;" value="${td01_xmxx.zylrydh}"/>
	</p> 
	<div class="divider"></div>
	<p>
		<label>需求说明：</label>
		<textarea class="td-textarea" style="width:630px;height:80px;" type="text" name="Td07_zyqrd.BZ">${td07_zyqrd.bz}</textarea>
	</p>
