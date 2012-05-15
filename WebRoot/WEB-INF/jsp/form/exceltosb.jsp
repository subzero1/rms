<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script language="javascript">
function reloadJfsbxx(){
	setTimeout(function(){
		$.pdialog.closeCurrent();
		var dialog = $("body").data("sqsbmx");
		if (dialog){
			var url = dialog.data("url");
			$.pdialog.open(url, "sqsbmx", "机房设备",{});
		}
	}, 100);
}
</script>

<div class="page">
	<div class="pageContent">
		<form method="post" action="exceltosb.do" enctype="multipart/form-data"	onsubmit="return iframeCallback(this,reloadJfsbxx);">
			<input type="hidden" name="project_id" value="${param.project_id }">
			<input type="hidden" name="sqd_id" value="${param.doc_id }">
			<input type="hidden" name="jfxx_id" value="${param.jfxx_id }">
			<div class="pageFormContent" layoutH="56">
				<p>EXCEL文件<input type="file" name="file" size="20" /></p>
				<div style="height:0px;"></div>
				<p style="color:red;"><input type="checkbox" name="delFlag" value="1"> 删除原设备信息</p>
				<div class="divider"></div>
				<p style="color: blue; font-wigth: bold;">
					EXCEL模板下载：&nbsp;
				</p>
				<p>
					<a href="info/jfexcelmodel.do?excelversion=97-03" target="_blank"><u>97-03版本</u>
					</a>
					<br />
					<br />
					<a href="info/jfexcelmodel.do?excelversion=07" target="_blank"><u>2007版本</u>
					</a>
				</p>
			</div>
			<div class="formBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
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