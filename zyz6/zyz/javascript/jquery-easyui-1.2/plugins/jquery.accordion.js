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
var _3=$.data(_2,"accordion").options;
var _4=$.data(_2,"accordion").panels;
var cc=$(_2);
if(_3.fit==true){
var p=cc.parent();
_3.width=p.width();
_3.height=p.height();
}
if(_3.width>0){
cc.width($.boxModel==true?(_3.width-(cc.outerWidth()-cc.width())):_3.width);
}
var _5="auto";
if(_3.height>0){
cc.height($.boxModel==true?(_3.height-(cc.outerHeight()-cc.height())):_3.height);
var _6=_4.length?_4[0].panel("header").css("height",null).outerHeight():"auto";
var _5=cc.height()-(_4.length-1)*_6;
}
for(var i=0;i<_4.length;i++){
var _7=_4[i];
var _8=_7.panel("header");
_8.height($.boxModel==true?(_6-(_8.outerHeight()-_8.height())):_6);
_7.panel("resize",{width:cc.width(),height:_5});
}
};
function _9(_a){
var _b=$.data(_a,"accordion").panels;
for(var i=0;i<_b.length;i++){
var _c=_b[i];
if(_c.panel("options").collapsed==false){
return _c;
}
}
return null;
};
function _d(_e,_f,_10){
var _11=$.data(_e,"accordion").panels;
for(var i=0;i<_11.length;i++){
var _12=_11[i];
if(_12.panel("options").title==_f){
if(_10){
_11.splice(i,1);
}
return _12;
}
}
return null;
};
function _13(_14){
var cc=$(_14);
cc.addClass("accordion");
if(cc.attr("border")=="false"){
cc.addClass("accordion-noborder");
}else{
cc.removeClass("accordion-noborder");
}
if(cc.find(">div[selected=true]").length==0){
cc.find(">div:first").attr("selected","true");
}
var _15=[];
cc.find(">div").each(function(){
var pp=$(this);
_15.push(pp);
_17(_14,pp,{});
});
cc.bind("_resize",function(){
var _16=$.data(_14,"accordion").options;
if(_16.fit==true){
_1(_14);
}
return false;
});
return {accordion:cc,panels:_15};
};
function _17(_18,pp,_19){
pp.panel($.extend({},_19,{collapsible:false,minimizable:false,maximizable:false,closable:false,doSize:false,collapsed:pp.attr("selected")!="true",tools:[{iconCls:"panel-tool-collapse",handler:function(){
var _1a=$.data(_18,"accordion").options.animate;
if(pp.panel("options").collapsed){
pp.panel("expand",_1a);
}else{
pp.panel("collapse",_1a);
}
return false;
}}],onBeforeExpand:function(){
var _1b=_9(_18);
if(_1b){
var _1c=$(_1b).panel("header");
_1c.removeClass("accordion-header-selected");
_1c.find(".panel-tool-collapse").triggerHandler("click");
}
pp.panel("header").addClass("accordion-header-selected");
},onExpand:function(){
if($.parser){
$.parser.parse(pp.panel("body"));
}
pp.panel("body").find(">div").triggerHandler("_resize");
var _1d=$.data(_18,"accordion").options;
_1d.onSelect.call(_18,pp.panel("options").title);
},onBeforeCollapse:function(){
pp.panel("header").removeClass("accordion-header-selected");
}}));
pp.panel("body").addClass("accordion-body");
pp.panel("header").addClass("accordion-header").click(function(){
$(this).find(".panel-tool-collapse").triggerHandler("click");
return false;
});
};
function _1e(_1f,_20){
var _21=$.data(_1f,"accordion").options;
var _22=$.data(_1f,"accordion").panels;
var _23=_9(_1f);
if(_23&&_23.panel("options").title==_20){
return;
}
var _24=_d(_1f,_20);
if(_24){
_24.panel("header").triggerHandler("click");
}else{
if(_23){
_23.panel("header").addClass("accordion-header-selected");
_21.onSelect.call(_1f,_23.panel("options").title);
}
}
};
function add(_25,_26){
var _27=$.data(_25,"accordion").options;
var _28=$.data(_25,"accordion").panels;
var pp=$("<div></div>").appendTo(_25);
_28.push(pp);
_17(_25,pp,_26);
_1(_25);
_27.onAdd.call(_25,_26.title);
_1e(_25,_26.title);
};
function _29(_2a,_2b){
var _2c=$.data(_2a,"accordion").options;
var _2d=$.data(_2a,"accordion").panels;
if(_2c.onBeforeRemove.call(_2a,_2b)==false){
return;
}
var _2e=_d(_2a,_2b,true);
if(_2e){
_2e.panel("destroy");
if(_2d.length){
_1(_2a);
var _2f=_9(_2a);
if(!_2f){
_1e(_2a,_2d[0].panel("options").title);
}
}
}
_2c.onRemove.call(_2a,_2b);
};
$.fn.accordion=function(_30,_31){
if(typeof _30=="string"){
return $.fn.accordion.methods[_30](this,_31);
}
_30=_30||{};
return this.each(function(){
var _32=$.data(this,"accordion");
var _33;
if(_32){
_33=$.extend(_32.options,_30);
_32.opts=_33;
}else{
_33=$.extend({},$.fn.accordion.defaults,$.fn.accordion.parseOptions(this),_30);
var r=_13(this);
$.data(this,"accordion",{options:_33,accordion:r.accordion,panels:r.panels});
}
_1(this);
_1e(this);
});
};
$.fn.accordion.methods={options:function(jq){
return $.data(jq[0],"accordion").options;
},panels:function(jq){
return $.data(jq[0],"accordion").panels;
},resize:function(jq){
return jq.each(function(){
_1(this);
});
},getSelected:function(jq){
return _9(jq[0]);
},getPanel:function(jq,_34){
return _d(jq[0],_34);
},select:function(jq,_35){
return jq.each(function(){
_1e(this,_35);
});
},add:function(jq,_36){
return jq.each(function(){
add(this,_36);
});
},remove:function(jq,_37){
return jq.each(function(){
_29(this,_37);
});
}};
$.fn.accordion.parseOptions=function(_38){
var t=$(_38);
return {width:(parseInt(_38.style.width)||undefined),height:(parseInt(_38.style.height)||undefined),fit:(t.attr("fit")?t.attr("fit")=="true":undefined),border:(t.attr("border")?t.attr("border")=="true":undefined),animate:(t.attr("animate")?t.attr("animate")=="true":undefined)};
};
$.fn.accordion.defaults={width:"auto",height:"auto",fit:false,border:true,animate:true,onSelect:function(_39){
},onAdd:function(_3a){
},onBeforeRemove:function(_3b){
},onRemove:function(_3c){
}};
})(jQuery);

