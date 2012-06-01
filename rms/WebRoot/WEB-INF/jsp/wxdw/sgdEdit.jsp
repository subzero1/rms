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
			$.ajax({
				type: 'POST',
				url:"wxdw/ajaxSaveSgd.do?wxdw_id=${param.wxdw_id}",
				data:$data.serializeArray(),
				dataType:"json",
				cache: false,
				success: callback || DWZ.ajaxDone,
				error: DWZ.ajaxError
			});
			
		});
		$(".preButt .del").click(function(){
			var sgd_id = $(this).prev("input[name=id]");
			if(sgd_id){
				$.ajax({
				type: 'POST',
				url:"wxdw/ajaxDelSgd.do?sgd_id="+sgd_id.val(),
				dataType:"json",
				cache: false,
				success: callback || DWZ.ajaxDone,
				error: DWZ.ajaxError
			});
			
			}
		});
		
	}); 	
	
</script> 

<table id="sgd_list" width="98%" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;">
	<tr>
		<td class="preButt">
			<input type="hidden" name="id" value=""/>
			<a class="save" href="javascript:saveSgd();" ><span>创建</span></a>
		</td>
		<td style="width:180px;padding:10px;">
			<input type="text" style="width:100%;" name="mc" value=""/>
			<textarea style="width:100%;height:40px;" name="bz"></textarea>
		</td>
		<td>
			<input type="checkbox" name="qyzyfp" value="苏州市—电缆"/>苏州市—电缆
			<input type="checkbox" name="qyzyfp" value="苏州市—移动"/>苏州市—移动
			<input type="checkbox" name="qyzyfp" value="相城区—移动"/>相城区—移动
			<input type="checkbox" name="qyzyfp" value="苏州市—光缆"/>苏州市—光缆
			<input type="checkbox" name="qyzyfp" value="张家港—移动"/>张家港—移动
		</td>
	</tr>
	<c:forEach begin="1" end="20">
	<tr>
		<td class="preButt">
			<input type="hidden" name="id" value=""/>
			<a class="save" href="javascript:;" ><span>保存</span></a>&nbsp;&nbsp;
			<a class="del" href="javascript:;" ><span>删除</span></a>
		</td>
		<td style="width:180px;padding:10px;">
			<input type="text" style="width:100%;" name="mc" value="施工单位名称"/>
			<textarea style="width:100%;height:40px;" name="bz">施工单位备注信息</textarea>
		</td>
		<td>
			<input type="checkbox" name="qyzyfp" value="苏州市—电缆" checked/>苏州市—电缆
			<input type="checkbox" name="qyzyfp" value="苏州市—移动"/>苏州市—移动
			<input type="checkbox" name="qyzyfp" value="相城区—移动" checked/>相城区—移动
			<input type="checkbox" name="qyzyfp" value="苏州市—光缆" checked/>苏州市—光缆
			<input type="checkbox" name="qyzyfp" value="张家港—移动"/>张家港—移动
		</td>
	</tr>
	</c:forEach>
</table>