<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<script language="javascript">
$(function(){
	var xmgly=$("#xmgly",navTab.getCurrentPanel()).val();
	var nd=$("#nd",navTab.getCurrentPanel()).val();
	$("#nd").change(function(){ 
	navTab.openTab('gdcqtj', 'aux/gdcqtj.do?xmgly='+xmgly+'&nd='+$(this).val(), {title:'项目信息'});
	});
	$("#xmgly").change(function(){ 
	navTab.openTab('gdcqtj', 'aux/gdcqtj.do?xmgly='+$(this).val()+'&nd='+nd, {title:'项目信息'});
	});
});
function searchListExport(){
	var $fezb_form=$("#fezb_form",navTab.getCurrentPanel());
	$fezb_form.attr("action","aux/gdcqtjToExcel.do");
	$fezb_form.submit();
	$fezb_form.attr("action","aux/gdcqtj.do");
}
</script>
<div class="page">
 <div class="pageContent" style="overflow: hidden">
	<div class="panelBar">
		<ul class="toolBar">
					<li> <a class="exportexcel" href="javascript:searchListExport();" ><span>导出</span></a></li>
					<li class="line">line</li>
				</ul>
	</div>
	<form id="fezb_form" action="aux/gdcqtj.do">
	年度：<netsky:htmlSelect id="nd" name="nd" objectForOption="years" style="width:70px" valueForOption="" showForOption="" value="${nd}"/>
	项目管理员：<netsky:htmlSelect id="xmgly" name="xmgly" objectForOption="xmglys" style="width:70px" valueForOption="" extend="---------,1" extendPrefix="true" showForOption="" value="${xmgly}"/>
	<input type="hidden" name="config" value="tf43_gdcqtj"/>
	</form>
	<table class="table" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;">
			<thead>
				<tr>
					<th style="width: 80px;">&nbsp;</th>
					<c:forEach begin="1" end="12" var="month">
						<th style="width: 50px;text-align: center">${month }月</th>
					</c:forEach>
					<th style="width: 70px;text-align: center">小计</th>
					<th style="width: 70px;text-align: center">区域工单量</th>
					<th style="width: 70px;text-align: center">区域占比</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${gdcqtjList}" var="gdcqtj">
				<tr>
					<c:set var="offset" value="-1"/>
					<c:forEach begin="1" end="15" var="i">
					<c:set var="offset" value="${offset+1}"/>
						<td style="width: 70px; text-align: center"><a href="aux/gdxxList.do?type=q1&xmgly=${ xmgly}&nd=${nd}&mh=${offset }&ssdq=${gdcqtj[1] }" target="navTab" rel="gdxxList">${gdcqtj[i] }</a></td>
					</c:forEach>  
					<td style="width: 70px; text-align: center">
					<c:if test="${gdcqtj[16]=='00.00'||gdcqtj[16]=='0' }">0</c:if>
					<c:if test="${!empty gdcqtj[16]&&gdcqtj[16]!='00.00'&&gdcqtj[16]!='0' }">${gdcqtj[16] }%</c:if>
					</td>
				</tr>
			</c:forEach> 
			</tbody>
		</table>
</div>
</div>