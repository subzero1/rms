<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<script type="text/javascript">
	function addhsry(id,name){
		if ($("#ids",$.pdialog.getCurrent()).val().indexOf(","+id)==-1){
			$("#ids",$.pdialog.getCurrent()).val($("#ids",$.pdialog.getCurrent()).val()+","+id);
			$("#names",$.pdialog.getCurrent()).val($("#names",$.pdialog.getCurrent()).val()+","+name);
		}
	}
	$(function(){
		$("#daihui",$.pdialog.getCurrent()).click(function(){
			if ($("#ids",$.pdialog.getCurrent()).val().length>0){
				var names = $("#names",$.pdialog.getCurrent()).val().substring(1);
				alertMsg.confirm("确认选择"+names+"为四方会审人员吗？",{
					okCall:function(){
						var ids = $("#ids",$.pdialog.getCurrent()).val().substring(1);
						$.bringBack({'Hsry':ids});
					}
				});
			} else {
				alertMsg.error("请选择会审人员！");
			}
		});
	});
</script>

<form id="pagerForm">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
	<input type="hidden" name="ids" value="${param.ids }"/>
	<input type="hidden" name="names" value="${param.names }"/>
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="mbk/getHsry.do" onsubmit="return dwzSearch(this, 'dialog');">
	<input type="hidden" name="ids" id="ids" value="${param.ids }"/>
	<input type="hidden" name="names" id="names" value="${param.names }"/>
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>姓名:</label>
				<input class="textInput" name="name" value="${param.name }" type="text"/>
			</li>
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="daihui">带回</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="118" targettype="dialog" width="100%">
		
		<thead>
			<tr>
				<th orderfield="name">姓名</th>
				<th width="60">选择</th>
			</tr>
		</thead>				
		<tbody>
			<c:forEach items="${hsryList }" var="hsry">
			<tr>
				<td>${hsry.name }</td>
				<td>
						<a class="btnSelect" href="javascript:addhsry('${hsry.id }','${hsry.name }')" title="查找带回">
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="panelBar">
		<div class="pages">
			<span>每页</span>
			<select class="combox" name="numPerPage" onchange="dialogPageBreak({numPerPage:this.value})" selectValue="${numPerPage}">
				<option value="10">10</option>
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
			</select>
			<span>条，共${totalCount}条</span>
		</div>
		<div class="pagination" targettype="dialog" totalCount="${totalCount}" numPerPage="${numPerPage}" orderField="${orderField }" currentPage="${param.pageNum}"></div>
	</div>
</div>