<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />
<div class="page">
	<div class="pageContent">
	<form id="form1" name="form1" class="pageForm required-validate" action="info/modifySjry.do" method="post" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="project_id" value="${param.project_id }" />
		<input type="hidden" name="module_id" value="${param.module_id }" />
		<input type="hidden" name="doc_id" value="${param.doc_id }" />
		<input type="hidden" name="opernode_id" value="${param.opernode_id }" />

		<div class="pageFormContent" layoutH="50">
			<table border='0' width='100%' cellspacing='0' cellpadding='0' style='border-collapse:collapse;' class='alert-table'>
				<c:if test="${sjryRole=='nb'}">
				<tr>
					<td><netsky:htmlSelect name="T_SJDW" objectForOption="sjdwList" style="width:300px;" valueForOption="name" showForOption="name" extend="内部设计,内部设计" extendPrefix="true" value="${default_sjdw}" onChange="switchSjdw(this)" htmlClass="td-select" />
					<input type="hidden" name="sjMatchList" id="sjMatchList" value="${sjMatchList}">
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				</c:if>
				<tr>
					<td><netsky:htmlSelect name="T_SJRY" id="T_SJRY" objectForOption="sjryList" style="width:123px;" valueForOption="id" showForOption="name" value="${default_sjry}" htmlClass="td-select"/>
					</td>
				</tr>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">确认修改</button></div></div></li>	
				<li><div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div></li>
			</ul>
		</div>
	</form>
	</div>
</div>
<script type="">
	function switchSjdw(obj){
		var t_sjry_obj = document.getElementById("T_SJRY");
		for (var i = t_sjry_obj.length; i >= 0; i--) {
			t_sjry_obj.remove(i);
		}
		
		var target_sjdw = obj.value;
		var sjMatchList = $("#sjMatchList").val();
		var sjMatchArray = sjMatchList.split(";");
		for(var i = 0;i < sjMatchArray.length;i++){

			var t_sjMatch = sjMatchArray[i];
			var tt = t_sjMatch.split("::");

			if(tt[0] == target_sjdw){
				var opt = document.createElement("option");
				opt.value = tt[1];
				opt.innerText = tt[2];
				t_sjry_obj.appendChild(opt);
			}
		}
	}
</script>