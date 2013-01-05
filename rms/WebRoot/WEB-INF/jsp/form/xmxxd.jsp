<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />

<script type="text/javascript">
$(function(){
	   		$("#qkdl_select",navTab.getCurrentPanel()).cascade({
				childSelect:$("#qkxl_select",navTab.getCurrentPanel()),
				tableName:'Tc07_qkxx',
				conditionColumn:'qk_id',
				valueForOption:'mc',
				key:'id',
				orderBy:'id',
				showForOption:{
								pattern:'[mc]',
								mc:'mc'
				}
			});	
	   	});
	   	
	function xzdbgc(){
		//选择打包工程
		var url = 'form/xzgcForDblx.do?xm_id=${td01_xmxx.id}';
		$.pdialog.open(url,'_xzgcForDblx','选择打包工程',{width:800,height:520});
	}
	
	function yssc(){
		//预算上传
		var url = 'dispath.do?url=gysImport.jsp?module_id=101&project_id=${td01_xmxx.id}';
		$.pdialog.open(url,'_yssc','预算上传',{width:400,height:180});
	}
	
	function gcdf(project_id,lb){
		//选择打包工程
		var url = 'wxdwkh/gcdf.do?project_id='+project_id+'&lb='+lb;
		$.pdialog.open(url,'_gcdf','工程考核',{width:500,height:309});
	}
	
	/*
	*防止将工程误删除
	*/
	var bg_je = '${td01_xmxx.bg_je}';
	if(bg_je == null || bg_je == ''){
		$("#BG_JE_XM").val(0);
	}
	else{
		$("#BG_JE_XM").val(bg_je);
	}
	
	
	/**
		sObj:需要修改的对象
		dObj:级联更新的对象
		hObj:辅助对象，隐藏域
	*/
	function computeZje(sObj,dObj,hObj){		 	
		var svar = sObj.value;
		var dvar = dObj.value;
		var hvar = hObj.value;

		if(svar == '')
			svar = 0;
		if(dvar == '')
			dvar = 0;
		if(hvar == '')
			hvar = 0;
		dvar = Number(dvar) - Number(hvar) + Number(svar);
		dObj.value = Math.round(dvar*100)/100;
	}
</script>


<input type="hidden" name="configType" value="byxml"/>
<input type="hidden" name="profile" value="xmxxd.xml"/>
<input type="hidden" name="Td01_xmxx.ID" value="${param.doc_id}">
<input type="hidden" name="Td01_xmxx.CJR" value="<c:out value="${td01_xmxx.cjr}" default="${user.name}"/>">
<input type="hidden" name="Td01_xmxx.XMGLYDH" value="<c:out value="${td01_xmxx.xmglydh}" default="${user.mobile_tel}"/>">
<input type="hidden" name="Td01_xmxx.CJRQ" value="<c:choose><c:when test="${empty param.doc_id}"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm"/></c:when><c:otherwise><fmt:formatDate value="${td01_xmxx.cjrq}" pattern="yyyy-MM-dd HH:mm"/></c:otherwise></c:choose>">
<input type="hidden" id="BG_JE_XM" name="Td01_xmxx.BG_JE" value="">
<input type="hidden" name="Td01_xmxx.XQS_ID" value="${td01_xmxx.xqs_id }"/>

<p>
	<label>项目名称：</label>
	<input type="text" name="Td01_xmxx.XMMC" value="${td01_xmxx.xmmc}" style="width:407px;"/>
</p>
<p>
	<label>项目编号：</label>
	<input type="text" name="Td01_xmxx.XMBH" value="${td01_xmxx.xmbh}" style="width:120px;"/>
</p>
<div style="height:0px;"></div>
<p>
	<label>投资切块：</label>
	<netsky:htmlSelect id="qkdl_select" name="Td01_xmxx.QKDL" objectForOption="qkdlList" style="width:157px;" valueForOption="qkmc" showForOption="qkmc" valueForExtend="{'id':'[id]','nd':'[nd]'}" extend="" extendPrefix="true"  value="${td01_xmxx.qkdl}" htmlClass="td-select"/>
</p>
<p>
	<label>切块细项：</label>
	<netsky:htmlSelect id="qkxl_select" name="Td01_xmxx.QKXL" objectForOption="qkxlList" style="width:157px;" valueForOption="mc" showForOption="mc" extend="" extendPrefix="true"  value="${td01_xmxx.qkxl}" htmlClass="td-select"/>
