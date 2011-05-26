package com.ivyinfo.meeting.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ivyinfo.framework.common.file.ResouceLoader;
import com.ivyinfo.framework.service.servlet.HttpRespons;
import com.ivyinfo.meeting.bean.MeetingBean;
import com.ivyinfo.meeting.bean.MeetingDetailBean;
import com.ivyinfo.meeting.bean.MeetingReturnBean;
import com.ivyinfo.meeting.bean.MeetingXMLBean;

public class StartMeeting implements MeetingXMLBean{
	
	public String StartMeetingUrl(MeetingDetailBean meetingDetailBean) throws Exception{
		try{
			System.err.println("getConfKey:"+meetingDetailBean.getConfKey());
			System.err.println("getPasswd:"+meetingDetailBean.getPasswd());
			ResouceLoader resouceloader = new ResouceLoader();
			String webBaseUrl = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.meeting.api.webBaseUrl");
			
//			stringbuf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//			stringbuf.append("<Message>");
//			stringbuf.append("<header>");
//			stringbuf.append("<action>startMeeting</action>");
//			stringbuf.append("<service>meeting</service>");
//			stringbuf.append("<type>xml</type>");
//			stringbuf.append("<siteName>box</siteName>");
//			stringbuf.append("<userName>admin</userName>");
//			stringbuf.append("<password>admin</password>");
//			stringbuf.append("</header>");
//			stringbuf.append("<body>");
//			stringbuf.append("<hostName>admin</hostName >");
//			stringbuf.append("<displayName>admin</displayName>");
//			stringbuf.append("<confKey>"+meetingDetailBean.getConfKey()+"</confKey>");
//			stringbuf.append("<meetingPwd>"+meetingDetailBean.getPasswd()+"</meetingPwd>");
//			stringbuf.append("<meetingEmail></meetingEmail>");
//			stringbuf.append("<webBaseUrl>"+webBaseUrl+"</webBaseUrl>");
//			stringbuf.append("</body>");
//			stringbuf.append("</Message>");
			
			MeetingDetailBean MDBean = new MeetingDetailBean();
			MDBean.setHostName("admin");
			MDBean.setDisplayName("admin");
			MDBean.setConfKey(meetingDetailBean.getConfKey());
			MDBean.setPasswd(meetingDetailBean.getPasswd());
			MDBean.setMeetingEmail("");
			MDBean.setWebBaseUrl(webBaseUrl);
			MDBean.setAttendees(false);
			
			MeetingBean MBean = new MeetingBean();
			MBean.setAction("startMeeting");
			MBean.setService("meeting");
			MBean.setType("xml");
			MBean.setSiteName("box");
			MBean.setUserName("admin");
			MBean.setPassword("admin");
			MBean.setMDBean(MDBean);
			
			JavaBeanToXML JBeanToXML = new JavaBeanToXML();
			String meetingxml = JBeanToXML.BeanToXML(MBean);
			
			System.err.println("meetingxml:"+meetingxml);
			
			MeetingInterface MeetingInterface = new MeetingInterface();
			HttpRespons hr = MeetingInterface.ToMeetingTo(meetingxml);
            
            String content = hr.getContent();
            System.err.println("content:"+content);
            
            XMLTo XMLTo = new XMLTo();
            List list = new ArrayList();
            list.add(RESULT);
            list.add(CIURL);
            list.add(TOKEN);
            MeetingReturnBean mrbean = XMLTo.XMLToValue(content,list);
            String result = mrbean.getResult();
            String ciurl = mrbean.getCiURL();
            String token = mrbean.getToken();
            
            return ciurl+"?siteId=1&dt=GMT&token="+token;
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
}
