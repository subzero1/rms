<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script language="javascript">
function searchListExport(){
	$form = $("#pagerForm", navTab.getCurrentPanel());
	if($form.find("input").size() == 4){
		alertMsg.warn("没有可输出信息!");
		return;
	}
	$form.attr("action","search/searchListExport.do");
	$form.submit();
	$form.attr("action","");
}

function open(zh,node_name){
/*
	var main_window = getHigherWin('pss_main',window);
	if(main_window!=null){
		main_window.swithNewOperDesk('dispath.do?url=blank.jsp','文档处理情况');
		var ps = main_window.getPageRowSize();
		document.form3.target = "pop_ifrm_blankpageRowSize"+ps;
	}
	*/
	alert("sfsfs");
	document.getElementById("zh").value=zh;
	document.getElementById("node_name").value=node_name;
	setTimeout("document.form3.submit()", 500);
}
	
	function getHigherWin(win_name,begin_win,begin_num){
	if(begin_num==null)begin_num=1;
	if(begin_num>5) return null;
	
	var url = begin_win.document.URL;
	var urls=url.substring(url.length-1,url.length);
	if(url.include('index')|| url.include('login') || url.include('logout') || urls=='/') {
		return null;
	}
	if(begin_win == null) return null;
	else{
		if(begin_win.name == win_name) return begin_win;
		else {
			if(begin_win.parent){
				begin_num++;
				return getHigherWin(win_name,begin_win.parent,begin_num);
			}
			else return null;
		}
	}
}

</script>

<form id="pagerForm" method="post" action="">
	<input type="hidden" name="pageNum" value="${page}" />
	<input type="hidden" name="numPerPage" value="${pageRowSize}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" id="module_id" name="module_id"
		value="${param.module_id}" />
	<c:forEach var="obj" items="${searchField}">
		<input type="hidden" name="ids" value="${obj[0]}" />
		<input type="hidden" name="${obj[0] }" value="${obj[2]}" />
	</c:forEach>
	<c:forEach var="obj" items="${fields_select}">
		<input type="hidden" name="fields_select" value="${obj}" />
	</c:forEach>
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);"
			action="sysManage/userList.do" method="post">
			<div class="searchBar">
				<div class="subBar">
					<ul>
						<li>
							<a class="button"
								href="search/nodecondition.do?module_id=${param.module_id }&navtab=${param.navtab }"
								target="dialog" width="460" height="350" rel="searchCondition"
								title="设置查询条件"><span>条件过滤</span> </a>
						</li>
						<li>
							<a class="button" href="javascript:searchListExport();"
								title="EXCEL导出"><span>EXCEL导出</span> </a>
						</li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<table class="table" width="100%" layouth="85">
			<thead>
				<tr>
					<th>
						表单
					</th>
					<th>
						流程图
					</th>
					<!-- 初始化标题名称 -->
					<c:set var="offset1" value="0" scope="page" />
					<c:forEach items="${entityList}" var="title">
						<c:set var="offset1" value="${offset1+1}" scope="page" />
						<th>
							${title .comments}
						</th>
					</c:forEach>

				</tr>
			</thead>
			<tbody>

				<c:set var="offset" value="0" scope="page" />
				<c:forEach var="obj" items="${resultList }">
					<c:set var="offset" value="${offset + 1}" scope="page" />
					<tr>
						<td>
							<c:if test="${obj[1] != null }">
								<a
									href="javascript:openFlowForm('{project_id:${obj[1][3]},doc_id:${obj[1][5]},module_id:${obj[1][4]},opernode_id:${obj[1][6]},node_id:${obj[1][7]},user_id:${obj[1][8]}}');"
									title="表单[${doc[cols].project_id}]"><img border="0"
										src="Images/form.gif" style="cursor: pointer" />
								</a>
							</c:if>
						</td>
						<td align="center">
							<c:if test="${obj[1] != null}">
								<a href="showTree.do${obj[1][2]}" target="navTab" rel="showTree"
									title="流程图[${obj[1][3]}]"><img border="0" src="Images/node.gif"
										style="cursor: pointer" /> </a>
							</c:if>
						</td>
						<c:forEach items="${obj[0]}" var="o">
							<td>
								${o }
							</td>
						</c:forEach>
					</tr>
				</c:forEach>

				<c:if test="${offset<pageRowSize }">

					<c:forEach begin="1" end="${pageRowSize-offset}">
						<tr>
							<c:forEach begin="0" end="15">
								<td align="center">
								</td>
							</c:forEach>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span>
				<select class="combox" name="numPerPage"
					onchange="navTabPageBreak({numPerPage:this.value})"
					selectValue="${numPerPage}">
					<option value="20">
						20
					</option>
					<option value="50">
						50
					</option>
					<option value="100">
						100
					</option>
					<option value="200">
						200
					</option>
				</select>
				<span>共${totalRows}条 </span>
			</div>

			<div class="pagination" formId="searchPageForm" targetType="navTab"
				totalCount="${totalRows}" numPerPage="${pageRowSize}"
				currentPage="${page}"></div>

		</div>
	</div>
</div>

<form name="form1" id="form1" action="wdclqk.do" method="post">
	<input type="hidden" name="toexcel" id="toexcel" />
	<input type="hidden" name="pageRowSize" value="${pageRowSize}" />
	<input type="hidden" id="page" name="page" value="${page}" />
	<input type="hidden" id="totalPages" name="totalPages"
		value="${totalPages}" />
	<input type="hidden" id="totalRows" name="totalRows"
		value="${totalRows}" />
	<input type="hidden" name="ssdq" value="${ssdq }" />
	<input type="hidden" name="zydl" value="${zydl }" />
	<input type="hidden" name="qkdl" value="${qkdl }" />
	<input type="hidden" name="tzlb" value="${tzlb }" />
	<input type="hidden" name="gclb" value="${gclb }" />
	<input type="hidden" name="zyxx" value="${zyxx }" />
	<input type="hidden" name="qkxl" value="${qkxl }" />
	<input type="hidden" name="zh" value="${param.zh }" />
	<input type="hidden" name="doc_status" value="${param.doc_status }" />
	<input type="hidden" name="node_name" value="${param.node_name }" />
	<input type="hidden" name="bdmc_id" value="${param.bdmc_id }" />
	<input type="hidden" name="toperson" value="${param.toperson }" />

</form>

<iframe style="height: 0px; width: 0px;" name="template"></iframe>