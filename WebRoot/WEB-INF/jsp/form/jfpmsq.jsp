<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />

<script language="javascript">

function getDesigner(var0) {

	var s_sjdw = $("#Td11_jfpmsq\\.SJDW").val();
	var s_doc_id = $("#Td11_jfpmsq\\.ID").val();
	var params = "sjdw=" + s_sjdw + "&doc_id=" + s_doc_id;
	$.ajax({type:"post",async:false,url:"info/ajaxGetDesigner.do", data:params, success:function (msg) {
		$("#" + var0 + "").empty();
		$("#" + var0 + "").append(msg);
	}});
}

//自动选择机房信息
function autoSelectJfxx(inputObj){
	if ($(inputObj).val()!="" && event.keyCode == DWZ.keyCode.ENTER){
		var cur_tr = $(inputObj).closest("tr");
		$.ajax({
			type: 'post',
			url: 'ajaxSelectJfxx.do',
			data: {'keyword':$(inputObj).val()},
			dataType:'xml',
			cache: false,
			success: function (xml) {
				if($(xml).text() == ""){
					alertMsg.info("未找到要查询的信息!");
					return;
				}
				cur_tr.find("[name=Td12_gljf.JF_ID]").val($(xml).find("jf_id").text());
				cur_tr.find("[name=Td12_gljf.JDMC]").val($(xml).find("jdmc").text());
				cur_tr.find("[name=Td12_gljf.JFMC]").val($(xml).find("jfmc").text());
			},
			error: DWZ.ajaxError
		});	
	}
}
function xgts(){
	url = "dispath.do?url=info/xgts.jsp?doc_id=${td11_jfpmsq.id}";
	$.pdialog.open(url,'_upload_slave_form','修改设计天数',{width:200,height:150});
}

function xgsjry(){
	url = "info/goModifySjry.do?opernode_id=${param.opernode_id}&doc_id=${td11_jfpmsq.id}&project_id=${param.project_id}&module_id=${param.module_id}";
	$.pdialog.open(url,'_xgsjry_form','修改设计人员',{width:400,height:200});
}

function zzlc(){
	var t_project_id = '${td11_jfpmsq.id}';
	$.ajax({
			type: 'post',
			url: 'ajaxStopFlow.do',
			data: {'project_id':t_project_id},
			dataType:'text',
			success: function (json) {
				alertMsg.correct('流程中止成功');
				docReload();
			},
			error:function(){alertMsg.info('操作失败，请重试'); }
		});	
}
</script>
<input type="hidden" name="configType" value="byxml"/>
<input type="hidden" name="profile" value="jfpmsq.xml"/>
<input type="hidden" name="Td11_jfpmsq.ID" id="Td11_jfpmsq.ID" value="${param.doc_id}">
	
