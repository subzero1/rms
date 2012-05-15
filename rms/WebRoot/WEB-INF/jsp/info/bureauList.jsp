<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h2 class="contentTitle">局点维护</h2>
<div class="sysmanage_left" style=" float:left; display:block; margin:10px; overflow:auto; width:25%; height:430px; border:solid 1px #CCC; line-height:21px; background:#FFF;">
<ul class="tree collapse">
		<c:forEach var="menu" items="${areaList}">
			<li>
				<a href="#">${menu.name}</a>
				<ul>
					<c:forEach var="nodeElement" items="${bureauListMap[menu.name]}">
						<li>
							<a href="sysManage/bureauEdit.do?id=${nodeElement.id}"
								target="loadFileArea" rel="bureauEdit">${nodeElement.name}</a>
						</li>
					</c:forEach>
				</ul>
		</c:forEach>
</ul>
</div>
<div id="bureauEdit" class="loadFileArea sysmanage_right" loadfile="sysManage/bureauEdit.do?id=${param.bureau_id}" style=" float:left; display:block; margin:10px; overflow:auto; width:65%; height:4300px; border:solid 1px #CCC; line-height:21px; background:#FFF;">
	
</div>