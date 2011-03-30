//需要动态加载的文件,为true 的模块加载
var modules = [
				       { include: false, incfile:'jsp/mail/mail2.js',canloaded:true},
				       { include: false, incfile:'js/ckeditor/ckeditor.js',canloaded:true}, 
				       { include: false, incfile:'jsp/mail/draftbox.js',canloaded:true}, 
				       { include: false, incfile:'jsp/mail/sentbox.js',canloaded:true},
				       { include: false, incfile:'jsp/mail/deleted.js',canloaded:true},
				       { include: false, incfile:'jsp/meeting/meeting.js',canloaded:true},
				       { include: false, incfile:'jsp/system/sysindex.js',canloaded:true},
				       { include: false, incfile:'jsp/mail/draftbox.js',canloaded:true},
				       { include: false, incfile:'jsp/mail/sentbox.js',canloaded:true},
				       { include: false, incfile:'jsp/mail/deleted.js',canloaded:true}
				    ];

var modules2= [
		       { include: false, incfile:'jsp/meeting/meet.js',canloaded:true}
		    ];
var arrModules = new Array();
arrModules[0]=new Array();
arrModules[1]=new Array();
arrModules[2]=new Array();

arrModules[0] = [
                 { include: false, incfile:'jsp/meeting/invitedmeeting2.js',canloaded:true}
	     ];

arrModules[1] = [
                 { include: false, incfile:'jsp/system/user.js',canloaded:true},
                 { include: false, incfile:'jsp/meeting/invitedmeeting.js',canloaded:true},
                 { include: false, incfile:'jsp/personal/personalinfo.js',canloaded:true}
      	     ];

arrModules[2] = [
                 { include: false, incfile:'jsp/meeting/invitedmeeting.js',canloaded:true},
                 { include: false, incfile:'jsp/personal/personalinfo.js',canloaded:true},
                 { include: false, incfile:'jsp/personal/personalinfo.js',canloaded:true}
      	     ];

var arrMenu=new Array();
arrMenu[0]=new Array();
arrMenu[1]=new Array();
arrMenu[2]=new Array();

arrMenu[0][0]=[
               {menu:"系统管理"},
               {menu:"机构管理"}
               ];

arrMenu[1][0]=[
               {menu:"管理平台"},
               {menu:"人员管理"}
               ];
arrMenu[1][1]=[
               {menu:"会议"},
               {menu:"会议列表"}
               ];
arrMenu[1][2]=[
               {menu:"个人设置"},
               {menu:"个人信息"},
               {menu:"修改密码"}
               ];

arrMenu[2][0]=[
               {menu:"会议"},
               {menu:"会议列表"}
               ];
arrMenu[2][1]=[
               {menu:"个人设置"},
               {menu:"个人信息"},
               {menu:"修改密码"}
               ];
               



/*加上输入table的样式开始*/
/*
		(function ($) {
			var oHead = document.getElementsByTagName('head')[0];
			var oScript = document.createElement('script');
			oScript.type = 'text/javascript';
			
			
	        $.fn.styleTable = function (options) {
	        	$.validator.setDefaults({
	    			submitHandler: function() {  },
	    			highlight: function(input) {
	    				$(input).addClass("ui-state-highlight");
	    			},
	    			unhighlight: function(input) {
	    				$(input).removeClass("ui-state-highlight");
	    			}
	    		});
	        	
	            var defaults = {
	                css: 'styleTable'
	            };
	            options = $.extend(defaults, options);

	            return this.each(function () {
	            	
	                input = $(this);
	                input.addClass(options.css);
	                input.find("th").addClass("ui-state-default");
	                input.find("td").addClass("ui-widget-content");
	                input.find("tr").each(function () {
	                    $(this).children("td:not(:first)").addClass("first");
	                    $(this).children("th:not(:first)").addClass("first");
	                });
	            });
	        };
	        
	    })(jQuery);   
	    */
		
	$(document).ready( function() {
		//加载TAB模块
		
		modules[0].include=true;
		modules[0].canloaded=true;
		$.ajax({
			  url: 'index.html',
			  beforeSend:function(){
					blockUI('<h1><img src="images/index/busy.gif" /> 加载中...</h1>',0);
				},
			  success: function(data) {
			    $('#tabs').html(data);
			    loadFiile(modules);
			    	$(".ui-layout-content").css("padding","0px");
			    	$(".ui-layout-content").css("overflow","hidden");
			  }
		});
	});	