<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
/*
	function addComments(){
		$("#mainbody").append("<tr><td><input type='hidden' name='Ta22_user_idea.USER_ID' value='${user.id}'/>\
				<input type='hidden' name='Ta22_user_idea.ID' value='' />\
				<select name='Ta22_user_idea.CHECK_RESULT' style='width:98%;'>\
					<option value='4'>同意</option>\
					<option value='5' >修改后再报</option>\
					<option value='6' >不同意</option>\
				</select></td>\
				<td><input type='text'  class='required' comments='审批意见' name='Ta22_user_idea.CHECK_IDEA' style='width:98%'/></td>\
				<td><a href='#' onclick='javascript:delComments(this);'  class='btnDel'><span>删除</span></a></td></tr>");
	}
	function delComments(obj){
		var $tr = $(obj).closest("tr");
		if($tr){
			var valid = false;
			$tr.find(":input").each(function(){
				if(this.name != "Ta22_user_idea.ID"){
					$(this).val("");
				}else{
					if($(this).val()!="")
						valid = true;
				}
			});
			if(valid)
				$tr.hide();
			else
				$tr.remove();
		}
	}
	*/
	/*
	$.().ready(function(){
		$("#save").click(function(){
			$.post("deleteTf15.do",data,callback);
		});
	});
	
	$(function(){
		
	});
	*/ 
	function pfjg(obj){ 
		var $tr=$(obj).closest("tr");
		if($(obj).val()=="优"){	
			$tr.find("input[name='Tf16_xmkhdf.JGFZ']").val(1);
		}else if($(obj).val()=="良"){
	  	    $tr.find("input[name='Tf16_xmkhdf.JGFZ']").val(0.8);
		}else if($(obj).val()=="中"){
		    $tr.find("input[name='Tf16_xmkhdf.JGFZ']").val(0.6);
		}else if($(obj).val()=="差"){
		    $tr.find("input[name='Tf16_xmkhdf.JGFZ']").val(0.4);
		}
		//如果做出修改，更新评分时间和人员
		var curTime=$("#curTime").val();
		var curUser=$("#curUser").val(); 
		$tr.find("input[name='Tf16_xmkhdf.PFSJ']").val(curTime);
		$tr.find("input[name='Tf16_xmkhdf.PFRY']").val(curUser);
	}
	//点DEL DEL 隐 ROLLBACK现 JQUERY 选择器 实现???
	
</script>
<!-- 当前时间 -->
<input type="hidden" name="curTime"  id="curTime" value="<fmt:formatDate value="${curDate}" pattern="yyyy-MM-dd HH:mm"/>"/> 
<!-- 当前用户id -->
<input type="hidden" name="curUser" id="curUser" value="${user.id }"/>
<div class="page">
	<div class="pageContent">
		<form id="form1" action="save.do" method="post" class="pageForm required-validate"  onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="tableInfomation" value="noFatherTable:com.rms.dataObjects.wxdw.Tf16_xmkhdf"/>
		<div style="overflow-x:hidden" class="pageFormContent" layouth="58">
			<table class="table" width="100%">
				<thead>
					<tr>
						<th style='width:100px;'>考核项</th>
						<th style='width:240px;'>描述</th>
						<th style='width:30px;'>结果</th>
					</tr>
				</thead>
				<tbody id="mainbody"><c:forEach var="gc" items="${gc1}">
					<tr>
						<td>
							<input type="hidden" style="width:100%"  name="Tf16_xmkhdf.ID" value="" />
							<input type="text" style="width:100%"  class="required" name="Tf16_xmkhdf.KHX" value="${gc[0]}" />
						</td>
						<td>
							<input type="text" style="width:100%"  class="required" name="Tf16_xmkhdf.MS" value="${gc[1]}" />
						</td>
						<td> 
							<input type="hidden" name="Tf16_xmkhdf.ZDFZ" value="${gc[2] }"  /> 
							<select name="Tf16_xmkhdf.JGXX" style="width:100%" onchange="pfjg(this)" id="jgxx">
								<option value="" >---请评价---</option>
								<option value="优" >优</option>
								<option value="良" >良</option>
								<option value="中" >中</option> 
								<option value="差" >差</option>
							</select>
							<input type="text" name="Tf16_xmkhdf.JGFZ"  id="jgfz" /> 
							<input type="hidden" name="Tf16_xmkhdf.PROJECT_ID" value="${project_id}"/> 
							<input type="hidden" name="Tf16_xmkhdf.PFRY"  value="${user.id }" id="pfry"/> 
							<input type="hidden" name="Tf16_xmkhdf.PFSJ"  value="<fmt:formatDate value="${curDate}" pattern="yyyy-MM-dd HH:mm"/>"/> 
							<input type="hidden" name="Tf16_xmkhdf.JSFS"  value="${gc[3]}"/> 
							<input type="hidden" name="Tf16_xmkhdf.LB" value="${gc[4]}">
						</td>
					</tr>
				</c:forEach>
					<c:forEach var="gc" items="${gc2}">
					<tr>
						<td>
							<input type="hidden" style="width:100%"  name="Tf16_xmkhdf.ID" value="${gc.id }" />
							<input type="text" style="width:100%"  class="required" name="Tf16_xmkhdf.KHX" value="${gc.khx}" />
						</td>
						<td>
							<input type="text" style="width:100%"  class="required" name="Tf16_xmkhdf.MS" value="${gc.ms}" />
						</td>
						<td> 
							<input type="hidden" name="Tf16_xmkhdf.ZDFZ" value="${gc.zdfz}"   /> 
							<select name="Tf16_xmkhdf.JGXX" style="width:100%" onchange="pfjg(this)" >
								<option value="优" <c:if test="${gc.jgxx=='优'}">selected</c:if>>优</option>
								<option value="良" <c:if test="${gc.jgxx=='良'}">selected</c:if>>良</option>
								<option value="中" <c:if test="${gc.jgxx=='中'}">selected</c:if>>中</option> 
								<option value="差" <c:if test="${gc.jgxx=='差'}">selected</c:if>>差</option>
							</select>
							<input type="text" name="Tf16_xmkhdf.JGFZ"  /> 
							<input type="hidden" name="Tf16_xmkhdf.PROJECT_ID" value="${gc.project_id }"/> 
							<input type="hidden" name="Tf16_xmkhdf.PFRY"  value="${gc.pfry }" id="pfry"/> 
							<input type="hidden" name="Tf16_xmkhdf.PFSJ"  value="<fmt:formatDate value="${gc.pfsj}" pattern="yyyy-MM-dd HH:mm"/>"/> 
							<input type="hidden" name="Tf16_xmkhdf.JSFS"  value="${gc.jsfs }"/> 
							<input type="hidden" name="Tf16_xmkhdf.LB" value="${gc.lb}"/>
						</td>
					</tr>
				</c:forEach>
				
				</tbody>
			</table>
		</div>
		<div class="formBar">
			<div class="button" style="float:left;"></div>
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" id="save">保 存</button></div></div></li>	
				<li><div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div></li>
			</ul>
		</div>
	</form>
	</div>
</div>


