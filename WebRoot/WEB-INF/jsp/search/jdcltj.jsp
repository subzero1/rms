<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script language="javascript">
function searchListExport(){
	$form = $("#pagerForm", navTab.getCurrentPanel());
	if($form.find("input").size() == 4){
		alertMsg.warn("没有可输出信息!");
		return;
	}
	$form.attr("action","search/searchListExport.do");
	$form.submit();
	$form.attr("action","");
}
</script>

<form id="pagerForm" method="post" action="">
	<input type="hidden" name="pageNum" value="${page}" />
	<input type="hidden" name="numPerPage" value="${pageRowSize}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" id="module_id" name="module_id" value="${param.module_id}"/>
	<c:forEach var="obj" items="${searchField}">
		<input type="hidden" name="ids" value="${obj[0]}" />
		<input type="hidden" name="${obj[0] }" value="${obj[2]}" />
	</c:forEach>
	<c:forEach var="obj" items="${fields_select}">
		<input type="hidden" name="fields_select" value="${obj}" />
	</c:forEach>
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="sysManage/userList.do" method="post">
		<div class="searchBar">
			<div class="subBar">
				<ul>
					<li><a class="button" href="search/condition.do?module_id=${param.module_id }&navtab=${param.navtab }" target="dialog" width="815" height="350" rel="searchCondition" title="设置查询条件"><span>条件过滤</span></a></li>
					<li><a class="button" href="javascript:searchListExport();" title="EXCEL导出"><span>EXCEL导出</span></a></li>
				</ul>
			</div>
		</div>
		</form>
	</div>
	<div class="pageContent">
		<table class="table" width="100%" layouth="85">
			<thead>
				<tr>
					<th width="50px" >
							序号
						</th>
					<c:set var="offset1" value="0" scope="page" />
						<c:forEach items="${docColsList}" var="title">
						<c:set var="offset1" value="${offset1+1}" scope="page" />
							<th>${title }</th>
						</c:forEach>
						<c:forEach begin="1" end="${6-offset1}">
									<th align="center">
									<c:set var="offset1" value="${offset1+1}" scope="page" />
									</th>
						</c:forEach>
				</tr>
			</thead>
			<tbody>
				<c:set var="offset" scope="page" value="0"/>
				<c:forEach var="obj" items="${resultList }">
						<c:set var="offset" value="${offset + 1}" scope="page" />
						<tr>
							<td align="center">
									${offset}
							</td>
							<td  align="center">
								${obj["bdmc"] }
							</td>
							<td align="center">
								${obj["jdmc"] }
							</td>
							<td  align="center">
								<a href="javascript:open('${obj["zh"] }','${obj["jdmc"] }');">${obj["wdsl"] }</a>
							</td>
							<td align="center">
								${obj["zh"] }
							</td>
							<td  align="center">
								${obj["xm"] }
							</td>
							<td align="center">
								${obj["dh"] }
							</td>
						</tr>
					</c:forEach>
				
				<tr>
				<td></td>
				</tr>
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
				<span>共${totalRows}条 </span>
			</div>
			
			<div class="pagination" formId="searchPageForm" targetType="navTab" totalCount="${totalRows}" numPerPage="${pageRowSize}" currentPage="${page}"></div>

		</div>
	</div>
</div>