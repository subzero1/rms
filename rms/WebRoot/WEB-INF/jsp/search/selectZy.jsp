<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		
<script language="javascript">		
$(function(){
	$("#zynd").change(function(){
		$.ajax({
			type: 'POST',
			url: 'search/getGczy.do',
			data: {'zynd':$(this).val()},
			dataType: "xml",
			cache: false,
			success: function(xml){
				if($(xml).text() == ""){
					alertMsg.info("����רҵ���ݴ���!");
					return;
				}
				$("#zydl").text("");
				$("#zyxx").text("");
				
				$(xml).find("zydl").each(function(){
					$("#zydl").append("<li><input type='checkbox' name='zy' value='"+$(this).find("id").text()+"'/><span>"+$(this).find("mc").text()+"</span></li>");
				});	
				
				$("input[name='zy']").click(function(){
					var dl_id = $(this).val();
					if(this.checked){
						$.ajax({
							type: 'POST',
							url: 'search/getZyxx.do',
							data: {'dl':dl_id},
							dataType: "xml",
							cache: false,
							success: function(xml){
								if($(xml).text() == ""){
									alertMsg.info("����רҵϸ�����!");
									return;
								}
								
								$(xml).find("zyxx").each(function(){
									$("#zyxx").append("<li><input type='checkbox' name='xx' value='"+$(this).text()+"' dl='"+dl_id+"'/><span>"+$(this).text()+"</span></li>");
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

//ȷ��ѡ��
function confirmSel(){
	var zydl = "",zyxx = "";
	$("input[name='zy']:checked").each(function(){
		zydl = zydl + $(this).next("span").text() + ",";
	});
	
	$("input[name='xx']:checked").each(function(){
		zyxx = zyxx + $(this).val() + ",";
	});
	$("input[fieldName='zydl']").val(zydl.substring(0,zydl.length-1));
	$("input[fieldName='zyxx']").val(zyxx.substring(0,zyxx.length-1));
}

//ȡ������ѡ��
function cancelSel(){
	$("#zydl").find("input[name='zy']").attr("checked",false);
	$("#zyxx").children().remove();
}
	
</script>
		

<div class="page">
	<div class="pageContent"  layouth="50">
		<select id="zynd" name="zynd">
			<option value="0">��ѡ��רҵ</option>
			<c:forEach var="obj" items="${result}">
				<option value="${obj }">
					${obj }
				</option>
			</c:forEach>
		</select>
		<br/><br/>
		<h2>����רҵ��</h2>
		<table>
			<tr>
				<td><ul id="zydl"></ul></td>
			</tr>
		</table>
		<h2>רҵϸ�ࣺ</h2>
		<table>
			<tr>
				<td><ul id="zyxx"></ul></td>
			</tr>
		</table>
	</div>
	<br/>
	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="javascript:confirmSel();">ȷ ��</button></div></div></li>
			<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="javascript:cancelSel();">�� ��</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="Button" class="close">ȡ ��</button></div></div></li>
		</ul>
	</div>
</div>
