<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
 
<style type="text/css"> 
.comdiv { 

z-index: 3; 

position: absolute; 

padding: 5px; 
  
text-align: left; 

color: black; 

background-color:#ebf0f5;

width: 221px; 

font-size: 13px; 

font-family: arial, sans-serif;   

border:1px #000 solid;

display:none;


} 

.moseover_style{
	background:
}
</style>
<form id="pagerForm" method="post" action="wxdw/zytlrList.do">
	<input type="hidden" name="ssdw" value="${param.ssdw}">
	<input type="hidden" name="keyword" value="${param.keyword}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>
<script type="text/javascript">
var k=0;
function searchListExport(){
	$form = $("#pagerForm", navTab.getCurrentPanel());
	$zytlrTab=$("tbody",navTab.getCurrentPanel());
	if($zytlrTab.find("tr").size() == 0){
		alertMsg.warn("没有可输出信息!");
		return;
	} 
	$form.attr("action","wxdw/tlrToExcel.do?config=zytlr");  
	$form.submit();  
	$form.attr("action","");
} 
function saveForm(){
	$("#zytlrForm",navTab.getCurrentPanel()).submit();
}
function getCompany(_this){
		var $SSDW=$(_this);
		var input_id=$SSDW.attr("id");
 		$.ajax({
		type:"post",
		url:"wxdw/getCompanyAjax.do",
		dataType:"json",
		async:false,
		data:{ssdw:$(_this).val()},
		success:function(json){
			var companys="";
			var $comdiv=$(".comdiv", navTab.getCurrentPanel());
			if(json!=null&&json!=""){
			for(var i=0;i<json.length;i++){ 
				companys+="<div id=\"com_"+i+"\" "; 
				companys+="onmouseover=\"mouseoverM(this)\" ";
				companys+="onmouseout=\"mouseoutM(this)\" ";
				companys+="onclick=\"clickM(this,'"+input_id+"')\" ";
				companys+="style=\"padding-bottom:5px;\"";
				companys+=">"; 
				companys+=json[i].ssdw+"<br>";
				companys+="</div>";
			} 
				var position_left=parseInt($(_this).position().left)+226;
				var position_top=parseInt($(_this).position().top);
				$comdiv.html(companys);
				$comdiv.css({left:position_left,top:position_top});
				$comdiv.show();
				k=directionkeysF(json.length,k,input_id);
			}else{
				$comdiv.hide();
			}
		}
	});
 }
 //隱藏層
 function hidediv(){
 	var $comdiv=$(".comdiv", navTab.getCurrentPanel());
 	$comdiv.hide();
 }
 //鼠标移过
 function mouseoverM(_this){
 	$(_this).css({background:"#3da6de",cursor: "pointer"});
 }
 //鼠标离开
 function mouseoutM(_this){
 	$(_this).css({background:"#ebf0f5"});
 }
 function clickM(_this,input_id){
	var $input=$("#"+input_id+"",navTab.getCurrentPanel());
	$input.val($(_this).text()); 
 	hidediv();
 }
 
 function directionkeysF(param0,k,input_id){
 	var  key=window.event.keyCode;
	var $input=$("#"+input_id+"",navTab.getCurrentPanel());
 	if(key ==38&&k>0){
 		k--;
 	}
 	if(key ==39){
 	}
 	if(key ==40&&k<param0-1){
 		k++;
 	}
 	var $com_=$("#com_"+k,navTab.getCurrentPanel());
 	$com_.css({background:"#3da6de",cursor: "pointer"});
 	if(key==13){
 		$input.val($com_.text()); 
 		hidediv();
 	}
 	return k;
 }
 //全選
 function checkall(_this){
 	var $zy_checkbox=$(".zy",navTab.getCurrentPanel());
 	if($(_this).attr("checked")=="checked"){
 		$zy_checkbox.each(function(){ 
 			$(this).attr("checked","true"); 
 		});  	
 	}else {
 		$zy_checkbox.each(function(){ 
 			$(this).removeAttr("checked"); 
 		}); 
 	}  
 } 
  
 $(function (){
 	var $zytlrForm_input=$("#zytlrForm",navTab.getCurrentPanel()); 
 	$zytlrForm_input.keypress(function(e){
 		if(e.which==13){ 
 			return false;
 		}
 	});
 	var $delete=$(".delete",navTab.getCurrentPanel());
 	$delete.click(function(){
 		var $zy_checkbox=$(".zy",navTab.getCurrentPanel()); 
 		var  zytl_ids="";
 		$zy_checkbox.each(function(){ 
 			if($(this).attr("checked")=="checked"){
 				if(zytl_ids=="")
 				zytl_ids+=$(this).closest("td").find(".zytl_name").val();
 				else
 				zytl_ids+=","+$(this).closest("td").find(".zytl_name").val();
 			}
 		});
 		if(zytl_ids==""){     
			return false;
 		}else{
 			$delete.attr("href",$delete.attr("href")+zytl_ids);
 		}  
 	});  
 });
