//引入地图js
//document.write("<script src='http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=true&amp;key=ABQIAAAA9a5YkX5EXikzhYWGwurA_xS_Oj5qpmefcyGOPtYq49ajgif6iRSodtnHBOTe_5WcLx-5SMLr34wytQ' type='text/javascript'></script>");
//document.write("<script src='http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=true&amp;key=ABQIAAAARh9NyD0w9ju6MPWHHWYQ8hS_Oj5qpmefcyGOPtYq49ajgif6iRSS-ZsFnbgeD4yUUM2nQl3N5fEqTA' type='text/javascript'></script>");
//测试key
//document.write("<script src='http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=true&amp;key=ABQIAAAA3pIy6xg3KGaElEdted5Y2BRSOBO0iMCcfhQuz8KwjLj4NOUOChS3K1zRUOaEwsBfaC9_KgXN1T2KkQ' type='text/javascript'></script>");
//正式key
//document.write("<script src='http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=true&amp;key=ABQIAAAATlXzMz1pV2EwnsBQmDMsTxRCcJ4X8cEjAUfnDd29fTa2N-y73hR-BRmNP9MZXpIwOFzS0erfcQoUZg' type='text/javascript'></script>");
document.write("<script src='/js/map/map_import.js' type='text/javascript'></script>");
//$.getScript("http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=true&amp;key=ABQIAAAATlXzMz1pV2EwnsBQmDMsTxRCcJ4X8cEjAUfnDd29fTa2N-y73hR-BRmNP9MZXpIwOFzS0erfcQoUZg");
//document.write("<script src='/js/map/map_import.js' type='text/javascript'></script>");
//document.write('<scr'+'ipt type="text/javascript" src="http://app.mapabc.com/apis?&t=flashmap&v=2.4&key=f03346eb3a99be025979045e8fa1a281c5159611cf6d3a336d76b49155bff8bfc7487d0934b3e0ec"></sc'+'ript>');
//document.write('<scr'+'ipt type="text/javascript" src="http://app.mapabc.com/apis?&t=ajaxmap&v=2.1.2&key=f03346eb3a99be025979045e8fa1a281c5159611cf6d3a336d76b49155bff8bfc7487d0934b3e0ec"></sc'+'ript>');

var markers;
var map;
var mgrs;
$(document).ready(function() {
    //全局变量 
    map = new Map()
    markers = new Map();
    mgrs = new Map();
});
function saveMap(uiId, sort) {
    uiId = uiId + "Map";
    var mak = "";
    if (markers.get(uiId) != null) {
    
        for (var i = 0; i < markers.get(uiId).length; i++) {
            if (markers.get(uiId)[i] != null) {
                mak += "".setAttribute("lat", markers.get(uiId)[i].getLatLng().lat()).setAttribute("lng", markers.get(uiId)[i].getLatLng().lng());
                mak += "-";
            }
        }
    }
    if (map != null) 
        posj = "".setAttribute("zoom", map.get(uiId).getZoom()).setAttribute("lat", map.get(uiId).getCenter().lat()).setAttribute("lng", map.get(uiId).getCenter().lng());
    bs = {
        adId: $("#adId").val(),//广告id
        richId: $(("#" + "richId" + (sort + 1))).val(),//富媒体id
        relId: $(("#" + uiId + "Id")).val(),
        richMediaTitle: $(("#richMediaTitle" + (sort + 1))).val(),//广告标题
        //guideId: i,
        sort: sort + 1,
        showType: "4",
        prosJson: posj,//地图信息
        delRichId: $("#delRichId").val(),
        makJson: mak
    }
    return bs;
}

//绘制地图
function desigeMap(uiId, jsPros, objMap) {
    mapv = map.get(uiId + "Map");
    var geoPoint0 = new GLatLng(jsPros.getAttribute("lat"), jsPros.getAttribute("lng")); //设置中心点（默认：南京）
    mapv.setCenter(geoPoint0, Number(jsPros.getAttribute("zoom"))); //设置缩放级别 ，默认10
    for (var i = 0; i < objMap.length; i++) 
        createMarker(uiId + "Map", objMap[i]);
    
}

