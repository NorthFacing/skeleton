$(function () {

    $("#grid-data").bootgrid({
        ajax: true,
        post: function () {
            /* To accumulate custom parameter with the request object */
            return {
                id: "b0df282a-0d67-40e5-8558-c9e93b7befed"
            };
        },
        url: path + "/admin/sysResource/pageData",
        formatters: {
            "resUrl": function (column, row) {
                return "<a href=\"#\">" + column.id + ": " + row.id + "</a>";
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
