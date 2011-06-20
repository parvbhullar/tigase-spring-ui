/**
 * 树形UI综合示例(下拉树)
 * 
 * @author XiongChun
 * @since 2010-10-28
 */
Ext.onReady(function() {

			var root = new Ext.tree.AsyncTreeNode({
						text : '部门树',
						expanded : true,
						id : '001'
					});

			var deptTree = new Ext.ux.tree.TreeGrid({
						loader : new Ext.tree.TreeLoader({
									dataUrl : 'treeDemo.ered?reqCode=departmentTreeInit'
								}),
						title : '',
						root : root,
						animate : false,
						renderTo : 'treeDiv',
						width : 650, // 必须指定,否则显示有问题
						//height : 400,
						columns : [{
									header : '部门名称',
									dataIndex : 'text',
									width : 200
								}, {
									header : '部门ID',
									dataIndex : 'id',
									width : 120
								}, {
									header : '父部门ID',
									dataIndex : 'parentid',
									width : 120
								}, {
									header : '自定义ID',
									dataIndex : 'customid',
									width : 60
								}, {
									header : '排序号',
									dataIndex : 'sortno',
									width : 60
								}],
						useArrows : true,
						border : true
					});

			deptTree.on("click", function(node, e) {
						Ext.MessageBox.alert('提示', 'ID:' + node.id + " text:" + node.text);
					});
					
			var firstWindow = new Ext.Window({
						title : '标准范例六(表格树)', // 窗口标题
						layout : 'fit', // 设置窗口布局模式
						width : 650, // 窗口宽度
						height : document.body.clientHeight - 50, // 窗口高度
						closable : false, // 是否可关闭
						collapsible : true, // 是否可收缩
						maximizable : true, // 设置是否可以最大化
						maximized :true, //窗口初始时就最大化
						border : false, // 边框线设置
						constrain : true, // 设置窗口是否可以溢出父容器
						items : [deptTree]
					});
			firstWindow.show(); // 显示窗口
		});