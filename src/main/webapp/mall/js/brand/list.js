$("#brandList").jqGrid({
	url : "/mall/admin/brand/getPage",
	datatype : "json",
	colNames : [ 'id', '名称', '描述', '添加时间' ],
	colModel : [ {
		name : 'id',
		index : 'id',
		hidden : true
	}, {
		name : 'name',
		index : 'name',
		width : 250
	}, {
		name : 'description',
		index : 'description',
		width : 600
	}, {
		name : 'createTime',
		index : 'createTime',
		width : 150
	} ],
	pager : '#brandPager',
	height : '400',
	viewrecords : true,
	sortorder : "desc",
	regional : 'cn'
});

jQuery("#brandList").jqGrid('navGrid', '#brandPager', {
	edit : false,
	add : false,
	del : false
});