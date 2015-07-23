$(document).ready(function() {
	initGrid();
});

/** list */
function initGrid() {
	$("#brandList").jqGrid({
		url : path + "/admin/brand/getPage",
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
		pager : '#brandPager',
		height : '400'
	});
}

function search() {
	var postData = $("#brandList").jqGrid("getGridParam", "postData");
	var s_name = $('#s_name').val();
	$.extend(postData, {
		'name' : s_name
	});
	$("#brandList").trigger("reloadGrid");
}
