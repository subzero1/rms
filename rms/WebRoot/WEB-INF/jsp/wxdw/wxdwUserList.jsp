<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>


		<div class="panelBar">
			<ul class="toolBar">
				<c:if test="${not empty param.wxdw_id }">
					<li><a class="add" href="wxdw/wxdwUserEdit.do?wxdw_id=${param.wxdw_id }" target="dialog" width="500" height="260" rel="wxdwUser" title="外协单位用户配置"><span>添加</span></a></li>
					<li class="line">line</li>
					<li><a class="edit" href="wxdw/wxdwUserEdit.do?wxdw_id=${param.wxdw_id }&id={user_id}" target="dialog" width="500" height="260" rel="wxdwUser" title="外协单位用户配置"><span>修改</span></a></li>
					<li class="line">line</li>
					<li><a class="exportexcel"	href="wxdw/wxdwUserList.do?wxdw_id=${param.wxdw_id }&toExcel=yes" target="dwzExport" targetType="navTab"><span>导出</span></a></li>
					<li class="line">line</li>
				</c:if>
			</ul>
		</div>
		
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
					<th style="width: 80px;" orderField="login_id">工号</th>
					<th style="width: 80px;" orderField="name">姓名</th>
					<th style="width: 120px;" orderField="mobile_tel">移动电话</th>
					<th style="width: 50px;" orderField="sex">性别</th>
					<th style="width: 150px;" orderField="email">邮箱</th>
					<th>相关岗位</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${wxdwUserList}" var="wxdwUser">
					<tr target="user_id" rel="${wxdwUser.id}">
						<td><a href="wxdw/wxdwUserEdit.do?wxdw_id=${param.wxdw_id }&id=${wxdwUser.id}" target="dialog" width="500" height="260" rel="wxdwUser" title="外协单位用户配置">${wxdwUser.login_id }</a></td>
						<td><a href="wxdw/wxdwUserEdit.do?wxdw_id=${param.wxdw_id }&id=${wxdwUser.id}" target="dialog" width="500" height="260" rel="wxdwUser" title="外协单位用户配置">${wxdwUser.name }</a></td>
						<td>${wxdwUser.mobile_tel }</td>
						<td>${wxdwUser.sex }</td>
						<td>${wxdwUser.email }</td>
						<td>
						<c:forEach items="${user_staMap[wxdwUser]}" var="ta02">
							${ta02.name }&nbsp;&nbsp;
						</c:forEach>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>