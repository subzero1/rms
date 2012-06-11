/**
 * @author ZhangHuihua@msn.com
 */
(function($){
	var _lookup = {currentGroup:"",currentName:"",index:-1};
	var _util = {
		_lookupPrefix: function(){
			var indexStr = _lookup.index < 0 ? "" : "["+_lookup.index+"]";
			var preFix = _lookup.currentGroup + indexStr;
			return preFix ? preFix+"." : "";
		},
		lookupPk: function(key){
			return _util._lookupPrefix() + _lookup.currentName + "." + key;
		},
		lookupField: function(key){
			return _util._lookupPrefix() + "dwz_" + _lookup.currentName + "." + key;
		}
	};
	
	$.extend({
		bringBackSuggest: function(args, targetType){
			var $box = targetType == "dialog" ? $.pdialog.getCurrent() : navTab.getCurrentPanel();
			
			if($box == null)
				$box = navTab.getCurrentPanel();
			$box.find(":input").each(function(){
				var $input = $(this), inputName = $input.attr("id"), inputId=$input.attr("name"); // $input.attr("name"); modify at 2011-08-08  用name判断改为id
				for (var key in args) {
					var name = ("id" == key) ? _util.lookupPk(key) : _util.lookupField(key);
					
					if (name == inputName) {
						$input.val(args[key]);
						break;
					}
				}
			});
		},
		bringBack: function(args, targetType){
			$.bringBackSuggest(args, targetType);
			$.pdialog.closeCurrent();
		}
	});
	
	$.fn.extend({
		lookup: function(){
			return this.each(function(){
				var $this = $(this), options = {mask:true, 
					width:$this.attr('width')||820, height:$this.attr('height')||400,
					maxable:eval($this.attr("maxable") || "true"),
					resizable:eval($this.attr("resizable") || "true")
				};
				$this.click(function(event){
					_lookup = $.extend(_lookup, {
						currentGroup: $this.attr("lookupGroup") || "", 
						currentName:$this.attr("lookupName") || "",
						index: parseInt($this.attr("index")|| -1)
					});
					$.pdialog.open($this.attr("href"), "_blank", $this.attr("title") || $this.text(), options);
					event.preventDefault();
				});
			});
		},
		suggest: function(){
			var op = {suggest$:"#suggest", suggestShadow$: "#suggestShadow"};
			var selectedIndex = -1;
			return this.each(function(){
				var $input = $(this).attr('autocomplete', 'off').keydown(function(event){
					if (event.keyCode == DWZ.keyCode.ENTER) return false; //屏蔽回车提交
				});
				
				var suggestFields=$input.attr('suggestFields').split(",");
				var targetType = $input.attr('targetType')==null? "navTab":$input.attr('targetType');  //modify at 2012-01-31
				
				function _show(){
					if($input.val()=="") return;	//输入域的值为空时不显示 modify at 2011-12-16
					
					var offset = $input.offset();
					var iTop = offset.top+$input.height();	//this.offsetHeight; modify at 2011-12-16
					var iWidth = $input.width();
					var $suggest = $(op.suggest$);
					if ($suggest.size() == 0) $suggest = $('<div id="suggest"></div>').appendTo($('body'));
					
					$suggest.css({
						left:offset.left+'px',
						top:iTop+'px'
					}).show();
					
					_lookup = $.extend(_lookup, {
						currentGroup: $input.attr("lookupGroup") || "", 
						currentName:$input.attr("lookupName") || "",
						index: parseInt($input.attr("index")|| -1)
					});

					$.ajax({
						type:'POST', dataType:"json", url:$input.attr("suggestUrl"), cache: false,
						data:{inputValue:$input.val()},
						success: function(response){
							if (!response) return;
							var html = '';

							$.each(response, function(i){
								var liAttr = '', liLabel = '';
								
								for (var i=0; i<suggestFields.length; i++){
									var str = this[suggestFields[i]];
									if (str) {
										if (liLabel) liLabel += '-';
										liLabel += str;
										if (liAttr) liAttr += ',';
										liAttr += "'"+suggestFields[i]+"':'"+str+"'";
									}
								}
								html += '<li lookupId="'+this["id"]+'" lookupAttrs="'+liAttr+'">' + liLabel + '</li>';
							});
							$suggest.html('<ul style="width:'+iWidth+'px">'+html+'</ul>').find("li").hoverClass("selected").click(function(){
								_select($(this),targetType);
							});
						},
						error: function(){
							$suggest.html('');
						}
					});

					$(document).bind("click", _close);
					return false;
				}
				function _select($item,targetType){
					var jsonStr = "{id:'"+$item.attr('lookupId')+"'," + $item.attr('lookupAttrs') +"}";
					$.bringBackSuggest(DWZ.jsonEval(jsonStr),targetType);
				}
				function _close(){
					$(op.suggest$).html('').hide();
					selectedIndex = -1;
					$(document).unbind("click", _close);
				}
				
				//$input.focus(_show).click(false).keyup(function(event){ //modify at 2011-12-16
				$input.click(false).keyup(function(event){	
					var $items = $(op.suggest$).find("li");
					switch(event.keyCode){
						case DWZ.keyCode.ESC:
						case DWZ.keyCode.TAB:
						case DWZ.keyCode.SHIFT:
						case DWZ.keyCode.HOME:
						case DWZ.keyCode.END:
						case DWZ.keyCode.LEFT:
						case DWZ.keyCode.RIGHT:
							break;
						case DWZ.keyCode.ENTER:
							_close();
							break;
						case DWZ.keyCode.DOWN:
							if (selectedIndex >= $items.size()-1) selectedIndex = -1;
							else selectedIndex++;
							break;
						case DWZ.keyCode.UP:
							if (selectedIndex < 0) selectedIndex = $items.size()-1;
							else selectedIndex--;
							break;
						default:
							_show();
					}
					$items.removeClass("selected");
					if (selectedIndex>=0) {
						var $item = $items.eq(selectedIndex).addClass("selected");
						_select($item);
					}
				});
			});
		},
		
		itemDetail: function(){
			return this.each(function(){
				var $table = $(this).css("clear","both"), $tbody = $table.find("tbody");
				var itemDetail = $table.attr("itemDetail") || "", fields=[];

				$table.find("tr:first th[type]").each(function(){
					var $th = $(this);
					var field = {
						type: $th.attr("type") || "text",
						patternDate: $th.attr("format") || "yyyy-MM-dd",
						name: $th.attr("name") || "",
						id: $th.attr("id") || "",					//modify at 2011-08-10 表单主从结构中增加id属性
						value: $th.attr("value") || "",				//modify at 2012-06-11 输入域增加value属性
						hideName: $th.attr("hideName") || "",		//modify at 2011-08-08 表单主从结构中增加隐藏域
						hideValue: $th.attr("hideValue") || "",		//modify at 2011-08-08 表单主从结构中增加隐藏域的值
						hideId: $th.attr("hideId") || "",			
						size: $th.attr("size") || "",
						width: $th.width() || "",					//modify at 2011-09-21 增加宽度
						enumName: $th.attr("enumName") || "",
						enumUrl: $th.attr("enumUrl") || "",
						enumData: $th.attr("enumData") || "",		//modify at 2011-08-09 扩展选项url参数,解决中文转码问题
						lookupName: $th.attr("lookupName") || "",
						lookupUrl: $th.attr("lookupUrl") || "",
						suggestUrl: $th.attr("suggestUrl"),
						suggestFields: $th.attr("suggestFields"),
						fieldClass: $th.attr("fieldClass") || "",	//modify at 2011-09-09 字段样式
						comments: $th.text() || ""					//modify at 2011-09-19 列标题作为输入域的注释
					};
					fields.push(field);
				});
				
				$tbody.find("a.btnDel").click(function(){
					var $btnDel = $(this);
					function delDbData(){						
						$.ajax({
							type:'POST', dataType:"json", url:$btnDel.attr('href'), cache: false,
							success: function(){
								$btnDel.parents("tr:first").remove();
								initSuffix($tbody);
							},
							error: DWZ.ajaxError
						});
					}
					if($btnDel.hasClass("emptyInput")){	//modify at 2011-08-09 扩展emptyInput类,清空字段不执行后台删除,配合保存实现
						var $tr = $btnDel.parents("tr:first");
												
						$tr.find(":input").not($("[name$='.ID']")).each(function(){
							$(this).val("");
							$(this).removeClass();
						});
						
						$tr.hide();
						
					}else{					
						if ($btnDel.attr("title")){
							alertMsg.confirm($btnDel.attr("title"), {okCall: delDbData});
						} else {
							delDbData();
						}
					}
					
					return false;
				});

				if (!$table.hasClass('noadd')) {
					var $addBut = $('<div class="button"><div class="buttonContent"><button type="button">添加明细</button></div></div>').insertBefore($table).find("button");
					var $rowNum = $('<input type="text" name="dwz_rowNum" class="textInput" style="margin:2px;" value="1" size="2"/>').insertBefore($table);
					
					var trTm = "";
					$addBut.click(function(){
						if (! trTm) trTm = trHtml(fields, itemDetail);
						var rowNum = 1;
						try{rowNum = parseInt($rowNum.val())} catch(e){}
	
						for (var i=0; i<rowNum; i++){
							var $tr = $(trTm.replaceAll("#index#", $tbody.find(">tr").size()));
							$tr.appendTo($tbody).initUI().find("a.btnDel").click(function(){
								$(this).parents("tr:first").remove();
								initSuffix($tbody);
								return false;
							});
							$tr.find(">td").each(function(){$(this).initDetailWidth();});	//modify at 2011-09-21 初始化宽度
						}
					});
				}
			});
			
			/**
			 * 删除时重新初始化下标
			 */
			function initSuffix($tbody) {
				$tbody.find('>tr').each(function(i){
					$(':input', this).each(function(){
						var $input = $(this);
						var name = $input.attr('name').replaceAll('\[[0-9]+\]','['+i+']');
						$input.attr('name', name);
					});
				});
			}
			function tdHtml(field, itemDetail){
				var html = '', fieldName = field.name, fieldId = field.id;		//itemDetail+'[#index#].'+field.name; modify at 2011-08-08 不修改字段名,lookup增加id
				var lookupFieldName = fieldName, lookupFieldId=itemDetail+'[#index#].dwz_'+field.lookupName+'.'+field.name;
				
				switch(field.type){
					case 'del':
						html = '<a href="javascript:void(0)" class="btnDel">删除</a>';
						break;
					case 'lookup':
						var suggestFrag = '';
						if (field.suggestFields) {
							suggestFrag = 'autocomplete="off" lookupGroup="'+itemDetail+'" lookupName="'+field.lookupName+'" index="#index#" suggestUrl="'+field.suggestUrl+'" suggestFields="'+field.suggestFields+'"';
						}
						
						html = '<input type="text" id="' + lookupFieldId + '" name="'+lookupFieldName+'"'+suggestFrag+' value="'+field.value+'" size="'+field.size+'" style="width:0px;" class="' + field.fieldClass + '" comments="' + field.comments + '"/>'
							+ '<a class="btnLook" href="'+field.lookupUrl+'" lookupGroup="'+itemDetail+'" lookupName="'+field.lookupName+'" index="#index#" title="查找带回">查找带回</a>';
						break;
					case 'attach':
						html = '<input type="hidden" name="'+itemDetail+'[#index#].'+field.lookupName+'.id"/>'
							+ '<input type="text" id="' + fieldId + '" name="'+lookupFieldName+'" value="'+field.value+'" size="'+field.size+'" readonly="readonly" style="width:0px;" class="' + field.fieldClass + '" comments="' + field.comments + '"/>'
							+ '<a class="btnAttach" href="'+field.lookupUrl+'" lookupGroup="'+itemDetail+'" lookupName="'+field.lookupName+'" index="#index#" width="560" height="300" title="查找带回">查找带回</a>';
						break;
					case 'enum':
						var options = DWZ.jsonEval(field.enumData);
						var enum_data = $.extend({enumName:field.enumName, inputName:fieldName}, options); //modify at 2011-08-09 扩展选项url参数,解决中文转码问题
						
						$.ajax({
							type:"POST", dataType:"html", async: false,
							url:field.enumUrl, 
							data:enum_data,	//{enumName:field.enumName, inputName:fieldName}, 
							success:function(response){
								html = response;
							}
						});
						break;
					case 'date':
						html = '<input type="text" id="' + fieldId + '" name="'+fieldName+'" class="date ' + field.fieldClass + '" pattern="'+field.patternDate+'" value="'+field.value+'" size="'+field.size+'" style="width:0px;" comments="' + field.comments + '"/>'
							+'<a class="inputDateButton" href="javascript:void(0)">选择</a>';
						break;
					case 'datetime':
						html = '<input type="text" id="' + fieldId + '" name="'+fieldName+'" class="date ' + field.fieldClass + '" pattern="'+field.patternDatetime+'" value="'+field.value+'" size="'+field.size+'" style="width:0px;" comments="' + field.comments + '"/>'
							+'<a class="inputDateButton" href="javascript:void(0)">选择</a>';
						break;
					case 'textarea':
						html = '<textarea id="' + fieldId + '" name="'+fieldName+'" size="'+field.size+'" style="border-width:0;width:0px;overflow-y:visible;" class="' + field.fieldClass + '" comments="' + field.comments + '"> '+field.value+' </textarea>';
						break;	
					default:
						html = '<input type="text" id="' + fieldId + '" name="'+fieldName+'" value="'+field.value+'" size="'+field.size+'" style="width:0px;" class="' + field.fieldClass + '" comments="' + field.comments + '"/>';
						break;
				}
				
				if(field.hideName!="" || field.hideId!="" ) html = '<input type="hidden" id="' + field.hideId + '" name="' + field.hideName + '" value="'+field.hideValue+'"/>' + html;	//modify at 2011-08-08 表单主从结构中增加隐藏域
				return '<td width="'+field.width+'">'+html+'</td>';
			}
			function trHtml(fields, itemDetail){
				var html = '';
				$(fields).each(function(){
					html += tdHtml(this, itemDetail);
				});
				return "<tr>"+html+"</tr>";
			}
		},
		
		selectedTodo: function(options){
			
			function _getIds(selectedIds){
				var ids = "";
				navTab.getCurrentPanel().find("input:checked").filter("[name='"+selectedIds+"']").each(function(i){
					var val = $(this).val();
					ids += i==0 ? val : ","+val;
				});
				return ids;
			}
			return this.each(function(){
				var $this = $(this);
				var selectedIds = $this.attr("rel") || "ids";
				var postType = $this.attr("postType") || "map";

				$this.click(function(){
					var ids = _getIds(selectedIds);
					if (!ids) {
						alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
						return false;
					}
					function _doPost(){
						$.ajax({
							type:'POST', url:$this.attr('href'), dataType:'json', cache: false,
							data: function(){
								if (postType == 'map'){
									return $.map(ids.split(','), function(val, i) {
										return {name: selectedIds, value: val};
									})
								} else {
									var _data = {};
									_data[selectedIds] = ids;
									return _data;
								}
							}(),
							success: navTabAjaxDone,
							error: DWZ.ajaxError
						});
					}
					var title = $this.attr("title");
					if (title) {
						alertMsg.confirm(title, {okCall: _doPost});
					} else {
						_doPost();
					}
					return false;
				});
				
			});
		}
	});
	//modify at 2011-09-20 增加初始化明细输入域宽度
	$.fn.extend({
		initDetailWidth: function(){
			var $input = $(this).find(":input").not("[type=hidden]");
			var $width = $(this).attr("width") || $(this).width();
			$(this).css("width",$width);
			if($(this).find(".btnLook").size()>0){
				$input.css("width", $width-42+"px");
			}else if($input.hasClass("date")){
				$input.css("width", $width-26+"px");
			}else{
				$input.css("width", $width-6+"px");
			}	
		}
	});
	
})(jQuery);