</script>
<div class="page">
	<div class="pageHeader">
		<form action="wxdw/zytlrList.do" method="post" onsubmit="return navTabSearch(this);">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>
						<input type="text" style="display:none"/>
						关键字：<input id="keyword" name="keyword" value="${param.keyword}" type="text" size="25" /></td>
						<td>所属单位：<netsky:htmlSelect name="ssdw" objectForOption="wxdwList"  style="width:234px;"  onChange="javascript:$(this).submit();" extend="" extendPrefix="true" value="${param.ssdw}" htmlClass="td-select sel" /></td>
					
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" onClick="javascript:searchOrExcelExport(this,'wxdw/zytlrList.do',navTabSearch);">检 索</button></div></div></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar"> 
					<li><a class="save" href="javascript:saveForm();"><span>保存</span></a></li>
					<li class="line">line</li>
					<li><a class="add" href="wxdw/zytlEdit.do" target="navTab" rel="zytlrEdit" title="资源填录人信息"><span>添加</span></a></li>
					<li class="line">line</li>
					<li><a class="delete" href="wxdw/tlrAjaxDel.do?zytl_ids=" target="ajaxTodo" title="确认删除吗？"><span>删除</span></a></li>
					<li class="line">line</li>
					<li> <a class="exportexcel" href="dispath.do?url=form/zytlrImport.jsp?config=zytlr" target="dialog" width="400" height="200"><span>导入</span></a></li>
					<li class="line">line</li> 
					<li> <a class="exportexcel" href="javascript:searchListExport();" ><span>导出</span></a></li>
					<li class="line">line</li>
			</ul>
		</div>
		<form method="post" id="zytlrForm" action="save.do" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
			<input type="hidden" name="tableInfomation" value="noFatherTable:com.rms.dataObjects.wxdw.Tf31_zytl" />
			<input type="hidden" name="_callbackType" value="forward"/>
			<input type="hidden" name="_message" value="提交数据保存" />
			<input type="hidden" name="_forwardUrl" value="wxdw/zytlrList.do"/>
			<input type="hidden" name="_navTabId" value="zytlrList"  /> 
		<table class="table" width="100%" layouth="138" id="zytlrTab">
			<thead>
				<tr>
					<th style="width:40px;" align="center">
					<input type="checkbox" id="check_all" onclick="checkall(this)"/>
					<input type="hidden" name="zytl_ids" id="zytl_ids"/>
					</th>
					<th style="width: 80px;"  orderField="gis_no" >GIS工号</th>
					<th style="width: 80px;"  orderField="tlrxm">填录人姓名</th>
					<th style="width: 196px;" orderField="ssdw">所属单位</th>
					<th style="width: 50px;"  orderField="nx">年限</th>
					<th style="width: 100px;" orderField="in_time">进入工程中心日期</th>
					<th style="width: 100px;" orderField="rzcj">认证成绩</th>
					<th style="width: 100px;" orderField="phone">联系电话</th>
					<th style="width: 100px;" orderField="zc">专长</th> 
				</tr>
			</thead>
			<tbody>
				<c:set var="offset" value="0"/>
				<c:forEach var="obj" items="${Tf31_zytlList}">
				<c:set var="offset" value="${offset+1}"/>
					<tr target="gc_id" rel="${obj[0].id}">
						<td style="text-align:center;">
						<input type="checkbox" class="zy" />
						<input type="hidden" name="zytl_name" value="${obj[0].id }" class="zytl_name"/>
						</td>
						<td>${obj[0].gis_no }</td>
						<td><a href="wxdw/zytlEdit.do?zytl_id=${obj[0].id }" target="navTab" rel="zytlrEdit" title="资源填录人信息单">${obj[0].tlrxm }</a></td>
						<td>
						<c:if test="${empty obj[1] }">
						<input type="text" name="Tf31_zytl.SSDW" id="tlr${offset}" value="${obj[0].ssdw }"  style="padding-right:0px;border:0;width:100%;color:red;" onkeyup="getCompany(this)"/>
						<input type="hidden" name="Tf31_zytl.ID" value="${obj[0].id }"/>
						</c:if>
						<c:if test="${!empty obj[1] }">
						${obj[0].ssdw }
						</c:if>
						</td>
						<td>${obj[0].nx }</td>
						<td><fmt:formatDate value="${obj[0].in_time }" pattern="yyyy-MM-dd"/></td>
						<td>${obj[0].rzcj }</td>
						<td>${obj[0].phone }</td>
						<td>${obj[0].zc }</td>
					</tr>
				</c:forEach>
				
						<div  class="comdiv"> 
						</div> 
				<c:if test="${offset<numPerPage}">
				<c:forEach begin="${offset}" end="${numPerPage-1}">
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</c:forEach>
				</c:if>
			</tbody>
		</table>
		</form>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span>
				<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})" selectValue="${numPerPage}">
					<option value="20">20</option>
					<option value="50">50</option>
					<option value="100">100</option>
					<option value="200">200</option>
				</select>
				<span>共${totalCount}条 </span>
			</div>

			<div class="pagination" targetType="navTab"	totalCount="${totalCount}" numPerPage="${numPerPage}" currentPage="${param.pageNum}"></div>

		</div>
	</div>
</div>