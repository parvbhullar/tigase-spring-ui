<%@ page contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" src="/js/ad/ad_detail_add.js"></script>

<script type="text/javascript">
var value = $.cookie('_adauth${command.coreAd.adId}');
if(value==1){
	showBg("#leadToAuthPage");
}
$("#infoClose").click(function(){
									$("#infoOpen").show();
									$("#infoClose").hide();
									})
$("#infoBtn").click(function(){
									$("#infoOpen").hide();
									$("#infoClose").show();
									})
//下是初始化页面的效果
//因为要用到jstl标签 所以无法放到js文件中
//按照广告类型初始化页面
$(function(){
$("#btnLeadToAuth").click(function(){
    $.cookie('_adauth${command.coreAd.adId}', null);
    window.location="/advertiser.do?action=authenticatePage";
});
if ("${command.coreAd.adId}".length>0)
{
	$(".info").show();
}
//banner初始化
var mapUi=new Array();

var bannerHtml = '${command.banner.bannerHtml}';
getFree();
if(bannerHtml!="")
{
	var bannerColor= $("#bannerColor").val();
	if(bannerColor!=""){
		$(".selectModelByColor").removeClass("now");
		$(".selectModelByColor").each(function(){
			if($(this).attr("color")==bannerColor){
				$(this).addClass("now");
			 }
		});
	}
	$("#bannerDesign").html(bannerHtml);
	if($("#bannerDemo").html().trim().length>0){
		$(".trBannerDemo").show();
	}
	if(isIE6){
		  setTimeout(function(){
			  $('#bannerDemo').attr('class','expl');
              $(".bannerLogo").removeClass().addClass("bannerLogo");
			},100);
	}
	//
	if (bt == 5) {
	      $("#bannerDesc").show();
      $("#bannerDesc").html("您可以输入40个中文字。")
      $("#imgPatternSpan").html("jpeg,jpg,png。")
      $("#outKey").hide();
  }
  if (bt == 6) {
      $("#bannerDesc").show();
      $("#bannerDesc").html("您可以输入32个中文字。")
      $("#imgPatternSpan").html("jpeg,jpg,png。")
      $("#outKey").hide();
  }
  if (bt == 7) {
      $("#bannerDesc").show();
      $("#bannerDesc").html("您可以输入18个中文字。")
      $("#imgPatternSpan").html("jpeg,jpg,png。")
      $("#outKey").hide();
  }
  if (bt == 8) {
      $("#bannerDesc").show();
      $("#bannerDesc").html("您每帧可以输入32个中文字。")
      $("#imgPatternSpan").html("jpeg,jpg,png。")
      $("#outKey").show();
  }
  if (bt == 9) {
	  $("#bannerDesc").hide();
      $("#imgPatternSpan").html("jpeg,jpg,png,gif。")
      $("#outKey").hide();
  }
	$(".bannerImage").click(function(){
		  var bannerImg=$(".bannerImage");
			if(bannerImg.size()>0){
					var width=bannerImg.width();			
					var height=bannerImg.height();
					$("#imgSizeSpan").html(width+"*"+height);
				}
		 $("#uploadImgDiv").hide();
         $("#bannerImageData").attr("src","");
         $("#bannerImgId").val("");
         $("#uploadImgBannerButtonDiv").hide();
        showBg('#uploadBannerImgDiv');
        if($('#upLoadBannerImageUploader').size()==0){
                 initBannerFileUpload();
        }
         $("#uploadBannerImgDiv").data("uiid", $(this).attr("id"));
		});
	var $bannerOut =  $(".bannerOut");
	$bannerOut.attr("contenteditable",true);
	if($bannerOut.size()>1){
		var outKey="";
		$bannerOut.each(function(i){
		
		outKey += "<a href='javascript:void(0)' class='outKey' tag='" + $(this).attr("id") + "'>第" + ++i + "帧文字</a>";
		});
		$("#outKey").html(outKey);
		var $outKey = $(".outKey");
	    var currKey = $outKey.first().addClass("now");
	    var currIndex = 0;
	    $outKey.click(function(){
        var margin_left = $bannerOut.first().css("margin-left");
        var imgWidth = $bannerOut.first().prev().css("width");
        imgWidth = imgWidth.substring(0,imgWidth.length-2);
        margin_left = margin_left.substring(0,margin_left.length-2);
        if (!$(this).is(".now")) {
            if (currIndex == 0) {
                if(!isIE6){
                	  var left = Number(margin_left*3)+Number(imgWidth);
                    $(".bannerOut:eq(1)").css("margin-left", left);
                    }
            }
            var outUiId = $(this).attr("tag");
            if (currIndex == 2) {
            	if (isIE6) {
                    setTimeout(function(){
                     $(".bannerOut:eq(1)").hide();
                     if($(".bannerOut:eq(1)").text().length<10){
                       $(".bannerOut:eq(1)").text($(".bannerOut:eq(1)").text()+"          ");
                     }
                     if ($(".bannerOut:first").text().length < 10) {
                       $(".bannerOut:first").text($(".bannerOut:first").text() + "          ");
                     }
                     $(".bannerOut:first").show();
                     },0);
                }
                else {
            	$bannerOut.eq(1).animate({
                    top: 0
                }, 1000);
                $("#" + outUiId).animate({
                    top: 0
                }, 1000);
                }
                currIndex = 1;
            }
            else {
            	if (isIE6) {
                    setTimeout(function(){
                    $(".bannerOut:first").hide();
                    if($(".bannerOut:first").text().length<10){
                      $(".bannerOut:first").text($(".bannerOut:first").text()+"          ");
                    }
                     if($(".bannerOut:eq(1)").text().length<10){
                      $(".bannerOut:eq(1)").text($(".bannerOut:eq(1)").text()+"          ");
                    }
                   $(".bannerOut:eq(1)").show();
                   },0);
               }
               else {
            	$bannerOut.first().animate({
                    top: -54
                    
                }, 1000);
                
                $("#" + outUiId).animate({
                    top: -54
                }, 1000);
               }
                currIndex = 2;
            }
            $outKey.removeClass("now");
            $(this).addClass("now");
        }
    });
}
}
var bt = $("#bannerType").val();
if(!isNaN(bt)){
	if(bt>0){
	$(".trBannerDemo").show();
	}
}
//
if(${command.bannerTypeDeprecated}){
	$('#bannerDraftSave').hide();
}
//富媒体初始化
if($("#adType").val()==1){
    //wap
    $("#pic01").click();
    if($("#wapId").val().trim()!="")
    {
    $("#closeWap").click();
    }
}

if($("#adType").val()==2||$("#adType").val()==3||$("#adType").val()==4){ 
	if($("#adType").val()==2){
	    //单页面
	    $("#pic02").click();
	}
    //多页面-普通页面
    if($("#adType").val()==3)
    	$("#pic03").click();
	if($("#adType").val()==4)
		$("#pic04").click();
    //给rich赋值
     <c:if test="${command.adType==3||command.adType==4}">
      $("#adChildNum").val("${command.adChildNum}").change();
     </c:if>
    <c:forEach items="${command.richList}" var="rich" varStatus="vai">
      <c:if test="${(vai.index<command.adChildNum&&(command.adType==3||command.adType==4))||(vai.index<1&&command.adType==2)}">
      $("#"+"richMediaTitle"+"${rich.sort}").val("${rich.richMediaTitle}");
      $("#"+"richId"+"${rich.sort}").val("${rich.richId}");
     
      if("${rich.showType}"=="1")
      {
          changeChildPage("1","showType"+"${rich.sort}");
          showBoxArray.get("#showType" + "${rich.sort}" + "Box").set_text('${rich.richMediaContent}');
          $("#showType"+"${rich.sort}"+"WordPhone").val("${rich.phone}");
      }
      if("${rich.showType}"=="2")
      {
          changeChildPage("2","showType"+"${rich.sort}");
          showBoxArray.get("#showType" + "${rich.sort}" + "PicBox").set_text('${rich.richMediaContent}');
          $(("#"+"showType"+"${rich.sort}"+"FormatType")).val('${rich.formatType}');
          $(("#"+"showType"+"${rich.sort}"+"ImgId")).val('${rich.relId}');
          if("${rich.relId}"!="")
          $(("#"+"showType"+"${rich.sort}"+"ImgId")).prev().attr("src","/fileuploading.do?action=loadSessionCropImg&fileName=${rich.relId}&t=" + Math.random()).show();
          $("#showType"+"${rich.sort}"+"WordPicPhone").val("${rich.phone}");
      }
      if("${rich.showType}"=="3")
      {
    	  //$("#adChildNum").val("${command.adChildNum}").change()
          changeChildPage("3","showType"+"${rich.sort}");
         // uiId = $(("#"+"showType"+"${rich.sort}" + "PicId")).val('${rich.relId}');imgCont
         uiId ="showType"+"${rich.sort}";
         var imgCont = '${rich.relId}';
          if(imgCont!=""){
	          var imgs = imgCont.split(",");
	          for(var i=0;i<imgs.length;i++){
	        	  $box = $(("." + uiId + "norPicBox:last")).clone().insertAfter(("." + uiId + "norPicBox:last"));
	              $box.show().find(".imgId").val(imgs[i]);
	              $box.find(".delImg").click(function(){
	                  $(this).parent().remove();
	              });
	              
	              $(("." + uiId + "picImg:last")).attr("src", "/fileuploading.do?action=loadSessionCropImg&fileName=" + imgs[i] + "&t=" + Math.random());
	          }
          }
      }
      if("${rich.showType}"=="4")
      {
    	  //$("#adChildNum").val("${command.adChildNum}").change()
          changeChildPage("4","showType"+"${rich.sort}");
          $("#"+"showType"+"${rich.sort}"+"MapId").val("${rich.relId}");
          var jsPros = "${rich.map.prosJson}";
          var optionArr = new Array();
          <c:forEach items="${rich.map.libPointInfoList}" var="option">
          	optionArr.push("${option.prosJson}");
          </c:forEach>
          desigeMap("showType"+"${rich.sort}",jsPros,optionArr);
          mapUi.push("showType"+"${rich.sort}"+"Map");
          
      }
      
      if("${rich.showType}"=="5")
      {
    	  changeChildPage("5","showType"+"${rich.sort}");
    	  var rel = "${rich.relId}";
    	  if(rel!="")
    	  {
        	  var rels = rel.split(",");
        	  for(var i=0;i<rels.length;i++)
        	  {
				  taobaotr = $(("."+"showType"+"${rich.sort}"+"TaobaoTr"));
        		  taobaotr.find(".numIID").last().val(rels[i]);
				  taobaotr.find(".addTaobao").last().click();
              }
          }
         
      }
     
      if("${rich.showType}"=="6")
      {
    	  changeChildPage("6","showType"+"${rich.sort}");
    	  var rel = "${rich.relId}";
    	  $("#showType"+"${rich.sort}"+"TaobaoUrl").val("${rich.libTaobao.taobaoUrl}");
    	  $("#showType"+"${rich.sort}"+"TaobaoId").val("${rich.libTaobao.id}");
      }
      if("${rich.showType}"=="7"||"${rich.showType}"=="8")
      {
          
    	  changeChildPage("${rich.showType}","showType"+"${rich.sort}");
    	  var rel = "${rich.relId}";
    	  $("#showType"+"${rich.sort}"+"MarketId").val("${rich.libMarket.id}");
    	  $("#showType"+"${rich.sort}"+"MarketName").val("${rich.libMarket.marketName}");
    	  $("#showType"+"${rich.sort}"+"MarketImgId").val("${rich.libMarket.marketImg}");
    	  <c:forEach items="${rich.libMarket.libMarketDetailList}" var="detail" >
    	  <c:choose>
    	  <c:when test="${detail.marketType==1}">
    	  $("#showType"+"${rich.sort}"+"MarketAndroidUrl").val("${detail.marketUrl}");
    	  $("#showType"+"${rich.sort}"+"MarketAndroidId").val("${detail.id}");
    	  </c:when>
    	  <c:when test="${detail.marketType==2}">
    	  $("#showType"+"${rich.sort}"+"MarketIphoneUrl").val("${detail.marketUrl}");
    	  $("#showType"+"${rich.sort}"+"MarketIponeId").val("${detail.id}");
    	  </c:when>
    	  </c:choose>
    	  
    	  </c:forEach>
    	  $("#showType"+"${rich.sort}"+"MarketImg").attr("src","/fileuploading.do?action=loadSessionCropImg&fileName=${rich.libMarket.marketImg}&t=" + Math.random()).show();
      }
	      <c:if test="${rich.richId!=null}">
	     	 $(("#close"+"${rich.sort}")).click();
	      </c:if>
      </c:if>
    </c:forEach>
    
}
 for(var i=0;i<mapUi.length;i++)
 	map.get(mapUi[i]).checkResize();
 //按钮初始化
 if($("#adId").val()!=""){
   $("#saveForm").show();
   $("#saveForm").next().show();
  }
})

$("#fish").show();
$(".loading").hide();
$(".previewColse").click(function(){
	if($("#previewPage").contents().find("#picBox").html()!=null){
		$("#previewPage").contents().find("#picBox").stopTime();
	}
	closeBg('#preview');
})
$("#previewWapShow").click(function(){
	showBg('#preview');
})
$(document).ready(function(){
	addCss("listCampaign");
});
</script>
