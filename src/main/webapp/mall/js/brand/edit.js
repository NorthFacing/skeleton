/** edit */
function addModal() {
	$('#editTitle').html('新增品牌');
	$('#editForm').attr('action', path + '/admin/brand/add');
	JZ.clearData('brand');
	$('#modal-edit').modal();
}

function updateModal() {
	$('#editTitle').html('修改品牌');
	if (JZ.checkSelectOne("brand")) {
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
		data : $("editForm").serialize(),
		dataType : "json",
		success : function(data) {
			if (data.code == 200) {
				JZ.alert('保存成功!');
			} else {
				JZ.alert('保存失败：' + data.msg);
			}
			$('#modal-edit').modal().remove();
		}
	});
}

/** delete */
function deleteModal() {
	if (JZ.checkSelectOne("brand")) {
		JZ.confirm("删除之后无法恢复，请确认是否删除？", deleteBrand);
	}
}
function deleteBrand() {
	alert("删除回到");
}
