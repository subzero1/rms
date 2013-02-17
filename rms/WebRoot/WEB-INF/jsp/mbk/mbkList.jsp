<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script type="">
	function lzjl(mbk_id){
		var url = 'mbk/lzjl.do?mbk_id='+mbk_id;
		$.pdialog.open(url,'_lzjl','流转记录',{width:700,height:400});
	}

function searchListExport(){
	$form = $("#pagerForm", navTab.getCurrentPanel());
	$mbk=$("#mbkdata",navTab.getCurrentPanel());
	if($mbk.find("tr").size() == 0){
		alertMsg.warn("没有可输出信息!");
		return;
	} 
	$form.attr("action","mbk/mbkToExcel.do?config=mbk_source");  
	$form.submit();  
	$form.attr("action","");
} 
</script>

<form id="pagerForm" method="post" action="mbk/mbkList.do">
	<input type="hidden" name="zymc" value="${param.zymc}">
	<input type="hidden" name="ssdq" value="${param.ssdq}">
	<input type="hidden" name="lb" value="${param.lb}">
	<input type="hidden" name="zt" value="${param.zt}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="mbk/mbkList.do" method="post"onsubmit="return navTabSearch(this);">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>地区：<netsky:htmlSelect name="ssdq" id="ssdq" objectForOption="dqList" valueForOption="" showForOption="" value="${param.ssdq}" extend="" extendPrefix="true" /></td>
						<td>类别：<netsky:htmlSelect name="lb" id="lb" objectForOption="lbList" valueForOption="" showForOption="" value="${param.lb}" extend=""  extendPrefix="true" /></td>
						<td>状态：<netsky:htmlSelect name="zt" id="zt" objectForOption="ztList" valueForOption="" showForOption="" value="${param.zt}" extend="" extendPrefix="true" /></td>
						<td>资源名称：<input id="zymc" name="zymc" value="${param.zymc}" type="text" size="25" /></td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" onClick="javascript:searchOrExcelExport(this,'mbk/mbkList.do',navTabSearch);">检 索</button></div></div></li>
						<c:if test="${not empty rolesMap['20101']}">
							<li><a class="button" href="search/searchList.do?module_id=1&navtab=mbkASList" target="navTab" rel="mbkASList" title="目标库高级查询"><span>高级查询</span></a></li>
						</c:if>
						</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<c:if test="${not empty rolesMap['20101']}">
					<li><a class="add" href="mbk/mbkEdit.do" target="navTab" rel="mbk" title="目标库信息"><span>添加</span></a></li>
					<li class="line">line</li>
					<li><a class="delete" href="mbk/ajaxMbkDel.do?id={mbk_id}&del=${param.del }" target="ajaxTodo" title="确认删除吗？"><span>删除</span></a></li>
					<li class="line">line</li>
					<li><a class="edit" href="mbk/mbkEdit.do?id={mbk_id}" target="navTab" rel="mbk" title="目标库信息"><span>修改</span></a></li>
					<li class="line">line</li>
					<li> <a class="exportexcel" href="dispath.do?url=form/mbkImport.jsp" target="dialog" width="400" height="200"><span>导入</span></a></li>
					<li class="line">line</li>
					<li> <a class="exportexcel" href="javascript:searchListExport();" ><span>导出</span></a></li>
					<li class="line">line</li>
				</c:if>
					<!-- <li><a class="helponline"	href="javascript:enterHelp('mbk')"><span>在线帮助</span></a></li>
					<li class="line">line</li> -->
			</ul>
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
					<th style="width:70px;"></th>
					<th orderField="zymc">资源名称</th>
					<th orderField="ssdq">地区</th>
					<th style="width: 120px;" orderField="jsxz">建设性质</th>
					<th style="width: 100px;" orderField="lb">类别</th>
					<th style="width: 80px;" orderField="tdr">谈点人</th>
					<th style="width: 80px;" orderField="cjsj">创建时间</th>
					<th style="width: 80px;" orderField="zt">状态</th>
				</tr>
			</thead>
			<tbody id="mbkdata">
				<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${mbkList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr target="mbk_id" rel="${obj[0].id}">
						<td style="text-align:center;">
							<c:if test="${obj[0] != null}">								
								<a href="javascript:lzjl('${obj[0].id }')" rel="lzjl" ><img border="0" src="Images/node.gif" style="cursor:pointer"/></a>
								&nbsp;
								<a class="add" href="jlgt/jlgtView.do?module_id=90&doc_id=${obj[0].id }" target="navTab" rel="jlgtView"><img border="0" src="Images/track_record.png" style="cursor:pointer"/></a>
							</c:if>		
						</td>
						<td><a href="mbk/mbkEdit.do?id=${obj[0].id}&listType=${obj[1].listType }" target="navTab" rel="mbk" title="目标库信息">${obj[0].zymc }</a></td>
						<td>${obj[0].ssdq }&nbsp;</td>
						<td>${obj[0].jsxz }</td>
						<td>${obj[0].lb }</td>
						<td>${obj[0].tdr }</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${obj[0].cjsj }"/></td>
						<td>${obj[0].zt }</td>
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