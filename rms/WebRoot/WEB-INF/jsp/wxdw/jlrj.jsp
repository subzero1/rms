<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" /> 
<script type="text/javascript">
	$(function(){
	});
</script>
<div class="page">
	<div class="pageContent">
		<form method="post" action="save.do" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
			<input type="hidden" name="tableInfomation" value="noFatherTable:com.rms.dataObjects.wxdw.Tf14_jlrj" />
			<input type="hidden" name="Tf14_jlrj.ID" value="${jlrj.id }" />
			<input type="hidden" name="Tf14_jlrj.PROJECT_ID" value="${gcxx.id }"/>
			<input type="hidden" name="_navTabId" value="jlgcList"/>
			
			<div class="pageFormContent" layoutH="53">
				<p>
					<label>工程编号：</label>
					<input type="text" readonly="readonly" style="width:120px;" value="${gcxx.gcbh }" />
				</p>
				<p>
					<label>工程名称：</label>
					<input type="text" readonly="readonly" style="width:120px;" value="${gcxx.gcmc }" />
				</p>
				<p>
					<label>填报日期：</label>
					<input type="text" readonly="readonly" name="Tf14_jlrj.CREATE_DATE" style="width:120px;" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${empty jlrj ? now : jlrj.create_date}" />" />
				</p>
				<p>
					<label>填报人员：</label>
					<input type="hidden" name="Tf14_jlrj.USER_ID" value="${user.id }" />
					<input type="text" readonly="readonly" style="width:120px;" value="${user.name }" />
				</p>
				<p>
					<label>工程状态：</label>
					<input type="text" readonly="readonly" name="Tf14_jlrj.GCZT" style="width:120px;" value="${gcxx.gczt }"/>
				</p>
				<div style="width:100%;height:0px;"></div>
				<p>
					<label>现场描述：</label>
					<textarea name="Tf14_jlrj.XCMS" style="width:346px;height:90px">${jlrj.xcms }</textarea>
				</p>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保 存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
		</div>
	</div>	