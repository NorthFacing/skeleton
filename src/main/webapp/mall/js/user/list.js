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
	$("#userList").jqGrid({
		url : path + "/admin/user/getPage",
		datatype : "json",
		colNames : [ 'id','orgId', '登录名', '用户昵称', '用户角色' ],
		colModel : [ {
			name : 'id',
			index : 'id',
			hidden : true
		}, {
			name : 'orgId',
			index : 'orgId',
			hidden : true
		}, {
			name : 'userName',
			index : 'userName',
			width : 200
		}, {
			name : 'nickName',
			index : 'nickName',
			width : 150
		}, {
			name : 'userRole',
			index : 'userRole',
			width : 460
		} ],
		pager : '#userPager'
	});
}

function search(orgId) {
	var postData = $("#userList").jqGrid("getGridParam", "postData");
	var s_name = $('#s_name').val();
	$.extend(postData, {
		'name' : s_name,
		'orgId' : orgId
	});
	$("#userList").trigger("reloadGrid");
}

/** list 结束 */
