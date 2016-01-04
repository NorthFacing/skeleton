$(function () {
    $("#save").on("click", function () {
        $.ajax({
            url: path + "/admin/sysRole/save",
            type: "POST",
            data: $("#form").serialize(),
            dataType: "json",
            success: function (data) {
                if (data.code == 200) {
                    CommonUI.alert("保存成功！");
                    window.location.href = path + "/admin/sysRole/list";
                } else {
                    CommonUI.alert(data.msg);
                }
            }
        });
    })
})