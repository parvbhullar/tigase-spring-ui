var ofc_get_data_vars = Array();

function ofc_ready(id)
{
	//alert("ofc_ready "+id);
}

function ofc_get_data_jsvar(id) {
	return ofc_get_data_vars[id];
}

function ofcCreate(divName, ofcWidth, ofcHeight, ofcData) 
{

	ofc_get_data_vars[divName] = JSON.stringify(ofcData);

  	swfobject.embedSWF("../js/openflashchart/open-flash-chart.swf",
    divName, ofcWidth, ofcHeight,
    "9.0.0","expressInstall.swf",
    {"get-data" : ofc_get_data_jsvar, "id" : divName});
     
}

function ofcCreateLargeDate(divName, ofcData) 
{
	ofcCreate(divName, 818, 207, ofcData);
}

function ofcCreateSmallDate(divName, ofcData) 
{
	ofcCreate(divName, 398, 198, ofcData);
}

function ofcCreateLarge(divName, url) 
{
	var  ofcData = ofc_ajax_call(url);

	ofcCreate(divName, 818, 207, ofcData);
}

function ofcCreateSmall(divName, url) 
{
	var  ofcData = ofc_ajax_call(url);
	ofcCreate(divName, 398, 198, ofcData);
}

function findSWF(movieName) 
{
	if (navigator.appName.indexOf("Microsoft")!= -1) 
	{
		return window[movieName];
	} 
	else 
	{
		return document[movieName];
	}
}

function ofcUpdateData(divName, ofcData)
{
	var ofc = findSWF(divName);
	ofc.load(JSON.stringify(ofcData));
}

function ofc_ajax_call(url)
{
		if(window.ActiveXObject)
		{
			innerXmlHttp1 = new ActiveXObject("Msxml2.XmlHttp");
		}
		else if(window.XMLHttpRequest)
		{
			innerXmlHttp1 =new XMLHttpRequest();
		}
		else
		{
			alert("no AJAX?");
		}
		
		var strUrl= encodeURI(url);
		var x = strUrl.indexOf("#");
		while(x >-1)
		{
			x = strUrl.indexOf("#");
			strUrl = strUrl.replace("#","%23");
		}	
		innerXmlHttp1.open("GET",strUrl,false);		
		innerXmlHttp1.send(null);		
		
		var strReturn = innerXmlHttp1.responseText;
		return strReturn;		
}	

function ajax_test()
{
	this.xmlHttp;
	this.divname;
	this.result;
	
	function createXMLHttpRequest() { 
	    if (window.ActiveXObject) {
	        this.xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	    } 
	    else if (window.XMLHttpRequest) {
	        this.xmlHttp = new XMLHttpRequest();
	    }
	}

	function dealAct(url,divname){
     	this.divname = divname;
	    createXMLHttpRequest();
	    xmlHttp.onreadystatechange = handleStateChange;
	    xmlHttp.open("GET",url);
	    xmlHttp.send(null);    
	}

	function handleStateChange(){
    if(xmlHttp.readystate == 4){    
            if(xmlHttp.status == 200){ 
                     this.result  = this.xmlHttp.responseText;
                }
        }else
        {
         document.getElementById(this.divname).innerHTML = "loading...";
        } 
	}
}



