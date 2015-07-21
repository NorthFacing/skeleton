$("#brandList").jqGrid({
   	url:"/mall/admin/brand/getPage",
	datatype: "json",
   	colNames:['id', '名称','描述', '添加时间'],
   	colModel:[
   		{name:'id',index:'id', width:55},
   		{name:'name',index:'name', width:90},
   		{name:'description',index:'description', width:100},
   		{name:'createTime',index:'createTime', width:80}
   	],
   	rowNum:10,
   	rowList:[10,20,30],
   	pager: '#brandPpager',
   	sortname: 'id',
    viewrecords: true,
    jsonReader: {
    	root:"gridModel",	
    	repeatitems : false
    },
    sortorder: "desc"
});
$("#brandList").jqGrid('navGrid','#brandPager',{edit:false,add:false,del:false});
//$(document).ready(function () {
//    $("#brandList").jqGrid({
//        url: 'http://trirand.com/blog/phpjqgrid/examples/jsonp/getjsonp.php?callback=?&qwery=longorders',
//        mtype: "GET",
//        datatype: "jsonp",
//        colModel: [
//            { label: 'OrderID', name: 'OrderID', key: true, width: 75 },
//            { label: 'Customer ID', name: 'CustomerID', width: 150 },
//            { label: 'Order Date', name: 'OrderDate', width: 150 },
//            { label: 'Freight', name: 'Freight', width: 150 },
//            { label:'Ship Name', name: 'ShipName', width: 150 }
//        ],
//		viewrecords: true,
//        width: 780,
//        height: 250,
//        rowNum: 20,
//        regional: "cn",
//        rowList: [10, 20, 30],
//        pager: "#brandPager"
//    });
//});


