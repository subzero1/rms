<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<script type="text/javascript">
	function addKcry(id,name){
		if ($("#ids",$.pdialog.getCurrent()).val().indexOf(","+id)==-1){
			$("#ids",$.pdialog.getCurrent()).val($("#ids",$.pdialog.getCurrent()).val()+","+id);
			$("#names",$.pdialog.getCurrent()).val($("#names",$.pdialog.getCurrent()).val()+ ($("#names",$.pdialog.getCurrent()).val() == "" ? "" : ",")+name);
			$("#names1",$.pdialog.getCurrent()).val($("#names1",$.pdialog.getCurrent()).val()+ ($("#names1",$.pdialog.getCurrent()).val() == "" ? "" : ",")+name);
		}
	}
	$(function(){
		$("#daihui",$.pdialog.getCurrent()).click(function(){     
			var names = $("#names",$.pdialog.getCurrent()).val(); 
			var ids = $("#ids",$.pdialog.getCurrent()).val().substring(1);
			var names = $("#names",$.pdialog.getCurrent()).val();
			$.bringBack({'SSDW':names,'SSDW_IDS':ids}); 
		});
		
		$("#clear",$.pdialog.getCurrent()).click(function(){
		$("#ids",$.pdialog.getCurrent()).val("");
		$("#names",$.pdialog.getCurrent()).val("");
		$("#names1",$.pdialog.getCurrent()).val("");
		});
	});
</script>

<form id="pagerForm" action="">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
	<input type="hidden" name="keyword" value="${param.keyword }"/>
	<input type="hidden" name="ids" value="${param.ids }"/>
	<input type="hidden" name="names" value="${param.names }"/>
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="form/selectDept.do" onsubmit="return dwzSearch(this, 'dialog');">
	<input type="hidden" name="ids" id="ids" value="${param.ids }"/>
	<input type="hidden" name="names" id="names" value="${param.names }"/>
	<div class="searchBar" style="height:60px">
		<ul class="searchContent" style="height:30px">
			<li>
				<label>关键字:</label>
				<input class="textInput" name="keyword" style="width:200px;" value="${param.keyword }" type="text"/>
			</li>  
		</ul>
		<div class="subBar">
		<div style="float:left">
				<label>已选:</label>
				<input class="textInput" name="name" style="width:200px;" readonly="readonly" id="names1" value="${param.names }" type="text"/>
		</div>
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="clear">清除</button></div></div></li>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" id="daihui">确定</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="118" targettype="dialog" width="100%">
		
		<thead>
			<tr>
				<th orderfield="mc">名称</th> 
				<th width="40">选择</th>
			</tr>
		</thead>				
		<tbody>
			<c:forEach items="${objList}" var="obj"> 
			<tr>
				<td>${obj[1]}</td> 
				<td>
						<a class="btnSelect" href="javascript:addKcry('${obj[0] }','${obj[1]}')" title="查找带回">
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