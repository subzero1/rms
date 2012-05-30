<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>


		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="wxdw/wxdwUserEdit.do" target="dialog" width="400" height="300" rel="wxdwUser" title="外协单位用户配置"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="edit" href="wxdw/wxdwUserEdit.do?id={user_id}" target="dialog" width="400" height="300" rel="wxdwUser" title="外协单位用户配置"><span>修改</span></a></li>
				<li class="line">line</li>
				<li><a class="delete"	href="wxdwUserDelAjax.do?id={user_id}" target="ajaxTodo" title="确认删除吗?"><span>删除</span></a></li>
				<li class="line">line</li>
				<li><a class="exportexcel"	href="wxdw/wxdwUserList.do?toExcel=yes" target="dwzExport" targetType="navTab"><span>导出</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
					<th style="width: 80px;" orderField="login_id">工号</th>
					<th style="width: 80px;" orderField="name">姓名</th>
					<th style="width:120px;" orderField="mobile_tel">移动电话</th>
					<th style="width: 50px;" orderField="sex">性别</th>
					<th style="width: 150px;" orderField="email">邮箱</th>
					<th>相关岗位</th>
				</tr>
			</thead>
			<tbody>
				<tr target="wxdw_id" rel="${obj.id}">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</table>