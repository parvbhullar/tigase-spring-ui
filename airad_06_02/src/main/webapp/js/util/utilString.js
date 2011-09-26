String.prototype.getAttribute = function(attribute){
    if (new RegExp("(^|;)\\s*" + attribute + "\\s*:\\s*([^;]*)\\s*(;|$)", "i").test(this)) 
        return RegExp.$2.replace(/%3B/gi, ";").replace(/%25/g, "%");
    return null;
};
String.prototype.setAttribute = function(attribute, value){
    value = ("" + value).replace(/%/g, "%25").replace(/;/g, "%3B").replace(/\r|\n/g, "");
    return (attribute + ":" + value + ";" + this);
};
String.prototype.deleteAttribute = function(attribute){
    return this.replace(new RegExp("\\b\\s*" + attribute + "\\s*:\\s*([^;]*)\\s*(;|$)", "gi"), "");
};
String.prototype.trim = function(){
return (this || "").replace( /^\s+|\s+$/g, "" );
}
String.prototype.isTel = function(){
       if(this.rtrim().length<7 || this.rtrim().length>18){
        return false;
       }
       else{
         return /^([0-9]|[\-])+$/.test(this.rtrim());
       }

}



String.prototype.ltrim = function(){

    return this.replace(/(^\s*)/g, "");
    
}


String.prototype.rtrim = function(){

    return this.replace(/(\s*$)/g, "");
    
}
String.prototype.maxLengthEn=function(textlength){
			var len=0;
			for(var i=0;i<this.length;i++){
				var intCode=this.charCodeAt(i);
				if (intCode>=0&&intCode<=128){
					len=len+1;
				}else{
					len=len+2;
				}
			}
            if (len > textlength) {
                return true;
            }
            return false;
            
}
Number.prototype.toFixed = function(len)//精确小数
{
    if (isNaN(len) || len == null) {
        len = 0;
    }
    else {
        if (len < 0) {
            len = 0;
        }
    }
    return Math.round(this * Math.pow(10, len)) / Math.pow(10, len);
}
function Map(){
    var struct = function(key, value){
        this.key = key;
        this.value = value;
    }
    
    var put = function(key, value){
        for (var i = 0; i < this.arr.length; i++) {
            if (this.arr[i].key === key) {
                this.arr[i].value = value;
                return;
            }
        }
        this.arr[this.arr.length] = new struct(key, value);
    }
    
    var get = function(key){
        for (var i = 0; i < this.arr.length; i++) {
            if (this.arr[i].key === key) {
                return this.arr[i].value;
            }
        }
        return null;
    }
    
    var remove = function(key){
        var v;
        for (var i = 0; i < this.arr.length; i++) {
            v = this.arr.pop();
            if (v.key === key) {
                continue;
            }
            this.arr.unshift(v);
        }
    }
    
    var size = function(){
        return this.arr.length;
    }
    
    var isEmpty = function(){
        return this.arr.length <= 0;
    }
    
    this.arr = new Array();
    this.get = get;
    this.put = put;
    this.remove = remove;
    this.size = size;
    this.isEmpty = isEmpty;
}
String.prototype.replaceAll = function (AFindText,ARepText){
                raRegExp = new RegExp(AFindText,"g");
                return this.replace(raRegExp,ARepText);
}