<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="NetSkyTagLibs" prefix="netsky"%>
<jsp:useBean id="now" class="java.util.Date" />

<script type="text/javascript">
			function fbgg() {
				document.getElementById("Tf09_wxgg.ZT").value='1';
				$("#wxggForm").submit();
			}		    	    
		    function ssdq(obj)
		    {
		        $.ajax({
		        	url:'wxdw/getWxdwAjax.do',
		        	type:'post',
		        	data:'lb='+$(obj).val()+'&ggLimit=${ggLimit}',
		        	success:function(msg){
		        		$("#boxdiv").empty();
		        		$("#boxdiv").append(msg);
		        		set_c('mbdw','Tf09_wxgg.MBDW');
		        	}
		        });
		    }
		
		    
			function set_c(name,name_v){
				var b=document.getElementsByName(name);
				var b_v="";
				var n=0;
				for(var i=0;i<b.length;i++)
				{
					if(b[i].checked)
					{
						if(n==1)
						{
							b_v=b_v+","+b[i].value;
						}
						else
						{
							b_v=b[i].value;
							n=1;
						}
					}
				}
				document.getElementById(name_v).value=b_v;
			}
						
			//全选
			function allbox(sgqy_id,box_id,obj){
				var b=document.getElementsByName(sgqy_id);
				var b_v="";
				var n=0;
				
				if(obj.value=="全选"){
					for(var i=0;i<b.length;i++)
					{
						if(b[i].checked){
							
						}else{
							b[i].checked=true;
						}
					}
					obj.value="全不选";
				}else{
					for(var i=0;i<b.length;i++)
					{
						if(b[i].checked){
							b[i].checked=false;
						}
					}
					obj.value="全选";
				}
				
				set_c('mbdw','Tf09_wxgg.MBDW');
			}
			
		function spanclick(obj){
			if ($(obj).prev("input").attr("checked") == "checked"){
				$(obj).prev("input").removeAttr("checked");
				}else {
					$(obj).prev("input").attr("checked",true);
				}
				set_c('mbdw','Tf09_wxgg.MBDW');
		}
		function saveWxgg (){
			$("#wxggForm").submit();
		}
		
		function save_jlfk(){
			if ($.trim($("#jlfk_yj").val())=="") {
				alertMsg.error("请填写反馈内容！");
			} else {
				$.ajax({
				url:'jlfk.do',
				type:'post',
				data:$("#form1",navTab.getCurrentPanel()).serializeArray(),
				success:function(msg){
					alertMsg.correct("反馈成功！");
					$("#ajax_jlfk").append(msg);
					$("#jlfk_yj").empty();
				}
			});
			}
		}
		</script>
