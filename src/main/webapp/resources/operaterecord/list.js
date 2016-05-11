$(document).ready(function () {
    initGrid();
});

/** list */
function initGrid() {
    $("#operaterecordList").jqGrid({
        url: path + "/admin/operaterecord/pageData",
        datatype: "json",
        colNames: ['id', 'productCode', 'machineId', 'C', 'R', 'originCode', 'playLoadStr', 'actionCruf', 'actionLength', 'dataLength', 'fromHost', 'toHost', 'appLoginType', 'deviceLoginType', 'lastModifyTime'],
        colModel: [{
            name: 'id',
            index: 'id',
            hidden: true
        }, {
            name: 'productCode',
            index: 'productCode',
            width: 80
        }, {
            name: 'machineId',
            index: 'machineId',
            width: 250
        }, {
            name: 'category',
            index: 'category',
            width: 50
        }, {
            name: 'resource',
            index: 'resource',
            width: 50
        }, {
            name: 'originCode',
            index: 'originCode',
            width: 100
        }, {
            name: 'playLoadStr',
            index: 'playLoadStr',
            width: 500
        }, {
            name: 'actionCruf',
            index: 'actionCruf',
            width: 150
        }, {
            name: 'actionLength',
            index: 'actionLength',
            width: 80
        }, {
            name: 'dataLength',
            index: 'dataLength',
            width: 80
        }, {
            name: 'fromHost',
            index: 'fromHost',
            width: 150
        }, {
            name: 'toHost',
            index: 'toHost',
            width: 150
        }, {
            name: 'appLoginType',
            index: 'appLoginType',
            width: 150
        }, {
            name: 'deviceLoginType',
            index: 'deviceLoginType',
            width: 150
        }, {
            name: 'lastModifyTime',
            index: 'lastModifyTime',
            width: 150
        }],
        pager: '#operaterecordPager'
    });
}

function search() {
    var postData = $("#operaterecordList").jqGrid("getGridParam", "postData");
    var productCode = $('#s_productCode').val();
    var machineId = $('#s_machineId').val();
    $.extend(postData, {
        'productCode': productCode,
        'machineId': machineId
    });
    $("#operaterecordList").trigger("reloadGrid");
}



