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
			val: ,//default:null(if null then $(this).val())
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
	$.fn.cascade = function (options) {
		var defaults = {orderBy:'',
						extendColumns:{},
						val:null,
						whereClause:''
		};
		var o = $.extend(defaults,options);
		var extendColumns = o.extendColumns;
		var showForOption = o.showForOption;
		var data = {tableName:o.tableName,
					conditionColumn:o.conditionColumn,
					valueForOption:o.valueForOption,
					orderBy:o.orderBy,
					whereClause:o.whereClause
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
				data["conditionValue"] = o.val == null ? $this.val() : o.val;
				$.ajax({
					url:'cascade.do',
					data:data,
					type:'post',
					success:function(msg){
						o.childSelect.empty();
						o.childSelect.append(msg);
					}
				});
			});
		});
	};
})(jQuery);