<div class="page">

	<!-- 表单头 -->
	<div class="pageHeader">
		<div class="searchBar">
			<!-- 表单名称 -->
			<h1>
				外协公告
			</h1>

		</div>
	</div>
	<c:choose>
			<c:when
				test="${wxgg.zt != '1'}">
	<!-- 主操作按钮 -->
	<div class="panelBar">
		<ul class="toolBar">
			<c:if test="${ggLimit =='' || gglimit== null}">

				<c:if test="${wxgg.zt!='1'|| (wxgg.zt == '1' && modify == 'yes')}">
					<c:if test="${not empty wxgg}">
						<li>
						<a class="icon" href="dispath.do?url=uploadFile.jsp&project_id=${wxgg.id}&doc_id=${wxgg.id}&module_id=3007&slave_type=8" target="dialog" rel="fjsc" width="460" height="300" title="附件上传"><span>附
								件</span>
						</a>
					</li>
					<li class="line">
						line
					</li>
					</c:if>
					<li>
						<a class="save" href="javascript:saveWxgg();"><span>保 存</span>
						</a>
					</li>
					<li class="line">
						line
					</li>
				</c:if>
				<c:if test="${wxgg.zt=='0'}">
					<li>
						<a class="save" href="javascript:fbgg();"><span>发 布</span>
						</a>
					</li>
					<li class="line">
						line
					</li>
				</c:if>
			</c:if>
		</ul>
	</div>

	<div class="pageContent" layouth="48">

				<form id="wxggForm" method="post" action="save.do"
					class="pageForm required-validate"
					onsubmit="return validateCallback(this,navTabAjaxDone);">

					<input type="hidden" name="tableInfomation"
						value="noFatherTable:com.rms.dataObjects.wxdw.Tf09_wxgg" />
					<input type="hidden" name="Tf09_wxgg.ID" id="Tf09_wxgg.ID"
						value="${wxgg.id}" />
					<input type="hidden" name="perproty" value="id,Tf09_wxgg,id" />
					<input type="hidden" name="_callbackType" value="forward" />
					<input type="hidden" name="_message" value="操作" />
					<input type="hidden" name="_forwardUrl" value="wxdw/wxgg.do" />
					<input type="hidden" name="_navTabId" value="wxggList" />
					<input type="hidden" name="Tf09_wxgg.ZT" id="Tf09_wxgg.ZT"
						value="0" />
					<div class="pageFormContent">
						<p>
							<label>
								发&nbsp;布&nbsp;人：
							</label>
							<input type="text" ${ggLimit } style="width: 308px"
								name="Tf09_wxgg.FBR_MC" id="Tf09_wxgg.FBR_MC"
								value="<c:out value="${wxgg.fbr_mc}" default="${user.name}"/>"
								readonly />
							<input type="hidden" name="Tf09_wxgg.FBR_ID"
								id="Tf09_wxgg.FBR_ID" value="${user.id }" />
						</p>
						<p>
							<label>
								时&nbsp;&nbsp;&nbsp;&nbsp;间：
							</label>
							<input type="text" ${ggLimit } name="Tf09_wxgg.FBSJ"
								id="Tf09_wxgg.FBSJ"
								value="<fmt:formatDate value="${empty wxgg.fbsj ? now : wxgg.fbsj}" pattern="yyyy-MM-dd"/>"
								class="date" pattern="yyyy-MM-dd" />
						</p>
						<div style="height: 0px;"></div>

						<p>
							<label>
								传达范围：
							</label>
							<input type="radio" ${ggLimit } name="Tf09_wxgg.CDFW" value="总负责人" <c:if test="${wxgg.cdfw=='总负责人'||(wxgg.cdfw!='项目经理'&&wxgg.cdfw!='资料管理员'&&wxgg.cdfw!='全体人员')}">checked="checked"</c:if> />
							总负责人<input type="radio" ${ggLimit } name="Tf09_wxgg.CDFW" value="项目经理" <c:if test="${wxgg.cdfw=='项目经理'}">checked</c:if> />
							项目经理<input type="radio" ${ggLimit } name="Tf09_wxgg.CDFW" value="资料管理员" <c:if test="${wxgg.cdfw=='资料管理员'}">checked</c:if> />
							资料管理员<input type="radio" ${ggLimit } name="Tf09_wxgg.CDFW" value="全体人员" <c:if test="${wxgg.cdfw=='全体人员'}">checked</c:if> />
							全体人员&nbsp;&nbsp;
						</p>
						<p>
							<label>
								紧急程度：
							</label>
							<input type="radio" ${ggLimit } name="Tf09_wxgg.JJCD" value="高"
								<c:if test="${wxgg.jjcd=='高'||(wxgg.jjcd!='中'&&wxgg.jjcd!='低')}">checked</c:if> />
							高<input type="radio" ${ggLimit } name="Tf09_wxgg.JJCD" value="中"
								<c:if test="${wxgg.jjcd=='中'}" >checked</c:if> />
							中<input type="radio" ${ggLimit } name="Tf09_wxgg.JJCD" value="低"
								<c:if test="${wxgg.jjcd=='低'}" >checked</c:if> />
							低
						</p>
						<div style="height: 0px;"></div>
						<p>
							<label>
								信息主题：
							</label>
							<input style="width: 540px;" type="text"
								${ggLimit }  name="Tf09_wxgg.GGZT" id="Tf09_wxgg.GGZT"
								class="required" value="${wxgg.ggzt}" />
						</p>
						<div style="height: 0px;"></div>
						<p>
							<label>
								信息内容：
							</label>
							<textarea style="width: 540px; height: 100px;" type="text"
								name="Tf09_wxgg.GGNR" id="Tf09_wxgg.GGNR" class="required"${ggLimit }>${wxgg.ggnr}</textarea>
						</p>
						<div style="height: 0px;"></div>
						<p>
							<label>
								外协单位类别：
							</label>
							<select name="Tf09_wxgg.WXDW_LB" id="Tf09_wxgg.WXDW_LB"
								onchange="ssdq(this);" style="width: 300px;"${ggLimit} >
								<option value="设计"
									<c:if test="${'设计'==lb }"> selected="selected" </c:if>>
									设计单位
								</option>
								<option value="施工"
									<c:if test="${'施工'==lb }"> selected="selected" </c:if>>
									施工单位
								</option>
								<option value="监理"
									<c:if test="${'监理'==lb }"> selected="selected" </c:if>>
									监理单位
								</option>
								<option value="审计"
									<c:if test="${'审计'==lb }"> selected="selected" </c:if>>
									审计单位
								</option>
							</select>
						</p>
						<p>
							<label></label>
							<input type="button" ${ggLimit }  id="" name=""
								onclick="javascript:allbox('mbdw','Tf09_wxgg.MBDW',this)"
								value="全选" />
						</p>
						<div style="height: 0px;"></div>
						<div>
							<div style="height: 0px;"></div>
							<input type="hidden" name="Tf09_wxgg.MBDW" id="Tf09_wxgg.MBDW"
								value="${wxgg.mbdw }"/>
							<c:if test="${ggLimit ==''||ggLimit == null}">
								<c:set var="offset" value="0" />
									<label>
										外协单位名称：
									</label>
									<div id="boxdiv">
								<div style="width: 100%; display: inline-block; margin-left: 100px;">
									<c:forEach var="list_mbdw" items="${list_mbdw}">
										<c:set var="offset" value="${offset+1}" />

										<p
											style="width: 220px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;"
											title="${list_mbdw.mc }">
											<input ${ggLimit }  type="checkbox" id="mbdw" name="mbdw"
												value="${list_mbdw.mc }"
												onClick="set_c('mbdw','Tf09_wxgg.MBDW')"
												<c:if test="${list_mbdw.mc==mbdw[list_mbdw.mc] }">checked="checked"</c:if> />
											&nbsp;
											<span onclick="javascript:spanclick(this);" style="cursor: pointer">${list_mbdw.mc}</span>
										</p>
										<c:if test="${offset % 3 == 0}">
								</div>
								<div
									style="width: 100%; display: inline-block; margin-left: 100px;">
							</c:if>
							</c:forEach>
						</div>
						</div>
						</c:if>
					</div>
					<div style="width: 100%; height: 0px;" />
						<p>
							<label>
								已读单位：
							</label>
							<input style="width: 540px;" type="text"
								${ggLimit }  name="Tf09_wxgg.CYDW" id="Tf09_wxgg.CYDW"
								value="${wxgg.cydw}" readonly />
						</p>
				</form>

				<div style="text-align: left; color: blue;">
					<h3>
						&nbsp;&nbsp;附件信息
					</h3>
				</div>
				<div class="divider" style="height: 1px;"></div>
				<table class="table" width="100%">
					<tbody>
						<c:forEach var="fj" items="${list_fj}">
							<tr>
								<td>
									<a href="download.do?slave_id=${fj.id}">${fj.file_name}</a>
									<c:if test="${role_id==20312}">
										<a href="delfile.do?slave_id=${fj.id}&callbackType=forward" target="ajaxTodo" title="确认删除吗？"><img
												src="Images/icon10.gif" alt="删除" />
										</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			
	</div>
	</c:when>
			<c:otherwise>
			
			
			
			
			
			
			<!-- 主操作按钮 -->
	<div class="panelBar">
		<ul class="toolBar">
			<c:if test="${role_id==20313}">
					<li>
						<a class="icon" href="dispath.do?url=uploadFile.jsp&project_id=${wxgg.id}&doc_id=${wxgg.id}&module_id=3007&slave_type=8" target="dialog" rel="fjsc" width="460" height="300" title="附件上传"><span>附
								件</span>
						</a>
					</li>
					<li class="line">
						line
					</li>
					<li>
						<a class="save" href="javascript:save_jlfk();"><span>反 馈</span>
						</a>
					</li>
					<li class="line">
						line
					</li>
			</c:if>
		</ul>
	</div>

	<div class="pageContent" layouth="48">

					<div class="pageFormContent">
						
						
						<h2 class="online-title">${wxgg.ggzt}</h2>
						<h4 class="online-msg">发表人：${wxgg.fbr_mc}&nbsp;&nbsp; 发表时间：<fmt:formatDate value="${wxgg.fbsj}" pattern="yyyy-MM-dd"/>&nbsp;&nbsp;传达范围：${wxgg.cdfw}&nbsp;&nbsp;紧急程度：${wxgg.jjcd}</h4>
						
						<div class="online-content">${wxgg.ggnr}</div>
						<h2>附 件：</h2>
							<c:forEach var="fj" items="${list_fj}">
								<div><a href="download.do?slave_id=${fj.id}">${fj.file_name}</a>&nbsp;&nbsp;${fj.user_name }&nbsp;&nbsp;<fmt:formatDate value="${fj.ftp_date }" pattern="yyyy-MM-dd"/>&nbsp;
									<c:if test="${user_name==wxgg.fbr_mc}"><a href="delfile.do?slave_id=${fj.id}&callbackType=forward" target="ajaxTodo" title="确认删除吗？"><img src="Images/icon10.gif" alt="删除"/></a></c:if>
								</div>
							</c:forEach>
						<div style="background-color:lightblue;height:30px;line-height:30px;">外协单位[类别：${wxgg.wxdw_lb}单位]<font color="red" class="">有标记符号的单位已读过这篇公告</font></div>
						<div style="height: 0px;"></div>
						<div>
							<div style="height: 0px;"></div>
							<c:if test="${ggLimit =='disabled'}">
						<c:set var="offset" value="0"/>
		  				<div style="width:100%;display:inline-block;">
		  				<c:forEach var="list_mbdw" items="${mbdw_list}">
		  				<c:set var="offset" value="${offset+1}"/>
		  				
						<p style="width:220px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;" title="${list_mbdw }"><span class="wxdwmc">${list_mbdw }</span><c:if test="${cydw[list_mbdw]==list_mbdw}"><img src="Images/online_ok.gif" title="已读"/></c:if> </p>
						<c:if test="${offset % 3 == 0}"></div><div style="width:100%;display:inline-block;"></c:if>
						</c:forEach>
						</div>
					</c:if>
					</div>
					
			
	</div>
	<form id="form1">
