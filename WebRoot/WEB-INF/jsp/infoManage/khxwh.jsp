<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
	function addComments(){
		$("#mainbodyx").append("<tr><td>\
				<input type='hidden' name='Tf15_khxwh.ID' value='' />\
				<input type='text'  class='required' comments='考核项' name='Tf15_khxwh.KHX' style='width:100%'/></td>\
				<td><input type='text'  class='required' comments='描述' name='Tf15_khxwh.MS' style='width:100%'/></td>\
				<td><input type='text'  class='required' comments='最高分数' name='Tf15_khxwh.FZ' style='width:100%'/></td>\
				<td><input type='text'  class='required' comments='计算方式' name='Tf15_khxwh.JSFS' style='width:100%'/></td>\
				<td><select name='Tf15_khxwh.LB' style='width:100%;' class='required' comments='类别'>\
				<option value=''>---请选择类别---</option>\
					<option value='sj'>设计</option>\
					<option value='sg' >施工</option>\
					<option value='jl' >监理</option>\
				</select></td>\
				<td><a href='#' onclick='javascript:delComments(this);'  class='btnDel'><span>删除</span></a></td></tr>");
	}
	function delComments(obj){
		var $tr = $(obj).closest("tr");
		if($tr){
			var valid = false;
			$tr.find(":input").each(function(){
				if(this.name != "Tf15_khxwh.ID"){
					$(this).val("");
					$(this).removeClass('required');
				}else{
					if($(this).val()!="")
						valid = true;
				}
			}); 
			if(valid){
				$tr.hide(); 
				}
				
			else{
				$tr.remove();
				}
		}
	}
	function saveComments(){
		$("#form1").submit();
	}
	//点DEL DEL 隐 ROLLBACK现 JQUERY 选择器 实现???
</script>

<div class="page">
	<div class="pageContent">
		<form id="save.do" action="save.do" method="post"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, dialogAjaxDone);">
			<input type="hidden" name="tableInfomation"
				value="noFatherTable:com.rms.dataObjects.wxdw.Tf15_khxwh" />

			<div class="pageFormContent" layouth="58">
				<table class="table" width="100%">
					<thead>
						<tr>
							<th style='width: 60px;'>
								考核项
							</th>
							<th style='width: 60px;'>
								描述
							</th>
							<th style='width: 60px;'>
								最高分数
							</th>
							<th style='width: 60px;'>
								计算方式
							</th>
							<th style='width: 60px;'>
								类别;
							</th>
							<th style='width: 30px;'>
								&nbsp;
							</th>
						</tr>
					</thead>
					<tbody id="mainbodyx">
					
						<c:forEach var="tf15" items="${tf15List}">
							<tr>
								<td>
									<input type="hidden" name="Tf15_khxwh.ID" value="${tf15.id}">
									<input type='text' class='required' comments='考核项'
										name='Tf15_khxwh.KHX' style='width: 100%' value="${tf15.khx }" />
								</td>
								<td>
									<input type='text' class='required' comments='描述'
										name='Tf15_khxwh.MS' style='width: 100%' value="${tf15.ms }"/>
								</td>
								<td>
									<input type='text' class='required' comments='最高分数'
										name='Tf15_khxwh.FZ' style='width: 100%' value="${tf15.fz}"/>
								</td>
								<td>
									<input type='text' class='required' comments='计算方式'
										name='Tf15_khxwh.JSFS' style='width: 100%' value="${tf15.jsfs }"/>
								</td>
								<td>
									<select name='Tf15_khxwh.LB' style='width: 100%;' class='required' comments='类别'>
									
										<option value=''>
											-----请选择类别-----
										</option>
										<option value='sj' <c:if test="${tf15.lb=='sj' }">selected</c:if>>
											设计
										</option>
										<option value='sg' <c:if test="${tf15.lb=='sg' }">selected</c:if>>
											施工
										</option>
										<option value='jl' <c:if test="${tf15.lb=='jl' }">selected</c:if>>
											监理
										</option>
									</select>
								</td>
								<td>
									<a href="#" onclick="javascript:delComments(this);"
										class="btnDel"><span>删除</span>
									</a>
								</td>
							</tr>
						</c:forEach>
						 
					</tbody>
				</table>
			</div>
			<div class="formBar">
				<div class="button" style="float: left;">
					<div class="buttonContent">
						<button type="Button" onclick="javascript:addComments();">
							增 加
						</button>
					</div>
				</div>
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
									保 存
								</button>
							</div>
						</div>
					</li>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button type="Button" class="close">
									取 消
								</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>


