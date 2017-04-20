// 弹出框（提示框，确认框）
var msgDialog = {
    alert: function (msg, callback, title, width) {
        var dialog = new ax5.ui.dialog();
        var mask = new ax5.ui.mask();
        dialog.setConfig({
            theme: "warning",
            width: (null == width || undefined == width) ? 350 : width,
            title: (null == title || undefined == title) ? "温馨提示" : title,
            lang: {
                "ok": "确定", "cancel": "取消"
            },
            onStateChanged: function () {
                if (this.state === "open") {
                    mask.open();
                }
                else if (this.state === "close") {
                    mask.close();
                }
            }
        });
        dialog.alert(msg, function () {
            if (callback) {
                callback();
            }
        });
    },
    confirm: function (msg, callback, title, width) {
        var confirmDialog = new ax5.ui.dialog();
        var mask = new ax5.ui.mask();
        confirmDialog.setConfig({
            theme: "danger",
            width: (null == width || undefined == width) ? 350 : width,
            title: (null == title || undefined == title) ? "操作确认" : title,
            lang: {
                "ok": "确定", "cancel": "取消"
            },
            onStateChanged: function () {
                if (this.state === "open") {
                    mask.open();
                }
                else if (this.state === "close") {
                    mask.close();
                }
            }
        });
        confirmDialog.confirm(msg, function () {
            if (this.key == "ok") {
                if (callback) {
                    callback();
                }
            }
        });
    },
    form: function (input, callback, title, width) {
        var promptDialog = new ax5.ui.dialog();
        var mask = new ax5.ui.mask();
        promptDialog.setConfig({
            theme: "danger",
            width: (null == width || undefined == width) ? 350 : width,
            title: (null == title || undefined == title) ? "数据表单" : title,
            lang: {
                "ok": "确定", "cancel": "取消"
            },
            onStateChanged: function () {
                if (this.state === "open") {
                    mask.open();
                }
                else if (this.state === "close") {
                    mask.close();
                }
            }
        });
        promptDialog.prompt({
            input: input
        }, function () {
            if (this.key == "ok") {
                if (callback) {
                    callback();
                }
            }
        });
    }
};

var dialogMsg = {
    toSelectOne: "请选中一条数据后进行操作！",
    selectOnlyOne: "只能选中一条数据进行操作！"
};

// 日期选择器
var CanlenderPicker = {
    date: function (targetId, callback) {
        var picker = new ax5.ui.picker();
        picker.bind({
            target: $('#' + targetId),
            direction: "top",
            content: {
                width: 250,
                margin: 10,
                type: 'date',
                config: {
                    control: {
                        left: '<i class="fa fa-chevron-left"></i>',
                        yearTmpl: '%s',
                        monthTmpl: '%s',
                        right: '<i class="fa fa-chevron-right"></i>'
                    },
                    lang: {
                        yearTmpl: "%s年",
                        months: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'],
                        dayTmpl: "%s"
                    }
                },
                formatter: {
                    pattern: 'date'
                }
            },
            onStateChanged: function () {
                if (callback) {
                    callback();
                }
            }
        });
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
