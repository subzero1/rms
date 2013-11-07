<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />

<script type="text/javascript">
$(function(){
			var $Td01_xmxx_GCLB=$("select[name='Td01_xmxx\.GCLB']");
			var $Td01_xmxx_ID=$("input[name='Td01_xmxx\.ID']");
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
			checkProject($Td01_xmxx_GCLB.val(),$Td01_xmxx_ID.val());
			 
			$Td01_xmxx_GCLB.change(function(){
				checkProject($(this).val(),$Td01_xmxx_ID.val()); 
			});	
	   	});
	   	
	function xzdbgc(){
		//ѡ��������
		var url = 'form/xzgcForDblx.do?xm_id=${td01_xmxx.id}';
		$.pdialog.open(url,'_xzgcForDblx','ѡ��������',{width:800,height:520});
	}
	
	function yssc(){
		//Ԥ���ϴ�
		var url = 'dispath.do?url=gysImport.jsp?module_id=101&project_id=${td01_xmxx.id}';
		$.pdialog.open(url,'_yssc','Ԥ���ϴ�',{width:400,height:180});
	}
	
	function gcdf(project_id,lb){
		//ѡ��������
		var url = 'wxdwkh/gcdf.do?project_id='+project_id+'&lb='+lb;
		$.pdialog.open(url,'_gcdf','���̿���',{width:700,height:433});
	}
	
	/*
	*��ֹ��������ɾ��
	*/
	var bg_je = '${td01_xmxx.bg_je}';
	if(bg_je == null || bg_je == ''){
		$("#BG_JE_XM").val(0);
	}
	else{
		$("#BG_JE_XM").val(bg_je);
	}
	
	
	/**
		sObj:��Ҫ�޸ĵĶ���
		dObj:�������µĶ���
		hObj:��������������
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
	function checkProject(param0,param1){
		var $sgdw=$("#sgdw");
		$.ajax({
			type:'post',
			url:'sgdw/checkProject.do',
			data:{name:param0},
			dataType:'html',
			async:false,
			success:function(msg){ 
			  if(msg.indexOf('[3]') == -1){
			  	$sgdw.attr("href","sgpd/sgpfCompany.do?xm_id="+param1);
			  }else {
			    $sgdw.attr("href","sgpd.do?xm_id="+param1);
			  }
			}
			
		});
	}
	
	function sgpf_for_xm(v1,v2){
		$.ajax({
			type:'post',
			url:'form/ajaxForSgpf.do',
			data:{project_id:v1,module_id:v2},
			dataType:"json",
			success: function(json){
				if(json.statusCode == DWZ.statusCode.ok){
					alertMsg.correct(json.message);
					return false;
				}
			},
			error: DWZ.ajaxError
		});
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
	<label>��Ŀ���ƣ�</label>
	<input type="text" name="Td01_xmxx.XMMC" value="${td01_xmxx.xmmc}" style="width:407px;"/>
</p>
<p>
	<label>��Ŀ��ţ�</label>
	<input type="text" name="Td01_xmxx.XMBH" value="${td01_xmxx.xmbh}" style="width:120px;"/>
</p>
<div style="height:0px;"></div>
<p>
	<label>��Ŀ���ͣ�</label>
	<netsky:htmlSelect name="Td01_xmxx.XMLX" objectForOption="xmlxList" style="width:157px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true"  value="${td01_xmxx.xmlx}" htmlClass="td-select"/>
</p>
<p>
	<label>Ԥ�����ͣ�</label>
	<netsky:htmlSelect name="Td01_xmxx.YSLX" objectForOption="yslxList" style="width:157px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true"  value="${td01_xmxx.yslx}" htmlClass="td-select"/>
</p>
<p>
	<label>��������</label>
	<netsky:htmlSelect name="Td01_xmxx.SSDQ" objectForOption="ssdqList" style="width:127px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true"  value="${td01_xmxx.ssdq}" htmlClass="td-select"/>
</p>
<div style="height:0px;"></div>
<p>
	<label>��Ŀ���</label>
	<netsky:htmlSelect name="Td01_xmxx.GCLB" objectForOption="gclbList" style="width:157px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true"  value="${td01_xmxx.gclb}" htmlClass="td-select"/>
</p>
<p>
	<label>����רҵ��</label>
		<netsky:htmlSelect id="zydl_select" name="Td01_xmxx.ZYDL" objectForOption="zydlList" style="width:157px;" valueForOption="zymc" showForOption="zymc" valueForExtend="{'id':'[id]','yxnd':'[yxnd]'}" extend="" extendPrefix="true" value="${td01_xmxx.zydl}" htmlClass="td-select"/>
</p>
<p>
	<label>רҵϸ�</label>
	<netsky:htmlSelect id="zyxx_select" name="Td01_xmxx.ZYXX" objectForOption="zyxxList" style="width:125px;" valueForOption="mc" showForOption="mc" extend="" extendPrefix="true" value="${td01_xmxx.zyxx}" htmlClass="td-select"/>
</p>
<div style="height:0px;"></div>
<p>
	<label>Ͷ���п飺</label>
	<netsky:htmlSelect id="qkdl_select" name="Td01_xmxx.QKDL" objectForOption="qkdlList" style="width:157px;" valueForOption="qkmc" showForOption="qkmc" valueForExtend="{'id':'[id]','nd':'[nd]'}" extend="" extendPrefix="true"  value="${td01_xmxx.qkdl}" htmlClass="td-select"/>
</p>
<p>
	<label>�п�ϸ�</label>
	<netsky:htmlSelect id="qkxl_select" name="Td01_xmxx.QKXL" objectForOption="qkxlList" style="width:157px;" valueForOption="mc" showForOption="mc" extend="" extendPrefix="true"  value="${td01_xmxx.qkxl}" htmlClass="td-select"/>
</p>
<p>
	<label>��Ŀ״̬��</label>
	<netsky:htmlSelect name="Td01_xmxx.XMZT" objectForOption="xmztList" style="width:127px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true"  value="${td01_xmxx.xmzt}" htmlClass="td-select"/>
</p>
<div class="divider"></div>
<p style="color:#ccc;font-weight:bold;width:700px;text-align:center;">Ԥ�������Ԫ��</p>
<div style="height:0px;"></div>
<p>
	<label>��Ͷ�ʣ�</label>
	<input type="text"  name="Td01_xmxx.YS_JE" value="${td01_xmxx.ys_je}" style="width:150px;"/>
</p>
<p>
	<label>�����ѣ�</label>
	<input type="text"  name="Td01_xmxx.YS_JAF" value="${td01_xmxx.ys_jaf}" style="width:150px;"/>
</p>
<p>
	<label>�豸�ѣ�</label>
	<input type="text"  name="Td01_xmxx.YS_SBF" value="${td01_xmxx.ys_sbf}" style="width:120px;"/>
</p>
<div style="height:0px;"></div>
<p>
	<label>�˹��ѣ�</label>
	<input type="text"  name="Td01_xmxx.YS_RGF" value="${td01_xmxx.ys_rgf}" style="width:150px;"/>
</p>
<p>
	<label>�չ����գ�</label>
	<input type="text"  name="Td01_xmxx.YS_PGGR" value="${td01_xmxx.ys_pggr}" style="width:150px;"/>
</p>
<p>
	<label>�������գ�</label>
	<input type="text"  name="Td01_xmxx.YS_JGGR" value="${td01_xmxx.ys_jggr}" style="width:120px;"/>
</p>
<div style="height:0px;"></div>
<p>
	<label>���Ϸѣ�</label>
	<input type="text"  name="Td01_xmxx.YS_CLF" value="${td01_xmxx.ys_clf}" style="width:150px;"/>
</p>
<p>
	<label>��е�ѣ�</label>
	<input type="text"  name="Td01_xmxx.YS_JXF" value="${td01_xmxx.ys_jxf}" style="width:150px;"/>
</p>
<p>
	<label>�Ǳ�ѣ�</label>
	<input type="text"  name="Td01_xmxx.YS_YBF" value="${td01_xmxx.ys_ybf}" style="width:120px;"/>
</p>
<div style="height:0px;"></div>
<p>
	<label>��Ʒѣ�</label>
	<input type="text"  name="Td01_xmxx.YS_SJF" value="${td01_xmxx.ys_sjf}" style="width:150px;"/>
</p>
<p>
	<label>����ѣ�</label>
	<input type="text"  name="Td01_xmxx.YS_JLF" value="${td01_xmxx.ys_jlf}" style="width:150px;"/>
</p>
<p>
	<label>�����ѣ�</label>
	<input type="text"  name="Td01_xmxx.YS_QTF" value="${td01_xmxx.ys_qtf}" style="width:120px;"/>
</p>	
<div class="divider"></div>
<p>
	<label>�����ţ�</label>
	<netsky:htmlSelect name="Td01_xmxx.XQBM" objectForOption="deptList" style="width:157px;" valueForOption="name" showForOption="name" extend="" extendPrefix="true"  value="${td01_xmxx.xqbm}" htmlClass="td-select"/>
</p>
<p>
	<label>�����</label>
	<input type="text"  name="Td01_xmxx.LXJE" value="${td01_xmxx.lxje}" style="width:115px;"/>
	<span>��Ԫ��</span>
</p>
<p>
	<label>����ʱ�䣺</label>
	<input type="text"  name="Td01_xmxx.LXSJ" value="<fmt:formatDate value="${td01_xmxx.lxsj}" pattern="yyyy-MM-dd"/>" style="width:120px;"/>
</p>
<div style="height:0px;"></div>
<p>
	<label>��Ƶ�λ��</label>
	<netsky:htmlSelect name="Td01_xmxx.SJDW" objectForOption="sjdwList" style="width:412px;" valueForOption="mc" showForOption="mc" extend="" extendPrefix="true" value="${td01_xmxx.sjdw}" htmlClass="td-select"/>
</p>
<p>
	<label>����ɷ�ʱ�䣺</label>
	<input type="text"  name="Td01_xmxx.SJPGSJ" value="<fmt:formatDate value="${td01_xmxx.sjpgsj}" pattern="yyyy-MM-dd"/>" style="width:120px;"/>
</p>
<div style="height:0px;"></div>
<p>
	<label>
		<c:choose>
			<c:when test="${(param.node_id == 10101 && empty user.send_htgly && empty td01_xmxx.sgdw) || param.node_id == 10105}">
				<a href="sgpd.do?xm_id=${td01_xmxx.id}" lookupGroup="sgdwOrg" width="700" height="380" style="color:red;" id="sgdw">ʩ����λ</a>��
			</c:when>
			<c:otherwise>
				ʩ����λ��
			</c:otherwise>
		</c:choose>
	</label>
	<input type="text"  name="Td01_xmxx.SGDW" id="sgdwOrg.SGDW" value="${td01_xmxx.sgdw}" style="width:407px;" readonly="readonly"/>
	<input type="hidden"  name="Td01_xmxx.SDPGYY" id="sgdwOrg.SDPGYY" value="${td01_xmxx.sdpgyy}" style="width:150px;"/>
	
</p>
<p>
	<label>ʩ���ɷ�ʱ�䣺</label>
	<input type="text"  name="Td01_xmxx.SGPFSJ" value="<fmt:formatDate value="${td01_xmxx.sgpfsj}" pattern="yyyy-MM-dd"/>" style="width:120px;"/>
</p>
<div style="height:0px;"></div>
<p>
	<label>����λ��</label>
	<netsky:htmlSelect name="Td01_xmxx.JLDW" objectForOption="jldwList" style="width:412px;" valueForOption="mc" showForOption="mc" extend="" extendPrefix="true" value="${td01_xmxx.jldw}" htmlClass="td-select"/>
</p>
<p>
	<label>�����ɷ�ʱ�䣺</label>
	<input type="text"  name="Td01_xmxx.JLPFSJ" value="<fmt:formatDate value="${td01_xmxx.jlpfsj}" pattern="yyyy-MM-dd"/>" style="width:120px;"/>
</p>
<div style="height:0px;"></div>
<p>
	<label>��ƿ���ʱ�ޣ�</label>
	<input type="text"  name="Td01_xmxx.KCFKZQ" value="${td01_xmxx.kcfkzq}" style="width:115px;"/>
	<span>���죩</span>
</p>
<p>
	<label>ʩ������ڣ�</label>
	<input type="text"  name="Td01_xmxx.SGJDTBZQ" value="${td01_xmxx.sgjdtbzq}" style="width:115px;"/>
	<span>���죩</span>
</p>
<p>
	<label>���������ڣ�</label>
	<input type="text"  name="Td01_xmxx.JLRJTBZQ" value="${td01_xmxx.jlrjtbzq}" style="width:80px;"/>
	<span>���죩</span>
</p>
<div class="divider"></div>
<p>
	<label>��Ŀ����Ա��</label>
	<input type="text"  name="Td01_xmxx.XMGLY" value="<c:out value="${td01_xmxx.xmgly}" default="${user.name}" />" style="width:150px;"/>
</p>
<p>
	<label>ʩ������Ա��</label>
	<input type="text"  name="Td01_xmxx.SGFZR" value="${td01_xmxx.sgfzr}" style="width:150px;"/>
</p>
<p>
	<label>������ʦ��</label>
	<input type="text"  name="Td01_xmxx.JLGCS" value="${td01_xmxx.jlgcs}" style="width:120px;"/>
</p>
<div style="height:0px;"></div>
<p>
	<label>�����Ա��</label>
	<input type="text"  name="Td01_xmxx.SJRY" value="${td01_xmxx.sjry}" style="width:150px;"/>
</p>
<p>
	<label>�������Ա��</label>
	<input type="text"  name="Td01_xmxx.LXGLY" value="${td01_xmxx.lxgly}" style="width:150px;"/>
</p>
<p>
	<label>ʩ��Ҫ���ڣ�</label>
	<input type="text"  name="Td01_xmxx.YQGQ" value="${td01_xmxx.yqgq}" style="width:85px;"/>
	<span>���죩</span>
</p>
<div style="height:0px;"></div>
<p>
	<label>ʵ�ʿ���ʱ�䣺</label>
	<input type="text"  name="Td01_xmxx.SJKGSJ" value="<fmt:formatDate value="${td01_xmxx.sjkgsj}" pattern="yyyy-MM-dd"/>" style="width:150px;"/>
</p>
<p>
	<label>ʵ�ʿ���ʱ�䣺</label>
	<input type="text"  name="Td01_xmxx.SJJGSJ" value="<fmt:formatDate value="${td01_xmxx.sjjgsj}" pattern="yyyy-MM-dd"/>" style="width:150px;"/>
</p>
<p>
	<label>����ʱ�䣺</label>
	<input type="text"  name="Td01_xmxx.YSSJ" value="<fmt:formatDate value="${td01_xmxx.yssj}" pattern="yyyy-MM-dd"/>" style="width:120px;"/>
</p>
<div style="height:0px;"></div>
<p>
	<label>��Դ¼���ˣ�</label>
	<input type="text"  name="Td01_xmxx.ZYLRY" value="${td01_xmxx.zylry}" style="width:150px;"/>
</p>
<p>
	<label>��Դ����Ա��</label>
	<input type="text"  name="Td01_xmxx.ZYGLY" value="${td01_xmxx.zygly}" style="width:150px;"/>
</p>
<p>
	<label>��Դȷ��ʱ�䣺</label>
	<input type="text"  name="Td01_xmxx.ZYQRSJ" value="<fmt:formatDate value="${td01_xmxx.zyqrsj}" pattern="yyyy-MM-dd"/>" style="width:120px;"/>
</p>
<div style="height:0px;"></div>
<p>
	<label>��Ŀ˵����</label>
	<textarea class="td-textarea" style="width:630px;height:60px;" type="text" name="Td01_xmxx.XMSM">${td01_xmxx.xmsm}</textarea>
</p>

<div class="divider"></div>
<p style="color:#ccc;font-weight:bold;width:700px;text-align:center;">��ͬ��Ϣ</p>
<div style="height:0px;"></div>	
<p>
	<label>��ƺ�ͬ��ţ�</label>
	<input type="text"  name="Td01_xmxx.SJHTBH" value="${td01_xmxx.sjhtbh}" style="width:150px;"/>
</p>
<p>
	<label>��</label>
	<input type="text"  name="Td01_xmxx.SJHTJE" value="${td01_xmxx.sjhtje}" style="width:115px;"/>
	<span>��Ԫ��</span>
</p>
<p>
	<label>ǩ�����ڣ�</label>
	<input type="text"  name="Td01_xmxx.SJHTQDRQ" value="<fmt:formatDate value="${td01_xmxx.sjhtqdrq}" pattern="yyyy-MM-dd"/>" style="width:120px;"/>
</p>
<div style="height:0px;"></div>	
<p>
	<label>ʩ����ͬ��ţ�</label>
	<input type="text"  name="Td01_xmxx.SGHTBH" value="${td01_xmxx.sghtbh}" style="width:150px;"/>
</p>
<p>
	<label>��</label>
	<input type="text"  name="Td01_xmxx.SGHTJE" value="${td01_xmxx.sghtje}" style="width:115px;"/>
	<span>��Ԫ��</span>
</p>
<p>
	<label>ǩ�����ڣ�</label>
	<input type="text"  name="Td01_xmxx.SGHTQDRQ" value="<fmt:formatDate value="${td01_xmxx.sghtqdrq}" pattern="yyyy-MM-dd"/>" style="width:120px;"/>
</p>
<div style="height:0px;"></div>	
<p>
	<label>�����ͬ��ţ�</label>
	<input type="text"  name="Td01_xmxx.JLHTBH" value="${td01_xmxx.jlhtbh}" style="width:150px;"/>
</p>
<p>
	<label>��</label>
	<input type="text"  name="Td01_xmxx.JLHTJE" value="${td01_xmxx.jlhtje}" style="width:115px;"/>
	<span>��Ԫ��</span>
</p>
<p>
	<label>ǩ�����ڣ�</label>
	<input type="text"  name="Td01_xmxx.JLHTQDRQ" value="<fmt:formatDate value="${td01_xmxx.jlhtqdrq}" pattern="yyyy-MM-dd"/>" style="width:120px;"/>
</p>

<div class="divider"></div>
<p style="color:#ccc;font-weight:bold;width:700px;text-align:center;">���������Ԫ��</p>
<div style="height:0px;"></div>	
<p style="width:105px;text-align:center;">����</p>
<p style="width:120px;text-align:center;">����</p>
<p style="width:120px;text-align:center;">����</p>
<p style="width:120px;text-align:center;">���</p>
<p style="width:120px;text-align:center;">�˼���</p>
<p style="width:120px;text-align:center;">�˼���</p>
<div style="height:0px;"></div>	
<p style="width:105px;text-align:center;">��������</p>
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
<p style="width:105px;text-align:center;">�չ�����</p>
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
<p style="width:105px;text-align:center;">���Ϸ�</p>
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
<p style="width:105px;text-align:center;">��е�Ǳ��</p>
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
<p style="width:105px;text-align:center;">������</p>
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
<p style="width:105px;text-align:center;">ʩ���ܷ���</p>
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
<p style="width:105px;text-align:center;">�����</p>
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
<p style="width:105px;text-align:center;">�����ܷ���</p>
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
<div style="text-align:left;color:blue;"><h3>&nbsp;&nbsp;��������б�</h3></div><div class="divider" style="height:1px;"></div>
<div style="width:780px;">
	<table class="table" width="100%">
		<thead>
			<tr>
				<th style="width: 30px;">���</th>
				<th style="width: 120px;">���̱��</th>
				<th >��������</th>
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
	<br/>
</div>