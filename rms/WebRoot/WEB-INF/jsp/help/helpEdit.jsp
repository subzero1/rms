<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="crht"%>

<script type="text/javascript">
<!--

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
			在线帮助维护
		</h1>
		<div>
			<form method="post" action="help/ajaxHelpSave.do"
				class="pageForm required-validate"
				onsubmit="return validateCallback(this,navTabAjaxDone)">
				<input type="hidden" id="tableInfomation" name="tableInfomation" value="noFatherTable:com.netsky.base.dataObjects.Tz06_help" keep="true" />
				<input type="hidden" name="Tz06_help.ID" value="<c:out value="${repository.id}" default="${param.id}"/>" keep="true" />
				<input type="hidden" name="_callbackType" value="forward" keep="true" />
				<input type="hidden" name="_forwardUrl" value="help/helpList.do?help_id=${tz06.id}" keep="true" />
				<input type="hidden" name="_navTabId" value="templateList" keep="true" />
				<input type="hidden" name="Tz06_help.RECORDOR" id="Tz06_help.RECORDOR" value="<c:out value="${tz06.recordor }" default="${user.name }"/>" />
				<input type="hidden" name="Tz06_help.RECORD_DATE" id="Tz06_help.RECORD_DATE" value="<c:choose><c:when test="${empty repository.record_date}">${now}</c:when><c:otherwise><fmt:formatDate value="${tz06.record_date}" pattern="yyyy-MM-dd"/></c:otherwise></c:choose>" />
				<div class="pageFormContent" layoutH="138">
					<div class="unit">
						<label>
							标题：
						</label>
						<input id="Tz06_help.TITLE" type="text" name="Tz06_help.TITLE" value="${tz06.title}" class="required" size="87" />
					</div>
					<div class="unit">
						<label>
							关键字：
						</label>
						<textarea id="Tz06_help.KEYS" name="Tz06_help.KEYS" rows="5" cols="85">${tz06.keys}</textarea>
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