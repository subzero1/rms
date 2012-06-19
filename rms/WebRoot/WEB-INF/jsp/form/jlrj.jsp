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
		<label>监理单位：</label>
		<input type="text" name="Td01_xmxx.JLDW" value="${td01_xmxx.jldw}" style="width:407px;"/>
	</p>
	<p>
		<label>委托日期：</label>
		<input type="text" name="Td01_xmxx.SJJGRQ" value="${td01_xmxx.sjjgrq}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>监理工程师：</label>
		<input type="text"  name="Td01_xmxx.JLGCS" value="${td01_xmxx.jlgcs}" style="width:150px;"/>
	</p>
	<p>
		<label>联系电话：</label>
		<input type="text"  name="Td01_xmxx.JLGCSDH" value="${td01_xmxx.jlgcsdh}" style="width:150px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>监理总结：</label>
		<textarea class="td-textarea" style="width:630px;height:120px;" type="text" name="Td03_jlzj.JLZJ">${td03_jlzj.jlzj}</textarea>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>备　注：</label>
		<textarea class="td-textarea" style="width:630px;height:60px;" type="text" name="Td03_jlzj.BZ">${td03_jlzj.bz}</textarea>
	</p>
	<div style="height:0px;"></div>
	<p style="color:blue;font-weight:bold;">&nbsp;&nbsp;&nbsp;监理日记</p>
	
	<table class="table" width="100%" layouth="138">
		<thead>
			<tr>
				<th style="width: 120px;">日期</th>
				<th>施工进度</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach begin="0" end="8">
				<tr>
					<td></td>
					<td></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

