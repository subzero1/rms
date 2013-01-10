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
		<input type="text" readOnly name="Td06_xqs.CJR" value="<c:out value="${td06_xqs.cjr}" default="${user.name}"/>" style="width:150px;"/>
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
		<input class="required" type="text" name="Td06_xqs.XQMC" style="width:376px;" value="${td06_xqs.xqmc}" />
	</p>
	<p>
		<label>建设性质：</label>
		<netsky:htmlSelect htmlClass="required" id="jsxz" name="Td06_xqs.JSXZ" style="width:156px;" objectForOption="jsxzList" valueForOption="name" showForOption="name" value="${td06_xqs.jsxz}" extend="" extendPrefix="true" />
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>所属地区：</label>
		<netsky:htmlSelect htmlClass="required" name="Td06_xqs.SSDQ" style="width:156px;" objectForOption="ssdqList" valueForOption="name" showForOption="name" value="${td06_xqs.ssdq}" extend="" extendPrefix="true" />
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
		<textarea class="td-textarea" style="width:630px;height:80px;" type="text" name="Td06_xqs.BZ">${td06_xqs.bz}</textarea>
	</p>
	<c:if test="${not empty mbkLength && mbkLength > 0}">
		<div style="height:10px;"></div>
		<p style="color:blue;font-weight:bold;">&nbsp;&nbsp;&nbsp;相关目标库列表</p>
		<div style="width:780px;">
		<table class="table" width="100%">
			<thead>
				<tr>
					<th style="width: 30px;">序号</th>
					<th style="width: 120px;">资源编号</th>
					<th >资源名称</th>
				</tr>
			</thead>
			<tbody>
			<c:set var="offset" value="0"/>
				<c:forEach items="${mbkList}" var="obj">
				<c:set var="offset" value="${offset+1}"/>
					<tr>
						<td>${offset }</td>
						<td>${obj.zybh }</td>
						<td><a href="javascript:navTab.openTab('mbk', 'mbk/mbkEdit.do?id=${obj.id }',{'title':'目标库'});">${obj.zymc }</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
	</c:if>
	<c:if test="${not empty xmxxLength && xmxxLength > 0}">
		<div style="height:10px;"></div>
		<p style="color:blue;font-weight:bold;">&nbsp;&nbsp;&nbsp;相关项目列表</p>
		<div style="width:780px;">
		<table class="table" width="100%">
			<thead>
				<tr>
					<th style="width: 30px;">序号</th>
					<th style="width: 120px;">项目编号</th>
					<th >项目名称</th>
				</tr>
			</thead>
			<tbody>
			<c:set var="offset" value="0"/>
				<c:forEach items="${xmxxList}" var="obj">
				<c:set var="offset" value="${offset+1}"/>
					<tr>
						<td>${offset }</td>
						<td>${obj.xmbh }</td>
						<td><a href="javascript:openFlowForm('{project_id:${obj.id },doc_id:${obj.id },module_id:101,opernode_id:-1,node_id:-1,user_id:${user.id }}');">${obj.xmmc }</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
	</c:if>
	<c:if test="${not empty gcxxLength && gcxxLength > 0}">
		<div style="height:10px;"></div>
		<p style="color:blue;font-weight:bold;">&nbsp;&nbsp;&nbsp;相关工程列表</p>
		<div style="width:780px;">
		<table class="table" width="100%">
			<thead>
				<tr>
					<th style="width: 30px;">序号</th>
					<th style="width: 120px;">工程编号</th>
					<th >工程名称</th>
				</tr>
			</thead>
			<tbody>
			<c:set var="offset" value="0"/>
				<c:forEach items="${gcxxList}" var="obj">
				<c:set var="offset" value="${offset+1}"/>
					<tr>
						<td>${offset }</td>
						<td>${obj.gcbh }</td>
						<td><a href="javascript:openFlowForm('{project_id:${obj.id },doc_id:${obj.id },module_id:102,opernode_id:-1,node_id:-1,user_id:${user.id }}');">${obj.gcmc }</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
	</c:if>
<script language="javascript">
	
	$("#jsxz").change(function(){
		var data = $(this).val();
		if(data == '基站'){
			$("#jz").css("display","block");
			$("#xq").css("display","none");
			$("input[ids=jz]").attr("class","required");
			$("input[ids=xq]").attr("class","norequired");
		}
		else if(data == '室分' ){
			$("#jz").css("display","none");
			$("#xq").css("display","none");
			$("input[ids=jz]").attr("class","norequired");
			$("input[ids=xq]").attr("class","norequired");
		}
		else{
			$("#jz").css("display","none");
			$("#xq").css("display","block");
			$("input[ids=jz]").attr("class","norequired");
			$("input[ids=xq]").attr("class","required digits");
		}
	});
	
	//通过建设性质来区分显示哪些字段
	var jsxz = $("#jsxz").val();
	if(jsxz == '' || jsxz == null || jsxz == '室分'){
		$("#jz").css("display","none");
		$("#xq").css("display","none");
		$("input[ids=jz]").attr("class","norequired");
		$("input[ids=xq]").attr("class","norequired");
	}
	else if(jsxz == 'undefine' || jsxz == '基站'){
		$("#jz").css("display","block");
		$("#xq").css("display","none");
		$("input[ids=jz]").attr("class","required");
		$("input[ids=xq]").attr("class","norequired");
	}
	else{
		$("#jz").css("display","none");
		$("#xq").css("display","block");
		$("input[ids=jz]").attr("class","norequired");
		$("input[ids=xq]").attr("class","required digits");
	}
	
</script>
