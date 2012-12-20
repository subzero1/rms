<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>南京电信工程建设目标库管理系统</title>

<link href="themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<!--[if IE]>
<link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<script src="js/jquery/speedup.js" type="text/javascript"></script>
<script src="js/jquery/jquery-1.7.1.js" type="text/javascript"></script>
<script src="js/jquery/jquery.cookie.js" type="text/javascript"></script>
<script src="js/jquery/jquery.validate.js" type="text/javascript"></script>
<script src="js/jquery/jquery.bgiframe.js" type="text/javascript"></script>
<script src="xheditor-1.1.13/xheditor-1.1.13-zh-cn.min.js" type="text/javascript"></script>
<script src="uploadify/scripts/swfobject.js" type="text/javascript"></script>
<script src="uploadify/scripts/jquery.uploadify.v2.1.0.js" type="text/javascript"></script>
<script src="js/jquery/jquery-ui-1.8.15.custom.min.js" type="text/javascript"></script>
		
<!-- svg图表  supports Firefox 3.0+, Safari 3.0+, Chrome 5.0+, Opera 9.5+ and Internet Explorer 6.0+ -->
<!--
<script type="text/javascript" src="js/chart/raphael.js"></script>
<script type="text/javascript" src="js/chart/g.raphael.js"></script>
<script type="text/javascript" src="js/chart/g.bar.js"></script>
<script type="text/javascript" src="js/chart/g.line.js"></script>
<script type="text/javascript" src="js/chart/g.pie.js"></script>
<script type="text/javascript" src="js/chart/g.dot.js"></script>
-->

<script src="js/dwz.src/dwz.core.js" type="text/javascript"></script>
<script src="js/dwz.src/dwz.util.date.js" type="text/javascript"></script>
<script src="js/dwz.src/dwz.validate.method.js" type="text/javascript"></script>
<script src="js/dwz.src/dwz.regional.zh.js" type="text/javascript"></script>
<script src="js/dwz.src/dwz.barDrag.js" type="text/javascript"></script>
<script src="js/dwz.src/dwz.drag.js" type="text/javascript"></script>
<script src="js/dwz.src/dwz.tree.js" type="text/javascript"></script>
<script src="js/dwz.src/dwz.accordion.js" type="text/javascript"></script>
<script src="js/dwz.src/dwz.ui.js" type="text/javascript"></script>
<script src="js/dwz.src/dwz.theme.js" type="text/javascript"></script>
<script src="js/dwz.src/dwz.switchEnv.js" type="text/javascript"></script>
<script src="js/dwz.src/dwz.alertMsg.js" type="text/javascript"></script>
<script src="js/dwz.src/dwz.contextmenu.js" type="text/javascript"></script>
<script src="js/dwz.src/dwz.navTab.js" type="text/javascript"></script>
<script src="js/dwz.src/dwz.tab.js" type="text/javascript"></script>
<script src="js/dwz.src/dwz.resize.js" type="text/javascript"></script>
<script src="js/dwz.src/dwz.dialog.js" type="text/javascript"></script>
<script src="js/dwz.src/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="js/dwz.src/dwz.sortDrag.js" type="text/javascript"></script>
<script src="js/dwz.src/dwz.cssTable.js" type="text/javascript"></script>
<script src="js/dwz.src/dwz.stable.js" type="text/javascript"></script>
<script src="js/dwz.src/dwz.taskBar.js" type="text/javascript"></script>
<script src="js/dwz.src/dwz.ajax.js" type="text/javascript"></script>
<script src="js/dwz.src/dwz.pagination.js" type="text/javascript"></script>
<script src="js/dwz.src/dwz.database.js" type="text/javascript"></script>
<script src="js/dwz.src/dwz.datepicker.js" type="text/javascript"></script>
<script src="js/dwz.src/dwz.effects.js" type="text/javascript"></script>
<script src="js/dwz.src/dwz.panel.js" type="text/javascript"></script>
<script src="js/dwz.src/dwz.checkbox.js" type="text/javascript"></script>
<script src="js/dwz.src/dwz.history.js" type="text/javascript"></script>
<script src="js/dwz.src/dwz.combox.js" type="text/javascript"></script>
<script src="js/dwz.src/dwz.print.js" type="text/javascript"></script>

<script src="js/dwz.src/dwz.regional.zh.js" type="text/javascript"></script>


<script src="js/jquery/jquery.jqprint.0.3.js" type="text/javascript"></script>

