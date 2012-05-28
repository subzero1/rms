<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />

<script language="javascript">
function saveJfxx(){
	$("#jfxx_form",navTab.getCurrentPanel()).submit();
}
</script>

<div class="page">
	<!-- 表单头 -->
	<div class="pageHeader">
		<div class="searchBar">
			<!-- 表单名称 -->
			<h1>资源信息单</h1>
		</div>
	</div>
	
	<!-- 主操作按钮 -->
	<div class="panelBar">
		<ul class="toolBar">
		 	<li><a class="save"	href="javascript:saveJfxx();"><span>保 存</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	
	
	<div class="pageContent">
		<form id="mbk_form" action="save.do" method="post"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="tableInfomation"	value="noFatherTable:com.rms.dataObjects.mbk.TD21_MBK" />
			<input type="hidden" name="TD21_MBK.ID" value="${TD21_MBK.id}" />
			<input type="hidden" name="_callbackType" value="forward"/>
			<input type="hidden" name="_message" value="保存" />
			<input type="hidden" name="_forwardUrl" value="mbk/mbkEdit.do"/>
			<input type="hidden" name="_navTabId" value="mbkList"/>
			<input type="hidden" name="validate" value="${validate}"/> 
			<div class="pageFormContent">
				<p>
					<label>发文部门：</label>
					<input type="text" name="Td00_jfxx.DJBM" style="width:407px;" value="<c:out value="${Td00_jfxx.djbm}" default="${user.dept_name}"/>" readonly/>
				</p>
				<p>
					<label>资源编号：</label>
					<input readonly type="text" name="Td00_jfxx.DJRQ" style="width:120px;" readonly value="<c:choose><c:when test="${empty param.doc_id}"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm"/></c:when><c:otherwise><fmt:formatDate value="${Td00_jfxx.djrq}" pattern="yyyy-MM-dd HH:mm"/></c:otherwise></c:choose>"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label width="100">起 草 人：</label> 
					<input type="text" name="Td00_jfxx.DJRY" style="width:150px;" value="<c:out value="${Td00_jfxx.djry}" default="${user.name}"/>" readonly/>
				</p>
				<p>
					<label width="90">联系电话：</label>
					<input type="text" name="Td00_jfxx.LXDH" style="width:150px;" value="<c:out value="${Td00_jfxx.lxdh}" default="${user.mobile_tel}"/>"/>
				</p>
				<div class="divider"></div>
				<p>
					<label>资源名称：</label>
					<input type="text" id="master.dwz_devLooup.Td00_jfxx.JDMC" style="width:382px;" name="Td00_jfxx.JDMC" value="${Td00_jfxx.jdmc}" />
				</p>
				<p>
					<label>局点性质：</label>
					<input type="text" id="master.dwz_devLooup.Td00_jfxx.JDXZ" style="width:120px;" name="Td00_jfxx.JDXZ" value="${Td00_jfxx.jdxz}" readonly/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>坐落地点：</label>
					<input type="text" name="Td00_jfxx.ZLDD" style="width:150px;" value="${Td00_jfxx.zldd}"/>
				</p>
				<p>
					<label width="90">谈点周期：</label>
					<input type="text" id="master.dwz_devLooup.Td00_jfxx.SSQY" style="width:150px;" name="Td00_jfxx.SSQY" value="${Td00_jfxx.ssqy}" readonly/>
				</p>
				<p>
					<label>谈点部门：</label>
					<input type="text" name="Td00_jfxx.JWD" style="width:120px;" value="${Td00_jfxx.jwd}"/>
				</p>
				<div class="divider"></div>
				<p>
					<label>谈 点 人：</label>
					<input type="text" name="Td00_jfxx.JFMC" style="width:407px;" value="${Td00_jfxx.jfmc}"/>
				</p>
				<p>
					<label>联系电话：</label>
					<input type="text" name="Td00_jfxx.JSRQ" style="width:120px;" value="${Td00_jfxx.jsrq}"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>联系人：</label> 
					<input type="text" name="Td00_jfxx.JFMJ" style="width:150px;" value="${Td00_jfxx.jfmj}"/>
				</p>
				<p>
					<label>联系电话：</label>
					<input type="text" name="Td00_jfxx.JFSZLC" style="width:150px;" value="${Td00_jfxx.jfszlc}"/>
				</p>
				<p>
					<label>所属地区：</label>
					<input type="text" name="Td00_jfxx.JFYT" style="width:120px;" value="${Td00_jfxx.jfyt}"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>经    度</label>
					<input type="text" name="Td00_jfxx.GLRY" style="width:150px;" value="${Td00_jfxx.glry}"/>
				</p>
				<p>
					<label>纬    度：</label>
					<input type="text" name="Td00_jfxx.GLYLXDH" style="width:150px;" value="${Td00_jfxx.glylxdh}"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>建设方式：</label> 
					<input type="text" name="Td00_jfxx.JFMJ" style="width:150px;" value="${Td00_jfxx.jfmj}"/>
				</p>
				<p>
					<label>是否共建：</label>
					<input type="text" name="Td00_jfxx.JFSZLC" style="width:150px;" value="${Td00_jfxx.jfszlc}"/>
				</p>
				<p>
					<label>谈点协调费：</label>
					<input type="text" name="Td00_jfxx.JFYT" style="width:120px;" value="${Td00_jfxx.jfyt}"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>签约时间：</label> 
					<input type="text" name="Td00_jfxx.JFMJ" style="width:150px;" value="${Td00_jfxx.jfmj}"/>
				</p>
				<p>
					<label>租    金：</label>
					<input type="text" name="Td00_jfxx.JFSZLC" style="width:150px;" value="${Td00_jfxx.jfszlc}"/>
				</p>
				<p>
					<label>年    限：</label>
					<input type="text" name="Td00_jfxx.JFYT" style="width:120px;" value="${Td00_jfxx.jfyt}"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>合同编号：</label> 
					<input type="text" name="Td00_jfxx.JFMJ" style="width:150px;" value="${Td00_jfxx.jfmj}"/>
				</p>
				<p>
					<label>业主姓名：</label>
					<input type="text" name="Td00_jfxx.JFSZLC" style="width:150px;" value="${Td00_jfxx.jfszlc}"/>
				</p>
				<p>
					<label>业主电话：</label>
					<input type="text" name="Td00_jfxx.JFYT" style="width:120px;" value="${Td00_jfxx.jfyt}"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>是否付首款：</label> 
					<input type="text" name="Td00_jfxx.JFMJ" style="width:150px;" value="${Td00_jfxx.jfmj}"/>
				</p>
				<p>
					<label>建设等级：</label>
					<input type="text" name="Td00_jfxx.JFSZLC" style="width:150px;" value="${Td00_jfxx.jfszlc}"/>
				</p>
				<p>
					<label>资源状态：</label>
					<input type="text" name="Td00_jfxx.JFYT" style="width:120px;" value="${Td00_jfxx.jfyt}"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>说明：</label>
					<textarea class="td-textarea" style="width:630px;height:40px;" type="text" name="Td00_jfxx.BZ"/>${Td00_jfxx.bz}</textarea>
				</p>
		</div>
		</form>
	</div>
</div>
