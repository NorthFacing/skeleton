/** edit */
function addModal() {
	// 新增必须选中父节点
	var treeObj = $.fn.zTree.getZTreeObj("orgTree");
	var nodes = treeObj.getSelectedNodes();
	if (nodes.length != 1) {
		JZ.alert("请在左侧选中一条节点后再进行新增操作！");
		return false;
	}
	JZ.clearForAdd('hotel');
	var node = nodes[0];
	$('#editTitle').html('新增酒店');
	$('#hotel_orgId').val(node.id);
	$('#hotel_orgName').val(node.name);
	$('#hotel_orgCode').val(node.code);
	$('#modal-edit').modal();
}

function updateModal() {
	$('#editTitle').html('修改酒店');
	if (JZ.checkForUpdate("hotel")) {
		$('#modal-edit').modal();
	}
}

function getOrg(id) {
	var org;
	$.ajax({
		url : path + '/admin/hotel/getById',
		type : 'GET',
		data : {
			id : id
		},
		async : false,
		dataType : "json",
		success : function(data) {
			if (data.code == 200) {
				org = data.data;
			} else {
				JZ.alert('程序出错：' + data.msg);
			}
		}
	});
	return org;
}

function save() {
	// TODO 先校验

	// 再提交
	$.ajax({
		url : path + '/admin/hotel/edit',
		type : 'POST',
		data : $("#editForm").serialize(),
		dataType : "json",
		success : function(data) {
			$('#modal-edit').modal('hide');
			if (data.code == 200) {
				JZ.alert('保存成功!');
				$('#hotelList').trigger("reloadGrid");
			} else {
				JZ.alert('保存失败：' + data.msg);
			}
		}
	});
}

/** delete */
function deleteModal() {
	if (JZ.checkSelectOne("hotel") != null) {
		JZ.confirm("删除之后无法恢复，请确认是否删除？", deletehotel);
	}
}

function deletehotel() {
	var id = $("#hotelList").jqGrid('getGridParam', 'selrow');
	$.ajax({
		url : path + '/admin/hotel/delById',
		type : 'POST',
		data : {
			'id' : id
		},
		dataType : "json",
		success : function(data) {
			if (data.code == 200) {
				JZ.alert('删除成功!');
				$('#hotelList').trigger("reloadGrid");
			} else {
				JZ.alert('删除失败：' + data.msg);
			}
		}
	});
}