</p>
<p>
	<label>所属区域：</label>
	<netsky:htmlSelect name="Td01_xmxx.SSDQ" objectForOption="ssdqList" style="width:127px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true"  value="${td01_xmxx.ssdq}" htmlClass="td-select"/>
</p>
<div style="height:0px;"></div>
<p>
	<label>项目类别：</label>
	<netsky:htmlSelect name="Td01_xmxx.GCLB" objectForOption="gclbList" style="width:157px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true"  value="${td01_xmxx.gclb}" htmlClass="td-select"/>
</p>
<p>
	<label>预算类型：</label>
	<netsky:htmlSelect name="Td01_xmxx.YSLX" objectForOption="yslxList" style="width:157px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true"  value="${td01_xmxx.yslx}" htmlClass="td-select"/>
</p>
<p>
	<label>项目状态：</label>
	<netsky:htmlSelect name="Td01_xmxx.XMZT" objectForOption="xmztList" style="width:127px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true"  value="${td01_xmxx.xmzt}" htmlClass="td-select"/>
</p>
<div class="divider"></div>
<p style="color:#ccc;font-weight:bold;width:700px;text-align:center;">预算情况</p>
<div style="height:0px;"></div>
<p>
	<label>总投资：</label>
	<input type="text"  name="Td01_xmxx.YS_JE" value="${td01_xmxx.ys_je}" style="width:150px;"/>
</p>
<p>
	<label>建安费：</label>
	<input type="text"  name="Td01_xmxx.YS_JAF" value="${td01_xmxx.ys_jaf}" style="width:150px;"/>
</p>
<p>
	<label>设备费：</label>
	<input type="text"  name="Td01_xmxx.YS_SBF" value="${td01_xmxx.ys_sbf}" style="width:120px;"/>
</p>
<div style="height:0px;"></div>
<p>
	<label>人工费：</label>
	<input type="text"  name="Td01_xmxx.YS_RGF" value="${td01_xmxx.ys_rgf}" style="width:150px;"/>
</p>
<p>
	<label>普工工日：</label>
	<input type="text"  name="Td01_xmxx.YS_PGGR" value="${td01_xmxx.ys_pggr}" style="width:150px;"/>
</p>
<p>
	<label>技工工日：</label>
	<input type="text"  name="Td01_xmxx.YS_JGGR" value="${td01_xmxx.ys_jggr}" style="width:120px;"/>
</p>
<div style="height:0px;"></div>
<p>
	<label>材料费：</label>
	<input type="text"  name="Td01_xmxx.YS_CLF" value="${td01_xmxx.ys_clf}" style="width:150px;"/>
</p>
<p>
	<label>机械费：</label>
	<input type="text"  name="Td01_xmxx.YS_JXF" value="${td01_xmxx.ys_jxf}" style="width:150px;"/>
</p>
<p>
	<label>仪表费：</label>
	<input type="text"  name="Td01_xmxx.YS_YBF" value="${td01_xmxx.ys_ybf}" style="width:120px;"/>
</p>
<div style="height:0px;"></div>
<p>
	<label>设计费：</label>
	<input type="text"  name="Td01_xmxx.YS_SJF" value="${td01_xmxx.ys_sjf}" style="width:150px;"/>
</p>
<p>
	<label>监理费：</label>
	<input type="text"  name="Td01_xmxx.YS_JLF" value="${td01_xmxx.ys_jlf}" style="width:150px;"/>
</p>
<p>
	<label>其它费：</label>
	<input type="text"  name="Td01_xmxx.YS_QTF" value="${td01_xmxx.ys_qtf}" style="width:120px;"/>
</p>	
<div class="divider"></div>
<p>
	<label>需求部门：</label>
	<netsky:htmlSelect name="Td01_xmxx.XQBM" objectForOption="deptList" style="width:157px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true"  value="${td01_xmxx.xqbm}" htmlClass="td-select"/>
</p>
<p>
	<label>立项金额：</label>
	<input type="text"  name="Td01_xmxx.LXJE" value="${td01_xmxx.lxje}" style="width:150px;"/>
</p>
<p>
	<label>立项时间：</label>
	<input type="text"  name="Td01_xmxx.LXSJ" value="<fmt:formatDate value="${td01_xmxx.lxsj}" pattern="yyyy-MM-dd"/>" style="width:120px;"/>
