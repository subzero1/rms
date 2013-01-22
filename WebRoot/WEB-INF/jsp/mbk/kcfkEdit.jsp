<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />
<style> 
.selectArea {
	background:url("Images/online_ok.gif") no-repeat center;
}
</style>
<script language="javascript"> 
function saveForm(){
	$("#mbk_form").submit();
} 
$(function(){
	var $kcfk_td=$(".kcfk_td", navTab.getCurrentPanel());
	var $input;
	var $jg_input;
	var $tr;
	$kcfk_td.css({cursor:"hand"});
	$kcfk_td.click(function(){
		$tr=$(this).closest("tr");
		$input=$("input[name='Td25_kcfkmx\.GS']",$tr);
		$jg_input=$("input[name='Td25_kcfkmx\.JG']",$tr);
	 	if($(this).hasClass("selectArea")){
			$(this).removeClass("selectArea");
			$input.attr("readonly","true");
			$input.val("");
			$jg_input.val(0);  
		}else{
			$(this).addClass("selectArea");
			$input.removeAttr("readonly");
			$input.val(1); 
			$jg_input.val(1);
		}
	});
	//初始化各个选项的值 选中与否
	$tr = $kcfk_td.closest("tr");
	$tr.each(function(){
		$input=$("input[name='Td25_kcfkmx\.GS']",this);
		if($input.val()!=''&&parseInt($input.val())!=0){
			$(this).find("td:eq(1)").addClass("selectArea");
			$input.removeAttr("readonly");
		}
	}); 
	
	
	$kcfksj=$("input[name='Td24_kcfkb\.FKSJ']",navTab.getCurrentPanel());
		if($kcfksj.val()!=''){
			$inputs=$(":input[name^='Td2']");
			$inputs.attr("readonly","readonly");
			$(".kcfk_td",navTab.getCurrentPanel()).attr("disabled","disabled");
			$(".dis_a",navTab.getCurrentPanel()).attr("disabled","disabled");
		}
	
});
function saveForm(){
	$("#kcfk_form",navTab.getCurrentPanel()).submit();
}

 function reportKcfk(obj,kcfk_id){
 		$.ajax({
		type:"post",
		url:"mbk/kcfk.do",
		dataType:"json",
		async:true,
		data:{mbk_id:obj,kcfk_id:kcfk_id},
		success:function(json){ 
			if(json.statusCode == DWZ.statusCode.ok){
					alertMsg.correct(json.message);
					navTab.closeCurrentTab();
				}else if(json.statusCode == DWZ.statusCode.error){
					alertMsg.info(json.message);
				}
		}
		
	});
 }
 function printKcfkTab(){
	$("#kcfkTab",navTab.getCurrentPanel()).jqprint();
}
</script>
<div class="page">
	<!-- 表单头 -->
	<div class="pageHeader">
		<div class="searchBar">
			<!-- 表单名称 -->
			<h1>
				基站勘察反馈单
			</h1>

		</div>
	</div>
	<!-- 主操作按钮 -->
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<a class="save dis_a" href="javascript:saveForm();"><span>保&nbsp;&nbsp;存</span>
				</a>
			</li>
			<li class="line">
				line
			</li>
				<li>
					<a class="icon dis_a"
						href="javascript:reportKcfk('${param.mbk_id}','${Td24_kcfkb.id }');"><span>反&nbsp;&nbsp;馈</span>
					</a>
				</li>
				<li class="line">
					line
				</li>
				<li>
					<a class="attach" href="javascript:docSlave('slave.do?project_id=${Td24_kcfkb.id}&amp;doc_id=${Td24_kcfkb.id}&amp;module_id=91&amp;node_id=-1&amp;opernode_id=-1&amp;user_id=${user.id }&slave_type=9');"
					<c:if test="${empty Td24_kcfkb.id}">disabled</c:if>>
					<span>附&nbsp;&nbsp; 件</span></a>
				</li>
				<li class="line">line</li>
		</ul>
	</div>

	<div class="pageContent" layouth="48">
		<form id="kcfk_form" action="save.do" method="post"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="tableInfomation" value="noFatherTable:com.rms.dataObjects.mbk.Td24_kcfkb" />
			<input type="hidden" name="tableInfomation" value="Td24_kcfkb,id,kcfk_id:com.rms.dataObjects.mbk.Td25_kcfkmx"/>
			<input type="hidden" name="_callbackType" value="forward" />
			<input type="hidden" name="_message" value="保存" />
			<input type="hidden" name="_forwardUrl" value="mbk/kcfkEdit.do?mbk_id=${mbk_id}" />
			<input type="hidden" name="_navTabId" value="kcfkList" />

			<input type="hidden" name="Td24_kcfkb.ID" value="${Td24_kcfkb.id }" />
			<input type="hidden" name="Td24_kcfkb.MBK_ID" value="${param.mbk_id }" />
			<input type="hidden" name="Td24_kcfkb.CJSJ" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			<input type="hidden" name="Td24_kcfkb.CJR" value="${user.name}"/>
			<input type="hidden" name="Td24_kcfkb.FKSJ" value="${Td24_kcfkb.fksj}"/>

		<table class="report" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;" id="kcfkTab">
			<thead>
				<tr>
					<th>类型</th> 
					<th colspan="2">选择(打钩)</th>  
				</tr>
			</thead>
			<tbody>
				<c:forEach var="Td25_kcfkmx" items="${Td25_kcfkmxList}">
					<c:if test="${Td25_kcfkmx[1]=='[1]'}">
						<tr>
							<td style="text-align:center">${Td25_kcfkmx[0].fkx}
							<input type="hidden" name="Td25_kcfkmx.ID" value="${Td25_kcfkmx[0].id}"/>
							<input type="hidden" name="Td25_kcfkmx.KCFK_ID" value="${Td25_kcfkmx[0].kcfk_id}"/>
							<input type="hidden" name="Td25_kcfkmx.FKX" value="${Td25_kcfkmx[0].fkx}"/>
							</td>
							<td colspan="2" class="kcfk_td">
								<input type="hidden" name="Td25_kcfkmx.JG" value="${Td25_kcfkmx[0].jg}"/>
								<input type="hidden" name="Td25_kcfkmx.GS" value="${Td25_kcfkmx[0].gs}"/>
							</td> 
						</tr> 
					</c:if>
				</c:forEach>
				 <c:forEach var="Tc01_property" items="${TableList}">
					<c:if test="${Tc01_property.flag=='[1]'}">
						<tr>
							<td style="text-align:center">${Tc01_property.name }
							<input type="hidden" name="Td25_kcfkmx.ID" value=""/>
							<input type="hidden" name="Td25_kcfkmx.KCFK_ID" value=""/>
							<input type="hidden" name="Td25_kcfkmx.FKX" value="${Tc01_property.name }"/>
							</td>
							<td colspan="2" class="kcfk_td">
								<input type="hidden" name="Td25_kcfkmx.JG" value="${Td25_kcfkmx[0].jg}"/>
								<input type="hidden" name="Td25_kcfkmx.GS" value=""/>
							</td> 
						</tr> 
					</c:if>
				</c:forEach>
				<tr>
					<th style="width: 150px;" >类型</th>
					<th style="width: 300px;">选择(打钩)</th> 
					<th  style="width: 300px;">个数</th>  
				</tr> 
				
				<c:forEach var="Td25_kcfkmx" items="${Td25_kcfkmxList}">
				<c:if test="${Td25_kcfkmx[1]=='[2]'}">
				<tr>
					<td  style="text-align:center">${Td25_kcfkmx[0].fkx}</td>
					<td class="kcfk_td">
						<input type="hidden" name="Td25_kcfkmx.ID" value="${Td25_kcfkmx[0].id}"/>
						<input type="hidden" name="Td25_kcfkmx.KCFK_ID" value="${Td25_kcfkmx[0].kcfk_id}"/>
						<input type="hidden" name="Td25_kcfkmx.FKX" value="${Td25_kcfkmx[0].fkx}"/>
					</td> 
					<td >
					<input type="text" name="Td25_kcfkmx.GS" value="${Td25_kcfkmx[0].gs}" style="height:24px;border:0;"/>
					<input type="hidden" name="Td25_kcfkmx.JG" value="${Td25_kcfkmx[0].jg}"/>
					</td> 
				</tr>  
				</c:if></c:forEach>
				
				<c:forEach var="Tc01_property" items="${TableList}">
				<c:if test="${Tc01_property.flag=='[2]'}">
				<tr>
					<td  style="text-align:center">${Tc01_property.name}</td>
					<td class="kcfk_td">
						<input type="hidden" name="Td25_kcfkmx.ID" value=""/>
						<input type="hidden" name="Td25_kcfkmx.KCFK_ID" value=""/>
						<input type="hidden" name="Td25_kcfkmx.FKX" value="${Tc01_property.name }"/>
					</td> 
					<td >
					<input type="text" name="Td25_kcfkmx.GS" value="" style="height:24px;border:0;"/>
					<input type="hidden" name="Td25_kcfkmx.JG" value="${Td25_kcfkmx[0].jg}"/>
					</td> 
				</tr>  
				</c:if></c:forEach>
				 
				<tr>
					<th >其他说明</th>
					<td  colspan="2">
					<textarea class="td-textarea" style="width:592px;height:42px;border:0;overflow:auto" name="Td24_kcfkb.QTSM">${Td24_kcfkb.qtsm}</textarea>
					</td> 
				</tr> 
				<tr style="width:30px">
					<td colspan="3" style="text-valign:middle">中心签字：<input type="text" style="border:none" name="Td24_kcfkb.WXZXQZ"  value="${Td24_kcfkb.wxzxqz}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									设计院签字：<input type="text" style="border:none" name="Td24_kcfkb.SJYQZ"  value="${Td24_kcfkb.sjyqz}"/></td> 
				</tr>  
				<tr>
					<td>
						待反馈后再确认：<br>
					（1）需要提交结构图后再定，<br>
					（2）改址后（超过50米）需要重新定位，<br>
					（3）不可签（位置、挂高、承重不符合要求）</td>
					<td colspan="2">
						<textarea class="td-textarea" style="width:592px; height: 120px;border:0px;overflow:auto" name="Td24_kcfkb.FKQR">${Td24_kcfkb.fkqr}</textarea>
					</td> 
				</tr>  
			</tbody>
		</table>
		</form>
		</div>
		<div id="attachBody" layoutH="68">
			<div class="panel">
				<h1>表单附件 [${fn:length(slaveList)}]</h1>
				<div id="slaveDiv" defH="150" style="background-color:#fff;">
					<c:set var="slaves" scope="page" value="0"/>
					<c:forEach var="slave" items="${slaveList}">
						<p>
							<a href="show_slave.do?slave_id=${slave.id}" target="dialog" width="100" height="600" title="查看">
						<img width="18" height='20'
						<c:choose>
							<c:when test="${slave.ext_name=='.pdf'}">
								src="Images/icon/pdf.png"
								</c:when>
								<c:when test="${slave.ext_name=='.txt'}">
								src="Images/icon/txt.png"
								</c:when>
								<c:when test="${slave.ext_name=='.doc'}">
								src="Images/icon/doc.png"
								</c:when>
								<c:when test="${slave.ext_name=='.xls'}">
								src="Images/icon/xls.png"
								</c:when>
								<c:when test="${slave.ext_name=='.zip'||slave.ext_name=='.rar'}">
								src="Images/icon/rar.png"
								</c:when>
								<c:when test="${slave.ext_name=='.gif'}">
								src="Images/icon/gif.png"
								</c:when>
								<c:when test="${slave.ext_name=='.jpg'||slave.ext_name=='.png'}">
								src="Images/icon/jpg.png"
								</c:when>
								<c:when test="${slave.ext_name=='.pptx'||slave.ext_name=='.ppt'}">
								src="Images/icon/ppt.png"
								</c:when>
								<c:otherwise>
								src="Images/icon/file.png"
								</c:otherwise>
								</c:choose>/>
						 />
							${slave.file_name}&nbsp;&nbsp;
						</a>
							<a href="download.do?slave_id=${slave.id}" title="下载"><font color="red">下载</font></a>
							<a href="javascript:del_slave('${slave.id}','${slaves }');"><img src="Images/icon10.gif" alt="删除"/></a>
						</p>
						<c:set var="slaves" scope="page" value="${slaves+1 }"/>
					</c:forEach>
				</div>
			</div>
		</div>	
		<div  style="clear:both"></div>   
	</div>

	