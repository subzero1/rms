<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script language="javascript">

$(function(){
	$("#nd").change(function(){
		$("#fezb_tab").loadUrl("wxdw/fezbEdit.do?lb=fezb&wxdw_id=${param.wxdw_id }&nd="+$(this).val());
	});
});

function saveQyzyConfig1(){
	var $form = $("#fezb_form", navTab.getCurrentPanel());
	var validate = true;
	$(".fezb", navTab.getCurrentPanel()).each(function(){
		if (isNaN($.trim($(this).val()))){
			$(".jqueryadd", navTab.getCurrentPanel()).remove();
			alertMsg.error('请输入正确的份额占比（数值类型）！');
			$(this).select();
			validate = false;
			return;
		}
		$form.append("<input class=\"jqueryadd\" type=\"hidden\" name=\"qy\" value=\"" + $(this).attr("qy") + "\"/>\
		<input class=\"jqueryadd\" type=\"hidden\" name=\"v1\" value=\"" + $(this).val() + "\"/>\
					<input class=\"jqueryadd\" type=\"hidden\" name=\"zy\" value=\"" + $(this).attr("zy") + "\"/>");
	});
	if (!validate){
		return;		
	}
	$.ajax({
		type: 'POST',
		url: $form.attr("action"),
		data:$form.serializeArray(),
		dataType:"json",
		cache: false,
		success: function(json){
			DWZ.ajaxDone(json);
			$(".jqueryadd", navTab.getCurrentPanel()).remove();
			$("#fezb_tab").loadUrl("wxdw/fezbEdit.do?lb=fezb&wxdw_id=${param.wxdw_id }&nd="+$("#nd").val());
		},
		error: DWZ.ajaxError
	});
}

</script>

	<div class="panelBar">
		<ul class="toolBar">
		<c:if test="${not empty param.wxdw_id }">
			<li><a class="save" href="javascript:saveQyzyConfig1();" ><span>保 存</span></a></li>
			<li class="line">line</li>
			</c:if>
		</ul>
	</div>
	<form id="fezb_form" action="wxdw/ajaxSaveWxdwConfig.do">
	年度：<netsky:htmlSelect id="nd" name="nd" objectForOption="years" style="width:70px" valueForOption="" showForOption="" value="${nd}"/>
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
						<td style="width: 80px; text-align: center"><c:if test="${not empty dygxMap[zy.name][qy.name] }"><input type="text" class="fezb" qy="${qy.name }" zy="${zy.name }" style="width:40px;background: transparent;text-align: right" value="<fmt:formatNumber pattern="0.00" value="${not empty fezbMap[zy.name][qy.name].v1 ? fezbMap[zy.name][qy.name].v1 : 0 }"/>"/>%</c:if></td>
					</c:forEach>
				</tr>
				</c:forEach>
			</tbody>
		</table>
