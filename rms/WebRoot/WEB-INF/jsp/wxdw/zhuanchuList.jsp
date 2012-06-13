<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script type="text/javascript">
	$(function(){
	});
	function zhuanchu(){
		if ($.trim($("#zcsl").val())== "" || isNaN($("#zcsl").val()) || $("#zcsl").val().indexOf(".") != -1 ||parseInt($("#zcsl").val())<=0 || parseInt($("#zcsl").val())>parseInt("${tf07.kcsl}")){
				alertMsg.error("转出数量必须为正整数且不能大于${tf07.kcsl}");
				return false;
			}
			$("#zhuanchubutton").attr("href",$("#zhuanchubutton").attr("href")+"&sl="+$("#zcsl").val());
			$("#zhuanchubutton").click();
	}
</script>


<div class="page">
<span style="color:blue;">${tf07.clmc }</span><span style="margin-left:5px;">转出数量：<input type="text" id="zcsl" style="width:40px;" value="${tf07.kcsl }"/></span>
	<div class="pageContent">
		<table class="table" width="100%" layouth="40">
			<thead>
				<tr>
					<th style="width: 350px;">工程名称</th>
					<th style="width: 50px;"></th>
				</tr>
			</thead>
			<tbody>
			<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${gcxxList}">
					<tr>
						<td>${obj.gcmc }</td>
						<td><a id="zhuanchubutton" style="display:none;margin-left:3px;color:darkblue" href="wxdw/zhuanchu.do?project_id=${obj.id }&tf07_id=${tf07.id }" title="转出后将不可回退，是否确定转出？" target="ajaxTodo" callback="dialogAjaxDone">转出</a><a href="#" style="margin-left:3px;color:darkblue" onclick="zhuanchu()">转出</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>