$("#orgList").jqGrid({
   	url:'/admin/org/list',
	datatype: "json",
   	colNames:['姓名','昵称', '角色', '注册时间'],
   	colModel:[
   		{name:'id',index:'id', width:55},
   		{name:'orgName',index:'orgName', width:90},
   		{name:'nickName',index:'nickName', width:100},
   		{name:'orgRole',index:'orgRole', width:80},
   		{name:'createTime',index:'createTime', width:80}
   	],
   	rowNum:10,
   	rowList:[10,20,30],
   	pager: '#orgPpager',
   	sortname: 'id',
    viewrecords: true,
    sortorder: "desc"
});
$("#orgList").jqGrid('navGrid','#orgPager',{edit:false,add:false,del:false});