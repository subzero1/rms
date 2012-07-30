<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script language="javascript">		
$(function(){
	$("#qknd").change(function(){
		$.ajax({
			type: 'POST',
			url: 'search/getTzqk.do',
			data: {'qknd':$(this).val()},
			dataType: "xml",
			cache: false,
			success: function(xml){
				if($(xml).text() == ""){
					alertMsg.error("返回切块数据错误!");
					return;
				}
				$("#qkdl").text("");
				$("#qkxl").text("");
				
				$(xml).find("qkdl").each(function(){
					$("#qkdl").append("<li><input type='checkbox' name='qk' value='"+$(this).find("id").text()+"'/><span>"+$(this).find("name").text()+"</span></li>");
				});	
				
				$("input[name='qk']").click(function(){
					var dl_id = $(this).val();
					if(this.checked){
						$.ajax({
							type: 'POST',
							url: 'search/getQkxx.do',
							data: {'dl':dl_id},
							dataType: "xml",
							cache: false,
							success: function(xml){
								if($(xml).text() == ""){
									alertMsg.error("返回切块细项错误!");
									return;
								}
								
								$(xml).find("qkxl").each(function(){
									$("#qkxl").append("<li><input type='checkbox' name='xl' value='"+$(this).text()+"' dl='"+dl_id+"'/><span>"+$(this).text()+"</span></li>");
								});	
							},
							error: DWZ.ajaxError
						});
					}else{
						$("input[dl="+dl_id+"]").parent().remove();
					}
				});
				
			},
			error: DWZ.ajaxError
		});
	});
});	

//确定选择
function confirmSel(){
	var dl = "",xl = "";
	$("input[name='qk']:checked").each(function(){
		dl = dl + $(this).next("span").text() + ",";
	});
	
	$("input[name='xl']:checked").each(function(){
		xl = xl + $(this).val() + ",";
	});
	$("input[fieldName='qkdl']").val(zydl.substring(0,dl.length-1));
	$("input[fieldName='qkxl']").val(zyxx.substring(0,xl.length-1));
}

//取消所有选择
function cancelSel(){
	$("#qkdl").find("input[name='qk']").attr("checked",false);
	$("#qkxl").children().remove();
}
	
</script>

<div class="page">
	<div class="pageContent"  layouth="50">
		<select id="qknd" name="qknd">
			<option value="0">请选择切块年度</option>
			<c:forEach var="obj" items="${result}">
				<option value="${obj }">
					${obj }
				</option>
			</c:forEach>
		</select>
		<h2>切块大类：</h2>
		<table>
			<tr>
				<td><ul id="qkdl"></ul></td>
			</tr>
		</table>
		<h2>切块细类：</h2>
		<table>
			<tr>
				<td><ul id="qkxl"></ul></td>
			</tr>
		</table>
	</div>
	<br/>
	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="javascript:confirmSel();">确 定</button></div></div></li>
			<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="javascript:cancelSel();">清 空</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div></li>
		</ul>
	</div>
</div>