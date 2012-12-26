<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />

<input type="hidden" name="configType" value="byxml"/>
<input type="hidden" name="profile" value="xqs.xml"/>
<input type="hidden" name="Td06_xqs.ID" value="${param.doc_id}">
<input type="hidden" name="Td06_xqs.PROJECT_ID" value="${param.project_id}">

<div class="pageFormContent">
	<p>
		<label>发文部门：</label>
		<input type="text" readOnly name="Td06_xqs.FWBM" value="<c:out value="${td06_xqs.fwbm}" default="${user.dept_name}"/>" style="width:407px;"/>
	</p>
	<p>
		<label>表单编号：</label>
		<input type="text" name="Td06_xqs.BDBH" value="${td06_xqs.bdbh}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>起 草 人：</label> 
		<input type="text" readOnly name="Td04_xmysd.CJR" value="<c:out value="${td06_xqs.cjr}" default="${user.name}"/>" style="width:150px;"/>
	</p>
	<p>
		<label>电话：</label>
		<input type="text" readOnly name="Td06_xqs.CJRDH" value="<c:out value="${td06_xqs.cjrdh}" default="${user.mobile_tel}"/>" style="width:150px;"/>
	</p>
	<p>
		<label>提出日期：</label>
		<input readonly type="text" name="Td06_xqs.CJRQ" style="width:120px;" value="<c:choose><c:when test="${empty param.doc_id}"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/></c:when><c:otherwise><fmt:formatDate value="${td06_xqs.cjrq}" pattern="yyyy-MM-dd"/></c:otherwise></c:choose>"/>
	</p>
	<div class="divider"></div>
	<p>
		<label> 需求名称：</label>
		<input class="required" type="text" name="Td06_xqs.ZYMC" style="width:376px;" value="${td06_xqs.zymc}" />
	</p>
	<p>
		<label>建设性质：</label>
		<netsky:htmlSelect htmlClass="required" id="jsxz" name="Td06_xqs.JSXZ" style="width:156px;" objectForOption="jsxzList" valueForOption="" showForOption="" value="${td06_xqs.jsxz}" extend="" extendPrefix="true" />
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>所属地区：</label>
		<netsky:htmlSelect htmlClass="required" name="Td06_xqs.SSDQ" style="width:156px;" objectForOption="dqList" valueForOption="" showForOption="" value="${td06_xqs.ssdq}" extend="" extendPrefix="true" />
	</p>
	<p>
		<label>坐落地点：</label>
		<input type="text" class="required" name="Td06_xqs.ZLDD" style="width:376px;" value="${td06_xqs.zldd}"/>
	</p> 
	<div id="jz">
		<div style="height:0px;"></div>
		<p>
			<label>经    度：</label>
			<input type="text" ids="jz" name="Td06_xqs.JD" style="width:150px;" value="${td06_xqs.jd}"/>
		</p>
		<p>
			<label>纬    度：</label>
			<input type="text" ids="jz" name="Td06_xqs.WD" style="width:150px;" value="${td06_xqs.wd}"/>
		</p>
		<p>
		<label>覆盖属性：</label> 
		<netsky:htmlSelect name="Td06_xqs.FGSX" style="width:126px;" objectForOption="fgsxList" valueForOption="name" showForOption="name" value="${td06_xqs.fgsx}" extend="" extendPrefix="true" />
		</p>
	</div>
	<div id="xq">
		<div style="height:0px;"></div>
		<p>
			<label>幢    数：</label>
			<input type="text" ids="xq" name="Td06_xqs.ZS" style="width:150px;" value="${td06_xqs.zs}"/>
		</p> 
		<p>
			<label>层    数：</label>
			<input type="text" ids="xq" name="Td06_xqs.CS" style="width:150px;" value="${td06_xqs.cs}"/>
		</p>
		<p>
			<label>户    数：</label>
			<input type="text" ids="xq" name="Td06_xqs.HS" style="width:120px;" value="${td06_xqs.hs}"/>
		</p>
	</div>
	<div class="divider"></div>
	<p>
		<label>需求说明：</label>
		<textarea class="td-textarea" style="width:630px;height:80px;" type="text" name="Td06_xqs.XQSM">${td06_xqs.xqsm}</textarea>
	</p>
	<div style="height:0px;"></div>
	<p style="color:blue;font-weight:bold;">&nbsp;&nbsp;&nbsp;相关工程列表</p>
</div>

