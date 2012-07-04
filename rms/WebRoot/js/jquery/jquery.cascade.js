/*
* Cascade
* version: Alpha 0.01 (07/04/2012)
*
* usage as:
*		$("#parentSelect").cascade({
*			childSelect:$("#childSelect"),
*			tableName:'Ta03_user',
*			conditionColumn:'dept_id',
*			valueColumn:'id',
			orderBy:'name asc', //default:id asc
*			extendColumns:{
*							login_id:'login_id',
*							passwd:'passwd',
*							title:'name'
*			},
*			showColumn:{
*							pattern:'name(login_id)',
*							name:'name',
*							login_id:'login_id'
*			}
*		});
*     
* Copyright 2012 ZhangYi[zhang_yi0627@hotmail.com]
*/
(function ($) {
	var defaults = {orderBy:'id desc'
	};
	$.fn.cascade = function (options) {
		options = $.extend(defaults,options);
		var extendColumns = options.extendColumns;
		var showColumn = options.showColumn;
		var data = {tableName:options.tableName,
					conditionColumn:options.conditionColumn,
					valueColumn:options.valueColumn,
					orderBy:options.orderBy
		};
		$.each(extendColumns ,function(i){
                data["extendColumn_"+i] = extendColumns[i];
        });
        $.each(showColumn ,function(i){
        		data["showColumn_"+i] = showColumn[i];
        });
		return this.each(function () {
			var $this = $(this);
			$this.change(function(){
				data["conditionValue"] = $this.val();
				$.ajax({
					url:'cascade.do',
					data:data,
					type:'post',
					success:function(msg){
						options.childSelect.empty();
						options.childSelect.append(msg);
					}
				});
			});
		});
	};
})(jQuery);

