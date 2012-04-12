/**
 * 调色板
 * 
 * @author XiongChun
 * @since 2010-01-13
 */
Ext.onReady(function() {
			var panel = new Ext.Panel({
						frame : true,
						id : 'panel',
						name : 'panel',
						contentEl : 'colorPaletteDiv'
					});

			var window = new Ext.Window({
						layout : 'fit',
						width : 315,
						height : 210,
						resizable : false,
						draggable : true,
						closeAction : 'hide',
						title : '<span class="commoncss">调色板</span>',
						modal : false,
						collapsible : true,
						titleCollapse : false,
						animCollapse : false,
						maximizable : false,
						border : false,
						closable : false,
						animateTarget : Ext.getBody(),
						constrain : true,
						items : [panel]
					});

			window.show();
		});