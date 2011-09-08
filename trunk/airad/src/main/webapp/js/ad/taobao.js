/**
 * taobao事件处理
 *
 */
function getItemFroId(numIId, rel, srv,sr,but){
    $.ajaxSetup({
        async: false
    });
    $.post("/ad.do?action=ajaxTaobao", {
        numIid: numIId,
        uiId: rel
    }, function(mgs){
        if (mgs.err == 0) {
            but.next().hide();
            srv.html("没有发现商品");
            srv.show();
        }
        else {
            
            $str = sr;
            $num = $str.find(".numIID").val();
            $str.html("<th>淘宝商品</th><td>" + mgs.pic_title + "<div class='fr'><a href='"+mgs.detail_url+"' target='_blank'>去淘宝查看此商品</a> <span class='gray'>|</span> <a href='javascript:void(0)' onclick='javascript:$(this).parent().parent().parent().remove()'>删除</a></div><input type='hidden' class='half numIID' value='"+$num+"' /><input type='hidden' class='half title' value='"+mgs.pic_title+"' /><input type='hidden' class='half picUrl' value='"+mgs.pic_url+"' /><input type='hidden' class='half detailUrl' value='"+mgs.detail_url+"' /></td>");
            $(".trTaobaoDemo:first").clone().insertAfter($str);//复制淘宝信息初始化淘宝
            $newTr = $str.next();//新的淘宝名目
            $newTr.show();
            $newTr.addClass(mgs.uiId + "TaobaoTr");//改变tr值
            $addButt = $newTr.find(".addTaobao");
            $addButt.attr("rel", mgs.uiId);
            var sv = $newTr.find(".taobaoInfo");//错误信息
            $addButt.click(function(){
                par = $(this).parent().find(".taobaoInfo");
                if($("."+mgs.uiId+"TaobaoTr").size()==4)
                {
                    alert("每个页面最多只能建三个产品")
                    return;
                }
                $bu = $(this);
                $numIId = $bu.prev().val();
                if (isNaN($numIId)) {
                
                    par.html("淘宝号应为一串数字");
                    par.show();
                    return false;
                }
                getItemFroId($numIId, $bu.attr("rel"), par,$(this).parent().parent(),this);
            });
        }
        
    }, 'json')
}

function initTaobao(uiId){

    /**
     * begin taobao
     */
    $("." + uiId + "TaobaoTr").show();
    //解决选择其它模版淘宝输入框回不来的问题
    $taobaoDemo = $("." + uiId + "trTaobaoDemo");
    if ($taobaoDemo.length > 1) {
        $taobaoDemo.last().show();
        return;
    } 
    if ($("." + uiId +"TaobaoTr").length == 0) {
      $taobaoDemo.clone().insertAfter($taobaoDemo);//复制淘宝信息初始化淘宝
      $taobao = $taobaoDemo.next();//复制淘宝信息初始化淘宝
      $taobao.addClass(uiId + "TaobaoTr");//改变tr值
      $taobao.show();
    }
        taobaoInfo = '<div class="info divTaobaoInfo" id="infoClose" style="cursor:pointer"><a href="javascript:void(0)" class="fr"><img src="/images/ico_infoc.gif" width="14" height="14" alt="展开" /></a><strong>我要怎样添加淘宝商品？</strong><small>进入淘宝网，进入商品所在详细信息页面，获取商品URL上的ID </small>'
        taobaoInfo += '</div>'
        taobaoInfo += '<div class="info" id="infoOpen" style="display:none"><a href="javascript:void(0)" class="fr" id="infoBtn"><img src="/images/ico_infoo.gif" width="14" height="14" alt="收起" /></a><strong>我要怎样添加淘宝商品？</strong>'
        taobaoInfo += '<p>1，<a href="http://www.taobao.com" target="_blank">进入淘宝网</a>，进入商品所在详细信息页面，获取商品URL上的ID。如下图所示：</p>'
		taobaoInfo += '<p><img src="/images/pic_taobao.jpg" alt=" " height="131" width="519" /></p>'
        taobaoInfo += '<p>2，在输入框中输入商品的ID号，并点击提交，则该商品添加成功。</p>'
        taobaoInfo += '<p>3，若有多个淘宝商品的，还可以继续添加。</p>'
        taobaoInfo += '</div>'
        $taobao.find(".taobaoDesc").after(taobaoInfo)
        
         $("#infoClose").click(function(){
            $("#infoOpen").show();
            $("#infoClose").hide();
        })
        $("#infoBtn").click(function(){
            $("#infoOpen").hide();
            $("#infoClose").show();
        })
                                    
    
    $addButt = $taobao.find(".addTaobao")
    $addButt.attr("rel", uiId)
    var sv = $taobao.find(".taobaoInfo");//错误信息
    $addButt.click(function(){
        par = $(this).parent().find(".taobaoInfo");;
        $bu = $(this);
        $bu.next().show();
        $numIId = $bu.prev().val();
        if (isNaN($numIId)) {
        
            sv.html("淘宝号应为一串数字");
            sv.show();
            $bu.next().hide();
            return false;
        }
        getItemFroId($numIId, $bu.attr("rel"),  par,$(this).parent().parent(),$bu);
    });
    $delButt = $taobao.find(".delTaobao")
    $delButt.attr("rel", uiId);
    $delButt.click(function(){
        uiId = $(this).attr("rel")
        $(this).parent().parent().remove();
    });
	

    //--------end taobao
}

    //--------end taobao
    /**
 * 初始化boxhtml
 * @param {Object} uiId
 */
function initTaobao2(uiId){
    /**
     * begin word
     */
    var $taobao2 = $("." + uiId + "Taobao2Tr");
    $taobao2.find(".taobaoUrl").attr("id",uiId+"TaobaoUrl");
    $taobao2.find(".taobaoId").attr("id",uiId+"TaobaoId");
    
    
    $taobao2.show();
    //-------end word
}
