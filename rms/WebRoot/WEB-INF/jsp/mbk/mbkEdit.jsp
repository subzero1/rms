<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />
<style>
	#printTable tr{
		
	}
</style>
<script language="javascript">
var change = false;

$("#mbk_form :input",navTab.getCurrentPanel()).change(function(){
		change = true;
	});
function saveMbk(){
	if ($("[name=Td21_mbk\\.ZYBH]").val()=="" && "${Td21_mbk.id}"=="" && $("[name=Td21_mbk\\.JSXZ]",navTab.getCurrentPanel()).val()!=""){
		$.ajax({
		url:'mbk/getZybh.do',
		data:'jsxz='+$("[name=Td21_mbk\\.JSXZ]",navTab.getCurrentPanel()).val(),
		type:'post',
		success:function(msg){
			msg = $.trim(msg);
			$("[name=Td21_mbk\\.ZYBH]",navTab.getCurrentPanel()).val(msg);
			$("#mbk_form",navTab.getCurrentPanel()).submit();
		}
	});
	} else {
		$("#mbk_form",navTab.getCurrentPanel()).submit();
	}
}
$(function(){
	$(".lzspan",navTab.getCurrentPanel()).click(function(){
		var flag = $(this).attr("flag");
		var data = 'id=${Td21_mbk.id}&type='+flag;
		if (flag == "zdxf") {
			if ($("[name=Td21_mbk\\.TDR_ID]",navTab.getCurrentPanel()).val()==""){
				alertMsg.info("请选择谈点人，并点击『保存』按钮");
				return;
			}
		} else if (flag == "sfkc"){
			$("#sfkca").click();
			return false;
		} else if (flag == "fahs"){
			$("#fahsa").click();
			return false;
		} else if (flag == "ycqx"){
			var input_info = "<p><label>谈点周期延期 </label><input type=\"text\" size=\"5\" value=\"10\" id=\"_ycqx\"><label> 天</label></p>";
			alertMsg.confirm(input_info, {			
				okCall: function(){
					var ycqx = $("#_ycqx").val();
					if(ycqx == ''){
						alertMsg.info('延期天数必填');
						return false;
					}
					data = data + '&ycqx=' + $("#_ycqx").val();
					$.ajax({
					url:'mbk/mbkLz.do',
					type:'post',
					data:data,
					dataType:"json",
					cache: false,
					success: function(json){
						navTabAjaxDone(json);
					},
					error: DWZ.ajaxError
				 });
				}
			});
			return false;
		} 
		else if (flag == "kcsq"){//勘察申请
			var input_info = "<p id=\"_c_nums\">勘察申请说明：</p>"
							+"<p><textarea id=\"_sqkcsm\" cols=\"25\" rows=\"6\" style=\"overflow\:auto\" onKeyUp=\"javascript:getCNum()\"></textarea></p>";
			alertMsg.confirm(input_info, {
				okCall: function(){
					var sqkcsm = $("#_sqkcsm").val();
					if(sqkcsm == ''){
						alertMsg.info('必须填写【勘察申请说明】');
						return;
					}
					data = data + '&sqkcsm=' + sqkcsm;
					$.ajax({
					url:'mbk/mbkLz.do',
					type:'post',
					data:data,
					dataType:"json",
					cache: false,
					success: function(json){
						navTabAjaxDone(json);
					},
					error: DWZ.ajaxError
				 });
				}
			});
			return false;
		}
		else if (flag == "zjs"){
			$("#zjsa").click();
			return false;
		}
		var alertmsg = "";
		if ("${not empty rolesMap['20101'] }"=="true" && change){
			alertmsg = "有数据未保存，请先保存后再进行操作！";
			alertMsg.info(alertmsg);
			return false;
		}
		else{
			alertMsg.confirm((alertmsg+"确认【"+$(this).text()+"】吗？").replace(/\s/g,""),{
			okCall:function(){
				$.ajax({
					url:'mbk/mbkLz.do',
					type:'post',
					data:data,
					dataType:"json",
					cache: false,
					success: function(json){
						navTabAjaxDone(json);
						if (flag == "jsz"){
							navTab.openTab('autoform', 'flowForm.do?module_id=102&node_id=${firstNode}&flow_id=102&mbk_id=${Td21_mbk.id}',{'title':'新建工程'});
						}
					},
					error: DWZ.ajaxError
				});
			}
		 });
		}
	});
	
	//四方勘察
	$("#tdrOrg\\.Kcry").change(function(){
		var kcsj=$("#kcsj").val();
		var sm=$("#sm").val(); 
		
		var i=0;
		while((kcsj==''||sm=='')&&(i++)<10){
			setTimeout(function(){},500);
			kcsj=$("#kcsj").val();
		    sm=$("#sm").val();
		}
		var data = 'id=${Td21_mbk.id}&type=sfkc&ids='+$(this).val()+'&kcsj='+kcsj+'&sm='+sm; 
		$.ajax({
			url:'mbk/mbkLz.do',
			type:'post',
			data:data,
			dataType:"json",
			cache: false,
			success: function(json){
				navTabAjaxDone(json);
			},
			error: DWZ.ajaxError
		});
	});
	
	//方案会审
	$("#tdrOrg\\.Hsry").change(function(){
		var data = 'id=${Td21_mbk.id}&type=fahs&ids='+$(this).val();
		$.ajax({
			url:'mbk/mbkLz.do',
			type:'post',
			data:data,
			dataType:"json",
			cache: false,
			success: function(json){
				navTabAjaxDone(json);
			},
			error: DWZ.ajaxError
		});
	});
	
	//转建设
	$("#tdrOrg\\.Xmgly").change(function(){
		var data = 'id=${Td21_mbk.id}&type=zjs&xmgly_id='+$(this).val();
		$.ajax({
			url:'mbk/mbkLz.do',
			type:'post',
			data:data,
			dataType:"json",
			cache: false,
			success: function(json){
				navTabAjaxDone(json);
			},
			error: DWZ.ajaxError
		});
	});
});
function printMbk(){
	$("#printTable",navTab.getCurrentPanel()).jqprint();
}

