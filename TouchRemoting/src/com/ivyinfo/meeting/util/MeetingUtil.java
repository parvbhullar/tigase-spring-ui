package com.ivyinfo.meeting.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.ivyinfo.framework.common.file.ResouceLoader;
import com.ivyinfo.framework.common.time.TimeTools;
import com.ivyinfo.framework.service.servlet.HttpRequester;
import com.ivyinfo.framework.service.servlet.HttpRespons;
import com.ivyinfo.meeting.bean.MeetingBean;
import com.ivyinfo.meeting.bean.MeetingDetailBean;
import com.ivyinfo.meeting.bean.MeetingReturnBean;
import com.ivyinfo.meeting.bean.MeetingXMLBean;
import com.ivyinfo.session.bean.SessionUserBean;
import com.ivyinfo.user.bean.UserBean;

public class MeetingUtil implements MeetingXMLBean{
	
	/**
	 * 新增预约会议
	 * @param request
	 * @param str_userid
	 * @param str_machineid
	 * @throws Exception
	 */
	public String SubmitMeeting(MeetingDetailBean meetingDetailBean,UserBean userBean) throws Exception{
		try{
			String vc_name = meetingDetailBean.getSubject();
			vc_name = (vc_name == null)?"":vc_name;
			
			String vc_password = meetingDetailBean.getPasswd();
			vc_password = (vc_password == null)?"":vc_password;
			
			String vc_conferencePattern = meetingDetailBean.getConferencePattern();
			vc_conferencePattern = (vc_conferencePattern == null)?"":vc_conferencePattern;
			
			String vc_attendeeAmount = meetingDetailBean.getAttendeeAmount();
			vc_attendeeAmount = (vc_attendeeAmount == null)?"":vc_attendeeAmount;
			
			String vc_hostName = meetingDetailBean.getHostName();
			vc_hostName = (vc_hostName == null)?"":vc_hostName;
			
			String vc_agenda = meetingDetailBean.getAgenda();
			vc_agenda = (vc_agenda == null)?"":vc_agenda;
			
			String vc_openType = meetingDetailBean.getOpenType();
			vc_openType = (vc_openType == null)?"":vc_openType;
			
			List attendee = meetingDetailBean.getAttendee();
			
			
			String startdate = meetingDetailBean.getStartTime();
			//System.out.println("meetingstarttime: " + startdate);
			String enddate = meetingDetailBean.getEndTime();
			
			String logname = userBean.getLogname();
			String password = userBean.getPassword();
			System.err.println("logname:"+logname);
			System.err.println("password:"+password);
			
			/**
			 * 将开始和结束时间转换为0时区
			 */
			TimeTools TimeTools = new TimeTools();
			Date startdatatime = TimeTools.getDateFormatYMDHMSString(startdate);
			//System.out.println("startdatatime :" + startdatatime);
			String fromFormat = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat format = new SimpleDateFormat (fromFormat);
			TimeZone zone = TimeZone.getTimeZone("GMT+0");
			format.setTimeZone(zone);
			String stime = format.format(startdatatime);
			System.out.println("stime:"+stime);
			
			Date enddatatime = TimeTools.getDateFormatYMDHMSString(enddate);
			format.setTimeZone(zone);
			String etime = format.format(enddatatime);
			System.out.println("etime:"+etime);
			
			String startTime = stime.replace(" ", "T");
			
			String endTime = etime.replace(" ", "T");
			
			ResouceLoader resouceloader = new ResouceLoader();
			String webBaseUrl = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.meeting.api.webBaseUrl");
			
			MeetingDetailBean MDBean = new MeetingDetailBean();
			MDBean.setSubject(vc_name);
			MDBean.setPasswd(vc_password);
			MDBean.setStartTime(startTime);
			MDBean.setEndTime(endTime);
			MDBean.setConferencePattern(vc_conferencePattern);
			MDBean.setAttendeeAmount(vc_attendeeAmount);
			MDBean.setHostName(vc_hostName);
			MDBean.setAgenda(vc_agenda);
			MDBean.setOpenType(vc_openType);
			MDBean.setTimeZoneId("45");
			MDBean.setCreator(logname);
			MDBean.setMailTemplateLocal("zh_CN");
			MDBean.setBeforehandTime("0");
			MDBean.setWebBaseUrl(webBaseUrl);
			MDBean.setAttendees(true);
			
			/*List attendee = new ArrayList();
			HashMap hashmap = new HashMap();
			hashmap.put("name", "aaa");
			hashmap.put("email", "xwllh627@gmail.com");
			hashmap.put("phone", "13988851578");
			
			attendee.add(hashmap);
			
			hashmap = new HashMap();
			hashmap.put("name", "bbb");
			hashmap.put("email", "826467077@qq.com");
			hashmap.put("phone", "13988851000");
			
			attendee.add(hashmap);*/
			
			MDBean.setAttendee(attendee);
			
			MeetingBean MBean = new MeetingBean();
			MBean.setAction("createReserveMeeting");
			MBean.setService("meeting");
			MBean.setType("xml");
			MBean.setSiteName("box");
			MBean.setUserName(logname);
			MBean.setPassword(password);
			MBean.setMDBean(MDBean);
			
			JavaBeanToXML JBeanToXML = new JavaBeanToXML();
			String meetingxml = JBeanToXML.BeanToXML(MBean);
			
			System.err.println("meetingxml:"+meetingxml);
			
			MeetingInterface MeetingInterface = new MeetingInterface();
			HttpRespons hr = MeetingInterface.ToMeetingTo(meetingxml);
			System.out.println("returnxml:"+hr.getContent());
			
			HttpRequester request = new HttpRequester();
			String content = request.base64Decode(hr.getContent());
			System.err.println("content:"+content);
			
			String result = content.substring(content.indexOf("<result>")+8,content.indexOf("</result>"));
			String returnerr = "";
			if("FAILURE".equals(result)){
				returnerr = content.substring(content.indexOf("<reason>")+8,content.indexOf("</reason>"));
			}
			return returnerr;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 新增固定会议
	 * @param request
	 * @param str_userid
	 * @param str_machineid
	 * @throws Exception
	 */
	public String FixedMeeting(MeetingDetailBean meetingDetailBean,UserBean userBean) throws Exception{
		try{
			String vc_name = meetingDetailBean.getSubject();
			vc_name = (vc_name == null)?"":vc_name;
			
			String vc_password = meetingDetailBean.getPasswd();
			vc_password = (vc_password == null)?"":vc_password;
			
			String vc_attendeeAmount = meetingDetailBean.getAttendeeAmount();
			vc_attendeeAmount = (vc_attendeeAmount == null)?"":vc_attendeeAmount;
			
			String vc_agenda = meetingDetailBean.getAgenda();
			vc_agenda = (vc_agenda == null)?"":vc_agenda;
			
			String vc_openType = meetingDetailBean.getOpenType();
			vc_openType = (vc_openType == null)?"":vc_openType;
			
			List attendee = meetingDetailBean.getAttendee();
			
			String startdate = meetingDetailBean.getStartTime();
			
			String enddate = meetingDetailBean.getEndTime();
			
			String timeZoneId = meetingDetailBean.getTimeZoneId();
			timeZoneId = (timeZoneId == null)?"":timeZoneId;
			
			String logname = userBean.getLogname();
			String password = userBean.getPassword();
			System.err.println("logname:"+logname);
			System.err.println("password:"+password);
			
			/**
			 * 将开始和结束时间转换为0时区
			 */
			TimeTools TimeTools = new TimeTools();
			Date startdatatime = TimeTools.getDateFormatYMDHMSString(startdate);
			String fromFormat = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat format = new SimpleDateFormat (fromFormat);
			TimeZone zone = TimeZone.getTimeZone("GMT+0");
			format.setTimeZone(zone);
			String stime = format.format(startdatatime);
			System.out.println("stime:"+stime);
			
			Date enddatatime = TimeTools.getDateFormatYMDHMSString(enddate);
			format.setTimeZone(zone);
			String etime = format.format(enddatatime);
			System.out.println("etime:"+etime);
			
			String startTime = stime.replace(" ", "T");
			
			String endTime = etime.replace(" ", "T");
			
			ResouceLoader resouceloader = new ResouceLoader();
			String webBaseUrl = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.meeting.api.webBaseUrl");
			
			MeetingDetailBean MDBean = new MeetingDetailBean();
			MDBean.setSubject(vc_name);
			MDBean.setStartTime(startTime);
			MDBean.setEndTime(endTime);
			MDBean.setTimeZoneId(timeZoneId);
			MDBean.setAttendeeAmount(vc_attendeeAmount);
			MDBean.setHostName(logname);
			MDBean.setOpenType(vc_openType);
			MDBean.setPasswd(vc_password);
			MDBean.setAgenda(vc_agenda);
			MDBean.setMailTemplateLocal("zh_CN");
			MDBean.setBeforehandTime("0");
			MDBean.setWebBaseUrl(webBaseUrl);
			MDBean.setAttendees(true);
			
			/*List attendee = new ArrayList();
			HashMap hashmap = new HashMap();
			hashmap.put("name", "aaa");
			hashmap.put("email", "aaa@163.com");
			hashmap.put("phone", "13988851578");
			
			attendee.add(hashmap);
			
			hashmap = new HashMap();
			hashmap.put("name", "bbb");
			hashmap.put("email", "bbb@163.com");
			hashmap.put("phone", "13988851000");
			
			attendee.add(hashmap);*/
			
			MDBean.setAttendee(attendee);
			
			MeetingBean MBean = new MeetingBean();
			MBean.setAction("createFixedMeeting");
			MBean.setService("meeting");
			MBean.setType("xml");
			MBean.setSiteName("box");
			MBean.setUserName(logname);
			MBean.setPassword(password);
			MBean.setMDBean(MDBean);
			
			JavaBeanToXML JBeanToXML = new JavaBeanToXML();
			String meetingxml = JBeanToXML.BeanToXML(MBean);
			
			System.err.println("meetingxml:"+meetingxml);
			
			MeetingInterface MeetingInterface = new MeetingInterface();
			HttpRespons hr = MeetingInterface.ToMeetingTo(meetingxml);
			System.out.println("returnxml : " + hr.getContent());
			
			HttpRequester request = new HttpRequester();
			String content = request.base64Decode(hr.getContent());
			System.err.println("content:"+content);
			
			String result = content.substring(content.indexOf("<result>")+8,content.indexOf("</result>"));
			String returnerr = "";
			if("FAILURE".equals(result)){
				returnerr = content.substring(content.indexOf("<reason>")+8,content.indexOf("</reason>"));
			}
			return returnerr;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 新增即时会议
	 * @param request
	 * @param str_userid
	 * @param str_machineid
	 * @throws Exception
	 */
	public String InstantMeeting(MeetingDetailBean meetingDetailBean,UserBean userBean) throws Exception{
		try{
			
			
			String vc_name = meetingDetailBean.getSubject();
			vc_name = (vc_name == null)?"":vc_name;
			
			String vc_password = meetingDetailBean.getPasswd();
			vc_password = (vc_password == null)?"":vc_password;
			
			String vc_conferencePattern = meetingDetailBean.getConferencePattern();
			vc_conferencePattern = (vc_conferencePattern == null)?"":vc_conferencePattern;
			
			String vc_attendeeAmount = meetingDetailBean.getAttendeeAmount();
			vc_attendeeAmount = (vc_attendeeAmount == null)?"":vc_attendeeAmount;
			
			String vc_openType = meetingDetailBean.getOpenType();
			vc_openType = (vc_openType == null)?"":vc_openType;
			
			String enddate = meetingDetailBean.getEndTime();
			
			String logname = userBean.getLogname();
			String password = userBean.getPassword();
			System.err.println("logname:"+logname);
			System.err.println("password:"+password);
			
			/**
			 * 将开始和结束时间转换为0时区
			 */
			TimeTools TimeTools = new TimeTools();
			Date startdatatime = TimeTools.getDateFormatYMDHMSString(TimeTools.getCurrentTime());
			String fromFormat = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat format = new SimpleDateFormat (fromFormat);
			TimeZone zone = TimeZone.getTimeZone("GMT+0");
			format.setTimeZone(zone);
			String stime = format.format(startdatatime);
			System.out.println("stime:"+stime);
			
			Date enddatatime = TimeTools.getDateFormatYMDHMSString(enddate);
			format.setTimeZone(zone);
			String etime = format.format(enddatatime);
			System.out.println("etime:"+etime);
			
			String startTime = stime.replace(" ", "T");
			
			String endTime = etime.replace(" ", "T");
			
			ResouceLoader resouceloader = new ResouceLoader();
			String webBaseUrl = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.meeting.api.webBaseUrl");
			
			MeetingDetailBean MDBean = new MeetingDetailBean();
			MDBean.setSubject(vc_name);
			MDBean.setPasswd(vc_password);
			MDBean.setStartTime(startTime);
			MDBean.setEndTime(endTime);
			MDBean.setConferencePattern(vc_conferencePattern);
			MDBean.setAttendeeAmount(vc_attendeeAmount);
			MDBean.setHostName(logname);
			MDBean.setOpenType(vc_openType);
			MDBean.setTimeZoneId("45");
			MDBean.setCreator(logname);
			MDBean.setMailTemplateLocal("zh_CN");
			MDBean.setWebBaseUrl(webBaseUrl);
			MDBean.setAttendees(false);
			
			MeetingBean MBean = new MeetingBean();
			MBean.setAction("createInstantMeeting_touch");
			MBean.setService("meeting");
			MBean.setType("xml");
			MBean.setSiteName("box");
			MBean.setUserName(logname);
			MBean.setPassword(password);
			MBean.setMDBean(MDBean);
			
			JavaBeanToXML JBeanToXML = new JavaBeanToXML();
			String meetingxml = JBeanToXML.BeanToXML(MBean);
			
			System.err.println("meetingxml:"+meetingxml);
			
			MeetingInterface MeetingInterface = new MeetingInterface();
			HttpRespons hr = MeetingInterface.ToMeetingTo(meetingxml);
			
			HttpRequester request = new HttpRequester();
			String content = request.base64Decode(hr.getContent());
			System.err.println("content:"+content);
			
			String result = content.substring(content.indexOf("<result>")+8,content.indexOf("</result>"));
			String returnerr = "";
			if("FAILURE".equals(result)){
				returnerr = content.substring(content.indexOf("<reason>")+8,content.indexOf("</reason>"));
			}
			return returnerr;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 新增周期会议
	 * @param request
	 * @param str_userid
	 * @param str_machineid
	 * @throws Exception
	 */
	public String RegularMeeting(MeetingDetailBean meetingDetailBean,UserBean userBean) throws Exception{
		try{
			String vc_name = meetingDetailBean.getSubject();
			vc_name = (vc_name == null)?"":vc_name;
			
			String vc_password = meetingDetailBean.getPasswd();
			vc_password = (vc_password == null)?"":vc_password;
			
			String repeatTypeKey = meetingDetailBean.getRepeatTypeKey();
			repeatTypeKey = (repeatTypeKey == null)?"":repeatTypeKey;
			
			String vc_repeatTypeValue = meetingDetailBean.getRepeatTypeValue();
			vc_repeatTypeValue = (vc_repeatTypeValue == null)?"":vc_repeatTypeValue;
			
			String vc_conferencePattern = meetingDetailBean.getConferencePattern();
			vc_conferencePattern = (vc_conferencePattern == null)?"":vc_conferencePattern;
			
			String vc_attendeeAmount = meetingDetailBean.getAttendeeAmount();
			vc_attendeeAmount = (vc_attendeeAmount == null)?"":vc_attendeeAmount;
			
			String vc_hostName = meetingDetailBean.getHostName();
			vc_hostName = (vc_hostName == null)?"":vc_hostName;
			
			String vc_agenda = meetingDetailBean.getAgenda();
			vc_agenda = (vc_agenda == null)?"":vc_agenda;
			
			String vc_openType = meetingDetailBean.getOpenType();
			vc_openType = (vc_openType == null)?"":vc_openType;
			
			List attendee = meetingDetailBean.getAttendee();
			
			String startHourMinute = meetingDetailBean.getStartHourMinute();
			String endHourMinute = meetingDetailBean.getEndHourMinute();
			
			String effTime = meetingDetailBean.getEffectiveTime() + " 00:00:00";
			
			String expTime = meetingDetailBean.getExpirationTime() + " 00:00:00";
			
			String logname = userBean.getLogname();
			String password = userBean.getPassword();
			System.err.println("logname:"+logname);
			System.err.println("password:"+password);
			
			/**
			 * 将开始和结束时间转换为0时区
			 */
			TimeTools TimeTools = new TimeTools();
			Date startdatatime = TimeTools.getDateFormatYMDHMSString(effTime);
			String fromFormat = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat format = new SimpleDateFormat (fromFormat);
			TimeZone zone = TimeZone.getTimeZone("GMT+0");
			format.setTimeZone(zone);
			String stime = format.format(startdatatime);
			System.out.println("stime:"+stime);
			
			Date enddatatime = TimeTools.getDateFormatYMDHMSString(expTime);
			format.setTimeZone(zone);
			String etime = format.format(enddatatime);
			System.out.println("etime:"+etime);
			
			String effectiveTime = stime.replace(" ", "T");
			
			String expirationTime = etime.replace(" ", "T");
			
			ResouceLoader resouceloader = new ResouceLoader();
			String webBaseUrl = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.meeting.api.webBaseUrl");
			
			MeetingDetailBean MDBean = new MeetingDetailBean();
			MDBean.setSubject(vc_name);
			MDBean.setStartHourMinute(startHourMinute);
			MDBean.setEndHourMinute(endHourMinute);
			MDBean.setEffectiveTime(effectiveTime);
			MDBean.setExpirationTime(expirationTime);
			MDBean.setRepeatTypeKey(repeatTypeKey);
			MDBean.setRepeatTypeValue(vc_repeatTypeValue);
			MDBean.setPasswd(vc_password);
			MDBean.setConferencePattern(vc_conferencePattern);
			MDBean.setAttendeeAmount(vc_attendeeAmount);
			MDBean.setHostName(vc_hostName);
			MDBean.setAgenda(vc_agenda);
			MDBean.setOpenType(vc_openType);
			MDBean.setTimeZoneId("45");
			MDBean.setCreator(logname);
			MDBean.setMailTemplateLocal("zh_CN");
			MDBean.setBeforehandTime("0");
			MDBean.setWebBaseUrl(webBaseUrl);
			MDBean.setAttendees(true);
			
			/*List attendee = new ArrayList();
			HashMap hashmap = new HashMap();
			hashmap.put("name", "aaa");
			hashmap.put("email", "aaa@163.com");
			hashmap.put("phone", "13988851578");
			
			attendee.add(hashmap);
			
			hashmap = new HashMap();
			hashmap.put("name", "bbb");
			hashmap.put("email", "bbb@163.com");
			hashmap.put("phone", "13988851000");
			
			attendee.add(hashmap);*/
			
			MDBean.setAttendee(attendee);
			
			MeetingBean MBean = new MeetingBean();
			MBean.setAction("createRegularMeeting");
			MBean.setService("meeting");
			MBean.setType("xml");
			MBean.setSiteName("box");
			MBean.setUserName(logname);
			MBean.setPassword(password);
			MBean.setMDBean(MDBean);
			
			JavaBeanToXML JBeanToXML = new JavaBeanToXML();
			String meetingxml = JBeanToXML.BeanToXML(MBean);
			
			System.err.println("meetingxml:"+meetingxml);
			
			MeetingInterface MeetingInterface = new MeetingInterface();
			HttpRespons hr = MeetingInterface.ToMeetingTo(meetingxml);
			System.out.println("returnxml : " + hr.getContent());
			
			HttpRequester request = new HttpRequester();
			String content = request.base64Decode(hr.getContent());
			System.err.println("content:"+content);
			
			String result = content.substring(content.indexOf("<result>")+8,content.indexOf("</result>"));
			String returnerr = "";
			if("FAILURE".equals(result)){
				returnerr = content.substring(content.indexOf("<reason>")+8,content.indexOf("</reason>"));
			}
			return returnerr;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 查看所有会议信息
	 * @return
	 * @throws Exception
	 */
	public MeetingReturnBean getMeetingMessage(UserBean userBean) throws Exception{
		try{
//			String startdate = "2010-11-01 00:00:00";
//			String enddate = "2010-11-30 00:00:00";
			String startdate = "";
			String enddate = "";
			
			String logname = userBean.getLogname();
			String password = userBean.getPassword();
			System.err.println("logname:"+logname);
			System.err.println("password:"+password);
			
			/**
			 * 将开始和结束时间转换为0时区
			 */
			TimeTools TimeTools = new TimeTools();
			String fromFormat = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat format = new SimpleDateFormat (fromFormat);
			TimeZone zone = TimeZone.getTimeZone("GMT+0");
			String stime = "";
			String etime = "";
			if(!"".equals(startdate)){
				Date startdatatime = TimeTools.getDateFormatYMDHMSString(startdate);
				format.setTimeZone(zone);
				stime = format.format(startdatatime);
				System.err.println("stime:"+stime);
			}
			
			if(!"".equals(enddate)){
				Date enddatatime = TimeTools.getDateFormatYMDHMSString(enddate);
				format.setTimeZone(zone);
				etime = format.format(enddatatime);
				System.err.println("etime:"+etime);
			}
			
			String startDateStart = stime.replace(" ", "T");
			
			String startDateEnd = etime.replace(" ", "T");
			
			MeetingDetailBean MDBean = new MeetingDetailBean();
			
			MDBean.setStartDateStart(startDateStart);
			MDBean.setStartDateEnd(startDateEnd);
			MDBean.setTimeZoneId("45");
			MDBean.setAttendees(false);
			
			MeetingBean MBean = new MeetingBean();
			MBean.setAction("listSummaryMeeting");
			MBean.setService("meeting");
			MBean.setType("xml");
			MBean.setSiteName("box");
			MBean.setUserName(logname);
			MBean.setPassword(password);
			MBean.setMDBean(MDBean);
			
			JavaBeanToXML JBeanToXML = new JavaBeanToXML();
			String meetingxml = JBeanToXML.BeanToXML(MBean);
			
			//System.err.println("meetingxml:"+meetingxml);
			
			MeetingInterface MeetingInterface = new MeetingInterface();
			HttpRespons hr = MeetingInterface.ToMeetingTo(meetingxml);
			
			String returnxml = HttpRequester.base64Decode(hr.getContent());
			//String returnxml = hr.getContent();
			//System.err.println("returnxml:"+returnxml);
			
			XMLTo XMLTo = new XMLTo();
			MeetingReturnBean mrbean = XMLTo.MeetingListXMLToValue(returnxml);
			
			return mrbean;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 查看会议详细信息
	 * @param confkey
	 * @throws Exception
	 */
	public MeetingReturnBean getMeetingViewMessage(String confkey,UserBean userBean) throws Exception{
		try{
			MeetingDetailBean MDBean = new MeetingDetailBean();
			
			String logname = userBean.getLogname();
			String password = userBean.getPassword();
			System.err.println("logname:"+logname);
			System.err.println("password:"+password);
			
			MDBean.setConfKey(confkey);
			MDBean.setAttendees(false);
			
			MeetingBean MBean = new MeetingBean();
			MBean.setAction("readMeeting");
			MBean.setService("meeting");
			MBean.setType("xml");
			MBean.setSiteName("box");
			MBean.setUserName(logname);
			MBean.setPassword(password);
			MBean.setMDBean(MDBean);
			
			JavaBeanToXML JBeanToXML = new JavaBeanToXML();
			String meetingxml = JBeanToXML.BeanToXML(MBean);
			
			System.err.println("meetingxml:"+meetingxml);
			
			MeetingInterface MeetingInterface = new MeetingInterface();
			HttpRespons hr = MeetingInterface.ToMeetingTo(meetingxml);
			
			String returnxml = HttpRequester.base64Decode(hr.getContent());
			System.err.println("returnxml:"+returnxml);
			
			XMLTo xmltobean = new XMLTo();
			List list = new ArrayList();
            list.add(RESULT);
            list.add(RCONKEY);
            list.add(RATTAMOUNT);
            list.add(RCONFPATTERN);
            list.add(RCONTYPE);
            list.add(RDURATION);
            list.add(RSTARTHM);
            list.add(RENDHM);
            list.add(RSTARTTIME);
            list.add(RENDTIME);
            list.add(REFFTIME);
            list.add(REXPTIME);
            list.add(RHOSTNAME);
            list.add(ROPENTYPE);
            list.add(RREPTYPEKEY);
            list.add(RREPTYPEVALUE);
            list.add(RSUBJECT);
            list.add(RAGENDA);
            MeetingReturnBean mrbean = xmltobean.XMLToValue(returnxml, list);
            
            String conferenceType = "";
            String repeatTypeKey = "";
            String repeatTypeValue = "";
            if(mrbean != null){
    			conferenceType = mrbean.getConferenceType();
    			repeatTypeKey = mrbean.getRepeatTypeKey();
    			repeatTypeValue = mrbean.getRepeatTypeValue();
    		}
    		
    		/**
    		 * 周期会议
    		 */
    		if("周期会议".equals(conferenceType)){
    			if("DAILY".equals(repeatTypeKey)){
    				repeatTypeValue = "每 "+repeatTypeValue+" 天";
    			}else if("WEEKLY".equals(repeatTypeKey)){
    				String repeatvalue = "每 ";
    				String weekvalue = String.valueOf(repeatTypeValue.charAt(0));
    				if("1".equals(weekvalue)){
    					repeatvalue += "周一 ";
    				}
    				weekvalue = String.valueOf(repeatTypeValue.charAt(1));
    				if("1".equals(weekvalue)){
    					repeatvalue += "周二 ";
    				}
    				weekvalue = String.valueOf(repeatTypeValue.charAt(2));
    				if("1".equals(weekvalue)){
    					repeatvalue += "周三 ";
    				}
    				weekvalue = String.valueOf(repeatTypeValue.charAt(3));
    				if("1".equals(weekvalue)){
    					repeatvalue += "周四 ";
    				}
    				weekvalue = String.valueOf(repeatTypeValue.charAt(4));
    				if("1".equals(weekvalue)){
    					repeatvalue += "周五 ";
    				}
    				weekvalue = String.valueOf(repeatTypeValue.charAt(5));
    				if("1".equals(weekvalue)){
    					repeatvalue += "周六 ";
    				}
    				weekvalue = String.valueOf(repeatTypeValue.charAt(6));
    				if("1".equals(weekvalue)){
    					repeatvalue += "周日";
    				}
    				repeatTypeValue = repeatvalue;
    			}else if("MONTHLY".equals(repeatTypeKey)){
    				repeatTypeValue = "每月 "+repeatTypeValue+" 号";
    			}
    			
    			mrbean.setRepeatTypeValue(repeatTypeValue);
    		}
            return mrbean;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 修改预约会议
	 * @param request
	 * @param str_userid
	 * @param str_machineid
	 * @throws Exception
	 */
	public void UpdMeeting(MeetingDetailBean meetingDetailBean,UserBean userBean) throws Exception{
		try{
			String confkey = meetingDetailBean.getConfKey();
			confkey = (confkey == null)?"":confkey;
			
			String vc_name = meetingDetailBean.getSubject();
			vc_name = (vc_name == null)?"":vc_name;
			
			String vc_password = meetingDetailBean.getPasswd();
			vc_password = (vc_password == null)?"":vc_password;
			
			String vc_conferencePattern = meetingDetailBean.getConferencePattern();
			vc_conferencePattern = (vc_conferencePattern == null)?"":vc_conferencePattern;
			
			String vc_attendeeAmount = meetingDetailBean.getAttendeeAmount();
			vc_attendeeAmount = (vc_attendeeAmount == null)?"":vc_attendeeAmount;
			
			String vc_hostName = meetingDetailBean.getHostName();
			vc_hostName = (vc_hostName == null)?"":vc_hostName;
			
			String vc_agenda = meetingDetailBean.getAgenda();
			vc_agenda = (vc_agenda == null)?"":vc_agenda;
			
			String vc_openType = meetingDetailBean.getOpenType();
			vc_openType = (vc_openType == null)?"":vc_openType;
			
			List attendee = meetingDetailBean.getAttendee();
			
			String startdate = meetingDetailBean.getStartTime();
			
			String enddate = meetingDetailBean.getEndTime();
			
			String logname = userBean.getLogname();
			String password = userBean.getPassword();
			System.err.println("logname:"+logname);
			System.err.println("password:"+password);
			
			/**
			 * 将开始和结束时间转换为0时区
			 */
			TimeTools TimeTools = new TimeTools();
			Date startdatatime = TimeTools.getDateFormatYMDHMSString(startdate);
			String fromFormat = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat format = new SimpleDateFormat (fromFormat);
			TimeZone zone = TimeZone.getTimeZone("GMT+0");
			format.setTimeZone(zone);
			String stime = format.format(startdatatime);
			System.out.println("stime:"+stime);
			
			Date enddatatime = TimeTools.getDateFormatYMDHMSString(enddate);
			format.setTimeZone(zone);
			String etime = format.format(enddatatime);
			System.out.println("etime:"+etime);
			
			String startTime = stime.replace(" ", "T");
			
			String endTime = etime.replace(" ", "T");
			
			ResouceLoader resouceloader = new ResouceLoader();
			String webBaseUrl = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.meeting.api.webBaseUrl");
			
			MeetingDetailBean MDBean = new MeetingDetailBean();
			MDBean.setConfKey(confkey);
			MDBean.setSubject(vc_name);
			MDBean.setPasswd(vc_password);
			MDBean.setStartTime(startTime);
			MDBean.setEndTime(endTime);
			MDBean.setConferencePattern(vc_conferencePattern);
			MDBean.setAttendeeAmount(vc_attendeeAmount);
			MDBean.setHostName(vc_hostName);
			MDBean.setAgenda(vc_agenda);
			MDBean.setOpenType(vc_openType);
			MDBean.setTimeZoneId("45");
			MDBean.setCreator(logname);
			MDBean.setMailTemplateLocal("zh_CN");
			MDBean.setBeforehandTime("0");
			MDBean.setWebBaseUrl(webBaseUrl);
			MDBean.setAttendees(true);
			
			/*List attendee = new ArrayList();
			HashMap hashmap = new HashMap();
			hashmap.put("name", "aaa");
			hashmap.put("email", "aaa@163.com");
			hashmap.put("phone", "13988851578");
			
			attendee.add(hashmap);
			
			hashmap = new HashMap();
			hashmap.put("name", "bbb");
			hashmap.put("email", "bbb@163.com");
			hashmap.put("phone", "13988851000");
			
			attendee.add(hashmap);*/
			
			MDBean.setAttendee(attendee);
			
			MeetingBean MBean = new MeetingBean();
			MBean.setAction("updateReserveMeeting");
			MBean.setService("meeting");
			MBean.setType("xml");
			MBean.setSiteName("box");
			MBean.setUserName(logname);
			MBean.setPassword(password);
			MBean.setMDBean(MDBean);
			
			JavaBeanToXML JBeanToXML = new JavaBeanToXML();
			String meetingxml = JBeanToXML.BeanToXML(MBean);
			
			System.err.println("meetingxml:"+meetingxml);
			
			MeetingInterface MeetingInterface = new MeetingInterface();
			HttpRespons hr = MeetingInterface.ToMeetingTo(meetingxml);
			System.out.println("returnxml :" + HttpRequester.base64Decode(hr.getContent()));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改固定会议
	 * @param request
	 * @param str_userid
	 * @param str_machineid
	 * @throws Exception
	 */
	public void UpdFixedMeeting(MeetingDetailBean meetingDetailBean,UserBean userBean) throws Exception{
		try{
			String confkey = meetingDetailBean.getConfKey();
			confkey = (confkey == null)?"":confkey;
			
			String vc_name = meetingDetailBean.getSubject();
			vc_name = (vc_name == null)?"":vc_name;
			
			String vc_password = meetingDetailBean.getPasswd();
			vc_password = (vc_password == null)?"":vc_password;
			
			String vc_attendeeAmount = meetingDetailBean.getAttendeeAmount();
			vc_attendeeAmount = (vc_attendeeAmount == null)?"":vc_attendeeAmount;
			
			String vc_agenda = meetingDetailBean.getAgenda();
			vc_agenda = (vc_agenda == null)?"":vc_agenda;
			
			String vc_openType = meetingDetailBean.getOpenType();
			vc_openType = (vc_openType == null)?"":vc_openType;
			
			List attendee = meetingDetailBean.getAttendee();
			
			String startdate = meetingDetailBean.getStartTime();
			
			String enddate = meetingDetailBean.getEndTime();
			
			String logname = userBean.getLogname();
			String password = userBean.getPassword();
			System.err.println("logname:"+logname);
			System.err.println("password:"+password);
			
			/**
			 * 将开始和结束时间转换为0时区
			 */
			TimeTools TimeTools = new TimeTools();
			Date startdatatime = TimeTools.getDateFormatYMDHMSString(startdate);
			String fromFormat = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat format = new SimpleDateFormat (fromFormat);
			TimeZone zone = TimeZone.getTimeZone("GMT+0");
			format.setTimeZone(zone);
			String stime = format.format(startdatatime);
			System.out.println("stime:"+stime);
			
			Date enddatatime = TimeTools.getDateFormatYMDHMSString(enddate);
			format.setTimeZone(zone);
			String etime = format.format(enddatatime);
			System.out.println("etime:"+etime);
			
			String startTime = stime.replace(" ", "T");
			
			String endTime = etime.replace(" ", "T");
			
			ResouceLoader resouceloader = new ResouceLoader();
			String webBaseUrl = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.meeting.api.webBaseUrl");
			
			MeetingDetailBean MDBean = new MeetingDetailBean();
			MDBean.setConfKey(confkey);
			MDBean.setSubject(vc_name);
			MDBean.setStartTime(startTime);
			MDBean.setEndTime(endTime);
			MDBean.setTimeZoneId("45");
			MDBean.setAttendeeAmount(vc_attendeeAmount);
			MDBean.setHostName(logname);
			MDBean.setOpenType(vc_openType);
			MDBean.setPasswd(vc_password);
			MDBean.setAgenda(vc_agenda);
			MDBean.setMailTemplateLocal("zh_CN");
			MDBean.setBeforehandTime("0");
			MDBean.setWebBaseUrl(webBaseUrl);
			MDBean.setAttendees(true);
			
			/*List attendee = new ArrayList();
			HashMap hashmap = new HashMap();
			hashmap.put("name", "aaa");
			hashmap.put("email", "aaa@163.com");
			hashmap.put("phone", "13988851578");
			
			attendee.add(hashmap);
			
			hashmap = new HashMap();
			hashmap.put("name", "bbb");
			hashmap.put("email", "bbb@163.com");
			hashmap.put("phone", "13988851000");
			
			attendee.add(hashmap);*/
			
			MDBean.setAttendee(attendee);
			
			MeetingBean MBean = new MeetingBean();
			MBean.setAction("updateFixedMeeting");
			MBean.setService("meeting");
			MBean.setType("xml");
			MBean.setSiteName("box");
			MBean.setUserName(logname);
			MBean.setPassword(password);
			MBean.setMDBean(MDBean);
			
			JavaBeanToXML JBeanToXML = new JavaBeanToXML();
			String meetingxml = JBeanToXML.BeanToXML(MBean);
			
			System.err.println("meetingxml:"+meetingxml);
			
			MeetingInterface MeetingInterface = new MeetingInterface();
			HttpRespons hr = MeetingInterface.ToMeetingTo(meetingxml);
			System.out.println("returnxml: " + hr.getContent());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改周期会议
	 * @param request
	 * @param str_userid
	 * @param str_machineid
	 * @throws Exception
	 */
	public void UpdRegularMeeting(MeetingDetailBean meetingDetailBean,UserBean userBean) throws Exception{
		try{
			String confkey = meetingDetailBean.getConfKey();
			confkey = (confkey == null)?"":confkey;
			
			String vc_name = meetingDetailBean.getSubject();
			vc_name = (vc_name == null)?"":vc_name;
			
			String vc_password = meetingDetailBean.getPasswd();
			vc_password = (vc_password == null)?"":vc_password;
			
			String vc_effectiveTime = meetingDetailBean.getEffectiveTime();
			vc_effectiveTime = (vc_effectiveTime == null)?"":vc_effectiveTime;
			
			String vc_expirationTime = meetingDetailBean.getExpirationTime();
			vc_expirationTime = (vc_expirationTime == null)?"":vc_expirationTime;
			
			String repeatTypeKey = meetingDetailBean.getRepeatTypeKey();
			repeatTypeKey = (repeatTypeKey == null)?"":repeatTypeKey;
			
			String vc_repeatTypeValue = meetingDetailBean.getRepeatTypeValue();
			vc_repeatTypeValue = (vc_repeatTypeValue == null)?"":vc_repeatTypeValue;
			
			String vc_conferencePattern = meetingDetailBean.getConferencePattern();
			vc_conferencePattern = (vc_conferencePattern == null)?"":vc_conferencePattern;
			
			String vc_attendeeAmount = meetingDetailBean.getAttendeeAmount();
			vc_attendeeAmount = (vc_attendeeAmount == null)?"":vc_attendeeAmount;
			
			String vc_hostName = meetingDetailBean.getHostName();
			vc_hostName = (vc_hostName == null)?"":vc_hostName;
			
			String vc_agenda = meetingDetailBean.getAgenda();
			vc_agenda = (vc_agenda == null)?"":vc_agenda;
			
			String vc_openType = meetingDetailBean.getOpenType();
			vc_openType = (vc_openType == null)?"":vc_openType;
			
			String startHourMinute = meetingDetailBean.getStartHourMinute();
			String endHourMinute = meetingDetailBean.getEndHourMinute();
			
			String effTime = vc_effectiveTime + " 00:00:00";
			
			String expTime = vc_expirationTime + " 00:00:00";
			
			List attendee = meetingDetailBean.getAttendee();
			
			String logname = userBean.getLogname();
			String password = userBean.getPassword();
			System.err.println("logname:"+logname);
			System.err.println("password:"+password);
			
			/**
			 * 将开始和结束时间转换为0时区
			 */
			TimeTools TimeTools = new TimeTools();
			Date startdatatime = TimeTools.getDateFormatYMDHMSString(effTime);
			String fromFormat = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat format = new SimpleDateFormat (fromFormat);
			TimeZone zone = TimeZone.getTimeZone("GMT+0");
			format.setTimeZone(zone);
			String stime = format.format(startdatatime);
			System.out.println("stime:"+stime);
			
			Date enddatatime = TimeTools.getDateFormatYMDHMSString(expTime);
			format.setTimeZone(zone);
			String etime = format.format(enddatatime);
			System.out.println("etime:"+etime);
			
			String effectiveTime = stime.replace(" ", "T");
			
			String expirationTime = etime.replace(" ", "T");
			
			ResouceLoader resouceloader = new ResouceLoader();
			String webBaseUrl = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.meeting.api.webBaseUrl");
			
			MeetingDetailBean MDBean = new MeetingDetailBean();
			MDBean.setConfKey(confkey);
			MDBean.setSubject(vc_name);
			MDBean.setStartHourMinute(startHourMinute);
			MDBean.setEndHourMinute(endHourMinute);
			MDBean.setEffectiveTime(effectiveTime);
			MDBean.setExpirationTime(expirationTime);
			MDBean.setRepeatTypeKey(repeatTypeKey);
			MDBean.setRepeatTypeValue(vc_repeatTypeValue);
			MDBean.setPasswd(vc_password);
			MDBean.setConferencePattern(vc_conferencePattern);
			MDBean.setAttendeeAmount(vc_attendeeAmount);
			MDBean.setHostName(vc_hostName);
			MDBean.setAgenda(vc_agenda);
			MDBean.setOpenType(vc_openType);
			MDBean.setTimeZoneId("45");
			MDBean.setCreator(logname);
			MDBean.setMailTemplateLocal("zh_CN");
			MDBean.setBeforehandTime("0");
			MDBean.setWebBaseUrl(webBaseUrl);
			MDBean.setAttendees(true);
			
			/*List attendee = new ArrayList();
			HashMap hashmap = new HashMap();
			hashmap.put("name", "aaa");
			hashmap.put("email", "aaa@163.com");
			hashmap.put("phone", "13988851578");
			
			attendee.add(hashmap);
			
			hashmap = new HashMap();
			hashmap.put("name", "bbb");
			hashmap.put("email", "bbb@163.com");
			hashmap.put("phone", "13988851000");
			
			attendee.add(hashmap);*/
			
			MDBean.setAttendee(attendee);
			
			MeetingBean MBean = new MeetingBean();
			MBean.setAction("updateRegularMeeting");
			MBean.setService("meeting");
			MBean.setType("xml");
			MBean.setSiteName("box");
			MBean.setUserName(logname);
			MBean.setPassword(password);
			MBean.setMDBean(MDBean);
			
			JavaBeanToXML JBeanToXML = new JavaBeanToXML();
			String meetingxml = JBeanToXML.BeanToXML(MBean);
			
			System.err.println("meetingxml:"+meetingxml);
			
			MeetingInterface MeetingInterface = new MeetingInterface();
			HttpRespons hr = MeetingInterface.ToMeetingTo(meetingxml);
			System.out.println("returnxml : "+ hr.getContent());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 取消会议
	 * @param request
	 * @param str_userid
	 * @param str_machineid
	 * @throws Exception
	 */
	public void DelMeeting(String confkey) throws Exception{
		try{
			ResouceLoader resouceloader = new ResouceLoader();
			String webBaseUrl = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.meeting.api.webBaseUrl");
			
			MeetingDetailBean MDBean = new MeetingDetailBean();
			MDBean.setConfKey(confkey);
			MDBean.setWebBaseUrl(webBaseUrl);
			MDBean.setAttendees(false);
			
			MeetingBean MBean = new MeetingBean();
			MBean.setAction("deleteMeeting");
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
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取个别用户�?创建的会�?
	 * @param request
	 * @param str_userid
	 * @param str_machineid
	 * @throws Exception 
	 * @throws Exception
	 */
	public Map getPrivateMeetingMessage(MeetingDetailBean meetingDetailBean,UserBean userBean) throws Exception{
		String logname = userBean.getLogname();
		String password = userBean.getPassword();
		System.err.println("logname:"+logname);
		System.err.println("password:"+password);
		
		String timeZone = meetingDetailBean.getTimeZoneId();
		MeetingDetailBean mdb = new MeetingDetailBean();
		mdb.setTimeZoneId(timeZone);
		
		MeetingBean MBean = new MeetingBean();
		
		MBean.setAction("listSummaryPrivateMeeting_touch");
		MBean.setService("meeting");
		MBean.setType("xml");
		MBean.setSiteName("box");
		MBean.setUserName(logname);
		MBean.setPassword(password);
		MBean.setMDBean(mdb);
		
		JavaBeanToXML JBeanToXML = new JavaBeanToXML();
		String meetingxml = JBeanToXML.BeanToXML(MBean);
		
		System.err.println("meetingxml:"+meetingxml);
		
		MeetingInterface MeetingInterface = new MeetingInterface();
		HttpRespons hr = MeetingInterface.ToMeetingTo(meetingxml);
		
		String returnxml = HttpRequester.base64Decode(hr.getContent());
		System.err.println("returnxml:"+returnxml);
		
		String result = returnxml.substring(returnxml.indexOf("<result>")+8,returnxml.indexOf("</result>"));
		Map map = new HashMap();
		if("SUCCESS".equals(result)){
			XMLTo XMLTo = new XMLTo();
			MeetingReturnBean mrbean = XMLTo.MeetingListXMLToValue(returnxml);
			map.put("result", "SUCCESS");
			map.put("reason", mrbean);
		}else if("FAILURE".equals(result)){
			map.put("result", "FAILURE");
			map.put("reason", returnxml.substring(returnxml.indexOf("<reason>")+8,returnxml.indexOf("</reason>")));
		}
		return map;
	}
	/**
	 * 获取要开启会议的参数值
	 * @param request
	 * @param str_userid
	 * @param str_machineid
	 * @throws Exception 
	 * @throws Exception 
	 * @throws Exception
	 */
	public Map getStartMeetingParameter(MeetingDetailBean meetingDetailBean,UserBean userBean) throws Exception{
		System.err.println("getConfKey:"+meetingDetailBean.getConfKey());
		System.err.println("getPasswd:"+meetingDetailBean.getPasswd());
		
		String logname = userBean.getLogname();
		String password = userBean.getPassword();
		String username = userBean.getName();
		System.err.println("logname:"+logname);
		System.err.println("password:"+password);
		System.err.println("username:"+username);
		
		ResouceLoader resouceloader = new ResouceLoader();
		String webBaseUrl = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.meeting.api.webBaseUrl");
		
		MeetingDetailBean MDBean = new MeetingDetailBean();
		MeetingBean MBean = new MeetingBean();
		if(meetingDetailBean.getHostName().equals(username)){
			MDBean.setHostName(logname);
			MDBean.setDisplayName(logname);
			MDBean.setConfKey(meetingDetailBean.getConfKey());
			MDBean.setPasswd(meetingDetailBean.getPasswd());
			MDBean.setMeetingEmail("");
			MDBean.setWebBaseUrl(webBaseUrl);
			MDBean.setAttendees(false);
			
			MBean.setAction("startMeeting");
			MBean.setService("meeting");
			MBean.setType("xml");
			MBean.setSiteName("box");
			MBean.setUserName(logname);
			MBean.setPassword(password);
			MBean.setMDBean(MDBean);
		}else{
			MDBean.setAttendeeName(logname);
			MDBean.setConfKey(meetingDetailBean.getConfKey());
			MDBean.setPasswd(meetingDetailBean.getPasswd());
			MDBean.setMeetingEmail("");
			MDBean.setServerName("");
			MDBean.setWebBaseUrl(webBaseUrl);
			
			MBean.setAction("joinMeeting");
			MBean.setService("meeting");
			MBean.setType("xml");
			MBean.setSiteName("box");
			MBean.setUserName(logname);
			MBean.setPassword(password);
			MBean.setMDBean(MDBean);
		}
		
		JavaBeanToXML JBeanToXML = new JavaBeanToXML();
		String meetingxml = JBeanToXML.BeanToXML(MBean);
		
		System.err.println("meetingxml:"+meetingxml);
		
		MeetingInterface MeetingInterface = new MeetingInterface();
		HttpRespons hr = MeetingInterface.ToMeetingTo(meetingxml);
        
        String content = HttpRequester.base64Decode(hr.getContent());
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
        System.err.println("ciurl:"+ciurl);
        System.err.println("token:"+token);
        
        Map map = new HashMap();
        map.put("ciurl", ciurl);
        map.put("token",token);
        return map;
	}
}
