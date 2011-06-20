/**
 * 表单：下拉选择框(远程数据源)
 * 
 * @author XiongChun
 * @since 2010-08-20
 */
Ext.onReady(function() {
			var areaStore = new Ext.data.Store({
						proxy : new Ext.data.HttpProxy({
									url : 'formDemo.ered?reqCode=queryAreaDatas'
								}),
						reader : new Ext.data.JsonReader({}, [{
											name : 'value'
										}, {
											name : 'text'
										}]),
						baseParams : {
							areacode : '53'
						}
					});
			// areaStore.load(); //如果mode : 'local',时候才需要手动load();

			var areaCombo = new Ext.form.ComboBox({
						hiddenName : 'area',
						fieldLabel : '云南行政区域',
						emptyText : '请选择...',
						triggerAction : 'all',
						store : areaStore,
						displayField : 'text',
						valueField : 'value',
						loadingText : '正在加载数据...',
						mode : 'remote', // 数据会自动读取,如果设置为local又调用了store.load()则会读取2次；也可以将其设置为local，然后通过store.load()方法来读取
						forceSelection : true,
						typeAhead : true,
						resizable : true,
						editable : false,
						anchor : '100%'
					});

			var chinaStore = new Ext.data.Store({
						proxy : new Ext.data.HttpProxy({
									url : 'formDemo.ered?reqCode=queryAreaDatas4Paging'
								}),
						reader : new Ext.data.JsonReader({
									totalProperty : 'TOTALCOUNT',
									root : 'ROOT'
								}, [{
											name : 'value'
										}, {
											name : 'text'
										}]),
						baseParams : {
							areacode : ''
						}
					});

			var chinaCombo = new Ext.form.ComboBox({
						hiddenName : 'area',
						fieldLabel : '中国行政区域',
						emptyText : '请选择...',
						triggerAction : 'all',
						store : chinaStore,
						displayField : 'text',
						valueField : 'value',
						loadingText : '正在加载数据...',
						mode : 'remote', // 数据会自动读取,如果设置为local又调用了store.load()则会读取2次；也可以将其设置为local，然后通过store.load()方法来读取
						forceSelection : true,
						typeAhead : true,
						pageSize : 15,
						minListWidth : 270,
						resizable : true,
						editable : false,
						anchor : '100%'
					});
					
			var firstForm = new Ext.form.FormPanel({
						id : 'firstForm',
						name : 'firstForm',
						labelWidth : 80, // 标签宽度
						// frame : true, //是否渲染表单面板背景色
						defaultType : 'textfield', // 表单元素默认类型
						labelAlign : 'right', // 标签对齐方式
						bodyStyle : 'padding:5 5 5 5', // 表单元素和表单面板的边距
						items : [areaCombo, chinaCombo]
					});

			var firstWindow = new Ext.Window({
						title : '下拉选择框(远程数据源)', // 窗口标题
						layout : 'fit', // 设置窗口布局模式
						width : 300, // 窗口宽度
						height : 250, // 窗口高度
						closable : false, // 是否可关闭
						collapsible : true, // 是否可收缩
						maximizable : true, // 设置是否可以最大化
						border : false, // 边框线设置
						constrain : true, // 设置窗口是否可以溢出父容器
						pageY : 20, // 页面定位Y坐标
						pageX : document.body.clientWidth / 2 - 300 / 2, // 页面定位X坐标
						items : [firstForm], // 嵌入的表单面板
						buttons : [{ // 窗口底部按钮配置
							text : '重置', // 按钮文本
							iconCls : 'tbar_synchronizeIcon', // 按钮图标
							handler : function() { // 按钮响应函数
								firstForm.form.reset();
							}
						}]
					});
			firstWindow.show(); // 显示窗口

		});