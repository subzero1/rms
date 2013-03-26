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
<script src="../js/chart/highcharts.js"></script>
<script type="text/javascript">
    
	var need_data = '${show_data}';
	if(need_data != ''){
		var dq_obj = need_data.split(",1]");
		if(dq_obj != null){
			for(var i = 0;i < dq_obj.length;i++){
				var dq_data = dq_obj[i];
				var zy_datas = dq_data.split(":1]")[1];
				var show_dq = dq_data.split(":1]")[0];
				zy_datas = zy_datas.substring(1,zy_datas.length-1);
				var zy_obj = zy_datas.split(",2]");
				for(var j=0;j < zy_obj.length;j++){
					var zy_data = zy_obj[j];
					var dw_datas = zy_data.split(":2]")[1];
					var show_zy = zy_data.split(":2]")[0];
					var dw_datas = dw_datas.substring(1,dw_datas.length-1);
					var dw_obj = dw_datas.split(",");
					var dw_array = new Array();
					for(var ii = 0;ii < dw_obj.length;ii++){
						dw_array[ii] = new Array();
						dw_array[ii][0] = dw_obj[ii].split(":")[0];
						dw_array[ii][1] = parseFloat(dw_obj[ii].split(":")[1]);
					}
					
					var random_offset = Math.round(Math.random()*1000);
					var obj = document.getElementById("outContainerDiv");
					var tmpDiv = document.createElement("div");
					tmpDiv.setAttribute("id","containerDiv"+random_offset);
					tmpDiv.style.width = "900px";
					tmpDiv.style.height = "400px";
					tmpDiv.style.background = "#ccc";
					obj.appendChild(tmpDiv);
					
					var datasource={};//初始化datasource
					datasource.Chart_title_text=show_dq+" "+show_zy+" 各单位份额占比";   
					datasource.Chart_series_name="各单位份额占比";
    				datasource.Chart_series_data=dw_array; 
    				datasource.Chart_chart_renderTo="containerDiv"+random_offset;
    				createPieChart(datasource);
				}
			}
		}
	}
	
	
    function createPieChart(_datasource){
    	var chart;
		//var datasource=dataPieChart(valueX);
       	var _chart={
           chart: {
               renderTo: 'containerDiv',
               plotBackgroundColor: null,
               plotBorderWidth: null,
               plotShadow: false
           },
           title: {
               text: 'Browser market shares at a specific website, 2010'
           },
           tooltip: {
       	    pointFormat: '{series.name}: <b>{point.percentage}%</b>',
           	percentageDecimals: 2
           },
           plotOptions: {
               pie: {
                   allowPointSelect: true,
                   cursor: 'pointer',
                   dataLabels: {
                       enabled: true,
                       color: '#000000',
                       connectorColor: '#000000',
                       formatter: function() {
                           return '<b>'+ this.point.name +'</b>: '+ (this.percentage).toFixed(2) +' %';
                       }
                   }
               }
           },
           series: [{
               type: 'pie',
               name: 'Browser share',
               data: [
                   ['Firefox',   45.0],
                   ['IE',       26.8],
                   ['Safari',    8.5],
                   ['Opera',     6.2],
                   ['Others',   0.7],
                   {
                       name: 'Chrome',
                       y: 12.8,
                       sliced: true,
                       selected: true
                   }
               ]
           }]
       }; 
        _chart.series[0].name=_datasource.Chart_series_name;
        _chart.title.text=_datasource.Chart_title_text;
        _chart.chart.renderTo=_datasource.Chart_chart_renderTo;
        _chart.series[0].data=_datasource.Chart_series_data; 
        //alert(_chart.series[0].data);
        chart=new Highcharts.Chart(_chart); 
     }  
	//container = document.createElement("div");
		//container.style.cssText = vb + "width:0;height:0;position:static;top:0;margin-top:" + conMarginTop + "px";
		//body.insertBefore( container, body.firstChild );
</script>
<body>
<div class="pageContent">
<div id="outContainerDiv" name="outContainerDiv" style="width:600px;height:400px;">	 

</div>
</div>
</body>