<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<script type="text/javascript">
 	function searchSubmit(){
 		var $keyword=$("#keyword",navTab.getCurrentPanel());
 		navTab.openTab('xmxxList', 'search/dqZyFezb.do?keyword='+$keyword.val(), {title:'项目信息列表'});
 	}
 	$(function(){
 		$("#keyword",navTab.getCurrentPanel()).keyup(function(e){
 			if(e.which==13)
 				searchSubmit();
 		});
 		
 	});
</script>
<form id="pagerForm" method="post" action="search/dqZyFezb.do">
	<input type="hidden" name="keyword" value="${param.keyword}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />
	<input type="hidden" name="jssj" value="${param.jssj }" />
	<input type="hidden" name="xmzt" value="${param.xmzt }" />
	<input type="hidden" name="xmgly" value="${param.xmgly }" />
</form>

<div class="page">
	<div class="pageHeader" style="height: 320px;border: 0px;">
		<form action="search/dqZyFezb.do" method="post"
			onsubmit="return navTabSearch(this);">
			<div class="pageFormContent" layoutH="53">
			<div style="height:0px;"></div>
					<p>
							<label>工程类别：</label>
							<netsky:htmlSelect name="gclb" objectForOption="gclbList"
								style="width:92px;" valueForOption="name" extend=""
								extendPrefix="true" showForOption="name" value="${param.ssdq}"
								htmlClass="td-select" />
					</p>
					<div style="height:0px;"></div>
						<p>
							<label>所属地区：</label>
							<netsky:htmlSelect name="ssdq" objectForOption="areaList"
								style="width:92px;" valueForOption="name" extend=""
								extendPrefix="true" showForOption="name" value="${param.ssdq}"
								htmlClass="td-select" />
						</p>
						<div style="height:0px;"></div>
					<p>
						<label>派工时间：</label>
							<input id="start_sgpfsj" name="start_sgpfsj" value="${param.keyword}"
								type="text" size="15" class="date"/>
							 <label>至</label><input id="end_sgpfsj" name="end_sgpfsj" value="${param.keyword}"
								type="text" size="15" class="date"/>
					</p>
					<div style="height:0px;"></div>
					<p>
						<label>立项时间：</label>
							<input id="start_lxsj" name="start_lxsj" value="${param.keyword}"
								type="text" size="15" class="date"/>
							<label>至</label><input id="end_lxsj" name="end_lxsj" value="${param.keyword}"
								type="text" size="15" class="date"/>
					</p>
					<div style="height:0px;"></div>
					<div class="subBar"> 
							<div class="buttonActive"  style="float: right;">
								<div class="buttonContent">
									<button type="button"
										onClick="javascript:searchOrExcelExport(this,'search/dqZyFezb.do',navTabSearch);$.pdialog.closeCurrent();">
										检 索
									</button>
								</div>
							</div> 
				</div>
			</div>
		</form>
	</div>

</div>