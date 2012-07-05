/*
* Cascade
* version: Alpha 0.01 (07/04/2012)
*
* usage as:
*		$("#parentSelect").cascade({
*			childSelect:$("#childSelect"),
*			tableName:'Ta03_user',
*			conditionColumn:'dept_id',
*			valueForOption:'id',
			orderBy:'name asc', //default:id asc
*			extendColumns:{
*							login_id:'login_id',
*							passwd:'passwd',
*							title:'name'
*			},
*			showForOption:{
*							pattern:'name(login_id)',
*							name:'name',
*							login_id:'login_id'
*			}
*		});
*     
* Copyright 2012 ZhangYi[zhang_yi0627@hotmail.com]
*/
(function ($) {
	var defaults = {orderBy:'id desc',
					extendColumns:{},
					val:null
	};
	$.fn.cascade = function (options) {
		options = $.extend(defaults,options);
		var extendColumns = options.extendColumns;
		var showForOption = options.showForOption;
		var data = {tableName:options.tableName,
					conditionColumn:options.conditionColumn,
					valueForOption:options.valueForOption,
					orderBy:options.orderBy
		};
		$.each(extendColumns ,function(i){
                data["extendColumn_"+i] = extendColumns[i];
        });
        $.each(showForOption ,function(i){
        		data["showForOption_"+i] = showForOption[i];
        });
		return this.each(function () {
			var $this = $(this);
			$this.change(function(){
				data["conditionValue"] = options.val == null ? $this.val() : options.val;
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

