<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<script type="text/javascript">
	function bringBack(tdr,tdr_id,tdrdh){
		$.ajax({
			url:'mbk/getTdbm.do?id='+tdr_id,
			type:'post',
			async:false,
			success:function(msg){
				$.bringBack({'TDR':tdr, 'TDR_ID':tdr_id,'TDRDH':tdrdh,'TDBM':$.trim(msg)});
			}
		});
	}
</script>

<form id="pagerForm" action="">
	<input type="hidden" name="hkzlmb" value="${param.hkzlmb}"/>
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
	<input type="hidden" name="dept_remark" value="${param.dept_remark }"/>
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="mbk/getWthgx.do" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>名  称:</label>
				<input class="textInput" name="name" value="${param.name }" type="text"/>
			</li>
			<!-- 
			<li>
				<label>类 别:</label>
				<select name="dept_remark" >
				<option value="">---------</option>
				<option value="前端" <c:if test="${param.dept_remark=='前端' }">selected</c:if>>前端</option>
				<option value="后端" <c:if test="${param.dept_remark=='后端' }">selected</c:if>>后端</option>
				<option value="区县" <c:if test="${param.dept_remark=='区县' }">selected</c:if>>区县</option>
				<option value="合作单位" <c:if test="${param.dept_remark=='合作单位' }">selected</c:if>>合作单位</option>
				</select>
			
			</li>
			 -->
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="118" targettype="dialog" width="100%">
		<thead>
			<tr>
				<th orderfield="ta01.name" style="width: 100px;">部门</th>
				<th orderfield="ta01.name">名称</th>
				<th width="60">查找带回</th>
			</tr>
		</thead>				
		<tbody>
			<c:forEach items="${tdrList }" var="tdr">
			<tr>
				<td>${tdr.dept_name }</td>
				<td>${tdr.name }</td>
				<td>
					<a class="btnSelect" href="javascript:bringBack('${tdr.name }','${tdr.id }','${tdr.mobile_tel }')" title="查找带回">
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