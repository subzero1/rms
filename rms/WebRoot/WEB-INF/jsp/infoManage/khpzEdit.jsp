<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
$(document).ready(function(){
	initSysManageWeb();
	
	$("#delGczy").click(function(){
		var tc03_id = $("#gczy_id",navTab.getCurrentPanel()).val();
		ajaxTodo('infoManage/ajaxDelZydl.do', navTabAjaxDone ,{"id":tc03_id});
	});
});
</script>

<form method="post" action="save.do" class="pageForm required-validate"
	onsubmit="return validateCallback(this, navTabAjaxDone);">
	<input type="hidden" name="tableInfomation"
		value="noFatherTable:com.rms.dataObjects.base.Tc03_gczy" />
	<input type="hidden" name="tableInfomation"
		value="Tc03_gczy,id,gczy_id:com.rms.dataObjects.base.Tc04_zyxx" />
	<input type="hidden" id="gczy_id" name="Tc03_gczy.ID"
		value="${tc03.id}" />
	<input type="hidden" name="perproty" value="id,Tc03_gczy,id">
	<input type="hidden" name="_callbackType" value="forward" />
	<input type="hidden" name="_forwardUrl" value="infoManage/zywhList.do" />
	<input type="hidden" name="_navTabId" value="zywhList" />
	<div class="panel sysmanage_max" defH="213"
		style="width: 97%; float: left; margin: 5px">
		<h1>
			配置信息
		</h1>
		<div>
			<div class="pageFormContent">
				<p>
					<label>
						名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：
					</label>
					<input type="text" name="Tc10_hzdw_khpz.MC" value="${khpz.mc}"
						class="required" style="width: 200px;" maxlength="15" />
				</p>
				<p>
					<label>
						间隔天数：
					</label>
					<input type="text" name="Tc10_hzdw_khpz.JGTS" value="${khpz.jgts}"
						class="required" style="width: 50px;" maxlength="15" />
				</p>
				<div style="height: 0px;"></div>
				<p>
					<label>
						打分天数：
					</label>
					<input type="text" name="Tc10_hzdw_khpz.DFTS"
						value="${khpz.dfts }"
						class="required digits" style="width: 100px;" maxlength="4"
						  />
				</p>
				<p>
					<label>
						下次考核：
					</label>
					<input type="text" name="Tc10_hzdw_khpz.XCKHSJ"
						value="${khpz.xckhsj}"
						class="required date" style="width: 100px;" maxlength="4"  />
				</p>
				<div style="height: 0px;"></div>
				<p>
					<label style="">
						最后考核：
					</label>
					<input type="text" name="Tc10_hzdw_khpz.ZHKHSJ"
						value="${khpz.zhkhsj}""
						 class="required date" style="width: 100px;" maxlength="4"
						 readOnly/>
				</p>
				<p>
					<label>
						是否有效：
					</label>
					<input type="radio" name="Tc10_hzdw_khpz.USEFLAG" value="1"
						<c:if test="${khpz.useflag=='1' }">checked</c:if> />
					是
					<input type="radio" name="Tc10_hzdw_khpz.USEFLAG" value="0"
						<c:if test="${khpz.useflag!='1' }">checked</c:if> />
					否
				</p>
				<div style="height: 0px;"></div>
				<p>
					<label  >
						单位类别：
					</label>
					<input type="text" name="Tc10_hzdw_khpz.DWLB"
						value="${khpz.dwlb}"
						class="required " style="width: 100px;" maxlength="4"
						readOnly />
				</p>
				
				<div style="height: 0px;"></div>
				<p>
					<label  >
						备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：
					</label>
					<input type="text" name="Tc10_hzdw_khpz.BZ"
						value="${khpz.bz}"
						    style="width: 100px;" maxlength="4"
						 />
				</p>
			</div>

			<div class="formBar">
				<div style="float: left;">
					<div class="button">
						<div class="buttonContent">
							<button type="Button" class="divFileReload"
								loadfile="infoManage/khpzEdit.do">
								新建配置
							</button>
						</div>
					</div>
				</div>
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
									保 存
								</button>
							</div>
						</div>
					</li>
					<c:if test="${not empty tc03}">
						<li>
							<div class="button">
								<div class="buttonContent">
									<button type="Button" id="delGczy">
										删 除
									</button>
								</div>
							</div>
						</li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	<div class="panel sysmanage_min" defH="110"
		style="width: 97%; float: left; margin: 5px">
		<h1>
			配置明细
		</h1>
		<div>
			<table class="list itemDetail">
				<thead>
					<tr>
						<th type="text" style="width: 100px;" name="Tc04_zyxx.MC"
							hideName="Tc04_zyxx.ID">
							考核项
						</th>
						<th type="text" style="width: 150px;" name="Tc04_zyxx.MC"
							hideName="Tc04_zyxx.ID">
							评估内容
						</th>
						<th type="text" style="width: 80px;" name="Tc04_zyxx.MC"
							hideName="Tc04_zyxx.ID">
							最高得分
						</th>
						<th type="text" style="width: 150px;" name="Tc04_zyxx.MC"
							hideName="Tc04_zyxx.ID">
							评估办法
						</th>
						<th type="text" style="width: 250px;" name="Tc04_zyxx.MC"
							hideName="Tc04_zyxx.ID">
							备注
						</th>
						<th type="del" style="width: 30px;">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="tc04" items="${zyxx_list}">
						<tr>
							<td>
								<input type="hidden" name="Tc04_zyxx.ID" value="${tc04.id}" />
								<input type="text" name="Tc04_zyxx.MC" value="${tc04.mc}"
									style="width: 0px;" />
							</td>
							<td>
								<a href="javascript:" class="btnDel emptyInput" title="确认删除此明细">删除</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<br />
			<div style="color: 888;">
				注：修改明细数据后需点击【保存】按钮保存修改。
			</div>
		</div>
	</div>
</form>
