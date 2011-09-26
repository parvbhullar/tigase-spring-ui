/**
 * @author Administrator
 */
$.rightBottom=function(uiId,_width,_height){
           //滚动条距顶部的距离
           var st = document.documentElement.scrollTop+document.body.scrollTop;
           //滚动条距左边的距离 
           var sl = document.documentElement.scrollLeft || document.body.scrollLeft;
           //屏幕的高度
           var ch = document.documentElement.clientHeight;
           //屏幕的宽度
            var cw = document.documentElement.clientWidth;
            var bH = $("body").height();
            var objT = Number(st) + Number(ch)-_height>bH-_height?bH:Number(st) + Number(ch)-_height;
            objT=objT>document.documentElement.clientHeight?objT:document.documentElement.clientHeight-_height;
            var objL = Number(sl) + Number(cw)-_width;
            
            
            $(uiId).css({
                top: objT-30,
                left: objL
            });
}