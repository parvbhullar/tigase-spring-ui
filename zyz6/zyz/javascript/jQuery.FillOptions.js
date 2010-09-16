/*
* jQuery FillOptions
*
* Author: luq885
* http://blog.csdn.net/luq885 (chinese) 
*
* Licensed like jQuery, see http://docs.jquery.com/License
*
* 作者：天天无用
* blog: http://blog.csdn.net/luq885
*/

var text;
var value;
var type;
var selected;
var keep;

jQuery.fn.FillOptions = function(url,options){
    if(url.length == 0) throw "request is required";        
    text = options.textfield || "text";
    value = options.valuefiled || "value";    
    type = options.datatype.toLowerCase() || "json";
    if(type != "xml")type="json";
    keep = options.keepold?true:false;
    selected = options.selectedindex || 0;
    
    $.ajaxSetup({async:false});
    var datas;
    if(type == "xml")
    {
        $.get(url,function(xml){datas=xml;});            
    }
    else
    {
        $.getJSON(url,function(json){datas=json;});
    }
    if(datas == undefined)
    {
		alert("no datas");
		return;
	}
    this.each(function(){
        if(this.tagName == "SELECT")
        {
            var select = this;
            if(!keep)$(select).html("");
            addOptions(select,datas);
        }
    });
}


function addOptions(select,datas)
{        
    var options;
    var datas;
    if(type == "xml")
    {
        $(text,datas).each(function(i){            
            option = new Option($(this).text(),$($(value,datas)[i]).text());
            if(i==selected)option.selected=true;
            select.options.add(option);
        });
    }
    else
    {
        $.each(datas,function(i,n){
            option = new Option(eval("n."+text),eval("n."+value));
            if(i==selected)option.selected=true;
            select.options.add(option);
        });
    }
}