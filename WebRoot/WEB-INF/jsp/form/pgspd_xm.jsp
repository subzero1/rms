<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />
<script type="text/javascript">
	$(function(){
		var $Td08_pgspd_SPLB=$("select[name='Td08_pgspd\.SPLB']");
		hte_xzdw($Td08_pgspd_SPLB.val()); 
		$Td08_pgspd_SPLB.change(function(){ 
			hte_xzdw($Td08_pgspd_SPLB.val());
		});
	});
	function hte_xzdw(param){
		var $hte=$(".hte");
		var $xzdw=$(".xzdw");
		if(param=='项目派工'){
			$xzdw.show();
			$hte.hide();
			$(":input[ids=hte]").attr("disabled","true");
			$(":input[ids=xzdw]").removeAttr("disabled");
		}else if(param=='更改合同额'){
			$hte.show();
			$xzdw.hide();
			$(":input[ids=xzdw]").attr("disabled","true");
			$(":input[ids=hte]").removeAttr("disabled");
		}
	} 
</script>
<input type="hidden" name="configType" value="byxml"/>
<input type="hidden" name="profile" value="pgspd.xml"/>
<input type="hidden" name="Td08_pgspd.ID" value="${param.doc_id}">
<input type="hidden" name="Td08_pgspd.PROJECT_ID" value="${param.project_id}">

<div class="pageFormContent">
	<p>
		<label>发文部门：</label>
		<input type="text" readOnly name="Td08_pgspd.FWBM" value="<c:out value="${td08_pgspd.fwbm}" default="${user.dept_name}"/>" style="width:407px;"/>
	</p>
	<p>
		<label>表单编号：</label>
		<input type="text" name="Td08_pgspd.BDBH" value="${td08_pgspd.bdbh}" style="width:120px;"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label>起 草 人：</label> 
		<input type="text" readOnly name="Td08_pgspd.CJR" value="<c:out value="${td08_pgspd.cjr}" default="${user.name}"/>" style="width:150px;"/>
	</p>
	<p>
		<label>电话：</label>
		<input type="text" readOnly name="Td08_pgspd.CJRDH" value="<c:out value="${td08_pgspd.cjrdh}" default="${user.mobile_tel}"/>" style="width:150px;"/>
	</p>
	<p>
		<label>提出日期：</label>
		<input readonly type="text" name="Td08_pgspd.CJRQ" style="width:120px;" value="<c:choose><c:when test="${empty param.doc_id}"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/></c:when><c:otherwise><fmt:formatDate value="${td08_pgspd.cjrq}" pattern="yyyy-MM-dd"/></c:otherwise></c:choose>"/>
	</p>
	<div style="height:0px;"></div>
	<p>
		<label> 工程名称：</label>
		<input class="required" type="text" name="Td01_xmxx.XMMC" style="width:405px;" value="${td01_xmxx.xmmc}" />
	</p>
	<p>
		<label>工程编号：</label>
		<input class="required" type="text" name="Td01_xmxx.XMBH" style="width:120px;" value="${td01_xmxx.xmbh}" />
	</p> 
	<div style="height:0px;"></div>
	<p>
		<label>审批类别： </label>
		<select class="required" name="Td08_pgspd.SPLB" >
			<option value="项目派工" <c:if test="${td08_pgspd.splb=='项目派工' }">selected</c:if>>项目派工</option>
			<option value="更改合同额" <c:if test="${td08_pgspd.splb=='更改合同额'||param.splb=='gghte' }">selected</c:if>>更改合同额</option>
		</select>
	</p> 
	<div class="divider"></div>  
	<div class="hte">
	<p>
		<label>设计合同额：</label> 
		<input type="text" ids="hte" name="Td08_pgspd.YSJHTE" readOnly value="<c:out value="${td08_pgspd.ysjhte}" default="${td01_xmxx.sjhtje }"/>"  style="width:150px;"/>
	</p>
		<p>
		<label>更改后：</label> 
		<input type="text" ids="hte" name="Td08_pgspd.GGSJHTE" value="<c:out value="${td08_pgspd.ggsjhte}" default="${td01_xmxx.sjhtje }"/>" style="width:150px;"/>
	</p>
	<div style="height:0px;"></div>
		<p>
		<label>施工合同额：</label> 
		<input type="text" ids="hte" name="Td08_pgspd.YSGHTE" readOnly value="<c:out value="${td08_pgspd.ysghte}" default="${td01_xmxx.sghtje }"/>"  style="width:150px;"/>
	</p>
		<p>
		<label>更改后：</label> 
		<input type="text" ids="hte" name="Td08_pgspd.GGSGHTE" value="<c:out value="${td08_pgspd.ggsghte}" default="${td01_xmxx.sghtje }"/>"  style="width:150px;"/>
	</p>
	<div style="height:0px;"></div>	
	<p>
		<label>监理合同额：</label> 
		<input type="text" ids="hte" name="Td08_pgspd.YJLHTE" readOnly value="<c:out value="${td08_pgspd.yjlhte}" default="${td01_xmxx.jlhtje }"/>"  style="width:150px;"/>
	</p>
		<p>
		<label>更改后：</label> 
		<input type="text" ids="hte" name="Td08_pgspd.GGJLHTE" value="<c:out value="${td08_pgspd.ggjlhte}" default="${td01_xmxx.jlhtje }"/>"   style="width:150px;"/>
	</p>
	</div>
	<div class="divider hte"></div> 
	<div class="xzdw">
	<p>
		<label> 系统选择单位：</label>
		<input type="text" readOnly ids="xzdw" name="Td08_pgspd.XTXZDW" style="width:630px;" value="<c:out value="${td08_pgspd.xtxzdw}" default="${sys_wxdw_name }"/>" />
	</p> 
	<p>
		<label> 实际选择单位：</label>
		<input  type="text" name="Td08_pgspd.SJXZDW" ids="xzdw" id="sjxzOrg.SJXZDW" style="width:630px;" value="<c:out value="${td08_pgspd.sjxzdw}" default="${man_wxdw_name }"/>" />
		<a class="btnLook" lookupGroup="sjxzOrg" href="sgpd/sjxzdw.do?xm_id=${td01_xmxx.id}" width="700" height="380"></a>
	</p>
	</div>
	<div class="divider xzdw" ></div>
	<p>
		<label>原因：</label>
		<textarea class="td-textarea" style="width:630px;height:80px;" type="text" name="Td08_pgspd.SDXPYY">${td08_pgspd.sdxpyy}</textarea>
	</p>
	<p>
		<label>说明：</label>
		<textarea class="td-textarea" style="width:630px;height:80px;" type="text" name="Td08_pgspd.BZ">${td08_pgspd.bz}</textarea>
	</p>
</div>