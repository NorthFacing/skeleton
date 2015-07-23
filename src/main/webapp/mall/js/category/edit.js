/** edit */
function addModal() {
	$('#editTitle').html('新增类目');
	$('#editForm').attr('action', path + '/admin/category/add');
	JZ.clearForAdd('category');
	$('#modal-edit').modal();
}

function updateModal() {
	$('#editTitle').html('修改类目');
	if (JZ.checkForUpdate("category")) {
		$('#editForm').attr('action', path + '/admin/category/update');
		$('#modal-edit').modal();
	}
}

function save() {
	// TODO 先校验
	// jQuery 的 validate 方法的使用
	// 再提交
	$.ajax({
		url : path + '/admin/category/edit',
		type : 'POST',
		data : $("#editForm").serialize(),
		dataType : "json",
		success : function(data) {
			$('#modal-edit').modal('hide');
			if (data.code == 200) {
				JZ.alert('保存成功!');
				$('#categoryList').trigger("reloadGrid");
			} else {
				JZ.alert('保存失败：' + data.msg);
			}
		}
	});
}

/** delete */
function deleteModal() {
	if (JZ.checkSelectOne("category") != null) {
		JZ.confirm("删除之后无法恢复，请确认是否删除？", deleteCategory);
	}
}

function deleteCategory() {
	var id = $("#categoryList").jqGrid('getGridParam', 'selrow');
	$.ajax({
		url : path + '/admin/category/edit',
		type : 'POST',
		data : {
			'id' : id,
			'isDelete' : '1'
		},
		dataType : "json",
		success : function(data) {
			if (data.code == 200) {
				JZ.alert('删除成功!');
				$('#categoryList').trigger("reloadGrid");
			} else {
				JZ.alert('删除失败：' + data.msg);
			}
		}
	});
}
