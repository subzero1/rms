<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" /> 
<script type="text/javascript">
	$(function(){
		$("#sgqr",$.pdialog.getCurrent()).click(function(){
			alertMsg.confirm("确认后该日常考核记录将不可修改！要手工确认吗？",{
				okCall:function(){
					$("#qrsj",$.pdialog.getCurrent()).val("<fmt:formatDate pattern='yyyy-MM-dd' value='${now}'/>");
					$("#form1",$.pdialog.getCurrent()).submit();
				}
			});		
		});
	});
</script>
<div class="page">
	<div class="pageContent">
		<form id="form1" method="post" action="save.do" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
			<input type="hidden" name="tableInfomation" value="noFatherTable:com.rms.dataObjects.wxdw.Tf17_rckh" />
			<input type="hidden" name="Tf17_rckh.ID" value="${tf17.id}" />
			<input type="hidden" name="_navTabId" value="rckhList"/>
			
			<div class="pageFormContent" layoutH="53">
				<p>
					<label>单位名称：</label>
					<input readonly="readonly" id="tf17Org.WXDW_MC" class="required" type="text" name="Tf17_rckh.WXDW_MC" style="width:<c:if test="${param.canedit == 'true' }">215</c:if><c:if test="${param.canedit == 'false' }">240</c:if>px;" value="${tf17.wxdw_mc }"/>
					<c:if test="${param.canedit == 'true' }">
					<a class="btnLook" href="wxdwkh/selectWxdw.do" lookupGroup="tf17Org" width="600" height="380">查找带回</a>
					</c:if>
					<input type="hidden" name="Tf17_rckh.WXDW_ID" id="tf17Org.WXDW_ID" value="${tf17.wxdw_id }"/>
					<input type="hidden" name="Tf17_rckh.WXDW_LB" id="tf17Org.WXDW_LB" value="${tf17.wxdw_lb }"/>
					<input type="hidden" name="Tf17_rckh.QRSJ" id="qrsj" />
				</p>
				<p>
					<label>考核时间：</label>
					<input type="text" class="required date" pattern="yyyy-MM-dd" name="Tf17_rckh.KHSJ" style="width:105px;" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${not empty tf17.khsj ? tf17.khsj : now }"/>" />
				</p>
				<p>
					<label>考核类别：</label>
					<netsky:htmlSelect name="Tf17_rckh.KHLB" objectForOption="khlbList" style="width:111px" valueForOption="name" showForOption="name" value="${tf17.khlb }"/>
				</p>
				<p>
					<label>考核人员：</label>
					<input class="required" readonly="readonly" type="text" name="Tf17_rckh.KHRY_NAME" style="width:240px;" value="${not empty tf17.khry_name ? tf17.khry_name : user.name }" />
					<input type="hidden" name="Tf17_rckh.KHRY_ID" style="width:150px;" value="${not empty tf17.khry_id ? tf17.khry_id : user.id }" />
				</p>
				<p>
					<label>罚款金额：</label>
					<input class="required number" type="text" name="Tf17_rckh.FKJE" style="width:105px;" value="<fmt:formatNumber pattern="0.00" value="${not empty tf17.fkje ? tf17.fkje : 0 }"/>" />
				</p>
				<p>
					<label>加扣分值：</label>
					<input class="required number" type="text" name="Tf17_rckh.JKFZ" style="width:105px;" value="<fmt:formatNumber pattern="0.00" value="${not empty tf17.jkfz ? tf17.jkfz : 0 }"/>" />
				</p>
				<p>
					<label>考核原因：</label>
					<textarea class="required" name="Tf17_rckh.KHYY" style="width:662px;height:145px;">${tf17.khyy }</textarea>
				</p>
				<p>
					<label>考核结果：</label>
					<textarea name="Tf17_rckh.KHJG" style="width:662px;height:145px;">${tf17.khjg }</textarea>
				</p>
			</div>
			<div class="formBar">
				<ul>
				<c:if test="${param.canedit == 'true' }">
				<c:if test="${tf17.fkje > 0 }">
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="sgqr">手工确认</button></div></div></li>
				</c:if>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保 存</button></div></div></li>
				</c:if>	
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
		</div>
	</div>	