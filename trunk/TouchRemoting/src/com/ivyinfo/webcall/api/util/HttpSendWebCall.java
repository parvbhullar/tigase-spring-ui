package com.ivyinfo.webcall.api.util;

import com.ivyinfo.framework.common.file.ResouceLoader;
import com.ivyinfo.framework.service.servlet.HttpRequester;
import com.ivyinfo.framework.service.servlet.HttpRespons;

public class HttpSendWebCall {
	
	public HttpRespons HttpSendWebCall(String str) throws Exception{
		try{
			HttpRequester request = new HttpRequester();
			
			ResouceLoader resouceloader = new ResouceLoader();
			String webcallURL = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.webcall.api.webcallurl");
			
//			str.getBytes("UTF-8");
			
			
            HttpRespons hr = request.sendPost(webcallURL, str);
            
            return hr;
            
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
}
