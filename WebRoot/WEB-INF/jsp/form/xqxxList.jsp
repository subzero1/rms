<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<form id="pagerForm" method="post" action="form/xmxxList.do">
	<input type="hidden" name="keyword" value="${param.keyword}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="form/xmxxList.do" method="post"onsubmit="return navTabSearch(this);">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>
						<input type="text" style="display:none"/>
						关键字：<input id="keyword" name="keyword" value="${param.keyword}" type="text" size="25" /></td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" onClick="javascript:searchOrExcelExport(this,'form/xmxxList.do',navTabSearch);">检 索</button></div></div></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
			<c:if test="${node_id == '10101'}">
					<li><a class="add" href="flowForm.do?module_id=101&node_id=10101&flow_id=101" target="navTab" rel="xmxx" title="需求书"><span>添加</span></a></li>
					<li class="line">line</li>
					<li><a class="delete" href="form/ajaxXmxxDel.do?id={xm_id}" target="ajaxTodo" title="确认删除吗？"><span>删除</span></a></li>
					<li class="line">line</li>
					<!-- 
					<li> <a class="exportexcel" href="dispath.do?url=form/xmxxImport.jsp" target="dialog" width="400" height="200"><span>导入</span></a></li>
					<li class="line">line</li>
					-->
				</c:if>
					<!-- 
					<li> <a class="exportexcel" href="javascript:searchListExport();" ><span>导出</span></a></li>
					<li class="line">line</li>
					
					<li><a class="helponline"	href="javascript:enterHelp('xmxx')"><span>在线2帮助</span></a></li>
					<li class="line">line</li>-->
				</ul>
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
					<th style="width:50px;"></th>
					<th orderField="xqmc">需求名称</th>
					<th style="width: 100px;" orderField="jsxz">建设性质</th>
					<th style="width: 80px;" orderField="ssdq">所属地区</th>
					<th style="width: 65px;" orderField="jd">经度</th>
					<th style="width: 65px;" orderField="wd">纬度</th>
					<th style="width: 80px;" orderField="fgsx">覆盖属性</th>
					<th style="width: 65px;" orderField="zs">幢数</th>
					<th style="width: 65px;" orderField="cs">层数</th>
					<th style="width: 65px;" orderField="hs">户数</th>
					<th style="width: 80px;" orderField="cjr">提出人</th>
					<th style="width: 80px;" orderField="cjrq">提出时间</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${xqxxList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr target="xq_id" rel="${obj.id}">
						<td style="text-align:center;">
							<a class="add" href="javascript:navTab.openTab('xmsgjd', 'wxdw/xmsgjd.do?id=${obj.id }', {title:'项目施工进度'});"  rel="gcsgjd"><img border="0" src="Images/chart_bar.png" style="cursor:pointer"/></a>&nbsp;&nbsp;
							<a class="add" href="jlgt/jlgtView.do?module_id=101&doc_id=${obj.id }" target="navTab" rel="jlgtView"><img border="0" src="Images/track_record.png" style="cursor:pointer"/></a>
						</td>
						<td><a href="openForm.do?project_id=${obj.id }&module_id=109&doc_id=${obj.id }&user_id=${user.id }&node_id=10902" target="navTab" rel="xqxx" title="需求书">${obj.xqmc }</a></td>
						<td>${obj.jsxz }&nbsp;</td>
						<td>${obj.ssdq }</td>
						<td>${obj.jd }</td>
						<td>${obj.wd }</td>
						<td>${obj.fgsx }</td>
						<td>${obj.zs }</td>
						<td>${obj.cs }</td>
						<td>${obj.hs }</td>
						<td>${obj.cjr }</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${obj.cjrq }"/></td>
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