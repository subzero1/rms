<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script language="javascript">

//计算页面上tab允许高度
$(function(){
	var bo = $("#business_oper");
	if(bo){
		var h = bo.height() - $("#enterprise_info").height() - 40;
		$("#business_disp").css("height",h);
	}
	$("#serialInfoShow").click(
	   function(){
	      var tax_code="${d04.tax_code}";
	      this.href="business/searialInfoShow.do?tax_code="+tax_code;
	   }
	);
	$("#d04_form :input").change(function() {
		$("#d04_form").data("changed",true);
	});
});
</script>

<div id="enterprise_info">
	<form id="wxdw_form" method="post" action="save.do"	class="pageForm required-validate"	onsubmit="return validateCallback(this,navTabAjaxDone);">
		<input type="hidden" name="tableInfomation"	value="noFatherTable:com.rms.dataObjects.wxdw.Tf01_wxdw" />
		<input type="hidden" name="Tf01_wxdw.ID" value="${tf01.id}" />
		<div class="pageFormContent">
			
				<p>
					<label>单位名称：</label>
					<input type="text" id="master.dwz_devLooup.Td21_mbk.JYMC" style="width:380px;" name="Td21_mbk.JDMC" value="${Td21_mbk.zymc}" />
				</p>
				<p>
					<label>营业执照号：</label>
					<input readonly type="text" name="Td21_mbk.ZYBH" style="width:120px;" readonly value="${Td21_mbk.zybh}"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>单位地址：</label>
					<input type="text" id="master.dwz_devLooup.Td21_mbk.JYMC" style="width:380px;" name="Td21_mbk.JDMC" value="${Td21_mbk.zymc}" />
				</p>
				<p>
					<label width="90"> 法    人：</label>
					<input type="text" name="Td21_mbk.FL" style="width:120px;" value="${Td21_mbk.fl}"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>资质等级：</label>
					<netsky:htmlSelect name="Td21_mbk.SSDQ" style="width:126px;" objectForOption="dqList" valueForOption="name" showForOption="name" value="${Td21_mbk.ssdq}" extend="" extendPrefix="true" />
				</p>
				<p>
					<label>状    态：</label>
					<input type="text" name="Td21_mbk.ZLDD" style="width:150px;" value="${Td21_mbk.zldd}"/>
				</p> 
				<div style="height:0px;"></div>
				<p>
					<label>类    别：</label>
					<input type="text" name="Td21_mbk.JD" style="width:150px;" value="${Td21_mbk.jd}"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>备    注：</label>
					<textarea class="td-textarea" style="width:630px;height:40px;" type="text" name="Td21_mbk.BZ"/>${Td21_mbk.bz}</textarea>
				</p>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive"><div class="buttonContent"><button type="submit">保 存</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
<div class="tabs">
	<div class="tabsHeader">
		<div class="tabsHeaderContent">
			<ul>
				<li class="selected">
					<a href="javascript:void(0)"><span>用户</span> </a>
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
			</ul>
		</div>
	</div>
	<div id="business_disp" class="tabsContent">
		<div id="glyh_tab" class="loadFileArea"	loadfile="">

		</div>
		<div id="qyzy_tab" class="loadFileArea" loadfile="">

		</div>
		<div id="fezb_tab" class="loadFileArea" loadfile="">

		</div>
		<div id="zdgcs_tab" class="loadFileArea" loadfile="">

		</div>
		<div id="gljye_tab" class="loadFileArea" loadfile="">

		</div>
		<div id="sgd_tab" class="loadFileArea" loadfile="">

		</div>
	</div>
	<div class="tabsFooter">
		<div class="tabsFooterContent"></div>
	</div>
</div>