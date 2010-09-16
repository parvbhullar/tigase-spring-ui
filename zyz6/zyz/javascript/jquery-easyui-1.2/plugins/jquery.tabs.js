/**
 * jQuery EasyUI 1.2
 * 
 * Licensed under the GPL:
 *   http://www.gnu.org/licenses/gpl.txt
 *
 * Copyright 2010 stworthy [ stworthy@gmail.com ] 
 * 
 */
(function($){
function _1(_2){
var _3=$(">div.tabs-header",_2);
var _4=0;
$("ul.tabs li",_3).each(function(){
_4+=$(this).outerWidth(true);
});
var _5=$("div.tabs-wrap",_3).width();
var _6=parseInt($("ul.tabs",_3).css("padding-left"));
return _4-_5+_6;
};
function _7(_8){
var _9=$(">div.tabs-header",_8);
var _a=0;
$("ul.tabs li",_9).each(function(){
_a+=$(this).outerWidth(true);
});
if(_a>_9.width()){
$(".tabs-scroller-left",_9).css("display","block");
$(".tabs-scroller-right",_9).css("display","block");
$(".tabs-wrap",_9).addClass("tabs-scrolling");
if($.boxModel==true){
$(".tabs-wrap",_9).css("left",2);
}else{
$(".tabs-wrap",_9).css("left",0);
}
var _b=_9.width()-$(".tabs-scroller-left",_9).outerWidth()-$(".tabs-scroller-right",_9).outerWidth();
$(".tabs-wrap",_9).width(_b);
}else{
$(".tabs-scroller-left",_9).css("display","none");
$(".tabs-scroller-right",_9).css("display","none");
$(".tabs-wrap",_9).removeClass("tabs-scrolling").scrollLeft(0);
$(".tabs-wrap",_9).width(_9.width());
$(".tabs-wrap",_9).css("left",0);
}
};
function _c(_d){
var _e=$.data(_d,"tabs").options;
var cc=$(_d);
if(_e.fit==true){
var p=cc.parent();
_e.width=p.width();
_e.height=p.height();
}
cc.width(_e.width).height(_e.height);
var _f=$(">div.tabs-header",_d);
if($.boxModel==true){
_f.width(_e.width-(_f.outerWidth()-_f.width()));
}else{
_f.width(_e.width);
}
_7(_d);
var _10=$(">div.tabs-panels",_d);
var _11=_e.height;
if(!isNaN(_11)){
if($.boxModel==true){
var _12=_10.outerHeight()-_10.height();
_10.css("height",(_11-_f.outerHeight()-_12)||"auto");
}else{
_10.css("height",_11-_f.outerHeight());
}
}else{
_10.height("auto");
}
var _13=_e.width;
if(!isNaN(_13)){
if($.boxModel==true){
_10.width(_13-(_10.outerWidth()-_10.width()));
}else{
_10.width(_13);
}
}else{
_10.width("auto");
}
};
function _14(_15){
var _16=$.data(_15,"tabs").options;
var tab=_17(_15);
if(tab){
var _18=$(_15).find(">div.tabs-panels");
var _19=_16.width=="auto"?"auto":_18.width();
var _1a=_16.height=="auto"?"auto":_18.height();
tab.panel("resize",{width:_19,height:_1a});
}
};
function _1b(_1c){
var cc=$(_1c);
cc.addClass("tabs-container");
cc.wrapInner("<div class=\"tabs-panels\"/>");
$("<div class=\"tabs-header\">"+"<div class=\"tabs-scroller-left\"></div>"+"<div class=\"tabs-scroller-right\"></div>"+"<div class=\"tabs-wrap\">"+"<ul class=\"tabs\"></ul>"+"</div>"+"</div>").prependTo(_1c);
var _1d=[];
var _1e=$(">div.tabs-header",_1c);
$(">div.tabs-panels>div",_1c).each(function(){
var pp=$(this);
_1d.push(pp);
_2b(_1c,pp);
});
$(".tabs-scroller-left, .tabs-scroller-right",_1e).hover(function(){
$(this).addClass("tabs-scroller-over");
},function(){
$(this).removeClass("tabs-scroller-over");
});
cc.bind("_resize",function(){
var _1f=$.data(_1c,"tabs").options;
if(_1f.fit==true){
_c(_1c);
_14(_1c);
}
return false;
});
return _1d;
};
function _20(_21){
var _22=$.data(_21,"tabs").options;
var _23=$(">div.tabs-header",_21);
var _24=$(">div.tabs-panels",_21);
if(_22.plain==true){
_23.addClass("tabs-header-plain");
}else{
_23.removeClass("tabs-header-plain");
}
if(_22.border==true){
_23.removeClass("tabs-header-noborder");
_24.removeClass("tabs-panels-noborder");
}else{
_23.addClass("tabs-header-noborder");
_24.addClass("tabs-panels-noborder");
}
$(".tabs-scroller-left",_23).unbind(".tabs").bind("click.tabs",function(){
var _25=$(".tabs-wrap",_23);
var pos=_25.scrollLeft()-_22.scrollIncrement;
_25.animate({scrollLeft:pos},_22.scrollDuration);
});
$(".tabs-scroller-right",_23).unbind(".tabs").bind("click.tabs",function(){
var _26=$(".tabs-wrap",_23);
var pos=Math.min(_26.scrollLeft()+_22.scrollIncrement,_1(_21));
_26.animate({scrollLeft:pos},_22.scrollDuration);
});
var _27=$.data(_21,"tabs").tabs;
for(var i=0,len=_27.length;i<len;i++){
var _28=_27[i];
var tab=_28.panel("options").tab;
var _29=_28.panel("options").title;
tab.unbind(".tabs").bind("click.tabs",{title:_29},function(e){
_39(_21,e.data.title);
});
tab.find("a.tabs-close").unbind(".tabs").bind("click.tabs",{title:_29},function(e){
_2a(_21,e.data.title);
return false;
});
}
};
function _2b(_2c,pp,_2d){
_2d=_2d||{};
pp.panel($.extend({},{selected:pp.attr("selected")=="true"},_2d,{border:false,noheader:true,closed:true,doSize:false,iconCls:(_2d.icon?_2d.icon:undefined),onLoad:function(){
$.data(_2c,"tabs").options.onLoad.call(_2c,pp);
}}));
var _2e=pp.panel("options");
var _2f=$(">div.tabs-header",_2c);
var _30=$("ul.tabs",_2f);
var tab=$("<li></li>").appendTo(_30);
var _31=$("<a href=\"javascript:void(0)\" class=\"tabs-inner\"></a>").appendTo(tab);
var _32=$("<span class=\"tabs-title\"></span>").html(_2e.title).appendTo(_31);
var _33=$("<span class=\"tabs-icon\"></span>").appendTo(_31);
if(_2e.closable){
_32.addClass("tabs-closable");
$("<a href=\"javascript:void(0)\" class=\"tabs-close\"></a>").appendTo(tab);
}
if(_2e.iconCls){
_32.addClass("tabs-with-icon");
_33.addClass(_2e.iconCls);
}
_2e.tab=tab;
};
function _34(_35,_36){
var _37=$.data(_35,"tabs").options;
var _38=$.data(_35,"tabs").tabs;
var pp=$("<div></div>").appendTo($(">div.tabs-panels",_35));
_38.push(pp);
_2b(_35,pp,_36);
_37.onAdd.call(_35,_36.title);
_7(_35);
_20(_35);
_39(_35,_36.title);
};
function _3a(_3b,_3c){
var _3d=$.data(_3b,"tabs").selectHis;
var pp=_3c.tab;
var _3e=pp.panel("options").title;
pp.panel($.extend({},_3c.options,{iconCls:(_3c.options.icon?_3c.options.icon:undefined)}));
var _3f=pp.panel("options");
var tab=_3f.tab;
tab.find("span.tabs-icon").attr("class","tabs-icon");
tab.find("a.tabs-close").remove();
tab.find("span.tabs-title").html(_3f.title);
if(_3f.closable){
tab.find("span.tabs-title").addClass("tabs-closable");
$("<a href=\"javascript:void(0)\" class=\"tabs-close\"></a>").appendTo(tab);
}else{
tab.find("span.tabs-title").removeClass("tabs-closable");
}
if(_3f.iconCls){
tab.find("span.tabs-title").addClass("tabs-with-icon");
tab.find("span.tabs-icon").addClass(_3f.iconCls);
}else{
tab.find("span.tabs-title").removeClass("tabs-with-icon");
}
if(_3e!=_3f.title){
for(var i=0;i<_3d.length;i++){
if(_3d[i]==_3e){
_3d[i]=_3f.title;
}
}
}
_20(_3b);
$.data(_3b,"tabs").options.onUpdate.call(_3b,_3f.title);
};
function _2a(_40,_41){
var _42=$.data(_40,"tabs").options;
var _43=$.data(_40,"tabs").tabs;
var _44=$.data(_40,"tabs").selectHis;
var tab=_45(_40,_41,true);
if(!tab){
return;
}
if(_42.onBeforeClose.call(_40,_41)==false){
return;
}
tab.panel("options").tab.remove();
tab.panel("destroy");
_42.onClose.call(_40,_41);
_7(_40);
for(var i=0;i<_44.length;i++){
if(_44[i]==_41){
_44.splice(i,1);
i--;
}
}
var _46=_44.pop();
if(_46){
_39(_40,_46);
}else{
if(_43.length){
_39(_40,_43[0].panel("options").title);
}
}
};
function _45(_47,_48,_49){
var _4a=$.data(_47,"tabs").tabs;
for(var i=0;i<_4a.length;i++){
var tab=_4a[i];
if(tab.panel("options").title==_48){
if(_49){
_4a.splice(i,1);
}
return tab;
}
}
return null;
};
function _17(_4b){
var _4c=$.data(_4b,"tabs").tabs;
for(var i=0;i<_4c.length;i++){
var tab=_4c[i];
if(tab.panel("options").closed==false){
return tab;
}
}
return null;
};
function _4d(_4e){
var _4f=$.data(_4e,"tabs").tabs;
for(var i=0;i<_4f.length;i++){
var tab=_4f[i];
if(tab.panel("options").selected){
_39(_4e,tab.panel("options").title);
return;
}
}
if(_4f.length){
_39(_4e,_4f[0].panel("options").title);
}
};
function _39(_50,_51){
var _52=$.data(_50,"tabs").options;
var _53=$.data(_50,"tabs").tabs;
var _54=$.data(_50,"tabs").selectHis;
if(_53.length==0){
return;
}
var _55=_45(_50,_51);
if(!_55){
return;
}
var _56=_17(_50);
if(_56){
_56.panel("close");
_56.panel("options").tab.removeClass("tabs-selected");
}
_55.panel("open");
var tab=_55.panel("options").tab;
tab.addClass("tabs-selected");
var _57=$(_50).find(">div.tabs-header div.tabs-wrap");
var _58=tab.position().left+_57.scrollLeft();
var _59=_58-_57.scrollLeft();
var _5a=_59+tab.outerWidth();
if(_59<0||_5a>_57.innerWidth()){
var pos=Math.min(_58-(_57.width()-tab.width())/2,_1(_50));
_57.animate({scrollLeft:pos},_52.scrollDuration);
}else{
var pos=Math.min(_57.scrollLeft(),_1(_50));
_57.animate({scrollLeft:pos},_52.scrollDuration);
}
_14(_50);
_54.push(_51);
_52.onSelect.call(_50,_51);
};
function _5b(_5c,_5d){
return _45(_5c,_5d)!=null;
};
$.fn.tabs=function(_5e,_5f){
if(typeof _5e=="string"){
return $.fn.tabs.methods[_5e](this,_5f);
}
_5e=_5e||{};
return this.each(function(){
var _60=$.data(this,"tabs");
var _61;
if(_60){
_61=$.extend(_60.options,_5e);
_60.options=_61;
}else{
$.data(this,"tabs",{options:$.extend({},$.fn.tabs.defaults,$.fn.tabs.parseOptions(this),_5e),tabs:_1b(this),selectHis:[]});
}
_20(this);
_c(this);
var _62=this;
setTimeout(function(){
_4d(_62);
},0);
});
};
$.fn.tabs.methods={options:function(jq){
return $.data(jq[0],"tabs").options;
},tabs:function(jq){
return $.data(jq[0],"tabs").tabs;
},resize:function(jq){
return jq.each(function(){
_c(this);
_14(this);
});
},add:function(jq,_63){
return jq.each(function(){
_34(this,_63);
});
},close:function(jq,_64){
return jq.each(function(){
_2a(this,_64);
});
},getTab:function(jq,_65){
return _45(jq[0],_65);
},getSelected:function(jq){
return _17(jq[0]);
},select:function(jq,_66){
return jq.each(function(){
_39(this,_66);
});
},exists:function(jq,_67){
return _5b(jq[0],_67);
},update:function(jq,_68){
return jq.each(function(){
_3a(this,_68);
});
}};
$.fn.tabs.parseOptions=function(_69){
var t=$(_69);
return {width:(parseInt(_69.style.width)||undefined),height:(parseInt(_69.style.height)||undefined),fit:(t.attr("fit")?t.attr("fit")=="true":undefined),border:(t.attr("border")?t.attr("border")=="true":undefined),plain:(t.attr("plain")?t.attr("plain")=="true":undefined)};
};
$.fn.tabs.defaults={width:"auto",height:"auto",plain:false,fit:false,border:true,scrollIncrement:100,scrollDuration:400,onLoad:function(_6a){
},onSelect:function(_6b){
},onBeforeClose:function(_6c){
},onClose:function(_6d){
},onAdd:function(_6e){
},onUpdate:function(_6f){
}};
})(jQuery);