</p>
<div style="height:0px;"></div>
<p>
	<label>设计单位：</label>
	<netsky:htmlSelect name="Td01_xmxx.SJDW" objectForOption="sjdwList" style="width:412px;" valueForOption="mc" showForOption="mc" extend="" extendPrefix="true" value="${td01_xmxx.sjdw}" htmlClass="td-select"/>
</p>
<p>
	<label>设计派发时间：</label>
	<input type="text"  name="Td01_xmxx.SJPGSJ" value="<fmt:formatDate value="${td01_xmxx.sjpgsj}" pattern="yyyy-MM-dd"/>" style="width:120px;"/>
</p>
<div style="height:0px;"></div>
<p>
	<label>
		<c:choose>
			<c:when test="${param.node_id == 10101}">
				<a href="sgpd.do?xm_id=${td01_xmxx.id}" lookupGroup="sgdwOrg" width="700" height="380" style="color:red;">施工单位</a>：
			</c:when>
			<c:otherwise>
				施工单位：
			</c:otherwise>
		</c:choose>
	</label>
	<input type="text"  name="Td01_xmxx.SGDW" id="sgdwOrg.SGDW" value="${td01_xmxx.sgdw}" style="width:407px;" readonly="readonly"/>
	<input type="hidden"  name="Td01_xmxx.SDPGYY" id="sgdwOrg.SDPGYY" value="${td01_xmxx.sdpgyy}" style="width:150px;"/>
</p>
<p>
	<label>施工派发时间：</label>
	<input type="text"  name="Td01_xmxx.SGPFSJ" value="<fmt:formatDate value="${td01_xmxx.sgpfsj}" pattern="yyyy-MM-dd"/>" style="width:120px;"/>
</p>
<div style="height:0px;"></div>
<p>
	<label>监理单位：</label>
	<netsky:htmlSelect name="Td01_xmxx.JLDW" objectForOption="jldwList" style="width:412px;" valueForOption="mc" showForOption="mc" extend="" extendPrefix="true" value="${td01_xmxx.jldw}" htmlClass="td-select"/>
</p>
<p>
	<label>监理派发时间：</label>
	<input type="text"  name="Td01_xmxx.JLPFSJ" value="<fmt:formatDate value="${td01_xmxx.jlpfsj}" pattern="yyyy-MM-dd"/>" style="width:120px;"/>
</p>
<div class="divider"></div>
<p>
	<label>项目管理员：</label>
	<input type="text"  name="Td01_xmxx.XMGLY" value="<c:out value="${td01_xmxx.xmgly}" default="${user.name}" />" style="width:150px;"/>
</p>
<p>
	<label>施工管理员：</label>
	<input type="text"  name="Td01_xmxx.SGFZR" value="${td01_xmxx.sgfzr}" style="width:150px;"/>
</p>
<p>
	<label>监理工程师：</label>
	<input type="text"  name="Td01_xmxx.JLGCS" value="${td01_xmxx.jlgcs}" style="width:120px;"/>
</p>
<div style="height:0px;"></div>
<p>
	<label>设计人员：</label>
	<input type="text"  name="Td01_xmxx.SJRY" value="${td01_xmxx.sjry}" style="width:150px;"/>
</p>
<p>
	<label>验收人员：</label>
	<input type="text"  name="Td01_xmxx.YSRY" value="${td01_xmxx.ysry}" style="width:150px;"/>
</p>
<p>
	<label>立项管理员：</label>
	<input type="text"  name="Td01_xmxx.LXGLY" value="${td01_xmxx.lxgly}" style="width:120px;"/>
</p>
<div style="height:0px;"></div>
<p>
	<label>开工时间：</label>
	<input type="text"  name="Td01_xmxx.SJKGSJ" value="<fmt:formatDate value="${td01_xmxx.sjkgsj}" pattern="yyyy-MM-dd"/>" style="width:150px;"/>
</p>
<p>
	<label>竣工时间：</label>
	<input type="text"  name="Td01_xmxx.SJJGSJ" value="<fmt:formatDate value="${td01_xmxx.sjjgsj}" pattern="yyyy-MM-dd"/>" style="width:150px;"/>
</p>
<p>
	<label>验收时间：</label>
	<input type="text"  name="Td01_xmxx.YSSJ" value="<fmt:formatDate value="${td01_xmxx.yssj}" pattern="yyyy-MM-dd"/>" style="width:120px;"/>
</p>
<div style="height:0px;"></div>
<p>
	<label>项目说明：</label>
	<textarea class="td-textarea" style="width:630px;height:60px;" type="text" name="Td01_xmxx.XMSM">${td01_xmxx.xmsm}</textarea>
