<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
$(document).ready(function(){	
	$("#year").change(function(){
		navTab.reload('infoManage/qkwhList.do',{data:{"year":$(this).val()}, navTabId:"qkwhList"});
	});
});
</script>

<h2 class="contentTitle">投资切块维护
<select id="year" style="width:110px;">
		<c:forEach var="i" begin="2012" end="${curYear+1}">
			<c:choose>
				<c:when test="${year == i }">
					<option selected value="${i}">${i}年度</option>
				</c:when>
				<c:otherwise>
					<option value="${i}">${i}年度</option>
				</c:otherwise>
			</c:choose>
		</c:forEach>								
	</select>
</h2>
<div class="sysmanage_left" style=" float:left; display:block; margin:10px; overflow:auto; width:22%; height:430px; border:solid 1px #CCC; line-height:21px; background:#FFF;">

	<ul class="tree expand">
		<li><a href="">${year}投资切块列表</a>
			<ul id="node_li">
			<c:forEach var="tzqk" items="${tzqk_list}">
			   <li><a href="infoManage/qkwhEdit.do?id=${tzqk.id}" target="loadFileArea" rel="qkwhEdit">${tzqk.qkmc }</a></li>
			</c:forEach>
			</ul>
		</li>
	</ul>
</div>
<div id="qkwhEdit" class="loadFileArea sysmanage_right" loadfile="infoManage/qkwhEdit.do?year=${year}&id=<c:out value="${param.id}" default="${tzqk_list[0].id}"/>" style=" float:left; display:block; margin:10px; overflow:hidden; width:68%; height:430px; border:solid 1px #CCC; line-height:21px; background:#FFF;">
</div>