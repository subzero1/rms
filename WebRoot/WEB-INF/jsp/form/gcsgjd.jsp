<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

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
		var chart;
		chart=new Highcharts.Chart({
			chart:{
				renderTo:'_container',
				type:'bar'
			},
			title:{
				text:'工程施工进度'
			},
			subtitle:{
				text:'ee'
			},
			xAxis:{
				categories:['a'],
				title:{
					text:null
				}
			},
			yAxis:{
				min:0, 
				title:{
					text:'Process(%)',
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
                name: 'Year 1800',
                data: [107, 31, 635, 203, 2]
            }, {
                name: 'Year 1900',
                data: [133, 156, 947, 408, 6]
            }, {
                name: 'Year 2008',
                data: [973, 914, 1000, 732, 34]
            }]
		});
	});
</script>
  </head>
  
  <body>
 <div id="_container" style="min-width:400px;height:400px;margin:0 auto"></div>
 <form action=""></form>
  </body>
</html>
