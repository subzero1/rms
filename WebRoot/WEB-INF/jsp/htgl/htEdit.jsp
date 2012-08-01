<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" /> 

<div class="page">
	<div class="pageContent">
		<form method="post" action="save.do" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
			<input type="hidden" name="tableInfomation" value="noFatherTable:com.rms.dataObjects.form.Td01_xmxx" />
			<input type="hidden" name="_callbackType" value=""/>
			<input type="hidden" name="_message" value="提交数据保存" />
			<input type="hidden" name="_forwardUrl" value=""/>
			<input type="hidden" name="_navTabId" value=""/>
			<input type="hidden" name="Td01_xmxx.ID" value="${td01.id}" />
			<div class="pageFormContent" layoutH="53">
				<c:choose>				
					<c:when test="${param.act=='ht'}">
						<p>
							<label>${htlbmc }合同编号：</label>
							<input  type="text" name="Td01_xmxx.${fn:toUpperCase(htlb) }HTBH" style="width:240px;" value="${td01.htbh }" class="required"/>
						</p>
						<div style="height:0px;"></div>
						<p>
							<label>${htlbmc }签订日期：</label>
							<input type="text" name="Td01_xmxx.${fn:toUpperCase(htlb) }HTQDRQ" style="width:240px;" value="${td01.htqdrq }" class="required date" />
						</p>
						<div style="height:0px;"></div>
						<p>
							<label>${htlbmc }合同金额：</label>
							<input type="text" name="Td01_xmxx.${fn:toUpperCase(htlb) }HTJE" style="width:240px;" value="${td01.htje }" class="required number" />
						</p>
					</c:when>
					<c:when test="${param.act=='ss'}">
						<p>
							<label>送审金额：</label>
							<input type="text" name="Td01_xmxx.SS_JE" style="width:240px;" value="${td01.ss_je }"  class="required number"/>
						</p>
						<div style="height:0px;"></div>
						<p>
							<label>送审日期：</label>
							<input type="text" name="Td01_xmxx.SSRQ" style="width:240px;" value="${td01.ssrq }"  class="required date"/>
						</p>
					</c:when>
					<c:otherwise>
						<p>
							<label>审定金额：</label>
							<input type="text" name="Td01_xmxx.SD_JE" style="width:240px;" value="${td01.sd_je }"  class="required number"/>
						</p>
						<div style="height:0px;"></div>
						<p>
							<label>审定日期：</label>
							<input type="text" name="Td01_xmxx.SJJSSJ" style="width:240px;" value="${td01.sjjssj }"  class="required date"/>
						</p>
						<div style="height:0px;"></div>
						<p>
							<label>开票日期：</label>
							<input type="text" name="Td01_xmxx.KPRQ" style="width:240px;" value="${td01.kprq }"  class="required date"/>
						</p>
						<div style="height:0px;"></div>
						<p>
							<label>挂账日期：</label>
							<input type="text" name="Td01_xmxx.GZRQ" style="width:240px;" value="${td01.gzrq }"  class="required date"/>
						</p>
					</c:otherwise>
				</c:choose>				
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button id="submitbutton" type="submit">提 交</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
		</div>
	</div>	