//引入图片剪切js
document.write("<script type='text/javascript' src='/js/util/jquery.Jcrop.js'></script>");


$(function(){

    //位于页面的位置高度和大小
    $(".impUp").width("500px");
    $(".impUp").height("300px");
    var popupHeight = $(".impUp").height();
    var popupWidth = $(".impUp").width();
    $(".impUp").css("postion", "absolute");
    $(".impUp").css("top", ($(window).height() - popupHeight) / 2 + $(window).scrollTop() + "px");
    $(".impUp").css("left", ($(window).width() - popupWidth) + $(window).scrollLeft() + "px");
    //$(".impUp").css("left", ($(window).width() - popupWidth) / 2 + $(window).scrollLeft() + "px");
    function showCoords(c){
    
        $.post("/utilAjax.do?action=upLoadImg", {
            x1: c.x,
            y1: c.y,
            x2: c.x2,
            y2: c.y2
        }, function(){
            $("#retuImg").attr("src", "/utilAjax.do?action=loadImg&t=" + Math.random());
            $("#bannerDemo").css("background-image", "url(/utilAjax.do?action=loadImg&t=" + Math.random() + ")");
            
        });
    }
    $('#cropbox').Jcrop({
        aspectRatio: 6 / 1, //选中区域宽高比为1，即选中区域为正方形
        bgColor: "#ccc", //裁剪时背景颜色设为灰色
        bgOpacity: 0.1, //透明度设为0.1
        allowResize: false, //不允许改变选中区域的大小
        setSelect: [0, 0, 30], //初始化选中区域
        //onChange:showCoords, //当选择区域变化的时候，执行对应的回调函数
        onSelect: showCoords //当选中区域的时候，执行对应的回调函数 
    });
    $("#sizeType").change(function(){
        $zt = $(this).val()
        //选择尺寸  1.300； 2.216；3.168；4.120
        
        
        if ($zt == "1") {
            $('#cropbox').Jcrop.setSelect([0, 0, 300]);
        }
        if ($zt == "2") {
            $('#cropbox').Jcrop.setSelect([0, 0, 216]);
        }
        if ($zt == "3") {
            $('#cropbox').Jcrop.setSelect([0, 0, 168]);
        }
        if ($zt == "4") {
            $('#cropbox').Jcrop.setSelect([0, 0, 120]);
        }
    })
})
//执行上传动作

$("#butUpload").click(function(){
    $.post("/utilAjax.do?action=upLoadImg", {
        x1: $("#txtX1").val(),
        y1: $("#txtY1").val(),
        x2: $("#txtX2").val(),
        y2: $("#txtY2").val()
    }, function(){
        $("#retuImg").attr("src", "/utilAjax.do?action=loadImg&t=" + Math.random());
        
    });
})

