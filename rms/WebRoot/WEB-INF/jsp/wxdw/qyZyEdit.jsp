<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<style>
.selectArea {
	background:url("Images/online_ok.gif") no-repeat center;
}
</style>

<script language="javascript">

$(function(){
	$(".qyzy").click(function(){
		if($(this).hasClass("selectArea")){
			$(this).removeClass("selectArea");
		}else{
			$(this).addClass("selectArea");
		}
	});
});

function saveQyzyConfig(){
	var $form = $("#qyzy_form", navTab.getCurrentPanel());
	
	$(".selectArea").each(function(){
		$form.append("<input type=\"hidden\" name=\"qy\" value=\"" + $(this).attr("qy") + "\"/>\
					<input type=\"hidden\" name=\"zy\" value=\"" + $(this).attr("zy") + "\"/>");
	});
		
	$.ajax({
		type: 'POST',
		url: $form.attr("action"),
		data:$form.serializeArray(),
		dataType:"json",
		cache: false,
		success: function(json){
			DWZ.ajaxDone(json);
			$("#sgd_tab").loadUrl("wxdw/sgdEdit.do?lb=sgd&wxdw_id=${param.wxdw_id }");
			$("#fezb_tab").loadUrl("wxdw/fezbEdit.do?lb=fezb&wxdw_id=${param.wxdw_id }");
			$("#qyzy_tab").loadUrl("wxdw/qyZyEdit.do?lb=qyzy&wxdw_id=${param.wxdw_id }");
		},
		error: DWZ.ajaxError
	});
}

</script>

	<div class="panelBar">
		<ul class="toolBar">
		<c:if test="${not empty param.wxdw_id }">
			<li><a class="save" href="javascript:saveQyzyConfig();" ><span>保 存</span></a></li>
			<li class="line">line</li>
			</c:if>
		</ul>
	</div>
	<form id="qyzy_form" action="wxdw/ajaxSaveWxdwConfig.do">
		<input type="hidden" name="lb" value="${param.lb }"/>
		<input type="hidden" name="wxdw_id" value="${param.wxdw_id }"/>
	</form>
	<table class="report" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;">
			<thead>
				<tr>
					<th style="width: 80px;">&nbsp;</th>
					<c:forEach items="${qyList}" var="qy">
						<th style="width: 80px;">${qy.name }</th>
					</c:forEach>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${zyList}" var="zy">
				<tr>
					<td style="width: 80px;">${zy.name }</td>
					<c:forEach items="${qyList}" var="qy">
						<td style="width: 80px;" class="qyzy <c:if test="${not empty dygxMap[zy.name][qy.name] }">selectArea</c:if>" qy="${qy.name }" zy="${zy.name }">&nbsp;</td>
					</c:forEach>
				</tr>
				</c:forEach>
			</tbody>
		</table>