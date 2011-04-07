function loadFiile(modules)
{
	for(var i=0;i<modules.length; i++)
    {
        if(modules[i].include == true&&modules[i].canloaded) {
        	filename = modules[i].incfile;
       		if(jQuery.browser.safari) {
       			jQuery.ajax({url:filename,dataType:'script', async:false, cache: true});
       		} else {
       			IncludeJavaScript(filename);
       			modules[i].canloaded=false;
       		}
        }
    }
}
    
function IncludeJavaScript(jsFile)
{
        var oHead = document.getElementsByTagName('head')[0];
        var oScript = document.createElement('script');
        oScript.type = 'text/javascript';
        oScript.charset = 'utf-8';
        oScript.src = jsFile;
        oHead.appendChild(oScript);        
};

function initModules(modules)
{
	for(var i=0;i<modules.length; i++)
    {
		modules[i].include = false;
    }
}

function initAccordionMenu(arrMenu){
	$("#accordion").remove();
	var obj=eval(arrMenu);
	var str1="<div id='accordion'><h3><a href='#' class='dcjq-parent'>"+obj[0].menu+"<span class='dcjq-icon'></span></a></h3><div><ul>";
	var str2="";
	for(var i=1;i<obj.length;i++)
	str2+="<li><a href='#' class='dcjq-parent'>"+obj[i].menu+"<span class='dcjq-icon'></span></a></li>";
	var str3="</ul></div>";
	$(".content").html(str1+str2+str3)
}

function countHeight(type){
	var h1=document.documentElement.clientHeight;
	if("list"==type){
		var h2=$("#userMod").css("height").substring(0,($("#tabs").css("height").length-2));
		var h=Number(h1)-450;
		if($.browser.msie){ h=h;}
	}else{ 
		if("form"==type){
			var h2=$("#userMod").css("height").substring(0,($("#tabs").css("height").length-2));
			var h=Number(h1)-450;
			if($.browser.msie){ h=h+4;}
		}
	}
	//innerLayout.sizePane('south', (h));innerLayout.open('south');
}



//自动关闭提示框
function blockUI(message,second)
{
	if(1==second)
	{
		alert(message);
	}else if(2==second){
		confirm(message);  
	}
}

function loaDing(message){
	$.blockUI({
		message: message,
		css: { 
        border: 'none', 
        padding: '15px', 
        backgroundColor: '#000', 
        '-webkit-border-radius': '10px', 
        '-moz-border-radius': '10px', 
        opacity: .5, 
        color: '#fff' 
    } }); 
	//if(second!=0)
		//setTimeout($.unblockUI, 2000);
}


