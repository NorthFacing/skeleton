/**
 * 设置自己的全局参数，覆盖默认值
 * 
 * @Author Bob
 * @Date 2015-7-21 20:54:42
 */
$.extend($.jgrid.defaults, {
	mtype : "get",
	sortname : 'createTime',
	sortorder : "desc",
	multiselect : false,
	rowNum : 10,
	rowList : [ 20, 50, 100 ],
	jsonReader : {
		root : "data.list",
		page : "data.pageNum",
		total : "data.totalPage",
		records : "data.recordsNum",
		repeatitems : false,
		id : "id",
		userdata : "code",
		subgrid : {
			root : "rows",
			repeatitems : true,
			cell : "cell"
		}
	},
	autowidth : true,
	shrinkToFit : false,
	sortorder : "desc",
	regional : 'cn'
});
