<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:useBean id="now" class="java.util.Date" />

<%
	request.setAttribute("x_n", "\n");
	request.setAttribute("x_img", "<img class='small'");
%>
<style>
.small{
	width:20%;
	height:20%;
}
.big{
	width:100px;
	height:100%;
}
</style>
<script type="text/javascript">
function huifu(up_id,title,bt){
		$.pdialog.open("jlgt/newJlgt.do?doc_id=${param.doc_id}&module_id=${param.module_id}&up_id="+up_id,"newJlgt",title,{"data":{"bt":bt,"content":"${bdxxList[0]['value']}"},"width":"630","height":"425"});
	}
	$(function(){
		$(".toggleA",navTab.getCurrentPanel()).click(function(){
			if ($(this).text()=="展开"){
				$(this).text("收起");
			}else {
				$(this).text("展开");
			}
			$(this).closest("div.right").find("div.neibuDiv").slideToggle("slow");
		});
		
		$(".small").each(function(){
			$(this).width("100px").height("100px").css("cursor","pointer");
		});
		$(".small").live("click",function(){
			$(this).removeClass("small");
			$(this).addClass("big");
			$(this).stop().animate({width:"90%",height:"90%"},"1000");
		});
		$(".big").live("click",function(){
			$(this).removeClass("big");
			$(this).addClass("small");
			$(this).stop().animate({width:"100px",height:"100px"},"1000");
		});
	});
</script>
<div id="jlgt" style="padding-left:20px;background:#fafafa;" layoutH="0">
	<div class="bdxxDiv" style="margin:20px 0px 0px 0px;">
		<c:forEach items="${bdxxList}" var="bdxx">
			<div style="margin:0 5px;">
				<span style="width: 150px; font-size: 14px; font-weight: bold">${bdxx['key']
					}：</span>
				<span
					style="color: rgb(120, 120, 120); font-size: 14px;">${bdxx['value']
					}</span>
			</div>
		</c:forEach>
	</div>
	<div style="text-align:right;width:800px;">
		<a style="color: #2b96e1;"
							href="javascript:huifu('','新的发言','');">发言</a>
	</div>
	<div class="jlgtDiv" style="margin-bottom:10px;">
		<c:forEach items="${yjJlgtList}" var="yjJlgt">
			<div
				style="border: solid rgb(217, 217, 217) 1px; background:eef7fb;); width: 800px;">				<div class="left" style="float: left; background: transparent;">
					<div style="margin: 10px; color: rgb(255, 127, 62)">
						${yjJlgt[1].name }
					</div>
					<div style="margin: 10px;">
						${yjJlgt[1].mobile_tel }
					</div>
				</div>
				<div class="right" style="margin-left: 130px; background: white;padding-right:15px;padding-bottom:10px;">
					<div class="content" style="min-height: 150px; padding: 10px;max-width: 650px;overflow-x:auto;overflow-y: hidden">
						${fn:replace(fn:replace(yjJlgt[0].nr, '<img', x_img), x_n, '<br />')}
					</div>
					<c:if test="${not empty ejJlgtMap[yjJlgt[0]]}">
						<div style="float: right; margin-left: 10px;">
							<a style="color: #2b96e1;" href="#" class="toggleA">展开</a>
						</div>
					</c:if>
					<div style="float: right; margin-left: 10px;">
						<a style="color: #2b96e1;"
							href="javascript:huifu('${yjJlgt[0].id }','回复','回复 ${yjJlgt[1].name }');">回复</a>
					</div>
					<div style="float: right; color: rgb(180,180,180);font:lighter 11px;">
						<fmt:formatDate value="${yjJlgt[0].fbsj }"
							pattern="yyyy-MM-dd HH:mm" />
					</div>
					<div style="margin-left: 10px;">
						&nbsp;<c:if test="${not empty yjJlgt[2]}">附件：</c:if>
						<c:forEach items="${yjJlgt[2]}" var="slave">
							<a style="color: #2b96e1;" href="download.do?slave_id=${slave.id }">${slave.file_name}</a>
						</c:forEach>
					</div>
					<c:if test="${not empty ejJlgtMap[yjJlgt[0]]}">
						<div class="neibuDiv"
							style="background: rgb(250, 250, 250); display: none; margin-left: 25px; margin-top: 10px; border: solid rgb(244, 244, 244) 1px;">
							<c:forEach items="${ejJlgtMap[yjJlgt[0]]}" var="ejJlgt">
								<div
									style="min-height: 80px; border-bottom: dashed rgb(204, 204, 204) 1px;">
									<div class="content" style="min-height: 50px; margin: 10px;max-width: 600px;overflow-x:auto;overflow-y: auto">
										${fn:replace(ejJlgt[0].nr, x_n, '<br />')}
									</div>
									<div
										style="float: right; margin-left: 10px; margin-right: 10px;">
										<a style="color: #2b96e1;"
											href="javascript:huifu('${yjJlgt[0].id }','回复','回复 ${ejJlgt[1].name }');">回复</a>
									</div>
									<div style="float: right;color: rgb(180,180,180);font:lighter 11px;">
										<fmt:formatDate value="${ejJlgt[0].fbsj }"
											pattern="yyyy-MM-dd HH:mm" />
									</div>
									<div style="margin-left: 10px;">
										&nbsp;<c:if test="${not empty ejJlgt[2]}">附件：</c:if>
										<c:forEach items="${ejJlgt[2]}" var="slave">
											<a style="color: #2b96e1;"
												href="download.do?slave_id=${slave.id }">${slave.file_name}</a>
										</c:forEach>
									</div>
								</div>
							</c:forEach>
						</div>
					</c:if>
				</div>

			</div>
		</c:forEach>
	</div>
</div>
