<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />

<script language="javascript">
var change = false;
function act(){
	if (change){
		alertMsg.info("先点保存按钮。");
	} else {
		alertMsg.confirm("入库后将不能修改，请确认后再执行此动作，是否确认入库？",{
			okCall:function(){
				$.ajax({
						type: 'POST',
						url:"wxdw/crkEditAjax.do?project_id=${gcxx.id}&dz=${dz}",
						dataType:"json",
						cache: false,
						success: function(json){
							DWZ.ajaxDone(json);
							navTab.reload();
						},
						error: DWZ.ajaxError
				});
			}
		});
	}
}
function checkandsave(){
	var flag = true;
	var flag1 = true;
	var ids = [];
	$("select[name=Tf08_clmxb\\.CLMC]").each(function(){
		if ($(this).closest("tr").css("display") != "none" && $.trim($(this).val())==""){
			flag = false;
			return;
		}
		var id = $(this).attr("flag");
		for(var i =0;i!=ids.length;i++){
			if (id == ids[i]){
				flag1 = false;
				return;
			}
		}
		ids.push(id);
	});
	if (!flag){
		alertMsg.error("请选择材料类型及材料名称");
		return;
	}
	if (!flag1){
		alertMsg.error("发现重复的材料，请合并后重新保存");
		return;
	}
	flag = true;
	$("input[name=Tf08_clmxb\\.SL]").each(function(){
		if (($(this).closest("tr").css("display") != "none") && (($.trim($(this).val())== "" || isNaN($(this).val()) || $(this).val().indexOf(".") != -1 ||parseInt($(this).val())<=0))){
			flag = false;
			return;
		}
	});
	if (!flag){
		alertMsg.error("请在数量栏填入正整数");
		return;
	}
	if ($("select[name=Tf08_clmxb\\.CLLX]").size() == 0){
		return;
	}
	$("#rkckjl",navTab.getCurrentPanel()).trigger("onsubmit");
}
$(function(){
	$("#rkckjl :input").live("change",function(){
		change = true;
	});
	$("select[name=Tf08_clmxb\\.CLLX]").die("change");
	$("select[name=Tf08_clmxb\\.CLLX]").live("change",function(){
		var params = "cllx="+$(this).val()+"&dz=${dz}&project_id=${gcxx.id}";
		var $this = $(this);
		$.ajax({type:"post",async:false,url:"wxdw/getClmc.do",data:params, success:function (msg) {
			$this.closest("tr").find("[name=Tf08_clmxb\\.CLMC]").empty();
			$this.closest("tr").find("[name=Tf08_clmxb\\.CLMC]").append(msg);
			$this.closest("tr").find("[name=Tf08_clmxb\\.CLMC]").change();
		}});
	});
	$("select[name=Tf08_clmxb\\.CLMC]").die("change");
	$("select[name=Tf08_clmxb\\.CLMC]").live("change",function(){
		var $this = $(this);
		var id = $this.children(':selected').attr('flag');
		var gg = $this.closest('tr').find('[name=Tf08_clmxb\\.GG]');
		var xh = $this.closest('tr').find('[name=Tf08_clmxb\\.XH]');
		var dw = $this.closest('tr').find('[name=Tf08_clmxb\\.DW]');
		var sl = $this.closest('tr').find('[name=Tf08_clmxb\\.SL]');
		var params = "id="+id+"&dz=${dz}";
		$.ajax({type:"post",async:false,url:"wxdw/setClxx.do",data:params, success:function (msg) {
			msgs = $.trim(msg).split("@#$");
			gg.val(msgs[0]);
			xh.val(msgs[1]);
			dw.val(msgs[2]);
			sl.val(msgs[3]);
		}});
	});
});

</script>

