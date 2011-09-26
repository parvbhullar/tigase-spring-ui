function airUui(jsonData){
    this.jsonData = jsonData;
}
airUui.prototype.trHtml=function(){
    var tr = [];
    var jsonUiDtat = this.jsonData.ui
    for(var ui in jsonUiDtat){
       var siginTr = jsonUiDtat[ui];
       this.type=siginTr.uiType;
       tr.push("<tr>");
       tr.push("<th>");
       tr.push(siginTr.uiName);
       tr.push("</th>");
       tr.push("<td>");
       tr.push(this.uiType());
       tr.push("</td>");
       tr.push("</tr>");
    }
    return tr.join("");
}
airUui.prototype.uiType=function(){
    if(this.type=='textarea'){
        return "<textarea class='htmlBox' id='showType1Box' style='width: 400px; height: 120px;'></textarea>";
    }
    if(this.type=='img'){
        return "<a herf='javascript:void(0)'>上传图片</a>";
    }
}
