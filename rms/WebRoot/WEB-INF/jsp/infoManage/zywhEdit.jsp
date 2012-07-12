<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
$(document).ready(function(){
	initSysManageWeb();
});
</script>

<form method="post" action="save.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	<input type="hidden" name="tableInfomation" value="noFatherTable:com.netsky.base.dataObjects.Ta05_group" />
	<input type="hidden" name="Tc03_gczy.ID" value="${tc03.id}" />
	<input type="hidden" name="perproty" value="group_id,Ta05_group,id">
	<input type="hidden" name="_callbackType" value="forward" />
	<input type="hidden" name="_forwardUrl" value="infoManage/zywhList.do" />
	<input type="hidden" name="_navTabId" value="zywhList" />
	<div class="panel sysmanage_max" defH="120" style="width: 97%; float: left; margin: 5px">
		<h1>专业信息</h1>
		<div>
			<div class="pageFormContent">
				<p>
					<label>
						专业名称：
					</label>
					<input type="text" name="Tc03_gczy.ZYMC" value="${tc03.zymc}"	class="required" style="width: 300px;" maxlength="15" />
				</p>
				<div style="height: 0px;"></div>
				<p>
					<label>
						有效年度：
					</label>
					<input type="text" name="Tc03_gczy.YXND" value="${tc03.yxnd}" class="required digits" style="width: 100px;" maxlength="4" />
				</p>
				<p>
					<label>
						是否市控：
					</label>
					<input type="radio" name="Tc03.gczy.SFSK" value="1"/>是
					<input type="radio" name="Tc03.gczy.SFSK" value="0"/>否
				</p>
				<div style="height: 0px;"></div>
			</div>
			<div class="formBar">
				<div  style="float:left;">
					<div class="button"><div class="buttonContent"><button type="Button" class="divFileReload" loadfile="sysManage/groupEdit.do">新建专业</button></div></div>
				</div>
				<ul>
					<li>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保 存</button></div></div>
					</li>
					<c:if test="${not empty group}">
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" id="delGczy">删 除</button></div></div>
					</li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	<div class="panel sysmanage_min" defH="110"	style="width: 97%; float: left; margin: 5px">
		<div>
			<table width="100%" class="list itemDetail">
			<thead>
				<tr>
					<th type="text"style="width:150px;" name="Tc04_zyxx.MC" hideName="Tc04_zyxx.ID">专业细项名称</th>
					<th type="del" style="width:30px;">操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="tc04" items="${zyxx_list}">
				<tr>
					<td>
						<input type="hidden" name="Tc04_zyxx.ID" value="${tc04.id}"/>
						<input type="text" name="Tc04_zyxx.MC" value="${tc04.mc}" style="width:0px;"/>
					</td>
					<td><a href="javascript:fsd" class="btnDel emptyInput" title="确认删除此明细">删除</a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		</div>
	</div>
</form>
