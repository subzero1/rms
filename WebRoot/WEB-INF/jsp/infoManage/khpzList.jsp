<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
$(document).ready(function(){	
	$("#year").change(function(){
		navTab.reload('infoManage/zywhList.do',{data:{"year":$(this).val()}, navTabId:"zywhList"});
	});
});
</script>
 
<div class="sysmanage_left"
	style="float: left; display: block; margin: 10px; overflow: auto; width: 22%; height: 430px; border: solid 1px #CCC; line-height: 21px; background: #FFF;">

	<ul class="tree expand">
		<li>
			<a href="">配置列表</a>
			<ul id="node_li">
				<c:forEach var="khpz" items="${khpz_list}">
					<li>
						<a href="infoManage/khpzEdit.do?id=${khpz.id}"
							target="loadFileArea" rel="khpzEdit">${khpz.mc }</a>
					</li>
				</c:forEach>
			</ul>
		</li>
	</ul>
</div>
<div id="khpzEdit" class="loadFileArea sysmanage_right"
	loadfile="infoManage/khpzEdit.do?id=<c:out value="${param.id}" default="${khpz_list[0].id}"/>"
	style="float: left; display: block; margin: 10px; overflow: hidden; width: 68%; border: solid 1px #CCC;  background: #FFF;">
</div>