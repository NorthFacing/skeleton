/** edit */
function addModal() {
	$('#editTitle').html('新增品牌');
	$('#editForm').attr('action', path + '/admin/brand/add');
	JZ.clearForAdd('brand');
	$('#modal-edit').modal();
}

function updateModal() {
	$('#editTitle').html('修改品牌');
	if (JZ.checkForUpdate("brand")) {
		$('#editForm').attr('action', path + '/admin/brand/update');
		$('#modal-edit').modal();
	}
}

function save() {
	// TODO 先校验
	// jQuery 的 validate 方法的使用
	// 再提交
	$.ajax({
		url : path + '/admin/brand/edit',
		type : 'POST',
		data : $("#editForm").serialize(),
		dataType : "json",
		success : function(data) {
			$('#modal-edit').modal('hide');
			if (data.code == 200) {
				JZ.alert('保存成功!');
				$('#brandList').trigger("reloadGrid");
			} else {
				JZ.alert('保存失败：' + data.msg);
			}
		}
	});
}

/** delete */
function deleteModal() {
	if (JZ.checkSelectOne("brand") != null) {
		JZ.confirm("删除之后无法恢复，请确认是否删除？", deleteBrand);
	}
}

function deleteBrand() {
	var id = $("#brandList").jqGrid('getGridParam', 'selrow');
	$.ajax({
		url : path + '/admin/brand/edit',
		type : 'POST',
		data : {
			'id' : id,
			'isDelete' : '1'
		},
		dataType : "json",
		success : function(data) {
			if (data.code == 200) {
				JZ.alert('删除成功!');
			} else {
				JZ.alert('删除失败：' + data.msg);
			}
		}
	});
}
