<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="crht"%>
<script type="text/javascript">
function fbtn2(flag)
{
	var v_type = $("#type").val();
	if(v_type == ''){
		$("#tr_type").css({"display":"block"});
	 }
	 else{
		 $("#tr_type").css({"display":"none"});
	 }
	 if(flag == 1){
		$("#tr_type").css({"display":"block"});
	 }
}
function setType()
{
	var type_list = '';
	var obj_sel = $(':input[name=t_type]');
	obj_sel.each(function(i){
		if($(this).attr('checked') == true){
			type_list  = type_list + $(this).val() + ",";
		}
	});
	if(type_list.length > 0){
		type_list = type_list.substring(0,type_list.length -1);
	}
	$('#type').val(type_list);
}
function openDialog(butt){
	var $form = $("#repositoryDelForm");
	if($("#repository_tbody input:checked").size()>0){
		$.pdialog.open('business/billBack.do', '', '票据收回', {mask:true,width:480,height:350,data: $form.serializeArray()});
	} else {
	   alertMsg.error('请先选择待收回票据！')
	}
}
function ajaxDelAll()
{
	if($("#repository_tbody input:checked").size()>0){
		alertMsg.confirm("您确认删除吗！", {
            okCall: function(){
			var $form = $("#repositoryDelForm");
			$.post("business/ajaxRepDelAll.do",$form.serializeArray(),function(){$("#form1").submit();},"json");
			
            }

});
	}
	else {
		   alertMsg.error('请先选择待删除知识库记录！')
		}
}
function isAll()
{
	//alert($("#ckall").attr("checked"));
	if($("#ckall").attr("checked")==true)
	{
		$("#repository_tbody input").attr("checked","checked");
	}
	else
	{
		$("#repository_tbody input").removeAttr("checked");
	}
}
</script>
<form id="pagerForm" method="post" action="">
	<input type="hidden" name="check_status" value="${param.check_status}">
	<input type="hidden" name="type" value="${param.type}">
	<input type="hidden" name="question" value="${param.question}" />
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form id="form1" onsubmit="return navTabSearch(this);"
			action="business/repositoryList.do" method="post">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>标题：</td>
						<td><input name="keywords" value="${param.keywords}" type="text" /></td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li>
							<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
						</li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
	<form id="repositoryDelForm" onsubmit="return navTabCheckbox(this);" action="business/ajaxRepDelAll.do" method="post">
		<div class="panelBar">
			<ul class="toolBar">
			<li><a class="delete" href="javascript:;" onclick="ajaxDelAll();"><span>删除所选</span> </a>
				</li>
				<li>
					<a class="add" href="business/repositoryEdit.do" target="navTab"
						rel="repositoryEdit" title="添加知识库信息"><span>添加</span> </a>
				</li>
				<li>
					<a class="edit"
						href="business/repositoryEdit.do?id={repository_id}"
						target="navTab" rel="repositoryEdit" title="知识库信息修改"><span>修改</span>
					</a>
				</li>
				<li>
					<a class="edit"
						href="business/repositoryDisp.do?id={repository_id}"
						target="navTab" rel="repositoryDisp" title="知识库信息查看"><span>查看</span>
					</a>
				</li>
				<li class="line">
					line
				</li>
			</ul>
		</div>
		<table class="table" width="100%" layouth="170">
			<thead>
				<tr>
				<th style="width:30px;"><input  title="全选/全不选" type="checkbox" id="ckall" onclick="isAll();"/> </th>
					<th  style="width: 250px;" orderField="title">
						标题
					</th>
					<th style="width: 180px;" orderField="keys">
						关键字
					</th>
					<th style="width: 80px;" orderField="recordor">
						创建人
					</th>
					<th style="width: 100px;" orderField="record_date">
						创建时间
					</th>
					<th>&nbsp;</th>
				</tr>
			</thead>
			<tbody id="help_tbody">
				<c:forEach var="i" begin="0" end="${numPerPage}">
					<tr target="help_id" rel="${repositoryList[i].id}">
						<td><c:if test="${not empty repositoryList[i].title}"><input type="checkbox"  name="help_id" value="${helpList[i].id}"></c:if></td>
						<td>
							${repositoryList[i].title }
						</td>
						<td>
							${repositoryList[i].keys }&nbsp;
						</td>
						<td>
							${repositoryList[i].recordor }
						</td>
						<td>
							<fmt:formatDate value="${repositoryList[i].record_date }" pattern="yyyy-MM-dd" />
						</td>
						<td>&nbsp;</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</form>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span>
				<select class="combox" name="numPerPage" change="navTabPageBreak"
					param="numPerPage" selectValue="${numPerPage}">
					<option value="20">
						20
					</option>
					<option value="50">
						50
					</option>
					<option value="100">
						100
					</option>
					<option value="200">
						200
					</option>
				</select>
				<span>共${totalCount}条 </span>
			</div>

			<div class="pagination" targetType="navTab"
				totalCount="${totalCount}" numPerPage="${numPerPage}"
				orderField="${orderField }"
				currentPage="${param.pageNum}"></div>

		</div>
	</div>
</div>