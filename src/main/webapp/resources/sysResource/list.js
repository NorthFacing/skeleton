$(function () {

    $("#grid-table").bootgridWrapper({
        url: path + "/admin/sysResource/pageData",
        post: params,
        formatters: {
            "resUrl": resUrlFormatter
        }
    });

    $("#search-btn").on("click", function () {
        $("#grid-data").bootgrid("reload");
    });

});

function params() {
    return {
        current: $("#grid-table").bootgrid("getCurrentPage"),
        rowCount: $("#grid-table").bootgrid("getRowCount")
    };
}

function resUrlFormatter(column, row) {
    return "<a href=\"#\">" + column.id + ": " + row.id + "</a>";
}

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
