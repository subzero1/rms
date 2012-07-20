<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	/*
	 *点击搜索框的时候将‘录入后按回车进行搜索’去掉。
	 */
	$("#_keyWord").click(function(){
		var keyWord = $(this).val();
		if(keyWord != '' && keyWord != null && keyWord.indexOf('回车') != -1){
			$(this).val('');
		}
	})
	
	/*
	 *回车按关键字进行搜索。
	 */
	$("#_keyWord").keypress(function(e){
	    
	    var counter = 0;
		var key = e.keyCode; //获取回车键
		
        if(key.toString() == "13"){
            var s_var = $(this).val(); //获取搜索关键字
        	$("#s_group").children().each(function(k,v){ //k,v 隐含参数 k:当前OPTION位置序号 v:当前OPTION对象。 
        		var gcmc = $(v).html();                  //取OPTION 中的show,$(v).val取OPTION中的value。
        		$(v).removeAttr('selected');             //将选中的OPTION取消选中。
        		if(gcmc.indexOf(s_var) != -1 && k != 0){
        			$(v).attr('selected','true');        //将符合条件的OPTION选中。
        			counter ++;
        		}
        	});
        	if(counter == 0){
        		alertMsg.warn('未找到符合条件的工程');
        	}
            return false;
        }
	})
	
</script>
<div class="page">
	<div class="pageContent">
		<form action="form/saveXzgcForDblx.do" method="post" class="pageForm"  onSubmit="return selectSubmit(this,dialogAjaxDone,'t_group');"> 
			<input type="hidden" name="xm_id" value="${param.xm_id}">
			<div class="pageFormContent" layoutH="56">
				<table>
					<tr>
						<td>
							<select id="s_group" name="s_group" multiple=1 type=select-multiple  ondblclick="javascript:moveAct('s_group','t_group');" style="width:350px;height:400px;">
							<option style="background-color:#ccc;" disabled>--------------------未选工程--------------------</option>
							<c:forEach var="obj" items="${unselect_groups}">
								<option title="${obj.gcmc}"  value="${obj.id}">${obj.gcmc}</option>
							</c:forEach>
							</select>
						</td>
						<td style="width:30px;text-align:center;">
							<input type="button" value="&gt;" onclick="javascript:moveAct('s_group','t_group');"><br/>
							<input type="button" value="&lt;" onclick="javascript:moveAct('t_group','s_group');">
						</td>
						<td>
							<select id="t_group" name="t_group" multiple=1 type=select-multiple   ondblclick="javascript:moveAct('t_group','s_group');" style="width:350px;height:400px;">
							<option style="background-color:#ccc;" disabled >--------------------已选工程--------------------</option>
							<c:forEach var="obj" items="${select_groups}">
								<option title="${obj.gcmc}"  value="${obj.id}">${obj.gcmc}</option>
							</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2"><input type="text" id="_keyWord" style="width:200" value="录入后按回车进行搜索" />
						<input type="text" name="" style="display:none;"/>
						</td>
					</tr>
				</table>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">确 定</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="Button" class="close">取 消</button></div></div>
					</li>
				</ul>
			</div>
		</form>  
	</div>
</div>
