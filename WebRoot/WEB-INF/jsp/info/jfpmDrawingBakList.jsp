<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<script>
function ghxzt(slave_id,jf_id){
	alertMsg.confirm("确认替换吗?", {
	okCall: function(){
	$.ajax({
		    url: 'changeDrawingFromBak.do?slave_id='+slave_id+'&jf_id='+jf_id,
		    type: 'POST',
		    dataType: 'json',
		    error: DWZ.ajaxError,
		    success: dialogAjaxDone,
			error: DWZ.ajaxError
		});
	}
	});
}
</script>

<div class="page">
	<div class="pageHeader">
		<form action="info/jfpmList.do" method="post">
			<div class="searchBar">&nbsp;</div>
		</form>
	</div>
	<div class="pageContent">
		<table class="table" width="100%">
			<thead>
				<tr>
					<th style="width: 180px;" >图纸名称</th>
					<th style="width: 80px;" >上传人</th>
					<th style="width: 100px;" >上传时间</th>
					<th style="width: 100px;" >操作</th>
					<th>&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="obj" items="${jfpmDrawingBakList}">
					<tr>
						<td>${obj.file_name }</td>
						<td>${obj.user_name }&nbsp;</td>
						<td><fmt:formatDate value="${obj.ftp_date }" pattern="yyyy-MM-dd" /></td>
						<td>&nbsp;&nbsp;&nbsp;<a href="download.do?slave_id=${obj.id }">[下载]</a>&nbsp;&nbsp;&nbsp;<a href="javascript:ghxzt('${obj.id}','${obj.doc_id}')">[更换]</a></td>
						<td>&nbsp;</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>