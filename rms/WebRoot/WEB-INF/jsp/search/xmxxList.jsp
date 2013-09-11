<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<script type="text/javascript">
	function searchListExport(){
	   var $pagerForm=$("#pagerForm",navTab.getCurrentPanel());
	   $pagerForm.attr("action","search/xmxxListExport.do"); 
	   $pagerForm.submit();
	}

 	function searchSubmit(){
 		var $keyword=$("#keyword",navTab.getCurrentPanel());
 		navTab.openTab('xmxxList', 'search/xmxxList.do?keyword='+$keyword.val(), {title:'项目信息列表'});
 	}
 	$(function(){
 		$("#keyword",navTab.getCurrentPanel()).keyup(function(e){
 			if(e.which==13)
 				searchSubmit();
 		});
 		
 	});
</script>
<form id="pagerForm" method="post" action="search/xmxxList.do">
	<input type="hidden" name="keyword" value="${param.keyword}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />
	<input type="hidden" name="xmzt" value="${xmzt }"/>
	<input type="hidden" name="xmgly" value="${param.xmgly }"/>
	<input type="hidden" name="ssdq" value="${param.ssdq }"/>
	<input type="hidden" name="beginjssj" value="${beginjssj }"/>
	<input type="hidden" name="endjssj" value="${endjssj }"/>
	<input type="hidden" name="beginsjjgsj" value="${beginsjjgsj }"/>
	<input type="hidden" name="endsjjgsj" value="${endsjjgsj }"/>
	<input type="hidden" name="beginlxsj" value="${beginlxsj }"/>
	<input type="hidden" name="endlxsj" value="${endlxsj }"/>
	<input type="hidden" name="config" value="td01_xmxxList" />
</form>

