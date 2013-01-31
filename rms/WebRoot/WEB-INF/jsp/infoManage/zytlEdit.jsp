<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />
<style>
span b {
	padding: 10px;
	padding-left: 20px;
	padding-right: 50px;
	font-size: 30px;
	color: red;
}
</style>
<script language="javascript"> 
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
			$sqkcsj=$("input[name='Td23_kcsqb\.SQSBSJ']",navTab.getCurrentPanel());
			$b=$("span b");
			if($sqkcsj.val()!=''){
				$inputs=$(":input[name^='Td23_kcsqb']");
				$inputs.attr("readonly","readonly");
				$b.attr("onclick","");
			}
			
			//设置年限 
	 		var $in_time=$("input[name='Tf31_zytl\.IN_TIME']");
	 		if ($in_time.val()!="")
				setNx($in_time);  
			$in_time.change(function(){ 
				setNx(this);
			});
			//设置专长
			var $zc=$("input[name='tf31_zytl\.ZC']"); 
			var zcs;
			$zc.click(function(){ 
				setZc(this,zcs);
			});
			
	   	}); 
	function saveMbk(){ 
		$("#mbk_form",navTab.getCurrentPanel()).submit(); 
	} 
	 function setNx(obj){
	    var $nx=$("input[name='Tf31_zytl\.NX']");
	 	var $in_time=$(obj);
		var in_time=new Date($in_time.val().replace(/-/g,"/")).getTime()/31536000000;
		var now=new Date().getTime()/31536000000;
		var nx=(now-in_time).toFixed(1); 
		$nx.val(nx);
	 }
	 function setZc(zc,zcs){  
	 	var $zcs=$("input[name='Tf31_zytl\.ZC']");
	 	zcs=$zcs.val();
	 	if($(zc).attr("checked")=='checked'){ 
	 		if(zcs==""){ 
	 			zcs+=$(zc).val();
	 		}else if(zcs.indexOf($(zc).val())<0){
	 			zcs+=(","+$(zc).val());
	 		}
	 		
	 	}else {  
	 		if(zcs.indexOf($(zc).val())>0){  
	 			 zcs=zcs.replace($(zc).val(),"");
	 			 zcs=zcs.replace(/[,]+/,",");
	 			 zcs=zcs.replace(/(^[,]+)|([,]+)$/g,"");
	 		}
	 		
	 	} 
	 	$zcs.val(zcs);
	 }
</script>
<div class="page">
	<!-- 表单头 -->
	<div class="pageHeader">
		<div class="searchBar">
			<!-- 表单名称 -->
			<h1>
				资源填录人信息
			</h1>

		</div>
	</div>
	<!-- 主操作按钮 -->
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<a class="save" href="javascript:saveMbk();"><span>保&nbsp;&nbsp;存</span>
				</a>
			</li>
			<li class="line">
				line
			</li>
		</ul>
	</div>

	<div class="pageContent" layouth="48">
		<form id="mbk_form" action="save.do" method="post"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="tableInfomation"
				value="noFatherTable:com.rms.dataObjects.wxdw.Tf31_zytl" />
			<input type="hidden" name="Tf31_zytl.ID" value="${Tf31_zytl.id}" />
			<input type="hidden" name="_callbackType" value="" />
			<input type="hidden" name="_message" value="保存" />
			<input type="hidden" name="_forwardUrl"
				value="infoManage/zytlrList.do" />
			<input type="hidden" name="_navTabId" value="zytlEdit" />


			<div class="pageFormContent">
				<p>
					<label>
						GIS系统工号：
					</label>
					<input type="text" name="Tf31_zytl.GIS_NO" style="width: 256px;"
						value="${Tf31_zytl.gis_no}" />
				</p>
				<p>
					<label>
						填录人姓名：
					</label>
					<input type="text" name="Tf31_zytl.TLRXM" style="width: 256px;"
						value="${Tf31_zytl.tlrxm}" />
				</p>

				<div style="height: 0px;"></div>
				<p>
					<label>
						年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;限：
					</label>
					<input type="text" name="Tf31_zytl.NX" id="Tf31_zytl.SSDQ"
						style="width: 256px;" value="${Tf31_zytl.nx}" readonly />
				</p>
				<p title="进入建设中心日期">
					<label>
						进&nbsp;入&nbsp;日&nbsp;期&nbsp;：
					</label>
					<input type="text" ids="jz" name="Tf31_zytl.IN_TIME"
						id="Tf31_zytl.JD" style="width: 256px;"
						value="<fmt:formatDate value="${Tf31_zytl.in_time}" pattern="yyyy-MM-dd"></fmt:formatDate>"
						class="date" pattern="yyyy-MM-dd" />
				</p>
				<div style="height: 0px;"></div>
				<p>
					<label>
						认&nbsp;证&nbsp;成&nbsp;绩&nbsp;：
					</label>
					<input type="text" ids="jz" name="Tf31_zytl.RZCJ" id="Tf31_zytl.WD"
						style="width: 256px;" value="${Tf31_zytl.rzcj}" />
				</p>
				<p>
					<label>
						联&nbsp;系&nbsp;电&nbsp;话&nbsp;：
					</label>
					<input type="text" name="Tf31_zytl.PHONE" id="Tf31_zytl.ZLDD"
						style="width: 256px;" value="${Tf31_zytl.phone}" />
				</p>
				<div style="height: 0px;"></div>
				<p>
					<label>
						所&nbsp;属&nbsp;单&nbsp;位&nbsp;：
					</label>
					<input type="text" name="Tf31_zytl.SSDW" id="Tf31_zytl.ZYMC"
						style="width: 235px;" value="${Tf31_zytl.ssdw}" />
					<a class="btnLook" lookupGroup="deptOrg" href="form/selectDept.do"></a>
				</p>
				<p style="cursor: hand;">
					<label>
						专&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;长：
					</label>
					<input type="checkbox" name="tf31_zytl.ZC" value="管道"
						<c:if test="${fn:contains(Tf31_zytl.zc,'管道')}">checked</c:if>
						  />
					管道
					<input type="checkbox" name="tf31_zytl.ZC" value="电缆"
						<c:if test="${fn:contains(Tf31_zytl.zc,'电缆')}">checked</c:if>
						 />
					电缆
					<input type="checkbox" name="tf31_zytl.ZC" value="光缆"
						<c:if test="${fn:contains(Tf31_zytl.zc,'光缆')}">checked</c:if>
						 />
					光缆
					<input type="text" name="Tf31_zytl.ZC" value="${ Tf31_zytl.zc}" />
				</p>
				<div style="height: 0px;"></div>
				<div class="divider"></div>
				<p>
					<label>
						在&nbsp;册&nbsp;情&nbsp;况&nbsp;：
					</label>
					<textarea class="td-textarea" style="width: 618px; height: 81px;"
						type="text" name="Tf31_zytl.ZCQK">${Tf31_zytl.zcqk}</textarea>
				</p>
				<p>
					<label>
						备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：
					</label>
					<textarea class="td-textarea" style="width: 618px; height: 81px;"
						type="text" name="Tf31_zytl.BZ">${Tf31_zytl.bz}</textarea>
				</p>
				<div class="divider"></div>
			</div>
		</form>
		<div style="clear: both"></div>


	</div>