$("#jsxz").change(function(){
		var data = $(this).val();
		if(data == '基站'){
			$("#jz").css("display","block");
			$("#xq").css("display","none");
			$("input[ids=jz]").attr("class","required");
			$("input[ids=xq]").attr("class","norequired");
		}
		else if(data == '室分' ){
			$("#jz").css("display","none");
			$("#xq").css("display","none");
			$("input[ids=jz]").attr("class","norequired");
			$("input[ids=xq]").attr("class","norequired");
		}
		else{
			$("#jz").css("display","none");
			$("#xq").css("display","block");
			$("input[ids=jz]").attr("class","norequired");
			$("input[ids=xq]").attr("class","required digits");
		}
	});
	
	/*
	 *计算还能输入多少汉字
	 */
	function getCNum(){
		var a = $("#_sqkcsm").val();
		var ac = 200 - a.length;
		if(ac < 0){
			ac = 0;
		}
		var at = "勘察申请说明： <font color=\"red\">还可以输入"+ac+"字</font>";
		$("#_c_nums").html(at);
	}
</script>
<div style="display: none">
	<table style="margin-top:10px;width:630px;" cellspacing="0" border="1" cellpadding="0" bordercolor="#000000" id="printTable">
			<caption style="width:630px;text-align:center;font-size:20px;font-weight:bolder;height:60px;line-height:60px;">资源信息单</caption>
			<tr style="height:50px;line-height:50px;">
				<td style="width:11%;font-size: 13px;text-align:center">资源编号</td><td style="width:22%;font-size: 13px;text-align:center">&nbsp;${Td21_mbk.zybh}</td>
				<td style="width:11%;font-size: 13px;text-align:center">资源名称</td><td style="width:55%;font-size: 13px;text-align:center" colspan="3">&nbsp;${Td21_mbk.zymc}</td>
			</tr>
			<tr style="height:50px;line-height:50px;">
				<td style="width:11%;font-size: 13px;text-align:center">建设性质</td><td style="width:22%;font-size: 13px;text-align:center">&nbsp;${Td21_mbk.jsxz}</td>
				<td style="width:11%;font-size: 13px;text-align:center">分 类</td><td style="width:22%;font-size: 13px;text-align:center">&nbsp;${Td21_mbk.lb}</td>
				<td style="width:11%;font-size: 13px;text-align:center">所属地区</td><td style="width:22%;font-size: 13px;text-align:center">&nbsp;${Td21_mbk.ssdq}</td>
			</tr>
			<tr style="height:50px;line-height:50px;">
				<td style="font-size: 13px;text-align:center">坐落地点</td><td style="font-size: 13px;text-align:center">&nbsp;${Td21_mbk.zldd}</td>
				<td style="font-size: 13px;text-align:center">经 度</td><td style="font-size: 13px;text-align:center">&nbsp;${Td21_mbk.jd}</td>
				<td style="font-size: 13px;text-align:center">纬 度</td><td style="font-size: 13px;text-align:center">&nbsp;${Td21_mbk.wd}</td>
			</tr>
			<tr style="height:50px;line-height:50px;"><td colspan="6">&nbsp;</td></tr>
			<tr style="height:50px;line-height:50px;">
				<td style="font-size: 13px;text-align:center">谈点部门</td><td style="font-size: 13px;text-align:center">&nbsp;${Td21_mbk.tdbm}</td>
				<td style="font-size: 13px;text-align:center">谈点人</td><td style="font-size: 13px;text-align:center">&nbsp;${Td21_mbk.tdr}</td>
				<td style="font-size: 13px;text-align:center">谈点人电话</td><td style="font-size: 13px;text-align:center">&nbsp;${Td21_mbk.tdrdh}</td>
			</tr>
			<tr style="height:50px;line-height:50px;">
				<td style="font-size: 13px;text-align:center">谈点周期</td><td style="font-size: 13px;text-align:center">&nbsp;${Td21_mbk.tdzq}</td>
				<td style="font-size: 13px;text-align:center">联系人</td><td style="font-size: 13px;text-align:center">&nbsp;${Td21_mbk.lxr}</td>
				<td style="font-size: 13px;text-align:center">联系电话</td><td style="font-size: 13px;text-align:center">&nbsp;${Td21_mbk.lxrdh}</td>
			</tr>
			<tr style="height:50px;line-height:50px;">
				<td style="font-size: 13px;text-align:center">建设方式</td><td style="font-size: 13px;text-align:center">&nbsp;${Td21_mbk.jsfs}</td>
				<td style="font-size: 13px;text-align:center">是否共建</td><td style="font-size: 13px;text-align:center">&nbsp;${Td21_mbk.sfgj}</td>
				<td style="font-size: 13px;text-align:center">谈点协调费</td><td style="font-size: 13px;text-align:center">&nbsp;${Td21_mbk.tdxtf}</td>
			</tr>
			<tr style="height:50px;line-height:50px;">
				<td style="font-size: 13px;text-align:center">签约时间</td><td style="font-size: 13px;text-align:center">&nbsp;<fmt:formatDate pattern="yyyy-MM-dd" value="${Td21_mbk.qysj}"/></td>
				<td style="font-size: 13px;text-align:center">租 金</td><td style="font-size: 13px;text-align:center">&nbsp;${Td21_mbk.zj}</td>
				<td style="font-size: 13px;text-align:center">年 限</td><td style="font-size: 13px;text-align:center">&nbsp;${Td21_mbk.nx}</td>
			</tr>
			<tr style="height:50px;line-height:50px;">
				<td style="font-size: 13px;text-align:center">合同编号</td><td style="font-size: 13px;text-align:center">&nbsp;${Td21_mbk.htbh}</td>
				<td style="font-size: 13px;text-align:center">业主姓名</td><td style="font-size: 13px;text-align:center">&nbsp;${Td21_mbk.yzmc}</td>
				<td style="font-size: 13px;text-align:center">业主电话</td><td style="font-size: 13px;text-align:center">&nbsp;${Td21_mbk.yzdh}</td>
			</tr>
			<tr style="height:50px;line-height:50px;">
				<td style="font-size: 13px;text-align:center">是否付首款</td><td style="font-size: 13px;text-align:center">&nbsp;${Td21_mbk.sfsfk}</td>
				<td style="font-size: 13px;text-align:center">建设等级</td><td style="font-size: 13px;text-align:center">&nbsp;${Td21_mbk.jsdj}</td>
				<td style="font-size: 13px;text-align:center">资源状态</td><td style="font-size: 13px;text-align:center">&nbsp;${Td21_mbk.zt}</td>
			</tr>
			<tr style="height:100px;">
				<td style="font-size: 13px;text-align:center">说 明</td><td colspan="5" style="line-height:18px;">&nbsp;${Td21_mbk.bz}</td>
			</tr>
			<tr style="height:50px;line-height:50px;"><td colspan="6">&nbsp;</td></tr>
			<tr style="height:50px;line-height:50px;">
				<td style="font-size: 13px;text-align:center">施工单位</td><td style="font-size: 13px;text-align:center">&nbsp;${Td21_mbk.sgdw}</td>
				<td style="font-size: 13px;text-align:center">施工管理员</td><td style="font-size: 13px;text-align:center">&nbsp;${Td21_mbk.sggly}</td>
				<td style="font-size: 13px;text-align:center">提交时间</td><td style="font-size: 13px;text-align:center">&nbsp;<fmt:formatDate pattern="yyyy-MM-dd" value="${Td21_mbk.tjsj}"/></td>
			</tr>
			<tr style="height:50px;line-height:50px;">
				<td style="font-size: 13px;text-align:center">开工时间</td><td style="font-size: 13px;text-align:center">&nbsp;<fmt:formatDate pattern="yyyy-MM-dd" value="${Td21_mbk.kgsj}"/></td>
				<td style="font-size: 13px;text-align:center">完工时间</td><td style="font-size: 13px;text-align:center">&nbsp;<fmt:formatDate pattern="yyyy-MM-dd" value="${Td21_mbk.wgsj}"/></td>
				<td style="font-size: 13px;text-align:center">施工管理员</td><td style="font-size: 13px;text-align:center">&nbsp;${Td21_mbk.gcfpsm}</td>
				<tr style="height:50px;line-height:50px;">
				<td style="height:120px;font-size: 13px;text-align:center">存在问题</td><td colspan="5" style="line-height:18px;">&nbsp;${Td21_mbk.czwt}</td>
			</tr>
		</table>
		</div>
