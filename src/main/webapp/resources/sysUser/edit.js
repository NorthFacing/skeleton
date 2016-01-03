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
    $('#editTitle').html('新增用户');
    $('#user_orgId').val(node.id);
    $('#user_orgName').val(node.name);
    $('#user_name').val("");
    $('#modal-edit').modal();
}

function updateModal() {
    $('#editTitle').html('修改用户');
    if (JZ.checkForUpdate("user")) {
        var id = $("#userList").jqGrid('getGridParam', 'selrow');
        var row = $("#userList").jqGrid('getRowData', id);
        var pName;
        var pFullName;
        if (row.parentId != null && row.parentId != "") {
            var org = getOrg(row.parentId);
            pName = org.name;
            pFullName = org.fullName + '->';
        } else {
            pName = "";
            pFullName = ""
        }
        $("#user_parentName").val(pName);
        $('#user_fullName').val(pFullName + row.name);
        change(pFullName);
        $('#modal-edit').modal();
    }
}

function getOrg(id) {
    var org;
    $.ajax({
        url: path + '/admin/user/getById',
        type: 'GET',
        data: {
            id: id
        },
        async: false,
        dataType: "json",
        success: function (data) {
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
        url: path + '/admin/user/edit',
        type: 'POST',
        data: $("#editForm").serialize(),
        dataType: "json",
        success: function (data) {
            $('#modal-edit').modal('hide');
            if (data.code == 200) {
                JZ.alert('保存成功!');
                $('#userList').trigger("reloadGrid");
            } else {
                JZ.alert('保存失败：' + data.msg);
            }
        }
    });
}

/** delete */
function deleteModal() {
    if (JZ.checkSelectOne("user") != null) {
        JZ.confirm("删除之后无法恢复，请确认是否删除？", deleteuser);
    }
}

function deleteuser() {
    var id = $("#userList").jqGrid('getGridParam', 'selrow');
    $.ajax({
        url: path + '/admin/user/delById',
        type: 'POST',
        data: {
            'id': id
        },
        dataType: "json",
        success: function (data) {
            if (data.code == 200) {
                JZ.alert('删除成功!');
                $('#userList').trigger("reloadGrid");
            } else {
                JZ.alert('删除失败：' + data.msg);
            }
        }
    });
}
