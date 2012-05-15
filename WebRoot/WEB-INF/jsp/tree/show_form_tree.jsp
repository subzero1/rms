<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
/**
 * @author Chiang 2010-02-10
 * 表单流程显示
 * @param List<String> node_list
 */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>主流程图</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" media="screen,projection" type="text/css" href="css/show_tree.css" />
<link rel="stylesheet" media="screen,projection" type="text/css" href="css/main.css" />
<script type="text/javascript" src="js/prototype.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/appCommon.js"></script>
<script language="javascript">
function do_click(flow_id,node_id,project_id,module_id,doc_id,node_status){
	if(node_status == -1)
		window.location.href = 'showTree.do?flow_id=' + flow_id;
	else{
		window.location.href = 'showTree.do?project_id=' + project_id + "&module_id=" + module_id + "&doc_id=" + doc_id;
	}
	//window.openCustomWin('showTree.do?flow_id=' + flow_id,550,300,'auto','tree');
}
function popFlowForm(url){
	var main_win = getHigherWin('pss_main',window);
	if(main_win != null)main_win.popOperWeb(url);
	else window.location.href = url;
}
</script>
</head>
<body leftmargin="10" topmargin="10">
	<div id="auto-in">
		 <c:if test="${not empty param.project_id}">
			 <div class="in-l in-m" onClick="javascript:openCustomWin('tree/spyj.do?project_id=${param.project_id }&module_id=${param.module_id }',700,350,'no');" title="查看当前工程所有表单审批明细">审批明细</div>
			 <div class="in-r"></div>
		 </c:if>
		 <div class="in-l in-m" onClick="javascript:doClose();" title="关闭当前流程">关 闭</div>
		 <div class="in-r"></div>
  	</div>
	<div style="height:35px"></div>
	<div id="tree-title">
  		<h1>${title} 流程图</h1>
  	</div>
	<div id="tree">
		<c:forEach var="obj" items="${line_list}">
			<div style='width:1px;height:1px;position: absolute; z-index: 1; left: ${obj.x }px; top: ${obj.y }px;background-color:${obj.color };'>
			</div>
		</c:forEach>
		<c:forEach var="obj" items="${word_list}">
			<div style='width:10px;height:10px;position: absolute; z-index: 1; left: ${obj.x }px; top: ${obj.y }px;' class='${obj.htmlClass }'>
				${obj.name }
			</div>
		</c:forEach>
		<c:forEach var="obj" items="${node_list}">
			<div style='width:${obj.width }px;height:${obj.height }px;position: absolute; z-index: 1; left: ${obj.x }px; top: ${obj.y }px;' class='${obj.htmlClass }' onclick='javascript:do_click(${obj.param })'>
				${obj.name }
			</div>
		</c:forEach>
	</div>
	<div id="form-select">
		<form name="form1" action="showFormTree.do" method="post">
			<c:if test="${not empty flowList}">			
				<select name="flow_id" onchange="javascript:document.form1.submit();">
					<c:forEach var="obj" items="${flowList}">
						<option value="${obj.id }"
						<c:if test="${obj.id == param.flow_id }">
							selected
						</c:if>
						>${obj.name }</option>
					</c:forEach>
				</select>			
			</c:if>
		</form>
		<ul id="tree-form">
			<c:forEach var="obj" items="${forms}">
				<c:if test="${obj.node_status != -1}">
					<li onclick="javascript:popFlowForm('openForm.do?project_id=${obj.project_id }&module_id=${obj.module_id }&doc_id=${obj.doc_id }&${admin }');" onmouseover="this.style.color='#b00000';" onmouseout="this.style.color=''">${obj.node_name}</li>
				</c:if>
			</c:forEach>
		</ul>
		<br/><br/>
	</div>
	<div>
		<div style="float:left;" id="color">
			节点颜色标识：
			<ul>
				<li> *.未走</li>
				<li> 1.正在处理</li>
				<li> 8.办结</li>
			</ul>
		</div>
	</div>
	<div>&nbsp;</div>
</body>
</html>
