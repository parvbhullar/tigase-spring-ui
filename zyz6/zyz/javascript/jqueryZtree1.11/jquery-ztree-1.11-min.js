(function(e){function oa(b){b.unbind(O);b.bind(O,function(a,c,d){typeof zTreeOnClick=="function"&&zTreeOnClick(a,c,d)});b.unbind(P);b.bind(P,function(a,c,d){typeof zTreeOnCheck=="function"&&zTreeOnCheck(a,c,d)});b.unbind(Q);b.bind(Q,function(a,c,d){typeof zTreeOnDrag=="function"&&zTreeOnDrag(a,c,d)});b.unbind(H);b.bind(H,function(a,c,d,f){typeof zTreeOnDrop=="function"&&zTreeOnDrop(a,c,d,f)});b.unbind(R);b.bind(R,function(a,c,d){typeof zTreeOnAsyncSuccess=="function"&&zTreeOnAsyncSuccess(a,c,d)});
b.unbind(S);b.bind(S,function(a,c,d,f,g){typeof zTreeOnAsyncError=="function"&&zTreeOnAsyncError(a,c,d,f,g)});b.unbind(T);b.bind(T,function(a,c,d){typeof zTreeOnCheckMaxError=="function"&&zTreeOnCheckMaxError(a,c,d)})}function F(b,a,c,d){if(c)for(var f=0;f<c.length;f++){var g=c[f];g.level=a;g.tId=b.treeObjId+"_"+ ++U;g.parentNode=d;g.checkedNew=g.checkedNew==undefined?g.checked==true:g.checkedNew;g.check_Focus=false;g.check_True_Full=true;g.check_False_Full=true;g.isFirstNode=(d?d:b.root).nodes.length==
c.length&&f==0;g.isLastNode=f==c.length-1;if(g.nodes&&g.nodes.length>0){g.open=g.open?true:false;g.isParent=true;ca(b,g);F(b,a+1,g.nodes,g)}else{g.isParent=g.isParent?true:false;ca(b,g);b.checkable&&f==c.length-1&&I(b,g)}}}function ca(b,a){var c=a.parentNode;c=c?e("#"+a.parentNode.tId+o):e("#"+b.treeObjId);c.append("<li id='"+a.tId+"' class='tree-node'><button class=\"switch\" id='"+a.tId+m+"' title='' onfocus='this.blur();'></button><a id='"+a.tId+v+"' onclick=\""+(a.click||"")+'" ><button class="'+
a.iconSkin+" ico\" id='"+a.tId+s+"' title='' onfocus='this.blur();'></button>"+a.name+"</a><ul id='"+a.tId+o+"'></ul></li>");c=e("#"+a.tId+m);var d=e("#"+a.tId+v),f=e("#"+a.tId+s),g=e("#"+a.tId+o);if(b.showLine){if(a.level==0&&a.isFirstNode&&a.isLastNode)c.attr("class",c.attr("class")+"_"+J);else if(a.level==0&&a.isFirstNode)c.attr("class",c.attr("class")+"_"+B);else a.isLastNode?c.attr("class",c.attr("class")+"_"+w):c.attr("class",c.attr("class")+"_"+K);a.isLastNode||g.addClass(C)}else c.attr("class",
c.attr("class")+"_"+da);if(a.isParent){var y=a.open?"_"+x:"_"+t;c.attr("class",c.attr("class")+y);f.attr("class",f.attr("class")+y)}else{c.attr("class",c.attr("class")+"_"+u);f.attr("class",f.attr("class")+"_"+u)}a.icon&&f.attr("style","background:url("+a.icon+") 0 0 no-repeat;");g.css({display:a.open?"block":"none"});if(a.isParent){c.bind("click",{treeObjId:b.treeObjId,treeNode:a},L);d.bind("dblclick",{treeObjId:b.treeObjId,treeNode:a},L)}d.bind("click",function(){window.getSelection?window.getSelection().removeAllRanges():
document.selection.empty();V(b,a);e("#"+b.treeObjId).trigger(O,[b.treeObjId,a])});if(b.checkable){c.after("<BUTTON type='BUTTON' ID='"+a.tId+"_check' onfocus='this.blur();' ></BUTTON>");var r=e("#"+a.tId+"_check");if(b.checkStyle==D&&b.checkRadioType==W&&a.checkedNew)b.checkRadioCheckedList=b.checkRadioCheckedList.concat([a]);z(b,r,a);r.bind("click",function(){var j=true;a.checkedNew=!a.checkedNew;if(b.checkStyle==D)if(a.checkedNew){if(b.checkRadioType==W){if(j=b.checkRadioCheckedList.length<b.checkRadioMaxNum)b.checkRadioCheckedList=
b.checkRadioCheckedList.concat([a])}else for(var h=a.parentNode?a.parentNode:b.root,p=0,i=0;i<h.nodes.length;i++)if(h.nodes[i].checkedNew){p++;if(p>b.checkRadioMaxNum){j=false;break}}if(!j)a.checkedNew=!a.checkedNew}else{if(b.checkRadioType==W)for(h=0;h<b.checkRadioCheckedList.length;h++)if(a==b.checkRadioCheckedList[h]){b.checkRadioCheckedList.splice(h,1);break}}else{a.checkedNew&&b.checkType.Y.indexOf("p")>-1&&X(b,a,true);a.checkedNew&&b.checkType.Y.indexOf("s")>-1&&Y(b,a,true);!a.checkedNew&&b.checkType.N.indexOf("p")>
-1&&X(b,a,false);!a.checkedNew&&b.checkType.N.indexOf("s")>-1&&Y(b,a,false)}z(b,r,a);a.nodes&&a.nodes.length>0?I(b,a.nodes[0]):I(b,a);j?e("#"+b.treeObjId).trigger(P,[b.treeObjId,a]):e("#"+b.treeObjId).trigger(T,[b.treeObjId,a])});r.bind("mouseover",function(){a.checkboxFocus=true;z(b,r,a)});r.bind("mouseout",function(){a.checkboxFocus=false;z(b,r,a)})}d.attr("target",a.target||"_blank");a.url&&!b.editable&&d.attr("href",a.url);d.bind("mousedown",function(j){if(!(j.button==2||!b.editable)){var h=document,
p,i;e(h).mousemove(function(q){window.getSelection?window.getSelection().removeAllRanges():document.selection.empty();e("body").css("cursor","pointer");e("#"+a.tId+m);if(b.dragStatus==0&&a.isParent&&a.open){A(a);b.dragNodeShowBefore=true}if(b.dragStatus==0){b.dragStatus=1;ea(true);V(b,a);var k=e("#"+a.tId).clone();k.attr("id",a.tId+"_tmp");k.css("padding","0");k.children("#"+a.tId+v).removeClass(Z);k.children("#"+a.tId+o).css("display","none");p=e("<ul class='zTreeDragUL'></ul>").append(k);p.attr("id",
a.tId+o+"_tmp");p.addClass(e("#"+b.treeObjId).attr("class"));p.appendTo("body");e("#"+b.treeObjId).trigger(Q,[b.treeObjId,a])}if(b.dragStatus==1){if(i){i.removeClass($);i.removeClass(M)}i=null;if(q.target.id==b.treeObjId&&a.parentNode!=null){i=e("#"+b.treeObjId);i.addClass($)}else if(q.target.id&&e("#"+b.treeObjId).find("#"+q.target.id).length>0){for(k=e("#"+q.target.id);!k.is("li")&&k.attr("id")!=b.treeObjId;)k=k.parent();if(a.parentNode&&k.attr("id")!=a.tId&&k.attr("id")!=a.parentNode.tId&&e("#"+
a.tId).find("#"+k.attr("id")).length==0){k.children("a").addClass(M);i=k.children("a")}else if(a.parentNode==null&&k.attr("id")!=a.tId&&e("#"+a.tId).find("#"+k.attr("id")).length==0){k.children("a").addClass(M);i=k.children("a")}}}p.css({top:q.clientY+(h.body.scrollTop==0?h.documentElement.scrollTop:h.body.scrollTop)+3+"px",left:q.clientX+(h.body.scrollLeft==0?h.documentElement.scrollLeft:h.body.scrollLeft)+3+"px"});return false});e(h).mouseup(function(){e(h).unbind("mousemove");e(h).unbind("mouseup");
e("body").css("cursor","auto");if(i){i.removeClass($);i.removeClass(M)}ea(false);if(b.dragStatus!=0){b.dragStatus=0;if(a.isParent&&b.dragNodeShowBefore&&!a.open){A(a);b.dragNodeShowBefore=false}p&&p.remove();if(i){var q="";if(i.attr("id")==b.treeObjId)q=null;else{for(i=i.parent();!i.is("li")&&i.attr("id")!=b.treeObjId;)i=i.parent();q=i.attr("id")}q=q==null?null:aa(b.root.nodes,q);fa(b,q,a);e("#"+b.treeObjId).trigger(H,[b.treeObjId,a,q])}else e("#"+b.treeObjId).trigger(H,[b.treeObjId,null,null])}});
return false}})}function ea(b){for(;N.length>0;){N[0].remove();N.shift()}if(b){b=e("iframe");for(var a=0;a<b.length;a++){var c=b.get(a),d;d=c;var f=Array(2);oRect=d.getBoundingClientRect();f[0]=oRect.left;f[1]=oRect.top;d=f;c=e("<div id='zTreeMask_"+a+"' class='zTreeMask' style='top:"+d[1]+"px; left:"+d[0]+"px; width:"+c.offsetWidth+"px; height:"+c.offsetHeight+"px;'></div>");c.appendTo("body");N.push(c)}}}function l(b,a){if(b){var c=b.attr("class").split("_");switch(a){case J:case B:case K:case w:case da:c[1]=
a;break;case x:case t:case u:c[2]=a;break}b.attr("class",c.join("_"))}}function E(b,a){if(b){var c=b.attr("class").split("_");switch(a){case x:case t:case u:c[1]=a;break}b.attr("class",c.join("_"))}}function z(b,a,c){if(a){a.removeClass();b=b.checkStyle+"_"+(c.checkedNew?pa:qa)+"_"+(c.checkedNew||b.checkStyle==D?c.check_True_Full?ga:ha:c.check_False_Full?ga:ha);b=c.checkboxFocus?b+"_"+ra:b;a.addClass(sa);a.addClass(b)}}function I(b,a){if(a&&a.parentNode){for(var c=true,d=true,f=0;f<a.parentNode.nodes.length;f++){if(b.checkStyle==
D&&(a.parentNode.nodes[f].checkedNew||!a.parentNode.nodes[f].check_True_Full))c=false;else if(b.checkStyle!=D&&a.parentNode.checkedNew&&(!a.parentNode.nodes[f].checkedNew||!a.parentNode.nodes[f].check_True_Full))c=false;else if(b.checkStyle!=D&&!a.parentNode.checkedNew&&(a.parentNode.nodes[f].checkedNew||!a.parentNode.nodes[f].check_False_Full))d=false;if(!c||!d)break}a.parentNode.check_True_Full=c;a.parentNode.check_False_Full=d;c=e("#"+a.parentNode.tId+"_check");z(b,c,a.parentNode);I(b,a.parentNode)}}
function L(b){ia(n[b.data.treeObjId],b.data.treeNode)}function ia(b,a){if(a&&a.nodes&&a.nodes.length>0)A(a);else b.async&&!b.editable&&ja(b,a)}function ja(b,a){for(var c="",d=0;a&&d<b.asyncParam.length;d++)c+=(c.length>0?"&":"")+b.asyncParam[d]+"="+a[b.asyncParam[d]];for(d=0;d<b.asyncParamOther.length;d+=2)c+=(c.length>0?"&":"")+b.asyncParamOther[d]+"="+b.asyncParamOther[d+1];e.ajax({type:"POST",url:b.asyncUrl,data:c,success:function(f){if(!(!f||f.length==0)){var g="";try{g=eval("("+f+")")}catch(y){}g&&
g!=""&&ka(b,a,g);e("#"+b.treeObjId).trigger(R,[b.treeObjId,f])}},error:function(f,g,y){e("#"+b.treeObjId).trigger(S,[b.treeObjId,f,g,y])}})}function A(b){var a=e("#"+b.tId+m),c=e("#"+b.tId+s),d=e("#"+b.tId+o);if(b.isParent&&b.nodes&&b.nodes.length>0){d.toggle("fast");if(a.attr("class").indexOf(t)>0){l(a,x);E(c,x);b.open=true}else{l(a,t);E(c,t);b.open=false}}}function ba(b,a,c){if(b){a&&a.open!=c&&A(a);if(a=a?a.nodes:n[b].root.nodes)for(var d=0;d<a.length;d++)a[d]&&ba(b,a[d],c)}}function G(b,a,c){if(b){a&&
a.open!=c&&A(a);a.parentNode&&G(b,a.parentNode,c)}}function X(b,a,c){var d=e("#"+a.tId+"_check");a.checkedNew=c;z(b,d,a);if(a.parentNode){d=true;if(!c)for(var f=0;f<a.parentNode.nodes.length;f++)if(a.parentNode.nodes[f].checkedNew){d=false;break}d&&X(b,a.parentNode,c)}}function Y(b,a,c){if(a){var d=e("#"+a.tId+"_check");a.checkedNew=c;z(b,d,a);if(a.nodes)for(d=0;d<a.nodes.length;d++)a.nodes[d]&&Y(b,a.nodes[d],c)}}function la(b,a){if(a){a.level=b?b.level+1:0;if(a.nodes)for(var c=0;c<a.nodes.length;c++)a.nodes[c]&&
la(a,a.nodes[c])}}function ka(b,a,c){if(a){if(e("#"+b.treeObjId).find("#"+a.tId).length!=0){target_switchObj=e("#"+a.tId+m);target_icoObj=e("#"+a.tId+s);target_aObj=e("#"+a.tId+v);target_ulObj=e("#"+a.tId+o);if(!a.open){l(target_switchObj,t);E(target_icoObj,t);a.open=false;target_ulObj.css({display:"none"})}if(!a.isParent){target_switchObj.unbind("click");target_switchObj.bind("click",function(){A(a)});target_aObj.unbind("dblclick");target_aObj.bind("dblclick",{treeObjId:b.treeObjId,treeNode:a},L)}ma(a,
c);F(b,a.level+1,c,a);G(b.treeObjId,a,true)}}else{ma(b.root,c);F(b,0,c,null)}}function ma(b,a){if(!b.nodes)b.nodes=[];if(b.nodes.length>0){var c=b.nodes[b.nodes.length-1].tId;b.nodes[b.nodes.length-1].isLastNode=false;b.nodes[b.nodes.length-1].isFirstNode?l(e("#"+c+m),B):l(e("#"+c+m),K);e("#"+c+o).addClass(C)}b.isParent=true;b.nodes=b.nodes.concat(a)}function fa(b,a,c){if(a!=c){var d=c.parentNode==null?b.root:c.parentNode,f=a===null||a==b.root;if(f&&a===null)a=b.root;var g=e("#"+c.tId+m),y=e("#"+
c.tId+o),r,j,h,p;if(f)r=r=e("#"+b.treeObjId);else{j=e("#"+a.tId+m);h=e("#"+a.tId+s);p=e("#"+a.tId+v);r=e("#"+a.tId+o)}l(j,x);E(h,x);a.open=true;r.css({display:"block"});if(!a.isParent&&!f){j.unbind("click");j.bind("click",function(){A(a)});p.unbind("dblclick");p.bind("dblclick",{treeObjId:b.treeObjId,treeNode:a},L)}r.append(e("#"+c.tId).detach());j=-1;for(h=0;h<d.nodes.length;h++)if(d.nodes[h].tId==c.tId)j=h;j>=0&&d.nodes.splice(j,1);if(a.nodes){if(b.showLine&&a.nodes.length>0){a.nodes[a.nodes.length-
1].isLastNode=false;j=e("#"+a.nodes[a.nodes.length-1].tId+o);h=e("#"+a.nodes[a.nodes.length-1].tId+m);j.addClass(C);f&&a.nodes[a.nodes.length-1].isFirstNode?l(h,B):l(h,K)}}else a.nodes=[];if(f)c.parentNode=null;else{a.isParent=true;c.parentNode=a}la(c.parentNode,c);a.nodes.splice(a.nodes.length,0,c);c.isLastNode=true;c.isFirstNode=a.nodes.length==1;if(b.showLine){l(g,w);y.removeClass(C)}if(d.nodes.length<1){d.isParent=false;j=e("#"+d.tId+o);h=e("#"+d.tId+m);c=e("#"+d.tId+s);l(h,u);E(c,u);j.css("display",
"none")}else if(b.showLine){d.nodes[d.nodes.length-1].isLastNode=true;d.nodes[d.nodes.length-1].isFirstNode=d.nodes.length==1;j=e("#"+d.nodes[d.nodes.length-1].tId+o);h=e("#"+d.nodes[d.nodes.length-1].tId+m);c=e("#"+d.nodes[d.nodes.length-1].tId+s);if(d==b.root)if(d.nodes.length==1)l(h,J);else{d=e("#"+d.nodes[0].tId+m);l(d,B);l(h,w)}else l(h,w);j.removeClass(C)}G(b.treeObjId,a,true)}}function aa(b,a){if(!b||!a)return null;for(var c=0;c<b.length;c++){if(b[c].tId==a)return b[c];var d=aa(b[c].nodes,
a);if(d)return d}return null}function V(b,a){b.curTreeNode&&e("#"+b.curTreeNode.tId+v).removeClass(Z);e("#"+a.tId+v).addClass(Z);b.curTreeNode=a}function na(b,a){if(!b)return[];for(var c=[],d=0;d<b.length;d++){if(b[d].checkedNew==a)c=c.concat([b[d]]);var f=na(b[d].nodes,a);if(f.length>0)c=c.concat(f)}return c}function ta(){return{container:null,init:function(b){this.container=b;return this},refresh:function(){var b=this.container.attr("id");e("#"+b).empty();n[b].curTreeNode=null;U=0;F(n[b],0,n[b].root.nodes)},
setEditable:function(b){var a=this.container.attr("id");n[a].editable=b;return this.refresh()},getNodes:function(){var b=this.container.attr("id");return n[b].root.nodes},getCurNode:function(){var b=this.container.attr("id");return n[b].curTreeNode},getSelectedNodes:function(b){var a=this.container.attr("id");if(a){b=b!=false;return na(n[a].root.nodes,b)}},getNodeByTId:function(b){var a=this.container.attr("id");if(a&&b)return aa(n[a].root.nodes,b)},expandAll:function(b){var a=this.container.attr("id");
ba(a,null,b)},expandNode:function(b,a,c){var d=this.container.attr("id");if(d&&b){if(c)ba(d,b,a);else b.open!=a&&ia(n[d],b);a&&G(d,b,a)}},selectNode:function(b){var a=this.container.attr("id");if(a&&b){V(n[a],b);G(a,b,true)}},addNodes:function(b,a){var c=this.container.attr("id");if(c&&a){b||(b=null);ka(n[c],b,a)}},moveNode:function(b,a){var c=this.container.attr("id");if(c&&a)if(!(b&&(a.parentNode==b||e("#"+a.tId).find("#"+b.tId).length>0))){b||(b=null);fa(n[c],b,a)}},removeNode:function(b){var a=
this.container.attr("id");if(a&&b){a=n[a];var c=b.parentNode==null?a.root:b.parentNode;if(a.curTreeNode===b)a.curTreeNode=null;e("#"+b.tId).remove();for(var d=-1,f=0;f<c.nodes.length;f++)if(c.nodes[f].tId==b.tId)d=f;d>=0&&c.nodes.splice(d,1);if(c.nodes.length<1){c.isParent=false;b=e("#"+c.tId+o);d=e("#"+c.tId+m);a=e("#"+c.tId+s);l(d,u);E(a,u);b.css("display","none")}else if(a.showLine){c.nodes[c.nodes.length-1].isLastNode=true;c.nodes[c.nodes.length-1].isFirstNode=c.nodes.length==1;b=e("#"+c.nodes[c.nodes.length-
1].tId+o);d=e("#"+c.nodes[c.nodes.length-1].tId+m);e("#"+c.nodes[c.nodes.length-1].tId+s);if(c==a.root)if(c.nodes.length==1)l(d,J);else{a=e("#"+c.nodes[0].tId+m);l(a,B);l(d,w)}else l(d,w);b.removeClass(C)}}}}}var O="ZTREE_CLICK",P="ZTREE_CHECK",Q="ZTREE_DRAG",H="ZTREE_DROP",R="ZTREE_ASYNC_SUCCESS",S="ZTREE_ASYNC_ERROR",T="ZTREE_CHECK_MAX_ERROR",m="_switch",s="_ico",o="_ul",v="_a",J="root",B="roots",K="center",w="bottom",da="noLine",C="line",x="open",t="close",u="docu",Z="curSelectedNode",$="tmpTargetTree",
M="tmpTargetNode",D="radio",sa="chk",qa="false",pa="true",ga="full",ha="part",ra="focus",W="all",n=[],U=0;e.fn.zTree=function(b,a){var c={treeObjId:"",checkable:false,editable:false,showLine:true,curTreeNode:null,dragStatus:0,dragNodeShowBefore:false,checkStyle:"checkbox",checkType:{Y:"ps",N:"ps"},checkRadioType:"level",checkRadioMaxNum:1,checkRadioCheckedList:[],async:false,asyncUrl:"",asyncParam:[],asyncParamOther:[],root:{isRoot:true,nodes:[]}};b&&e.extend(c,b);c.treeObjId=this.attr("id");c.root.tId=
-1;c.root.name="ZTREE ROOT";c.root.isRoot=true;c.checkRadioCheckedList=[];U=0;if(a)c.root.nodes=a;n[c.treeObjId]=c;e("#"+c.treeObjId).empty();if(c.root.nodes&&c.root.nodes.length>0)F(c,0,c.root.nodes);else c.async&&c.asyncUrl&&c.asyncUrl.length>0&&ja(c);oa(this);return(new ta).init(this)};var N=[]})(jQuery);