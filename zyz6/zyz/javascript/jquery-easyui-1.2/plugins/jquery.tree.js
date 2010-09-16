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
var _3=$(_2);
_3.addClass("tree");
return _3;
};
function _4(_5){
var _6=[];
_7(_6,$(_5));
function _7(aa,_8){
_8.find(">li").each(function(){
var _9=$(this);
var _a={};
_a.text=_9.find(">span").html();
if(!_a.text){
_a.text=_9.html();
}
_a.id=_9.attr("id");
_a.iconCls=_9.attr("icon");
_a.checked=_9.attr("checked")=="true";
_a.state=_9.attr("state")||"open";
var _b=_9.find(">ul");
if(_b.length){
_a.children=[];
_7(_a.children,_b);
}
aa.push(_a);
});
};
return _6;
};
function _c(_d){
var _e=$.data(_d,"tree").options;
var _f=$.data(_d,"tree").tree;
$("div.tree-node",_f).unbind(".tree").bind("dblclick.tree",function(){
_91(_d,this);
_e.onDblClick.call(_d,_74(_d));
}).bind("click.tree",function(){
_91(_d,this);
_e.onClick.call(_d,_74(_d));
}).bind("mouseenter.tree",function(){
$(this).addClass("tree-node-hover");
return false;
}).bind("mouseleave.tree",function(){
$(this).removeClass("tree-node-hover");
return false;
});
$("span.tree-hit",_f).unbind(".tree").bind("click.tree",function(){
var _10=$(this).parent();
_50(_d,_10[0]);
return false;
}).bind("mouseenter.tree",function(){
if($(this).hasClass("tree-expanded")){
$(this).addClass("tree-expanded-hover");
}else{
$(this).addClass("tree-collapsed-hover");
}
}).bind("mouseleave.tree",function(){
if($(this).hasClass("tree-expanded")){
$(this).removeClass("tree-expanded-hover");
}else{
$(this).removeClass("tree-collapsed-hover");
}
});
$("span.tree-checkbox",_f).unbind(".tree").bind("click.tree",function(){
var _11=$(this).parent();
_12(_d,_11[0],!$(this).hasClass("tree-checkbox1"));
return false;
});
};
function _12(_13,_14,_15){
var _16=$.data(_13,"tree").options;
if(!_16.checkbox){
return;
}
var _17=$(_14);
var ck=_17.find(".tree-checkbox");
ck.removeClass("tree-checkbox0 tree-checkbox1 tree-checkbox2");
if(_15){
ck.addClass("tree-checkbox1");
}else{
ck.addClass("tree-checkbox0");
}
if(_16.cascadeCheck){
_18(_17);
_19(_17);
}
var _1a=$.extend({},$.data(_14,"tree-node"),{target:_14,checked:_17.find(".tree-checkbox").hasClass("tree-checkbox1")});
_16.onCheck.call(_13,_1a,_15);
function _19(_1b){
var _1c=_1b.next().find(".tree-checkbox");
_1c.removeClass("tree-checkbox0 tree-checkbox1 tree-checkbox2");
if(_1b.find(".tree-checkbox").hasClass("tree-checkbox1")){
_1c.addClass("tree-checkbox1");
}else{
_1c.addClass("tree-checkbox0");
}
};
function _18(_1d){
var _1e=_5c(_13,_1d[0]);
if(_1e){
var ck=$(_1e.target).find(".tree-checkbox");
ck.removeClass("tree-checkbox0 tree-checkbox1 tree-checkbox2");
if(_1f(_1d)){
ck.addClass("tree-checkbox1");
}else{
if(_20(_1d)){
ck.addClass("tree-checkbox0");
}else{
ck.addClass("tree-checkbox2");
}
}
_18($(_1e.target));
}
function _1f(n){
var ck=n.find(".tree-checkbox");
if(ck.hasClass("tree-checkbox0")||ck.hasClass("tree-checkbox2")){
return false;
}
var b=true;
n.parent().siblings().each(function(){
if(!$(this).find(">div.tree-node .tree-checkbox").hasClass("tree-checkbox1")){
b=false;
}
});
return b;
};
function _20(n){
var ck=n.find(".tree-checkbox");
if(ck.hasClass("tree-checkbox1")||ck.hasClass("tree-checkbox2")){
return false;
}
var b=true;
n.parent().siblings().each(function(){
if(!$(this).find(">div.tree-node .tree-checkbox").hasClass("tree-checkbox0")){
b=false;
}
});
return b;
};
};
};
function _21(_22,_23){
var _24=$.data(_22,"tree").options;
var _25=$(_23);
if(_26(_22,_23)){
var ck=_25.find(".tree-checkbox");
if(!ck.length){
$("<span class=\"tree-checkbox tree-checkbox0\"></span>").insertBefore(_25.find(".tree-title"));
_c(_22);
}
}else{
var ck=_25.find(".tree-checkbox");
if(_24.onlyLeafCheck){
ck.remove();
}else{
if(ck.hasClass("tree-checkbox1")){
_12(_22,_23,true);
}else{
if(ck.hasClass("tree-checkbox2")){
var _27=true;
var _28=true;
var _29=_2a(_22,_23);
for(var i=0;i<_29.length;i++){
if(_29[i].checked){
_28=false;
}else{
_27=false;
}
}
if(_27){
_12(_22,_23,true);
}
if(_28){
_12(_22,_23,false);
}
}
}
}
}
};
function _2b(_2c,ul,_2d,_2e){
var _2f=$.data(_2c,"tree").options;
if(!_2e){
$(ul).empty();
}
var _30=[];
var _31=$(ul).prev("div.tree-node").find(">span.tree-indent,>span.tree-hit").length;
_32(ul,_2d,_31);
_c(_2c);
for(var i=0;i<_30.length;i++){
_12(_2c,_30[i],true);
}
var _33=null;
if(_2c!=ul){
var _34=$(ul).prev();
_33=$.extend({},$.data(_34[0],"tree-node"),{target:_34[0],checked:_34.find(".tree-checkbox").hasClass("tree-checkbox1")});
}
_2f.onLoadSuccess.call(_2c,_33,_2d);
function _32(ul,_35,_36){
for(var i=0;i<_35.length;i++){
var li=$("<li></li>").appendTo(ul);
var _37=_35[i];
if(_37.state!="open"&&_37.state!="closed"){
_37.state="open";
}
var _38=$("<div class=\"tree-node\"></div>").appendTo(li);
_38.attr("node-id",_37.id);
$.data(_38[0],"tree-node",{id:_37.id,text:_37.text,iconCls:_37.iconCls,attributes:_37.attributes});
$("<span class=\"tree-title\"></span>").html(_37.text).appendTo(_38);
if(_2f.checkbox){
if(_2f.onlyLeafCheck){
if(_37.state=="open"&&(!_37.children||!_37.children.length)){
if(_37.checked){
$("<span class=\"tree-checkbox tree-checkbox1\"></span>").prependTo(_38);
}else{
$("<span class=\"tree-checkbox tree-checkbox0\"></span>").prependTo(_38);
}
}
}else{
if(_37.checked){
$("<span class=\"tree-checkbox tree-checkbox1\"></span>").prependTo(_38);
_30.push(_38[0]);
}else{
$("<span class=\"tree-checkbox tree-checkbox0\"></span>").prependTo(_38);
}
}
}
if(_37.children&&_37.children.length){
var _39=$("<ul></ul>").appendTo(li);
if(_37.state=="open"){
$("<span class=\"tree-icon tree-folder tree-folder-open\"></span>").addClass(_37.iconCls).prependTo(_38);
$("<span class=\"tree-hit tree-expanded\"></span>").prependTo(_38);
}else{
$("<span class=\"tree-icon tree-folder\"></span>").addClass(_37.iconCls).prependTo(_38);
$("<span class=\"tree-hit tree-collapsed\"></span>").prependTo(_38);
_39.css("display","none");
}
_32(_39,_37.children,_36+1);
}else{
if(_37.state=="closed"){
$("<span class=\"tree-icon tree-folder\"></span>").addClass(_37.iconCls).prependTo(_38);
$("<span class=\"tree-hit tree-collapsed\"></span>").prependTo(_38);
}else{
$("<span class=\"tree-icon tree-file\"></span>").addClass(_37.iconCls).prependTo(_38);
$("<span class=\"tree-indent\"></span>").prependTo(_38);
}
}
for(var j=0;j<_36;j++){
$("<span class=\"tree-indent\"></span>").prependTo(_38);
}
}
};
};
function _3a(_3b,ul,_3c,_3d){
var _3e=$.data(_3b,"tree").options;
_3c=_3c||{};
var _3f=null;
if(_3b!=ul){
var _40=$(ul).prev();
_3f=$.extend({},$.data(_40[0],"tree-node"),{target:_40[0],checked:_40.find(".tree-checkbox").hasClass("tree-checkbox1")});
}
if(_3e.onBeforeLoad.call(_3b,_3f,_3c)==false){
return;
}
if(!_3e.url){
return;
}
var _41=$(ul).prev().find(">span.tree-folder");
_41.addClass("tree-loading");
$.ajax({type:"post",url:_3e.url,data:_3c,dataType:"json",success:function(_42){
_41.removeClass("tree-loading");
_2b(_3b,ul,_42);
if(_3d){
_3d();
}
},error:function(){
_41.removeClass("tree-loading");
_3e.onLoadError.apply(_3b,arguments);
if(_3d){
_3d();
}
}});
};
function _43(_44,_45){
var _46=$.data(_44,"tree").options;
var _47=$(_45);
var hit=_47.find(">span.tree-hit");
if(hit.length==0){
return;
}
if(hit.hasClass("tree-expanded")){
return;
}
var _48=$.extend({},$.data(_45,"tree-node"),{target:_45,checked:_47.find(".tree-checkbox").hasClass("tree-checkbox1")});
if(_46.onBeforeExpand.call(_44,_48)==false){
return;
}
hit.removeClass("tree-collapsed tree-collapsed-hover").addClass("tree-expanded");
hit.next().addClass("tree-folder-open");
var ul=_47.next();
if(ul.length){
if(_46.animate){
ul.slideDown("normal",function(){
_46.onExpand.call(_44,_48);
});
}else{
ul.css("display","block");
_46.onExpand.call(_44,_48);
}
}else{
var _49=$("<ul style=\"display:none\"></ul>").insertAfter(_47);
_3a(_44,_49[0],{id:_48.id},function(){
if(_46.animate){
_49.slideDown("normal",function(){
_46.onExpand.call(_44,_48);
});
}else{
_49.css("display","block");
_46.onExpand.call(_44,_48);
}
});
}
};
function _4a(_4b,_4c){
var _4d=$.data(_4b,"tree").options;
var _4e=$(_4c);
var hit=_4e.find(">span.tree-hit");
if(hit.length==0){
return;
}
if(hit.hasClass("tree-collapsed")){
return;
}
var _4f=$.extend({},$.data(_4c,"tree-node"),{target:_4c,checked:_4e.find(".tree-checkbox").hasClass("tree-checkbox1")});
if(_4d.onBeforeCollapse.call(_4b,_4f)==false){
return;
}
hit.removeClass("tree-expanded tree-expanded-hover").addClass("tree-collapsed");
hit.next().removeClass("tree-folder-open");
if(_4d.animate){
_4e.next().slideUp("normal",function(){
_4d.onCollapse.call(_4b,_4f);
});
}else{
_4e.next().css("display","none");
_4d.onCollapse.call(_4b,_4f);
}
};
function _50(_51,_52){
var hit=$(_52).find(">span.tree-hit");
if(hit.length==0){
return;
}
if(hit.hasClass("tree-expanded")){
_4a(_51,_52);
}else{
_43(_51,_52);
}
};
function _53(_54){
var _55=_56(_54);
for(var i=0;i<_55.length;i++){
_43(_54,_55[i].target);
var _57=_2a(_54,_55[i].target);
for(var j=0;j<_57.length;j++){
_43(_54,_57[j].target);
}
}
};
function _58(_59,_5a){
var _5b=[];
var p=_5c(_59,_5a);
while(p){
_5b.unshift(p);
p=_5c(_59,p.target);
}
for(var i=0;i<_5b.length;i++){
_43(_59,_5b[i].target);
}
};
function _5d(_5e){
var _5f=_56(_5e);
for(var i=0;i<_5f.length;i++){
_4a(_5e,_5f[i].target);
var _60=_2a(_5e,_5f[i].target);
for(var j=0;j<_60.length;j++){
_4a(_5e,_60[j].target);
}
}
};
function _61(_62){
var _63=_56(_62);
if(_63.length){
return _63[0];
}else{
return null;
}
};
function _56(_64){
var _65=[];
$(_64).find(">li").each(function(){
var _66=$(this).find(">div.tree-node");
_65.push($.extend({},$.data(_66[0],"tree-node"),{target:_66[0],checked:_66.find(".tree-checkbox").hasClass("tree-checkbox1")}));
});
return _65;
};
function _2a(_67,_68){
var _69=[];
if(_68){
_6a($(_68));
}else{
var _6b=_56(_67);
for(var i=0;i<_6b.length;i++){
_69.push(_6b[i]);
_6a($(_6b[i].target));
}
}
function _6a(_6c){
_6c.next().find("div.tree-node").each(function(){
_69.push($.extend({},$.data(this,"tree-node"),{target:this,checked:$(this).find(".tree-checkbox").hasClass("tree-checkbox1")}));
});
};
return _69;
};
function _5c(_6d,_6e){
var _6f=$(_6e).parent().parent().prev();
if(_6f.length){
return $.extend({},$.data(_6f[0],"tree-node"),{target:_6f[0],checked:_6f.find(".tree-checkbox").hasClass("tree-checkbox1")});
}else{
return null;
}
};
function _70(_71){
var _72=[];
$(_71).find(".tree-checkbox1").each(function(){
var _73=$(this).parent();
_72.push($.extend({},$.data(_73[0],"tree-node"),{target:_73[0],checked:_73.find(".tree-checkbox").hasClass("tree-checkbox1")}));
});
return _72;
};
function _74(_75){
var _76=$(_75).find("div.tree-node-selected");
if(_76.length){
return $.extend({},$.data(_76[0],"tree-node"),{target:_76[0],checked:_76.find(".tree-checkbox").hasClass("tree-checkbox1")});
}else{
return null;
}
};
function _77(_78,_79){
var _7a=$(_79.parent);
var ul;
if(_7a.length==0){
ul=$(_78);
}else{
ul=_7a.next();
if(ul.length==0){
ul=$("<ul></ul>").insertAfter(_7a);
}
}
if(_79.data&&_79.data.length){
var _7b=_7a.find("span.tree-icon");
if(_7b.hasClass("tree-file")){
_7b.removeClass("tree-file").addClass("tree-folder");
var hit=$("<span class=\"tree-hit tree-expanded\"></span>").insertBefore(_7b);
if(hit.prev().length){
hit.prev().remove();
}
}
}
_2b(_78,ul[0],_79.data,true);
_21(_78,ul.prev());
};
function _7c(_7d,_7e){
var _7f=_5c(_7d,_7e);
var _80=$(_7e);
var li=_80.parent();
var ul=li.parent();
li.remove();
if(ul.find(">li").length==0){
var _80=ul.prev();
_80.find(".tree-icon").removeClass("tree-folder").addClass("tree-file");
_80.find(".tree-hit").remove();
$("<span class=\"tree-indent\"></span>").prependTo(_80);
if(ul[0]!=_7d){
ul.remove();
}
}
if(_7f){
_21(_7d,_7f.target);
}
};
function _81(_82,_83){
function _84(aa,ul){
ul.find(">li").each(function(){
var _85=$(this).find(">div.tree-node");
var _86=$.extend({},$.data(_85[0],"tree-node"),{target:_85[0],checked:_85.find(".tree-checkbox").hasClass("tree-checkbox1")});
if(!_26(_82,_85[0])){
_86.state=_85.find(".tree-hit").hasClass("tree-expanded")?"open":"closed";
}
var sub=$(this).find(">ul");
if(sub.length){
_86.children=[];
_84(_86.children,sub);
}
aa.push(_86);
});
};
var _87=$(_83);
var _88=$.extend({},$.data(_83,"tree-node"),{target:_83,checked:_87.find(".tree-checkbox").hasClass("tree-checkbox1"),children:[]});
_84(_88.children,_87.next());
_7c(_82,_83);
return _88;
};
function _89(_8a,_8b){
var _8c=$(_8b.target);
var _8d=$.data(_8b.target,"tree-node");
if(_8d.iconCls){
_8c.find(".tree-icon").removeClass(_8d.iconCls);
}
$.extend(_8d,_8b);
$.data(_8b.target,"tree-node",_8d);
_8c.attr("node-id",_8d.id);
_8c.find(".tree-title").html(_8d.text);
if(_8d.iconCls){
_8c.find(".tree-icon").addClass(_8d.iconCls);
}
var ck=_8c.find(".tree-checkbox");
ck.removeClass("tree-checkbox0 tree-checkbox1 tree-checkbox2");
if(_8d.checked){
ck.addClass("tree-checkbox1");
}else{
ck.addClass("tree-checkbox0");
}
};
function _8e(_8f,id){
var _90=$(_8f).find("div.tree-node[node-id="+id+"]");
if(_90.length){
return $.extend({},$.data(_90[0],"tree-node"),{target:_90[0],checked:_90.find(".tree-checkbox").hasClass("tree-checkbox1")});
}else{
return null;
}
};
function _91(_92,_93){
var _94=$.data(_92,"tree").options;
var _95=$(_93);
var _96=$.extend({},$.data(_95[0],"tree-node"),{target:_95[0],checked:_95.find(".tree-checkbox").hasClass("tree-checkbox1")});
if(_94.onBeforeSelect.call(_92,_96)==false){
return;
}
$("div.tree-node-selected",_92).removeClass("tree-node-selected");
_95.addClass("tree-node-selected");
_94.onSelect.call(_92,_96);
};
function _26(_97,_98){
var _99=$(_98);
var hit=$(">span.tree-hit",_99);
return hit.length==0;
};
$.fn.tree=function(_9a,_9b){
if(typeof _9a=="string"){
return $.fn.tree.methods[_9a](this,_9b);
}
var _9a=_9a||{};
return this.each(function(){
var _9c=$.data(this,"tree");
var _9d;
if(_9c){
_9d=$.extend(_9c.options,_9a);
_9c.options=_9d;
}else{
_9d=$.extend({},$.fn.tree.defaults,$.fn.tree.parseOptions(this),_9a);
$.data(this,"tree",{options:_9d,tree:_1(this)});
var _9e=_4(this);
_2b(this,this,_9e);
}
if(_9d.data){
_2b(this,this,_9d.data);
}
if(_9d.url){
_3a(this,this);
}
});
};
$.fn.tree.methods={options:function(jq){
return $.data(jq[0],"tree").options;
},loadData:function(jq,_9f){
return jq.each(function(){
_2b(this,this,_9f);
});
},reload:function(jq){
return jq.each(function(){
$(this).empty();
_3a(this,this);
});
},getRoot:function(jq){
return _61(jq[0]);
},getRoots:function(jq){
return _56(jq[0]);
},getParent:function(jq,_a0){
return _5c(jq[0],_a0);
},getChildren:function(jq,_a1){
return _2a(jq[0],_a1);
},getChecked:function(jq){
return _70(jq[0]);
},getSelected:function(jq){
return _74(jq[0]);
},isLeaf:function(jq,_a2){
return _26(jq[0],_a2);
},find:function(jq,id){
return _8e(jq[0],id);
},select:function(jq,_a3){
return jq.each(function(){
_91(this,_a3);
});
},check:function(jq,_a4){
return jq.each(function(){
_12(this,_a4,true);
});
},uncheck:function(jq,_a5){
return jq.each(function(){
_12(this,_a5,false);
});
},collapse:function(jq,_a6){
return jq.each(function(){
_4a(this,_a6);
});
},expand:function(jq,_a7){
return jq.each(function(){
_43(this,_a7);
});
},collapseAll:function(jq){
return jq.each(function(){
_5d(this);
});
},expandAll:function(jq){
return jq.each(function(){
_53(this);
});
},expandTo:function(jq,_a8){
return jq.each(function(){
_58(this,_a8);
});
},toggle:function(jq,_a9){
return jq.each(function(){
_50(this,_a9);
});
},append:function(jq,_aa){
return jq.each(function(){
_77(this,_aa);
});
},remove:function(jq,_ab){
return jq.each(function(){
_7c(this,_ab);
});
},pop:function(jq,_ac){
return _81(jq[0],_ac);
},update:function(jq,_ad){
return jq.each(function(){
_89(this,_ad);
});
}};
$.fn.tree.parseOptions=function(_ae){
var t=$(_ae);
return {url:t.attr("url"),checkbox:(t.attr("checkbox")?t.attr("checkbox")=="true":undefined),cascadeCheck:(t.attr("cascadeCheck")?t.attr("cascadeCheck")=="true":undefined),onlyLeafCheck:(t.attr("onlyLeafCheck")?t.attr("onlyLeafCheck")=="true":undefined),animate:(t.attr("animate")?t.attr("animate")=="true":undefined)};
};
$.fn.tree.defaults={url:null,animate:false,checkbox:false,cascadeCheck:true,onlyLeafCheck:false,data:null,onBeforeLoad:function(_af,_b0){
},onLoadSuccess:function(_b1,_b2){
},onLoadError:function(){
},onClick:function(_b3){
},onDblClick:function(_b4){
},onBeforeExpand:function(_b5){
},onExpand:function(_b6){
},onBeforeCollapse:function(_b7){
},onCollapse:function(_b8){
},onCheck:function(_b9,_ba){
},onBeforeSelect:function(_bb){
},onSelect:function(_bc){
}};
})(jQuery);

