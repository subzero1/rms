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
	var $kcfk_td=$(".kcfk_td", navTab.getCurrentPanel());
	var $input;
	var $tr;
	$kcfk_td.css({cursor:"hand"});
	$kcfk_td.click(function(){
		$tr=$(this).closest("tr");
		$input=$("input[name='Td25_kcfkmx\.GS']",$tr);
	 	if($(this).hasClass("selectArea")){
			$(this).removeClass("selectArea");
			$input.attr("readonly","true");
			$input.val("");  
		}else{
			$(this).addClass("selectArea");
			$input.removeAttr("readonly");
			$input.val(1); 
		}
	});
	//初始化各个选项的值 选中与否
	$tr = $kcfk_td.closest("tr");
	$tr.each(function(){
		$input=$("input[name='Td25_kcfkmx\.GS']",this);
		if($input.val()!=''&&parseInt($input.val())!=0){
			$(this).find("td:eq(1)").addClass("selectArea");
		}
	});
	//是否可读
	
	
});
function saveForm(){
	$("#kcfk_form",navTab.getCurrentPanel()).submit();
}
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
			<input type="hidden" name="tableInfomation" 
			value="Td24_kcfkb,id,kcfk_id:com.rms.dataObjects.mbk.Td25_kcfkmx"/>
			<input type="hidden" name="_callbackType" value="" />
			<input type="hidden" name="_message" value="保存" />
			<input type="hidden" name="_forwardUrl" value="" />
			<input type="hidden" name="_navTabId" value="kcfkList" />

			<input type="hidden" name="Td24_kcfkb.ID" value="${Td24_kcfkb.id }" />
			<input type="hidden" name="Td24_kcfkb.MBK_ID"
				value="${Td24_kcfkb.mbk_id }" />
			<input type="hidden" name="Td24_kcfkb.FKSJ" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			<input type="hidden" name="Td24_kcfkb.CJR" value="${user.name}"/>

		<table class="report" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;" >
			<thead>
				<tr>
					<th style="width: 196px;">类型</th> 
					<th style="width: 256px;"  colspan="2">选择(打钩)</th>  
				</tr>
			</thead>
			<tbody>
				<c:forEach var="Td25_kcfkmx" items="${Td25_kcfkmxList}">
					<c:if test="${Td25_kcfkmx[1]=='[1]'}">
						<tr>
							<td style="width: 196px;" >${Td25_kcfkmx[0].fkx}
							<input type="hidden" name="Td25_kcfkmx.ID" value="${Td25_kcfkmx[0].id}"/>
							<input type="hidden" name="Td25_kcfkmx.KCFK_ID" value="${Td25_kcfkmx[0].kcfk_id}"/>
							<input type="hidden" name="Td25_kcfkmx.FKX" value="${Td25_kcfkmx[0].fkx}"/>
							</td>
							<td style="width: 256px;"  colspan="2" class="kcfk_td">
								<input type="hidden" name="Td25_kcfkmx.GS" value="${Td25_kcfkmx[0].gs}"/>
							</td> 
						</tr> 
					</c:if>
				</c:forEach>
				 <c:forEach var="Tc01_property" items="${TableList}">
					<c:if test="${Tc01_property.flag=='[1]'}">
						<tr>
							<td style="width: 196px;" >${Tc01_property.name }
							<input type="hidden" name="Td25_kcfkmx.ID" value=""/>
							<input type="hidden" name="Td25_kcfkmx.KCFK_ID" value=""/>
							<input type="hidden" name="Td25_kcfkmx.FKX" value="${Tc01_property.name }"/>
							</td>
							<td style="width: 256px;"  colspan="2" class="kcfk_td">
								<input type="hidden" name="Td25_kcfkmx.GS" value=""/>
							</td> 
						</tr> 
					</c:if>
				</c:forEach>
				<tr>
					<td style="width: 196px;" >类型</td>
					<td >选择(打钩)</td> 
					<td  >个数</td>  
				</tr> 
				
				<c:forEach var="Td25_kcfkmx" items="${Td25_kcfkmxList}">
				<c:if test="${Td25_kcfkmx[1]=='[2]'}">
				<tr>
					<td style="width: 196px;" >${Td25_kcfkmx[0].fkx}</td>
					<td class="kcfk_td">
						<input type="hidden" name="Td25_kcfkmx.ID" value="${Td25_kcfkmx[0].id}"/>
						<input type="hidden" name="Td25_kcfkmx.KCFK_ID" value="${Td25_kcfkmx[0].kcfk_id}"/>
						<input type="hidden" name="Td25_kcfkmx.FKX" value="${Td25_kcfkmx[0].fkx}"/>
					</td> 
					<td ><input type="text" name="Td25_kcfkmx.GS" value="${Td25_kcfkmx[0].gs}" style="width: 191px;height:24px;border-top:0;"/></td> 
				</tr>  
				</c:if></c:forEach>
				
				<c:forEach var="Tc01_property" items="${TableList}">
				<c:if test="${Tc01_property.flag=='[2]'}">
				<tr>
					<td style="width: 196px;" >${Tc01_property.name}</td>
					<td class="kcfk_td">
						<input type="hidden" name="Td25_kcfkmx.ID" value=""/>
						<input type="hidden" name="Td25_kcfkmx.KCFK_ID" value=""/>
						<input type="hidden" name="Td25_kcfkmx.FKX" value="${Tc01_property.name }"/>
					</td> 
					<td ><input type="text" name="Td25_kcfkmx.GS" value="" style="width: 191px;height:24px;border-top:0;"/></td> 
				</tr>  
				</c:if></c:forEach>
				 
				<tr>
					<td style="width: 196px;" >其他说明</td>
					<td style="width: 256px;" colspan="2">
					<textarea class="td-textarea" style="width: 618px; height:42px;border-right:0;"
						name="Td23_kcsqb.QTSM">${Td24_kcfkb.qtsm}</textarea>
					</td> 
				</tr> 
				<tr>
					<td style="width: 64px;" >中心签字</td>
					<td style="width: 64px;"><input type="text"  name="Td24_kcfkb.WXZXQZ" style="width: 191px;height:24px;" value="${Td24_kcfkb.wxzxqz}"/></td> 
					<td style="width: 64px;">设计院签字</td> 
					<td style="width: 64px;"><input type="text"  name="Td24_kcfkb.SJYQZ" style="width: 191px;height:24px;" value="${Td24_kcfkb.sjyqz}"/></td> 
				</tr>  
				<tr>
					<td style="width: 196px;" >
						待反馈后再确认：
					（1）需要提交结构图后再定，
					（2）改址后（超过50米）需要重新定位，
					（3）不可签（位置、挂高、承重不符合要求）</td>
					<td style="width: 256px;" colspan="2">
				<textarea class="td-textarea" style="width: 618px; height: 81px;"
						name="Td23_kcsqb.FKQR">${Td24_kcfkb.fkqr}</textarea>
				</td> 
				</tr>  
			</tbody>
		</table>
		</form>
		<div style="clear: both"></div>


	</div>