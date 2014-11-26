<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<form id="pagerForm" method="post" action="form/orderList.do">
	<input type="hidden" name="keyword" value="${param.keyword}">
	<input type="hidden" name="ddzt" value="${ddzt}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="form/orderList.do" method="post"onsubmit="return navTabSearch(this);">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>
							<input type="text" style="display:none"/>
							关键字：
							<input id="keyword" name="keyword" value="${param.keyword}" type="text" size="25" />；
							<netsky:htmlSelect name="ddzt" objectForOption="ddztList"  valueForOption="name" showForOption="name" extend="全部,全部" extendPrefix="true" value="${ddzt}" htmlClass="td-select"/>；&nbsp;
							<c:choose>
								<c:when test="${curRole=='groupManager'}">
									<td><netsky:htmlSelect name="xmgly" id="xmgly" objectForOption="listForWorkGroup" valueForOption="name" showForOption="name" value="${param.xmgly}" extend="全部,"  extendPrefix="true" /></td>
								</c:when>
							<c:otherwise></c:otherwise>
						</c:choose>
						<netsky:htmlSelect name="sjmc" objectForOption="sjmcList"  extend="---选择时间---," extendPrefix="true" value="${sjmc}" htmlClass="td-select"/>
						<input id="date1" name="date1" value="${param.date1}" type="text" size="10" class="date"/>至
						<input id="date2" name="date2" value="${param.date2}" type="text" size="10" class="date"/>
						</td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" onClick="javascript:searchOrExcelExport(this,'form/orderList.do',navTabSearch);">检 索</button></div></div></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<c:if test="${node_id == '11401' || node_id == '11404'}">
					<li><a class="add" href="flowForm.do?module_id=114&node_id=11401&flow_id=114" target="navTab" rel="ddxx" title="定单信息单"><span>添加</span></a></li>
					<li class="line">line</li>
				</c:if>
				<c:if test="${node_id == '11401'}">
					<li><a class="delete" href="form/ajaxDdxxDel.do?id={xm_id}" target="ajaxTodo" title="确认删除吗？"><span>删除</span></a></li>
					<li class="line">line</li>
				</c:if>
				<li> <a class="exportexcel" href="form/orderInfoToExcel.do?config=td00_order" ><span>导出</span></a></li>
				</ul>
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
					<th style="width:20px;"></th>
					<th orderField="gcbh" style="width: 80px;">订单编码</th>
					<th style="width: 250px;" orderField="gcmc">标题</th>
					<th style="width: 200px;" orderField="lxxx">联系信息</th>
					<th style="width: 250px;" orderField="a_adress">A端装机地址</th>
					<th style="width: 80px;" orderField="xmgly">项目管理员</th>
					<th ></th> 
				</tr>
			</thead>
			<tbody>
				<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${objList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr target="xm_id" rel="${obj.id}">
						<td style="text-align:center;"></td>
						<td><a href="openForm.do?project_id=${obj.id }&module_id=114&doc_id=${obj.id }&user_id=${user.id }&limit=${limit }&node_id=${node_id }" target="navTab" rel="ddxx" title="定单信息">${obj.gcbh }</a></td>
						<td><a href="openForm.do?project_id=${obj.id }&module_id=114&doc_id=${obj.id }&user_id=${user.id }&limit=${limit }&node_id=${node_id }" target="navTab" rel="ddxx" title="定单信息">${obj.gcmc }</a></td>
						<td>${obj.lxxx }</td>
						<td>${obj.a_adress}</td>
						<td>${obj.xmgly}</td>
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