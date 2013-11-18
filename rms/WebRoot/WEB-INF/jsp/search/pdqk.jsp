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
				<th width="60">决算数</th>
				<th width="70">项目总数</th>
				<th width="60">决算率</th>
				<th width="80">决算率排名</th>
				<th width="70">综合得分</th>
				<th width="70">得分排名</th>
				<th width="70">计划占比</th>
				<th width="70">实际占比</th>
				<th width="60">偏差率</th>
				<th width="70">偏差档级</th>
				<th width="80">在建项目数</th>
				<th width="80">最大项目数</th>
			</tr>
		</thead>				
		<tbody>
			<c:forEach items="${detail_list }" var="o">
			<tr 
				<c:choose>
					<c:when test="${o[1].xtxzbz == 1}">
					style="color:red" title="系统选择"
					</c:when>
					<c:when test="${o[2]==1 }">
					style=color:green  title="实际选择"
					</c:when>
				</c:choose>
			>
				<td>${o[0].mc }</td>
				<td><fmt:formatNumber pattern="0" value="${o[1].jssl}"/></td>
				<td><fmt:formatNumber pattern="0" value="${o[1].xmsl }"/></td>
				<td><fmt:formatNumber pattern="0.00" value="${o[1].jsl }"/>%</td>
				<td><fmt:formatNumber pattern="0" value="${o[1].jslpm }"/></td>
				<td><fmt:formatNumber pattern="0.00" value="${o[1].zhdf }"/></td>
				<td><fmt:formatNumber pattern="0" value="${o[1].zhdfpm }"/></td>
				<td><fmt:formatNumber pattern="0.00" value="${o[1].jhfezb }"/>%</td>
				<td><fmt:formatNumber pattern="0.00" value="${o[1].sjfezb }"/>%</td>
				<td><fmt:formatNumber pattern="0.00" value="${o[1].fepcl }"/>%</td>
				<td><fmt:formatNumber pattern="0" value="${o[1].dj }"/></td>
				<td><fmt:formatNumber pattern="0" value="${o[1].zjgcs }"/></td>
				<td><fmt:formatNumber pattern="0" value="${o[1].zdgcs }"/></td>
			</tr>
			</c:forEach> 
			<tr><td colspan="13" ></td></tr>
			<tr><td colspan="13" ><b>决算率:</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;决算项目数/项目总数*100%&nbsp;&nbsp;</td></tr>
			<tr><td colspan="13" ><b>偏差率:</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(计划占比-实际占比)/计划占比&nbsp;&nbsp;</td></tr>
			<tr><td colspan="13" ><b>偏差档级:</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="aux/fepcl.do" target="dialog" width="400" height="250"><font color="blue">点此处查看</font></a></td></tr>
			<tr><td colspan="13" ><b>排名加权:</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;综合得分排名*0.6+决算率排名*0.4</td></tr>
			<tr><td colspan="13" ><b>排名优先级:</b>&nbsp;偏差档级（升序）、排名加权（升序）、综合得分（降序）、决算率（降序）、计划份额占比（降序）</td></tr>
			
		</tbody>
	</table>

</div>