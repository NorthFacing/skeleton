$(document).ready(function() {
	initGrid();
});

/** list */
function initGrid() {
	$("#parameterList").jqGrid({
		url : path + "/admin/parameter/getPage",
		datatype : "json",
		colNames : [ 'id', 'categoryId', '类目归属', '参数名称', '参数描述' ],
		colModel : [ {
			name : 'id',
			index : 'id',
			hidden : true
		}, {
			name : 'categoryId',
			index : 'categoryId',
			hidden : true
		}, {
			name : 'categoryName',
			index : 'categoryName',
			width : 250
		}, {
			name : 'name',
			index : 'name',
			width : 250
		}, {
			name : 'description',
			index : 'description',
			width : 550
		} ],
		pager : '#parameterPager'
	});
}

function search() {
	var postData = $("#parameterList").jqGrid("getGridParam", "postData");
	var s_name = $('#s_name').val();
	$.extend(postData, {
		'name' : s_name
	});
	$("#parameterList").trigger("reloadGrid");
}