<div class="page">
	<div class="pageHeader">
		<form action="search/xmxxList.do" method="post"
			onsubmit="return navTabSearch(this);">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>
							<input type="text" style="display: none" />
							关键字：
							<input id="keyword" name="keyword" value="${keyword}" type="text" size="25" />&nbsp;&nbsp;&nbsp;&nbsp;
								决算时间：
							<input id="beginjssj" name="beginjssj" value="${beginjssj}" type="text"  class="date" size="10"/>
							至<input id="endjssj" name="endjssj" value="${endjssj}" type="text"  class="date" size="10"/>&nbsp;&nbsp;&nbsp;&nbsp;
								状态：
							<select id="xmzt" name="xmzt" value="${param.xmzt}">
							<option value="" >-----------</option>
							<option value="立项完成" <c:if test="${param.xmzt=='立项完成'}">selected</c:if>>立项完成</option>
							<option value="施工开始" <c:if test="${param.xmzt=='施工开始'}">selected</c:if>>施工开始</option>
							<option value="施工完成" <c:if test="${param.xmzt=='施工完成'}">selected</c:if>>施工完成</option>
							</select>&nbsp;&nbsp;&nbsp;&nbsp;
							所属地区：
							<netsky:htmlSelect name="ssdq" objectForOption="areaList" style="width:92px;" valueForOption="name"  extend=""  extendPrefix="true"  showForOption="name" value="${param.ssdq}" htmlClass="td-select"/>
							&nbsp;&nbsp;&nbsp;&nbsp;
							项目经理：
							<netsky:htmlSelect name="xmgly" objectForOption="xmglyList" style="width:92px;" valueForOption="name"  extend=""  extendPrefix="true"  showForOption="name" value="${param.xmgly}" htmlClass="td-select"/>
						</td>
					</tr>
				</table>
				<div class="subBar">
							竣工时间：
							<input id="beginsjjgsj" name="beginsjjgsj" value="${beginsjjgsj }" class="date" size="10"/>
							至<input id="endsjjgsj" name="endsjjgsj" value="${endsjjgsj}" type="text"  class="date" size="10"/>&nbsp;&nbsp;&nbsp;&nbsp;
							立项时间：<input id="beginlxsj" name="beginlxsj" value="${beginlxsj }" class="date" size="10"/>
							至<input id="endlxsj" name="endlxsj" value="${endlxsj}" type="text"  class="date" size="10"/>
					<ul>
						<li>
							<div class="buttonActive">
								<div class="buttonContent">
									<button type="button"
										onClick="javascript:searchOrExcelExport(this,'search/xmxxList.do',navTabSearch);">
										检 索
									</button>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<c:if test="${node_id == '10101'}">
					<li>
						<a class="add"
							href="flowForm.do?module_id=101&node_id=10101&flow_id=101"
							target="navTab" rel="xmxx" title="项目信息单"><span>添加</span>
						</a>
					</li>
					<li class="line">
						line
					</li>
					<li>
						<a class="delete" href="form/ajaxXmxxDel.do?id={xm_id}"
							target="ajaxTodo" title="确认删除吗？"><span>删除</span>
						</a>
					</li>
					<li class="line">
						line
					</li>
					<!--<li>
					<a class="batchmodify"	href="dispath.do?url=form/batchUpdateProject.jsp" target="dialog" rel="batchUpdateProject" width="400" height="200"><span>批量修改</span></a>
					</li>
					<li class="line">line</li>-->
					<li>
						<a class="exportexcel"
							href="dispath.do?url=form/xlsImport.jsp?config=batch_update_xm"
							target="dialog" width="400" height="200"><span>导入</span>
						</a>
					</li>
					<li class="line">
						line
					</li>
				</c:if>
				 
					<li> <a class="exportexcel" href="javascript:searchListExport();" ><span>导出</span></a></li>
					<li class="line">line</li>
					<!--
					<li><a class="helponline"	href="javascript:enterHelp('xmxx')"><span>在线2帮助</span></a></li>
					<li class="line">line</li>
					-->
			</ul>
		</div>
		<table class="table" width="100%" layouth="138">
			<thead>
				<tr>
					<th style="width: 60px;"></th>
					<th orderField="xmmc">
						项目名称
					</th>
					<th style="width: 115px;" orderField="xmbh">
						项目编号
					</th>
					<th style="width: 60px;" orderField="ssdq">
						所属地区
					</th>
					<th style="width: 60px;" orderField="xmgly">
						项目经理
					</th>
					<th style="width: 200px;" orderField="sgdw">
						施工单位
					</th>
					<th style="width: 75px;" orderField="sjjgsj">
						竣工时间
					</th>
					<th style="width: 75px;" orderField="jssj">
						决算时间
					</th>
					<th style="width: 70px;" orderField="xmzt">
						项目状态
					</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="offset" value="0" />
				<c:forEach var="obj" items="${xmxxList}">
					<c:set var="offset" value="${offset+1}" />
					<tr target="xm_id" rel="${obj.id}">
						<td style="text-align: center;">
							<a class="add"
								href="javascript:navTab.openTab('xmsgjd', 'wxdw/xmsgjd.do?id=${obj.id }', {title:'项目施工进度'});"
								rel="gcsgjd"><img border="0" src="Images/chart_bar.png"
									style="cursor: pointer" />
							</a>&nbsp;&nbsp;
							<a class="add"
								href="jlgt/jlgtView.do?module_id=101&doc_id=${obj.id }"
								target="navTab" rel="jlgtView"><img border="0"
									src="Images/track_record.png" style="cursor: pointer" />
							</a>
						</td>
						<td>
							<a
								href="openForm.do?project_id=${obj.id }&module_id=101&doc_id=${obj.id }&user_id=${user.id }&limit=${limit }&node_id=${node_id }"
								target="navTab" rel="xmxx" title="${obj.xmmc }">${obj.xmmc }</a>
						</td>
						<td>
							${obj.xmbh }&nbsp;
						</td>
						<td>
							${obj.ssdq }
						</td>
						<td>
							${obj.xmgly }
						</td>
						<td>
							${obj.sgdw }
						</td>
						<td>
							<fmt:formatDate value="${obj.sjjgsj }" pattern="yyyy-MM-dd" />
						</td>
						<td>
							<fmt:formatDate value="${obj.jssj }" pattern="yyyy-MM-dd"/>
						</td>
						<td>
							${obj.xmzt }
						</td>
					</tr>
				</c:forEach>
				<c:if test="${offset<numPerPage}">
					<c:forEach begin="${offset}" end="${numPerPage-1}">
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td> 
							<td></td> 
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
				<span>共${totalCount}条 </span>
			</div>

			<div class="pagination" targetType="navTab"
				totalCount="${totalCount}" numPerPage="${numPerPage}"
				currentPage="${param.pageNum}"></div>

		</div>
	</div>
</div>