<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>


<div class="panelBar">
	<ul class="toolBar">
		<c:if test="${not empty param.wxdw_id }">
			<li>
				<a class="add" href="wxdw/wxryEdit.do?wxdw_id=${wxdw_id }"
					target="dialog" width="500" height="330" rel="wxdwUser"
					title="添加外协人员"><span>添加</span>
				</a>
			</li>
			<li class="line">
				line
			</li>
			<li>
				<a class="edit"
					href="wxdw/wxryEdit.do?wxry_id={wxry_id}"
					target="dialog" width="500" height="330" rel="wxdwUser"
					title="修改外协人员信息"><span>修改</span>
				</a>
			</li>
			<li class="line">
				line
			</li>
			<li>
				<a class="exportexcel"
					href="wxdw/wxryToExcel.do?config=wxry_tmp"
					target="dwzExport" targetType="navTab"><span>导出</span>
				</a>
			</li>
			<li class="line">
				line
			</li>
		</c:if>
	</ul>
</div>
<form id="pagerForm" method="post" action="wxdw/wxryList.do"> 
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />
</form>
<form></form>
<table class="table" width="100%" layouth="138">
	<thead>
		<tr>
			<th style="width: 80px;" orderField="name">
				姓名
			</th>
			<th style="width: 80px;" orderField="sex">
				性别
			</th>
			<th style="width: 120px;" orderField="mobile">
				移动电话
			</th>
			<th style="width: 150px;" orderField="sfz">
				身份证号
			</th>
			<th style="width: 150px;" orderField="address">
				地址
			</th>
			<th style="width: 150px;" orderField="status">
				状态
			</th>
			<th style="width: 150px;" orderField="bz">
				备注
			</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${wxryList}" var="wxry">
			<tr target="wxry_id" rel="${wxry.id}">
				<td>
					<a href="wxdw/wxryEdit.do?wxdw_id=${param.wxdw_id }&id=${wxry.id}"
						target="dialog" width="500" height="260" rel="wxdwUser"
						title="外协人员">${wxry.name }</a>
				</td>
				<td>
					 ${wxry.sex } 
				</td>
				<td>
					${wxry.mobile}
				</td>
				<td>
					${wxry.sfz }
				</td>
				<td>
					${wxry.address }
				</td>
				<td>
					${wxry.status }
				</td>
				<td>
					${wxry.bz }
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>