<table width="98%" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;" class="hf-table">
			<caption><a href="#post" class="f-right">[我要反馈]</a></caption>
				<tr>
					<td>
		            	<input type="hidden" id="project_id" name="project_id" value="${wxgg.id}"/>
		            	<input type="hidden" id="module_id" name="module_id" value="3007"/>
		            	<input type="hidden" id="user_id" name="user_id" value="${user.id }"/>
		            	<input type="hidden" id="doc_id" name="document_id" value="${wxgg.id}"/>
						<table id="ajax_jlfk" width="97%" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;">
							<c:forEach var="jlfk" items="${list_jlfk}">
								<tr>
									<td class="hf-msg"> ${jlfk.name}&nbsp;<c:if test="${not empty jlfk.wxdw}">[${jlfk.wxdw}]&nbsp;</c:if> <fmt:formatDate value="${jlfk.time}" pattern="yyyy-MM-dd HH:mm"/></td>
								</tr>
								<tr>
									<td>${jlfk.yj}</td>
								</tr>
							</c:forEach>
						</table>	  
					</td>
				</tr>
				<tr>
					<th>
					</th>
				</tr>
		</table>
		<c:choose>
		  <c:when test="${role_id==20313}">	
			<table id="bd" name="bd" width="98%" border="0" cellspacing="0" cellpadding="0" style="border-collapse:collapse;" class="post-table">
				<caption>发表反馈意见<a name="post" href=""/></caption>
				<tr>
					<th colspan="2">
						<textarea class="td-textarea" style="height:80px;width:100%" type="text" name="yj" id="jlfk_yj"></textarea>
					</th>
				</tr>
				<tr>
					<td>
					</td>				
				</tr>
			</table>	
		</c:when>
		</c:choose>	
		</form>		
			</c:otherwise>
		</c:choose>
</div>