<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />

<input type="hidden" name="configType" value="byxml"/>
<input type="hidden" name="profile" value="jlzj.xml"/>
<input type="hidden" name="Td03_jlzj.ID" value="${param.doc_id}">

<div class="pageFormContent">
	<p>
		<label>发文部门：</label>
		<input type="text" readOnly name="Td03_jlzj.FWBM" value="<c:out value="${td03_jlzj.fwbm}" default="${user.dept_name}"/>" style="width:407px;"/>
	</p>
	<p>
		<label>表单编号：</label>
		<input type="text" name="Td03_jlzj.BDBH" value="${td03_jlzj.bdbh}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>起 草 人：</label> 
		<input type="text" readOnly name="Td03_jlzj.CJR" value="<c:out value="${td03_jlzj.cjr}" default="${user.name}"/>" style="width:150px;"/>
	</p>
	<p>
		<label>电话：</label>
		<input type="text" readOnly name="Td03_jlzj.CJRDH" value="<c:out value="${td03_jlzj.cjrdh}" default="${user.mobile_tel}"/>" style="width:150px;"/>
	</p>
	<p>
		<label>提出日期：</label>
		<input readonly type="text" name="Td03_jlzj.CJRQ" style="width:120px;" value="<c:choose><c:when test="${empty param.doc_id}"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm"/></c:when><c:otherwise><fmt:formatDate value="${td03_jlzj.cjrq}" pattern="yyyy-MM-dd HH:mm"/></c:otherwise></c:choose>"/>
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
		<label>监理单位：</label>
		<input type="text" name="Td01_xmxx.JLDW" value="${td01_xmxx.jldw}" style="width:407px;"/>
	</p>
	<p>
		<label>委托日期：</label>
		<input type="text" name="Td01_xmxx.JLPFSJ" value="<fmt:formatDate value="${td01_xmxx.jlpfsj}" pattern="yyyy-MM-dd"/>" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>监理工程师：</label>
		<input type="text" name="Td01_xmxx.JLGCS" value="${td01_xmxx.jlgcs}" style="width:407px;"/>
	</p>
	<p>
		<label>联系电话：</label>
		<input type="text" name="Td01_xmxx.JLGCSDH" value="${td01_xmxx.jlgcsdh}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>监理总结：</label>
		<textarea class="td-textarea" style="width:630px;height:80px;" type="text" name="Td03_jlzj.JLZJ">${td03_jlzj.jlzj}</textarea>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>备　注：</label>
		<textarea class="td-textarea" style="width:630px;height:80px;" type="text" name="Td03_jlzj.BZ">${td03_jlzj.bz}</textarea>
	</p>
	

