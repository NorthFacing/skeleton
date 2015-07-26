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
	$("#hotelList").jqGrid(
			{
				url : path + "/admin/hotel/getPage",
				datatype : "json",
				colNames : [ 'id', 'orgId', 'orgCode', '酒店名称', '酒店经理ID',
						'酒店经理', '联系电话', '联系手机', '所属层级', '所属层级', '详细地址' ],
				colModel : [ {
					name : 'id',
					index : 'id',
					hidden : true
				}, {
					name : 'orgId',
					index : 'orgId',
					hidden : true
				}, {
					name : 'orgCode',
					index : 'orgCode',
					hidden : true
				}, {
					name : 'name',
					index : 'name',
					width : 200
				}, {
					name : 'managerId',
					index : 'managerId',
					hidden : true
				}, {
					name : 'managerName',
					index : 'managerName',
					width : 200
				}, {
					name : 'telePhone',
					index : 'telePhone',
					width : 100
				}, {
					name : 'mobile',
					index : 'mobile',
					width : 100
				}, {
					name : 'orgName',
					index : 'orgName',
					hidden : true
				}, {
					name : 'orgFullName',
					index : 'orgFullName',
					width : 200
				}, {
					name : 'address',
					index : 'address',
					width : 300
				} ],
				pager : '#hotelPager'
			});
}

function search(orgId) {
	var postData = $("#hotelList").jqGrid("getGridParam", "postData");
	var s_name = $('#s_name').val();
	$.extend(postData, {
		'name' : s_name,
		'orgId' : orgId
	});
	$("#hotelList").trigger("reloadGrid");
}

/** list 结束 */
