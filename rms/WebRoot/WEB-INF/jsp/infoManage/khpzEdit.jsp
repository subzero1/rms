<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
$(document).ready(function(){
	initSysManageWeb(); 
	$("#delKhpz").click(function(){
		var kh_id = $("#kh_id",navTab.getCurrentPanel()).val();
		ajaxTodo('infoManage/ajaxKhpzDel.do', navTabAjaxDone ,{"kh_id":kh_id});
	});
});
</script>

<form method="post" action="save.do" class="pageForm required-validate"
	onsubmit="return validateCallback(this, navTabAjaxDone);">
	<input type="hidden" name="tableInfomation"
		value="noFatherTable:com.rms.dataObjects.base.Tc10_hzdw_khpz" />
	<input type="hidden" name="tableInfomation"
		value="Tc10_hzdw_khpz,id,kh_id:com.rms.dataObjects.base.Tc11_khpzmx" />
	<input type="hidden" id="kh_id" name="Tc10_hzdw_khpz.ID"
		value="${khpz.id}" />
	<input type="hidden" name="perproty" value="id,Tc10_hzdw_khpz,id">
	<input type="hidden" name="_callbackType" value="forward" />
	<input type="hidden" name="_forwardUrl" value="infoManage/khpzList.do" />
	<input type="hidden" name="_navTabId" value="khpz" />
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
					<label >
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
					<input type="text" name="Tc10_hzdw_khpz.DFTS" value="${khpz.dfts }"
						class="required digits" style="width: 200px;" maxlength="4" />
				</p>
				<p>
					<label >
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
					<label style="">
						最后考核：
					</label>
					<input type="text" name="Tc10_hzdw_khpz.ZHKHSJ"
						value="<fmt:formatDate value='${khpz.zhkhsj}' pattern='yyyy-MM-dd'/>" style="width: 200px;"
						     readOnly/>
				</p>
				<p>
					<label >
						单位类别：
					</label>
					<select style="width: 100" name="Tc10_hzdw_khpz.DWLB">
						<option
							<c:if test="${khpz.dwlb=='设计'||empty khpz.dwlb}"> selected</c:if>>
							设计
						</option>
						<option <c:if test="${khpz.dwlb=='施工'}"> selected</c:if>>
							施工
						</option>
						<option <c:if test="${khpz.dwlb=='监理'}"> selected</c:if>>
							监理
						</option>
					</select>
				</p>
				<div style="height: 0px;"></div>
				<p>
					<label >
						下次考核：
					</label>
					<input type="text" name="Tc10_hzdw_khpz.XCKHSJ"
					value="<fmt:formatDate	value="${khpz.xckhsj}" pattern="yyyy-MM-dd" />" class="required date" style="width: 200px;"
						 pattern="yyyy-MM-dd" readOnly/>
				</p>

				<div style="height: 0px;"></div>
				<p>
					<label>
						备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：
					</label>
					<input type="text" name="Tc10_hzdw_khpz.BZ" value="${khpz.bz}"
						style="width: 520px;"  />
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
					<c:if test="${not empty khpz}">
						<li>
							<div class="button">
								<div class="buttonContent">
									<button type="Button" id="delKhpz">
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
						<th type="text" style="width: 100px;" name="Tc11_khpzmx.KHX" hideName="Tc11_khpzmx.ID">
							考核项
						</th>
						<th type="text" style="width: 150px;" name="Tc11_khpzmx.PGNR">
							评估内容
						</th>
						<th type="text" style="width: 80px;" name="Tc11_khpzmx.ZGFZ">
							最高得分
						</th>
						<th type="text" style="width: 150px;" name="Tc11_khpzmx.PGBF">
							评估办法
						</th>
						<th type="text" style="width: 250px;" name="Tc11_khpzmx.BZ">
							备注
						</th>
						<th type="del" style="width: 30px;">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="pzmx" items="${pzmxList}">
						<tr>
							<td>
								<input type="hidden" name="Tc11_khpzmx.ID" value="${pzmx.id}" />
								<input type="text" name="Tc11_khpzmx.KHX" value="${pzmx.khx}"
									style="width: 0px;" />
							</td>
							<td>
								<input type="text" name="Tc11_khpzmx.PGNR" value="${pzmx.pgnr}"
									style="width: 0px;" />
							</td>
							<td>
								<input type="text" name="Tc11_khpzmx.ZGFZ" value="${pzmx.zgfz}"
									style="width: 0px;" />
							</td><td>
							<input type="text" name="Tc11_khpzmx.PGBF" value="${pzmx.pgbf}"
								style="width: 0px;" />
							</td>
							<td>
								<input type="text" name="Tc11_khpzmx.BZ" value="${pzmx.bz}"
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
