<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	<h2 class="contentTitle">用户维护</h2>
	<div class="sysmanage_left" style=" float:left; display:block; margin:10px; overflow:auto; width:22%; height:500px; border:solid 1px #CCC; line-height:21px; background:#FFF;">
	<ul class="tree expand collapse">
		<li><a href="">用户列表</a>
		 <ul>
			<c:forEach var="menu" items="${areaList}">
				<li>
					<a href="#">${menu.name}</a>
					<ul>
						<c:forEach var="nodeElement" items="${user_map[menu.name]}">
							<li class="node_li">
								<a href="sysManage/userEdit.do?id=${nodeElement.id}"
									target="loadFileArea" rel="userEdit">${nodeElement.name}</a>
							</li>
						</c:forEach>
					</ul>
			</c:forEach>
		 </ul>
		</li>
	</ul>
	</div>
	<div id="userEdit" class="loadFileArea sysmanage_right" loadfile="sysManage/userEdit.do?id=<c:out value="${param.user_id}" default="1"/>" style=" float:left; display:block; margin:10px; overflow:hidden; width:72%; height:500px; border:solid 1px #CCC; line-height:21px; background:#FFF;">
		
	</div>
