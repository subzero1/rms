<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script type="text/javascript">
	$(function(){
		$("#gcmc").keyup(function(e){
			if (e.which == 13){
				$("#searchButton",navTab.getCurrentPanel()).click();
			}
		});
	});
</script>

<form id="pagerForm" method="post" action="">
	<input type="hidden" name="gcmc" value="${param.gcmc}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="wxdw/gcclList.do" method="post">
			<input type="hidden" id="selectedId_demo" />
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>工程名称：<input id="gcmc" name="gcmc" value="${param.gcmc}" type="text" size="25" />
						<input type="text" style="display:none;"/>
						</td>
						<td>
						<c:if test="${empty param.canedit}"><input type="checkbox" name="allproject" value="1" <c:if test="${not empty param.allproject }">checked="checked"</c:if>/>包含已完工工程</c:if>
						</td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="searchButton" onClick="javascript:searchOrExcelExport(this,'${url }',navTabSearch);">检 索</button></div></div></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="exportexcel"	href="javascript:enterHelp('jlrj')"><span>在线帮助</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
					<th style="width:35px;"></th>
					<th style="width: 150px;" orderField="gcbh">工程编号</th>
					<th orderField="gcmc">工程名称</th>
					<th style="width: 100px;" orderField="jldw">监理单位</th>
					<th style="width: 70px;" orderField="jlrjtbzq">填报周期</th>
					<th style="width: 100px;" orderField="create_date">创建时间</th>
					<th style="width: 70px;" orderField="zt">状态</th>
					<th style="width: 200px;" orderField="xcms">现场描述</th>
				</tr>
			</thead>
			<tbody>
			<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${gcxxList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr style="background-color: ${obj[1] }">
						<td>
								<a href="showTree.do?project_id=${obj[0].id }&doc_id=${obj[0].id }&module_id=101" target="navTab" rel="showTree" title="流程图[${obj[0].id}]"><img border="0" src="Images/node.gif" style="cursor:pointer"/></a>
						</td>
						<td>${obj[0].xmbh }</td>
						<td>${obj[0].xmmc }</td>
						<td>${obj[0].jldw }</td>
						<td><c:if test="${not empty param.canedit}"><a href="wxdw/jlrj.do?project_id=${obj[0].id}<c:if test="${obj[1] == '' }">&id=${obj[0].jlrj_id }</c:if>" target="dialog" rel="jlrj" width="528" height="297" title="监理日志">${empty obj[0].jlrjtbzq ? '默认3' : obj[0].jlrjtbzq}天</a></c:if>
							<c:if test="${empty param.canedit}">${empty obj[0].jlrjtbzq ? '默认3' : obj[0].jlrjtbzq}天</c:if>
						</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${obj[0].create_date }"/></td>
						<td>${obj[0].zt }</td>
						<td>${obj[0].xcms }</td>
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

			<div class="pagination" targetType="navTab"	totalCount="${totalCount}" numPerPage="${numPerPage}" currentPage="${pageNum}"></div>

		</div>
	</div>
</div>