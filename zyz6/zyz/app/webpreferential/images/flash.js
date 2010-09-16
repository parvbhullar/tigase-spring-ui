// s: source url
// d: flash id
// w: source width
// h: source height
// t: wmode
function mf(s,d,w,h,t){
	return "<object classid=\"clsid:d27cdb6e-ae6d-11cf-96b8-444553540000\" codebase=\"http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0\" width="+w+" height="+h+" id="+d+"><param name=wmode value="+t+" /><param name=movie value="+s+" /><param name=quality value=high /><embed src="+s+" quality=high wmode="+t+" type=\"application/x-shockwave-flash\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" width="+w+" height="+h+"></embed></object>";
}

// write document contents
function documentwrite(src){
	document.write(src);
}

// assign code innerHTML
function setcode(target, code){ 
	target.innerHTML = code; 
}