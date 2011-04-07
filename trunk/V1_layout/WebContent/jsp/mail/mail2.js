$(document).ready( function() {
	$.ajax({
		   type: "POST",
		   url: "loginAuth",
		   success: function(msg){
		     	var array=eval("json"+msg).options;
				for(var i=0; i<eval(array).length; i++)
				{
						  $("#mainContent ul:first").append('<li><a href="#tabs-'+(i+1)+'">'+eval(array)[i].modname+'</a></li>')
				}
				eval("load"+eval(array)[0].mod+"("+msg+",0)");
				$(".ui-accordion-content-active").css("padding",0);
					if(!arrModules[msg][0].include){
						var vartab=$( "#tabs" ).tabs({
								select: function(event, ui) {
									if ( $("#tabs-"+(ui.index+1)+" #loop").css("display")=="inline-block"){
										innerLayout.open('south');
										$("#mainContent .ui-layout-south").html("");
									}else{
										innerLayout.close('south');
									}
									if(!arrModules[msg][ui.index].include)
									{
										eval("load"+eval(array)[ui.index].mod+"("+msg+","+ui.index+")");
									}
									else{
										initAccordionMenu(arrMenu[msg][ui.index])
										initAccordion();
										initAccordionFun(msg,ui.index);
									}
									$(".ui-accordion-content-active").css("padding",0);
							}
					    }
					);
					$("#personal").click(function(){
						$("#tabs ul li:last a").click();
					})	
				}
		   },
		   complete:function(){
		   }
	});
	
	(function(webim){
		var _IMC = {
				production_name: 'dz',
				version: '1.0',
				path: '<?php echo $webim_path; ?>',
				is_login: "0",
				login_options: "",
				user: "",
				setting: "",
				menu: "",
				disable_chatlink: "1",
				enable_shortcut:"1",
				disable_menu: "1",
				theme: "",
				local: "zh-CN",
				jsonp: "",
				min: ".min"
				};
		
	    var path = "/";
	    //webim.extend( webim.setting.defaults.data, _IMC.setting );
	    var webim = window.webim;
	    webim.defaults.urls = {
	        online:"mail?action=online",
	        offline:"mail?action=offline",
	        message:"mail?action=message",
	        presence:"mail?action=presence",
	        refresh:"mail?action=refresh",
	        status:"mail?action=status"
	        
	    };
	    webim.setting.defaults.url = "mail?action=setting";
	    webim.history.defaults.urls = {
	        load: "mail?action=history",
	        clear: "mail?action=clear_history",
	        download: "mail?action=download_history"
	    };
	    webim.room.defaults.urls = {
	        member: "mail?action=members",
	        join: "mail?action=join",
	        leave: "mail?action=leave"
	    };
	    webim.buddy.defaults.url = "mail?action=buddies";
	    webim.notification.defaults.url =  "mail?action=notifications";

	    webim.ui.emot.init({"/":  + "/js/im/static/images/emot/default"});
	    var soundUrls = {
	        lib: "/js/im/static/assets/sound.swf",
	        msg: "/js/im/static/assets/sound/msg.mp3"
	    };
	    var ui = new webim.ui(document.body, {
	        imOptions: {
	            jsonp: _IMC.jsonp
	        },
	        soundUrls: soundUrls
	    }), im = ui.im;

	    if( _IMC.user ) im.user( _IMC.user );
	    if( _IMC.menu ) ui.addApp("menu", { "data": _IMC.menu } );
	    if( _IMC.enable_shortcut ) ui.layout.addShortcut( _IMC.menu );

	    ui.addApp("buddy", {
	        is_login: _IMC['is_login'],
	        loginOptions: _IMC['login_options']
	    } );
	    ui.addApp("room");
	    ui.addApp("notification");
	    ui.addApp("setting", {"data": webim.setting.defaults.data});
	    if( !_IMC.disable_chatlink )ui.addApp("chatlink", {
	        off_link_class: /r_option|spacelink/i
	    });
	    ui.render();
	    _IMC['is_login'] && im.autoOnline() && im.online();
	})(webim);
})

//加载系统管理模块
function loadsys(authIndex,index){
	$("#accordion").remove();
	
	$(".content").html("<div id='accordion'><h3><a href='#'>系统管理</a></h3><div><ul><li><a href='#'>机构管理</a></li></ul></div>");
	initAccordion();
	initAccordionFun(authIndex,index);
	$.ajax({
		  url: 'jsp/system/org.jsp',
		  success: function(data) {
		    $("#tabs-"+(index+1)).html(data);
		  },
		  complete:function(){
		  }
	});
}

