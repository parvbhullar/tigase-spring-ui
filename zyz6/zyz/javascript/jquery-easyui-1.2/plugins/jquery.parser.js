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
$.parser={auto:true,plugins:["linkbutton","menu","menubutton","splitbutton","layout","tree","window","dialog","datagrid","combobox","combotree","numberbox","validatebox","numberspinner","timespinner","calendar","datebox","panel","tabs","accordion"],parse:function(_1){
if($.parser.auto){
for(var i=0;i<$.parser.plugins.length;i++){
(function(){
var _2=$.parser.plugins[i];
var r=$(".easyui-"+_2,_1);
if(r.length){
if(r[_2]){
r[_2]();
}else{
if(window.easyloader){
easyloader.load(_2,function(){
r[_2]();
});
}
}
}
})();
}
}
}};
$(function(){
$.parser.parse();
});
})(jQuery);

