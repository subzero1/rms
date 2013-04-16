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

<div class="pageContent">
	<table class="table" layoutH="118" targettype="dialog" width="100%">
		<thead>
			<tr>
				<th width="200">单位名称</th>
				<th width="70">决算率</th>
				<th width="70">综合得分</th>
				<th width="70">计划占比</th>
				<th width="70">实际占比</th>
				<th width="50">偏差率</th>
				<th width="70">偏差档级</th>
			</tr>
		</thead>				
		<tbody>
			<c:forEach items="${allList }" var="o">
			<tr>
				<td title="${o[0].mc }<c:if test="${o[0].mc == zdxp.mc}">（系统选择）</c:if>" style="<c:if test="${o[0].mc == zdxp.mc}">color:red</c:if>" >${o[0].mc }<c:if test="${o[0].mc == zdxp.mc}">（系统选择）</c:if></td>
				<td style="<c:if test="${o[0].mc == zdxp.mc}">color:red</c:if>"><fmt:formatNumber pattern="0.00%" value="${o[3] }"/></td>
				<td style="<c:if test="${o[0].mc == zdxp.mc}">color:red</c:if>"><fmt:formatNumber pattern="0" value="${o[2] }"/></td>
				<td style="<c:if test="${o[0].mc == zdxp.mc}">color:red</c:if>"><fmt:formatNumber pattern="0.00%" value="${o[6]/100 }"/></td>
				<td style="<c:if test="${o[0].mc == zdxp.mc}">color:red</c:if>"><fmt:formatNumber pattern="0.00%" value="${o[7]/100 }"/></td>
				<td style="<c:if test="${o[0].mc == zdxp.mc}">color:red</c:if>"><fmt:formatNumber pattern="0.00%" value="${o[8]/100 }"/></td>
				<td style="<c:if test="${o[0].mc == zdxp.mc}">color:red</c:if>">${o[9] }</td>
			</tr>
			</c:forEach> 
			<c:forEach items="${pxsjxzdw}" var="o">
			<tr title="${o[0].mc }(实际选择)" style="color:green;">
				<td>${o[0].mc }(实际选择)</td>
				<td><fmt:formatNumber pattern="0.00%" value="${o[3] }"/></td>
				<td><fmt:formatNumber pattern="0" value="${o[2] }"/></td>
				<td><fmt:formatNumber pattern="0.00%" value="${o[6]/100 }"/></td>
				<td><fmt:formatNumber pattern="0.00%" value="${o[7]/100 }"/></td>
				<td><fmt:formatNumber pattern="0.00%" value="${o[8]/100 }"/></td>
				<td>${o[9] }</td>
			</tr>
			</c:forEach>
			<c:if test="">
			<c:forEach items="${empty sjxzdw}" var="o">
			<tr style="color:green;">
				<td>${o[1]}</td>
				<td>-</td>
				<td>-</td>
				<td>-</td>
				<td>-</td>
				<td>-</td>
				<td>-</td>
			</tr>
			</c:forEach>
			</c:if>
		</tbody>
	</table>

</div>