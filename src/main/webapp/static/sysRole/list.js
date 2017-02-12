$(function () {
    $("#grid-data").bootgridWrapper({
        navigation: 2,
        ajax: true,
        url: path + "/admin/sysRole/pageData",
        post: function () {
            return {
                pageNum: $("#grid-data").bootgrid("getCurrentPage"),
                pageSize: $("#grid-data").bootgrid("getRowCount"),
                status: 1,
            };
        },
        formatters: {
            "commands": function (column, row) {
                return "<a href='" + path + "/admin/corp/editCorp?id=" + row.id + "' type=\"button\" class=\"btn btn-xs btn-warning command-edit\" data-row-id=\"" + row.id + "\">修改</a> "
                    + " <a onclick=\"delById(" + row.id + ",2,'" + path + "/admin/corp/deleteById','删除')\" href=\"javascript:;\" type=\"button\" class=\"btn btn-xs btn-warning command-edit\" data-row-id=\"" + row.id + "\">删除</a>";
            }
        }
    });
    $("#search-btn").on("click", function (arg1, arg2) {
        $("#grid-data").bootgrid("reload");
    });

});

function delById(id) {
    $.ajax({
        type: "GET",
        url: url,
        data: {id: id},
        dataType: "json",
        success: function (data) {
            if (data.data) {
                CommonUI.alert(str + "成功！");
                window.location.href = "$!{path}/admin/corp/corpManager";
            } else {
                CommonUI.alert(str + "失败:" + data.msg);
            }
        }
    });
}