<div class="page">

	<!-- 表单头 -->
	<div class="pageHeader">
		<div class="searchBar">
			<!-- 表单名称 -->
			<h1>资源信息单</h1>
			
		</div>
	</div>
	
	<!-- 主操作按钮 -->
	<div class="panelBar">
		<ul class="toolBar">
		 	<c:if test="${not empty rolesMap['20101']}">
		 	<li><a class="save"	href="javascript:saveMbk();"><span>保 存</span></a></li>
			<li class="line">line</li>
		 	</c:if>
			<li><a class="print" href="javascript:printMbk()"><span>打 印</span></a></li>
			<li class="line">line</li>
			<c:if test="${not empty Td21_mbk.id }">
			 
			<li><a class="attach" 
			href="javascript:docSlave('slave.do?project_id=${project_id }&amp;doc_id=${project_id }&amp;module_id=90&amp;node_id=-1&amp;opernode_id=-1&amp;user_id=${user.id }&slave_type=5');">
			<span>附 件</span></a></li>
			<li class="line">line</li> 
			
			<c:if test="${not empty rolesMap['20101'] && empty Td21_mbk.hdfs }">
			<li><a class="send" href="#"><span flag="zdrl" class="lzspan">派发</span></a></li>
			<li class="line">line</li>
			</c:if>
			<c:if test="${not empty rolesMap['20102'] && Td21_mbk.hdfs == '派发' && Td21_mbk.zt == '新建'}">
			<li><a class="icon" href="#"><span flag="rl" class="lzspan">认 领</span></a></li>
			<li class="line">line</li>
			</c:if>
			<c:if test="${not empty rolesMap['20101'] && empty Td21_mbk.hdfs }">
			<li><a class="send" href="#"><span flag="zdxf" class="lzspan">指定下发</span></a></li>
			<li class="line">line</li>
			</c:if>
			<c:if test="${not empty rolesMap['20101'] && Td21_mbk.zt != '转建设' && Td21_mbk.zt != '建设中' && not empty Td21_mbk.hdfs}">
			<li><a class="icon" href="#"><span flag="cxtd" class="lzspan">重新谈点</span></a></li>
			<li class="line">line</li>
			</c:if>
			<c:if test="${not empty rolesMap['20101'] && param.listType == 'fkcq'}">
			<li><a class="icon" href="#"><span flag="jxtd" class="lzspan">继续谈点</span></a></li>
			<li class="line">line</li>
			</c:if>
			<c:if test="${not empty rolesMap['20101'] && param.listType == 'tdcq'}">
			<li><a class="icon" href="#"><span flag="ycqx" class="lzspan">延期谈点</span></a></li>
			<li class="line">line</li>
			</c:if>
			<c:if test="${(not empty rolesMap['20102']) && Td21_mbk.zt == '开始谈点'}">
			<li><a class="icon" href="#"><span flag="ht" class="lzspan">回 退</span></a></li>
			<li class="line">line</li>
			</c:if>
			<c:if test="${(not empty rolesMap['20101'] || not empty rolesMap['20102']) && Td21_mbk.zt == '开始谈点'}">
			<li><a class="icon" href="#"><span flag="dcxy" class="lzspan">达成协议</span></a></li>
			<li class="line">line</li>
			</c:if>
			<c:if test="${not empty rolesMap['20101'] && (Td21_mbk.zt == '达成协议' || Td21_mbk.zt == '勘察申请')}">
				<c:choose>
					<c:when test="${Td21_mbk.jsxz == '室分'}">
					<li><a class="icon" href="#"><span flag="zjs" class="lzspan">转建设</span></a></li>
					<li class="line">line</li>
					</c:when>
					<c:otherwise>
						<li><a class="survey" href="#"><span flag="sfkc" class="lzspan">四方勘察</span></a></li>
						<li class="line">line</li>
					</c:otherwise>
				</c:choose>
			</c:if>
			<c:if test="${param.listType == 'kcsq'}">
			<li><a class="icon" href="#"><span flag="kcsq" class="lzspan">勘察申请</span></a></li>
			<li class="line">line</li>
			</c:if>
			<c:if test="${param.listType == 'fahssq'}">
			<li><a class="icon" href="#"><span flag="fahssq" class="lzspan">会审申请</span></a></li>
			<li class="line">line</li>
			</c:if>
			<c:if test="${not empty rolesMap['20101'] && Td21_mbk.zt == '四方勘察'}">
			<li><a class="unsurvey" href="#"><span flag="kcjs" class="lzspan">勘察结束</span></a></li>
			<li class="line">line</li>
			</c:if>
			<c:if test="${not empty rolesMap['20101'] && (Td21_mbk.zt == '勘察结束' || Td21_mbk.zt == '会审申请')}">
			<li><a class="icon" href="#"><span flag="fahs" class="lzspan">方案会审</span></a></li>
			<li class="line">line</li>
			</c:if>
			<c:if test="${not empty rolesMap['20101'] && Td21_mbk.zt == '方案会审'}">
			<li><a class="icon" href="#"><span flag="hswc" class="lzspan">会审完成</span></a></li>
			<li class="line">line</li>
			</c:if>
			<c:if test="${not empty rolesMap['20101'] && Td21_mbk.zt == '会审完成'}">
			<li><a class="icon" href="#"><span flag="zjs" class="lzspan">转建设</span></a></li>
			<li class="line">line</li>
			</c:if>
			<c:if test="${not empty rolesMap['20105'] && (Td21_mbk.zt == '转建设' || Td21_mbk.zt == '建设中') && haveGlgc == 'no'}">
			<li><a class="add" href="#"><span flag="jsz" class="lzspan">新建工程</span></a>
			</li>
			<li class="line">line</li>
			</c:if>
			<li><a class="add" href="jlgt/jlgtView.do?module_id=90&doc_id=${project_id}" target="navTab" rel="jlgtView"><span flag="jlfk" class="edit">交流反馈</span></a>
			</li>
			<li class="line">line</li>
			</c:if>
		</ul>
	</div>
	
	
	<div class="pageContent" layouth="48">
		<form id="mbk_form" action="save.do" method="post"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="tableInfomation"	value="noFatherTable:com.rms.dataObjects.mbk.Td21_mbk" />
			<input type="hidden" name="Td21_mbk.ID" value="${Td21_mbk.id}" />
			<input type="hidden" name="_callbackType" value="forward"/>
			<input type="hidden" name="perproty" value="id,Td21_mbk,id">
			<input type="hidden" name="_message" value="保存" />
			<input type="hidden" name="_forwardUrl" value="mbk/mbkEdit.do"/>
			<input type="hidden" name="_navTabId" value="mbkList"/> 
			<input type="hidden" name="kcsj" id="tdrOrg.kcsj" value=""/>
			<input type="hidden" name="sm"  id="tdrOrg.sm" value=""/>
			<input type="hidden" name="Td21_mbk.CJR" value="<c:out value="${Td21_mbk.cjr }" default="${user.name }"/>"/>
			<input type="hidden" name="Td21_mbk.CJRDH" value="<c:out value="${Td21_mbk.cjrdh }" default="${user.mobile_tel }"/>"/>
			<c:if test="${empty Td21_mbk.cjsj}">
				<input type="hidden" name="Td21_mbk.CJSJ" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>"/>
			</c:if>
			<c:if test="${Td21_mbk.zt=='勘察申请' && not empty rolesMap['20101']}">
				<input type="hidden" id="show_sqkcsm" value="${Td21_mbk.sqkcsm }"/>
			</c:if>
			<c:if test="${Td21_mbk.zt=='会审申请' && not empty rolesMap['20101']}">
				<input type="hidden" id="show_sqfahs" value="提醒"/>
			</c:if>
			<div class="pageFormContent">
				<p>
					<label>资源编号：</label>
					<input readonly="readonly" type="text" name="Td21_mbk.ZYBH" style="width:150px;" value="${Td21_mbk.zybh}"/>
				</p>
				<p>
					<label>资源名称：</label>
					<input class="required" type="text" name="Td21_mbk.ZYMC" style="width:376px;" value="${Td21_mbk.zymc}" />
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>建设性质：</label>
					<netsky:htmlSelect htmlClass="required" id="jsxz" name="Td21_mbk.JSXZ" style="width:156px;" objectForOption="jsxzList" valueForOption="" showForOption="" value="${Td21_mbk.jsxz}" extend="" extendPrefix="true" />
				</p>
				<p>
					<label> 分    类：</label>
					<netsky:htmlSelect htmlClass="required" name="Td21_mbk.LB" style="width:156px;" objectForOption="lbList" valueForOption="" showForOption="" value="${Td21_mbk.lb}" extend="" extendPrefix="true" />
				</p>
				<p>
					<label>建设方式：</label> 
					<netsky:htmlSelect htmlClass="required" name="Td21_mbk.JSFS" style="width:126px;" objectForOption="jsfsList" valueForOption="name" showForOption="name" value="${Td21_mbk.jsfs}" extend="" extendPrefix="true" />
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>所属地区：</label>
					<netsky:htmlSelect htmlClass="required" name="Td21_mbk.SSDQ" style="width:156px;" objectForOption="dqList" valueForOption="" showForOption="" value="${Td21_mbk.ssdq}" extend="" extendPrefix="true" />
				</p>
				<p>
					<label>坐落地点：</label>
					<input type="text" class="required" name="Td21_mbk.ZLDD" style="width:376px;" value="${Td21_mbk.zldd}"/>
				</p> 
				<div id="jz">
					<div style="height:0px;"></div>
					<p>
						<label>经    度：</label>
						<input type="text" ids="jz" name="Td21_mbk.JD" style="width:150px;" value="${Td21_mbk.jd}"/>
					</p>
					<p>
						<label>纬    度：</label>
						<input type="text" ids="jz" name="Td21_mbk.WD" style="width:150px;" value="${Td21_mbk.wd}"/>
					</p>
					<p>
					<label>覆盖属性：</label> 
					<netsky:htmlSelect name="Td21_mbk.FGSX" style="width:126px;" objectForOption="fgsxList" valueForOption="name" showForOption="name" value="${Td21_mbk.fgsx}" extend="" extendPrefix="true" />
				</p>
				</div>
				<div id="xq">
					<div style="height:0px;"></div>
					<p>
						<label>幢    数：</label>
						<input type="text" ids="xq" name="Td21_mbk.ZS" style="width:150px;" value="${Td21_mbk.zs}"/>
					</p> 
					<p>
						<label>层    数：</label>
						<input type="text" ids="xq" name="Td21_mbk.CS" style="width:150px;" value="${Td21_mbk.cs}"/>
					</p>
					<p>
						<label>户    数：</label>
						<input type="text" ids="xq" name="Td21_mbk.HS" style="width:120px;" value="${Td21_mbk.hs}"/>
					</p>
				</div>
				<div class="divider"></div>
				<p>
					<label>谈点部门：</label>
					<input type="text" readonly="readonly" id="tdrOrg.TDBM" name="Td21_mbk.TDBM" style="width:150px;" value="${Td21_mbk.tdbm}"/>
					
				</p>
				<p>
					<label>谈点人：</label>
					<input type="text" name="Td21_mbk.TDR" id="tdrOrg.TDR" style="width:125px" readonly="readonly" value="${Td21_mbk.tdr }" />
					<a class="btnLook" href="mbk/getTdr.do" lookupGroup="tdrOrg" width="600" height="380">查找带回</a>
					<input type="hidden" name="Td21_mbk.TDR_ID" id="tdrOrg.TDR_ID" value="${Td21_mbk.tdr_id}"/>
					<a style="display:none" id="sfkca" class="btnLook" href="mbk/getKcry.do" lookupGroup="tdrOrg" width="600" height="430">查找带回</a>
					<input type="hidden" id="tdrOrg.Kcry"/>
					<a style="display:none" id="fahsa" class="btnLook" href="mbk/getHsry.do" lookupGroup="tdrOrg" width="600" height="380">查找带回</a>
					<input type="hidden" id="tdrOrg.Hsry"/>
					<a style="display:none" id="zjsa" class="btnLook" href="mbk/getXmgly.do" lookupGroup="tdrOrg" width="600" height="380">查找带回</a>
					<input type="hidden" id="tdrOrg.Xmgly"/>
				</p>
				<p>
					<label>谈点人电话：</label>
					<input type="text" id="tdrOrg.TDRDH" name="Td21_mbk.TDRDH" style="width:120px;" value="${Td21_mbk.tdrdh}"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label width="90">反馈周期：</label>
					<input class="digits" type="text" name="Td21_mbk.FKZQ" style="width:150px;" value="<c:out value="${Td21_mbk.fkzq}" default="5"/>"/>
				</p>
				<p>
					<label width="90">谈点周期：</label>
					<input class="digits" type="text" name="Td21_mbk.TDZQ" style="width:150px;" value="<c:out value="${Td21_mbk.tdzq}" default="30"/>"/>
				</p>
				<p>
					<label>是否共建：</label>
					<input type="radio" name="Td21_mbk.SFGJ" <c:if test="${Td21_mbk.sfgj == '是' || empty Td21_mbk.sfgj }">checked="checked"</c:if> value="是" />
					是
					<input type="radio" name="Td21_mbk.SFGJ" <c:if test="${Td21_mbk.sfgj == '否' }">checked="checked"</c:if> value="否"/>
					<span style="margin-right:74px;">否</span>
				</p>
				
				<div style="height:0px;"></div>
				<p>
					<label>对方联系人：</label> 
					<input type="text" name="Td21_mbk.LXR" style="width:150px;" value="${Td21_mbk.lxr}"/>
				</p>
				<p>
					<label>联系电话：</label>
					<input type="text" name="Td21_mbk.LXRDH" style="width:150px;" value="${Td21_mbk.lxrdh}"/>
				</p>
				
				<p>
					<label>谈点协调费：</label>
					<input type="text" name="Td21_mbk.TDXTF" style="width:120px;" value="${Td21_mbk.tdxtf}"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>签约时间：</label> 
					<input type="text" name="Td21_mbk.QYSJ" style="width:150px;" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${Td21_mbk.qysj}"/>" class="date" pattern="yyyy-MM-dd"/>
				</p>
				<p>
					<label>租    金：</label>
					<input class="digits" type="text" name="Td21_mbk.ZJ" style="width:150px;" value="${Td21_mbk.zj}"/>
				</p>
				<p>
					<label>年    限：</label>
					<input class="number" type="text" name="Td21_mbk.NX" style="width:120px;" value="${Td21_mbk.nx}"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>合同编号：</label> 
					<input type="text" name="Td21_mbk.HTBH" style="width:150px;" value="${Td21_mbk.htbh}"/>
				</p>
				<p>
					<label>业主姓名：</label>
					<input type="text" name="Td21_mbk.YZMC" style="width:150px;" value="${Td21_mbk.yzmc}"/>
				</p>
				<p>
					<label>业主电话：</label>
					<input type="text" name="Td21_mbk.YZDH" style="width:120px;" value="${Td21_mbk.yzdh}"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>是否付首款：</label> 
					<input type="radio" name="Td21_mbk.SFSFK" <c:if test="${Td21_mbk.sfsfk == '是' || empty Td21_mbk.sfsfk }">checked="checked"</c:if> value="是" />
					是
					<input type="radio" name="Td21_mbk.SFSFK" <c:if test="${Td21_mbk.sfsfk == '否' }">checked="checked"</c:if> value="否"/>
					<span style="margin-right:74px;">否</span>
				</p>
				<p>
					<label>建设等级：</label>
					<input type="radio" name="Td21_mbk.JSDJ" <c:if test="${Td21_mbk.jsdj == 'A' || empty Td21_mbk.jsdj }">checked="checked"</c:if> value="A" />
					A
					<input type="radio" name="Td21_mbk.JSDJ" <c:if test="${Td21_mbk.jsdj == 'B' }">checked="checked"</c:if> value="B" />
					B
					<input type="radio" name="Td21_mbk.JSDJ" <c:if test="${Td21_mbk.jsdj == 'C' }">checked="checked"</c:if> value="C"/>
					<span style="margin-right:54px;">C</span>
				</p>
				<p>
					<label>资源状态：</label>
					<input type="text" name="Td21_mbk.ZT" readonly style="width:120px;" value="${empty Td21_mbk.zt ? '新建' : Td21_mbk.zt}"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>谈点说明：</label>
					<textarea class="td-textarea" style="width:630px;height:40px;" type="text" name="Td21_mbk.BZ">${Td21_mbk.bz}</textarea>
				</p>
				<div class="divider"></div>
				<p>
					<label>施工单位：</label> 
					<input type="text" name="Td21_mbk.SGDW" style="width:150px;" value="${Td21_mbk.sgdw}"/>
				</p>
				<p>
					<label>施工管理员：</label>
					<input type="text" name="Td21_mbk.SGGLY" style="width:150px;" value="${Td21_mbk.sggly}"/>
				</p>
				<p>
					<label>提交时间：</label>
					<input type="text" name="Td21_mbk.TJSJ" style="width:120px;" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${Td21_mbk.tjsj}"/>" class="date" pattern="yyyy-MM-dd"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>开工时间：</label> 
					<input type="text" name="Td21_mbk.KGSJ" style="width:150px;" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${Td21_mbk.kgsj}"/>" class="date" pattern="yyyy-MM-dd"/>
				</p>
				<p>
					<label>完工时间：</label>
					<input type="text" name="Td21_mbk.WGSJ" style="width:150px;" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${Td21_mbk.wgsj}"/>" class="date" pattern="yyyy-MM-dd"/>
				</p>
				<p>
					<label>工程分配说明：</label>
					<input type="text" name="Td21_mbk.GCFPSM" style="width:120px;" value="${Td21_mbk.gcfpsm}"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>存在问题：</label>
					<textarea class="td-textarea" style="width:630px;height:40px;" type="text" name="Td21_mbk.CZWT">${Td21_mbk.czwt}</textarea>
				</p>
		</div>
		</form>
		
	<div style="text-align:left;color:blue;"><h3>&nbsp;&nbsp;目标库流转记录</h3></div><div class="divider" style="height:1px;"></div>
		<table class="table" width="100%">
		<thead>
			<tr>
				<th style="width: 30px;">序号</th>
				<th style="width: 130px;">开始时间</th>
				<th style="width: 130px;">结束时间</th>
				<th style="width: 80px;">相关人</th>
				<th style="width: 80px;">相关人部门</th>
				<th>说明</th>
			</tr>
		</thead>
		<tbody>
		<c:set var="offset" value="0"/>
			<c:forEach items="${lzjlList}" var="lzjl">
			<c:set var="offset" value="${offset+1}"/>
				<tr>
					<td>${offset }</td>
					<td><fmt:formatDate value="${lzjl.kssj }" pattern="yyyy-MM-dd HH:mm"/></td>
					<td><fmt:formatDate value="${lzjl.jssj }" pattern="yyyy-MM-dd HH:mm"/></td>
					<td>${lzjl.xgr }</td>
					<td>${lzjl.xgr_bm }</td>
					<td>${lzjl.sm }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
		
		
	</div>		
		
		<!---调整---->
		<div id="attachBody" layoutH="68">
			<div class="panel">
				<h1>表单附件 [${fn:length(formslave)+fn:length(extslave)+fn:length(uploadslave)}]</h1>
				<div id="slaveDiv" defH="150" style="background-color:#fff;">
					<c:set var="slaves" scope="page" value="0"/>
					
					<!-- temp -->
					<p class="slaveList"><a href="javascript:return false;" onclick="javascript:navTab.openTab('gcsgjd', 'wxdw/gcsgjd.do?id=5026', {title:'工程施工进度'});">相关工程施工进度</a></p>
					<c:set var="slaves" scope="page" value="1"/>
					<!-- temp -->
					
					<c:forEach var="obj" items="${_formslink}">
						<p class="slaveList"><a href="${obj.formurl}">${obj.slave_name}</a></p>
						<c:set var="slaves" scope="page" value="${slaves+1 }"/>
					</c:forEach>
					<c:forEach var="obj" items="${formslave}">
						<p class="slaveList"><a href="${obj.formurl}">${obj.slave_name}</a></p>
						<c:set var="slaves" scope="page" value="${slaves+1 }"/>
					</c:forEach>
					
					<c:forEach var="obj" items="${extslave}">
						<p class="slaveList"><a href="javascript:return false;" onclick="${obj.formurl}">${obj.slave_name}</a></p>
						<c:set var="slaves" scope="page" value="${slaves+1 }"/>
					</c:forEach>
					
					<c:forEach var="obj" items="${uploadslave}">
						<p class="slaveList">
							${obj.slave_name}&nbsp;&nbsp;
							<a href="show_slave.do?slave_id=${obj.slave_id}" target="dialog" width="1000" height="600" title="查看"><font color=blue>查看</font></a>
							<a href="download.do?slave_id=${obj.slave_id}" title="下载"><font color="red">下载</font></a>
							<c:if test="${obj.rw == 'w'}"><a href="javascript:del_slave('${obj.slave_id}','${slaves }');"><img src="Images/icon10.gif" alt="删除"/></a></c:if>
						</p>
						<c:set var="slaves" scope="page" value="${slaves+1 }"/>
					</c:forEach>
				</div>
			</div>
			<!-- 
			<div id="jlfkTitle" class="panel" style="margin-top:10px;">
				<h1>交流反馈 [${fn:length(jlfk)}]</h1>
				<div id="jlfkdiv" defH="150" style="background-color:#fff;">
					<c:forEach var="jlfk" items="${jlfk}">
						<p class="jlfkList">
							<b>${jlfk.name}</b> [<fmt:formatDate value="${jlfk.time}" pattern="yyyy-MM-dd HH:mm"/>] <c:if test="${jlfk.slave_id != null}"><a href="download.do?slave_id=${jlfk.slave_id}"><img src="Images/slave.gif" alt="下载附件"/></a></c:if><br/>
							${jlfk.yj}&nbsp;<c:if test="${jlfk.rw == 'w'}"><a href="javascript:del_send('${jlfk.project_id}','${param.project_id}','${jlfk.slave_id}','${param.module_id }','${param.user_id }','${param.doc_id }');"><img src="Images/icon10.gif" alt="删除"/></a></c:if>
						</p>
					</c:forEach>
				</div>
			</div>	
			-->		
		</div>	
		<div  style="clear:both"></div> 
		
	
