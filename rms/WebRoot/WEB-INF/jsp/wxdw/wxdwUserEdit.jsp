<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>


<div class="page">

	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="save" href="javascript:"><span>保存</span></a></li>
				<li class="line">line</li>
				<li><a class="exportexcel"	href="wxdw/wxdwList.do?toExcel=yes" target="dwzExport" targetType="navTab"><span>导出</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		
		<table width="100%" class="list" itemdetail="gljfDetail" width="100%">
			<thead>
				<tr>
					<th type="text" style="width:80px;" name="Ta03_user.NAME" hideName="Ta03_user.ID" >姓名</th>
					<th type="text" style="width:120px;" name="Ta03_user.MOBILE_TEL">移动电话</th>
					<th type="text"  style="width:50px;" name="Ta03_user.SEX">性别</th>
					<th type="text" style="width:150px;" name="Ta03_use.EMAIL">邮箱</th>
					<th type="text">岗位</th>
					<th type="del" style="width:30px;">操作</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<input type="hidden" name="Ta03_user.ID" value=""/>
						<input type="text"  name="Ta03_user.NAME" value="" style="width:0px;"/>
					</td>
					<td><input type="text" name="Ta03_user.MOBILE_TEL" value="" style="width:0px;"/></td>
					<td><input type="text" name="Ta03_user.SEX" value="" style="width:0px;"/></td>
					<td><input type="text" name="Ta03_use.EMAIL" value="" style="width:0px;"/></td>
					<td><input type="text" name="" value="" style="width:0px;"/></td>
					<td><a href="javascript:" class="btnDel emptyInput" title="确认删除此明细">删除</a></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>