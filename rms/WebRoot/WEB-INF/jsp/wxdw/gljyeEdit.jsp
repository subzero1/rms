<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script language="javascript">

$(function(){
});

function saveQyzyConfig3(){
	var $form = $("#gljye_form", navTab.getCurrentPanel());
	var validate = true;
	$(".gljye", navTab.getCurrentPanel()).each(function(){
		if (isNaN($.trim($(this).val()))){
			$(".jqueryadd").remove();
			alertMsg.error('请输入正确的关联交易额（数值类型）！');
			$(this).select();
			validate = false;
			return;
		}
		$form.append("<input class=\"jqueryadd\" type=\"hidden\" name=\"qy\" value=\"\"/>\
		<input class=\"jqueryadd\" type=\"hidden\" name=\"v1\" value=\"" + $(this).val() + "\"/>\
		<input class=\"jqueryadd\" type=\"hidden\" name=\"nds\" value=\"" + $(this).attr("nd") + "\"/>\
					<input class=\"jqueryadd\" type=\"hidden\" name=\"zy\" value=\"\"/>");
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
			$("#gljye_tab").loadUrl("wxdw/gljyeEdit.do?lb=gljye&wxdw_id=${param.wxdw_id }");
		},
		error: DWZ.ajaxError
	});
}

</script>

	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="save" href="javascript:saveQyzyConfig3();" ><span>保 存</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<form id="gljye_form" action="wxdw/ajaxSaveWxdwConfig.do">
		<input type="hidden" name="lb" value="${param.lb }"/>
		<input type="hidden" name="wxdw_id" value="${param.wxdw_id }"/>
	</form>
	<table class="report" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;">
			<thead>
			</thead>
			<tbody>
				<c:forEach items="${years}" var="year">
				<tr>
					<td style="width: 80px;">${year }</td>
					<td style="width: 150px; text-align: center"><input nd="${year }" type="text" class="gljye" style="width:130px;background: transparent;text-align: right" value="<fmt:formatNumber pattern="0" value="${not empty gljyeMap[year].v1 ? gljyeMap[year].v1 : 0 }"/>"/>元</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
