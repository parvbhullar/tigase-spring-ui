function ofcCreate(divName, ofcWidth, ofcHeight, ofcGetDataFuncName) 
{
  swfobject.wmode="transparent";
  swfobject.embedSWF("./openflashchart/open-flash-chart.swf",
     divName, ofcWidth, ofcHeight,
     "9.0.0","expressInstall.swf",
     //{"get-data" : ofcGetDataFuncName}
	 {"data-file" : ofcGetDataFuncName},
	 {"wmode" : "transparent"}
	 );
	   
}

function ofcCreateLarge(divName, ofcGetDataFuncName) 
{
	ofcCreate(divName, 818, 207, ofcGetDataFuncName);
}

function ofcCreateSmall(divName, ofcGetDataFuncName) 
{
	ofcCreate(divName, 398, 198, ofcGetDataFuncName);
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

function ofcUpdateDataAJAX(divName, ofcUrl)
{
		if(window.ActiveXObject)
		{
			innerXmlHttp = new ActiveXObject("Msxml2.XmlHttp");
		}
		else if(window.XMLHttpRequest)
		{
			innerXmlHttp =new XMLHttpRequest();
		}
		else
		{
			alert("no AJAX?");
		}
				
		innerXmlHttp.open("GET", ofcUrl, false);
		innerXmlHttp.send(null);
		var strReturn = innerXmlHttp.responseText;
		//alert(strReturn);
  	
  	var ofc = findSWF(divName);
	  ofc.load(strReturn);
}
