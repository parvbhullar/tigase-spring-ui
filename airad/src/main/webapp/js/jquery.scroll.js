/**
 * scroll v1.0.0
 * Copyright 2011 airAd Oy
 * @author sunny
 * Date: 2011-07-12
 */
if (jQuery) {
  (function(a){
    a.extend(a.fn, {
        f:{
          buttonClass:'',
          adPicClass:'',
          interval:5000,
          gotoEnd:1000,
          moveHeigh:242,
          timeId:0,
          picCount:0,
          isStart:true,
          beforeClick:'',
          aftefClick:'',
          insertTab:function(picCount){
            var html="";
            html+='<div class="numBox">';
            for(var i=picCount-1;i>-1;i--){
              if (i == picCount-1) {
                html += '<div class="now adPicButton index_'+i+'" value="'+i+'"></div>';
              }else{
                 html += '<div class="adPicButton index_'+i+'" value="'+i+'"></div>';
              }
            }
            html+='</div>';
            return html;
          },
          buttonTab: function(obj) {
              var d = $(obj);
                a(this).f.isStart = true;
                var button = "index_" + d.attr("value");
                var nodePic = $("." + a(obj).f.adPicClass);
                var s = a(this).f.beforeClick();
                //点击计算
                var p = "";
                var qi  = Number(nodePic.last().attr("value")) - Number(d.attr("value"));
                     if(qi==2||qi==-1){
                       p=nodePic.prev();
                     }else{
                       p = nodePic.eq(1);
                     }
                     p.animate({
                      "margin-top": "0px"
                    }, a(obj).f.gotoEnd, function() {
                         var c=$("." + a(obj).f.adPicClass);
                         c.last().css("margin-top", "-" + a(obj).f.moveHeigh + "px").insertBefore(c.first());
                          if ($("." + a(this).f.adPicClass).is(":animated")) {
                            $("." + a(this).f.buttonClass).each(function() {
                              if ($(this).hasClass("now")) {
                                if ($("." + a(this).f.adPicClass).first().hasClass("pic_" + $(this).attr("value"))) {
                                  a(this).f.buttonTab(this);
                                  return false;
                                }
                              }
                            });
                          }
                    });
          }
        },
        scrollDown:function(b){
          var f = a.extend(a(this).f,b);a(this).f = f;var nodePic = $("."+f.adPicClass); 
          nodePic.each(function(i){$(this).addClass("pic_"+i).attr("value",i);});
          a(this).f.picCount=nodePic.size();
          var insertTab = a(this).f.insertTab(a(this).f.picCount);
          a(this).append(insertTab);
          a(this).start();
          
          //用于控制动画的开始和结束
          //停止
          nodePic.bind("mouseover",function(){
            a(this).stop();
            return false;
          });
          //开始
          nodePic.bind("mouseout",function(){
              a(this).start();
          });
          var button = $("."+f.buttonClass);
          button.click(function(){
             $("." + a(this).f.buttonClass).removeClass("now");
             $(this).addClass("now");
             if ($("." + a(this).f.adPicClass).is(":animated")) {
              return false;
            }
            a(this).f.isStart = false;
            a(this).f.buttonTab(this);
          });
          button.mouseover(function(){
             a(this).stop();
          });
        },start:function(){
           if(!a(this).f.isStart){return false;}
           a(this).f.timeId=setInterval(function(){
                var nodePic = $("."+a(this).f.adPicClass);
                var cb;//当前按钮
                //按钮效果
                var button = $("."+a(this).f.buttonClass);
                  button.each(function(){
                  if($(this).hasClass("now")){
                    button.removeClass("now");
                    if($(this).attr("value")==0){
                      cb=$(".index_"+(Number(a(this).f.picCount)-1));
                      cb.addClass("now");
                    }else{
                      cb=$(this).next();
                      cb.addClass("now");
                      
                    }
                    return false;
                  }
                });
                
                var s=true;
                //滑动前执行的事件
                if($.isFunction(a(this).f.beforeClick)){
                     s=a(this).f.beforeClick();
                }
                //滑动
                nodePic.each(function(){
                 if($(this).attr("value")==cb.attr("value")){
                      $(this).animate({"margin-top": "0px"}, a(this).f.gotoEnd,function(){
                      if($.isFunction(a(this).f.beforeClick)){
                        if (s) {
                          //滑动后执行的事件
                          a(this).f.aftefClick();
                        }
                      }
                      $(this).next().css("margin-top","-"+a(this).f.moveHeigh+"px").insertBefore(nodePic.first());
                    });
                    return false;
                 }
                });
            
            },a(this).f.interval);
              return false;
        },stop:function(){
           window.clearInterval(a(this).f.timeId);
           return false;
        },noStart:function(){
           a(this).f.isStart=false;
        }
    });
  })(jQuery)
};