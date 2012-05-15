<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="now" class="java.util.Date" />

<div class="page">
	<div class="pageContent">
	<form id="form1" name="form1" class="pageForm required-validate" action="save.do" method="post" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="tableInfomation" value="noFatherTable:com.jfms.dataObjects.info.Td11_jfpmsq"/>
		<input type="hidden" name="slaveFatherTables" value="Td11_jfpmsq,ID,DOC_ID"/>
		<input type="hidden" name="_callbackType" value=""/>
		<input type="hidden" name="_forwardUrl" value=""/>
		<input type="hidden" name="_navTabId" value="_current"/>
		<input type="hidden" name="Td11_jfpmsq.ID" value="${param.doc_id }" />

		<div class="pageFormContent" layoutH="50">
			<table border='0' width='100%' cellspacing='0' cellpadding='0' style='border-collapse:collapse;' class='alert-table'>
				<tr>
					<td><input type="text" name="Td11_jfpmsq.SJSX" value="" /></td>
				</tr>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">确认修改</button></div></div></li>	
				<li><div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div></li>
			</ul>
		</div>
	</form>
	</div>
</div>