//初始化地图
function showMap(uiId) {
    //new
    
 /*  
     var mapoption = new MMapOptions(); 
  
    mapoption.toolbar = MConstants.ROUND; //设置地图初始化工具条，ROUND:新版圆工具条 
  
   // mapoption.overviewMap = MConstants.SHOW; //设置鹰眼地图的状态，SHOW:显示，HIDE:隐藏（默认） 
  
   // mapoption.scale = MConstants.SHOW; //设置地图初始化比例尺状态，SHOW:显示（默认），HIDE:隐藏。 
  
    mapoption.zoom = 10;//要加载的地图的缩放级别 
  
    mapoption.center = new MLngLat(118.800201, 32.073964);//要加载的地图的中心点经纬度坐标 
  
    mapoption.language = MConstants.MAP_CN;//设置地图类型，MAP_CN:中文地图（默认），MAP_EN:英文地图 
  
    //mapoption.fullScreenButton = MConstants.SHOW;//设置是否显示全屏按钮，SHOW:显示（默认），HIDE:隐藏 
  
    //mapoption.centerCross = MConstants.SHOW;//设置是否在地图上显示中心十字,SHOW:显示（默认），HIDE:隐藏 
  
   // mapoption.toolbarPos=new MPoint(20,20); //设置工具条在地图上的显示位置 
  
    var mapObj = new MMap(uiId, mapoption); //地图初始化 
    map.put(uiId, mapObj);
    //showType1Map
   // GUnload()*/
    
   /* var mgr;*/
    
    var mm = new GMap2($(("#" + uiId))[0]);
    mm.addControl(new GSmallMapControl());
    mm.addControl(new GMapTypeControl());
    var geoPoint0 = new GLatLng(32.073964, 118.800201); //设置中心点（默认：南京）
    mm.setCenter(geoPoint0, 10); //设置缩放级别 ，默认10
    map.put(uiId, mm);
    
    
    $("#delAllControl").click(function() {
        map.clearOverlays();
        
        
    });
    //设置点
    var vm;
    
    //保存信息
    function saveData() {
        // var name = escape(document.getElementById("name").value);
        //  var address = escape(document.getElementById("address").value);
        var latlng = vm.getLatLng();
        var lat = latlng.lat();
        var lng = latlng.lng();
        vm.closeInfoWindow();
        
    }
    
    function delData() {
        vm.remove();
        
    }
}

//创建点
function createMarker(uiId, jsPros) {
  /* */ if (markers.get(uiId) != null) {
        var v = 0;
        for (var i = 0; i < markers.get(uiId).length; i++) {
            if (markers.get(uiId)[i] != null) 
                v++
            if (v == 5) {
                alert("每个地图最多只能设置5个点");
                return;
            }
        }
    }
    if (mgrs.get(uiId) == null) {
        mgr = new MarkerManager(map.get(uiId), {
            trackMarkers: true
        });
    }
    
    //样式
    var baseIcon = new GIcon(G_DEFAULT_ICON);
    
    baseIcon.shadow = "/images/ad/shadow50.png";
    
    baseIcon.iconSize = new GSize(20, 34);
    
    baseIcon.shadowSize = new GSize(37, 34);
    
    baseIcon.iconAnchor = new GPoint(9, 34);
    
    baseIcon.infoWindowAnchor = new GPoint(9, 2);
   
    
    var redIcon = new GIcon(baseIcon);
    
    redIcon.image = "/images/ad/marker.png";
    
    markerOptions = {
        icon: redIcon,
        draggable: true
    };
    var marker;
    if (jsPros != null) {
        marker = new GMarker(new GLatLng(jsPros.getAttribute("lat"), jsPros.getAttribute("lng")), markerOptions);
    }
    else {
        marker = new GMarker(map.get(uiId).getCenter(), markerOptions);
    }
    if (markers.get(uiId) == null) {
        air = new Array();
        air.push(marker);
        markers.put(uiId, air);
    }
    else {
        markers.get(uiId).push(marker);
        
    }
    var temIndex = markers.get(uiId).length;
    var html = "<a herf='javascript:void' onclick=removeMarker('" + uiId + "'," + temIndex + ")>删除</a>";
    GEvent.addListener(marker, "click", function() {
        marker.openInfoWindowHtml(html);
        vm = marker
    });
    GEvent.addListener(marker, "dragstart", function() {
        marker.closeInfoWindow();
        vm = marker
    });
    GEvent.addListener(marker, "dragend", function() {
        marker.openInfoWindowHtml(html);
        vm = marker
    });
    mgr.addMarkers(marker);
    map.get(uiId).addOverlay(marker);
    
}

function removeMarker(mmKey, num) {
    markers.get(mmKey)[Number(num) - 1].remove();
    markers.get(mmKey)[Number(num) - 1] = null;
}

function initMap(uiId) {

    $mapTr = $("#" + uiId + "MapTr");
    if ($mapTr.find(("#" + uiId + "Map")).val() != null) {
        $mapTr.show();
        $(("#" + uiId + "Map")).show();
        return;
    }
    $mapTr.find(".map").attr("id", uiId + "Map");//改变map值
    $mapTr.find(".mapId").attr("id", uiId + "MapId");//改变map值
    $mapTr.find(".mapCenter").attr("id", uiId + "MapCenter");//改变map值
    $addpoint = $mapTr.find(".addPoint")//地图添加节点按钮
    $addpoint.attr("id", uiId + "AddPoint");//改变Point值
    $addpoint.children("span").html(uiId + "Map");//改变Point值
    $delpoint = $mapTr.find(".delAllControl")//地图添加节点按钮
    $delpoint.attr("id", uiId + "delAllPoint");//改变Point值
    $delpoint.val(uiId + "Map");//改变Point值
   /**/ $delpoint.bind("click", function() {
        if (mgrs.get($(this).val()) != null) {
            mgrs.get($(this).val()).clearMarkers();
        }
    })
    $addpoint.bind("click", function() {
        //增加地图点
        createMarker($(this).children("span").html())
    })
    $mapTr.show();
    showMap(uiId + "Map");//传染你当前页面的
}
