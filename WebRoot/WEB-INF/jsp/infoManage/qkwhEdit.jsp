<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
$(document).ready(function(){
	initSysManageWeb();
	
	$("#delTzqk").click(function(){
		var tc06_id = $("#tzqk_id",navTab.getCurrentPanel()).val();
		ajaxTodo('infoManage/ajaxDelQkdl.do', navTabAjaxDone ,{"id":tc06_id});
	});
});
</script>

<form method="post" action="save.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	<input type="hidden" name="tableInfomation" value="noFatherTable:com.rms.dataObjects.base.Tc06_tzqk" />
	<input type="hidden" name="tableInfomation" value="Tc06_tzqk,id,qk_id:com.rms.dataObjects.base.Tc07_qkxx"/>
	<input type="hidden" id="tzqk_id" name="Tc06_tzqk.ID" value="${tc06.id}" />
	<input type="hidden" name="perproty" value="id,Tc06_tzqk,id">
	<input type="hidden" name="_callbackType" value="forward" />
	<input type="hidden" name="_forwardUrl" value="infoManage/qkwhList.do" />
	<input type="hidden" name="_navTabId" value="qkwhList" />
	<div class="panel sysmanage_max" defH="150" style="width: 97%; float: left; margin: 5px">
		<h1>切块信息</h1>
		<div>
			<div class="pageFormContent">
				<p>
					<label>
						编 号：
					</label>
					<input type="text" name="Tc06_tzqk.BH" value="${tc06.bh}" style="width: 300px;" maxlength="15" />
				</p>
				<div style="height: 0px;"></div>
				<p>
					<label>
						切块名称：
					</label>
					<input type="text" name="Tc06_tzqk.QKMC" value="${tc06.qkmc}"	class="required" style="width: 300px;" maxlength="15" />
				</p>
				<div style="height: 0px;"></div>
				<p>
					<label>
						是否有效：
					</label>
					<input type="radio" name="Tc06_tzqk.FLAG" value="1" <c:if test="${tc06.flag=='1' }">checked</c:if>/>是
					<input type="radio" name="Tc06_tzqk.FLAG" value="" <c:if test="${tc06.flag!='1' }">checked</c:if>/>否
				</p>
				<div style="height: 0px;"></div>
			</div>
			<div class="formBar">
				<div  style="float:left;">
					<div class="button"><div class="buttonContent"><button type="Button" class="divFileReload" loadfile="infoManage/qkwhEdit.do?year=${param.year }">新建切块</button></div></div>
				</div>
				<ul>
					<li>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保 存</button></div></div>
					</li>
					<c:if test="${not empty tc06}">
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" id="delTzqk">删 除</button></div></div>
					</li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	<div class="panel sysmanage_min" defH="110"	style="width: 97%; float: left; margin: 5px">
		<h1>切块小项</h1>
		<div>
			<table class="list itemDetail">
			<thead>
				<tr>
					<th type="text"style="width:300px;" name="Tc07_qkxx.MC" hideName="Tc07_qkxx.ID">名称</th>
					<th type="del" style="width:30px;">操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="tc07" items="${qkxx_list}">
				<tr>
					<td>
						<input type="hidden" name="Tc07_qkxx.ID" value="${tc07.id}"/>
						<input type="text" name="Tc07_qkxx.MC" value="${tc07.mc}" style="width:0px;"/>
					</td>
					<td><a href="javascript:" class="btnDel emptyInput" title="确认删除此明细">删除</a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<br/>
		<div style="color:888;">注：修改明细数据后需点击【保存】按钮保存修改。</div>
		</div>
	</div>
</form>
