<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" /> 
<script type="text/javascript">
	$(function(){
		$("#submitButton",$.pdialog.getCurrent()).click(function(){
			$("#_tbjd",$.pdialog.getCurrent()).val(parseFloat($("#tbjd",$.pdialog.getCurrent()).val())/100);
			$("#form1",$.pdialog.getCurrent()).submit();
		});
	});
</script>
<div class="page">
	<div class="pageContent">
		<form id="form1" method="post" action="save.do" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
			<input type="hidden" name="tableInfomation" value="noFatherTable:com.rms.dataObjects.wxdw.Tf10_gzltb" />
			<input type="hidden" name="Tf10_gzltb.ID" value="${gzltb.id }" />
			<input type="hidden" name="_navTabId" value="jdfktx"/>
			
			<div class="pageFormContent" layoutH="53">
				<p>
					<label>工程编号：</label>
					<input type="text" readonly="readonly" style="width:120px;" value="${gcxx.xmbh }" />
				</p>
				<p>
					<label>工程名称：</label>
					<input type="text" readonly="readonly" style="width:120px;" value="${gcxx.xmmc }" />
				</p>
				<p>
					<label>填报日期：</label>
					<input type="text" readonly="readonly" name="Tf10_gzltb.TBRQ" style="width:120px;" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${empty gzltb ? now : gzltb.tbrq}" />" />
				</p>
				<p>
					<label>填报人员：</label>
					<input type="text" readonly="readonly" name="Tf10_gzltb.TBR" style="width:120px;" value="${user.name }" />
				</p>
				<p>
					<label>时间进度：</label>
					<input type="text" readonly="readonly" style="width:120px;" value="<fmt:formatNumber pattern="0.00%" value="${param.jhjd }"/>" />
					<input type="hidden" name="Tf10_gzltb.SJJD" style="width:120px;" value="<fmt:formatNumber pattern="0.0000" value="${param.jhjd }"/>" />
				</p>
				<p>
					<label>填报进度：</label>
					<input type="hidden" id="_tbjd" name="Tf10_gzltb.TBJD" style="width:120px;" class="number" value="${gzltb.tbjd }"/>
					<input type="text" id="tbjd" style="width:110px;" class="number" value="<fmt:formatNumber pattern="0.00" value="${gzltb.tbjd * 100 }" />"/>%
					<input type="hidden" name="Tf10_gzltb.PROJECT_ID" value="${gcxx.id }"/>
				</p>
				<p>
					<label>进度描述：</label>
					<textarea name="Tf10_gzltb.JDMS" style="width:346px;height:90px">${gzltb.jdms }</textarea>
				</p>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="submitButton">保 存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
		</div>
	</div>	