</p>

<div class="divider"></div>
<p style="color:#ccc;font-weight:bold;width:700px;text-align:center;">合同信息</p>
<div style="height:0px;"></div>	
<p>
	<label>设计合同编号：</label>
	<input type="text"  name="Td01_xmxx.SJHTBH" value="${td01_xmxx.sjhtbh}" style="width:150px;"/>
</p>
<p>
	<label>金额：</label>
	<input type="text"  name="Td01_xmxx.SJHTJE" value="${td01_xmxx.sjhtje}" style="width:150px;"/>
</p>
<p>
	<label>签订日期：</label>
	<input type="text"  name="Td01_xmxx.SJHTQDRQ" value="<fmt:formatDate value="${td01_xmxx.sjhtqdrq}" pattern="yyyy-MM-dd"/>" style="width:120px;"/>
</p>
<div style="height:0px;"></div>	
<p>
	<label>施工合同编号：</label>
	<input type="text"  name="Td01_xmxx.SGHTBH" value="${td01_xmxx.sghtbh}" style="width:150px;"/>
</p>
<p>
	<label>金额：</label>
	<input type="text"  name="Td01_xmxx.SGHTJE" value="${td01_xmxx.sghtje}" style="width:150px;"/>
</p>
<p>
	<label>签订日期：</label>
	<input type="text"  name="Td01_xmxx.SGHTQDRQ" value="<fmt:formatDate value="${td01_xmxx.sghtqdrq}" pattern="yyyy-MM-dd"/>" style="width:120px;"/>
</p>
<div style="height:0px;"></div>	
<p>
	<label>监理合同编号：</label>
	<input type="text"  name="Td01_xmxx.JLHTBH" value="${td01_xmxx.jlhtbh}" style="width:150px;"/>
</p>
<p>
	<label>金额：</label>
	<input type="text"  name="Td01_xmxx.JLHTJE" value="${td01_xmxx.jlhtje}" style="width:150px;"/>
</p>
<p>
	<label>签订日期：</label>
	<input type="text"  name="Td01_xmxx.JLHTQDRQ" value="<fmt:formatDate value="${td01_xmxx.jlhtqdrq}" pattern="yyyy-MM-dd"/>" style="width:120px;"/>
</p>

<div class="divider"></div>
<p style="color:#ccc;font-weight:bold;width:700px;text-align:center;">结算情况</p>
<div style="height:0px;"></div>	
<p style="width:105px;text-align:center;">费用</p>
<p style="width:120px;text-align:center;">结算</p>
<p style="width:120px;text-align:center;">初审</p>
<p style="width:120px;text-align:center;">审计</p>
<p style="width:120px;text-align:center;">核减额</p>
<p style="width:120px;text-align:center;">核减率</p>
<div style="height:0px;"></div>	
<p style="width:105px;text-align:center;">技工工日</p>
<p>
	<input type="text"  name="Td01_xmxx.SS_JGGR" value="<fmt:formatNumber value="${td01_xmxx.ss_jggr}" pattern="##0.00"/>" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.CS_JGGR" value="<fmt:formatNumber value="${td01_xmxx.cs_jggr}" pattern="##0.00"/>" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.SD_JGGR" value="<fmt:formatNumber value="${td01_xmxx.sd_jggr}" pattern="##0.00"/>" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.HJE_JGGR" value="" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.HJL_JGGR" value="" style="width:120px;"/>
</p>
<div style="height:0px;"></div>	
<p style="width:105px;text-align:center;">普工工日</p>
<p>
	<input type="text"  name="Td01_xmxx.SS_PGGR" value="<fmt:formatNumber value="${td01_xmxx.ss_pggr}" pattern="##0.00"/>" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.CS_PGGR" value="<fmt:formatNumber value="${td01_xmxx.cs_pggr}" pattern="##0.00"/>" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.SD_PGGR" value="<fmt:formatNumber value="${td01_xmxx.sd_pggr}" pattern="##0.00"/>" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.HJE_PGGR" value="" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.HJL_PGGR" value="" style="width:120px;"/>
</p>
<div style="height:0px;"></div>	
<p style="width:105px;text-align:center;">材料费</p>
<p>
	<input type="text"  name="Td01_xmxx.SS_CLF" value="<fmt:formatNumber value="${td01_xmxx.ss_clf}" pattern="##0.00"/>" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.CS_CLF" value="<fmt:formatNumber value="${td01_xmxx.cs_clf}" pattern="##0.00"/>" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.SD_CLF" value="<fmt:formatNumber value="${td01_xmxx.sd_clf}" pattern="##0.00"/>" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.HJE_CLF" value="" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.HJL_CLF" value="" style="width:120px;"/>
