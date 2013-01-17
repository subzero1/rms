<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />
<style> 
.selectArea {
	background:url("Images/online_ok.gif") no-repeat center;
}
</style>
<script language="javascript"> 
function saveForm(){
	$("#mbk_form").submit();
} 
$(function(){
	$kcfk_td=$(".kcfk_td", navTab.getCurrentPanel());
	$kcfk_td.css({cursor:"hand"});
	$kcfk_td.click(function(){
		$tr=$(this).closest("tr");
		$input=$("input[name='Td25_kcfkmx\.GS']",$tr);
	 	if($(this).hasClass("selectArea")){
			$(this).removeClass("selectArea");
			$input.attr("readonly","readonly");
			$input.val("");  
		}else{
			$(this).addClass("selectArea");
			$input.removeAttr("readonly");
			$input.val(1); 
		}
	});
	
	
});
</script>
<div class="page">
	<!-- 表单头 -->
	<div class="pageHeader">
		<div class="searchBar">
			<!-- 表单名称 -->
			<h1>
				基站勘察反馈单
			</h1>

		</div>
	</div>
	<!-- 主操作按钮 -->
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<a class="save" href="javascript:saveForm();"><span>保&nbsp;&nbsp;存</span>
				</a>
			</li>
			<li class="line">
				line
			</li>
		</ul>
	</div>

	<div class="pageContent" layouth="48">
		<form id="kcfk_form" action="save.do" method="post"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="tableInfomation"
				value="noFatherTable:com.rms.dataObjects.mbk.Td24_kcfkb" />
			<input type="hidden" name="_callbackType" value="" />
			<input type="hidden" name="_message" value="保存" />
			<input type="hidden" name="_forwardUrl" value="" />
			<input type="hidden" name="_navTabId" value="kcsqList" />

			<input type="hidden" name="Td24_kcfkb.ID" value="${Td24_kcfkb.id }" />
			<input type="hidden" name="Td24_kcfkb.MBK_ID"
				value="${Td24_kcfkb.mbk_id }" />
			<input type="hidden" name="Td24_kcfkb.FKSJ" value="${now}" />

		<table class="report" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;" >
			<thead>
				<tr>
					<th style="width: 256px;">类型</th> 
					<th style="width: 366px;"  colspan="2">选择(打钩)</th>  
				</tr>
			</thead>
			<tbody>
				 <c:forEach var="Tc01_property" items="${Tc01_property}">
					<c:if test="${Tc01_property.flag=='[1]'}">
						<tr>
							<td style="width: 256px;" >${Tc01_property.name }
							<input type="hidden" name="Td25_kcfkmc.ID" value=""/>
							<input type="hidden" name="Td25_kcfkmc.KCFK_ID" value=""/>
							<input type="hidden" name="Td25_kcfkmc.FKX" value=""/>
							</td>
							<td style="width: 383px;"  colspan="2" class="kcfk_td">
								<input type="hidden" name="Td25_kcfkmx.GS" value=""/>
							</td> 
						</tr> 
					</c:if>
				</c:forEach>
				<tr>
					<td style="width: 256px;" >类型</td>
					<td >选择(打钩)</td> 
					<td  >个数</td>  
				</tr> 
				<c:forEach var="Tc01_property" items="${Tc01_property}">
				<c:if test="${Tc01_property.flag=='[2]'}">
				<tr>
					<td style="width: 256px;" >${Tc01_property.name}</td>
					<td class="kcfk_td">
						<input type="hidden" name="Td25_kcfkmc.ID" value=""/>
						<input type="hidden" name="Td25_kcfkmc.KCFK_ID" value=""/>
						<input type="hidden" name="Td25_kcfkmc.FKX" value=""/>
					</td> 
					<td ><input type="text" name="Td25_kcfkmx.GS" value="" style="width: 191px;height:24px;border-top:0;"/></td> 
				</tr>  
				</c:if></c:forEach>
				<tr>
					<td style="width: 256px;" >其他</td>
					<td style="width: 128px;" class="kcfk_td kcfk_tdgs">
						<input type="hidden" name="Td25_kcfkmc.ID" value=""/>
						<input type="hidden" name="Td25_kcfkmc.KCFK_ID" value=""/>
						<input type="hidden" name="Td25_kcfkmc.FKX" value=""/>
					</td> 
					<td class="kcfk_tdgs"><input type="text" name="Td25_kcfkmx.GS" value="" style="width: 191px;height:24px;border-top:0;"/></td> 
				</tr> 
				<tr>
					<td style="width: 256px;" >其他说明</td>
					<td style="width: 383px;" colspan="2">
					<textarea class="td-textarea" style="width: 618px; height:42px;border-right:0;"
						name="Td23_kcsqb.QTSM">${Td23_kcsqb.qtsm }</textarea>
					</td> 
				</tr> 
				<tr>
					<td style="width: 64px;" >中心签字</td>
					<td style="width: 64px;"><input type="text"  name="Td24_kcfkb.WXZXQZ" style="width: 191px;height:24px;" value="${Td24_kcfkb.wxzxqz}"/></td> 
					<td style="width: 64px;">设计院签字</td> 
					<td style="width: 64px;"><input type="text"  name="Td24_kcfkb.SJYQZ" style="width: 191px;height:24px;" value="${Td24_kcfkb.sjyqz}"/></td> 
				</tr>  
				<tr>
					<td style="width: 256px;" >
						待反馈后再确认：
					（1）需要提交结构图后再定，
					（2）改址后（超过50米）需要重新定位，
					（3）不可签（位置、挂高、承重不符合要求）</td>
					<td style="width: 383px;" colspan="2">
				<textarea class="td-textarea" style="width: 618px; height: 81px;"
						name="Td23_kcsqb.FKQR">${Td23_kcsqb.fkqr }</textarea>
				</td> 
				</tr>  
			</tbody>
		</table>
		</form>
		<div style="clear: both"></div>


	</div>