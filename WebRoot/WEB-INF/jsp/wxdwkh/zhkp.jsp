<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />
<script language="javascript">
</script>
<div class="page">

	<!-- 表单头 -->
	<div class="pageHeader">
		<div class="searchBar">
			<!-- 表单名称 -->
			<h1>综合考评</h1>
			
		</div>
	</div>
	
	<!-- 主操作按钮 -->
	<div class="panelBar">
		<ul class="toolBar">
		</ul>
	</div>
	
	
	<div class="pageContent" layouth="48">
		<form id="mbk_form" action="save.do" method="post"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div class="pageFormContent">
				<p>
					<label>单位名称：</label>
					<input readonly="readonly" type="text" name="Td21_mbk.ZYMC" style="width:386px;" value="${tf18.wxdw_mc}" />
				</p>
				<p>
					<label>类别：</label>
					<input readonly="readonly" type="text" name="Td21_mbk.ZYBH" style="width:140px;" value="${tf18.wxdw_lb}"/>
				</p>
				<div style="height:0px;"></div>
				
				
				<p>
					<label>作业类别：</label>
					<input readonly="readonly" type="text" name="Td21_mbk.ZYMC" style="width:386px;" value="${tf18.zylb}" />
				</p>
				<p>
					<label>综合得分：</label>
					<input readonly="readonly" type="text" name="Td21_mbk.ZYBH" style="width:140px;" value="<fmt:formatNumber pattern="0.00" value="${tf18.zhdf}"/>"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>作业区域：</label>
					<input readonly="readonly" type="text" name="Td21_mbk.ZYMC" style="width:386px;" value="${tf18.zyqy}" />
				</p>
				<p>
					<label><a href="wxdwkh/khkf.do?id=${tf18.id }" style="color:blue" title="本单位日常考核" target="navTab" rel="khkf">考核扣分</a>：</label>
					<input readonly="readonly" type="text" name="Td21_mbk.ZYBH" style="width:140px;" value="<fmt:formatNumber pattern="0.00" value="${tf18.cscore}"/>"/>
				</p>
				<div style="height:0px;"></div>
				<div style="text-align:left;color:blue;"><h3>&nbsp;&nbsp;任务情况</h3></div><div class="divider" style="height:1px;"></div>
				<p>
					<label><a href="wxdwkh/xmList.do?id=${tf18.id }" style="color:blue" title="本单位受理项目" target="navTab" rel="slxm">项目数</a>：</label>
					<input readonly="readonly" type="text" name="Td21_mbk.ZLDD" style="width:140px;" value="<fmt:formatNumber pattern="0" value="${tf18.xms}"/>"/>
				</p> 
				<p>
					<label>合同额：</label>
					<input readonly="readonly" type="text" name="Td21_mbk.JD" style="width:140px;" value="<fmt:formatNumber pattern="0.00" value="${tf18.hte}"/>"/>
				</p>
				<p>
					<label>结算额：</label>
					<input readonly="readonly" type="text" name="Td21_mbk.WD" style="width:140px;" value="<fmt:formatNumber pattern="0.00" value="${tf18.jse}"/>"/>
				</p>
				<div style="height:0px;"></div>
				
				<p>
					<label>完工率：</label>
					<input readonly="readonly" type="text" name="Td21_mbk.ZLDD" style="width:140px;" value="<fmt:formatNumber pattern="0.00%" value="${tf18.wgl}"/>"/>
				</p>
				<p>
					<label>超期率：</label>
					<input readonly="readonly" type="text" name="Td21_mbk.JD" style="width:140px;" value="<fmt:formatNumber pattern="0.00%" value="${tf18.cql}"/>"/>
				</p>
				<p>
					<label>决算率：</label>
					<input readonly="readonly" type="text" name="Td21_mbk.WD" style="width:140px;" value="<fmt:formatNumber pattern="0.00%" value="${tf18.jsl}"/>"/>
				</p>
				<div style="height:0px;"></div>
		</div>
		</form>
		
		
	</div>
</div>
