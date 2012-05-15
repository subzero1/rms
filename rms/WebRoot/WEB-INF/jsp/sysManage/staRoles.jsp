<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="page">
	<div class="pageContent">
		<form action="sysManage/saveStaRoles.do" method="post" class="pageForm"  onSubmit="return selectSubmit(this,dialogAjaxDone,'t_role');"> 
			<input type="hidden" name="station_id" value="${param.id}">
			<div class="pageFormContent" layoutH="56">
				<table>
					<tr>
						<td>
							<select id="s_role" name="s_role" multiple=1 type=select-multiple  ondblclick="javascript:moveAct('s_role','t_role');" style="width:195px;height:240px;">
							<option style="background-color:#ccc;" disabled>----------未选项----------</option>
							<c:forEach var="obj" items="${unselect_roles}">
								<option title="${obj.remark}"  value="${obj.id}">${obj.name}</option>
							</c:forEach>
							</select>
						</td>
						<td style="width:30px;text-align:center;">
							<input type="button" value="&gt;" onclick="javascript:moveAct('s_role','t_role');"><br/>
							<input type="button" value="&lt;" onclick="javascript:moveAct('t_role','s_role');">
						</td>
						<td>
							<select id="t_role" name="t_role" multiple=1 type=select-multiple   ondblclick="javascript:moveAct('t_role','s_role');" style="width:195px;height:240px;">
							<option style="background-color:#ccc;" disabled >----------已选项----------</option>
							<c:forEach var="obj" items="${select_roles}">
								<option title="${obj.remark}"  value="${obj.id}">${obj.name}</option>
							</c:forEach>
							</select>
							<input type="hidden" id="roles" name="roles"/>
						</td>
					</tr>
				</table>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">确 定</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div>
					</li>
				</ul>
			</div>
		</form>  
	</div>
</div>
