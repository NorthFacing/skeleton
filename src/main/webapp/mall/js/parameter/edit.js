/** edit */
function addModal() {
	getCategory();
	$('#editTitle').html('新增参数');
	JZ.clearForAdd('parameter');
	$('#modal-edit').modal();
}

function getCategory() {
	$.ajax({
		url : path + '/admin/category/getList',
		type : 'POST',
		data : {
			'isDelete' : '0'
		},
		async : false,
		dataType : "json",
		success : function(data) {
			if (data.code == 200) {
				$('#parameter_categoryName').empty();
				var opt = '<option value="">请选择</option>';
				var category = data.data;
				for (var i = 0; i < category.length; i++) {
					opt += '<option value="' + category[i].id + '">'
							+ category[i].name + '</option>';
				}
				$('#parameter_categoryName').html(opt);
			} else {
				JZ.alert('程序出错：' + data.msg);
			}
		}
	});
}

function updateModal() {
	$('#editTitle').html('修改参数');
	getCategory();
	if (JZ.checkForUpdate("parameter")) {
		var id = $("#parameterList").jqGrid('getGridParam', 'selrow');
		var row = $("#parameterList").jqGrid('getRowData', id);
		$('#parameter_categoryName').val(row.categoryId);
		$('#parameter_categoryName').attr("disabled", true);
		$('#modal-edit').modal();
	}
}

function save() {
	// TODO 先校验
	
	// 再提交
	$.ajax({
		url : path + '/admin/parameter/edit',
		type : 'POST',
		data : $("#editForm").serialize(),
		dataType : "json",
		success : function(data) {
			$('#modal-edit').modal('hide');
			if (data.code == 200) {
				JZ.alert('保存成功!');
				$('#parameterList').trigger("reloadGrid");
			} else {
				JZ.alert('保存失败：' + data.msg);
			}
		}
	});
}

/** delete */
function deleteModal() {
	if (JZ.checkSelectOne("parameter") != null) {
		JZ.confirm("删除之后无法恢复，请确认是否删除？", deleteparameter);
	}
}

function deleteparameter() {
	var id = $("#parameterList").jqGrid('getGridParam', 'selrow');
	$.ajax({
		url : path + '/admin/parameter/edit',
		type : 'POST',
		data : {
			'id' : id,
			'isDelete' : '1'
		},
		dataType : "json",
		success : function(data) {
			if (data.code == 200) {
				JZ.alert('删除成功!');
				$('#parameterList').trigger("reloadGrid");
			} else {
				JZ.alert('删除失败：' + data.msg);
			}
		}
	});
}
