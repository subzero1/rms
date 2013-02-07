<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
 
<form id="pagerForm" method="post" action="wxdw/zytlrList.do">
	<input type="hidden" name="ssdw" value="${param.ssdw}">
	<input type="hidden" name="keyword" value="${param.keyword}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>
<script type="text/javascript">
function searchListExport(){
	$form = $("#pagerForm", navTab.getCurrentPanel());
	$zytlrTab=$("tbody",navTab.getCurrentPanel());
	if($zytlrTab.find("tr").size() == 0){
		alertMsg.warn("没有可输出信息!");
		return;
	} 
	$form.attr("action","wxdw/tlrToExcel.do?config=zytlr");  
	$form.submit();  
	$form.attr("action","");
} 
function saveForm(){
	$("#zytlrForm",navTab.getCurrentPanel()).submit();
}
</script>
<div class="page">
	<div class="pageHeader">
		<form action="wxdw/zytlrList.do" method="post" onsubmit="return navTabSearch(this);">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>
						<input type="text" style="display:none"/>
						关键字：<input id="keyword" name="keyword" value="${param.keyword}" type="text" size="25" /></td>
						<td>所属单位：<netsky:htmlSelect name="ssdw" objectForOption="wxdwList"  style="width:234px;"  onChange="javascript:$(this).submit();" extend="" extendPrefix="true" value="${param.ssdw}" htmlClass="td-select sel" /></td>
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
					<li><a class="save" href="javascript:saveForm();"><span>保存</span></a></li>
					<li class="line">line</li>
					<li><a class="add" href="wxdw/zytlEdit.do" target="navTab" rel="zytlrEdit" title="资源填录人信息"><span>添加</span></a></li>
					<li class="line">line</li>
					<li><a class="delete" href="wxdw/tlrAjaxDel.do?zytl_id={gc_id}" target="ajaxTodo" title="确认删除吗？"><span>删除</span></a></li>
					<li class="line">line</li>
					<li> <a class="exportexcel" href="dispath.do?url=form/zytlrImport.jsp?config=zytlr" target="dialog" width="400" height="200"><span>导入</span></a></li>
					<li class="line">line</li> 
					<li> <a class="exportexcel" href="javascript:searchListExport();" ><span>导出</span></a></li>
					<li class="line">line</li>
			</ul>
		</div>
		<form method="post" id="zytlrForm" action="save.do" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
			<input type="hidden" name="tableInfomation" value="noFatherTable:com.rms.dataObjects.wxdw.Tf31_zytl" />
			<input type="hidden" name="_callbackType" value="forward"/>
			<input type="hidden" name="_message" value="提交数据保存" />
			<input type="hidden" name="_forwardUrl" value="wxdw/zytlrList.do"/>
			<input type="hidden" name="_navTabId" value="zytlrList"  /> 
		<table class="table" width="100%" layouth="138" id="zytlrTab">
			<thead>
				<tr>
					<th style="width:40px;"></th>
					<th style="width: 80px;" orderField="gis_no" >GIS工号</th>
					<th style="width: 80px;" orderField="tlrxm">填录人姓名</th>
					<th style="width: 196px;" orderField="ssdw">所属单位</th>
					<th style="width: 50px;" orderField="nx">年限</th>
					<th style="width: 100px;" orderField="in_time">进入工程中心日期</th>
					<th style="width: 100px;" orderField="rzcj">认证成绩</th>
					<th style="width: 100px;" orderField="phone">联系电话</th>
					<th style="width: 100px;" orderField="zc">专长</th> 
				</tr>
			</thead>
			<tbody>
				<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${Tf31_zytlList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr target="gc_id" rel="${obj[0].id}">
						<td style="text-align:center;">
						${offset }
						</td>
						<td>${obj[0].gis_no }</td>
						<td><a href="wxdw/zytlEdit.do?zytl_id=${obj[0].id }" target="navTab" rel="zytlrEdit" title="资源填录人信息单">${obj[0].tlrxm }</a></td>
						<td>
						<c:if test="${empty obj[1] }">
						<input type="text" name="Tf31_zytl.SSDW" value="${obj[0].ssdw }"  style="padding-right:0px;border:0;width:100%;color:red;"/>
						<input type="hidden" name="Tf31_zytl.ID" value="${obj[0].id }"/>
						</c:if>
						<c:if test="${!empty obj[1] }">
						${obj[0].ssdw }
						</c:if>
						</td>
						<td>${obj[0].nx }</td>
						<td><fmt:formatDate value="${obj[0].in_time }" pattern="yyyy-MM-dd"/></td>
						<td>${obj[0].rzcj }</td>
						<td>${obj[0].phone }</td>
						<td>${obj[0].zc }</td>
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
		</form>
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