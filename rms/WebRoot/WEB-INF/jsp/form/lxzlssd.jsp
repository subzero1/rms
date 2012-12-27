<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />

<input type="hidden" name="configType" value="byxml"/>
<input type="hidden" name="profile" value="lxzlssd.xml"/>
<input type="hidden" name="Td05_lxzlssd.ID" value="${param.doc_id}">
<input type="hidden" name="Td05_lxzlssd.PROJECT_ID" value="${param.project_id}">

<div class="pageFormContent">
	<p>
		<label>发文部门：</label>
		<input type="text" readOnly name="Td05_lxzlssd.FWBM" value="<c:out value="${td05_lxzlssd.fwbm}" default="${user.dept_name}"/>" style="width:407px;"/>
	</p>
	<p>
		<label>表单编号：</label>
		<input type="text" name="Td05_lxzlssd.BDBH" value="${td05_lxzlssd.bdbh}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>起 草 人：</label> 
		<input type="text" readOnly name="Td05_lxzlssd.CJR" value="<c:out value="${td05_lxzlssd.cjr}" default="${user.name}"/>" style="width:150px;"/>
	</p>
	<p>
		<label>电话：</label>
		<input type="text" readOnly name="Td05_lxzlssd.CJRDH" value="<c:out value="${td05_lxzlssd.cjrdh}" default="${user.mobile_tel}"/>" style="width:150px;"/>
	</p>
	<p>
		<label>提出日期：</label>
		<input readonly type="text" name="Td05_lxzlssd.CJRQ" style="width:120px;" value="<c:choose><c:when test="${empty param.doc_id}"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/></c:when><c:otherwise><fmt:formatDate value="${td05_lxzlssd.cjrq}" pattern="yyyy-MM-dd"/></c:otherwise></c:choose>"/>
	</p>
	<div class="divider"></div>
	<p>
		<label>项目名称：</label>
		<input type="text" name="Td01_xmxx.XMMC" value="${td01_xmxx.xmmc}" style="width:407px;"/>
	</p>
	<p>
		<label>项目编号：</label>
		<input type="text" name="Td01_xmxx.XMBH" value="${td01_xmxx.xmbh}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>所属地区：</label>
		<input type="text" name="Td01_xmxx.SSDQ" value="${td01_xmxx.ssdq}" style="width:150px;"/>
	</p>
	<p>
		<label>工程类别：</label>
		<input type="text" name="Td01_xmxx.GCLB" value="${td01_xmxx.gclb}" style="width:150px;"/>
	</p>
	<p>
		<label>项目管理员：</label>
		<input type="text" name="Td01_xmxx.XMGLY" value="${td01_xmxx.xmgly}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>项目说明：</label>
		<textarea class="td-textarea" style="width:630px;height:80px;" type="text" name="Td01_xmxx.XMSM">${td01_xmxx.xmsm}</textarea>
	</p>
	<div style="height:0px;"></div>
	<p style="color:blue;font-weight:bold;">&nbsp;&nbsp;&nbsp;相关工程列表</p>
	
	<table class="table" width="100%" layouth="138">
		<thead>
			<tr>
				<th style="width: 140px;">工程编号</th>
				<th style="width: 300px;">工程名称</th>
				<th>&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="offset" value="0"/>
			<c:forEach items="${glgcList}" var="obj">
			<c:set var="offset" value="${offset+1}"/>
				<tr>
					<td>${obj.gcbh }</td>
					<td><a href="javascript:openFlowForm('{project_id:${obj.id },doc_id:${obj.id },module_id:102,opernode_id:-1,node_id:-1,user_id:${user.id }}');">${obj.gcmc }</a></td>
					<td>&nbsp;</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