<div class="page">
	<div class="pageHeader">
		<div class="searchBar">
			<h1>工程材料${type }</h1>
		</div>
	</div>
	
	<div class="panelBar">
		<ul class="toolBar" id="_flowButton">
		 	<li><a class="save"	href="javascript:checkandsave();"><span>保 存</span></a></li>
			<li class="line">line</li>
		 	<li><a class="icon"	href="javascript:act();"><span>${type }</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	
	<div class="pageContent" layoutH="0">
		
		<div class="pageFormContent" layouth="138" style="text-align:center;">
			<form id="rkckjl" action="save.do" method="post"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		  <input type="hidden" name="tableInfomation" value="noFatherTable:com.rms.dataObjects.wxdw.Tf08_clmxb"/>
			<input type="hidden" name="_navTabId" value="" keep="true"/>
			<input type="hidden" name="_message" value="数据保存" keep="true"/>
			<input type="hidden" name="_callbackType" value="forward" keep="true"/>
				<span style="float:left">工程名称：${gcxx.gcmc}</span>
				<span style="float:left;margin-left:20px;">工程编号：${gcxx.gcbh}</span>
			<div class="divider" style="height:1px;"></div>
			<div style="text-align:left;color:blue;"><h3>待${type }材料&nbsp;</h3></div>
			<table width="100%" class="list  itemDetail" width="100%">
				<thead>
					<tr>
						<th type="enum" style="width:85px;" name="Tf08_clmxb.CLLX" enumName="cllx" enumUrl="wxdw/cllxSelect.do" enumData="{'type':'材料类型','dz':'${dz }','project_id':'${gcxx.id }'}" hideName="Tf08_clmxb.ID">材料类别</th>
						<th type="enum" style="width:320px;" name="Tf08_clmxb.CLMC" enumName="clmc" enumUrl="wxdw/clmcSelect.do" enumData="{'dz':'${dz }'}" hideName="Tf08_clmxb.FLAG" hideValue="0">材料名称</th>
						<th type="text" style="width:120px;" name="Tf08_clmxb.GG" hideName="Tf08_clmxb.ZHXX_ID" hideValue="${gcxx.id }">规格</th>
						<th type="text" style="width:120px;" name="Tf08_clmxb.XH" hideName="Tf08_clmxb.DZ" hideValue="${dz }">型号</th>
						<th type="text" style="width:60px;" name="Tf08_clmxb.DW" hideName="Tf08_clmxb.CZSJ" hideValue="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}"/>">单位</th>
						<th type="text" class="" style="width:60px;" name="Tf08_clmxb.SL" hideName="Tf08_clmxb.SGDW_ID" hideValue="${sgdw_id }">数量</th>
						<th type="del" hideName="Tf08_clmxb.CZRY" hideValue="${user.name }">操作</th>
					</tr>
				</thead>
				<tbody>
				<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${tf08List}">
				<input type="hidden" name="Tf08_clmxb.ID" value="${obj.id}"/>
					<tr>
						<td>
							<netsky:htmlSelect name="Tf08_clmxb.CLLX" objectForOption="cllxList" valueForOption="" showForOption="" value="${obj.cllx}" extend="" extendPrefix="true" style="width:0px;"/>
						</td>
						<td>
							<select name="Tf08_clmxb.CLMC" style="width:0px;">
								<option></option>
								<c:forEach items="${clmcselectList[offset] }" var="clmc">
									<option flag="${clmc.id }" title="${clmc.clmc}—${empty clmc.gg ? '（无规格说明）' : clmc.gg}—${empty clmc.xh ? '（无型号说明）' : clmc.xh}" value="${clmc.clmc }" <c:if test="${clmc.clmc == obj.clmc && clmc.gg == obj.gg && clmc.xh == obj.xh}">selected="selected"</c:if>>${clmc.clmc}—${empty clmc.gg ? '（无规格说明）' : clmc.gg}—${empty clmc.xh ? '（无型号说明）' : clmc.xh}</option> 
								</c:forEach>
							</select>
						</td>
						<td><input class="" type="text" name="Tf08_clmxb.GG" value="${obj.gg}" style="width:0px;"/></td>
						<td><input class="" type="text" name="Tf08_clmxb.XH" value="${obj.xh}" style="width:0px;"/></td>
						<td><input class="" type="text" name="Tf08_clmxb.DW" value="${obj.dw}" style="width:0px;"/></td>
						<td><input class="" type="text" name="Tf08_clmxb.SL" value="${obj.sl}" style="width:0px;"/></td>
						<td><a href="javascript:fsd" class="btnDel emptyInput" title="确认删除此明细">删除</a></td>
					</tr>
					<c:set var="offset" value="${offset+1}"/>
				</c:forEach>
				</tbody>
			</table></form>
		</div>
	</div>
	
</div>
