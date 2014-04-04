<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script type="text/javascript">
	function searchListExport(){
		var $pagerForm=$("#pagerForm",navTab.getCurrentPanel());
		$pagerForm.attr("action","wxry/wxryExportList.do"); 
		$pagerForm.submit();
}
</script>

<form id="pagerForm" method="post" action="wxdw/wxrylbList.do">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
	<input type="hidden" name="wxry_mc" value="${mc}" />
	<input type="hidden" name="wxry_name" value="${name}" />
	<input type="hidden" name="wxry_sfz" value="${sfz}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="wxdw/wxrylbList.do" method="post" onsubmit="return navTabSearch(this);">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>
						单位名称：<input id="wxry_mc" name="wxry_mc" value="${mc}" type="text" size="25" /></td>
						<td>
						姓名：<input id="wxry_name" name="wxry_name" value="${name}" type="text" size="25" /></td>
						<td>
						身份证号：<input id="wxry_sfz" name="wxry_sfz" value="${sfz}" type="text" size="25" /></td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" onClick="javascript:searchOrExcelExport(this,'wxdw/wxrylbList.do',navTabSearch);">检 索</button></div></div></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="exportexcel" href="javascript:searchListExport();" ><span>导出</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" width="2000px" layouth="138">
			<thead>
			<tr>
				<th width="300px">
					单位名称
				</th>
				<th width="100px">
					姓名
				</th>
				<th width="50px">
					性别
				</th>
				<th width="100px">
					移动电话
				</th>
				<th width="100px">
					身份证号
				</th>
				<th width="200px">
					 地址
				</th>
				<th width="200px">
					 备注
				</th>
				<th width="80px">
					 状态
				</th>
				<th width="80px">
					概预算证 
				</th>
				<th width="80px">
					安全员证
				</th>
				<th width="80px">
					监理证
				</th>
				<th width="80px">
					登高证
				</th>
				<th width="80px">
					电工证
				</th>
				<th width="80px">
					下挂单位
				</th>
				<th width="80px">
					专业
				</th>
				<th width="150px">
					是否签订劳动合同
				</th>
				<th width="100px">
					是否上保险
				</th>
				<th width="100px">
					派发安全用品
				</th> 
			</tr>
			</thead>
			<tbody>
		<c:set var="offset" value="0"/>
			<c:forEach items="${wxryList1}" var="wxry">
			<c:set var="offset" value="${offset+1}"/>
			<tr>
				<td width="300px">
					${wxry[0] }
				</td>
				<td width="100px">
					${wxry[1] }
				</td>
				<td width="50px">
					${wxry[2] }
				</td>
				<td width="100px">
					${wxry[3] }
				</td>
				<td width="150px">
					${wxry[4] }
				</td>
				<td width="200px">
					${wxry[5] }
				</td>
				<td width="200px">
					${wxry[6] }
				</td>
				<td width="80px">
					${wxry[7] }
				</td>
				<td width="80px">
					${wxry[8] }
				</td>
				<td width="80px">
					${wxry[9] }
				</td>
				<td width="80px">
					${wxry[10] }
				</td>
				<td width="80px">
					${wxry[11] }
				</td>
				<td width="80px">
					${wxry[12] }
				</td>
				<td width="80px">
					${wxry[13] }
				</td>
				<td width="80px">
					${wxry[14] }
				</td>
				<td width="150px">
					<c:if test="${wxry[15]==1}">是</c:if>
					<c:if test="${wxry[15]==0}">否</c:if>
				</td>
				<td width="100px">
					<c:if test="${wxry[16]==1}">是</c:if>
					<c:if test="${wxry[16]==0}">否</c:if>
				</td>
				<td width="100px">
					<c:if test="${wxry[17]==1}">是</c:if>
					<c:if test="${wxry[17]==0}">否</c:if>
				</td>
			</tr>
		</c:forEach>
		<c:if test="${offset<numPerPage}">
				<c:forEach begin="${offset}" end="${numPerPage-1}">
					<tr>
						<c:forEach begin="0" end="17" >
							<td></td>
						</c:forEach>
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
				<span>共${wxryCount}条 </span>
			</div>

			<div class="pagination" targetType="navTab"	totalCount="${totalCount}" numPerPage="${numPerPage}" currentPage="${param.pageNum}"></div>
		</div>
	</div>
</div>