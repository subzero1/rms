<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />
<script type="text/javascript">
	$(function(){
	   		$("#jsxz",navTab.getCurrentPanel()).cascade({
				childSelect:$("#jsfs",navTab.getCurrentPanel()),
				tableName:'Tc12_jsfs',
				conditionColumn:'jsxz_id',
				valueForOption:'mc',
				key:'id',
				orderBy:'id',
				showForOption:{
								pattern:'[mc]',
								mc:'mc'
				}
			});	
	   	});
</script>
<input type="hidden" name="configType" value="byxml"/>
<input type="hidden" name="profile" value="xqs.xml"/>
<input type="hidden" name="Td06_xqs.ID" value="${param.doc_id}">
<input type="hidden" name="Td06_xqs.PROJECT_ID" value="${param.project_id}">

<div class="pageFormContent">
	<p>
		<label>发文部门：</label>
		<input type="text" readOnly name="Td06_xqs.FWBM" value="<c:out value="${td06_xqs.fwbm}" default="${user.dept_name}"/>" style="width:407px;"/>
	</p>
	<p>
		<label>表单编号：</label>
		<input type="text" name="Td06_xqs.BDBH" value="${td06_xqs.bdbh}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>起 草 人：</label> 
		<input type="text" readOnly name="Td06_xqs.CJR" value="<c:out value="${td06_xqs.cjr}" default="${user.name}"/>" style="width:150px;"/>
	</p>
	<p>
		<label>电话：</label>
		<input type="text" readOnly name="Td06_xqs.CJRDH" value="<c:out value="${td06_xqs.cjrdh}" default="${user.mobile_tel}"/>" style="width:150px;"/>
	</p>
	<p>
		<label>提出日期：</label>
		<input readonly type="text" name="Td06_xqs.CJRQ" style="width:120px;" value="<c:choose><c:when test="${empty param.doc_id}"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/></c:when><c:otherwise><fmt:formatDate value="${td06_xqs.cjrq}" pattern="yyyy-MM-dd"/></c:otherwise></c:choose>"/>
	</p>
	<div class="divider"></div>
	<p>
		<label> 需求名称：</label>
		<input class="required" type="text" name="Td06_xqs.XQMC" style="width:406px;" value="${td06_xqs.xqmc}" />
	</p>
	<p>
		<label>所属地区：</label>
		<netsky:htmlSelect htmlClass="required" name="Td06_xqs.SSDQ" style="width:126px;" objectForOption="ssdqList" valueForOption="name" showForOption="name" value="${td06_xqs.ssdq}" extend="" extendPrefix="true" />
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>建设性质：</label>
		<netsky:htmlSelect htmlClass="required" id="jsxz" name="Td06_xqs.JSXZ" style="width:156px;" objectForOption="jsxzList" valueForOption="name" showForOption="name" value="${td06_xqs.jsxz}" valueForExtend="{'id':'[id]'}" extend="" extendPrefix="true" />
	</p>
	<p>
		<label>建设方式：</label>
		<netsky:htmlSelect htmlClass="required" id="jsfs" name="Td06_xqs.JSFS" style="width:156px;" objectForOption="jsfsList" valueForOption="mc" showForOption="mc" value="${td06_xqs.jsfs}" extend="" extendPrefix="true" />
	</p>
	<p>
		<label>所属网格：</label>
		<netsky:htmlSelect htmlClass="required" name="Td06_xqs.SSWG" style="width:126px;" objectForOption="sswgList" valueForOption="name" showForOption="name" value="${td06_xqs.sswg}" extend="" extendPrefix="true" />
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>坐落地点：</label>
		<input type="text" class="required" name="Td06_xqs.ZLDD" style="width:406px;" value="${td06_xqs.zldd}"/>
	</p> 
	<p>
		<label>建设场景：</label>
		<netsky:htmlSelect htmlClass="required" id="jscj" name="Td06_xqs.JSCJ" style="width:126px;" objectForOption="jscjList" valueForOption="name" showForOption="name" value="${td06_xqs.jscj}" extend="" extendPrefix="true" />
	</p>
	<div id="jz">
		<div style="height:0px;"></div>
		<p>
			<label>经    度：</label>
			<input type="text" ids="jz" name="Td06_xqs.JD" style="width:150px;" value="${td06_xqs.jd}"/>
		</p>
		<p>
			<label>纬    度：</label>
			<input type="text" ids="jz" name="Td06_xqs.WD" style="width:150px;" value="${td06_xqs.wd}"/>
		</p>
	</div>
	<div id="xq">
		<div style="height:0px;"></div>
		<p>
			<label>幢    数：</label>
			<input type="text" ids="xq" name="Td06_xqs.ZS" style="width:150px;" value="${td06_xqs.zs}"/>
		</p> 
		<p>
			<label>层    数：</label>
			<input type="text" ids="xq" name="Td06_xqs.CS" style="width:150px;" value="${td06_xqs.cs}"/>
		</p>
		<p>
			<label>户    数：</label>
			<input type="text" ids="xq" name="Td06_xqs.HS" style="width:120px;" value="${td06_xqs.hs}"/>
		</p>
	</div>				<div class="extend">
				<div class="divider"></div> 
					<p>
						<label>规划塔型：</label>
						 <netsky:htmlSelect htmlClass="required" name="Td06_xqs.GHTX" id="Td06_xqs.GHTX" style="width:155px;" objectForOption="ghtxList" valueForOption="" showForOption="" value="${Td06_xqs.ghtx}" extend="" extendPrefix="true" />

					</p>
					<p>
						<label>塔&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;高：</label>
						<input type="text" name="Td06_xqs.TG" id="Td06_xqs.TG" style="width:150px;" value="${Td06_xqs.tg}"/>
					</p>
					<p>
						<label>天馈挂高：</label>
						<input type="text" name="Td06_xqs.TKGG" id="Td06_xqs.TKGG" style="width:126px;" value="${Td06_xqs.tkgg}"/>
					</p>
				<div style="height:0px;"></div>
				
					<p>
						<label>机房共享属性：</label>
					<netsky:htmlSelect name="Td06_xqs.JFGXSX" id="Td06_xqs.JFGXSX" style="width:156px;" objectForOption="jfgxsxList" valueForOption="" showForOption="" value="${Td06_xqs.jfgxsx}" extend="" extendPrefix="true" />
					</p>
					<p>
						<label>塔桅共享属性：</label>
					<netsky:htmlSelect name="Td06_xqs.TWGXSX" id="Td06_xqs.TWGXSX" style="width:156px;" objectForOption="twgxsxList" valueForOption="" showForOption="" value="${Td06_xqs.twgxsx}" extend="" extendPrefix="true" />
					</p>
					<p>
						<label>站&nbsp;&nbsp;间&nbsp;&nbsp;距：</label>
						<input type="text" name="Td06_xqs.ZJJ" id="Td06_xqs.ZJJ" style="width:126px;" value="${Td06_xqs.zjj}"/>
					</p>
				<div style="height:0px;"></div>
				
					<p>
						<label>规划区域：</label>
					<netsky:htmlSelect name="Td06_xqs.GHQY" id="Td06_xqs.GHQY" style="width:156px;" objectForOption="ghqyList" valueForOption="" showForOption="" value="${Td06_xqs.ghqy}" extend="" extendPrefix="true" />
					</p>
					<p>
						<label>方&nbsp;&nbsp;位&nbsp;&nbsp;角：</label>
						<input type="text" name="Td06_xqs.FWJ" id="Td06_xqs.FWJ" style="width:150px;" value="${Td06_xqs.fwj}"/>
					</p>
					<p>
						<label>下&nbsp;&nbsp;倾&nbsp;&nbsp;角：</label>
						<input type="text" name="Td06_xqs.XQJ" id="Td06_xqs.XQJ" style="width:126px;" value="${Td06_xqs.xqj}"/>
					</p> 
				<div style="height:0px;"></div> 
				<p>
					<label>驱动原因一：</label>
					<netsky:htmlSelect name="Td06_xqs.JSQDYY1" id="Td06_xqs.JSQDYY1" style="width:156px;" objectForOption="reasonList" valueForOption="" showForOption="" value="${Td06_xqs.jsqdyy1}" extend="" extendPrefix="true" />
				</p>
				<p>
					<label>驱动原因二：</label>
					<netsky:htmlSelect name="Td06_xqs.JSQDYY2" id="Td06_xqs.JSQDYY2" style="width:156px;" objectForOption="reasonList" valueForOption="" showForOption="" value="${Td06_xqs.jsqdyy2}" extend="" extendPrefix="true" />
				</p>
				<p>
					<label>驱动原因三：</label>
					<netsky:htmlSelect name="Td06_xqs.JSQDYY3" id="Td06_xqs.JSQDYY3" style="width:126px;" objectForOption="reasonList" valueForOption="" showForOption="" value="${Td06_xqs.jsqdyy3}" extend="" extendPrefix="true" />
				</p> 
				</div>
	<div class="divider"></div>
	
	<p>
		<label>需求说明：</label>
		<textarea class="td-textarea" style="width:630px;height:80px;" type="text" name="Td06_xqs.BZ">${td06_xqs.bz}</textarea>
	</p>
	<c:if test="${not empty mbkLength && mbkLength > 0}">
		<div style="height:10px;"></div>
		<p style="color:blue;font-weight:bold;">&nbsp;&nbsp;&nbsp;相关目标库列表</p>
		<div style="width:780px;">
		<table class="table" width="100%">
			<thead>
				<tr>
					<th style="width: 30px;">序号</th>
					<th style="width: 120px;">资源编号</th>
					<th >资源名称</th>
				</tr>
			</thead>
			<tbody>
			<c:set var="offset" value="0"/>
				<c:forEach items="${mbkList}" var="obj">
				<c:set var="offset" value="${offset+1}"/>
					<tr>
						<td>${offset }</td>
						<td>${obj.zybh }</td>
						<td><a href="javascript:navTab.openTab('mbk', 'mbk/mbkEdit.do?id=${obj.id }',{'title':'目标库'});">${obj.zymc }</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
	</c:if>
	<c:if test="${not empty xmxxLength && xmxxLength > 0}">
		<div style="height:10px;"></div>
		<p style="color:blue;font-weight:bold;">&nbsp;&nbsp;&nbsp;相关项目列表</p>
		<div style="width:780px;">
		<table class="table" width="100%">
			<thead>
				<tr>
					<th style="width: 30px;">序号</th>
					<th style="width: 120px;">项目编号</th>
					<th >项目名称</th>
				</tr>
			</thead>
			<tbody>
			<c:set var="offset" value="0"/>
				<c:forEach items="${xmxxList}" var="obj">
				<c:set var="offset" value="${offset+1}"/>
					<tr>
						<td>${offset }</td>
						<td>${obj.xmbh }</td>
						<td><a href="javascript:openFlowForm('{project_id:${obj.id },doc_id:${obj.id },module_id:101,opernode_id:-1,node_id:-1,user_id:${user.id }}');">${obj.xmmc }</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
	</c:if>
	<c:if test="${not empty gcxxLength && gcxxLength > 0}">
		<div style="height:10px;"></div>
		<p style="color:blue;font-weight:bold;">&nbsp;&nbsp;&nbsp;相关工程列表</p>
		<div style="width:780px;">
		<table class="table" width="100%">
			<thead>
				<tr>
					<th style="width: 30px;">序号</th>
					<th style="width: 120px;">工程编号</th>
					<th >工程名称</th>
				</tr>
			</thead>
			<tbody>
			<c:set var="offset" value="0"/>
				<c:forEach items="${gcxxList}" var="obj">
				<c:set var="offset" value="${offset+1}"/>
					<tr>
						<td>${offset }</td>
						<td>${obj.gcbh }</td>
						<td><a href="javascript:openFlowForm('{project_id:${obj.id },doc_id:${obj.id },module_id:102,opernode_id:-1,node_id:-1,user_id:${user.id }}');">${obj.gcmc }</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
	</c:if>
