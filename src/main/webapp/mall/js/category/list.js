$(document).ready(function() {
	initGrid();
});

/** list */
function initGrid() {
	$("#categoryList").jqGrid({
		url : path + "/admin/category/getPage",
		datatype : "json",
		colNames : [ 'id', '名称', '编码', '描述'],
		colModel : [ {
			name : 'id',
			index : 'id',
			hidden : true
		}, {
			name : 'name',
			index : 'name',
			width : 250
		}, {
			name : 'code',
			index : 'code',
			width : 250
		}, {
			name : 'description',
			index : 'description',
			width : 550
		} ],
		pager : '#categoryPager',
		height : '400'
	});
}

function search() {
	var postData = $("#categoryList").jqGrid("getGridParam", "postData");
	var s_name = $('#s_name').val();
	$.extend(postData, {
		'name' : s_name
	});
	$("#categoryList").trigger("reloadGrid");
}
