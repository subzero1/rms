<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" /> 
<script language="javascript">
	function upPic(wxry_id){
		$.pdialog.open("wxdw/upWxryHead.do?wxry_id="+wxry_id,'_upPic','上传头像',{width:600,height:380});
		var dialog = $("body").data('_upPic');
		setTimeout(function(){$.pdialog.switchDialog(dialog);}, 100);
	}
	function saveForm(){
		$("#wxyr_form",navTab.getCurrentPanel()).submit();
	}
</script>
<div class="page">
	<!-- 表单头 -->
	<div class="pageHeader">
		<div class="searchBar">
			<!-- 表单名称 -->
			<h1>
				外协人员信息
			</h1>
		</div>
	</div>
	<div class="pageContent">
	<div class="panelBar">
			<ul class="toolBar">
				<li>
				<a class="save" href="javascript:saveForm();" 
					title="外协人员信息保存"><span>保&nbsp;&nbsp;&nbsp;存</span>
				</a>
			</li>
				<li class="line">line</li>
			</ul>
		</div>
		<form method="post" action="save.do" id="wxyr_form" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<input type="hidden" name="tableInfomation" value="noFatherTable:com.rms.dataObjects.wxdw.Tf30_wxry" />
			<input type="hidden" name="Tf30_wxry.ID" value="${wxry.id}" />
			<input type="hidden" name="Tf30_wxry.WXDW_ID" value="${param.wxdw_id}" />
			<input type="hidden" name="_callbackType" value="closeCurrent" />
			<input type="hidden" name="_forwardUrl" value="wxdw/wxryList.do?wxdw_id=${param.wxdw_id}" />
			<input type="hidden" name="_navTabId" value="wxryList" />
			<div class="pageFormContent" layoutH="53">
				<p>
					<label>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</label>
					<input type="text" name="Tf30_wxry.NAME" style="width:165px;" value="${wxry.name }" class="required" />
				</p> 
				<div style="width:200px;height:130px;text-align:left;float:right;margin-right:500px;">
					<div style="width:150px;height:110px;border:dotted 2px black;">
						<c:choose>
							<c:when test="${not empty pic_id && pic_id != -1}">
								<img id="personal_head" src="download.do?slave_id=${pic_id}&radom=<%=new Random().nextInt()%>"/>
							</c:when>
							<c:otherwise>
								
							</c:otherwise>
						</c:choose>
					</div>
				</div> 
				<div class="divider"></div>
				<div style="height: 0px;"></div><p>
				<p>
					<label>移动电话：</label>
					<input type="text" name="Tf30_wxry.MOBILE" style="width:165px;" value="${wxry.mobile}" class="required" />
				</p>
				<p>
					<label>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</label>
					<input style="width: 30px" type="radio" name="Tf30_wxry.SEX" <c:if test="${wxry.sex == '男' || empty wxry.sex}">checked="checked"</c:if> value="男" />
					男
					<input style="width: 30px" type="radio" name="Tf30_wxry.SEX" <c:if test="${wxry.sex == '女' }">checked="checked"</c:if> value="女"/>
					女
				</p>
				
				<div style="height: 0px;"></div><p>
					<label>身份证号：</label>
					<input  type="text" name="Tf30_wxry.SFZ" style="width:165px;" value="${wxry.sfz}" />
				</p>
				<p>
					<label>状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：</label>
					<input type="radio" name="Tf30_wxry.STATUS" value="在职" <c:if test="${wxry.status=='在职' ||empty wxry }">checked</c:if> />
					在职
					<input type="radio" name="Tf30_wxry.STATUS" value="离职" <c:if test="${wxry.status=='离职' }">checked</c:if> />
					离职
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>概预算证：</label>
					<input type="text" name="Tf30_wxry.GYSZ" style="width:165px;" value="${wxry.gysz}" />
				</p>
				<p>
					<label>安全员证：</label>
					<input type="text" name="Tf30_wxry.AQYZ" style="width:165px;" value="${wxry.gysz }" />
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>监&nbsp;&nbsp;理&nbsp;&nbsp;证：</label>
					<input type="text" name="Tf30_wxry.JLZ" style="width:165px;" value="${wxry.jlz }" />
				</p>
				<p>
					<label>登&nbsp;&nbsp;高&nbsp;&nbsp;证：</label>
					<input type="text" name="Tf30_wxry.DGZ" style="width:165px;" value="${wxry.dgz}" />
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>电&nbsp;&nbsp;工&nbsp;&nbsp;证：</label>
					<input type="text" name="Tf30_wxry.EC" style="width:165px;" value="${wxry.ec }" />
				</p>
				<p>
					<label>本部/下挂单位：</label>
					<input type="text" name="Tf30_wxry.BX" style="width:165px;" value="${wxry.bx }" />
				</p>
				<p>
					<label>专&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业：</label>
					<input type="text" name="Tf30_wxry.MAJOR" style="width:165px;" value="${wxry.major }" />
				</p> 
				<div class="divider"></div>
				<div style="height:0px;"></div>
				<p>
					<label>劳动合同：</label>
					<select name="Tf30_wxry.CONTRACT" style="width:170px;" value="${wxry.contract}">
						<option value="">--------------------</option>
						<option value="0">否</option>
						<option value="1">是</option> 
					</select> 
				</p> 
				<p>
					<label>保&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;险：</label>
					<select name="Tf30_wxry.CONTRACT" style="width:170px;" value="${wxry.contract}">
						<option value="">---------------------</option>
						<option value="0">否</option>
						<option value="1">是</option> 
					</select> 
				</p> 
				<p>
					<label>保护用品：</label>
					
					<select name="Tf30_wxry.CONTRACT" style="width:170px;" value="${wxry.contract}">
						<option value="">--------------------</option>
						<option value="0">否</option>
						<option value="1">是</option> 
					</select> 
				</p>
				<div style="height:0px;"></div>
				
				<p>
					<label>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
					 <textarea rows="3" cols="45" name="Tf30_wxry.BZ" style="width:705px;">${wxry.bz }</textarea>
				</p>
				<div style="height:0px;"></div>
			<div class="formBar">
				<ul>
					<c:if test="${not empty wxry}">
						<li><div class="buttonActive"><div class="buttonContent"><button id="submitbutton" onclick="javascript:upPic(${wxry.id })">上传照片</button></div></div></li>
					</c:if>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div>
					</li>
				</ul>
			</div>
				 
			</div>
		</form>
		</div>
	</div>	