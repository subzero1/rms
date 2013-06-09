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
	<table class="table" layoutH="75" targettype="dialog" width="100%">
		<thead>
			<tr>
				<th width="200">单位名称</th>
				<th width="70">决算率</th>
				<th width="70">综合得分</th>
				<th width="70">计划占比</th>
				<th width="70">实际占比</th>
				<th width="50">偏差率</th>
				<th width="70">偏差档级</th>
				<th width="50">项目数</th>
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
				<td style="<c:if test="${o[0].mc == zdxp.mc}">color:red</c:if>">${o[11] }</td>
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
				<td>${o[11] }</td>
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
				<td>-</td>
			</tr>
			</c:forEach>
			</c:if>
			<tr><td colspan="8" ></td></tr>
			<tr><td colspan="8" >决算率:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;已经决算过的工程数/总工程数&nbsp;&nbsp;(年度:<fmt:formatDate value="${xmxx.lxsj}" pattern="yyyy"/>,专业:${xmxx.gclb} ,地区:${xmxx.ssdq },项目:${xmxx.xmmc })</td></tr>
			<tr><td colspan="8" >实际占比:&nbsp;当前单位的合同金额/项目总合同金额&nbsp;&nbsp;(年度:<fmt:formatDate value="${xmxx.lxsj}" pattern="yyyy"/>,专业:${xmxx.gclb} ,地区:${xmxx.ssdq },项目:${xmxx.xmmc })</td></tr>
			<tr><td colspan="8" >偏差率:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(计划占比-实际占比)/计划占比&nbsp;&nbsp;(年度:<fmt:formatDate value="${xmxx.lxsj}" pattern="yyyy"/>,专业:${xmxx.gclb} ,地区:${xmxx.ssdq },项目:${xmxx.xmmc })</td></tr>
			<tr><td colspan="8" >偏差等级:&nbsp;<a href="aux/fepcl.do" target="dialog" width="400" height="250">详情</td></tr>
			<tr ><td colspan="8" style="border: 0px;">项目数:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前单位的项目数(<fmt:formatDate value="${xmxx.lxsj}" pattern="yyyy"/>年度,${xmxx.gclb} ,${xmxx.ssdq })</td></tr>
		
		</tbody>
	</table>

</div>