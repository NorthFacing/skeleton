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
	rowNum : 10,
	rowList : [ 10, 20, 30 ],
	pager : '#brandPager',
	jsonReader : {
		root : "data.rows",
		page : "data.pageNum",
		total : "data.totalPage",
		records : "data.recordsNum",
		repeatitems : false,
		cell : "cell",
		id : "id",
		userdata : "code",
		subgrid : {
			root : "rows",
			repeatitems : true,
			cell : "cell"
		}
	},
	height : '400',
	multiselect : false,// 多选（复选框）
	sortname : 'id',
	mtype : "get",
	viewrecords : true,
	sortorder : "desc",
	regional : 'cn'
});

jQuery("#brandList").jqGrid('navGrid', '#brandPager', {
	edit : false,
	add : false,
	del : false
});