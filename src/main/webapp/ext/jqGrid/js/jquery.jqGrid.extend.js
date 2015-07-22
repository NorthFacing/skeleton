/**
 * 初始化Grid 需实现方法registerMenuEvent来初始化
 * 
 * 浮动菜单选项 1、 调用模糊查询服务可用此方法来实现
 * $("#gridId").jqGrid('setGridParam',{url:"*.action?sidx="+parm1+"&amp;sval="+parm2,page:1}).trigger("reloadGrid");
 * 其中: 1、sidx是模糊查询列，如果为空，则全部模糊 2、sval是查询值 3、page: 1 查询结束默认显示第一页 2、表格排序参数
 * 1、sidx:模糊查询列 2、sord:排序方式 (ASC,DESC)
 */
$.fn.initlxnGrid = function(item) {
	// 判断浏览器设置高度
	var initHeight = 381;
	if (!$.browser.msie) {
		initHeight = 371;
	}
	var pagerDivId = (item.otherTable) ? "pager_div_" + item.otherTable
			: "pager_div";
	if (item.viewrecords) {
		$("&lt;div id='" + pagerDivId + "'&gt;&lt;/div&gt;").appendTo(
				document.body);
	}
	this.jqGrid({
		url : item.url,
		mtype : "PosT",
		datatype : "json",
		viewrecords : item.viewrecords,// 是否显示数据记录
		height : item.height ? item.height : initHeight,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : '#' + pagerDivId,
		sortorder : item.sortorder,// 排序类型
		sortname : item.sortname,// 默认排序列
		scroll : false,
		search : false,
		pagerpos : 'left',// 分页位置
		recordpos : 'right',// 数据总数位置
		loadComplete : item.loadComplete,// 加载完回调
		forceFit : true,
		autowidth : true,// 是否宽度自适应
		rownumbers : false,// 是否显示行号
		multiselect : item.multiselect,// 是否多选
		onCellSelect : item.onCellSelect,
		scrollOffset : 0,
		colNames : item.colNames,
		colModel : item.colModel,
		prmNames : {
			page : "currentPage",
			rows : "pageSize",
			sort : "sidx",
			order : "sord",
			search : "_search",
			nd : "nd",
			id : "id",
			oper : "oper",
			editoper : "edit",
			addoper : "add",
			deloper : "del",
			subgridid : "id",
			npage : null,
			totalrows : "totalrows"
		},
		jsonReader : { // 和后台返回的JSON串相对应
			root : "rows",// json数据项key
			records : "records",// 总记录数
			total : 'total',// 总页数
			page : 'page',// 当前页
			cell : "",
			repeatitems : false
		},
		caption : ''// grid 的title
	});
	// 注册grid bar
	jQuery("#volume_table").jqGrid('navGrid', '#foot_bar', {
		refresh : false,
		search : false,
		edit : false,
		add : false,
		del : false
	});
};