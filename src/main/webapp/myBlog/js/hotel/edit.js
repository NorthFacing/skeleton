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
	setOptions(getManager(node.id));
	$('#modal-edit').modal();
}

function updateModal() {
	$('#editTitle').html('修改酒店');
	var id = $("#hotelList").jqGrid('getGridParam', 'selrow');
	if (id == null) {
		JZ.alert(s == null ? "请选择一条数据后再进行操作！" : s);
		return false;
	}
	var data = $("#hotelList").jqGrid('getRowData', id);
	setOptions(getManager(data.orgId));
	for ( var param in data) {
		$("#hotel_" + param).val(data[param]);
	}
	$('#modal-edit').modal();
}

function getManager(orgId) {
	var userList;
	$.ajax({
		url : path + '/admin/user/getList',
		type : 'POST',
		data : {
			orgId : orgId,
			userRole : 'hotelManager',
			isDelete : 0
		},
		async : false,
		dataType : "json",
		success : function(data) {
			if (data.code == 200) {
				userList = data.data;
			} else {
				JZ.alert('程序出错：' + data.msg);
			}
		}
	});
	return userList;
}

function setOptions(userList) {
	var opt = '<option value="">请选择</option>';
	for (var i = 0; i < userList.length; i++) {
		opt += '<option value="' + userList[i].id + '">' + userList[i].nickName
				+ '</option>';
	}
	$('#hotel_managerId').html(opt);
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
