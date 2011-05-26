package com.ivyinfo.meeting.util;

import com.ivyinfo.framework.common.file.ResouceLoader;
import com.ivyinfo.framework.service.servlet.HttpRequester;
import com.ivyinfo.framework.service.servlet.HttpRespons;

public class MeetingInterface {
	
	public HttpRespons ToMeetingTo(String str) throws Exception{
		try{
			HttpRequester request = new HttpRequester();
			
			ResouceLoader resouceloader = new ResouceLoader();
			String meetingURL = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.meeting.api.meetingUrl");
			
			str.getBytes("UTF-8");
			
			
            HttpRespons hr = request.sendPost(meetingURL, request.base64Encode(str));
            
            return hr;
            
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
