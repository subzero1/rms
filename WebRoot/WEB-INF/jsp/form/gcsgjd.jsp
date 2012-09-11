<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>工程施工进度</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript">
	$(function(){
		var dataSource=datas(); 
		var chart;
		var _chart={
			chart:{
				renderTo:'_container',
				type:'bar'
			},
			title:{
				text:'工程施工进度'
			},
			subtitle:{
				text:''
			},
			xAxis:{
				categories:["关联工程"],
				title:{
					text:null
				}
			},
			yAxis:{
				min:0, 
				max:100,
				title:{
					text:'进度(%)',
					align:'justify'
				}
			},
			tooltip:{
				formatter:function(){
					return ''+
					this.series.name+':'+this.y+'%';
				}
			},
			 plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                },
                series:{
                	cursor:'pointer',
                	events:{
                		click:function(){
                			var str=parseInt( this.name.split(".")[0]);
                		window.location.href=''+dataSource._params[str-1]; 
                			
                		}
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
            series: []
		}; 
		_chart.series = dataSource.series;  
		chart=new Highcharts.Chart(_chart);
		
	});
	function datas(){
		var dataSource={};
		var _xAxis_categories=new Array();
		var _series_data=new Array();
		var series=new Array();
		var _params=new Array();//参数表
		
		var xAxis_categories=$("input[name='xAxis_categories']");
		var series_data=$("input[name='series_data']");
		var _title=$("#_title").val();
		var subtitle=$("#subtitle").val();
		var engineer_id=$("input[name='engineer_id']");
		var params=$("input[name='paramX']");
		
		for(var i=0;i<series_data.length;i++){
			_xAxis_categories[i]=xAxis_categories[i].value;
			_series_data[i]=series_data[i].value;
			_params[i]=params[i].value;
		}
		//设置data数据源
		for(var j=0;j<_series_data.length;j++){ 
    		series[j]={};
    		series[j].data=new Array();
    		series[j].name=(j+1)+"."+_xAxis_categories[j];
    		series[j].data[0]=parseInt(100* _series_data[j]);  
    		series[j].dataURL="sggsgsgsgsgsgs";
    	}  
    	dataSource._params=_params;
    	dataSource._title=_title;
    	dataSource.subtitle=subtitle;
    	dataSource.series=series;
		dataSource.xAxis_categories=_xAxis_categories;
		dataSource.series_data=_series_data; 
			return dataSource;
	} 
</script>
	</head>

	<body>
		<div id="_container"
			style="min-width: 400px; height: 400px; margin: 0 auto"></div>
		<form action="" name="dataSource" id="dataSource">
			<input type="hidden" name="_title" id="_title" value="工程施工进度" />
			<input type="hidden" name="_subtitle" id="subtitle" value="" />
			<c:forEach var="engineer" items="${engineerList}">
				<input type="hidden" name="engineer_id" value="${engineer.id}" />
				<input type="hidden" name="xAxis_categories"
					value="${engineer.gcmc}" />
				<input type="hidden" name="series_data" value="${engineer.sgjd}" />
				<input type="hidden" name="series_name" value="${engineer.gcmc}" />
			</c:forEach>
			<c:forEach var="paramX" items="${paramList}">
				<input type="hidden" name="paramX" value="${paramX}"/>
			</c:forEach> 
		</form>
	</body>
</html>
