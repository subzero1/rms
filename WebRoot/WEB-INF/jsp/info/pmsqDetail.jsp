<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script language="javascript">
//图纸上传回调函数
function updrawingCallback(json){
	DWZ.ajaxDone(json);
	if (json.statusCode == DWZ.statusCode.ok){
		navTab.reloadFlag("autoform${param.module_id}${param.sqd_id}");
		setTimeout(function(){
			$.pdialog.closeCurrent();
			var dialog = $("body").data("pmsqDetail");
			if (dialog){
				var url = dialog.data("url");
				$.pdialog.open(url, "pmsqDetail", "机房图纸",{});
			}
		}, 100);
	}
}

function download(id,td12_id){
	var flag = false;
	$.ajax({
	    url: 'info/sessionTimeout.do',
	    data:{id:id,td12_id:td12_id},
	    type: 'POST',
	    async: false,
	    dataType: 'json',
	    error: DWZ.ajaxError,
	    success: function(json){
	        DWZ.ajaxDone(json);
			if (json.statusCode == DWZ.statusCode.ok){
				var _msg = json.message;
				if(_msg != null && (_msg.indexOf('冲突') != -1 || _msg.indexOf('旧') != -1)){
					alertMsg.warn(_msg);
				}
				//$.get("download.do?slave_id="+json.id);
				flag = true;
				$.get("info/ajaxSetDownloadTime.do?td12_id="+json.td12_id);
			}
			if (flag){
				ifra.location.href="download.do?slave_id="+json.id;
			}  
	    }
	});
}

function upload2(spdpmid,module_id,project_id,doc_id,slave_type){
	$.ajax({
	    url: 'info/ajaxUploadCondition.do',
	    data:{spdpmid:spdpmid,module_id:module_id,project_id:project_id,doc_id:doc_id,slave_type:slave_type},
	    type: 'POST',
	    dataType: 'json',
	    error: DWZ.ajaxError,
	    success: function(json){
	        DWZ.ajaxDone(json);
			if (json.statusCode == DWZ.statusCode.ok){
				$.pdialog.open('dispath.do?url=uploadFile.jsp', 'uploadFile', '附件上传', {mask:true,width:400,height:250,data: {slave_id:spdpmid,module_id:module_id,project_id:project_id,doc_id:doc_id,slave_type:slave_type,callback:"updrawingCallback"}});
			}  
	    }
	});
}
function ghxzt(){
	alertMsg.confirm("确认替换吗?", {
	okCall: function(){
	$.ajax({
		    url: 'ghxzt.do?td12_id=${param.doc_id }',
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

<form id="pagerForm" method="post" action="">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="pmsqDetail.do" method="post">
			<input type="hidden" id="selectedId_demo" />
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td colspan="2"><span style="font-size:14px;font-weight:bold;">机房名称：${jfmc }</span></td>
					</tr>
					<tr>
						<td>
							<span style="color:blue;">机房规划图： </span>
							<c:choose>
								<c:when test="${not empty jfght.id }">
									<a href="download.do?slave_id=${jfght.id }">[下载]</a>
								</c:when>
								<c:otherwise>
									无
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<span style="color:blue;">机房现状图： </span>
							<c:choose>
								<c:when test="${not empty jfxzt.id }">
									<a href="javascript:download('${jfxzt.id }','${param.doc_id }')">[下载]</a>
								</c:when>
								<c:otherwise>
									无
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:if test="${canReplace=='yes'}">
								<span><a href="javascript:ghxzt();" style="color:#f00;font-weight:bold;font-size:14px">[<c:if test="${not empty td12.zhgxsj}">重新</c:if>更换现状图]</a></span>
							</c:if>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
			<li><c:if test="${canUpload=='yes'}">
					<a class="add" href="javascript:upload2('${sqdpmid }','${param.module_id }','${param.project_id}','${param.doc_id}','${slave_type }')" width="400" height="260"><span>上传图纸</span></a>
				</c:if>
			</li>
			</ul>
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
					<th orderField="xmmc">项目名称</th>
					<th style="width: 110px;" orderField="">申请时间</th>
					<th style="width: 60px;" orderField="">设计员</th>
					<th style="width: 100px;" orderField="">设计员电话</th>
					<th style="width: 40px;" orderField="">图纸</th>
					<th style="width: 110px;" orderField="">上传时间</th>
					<th style="width: 110px;" orderField="">审核通过时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="obj" items="${pmsqDetailList}">
					<tr>
						<td <c:if test="${param.doc_id==obj[1].id }">style="color:red"</c:if>>${obj[0].xmmc }</td>
						<td><fmt:formatDate value="${obj[0].cjrq }" pattern="yyyy-MM-dd HH:mm" />&nbsp;</td>
						<td>${obj[0].sjry }</td>
						<td>${obj[3].mobile_tel }</td>
						<td><a href="download.do?slave_id=${obj[2].id }"><img border="0" src="Images/file.gif"/></a></td>
						<td><fmt:formatDate value="${obj[2].ftp_date }" pattern="yyyy-MM-dd HH:mm" /></td>
						<td><fmt:formatDate value="${obj[1].zhgxsj }" pattern="yyyy-MM-dd HH:mm" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span>
				<select class="combox" name="numPerPage" onchange="dialogPageBreak({numPerPage:this.value})" selectValue="${numPerPage}">
					<option value="20">20</option>
					<option value="50">50</option>
					<option value="100">100</option>
					<option value="200">200</option>
				</select>
				<span>共${totalCount}条 </span>
			</div>

			<div class="pagination" targetType="dialog"	totalCount="${totalCount}" numPerPage="${numPerPage}" currentPage="${pageNum}"></div>

		</div>
	</div>
</div>
<iframe height="0" width="0" name="ifra"></iframe>