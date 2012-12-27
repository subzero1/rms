<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />

<input type="hidden" name="configType" value="byxml"/>
<input type="hidden" name="profile" value="xmbgd.xml"/>
<input type="hidden" name="Td02_xmbgd.ID" value="${param.doc_id}">
<input type="hidden" name="Td02_xmbgd.PROJECT_ID" value="${param.project_id}">

<div class="pageFormContent">
	<p>
		<label>发文部门：</label>
		<input type="text" readOnly name="Td02_xmbgd.FWBM" value="<c:out value="${td02_xmbgd.fwbm}" default="${user.dept_name}"/>" style="width:407px;"/>
	</p>
	<p>
		<label>表单编号：</label>
		<input type="text" name="Td02_xmbgd.BDBH" value="${td02_xmbgd.bdbh}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>起 草 人：</label> 
		<input type="text" readOnly name="Td02_xmbgd.CJR" value="<c:out value="${td02_xmbgd.cjr}" default="${user.name}"/>" style="width:150px;"/>
	</p>
	<p>
		<label>电话：</label>
		<input type="text" readOnly name="Td02_xmbgd.CJRDH" value="<c:out value="${td02_xmbgd.cjrdh}" default="${user.mobile_tel}"/>" style="width:150px;"/>
	</p>
	<p>
		<label>提出日期：</label>
		<input readonly type="text" name="Td02_xmbgd.CJRQ" style="width:120px;" value="<c:choose><c:when test="${empty param.doc_id}"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/></c:when><c:otherwise><fmt:formatDate value="${td02_xmbgd.cjrq}" pattern="yyyy-MM-dd"/></c:otherwise></c:choose>"/>
	</p>
	<div class="divider"></div>
	<p>
		<label>项目名称：</label>
		<input type="text" name="Td00_gcxx.GCMC" value="${td00_gcxx.gcmc}" style="width:407px;"/>
	</p>
	<p>
		<label>项目编号：</label>
		<input type="text" name="Td00_gcxx.GCBH" value="${td00_gcxx.gcbh}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>变更类别：</label>
		<netsky:htmlSelect name="Td02_xmbgd.BGLB" objectForOption="bglbList" style="width:157px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true" value="${td02_xmbgd.bglb}" htmlClass="td-select"/>
	</p>
	<p>
		<label>变更种类：</label>
		<netsky:htmlSelect name="Td02_xmbgd.BGZL" objectForOption="bgzlList" style="width:155px;" valueForOption="name" showForOption="name" value="${td02_xmbgd.bgzl}" htmlClass="td-select"/>
	</p>
	<p>
		<label>项目等级：</label>
		<input type="text" name="Td00_gcxx.XMDJ" value="" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>设计金额：</label>
		<input type="text"  name="Td00_gcxx.YS_JE" value="${td00_gcxx.ys_je}" style="width:150px;"/>
	</p>
	<p>
		<label>立项金额：</label>
		<input type="text"  name="Td02_xmbgd.LXJE" value="${td00_gcxx.lxje}" style="width:150px;"/>
	</p>
	<p>
		<label>变更金额：</label>
		<input type="text"  name="Td02_xmbgd.BGJE" value="${td02_xmbgd.bgje}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p style="width:350px;text-align:center;">
		原设计内容
	</p>
	<p style="width:350px;text-align:center;">
		变更后工作内容
	</p>
	<div style="height:0px;"></div>
	<p style="text-align:center;margin-left:5px;">
		<textarea class="td-textarea" style="width:350px;height:60px;" type="text" name="Td02_xmbgd.YSJNR">${td02_xmbgd.ysjnr}</textarea>
	</p>
	<p style="text-align:center;margin-left:5px;">
		<textarea class="td-textarea" style="width:350px;height:60px;" type="text" name="Td02_xmbgd.BGHNR">${td02_xmbgd.bghnr}</textarea>
	</p>
	<div style="height:0px;"></div>
	<p style="width:350px;text-align:center;">
		原设计工作量
	</p>
	<p style="width:350px;text-align:center;">
		变更后工作量
	</p>
	<div style="height:0px;"></div>
	<p style="text-align:center;margin-left:5px;">
		<textarea class="td-textarea" style="width:350px;height:60px;" type="text" name="Td02_xmbgd.YSJGZL">${td02_xmbgd.ysjgzl}</textarea>
	</p>
	<p style="text-align:center;margin-left:5px;">
		<textarea class="td-textarea" style="width:350px;height:60px;" type="text" name="Td02_xmbgd.BGHGZL">${td02_xmbgd.bghgzl}</textarea>
	</p>
	<div style="height:0px;"></div>
	<p style="width:700px;text-align:center;">
		变更原因说明
	</p>
	<div style="height:0px;"></div>
	<p style="text-align:center;margin-left:5px;">
		<textarea class="td-textarea" style="width:715px;height:60px;" type="text" name="Td02_xmbgd.BGYYSM">${td02_xmbgd.bgyysm}</textarea>
	</p>
	<div style="height:0px;"></div>
	<p style="color:blue;font-weight:bold;">&nbsp;&nbsp;&nbsp;历次变更明细</p>
	
	<table class="table" width="100%" layouth="138">
		<thead>
			<tr>
				<th style="width: 30px;">序号</th>
				<th style="width: 90px;">变更日期</th>
				<th style="width: 80px;">变更金额</th>
				<th style="width: 80px;">变更类别</th>
				<th style="width: 80px;">变更种类</th>
				<th>变更原因</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="offset" value="0"/>
			<c:forEach var="obj" items="${bgList}">
				<c:set var="offset" value="${offset + 1}"/>
				<tr>
					<td style="text-align:center">${offset}</td>
					<td><a href="javascript:openFlowForm('{project_id:${obj.project_id},doc_id:${obj.id},module_id:106,opernode_id:-1,node_id:-1,user_id:-1}');"><fmt:formatDate value="${obj.cjrq }" pattern="yyyy-MM-dd"/></a></td>
					<td>${obj.bgje }</td>
					<td>${obj.bglb }</td>
					<td>${obj.bgzl }</td>
					<td>${obj.bgyysm }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

