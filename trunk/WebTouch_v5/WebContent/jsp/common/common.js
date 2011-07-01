/**
 * 
 */
$(document).ready( function() {
	$("#listDiv").jqGrid({ 
		url:'mail/mailReceive.do', 
		datatype: 'json', 
		mtype: 'post', 
		colNames:['id','材料费','包仓费','标签费', '仓储费','打托费','加固费'], 
		colModel :[  
		    {name:'id', 		index:'id', 			hidden:true},
		    {name:'state', 		index:'state', 			width:50,align:"center"},
			{name:'sendname', 	index:'sendname', 		width:150,align:"left"},  
			{name:'subject', 	index:'subject', 		width:300,align:"left"},  
			{name:'datetime', 	index:'datetime', 		width:100,align:"center"}, 
			{name:'filename',	index:'filename', 		width:50 ,align:"center"},
			{name:'name',		index:'name', 			width:50 ,align:"center",hidden:true}
			], 
		pager: '#page', 
		rowNum:10,
		autowidth:true,
		height:320,
		loadtext: '正在加载...',
		loadui : 'block',
		multiselect : true,
		rowList:[10], 
		sortname: 'id', 
		sortorder: "desc",
		recordtext: "显示记录从{0}到{1},总数 {2} 条",		//显示记录数的格式
		emptyrecords: "无数据",							//空记录时的提示信息
		pgtext : "第 {0}页 总页数 {1}",					//页数显示格式
		jsonReader:{
            repeatitems : false
    	},
		loadComplete:function(){
			$("#listDiv tr").each(function()
			{
						var state=$('td:eq(2)',this).html();
						if(0==state)
						{
							$('td:eq(2)',this).html("");
							$('td:eq(2)',this).append('<img title="未读" src="images/mail/unRd3.gif"');
						}else
						{
							if(1==state)
							{
								$('td:eq(2)',this).html("");
							}
							else
							{
								if(2==state)
								{
									$('td:eq(2)',this).html("");
									$('td:eq(2)',this).append('<img title="回复" src="images/mail/replied3.gif"');
								}
							}
						}
						var filename=$('td:eq(6)',this).html();
						if("&nbsp;"!=filename)
						{
							$('td:eq(6)',this).html("");
							$('td:eq(6)',this).append('<img title="附件" src="images/mail/atchm.gif"');
						}
						var id=$('td:eq(1)',this).html();
						var name=$('td:eq(7)',this).html();
						$('td:not(:eq(0))',this).click(function(){
							//查看信件
							viewMail("#dialogViewMail",id,name,"/mail/receiveDetail.do");
							
							return false;
						});
			});
			$(".ui-pg-table").find("select").hide();
			var str=$("#page_right div").html();
			$("#mailCount").html("");
			$("#mailCount").html("<h4 style='display: inline;'>收件箱</h4>(共 "+str.substring(str.indexOf("of")+3)+" 封)");
		},
		viewrecords: true 
 	 });
})
