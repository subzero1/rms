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
		alertMsg.confirm("${type }后将不能修改，请确认后再执行此动作，是否确认${type }？",{
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
	var flag2 = true;
	var ids = [];
	$("select[name=Tf08_clmxb\\.CLMC]").each(function(){
		if ($(this).closest("tr").css("display") != "none" && $.trim($(this).val())==""){
			flag = false;
			return;
		}
		var id = $(this).children(":selected").attr("flag");
		for(var i =0;i!=ids.length;i++){
			if (id!=undefined && id == ids[i]){
				flag1 = false;
				return;
			}
		}
		ids.push(id);
	});
	if (!flag){
		alertMsg.info("请选择材料类型及材料名称");
		return;
	}
	if (!flag1){
		alertMsg.info("发现重复的材料，请合并后重新保存");
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
		alertMsg.info("请在数量栏填入正整数");
		return;
	}
	//if ($("select[name=Tf08_clmxb\\.CLLX]").size() == 0){
		//return;
	//}
	$("#rkckjl",navTab.getCurrentPanel()).trigger("onsubmit");
}


//自动选择材料
function autoSelectClxx(inputObj){
	if ($(inputObj).val()!="" && event.keyCode == DWZ.keyCode.ENTER){
		var cur_tr = $(inputObj).closest("tr");
		alert('s');
		$.ajax({
			type: 'post',
			url: 'ajaxSelectClxx.do',
			data: {'keyword':$(inputObj).val()},
			dataType:'xml',
			cache: false,
			success: function (xml) {
				if($(xml).text() == ""){
					alertMsg.info("未找到要查询的信息!");
					return;
				}
				cur_tr.find("[name=Tf08_clmxb.CLBM]").val($(xml).find("clbm").text());
				cur_tr.find("[name=Tf08_clmxb.CLMC]").val($(xml).find("clmc").text());
				cur_tr.find("[name=Tf08_clmxb.XH]").val($(xml).find("xh").text());
				cur_tr.find("[name=Tf08_clmxb.GG]").val($(xml).find("gg").text());
				cur_tr.find("[name=Tf08_clmxb.DW]").val($(xml).find("dw").text());
			},
			error: DWZ.ajaxError
		});	
	}
}
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
						<th type="lookup" style="width:180px;" id="clxxDetail[#index#].clxxLookup.CLBM" lookupGroup="clxxDetail[#index#].clxxLookup" name="Tf08_clmxb.CLBM"  hideName="Tf08_clmxb.ID" lookupName="clxxLookup" lookupUrl="selectClxx.do" suggestUrl="ajaxAutocompleteClxx.do" suggestFields="CLBM,CLMC,XH,GG,DW" autocomplete="off">材料编码</th>
						<th type="text" style="width:320px;" id="clxxDetail[#index#].clxxLookup.CLMC" name="Tf08_clmxb.CLMC"  hideName="Tf08_clmxb.FLAG" hideValue="0">材料名称</th>
						<th type="text" style="width:120px;" id="clxxDetail[#index#].clxxLookup.GG" name="Tf08_clmxb.GG" hideName="Tf08_clmxb.ZHXX_ID" hideValue="${gcxx.id }">规格</th>
						<th type="text" style="width:120px;" id="clxxDetail[#index#].clxxLookup.XH" name="Tf08_clmxb.XH" hideName="Tf08_clmxb.DZ" hideValue="${dz }">型号</th>
						<th type="text" style="width:60px;" id="clxxDetail[#index#].clxxLookup.DW" name="Tf08_clmxb.DW" hideName="Tf08_clmxb.CZSJ" hideValue="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}"/>">单位</th>
						<th type="text" class="" style="width:60px;" name="Tf08_clmxb.SL" hideName="Tf08_clmxb.SGDW_ID" hideValue="${sgdw_id }">数量</th>
						<th type="del" hideName="Tf08_clmxb.CZRY" hideValue="${user.name }">操作</th>
					</tr>
				</thead>
				<tbody>
				<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${tf08List}">
					<tr>
					<input type="hidden" name="Tf08_clmxb.ID" value="${obj.id}"/>
					<input type="hidden" name="Tf08_clmxb.FLAG" value="${obj.flag}"/>
					<input type="hidden" name="Tf08_clmxb.ZHXX_ID" value="${obj.zhxx_id}"/>
					<input type="hidden" name="Tf08_clmxb.DZ" value="${obj.dz}"/>
					<input type="hidden" name="Tf08_clmxb.CZSJ" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${obj.czsj}"/>"/>
					<input type="hidden" name="Tf08_clmxb.SGDW_ID" value="${obj.sgdw_id}"/>
					<input type="hidden" name="Tf08_clmxb.CZRY" value="${obj.czry}"/>
						<td>
							<input type="text" id="clxxDetail[${offset }].dwz_clxxLookup.CLBM" name="Tf08_clmxb.CLBM" value="${obj.clbm}" style="width:0px;" index="${offset }" lookupGroup="clxxDetail"  lookupName="clxxLookup" suggestUrl="ajaxAutocompleteClxx.do" suggestFields="CLBM,CLMC,XH,GG,DW" autocomplete="off"/>
							<a class="btnLook" href="selectClxx.do" index="${offset }" lookupGroup="clxxDetail"  lookupName="clxxLookup">查找带回</a>
						</td>
						<td><input class="" type="text" name="Tf08_clmxb.CLMC" value="${obj.clmc}" style="width:0px;"/></td>
						<td><input class="" type="text" name="Tf08_clmxb.GG" value="${obj.gg}" style="width:0px;"/></td>
						<td><input class="" type="text" name="Tf08_clmxb.XH" value="${obj.xh}" style="width:0px;"/></td>
						<td><input class="" type="text" name="Tf08_clmxb.DW" value="${obj.dw}" style="width:0px;"/></td>
						<td><input class="" type="text" name="Tf08_clmxb.SL" value="${obj.sl}" flag="${obj.sl }" style="width:0px;"/></td>
						<td><a href="javascript:fsd" class="btnDel emptyInput" title="确认删除此明细">删除</a></td>
					</tr>
					<c:set var="offset" value="${offset+1}"/>
				</c:forEach>
				</tbody>
			</table></form>
		</div>
	</div>
	
</div>
