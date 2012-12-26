<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />

<input type="hidden" name="configType" value="byxml"/>
<input type="hidden" name="profile" value="xmysd.xml"/>
<input type="hidden" name="Td04_xmysd.ID" value="${param.doc_id}">
<input type="hidden" name="Td04_xmysd.PROJECT_ID" value="${param.project_id}">

<div class="pageFormContent">
	<p>
		<label>发文部门：</label>
		<input type="text" readOnly name="Td04_xmysd.FWBM" value="<c:out value="${td04_xmysd.fwbm}" default="${user.dept_name}"/>" style="width:407px;"/>
	</p>
	<p>
		<label>表单编号：</label>
		<input type="text" name="Td04_xmysd.BDBH" value="${td04_xmysd.bdbh}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>起 草 人：</label> 
		<input type="text" readOnly name="Td04_xmysd.CJR" value="<c:out value="${td04_xmysd.cjr}" default="${user.name}"/>" style="width:150px;"/>
	</p>
	<p>
		<label>电话：</label>
		<input type="text" readOnly name="Td04_xmysd.CJRDH" value="<c:out value="${td04_xmysd.cjrdh}" default="${user.mobile_tel}"/>" style="width:150px;"/>
	</p>
	<p>
		<label>提出日期：</label>
		<input readonly type="text" name="Td04_xmysd.CJRQ" style="width:120px;" value="<c:choose><c:when test="${empty param.doc_id}"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/></c:when><c:otherwise><fmt:formatDate value="${td04_xmysd.cjrq}" pattern="yyyy-MM-dd"/></c:otherwise></c:choose>"/>
	</p>
	<div class="divider"></div>
	<p>
		<label>项目名称：</label>
		<input type="text" name="Td00_gcxx.GCMC" value="${td00_gcxx.xmmc}" style="width:407px;"/>
	</p>
	<p>
		<label>项目编号：</label>
		<input type="text" name="Td00_gcxx.GCBH" value="${td00_gcxx.xmbh}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>施工单位：</label>
		<input type="text" name="Td00_gcxx.SGDW" value="${td00_gcxx.sgdw}" style="width:407px;"/>
	</p>
	<p>
		<label>开工日期：</label>
		<input type="text" name="Td00_gcxx.SJKGSJ" value="<fmt:formatDate value="${td00_gcxx.sjkgsj}" pattern="yyyy-MM-dd"/>" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>监理单位：</label>
		<input type="text" name="Td00_gcxx.JLDW" value="${td00_gcxx.jldw}" style="width:407px;"/>
	</p>
	<p>
		<label>竣工日期：</label>
		<input type="text" name="Td00_gcxx.SJJGRQ" value="<fmt:formatDate value="${td00_gcxx.sjjgsj}" pattern="yyyy-MM-dd"/>" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>完成的主要<br/>工程内容：</label>
		<textarea class="td-textarea" style="width:630px;height:80px;" type="text" name="Td04_xmysd.WCZYGZNR">${td04_xmysd.wczygznr}</textarea>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>提前或推迟<br/>原因：</label>
		<textarea class="td-textarea" style="width:630px;height:80px;" type="text" name="Td04_xmysd.TQTCYY">${td04_xmysd.tqtcyy}</textarea>
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

