<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="now" class="java.util.Date" />

<script language="javascript">
function del(){
	 $("#reader_tel",$.pdialog.getCurrent()).val("");
	 $("#reader_name",$.pdialog.getCurrent()).val("");
	 $(".mrtzr",$.pdialog.getCurrent()).attr("checked",false);
	}
	function selectToUser(){
		var selectO = $("#user_list option:selected");
		selectO.each(function(){
		if($("#reader_name").val()==""){
			$("#reader_name").val($(this).text());
			$("#reader_name1").val($(this).text());
			$("#reader_tel").val($(this).val());
			for(var i = 1 ; i < $(this).val().split(",").length ; i++){
			$("#reader_name").val($("#reader_name").val()+ "；" +$(this).text());
			}
		}else{
			$("#reader_name1").val($("#reader_name1").val()+"；"+$(this).text());
			$("#reader_name").val($("#reader_name").val() + "；" + $(this).text());
			$("#reader_tel").val($("#reader_tel").val() + "," + $(this).val());
			for(var i = 1 ; i < $(this).val().split(",").length ; i++){
			$("#reader_name").val($("#reader_name").val()+ "；" +$(this).text());
			}
		}
		});
	}
	$(function(){
		//及连菜单
		$("#area",$.pdialog.getCurrent()).change(function(){
			jilian('dept','Ta01_dept.area_name',$("#area").val(),'id','name');
			$("#dept").change();
		})
		$("#dept",$.pdialog.getCurrent()).change(function(){
			jilian('user_list','Ta03_user.dept_id',$("#dept").val(),'mobile_tel','name');
		})
		
	})
$(function(){
	$(".mrtzr",$.pdialog.getCurrent()).change(function(){
		var reader_name = $("#reader_name",$.pdialog.getCurrent()).val();
		var reader_tel = $("#reader_tel",$.pdialog.getCurrent()).val();
		if ($(this).attr("checked")=="checked"){
			if (reader_name.indexOf($(this).attr("tzrname"))==-1){
				if (reader_name!=""){
					reader_name+="；";
				}
				reader_name+=$(this).attr("tzrname");
			}
			if (reader_tel.indexOf($(this).val())==-1){
				if (reader_tel!=""){
					reader_tel+=",";
				}
				reader_tel+=$(this).val();
			}
		}else{
			if (reader_name.indexOf($(this).attr("tzrname"))!=-1){
				reader_name=reader_name.replaceAll($(this).attr("tzrname"),"");
				reader_name=reader_name.replaceAll("；；","；");
				if (reader_name.indexOf("；")==0){
					reader_name=reader_name.substring(1);
				}
				if (reader_name.lastIndexOf(",")==reader_name.length-1){
					reader_name=reader_name.substring(0,reader_name.length-1);
				}
			}
			if (reader_tel.indexOf($(this).val())!=-1){
				reader_tel=reader_tel.replaceAll($(this).val(),"");
				reader_tel=reader_tel.replaceAll(",,",",");
				if (reader_tel.indexOf(",")==0){
					reader_tel=reader_tel.substring(1);
				}
				if (reader_tel.lastIndexOf(",")==reader_tel.length-1){
					reader_tel=reader_tel.substring(0,reader_tel.length-1);
				}
			}
		}
		$("#reader_name",$.pdialog.getCurrent()).val(reader_name);
		$("#reader_tel",$.pdialog.getCurrent()).val(reader_tel)
	});
});
function uploadSlave(butt){
	var up_form = $(butt).closest("form");
		up_form.submit();
}
</script>

