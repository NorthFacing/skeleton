// contextPath 全局变量
var contextPath = window.location.pathname.split("/")[1];
var path = "/" + contextPath;

var JZ = {
	alert : function(s, callback, height) {
		height = height || 40;
		var alertDialog = dialog({
			title : '温馨提示',
			content : s,
			width : 350,
			height : height,
			fixed : true,
			okValue : "确定",
			ok : function() {
				if (callback) {
					callback();
				} else {
					alertDialog.close().remove();
				}
			}
		});
		alertDialog.showModal();
		var alertTipTimer = setTimeout(function() {
			alertDialog.close().remove();
			alertTipTimer = null;
		}, 3000);
	},
	confirm : function(s, callback, height) {
		height = height || 40;
		var alertDialog = dialog({
			title : '温馨提示',
			content : s,
			width : 350,
			height : height,
			fixed : true,
			okValue : "确定",
			ok : function() {
				if (callback) {
					callback();
				} else {
					alertDialog.close().remove();
				}
			},
			cancelValue : "取消",
			cancel : function() {
				alertDialog.close().remove();
			}
		});
		alertDialog.showModal();
	},

	/**
	 * prefix：前缀； s：自定义提示语
	 */
	checkSelectOne : function(prefix, s) {
		var id = $("#" + prefix + "List").jqGrid('getGridParam', 'selrow');
		/** 判断是否选中了一条数据 */
		if (id == null) {
			JZ.alert(s == null ? "请选择一条数据后再进行操作！" : s);
			return false;
		}
		/** 如果不为空，进行属性字段赋值 */
		var data = $("#" + prefix + "List").jqGrid('getRowData', id);
		for ( var param in data) {
			$("#" + prefix + "_" + param).val(data[param]);
		}
		return true;
	},
	/** 清空form表单中的数据 */
	clearData : function(prefix) {
		var colNames = $("#" + prefix + "List").jqGrid('getColProp')
		for ( var param in colNames) {
			alert(colNames[param]);
			$("#" + prefix + "_" + colNames[param]).val(null);
		}
	}

}