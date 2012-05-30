<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript">
function click_check(obj,i){
    var chk=false;
    var value_t;
    if (obj.checked){
	  chk = true;
	  value_t=1;
	}
    else{
    	 value_t=0;   		
    }
    
    document.getElementsByName("Ta27_user_remind.MOBILE_FLAG")[i].value=value_t;
}

function click_check1(obj,i){
    var chk=false;
    var value_t;
    if (obj.checked){
	  chk = true;
	  value_t=1;
	}
    else{
    	 value_t=0;   		
    }
    
    document.getElementsByName("Ta27_user_remind.MESSAGE_FLAG")[i].value=value_t;
}

</script>
<form method="post" action="alarm.do?save=yes" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
		<input type="hidden" name="tableInfomation" value="noFatherTable:com.netsky.base.dataObjects.Ta27_user_remind"/>
		<input type="hidden" name="tableInfomation" value="noFatherTable:com.netsky.base.dataObjects.Ta23_personalization"/>
		<input type="hidden" name="dispatchStr" value="/alarm.do"/>
		<input type="hidden" name="validate" value=""/>
<div style="width:450px;">
<b class="b1"></b><b class="b2"></b><b class="b3"></b><b class="b4"></b>
 <div class="contentc scroll-body" style="100%height:150px;width:442px;">
	<table width="440" border="0" cellspacing="0" cellpadding="0" class="data-table2" style="border-collapse:collapse;">
		<tr>
		    <td width="100%" colspan="2" class="t-left"></td>
		</tr>
		<tr>
			<td width="100%" colspan="2" class="t-left">1、预警、超时文档提醒：&nbsp;&nbsp;
							<input type="checkbox" name="mobile_flag1" id="mobile_flag1" onclick="javascript:click_check(this,0);" <c:if test="${mobile_flag1==1}">checked</c:if> value="1" />&nbsp;手机短信&nbsp;&nbsp;&nbsp;
							<input type="checkbox" name="message_flag1" id="message_flag1" onclick="javascript:click_check1(this,0);"<c:if test="${message_flag1==1}">checked</c:if> value="1" />&nbsp;系统短消息
							<input type="hidden" name="Ta27_user_remind.ID" value=""/>
							<input type="hidden" name="Ta27_user_remind.USER_ID" value="${user.id }"/>
							<input type="hidden" name="Ta27_user_remind.REMIND_TYPE" value="1"/>
							<input type="hidden" name="Ta27_user_remind.MOBILE_FLAG" value="${mobile_flag1 }"/>
							<input type="hidden" name="Ta27_user_remind.MESSAGE_FLAG" value="${message_flag1 }"/>
							
			</td>
		</tr>
		<tr>
			<td width="100%" colspan="2" class="t-left">2、待办、回复文档提醒：&nbsp;&nbsp;
							<input type="checkbox" name="mobile_flag2" id="mobile_flag2" onclick="javascript:click_check(this,1);" <c:if test="${mobile_flag2==1}">checked</c:if> value="1" />&nbsp;手机短信&nbsp;&nbsp;&nbsp;
							<input type="hidden" name="Ta27_user_remind.ID" value=""/>
							<input type="hidden" name="Ta27_user_remind.USER_ID" value="${user.id }"/>
							<input type="hidden" name="Ta27_user_remind.REMIND_TYPE" value="2"/>
							<input type="hidden" name="Ta27_user_remind.MOBILE_FLAG" value="${mobile_flag2 }"/>
							<input type="hidden" name="Ta27_user_remind.MESSAGE_FLAG" value="${message_flag2 }"/>
							
			</td>
		</tr>
		<tr>
			<td width="100%" colspan="2" class="t-left">
				<ul>
					<c:forEach var="alarm_list" items="${alarm_list}">
						<li style="display:inline;width:105px;float:left;font-size:12px;">
							<input type="checkbox" name="po_id" <c:if test="${fn:contains(obj_new,alarm_list.id)}">checked</c:if> value="${alarm_list.id}"/>${alarm_list.name}
		 				</li>
					</c:forEach>
				</ul>
			</td>
		</tr>
		<tr>
			<td width="100%" colspan="2" class="t-left" height="30px;">3、在线提问、权限申请处理完成提醒：&nbsp;&nbsp;
							<input type="checkbox" name="mobile_flag3" id="mobile_flag3" onclick="javascript:click_check(this,2);" <c:if test="${mobile_flag3==1}">checked</c:if> value="1" />&nbsp;手机短信&nbsp;&nbsp;&nbsp;
							<input type="hidden" name="Ta27_user_remind.ID" value=""/>
							<input type="hidden" name="Ta27_user_remind.USER_ID" value="${user.id }"/>
							<input type="hidden" name="Ta27_user_remind.REMIND_TYPE" value="3"/>
							<input type="hidden" name="Ta27_user_remind.MOBILE_FLAG" value="${mobile_flag3 }"/>
							<input type="hidden" name="Ta27_user_remind.MESSAGE_FLAG" value="${message_flag3 }"/>
			</td>
		</tr>
		<tr>
			<td width="100%" colspan="2" class="t-left"></td>
		</tr>
	</table>
</div>
<b class="b4"></b><b class="b3"></b><b class="b2"></b><b class="b1"></b>
<br/>
<div id='button-div'>
	<input class='button-alertl f-right' type='submit' value='设置完成,提交'/>
	<input class="button-alert f-left" type="button" value="取 消" onclick="javascript:parent.closeCustomWin();"/>
</div>
</div>
</form>
