<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />

<input type="hidden" name="configType" value="byxml"/>
<input type="hidden" name="profile" value="xmxxd.xml"/>
<input type="hidden" name="Td00_gcxx.ID" value="${param.doc_id}">

<div class="pageFormContent">
	<p>
		<label>项目名称：</label>
		<input type="text" name="Td00_gcxx.XMMC" value="${td01_xmxx.xmmc}" style="width:407px;"/>
	</p>
	<p>
		<label>项目编号：</label>
		<input type="text" name="Td01_xmxx.XMBH" value="${td01_xmxx.xmbh}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>投资切块：</label>
		<netsky:htmlSelect name="Td01_xmxx.QKDL" objectForOption="qkdlList" style="width:157px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true"  value="${td01_xmxx.qkdl}" htmlClass="td-select"/>
	</p>
	<p>
		<label>切块细项：</label>
		<netsky:htmlSelect name="Td01_xmxx.QKXL" objectForOption="qkdlList" style="width:157px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true"  value="${td01_xmxx.qkxl}" htmlClass="td-select"/>
	</p>
	<p>
		<label>所属区域：</label>
		<netsky:htmlSelect name="Td01_xmxx.SSDQ" objectForOption="ssdqList" style="width:127px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true"  value="${td01_xmxx.ssdq}" htmlClass="td-select"/>
	</p>
	<div class="divider"></div>
	<p style="color:#ccc;font-weight:bold;width:513px;text-align:center;">预算情况</p>
	<p>
		<label>工程总投资：</label>
		<input type="text"  name="Td01_xmxx.YS_JE" value="${td01_xmxx.ys_je}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>建安费：</label>
		<input type="text"  name="Td01_xmxx.YS_JAF" value="${td01_xmxx.ys_jaf}" style="width:150px;"/>
	</p>
	<p>
		<label>设备费：</label>
		<input type="text"  name="Td01_xmxx.YS_SBF" value="${td01_xmxx.ys_sbf}" style="width:150px;"/>
	</p>
	<p>
		<label>材料费：</label>
		<input type="text"  name="Td01_xmxx.YS_CLF" value="${td01_xmxx.ys_clf}" style="width:120px;"/>
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
		<label>机械费：</label>
		<input type="text"  name="Td01_xmxx.YS_JXF" value="${td01_xmxx.ys_jxf}" style="width:150px;"/>
	</p>
	<p>
		<label>仪表费：</label>
		<input type="text"  name="Td01_xmxx.YS_YBF" value="${td01_xmxx.ys_ybf}" style="width:150px;"/>
	</p>
	<p>
		<label>其它费：</label>
		<input type="text"  name="Td01_xmxx.YS_QTF" value="${td01_xmxx.ys_qtf}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>监理费：</label>
		<input type="text"  name="Td01_xmxx.YS_JLF" value="${td01_xmxx.ys_jlf}" style="width:150px;"/>
	</p>
	<p>
		<label>施工费：</label>
		<input type="text"  name="Td01_xmxx.YS_SGF" value="${td01_xmxx.ys_sgf}" style="width:150px;"/>
	</p>
	<p>
		<label>设计费：</label>
		<input type="text"  name="Td01_xmxx.YS_SJF" value="${td01_xmxx.ys_sjf}" style="width:120px;"/>
	</p>	
	<div class="divider"></div>
	<p>
		<label>立项金额：</label>
		<input type="text"  name="Td01_xmxx.LXJE" value="${td01_xmxx.ys_lxje}" style="width:150px;"/>
	</p>
	<p>
		<label>立项时间：</label>
		<input type="text"  name="Td01_xmxx.LXSJ" value="${td01_xmxx.ys_lxsj}" style="width:150px;"/>
	</p>
	<p>
		<label>需求部门：</label>
		<input type="text"  name="Td01_xmxx.XQBM" value="${td01_xmxx.xqbm}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>设计单位：</label>
		<input type="text"  name="Td01_xmxx.SJDW" value="${td01_xmxx.sjdw}" style="width:407px;"/>
	</p>
	<p>
		<label>设计派发时间：</label>
		<input type="text"  name="Td01_xmxx.SJPGSJ" value="${td01_xmxx.sjpgsj}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label><a href="sgpd.do?xm_id=${Td01_xmxx.id}" lookupGroup="sgdwOrg" width="700" height="380" style="color:red;">施工单位</a>：</label>
		<input type="text"  name="Td01_xmxx.SGDW" id="sgdwOrg.SGDW" value="${Td01_xmxx.sgdw}" style="width:407px;" readonly="readonly"/>
		<input type="hidden"  name="Td01_xmxx.SDPGYY" id="sgdwOrg.SDPGYY" value="${Td01_xmxx.sdpgyy}" style="width:150px;"/>
	</p>
	<p>
		<label>施工派发时间：</label>
		<input type="text"  name="Td01_xmxx.SGPFSJ" value="${td01_xmxx.sgpfsj}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>监理单位：</label>
		<input type="text"  name="Td01_xmxx.JLDW" value="${td01_xmxx.jldw}" style="width:407px;"/>
	</p>
	<p>
		<label>监理派发时间：</label>
		<input type="text"  name="Td01_xmxx.JLPFSJ" value="${td01_xmxx.jlpfsj}" style="width:120px;"/>
	</p>
	<div class="divider"></div>
	<p>
		<label>项目管理员：</label>
		<input type="text"  name="Td01_xmxx.XMGLY" value="${td01_xmxx.xmgly}" style="width:150px;"/>
	</p>
	<p>
		<label>施工管理员：</label>
		<input type="text"  name="Td01_xmxx.SGGLY" value="${td01_xmxx.sggly}" style="width:150px;"/>
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
		<input type="text"  name="Td01_xmxx.SJKGSJ" value="${td01_xmxx.sjkgsj}" style="width:150px;"/>
	</p>
	<p>
		<label>竣工时间：</label>
		<input type="text"  name="Td01_xmxx.SJJGSJ" value="${td01_xmxx.sjjgsj}" style="width:150px;"/>
	</p>
	<p>
		<label>验收时间：</label>
		<input type="text"  name="Td01_xmxx.YSSJ" value="${td01_xmxx.yssj}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>预算类型：</label>
		<input type="text"  name="Td01_xmxx.YSLX" value="${td01_xmxx.yslx}" style="width:150px;"/>
	</p>
	<p>
		<label>项目阶段：</label>
		<input type="text"  name="Td01_xmxx.XMJD" value="${td01_xmxx.xmjd}" style="width:150px;"/>
	</p>
	<p>
		<label>项目状态：</label>
		<input type="text"  name="Td01_xmxx.XMZT" value="${td01_xmxx.xmzt}" style="width:120px;"/>
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
		<input type="text"  name="Td01_xmxx.SJHTQDRQ" value="${td01_xmxx.sjhtqdrq}" style="width:120px;"/>
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
		<input type="text"  name="Td01_xmxx.SGHTQDRQ" value="${td01_xmxx.sghtqdrq}" style="width:120px;"/>
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
		<input type="text"  name="Td01_xmxx.JLHTQDRQ" value="${td01_xmxx.jlhtqdrq}" style="width:120px;"/>
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
		<input type="text"  name="Td01_xmxx.SS_JGGR" value="${Td01_xmxx.ss_jggr}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.CS_JGGR" value="${Td01_xmxx.cs_jggr}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.SD_JGGR" value="${Td01_xmxx.sd_jggr}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.HJE_JGGR" value="${Td01_xmxx.hje_jggr}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.HJL_JGGR" value="${Td01_xmxx.hjl_jggr}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>	
	<p style="width:105px;text-align:center;">普工工日</p>
	<p>
		<input type="text"  name="Td01_xmxx.SS_PGF" value="${Td01_xmxx.ss_pgf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.CS_PGF" value="${Td01_xmxx.cs_pgf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.SD_PGF" value="${Td01_xmxx.sd_pgf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.HJE_PGF" value="${Td01_xmxx.hje_pgf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.HJL_PGF" value="${Td01_xmxx.hjl_pgf}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>	
	<p style="width:105px;text-align:center;">建安费</p>
	<p>
		<input type="text"  name="Td01_xmxx.SS_JAF" value="${Td01_xmxx.ss_jaf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.CS_JAF" value="${Td01_xmxx.cs_jaf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.SD_JAF" value="${Td01_xmxx.sd_jaf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.HJE_JAF" value="${Td01_xmxx.hje_jaf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.HJL_JAF" value="${Td01_xmxx.hjl_jaf}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>	
	<p style="width:105px;text-align:center;">人工费</p>
	<p>
		<input type="text"  name="Td01_xmxx.SS_RGF" value="${Td01_xmxx.ss_rgf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.CS_RGF" value="${Td01_xmxx.cs_rgf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.SD_RGF" value="${Td01_xmxx.sd_rgf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.HJE_RGF" value="${Td01_xmxx.hje_rgf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.HJL_RGF" value="${Td01_xmxx.hjl_rgf}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>	
	<p style="width:105px;text-align:center;">材料费</p>
	<p>
		<input type="text"  name="Td01_xmxx.SS_CLF" value="${Td01_xmxx.ss_clf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.CS_CLF" value="${Td01_xmxx.cs_clf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.SD_CLF" value="${Td01_xmxx.sd_clf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.HJE_CLF" value="${Td01_xmxx.hje_clf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.HJL_CLF" value="${Td01_xmxx.hjl_clf}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>	
	<p style="width:105px;text-align:center;">设备费</p>
	<p>
		<input type="text"  name="Td01_xmxx.SS_SBF" value="${Td01_xmxx.ss_sbf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.CS_SBF" value="${Td01_xmxx.cs_sbf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.SD_SBF" value="${Td01_xmxx.sd_sbf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.HJE_SBF" value="${Td01_xmxx.hje_sbf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.HJL_SBF" value="${Td01_xmxx.hjl_sbf}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>	
	<p style="width:105px;text-align:center;">机械费</p>
	<p>
		<input type="text"  name="Td01_xmxx.SS_JXF" value="${Td01_xmxx.ss_jxf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.CS_JXF" value="${Td01_xmxx.cs_jxf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.SD_JXF" value="${Td01_xmxx.sd_jxf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.HJE_JXF" value="${Td01_xmxx.hje_jxf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.HJL_JXF" value="${Td01_xmxx.hjl_jxf}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>	
	<p style="width:105px;text-align:center;">仪表费</p>
	<p>
		<input type="text"  name="Td01_xmxx.SS_YBF" value="${Td01_xmxx.ss_ybf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.CS_YBF" value="${Td01_xmxx.cs_ybf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.SD_YBF" value="${Td01_xmxx.sd_ybf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.HJE_YBF" value="${Td01_xmxx.hje_ybf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.HJL_YBF" value="${Td01_xmxx.hjl_ybf}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>	
	<p style="width:105px;text-align:center;">设计费</p>
	<p>
		<input type="text"  name="Td01_xmxx.SS_SJF" value="${Td01_xmxx.ss_sjf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.CS_SJF" value="${Td01_xmxx.cs_sjf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.SD_SJF" value="${Td01_xmxx.sd_sjf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.HJE_SJF" value="${Td01_xmxx.hje_sjf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.HJL_SJF" value="${Td01_xmxx.hjl_sjf}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>	
	<p style="width:105px;text-align:center;">监理费</p>
	<p>
		<input type="text"  name="Td01_xmxx.SS_JLF" value="${Td01_xmxx.ss_jlf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.CS_JLF" value="${Td01_xmxx.cs_jlf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.SD_JLF" value="${Td01_xmxx.sd_jlf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.HJE_JLF" value="${Td01_xmxx.hje_jlf}" style="width:120px;"/>
	</p>
	<p>
		<input type="text"  name="Td01_xmxx.HJL_JLF" value="${Td01_xmxx.hjl_jlf}" style="width:120px;"/>
	</p>
	
	<div style="height:0px;"></div>
	<p>
		<label>项目说明：</label>
		<textarea class="td-textarea" style="width:630px;height:60px;" type="text" name="Td01_xmxx.GCSM">${Td01_xmxx.gcsm}</textarea>
	</p>
	<div class="divider"></div>
	<p style="color:blue;font-weight:bold;">&nbsp;&nbsp;&nbsp;相关工程列表</p>
	<div style="height:0px;"></div>
	
	
	
</div>