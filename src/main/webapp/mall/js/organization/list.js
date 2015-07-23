$(document).ready(function() {
	initZTree();
	initGrid();
});

/** zTree */
var setting = {
	data: {
		simpleData: {
			enable: true
		}
	}
};
var zNodes =[
	{ id:1, pId:0, name:"展开、折叠 自定义图标不同", open:true, iconOpen:"img/1_open.png", iconClose:"img/1_close.png"},
	{ id:11, pId:1, name:"叶子节点1", icon:"img/2.png"},
	{ id:12, pId:1, name:"叶子节点2", icon:"img/3.png"},
	{ id:13, pId:1, name:"叶子节点3", icon:"img/5.png"},
	{ id:2, pId:0, name:"展开、折叠 自定义图标相同", open:true, icon:"img/4.png"},
	{ id:21, pId:2, name:"叶子节点1", icon:"img/6.png"},
	{ id:22, pId:2, name:"叶子节点2", icon:"img/7.png"},
	{ id:23, pId:2, name:"叶子节点3", icon:"img/8.png"},
	{ id:3, pId:0, name:"不使用自定义图标", open:true },
	{ id:31, pId:3, name:"叶子节点1"},
	{ id:32, pId:3, name:"叶子节点2"},
	{ id:33, pId:3, name : "叶子节点3"}
];

function initZTree() {
	$.fn.zTree.init($("#orgTree"), setting, zNodes);
}

/** list */
function initGrid() {
	$("#organizationList").jqGrid({
		url : path + "/admin/organization/getPage",
		datatype : "json",
		colNames : [ 'id', '名称', '编码', '全称' ],
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

function search() {
	var postData = $("#organizationList").jqGrid("getGridParam", "postData");
	var s_name = $('#s_name').val();
	$.extend(postData, {
		'name' : s_name
	});
	$("#organizationList").trigger("reloadGrid");
}
