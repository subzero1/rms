<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script type="text/javascript">
	$(function(){
		$("#wxdw_mc",navTab.getCurrentPanel()).keyup(function(e){
			if (e.which == 13){
				$("#searchButton",navTab.getCurrentPanel()).click();
			}
		});
	});
</script>

<form id="pagerForm" method="post" action="">
	<input type="hidden" name="wxdw_lb" value="${param.wxdw_lb}">
	<input type="hidden" name="wxdw_mc" value="${param.wxdw_mc}">
	<input type="hidden" name="date1" value="${date1}">
	<input type="hidden" name="date2" value="${date2}">
	<input type="hidden" name="khlb" value="${param.khlb}">
	<input type="hidden" name="type" value="${param.type}">
	<input type="hidden" name="qrzt" value="${param.qrzt}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="wxdwkh/rckhList.do?type=${param.type }" method="post">
			<input type="hidden" id="selectedId_demo" />
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<c:if test="${param.type == 'khry' }">
						<td>单位类别：<netsky:htmlSelect name="wxdw_lb" id="lb" objectForOption="lbList" valueForOption="" showForOption="" value="${param.wxdw_lb}" extend=""  extendPrefix="true" /></td>
						<td>单位名称：<input id="wxdw_mc" name="wxdw_mc" value="${param.wxdw_mc}" type="text" size="25" />
						<input type="text" style="display:none;"/>
						</td>
						</c:if>
						<td>考核类别：<netsky:htmlSelect name="khlb" id="khlb" objectForOption="khlbList" valueForOption="name" showForOption="name" value="${param.khlb}" extend=""  extendPrefix="true" /></td>
						<td>考核时间：<input id="date1" name="date1" value="${date1}" type="text" class="date" pattern="yyyy-MM-dd" size="15" />
						-<input id="date2" name="date2" value="${date2}" type="text" class="date" pattern="yyyy-MM-dd" size="15" />
						</td>
						<td>确认状态：<netsky:htmlSelect name="qrzt" id="qrzt" objectForOption="qrztList" valueForOption="" showForOption="" value="${param.qrzt}" extend=""  extendPrefix="true" /></td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="searchButton" onClick="javascript:searchOrExcelExport(this,'wxdwkh/rckhList.do?type=${param.type }',navTabSearch);">检 索</button></div></div></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<c:if test="${param.type == 'khry'}">
				<li><a class="add" href="wxdwkh/rckhEdit.do?canedit=true" target="dialog" width="800" height="480" rel="rckh" title="日常考核"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="edit" href="wxdwkh/rckhEdit.do?id={rckh_id}&canedit=true" target="dialog" width="800" height="480" rel="rckh" title="日常考核"><span>修改</span></a></li>
				<li class="line">line</li>
				</c:if>
				<c:if test="${param.type == 'wxdw'}">
				<li><a class="edit" href="wxdwkh/rckhEdit.do?id={rckh_id}&canedit=false" target="dialog" width="800" height="480" rel="rckh" title="日常考核"><span>查看</span></a></li>
				<li class="line">line</li>
				</c:if>
				<li><a class="exportexcel"	href="javascript:enterHelp('rckh')"><span>在线帮助</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" width="100%" layouth="138" style="table-layout:fixed;">
			<thead>
				<tr>
					<c:if test="${param.type == 'khry' }">
					<th orderField="wxdw_mc">单位名称</th>
					<th style="width: 80px;" orderField="wxdw_lb">单位类别</th>
					</c:if>
					<th style="width: 80px;" orderField="khsj">考核时间</th>
					<th style="width: 80px;" orderField="khry_name">考核人员</th>
					<th style="width: 80px;" orderField="khyy">考核原因</th>
					<th style="width: 80px;" orderField="khlb">考核类别</th>
					<th style="width: 80px;" orderField="fkje">罚款金额</th>
					<th style="width: 80px;" orderField="jkfz">加扣分值</th>
					<th style="width: 80px;" orderField="khjg">考核结果</th>
					<th style="width: 80px;" orderField="qrsj">确认</th>
				</tr>
			</thead>
			<tbody>
			<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${rckhList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr target="rckh_id" rel="${obj.id}">
					<c:if test="${param.type == 'khry' }">
						<td>${obj.wxdw_mc }</td>
						<td>${obj.wxdw_lb }</td>
					</c:if>
						<td><fmt:formatDate value="${obj.khsj}" pattern="yyyy-MM-dd"/></td>
						<td>${obj.khry_name }</td>
						<td>${obj.khyy }</td>
						<td>${obj.khlb }</td>
						<td><fmt:formatNumber pattern="0.00" value="${obj.fkje }"/></td>
						<td><fmt:formatNumber pattern="0.00" value="${obj.jkfz }"/></td>
						<td style="white-space:nowrap;overflow:hidden;">${obj.khjg }</td>
						<td style="text-align:center"><c:if test="${not empty obj.qrsj }"><img src="Images/online_ok.gif"/></c:if></td>
					</tr>
				</c:forEach>
				<c:if test="${offset<numPerPage}">
				<c:forEach begin="${offset}" end="${numPerPage-1}">
					<tr>
					<c:if test="${param.type == 'khry' }">
						<td></td>
						<td></td>
					</c:if>
						<td></td>
						<td></td>
						<td></td>
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