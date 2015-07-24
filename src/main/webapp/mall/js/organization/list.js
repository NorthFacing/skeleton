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
		url : path + '/admin/organization/getList',
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
	$("#organizationList").jqGrid({
		url : path + "/admin/organization/getPage",
		datatype : "json",
		colNames : [ 'id', '层级名称', '层级编码', '层级全称' ],
		colModel : [ {
			name : 'id',
			index : 'id',
			hidden : true
		}, {
			name : 'name',
			index : 'name',
			width : 200
		}, {
			name : 'code',
			index : 'code',
			width : 150
		}, {
			name : 'fullName',
			index : 'fullName',
			width : 460
		} ],
		pager : '#organizationPager',
		height : '450'
	});
}

function search(parentId) {
	var postData = $("#organizationList").jqGrid("getGridParam", "postData");
	var s_name = $('#s_name').val();
	$.extend(postData, {
		'name' : s_name,
		'parentId' : parentId
	});
	$("#organizationList").trigger("reloadGrid");
}

/** list 结束 */
