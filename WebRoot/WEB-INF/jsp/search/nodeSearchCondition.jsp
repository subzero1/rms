<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="pss"%>
<jsp:useBean id="now" class="java.util.Date" />

<script language="javascript">

//从列表中获取已经设置的查询条件
$(function(){
	//避免冲突,删除统计表的条件缓存;
	$("#mainReportCondition").empty();
	
	$p = $(navTab._getPanel("${param.navtab }") || document);
	var src_o = $("#pagerForm input", $p);
	var targ_o = $("#conditionForm input");
	src_o.each(function(){
		var src_input = this;
		if($(src_input).attr("name")=="fields_select"){
			$("#conditionForm #fields option[value="+$(src_input).val()+"]").attr("selected",true);
			moveAct('fields','fields_select');
		}
		if($(src_input).attr("name")!="fields_select" && $(src_input).attr("name")!="ids"){
			targ_o.each(function(){
				var targ_input = this;
				if($(src_input).attr("name") == $(targ_input).attr("name"))
					$(targ_input).val($(src_input).val());
			});
		}
	});
});
</script>

<div class="page" style="overflow-x: hidden">
	<div class="pageContent" style="overflow-x: hidden">
		<form id="conditionForm" action="" method="post">
			<input type="hidden" id="page" name="page" value="${page}" />
			<input type="hidden" id="module_id" name="module_id"
				value="${param.module_id}" />

			<div class="pageFormContent" layoutH="56">


				<!-- 右侧条件选择 -->
				<div style="width: 400px; position: absolute; top: 0px; left: 10px;">

					<div class="title01">
						<h3>
							查询条件：
						</h3>
					</div>
					<b class="b1"></b><b class="b2"></b><b class="b3"></b><b class="b4"></b>
					<div class="contentc" style="height: 158px;">
						<table width="400" border="0" cellspacing="0" cellpadding="0"
							style="border-collapse: collapse;">
							<tr>
								<td>
									<table id="tab_table">
										<tr>
											<c:if test="${fn:length(selectList) > 0}">

											</c:if>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<div id="content_div"
										style="width: 410px; height: 135px; overflow-y: auto;"
										class="scroll-body">
										<div style="display: block;">
											<table width="400" cellspacing="0" cellpadding="0" border="0"
												class="data-table2" style="border-collapse: collapse;">
											</table>

										</div>
										<div style="display: block;">
											<table width="400" border="0" cellspacing="0" cellpadding="0"
												class="data-table2" style="border-collapse: collapse;">
												<tr>
													<td class="t-right">
														表单名称：
													</td>
													<td>
														<pss:htmlSelect style="width:286px"
															objectForOption="bdmcList" showForOption="name"
															valueForOption="id" name="bdmc_id" value="" />
													</td>
												</tr>
												<c:forEach var="obj" items="${selectList}">

													<tr>
														<td width="90" class="t-right">
															${obj.comments }：
															<input type="hidden" name="ids" value="${obj.id }" />
														</td>
														<td width="310">
															<input name="${obj.id }" fieldName="${obj.name }"
																value="" style="width: 280px;"
																title="双击选择${obj.comments }"
																ondblclick="javascript:$.pdialog.open('${obj.selecturl }&name='+this.name, 'sel_key', '选择${obj.comments }', {mask:true,width:500,height:400});" />
														</td>
													</tr>
												</c:forEach>
												<tr>
													<td width="90" class="t-right">
														文档状态：
													</td>
													<td width="310">
														<select name="doc_status">
															<option value="0,1,2,4">
																未处理
															</option>
															<option value="8">
																已处理
															</option>
														</select>
													</td>
												</tr>
											</table>
										</div>

									</div>
								</td>
							</tr>
						</table>
					</div>
					<b class="b4"></b><b class="b3"></b><b class="b2"></b><b class="b1"></b>
					<b class="b1"></b><b class="b2"></b><b class="b3"></b><b class="b4"></b>
					<div class="contentc">
						<div style="height: 24px;">
							<table width="400" height="23" border="0" cellspacing="0"
								cellpadding="0" style="border-collapse: collapse;">
								<tr>
									<td>
										<div style="position: absolute;">
											<input type="hidden" name="type" value="2" />
											是否具体到人：
											<input type="checkbox" name="toperson" value="yes" />
										</div>
										<div>

										</div>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<b class="b4"></b><b class="b3"></b><b class="b2"></b><b class="b1"></b>
					<div class="contentc">
						<div style="height: 24px;">
							<table width="400" height="23" border="0" cellspacing="0"
								cellpadding="0" style="border-collapse: collapse;">
								<tr>
									<td>
										<div style="position: absolute;">
											<input type="hidden" name="type" value="2" />
											<input id="template_name" name="template_name" value=""
												style="width: 136px;" />
										</div>
										<div>
											<select id="template_sel" name="template_id"
												style="width: 160px"
												onchange="javascript:changeTemplate(searchCtCallback);">
												<option value="">
													新建模板
												</option>
												<c:forEach var="obj" items="${templateList}">
													<option value="${obj.id }">
														${obj.name }
													</option>
												</c:forEach>
											</select>
											&nbsp;
											<input type="button" value="保存模板"
												onclick="javascript:saveTemplate(this,1);" />
											<input type="button" value="删除模板"
												onclick="javascript:delTemplate(this);" />
										</div>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<b class="b4"></b><b class="b3"></b><b class="b2"></b><b class="b1"></b>
				</div>
			</div>
			<div class="formBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="button"
									onclick="javascript:searchOrExcelExport(this, 'search/jdcltj.do?module_id=${param.module_id}&navtab=${param.navtab}', dialogToNavTabSearch, '${param.navtab }|fields_select');">
									查 询
								</button>
							</div>
						</div>
					</li>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="button"
									onclick="javascript:searchOrExcelExport(this, 'search/searchListExport.do');">
									EXCEL导出
								</button>
							</div>
						</div>
					</li>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button type="Button" class="close">
									取 消
								</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>

