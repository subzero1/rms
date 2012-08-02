<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
/**
 * @author Chiang 2010-02-10
 * 表单流程显示
 * @param List<String> node_list
 */
%>

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

<div class="page">
	<div class="panelBar">
		<ul class="toolBar">
		 	<li>
				<a class="edit"	href="tree/spyj.do?project_id=${param.project_id }&module_id=${param.module_id }" target="dialog" width="700" height="400" title="查看表单审批明细"><span>审批明细</span></a>
		 	</li>
			<li class="line">line</li>
		 </ul>
  	</div>	
	<div class="pageContent" style="width:100%" layoutH="28">
	
		<div id="tree-title">
	  		<br/><h1 style="font-size:14px;">项目名称：${title}</h1>
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
		</div>
		<!-- 
		<ul id="tree-form">
			<c:forEach var="obj" items="${forms}">
				<c:if test="${obj.node_status != -1}">
					<li onclick="javascript:popFlowForm('openForm.do?project_id=${obj.project_id }&module_id=${obj.module_id }&doc_id=${obj.doc_id }&${admin }');" onmouseover="this.style.color='#b00000';" onmouseout="this.style.color=''">${obj.node_name}</li>
				</c:if>
			</c:forEach>
		</ul>
 		-->
 		
		<div id="color" style="float:left;">  
			节点颜色标识：
			<ul>
				<li> *.未走</li>
				<li> 1.正在处理</li>
				<li> 8.办结</li>
			</ul>
		</div>
	</div>
</div>
