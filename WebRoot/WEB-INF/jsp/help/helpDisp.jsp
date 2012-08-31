<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script language="javascript">
function callQuote(){
	quoteRepository('${repository.id }');
	navTab.closeCurrentTab();
}
function tf(o)
{
	var s=$(o).attr("title");
	//alert(s);
	if($.trim($(o).next().text())!=null&&$.trim($(o).next().text())!="")
	{if(s=="+")
	{
		$(o).find("img").eq(0).attr("src","Images/minus.gif");
		$(o).find("img").eq(1).attr("src","Images/folderOpen.gif");
	$(o).next().css("display","inline");

	$(o).next().find("table tr:even").css("display","inline");
	$(o).attr("title","-");
	}
	else 
		{
		$(o).find("img").eq(0).attr("src","Images/plus.gif");
		$(o).next().css("display","none");
		$(o).find("img").eq(1).attr("src","Images/folderClosed.gif");
		$(o).attr("title","+");

		}
	}
}
$(function(){
	$("#test").css("display","none");
	$("#test").find("table").andSelf().attr("border","0");
$("#test td:empty").each(function(i,o){

$(o).parent().prev().find("img").eq(0).attr("src","Images/minus.gif");
$(o).parent().prev().find("img").eq(1).attr("src","Images/file.gif");
$(o).parent().prev().attr("title","-");

});
});
</script>
<style>
.soltest div { overflow:hidden; height:auto;}
.panelFooter{position:absolute;width:94%;}
.panelFooterContent{position:absolute;bottom:10px;width:94%;}
</style>
<div style="float:left; display:block; margin:0px;height:auto;line-height:21px; background:#FFF;width:100%;overflow:auto;">
	<div class=" soltest"  style="width:94%;float:left;margin:10px;overflow-x:visible;overflow-y:auto;" layouth="28">
	<div class="panel">
		<h1>知识库问题</h1>
		<div style="overflow-x:visible;">
			<div class="repository-details">	
				<span>分类：</span><span id="b07_type">${repository.type}</span>
				<span class="v-split">|</span>
				<span>创建时间：<fmt:formatDate value="${repository.create_time}"  pattern="yyyy-MM-dd"/></span>
				<span class="v-split">|</span>
				<span>创建人：</span><span id="b07_creator">${repository.creator}</span>
				<span class="v-split">|</span>
				<span>审核状态：
					<c:choose>
					<c:when test="${repository.status==1}">已通过</c:when>
					<c:otherwise>未通过</c:otherwise>
					</c:choose>
				</span>
			</div>	
			<pre><span id="b07_key">${repository.key}</span></pre>
			
			<div class="repository-solution">
				<pre><b>故障现象</b>
				<br/><span id="b07_question">${repository.question}</span></pre>
			</div>
			<div>
				<b>解决方法</b> &nbsp;<c:if test="${param.quote == 1}">
				<a href="javascript:callQuote();" class="listbutton">引用</a>
				<a  target="dialog" width="300" height="50" title="发送邮件" href="business/repositoryMail.do?id=${repository.id }" class="listbutton">发送邮件</a>
				</c:if>
				<br/>
<c:if test="${not empty repository.repDetails}">
	<table border="0" style="width: 100%;background-color: #eee;font-size:13px;" id="test">
		<c:forEach var="b08" items="${repository.repDetails}">
			<tr  style="display: inline;" onclick="tf(this);">
				<td width="5%"></td><td width="95%">${b08.remark}</td>
			</tr>
			<tr style="display: none;">
				<td colspan="2"><c:if test="${not empty b08.repDetails}">
					<table border="0" style="width: 100%">
						<c:forEach var="b08a" items="${b08.repDetails}">
							<tr  style="display: none;" onclick="tf(this);">
								<td></td><td>${b08a.remark}</td>
							</tr>
							<tr style="display: none;">
								<td colspan="2"><c:if test="${not empty b08a.repDetails}">
									<table border="0" style="width: 100%">
										<c:forEach var="b08b" items="${b08a.repDetails}">
											<tr  style="display: none;" onclick="tf(this);">
												<td></td><td>${b08b.remark}</td>
											</tr>
											<tr style="display: none;">
												<td colspan="2"><c:if test="${not empty b08.repDetails}">
													<table border="0" style="width: 100%" >
														<c:forEach var="b08c" items="${b08b.repDetails}">
															<tr  style="display: none;" onclick="tf(this);">
																<td></td><td>${b08c.remark}</td>
															</tr>
															<tr style="display: none;">
																<td colspan="2"><c:if test="${not empty b08c.repDetails}">
																	<table border="0" style="width: 100%">
																		<c:forEach var="b08d" items="${b08c.repDetails}">
																			<tr  style="display: none;" onclick="tf(this);">
																				<td></td><td>${b08d.remark}</td>
																			</tr>
																			<tr style="display: none;">
																				<td colspan="2"><c:if test="${not empty b08d.repDetails}">
																					<table border="0" style="width: 100%">
																						<c:forEach var="b08e" items="${b08d.repDetails}">
																							<tr  style="display: none;" onclick="tf(this);">
																								<td></td><td>${b08e.remark}</td>
																							</tr>
																							<tr style="display: none;">
																								<td colspan="2"></td>
																							</tr>
																						</c:forEach>
																					</table>
																				</c:if>
																				</td>
																			</tr>
																		</c:forEach>
																	</table>
																</c:if>
																</td>
															</tr>
														</c:forEach>
													</table>
												</c:if></td>
											</tr>
										</c:forEach>
									</table>
								</c:if></td>
							</tr>
						</c:forEach>
					</table>
				</c:if></td>
			</tr>
		</c:forEach>
	</table>
</c:if>
			</div>
		</div>
</div>
			<div style="clear:none;border-left:solid 1px #b8d0d6;border-right:solid 1px #b8d0d6;border-bottom:solid 1px #b8d0d6;background:#eef4f5;">
				<div id="businessList" class="loadFileArea"
					loadfile="business/repositoryTree.do?showEdit=no&repository_id=${repository.id }">
				</div>
			</div>
	</div>
</div>