<div class="pageFormContent">
	<p>
		<label>申请部门：</label>
		<input type="text" readOnly name="Td11_jfpmsq.FWBM" value="<c:out value="${td11_jfpmsq.fwbm}" default="${user.dept_name}"/>" style="width:407px;"/>
	</p>
	<p>
		<label>表单编号：</label>
		<input type="text" name="Td11_jfpmsq.BDBH" value="${td11_jfpmsq.bdbh}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>申请人：</label> 
		<input type="text" readOnly name="Td11_jfpmsq.CJR" value="<c:out value="${td11_jfpmsq.cjr}" default="${user.name}"/>" style="width:150px;"/>
	</p>
	<p>
		<label>电话：</label>
		<input type="text" readOnly name="Td11_jfpmsq.CJRDH" value="<c:out value="${td11_jfpmsq.cjrdh}" default="${user.mobile_tel}"/>" style="width:150px;"/>
	</p>
	<p>
		<label>提出日期：</label>
		<input readonly type="text" name="Td11_jfpmsq.CJRQ" style="width:120px;" value="<c:choose><c:when test="${empty param.doc_id}"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm"/></c:when><c:otherwise><fmt:formatDate value="${td11_jfpmsq.cjrq}" pattern="yyyy-MM-dd HH:mm"/></c:otherwise></c:choose>"/>
	</p>
	<div class="divider"></div>
	<p>
		<label>项目名称：</label>
		<input type="text" name="Td11_jfpmsq.XMMC" value="${td11_jfpmsq.xmmc}" style="width:407px;"/>
	</p>
	<p>
		<label>建设性质：</label>
		<netsky:htmlSelect name="Td11_jfpmsq.JSXZ" objectForOption="jsxzList" style="width:123px;" valueForOption="name" showForOption="name" value="${td11_jfpmsq.jsxz}" htmlClass="td-select"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>设计单位：</label>
		<netsky:htmlSelect name="Td11_jfpmsq.SJDW" id="Td11_jfpmsq.SJDW" objectForOption="sjdwList" style="width:193px;" valueForOption="name" showForOption="name" extend="内部设计,内部设计" extendPrefix="true"  onChange="getDesigner('sjry')" value="${td11_jfpmsq.sjdw}" htmlClass="td-select"/>
	</p>
	<p>
		<label>设计人员：</label>
		<netsky:htmlSelect id="sjry" name="Td11_jfpmsq.SJRY" objectForOption="sjryList" style="width:120px;" valueForOption="name" showForOption="name" value="${td11_jfpmsq.sjry}" htmlClass="td-select"/>
	</p>
	<p>
		<label>所属专业：</label>
		<netsky:htmlSelect name="Td11_jfpmsq.SSZY" objectForOption="zyList" style="width:123px;" valueForOption="name" showForOption="name" value="${td11_jfpmsq.sszy}" htmlClass="td-select"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>设计天数：</label>
		<input type="text" name="Td11_jfpmsq.SJSX" value="${td11_jfpmsq.sjsx}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label><c:choose>
			<c:when test="${param.module_id==101}">申请说明：</c:when>
			<c:otherwise>变更原因：</c:otherwise>
		</c:choose></label>
		<textarea class="td-textarea" style="width:630px;height:80px;" type="text" name="Td11_jfpmsq.BZ">${td11_jfpmsq.bz}</textarea>
	</p>
</div>

<div style="text-align:left;color:blue;"><h3>项目关联机房&nbsp;<c:if test="${not empty canSave && canSave=='yes' && not empty param.doc_id}"><a class="icon" href="info/sqdExcelJfxxJsp.do?module_id=${param.module_id}&project_id=${param.project_id }" target="dialog" rel="jfimport" width="360" height="200" title="导入机房信息">【<font color="red">EXCEL导入</font>】</a></c:if></h3></div><div class="divider" style="height:1px;"></div>

