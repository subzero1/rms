<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script language="javascript">
function modifyTitle(td_id,tf19_id,tf19_khmc){	
	var input_info = "<p><textarea  style=\"width:220px;height:100px;\" id=\"_khmc\">"+tf19_khmc+"</textarea></p>";
	alertMsg.input(input_info, {title:'考核名称修改',
		okCall: function(){
			var khmc = $("#_khmc").val();
			var data = {'id':tf19_id,'khmc':khmc};
			$.ajax({
				url:'wxdwkh/ajaxModifyKhmc.do',
				type:'post',
				data:data,
				dataType:"text",
				cache: false,
				success: function(text){
					if(text=="ok"){
						var khmcTd = $("#"+td_id);
						khmcTd.html(khmc);
					}
				},
				error: DWZ.ajaxError
			 });
		}
	});
}
</script>

<form id="pagerForm" method="post" action="wxdwkh/hzdwkhList.do">
	<input type="hidden" name="khnr" value="${khnr}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="wxdwkh/hzdwkhList.do" method="post"onsubmit="return navTabSearch(this);">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>
						<input type="text" style="display:none"/>
						考核内容：<netsky:htmlSelect name="khnr" id="khnr" objectForOption="khnrList" valueForOption="id" showForOption="mc" value="${param.khnr}" extend=""  extendPrefix="true" /></td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" onClick="javascript:searchOrExcelExport(this,'wxdwkh/hzdwkhList.do',navTabSearch);">检 索</button></div></div></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<!-- 
			<ul class="toolBar">
				<li><a class="helponline"	href="javascript:enterHelp('xmgl')"><span>在线帮助</span></a></li>
				<li class="line">line</li>
			</ul>
			 -->
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
					<th style="width:40px;">类别</th>
					<th style="width: 360px;" orderField="tf19.khmc">标题</th>
					<th style="width: 150px;" orderField="tf19.khkssj">开始时间</th>
					<th style="width: 150px;" orderField="tf19.khjssj">结束时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${khxxList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr target="khxx_id" rel="${obj["tf19"].id}">
						<td style="text-align:center;">${obj["tc10"].dwlb }</td>
						<td id="khmc${offset }">${obj["tf19"].khmc }</td>
						<td><fmt:formatDate value="${obj[\"tf19\"].khkssj }" pattern="yyyy-MM-dd"/></td>
						<td><fmt:formatDate value="${obj[\"tf19\"].khjssj }" pattern="yyyy-MM-dd"/></td>
						<td><a href="wxdwkh/khpf.do?khxx_id=${obj["tf19"].id }" target="navTab" rel="khpf"><font color="blue"><u>打分</u></font></a> 
						&nbsp;&nbsp;&nbsp;&nbsp;<a href="wxdwkh/khpfView.do?khxx_id=${obj["tf19"].id }" target="navTab" rel="ckkhpf"><font color="blue"><u>查看结果</u></font></a>
						&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:modifyTitle('khmc${offset }','${obj["tf19"].id }','${obj["tf19"].khmc }');"><font color="blue"><u>修改标题</u></font></td>
					</tr>
				</c:forEach>
				<c:if test="${offset<numPerPage}">
				<c:forEach begin="${offset}" end="${numPerPage-1}">
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</c:forEach>
				</c:if>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span>
				<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})" selectValue="${numPerPage}">
					<option value="20">20</option>
					<option value="50">50</option>
					<option value="100">100</option>
					<option value="200">200</option>
				</select>
				<span>共${totalCount}条 </span>
			</div>

			<div class="pagination" targetType="navTab"	totalCount="${totalCount}" numPerPage="${numPerPage}" currentPage="${param.pageNum}"></div>

		</div>
	</div>
</div>