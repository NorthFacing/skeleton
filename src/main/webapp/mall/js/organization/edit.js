/** edit */
function addModal() {
	// 新增必须选中父节点
	var treeObj = $.fn.zTree.getZTreeObj("orgTree");
	var nodes = treeObj.getSelectedNodes();
	if (nodes.length != 1) {
		JZ.alert("请在左侧选中一条节点后再进行新增操作！");
		return false;
	}
	var node = nodes[0];
	$('#editTitle').html('新增层级');
	$('#editForm').attr('action', path + '/admin/organization/add');
	$('#organization_parentId').val(node.id);
	$('#organization_parentName').val(node.name);
	$('#organization_name').val("");
	$('#organization_code').val(node.code + '.' + getCode(node.id));
	$('#organization_fullName').val(node.fullName + '->');
	change(node.fullName + '->');
	$('#modal-edit').modal();
}

function change(pName) {
	$('#organization_name').bind('input propertychange', function() {
		var name = $('#organization_name').val();
		$('#organization_fullName').val(pName + name);
	});
}

function getCode(pId) {
	var code;
	$.ajax({
		url : path + '/admin/organization/getCodeByParentId',
		type : 'GET',
		data : {
			parentId : pId
		},
		async : false,
		dataType : "json",
		success : function(data) {
			if (data.code == 200) {
				code = data.data;
			} else {
				JZ.alert('程序出错：' + data.msg);
			}
		}
	});
	return code;
}

function updateModal() {
	$('#editTitle').html('修改层级');
	if (JZ.checkForUpdate("organization")) {
		$('#editForm').attr('action', path + '/admin/organization/update');
		$('#modal-edit').modal();
	}
}

function save() {
	// TODO 先校验
	// jQuery 的 validate 方法的使用
	// 再提交
	$.ajax({
		url : path + '/admin/organization/edit',
		type : 'POST',
		data : $("#editForm").serialize(),
		dataType : "json",
		success : function(data) {
			$('#modal-edit').modal('hide');
			if (data.code == 200) {
				JZ.alert('保存成功!');
				$('#organizationList').trigger("reloadGrid");
			} else {
				JZ.alert('保存失败：' + data.msg);
			}
		}
	});
}

/** delete */
function deleteModal() {
	if (JZ.checkSelectOne("organization") != null) {
		JZ.confirm("删除之后无法恢复，请确认是否删除？", deleteOrganization);
	}
}

function deleteOrganization() {
	var id = $("#organizationList").jqGrid('getGridParam', 'selrow');
	$.ajax({
		url : path + '/admin/organization/delById',
		type : 'POST',
		data : {
			'id' : id
		},
		dataType : "json",
		success : function(data) {
			if (data.code == 200) {
				JZ.alert('删除成功!');
				$('#organizationList').trigger("reloadGrid");
			} else {
				JZ.alert('删除失败：' + data.msg);
			}
		}
	});
}
