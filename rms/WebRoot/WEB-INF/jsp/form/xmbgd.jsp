<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />

<div class="pageFormContent">
	<p>
		<label>发文部门：</label>
		<input type="text" readOnly name="Td02_xmbgd.FWBM" value="<c:out value="${Td02_xmbgd.fwbm}" default="${user.dept_name}"/>" style="width:407px;"/>
	</p>
	<p>
		<label>表单编号：</label>
		<input type="text" name="Td02_xmbgd.BDBH" value="${Td02_xmbgd.bdbh}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>起 草 人：</label> 
		<input type="text" readOnly name="Td02_xmbgd.CJR" value="<c:out value="${Td02_xmbgd.cjr}" default="${user.name}"/>" style="width:150px;"/>
	</p>
	<p>
		<label>电话：</label>
		<input type="text" readOnly name="Td02_xmbgd.CJRDH" value="<c:out value="${Td02_xmbgd.cjrdh}" default="${user.mobile_tel}"/>" style="width:150px;"/>
	</p>
	<p>
		<label>提出日期：</label>
		<input readonly type="text" name="Td02_xmbgd.CJRQ" style="width:120px;" value="<c:choose><c:when test="${empty param.doc_id}"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm"/></c:when><c:otherwise><fmt:formatDate value="${Td02_xmbgd.cjrq}" pattern="yyyy-MM-dd HH:mm"/></c:otherwise></c:choose>"/>
	</p>
	<div class="divider"></div>
	<p>
		<label>项目名称：</label>
		<input type="text" name="Td01_xmxx.XMMC" value="${Td01_xmxx.xmmc}" style="width:407px;"/>
	</p>
	<p>
		<label>项目编号：</label>
		<input type="text" name="Td01_xmxx.XMBH" value="${Td01_xmxx.xmbh}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>变更类别：</label>
		<netsky:htmlSelect name="Td02_xmbgd.BGLB" objectForOption="gclbList" style="width:157px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true" value="${Td02_xmbgd.gclb}" htmlClass="td-select"/>
	</p>
	<p>
		<label>变更种类：</label>
		<netsky:htmlSelect name="Td02_xmbgd.BGZL" objectForOption="zydlList" style="width:155px;" valueForOption="name" showForOption="name" value="${Td02_xmbgd.zydl}" htmlClass="td-select"/>
	</p>
	<p>
		<label>项目等级：</label>
		<input type="text" name="Td01_xmxx.XMDJ" value="${Td01_xmxx.xmdj}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>设计金额：</label>
		<input type="text"  name="Td01_xmxx.YS_JE" value="${Td01_xmxx.ys_je}" style="width:150px;"/>
	</p>
	<p>
		<label>立项金额：</label>
		<input type="text"  name="Td02_xmbgd.LXJE" value="${Td02_xmbgd.lxje}" style="width:150px;"/>
	</p>
	<p>
		<label>变更金额：</label>
		<input type="text"  name="Td02_xmbgd.BGJE" value="${Td02_xmbgd.ys_sbf}" style="width:120px;"/>
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
		<textarea class="td-textarea" style="width:350px;height:60px;" type="text" name="Td02_xmbgd.YSJNR">${Td02_xmbgd.sjyq}</textarea>
	</p>
	<p style="text-align:center;margin-left:5px;">
		<textarea class="td-textarea" style="width:350px;height:60px;" type="text" name="Td02_xmbgd.BGHNR">${Td02_xmbgd.sjyq}</textarea>
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
		<textarea class="td-textarea" style="width:350px;height:60px;" type="text" name="Td02_xmbgd.YSJGZL">${Td02_xmbgd.sjyq}</textarea>
	</p>
	<p style="text-align:center;margin-left:5px;">
		<textarea class="td-textarea" style="width:350px;height:60px;" type="text" name="Td02_xmbgd.BGHGZL">${Td02_xmbgd.sjyq}</textarea>
	</p>
	<div style="height:0px;"></div>
	<p style="width:700px;text-align:center;">
		变更原因说明
	</p>
	<div style="height:0px;"></div>
	<p style="text-align:center;margin-left:5px;">
		<textarea class="td-textarea" style="width:715px;height:60px;" type="text" name="Td02_xmbgd.BGYYSM">${Td02_xmbgd.gcsm}</textarea>
	</p>
	<div style="height:0px;"></div>
	<p style="color:blue;font-weight:bold;">&nbsp;&nbsp;&nbsp;历次变更明细</p>
	
	<table class="table" width="100%" layouth="138">
		<thead>
			<tr>
				<th style="width: 30px;">序号</th>
				<th style="width: 90px;">变更日期</th>
				<th style="width: 80px;">变更金额</th>
				<th style="width: 120px;">变更后立项金额</th>
				<th style="width: 80px;">变更比例</th>
				<th>变更原因</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach begin="0" end="8">
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

