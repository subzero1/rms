<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script language="javascript">

$(function(){
	$("#nd", navTab.getCurrentPanel()).change(function(){
		$("#zdgcs_tab").loadUrl("wxdw/zjgcsEdit.do?lb=zdgcs&wxdw_id=${param.wxdw_id }&nd="+$(this).val());
	});
});

function saveQyzyConfig2(){
	var $form = $("#zjgcs_form", navTab.getCurrentPanel());
	var validate = true;
	$(".zdgcs", navTab.getCurrentPanel()).each(function(){
		if (isNaN($.trim($(this).val())) || $(this).val().indexOf(".")!=-1){
			$(".jqueryadd").remove();
			alertMsg.info('请输入正确的最大在建工程数（整数值类型）！');
			$(this).select();
			validate = false;
			return;
		}
		$form.append("<input class=\"jqueryadd\" type=\"hidden\" name=\"qy\" value=\"\"/>\
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
			$("#zdgcs_tab").loadUrl("wxdw/zjgcsEdit.do?lb=zdgcs&wxdw_id=${param.wxdw_id }&nd="+$("#nd").val());
		},
		error: DWZ.ajaxError
	});
}

</script>

	<div class="panelBar">
		<ul class="toolBar">
		<c:if test="${not empty param.wxdw_id && (empty param.role || param.role != 'wxdw')}">
			<li><a class="save" href="javascript:saveQyzyConfig2();" ><span>保 存</span></a></li>
			<li class="line">line</li>
			</c:if>
		</ul>
	</div>
	<form id="zjgcs_form" action="wxdw/ajaxSaveWxdwConfig.do">
	年度：<netsky:htmlSelect id="nd" name="nd" objectForOption="years" style="width:70px" valueForOption="" showForOption="" value="${nd}"/>
		<input type="hidden" name="lb" value="${param.lb }"/>
		<input type="hidden" name="wxdw_id" value="${param.wxdw_id }"/>
	</form>
	<table class="report" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;">
			<thead>
			</thead>
			<tbody>
				<c:forEach items="${zyList}" var="zy">
				<tr>
					<td style="width: 80px;">${zy.name }</td>
					<td style="width: 80px; text-align: center"><input zy="${zy.name }" type="text" class="zdgcs" style="width:40px;background: transparent;text-align: right" value="<fmt:formatNumber pattern="0" value="${not empty zjgcsMap[zy.name].v1 ? zjgcsMap[zy.name].v1 : 0 }"/>"/></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
