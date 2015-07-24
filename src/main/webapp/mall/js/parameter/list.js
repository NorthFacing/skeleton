$(document).ready(function() {
	initGrid();
	// 同步方法，放在后面
	initZTree();
});

/** zTree 开始 */
var setting = {
	async : {
		enable : true,
		autoParam : [ "id" ],
		url : path + '/admin/parameter/getList',
		contentType : "application/json",
		dataFilter : ajaxDataFilter,
		otherParam : {
			"sidx" : "name",
			"sord" : "asc"
		},
		type : "post"
	},
	callback : {
		onClick : zTreeOnClick
	},
	data : {
		simpleData : {
			enable : true,
			idKey : "id",
			pIdKey : "parentId"

		}
	}
};

function zTreeOnClick(event, treeId, treeNode) {
	search(treeNode.id);
};

function ajaxDataFilter(treeId, parentNode, responseData) {
	if (responseData.code = 200) {
		return responseData.data;
	} else {
		JZ.alert("数据加载异常：" + responseData.msg);
	}
};

function initZTree() {
	$.fn.zTree.init($("#orgTree"), setting);
}

/** zTree 结束 */

/** list 开始 */
function initGrid() {
	$("#parameterList").jqGrid({
		url : path + "/admin/parameter/getPage",
		datatype : "json",
		colNames : [ 'id', '参数名称', '参数描述' ],
		colModel : [ {
			name : 'id',
			index : 'id',
			hidden : true
		}, {
			name : 'name',
			index : 'name',
			width : 200
		}, {
			name : 'description',
			index : 'description',
			width : 400
		} ],
		pager : '#parameterPager'
	});
}

function search(parentId) {
	var postData = $("#parameterList").jqGrid("getGridParam", "postData");
	var s_name = $('#s_name').val();
	$.extend(postData, {
		'name' : s_name,
		'parentId' : parentId
	});
	$("#parameterList").trigger("reloadGrid");
}

/** list 结束 */
