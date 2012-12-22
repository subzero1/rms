<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<style> 
	.odd { background-color: #edf1f6;} 
	.even { background-color: ;} 
	.preButt {width:70px; background-color: #b8d0d6; text-align:center;}
</style> 

<script language="javascript"> 

	$(document).ready(function() { 
	
		$('#sgd_list td').css({borderBottom:"dashed 1px #b8d0d6"});
		$('#sgd_list tr:odd').addClass('odd'); 
		$('#sgd_list tr:even').addClass('even');
		
		$(".preButt .save").click(function(){
			var $data = $(this).closest("tr").find(":input");
			if ($(this).closest("tr").find(":input[name=mc]").val()==""){
				alertMsg.info('施工队名称不能为空！');
				return false;
			}
			$.ajax({
				type: 'POST',
				url:"wxdw/ajaxSaveSgd.do?wxdw_id=${param.wxdw_id}",
				data:$data.serializeArray(),
				dataType:"json",
				cache: false,
				success: function(json){
					DWZ.ajaxDone(json);
					$("#sgd_tab").loadUrl("wxdw/sgdEdit.do?lb=sgd&wxdw_id=${param.wxdw_id }");
				},
				error: DWZ.ajaxError
			});
			
		});
		$(".preButt .del").click(function(){
			var sgd_id = $(this).prev("input[name=id]");
			if(sgd_id){
			alertMsg.confirm("确认删除该施工队吗？", {
			okCall: function(){
				$.ajax({
						type: 'POST',
						url:"wxdw/ajaxDelSgd.do?sgd_id="+sgd_id.val(),
						dataType:"json",
						cache: false,
						success: function(json){
							DWZ.ajaxDone(json);
							$("#sgd_tab").loadUrl("wxdw/sgdEdit.do?lb=sgd&wxdw_id=${param.wxdw_id }");
						},
						error: DWZ.ajaxError
					});
				}
				});
			}
			
			
			
		});
		
	}); 	
	
</script> 

<table id="sgd_list" width="98%" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;">
	<tr>
		<td class="preButt">
			<input type="hidden" name="id" value=""/>
			<c:if test="${not empty param.wxdw_id}">
			<a class="save" href="javascript:saveSgd();" ><span>创建</span></a>
			</c:if>
		</td>
		<td style="width:180px;padding:10px;">
			<input type="text" style="width:100%;" name="mc" value=""/>
			<textarea style="width:100%;height:40px;" name="bz"></textarea>
		</td>
		<td>
			<c:forEach items="${dygxList}" var="dygx">
				<input type="checkbox" name="qyzyfp" value="${dygx.dq }-${dygx.zy }"/>${dygx.dq }-${dygx.zy }
			</c:forEach>
		</td>
	</tr>
	<c:forEach items="${sgdList}" var="sgd">
	<tr>
		<td class="preButt">
			<a class="save" href="javascript:;" ><span>保存</span></a>&nbsp;&nbsp;
			<input type="hidden" name="id" value="${sgd.id }"/>
			<a class="del" href="javascript:;" ><span>删除</span></a>
		</td>
		<td style="width:180px;padding:10px;">
			<input type="text" style="width:100%;" name="mc" value="${sgd.mc }"/>
			<textarea style="width:100%;height:40px;" name="bz">${sgd.bz }</textarea>
		</td>
		<td>
			<c:forEach items="${dygxList}" var="dygx">
				<input type="checkbox" name="qyzyfp" value="${dygx.dq }-${dygx.zy }" <c:if test="${not empty dygxMap[sgd][dygx.dq][dygx.zy]}">checked="checked"</c:if>/>${dygx.dq }-${dygx.zy }
			</c:forEach>
		</td>
	</tr>
	</c:forEach>
</table>