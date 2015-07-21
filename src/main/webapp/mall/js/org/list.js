$("#userList").jqGrid({
   	url:'/admin/user/list',
	datatype: "json",
   	colNames:['姓名','昵称', '角色', '注册时间'],
   	colModel:[
   		{name:'id',index:'id', width:55},
   		{name:'userName',index:'userName', width:90},
   		{name:'nickName',index:'nickName', width:100},
   		{name:'userRole',index:'userRole', width:80},
   		{name:'createTime',index:'createTime', width:80}
   	],
   	rowNum:10,
   	rowList:[10,20,30],
   	pager: '#userPpager',
   	sortname: 'id',
    viewrecords: true,
    sortorder: "desc"
});
$("#userList").jqGrid('navGrid','#userPager',{edit:false,add:false,del:false});