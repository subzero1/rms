<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />
<script type="text/javascript">
$(function () {
    var chart;
    var categories = "${categories}".split(",");
    var sjjddata_tmp = "${sjjddata}".split(",");
    var tbjddata_tmp = "${tbjddata}".split(",");
    var sjjddata = [];
    for (i in sjjddata_tmp) {
    	sjjddata.push(parseFloat(sjjddata_tmp[i]));
    }
    var tbjddata = [];
    for (i in tbjddata_tmp) {
    	tbjddata.push(parseFloat(tbjddata_tmp[i]));
    }
    $(document).ready(function() {
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'highchartscontainer',
                type: 'line',
                marginRight: 130,
                marginBottom: 40
            },
            title: {
                text: '曲线图',
                x: -20 //center
            },
            xAxis: {
                categories: categories,
                 title: {
                    text: '日期'
                }
            },
            yAxis: {
                title: {
                    text: '进度'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                formatter: function() {
                        return '<b>'+ this.series.name +'</b><br/>'+
                        this.x +': '+ this.y +'%';
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -10,
                y: 100,
                borderWidth: 0
            },
            series: [{
                name: '时间进度',
                data: sjjddata
            }, {
                name: '填报进度',
                data: tbjddata
            }]
        });
    });
    
});
		</script>
<div class="page">

	<!-- 表单头 -->
	<div class="pageHeader">
		<div class="searchBar">
			<!-- 表单名称 -->
			<h1>资源信息单</h1>
			
		</div>
	</div>
	
	<!-- 主操作按钮 -->
	<div class="panelBar">
	<!-- 
		<ul class="toolBar">
		 	<li><a class="save"	href="javascript:saveMbk();"><span>保 存</span></a></li>
			<li class="line">line</li>
		</ul>
	 -->
	</div>
	
	
	<div class="pageContent" layouth="48">
			<div class="pageFormContent">
				<p>
					<label>工程编号：</label>
					<input readonly="readonly" type="text" style="width:150px;" value="${gcxx.gcbh}"/>
				</p>
				<p>
					<label>工程名称：</label>
					<input readonly="readonly" type="text" style="width:376px;" value="${gcxx.gcmc}" />
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>派工日期：</label>
					<input readonly="readonly" type="text" style="width:150px;" value="<fmt:formatDate value="${gcxx.sgpfsj}" pattern="yyyy-MM-dd"/>"/>
				</p>
				<p>
					<label>计划竣工日期：</label>
					<input readonly="readonly" type="text" style="width:376px;" value="<fmt:formatDate value="${gcxx.jhjgsj}" pattern="yyyy-MM-dd"/>"/>
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>填报周期：</label>
					<input readonly="readonly" type="text" style="width:150px;" value="${empty gcxx.sgjdtbzq ? '默认3' : gcxx.sgjdtbzq}天"/>
				</p>
				<p>
					<label>施工单位：</label>
					<input readonly="readonly" type="text" style="width:376px;" value="${gcxx.sgdw}" />
				</p>
				<div style="height:0px;"></div>
				<p>
					<label>时间进度：</label>
					<input readonly="readonly" type="text" style="width:150px;" value="<fmt:formatNumber pattern="0.00%" value="${gcxx.sjjd}"/>"/>
				</p>
				<p>
					<label>最新反馈进度：</label>
					<input readonly="readonly" type="text" style="width:376px;" value="<fmt:formatNumber pattern="0.00%" value="${gcxx.tbjd}"/>" />
				</p>
		</div>
		<div id="highchartscontainer" style="width: 770px; height: 280px;"></div>
		<div style="text-align:left;color:blue;"><h3>&nbsp;&nbsp;进度反馈记录</h3></div><div class="divider" style="height:1px;"></div>
		<table class="table" width="100%">
		<thead>
			<tr>
				<th style="width: 100px;">反馈日期</th>
				<th style="width: 80px;">时间进度</th>
				<th style="width: 80px;">反馈进度</th>
				<th style="width: 80px;">填报人</th>
				<th>说明</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${tf10List}" var="obj">
				<tr>
					<td><fmt:formatDate value="${obj.tbrq }" pattern="yyyy-MM-dd"/></td>
					<td><fmt:formatNumber value="${obj.sjjd }" pattern="0.00%"/></td>
					<td><fmt:formatNumber value="${obj.tbjd }" pattern="0.00%"/></td>
					<td>${obj.tbr }</td>
					<td>${obj.jdms }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
		
		
	</div>
</div>
