<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<form id="pagerForm" method="post" action="search/wxdwReceiveAndTimeout.do">
	<input type="hidden" name="keyword" value="${param.keyword}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="aux/wxdwReceiveAndTimeout2.do" method="post"onsubmit="return navTabSearch(this);">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>
						<input type="text" style="display:none"/>
						立项时间：<input id="lxsj1" class="date" pattern="yyyy-MM-dd" name="lxsj1" value="${param.lxsj1}" type="text" size="10" />  至  <input id="lxsj2" name="lxsj2" class="date" pattern="yyyy-MM-dd" value="${param.lxsj2}" type="text" size="10" />&nbsp;&nbsp;&nbsp;&nbsp;
						派单时间：<input id="pdsj1" class="date" pattern="yyyy-MM-dd" name="pdsj1" value="${param.pdsj1}" type="text" size="10" />  至  <input id="pdsj2" name="pdsj2" class="date" pattern="yyyy-MM-dd" value="${param.pdsj2}" type="text" size="10" />
						<netsky:htmlSelect id="dwlb" name="dwlb" objectForOption="dwlbList"  valueForOption="" showForOption=""  value="${param.dwlb}" htmlClass="td-select"/>
						<netsky:htmlSelect id="ywxm" name="ywxm" objectForOption="ywxmList"  valueForOption="" showForOption="" extend="全部," extendPrefix="true" value="${param.ywxm}" htmlClass="td-select"/>
						</td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" onClick="javascript:searchOrExcelExport(this,'aux/wxdwReceiveAndTimeout2.do',navTabSearch);">检 索</button></div> <div class="buttonContent"><button type="button" onClick="javascript:alert('开发中')">Excel导出</button></div></div></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		 
		<table class="table" width="100%" layouth="116">
			<thead>
				<tr> 
					<th style="width: 30px;">序号</th>
					<th style="width: 250px;">合作单位</th>
					<th style="width: 60px;">派单数</th>
					<th style="width: 60px;">接单数</th>
					<th style="width: 60px;">实际选择数</th>
					<th style="width: 60px;">系统选择数</th>
					<th style="width: 60px;">超期数</th>
					<th style="width: 60px;">超期率</th>
					<th style="width: 60px;">决算数</th>
					<th style="width: 60px;">决算率</th> 
					<th></th> 
				</tr>
			</thead>
			<tbody>
				<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${jdcqList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr> 
						<td style="text-align:center">${offset }</td>
						<td>${obj.mc }</td>
						<td><a href="aux/xmmxList.do?mc=${obj.mc }&op=7&dwlb=${param.dwlb}&lxsj1=${param.lxsj1 }&lxsj2=${param.lxsj2 }&pdsj1=${param.pdsj1 }&pdsj2=${param.pdsj2 }&ywxm=${param.ywxm}"
						 target="navTab" rel="xmmxList" title="${obj.mc }[${obj.pds}]">${obj.pds }</a></td> 
						 
						<td><a href="aux/xmmxList.do?mc=${obj.mc }&op=8&dwlb=${param.dwlb}&lxsj1=${param.lxsj1 }&lxsj2=${param.lxsj2 }&pdsj1=${param.pdsj1 }&pdsj2=${param.pdsj2 }&ywxm=${param.ywxm}"
						 target="navTab" rel="xmmxList" title="${obj.mc }[${obj.jds}]">${obj.jds }</a></td>
						<td><a href="aux/xmmxList.do?mc=${obj.mc}&op=11&dwlb=${param.dwlb}&lxsj1=${param.lxsj1 }&lxsj2=${param.lxsj2 }&pdsj1=${param.pdsj1 }&pdsj2=${param.pdsj2 }&ywxm=${param.ywxm}" target="navTab" rel="xmxxList" title="${obj.mc }[${obj.cqs }]">${obj.sjxzs }</a></td> 
						<td>${obj.pds-obj.sjxzs }</td> 
						<td><a href="aux/xmmxList.do?mc=${obj.mc}&op=5&dwlb=${param.dwlb}&lxsj1=${param.lxsj1 }&lxsj2=${param.lxsj2 }&pdsj1=${param.pdsj1 }&pdsj2=${param.pdsj2 }&ywxm=${param.ywxm}" target="navTab" rel="xmxxList" title="${obj.mc }[${obj.cqs }]">${obj.cqs }</a></td>
						<td>${obj.cql }</td>
						
						<td><a href="aux/xmmxList.do?mc=${obj.mc}&op=6&dwlb=${param.dwlb}&lxsj1=${param.lxsj1 }&lxsj2=${param.lxsj2 }&pdsj1=${param.pdsj1 }&pdsj2=${param.pdsj2 }&ywxm=${param.ywxm}" target="navTab" rel="xmxxList" title="${obj.mc }[${obj.jss }]">${obj.jss }</a></td>
						<td>${obj.jsl }</td>
						<td>&nbsp;</td>
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
			<div class="pages"></div>
		</div>
	</div>
</div>