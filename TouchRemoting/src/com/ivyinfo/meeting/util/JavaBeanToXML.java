package com.ivyinfo.meeting.util;

import java.util.HashMap;
import java.util.List;

import com.ivyinfo.meeting.bean.MeetingBean;
import com.ivyinfo.meeting.bean.MeetingDetailBean;
import com.ivyinfo.meeting.bean.MeetingXMLBean;

public class JavaBeanToXML implements MeetingXMLBean{
	
	private static final String XML_VERSION =
        "<?xml version=\"1.0\" encoding=\""
//		+ "UTF-8"
        + System.getProperty("file.encoding")
        + "\"?>";
	
	public String BeanToXML(MeetingBean MBean) throws Exception{
		try{
			StringBuffer stringbuf = new StringBuffer(XML_VERSION);
			
			stringbuf.append("<").append(MESSAGE).append(">");
			stringbuf.append("<").append(HEADER).append(">");
			
			stringbuf.append(MeetingToXML.createTagFromString(ACTION, MBean.getAction()));
			stringbuf.append(MeetingToXML.createTagFromString(SERVICE, MBean.getService()));
			stringbuf.append(MeetingToXML.createTagFromString(TYPE, MBean.getType()));
			stringbuf.append(MeetingToXML.createTagFromString(SITENAME, MBean.getSiteName()));
			stringbuf.append(MeetingToXML.createTagFromString(USERNAME, MBean.getUserName()));
			stringbuf.append(MeetingToXML.createTagFromString(PASSWORD, MBean.getPassword()));
			
			stringbuf.append("</").append(HEADER).append(">");
			stringbuf.append("<").append(BODY).append(">");
			
			MeetingDetailBean MDBean = MBean.getMDBean();
			stringbuf.append(MeetingToXML.createTagFromString(SUBJECT, MDBean.getSubject()));
			stringbuf.append(MeetingToXML.createTagFromString(STARTTIME, MDBean.getStartTime()));
			stringbuf.append(MeetingToXML.createTagFromString(ENDTIME, MDBean.getEndTime()));
			stringbuf.append(MeetingToXML.createTagFromString(TIMEZONEID, MDBean.getTimeZoneId()));
			stringbuf.append(MeetingToXML.createTagFromString(ATTAMOUNT, MDBean.getAttendeeAmount()));
			stringbuf.append(MeetingToXML.createTagFromString(HOSTNAME, MDBean.getHostName()));
			stringbuf.append(MeetingToXML.createTagFromString(CREATOR, MDBean.getCreator()));
			stringbuf.append(MeetingToXML.createTagFromString(OPENTYPE, MDBean.getOpenType()));
			stringbuf.append(MeetingToXML.createTagFromString(PASSWD, MDBean.getPasswd()));
			stringbuf.append(MeetingToXML.createTagFromString(CONPATTERN, MDBean.getConferencePattern()));
			stringbuf.append(MeetingToXML.createTagFromString(AGENDA, MDBean.getAgenda()));
			stringbuf.append(MeetingToXML.createTagFromString(MAILLOCAL, MDBean.getMailTemplateLocal()));
			stringbuf.append(MeetingToXML.createTagFromString(BEFORETIME, MDBean.getBeforehandTime()));
			stringbuf.append(MeetingToXML.createTagFromString(WEBBASEURL, MDBean.getWebBaseUrl()));
			
			stringbuf.append(MeetingToXML.createTagFromString(STARTHM, MDBean.getStartHourMinute()));
			stringbuf.append(MeetingToXML.createTagFromString(ENDHM, MDBean.getEndHourMinute()));
			stringbuf.append(MeetingToXML.createTagFromString(EFFTIME, MDBean.getEffectiveTime()));
			stringbuf.append(MeetingToXML.createTagFromString(EXPTIME, MDBean.getExpirationTime()));
			stringbuf.append(MeetingToXML.createTagFromString(REPTYPEKEY, MDBean.getRepeatTypeKey()));
			stringbuf.append(MeetingToXML.createTagFromString(REPTYPEVALUE, MDBean.getRepeatTypeValue()));
			
			stringbuf.append(MeetingToXML.createTagFromString(STARTDATESTART, MDBean.getStartDateStart()));
			stringbuf.append(MeetingToXML.createTagFromString(STARTDATEEND, MDBean.getStartDateEnd()));
			
			stringbuf.append(MeetingToXML.createTagFromString(CONFKEY, MDBean.getConfKey()));
			
			stringbuf.append(MeetingToXML.createTagFromString(ATTENDEENAME, MDBean.getAttendeeName()));
			stringbuf.append(MeetingToXML.createTagFromString(SERVERNAME, MDBean.getServerName()));
			
			stringbuf.append(MeetingToXML.createTagFromString(MEETINGPWD, MDBean.getPasswd()));
			stringbuf.append(MeetingToXML.createTagFromString(DISPLAYNAME, MDBean.getDisplayName()));
			stringbuf.append(MeetingToXML.createTagFromString(MEETINGEMAIL, MDBean.getMeetingEmail()));
			
			List attendee = (List)MDBean.getAttendee();
			if(MDBean.isAttendees()){
				stringbuf.append("<").append(ATTENDEES).append(">");
				
				for(int i=0;i<attendee.size();i++){
					HashMap hashmap = (HashMap)attendee.get(i);
					
					stringbuf.append("<").append(ATTENDEE).append(">");
					
					stringbuf.append(MeetingToXML.createTagFromString(NAME, String.valueOf(hashmap.get(NAME))));
					stringbuf.append(MeetingToXML.createTagFromString(EMAIL, String.valueOf(hashmap.get(EMAIL))));
					stringbuf.append(MeetingToXML.createTagFromString(PHONE, String.valueOf(hashmap.get(PHONE))));
					
					stringbuf.append("</").append(ATTENDEE).append(">");
				}
				stringbuf.append("</").append(ATTENDEES).append(">");
			}
			stringbuf.append("</").append(BODY).append(">");
			stringbuf.append("</").append(MESSAGE).append(">");
			
			return stringbuf.toString();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
