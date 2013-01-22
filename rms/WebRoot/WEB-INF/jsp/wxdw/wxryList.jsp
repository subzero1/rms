<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<style>
	.tabtable th,td{height:22px;line-height: 21px;overflow: hidden;padding: 0px 3px;}
	.tabtable th{
		border:solid 1px #d0d0d0;border-top:solid 0px #d0d0d0;
		vertical-align:middle;
		background: url("http://localhost:8080/rms/themes/default/images/grid/tableth.png") repeat-x #F0EFF0;		
		}
	.tabtable td{border:solid 1px #ededed;background:#fff;}
</style>
<div class="panelBar">
	<ul class="toolBar">
		<c:if test="${not empty param.wxdw_id }">
			<li>
				<a class="add" href="wxdw/wxryEdit.do?wxdw_id=${param.wxdw_id }"
					target="dialog" width="500" height="350" rel="wxry"
					title="外协人员信息"><span>添加</span>
				</a>
			</li>
			<li class="line">
				line
			</li>
			<li>
				<a class="edit"
					href="wxdw/wxryEdit.do?wxry_id={wxry_id}&wxdw_id=${param.wxdw_id}"
					target="dialog" width="500" height="350" rel="wxry"
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
	<input type="hidden" name="orderDirection"	value="${param.orderDirection}" />
		<input type="hidden" name="wxry_id" value="${param.wxdw_id}"/>
</form>
<form></form>
<table class="tabtable" width="90%" layouth="290" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;">
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
			<th style="width: 150px;" orderField="gysz">
				 概预算证
			</th>
			<th style="width: 150px;" orderField="aqyz">
				 安全员证
			</th>
			<th style="width: 150px;" orderField="jlz">
				 监理证
			</th>
			<th style="width: 150px;" orderField="dgz">
				登高证 
			</th>
			<th style="width: 150px;" orderField="ec">
				电工证
			</th>
			<th style="width: 60px;">
				照片
			</th>
			<th style="width: 150px;" orderField="bz">
				备注
			</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${wxryList}" var="wxry">
			<tr target="wxry_id" rel="${wxry[0].id}">
				<td style="width: 80px;">
					<a href="wxdw/wxryEdit.do?wxry_id=${wxry[0].id}&wxdw_id=${param.wxdw_id}"
						target="dialog" width="500" height="350" rel="wxry"
						title="外协人员信息">${wxry[0].name }</a>
				</td>
				<td style="width: 80px;">
					 ${wxry[0].sex } 
				</td>
				<td style="width: 120px;">
					${wxry[0].mobile}
				</td>
				<td style="width: 150px;">
					${wxry[0].sfz }
				</td>
				<td style="width: 150px;">
					${wxry[0].address }
				</td>
				<td style="width: 150px;">
					${wxry[0].status }
				</td>
				<td style="width: 150px;">
					${wxry[0].gysz}
				</td>
				<td style="width: 150px;">
					${wxry[0].aqyz}
				</td>
				<td style="width: 150px;">
					${wxry[0].jlz}
				</td>
				<td style="width: 150px;">
					${wxry[0].dgz}
				</td>
				<td style="width: 150px;">
					${wxry[0].ec}
				</td>
				<td style="width: 60px;">
					<a href="wxdw/wxryEdit.do?wxry_id=${wxry[0].id}&wxdw_id=${param.wxdw_id}"
						target="dialog" width="500" height="350" rel="wxry"
						title="外协人员信息">${wxry[1].havePic }</a>
				</td>
				<td style="width: 150px;">
					${wxry[0].bz }
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>