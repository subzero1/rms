<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<script type="text/javascript">
	$(function(){
		if ("${param.errormsg}"=="tdnotfound"){
		alertMsg.info("工程或项目未找到！");
		} else if ("${param.errormsg}"=="tfnotfound"){
			alertMsg.info("未找到符合的施工单位！");
		}
	}); 
	function bringBack(sgdw){
			var sdpgyy = $("#sdpgyy",$.pdialog.getCurrent()).val();
			if (sgdw == "${zdxp.mc}"){
				sdpgyy = "";
			} 
			$.bringBack({'SGDW':sgdw, 'SDPGYY':sdpgyy});
	} 
	function all_company(param0,param1,param2){
	   	 $.pdialog.closeCurrent(); 
		 $.pdialog.open('sgpd/sgpfCompany.do?sys_wxdw_id='+param0
		 +'&project_id='+param1+"&module_id="+param2,
		 'sgdw','施工单位',{width:700,height:380});
	} 
	function bringPgsp(param0,param1,param2,param3){
		var url="form/pgsp.do?";
		var $admin=$("#admin",$.pdialog.getCurrent()); 
		url+="sys_wxdw_id="+param0;
		url+="&man_wxdw_id="+param1;
		url+="&project_id="+param2;
		url+="&module_id="+param3; 
		$.pdialog.closeCurrent();
		if($admin.val()){
			$.pdialog.closeCurrent();
		}else{
			navTab.openTab('pgsp', url, {title:'手动选派原因'});
		}
		
	}
</script>

<div class="pageHeader">
	<div class="searchBar">
		<table class="searchContent"> 
		<tr><td><input type="hidden" id="admin" value="${admin }"/></td></tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="all_company('${zdxp.id}','${project_id }','112')">所有单位</button></div></div></li>
			</ul>
		</div>
	</div>
</div>
<div class="pageContent">
	<table class="table" layoutH="118" targettype="dialog" width="100%">
		<thead>
			<tr>
				<th width="200">单位名称</th>
				<th width="60">决算数</th>
				<th width="70">项目总数</th>
				<th width="60">决算率</th>
				<th width="80">决算率排名</th>
				<th width="70">综合得分</th>
				<th width="70">综合得分</th>
				<th width="70">计划占比</th>
				<th width="70">实际占比</th>
				<th width="60">偏差率</th>
				<th width="70">偏差档级</th>
				<th width="80">在建项目数</th>
				<th width="80">最大项目数</th>
				<th width="60">查找带回</th>
			</tr>
		</thead>				
		<tbody>
			<c:forEach items="${allList }" var="o">
			<tr>
				<td title="${o[0].mc }<c:if test="${o[0].mc == zdxp.mc}">（系统选择）</c:if>" style="<c:if test="${o[0].mc == zdxp.mc}">color:red</c:if>" >${o[0].mc }<c:if test="${o[0].mc == zdxp.mc}">（系统选择）</c:if></td>
				<td style="<c:if test="${o[0].mc == zdxp.mc}">color:red</c:if>"><fmt:formatNumber pattern="0" value="${o[11] }"/></td>
				<td style="<c:if test="${o[0].mc == zdxp.mc}">color:red</c:if>"><fmt:formatNumber pattern="0" value="${o[12] }"/></td>
				<td style="<c:if test="${o[0].mc == zdxp.mc}">color:red</c:if>"><fmt:formatNumber pattern="0.00" value="${o[3] }"/>%</td>
				<td style="<c:if test="${o[0].mc == zdxp.mc}">color:red</c:if>"><fmt:formatNumber pattern="0" value="${o[5]}"/></td>
				<td style="<c:if test="${o[0].mc == zdxp.mc}">color:red</c:if>"><fmt:formatNumber pattern="0.00" value="${o[2] }"/></td>
				<td style="<c:if test="${o[0].mc == zdxp.mc}">color:red</c:if>"><fmt:formatNumber pattern="0" value="${o[4] }"/></td>
				<td style="<c:if test="${o[0].mc == zdxp.mc}">color:red</c:if>"><fmt:formatNumber pattern="0.00" value="${o[6] }"/>%</td>
				<td style="<c:if test="${o[0].mc == zdxp.mc}">color:red</c:if>"><fmt:formatNumber pattern="0.00" value="${o[7] }"/>%</td>
				<td style="<c:if test="${o[0].mc == zdxp.mc}">color:red</c:if>"><fmt:formatNumber pattern="0.00" value="${o[8] }"/>%</td>
				<td style="<c:if test="${o[0].mc == zdxp.mc}">color:red</c:if>"><fmt:formatNumber pattern="0" value="${o[9] }"/></td>
				<td style="<c:if test="${o[0].mc == zdxp.mc}">color:red</c:if>"><fmt:formatNumber pattern="0" value="${o[13] }"/></td>
				<td style="<c:if test="${o[0].mc == zdxp.mc}">color:red</c:if>"><fmt:formatNumber pattern="0" value="${o[14] }"/></td>
				<td <c:if test="${o[0].mc != zdxp.mc}"> onclick="bringPgsp('${zdxp.id}'  ,'${o[0].id }','${project_id }','112')" </c:if>>
						<a class="btnSelect" href="javascript:bringBack('${o[0].mc }')" title="查找带回"></a>
				</td>
			</tr>
			</c:forEach>
			<tr><td colspan="14" ></td></tr>
			<tr><td colspan="14" ><b>决算率:</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;决算项目数/项目总数*100%&nbsp;&nbsp;</td></tr>
			<tr><td colspan="14" ><b>偏差率:</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(计划占比-实际占比)/计划占比&nbsp;&nbsp;</td></tr>
			<tr><td colspan="14" ><b>偏差档级:</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="aux/fepcl.do" target="dialog" width="400" height="250"><font color="blue">点此处查看</font></a></td></tr>
			<tr><td colspan="14" ><b>排名加权:</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;综合得分排名*0.6+决算率排名*0.4</td></tr>
			<tr><td colspan="14" ><b>排名优先级:</b>&nbsp;偏差档级（升序）、排名加权（升序）、综合得分（降序）、决算率（降序）、计划份额占比（降序）</td></tr>
		</tbody>
	</table>

</div>