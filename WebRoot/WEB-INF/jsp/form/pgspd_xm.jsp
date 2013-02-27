<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />

<input type="hidden" name="configType" value="byxml"/>
<input type="hidden" name="profile" value="pgspd.xml"/>
<input type="hidden" name="Td08_pgspd.ID" value="${param.doc_id}">
<input type="hidden" name="Td08_pgspd.PROJECT_ID" value="${param.project_id}">

<div class="pageFormContent">
	<p>
		<label>发文部门：</label>
		<input type="text" readOnly name="Td08_pgspd.FWBM" value="<c:out value="${td08_pgspd.fwbm}" default="${user.dept_name}"/>" style="width:407px;"/>
	</p>
	<p>
		<label>表单编号：</label>
		<input type="text" name="Td08_pgspd.BDBH" value="${td08_pgspd.bdbh}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>起 草 人：</label> 
		<input type="text" readOnly name="Td08_pgspd.CJR" value="<c:out value="${td08_pgspd.cjr}" default="${user.name}"/>" style="width:150px;"/>
	</p>
	<p>
		<label>电话：</label>
		<input type="text" readOnly name="Td08_pgspd.CJRDH" value="<c:out value="${td08_pgspd.cjrdh}" default="${user.mobile_tel}"/>" style="width:150px;"/>
	</p>
	<p>
		<label>提出日期：</label>
		<input readonly type="text" name="Td08_pgspd.CJRQ" style="width:120px;" value="<c:choose><c:when test="${empty param.doc_id}"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/></c:when><c:otherwise><fmt:formatDate value="${td08_pgspd.cjrq}" pattern="yyyy-MM-dd"/></c:otherwise></c:choose>"/>
	</p>
	<div class="divider"></div>
	<p>
		<label> 工程名称：</label>
		<input class="required" type="text" name="Td01_xmxx.XMMC" style="width:405px;" value="${td01_xmxx.xmmc}" />
	</p>
	<p>
		<label>工程编号：</label>
		<input class="required" type="text" name="Td01_xmxx.XMBH" style="width:120px;" value="${td01_xmxx.xmbh}" />
	</p>
	<div style="height:0px;"></div>
	<p>
		<label> 系统选择单位：</label>
		<input type="text" readOnly name="Td08_pgspd.XTXZDW" style="width:630px;" value="<c:out value="${td08_pgspd.xtxzdw}" default="${sys_wxdw_name }"/>" />
	</p>
	<div class="divider"></div>
	<p>
		<label> 实际选择单位：</label>
		<input  type="text" name="Td08_pgspd.SJXZDW" style="width:630px;" value="<c:out value="${td08_pgspd.sjxzdw}" default="${man_wxdw_name }"/>" />
	</p>
	<div class="divider"></div>
	<p>
		<label>手动选派原因：</label>
		<textarea class="td-textarea" style="width:630px;height:80px;" type="text" name="Td08_pgspd.SDXPYY">${td08_pgspd.sdxpyy}</textarea>
	</p>
	<p>
		<label>说明：</label>
		<textarea class="td-textarea" style="width:630px;height:80px;" type="text" name="Td08_pgspd.BZ">${td08_pgspd.bz}</textarea>
	</p>
</div>