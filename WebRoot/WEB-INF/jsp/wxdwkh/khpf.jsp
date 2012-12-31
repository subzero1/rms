<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>

<script language="javascript">
function setPfbz(tdObj){
	var $bz = $(tdObj).find(".bz");
	var input_info = "<p><textarea  style=\"width:220px;height:100px;\" id=\"_bz\">"+$bz.val()+"</textarea></p>";
	alertMsg.input(input_info, {title:'评分说明',		
		okCall: function(){
			var _bz = $("#_bz").val();
			$bz.val(_bz);
		}
	});
}

function saveFz(){
	$("#pfbody",navTab.getCurrentPanel()).find(".fz").each(function(){
		var $this = $(this);
		if($this.val()=="")
			$this.closest("td").find("input").not(".id").val("");
	});
	$("#khpf_form").submit();
}

</script>

<div class="page">
	<div class="pageHeader">
		<span style="font-size:14px;font-weight:bold;">${tf19.khmc }</span>
		&nbsp;&nbsp;<span style="color:#888;font-weight:bold;">[ <fmt:formatDate value="${tf19.khkssj }" pattern="yyyy年MM月dd日"/> —— <fmt:formatDate value="${tf19.khjssj }" pattern="yyyy年MM月dd日"/> ]</span>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<c:if test="${canSave==1}">
					<li><a class="save"	href="javascript:saveFz();"><span>保 存</span></a></li>
					<li class="line">line</li>
				</c:if>
			</ul>
			<div style="float:right;"><a href="wxdwkh/khsm.do?kh_id=${tf19.kh_id }" target="dialog" width="800" height="550" rel="khsm"><font color="blue">查看考核说明</font></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
		</div>
		<form id="khpf_form" method="post" action="save.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="tableInfomation" value="noFatherTable:com.rms.dataObjects.wxdw.Tf20_khxxmx" keep="true"/>
			<input type="hidden" name="_navTabId" value="" keep="true"/>
			<input type="hidden" name="_message" value="评分保存" keep="true"/>
			<input type="hidden" name="_callbackType" value="forward" keep="true"/>
			<input type="hidden" name="_forwardUrl" value="wxdwkh/khpf.do?khxx_id=${tf19.id }" keep="true"/>		
						
			<table class="table" layouth="82">
				<thead>
					<tr>
						<th style="width: 240px;">合作单位</th>
						<c:forEach items="${khxList}" var="khx">
							<th style="width: 120px;">${khx.khx }</th>
						</c:forEach>
					</tr>
				</thead>
				<tbody id="pfbody">
					<c:forEach items="${hzdwList}" var="hzdw">
					<tr>
						<td>${hzdw.mc }</td>
						<c:forEach items="${khxList}" var="khx">
							<td style="text-align: center" ondblclick="javascript:setPfbz(this);">
								<input type="hidden" name="Tf20_khxxmx.KH_ID" value="${param.khxx_id }"/>
								<input type="hidden" name="Tf20_khxxmx.KHX_ID" value="${khx.id }"/>
								<input type="hidden" name="Tf20_khxxmx.WXDW_ID" value="${hzdw.id }"/>
								<input type="hidden" name="Tf20_khxxmx.USER_ID" value="${user.id }"/>
								
								<c:set var="hzdw_id" ><c:out value="${hzdw.id}"/></c:set>
								<c:set var="khx_id" ><c:out value="${khx.id}"/></c:set>	
								<c:choose>
									<c:when  test="${not empty khpfMap[hzdw_id][khx_id] }">
										<input type="hidden" name="Tf20_khxxmx.ID" class="id" value="${khpfMap[hzdw_id][khx_id].id }"/>
										<input type="hidden" name="Tf20_khxxmx.BZ" class="bz" value="${khpfMap[hzdw_id][khx_id].bz }"/>
										<input type="text"  name="Tf20_khxxmx.FZ" style="width:90%;text-align:center;" class="fz request digits" max="${khx.zgfz }" min="0" value="${khpfMap[hzdw_id][khx_id].fz }"/>
									</c:when>
									<c:otherwise>
										<input type="hidden" name="Tf20_khxxmx.ID" class="id" value=""/>
										<input type="hidden" name="Tf20_khxxmx.BZ" class="bz" value=""/>
										<input type="text" name="Tf20_khxxmx.FZ" style="width:90%;text-align:center;" class="fz request digits" max="${khx.zgfz }" min="0" value=""/>
									</c:otherwise>
								</c:choose>
							</td>
						</c:forEach>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
</div>