</p>
<div style="height:0px;"></div>	
<p style="width:105px;text-align:center;">机械仪表费</p>
<p>
	<input type="text"  name="Td01_xmxx.SS_JXF" value="<fmt:formatNumber value="${td01_xmxx.ss_jxf}" pattern="##0.00"/>" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.CS_JXF" value="<fmt:formatNumber value="${td01_xmxx.cs_jxf}" pattern="##0.00"/>" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.SD_JXF" value="<fmt:formatNumber value="${td01_xmxx.sd_jxf}" pattern="##0.00"/>" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.HJE_JXF" value="" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.HJL_JXF" value="" style="width:120px;"/>
</p>
<div style="height:0px;"></div>	
<p style="width:105px;text-align:center;">其它费</p>
<p>
	<input type="text"  name="Td01_xmxx.SS_QTF" value="<fmt:formatNumber value="${td01_xmxx.ss_qtf}" pattern="##0.00"/>" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.CS_QTF" value="<fmt:formatNumber value="${td01_xmxx.cs_qtf}" pattern="##0.00"/>" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.SD_QTF" value="<fmt:formatNumber value="${td01_xmxx.sd_qtf}" pattern="##0.00"/>" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.HJE_QTF" value="" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.HJL_QTF" value="" style="width:120px;"/>
</p>
<div style="height:0px;"></div>	
<p style="width:105px;text-align:center;">施工总费用</p>
<p>
	<input type="text"  name="Td01_xmxx.SS_SGF" value="<fmt:formatNumber value="${td01_xmxx.ss_sgf}" pattern="##0.00"/>" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.CS_SGF" value="<fmt:formatNumber value="${td01_xmxx.cs_sgf}" pattern="##0.00"/>" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.SD_SGF" value="<fmt:formatNumber value="${td01_xmxx.sd_sgf}" pattern="##0.00"/>" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.HJE_SGF" value="" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.HJL_SGF" value="" style="width:120px;"/>
</p>
<div style="height:0px;"></div>	
<p style="width:105px;text-align:center;">监理费</p>
<p>
	<input type="text"  name="Td01_xmxx.SS_JLF" value="<fmt:formatNumber value="${td01_xmxx.ss_jlf}" pattern="##0.00"/>" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.CS_JLF" value="<fmt:formatNumber value="${td01_xmxx.cs_jlf}" pattern="##0.00"/>" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.SD_JLF" value="<fmt:formatNumber value="${td01_xmxx.sd_jlf}" pattern="##0.00"/>" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.HJE_JLF" value="" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.HJL_JLF" value="" style="width:120px;"/>
</p>
<div style="height:0px;"></div>
<p style="width:105px;text-align:center;">结算总费用</p>
<p>
	<input type="text"  name="Td01_xmxx.SS_JE" value="<fmt:formatNumber value="${td01_xmxx.ss_je}" pattern="##0.00"/>" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.CS_JE" value="<fmt:formatNumber value="${td01_xmxx.cs_je}" pattern="##0.00"/>" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.SD_JE" value="<fmt:formatNumber value="${td01_xmxx.sd_je}" pattern="##0.00"/>" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.HJE_JE" value="" style="width:120px;"/>
</p>
<p>
	<input type="text"  name="Td01_xmxx.HJL_JE" value="" style="width:120px;"/>
</p>	

<div style="height:10px;"></div>
<div style="text-align:left;color:blue;"><h3>&nbsp;&nbsp;打包工程列表</h3></div><div class="divider" style="height:1px;"></div>
<div style="width:780px;">
	<table class="table" width="100%">
		<thead>
			<tr>
				<th style="width: 30px;">序号</th>
				<th style="width: 120px;">工程编号</th>
				<th >工程名称</th>
			</tr>
		</thead>
		<tbody>
		<c:set var="offset" value="0"/>
			<c:forEach items="${glgcList}" var="obj">
			<c:set var="offset" value="${offset+1}"/>
				<tr>
					<td>${offset }</td>
					<td>${obj.gcbh }</td>
					<td><a href="javascript:openFlowForm('{project_id:${obj.id },doc_id:${obj.id },module_id:102,opernode_id:-1,node_id:-1,user_id:${user.id }}');">${obj.gcmc }</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>