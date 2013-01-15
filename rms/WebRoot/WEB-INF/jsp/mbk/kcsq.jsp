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
   	});
$("#mbk_form :input",navTab.getCurrentPanel()).change(function(){
		change = true;
	});
function saveMbk(){ 
	$("#mbk_form",navTab.getCurrentPanel()).submit(); 
}
 
</script>
<div class="page"> 
	<!-- 主操作按钮 -->
	<div class="panelBar">
		<ul class="toolBar">
		 	<c:if test="${not empty rolesMap['20101'] || not empty rolesMap['20102']}">
		 	<li><a class="save"	href="javascript:saveMbk();"><span>保 存</span></a></li>
			<li class="line">line</li> 
			</c:if>
		 	<c:if test="${not empty rolesMap['20101'] || not empty rolesMap['20102']}">
		 	<li><a class="icon"	href="javascript:saveMbk();"><span>上报</span></a></li>
			<li class="line">line</li> 
			</c:if>
		</ul>
	</div>
	
	<div class="pageContent" layouth="48">
		<form id="mbk_form" action="save.do" method="post"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="tableInfomation"	value="noFatherTable:com.rms.dataObjects.mbk.Td23_kcsqb" />
			<input type="hidden" name="Td23_kcsqb.ID" value="${Td23_kcsqb.id}" />
			<input type="hidden" name="_callbackType" value="closeCurrent"/>
			<input type="hidden" name="_message" value="保存" />
			<input type="hidden" name="_forwardUrl" value=""/>
			<input type="hidden" name="_navTabId" value="kcsqList"/> 
			<div class="pageFormContent">
				<p>
					<label>目标库编号：</label>
					<input readonly="readonly" type="text" name="Td21_mbk.ZYBH" style="width:256px;" value="${Td21_mbk.zybh}"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>资源名称&nbsp;&nbsp;&nbsp;&nbsp;：</label>
					<input  type="text" name="Td21_mbk.ZYMC" id="Td21_mbk.ZYMC" style="width:256px;" value="${Td21_mbk.zymc}" readonly="readonly"/>
				</p>
				<p>
					<label>区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;域：</label>
					<input  type="text" name="Td21_mbk.SSDQ" id="Td21_mbk.SSDQ" style="width:256px;" value="${Td21_mbk.ssdq}" readonly="readonly"/>
				</p> 
				<div style="height:0px;"></div>
				<div id="jz">
					<div style="height:0px;"></div>
					<p>
						<label>经&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度：</label>
						<input type="text" ids="jz" name="Td21_mbk.JD" id="Td21_mbk.JD" style="width:256px;" value="${Td21_mbk.jd}" readonly="readonly"/>
					</p>
					<p>
						<label>纬&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度：</label>
						<input type="text" ids="jz" name="Td21_mbk.WD" id="Td21_mbk.WD" style="width:256px;" value="${Td21_mbk.wd}" readonly="readonly"/>
					</p>
				<div style="height:0px;"></div>
				<p>
					<label>地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：</label> 
					<input   type="text" name="Td21_mbk.ZLDD" id="Td21_mbk.ZLDD" style="width:618px;" value="${Td21_mbk.zldd}" readonly="readonly"/>
				</p>
				</div> 
				<div class="divider"></div> 
				<p>
					<label>谈&nbsp;&nbsp;&nbsp;&nbsp;点&nbsp;&nbsp;&nbsp;&nbsp;人：</label>
					<input type="text" name="Td21_mbk.TDR" id="tdrOrg.TDR" style="width:256px" readonly="readonly" value="${Td21_mbk.tdr }" readonly="readonly"/>
				</p>
				<p>
					<label>谈点人电话：</label>
					<input type="text" id="tdrOrg.TDRDH" name="Td21_mbk.TDRDH" style="width:256px;" value="${Td21_mbk.tdrdh}" readonly="readonly"/>
				</p>
				<div style="height:0px;"></div>
				 <p>
					<label>业主联系人：</label>
					<input type="text" name="Td21_mbk.YZMC" id="tdrOrg.YZMC" style="width:256px" readonly="readonly" value="${Td21_mbk.yzmc}" />
				</p>
				<p>
					<label>业主电话&nbsp;&nbsp;&nbsp;&nbsp;：</label>
					<input type="text" id="tdrOrg.YZDH" name="Td21_mbk.YZDH" style="width:256px;" value="${Td21_mbk.yzdh}" readonly="readonly"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label >预约勘察时间：</label> 
					<input type="text" name="Td23_KCSQB.YYKCSJ" style="width:256px;" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${Td23_kcsqb.yykcsj}"/>" class="date" pattern="yyyy-MM-dd hh:mm" title="和业主初步预约勘察时间"/>
				</p> 
				<div class="divider"></div>
				<div style="height:0px;"></div>
				<div style="text-align:left;color:blue;"><h3>&nbsp;&nbsp;沟通情况（房子、天面、楼层）</h3></div><div class="divider" style="height:1px;"></div>
				<p>
					<label>提供机房&nbsp;&nbsp;&nbsp;&nbsp;：</label>
					<input type="text" id="tdrOrg.TDRDH" name="Td23_KCSQB.SFTGJF" style="width:120px;" value="${Td23_kcsqb.sftgjf}"/>
				</p>
				<p>
					<label>房顶间房&nbsp;&nbsp;&nbsp;&nbsp;：</label>
					<input type="text" id="tdrOrg.TDRDH" name="Td23_KCSQB.SFTYLD" style="width:120px;" value="${Td23_kcsqb.sftyld}" title="同意在房顶建房"/>
				</p>
				<p>
					<label>美化天线&nbsp;&nbsp;&nbsp;&nbsp;：</label>
					<input type="text" id="tdrOrg.TDRDH" name="Td23_KCSQB.SFMHTX" style="width:120px;" value="${Td23_kcsqb.sfmhtx}"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>其&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;他：</label>
					<input type="text" id="tdrOrg.TDRDH" name="Td23_KCSQB.QTGT" style="width:618px;" value="${Td23_kcsqb.qtgt}"/>
				</p>
				<div class="divider"></div>
				<p>
					<label>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
					<textarea class="td-textarea" style="width:618px;height:40px;" type="text" name="Td23_KCSQB.BZ">${Td23_kcsqb.bz}</textarea>
				</p>
				<div class="divider"></div>
		</div>
		</form>
		<div  style="clear:both"></div> 
		
	
</div>
 
