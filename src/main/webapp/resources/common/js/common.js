// contextPath 全局变量
//var contextPath = window.location.pathname.split("/")[1];
//var path = "/" + contextPath;

var CommonUtil = {
    stringUtil: {
        isBlank: function (str) {
            return (str == null || str == "");
        }
    },
    getHost: function (url) {
        var host = "null";
        if (typeof url == "undefined"
            || null == url)
            url = window.location.href;
        var regex = /.*\:\/\/([^\/]*).*/;
        var match = url.match(regex);
        if (typeof match != "undefined"
            && null != match)
            host = match[1];
        return host;
    },
    getUrlParam: function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]);
        return null; //返回参数值
    },
    dateFormat: function (date, format) {
        /**
         * //使用方法
         var now = new Date();
         var nowStr = now.format("yyyy-MM-dd hh:mm:ss");
         //使用方法2:
         var testDate = new Date();
         var testStr = testDate.format("YYYY年MM月dd日hh小时mm分ss秒");
         alert(testStr);
         //示例：
         alert(new Date().Format("yyyy年MM月dd日"));
         alert(new Date().Format("MM/dd/yyyy"));
         alert(new Date().Format("yyyyMMdd"));
         alert(new Date().Format("yyyy-MM-dd hh:mm:ss"));
         */
        var o = {
            "M+": date.getMonth() + 1, //month
            "d+": date.getDate(), //day
            "h+": date.getHours(), //hour
            "m+": date.getMinutes(), //minute
            "s+": date.getSeconds(), //second
            "q+": Math.floor((date.getMonth() + 3) / 3), //quarter
            "S": date.getMilliseconds() //millisecond
        };

        if (/(y+)/.test(format)) {
            format = format.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
        }

        for (var k in o) {
            if (new RegExp("(" + k + ")").test(format)) {
                format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
            }
        }
        return format;
    },
    dateAddMonth: function (dtstr, n) {   // n个月后
        var s = dtstr.split("-");
        var yy = parseInt(s[0]);
        var mm = parseInt(s[1] - 1);
        var dd = parseInt(s[2]);
        var dt = new Date(yy, mm, dd);
        dt.setMonth(dt.getMonth() + n);
        if ((dt.getYear() * 12 + dt.getMonth()) > (yy * 12 + mm + n)) {
            dt = new Date(dt.getYear(), dt.getMonth(), 0);
        }
        var year = dt.getYear();
        var month = dt.getMonth() + 1;
        var days = dt.getDate();
        var dd = year + "-" + month + "-" + days;
        return dd;
    }

};

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
            labels: {
                infos: "显示第 {{ctx.start}} 到第 {{ctx.end}} 条记录，总共 {{ctx.total}} 条记录",
                noResults: "没有数据！"
            }
        }, options);
        var bg = $(this).bootgrid(opts);
        return bg;
    }
});
