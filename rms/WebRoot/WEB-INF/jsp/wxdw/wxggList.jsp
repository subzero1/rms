<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script type="text/javascript">
		$("#fbr_search",navTab.getCurrentPanel()).keyup(function(e){
			if (e.which == 13){
				$("#searchButton",navTab.getCurrentPanel()).click();
			}
		});
		     function del_check(id){
		     alert(id);
			}
		</script>

<form id="pagerForm" method="post" action="">
	<input type="hidden" name="mc" value="${param.mc}">
	<input type="hidden" name="lb" value="${param.lb}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="wxdw/wxggList.do?role_id=${role_id}" method="post">
			<input type="hidden" id="selectedId_demo" />
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>关键字：<input id="fbr_search" name="fbr_search" value="${fbr_search}" type="text" size="25" />
						<input type="text" style="display:none;"/>
						</td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="searchButton" onClick="javascript:searchOrExcelExport(this,'wxdw/wxggList.do?role_id=${role_id}',navTabSearch);">检 索</button></div></div></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="wxdw/wxgg.do?role_id=${role_id}" target="navTab" rel="wxgg" title="添加"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="edit" href="wxdw/wxgg.do?role_id=${role_id}&id={wxgg_id}" target="navTab" rel="wxgg" title="外协公告维护"><span>修改</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="wxdw/delWxgg.do?id={wxgg_id}" target="ajaxTodo" title="确认删除吗？"><span>删除</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
					
					<th orderField="ggzt">公告标题</th>
					<th width="60" orderField="jjcd">紧急程度</th>
					<th width="70" orderField="cdfw">传达范围</th>
					<th width="100" orderField="fbsj">发布时间</th>
					<th width="60" orderField="fbr_mc">发布人员</th>
					<th width="40">发送</th>
					<th width="40">已阅</th>
					<th width="40">回复</th>
					<c:if test="${isWxdw}">
					<th width="60">已读/未读</th>
					</c:if>
				</tr>
			</thead>
			<tbody>
			<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${wxggList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr target="wxgg_id" rel="${obj[0].id}">
					    <c:set var="offset" scope="page" value="${offset + 1}"/>
						<td><a href="wxdw/wxgg.do?role_id=${role_id}&id=${obj[0].id}" target="navTab" rel="wxgg" title="外协公告维护">${obj[0].ggzt }</a><c:if test="${obj[0].zt == 0 }"><font color='red'>(未发送)</font></c:if></td>
						<td style="text-align:center">${obj[0].jjcd }</td>
						<td style="text-align:center">${obj[0].cdfw }</td>
						<td style="text-align:center"><fmt:formatDate value="${obj[0].fbsj }" pattern="yyyy-MM-dd HH:mm"/></td>
						<td>${obj[0].fbr_mc } </td>
						<td style="text-align:center">${obj[2] }</td>
						<td style="text-align:center">${obj[3] }</td>
						<td style="text-align:center">${obj[4] } </td>
						<c:if test="${isWxdw}">
						<td style="text-align:center"><c:if test="${obj[1]=='1' }"><img src="Images/online_ok.gif" title="已读"/></c:if></td>
						</c:if>
					</tr>
				</c:forEach>
				<c:if test="${offset<numPerPage}">
				<c:forEach begin="1" end="${numPerPage-offset}">
					<tr>	
						<td>&nbsp;</td>
						<td>&nbsp;</td>
	                    <td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<c:if test="${isWxdw}">
						<td>&nbsp;</td>
						</c:if>
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