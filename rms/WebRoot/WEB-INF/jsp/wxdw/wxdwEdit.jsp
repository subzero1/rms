<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
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
					<input type="text" style="width:405px;" name="Tf01_wxdw.MC" value="${Tf01_wxdw.mc}" />
				</p>
				<p>
					<label>营业执照号：</label>
					<input readonly type="text" name="Tf01_wxdw.YYZZH" style="width:120px;" readonly value="${Tf01_wxdw.yyzzh}"/>
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
					<input type="text" name="Tf01_wxdw.ZT" style="width:120px;" value="${Tf01_wxdw.zt}"/>
				</p> 
				<div style="height:0px;"></div>
				<p>
					<label>类    别：</label>
					<input type="radio" name="Tf01_wxdw.LB" value="设计单位"/>设计单位
					<input type="radio" name="Tf01_wxdw.LB" value="设计单位"/>施工单位
					<input type="radio" name="Tf01_wxdw.LB" value="设计单位"/>监理单位
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>备    注：</label>
					<textarea class="td-textarea" style="width:630px;height:40px;" type="text" name="Tf01_wxdw.BZ"/>${Tf01_wxdw.bz}</textarea>
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