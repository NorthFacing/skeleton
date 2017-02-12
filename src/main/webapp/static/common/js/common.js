var CommonUI = {
    alert: function (mes, callback) {
        var d = dialog({
            title: '提示信息',
            width: 350,
            content: mes,
            button: [{
                value: '确定',
                callback: function () {
                    if (callback) {
                        callback();
                    }
                },
                autofocus: true
            }]
        });
        d.showModal();
    },
    confirm: function (s, callback, height) {
        height = height || 40;
        var alertDialog = dialog({
            title: '温馨提示',
            content: s,
            width: 350,
            height: height,
            fixed: true,
            okValue: "确定",
            ok: function () {
                if (callback) {
                    callback();
                } else {
                    alertDialog.close().remove();
                }
            },
            cancelValue: "取消",
            cancel: function () {
                alertDialog.close().remove();
            }
        });
        alertDialog.showModal();
    }
};

// 列表表格
$.fn.extend({
    bootgridWrapper: function (options) {
        var opts = $.extend({
            ajax: true,
            navigation: 2,
            selection: true,
            rowSelect: true,
            multiSelect: true,
            labels: {
                infos: "显示第 {{ctx.start}} 到第 {{ctx.end}} 条记录，总共 {{ctx.total}} 条记录",
                noResults: "没有数据！"
            }
        }, options);
        var bg = $(this).bootgrid(opts);
        return bg;
    }
});
