<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
$(document).ready(function(){	
	$("#year").change(function(){
		navTab.reload('infoManage/zywhList.do',{data:{"year":$(this).val()}, navTabId:"zywhList"});
	});
});
</script>

<h2 class="contentTitle">专业维护
</h2>
<div class="sysmanage_left" style=" float:left; display:block; margin:10px; overflow:auto; width:22%; height:430px; border:solid 1px #CCC; line-height:21px; background:#FFF;">

	<ul class="tree expand">
		<li><a href="">${year}专业列表</a>
			<ul id="node_li">
			<c:forEach var="gczy" items="${gczy_list}">
			   <li><a href="infoManage/zywhEdit.do?id=${gczy.id}" target="loadFileArea" rel="zywhEdit">${gczy.zymc }</a></li>
			</c:forEach>
			</ul>
		</li>
	</ul>
</div>
<div id="zywhEdit" class="loadFileArea sysmanage_right" loadfile="infoManage/zywhEdit.do?year=${year}&id=<c:out value="${param.id}" default="${gczy_list[0].id}"/>" style=" float:left; display:block; margin:10px; overflow:hidden; width:68%; height:430px; border:solid 1px #CCC; line-height:21px; background:#FFF;">
</div>