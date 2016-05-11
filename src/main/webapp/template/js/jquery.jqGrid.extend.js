/**
 * 设置自己的全局参数，覆盖默认值
 */
$.extend($.jgrid.defaults, {
    mtype: "get",
    sortname: 'create_time',
    sortorder: "desc",
    multiselect: false,
    rowNum: 20,
    rowList: [20, 50, 100],
    jsonReader: {
        root: "data.result",
        page: "data.pageNum",
        total: "data.totalPage",
        records: "data.totalCount",
        repeatitems: false,
        id: "id",
        userdata: "code"
    },
    prmNames: {
        page: "pageNum",
        rows: "pageSize"
    },
    autowidth: true,
    shrinkToFit: false,
    regional: 'cn',
    height: '450'
});

/** 所有的请求改为POST方式，防止乱码 */
$.jgrid.ajaxOptions.type = 'post';