<c:choose>
	<c:when test="${not empty canSave && canSave=='yes'}">
		<table width="100%" class="list" itemdetail="gljfDetail" width="100%">
			<thead>
				<tr>
					<th type="lookup" name="Td12_gljf.JDMC" hideName="Td12_gljf.ID" lookupName="gljfLookup" lookupUrl="selectJfxx.do" suggestUrl="ajaxAutocompleteJfxx.do" suggestFields="Td12_gljf.JF_ID,Td12_gljf.JDMC,Td12_gljf.JFMC" autocomplete="off">局点名称</th>
					<th type="text" id="gljfDetail[#index#].dwz_gljfLookup.Td12_gljf.JFMC" name="Td12_gljf.JFMC" hideName="Td12_gljf.JF_ID" hideId="gljfDetail[#index#].dwz_gljfLookup.Td12_gljf.JF_ID">机房名称</th>
					<th type="text"  style="width:100px;" name="Td12_gljf.XZJJSL">新增机架数量</th>
					<th type="text" style="width:85px;" name="Td12_gljf.JJCC">机架尺寸</th>
					<th type="enum" style="width:85px;" name="Td12_gljf.SBGDFS" enumName="gdfs" enumUrl="info/propertySelect.do" enumData="{'type':'供电方式'}">供电方式</th>
					<th type="text" style="width:200px;" name="Td12_gljf.BZ">备注</th>
					<th type="del" style="width:30px;">操作</th>
				</tr>
			</thead>
			<tbody>
			<c:set var="offset" scope="page" value="0"/>
			<c:forEach var="obj" items="${td12_gljf}">
				<tr>
					<td>
						<input type="hidden" name="Td12_gljf.ID" value="${obj.id}"/>
						<input type="text" id="gljfDetail[${offset }].dwz_gljfLookup.Td12_gljf.JDMC" name="Td12_gljf.JDMC" value="${obj.jdmc}" style="width:0px;" index="${offset }" lookupGroup="gljfDetail"  lookupName="gljfLookup" suggestUrl="ajaxAutocompleteJfxx.do" suggestFields="Td12_gljf.JF_ID,Td12_gljf.JDMC,Td12_gljf.JFMC" autocomplete="off"/>
						<a class="btnLook" href="selectJfxx.do" index="${offset }" lookupGroup="gljfDetail"  lookupName="gljfLookup">查找带回</a>
					</td>
					<td>
						<input type="hidden" id="gljfDetail[${offset }].dwz_gljfLookup.Td12_gljf.JF_ID" name="Td12_gljf.JF_ID" value="${obj.jf_id}"/>
						<input type="text" id="gljfDetail[${offset }].dwz_gljfLookup.Td12_gljf.JFMC" name="Td12_gljf.JFMC" value="${obj.jfmc}" style="width:0px;"/>
					</td>
					<td><input type="text" name="Td12_gljf.XZJJSL" value="${obj.xzjjsl}" style="width:0px;"/></td>
					<td><input type="text" name="Td12_gljf.JJCC" value="${obj.jjcc}" style="width:0px;"/></td>
					<td><netsky:htmlSelect name="Td12_gljf.SBGDFS" objectForOption="gdfsList" valueForOption="name" showForOption="name" value="${obj.sbgdfs}" extend="" style="width:0px;"/></td>
					<td><input type="text" name="Td12_gljf.BZ" value="${obj.bz}" style="width:0px;"/></td>
					<!--<td><textarea name="Td12_gljf.BZ" id="Td12_gljf.BZ" style="border-width:0;overflow-y:hidden;height:18px;" >${obj.bz}</textarea></td>-->
					<td><a href="javascript:fsd" class="btnDel emptyInput" title="确认删除此明细">删除</a></td>
				</tr>
				<c:set var="offset" scope="page" value="${offset+1 }"/>
			</c:forEach>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<table width="100%" class="list nowrap">
			<thead>
				<tr>
					<th style="width:80px;">局点名称</th>
					<th style="width:120px;">机房名称</th>
					<th style="width:100px;">新增机架数量</th>
					<th style="width:80px;">机架尺寸</th>
					<th style="width:80px;">供电方式</th>
					<th style="width:200px;">备注</th>
					<th style="width:100px;">状态</th>
					<th style="width:60px;">操作</th>
					<th>&nbsp;</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="obj" items="${td12_gljf}">
				<tr>
					<td>${obj.jdmc}</td>
					<td>${obj.jfmc}</td>
					<td>${obj.xzjjsl}</td>
					<td>${obj.jjcc}</td>
					<td>${obj.sbgdfs}</td>
					<td>${obj.bz}</td>
					<td>
						<c:choose>
							<c:when test="${not empty obj.zhgxsj}"><font color="blue">已更新</font></c:when>
							<c:when test="${not empty obj.scsj}"><font color="blue">已上传</font></c:when>
							<c:when test="${not empty obj.xzsj}"><font color="blue">已下载</font></c:when>
							<c:otherwise><font color="red">申请中</font></c:otherwise>
						</c:choose>
					</td>
					<td><a href="pmsqDetail.do?sqd_id=${param.doc_id }&module_id=${param.module_id }&project_id=${param.project_id }&doc_id=${obj.id }&jfxx_id=${obj.jf_id}&node_id=${param.node_id }&limit=" rel="pmsqDetail" target="dialog" width="800" height="500" title="机房图纸">图纸</a> &nbsp;
						<a href="pmsqSbmx.do?module_id=${param.module_id }&project_id=${param.project_id }&doc_id=${obj.id }&jfxx_id=${obj.jf_id}&node_id=${param.node_id }&limit=w" target="dialog" rel="sqsbmx" width="800" height="500" title="机房设备">设备</a>
					</td>
					<td>&nbsp;</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</c:otherwise>
</c:choose>