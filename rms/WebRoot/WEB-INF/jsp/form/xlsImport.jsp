<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />

<script type="text/javascript">
	$(function(){
		$("#importbutton",$.pdialog.getCurrent()).click(function(){
			if ($("#file",$.pdialog.getCurrent()).val()==""){
				alertMsg.info("温馨提示：请选择导入EXCEL文件！");
				return false;
			}
			if(!/(.xls)$/.exec($("#file",$.pdialog.getCurrent()).val())){
				if(/(.xlsx)$/.exec($("#file",$.pdialog.getCurrent()).val())){
					alertMsg.info("温馨提示：系统无法识别excel2007，建议选择excel2003文件");
					return false;
				}else{ 
					alertMsg.info("温馨提示：并非excel文件，请您重新导入");
				}
				return false;
			}
			alertMsg.confirm("确定导入数据吗？", {
				okCall: function(){
					$("#importform",$.pdialog.getCurrent()).submit();
				}
			});
		}); 
	}); 
	function tempDownload(config){
		window.location.href="export/tempDownload.do?config="+config; 
	}
</script>

<div class="page">
	<div class="pageContent">
		<form method="post" action="import2.do" enctype="multipart/form-data"
			id="importform"
			onsubmit="return iframeCallback(this,dialogAjaxDone);">
			<input type="hidden" name="packgePath"
				value="com.rms.dataObjects.form" />
			<input type="hidden" name="_callbackType" value="closeCurrent" />
			<input type="hidden" name="_navTabId" value="" />
			<input type="hidden" name="_forwardUrl" value="" />
			<input type="hidden" name="perproty" value=" " />
			<input type="hidden" name="config" value="${param.config}" />
			<input type="hidden" name="Td00_gcxx.CJRQ" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>"/>
			<input type="hidden" name="Td01_xmxx.CJRQ" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>"/>
			<div class="pageFormContent" layoutH="58">
				<br/>
				<br/>
				<p style="">
					<label>EXCEL文件</label>
					<input id="file" type="file" name="file" size="20" style="background-color:#ebf0f5;"/>
				</p>
			</div>
			<div class="formBar">
				<ul>
					<li>
						<div class="buttonActive"> 
							<div class="buttonContent">
							<button class="importbutton" onclick="tempDownload('${param.config}')">
								模板下载
							</button>
							</div>
						</div>
					</li>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="button" id="importbutton" >
									导 入
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