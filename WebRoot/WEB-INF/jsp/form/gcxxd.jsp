<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />

<input type="hidden" name="configType" value="byxml"/>
<input type="hidden" name="profile" value="gcxxd.xml"/>
<input type="hidden" name="Td00_gcxx.ID" value="${param.doc_id}">
<input type="hidden" name="XM_ID" value="${Td00_gcxx.xm_id}">

<div class="pageFormContent">
	<p>
		<label>需求部门：</label>
		<input type="text" readOnly name="Td00_gcxx.XQBM" value="<c:out value="${td00_gcxx.xqbm}" default="${user.dept_name}"/>" style="width:150px;"/>
	</p>
	<p>
		<label>需求提出人：</label> 
		<input type="text" readOnly name="Td00_gcxx.CJR" value="<c:out value="${td00_gcxx.cjr}" default="${user.name}"/>" style="width:150px;"/>
	</p>
	<p>
		<label>提出日期：</label>
		<input readonly type="text" name="Td00_gcxx.CJRQ" style="width:120px;" value="<c:choose><c:when test="${empty param.doc_id}"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm"/></c:when><c:otherwise><fmt:formatDate value="${td00_gcxx.cjrq}" pattern="yyyy-MM-dd HH:mm"/></c:otherwise></c:choose>"/>
	</p>
	<div class="divider"></div>
	<p>
		<label>工程名称：</label>
		<input type="text" name="Td00_gcxx.GCMC" value="${Td00_gcxx.gcmc}" style="width:407px;"/>
	</p>
	<p>
		<label>工程编号：</label>
		<input type="text" name="Td00_gcxx.GCBH" value="${Td00_gcxx.gcbh}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>所属区域：</label>
		<netsky:htmlSelect name="Td00_gcxx.SSDQ" objectForOption="ssdqList" style="width:157px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true"  value="${td00_gcxx.ssdq}" htmlClass="td-select"/>
	</p>
	<p>
		<label>要求完成时间：</label>
		<input type="text"  name="Td00_gcxx.YQWCSJ" value="${Td00_gcxx.yqwcsj}" style="width:150px;"/>
	</p>
	<p>
		<label>项目编号：</label>
		<input type="text" readOnly name="Td00_gcxx.XMBH" value="${Td00_gcxx.xmbh}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>工程类别：</label>
		<netsky:htmlSelect name="Td00_gcxx.GCLB" objectForOption="gclbList" style="width:157px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true" value="${Td00_gcxx.gclb}" htmlClass="td-select"/>
	</p>
	<p>
		<label>工程专业：</label>
		<netsky:htmlSelect name="Td00_gcxx.ZYDL" objectForOption="zydlList" style="width:155px;" valueForOption="name" showForOption="name" value="${Td00_gcxx.zydl}" htmlClass="td-select"/>
	</p>
	<p>
		<label>专业细项：</label>
		<netsky:htmlSelect name="Td00_gcxx.ZYXX" objectForOption="zyxxList" style="width:125px;" valueForOption="name" showForOption="name" value="${Td00_gcxx.zyxx}" htmlClass="td-select"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>需求说明：</label>
		<textarea class="td-textarea" style="width:630px;height:60px;" type="text" name="Td00_gcxx.GCSM">${Td00_gcxx.gcsm}</textarea>
	</p>
	<div class="divider"></div>
	<p>
		<label>设计单位：</label>
		<netsky:htmlSelect name="Td00_gcxx.SJDW" objectForOption="sjdwList" style="width:157px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true" value="${Td00_gcxx.sjdw}" htmlClass="td-select"/>
	</p>
	<p>
		<label>勘察反馈时限：</label>
		<input type="text"  name="Td00_gcxx.KCFKZQ" value="${Td00_gcxx.kcfkzq}" style="width:150px;"/>
	</p>
	<p>
		<label>设计时限：</label>
		<input type="text"  name="Td00_gcxx.SJSX" value="${Td00_gcxx.sjsx}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label><a href="sgpftst.do" target="navTab" rel="sgpftst">透</a><a href="sgpd.do?project_id=${Td00_gcxx.id}" lookupGroup="sgdwOrg" width="700" height="380" style="color:red;">施工单位</a>：</label>
		<input type="text"  name="Td00_gcxx.SGDW" id="sgdwOrg.SGDW" value="${Td00_gcxx.sgdw}" style="width:150px;" readonly="readonly"/>
		<input type="hidden"  name="Td00_gcxx.SDPGYY" id="sgdwOrg.SDPGYY" value="${Td00_gcxx.sdpgyy}" style="width:150px;"/>
	</p>
	<p>
		<label>进度填报周期：</label>
		<input type="text"  name="Td00_gcxx.SGJDTBZQ" value="${Td00_gcxx.sgjdtbzq}" style="width:150px;"/>
	</p>
	<p>
		<label>计划竣工时间：</label>
		<input type="text"  name="Td00_gcxx.JHJGSJ" value="${Td00_gcxx.jhjgsj}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>监理单位：</label>
		<netsky:htmlSelect name="Td00_gcxx.JLDW" objectForOption="jldwList" style="width:157px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true" value="${Td00_gcxx.jldw}" htmlClass="td-select"/>
	</p>
	<p>
		<label>日志填报周期：</label>
		<input type="text"  name="Td00_gcxx.JLRJTBZQ" value="${Td00_gcxx.jlrjtbzq}" style="width:150px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>设计派发时间：</label>
		<input type="text"  name="Td00_gcxx.SJPFSJ" value="${Td00_gcxx.sjpfsj}" style="width:150px;"/>
	</p>
	<p>
		<label>施工派发时间：</label>
		<input type="text"  name="Td00_gcxx.SGPFSJ" value="${Td00_gcxx.sgpfsj}" style="width:150px;"/>
	</p>
	<p>
		<label>监理派发时间：</label>
		<input type="text"  name="Td00_gcxx.JLPFSJ" value="${Td00_gcxx.jlpfsj}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>设计人员：</label>
		<input type="text"  name="Td00_gcxx.SJRY" value="${Td00_gcxx.sjry}" style="width:150px;"/>
	</p>
	<p>
		<label>施工管理员：</label>
		<input type="text"  name="Td00_gcxx.FWBM" value="${Td00_gcxx.fwbm}" style="width:150px;"/>
	</p>
	<p>
		<label>监理工程师：</label>
		<input type="text"  name="Td00_gcxx.FWBM" value="${Td00_gcxx.fwbm}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>项目管理员：</label>
		<input type="text"  name="Td00_gcxx.FWBM" value="${Td00_gcxx.fwbm}" style="width:150px;"/>
	</p>
	<p>
		<label>实际开工时间：</label>
		<input type="text"  name="Td00_gcxx.SJKGSJ" value="${Td00_gcxx.sjkgsj}" style="width:150px;"/>
	</p>
	<p>
		<label>实际竣工时间：</label>
		<input type="text"  name="Td00_gcxx.SJJGSJ" value="${Td00_gcxx.sjjgsj}" style="width:120px;"/>
	</p>
	
	<div class="divider"></div>
	<p style="color:#ccc;font-weight:bold;width:513px;text-align:center;">预算情况</p>
	<p>
		<label>工程总投资：</label>
		<input type="text"  name="Td00_gcxx.YS_JE" value="${Td00_gcxx.ys_je}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>建安费：</label>
		<input type="text"  name="Td00_gcxx.YS_JAF" value="${Td00_gcxx.ys_jaf}" style="width:150px;"/>
	</p>
	<p>
		<label>设备费：</label>
		<input type="text"  name="Td00_gcxx.YS_SBF" value="${Td00_gcxx.ys_sbf}" style="width:150px;"/>
	</p>
	<p>
		<label>材料费：</label>
		<input type="text"  name="Td00_gcxx.YS_CLF" value="${Td00_gcxx.ys_clf}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>人工费：</label>
		<input type="text"  name="Td00_gcxx.YS_RGF" value="${Td00_gcxx.ys_rgf}" style="width:150px;"/>
	</p>
	<p>
		<label>普工工日：</label>
		<input type="text"  name="Td00_gcxx.YS_PGGR" value="${Td00_gcxx.ys_pggr}" style="width:150px;"/>
	</p>
	<p>
		<label>技工工日：</label>
		<input type="text"  name="Td00_gcxx.YS_JGGR" value="${Td00_gcxx.ys_jggr}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>机械费：</label>
		<input type="text"  name="Td00_gcxx.YS_JXF" value="${Td00_gcxx.ys_jxf}" style="width:150px;"/>
	</p>
	<p>
		<label>仪表费：</label>
		<input type="text"  name="Td00_gcxx.YS_YBF" value="${Td00_gcxx.ys_ybf}" style="width:150px;"/>
	</p>
	<p>
		<label>其它费：</label>
		<input type="text"  name="Td00_gcxx.YS_QTF" value="${Td00_gcxx.ys_qtf}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>监理费：</label>
		<input type="text"  name="Td00_gcxx.YS_JLF" value="${Td00_gcxx.ys_jlf}" style="width:150px;"/>
	</p>
	<p>
		<label>施工费：</label>
		<input type="text"  name="Td00_gcxx.YS_SGF" value="${Td00_gcxx.ys_sgf}" style="width:150px;"/>
	</p>
	<p>
		<label>设计费：</label>
		<input type="text"  name="Td00_gcxx.YS_SJF" value="${Td00_gcxx.ys_sjf}" style="width:120px;"/>
	</p>
	
	<div class="divider"></div>
	<p>
		<label>设计要求：</label>
		<textarea class="td-textarea" style="width:262px;height:40px;" type="text" name="Td00_gcxx.SJYQ">${Td00_gcxx.sjyq}</textarea>
	</p>
	<p>
		<label>设计反馈：</label>
		<textarea class="td-textarea" style="width:262px;height:40px;" type="text" name="Td00_gcxx.SJFK">${Td00_gcxx.sjfk}</textarea>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>施工要求：</label>
		<textarea class="td-textarea" style="width:262px;height:40px;" type="text" name="Td00_gcxx.SGYQ">${Td00_gcxx.sgyq}</textarea>
	</p>
	<p>
		<label>施工反馈：</label>
		<textarea class="td-textarea" style="width:262px;height:40px;" type="text" name="Td00_gcxx.SGFK">${Td00_gcxx.sgfk}</textarea>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>监理要求：</label>
		<textarea class="td-textarea" style="width:262px;height:40px;" type="text" name="Td00_gcxx.JLYQ">${Td00_gcxx.jlyq}</textarea>
	</p>
	<p>
		<label>监理反馈：</label>
		<textarea class="td-textarea" style="width:262px;height:40px;" type="text" name="Td00_gcxx.JLFK">${Td00_gcxx.jlfk}</textarea>
	</p>
	
	<div class="divider"></div>
	<p style="color:blue;font-weight:bold;">&nbsp;&nbsp;&nbsp;相关工程列表</p>
	<div style="height:0px;"></div>
	
	
	
</div>