</div>
<script language="javascript">
	
	$("#jsxz").change(function(){
		var data = $(this).val();
		if(data.indexOf('基站')!=-1){
			$("#jz").css("display","block");
			$("#xq").css("display","none");
			$("#jscj").attr("disabled","true");
			$("input[ids=jz]").attr("class","required");
			$("input[ids=xq]").attr("class","norequired");
		}
		else if(data.indexOf('室分')!=-1 ){
			$("#jz").css("display","none");
			$("#xq").css("display","none");
			$("#jscj").removeAttr("disabled");
			$("input[ids=jz]").attr("class","norequired");
			$("input[ids=xq]").attr("class","norequired");
		}
		else{
			$("#jz").css("display","none");
			$("#xq").css("display","block");
			$("#jscj").attr("disabled","true");
			$("input[ids=jz]").attr("class","norequired");
			$("input[ids=xq]").attr("class","required digits");
		}
	});
	
	//通过建设性质来区分显示哪些字段
	var jsxz = $("#jsxz").val();
	if(jsxz == '' || jsxz == null || jsxz.indexOf('室分')!=-1){
		$("#jz").css("display","none");
		$("#xq").css("display","none");
		$("#jscj").removeAttr("disabled");
		$("input[ids=jz]").attr("class","norequired");
		$("input[ids=xq]").attr("class","norequired");
	}
	else if(jsxz == 'undefine' || jsxz.indexOf('基站')!=-1){
		$("#jz").css("display","block");
		$("#xq").css("display","none");
		$("#jscj").attr("disabled","true");
		$("input[ids=jz]").attr("class","required");
		$("input[ids=xq]").attr("class","norequired");
	}
	else{
		$("#jz").css("display","none");
		$("#xq").css("display","block");
		$("#jscj").attr("disabled","true");
		$("input[ids=jz]").attr("class","norequired");
		$("input[ids=xq]").attr("class","required digits");
	}
	
</script>
