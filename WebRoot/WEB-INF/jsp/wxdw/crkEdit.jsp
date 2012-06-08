<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />

<script type="text/javascript">
$(function(){
	
});


</script>

<div class="page">
	<div class="pageHeader">
		<div class="searchBar">
			<h1>工程材料${type }</h1>
		</div>
	</div>
	
	<div class="panelBar">
		<ul class="toolBar" id="_flowButton">
		 	<li><a class="save"	href="javascript:;"><span>保 存</span></a></li>
			<li class="line">line</li>
		 	<li><a class="icon"	href="javascript:;"><span>${type }</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	
	<div class="pageContent" layoutH="0">
		
		<div class="pageFormContent" layouth="138" style="text-align:center;">
			<form id="" action="save.do" method="post"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		  
			<input type="hidden" name="_navTabId" value="" keep="true"/>
			<input type="hidden" name="_message" value="数据保存" keep="true"/>
			<input type="hidden" name="_callbackType" value="forward" keep="true"/>
			<input type="hidden" name="_forwardUrl" value="flowForm.do" keep="true"/>
			
			<p>
				<label>工程名称：</label>
				<input type="text" name="Td00_gcxx.GCMC" value="${Td00_gcxx.gcmc}" style="width:407px;"/>
			</p>
			<p>
				<label>工程编号：</label>
				<input type="text" name="Td00_gcxx.GCBH" value="${Td00_gcxx.gcbh}" style="width:120px;"/>
			</p>
			
			<div class="divider" style="height:1px;"></div>
			<div style="text-align:left;color:blue;"><h3>待${type }材料&nbsp;</h3></div>
			<table width="100%" class="list" itemdetail="clDetail" width="100%">
				<thead>
					<tr>
						<th type="enum" style="width:85px;" name="Tf08_clmxb.CLLX" enumName="cllx" enumUrl="wxdw/cllxSelect.do" enumData="{'type':'材料类型','dz':'${dz }'}" hideName="Tf08_clmxb.ID">材料类别</th>
						<th type="enum" style="width:320px;"  name="Tf08_clmxb.CLMC" enumName="clmc" enumUrl="wxdw/clmcSelect.do">材料名称</th>
						<th type="text" style="width:120px;" name="Tf08_clmxb.GG">规格</th>
						<th type="text" style="width:120px;" name="Tf08_clmxb.XH">型号</th>
						<th type="text" style="width:60px;" name="Tf08_clmxb.DW">单位</th>
						<th type="text" style="width:60px;" name="Tf08_clmxb.SL">数量</th>
						<th type="del">操作</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="obj" items="${tf08_clmxb}">
					<tr>
						<td>
							<input type="hidden" name="Tf08_clmxb.ID" value="${obj.id}"/>
						</td>
						<td>
							<netsky:htmlSelect name="Tf08_clmxb.CLMC" objectForOption="clmcList" valueForOption="name" showForOption="name" value="${obj.clmc}" extend="" style="width:0px;"/>
						</td>
						<td><input type="text" name="Tf08_clmxb.GG" value="${obj.gg}" style="width:0px;"/></td>
						<td><input type="text" name="Tf08_clmxb.XH" value="${obj.xh}" style="width:0px;"/></td>
						<td><input type="text" name="Tf08_clmxb.DW" value="${obj.dw}" style="width:0px;"/></td>
						<td><input type="text" name="Tf08_clmxb.SL" value="${obj.sl}" style="width:0px;"/></td>
						<td><a href="javascript:fsd" class="btnDel emptyInput" title="确认删除此明细">删除</a></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
</div>
