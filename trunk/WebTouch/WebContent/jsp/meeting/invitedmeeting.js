var isvalid;
$(document).ready( function() {
		//initButton("list");
		comInitButton("list");
		//intiButtonClickFun("list");
		comIntiButtonClickFun("list");
		//initContent("#meeting","list");
		comInitContent("#meeting","list");
		//initContentFunc("#meeting","list");
		comInitContentFunc("#meeting","list");
		validateMeetingForm();
		//未开时会议初始化
	});	
	
	//加载列表
	function loadMeetingModList(){
		var varmaillist=$("#meetingList").jqGrid({ 
			url:'MyMeetingServlet?action=MyMeetingListNot', 
			datatype: 'json', 
			mtype: 'POST', 
			colNames:['confkey','会议类型','开始时间','主题','主持人', '持续时间(分钟)','会议状态','操作'], 
			colModel :[  
			    {name:'confKey', 	index:'confKey', 		hidden:true},
			    {name:'confPattern',index:'confPattern', 	width:100,align:"left", 	sortable:false},
			    {name:'startTime', 	index:'startTime', 		width:200,align:"center", 	sortable:false},
				{name:'subject', 	index:'subject', 		width:200,align:"left",		sortable:false},  
				{name:'host', 		index:'host', 			width:100,align:"left", 	sortable:false},  
				{name:'duringTime', index:'duringTime', 	width:100,align:"right", 	sortable:false}, 
				{name:'status', 	index:'status', 		width:100,align:"center", 	sortable:false}, 
				{name:'operation',	index:'operation', 		width:50 ,align:"center", 	sortable:false},
				], 
			pager: "#page",
			rowNum:10,
			autowidth:true,
			height:260,
//			height:320,
//			caption: '未开始的会议',
			loadtext: '正在加载...',
			loadui : 'block',
			multiselect : false,
			//rowList:[10], 
			sortname: 'confkey', 
			recordtext: "记录 {0} - {1} of {2}",//显示记录数的格式
			emptyrecords: "无数据",//空记录时的提示信息
			pgtext : "第 {0}页 总页数 {1}",//页数显示格式
			jsonReader:{
                repeatitems : false
        	},
			loadComplete:function(){
				$("#notstartmeetingList td").css("border-left","0px");
				$("#notstartmeetingList td").css("border-right","0px");
				$("#notstartmeetingList .ui-jqgrid-view").css("border","0px");
				$("#notstartmeetingList tr").each(function()
				{
							var confType=$('td:eq(1)',this).html();
							var id = $('td:eq(0)',this).html();
							//设置点击进入会议键的事件
							$('td:eq(7)',this).click(function(){
								$("#startMeetingForm").reset;//清除之前的填写的信息
								$( "#startMeetingDiv" ).dialog( "open" );
								$.ajax({
									  url: 'MeetingServlet?action=startMeetingDlg&id='+id,
									  success: function(data) {
										var obj = eval('(' + data + ')');
										var subject = obj.subject;
										var agenda = obj.agenda;
										var startTime = obj.startTime;
										var endTime = obj.endTime;
										var confPattern = obj.confPattern;
										var confType = obj.confType;
										var attendeeNum = obj.attendeeNum;
										var during = obj.during;
										var host = obj.hostName;
										var confKey = obj.confKey;
										
										$('#startMeetingForm td[name$="subject"]').html(subject);
										$('#startMeetingForm TEXTAREA[name$="decsr"]').val(agenda);
										$('#startMeetingForm td[name$="confKey"]').html(id);
										$('#startMeetingForm td[name$="confType"]').html(confType);
										$('#startMeetingForm input[name$="host"]').val(host);
										$('#startMeetingForm td[name$="startTime"]').html(startTime);
										$('#startMeetingForm td[name$="endTime"]').html(endTime);
										$('#startMeetingForm td[name$="duration"]').html(during);
										$('#startMeetingForm td[name$="attendeeNum"]').html(attendeeNum);
										$('#startMeetingForm td[name$="confPattern"]').html(confPattern);
										$('#startMeetingForm input[name$="confK"]').val(confKey);
									  }
									});
								return false;
							});
				});
			},
			viewrecords: true 
	 	 });
		$("#listDiv").css("padding","10px");
	}

function validateMeetingForm(){
	$("#meetingForm").validate({
		rules: {
			subject: "required"
		},
		messages: {
			subject: "请输入主题"
		},

		errorPlacement: function(error, element) {
			if ( element.is(":radio") )
				error.appendTo( element.parent().next().next() );
			else if ( element.is(":checkbox") )
				error.appendTo ( element.next() );
			else
				error.appendTo( element.parent().next() );
			isvalid=false;
		},
		success: function(label) {
			label.html("&nbsp;").addClass("checked");
			isvalid=true;
		}
	});
}

function showRequest(formData, jqForm, options) {
	alert("showRequest");
	alert(isvalid);
	
} 

function showResponse(responseText, statusText, xhr, $form)  {
	alert("showResponse");
	if(isvalid==true)
	{}
} 


//按钮函数 	分成form和list两种状态
function comIntiButtonClickFun(type)
{
	if(type=="form"){
		$("#meeting #check").click(function() {
			var options = { 
			        beforeSubmit:  showRequest, 
			        success:       showResponse  // post-submit callback 
			    }; 
			    $('#meetingForm').submit(function() {
			        $(this).ajaxSubmit(options); 
			        return false; 
			    }); 
		});
		$("#meeting #reload").click(function() {
			  comInitButton("list");
			  comInitContent("#meeting","list");
			  //comInitContentFunc("#meeting","list");
			});
		
	}else{
		$("#meeting #plus").click(function() {
			  $(function(){					
					$('#tokeninput3').tokeninput({
						type :'1',
						url : 'MeetingServlet?action=getAttendee',//获得数据的Servlet
						initDataFromId	:'token-initdata',
						preAddFromId	:'token-preadd'					
					});					
				});
			  comInitButton("form");
			  comIntiButtonClickFun("form");
			  $("#meeting #listDiv").hide();
			  $("#meeting #curdForm").show();
			  innerLayout.hide('south');
			});
		/*
		$("#meeting #trash").click(function() {
			  alert('trash');
			});
		*/
		$("#meeting #loop").click(function() {
			});
	}
}

//按钮显示  	分成form和list两种状态
function comInitButton(type)
{
	if(type=="form"){
		$("#meeting #plus").hide();
		//$("#meeting #trash").hide();
		$("#meeting #loop").hide();
		
		$("#meeting #check").show();
		$("#meeting #reload").show();	
	}else{
		$("#meeting #plus").show();
		//$("#meeting #trash").show();
		$("#meeting #loop").show();
		
		$("#meeting #check").hide();
		$("#meeting #reload").hide();
	}
}



//初始化内容区域 显示与否 分成form和list两种状态
function comInitContent(parentdiv,type){
	if(type=="form"){
		$(parentdiv+" #curdForm").show();
		$(parentdiv+" #listDiv").hide();
	}else{
		$(parentdiv+" #curdForm").hide();
		innerLayout.show('south');
		$(parentdiv+" #listDiv").show();
		$(parentdiv+" #listDiv").show();
	}
}

//初始化内容区域的数据  分成form和list两种状态
function comInitContentFunc(parentdiv,type){
	if(type=="form"){
		
	}else{
		loadMeetingModList();
		countHeight();
	}
}