<div class="page">
	<div class="pageContent">
		<form method="post" id="form1" action="save.do" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone)">
			<input type="hidden" name="tableInfomation" value="noFatherTable:com.netsky.base.dataObjects.Te09_jlgt"/>
			<input type="hidden" name="slaveTable" value="com.netsky.base.dataObjects.Te01_slave"/>
			<input type="hidden" name="slaveType" value="ftp"/>
			<input type="hidden" name="slaveFatherTables" value="Te09_jlgt,ID,DOC_ID/Te09_jlgt,ID,project_id"/>
			<input type="hidden" name="Te09_jlgt.ID" id="Te09_jlgt.ID" value=""/>
			<input type="hidden" name="_navTabId" value="_current"/>
			<input type="hidden" name="_forwardUrl" value=""/>
			<input type="hidden" name="_callbackType" value=""/>
			
			<!--  短信提醒SERVICE -->
			<input type="hidden" name="ServiceName" value="sendMessageService"/>
			<input type="hidden" name="ServiceFunction" value="sendMessage"/>
			<input type="hidden" name="ServicePerproty" value="content/sender_name/additionTel/reader_tel/Te09.TZR_NAMES"/>
			<input type="hidden" name="content" id="content" value="关于【${param.content }】的交流沟通有了新的发言，请您及时关注！"/>
			<input type="hidden" name="sender_name" id="sender_name" value="${user.name }"/>
			<input type="hidden" name="additionTel" id="additionTel" value=""/>
			<input type="hidden" name="reader_tel" id="reader_tel" value="${reader_tel }"/>
			
			<input type="hidden" name="Te09_jlgt.TZR_IDS" id="Te09_jlgt.TZR_IDS" value=""/>
			<input type="hidden" name="Te09_jlgt.UP_ID" id="Te09_jlgt.UP_ID" value="${param.up_id }"/>
			<input type="hidden" name="Te09_jlgt.MODULE_ID" value="${param.module_id }"/>
			<input type="hidden" name="Te09_jlgt.DOC_ID" value="${param.doc_id }"/>
			<input type="hidden" name="Te09_jlgt.FBSJ" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			<input type="hidden" name="Te09_jlgt.FBR_ID" value="${user.id}"/>
			<input type="hidden" name="Te09_jlgt.FBR" value="${user.name}"/>
			<input type="hidden" name="Te09_jlgt.COMMENTS" value="${param.content}"/>
			<div class="pageFormContent">
			<input type="text" name="Te09_jlgt.TITLE" value="${param.bt}" class="required" maxlength="50" style="width:580px;margin-bottom:5px;"/>
			<textarea class="editor required" id="Te09_jlgt.NR" name="Te09_jlgt.NR"
				style="width:585px;height:130px;" tools="simple" upLinkUrl="upload.do"
				upLinkExt="zip,rar,txt" upImgUrl="ajaxXhEditorUpload.do"
				upImgExt="jpg,jpeg,gif,png" upFlashUrl="upload.do"
				upFlashExt="swf" upMediaUrl="upload.do"upMediaExt:"avi"></textarea>
				<div><span style="">附 件</span><input type="file" name="the_file1" id="the_file1" size="30"/>
					<input type="hidden" name="Te01_slave.ID" value=""/>
					<input type="hidden" name="Te01_slave.MODULE_ID" value="80"/>
					<input type="hidden" name="Te01_slave.USER_ID" value="${user.id}"/>
					<input type="hidden" name="Te01_slave.USER_NAME" value="${user.name}"/>
					<input type="hidden" name="Te01_slave.FTP_DATE" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
					<input type="hidden" name="Te01_slave.FILE_NAME" value="1"/>
					<input type="hidden" name="Te01_slave.EXT_NAME" value="1"/>
					<input type="hidden" name="Te01_slave.FTP_URL" value="1"/>
					<input type="hidden" name="Te01_slave.SLAVE_TYPE" value="其他附件" /></div>
			</div>
			<div style="width:100%;">
				<div class="left" style="float:left;margin-left:10px;">
				<div style="margin-bottom:5px;font-size:16px;">短信提醒</div>
				<div style="">默认选择：</div>
				<c:forEach items="${mrtzrList}" var="tzr">
					<span style="margin-right:10px;"><input type="checkbox" checked="checked" class="mrtzr" tzrname="${tzr[0] }" value="${tzr[1] }"/>${tzr[0] }</span>
				</c:forEach>
				<div style="margin-top:15px;">通知人姓名：</div>
				<p>
				<input type="text" name="Te09_jlgt.TZR_NAMES" id="reader_name" style="width:350px;" value="${reader_name }" readonly="readonly"/>
				<img src="Images/trash.gif" onclick="javascript:del();" style="cursor:pointer" title="清空内容" />
				</p>
				</div>
				<div class="right" style="position:relative;top:-30px;;margin-left:380px;width:200px;text-align:right;padding:5px; 5px;">
					 <select name="area" id="area" style="width:80px;">
				    	<c:forEach var="area" items="${areaList}">
							<c:choose>
								<c:when test="${user.area_name == area.name}">
									<option selected value="${area.name}">${area.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${area.name}">${area.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				    </select>
				    
				    <select name="dept" id="dept" style="width:115px;">
				    	<c:forEach var="dept" items="${user_dept_list}">
							<c:choose>
								<c:when test="${user_dept_id == dept[0]}">
									<option selected value="${dept[0]}">${dept[1]}</option>
								</c:when>
								<c:otherwise>
									<option value="${dept[0]}">${dept[1]}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				    </select>
				    <select id="user_list" name="user_list" multiple="1" type="select-multiple" style="width:198px;height:120px;" ondblclick="javascript:selectToUser();">
					<c:forEach var="user" items="${user_list}">
						<option value="${user.mobile_tel }">${user.name }</option>
					</c:forEach>
					</select>
			</div>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="javascript:uploadSlave(this);">发 表</button>
						<!-- <button type="Button" onclick="javascript:setAndUpload(this);">上传文件</button> -->
					</div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>	