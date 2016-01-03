/**
 * 将“元”转变为“分”
 *
 * @param cents
 *            需要转换的数据（以分为单位的整数）
 */
Handlebars.registerHelper('formatDollars', function (cents) {
    return (cents / 100).toFixed(2);
});

/**
 *
 */
Handlebars.registerHelper('formatDollars', function (cents) {
    return (cents / 100).toFixed(2);
});

/**
 * Demo
 *
 * @Demo registerHelper 写法范例
 * @Param v1
 *            参数1
 * @Param v2
 *            参数2
 * @Param options
 *            Handlebars自身参数，无需显示赋值
 */
Handlebars.registerHelper("demo", function (v1, v2, options) {
    if (v1 > v2) {
        // 满足添加继续执行（fn表示返回if模板渲染之后的结果）
        return options.fn(this);
    } else {
        // 不满足条件执行{{else}}部分（inverse表示返回else模板渲染之后的结果）
        return options.inverse(this);
    }
});