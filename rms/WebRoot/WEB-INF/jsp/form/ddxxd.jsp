<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />

<script type="text/javascript">
	
   /*
	*防止将工程误删除
	*/
	var bg_je = '${td00_gcxx.bg_je}';
	if(bg_je == null || bg_je == ''){
		$("#BG_JE").val(0);
	}
	else{
		$("#BG_JE").val(bg_je);
	}
</script>

<input type="hidden" name="configType" value="byxml"/>
<input type="hidden" name="profile" value="gcxxd.xml"/>
<input type="hidden" name="Td00_gcxx.ID" value="${param.doc_id}">
<input type="hidden" name="XM_ID" value="${td00_gcxx.xm_id}">
<input type="hidden" name="GLGC_ID" value="${td00_gcxx.glgc_id}">
<input type="hidden" id="BG_JE" name="Td00_gcxx.BG_JE" value="">
<input type="hidden" id="MBK_ID" name="Td00_gcxx.MBK_ID" value="<c:out value="${td00_gcxx.mbk_id}" default="${param.mbk_id }"/>">
<input type="hidden"  name="Td00_gcxx.XQBM" value="<c:out value="${td00_gcxx.xqbm}" default="${user.dept_name}"/>" style="width:150px;"/>
<input type="hidden"  name="Td00_gcxx.CJR" value="<c:out value="${td00_gcxx.cjr}" default="${user.name}"/>" style="width:150px;"/>
<input type="hidden" name="Td00_gcxx.XMGLYDH" value="<c:out value="${td00_gcxx.xmglydh}" default="${user.mobile_tel}"/>">
<input type="hidden" name="Td00_gcxx.CJRQ" style="width:120px;" value="<c:choose><c:when test="${empty param.doc_id}"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm"/></c:when><c:otherwise><fmt:formatDate value="${td00_gcxx.cjrq}" pattern="yyyy-MM-dd HH:mm"/></c:otherwise></c:choose>"/>
<input type="hidden" name="Td00_gcxx.XQS_ID" value="${td00_gcxx.xqs_id }"/>
	<p>
		<label>工程名称：</label>
		<input type="text" name="Td00_gcxx.GCMC" value="<c:out value="${td00_gcxx.gcmc}" default="${mbk.zymc }"/>" style="width:407px;"/>
	</p>
	<p>
		<label>工程编号：</label>
		<input type="text" name="Td00_gcxx.GCBH" value="${td00_gcxx.gcbh}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>联系信息：</label>
		<input type="text" name="Td00_gcxx.GCBH" value="${td00_gcxx.gcbh}" style="width:407px;"/>
	</p>
	<p>
		<label>所属区域：</label>
		<netsky:htmlSelect name="Td00_gcxx.SSDQ" objectForOption="ssdqList" style="width:127px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true"  value="${td00_gcxx.ssdq}" htmlClass="td-select"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>A端装机地址：</label>
		<input type="text" name="Td00_gcxx.GCBH" value="${td00_gcxx.gcbh}" style="width:407px;"/>
	</p>
	<p>
		<label>工程类别：</label>
		<netsky:htmlSelect name="Td00_gcxx.GCLB" objectForOption="gclbList" style="width:127px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true" value="${td00_gcxx.gclb}" htmlClass="td-select"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>Z端装机地址：</label>
		<input type="text" name="Td00_gcxx.GCBH" value="${td00_gcxx.gcbh}" style="width:407px;"/>
	</p>
	<p>
		<label>工程状态：</label>
		<netsky:htmlSelect name="Td00_gcxx.GCZT" objectForOption="xmztList" style="width:127px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true"  value="${td00_gcxx.gczt}" htmlClass="td-select"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>需求说明：</label>
		<textarea class="td-textarea" style="width:630px;height:60px;" type="text" name="Td00_gcxx.GCSM">${td00_gcxx.gcsm}</textarea>
	</p>
	<div class="divider"></div>
	<p>
		<label>设计单位：</label>
		<netsky:htmlSelect name="Td00_gcxx.SJDW" objectForOption="sjdwList" style="width:413px;" valueForOption="mc" showForOption="mc" extend="" extendPrefix="true" value="${td00_gcxx.sjdw}" htmlClass="td-select"/>
	</p>
	<p>
		<label>设计派发时间：</label>
		<input type="text"  name="Td00_gcxx.SJPGSJ" value="<fmt:formatDate value="${td00_gcxx.sjpgsj}" pattern="yyyy-MM-dd"/>" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>施工单位：</label>
		<netsky:htmlSelect name="Td00_gcxx.SGDW" objectForOption="sgdwList" style="width:413px;" valueForOption="mc" showForOption="mc" extend="" extendPrefix="true" value="${td00_gcxx.sgdw}" htmlClass="td-select"/>
	</p>
	<p>
		<label>施工派发时间：</label>
		<input type="text"  name="Td00_gcxx.SGPFSJ" value="<fmt:formatDate value="${td00_gcxx.sgpfsj}" pattern="yyyy-MM-dd"/>" style="width:120px;"/>
	</p>
	
	
	<div style="height:0px;"></div>
	<p>
		<label>项目管理员：</label>
		<input type="text"  name="Td00_gcxx.XMGLY" value="<c:out value="${td00_gcxx.xmgly}" />" style="width:150px;"/>
	</p>
	<p>
		<label>计划竣工时间：</label>
		<input type="text"  name="Td00_gcxx.JHJGSJ" value="<fmt:formatDate value="${td00_gcxx.jhjgsj}" pattern="yyyy-MM-dd"/>" style="width:150px;"/>
	</p>
	<p>
		<label>实际竣工时间：</label>
		<input type="text"  name="Td00_gcxx.SJJGSJ" value="<fmt:formatDate value="${td00_gcxx.sjjgsj}" pattern="yyyy-MM-dd"/>" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>



	