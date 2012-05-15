<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="info/sqdExcelJfxx.do?module_id=${param.module_id}&project_id=${param.project_id }"
			enctype="multipart/form-data"
			onsubmit="return iframeCallback(this,dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<p>
					
						EXCEL文件
					
					<input type="file" name="file" size="20" />
				</p>
				<div class="divider"></div>
				<p style="color: blue; font-wigth: bold;">
					EXCEL模板下载：&nbsp;
				</p>
				<p>
					<a href="info/sqd_jf_excelmodel.do" target="_blank"><u>下载</u>
					</a>
					<br />
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