<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />
<style>
span b{
	padding:10px;
	padding-left:20px;
	padding-right:50px; 
	font-size: 30px;
	color: red;  
} 
</style>
<script language="javascript">
var change = false;
$(function(){
   		$("#jsxz",navTab.getCurrentPanel()).cascade({
			childSelect:$("#jsfs",navTab.getCurrentPanel()),
			tableName:'Tc12_jsfs',
			conditionColumn:'jsxz_id',
			valueForOption:'mc',
			key:'id',
			orderBy:'id',
			showForOption:{
				pattern:'[mc]',
				mc:'mc'
			}
		});	
		$sqkcsj=$("input[name='Td23_kcsqb\.SQSBSJ']",navTab.getCurrentPanel());
		$b=$("span b");
		if($sqkcsj.val()!=''){
			$inputs=$(":input[name^='Td23_kcsqb']");
			$inputs.attr("readonly","readonly");
			$b.attr("onclick","");
		}
		
   	});
$("#mbk_form :input",navTab.getCurrentPanel()).change(function(){
		change = true;
	});
function saveMbk(){ 
	$("#mbk_form",navTab.getCurrentPanel()).submit(); 
}
function setCommunicate(_this,condition){
	$p=$(_this).closest("p");
	$input=$("input[name='Td23_kcsqb\."+condition+"']",$p);
	var span_text= $(_this).text();  
	if(span_text=='√'){
		$(_this).html('×');
		$(_this).css({color:"red"});
		$input.val('0');
	}else if(span_text=='×'||span_text==''){
		$(_this).html('√'); 
		$(_this).css({color:"green"});
		$input.val('1');
	} 
}
 function reportKcsq(obj,kcsq_id){
 		$.ajax({
		type:"post",
		url:"mbk/reportKcsq.do",
		dataType:"json",
		async:true,
		data:{mbk_id:obj,kcsqb_id:kcsq_id},
		success:function(json){ 
			if(json.statusCode == DWZ.statusCode.ok){
					alertMsg.correct(json.message);
					navTab.closeCurrentTab();
				}else if(json.statusCode == DWZ.statusCode.error){
					alertMsg.info(json.message);
				}
		}
		
	});
 }
