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
			var kcsj=$("#kcsj",$.pdialog.getCurrent()).val();
			var sm=$.trim($("#sm",$.pdialog.getCurrent()).val());  
			if($("#kcsj",$.pdialog.getCurrent()).val().length<=0){
				alertMsg.info("请输入勘察时间！");
			}else if($("#sm",$.pdialog.getCurrent()).val().length<=0){
				alertMsg.info("请输入说明！");
			}else if($("#ids",$.pdialog.getCurrent()).val().length<=0){
				alertMsg.info("请选择四方勘察人员！");
			}else { 
				var names = $("#names",$.pdialog.getCurrent()).val();
				alertMsg.confirm("确认选择【"+names+"】为四方勘察人员吗？",{
					okCall:function(){
						var ids = $("#ids",$.pdialog.getCurrent()).val().substring(1);  
						$.bringBack({'Kcry':ids,'sm':sm,'kcsj':kcsj});
					}
				});
			}
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
	<input type="hidden" name="ids" value="${param.ids }"/>
	<input type="hidden" name="names" value="${param.names }"/>
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="mbk/getKcry.do" onsubmit="return dwzSearch(this, 'dialog');">
	<input type="hidden" name="ids" id="ids" value="${param.ids }"/>
	<input type="hidden" name="names" id="names" value="${param.names }"/>
	<div class="searchBar" style="height:120px">
		<ul class="searchContent" style="height:80px">
			<li>
				<label>姓 名:</label>
				<input class="textInput" name="name" style="width:200px;" value="${param.name }" type="text"/>
			</li>
			<li>
				<label>勘察时间:</label>
				<input class="date" pattern="yyyy-MM-dd" name="kcsj" id="kcsj" style="width:200px;"  type="text"/>
			</li>
			<li>
				<label>说明:</label>
				<input class="textInput" name="smx" style="width:200px;" id="sm" type="text"/>
			</li>
		</ul>
		<div class="subBar">
		<div style="float:left">
				<label>已 选:</label>
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
				<th orderfield="name">姓名</th>
				<th orderfield="name">部门</th>
				<th width="30">选择</th>
			</tr>
		</thead>				
		<tbody>
			<c:forEach items="${staffList}" var="kcry"> 
			<tr>
				<td>${kcry[0].name }</td>
				<td>${kcry[1].name }</td>
				<td>
						<a class="btnSelect" href="javascript:addKcry('${kcry[0].id }','${kcry[0].name }')" title="查找带回">
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