//加载管理平台
function loadorgsys(authIndex,index){
	$("#accordion").remove();
	$(".content").html("<div id='accordion'><h3><a href='#' class='dcjq-parent'>管理平台<span class='dcjq-icon'></span></a></h3><div><ul><li><a href='#' class='dcjq-parent'>人员管理<span class='dcjq-icon'></span></a></li></ul></div>");
	initAccordion();
	initAccordionFun(authIndex,index);
	$.ajax({
		  url: 'jsp/system/user.jsp',
		  success: function(data) {
		    $("#tabs-"+(index+1)).html(data);
		  },
		  complete:function(){
			  arrModules[authIndex][0].include=true;	//加载用户模块
			  arrModules[authIndex][0].canloaded=true;	//加载用户模块
			  loadFiile(arrModules[authIndex]);
		  }
	});
}

//加载用户
function loaduser(authIndex,index){
	$("#accordion").remove();
	$(".content").html("<div id='accordion'><h3><a href='#' class='dcjq-parent'>管理平台<span class='dcjq-icon'></span></a></h3><div><ul><li><a href='#' class='dcjq-parent'>人员管理<span class='dcjq-icon'></span></a></li></ul></div>");
	initAccordion();
	initAccordionFun(authIndex,index);
	$.ajax({
		  url: 'jsp/system/user.jsp',
		  success: function(data) {
		    $("#tabs-"+(index+1)).html(data);
		  },
		  complete:function(){}
	});
}


//加载会议模块
function loadmeeting(authIndex,index){
	
	//加载会议模块
	$("#accordion").remove();
	$(".content").html("<div id='accordion'><h3><a href='#' class='dcjq-parent'>会议<span class='dcjq-icon'></span></a></h3><div><ul><li><a href='#' class='dcjq-parent'>会议列表<span class='dcjq-icon'></span></a></li></ul></div>");
	initAccordion();
	initAccordionFun(authIndex,index);
	$.ajax({
		  url: 'jsp/meeting/invitedmeeting.jsp',
		  success: function(data) {
		    $("#tabs-"+(index+1)).html(data);
		  },
		  complete:function(){
			  arrModules[authIndex][index].include=true;	//加载会议模块
			  arrModules[authIndex][index].canloaded=true;	//加载会议模块
			  loadFiile(arrModules[authIndex]);
			  $("#mainContent .ui-layout-south").html("");
		  }
	});
}

//加载短消息模块
function loadmessage(authIndex,index){
	$("#accordion").remove();
}

//加载个人设置模块
function loadpersonal(authIndex,index){
	$("#accordion").remove();
	$(".content").html("<div id='accordion'><h3><a href='#' class='dcjq-parent'>个人设置<span class='dcjq-icon'></span></a></h3><div><ul><li><a href='#' class='dcjq-parent'>个人信息<span class='dcjq-icon'></span></a></li><li><a href='#' class='dcjq-parent'>修改密码<span class='dcjq-icon'></span></a></li></ul></div>");
	initAccordion();
	initAccordionFun(authIndex,index);
	$.ajax({
		  url: 'jsp/personal/personalinfo.jsp',
		  success: function(data) {
		    $("#tabs-"+(index+1)).html(data);
		  },
		  complete:function(){
			  arrModules[authIndex][index].include=true;	//加载个人设置模块
			  arrModules[authIndex][index].canloaded=true;	//加载个人设置模块
			  loadFiile(arrModules[authIndex]);
		  }
	});
}

/*初始化会议列表*/
function initMeeting(authIndex,index){

	$.ajax({
		  url: 'jsp/meeting/invitedmeeting.jsp',
		  success: function(data) {
		    $("#tabs-"+(index+1)).html(data);
		  },
		  complete:function(){
			  arrModules[2][0].include=true;	//加载会议模块
			  arrModules[2][0].canloaded=true;	//加载会议模块
			  loadFiile(arrModules[2]);
		  }
	});
}

/*初始个人信息*/
function initPersonalinfo(authIndex,index){
	$.ajax({
		  url: 'jsp/personal/personalinfo.jsp',
		  success: function(data) {
		    $("#tabs-"+(index+1)).html(data);
		  },
		  complete:function(){
			  arrModules[authIndex][index].include=true;	//加载个人信息模块
			  arrModules[authIndex][index].canloaded=true;	//加载个人信息模块
			  loadFiile(arrModules[authIndex]);
		  }
	});
}



//初始化折叠框
function initAccordion(){
	var icons = {
			header: "ui-icon-circle-arrow-e",
			headerSelected: "ui-icon-circle-arrow-s"
		};
	$( "#accordion" ).show();
	$( "#accordion" ).accordion();
	$( "#accordion" ).accordion( "option", "autoHeight", true );
	$( "#accordion" ).accordion({ clearStyle: true });
	$( "#accordion" ).accordion( "option", "icons", icons );
	$( "#accordion" ).accordion( "option", "collapsible", true );
}

