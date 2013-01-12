<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />
<script type="text/javascript">
$(function(){
	$ysry=$('#ysry',navTab.getCurrentPanel());
	$dept_id=$("#deptOrg\\.DEPT_IDS",navTab.getCurrentPanel());
	var init_url=$ysry.attr('href');
	$dept_id.change(function(){
		var url=init_url+'?dept_ids='+$dept_id.val();
		$ysry.attr('href',url);  
	});
	
}); 
</script>
<input type="hidden" name="configType" value="byxml"/>
<input type="hidden" name="profile" value="xmysd.xml"/>
<input type="hidden" name="Td04_xmysd.ID" value="${param.doc_id}">
<input type="hidden" name="Td01_xmxx.ID" value="${param.project_id}">

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
		<input type="text" name="Td01_xmxx.XMMC" value="${td01_xmxx.xmmc}" style="width:407px;"/>
	</p>
	<p>
		<label>项目编号：</label>
		<input type="text" name="Td01_xmxx.XMBH" value="${td01_xmxx.xmbh}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>施工单位：</label>
		<input type="text" name="Td01_xmxx.SGDW" value="${td01_xmxx.sgdw}" style="width:407px;"/>
	</p>
	<p>
		<label>开工日期：</label>
		<input type="text" name="Td01_xmxx.SJKGSJ" value="<fmt:formatDate value="${td01_xmxx.sjkgsj}" pattern="yyyy-MM-dd"/>" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>监理单位：</label>
		<input type="text" name="Td01_xmxx.JLDW" value="${td01_xmxx.jldw}" style="width:407px;"/>
	</p>
	<p>
		<label>竣工日期：</label>
		<input type="text" name="Td01_xmxx.SJJGRQ" value="<fmt:formatDate value="${td01_xmxx.sjjgsj}" pattern="yyyy-MM-dd"/>" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>验收部门：</label>
		<input type="text" name="Td01_xmxx.YSBM" value="${td01_xmxx.ysbm}" style="width:381px;" id="deptOrg.YSBM"/>
		<a class="btnLook" lookupGroup="deptOrg" href="form/selectDept.do" width="600" height="371"></a>
		<input type="hidden" name="Ta01_dept.DEPT_IDS" id="deptOrg.DEPT_IDS" value=""/>
	</p>
	<p>
		<label>验收时间：</label>
		<input class="date" type="text" name="Td01_xmxx.YSSJ" value="<fmt:formatDate value="${td01_xmxx.yssj}" pattern="yyyy-MM-dd"/>" />
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>验收人员：</label>
		<input type="text" name="Td01_xmxx.YSRY" value="${td01_xmxx.ysry}" style="width:381px;" id="ysryOrg.YSRY"/>
		<a id="ysry" class="btnLook" lookupGroup="ysryOrg" href="form/selectYsry.do" width="600" height="371"></a>
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

