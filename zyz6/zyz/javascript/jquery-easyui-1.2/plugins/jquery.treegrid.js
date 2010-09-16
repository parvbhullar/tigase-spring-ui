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
var _3=$.data(_2,"treegrid").options;
$(_2).datagrid($.extend({},_3,{url:null,onResizeColumn:function(_4,_5){
_6(_2);
_3.onResizeColumn.call(_2,_4,_5);
}}));
};
function _6(_7,_8){
var _9=$.data(_7,"datagrid").options;
var _a=$.data(_7,"datagrid").panel;
var _b=_a.find(">div.datagrid-view");
var _c=_b.find(">div.datagrid-view1");
var _d=_b.find(">div.datagrid-view2");
if(_9.rownumbers||(_9.frozenColumns&&_9.frozenColumns.length>0)){
if(_8){
_d.find("tr[node-id="+_8+"]").next("tr.treegrid-tr-tree").find("tr[node-id]").each(function(){
_e($(this).attr("node-id"));
});
}else{
_d.find("tr[node-id]").each(function(){
_e($(this).attr("node-id"));
});
}
}
if(_9.height=="auto"){
var _f=_d.find("div.datagrid-body table").height()+18;
_c.find("div.datagrid-body").height(_f);
_d.find("div.datagrid-body").height(_f);
_b.height(_d.height());
}
function _e(_10){
var tr1=_c.find("tr[node-id="+_10+"]");
var tr2=_d.find("tr[node-id="+_10+"]");
tr1.css("height",null);
tr2.css("height",null);
var _11=Math.max(tr1.height(),tr2.height());
tr1.css("height",_11);
tr2.css("height",_11);
};
};
function _12(_13){
var _14=$.data(_13,"treegrid").options;
if(!_14.rownumbers){
return;
}
$(_13).datagrid("getPanel").find("div.datagrid-view1 div.datagrid-body div.datagrid-cell-rownumber").each(function(i){
$(this).html(i+1);
});
};
function _15(_16){
var _17=$.data(_16,"treegrid").options;
var _18=$(_16).datagrid("getPanel").find("div.datagrid-body");
_18.find("span.tree-hit").unbind(".treegrid").bind("click.treegrid",function(){
var tr=$(this).parent().parent().parent();
var id=tr.attr("node-id");
_86(_16,id);
return false;
}).bind("mouseenter.treegrid",function(){
if($(this).hasClass("tree-expanded")){
$(this).addClass("tree-expanded-hover");
}else{
$(this).addClass("tree-collapsed-hover");
}
}).bind("mouseleave.treegrid",function(){
if($(this).hasClass("tree-expanded")){
$(this).removeClass("tree-expanded-hover");
}else{
$(this).removeClass("tree-collapsed-hover");
}
});
_18.find("tr").unbind(".treegrid").bind("mouseenter.treegrid",function(){
var id=$(this).attr("node-id");
_18.find("tr[node-id="+id+"]").addClass("datagrid-row-over");
}).bind("mouseleave.treegrid",function(){
var id=$(this).attr("node-id");
_18.find("tr[node-id="+id+"]").removeClass("datagrid-row-over");
}).bind("click.treegrid",function(){
var id=$(this).attr("node-id");
_76(_16,id);
_17.onClickRow.call(_16,_19(_16,id));
return false;
}).bind("dblclick",function(){
var id=$(this).attr("node-id");
_17.onDblClickRow.call(_16,_19(_16,id));
return false;
});
};
function _1a(_1b,_1c){
var _1d=$.data(_1b,"datagrid").options;
var _1e=$(_1b).datagrid("getPanel").find(">div.datagrid-view");
var _1f=_1e.find(">div.datagrid-view1");
var _20=_1e.find(">div.datagrid-view2");
var tr1=_1f.find(">div.datagrid-body tr[node-id="+_1c+"]");
var tr2=_20.find(">div.datagrid-body tr[node-id="+_1c+"]");
var _21=tr1.next("tr.treegrid-tr-tree");
var _22=tr2.next("tr.treegrid-tr-tree");
var _23=_21.find(">td>div");
var _24=_22.find(">td>div");
var td1=tr1.find("td[field="+_1d.treeField+"]");
var td2=tr2.find("td[field="+_1d.treeField+"]");
var _25=td1.find("span.tree-indent,span.tree-hit").length+td2.find("span.tree-indent,span.tree-hit").length;
return [_23,_24,_25];
};
function _26(_27,_28){
var _29=$.data(_27,"treegrid").options;
var _2a=$(_27).datagrid("getPanel").find(">div.datagrid-view");
var _2b=_2a.find(">div.datagrid-view1");
var _2c=_2a.find(">div.datagrid-view2");
var tr1=_2b.find(">div.datagrid-body tr[node-id="+_28+"]");
var tr2=_2c.find(">div.datagrid-body tr[node-id="+_28+"]");
var _2d=$(_27).datagrid("getColumnFields",true).length+(_29.rownumbers?1:0);
var _2e=$(_27).datagrid("getColumnFields",false).length;
_2f(tr1,_2d);
_2f(tr2,_2e);
function _2f(tr,_30){
$("<tr class=\"treegrid-tr-tree\">"+"<td style=\"border:0px\" colspan=\""+_30+"\">"+"<div></div>"+"</td>"+"<tr>").insertAfter(tr);
};
};
function _31(_32,_33,_34,_35){
var _36=$.data(_32,"treegrid").options;
var _37=$.data(_32,"datagrid").panel;
var _38=_37.find(">div.datagrid-view");
var _39=_38.find(">div.datagrid-view1");
var _3a=_38.find(">div.datagrid-view2");
var _3b=$(_32).datagrid("getColumnFields",true);
var _3c=$(_32).datagrid("getColumnFields",false);
_3d(_34,_33);
if(_33){
var _3e=_19(_32,_33);
if(_3e.children){
_3e.children=_3e.children.concat(_34);
}else{
_3e.children=_34;
}
var _3f=_1a(_32,_33);
var cc1=_3f[0];
var cc2=_3f[1];
var _40=_3f[2];
}else{
$.data(_32,"treegrid").data=$.data(_32,"treegrid").data.concat(_34);
var cc1=_39.find(">div.datagrid-body>div.datagrid-body-inner");
var cc2=_3a.find(">div.datagrid-body");
var _40=0;
}
if(!_35){
$.data(_32,"treegrid").data=_34;
cc1.empty();
cc2.empty();
}
var _41=_42(_34,_40);
cc1.html(_41[0].join(""));
cc2.html(_41[1].join(""));
_6(_32);
_12(_32);
_15(_32);
function _3d(_43,_44){
for(var i=0;i<_43.length;i++){
var row=_43[i];
row._parentId=_44;
if(row.children&&row.children.length){
_3d(row.children,row[_36.idField]);
}
}
};
function _42(_45,_46){
var _47=["<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\"><tbody>"];
var _48=["<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\"><tbody>"];
var _49=[_47,_48];
for(var i=0;i<_45.length;i++){
var row=_45[i];
if(row.state!="open"&&row.state!="closed"){
row.state="open";
}
_49[0]=_49[0].concat(_4a(row,_3b,_46,_36.rownumbers));
_49[1]=_49[1].concat(_4a(row,_3c,_46));
if(row.children&&row.children.length){
var tt=_42(row.children,_46+1);
var v=row.state=="closed"?"none":"block";
_49[0].push("<tr class=\"treegrid-tr-tree\"><td style=\"border:0px\" colspan="+(_3b.length+(_36.rownumbers?1:0))+"><div style=\"display:"+v+"\">");
_49[0]=_49[0].concat(tt[0]);
_49[0].push("</div></td></tr>");
_49[1].push("<tr class=\"treegrid-tr-tree\"><td style=\"border:0px\" colspan="+_3c.length+"><div style=\"display:"+v+"\">");
_49[1]=_49[1].concat(tt[1]);
_49[1].push("</div></td></tr>");
}
}
_49[0].push("</tbody></table>");
_49[1].push("</tbody></table>");
return _49;
};
function _4a(row,_4b,_4c,_4d){
var _4e=["<tr node-id="+row[_36.idField]+">"];
if(_4d){
_4e.push("<td class=\"datagrid-td-rownumber\"><div class=\"datagrid-cell-rownumber\">0</div></td>");
}
for(var i=0;i<_4b.length;i++){
var _4f=_4b[i];
var col=$(_32).datagrid("getColumnOption",_4f);
if(col){
var _50="width:"+(col.width)+"px;";
_50+="text-align:"+(col.align||"left")+";";
_50+=_36.nowrap==false?"white-space:normal;":"";
_4e.push("<td field=\""+_4f+"\">");
_4e.push("<div style=\""+_50+"\" ");
if(col.checkbox){
_4e.push("class=\"datagrid-cell-check ");
}else{
_4e.push("class=\"datagrid-cell ");
}
_4e.push("\">");
if(col.checkbox){
if(true){
_4e.push("<input type=\"checkbox\" checked=\"checked\"/>");
}else{
_4e.push("<input type=\"checkbox\"/>");
}
}
var val=null;
if(col.formatter){
val=col.formatter(row[_4f],row);
}else{
val=row[_4f]||"&nbsp;";
}
if(_4f==_36.treeField){
for(var j=0;j<_4c;j++){
_4e.push("<span class=\"tree-indent\"></span>");
}
if(row.state=="closed"){
_4e.push("<span class=\"tree-hit tree-collapsed\"></span>");
_4e.push("<span class=\"tree-icon tree-folder "+row.iconCls+"\"></span>");
}else{
if(row.children&&row.children.length){
_4e.push("<span class=\"tree-hit tree-expanded\"></span>");
_4e.push("<span class=\"tree-icon tree-folder tree-folder-open "+row.iconCls+"\"></span>");
}else{
_4e.push("<span class=\"tree-indent\"></span>");
_4e.push("<span class=\"tree-icon tree-file "+row.iconCls+"\"></span>");
}
}
_4e.push("<span class=\"tree-title\">"+val+"</span>");
}else{
_4e.push(val);
}
_4e.push("</div>");
_4e.push("</td>");
}
}
_4e.push("</tr>");
return _4e;
};
};
function _51(_52,_53,_54,_55,_56){
var _57=$.data(_52,"treegrid").options;
var _58=$(_52).datagrid("getPanel").find("div.datagrid-body");
_54=_54||{};
var row=_19(_52,_53);
if(_57.onBeforeLoad.call(_52,row,_54)==false){
return;
}
if(!_57.url){
return;
}
var _59=_58.find("tr[node-id="+_53+"] span.tree-folder");
_59.addClass("tree-loading");
$.ajax({type:_57.method,url:_57.url,data:_54,dataType:"json",success:function(_5a){
_59.removeClass("tree-loading");
_31(_52,_53,_5a,_55);
if(_56){
_56();
}
},error:function(){
_59.removeClass("tree-loading");
_57.onLoadError.apply(_52,arguments);
if(_56){
_56();
}
}});
};
function _5b(_5c){
var _5d=_5e(_5c);
if(_5d.length){
return _5d[0];
}else{
return null;
}
};
function _5e(_5f){
return $.data(_5f,"treegrid").data;
};
function _60(_61,_62){
var row=_19(_61,_62);
if(row._parentId){
return _19(_61,row._parentId);
}else{
return null;
}
};
function _63(_64,_65){
var _66=$.data(_64,"treegrid").options;
var _67=$(_64).datagrid("getPanel").find("div.datagrid-view2 div.datagrid-body");
var _68=[];
if(_65){
_69(_65);
}else{
var _6a=_5e(_64);
for(var i=0;i<_6a.length;i++){
_68.push(_6a[i]);
_69(_6a[i][_66.idField]);
}
}
function _69(_6b){
var _6c=_19(_64,_6b);
if(_6c&&_6c.children){
for(var i=0,len=_6c.children.length;i<len;i++){
var _6d=_6c.children[i];
_68.push(_6d);
_69(_6d[_66.idField]);
}
}
};
return _68;
};
function _6e(_6f){
var _70=$(_6f).datagrid("getPanel").find("div.datagrid-body");
var id=_70.find("tr.tree-node-selected").attr("node-id");
return _19(_6f,id);
};
function _19(_71,_72){
var _73=$.data(_71,"treegrid").options;
var _74=$.data(_71,"treegrid").data;
var cc=[_74];
while(cc.length){
var c=cc.shift();
for(var i=0;i<c.length;i++){
var _75=c[i];
if(_75[_73.idField]==_72){
return _75;
}else{
if(_75["children"]){
cc.push(_75["children"]);
}
}
}
}
return null;
};
function _76(_77,_78){
var _79=$(_77).datagrid("getPanel").find("div.datagrid-body");
_79.find("tr.tree-node-selected").removeClass("tree-node-selected");
_79.find("tr[node-id="+_78+"]").addClass("tree-node-selected");
};
function _7a(_7b,_7c){
var _7d=$.data(_7b,"treegrid").options;
var _7e=$(_7b).datagrid("getPanel").find("div.datagrid-body");
var row=_19(_7b,_7c);
var tr=_7e.find("tr[node-id="+_7c+"]");
var hit=tr.find("span.tree-hit");
if(hit.length==0){
return;
}
if(hit.hasClass("tree-collapsed")){
return;
}
if(_7d.onBeforeCollapse.call(_7b,row)==false){
return;
}
hit.removeClass("tree-expanded tree-expanded-hover").addClass("tree-collapsed");
hit.next().removeClass("tree-folder-open");
tr=tr.next("tr.treegrid-tr-tree");
var cc=tr.find(">td>div");
if(_7d.animate){
cc.slideUp("normal",function(){
_7d.onCollapse.call(_7b,row);
});
}else{
cc.hide();
_7d.onCollapse.call(_7b,row);
}
};
function _7f(_80,_81){
var _82=$.data(_80,"treegrid").options;
var _83=$(_80).datagrid("getPanel").find("div.datagrid-body");
var tr=_83.find("tr[node-id="+_81+"]");
var hit=tr.find("span.tree-hit");
var row=_19(_80,_81);
if(hit.length==0){
return;
}
if(hit.hasClass("tree-expanded")){
return;
}
if(_82.onBeforeExpand.call(_80,row)==false){
return;
}
hit.removeClass("tree-collapsed tree-collapsed-hover").addClass("tree-expanded");
hit.next().addClass("tree-folder-open");
var _84=tr.next("tr.treegrid-tr-tree");
if(_84.length){
var cc=_84.find(">td>div");
_85(cc);
}else{
_26(_80,row[_82.idField]);
var _84=tr.next("tr.treegrid-tr-tree");
var cc=_84.find(">td>div");
cc.hide();
_51(_80,row[_82.idField],{id:row[_82.idField]},true,function(){
_85(cc);
});
}
function _85(cc){
if(_82.animate){
cc.slideDown("normal",function(){
_6(_80,_81);
_82.onExpand.call(_80,row);
});
}else{
cc.show();
_6(_80,_81);
_82.onExpand.call(_80,row);
}
};
};
function _86(_87,_88){
var _89=$(_87).datagrid("getPanel").find("div.datagrid-body");
var tr=_89.find("tr[node-id="+_88+"]");
var hit=tr.find("span.tree-hit");
if(hit.hasClass("tree-expanded")){
_7a(_87,_88);
}else{
_7f(_87,_88);
}
};
function _8a(_8b){
var _8c=$.data(_8b,"treegrid").options;
var _8d=_63(_8b);
for(var i=0;i<_8d.length;i++){
_7a(_8b,_8d[i][_8c.idField]);
}
};
function _8e(_8f){
var _90=$.data(_8f,"treegrid").options;
var _91=_63(_8f);
for(var i=0;i<_91.length;i++){
_7f(_8f,_91[i][_90.idField]);
}
};
function _92(_93,_94){
var _95=$.data(_93,"treegrid").options;
var ids=[];
var p=_60(_93,_94);
while(p){
var id=p[_95.idField];
ids.unshift(id);
p=_60(_93,id);
}
for(var i=0;i<ids.length;i++){
_7f(_93,ids[i]);
}
};
$.fn.treegrid=function(_96,_97){
if(typeof _96=="string"){
return $.fn.treegrid.methods[_96](this,_97);
}
_96=_96||{};
return this.each(function(){
var _98=$.data(this,"treegrid");
if(_98){
$.extend(_98.options,_96);
}else{
$.data(this,"treegrid",{options:$.extend({},$.fn.treegrid.defaults,$.fn.treegrid.parseOptions(this),_96),data:[]});
}
_1(this);
_51(this);
});
};
$.fn.treegrid.methods={options:function(jq){
return $.data(jq[0],"treegrid").options;
},resize:function(jq,_99){
return jq.each(function(){
$(this).datagrid("resize",_99);
});
},loadData:function(jq,_9a){
return jq.each(function(){
_31(this,null,_9a);
});
},reload:function(jq){
return jq.each(function(){
_51(this);
});
},getData:function(jq){
return $.data(jq[0],"treegrid").data;
},getRoot:function(jq){
return _5b(jq[0]);
},getRoots:function(jq){
return _5e(jq[0]);
},getParent:function(jq,id){
return _60(jq[0],id);
},getChildren:function(jq,id){
return _63(jq[0],id);
},getSelected:function(jq){
return _6e(jq[0]);
},find:function(jq,id){
return _19(jq[0],id);
},select:function(jq,id){
return jq.each(function(){
_76(this,id);
});
},collapse:function(jq,id){
return jq.each(function(){
_7a(this,id);
});
},expand:function(jq,id){
return jq.each(function(){
_7f(this,id);
});
},toggle:function(jq,id){
return jq.each(function(){
_86(this,id);
});
},collapseAll:function(jq){
return jq.each(function(){
_8a(this);
});
},expandAll:function(jq){
return jq.each(function(){
_8e(this);
});
},expandTo:function(jq,id){
return jq.each(function(){
_92(this,id);
});
}};
$.fn.treegrid.parseOptions=function(_9b){
var t=$(_9b);
return $.extend({},$.fn.datagrid.parseOptions(_9b),{treeField:t.attr("treeField"),animate:(t.attr("animate")?t.attr("animate")=="true":undefined)});
};
$.fn.treegrid.defaults=$.extend({},$.fn.datagrid.defaults,{treeField:null,animate:false,onBeforeLoad:function(row,_9c){
},onLoadSuccess:function(row){
},onLoadError:function(){
},onBeforeCollapse:function(row){
},onCollapse:function(row){
},onBeforeExpand:function(row){
},onExpand:function(row){
},onClickRow:function(row){
},onDblClickRow:function(row){
}});
})(jQuery);

