<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script type="text/javascript">
	$(function(){
		$("#mc").keyup(function(e){
			if (e.which == 13){
				$("#searchButton",navTab.getCurrentPanel()).click();
			}
		});
	});
</script>

<form id="pagerForm" method="post" action="">
	<input type="hidden" name="wxry_name" value="${param.wxry_name}">
	<input type="hidden" name="wxdw_id" value="${param.wxdw_id}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="wxdw/wxryList.do" method="post">
			<input type="hidden" id="selectedId_demo" />
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>姓&nbsp;&nbsp;&nbsp;名：<input id="wxry_name" name="wxry_name" value="${param.name}" type="text" size="25" />
						<input type="hidden" name="wxdw_id" value="${param.wxdw_id}">
						<input type="text" style="display:none;"/>
						</td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="searchButton" onClick="javascript:searchOrExcelExport(this,'wxdw/wxryList.do',navTabSearch);">检 索</button></div></div></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li>
				<a class="add" href="wxdw/wxryEdit.do?wxdw_id=${param.wxdw_id }"
					target="navTab" rel="wxry"
					title="外协人员信息"><span>添加</span>
				</a>
			</li>
			<li class="line">
				line
			</li>
			<li>
				<a class="edit"
					href="wxdw/wxryEdit.do?wxry_id={wxry_id}&wxdw_id=${param.wxdw_id}"
					target="navTab" rel="wxry"
					title="修改外协人员信息"><span>修改</span>
				</a>
			</li>
			<li class="line">
				line
			</li>
				<li><a class="exportexcel"	
				href="wxdw/wxdwList.do?toExcel=yes" 
				target="dwzExport" targetType="navTab"><span>导入</span></a></li>
				<li class="line">line</li>
				<li><a class="exportexcel"	
				href="wxdw/wxryToExcel.do?config=tf30_wxry" 
				target="dwzExport" targetType="navTab"><span>导出</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
					<th style="width: 80px;" orderField="name">
				姓名
			</th>
			<th style="width: 120px;" orderField="mobile">
				移动电话
			</th>
			<th style="width: 150px;" orderField="sfz">
				身份证号
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
			<th style="width: 150px;" orderField="bx">
				本部/下挂单位
			</th>
			<th style="width: 150px;" orderField="major">
				专业
			</th>
			<th style="width: 150px;" orderField="contract">
				是否签订劳动合同
			</th>
			<th style="width: 150px;" orderField="insure">
				是否购买保险
			</th>
			<th style="width: 150px;" orderField="safety">
				安全保护用品
			</th>
			<th style="width: 60px;">
				照片
			</th> 
				</tr>
			</thead>
			<tbody>
				<c:set var="offset" value="0"/>
			<c:forEach items="${wxryList}" var="wxry">
			<c:set var="offset" value="${offset+1}"/>
			<tr target="wxry_id" rel="${wxry[0].id}">
				<td style="width: 80px;">
					<a href="wxdw/wxryEdit.do?wxry_id=${wxry[0].id}&wxdw_id=${param.wxdw_id}"
						target="navTab" rel="wxryEdit"
						title="外协人员信息">${wxry[0].name }</a>
				</td>
				<td style="width: 120px;">
					${wxry[0].mobile}
				</td>
				<td style="width: 150px;">
					${wxry[0].sfz }
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
				<td style="width: 150px;">
					${wxry[0].bx}
				</td>
				<td style="width: 150px;">
					${wxry[0].major}
				</td>
				<td style="width: 150px;">
					<c:if test="${wxry[0].contract==1}">是</c:if>
					<c:if test="${wxry[0].contract==0}">否</c:if>
				</td>
				<td style="width: 150px;">
					<c:if test="${wxry[0].insure==1}">是</c:if>
					<c:if test="${wxry[0].insure==0}">否</c:if>
				</td>
				<td style="width: 150px;">
					<c:if test="${wxry[0].safety==1}">是</c:if>
					<c:if test="${wxry[0].safety==0}">否</c:if>
				</td>
				<td style="width: 60px;">
					<a href="wxdw/wxryEdit.do?wxry_id=${wxry[0].id}&wxdw_id=${param.wxdw_id}"
						target="dialog" width="500" height="350" rel="wxry"
						title="外协人员信息">${wxry[1].havePic }</a>
				</td> 
			</tr>
		</c:forEach>
			<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${wxdwList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr target="wxdw_id" rel="${obj.id}">
						<td>${obj.lb }</td>
						<td><a href="wxdw/wxdwEdit.do?id=${obj.id}" target="navTab" rel="wxdw" title="合作单位维护">${obj.mc }</a></td>
						<td>${obj.dwdz }</td>
						<td>${obj.zt }</td>
					</tr>
				</c:forEach>
				<c:if test="${offset<numPerPage}">
				<c:forEach begin="${offset}" end="${numPerPage-1}">
					<tr>
					<c:forEach begin="0" end="13">
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
				<span>共${totalCount}条 </span>
			</div>

			<div class="pagination" targetType="navTab"	totalCount="${totalCount}" numPerPage="${numPerPage}" currentPage="${param.pageNum}"></div>

		</div>
	</div>
</div>