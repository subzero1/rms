<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>


<div class="page">
	<div class="pageContent">
		<form method="post" action="save.do" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
			<input type="hidden" name="tableInfomation"	value="noFatherTable:com.rms.dataObjects.info.Td13_jfsbmx" />
			<input type="hidden" name="Td13_jfsbmx.ID" value="${td13.id}" />
			<input type="hidden" name="Td13_jfsbmx.JFXX_ID" value="<c:out value="${td13.jfxx_id}" default="${param.jfxx_id }"/>" />
			<input type="hidden" name="Td13_jfsbmx.PROJECT_ID" value="<c:out value="${td13.project_id}" default="${param.project_id }"/>" />
			<input type="hidden" name="Td13_jfsbmx.SQD_ID" value="<c:out value="${td13.sqd_id}" default="${param.doc_id }"/>" />
			<input type="hidden" name="_callbackType" value="forward"/>
			<input type="hidden" name="_forwardUrl" value="pmsqSbmx.do?module_id=${param.module_id }&project_id=${param.project_id }&doc_id=${param.doc_id }&jfxx_id=${param.jfxx_id }&node_id=${param.node_id }&limit=${param.limit }"/>
			<input type="hidden" name="_message" value="设备信息保存" />
			<div class="pageFormContent" layoutH="56">
				<p>
					<label>设备名称：</label>
					<input class="required" name="Td13_jfsbmx.SBMC" type="text" style="width:420px" value="${td13.sbmc }"  maxlength="100"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>设备型号：</label>
					<input class="required" name="Td13_jfsbmx.SBXH" type="text" style="width:155px" value="${td13.sbxh }"  maxlength="100"/>
				</p>
				<p>
					<label>设备规格：</label>
					<input class="required" name="Td13_jfsbmx.SBGG" type="text" style="width:155px" value="${td13.sbgg }"  maxlength="100"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>单 位：</label>
					<input class="required" name="Td13_jfsbmx.DW" type="text" style="width:155px" value="${td13.dw }"  maxlength="10"/>
				</p>
				<p>
					<label>数 量：</label>
					<input class="required number" name="Td13_jfsbmx.SL" type="text" style="width:155px" value="${td13.sl }"  maxlength="10"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>机架尺寸：</label>
					<input class="" name="Td13_jfsbmx.JJCC" type="text" style="width:155px" value="${td13.jjcc }"  maxlength="25"/>
				</p>
				<p>
					<label>厂 家：</label>
					<input class="required" name="Td13_jfsbmx.CJ" type="text" style="width:155px" value="${td13.cj }"  maxlength="100"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>供电方式：</label>
					<netsky:htmlSelect name="Td13_jfsbmx.GDFS" objectForOption="gdfsList" style="width:160px" valueForOption="name" showForOption="name" value="${td13.gdfs}"/>
				</p>
				<p>
					<label>所属专业：</label>
					<netsky:htmlSelect name="Td13_jfsbmx.SSZY" objectForOption="zyList" style="width:160px" valueForOption="name" showForOption="name" value="${td13.sszy}" htmlClass="td-select"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>安装位置：</label>
					<input class="" name="Td13_jfsbmx.AZWZ" type="text"  style="width:155px" value="${td13.azwz }"  maxlength="100"/>
				</p>
				<p>
					<label>建设性质：</label>
					<netsky:htmlSelect name="Td13_jfsbmx.JSXZ" objectForOption="jsxzList" style="width:160px" valueForOption="name" showForOption="name" value="${td13.jsxz}" htmlClass="td-select"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>安装日期：</label>
					<input class="date" name="Td13_jfsbmx.AZRQ" type="text"  style="width:155px" value="<fmt:formatDate value="${td13.azrq }" pattern="yyyy-MM-dd" />"  maxlength="150"/>
				</p>
				<p>
					<label>施工单位：</label>
					<input class="" name="Td13_jfsbmx.SGDW" type="text"  style="width:155px" value="${td13.sgdw }"  maxlength="100"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>负责人：</label>
					<input class="" name="Td13_jfsbmx.FZR" type="text"  style="width:155px" value="${td13.fzr }"  maxlength="10"/>
				</p>
				<p>
					<label>联系电话：</label>
					<input class="" name="Td13_jfsbmx.LXDH" type="text"  style="width:155px" value="${td13.lxdh }"  maxlength="50"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>备 注：</label>
					<textarea class="td-textarea" style="height:80px;width:420px;" type="text" name="Td13_jfsbmx.BZ">${td13.bz}</textarea>
				</p>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit" >保 存</button></div></div></li>				
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" onclick="javascript:$.pdialog.reload('pmsqSbmx.do?module_id=${param.module_id }&project_id=${param.project_id }&doc_id=${param.doc_id }&jfxx_id=${param.jfxx_id}&node_id=${param.node_id }&limit=${param.limit }');">返 回</button></div></div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>