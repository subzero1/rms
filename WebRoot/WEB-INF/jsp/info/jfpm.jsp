<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />

<script language="javascript">
function saveJfxx(){
	$("#jfxx_form",navTab.getCurrentPanel()).submit();
}
</script>

<div class="page">
	<!-- 表单头 -->
	<div class="pageHeader">
		<div class="searchBar">
			<!-- 表单名称 -->
			<h1>机房信息表</h1>
			<!-- 辅助操作 -->
			<p style="float: right;text-align:right;">
				<c:if test="${not empty Td00_jfxx}">
					<div style="float:right;color:blue;">
							机房规划图：
							<c:if test="${param.limit==1}"><a href="dispath.do?url=uploadFile.jsp?slave_id=${jfght.id }&module_id=200&project_id=${Td00_jfxx.id}&doc_id=${Td00_jfxx.id}&slave_type=2&navTabId=jfpm&ischeckDrawingVersion=y" target="dialog" width="400" height="260">[上传]</a> </c:if>
							<c:if test="${not empty jfght}"><a href="download.do?slave_id=${jfght.id }">[下载]</a></c:if>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							机房现状图：
							<c:if test="${param.limit==1}"><a href="dispath.do?url=uploadFile.jsp?slave_id=${jfxzt.id }&module_id=200&project_id=${Td00_jfxx.id}&doc_id=${Td00_jfxx.id}&slave_type=3&navTabId=jfpm&ischeckDrawingVersion=y" target="dialog" width="400" height="260">[上传]</a> </c:if>
							<c:if test="${not empty jfxzt}"><a href="download.do?slave_id=${jfxzt.id }">[下载]</a></c:if>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="info/jfpmDrawingBakList.do?doc_id=${Td00_jfxx.id }&a=b" target="dialog" rel="jfpmDrawingBakList" width="550" height="400" title="机房现状图备份">[现状图备份]</a>
					</div>
				</c:if>
			</p>
		</div>
	</div>
	
	<!-- 主操作按钮 -->
	<div class="panelBar">
		<ul class="toolBar">
		 	<c:if test="${param.limit==1}">
			 	<li><a class="save"	href="javascript:saveJfxx();"><span>保 存</span></a></li>
				<li class="line">line</li>
			</c:if>
		</ul>
	</div>
	
	
	<div class="pageContent">
		<form id="jfxx_form" action="save.do" method="post"  class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="tableInfomation"	value="noFatherTable:com.jfms.dataObjects.info.Td00_jfxx" />
			<input type="hidden" name="Td00_jfxx.ID" value="${Td00_jfxx.id}" />
			<input type="hidden" name="_callbackType" value="forward"/>
			<input type="hidden" name="_message" value="机房信息保存" />
			<input type="hidden" name="_forwardUrl" value="info/jfpm.do?limit=${param.limit }"/>
			<input type="hidden" name="_navTabId" value="jfpmList"/>
			<input type="hidden" name="perproty" value="id,Td00_jfxx,id"/>
			<input type="hidden" name="validate" value="${validate}"/> 
			<div class="pageFormContent">
				<p>
					<label>登记部门：</label>
					<input type="text" name="Td00_jfxx.DJBM" style="width:407px;" value="<c:out value="${Td00_jfxx.djbm}" default="${user.dept_name}"/>" readonly/>
				</p>
				<p>
					<label>登记日期：</label>
					<input readonly type="text" name="Td00_jfxx.DJRQ" style="width:120px;" readonly value="<c:choose><c:when test="${empty param.doc_id}"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm"/></c:when><c:otherwise><fmt:formatDate value="${Td00_jfxx.djrq}" pattern="yyyy-MM-dd HH:mm"/></c:otherwise></c:choose>"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label width="100">登 记 人：</label> 
					<input type="text" name="Td00_jfxx.DJRY" style="width:150px;" value="<c:out value="${Td00_jfxx.djry}" default="${user.name}"/>" readonly/>
				</p>
				<p>
					<label width="90">联系电话：</label>
					<input type="text" name="Td00_jfxx.LXDH" style="width:150px;" value="<c:out value="${Td00_jfxx.lxdh}" default="${user.mobile_tel}"/>"/>
				</p>
				<div class="divider"></div>
				<p>
					<label>局点名称：</label>
					<input type="text" id="master.dwz_devLooup.Td00_jfxx.JDMC" style="width:382px;" name="Td00_jfxx.JDMC" value="${Td00_jfxx.jdmc}" readonly/><a class="btnLook" href="selectJdxx.do"  lookupGroup="master" lookupName="devLooup" width="600" height="380">查找带回</a>
				</p>
				<p>
					<label>局点性质：</label>
					<input type="text" id="master.dwz_devLooup.Td00_jfxx.JDXZ" style="width:120px;" name="Td00_jfxx.JDXZ" value="${Td00_jfxx.jdxz}" readonly/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label width="90">所属区域：</label>
					<input type="text" id="master.dwz_devLooup.Td00_jfxx.SSQY" style="width:150px;" name="Td00_jfxx.SSQY" value="${Td00_jfxx.ssqy}" readonly/>
				</p>
				<p>
					<label>坐落地点：</label>
					<input type="text" name="Td00_jfxx.ZLDD" style="width:150px;" value="${Td00_jfxx.zldd}"/>
				</p>
				<p>
					<label>经纬度：</label>
					<input type="text" name="Td00_jfxx.JWD" style="width:120px;" value="${Td00_jfxx.jwd}"/>
				</p>
				<div class="divider"></div>
				<p>
					<label>机房名称：</label>
					<input type="text" name="Td00_jfxx.JFMC" style="width:407px;" value="${Td00_jfxx.jfmc}"/>
				</p>
				<p>
					<label>建设日期：</label>
					<input type="text" class="date" name="Td00_jfxx.JSRQ" style="width:120px;" value="${Td00_jfxx.jsrq}"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>机房面积：</label> 
					<input type="text" name="Td00_jfxx.JFMJ" style="width:150px;" value="${Td00_jfxx.jfmj}"/>
				</p>
				<p>
					<label>所在楼层：</label>
					<input type="text" name="Td00_jfxx.JFSZLC" style="width:150px;" value="${Td00_jfxx.jfszlc}"/>
				</p>
				<p>
					<label>机房用途：</label>
					<input type="text" name="Td00_jfxx.JFYT" style="width:120px;" value="${Td00_jfxx.jfyt}"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>管理人员</label>
					<input type="text" name="Td00_jfxx.GLRY" style="width:150px;" value="${Td00_jfxx.glry}"/>
				</p>
				<p>
					<label>管理员电话：</label>
					<input type="text" name="Td00_jfxx.GLYLXDH" style="width:150px;" value="${Td00_jfxx.glylxdh}"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>备 注：</label>
					<textarea class="td-textarea" style="width:630px;height:40px;" type="text" name="Td00_jfxx.BZ"/>${Td00_jfxx.bz}</textarea>
				</p>
			<c:if test="${not empty Td00_jfxx}">
			<div class="panelBar">
				<ul class="toolBar">
					<c:if test="${param.limit==1}">
						<li><a class="add" href="info/sbxx.do?jfxx_id=${Td00_jfxx.id }" target="dialog" width="580" height="400" title="添加设备"><span>添加</span></a></li>
						<li class="line">line</li>
						<li><a class="delete" href="info/ajaxSbxxDel.do?id={sbxx_id}" target="ajaxTodo" title="确认删除吗？"><span>删除</span></a></li>
						<li class="line">line</li>
						<li><a class="edit" href="info/sbxx.do?jfxx_id=${Td00_jfxx.id }&id={sbxx_id}" target="dialog" width="580" height="400" title=设备信息卡"><span>修改</span></a></li>
						<li class="line">line</li>
						<li><a class="icon" href="info/exceltosbxxjsp.do?jfxx_id=${Td00_jfxx.id }" target="dialog" rel="exceltosbxxjsp" width="360" height="200" title="导入设备（Excel格式）"><span>导入</span></a></li>
						<li class="line">line</li>
					</c:if>
					<li><a class="exportexcel" href="info/sbxxtoexcel.do?excelversion=97-03&jfxx_id=${Td00_jfxx.id }" target="dwzExport" targetType="navTab"><span>导出</span></a></li>
					<li class="line">line</li>
				</ul>
			</div>
			</c:if>
			<table class="table" width="100%"  layoutH="420">
				<thead>
					<tr>
						<th orderField="sbmc">设备名称</th>
						<th orderField="sbxh">设备型号</th>
						<th style="width:80px;" orderField="jjcc">机架尺寸</th>
						<th style="width:120px;" orderField="cj">厂家</th>
						<th style="width:80px;" orderField="gdfs">供电方式</th>
						<th style="width:80px;" orderField="azwz">安装位置</th>
						<th style="width:100px;" orderField="azrq">安装日期</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="obj" items="${sbxxList}">
						<tr target="sbxx_id" rel="${obj.id}">
							<td>${obj.sbmc }</td>
							<td>${obj.sbxh }&nbsp;</td>
							<td>${obj.jjcc }</td>
							<td>${obj.cj }</td>
							<td>${obj.gdfs }</td>
							<td>${obj.azwz }</td>
							<td><fmt:formatDate value="${obj.azrq }" pattern="yyyy-MM-dd" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		</form>
	</div>
</div>
