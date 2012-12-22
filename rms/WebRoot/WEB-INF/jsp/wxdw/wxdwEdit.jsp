<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<script language="javascript">

//计算页面上tab允许高度
$(function(){
	var h = navTab._panelBox.height() - $("#wxdw_info").height() - 25;
	$("#wxdwpz_disp").css("height",h);
	
	$("#submitbutton", navTab.getCurrentPanel()).click(function(){
		var flag = false;
	if ("${Tf01_wxdw.id}"==""){
		alertMsg.confirm("您选择的该合作单位类别为"+$("input[name='Tf01_wxdw\\.LB']:checked").val()+"单位，确认保存吗？", {
			okCall: function(){
				$.ajax({
					url:'wxdw/getWxdwNOAjax.do?',
					data:'lb='+$("input[name='Tf01_wxdw\\.LB']:checked").val(),
					type:'post',
					success:function(msg){
						msg = $.trim(msg);
						$("#no").val(msg);
						validateCallback($("#wxdw_form"),navTabAjaxDone);
					}
				});
			}
		});
	} else if ("${Tf01_wxdw.lb}"!=$("input[name='Tf01_wxdw\\.LB']:checked").val()){
		alertMsg.confirm("您更改了该合作单位的类别，确认保存吗？", {
			okCall: function(){
					validateCallback($("#wxdw_form"),navTabAjaxDone);
			}
		});
	} else {
		validateCallback($("#wxdw_form"),navTabAjaxDone);
	}
	});
});
</script>

<div id="wxdw_info">
	<form id="wxdw_form" method="post" action="save.do"	class="pageForm required-validate">
		<input type="hidden" name="tableInfomation"	value="noFatherTable:com.rms.dataObjects.wxdw.Tf01_wxdw" />
		<input type="hidden" name="Tf01_wxdw.ID" value="${Tf01_wxdw.id}" />
		<input type="hidden" name="perproty" value="id,Tf01_wxdw,id">
		<input type="hidden" name="_forwardUrl" value="wxdw/wxdwEdit.do" />
		<input type="hidden" name="_callbackType" value="forward" />
		<input type="hidden" name="_navTabId" value="wxdwList" />
		<input type="hidden" name="Tf01_wxdw.NO" id="no" value="${Tf01_wxdw.no }" />
		<div class="pageFormContent">
			
				<p>
					<label>单位名称：</label>
					<input class="required" type="text" style="width:405px;" name="Tf01_wxdw.MC" value="${Tf01_wxdw.mc}" />
				</p>
				<p>
					<label>营业执照号：</label>
					<input type="text" name="Tf01_wxdw.YYZZH" style="width:120px;" value="${Tf01_wxdw.yyzzh}"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>单位地址：</label>
					<input type="text" style="width:630px;" name="Tf01_wxdw.DWDZ" value="${Tf01_wxdw.dwdz}" />
				</p>
				<div style="height:0px;"></div>
				<p>
					<label width="90"> 法    人：</label>
					<input type="text" name="Tf01_wxdw.FR" style="width:150px;" value="${Tf01_wxdw.fr}"/>
				</p>
				<p>
					<label>资质等级：</label>
					<input type="text" name="Tf01_wxdw.ZZDJ" style="width:150px;" value="${Tf01_wxdw.zzdj}"/>
				</p>
				<p>
					<label>状    态：</label>
					<input type="radio" name="Tf01_wxdw.ZT" value="正常" checked="checked"/>正常
					<input type="radio" name="Tf01_wxdw.ZT" value="停工" <c:if test="${Tf01_wxdw.zt == '停工'}">checked="checked"</c:if>/>停工
					
				</p> 
				<div style="height:0px;"></div>
				<p>
					<label>类    别：</label>
					<input type="radio" name="Tf01_wxdw.LB" value="设计" <c:if test="${Tf01_wxdw.lb == '设计' || empty Tf01_wxdw.lb}">checked="checked"</c:if>/>设计单位
					<input type="radio" name="Tf01_wxdw.LB" value="施工" <c:if test="${Tf01_wxdw.lb == '施工'}">checked="checked"</c:if>/>施工单位
					<input type="radio" name="Tf01_wxdw.LB" value="监理" <c:if test="${Tf01_wxdw.lb == '监理'}">checked="checked"</c:if>/>监理单位
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>备    注：</label>
					<textarea class="td-textarea" style="width:630px;height:40px;" name="Tf01_wxdw.BZ">${Tf01_wxdw.bz}</textarea>
				</p>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive"><div class="buttonContent"><button type="button" id="submitbutton">保 存</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
<div class="tabs">
	<div class="tabsHeader">
		<div class="tabsHeaderContent">
			<ul>
				<li>
					<a href="javascript:void(0)"><span>帐 户</span> </a>
				</li>
				<li>
					<a href="javascript:void(0)"><span>区域专业</span> </a>
				</li>
				<li>
					<a href="javascript:void(0)"><span>份额占比</span> </a>
				</li>
				<li>
					<a href="javascript:void(0)"><span>最大在建工程数</span> </a>
				</li>
				<li>
					<a href="javascript:void(0)"><span>关联交易额</span> </a>
				</li>
				<li>
					<a href="javascript:void(0)"><span>施工队</span> </a>
				</li>
				<li>
					<a href="javascript:void(0)"><span>外协人员</span> </a>
				</li>
			</ul>
		</div>
	</div>
	<div id="wxdwpz_disp" class="tabsContent">
		<div id="glyh_tab" class="loadFileArea"	loadfile="wxdw/wxdwUserList.do?wxdw_id=${Tf01_wxdw.id}">

		</div>
		<div id="qyzy_tab" class="loadFileArea" loadfile="wxdw/qyZyEdit.do?lb=qyzy&wxdw_id=${Tf01_wxdw.id}">

		</div>
		<div id="fezb_tab" class="loadFileArea" loadfile="wxdw/fezbEdit.do?lb=fezb&wxdw_id=${Tf01_wxdw.id}">

		</div>
		<div id="zdgcs_tab" class="loadFileArea" loadfile="wxdw/zjgcsEdit.do?lb=zdgcs&wxdw_id=${Tf01_wxdw.id}">

		</div>
		<div id="gljye_tab" class="loadFileArea" loadfile="wxdw/gljyeEdit.do?lb=gljye&wxdw_id=${Tf01_wxdw.id}">

		</div>
		<div id="sgd_tab" class="loadFileArea" loadfile="wxdw/sgdEdit.do?lb=sgd&wxdw_id=${Tf01_wxdw.id}">

		</div>
		<div id="glyh_tab" class="loadFileArea"	loadfile="wxdw/wxryList.do?wxdw_id=${Tf01_wxdw.id}">

		</div>
	</div>
	<div class="tabsFooter">
		<div class="tabsFooterContent"></div>
	</div>
</div>
