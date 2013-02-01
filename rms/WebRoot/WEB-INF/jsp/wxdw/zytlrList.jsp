<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<form id="pagerForm" method="post" action="infoManage/zytlrList.do">
	<input type="hidden" name="keyword" value="${param.keyword}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="infoManage/zytlrList.do" method="post"onsubmit="return navTabSearch(this);">
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
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" onClick="javascript:searchOrExcelExport(this,'wxdw/zytlrList.do',navTabSearch);">检 索</button></div></div></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar"> 
					<li><a class="add" href="wxdw/zytlEdit.do" target="navTab" rel="zytlrEdit" title="资源填录人信息"><span>添加</span></a></li>
					<li class="line">line</li>
					<li><a class="delete" href="form/ajaxGcxxDel.do?id={gc_id}" target="ajaxTodo" title="确认删除吗？"><span>删除</span></a></li>
					<li class="line">line</li>
					<li> <a class="exportexcel" href="dispath.do?url=form/xlsImport.jsp?config=batch_update_gc" target="dialog" width="400" height="200"><span>导入</span></a></li>
					<li class="line">line</li> 
				    <!-- 
					<li> <a class="exportexcel" href="javascript:searchListExport();" ><span>导出</span></a></li>
					<li class="line">line</li>
					
					<li><a class="helponline"	href="javascript:enterHelp('gcxx')"><span>在线2帮助</span></a></li>
					<li class="line">line</li>-->
			</ul>
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
					<th style="width:30px;"></th>
					<th style="width: 120px;" orderField="gis_no" >GIS工号</th>
					<th style="width: 120px;" orderField="tlrxm">填录人姓名</th>
					<th style="width: 120px;"  orderField="ssdw">所属单位</th>
					<th style="width: 120px;"  orderField="nx">年限</th>
					<th style="width: 120px;" orderField="in_time">进入工程中心日期</th>
					<th style="width: 120px;" orderField="rzcj">认证成绩</th>
					<th style="width: 120px;" orderField="phone">联系电话</th>
					<th style="width: 120px;" orderField="zc">专长</th> 
				</tr>
			</thead>
			<tbody>
				<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${Tf31_zytlList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr target="gc_id" rel="${obj.id}">
						<td style="text-align:center;">
						${offset }
						</td>
						<td>${obj.gis_no }</td>
						<td><a href="wxdw/zytlEdit.do?zytl_id=${obj.id }" target="navTab" rel="zytlrEdit" title="资源填录人信息单">${obj.tlrxm }</a></td>
						<td>${obj.ssdw }</td>
						<td>${obj.nx }</td>
						<td><fmt:formatDate value="${obj.in_time }" pattern="yyyy-MM-dd"/></td>
						<td>${obj.rzcj }</td>
						<td>${obj.phone }</td>
						<td>${obj.zc }</td>
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