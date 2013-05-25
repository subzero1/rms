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
		<input type="text" name="Td00_gcxx.LXXX" value="${td00_gcxx.lxxx}" style="width:407px;"/>
	</p>
	<p>
		<label>所属区域：</label>
		<netsky:htmlSelect name="Td00_gcxx.SSDQ" objectForOption="ssdqList" style="width:127px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true"  value="${td00_gcxx.ssdq}" htmlClass="td-select"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>A端装机地址：</label>
		<input type="text" name="Td00_gcxx.A_ADRESS" value="${td00_gcxx.a_adress}" style="width:407px;"/>
	</p>
	<!--  
	<p>
		<label>工程类别：</label>
		<netsky:htmlSelect name="Td00_gcxx.GCLB" objectForOption="gclbList" style="width:127px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true" value="${td00_gcxx.gclb}" htmlClass="td-select"/>
	</p>
	-->
	<p>
		<label>定单状态：</label>
		<netsky:htmlSelect name="Td00_gcxx.DDZT" objectForOption="ddztList" style="width:127px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true"  value="${td00_gcxx.ddzt}" htmlClass="td-select"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>Z端装机地址：</label>
		<input type="text" name="Td00_gcxx.A_ADRESS" value="${td00_gcxx.z_adress}" style="width:407px;"/>
	</p>
	
	<div style="height:0px;"></div>
	<p>
		<label>需求说明：</label>
		<textarea class="td-textarea" style="width:630px;height:60px;" type="text" name="Td00_gcxx.GCSM">${td00_gcxx.gcsm}</textarea>
	</p>
	<div class="divider"></div>
	<p>
		<label>计划竣工时间：</label>
		<input type="text"  name="Td00_gcxx.JHJGSJ" value="<fmt:formatDate value="${td00_gcxx.jhjgsj}" pattern="yyyy-MM-dd"/>" style="width:150px;"/>
	</p>
	<p>
		<label>实际竣工时间：</label>
		<input type="text"  name="Td00_gcxx.SJJGSJ" value="<fmt:formatDate value="${td00_gcxx.sjjgsj}" pattern="yyyy-MM-dd"/>" style="width:150px;"/>
	</p>
	
	<p>
		<label>工单状态：</label>
		<input name="Td00_gcxx.GDZTZT"  style="width:120px;" value="${td00_gcxx.gdztzt}"/>
	<p>
	<div style="height:0px;"></div>
	<p>
		<label>分光器编码：</label>
		<input class="required" type="text" style="width:150px;" name="Td00_gcxx.WCFGQBM" value="${td00_gcxx.wcfgqbm}" />
	</p>
	<p>
		<label>是否投诉：</label>
		<select   style="width:157px;" name="Td00_gcxx.SFTS" value="${td00_gcxx.sfts}" >
			<option value="否" <c:if test="${td00_gcxx.sfts=='否' }">selected</c:if>>否</option>
			<option value="是" <c:if test="${td00_gcxx.sfts=='是' }">selected</c:if>>是</option>
		</select>
		<input type="hidden" name="Td00_gcxx.SFCQ" style="width:120px;" value="${td00_gcxx.sfcq}" readonly/>
	</p>
	<p>
	<c:choose>
		<c:when test="${not empty rolesMap['100106'] }">
			<label>项目管理员：</label>
			<netsky:htmlSelect name="Td00_gcxx.XMGLY" objectForOption="xmglyList" style="width:127px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true" value="${td00_gcxx.xmgly}" htmlClass="td-select"/>
		</c:when>
		<c:otherwise>
			<label>项目管理员：</label>
			<input type="text" readOnly name="Td00_gcxx.XMGLY" value="<c:out value="${td00_gcxx.xmgly}" />" style="width:120px;"/>
		</c:otherwise>
	</c:choose>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>计划完成时间：</label>
		<input  type="text" style="width:150px;" name="Td00_gcxx.JHJGSJ"  value="<fmt:formatDate value="${td00_gcxx.jhjgsj}" pattern="yyyy-MM-dd"/>"  readonly/>
	</p>
	<p>
		<label>实际完成时间：</label>
		<input  type="text" style="width:150px;" name="Td00_gcxx.WCSJ"  value="<fmt:formatDate value="${td00_gcxx.wcsj}" pattern="yyyy-MM-dd"/>"  readonly/>
	</p>
	<p>
		<label>是否超期：</label>
		<input  type="text" style="width:120px;" name="Td00_gcxx_SFCQ"  value="${td00_gcxx.sfcq}"  readonly/>
	</p>
	<div style="height:0px;"></div>

	<div style="height:0px;"></div>
	<p>
		<label>回单备注：</label>
		<textarea name="Td00_gcxx.HDBZ" id="nr"
			style='width:630px; height: 40px'>${td00_gcxx.hdbz }</textarea>
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
	
	<div style="text-align:left;color:blue;"><h3>&nbsp;&nbsp;目标库流转记录</h3></div><div class="divider" style="height:1px;"></div>
		<table class="table" width="60%">
		<thead>
			<tr>
				<th style="width: 30px;">序号</th>
				<th style="width: 60px;">回单人</th>
				<th style="width: 110px;">回单时间</th>
				<th style="width: 80px;">回单岗位</th>
				<th>回单内容</th>
			</tr>
		</thead>
		<tbody>
		<c:set var="offset" value="0"/>
			<c:forEach items="${objList}" var="obj">
			<c:set var="offset" value="${offset+1}"/>
				<tr>
					<td style="text-align:center">${offset }</td>
					<td>${obj.hdr }</td>
					<td><fmt:formatDate value="${obj.hdsj }" pattern="yyyy-MM-dd HH:mm"/></td>
					<td>${obj.hdgw }</td>
					<td title="${obj.nr }">${obj.nr }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>



	