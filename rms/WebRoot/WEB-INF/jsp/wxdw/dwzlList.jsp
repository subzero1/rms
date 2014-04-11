<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<form id="pagerForm" method="post" action="">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />
</form>
		<div class="panelBar">
			<ul class="toolBar">
				<li>
					<a class="attach"
						href="dispath.do?url=uploadFile.jsp&project_id=${wxdw_id}&doc_id=${wxdw_id}&module_id=93&slave_type=10"
						target="dialog" width="400" height="260" ref="upload" title="附件上传"><span>上传</span>
					</a>
				</li>
				<li class="line">
					line
				</li>
			</ul>
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
					<th style="width: 120px;">附件名</th>
					<th style="width: 80px;">用户</th>
					<th style="width: 150px;">上传时间</th>
					<th style="width: 80px;">标记</th>
					<th style="width: 250px;">上传地址</th>
					<th style="width: 80px;"></th>
				</tr>
			</thead>
			<tbody>
				<c:set var="offset" value="0" />
				<c:forEach var="obj" items="${te01List}">
					<c:set var="offset" value="${offset+1}" />
					<tr target="wxdw_id" rel="${obj[0]}">
						<td style="width: 120px;">
							<a href="download.do?slave_id=${obj[0] }" target="_blank">${obj[1] }</a>
						</td>
						<td style="width: 80px;">
							${obj[2] }
						</td>
						<td style="width: 150px;">
							${obj[3] }
						</td>
						<td style="width: 80px;">
							${obj[4] }
						</td>
						<td style="width: 250px;">
							${obj[5] }
						</td>
						<td style="width: 80px;">
							<a href="javascript:del_slave(${obj[0] }, ${offset - 1})"><img border="0" src="Images/trash.gif" style="cursor:pointer" alt="删除"></a>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${offset<numPerPage}">
					<c:forEach begin="${offset}" end="${numPerPage-1}">
						<tr>
							<c:forEach begin="0" end="5">
								<td></td>
							</c:forEach>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>