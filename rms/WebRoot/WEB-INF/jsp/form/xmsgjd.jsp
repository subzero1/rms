<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script type="text/javascript">
			
 
	
  $(document).ready(function() { 
  		    var chart; 
  		    var dataSource=datas();  
    		var chart1 = {
            chart: {
                renderTo: 'container1',
                type: 'bar'
            },
            title: {
                text: '项目施工进度'
            },
            subtitle: {
                text: null
            },
            xAxis: {
                categories: dataSource.xmmc,
                title: {
                    text: null
                }
            },
            yAxis: {
                min: 0,
                max:100,
                title: {
                    text: '施工进度 (%)',
                    align: 'high'
                },
                labels: {
                    overflow: 'justify'
                }
            },
            tooltip: {
                formatter: function() {
                    return ''+
                        this.series.name +': '+ this.y +' %';
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
                			var str=parseInt(this.name.split(".")[0]); 
                			window.location.href=''+dataSource.params[str-1];
                			alert(dataSource.params[str-1]);
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
        chart1.series = dataSource.series;  
	    chart = new Highcharts.Chart(chart1);   
    
    }); 

    function datas(){ 
    var dataSource={};
    var gcmc=$("input[name='gcmc']"); //工程名称
    var sgjd=$("input[name='sgjd']");//施工进度
    var xmmc=$("input[name='xmmc']").val();//项目名称 
    var params=$("input[name='paramX']");//链接参数列表
    //设置x坐标标题
    var _gcmc=new Array();
    var _sgjd=new Array();
    var _xmmc=new Array();
    var _series=new Array();
    var _params=new Array();
    for(var i=0;i<gcmc.length;i++){
		_gcmc[i]=gcmc[i].value;
		_sgjd[i]=sgjd[i].value;
		 _params[i]=params[i].value; 
		}   
	_xmmc[0]=xmmc;
     // 设置series数据源 
    	for(var j=0;j<_gcmc.length;j++){ 
    		_series[j]={};
    		_series[j].data=new Array();
    		_series[j].name=(j+1)+"."+_gcmc[j];
    		_series[j].data[0]=parseInt(100* _sgjd[j]); 
    	}  
    	dataSource.xmmc=_xmmc;
    	dataSource.gcmc=_gcmc;
    	dataSource.sgjd=_sgjd;
    	dataSource.params=_params;
    	dataSource.series=_series; 
    	return dataSource;
    };
	 
	
		</script>


<div id="container1"
	style="min-width: 400px; height: 400px; margin: 0 auto"></div>
<form action="" name="proForm" id="proForm">
	<input type="hidden" name="theme" value="施工进度统计" />
	<input type="hidden" name="xtitle" value="process(%)" />
</form>
&gt;&nbsp;
<form action="" name="engForm" id="engForm">
	<input type="hidden" name="xmmc" value="${project.xmmc }" />
	<c:forEach var="en" items="${projectList}">
		<input type="hidden" name="gcmc" value="${en.gcmc}">
		<input type="hidden" name="xm_id" value="${en.xm_id}" />
		<input type="hidden" name="sgjd" value="${en.sgjd}" />
	</c:forEach>

	<c:forEach var="paramX" items="${paramList}">
		<input type="text" name="paramX" value="${paramX}" />
	</c:forEach>
</form>

