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
					<label>资源编号：</label>
					<input readonly type="text" name="Td21_mbk.ZYBH" style="width:150px;" readonly value="${Td21_mbk.zybh}"/>
				</p>
				<p>
					<label>资源名称：</label>
					<input type="text" id="master.dwz_devLooup.Td21_mbk.JYMC" style="width:380px;" name="Td21_mbk.JDMC" value="${Td21_mbk.zymc}" />
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>建设性质：</label>
					<netsky:htmlSelect name="Td21_mbk.JSXZ" style="width:156px;" objectForOption="jsxzList" valueForOption="name" showForOption="name" value="${Td21_mbk.jsxz}" extend="" extendPrefix="true" />
				</p>
				<p>
					<label width="90"> 分    类：</label>
					<input type="text" name="Td21_mbk.FL" style="width:150px;" value="${Td21_mbk.fl}"/>
				</p>
				<p>
					<label>所属地区：</label>
					<netsky:htmlSelect name="Td21_mbk.SSDQ" style="width:126px;" objectForOption="dqList" valueForOption="name" showForOption="name" value="${Td21_mbk.ssdq}" extend="" extendPrefix="true" />
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>坐落地点：</label>
					<input type="text" name="Td21_mbk.ZLDD" style="width:150px;" value="${Td21_mbk.zldd}"/>
				</p> 
				<p>
					<label>经    度：</label>
					<input type="text" name="Td21_mbk.JD" style="width:150px;" value="${Td21_mbk.jd}"/>
				</p>
				<p>
					<label>纬    度：</label>
					<input type="text" name="Td21_mbk.WD" style="width:120px;" value="${Td21_mbk.wd}"/>
				</p>
				<div class="divider"></div>
				<p>
					<label>谈点部门：</label>
					<input type="text" name="Td21_mbk.TDBM" style="width:150px;" value="${Td21_mbk.tdbm}"/>
				</p>
				<p>
					<label>谈 点 人：</label>
					<input type="text" name="Td21_mbk.TDR" style="width:150px;" value="${Td21_mbk.tdr}"/>
				</p>
				<p>
					<label>联系电话：</label>
					<input type="text" name="Td21_mbk.TDRDH" style="width:120px;" value="${Td21_mbk.tdrdh}"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label width="90">谈点周期：</label>
					<input type="text" name="Td21_mbk.TDZQ" style="width:150px;" value="${Td21_mbk.tdzq}"/>
				</p>
				<p>
					<label>联系人：</label> 
					<input type="text" name="Td21_mbk.LXR" style="width:150px;" value="${Td21_mbk.lxr}"/>
				</p>
				<p>
					<label>联系电话：</label>
					<input type="text" name="Td21_mbk.LXRDH" style="width:120px;" value="${Td21_mbk.lxrdh}"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>建设方式：</label> 
					<netsky:htmlSelect name="Td21_mbk.JSFS" style="width:156px;" objectForOption="jsfsList" valueForOption="name" showForOption="name" value="${Td21_mbk.jsfs}" extend="" extendPrefix="true" />
				</p>
				<p>
					<label>是否共建：</label>
					<input type="text" name="Td21_mbk.SFGJ" style="width:150px;" value="${Td21_mbk.sfgj}"/>
				</p>
				<p>
					<label>谈点协调费：</label>
					<input type="text" name="Td21_mbk.TDXTF" style="width:120px;" value="${Td21_mbk.tdxtf}"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>签约时间：</label> 
					<input type="text" name="Td21_mbk.QYSJ" style="width:150px;" value="${Td21_mbk.qysj}"/>
				</p>
				<p>
					<label>租    金：</label>
					<input type="text" name="Td21_mbk.ZJ" style="width:150px;" value="${Td21_mbk.zj}"/>
				</p>
				<p>
					<label>年    限：</label>
					<input type="text" name="Td21_mbk.NX" style="width:120px;" value="${Td21_mbk.nx}"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>合同编号：</label> 
					<input type="text" name="Td21_mbk.HTBH" style="width:150px;" value="${Td21_mbk.htbh}"/>
				</p>
				<p>
					<label>业主姓名：</label>
					<input type="text" name="Td21_mbk.YZXM" style="width:150px;" value="${Td21_mbk.yzxm}"/>
				</p>
				<p>
					<label>业主电话：</label>
					<input type="text" name="Td21_mbk.YZDH" style="width:120px;" value="${Td21_mbk.yzdh}"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>是否付首款：</label> 
					<input type="text" name="Td21_mbk.SFFSK" style="width:150px;" value="${Td21_mbk.sffsk}"/>
				</p>
				<p>
					<label>建设等级：</label>
					<input type="text" name="Td21_mbk.JSDJ" style="width:150px;" value="${Td21_mbk.jsdj}"/>
				</p>
				<p>
					<label>资源状态：</label>
					<input type="text" name="Td21_mbk.ZYZT" style="width:120px;" value="${Td21_mbk.zyzt}"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>说    明：</label>
					<textarea class="td-textarea" style="width:630px;height:40px;" type="text" name="Td21_mbk.BZ"/>${Td21_mbk.bz}</textarea>
				</p>
				<div class="divider"></div>
				<p>
					<label>施工单位：</label> 
					<input type="text" name="Td21_mbk.SGDW" style="width:150px;" value="${Td21_mbk.sgdw}"/>
				</p>
				<p>
					<label>施工管理员：</label>
					<input type="text" name="Td21_mbk.SGGLY" style="width:150px;" value="${Td21_mbk.sggly}"/>
				</p>
				<p>
					<label>提交施工管理员时间：</label>
					<input type="text" name="Td21_mbk.TJSJ" style="width:120px;" value="${Td21_mbk.tjsj}"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>开工时间：</label> 
					<input type="text" name="Td21_mbk.KGSJ" style="width:150px;" value="${Td21_mbk.kgsj}"/>
				</p>
				<p>
					<label>完工时间：</label>
					<input type="text" name="Td21_mbk.WGSJ" style="width:150px;" value="${Td21_mbk.wgsj}"/>
				</p>
				<p>
					<label>工程分配说明：</label>
					<input type="text" name="Td21_mbk.GCFPSM" style="width:120px;" value="${Td21_mbk.gcfpsm}"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>存在问题：</label>
					<textarea class="td-textarea" style="width:630px;height:40px;" type="text" name="Td21_mbk.CZWT"/>${Td21_mbk.czwt}</textarea>
				</p>
		</div>
		</form>
		
		<div style="text-align:left;color:blue;"><h3>&nbsp;&nbsp;目标库流转记录</h3></div><div class="divider" style="height:1px;"></div>
		
		
	</div>
</div>