<script src="js/src/netsky.ui.js" type="text/javascript"></script>
<script src="js/src/netsky.buttonControl.js" type="text/javascript"></script>
<script src="js/src/netsky.js" type="text/javascript"></script>
<script src="js/src/netsky.searchReport.js" type="text/javascript"></script>
<script src="js/src/netsky.formValidator.js" type="text/javascript"></script>
<script type="text/javascript" src="js/CutPic.js"></script>
<script type="text/javascript" src="js/src/netsky.cascade.js"></script>
<script type="text/javascript" src="js/chart/highcharts.js"></script>

		
<script type="text/javascript">
$(function(){
	DWZ.init("dwz.frag.xml", {
		//loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
		loginUrl:"index.jsp",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
	
	//默认打开第一个操作
	/*
	var menuList = $("div .accordionContent").find("li a");
	if(menuList.size()>1){
		var first_oper = menuList.get(1);
		setTimeout(function(){$(first_oper).click();}, 500);
	}
	*/
	
});

//清理浏览器内存,只对IE起效,FF不需要
if ($.browser.msie) {
	window.setInterval("CollectGarbage();", 10000);
}
function strTrim(str){
	blankExp1=/^ +/;
	blankExp2=/ +$/;
	blankExp3=/^[\n\r]([\n\r]?)+/;	
	blankExp4=/[\n\r]([\n\r]?)+$/;
	str = str.replace(blankExp1,'');
	str = str.replace(blankExp2,'');
	str = str.replace(blankExp3,'');
	str = str.replace(blankExp4,'');
	return str;	
}

function filterSpace(obj,type){
	if((event.keyCode == 13 && type==1) || type==2){
		var tmpKey = obj.value;
		if(tmpKey != null && tmpKey != ''){
			tmpKey = strTrim(tmpKey);
			obj.value = tmpKey;
		}
	}
}
</script>
	</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="javascript:void(0)">标志</a>
				<ul class="nav">
					<li style="color: #666" class="LoginId">您好：${user.name }（${user.login_id }）</li>
					<li class="navBtn">
						<a	href="MessageWrite.do?type=phone" target="dialog" rel="messageWrite" title="手机短信" width="670" height="350">
							<img src="Images/phone.png"  title="短信发送"/>
						</a>
					</li>
					<li class="navBtn"><a href="pwdSetting.do" target="dialog" width="320" height="240" title="密码修改"><img src="Images/change_key.png"/></a></li>
				</ul>
				
		       <form name="pSearchForm" method="post" action="">
		   		  <div id="searchbar">
		   		  	<select class="search-sel">
		   		  	 <option value ="volvo">Volvo</option>
					  <option value ="saab">Saab</option>
					  <option value="opel">Opel</option>
					  <option value="audi">Audi</option>		   		  	
		   		  	</select>
			        <input type="text" name="" class="search-input" style="border: 0px solid #dfe3e6; background:transparent; margin:1px 0 0 20px;" value="输入工程名称或编号 按Enter键查询" onfocus="javascript:this.select();" onkeydown="javascript:filterSpace(this,1);return searchProject(1);"/>
			      </div>
		       </form>
       				
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<!--<li theme="red"><div>红色</div></li>-->
					<li theme="purple"><div>红色</div></li>
					<li theme="silver"><div>黄色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>
		</div>

    <!--Loginout-->
    <div id="loginoutbar"> &nbsp;&nbsp;<a href="logout.do" style="font-size:14px;font-weight:bold">注销登录</a> </div>
    <hr class="noscreen" />
    <!--/Loginout-->
    
		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>
				<div class="accordion" fillSpace="sidebar">
					<div class="accordionContent">
						<ul class="tree treeFolder expand">
							<li>
								<a href="#">我的文档</a>
								<ul>
									<li><a href="workList.do?workState=1" target="navTab" rel="workList">待办文档(${csMap.dbWds})</a></li>
									<li><a href="workList.do?workState=2" target="navTab" rel="workList">在办文档(${csMap.zbWds})</a></li>
									<li><a href="workList.do?workState=3" target="navTab" rel="workList">待复文档(${csMap.dfWds})</a></li>
									<li><a href="workList.do?workState=4" target="navTab" rel="workList">回复文档(${csMap.hfWds})</a></li>
									<li><a href="workList.do?workState=5" target="navTab" rel="workList">办结文档(${csMap.bjWds})</a></li>
								</ul>
							</li>
							
							<c:forEach var="menu" items="${menuList}">
								<li>
									<a href="#">${menu.name}</a>
									<ul>
										<c:forEach var="nodeElement" items="${menuListMap[menu.id]}">
											<li>
												<a href="${nodeElement.url}"
													target="${nodeElement.target}" rel="${nodeElement.rel}">${nodeElement.name}</a>
											</li>
										</c:forEach>
									</ul>
							</c:forEach>
							
							<li>
								<a href="#">辅助功能</a>
								<ul>
									<li><a href="OnLineList.do?wtlx=601" target="navTab" rel="OnLineList" title="系统公告">系统公告</a></li>
									<li><a href="MessageList.do?messageState=1" target="navTab" rel="message" title="短消息">短消息</a></li>
									<li><a href="OnLineList.do?wtlx=15" target="navTab" rel="OnLineList" title="在线提问">在线提问</a></li>
									<li><a href="userInfo.do" target="dialog" width="520" height="320" title="用户个人信息设置">个人设置</a></li>
									<li><a href="commentsList.do" target="dialog" rel="commentsList" width="700" height="450" title="审批意见维护">审批意见维护</a></li>
									<li><a href="alarm.do" target="dialog" width="480" height="200" title="超时提醒设置">超时提醒设置</a></li>
									<li><a href="help/helpList.do" target="navTab" rel="helpList" title="在线帮助">在线帮助</a></li>
									<li><a href="jlgt/jlgtList.do" target="navTab" rel="jlgtList">交流沟通列表</a></li>
								</ul>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent">
						<!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的桌面</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的桌面</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div id="desktop"  class="loadFileArea" loadfile="desktop.do">
					
						</div>
					</div>
				
				</div>
			</div>
		</div>

	</div>

	<div id="footer">
		研发单位：天津市网天信息技术有限公司 &nbsp;&nbsp;技术服务电话：022-83946861转8080
	</div>
	
	<!--报表条件-->
	<div id="mainReportCondition" style="display: none;"></div>

</body>
</html>