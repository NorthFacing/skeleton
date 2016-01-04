$(function () {
    $("#grid-data").bootgridWrapper({
        navigation: 2,
        ajax: true,
        url: path + "/admin/corp/corpManagerData",
        post: function () {
            return {
                currentPage: $("#grid-data").bootgrid("getCurrentPage"),
                //name: $("#name").val() + "",
                status: 1,
            };
        },
        formatters: {
            "commands": function (column, row) {
                if (row.status == 1) {
                    //正常 时
                    return "<a href='" + path + "/admin/corp/editCorp?id=" + row.id + "' type=\"button\" class=\"btn btn-xs btn-warning command-edit\" data-row-id=\"" + row.id + "\">修改</a> "
                        + " <a onclick=\"caoZuofirm(" + row.id + ",2,'" + path + "/admin/corp/deleteById','删除')\" href=\"javascript:;\" type=\"button\" class=\"btn btn-xs btn-warning command-edit\" data-row-id=\"" + row.id + "\">删除</a>";
                }
                else if (row.status == 2) {
                    //删除时
                }
            }
        }
    });
    $("#search-btn").on("click", function (arg1, arg2) {
        $("#grid-data").bootgrid("reload");
    });

});

function delById(str, url, status, id) {
    var code = $("#code").val();
    if ($.trim(code) == "") {
        CommonUI.alert("验证码不能为空！");
        return;
    }
    $.ajax({
        type: "GET",
        url: url,
        data: {id: id, code: code, status: status},
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
