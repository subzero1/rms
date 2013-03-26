<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<form id="pagerForm" method="post" action="search/userLogin.do">
	<input type="hidden" name="keyword" value="${param.keyword}">
	<input type="hidden" name="pageNum" value="${param.pageNum}" />
	<input type="hidden" name="numPerPage" value="${param.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>
<script type="text/javascript">
	var need_data = '${show_data}';
	if(need_data != ''){
		var dq_obj = need_data.split(",1]");
		if(dq_obj != null){
			for(var i = 0;i < dq_obj.length;i++){
				var dq_data = dq_obj[i];
				var zy_datas = dq_data.split(":1]")[1];
				zy_datas = zy_datas.substring(1,zy_datas.length-1);
				var zy_obj = zy_datas.split(",2]");
				for(var j=0;j < zy_obj.length;j++){
					var zy_data = zy_obj[j];
					var dw_datas = zy_data.split(":2]")[1];
					var dw_datas = dw_datas.substring(1,dw_datas.length-1);
					if(j==0)
						alert(dw_datas);
				}
			}
		}
	}
	
	
	//container = document.createElement("div");
		//container.style.cssText = vb + "width:0;height:0;position:static;top:0;margin-top:" + conMarginTop + "px";
		//body.insertBefore( container, body.firstChild );
</script>
<div class="page">

	<div class="pageContent">
		 
		
	</div>
</div>