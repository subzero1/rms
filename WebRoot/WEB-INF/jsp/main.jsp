<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=7" />
		<title>南京电信工程建设目标库管理系统（RMS）</title>

		<link href="themes/default/style.css" rel="stylesheet" type="text/css" />
		<link href="themes/css/core.css" rel="stylesheet" type="text/css" />
		<link href="uploadify/css/uploadify.css" rel="stylesheet" type="text/css" />
		<link href="css/main.css" rel="stylesheet" type="text/css" />
		<!--[if IE]>
		<link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" />
		<![endif]-->
		<script src="js/speedup.js" type="text/javascript"></script>
		<script src="js/jquery-1.4.4.js" type="text/javascript"></script>
		<script src="js/jquery-ui-1.8.15.custom.min.js" type="text/javascript"></script>
		<script src="js/jquery.cookie.js" type="text/javascript"></script>
		<script src="js/jquery.validate.js" type="text/javascript"></script>
		<script src="js/jquery.bgiframe.js" type="text/javascript"></script>
		<script src="uploadify/scripts/swfobject.js" type="text/javascript"></script>
		<script src="uploadify/scripts/jquery.uploadify.v2.1.4.js" type="text/javascript"></script>
		<script src="xheditor-1.1.9/xheditor-1.1.9-zh-cn.min.js" type="text/javascript"></script>



		<script src="js/dwz.src/dwz.core.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.util.date.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.validate.method.js" type="text/javascript"></script>
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
		<script src="js/dwz.src/dwz.pngFix.js" type="text/javascript"></script>
		<script src="js/dwz.src/dwz.regional.zh.js" type="text/javascript"></script>
		
		<script src="js/src/netsky.ui.js" type="text/javascript"></script>
		<script src="js/src/netsky.buttonControl.js" type="text/javascript"></script>
		<script src="js/src/netsky.js" type="text/javascript"></script>
		<script src="js/src/netsky.searchReport.js" type="text/javascript"></script>
		<script src="js/src/netsky.formValidator.js" type="text/javascript"></script>
		<script type="text/javascript" src="js/CutPic.js"></script>

		<!--
		<script src="js/dwz.min.js" type="text/javascript"></script>
		
		<script src="js/dwz.regional.zh.js" type="text/javascript"></script>
		
		 -->
<script type="text/javascript">
$(function(){
	DWZ.init("dwz.frag.xml", {
		loginUrl:"index.jsp",	// 跳到登录页面
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"themes"});
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
</script>
	</head>

	<body scroll="no">
		<div id="layout">
			<div id="header">
				<div class="headerNav">
					<a class="logo" href="javascript:void(0)">标志</a>
					<ul class="nav">
						<li style="color: #FFF">当前用户：${user.name }（${user.login_id }）</li>
						<li style="color: #FFF">
							<a	href="MessageWrite.do?type=phone" target="dialog" rel="messageWrite" title="手机短信" width="670" height="350">
								短信发送
							</a>
						</li>
						<li><a href="logout.do">退出</a></li>
					</ul>
					<ul class="themeList" id="themeList">
						<li theme="default"><div class="selected">蓝色</div></li>
						<li theme="green"><div>绿色</div></li>
						<li theme="purple"><div>紫色</div></li>
						<li theme="silver"><div>银色</div></li>
					</ul>
				</div>
			</div>

			<div id="leftside">
				<div id="sidebar_s">
					<div class="collapse">
						<div class="toggleCollapse">
							<div></div>
						</div>
					</div>
				</div>
				<div id="sidebar">
					<div class="toggleCollapse">
						<h2>
							主菜单
						</h2>
						<div>
							收缩
						</div>
					</div>
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
										<li><a href="OnLineList.do?wtlx=15" target="navTab" rel="OnLineList" width="420" height="320" title="在线提问">在线提问</a></li>
										<li><a href="userInfo.do" target="dialog" width="520" height="320" title="用户个人信息设置">个人设置</a></li>
										<li><a href="commentsList.do" target="dialog" rel="commentsList" width="700" height="450" title="审批意见维护">审批意见维护</a></li>
										<li><a href="alarm.do" target="dialog" width="480" height="200" title="超时提醒设置">超时提醒设置</a></li>
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
					<div class="navTab-panel tabsPageContent">
						<div id="desktop"  class="loadFileArea" loadfile="desktop.do">
						
						</div>
					
					</div>
				</div>
			</div>

			<div id="taskbar" style="left: 0px; display: none;">
				<div class="taskbarContent">
					<ul></ul>
				</div>
				<div class="taskbarLeft taskbarLeftDisabled" style="display: none;">
					taskbarLeft
				</div>
				<div class="taskbarRight" style="display: none;">
					taskbarRight
				</div>
			</div>
			<div id="splitBar"></div>
			<div id="splitBarProxy"></div>
		</div>

		<div id="footer">
			研发单位：天津市网天信息技术有限公司 &nbsp;&nbsp;技术服务电话：022-83946861转8080
		</div>

		<!--拖动效果-->
		<div class="resizable"></div>
		<!--阴影-->
		<div class="shadow" style="width: 508px; top: 148px; left: 296px;">
			<div class="shadow_h">
				<div class="shadow_h_l"></div>
				<div class="shadow_h_r"></div>
				<div class="shadow_h_c"></div>
			</div>
			<div class="shadow_c">
				<div class="shadow_c_l" style="height: 296px;"></div>
				<div class="shadow_c_r" style="height: 296px;"></div>
				<div class="shadow_c_c" style="height: 296px;"></div>
			</div>
			<div class="shadow_f">
				<div class="shadow_f_l"></div>
				<div class="shadow_f_r"></div>
				<div class="shadow_f_c"></div>
			</div>
		</div>
		<!--遮盖屏幕-->
		<div id="alertBackground" class="alertBackground"></div>
		<div id="dialogBackground" class="dialogBackground"></div>

		<div id='background' class='background'></div>
		<div id='progressBar' class='progressBar'>
			数据加载中，请稍等...
		</div>

		<!--报表条件-->
		<div id="mainReportCondition" style="display: none;"></div>

	</body>
</html>