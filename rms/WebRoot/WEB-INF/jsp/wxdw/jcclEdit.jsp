<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" /> 
<style>
.width160 {
	width: 130px
}
</style>
<script type="text/javascript">
	$(function(){
	});
</script>
<div class="page">
	<div class="pageContent">
		<form method="post" action="save.do" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
			<input type="hidden" name="tableInfomation" value="noFatherTable:com.rms.dataObjects.wxdw.Tf06_clb" />
			<input type="hidden" name="Tf06_clb.ID" value="${tf06.id}" />
			<input type="hidden" name="_navTabId" value="jcclList"/>
			
			<div class="pageFormContent" layoutH="53">
				<p>
					<label>名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</label>
					<input class="required" type="text" name="Tf06_clb.CLMC" style="width:120px;" value="${tf06.clmc }"/>
				</p>
				<p>
					<label>单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：</label>
					<input type="text" name="Tf06_clb.DW" style="width:120px;" value="${tf06.dw }" />
				</p>
				<p>
					<label>规&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格：</label>
					<input type="text" name="Tf06_clb.GG" style="width:120px;" value="${tf06.gg }" />
				</p>
				<p>
					<label>型&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</label>
					<input type="text" name="Tf06_clb.XH" style="width:120px;" value="${tf06.xh }" />
				</p>
				<p>
					<label>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</label>
					<netsky:htmlSelect extend="" extendPrefix="true" name="Tf06_clb.CLLX" objectForOption="cllxList" style="width:126px" valueForOption="name" showForOption="name" value="${tf06.cllx }"/>
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