<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />

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
		<input readonly type="text" name="Td04_xmysd.CJRQ" style="width:120px;" value="<c:choose><c:when test="${empty param.doc_id}"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm"/></c:when><c:otherwise><fmt:formatDate value="${td04_xmysd.cjrq}" pattern="yyyy-MM-dd HH:mm"/></c:otherwise></c:choose>"/>
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
		<input type="text" name="Td01_xmxx.SJKGRQ" value="${td01_xmxx.sjkgrq}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>监理单位：</label>
		<input type="text" name="Td01_xmxx.JLDW" value="${td01_xmxx.jldw}" style="width:407px;"/>
	</p>
	<p>
		<label>竣工日期：</label>
		<input type="text" name="Td01_xmxx.SJJGRQ" value="${td01_xmxx.sjjgrq}" style="width:120px;"/>
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
				<th style="width: 120px;">工程编号</th>
				<th style="width: 260px;">工程名称</th>
				<th>验收资料</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach begin="0" end="8">
				<tr>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

