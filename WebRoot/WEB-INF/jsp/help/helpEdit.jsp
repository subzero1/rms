<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="crht"%>

<script type="text/javascript">
<!--

function aa(obj){
alert('xx');
	var	$obj = $(obj);
	alert($obj.length);
	var $span = $obj.find(".span");
	alert($span.length);
	//var $span=$($(obj) " > span");
	//alert($span.length);
	//$($span).each(function(i){
		//$this.css("display","block");		
	//});
	$span.css("display","block");		
}
function fbtn1()
{
	if($("#btn1").attr('src') == 'Images/choosebox.gif'){
		$("#fieldset1").css({"height":"auto"});
		$("#btn1").attr('src','Images/closebox.gif');
		
		//展开的时候回置checkbox
		var type_list = $('#b07_type').val();
		var types = type_list.split(',');
		var obj_sel = $(':input[name=repository_TYPE]');
		obj_sel.each(function(i){
			for(var k = 0;k < types.length;k++){
				if(types[k] == $(this).val()){
					$(this).attr('checked','true');
					break;
				}
			}
		});	
	  }
	  else{
		  $("#fieldset1").css({"height":"0px"});
		  $("#btn1").attr('src','Images/choosebox.gif');
	  }
}

function setType()
{
	var type_list = '';
	var obj_sel = $(':input[name=repository_TYPE]');
	obj_sel.each(function(i){
		if($(this).attr('checked') == true){
			type_list  = type_list + $(this).val() + ",";
		}
	});
	if(type_list.length > 0){
		type_list = type_list.substring(0,type_list.length -1);
	}
	$('#b07_type').val(type_list);
}

function repositorySave(butt){
	//设置b07状态
	var status = $("form>input:checked").val();
	$('#B07_repository\\.STATUS').val(status);
	
	//光标置入列表输入域(解决含有xheditor编辑器的navtab关闭后,失掉焦点问题)
	$input = $("#b07_key", navTab.getCurrentPanel());
	$input.focus();

	//var tempv=$('#b07_solution').val();
	//if(tempv.indexOf("id")==-1)
	//$('#b07_solution').val(tempv.replace("table","table id='tt' "));
	
	//form提交
	$form = $(butt).closest("form");
	$form.submit();
}

function cancelEdit(){
	//光标置入列表输入域(解决含有xheditor编辑器的navtab关闭后,失掉焦点问题)
	$input = $("#b07_key", navTab.getCurrentPanel());
	$input.focus();
	navTab.closeCurrentTab();
}


//-->
</script>
<style>
<!--
.pageFormContent div{clear:none;}
.unit{clear:both;}
-->
</style>
<div
	style="display: block; overflow: hidden; padding: 0 10px; line-height: 21px;">

	<div class="panel" style="width: 96%;; float: left; margin: 10px">
		<h1>
			知识库信息维护
		</h1>
		<div>
			<form method="post" action="business/ajaxRepositorySave.do"
				class="pageForm required-validate"
				onsubmit="return validateCallback(this,navTabAjaxDone)">
				<input type="hidden" id="tableInfomation" name="tableInfomation"
					value="noFatherTable:com.crht.dataObjects.base.B07_repository" keep="true" />
				<input type="hidden" name="B07_repository.ID"
					value="<c:out value="${repository.id}" default="${param.id}"/>" keep="true" />
				<input type="hidden" name="_callbackType" value="forward" keep="true" />
				<input type="hidden" name="_forwardUrl"
					value="business/repositoryList.do?repository_id=${repository.id}" keep="true" />
				<input type="hidden" name="_navTabId" value="templateList" keep="true" />

				<input type="hidden" id="b07_creator" name="B07_repository.CREATOR" id="B07_repository.CREATOR" value="<c:out value="${repository.creator }" default="${user.name }"/>" />
				<input type="hidden" id="b07_create_time" name="B07_repository.CREATE_TIME" id="B07_repository.CREATE_TIME" value="<c:choose><c:when test="${empty repository.create_time}">${now}</c:when><c:otherwise><fmt:formatDate value="${repository.create_time}" pattern="yyyy-MM-dd"/></c:otherwise></c:choose>" />
				<div class="pageFormContent" layoutH="138">
					<div class="unit">
						<label>
							标题：
						</label>
						<input id="b07_key" type="text" name="B07_repository.KEY" value="${repository.key}"	class="required" size="87" />
					</div>
					<div class="unit">
						<label>
							分类：
						</label>
						<input type="text" id="b07_type" name="B07_repository.TYPE" readOnly size="78" value="${repository.type}"  />
						<img onclick="javascript:fbtn1()" onmouseover="javascript:fbtn1()" id="btn1" name="btn1" style="cursor:hand;" src="Images/choosebox.gif" />
						<br>
						<fieldset id="fieldset1" style="width: 500px; height: 0px; overflow: auto;margin-left:89px;" >
							<c:forEach items="${typeList}" var="t" varStatus="status">
								<input onclick="setType()" type="checkbox" name="repository_TYPE" id="repository_TYPE" value="${t.name }"
									 />${t.name }
									<c:if test="${status.count%5==0}">
									<br />
								</c:if>
							</c:forEach>
						</fieldset>
						<br>
						<label>
							审核状态：
						</label>
						<c:if test="${repository.status == ''&& not empty repository}">
							<input type="radio" name="B07.STATUS" checked value="" />未审核
						</c:if>
						<input type="radio" name="B07.STATUS" <c:if test="${repository.status==1||empty repository}">checked</c:if> value="1" />
						通过
						<c:if test="${repository.status != 1}">
							<input type="radio" name="B07.STATUS" <c:if test="${repository.status==0}">checked</c:if> value="0" />
							未通过
						</c:if>
						<input type="hidden" id="b07_status" name="B07_repository.STATUS" value="${repository.status}" />
						<c:if test="${repository.status==1}">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="red">知识库编号：【${repository.code}】</font>
						</c:if>
					</div>
					<div class="unit">
						<label>
							故障现象：
						</label>
						<textarea id="b07_question" name="B07_repository.QUESTION" rows="5" cols="85">${repository.question}</textarea>
					</div>
					<div style="clear:none">
						<label>
							解决方案：
						</label>
						<div id="businessList" class="loadFileArea"
							loadfile="business/repositoryTree.do?showEdit=yes&repository_id=${repository.id }">
						</div>
					</div>
					<div class="divider"></div>
				</div>
				<div class="formBar">
					<ul>
						<li>
							<div class="buttonActive">
								<div class="buttonContent">
									<button type="button" onclick="javascript:repositorySave(this);">
										提 交
									</button>
								</div>
							</div>
						</li>
						<li>
							<div class="button">
								<div class="buttonContent">
									<button type="button" onclick="javascript:navTab.openTab('repositoryDisp', 'business/repositoryDisp.do?act=view&id=${repository.id}', { title:'知识库信息编辑预览', flesh:false, data:{} });">
										预 览
									</button>
								</div>
							</div>
						</li>
						<li>
							<div class="button">
								<div class="buttonContent">
									<button type="button" onclick="javascript:cancelEdit();">
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
</div>