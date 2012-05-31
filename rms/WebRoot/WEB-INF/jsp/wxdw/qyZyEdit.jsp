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
		success: callback || DWZ.ajaxDone,
		error: DWZ.ajaxError
	});
}

</script>

	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="save" href="javascript:saveQyzyConfig();" ><span>保 存</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<form id="qyzy_form" action="wxdw/ajaxSaveWxdwConfig.do">
		<input type="hidden" name="lb" value=""/>
		<input type="hidden" name="wxdw_id" value="${param.wxdw_id }"/>
	</form>
	<table class="report" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;">
			<thead>
				<tr>
					<th style="width: 80px;">&nbsp;</th>
					<th style="width: 80px;">电缆</th>
					<th style="width:80px;">光缆</th>
					<th style="width: 80px;">移动</th>
					<th style="width: 80px;">土建</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td style="width: 80px;">白下区</td>
					<td style="width: 80px;" class="qyzy selectArea" qy="白下区" zy="电缆">&nbsp;	</td>
					<td style="width: 80px;" class="qyzy" qy="白下区" zy="光缆">&nbsp;</td>
					<td style="width: 80px;" class="qyzy" qy="白下区" zy="移动">&nbsp;</td>
					<td style="width: 80px;" class="qyzy" qy="白下区" zy="土建">&nbsp;</td>
				</tr>
				<tr>
					<td>玄武区</td>
					<td class="qyzy">&nbsp;</td>
					<td class="qyzy">&nbsp;</td>
					<td class="qyzy">&nbsp;</td>
					<td class="qyzy selectArea" qy="玄武区" zy="土建">&nbsp;</td>
				</tr>
				<tr>
					<td>秦淮区</td>
					<td class="qyzy">&nbsp;</td>
					<td class="qyzy">&nbsp;</td>
					<td class="qyzy selectArea" qy="秦淮区" zy="移动">&nbsp;</td>
					<td class="qyzy">&nbsp;</td>
				</tr>
				<tr>
					<td>……</td>
					<td class="qyzy selectArea" qy="……" zy="电缆">&nbsp;</td>
					<td class="qyzy">&nbsp;</td>
					<td class="qyzy">&nbsp;</td>
					<td class="qyzy">&nbsp;</td>
				</tr>
			</tbody>
		</table>