</div>
<script language="javascript">
	//通过建设性质来区分显示哪些字段
	var jsxz = $("#jsxz").val();
	if(jsxz == '' || jsxz == null || jsxz == '室分'){
		$("#jz").css("display","none");
		$("#xq").css("display","none");
		$("input[ids=jz]").attr("class","norequired");
		$("input[ids=xq]").attr("class","norequired");
	}
	else if(jsxz == 'undefine' || jsxz == '基站'){
		$("#jz").css("display","block");
		$("#xq").css("display","none");
		$("input[ids=jz]").attr("class","required");
		$("input[ids=xq]").attr("class","norequired");
	}
	else{
		$("#jz").css("display","none");
		$("#xq").css("display","block");
		$("input[ids=jz]").attr("class","norequired");
		$("input[ids=xq]").attr("class","required digits");
	}
	
	//按照屏幕分配表单右侧信息
	var max_w = $("#autoform_body",navTab.getCurrentPanel()).width();
	var attach_w = navTab._panelBox.width() - 810;
	var attach_h = navTab._panelBox.height() - 63;
	if(max_w <1080){
		//默认展开表单
		var bar = $("#sidebar");
		if(bar.is(":hidden") == false)	$(".toggleCollapse div", bar).click();
		attach_w = attach_w+175;
	}
	$("#attachBody",navTab.getCurrentPanel()).css({"width":attach_w+"px", "position":"relative"});
	$("#attachBody",navTab.getCurrentPanel()).css("margin","-" + attach_h + " 30 30 770");	
	
	$(".panel div",navTab.getCurrentPanel()).height(parseInt((attach_h-150)*0.5));
	$(".panel div",navTab.getCurrentPanel()).first().height(parseInt((attach_h-150)*0.5));
	
	
	//修改表单的tabid
	var  module_id = $("#module_id",navTab.getCurrentPanel()).val();
	var  doc_id = $("#doc_id",navTab.getCurrentPanel()).val();
	
	if(doc_id != ""){
		var tabid = navTab._getTabs().eq(navTab._currentIndex).attr("tabid");
		if(tabid == "autoform"){
			navTab._getTabs().eq(navTab._currentIndex).attr("tabid","autoform"+module_id+doc_id);
		}
	}
	
	//如果有勘察申请，则提示勘察申请的内容
	var show_sqkcsm = $("#show_sqkcsm");
	if(show_sqkcsm != null && show_sqkcsm != 'undefined' && show_sqkcsm.size() == 1 && show_sqkcsm.val() != ''){
		var show_content = "<b>谈点人员已经提出勘察申请，内容如下：</b><br/>"+show_sqkcsm.val()+"<br/><b>是否现在发起四方勘察?</b>";
		alertMsg.confirm(show_content, {			
				okCall: function(){
					$("#sfkca").click();
				}
			});
	}
	
	//如果有方案会审申请，则提示会审申请的内容
	var show_sqfahs = $("#show_sqfahs");
	if(show_sqfahs != null && show_sqfahs != 'undefined' && show_sqfahs.size() == 1 && show_sqfahs.val() != ''){
		var show_content = "<b>设计人员已经提出会审申请，<br/>是否现在发起方案会审?</b>";
		alertMsg.confirm(show_content, {			
				okCall: function(){
					$("#fahsa").click();
				}
			});
	}
</script>

