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
		width : 550
	}, {
		name : 'createTime',
		index : 'createTime',
//		formatter : "date",
//		formatoptions : {
//			srcformat : "iso8601",
//			newformat: 'Y-m-d'
//		},
		width : 150

	} ],
	pager : '#brandPager',
	height : '400'
});
