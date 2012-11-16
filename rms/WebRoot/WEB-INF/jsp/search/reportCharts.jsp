<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>报表统计图</title>
		<script type="text/javascript">
		function createStackedBar(chart_type) {
		var datasource=_chartdata(chart_type);
    	var chart; 
        var _chart ={
            chart: {
                renderTo: 'containerDiv',
                type: 'bar'
            },
            title: {
                text: 'Stacked bar chart'
            },
            xAxis: {
                categories: ['Apples', 'Oranges', 'Pears', 'Grapes', 'Bananas']
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Total fruit consumption'
                }
            },
            legend: {
                backgroundColor: '#FFFFFF',
                reversed: true
            },
            tooltip: {
                formatter: function() {
                    return ''+
                        this.series.name +': '+ this.y +'';
                }
            },
            plotOptions: {
                series: {
                    stacking: 'normal'
                }
            },
                series: [{
                name: 'option1',
                data: [5, 3, 4, 7, 2,5]
            }, {
                name: 'option2',
                data: [2, 2, 3, 2, 1,6]
            }]
        }; 
        _chart.title.text=datasource.Chart_title_text;
        _chart.xAxis.categories=datasource.Chart_xAxis_categories;
        _chart.yAxis.title.text=datasource.Chart_yAxis_title; 
      	_chart.series=datasource.Chart_series; 
      	if(datasource.Chart_chart_type!=""){
      		_chart.chart.type=datasource.Chart_chart_type;
      	}  
        chart=new Highcharts.Chart(_chart); 
}
//    basic bar   
	function createBasicBar(chart_type){ 
		var datasource=_chartdata(chart_type);
    	var chart;  
    	var _chart={
            chart: {
                renderTo: 'containerDiv',
                type: 'column'
            },
            title: {
                text: 'Historic World Population by Region'
            },
            subtitle: {
                text: 'Source: Wikipedia.org'
            },
            xAxis: {
                categories: ['Africa', 'America', 'Asia', 'Europe', 'Oceania'],
                title: {
                    text: null
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Population (millions)',
                    align: 'high'
                },
                labels: {
                    overflow: 'justify'
                }
            },
            tooltip: {
                formatter: function() {
                    return ''+
                        this.series.name +': '+ this.y +'';
                }
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -100,
                y: 100,
                floating: true,
                borderWidth: 1,
                backgroundColor: '#FFFFFF',
                shadow: true
            },
            credits: {
                enabled: false
            },
            series: [{
                name: ' 1800年',
                data: [11071, 31, 635, 203, 2]
            }, {
                name: 'Year 1900',
                data: [11331, 156, 947, 408, 6]
            }, {
                name: 'Year 2008',
                data: [19731, 914, 4054, 732, 34]
            }]
        };
        _chart.title.text=datasource.Chart_title_text;
        _chart.xAxis.categories=datasource.Chart_xAxis_categories;
        _chart.yAxis.title.text=datasource.Chart_yAxis_title; 
      	_chart.series=datasource.Chart_series; 
      	if(datasource.Chart_chart_type!=""){
      		_chart.chart.type=datasource.Chart_chart_type;
      	}  
        chart=new Highcharts.Chart(_chart); 
        
     }
		 
		function _chartdata(chart_type){
			var datasource={};//初始化datasource
			datasource.Chart_title_text="";
			datasource.Chart_yAxis_title="";
			datasource.Chart_xAxis_categories=new Array();
			datasource.Chart_series=new Array();
			  
			var Chart_title_text=$("#chartdataForm input[name='Chart.title.text']",navTab.getCurrentPanel()).val(); 
			var Chart_yAxis_title=$("#chartdataForm input[name='Chart.yAxis.title']",navTab.getCurrentPanel()).val();
			var Chart_xAxis_categories=$("#chartdataForm input[name='Chart.xAxis.categories']",navTab.getCurrentPanel());
			var Chart_series_name=$("#chartdataForm input[name='Chart.series.name']",navTab.getCurrentPanel());
			var Chart_series_data=$("#chartdataForm input[name='Chart.series.data']",navTab.getCurrentPanel());
			
			datasource.Chart_title_text=Chart_title_text;
			datasource.Chart_yAxis_title=Chart_yAxis_title; 
			datasource.Chart_chart_type=chart_type;
			
			//分类值
			for(var i=0;i<Chart_xAxis_categories.length;i++){
				datasource.Chart_xAxis_categories[i]=Chart_xAxis_categories[i].value;
			} 
			//data数组初始化
			for(var i=0;i<Chart_series_name.length;i++){ 
				datasource.Chart_series[i]={};
				datasource.Chart_series[i].name="";
				datasource.Chart_series[i].data=new Array();
			}
			//name属性值
			for(var i=0;i<Chart_series_name.length;i++){
				datasource.Chart_series[i].name=Chart_series_name[i].value; 
			}  
			if(Chart_xAxis_categories.length>=20){
				alertMsg.info("温馨提示：您的统计项超出了范围，可能会显示不清晰！"); 
			}
			//data属性值 
			for(var i=0;i<Chart_xAxis_categories.length;i++){ 
				for(var j=0;j<Chart_series_name.length;j++){ 
				if(Chart_series_data[i*Chart_series_name.length+j].value==""){
					datasource.Chart_series[j].data[i]="0";
				}else{
					 datasource.Chart_series[j].data[i]=parseInt(Chart_series_data[i*Chart_series_name.length+j].value);
					}  
				}
			}   
			return datasource; 
		} 
		
		$(function(){
			createStackedBar('bar');
		});
		
		</script>
	</head>

	<body>
		<br>
		<div id="containerDiv"
			style="min-width: 400px; height: 400px; margin: 0 auto"></div>
			<button onclick="createStackedBar('bar')">纵向堆栈图</button>
			<button onclick="createStackedBar('column')">横向堆栈图</button>
			<button onclick="createStackedBar('line')">折线图</button>
			 <button onclick="createBasicBar('bar')">纵向柱状图</button> 
			 <button onclick="createBasicBar('column')">横向柱状图</button>
		<form action="" name="chartdataForm" id="chartdataForm">
			<input type="hidden" name="Chart.title.text" value="信息统计情况" /> 
			<input type="hidden" name="Chart.yAxis.title" value=""/> 
			<c:forEach var="result" items="${_resultList}" begin="1"> 
				<input type="hidden" name="Chart.xAxis.categories" value="${result[0].value}" />
			</c:forEach>
			<c:forEach var="result" items="${_resultList}" end="0">
					<c:forEach var="th" items="${result}" begin="1">
						<input type="hidden" name="Chart.series.name" value="${th.value}" /> 
					</c:forEach>
			</c:forEach>   
			<c:forEach var="result" items="${_resultList}" begin="1">
				<c:forEach var="th" items="${result}" begin="1">
					<input type="hidden" name="Chart.series.data" value="${th.value }"/>
				</c:forEach> 
			</c:forEach> 
		</form>
		<div> </div> </body>
</html>
