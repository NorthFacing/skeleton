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
//   	rowNum:10,
//   	rowList:[10,20,30],
//   	pager: '#brandPpager',
//   	sortname: 'id',
//    viewrecords: true,
//    jsonReader: {
//    	root:"gridModel",	
//    	repeatitems : false
//    },
//    sortorder: "desc"
  rowNum:10,
  rowList:[10,20,30],
  pager: '#pager2',
  sortname: 'id',
  viewrecords: true,
  sortorder: "desc",
  regional : 'cn'
});
$("#brandList").jqGrid('navGrid','brandPager',{edit:false,add:false,del:false});


//jQuery("#brandList").jqGrid({
//	url:'/mall/admin/brand/getPage',
//    datatype: "json",
//    colNames:['Inv No','Date', 'Client', 'Amount','Tax','Total','Notes'],
//    colModel:[
//        {name:'id',index:'id', width:55},
//        {name:'invdate',index:'invdate', width:90},
//        {name:'name',index:'name asc, invdate', width:100},
//        {name:'amount',index:'amount', width:80, align:"right"},
//        {name:'tax',index:'tax', width:80, align:"right"},        
//        {name:'total',index:'total', width:80,align:"right"},        
//        {name:'note',index:'note', width:150, sortable:false}        
//    ],
//    rowNum:10,
//    rowList:[10,20,30],
//    pager: '#pager2',
//    sortname: 'id',
//    viewrecords: true,
//    sortorder: "desc",
//    caption:"JSON Example"
//});
//jQuery("#brandList").jqGrid('navGrid','#brandPager',{edit:false,add:false,del:false});