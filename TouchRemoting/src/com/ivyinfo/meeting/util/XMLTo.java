package com.ivyinfo.meeting.util;

import java.util.ArrayList;
import java.util.List;

import com.ivyinfo.meeting.bean.MeetingReturnBean;
import com.ivyinfo.meeting.bean.MeetingReturnDetailBean;
import com.ivyinfo.meeting.bean.MeetingXMLBean;

public class XMLTo implements MeetingXMLBean{
	

	public MeetingReturnBean XMLToValue(String xml,List list) throws Exception{
		try{
			MeetingReturnBean mrbean = new MeetingReturnBean();
			
			for(int i=0;i<list.size();i++){
				String configname = (String) list.get(i);
				
				if(configname.equals(RESULT)){
					if(xml.indexOf("<"+RESULT+">")>-1){
						int configsize = RESULT.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+RESULT+">")+configsize,xml.indexOf("</"+RESULT+">"));
						mrbean.setResult(xmlvalue);
					}
				}
				if(configname.equals(CIURL)){
					if(xml.indexOf("<"+CIURL+">")>-1){
						int configsize = CIURL.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+CIURL+">")+configsize,xml.indexOf("</"+CIURL+">"));
						mrbean.setCiURL(xmlvalue);
					}
				}
				if(configname.equals(TOKEN)){
					if(xml.indexOf("<"+TOKEN+">")>-1){
						int configsize = TOKEN.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+TOKEN+">")+configsize,xml.indexOf("</"+TOKEN+">"));
						mrbean.setToken(xmlvalue);
					}
				}
				if(configname.equals(RAGENDA)){
					if(xml.indexOf("<"+RAGENDA+">")>-1){
						int configsize = RAGENDA.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+RAGENDA+">")+configsize,xml.indexOf("</"+RAGENDA+">"));
						mrbean.setAgenda(xmlvalue);
					}
				}
				if(configname.equals(RCONKEY)){
					if(xml.indexOf("<"+RCONKEY+">")>-1){
						int configsize = RCONKEY.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+RCONKEY+">")+configsize,xml.indexOf("</"+RCONKEY+">"));
						mrbean.setConfKey(xmlvalue);
					}
				}
				if(configname.equals(RATTAMOUNT)){
					if(xml.indexOf("<"+RATTAMOUNT+">")>-1){
						int configsize = RATTAMOUNT.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+RATTAMOUNT+">")+configsize,xml.indexOf("</"+RATTAMOUNT+">"));
						mrbean.setAttendeeAmount(xmlvalue);
					}
				}
				if(configname.equals(RCONFPATTERN)){
					if(xml.indexOf("<"+RCONFPATTERN+">")>-1){
						int configsize = RCONFPATTERN.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+RCONFPATTERN+">")+configsize,xml.indexOf("</"+RCONFPATTERN+">"));
						mrbean.setConferencePattern(xmlvalue);
					}
				}
				if(configname.equals(RCONTYPE)){
					if(xml.indexOf("<"+RCONTYPE+">")>-1){
						int configsize = RCONTYPE.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+RCONTYPE+">")+configsize,xml.indexOf("</"+RCONTYPE+">"));
						mrbean.setConferenceType(xmlvalue);
					}
				}
				if(configname.equals(RDURATION)){
					if(xml.indexOf("<"+RDURATION+">")>-1){
						int configsize = RDURATION.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+RDURATION+">")+configsize,xml.indexOf("</"+RDURATION+">"));
						mrbean.setDuration(xmlvalue);
					}
				}
				if(configname.equals(RSTARTHM)){
					if(xml.indexOf("<"+RSTARTHM+">")>-1){
						int configsize = RSTARTHM.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+RSTARTHM+">")+configsize,xml.indexOf("</"+RSTARTHM+">"));
						mrbean.setStartHourMinute(xmlvalue);
					}
				}
				if(configname.equals(RENDHM)){
					if(xml.indexOf("<"+RENDHM+">")>-1){
						int configsize = RENDHM.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+RENDHM+">")+configsize,xml.indexOf("</"+RENDHM+">"));
						mrbean.setEndHourMinute(xmlvalue);
					}
				}
				if(configname.equals(RSTARTTIME)){
					if(xml.indexOf("<"+RSTARTTIME+">")>-1){
						int configsize = RSTARTTIME.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+RSTARTTIME+">")+configsize,xml.indexOf("</"+RSTARTTIME+">"));
						xmlvalue = xmlvalue.replace("T", " ");
						mrbean.setStartTime(xmlvalue);
					}
				}
				if(configname.equals(RENDTIME)){
					if(xml.indexOf("<"+RENDTIME+">")>-1){
						int configsize = RENDTIME.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+RENDTIME+">")+configsize,xml.indexOf("</"+RENDTIME+">"));
						xmlvalue = xmlvalue.replace("T", " ");
						mrbean.setEndTime(xmlvalue);
					}
				}
				if(configname.equals(REFFTIME)){
					if(xml.indexOf("<"+REFFTIME+">")>-1){
						int configsize = REFFTIME.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+REFFTIME+">")+configsize,xml.indexOf("</"+REFFTIME+">"));
						xmlvalue = xmlvalue.replace("T", " ");
						xmlvalue = xmlvalue.substring(0,10);
						mrbean.setEffectiveTime(xmlvalue);
					}
				}
				if(configname.equals(REXPTIME)){
					if(xml.indexOf("<"+REXPTIME+">")>-1){
						int configsize = REXPTIME.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+REXPTIME+">")+configsize,xml.indexOf("</"+REXPTIME+">"));
						xmlvalue = xmlvalue.replace("T", " ");
						xmlvalue = xmlvalue.substring(0,10);
						mrbean.setExpirationTime(xmlvalue);
					}
				}
				if(configname.equals(RHOSTNAME)){
					if(xml.indexOf("<"+RHOSTNAME+">")>-1){
						int configsize = RHOSTNAME.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+RHOSTNAME+">")+configsize,xml.indexOf("</"+RHOSTNAME+">"));
						mrbean.setHostName(xmlvalue);
					}
				}
				if(configname.equals(ROPENTYPE)){
					if(xml.indexOf("<"+ROPENTYPE+">")>-1){
						int configsize = ROPENTYPE.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+ROPENTYPE+">")+configsize,xml.indexOf("</"+ROPENTYPE+">"));
						mrbean.setOpenType(xmlvalue);
					}
				}
				if(configname.equals(RREPTYPEKEY)){
					if(xml.indexOf("<"+RREPTYPEKEY+">")>-1){
						int configsize = RREPTYPEKEY.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+RREPTYPEKEY+">")+configsize,xml.indexOf("</"+RREPTYPEKEY+">"));
						mrbean.setRepeatTypeKey(xmlvalue);
					}
				}
				if(configname.equals(RREPTYPEVALUE)){
					if(xml.indexOf("<"+RREPTYPEVALUE+">")>-1){
						int configsize = RREPTYPEVALUE.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+RREPTYPEVALUE+">")+configsize,xml.indexOf("</"+RREPTYPEVALUE+">"));
						mrbean.setRepeatTypeValue(xmlvalue);
					}
				}
				if(configname.equals(RSUBJECT)){
					if(xml.indexOf("<"+RSUBJECT+">")>-1){
						int configsize = RSUBJECT.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+RSUBJECT+">")+configsize,xml.indexOf("</"+RSUBJECT+">"));
						mrbean.setSubject(xmlvalue);
					}
				}
			}
			return mrbean;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public MeetingReturnBean MeetingListXMLToValue(String returnxml) throws Exception{
		try{
			MeetingReturnBean mrbean = new MeetingReturnBean();
			int configsize = RESULT.length()+2;
			String result = returnxml.substring(returnxml.indexOf("<"+RESULT+">")+configsize,returnxml.indexOf("</"+RESULT+">"));
			mrbean.setResult(result);
			
			List list = new ArrayList();
			while(returnxml.indexOf("<"+MEETING+">")>-1){
				MeetingReturnDetailBean mrdbean = new MeetingReturnDetailBean();
				int consize = MEETING.length()+2;
				int econsize = MEETING.length()+3;
				String meetingxml = returnxml.substring(returnxml.indexOf("<"+MEETING+">")+consize,returnxml.indexOf("</"+MEETING+">"));
				if(meetingxml.indexOf("<"+RCONKEY+">")>-1){
					int nodesize = RCONKEY.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+RCONKEY+">")+nodesize,meetingxml.indexOf("</"+RCONKEY+">"));
					mrdbean.setConfKey(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+RSUBJECT+">")>-1){
					int nodesize = RSUBJECT.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+RSUBJECT+">")+nodesize,meetingxml.indexOf("</"+RSUBJECT+">"));
					mrdbean.setSubject(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+RHOSTNAME+">")>-1){
					int nodesize = RHOSTNAME.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+RHOSTNAME+">")+nodesize,meetingxml.indexOf("</"+RHOSTNAME+">"));
					mrdbean.setHostName(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+RSTARTTIME+">")>-1){
					int nodesize = RSTARTTIME.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+RSTARTTIME+">")+nodesize,meetingxml.indexOf("</"+RSTARTTIME+">"));
					rxmlvalue = rxmlvalue.replace("T", " ");
					mrdbean.setStartTime(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+RENDTIME+">")>-1){
					int nodesize = RENDTIME.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+RENDTIME+">")+nodesize,meetingxml.indexOf("</"+RENDTIME+">"));
					rxmlvalue = rxmlvalue.replace("T", " ");
					mrdbean.setEndTime(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+RSTATUS+">")>-1){
					int nodesize = RSTATUS.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+RSTATUS+">")+nodesize,meetingxml.indexOf("</"+RSTATUS+">"));
					mrdbean.setStatus(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+RDURINGTIME+">")>-1){
					int nodesize = RDURINGTIME.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+RDURINGTIME+">")+nodesize,meetingxml.indexOf("</"+RDURINGTIME+">"));
					mrdbean.setDuringTime(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+RCONFTYPE+">")>-1){
					int nodesize = RCONFTYPE.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+RCONFTYPE+">")+nodesize,meetingxml.indexOf("</"+RCONFTYPE+">"));
					mrdbean.setConfType(rxmlvalue);
				}
				list.add(mrdbean);
				returnxml = returnxml.substring(returnxml.indexOf("</"+MEETING+">")+econsize,returnxml.length());
			}
			mrbean.setList(list);
			
			return mrbean;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
