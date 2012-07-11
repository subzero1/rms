<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />

<script type="text/javascript">
$(function(){
	   		$("#zydl_select",navTab.getCurrentPanel()).cascade({
				childSelect:$("#zyxx_select",navTab.getCurrentPanel()),
				tableName:'Tc04_zyxx',
				conditionColumn:'gczy_id',
				valueForOption:'mc',
				key:'id',
				orderBy:'id',
				showForOption:{
								pattern:'[mc]',
								mc:'mc'
				}
			});	
	   	});
	   	
	function xjglgc(){
		var t_param = 'module_id=102&node_id=10201&flow_id=102&glgc_id=${td00_gcxx.id}';
		navTab.closeCurrentTab();
		navTab.openTab('autoform102', 'flowForm.do?'+t_param, {title:'表单'});
		
	}
</script>


<input type="hidden" name="configType" value="byxml"/>
<input type="hidden" name="profile" value="gcxxd.xml"/>
<input type="hidden" name="Td00_gcxx.ID" value="${param.doc_id}">
<input type="hidden" name="XM_ID" value="${td00_gcxx.xm_id}">
<input type="hidden" name="GLGC_ID" value="${td00_gcxx.glgc_id}">

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
		<input type="text" name="Td00_gcxx.GCMC" value="${td00_gcxx.gcmc}" style="width:407px;"/>
	</p>
	<p>
		<label>工程编号：</label>
		<input type="text" name="Td00_gcxx.GCBH" value="${td00_gcxx.gcbh}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>所属区域：</label>
		<netsky:htmlSelect name="Td00_gcxx.SSDQ" objectForOption="ssdqList" style="width:157px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true"  value="${td00_gcxx.ssdq}" htmlClass="td-select"/>
	</p>
	<p>
		<label>要求完成时间：</label>
		<input type="text"  name="Td00_gcxx.YQWCSJ" value="<fmt:formatDate value="${td00_gcxx.yqwcsj}" pattern="yyyy-MM-dd"/>" style="width:150px;"/>
	</p>
	<p>
		<label>项目编号：</label>
		<input type="text" readOnly name="Td00_gcxx.XMBH" value="${td01_xmxx.xmbh}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>工程类别：</label>
		<netsky:htmlSelect name="Td00_gcxx.GCLB" objectForOption="gclbList" style="width:157px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true" value="${td00_gcxx.gclb}" htmlClass="td-select"/>
	</p>
	<p>
		<label>工程专业：</label>
		<netsky:htmlSelect id="zydl_select" name="Td00_gcxx.ZYDL" objectForOption="zydlList" style="width:157px;" valueForOption="zymc" showForOption="zymc" valueForExtend="{'id':'[id]','yxnd':'[yxnd]'}" extend="" extendPrefix="true" value="${td00_gcxx.zydl}" htmlClass="td-select"/>
	</p>
	<p>
		<label>专业细项：</label>
		<netsky:htmlSelect id="zyxx_select" name="Td00_gcxx.ZYXX" objectForOption="zyxxList" style="width:125px;" valueForOption="mc" showForOption="mc" extend="" extendPrefix="true" value="${td00_gcxx.zyxx}" htmlClass="td-select"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>需求说明：</label>
		<textarea class="td-textarea" style="width:630px;height:60px;" type="text" name="Td00_gcxx.GCSM">${td00_gcxx.gcsm}</textarea>
	</p>
	<div class="divider"></div>
	<p>
		<label>设计单位：</label>
		<netsky:htmlSelect name="Td00_gcxx.SJDW" objectForOption="sjdwList" style="width:157px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true" value="${td00_gcxx.sjdw}" htmlClass="td-select"/>
	</p>
	<p>
		<label>勘察反馈时限：</label>
		<input type="text"  name="Td00_gcxx.KCFKZQ" value="${td00_gcxx.kcfkzq}" style="width:150px;"/>
	</p>
	<p>
		<label>设计时限：</label>
		<input type="text"  name="Td00_gcxx.SJSX" value="${td00_gcxx.sjsx}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label><a href="sgpftst.do" target="navTab" rel="sgpftst">透</a><a href="sgpd.do?project_id=${Td00_gcxx.id}" lookupGroup="sgdwOrg" width="700" height="380" style="color:red;">施工单位</a>：</label>
		<input type="text"  name="Td00_gcxx.SGDW" id="sgdwOrg.SGDW" value="${td00_gcxx.sgdw}" style="width:150px;" readonly="readonly"/>
		<input type="hidden"  name="Td00_gcxx.SDPGYY" id="sgdwOrg.SDPGYY" value="${td00_gcxx.sdpgyy}" style="width:150px;"/>
	</p>
	<p>
		<label>进度填报周期：</label>
		<input type="text"  name="Td00_gcxx.SGJDTBZQ" value="${td00_gcxx.sgjdtbzq}" style="width:150px;"/>
	</p>
	<p>
		<label>计划竣工时间：</label>
		<input type="text"  name="Td00_gcxx.JHJGSJ" value="<fmt:formatDate value="${td00_gcxx.jhjgsj}" pattern="yyyy-MM-dd"/>" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>监理单位：</label>
		<netsky:htmlSelect name="Td00_gcxx.JLDW" objectForOption="jldwList" style="width:157px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true" value="${td00_gcxx.jldw}" htmlClass="td-select"/>
	</p>
	<p>
		<label>日志填报周期：</label>
		<input type="text"  name="Td00_gcxx.JLRJTBZQ" value="${td00_gcxx.jlrjtbzq}" style="width:150px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>设计派发时间：</label>
		<input type="text"  name="Td00_gcxx.SJPGSJ" value="<fmt:formatDate value="${td00_gcxx.sjpgsj}" pattern="yyyy-MM-dd"/>" style="width:150px;"/>
	</p>
	<p>
		<label>施工派发时间：</label>
		<input type="text"  name="Td00_gcxx.SGPFSJ" value="<fmt:formatDate value="${td00_gcxx.sgpfsj}" pattern="yyyy-MM-dd"/>" style="width:150px;"/>
	</p>
	<p>
		<label>监理派发时间：</label>
		<input type="text"  name="Td00_gcxx.JLPFSJ" value="<fmt:formatDate value="${td00_gcxx.jlpfsj}" pattern="yyyy-MM-dd"/>" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>设计人员：</label>
		<input type="text"  name="Td00_gcxx.SJRY" value="${td00_gcxx.sjry}" style="width:150px;"/>
	</p>
	<p>
		<label>施工管理员：</label>
		<input type="text"  name="Td00_gcxx.SGFZR" value="${td00_gcxx.sgfzr}" style="width:150px;"/>
	</p>
	<p>
		<label>监理工程师：</label>
		<input type="text"  name="Td00_gcxx.JLGCS" value="${td00_gcxx.jlgcs}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>项目管理员：</label>
		<input type="text"  name="Td00_gcxx.XMGLY" value="${td00_gcxx.xmgly}" style="width:150px;"/>
	</p>
	<p>
		<label>实际开工时间：</label>
		<input type="text"  name="Td00_gcxx.SJKGSJ" value="<fmt:formatDate value="${td00_gcxx.sjkgsj}" pattern="yyyy-MM-dd"/>" style="width:150px;"/>
	</p>
	<p>
		<label>实际竣工时间：</label>
		<input type="text"  name="Td00_gcxx.SJJGSJ" value="<fmt:formatDate value="${td00_gcxx.sjjgsj}" pattern="yyyy-MM-dd"/>" style="width:120px;"/>
	</p>
	
	<div class="divider"></div>
	<p style="color:#ccc;font-weight:bold;width:513px;text-align:center;">预算情况</p>
	<p>
		<label>工程总投资：</label>
		<input type="text"  name="Td00_gcxx.YS_JE" value="${td00_gcxx.ys_je}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>建安费：</label>
		<input type="text"  name="Td00_gcxx.YS_JAF" value="${td00_gcxx.ys_jaf}" style="width:150px;"/>
	</p>
	<p>
		<label>设备费：</label>
		<input type="text"  name="Td00_gcxx.YS_SBF" value="${td00_gcxx.ys_sbf}" style="width:150px;"/>
	</p>
	<p>
		<label>材料费：</label>
		<input type="text"  name="Td00_gcxx.YS_CLF" value="${td00_gcxx.ys_clf}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>人工费：</label>
		<input type="text"  name="Td00_gcxx.YS_RGF" value="${td00_gcxx.ys_rgf}" style="width:150px;"/>
	</p>
	<p>
		<label>普工工日：</label>
		<input type="text"  name="Td00_gcxx.YS_PGGR" value="${td00_gcxx.ys_pggr}" style="width:150px;"/>
	</p>
	<p>
		<label>技工工日：</label>
		<input type="text"  name="Td00_gcxx.YS_JGGR" value="${td00_gcxx.ys_jggr}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>机械费：</label>
		<input type="text"  name="Td00_gcxx.YS_JXF" value="${td00_gcxx.ys_jxf}" style="width:150px;"/>
	</p>
	<p>
		<label>仪表费：</label>
		<input type="text"  name="Td00_gcxx.YS_YBF" value="${td00_gcxx.ys_ybf}" style="width:150px;"/>
	</p>
	<p>
		<label>其它费：</label>
		<input type="text"  name="Td00_gcxx.YS_QTF" value="${td00_gcxx.ys_qtf}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>监理费：</label>
		<input type="text"  name="Td00_gcxx.YS_JLF" value="${td00_gcxx.ys_jlf}" style="width:150px;"/>
	</p>
	<p>
		<label>施工费：</label>
		<input type="text"  name="Td00_gcxx.YS_SGF" value="${td00_gcxx.ys_sgf}" style="width:150px;"/>
	</p>
	<p>
		<label>设计费：</label>
		<input type="text"  name="Td00_gcxx.YS_SJF" value="${td00_gcxx.ys_sjf}" style="width:120px;"/>
	</p>
	
	<div class="divider"></div>
	<p>
		<label>设计要求：</label>
		<textarea class="td-textarea" style="width:262px;height:40px;" type="text" name="Td00_gcxx.SJYQ">${td00_gcxx.sjyq}</textarea>
	</p>
	<p>
		<label>设计反馈：</label>
		<textarea class="td-textarea" style="width:262px;height:40px;" type="text" name="Td00_gcxx.SJFK">${td00_gcxx.sjfk}</textarea>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>施工要求：</label>
		<textarea class="td-textarea" style="width:262px;height:40px;" type="text" name="Td00_gcxx.SGYQ">${td00_gcxx.sgyq}</textarea>
	</p>
	<p>
		<label>施工反馈：</label>
		<textarea class="td-textarea" style="width:262px;height:40px;" type="text" name="Td00_gcxx.SGFK">${td00_gcxx.sgfk}</textarea>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>监理要求：</label>
		<textarea class="td-textarea" style="width:262px;height:40px;" type="text" name="Td00_gcxx.JLYQ">${td00_gcxx.jlyq}</textarea>
	</p>
	<p>
		<label>监理反馈：</label>
		<textarea class="td-textarea" style="width:262px;height:40px;" type="text" name="Td00_gcxx.JLFK">${td00_gcxx.jlfk}</textarea>
	</p>
	
	<div class="divider"></div>
	<p style="color:blue;font-weight:bold;">&nbsp;&nbsp;&nbsp;相关工程列表</p>
	<div style="height:0px;"></div>
	
	
	
</div>