//初始化折叠框内点击函数
function initAccordionFun(authIndex,index){
	if(0==authIndex)
	{
		$("#accordion div li:first").bind(
				'click', function() {
						initOrg();
					}
		);
		
	}else if(1==authIndex){
		if(0==index){
			$("#accordion div li:first").bind(
					'click', function() {
						initUser(authIndex);
						}
			);
		}else if(1==index){
			$("#accordion div li:first").bind(
					'click', function() {
						initMeeting(authIndex);
						}
			);
		}
	}else
	if(2==authIndex)
	{
		if(0==index){
			$("#accordion div li:first").bind(
					'click', function() {
						initMeeting(authIndex,index);
					});
		}else if(1==index){
			
		}else if(2==index){
			$("#accordion div li:first").bind(
					'click', function() {
						initPersonalinfo(authIndex,index);
					});
		}
	}
}

//分列表和添删改查两种情况初始化按钮
function initInitNavButton(buttonNavType){
	if(0==buttonNavType){//列表
		$("#buttonDeleteMeeting").button({
			text: "删除"
		}).click(function() {
			alert("会议删除方法");
		});
		
		$("#buttonRefreshMeeting").button({
			text: "刷新"
		}).click(function() {
			alert("会议刷新方法");
		});
		$("#button-curd").hide();
		$("#button-list").show();
		
	}
	else{
		
		
		$("#buttonSaveMeeting").button({
			text: "保存"
		}).click(function() {
			

			$.post("MeetingServlet?action=addReserveMeeting", $("#meetingForm").serialize(),
					function (data, textStatus){	
						var result = data.result;
						var reason = data.reason;
						if(result == "success"){
							$( "#addReserveMeetingDiv" ).dialog( "close" );
							$("#meetingList").trigger("reloadGrid");//刷新表格
						}else{
							alert(reason);
						}
					}, 
					"json");
			
		});
		
		$("#buttonCancelMeeting").button({
			text: "取消"
		}).click(function() {
			alert("会议取消方法");
		});
		$("#button-list").hide();
		$("#button-curd").show();
	}
}

//初始化内容区域分为CRUD和LIST两种形式
function initContent(buttonNavType){
	if(0==buttonNavType){//列表
		$("#meetingForm").hide();
		$("#meetingListId").show();
		innerLayout.sizePane('south', 25);innerLayout.open('south');
	}
	else{
		innerLayout.hide('south');
		$("#meetingListId").hide();
		$("#meetingForm").show();
	}

	
}

//初始化收件箱按钮及列表
function initReceivebox(){
	$.ajax({
		  url: 'jsp/mail/receivebox.html',
		  success: function(data) {
		    $("#tabs-3").html(data);
		  },
		  complete:function(){
			  modules[8].include=true;	//加载已发送模块
			  modules[8].canloaded=true;//加载已发送模块
			  //loadFiile(modules);
		  }
	});
}

//初始化已发送邮箱按钮及列表
function initSentbox(){
	$.ajax({
		  url: 'jsp/mail/sentbox.html',
		  success: function(data) {
		    $("#tabs-3").html(data);
		  },
		  complete:function(){
			  modules[8].include=true;	//加载已发送模块
			  modules[8].canloaded=true;//加载已发送模块
			  loadFiile(modules);
		  }
	});

	
}

//初始化草稿邮箱按钮及列表
function initDraftbox(){
	$.ajax({
		  url: 'jsp/mail/draftbox.html',
		  success: function(data) {
		    $("#tabs-3").html(data);
		  },
		  complete:function(){
			  modules[7].include=true;//加载草稿箱模块
			  modules[7].canloaded=true;//加载草稿箱模块
			  loadFiile(modules);
		  }
	});

	
}

//初始化已删除邮箱按钮及列表
function initDeletedtbox(){
	$.ajax({
		  url: 'jsp/mail/deleted.html',
		  success: function(data) {
		    $("#tabs-3").html(data);
		  },
		  complete:function(){
			  modules[9].include=true;//加载已删除模块
			  modules[9].canloaded=true;//加载已删除模块
			  loadFiile(modules);
		  }
	});
}

function initOrg(){
	$.ajax({
		  url: 'jsp/system/org.jsp',
		  success: function(data) {
		    $("#tabs-1").html(data);
		  }
	});
	
}

function initUser(authIndex){
	$.ajax({
		  url: 'jsp/system/user.jsp',
		  success: function(data) {
		    $("#tabs-1").html(data);
		  },
		  complete:function(){
			  arrModules[authIndex][0].include=true;	//加载用户模块
			  arrModules[authIndex][0].canloaded=true;	//加载用户模块
			  loadFiile(arrModules[authIndex]);
		  }
	});
	
}

function initAuth(){
	$.ajax({
		  url: 'jsp/system/auth.jsp',
		  success: function(data) {
		    $("#tabs-1").html(data);
		  }
	});
	
}