</script>
<div class="page">
	<!-- 表单头 -->
	<div class="pageHeader">
		<div class="searchBar">
			<!-- 表单名称 -->
			<h1>
				勘察申请单
			</h1>

		</div>
	</div>
	<!-- 主操作按钮 -->
	<div class="panelBar">
		<ul class="toolBar">
			<c:if
				test="${not empty rolesMap['20101'] || not empty rolesMap['20102']}">
				<c:if test="${empty Td23_kcsqb.sqsbsj}">
				<li>
					<a class="save" href="javascript:saveMbk();"><span>保&nbsp;&nbsp;存</span>
					</a>
				</li>
				<li class="line">
					line
				</li>
					</c:if>
			</c:if>
			<c:if
				test="${not empty rolesMap['20101'] || not empty rolesMap['20102']}">
				<li>
					<a class="icon"
						href="javascript:reportKcsq('${Td21_mbk.id}','${Td23_kcsqb.id}');"><span>上&nbsp;&nbsp;报</span>
					</a>
				</li>
				<li class="line">
					line
				</li>
			</c:if>
		</ul>
	</div>

	<div class="pageContent" layouth="48">
		<form id="mbk_form" action="save.do" method="post"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="tableInfomation"
				value="noFatherTable:com.rms.dataObjects.mbk.Td23_kcsqb" />
			<input type="hidden" name="Td23_kcsqb.ID" value="${Td23_kcsqb.id}" />
			<input type="hidden" name="Td23_kcsqb.MBK_ID" value="${Td21_mbk.id}" />
			<input type="hidden" name="Td23_kcsqb.CJR" value="${user.name}" /> 
			<input type="hidden" name="Td23_kcsqb.SQSBSJ" value="${Td23_kcsqb.sqsbsj}"/>
			<input type="hidden" name="_callbackType" value="" />
			<input type="hidden" name="_message" value="保存" />
			<input type="hidden" name="_forwardUrl" value="" />
			<input type="hidden" name="_navTabId" value="kcsqList" />


			<div class="pageFormContent">
				<p>
					<label>
						目标库编号：
					</label>
					<input readonly="readonly" type="text" name="Td21_mbk.ZYBH"
						style="width: 256px;" value="${Td21_mbk.zybh}" />
				</p>
				<div style="height: 0px;"></div>
				<p>
					<label>
						资源名称&nbsp;&nbsp;&nbsp;&nbsp;：
					</label>
					<input type="text" name="Td21_mbk.ZYMC" id="Td21_mbk.ZYMC"
						style="width: 256px;" value="${Td21_mbk.zymc}" readonly="readonly" />
				</p>
				<p>
					<label>
						区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;域：
					</label>
					<input type="text" name="Td21_mbk.SSDQ" id="Td21_mbk.SSDQ"
						style="width: 256px;" value="${Td21_mbk.ssdq}" readonly="readonly" />
				</p>
				<div style="height: 0px;"></div>
				<div id="jz">
					<div style="height: 0px;"></div>
					<p>
						<label>
							经&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度：
						</label>
						<input type="text" ids="jz" name="Td21_mbk.JD" id="Td21_mbk.JD"
							style="width: 256px;" value="${Td21_mbk.jd}" readonly="readonly" />
					</p>
					<p>
						<label>
							纬&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度：
						</label>
						<input type="text" ids="jz" name="Td21_mbk.WD" id="Td21_mbk.WD"
							style="width: 256px;" value="${Td21_mbk.wd}" readonly="readonly" />
					</p>
					<div style="height: 0px;"></div>
					<p>
						<label>
							地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：
						</label>
						<input type="text" name="Td21_mbk.ZLDD" id="Td21_mbk.ZLDD"
							style="width: 618px;" value="${Td21_mbk.zldd}"
							readonly="readonly" />
					</p>
				</div>
				<div class="divider"></div>
				<p>
					<label>
						谈&nbsp;&nbsp;&nbsp;&nbsp;点&nbsp;&nbsp;&nbsp;&nbsp;人：
					</label>
					<input type="text" name="Td21_mbk.TDR" id="tdrOrg.TDR"
						style="width: 256px" readonly="readonly" value="${Td21_mbk.tdr }"
						readonly="readonly" />
				</p>
				<p>
					<label>
						谈点人电话：
					</label>
					<input type="text" id="tdrOrg.TDRDH" name="Td21_mbk.TDRDH"
						style="width: 256px;" value="${Td21_mbk.tdrdh}"
						readonly="readonly" />
				</p>
				<div style="height: 0px;"></div>
				<p>
					<label>
						业主联系人：
					</label>
					<input type="text" name="Td21_mbk.YZMC" id="tdrOrg.YZMC"
						style="width: 256px" readonly="readonly" value="${Td21_mbk.yzmc}" />
				</p>
				<p>
					<label>
						业主电话&nbsp;&nbsp;&nbsp;&nbsp;：
					</label>
					<input type="text" id="tdrOrg.YZDH" name="Td21_mbk.YZDH"
						style="width: 256px;" value="${Td21_mbk.yzdh}" readonly="readonly" />
				</p>
				<div style="height: 0px;"></div>
				<p>
					<label>
						预约勘察时间：
					</label>
					<input type="text" format="yyyy-MM-dd HH:mm"
						name="Td23_kcsqb.YYKCSJ" style="width: 256px;"
						value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${Td23_kcsqb.yykcsj}"/>"
						class="date required" pattern="yyyy-MM-dd HH:mm" />
				</p>
				<div class="divider"></div>
				<div style="height: 0px;"></div>
				<div style="text-align: left; color: blue;">
					<h3>
						&nbsp;&nbsp;沟通情况（房子、天面、楼层）
					</h3>
				</div>
				<div class="divider" style="height: 1px;"></div>
				<p>
					<label>
						提供机房&nbsp;&nbsp;&nbsp;&nbsp;：
					</label>
					<span
						style="margin-left: 30px; margin-right: 35px; font-size: 20; cursor: hand;"
						title="是否同意提供机房"> <c:if
							test="${Td23_kcsqb.sftgjf==0||empty Td23_kcsqb.sftgjf}">
							<b onclick="setCommunicate(this,'SFTGJF')">×</b>
						</c:if> <c:if test="${Td23_kcsqb.sftgjf==1}">
							<b onclick="setCommunicate(this,'SFTGJF')" style='color: green'>√</b>
						</c:if> </span>
					<input type="hidden" name="Td23_kcsqb.SFTGJF" style="width: 120px;"
						value="${Td23_kcsqb.sftgjf}" />
				</p>
				<p>
					<label>
						房顶建房&nbsp;&nbsp;&nbsp;&nbsp;：
					</label>
					<span
						style="margin-left: 30px; margin-right: 35px; font-size: 20; cursor: hand;"
						title="是否同意在房顶建房"> <c:if
							test="${Td23_kcsqb.sftyld==0||empty Td23_kcsqb.sftyld}">
							<b onclick="setCommunicate(this,'SFTYLD')">×</b>
						</c:if> <c:if test="${Td23_kcsqb.sftyld==1}">
							<b onclick="setCommunicate(this,'SFTYLD')" style='color: green'>√</b>
						</c:if> </span>
					<input type="hidden" name="Td23_kcsqb.SFTYLD" style="width: 120px;"
						value="${Td23_kcsqb.sftyld}" />
				</p>
				<p>
					<label>
						美化天线&nbsp;&nbsp;&nbsp;&nbsp;：
					</label>
					<span
						style="font-size: 20; cursor: hand;"
						title="是否美化天线"> <c:if
							test="${Td23_kcsqb.sfmhtx==0||empty Td23_kcsqb.sfmhtx}">
							<b onclick="setCommunicate(this,'SFMHTX')">×</b>
						</c:if> <c:if test="${Td23_kcsqb.sfmhtx==1}">
							<b onclick="setCommunicate(this,'SFMHTX')" style='color: green'>√</b>
						</c:if> </span>
					<input type="hidden" name="Td23_kcsqb.SFMHTX" style="width: 120px;"
						value="${Td23_kcsqb.sfmhtx}" />
				</p>
				<div style="height: 0px;"></div>
				<p>
					<label>
						其&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;他：
					</label>
					<input type="text" id="tdrOrg.TDRDH" name="Td23_kcsqb.QTGT"
						style="width: 618px;" value="${Td23_kcsqb.qtgt}" />
				</p>
				<div class="divider"></div>
				<p>
					<label>
						备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：
					</label>
					<textarea class="td-textarea" style="width: 618px; height: 81px;"
						type="text" name="Td23_kcsqb.BZ">${Td23_kcsqb.bz}</textarea>
				</p>
				<div class="divider"></div>
			</div>
		</form>
		<div style="clear: both"></div>


	</div>