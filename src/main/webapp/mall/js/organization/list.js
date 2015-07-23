$(document).ready(function() {
	initGrid();
});

/** list */
function initGrid() {
	$("#organizationList").jqGrid({
		url : path + "/admin/organization/getPage",
		datatype : "json",
		colNames : [ 'id', '名称', '编码', '全称'],
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
			name : 'fullName',
			index : 'fullName',
			width : 550
		} ],
		pager : '#organizationPager',
		height : '400'
	});
}

function search() {
	var postData = $("#organizationList").jqGrid("getGridParam", "postData");
	var s_name = $('#s_name').val();
	$.extend(postData, {
		'name' : s_name
	});
	$("#organizationList").trigger("reloadGrid");
}
