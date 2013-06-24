<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<script type="text/javascript">
 $(function(){ 
	var nd=$("#nd",navTab.getCurrentPanel()).val();
	$("#nd").change(function(){ 
		navTab.openTab('gdsjhz', 'aux/gdsjhz.do?nd='+$(this).val(), {title:'工单数据汇总表'});
	}); 
});

 function searchListExport(){
	var $fezb_form=$("#fezb_form",navTab.getCurrentPanel());
	$fezb_form.attr("action","aux/gdsjhzToExcel.do");
	$fezb_form.submit();
	$fezb_form.attr("action","aux/gdsjhz.do");
}
</script>
	<div class="panelBar">
		<ul class="toolBar">
					<li> <a class="exportexcel" href="javascript:searchListExport();" ><span>导出</span></a></li>
					<li class="line">line</li>
				</ul>
	</div>
	<form id="fezb_form" action="/aux/gdsjhz.do">
	年度：<netsky:htmlSelect id="nd" name="nd" objectForOption="years" style="width:70px" valueForOption="" showForOption="" value="${nd}"/>
		<input type="hidden" name="config" value="tf43_gdsjhz"/>
	</form>
	<table class="report" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;">
			<thead>
				<tr>
					<th style="width: 80px;">类别\月份</th>
					<c:forEach begin="1" end="12" var="month">
						<th style="width: 80px;">${month }月</th>
					</c:forEach>
				</tr>
			</thead>
			<tbody>
			<c:forEach begin="1" end="4" var="i">
				<tr>
					<c:forEach items="${gdsjhzList}" var="gdsjhz">
						<td style="width: 80px; text-align: center">${gdsjhz[i] }</td>
					</c:forEach>
				</tr>
			</c:forEach>	
				 
			</tbody>
		</table>
