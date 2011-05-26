package com.ivyinfo.meeting.util;

import java.util.ArrayList;
import java.util.List;

import com.ivyinfo.meeting.bean.MeetingReturnBean;
import com.ivyinfo.meeting.bean.MeetingReturnDetailBean;
import com.ivyinfo.meeting.bean.MeetingReturnDetailSysBean;
import com.ivyinfo.meeting.bean.MeetingReturnSysBean;
import com.ivyinfo.meeting.bean.MeetingXMLBean;
import com.ivyinfo.meeting.bean.MeetingXMLSysBean;

public class XMLToSys implements MeetingXMLSysBean{
	

	public MeetingReturnSysBean XMLToValue(String xml,List list) throws Exception{
		try{
			MeetingReturnSysBean mrsbean = new MeetingReturnSysBean();
			
			for(int i=0;i<list.size();i++){
				String configname = (String) list.get(i);
				
				if(configname.equals(RESULT)){
					if(xml.indexOf("<"+RESULT+">")>-1){
						int configsize = RESULT.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+RESULT+">")+configsize,xml.indexOf("</"+RESULT+">"));
						mrsbean.setResult(xmlvalue);
					}
				}
				if(configname.equals(EXCEPTIONID)){
					if(xml.indexOf("<"+EXCEPTIONID+">")>-1){
						int configsize = EXCEPTIONID.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+EXCEPTIONID+">")+configsize,xml.indexOf("</"+EXCEPTIONID+">"));
						mrsbean.setExceptionID(xmlvalue);
					}
				}
				if(configname.equals(REASON)){
					if(xml.indexOf("<"+REASON+">")>-1){
						int configsize = REASON.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+REASON+">")+configsize,xml.indexOf("</"+REASON+">"));
						mrsbean.setReason(xmlvalue);
					}
				}
				if(configname.equals(ORGNAME)){
					if(xml.indexOf("<"+ORGNAME+">")>-1){
						int configsize = ORGNAME.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+ORGNAME+">")+configsize,xml.indexOf("</"+ORGNAME+">"));
						mrsbean.setName(xmlvalue);
					}
				}
				if(configname.equals(ORGCODE)){
					if(xml.indexOf("<"+ORGCODE+">")>-1){
						int configsize = ORGCODE.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+ORGCODE+">")+configsize,xml.indexOf("</"+ORGCODE+">"));
						mrsbean.setOrgCode(xmlvalue);
					}
				}
				if(configname.equals(ORGID)){
					if(xml.indexOf("<"+ORGID+">")>-1){
						int configsize = ORGID.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+ORGID+">")+configsize,xml.indexOf("</"+ORGID+">"));
						mrsbean.setOrgId(xmlvalue);
					}
				}
				if(configname.equals(RPARENTORGNAME)){
					if(xml.indexOf("<"+RPARENTORGNAME+">")>-1){
						int configsize = RPARENTORGNAME.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+RPARENTORGNAME+">")+configsize,xml.indexOf("</"+RPARENTORGNAME+">"));
						mrsbean.setParentOrgName(xmlvalue);
					}
				}
				if(configname.equals(RPHONE)){
					if(xml.indexOf("<"+RPHONE+">")>-1){
						int configsize = RPHONE.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+RPHONE+">")+configsize,xml.indexOf("</"+RPHONE+">"));
						mrsbean.setPhone(xmlvalue);
					}
				}
				if(configname.equals(REMAIL)){
					if(xml.indexOf("<"+REMAIL+">")>-1){
						int configsize = REMAIL.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+REMAIL+">")+configsize,xml.indexOf("</"+REMAIL+">"));
						mrsbean.setEmail(xmlvalue);
					}
				}
				if(configname.equals(RPARENTORGID)){
					if(xml.indexOf("<"+RPARENTORGID+">")>-1){
						int configsize = RPARENTORGID.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+RPARENTORGID+">")+configsize,xml.indexOf("</"+RPARENTORGID+">"));
						mrsbean.setParentOrgId(xmlvalue);
					}
				}
				if(configname.equals(RADDRESS)){
					if(xml.indexOf("<"+RADDRESS+">")>-1){
						int configsize = RADDRESS.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+RADDRESS+">")+configsize,xml.indexOf("</"+RADDRESS+">"));
						mrsbean.setAddress(xmlvalue);
					}
				}
				if(configname.equals(RPOSTCODE)){
					if(xml.indexOf("<"+RPOSTCODE+">")>-1){
						int configsize = RPOSTCODE.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+RPOSTCODE+">")+configsize,xml.indexOf("</"+RPOSTCODE+">"));
						mrsbean.setPostCode(xmlvalue);
					}
				}
				if(configname.equals(RDESCRIPTION)){
					if(xml.indexOf("<"+RDESCRIPTION+">")>-1){
						int configsize = RDESCRIPTION.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+RDESCRIPTION+">")+configsize,xml.indexOf("</"+RDESCRIPTION+">"));
						mrsbean.setDescription(xmlvalue);
					}
				}
				if(configname.equals(RLINKMAN)){
					if(xml.indexOf("<"+RLINKMAN+">")>-1){
						int configsize = RLINKMAN.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+RLINKMAN+">")+configsize,xml.indexOf("</"+RLINKMAN+">"));
						mrsbean.setLinkman(xmlvalue);
					}
				}
				if(configname.equals(RPRINCIPAL)){
					if(xml.indexOf("<"+RPRINCIPAL+">")>-1){
						int configsize = RPRINCIPAL.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+RPRINCIPAL+">")+configsize,xml.indexOf("</"+RPRINCIPAL+">"));
						mrsbean.setPrincipal(xmlvalue);
					}
				}
				if(configname.equals(RACTIVEDATE)){
					if(xml.indexOf("<"+RACTIVEDATE+">")>-1){
						int configsize = RACTIVEDATE.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+RACTIVEDATE+">")+configsize,xml.indexOf("</"+RACTIVEDATE+">"));
						mrsbean.setActiveDate(xmlvalue);
					}
				}
				if(configname.equals(RADDRESS)){
					if(xml.indexOf("<"+RADDRESS+">")>-1){
						int configsize = RADDRESS.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+RADDRESS+">")+configsize,xml.indexOf("</"+RADDRESS+">"));
						mrsbean.setAddress(xmlvalue);
					}
				}
				if(configname.equals(CELLPHONE)){
					if(xml.indexOf("<"+CELLPHONE+">")>-1){
						int configsize = CELLPHONE.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+CELLPHONE+">")+configsize,xml.indexOf("</"+CELLPHONE+">"));
						mrsbean.setCellphone(xmlvalue);
					}
				}
				if(configname.equals(CITY)){
					if(xml.indexOf("<"+CITY+">")>-1){
						int configsize = CITY.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+CITY+">")+configsize,xml.indexOf("</"+CITY+">"));
						mrsbean.setCity(xmlvalue);
					}
				}
				if(configname.equals(COMPANY)){
					if(xml.indexOf("<"+COMPANY+">")>-1){
						int configsize = COMPANY.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+COMPANY+">")+configsize,xml.indexOf("</"+COMPANY+">"));
						mrsbean.setCompany(xmlvalue);
					}
				}
				if(configname.equals(COUNTRY)){
					if(xml.indexOf("<"+COUNTRY+">")>-1){
						int configsize = COUNTRY.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+COUNTRY+">")+configsize,xml.indexOf("</"+COUNTRY+">"));
						mrsbean.setCountry(xmlvalue);
					}
				}
				if(configname.equals(DEPTID)){
					if(xml.indexOf("<"+DEPTID+">")>-1){
						int configsize = DEPTID.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+DEPTID+">")+configsize,xml.indexOf("</"+DEPTID+">"));
						mrsbean.setDeptId(xmlvalue);
					}
				}
				if(configname.equals(DUTY)){
					if(xml.indexOf("<"+DUTY+">")>-1){
						int configsize = DUTY.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+DUTY+">")+configsize,xml.indexOf("</"+DUTY+">"));
						mrsbean.setDuty(xmlvalue);
					}
				}
				if(configname.equals(EASYCALLUSED)){
					if(xml.indexOf("<"+EASYCALLUSED+">")>-1){
						int configsize = EASYCALLUSED.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+EASYCALLUSED+">")+configsize,xml.indexOf("</"+EASYCALLUSED+">"));
						mrsbean.setEasycallUsed(xmlvalue);
					}
				}
				if(configname.equals(EIMUSED)){
					if(xml.indexOf("<"+EIMUSED+">")>-1){
						int configsize = EIMUSED.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+EIMUSED+">")+configsize,xml.indexOf("</"+EIMUSED+">"));
						mrsbean.setEimUsed(xmlvalue);
					}
				}
				if(configname.equals(REMAIL)){
					if(xml.indexOf("<"+REMAIL+">")>-1){
						int configsize = REMAIL.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+REMAIL+">")+configsize,xml.indexOf("</"+REMAIL+">"));
						mrsbean.setEmail(xmlvalue);
					}
				}
				if(configname.equals(ENABLED)){
					if(xml.indexOf("<"+ENABLED+">")>-1){
						int configsize = ENABLED.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+ENABLED+">")+configsize,xml.indexOf("</"+ENABLED+">"));
						mrsbean.setEnabled(xmlvalue);
					}
				}
				if(configname.equals(EXTENSION)){
					if(xml.indexOf("<"+EXTENSION+">")>-1){
						int configsize = EXTENSION.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+EXTENSION+">")+configsize,xml.indexOf("</"+EXTENSION+">"));
						mrsbean.setExtension(xmlvalue);
					}
				}
				if(configname.equals(FAX)){
					if(xml.indexOf("<"+FAX+">")>-1){
						int configsize = FAX.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+FAX+">")+configsize,xml.indexOf("</"+FAX+">"));
						mrsbean.setFax(xmlvalue);
					}
				}
				if(configname.equals(FIRSTNAME)){
					if(xml.indexOf("<"+FIRSTNAME+">")>-1){
						int configsize = FIRSTNAME.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+FIRSTNAME+">")+configsize,xml.indexOf("</"+FIRSTNAME+">"));
						mrsbean.setFirstName(xmlvalue);
					}
				}
				if(configname.equals(GENDER)){
					if(xml.indexOf("<"+GENDER+">")>-1){
						int configsize = GENDER.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+GENDER+">")+configsize,xml.indexOf("</"+GENDER+">"));
						mrsbean.setGender(xmlvalue);
					}
				}
				if(configname.equals(ID)){
					if(xml.indexOf("<"+ID+">")>-1){
						int configsize = ID.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+ID+">")+configsize,xml.indexOf("</"+ID+">"));
						mrsbean.setId(xmlvalue);
					}
				}
				if(configname.equals(INACTIVEDATE)){
					if(xml.indexOf("<"+INACTIVEDATE+">")>-1){
						int configsize = INACTIVEDATE.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+INACTIVEDATE+">")+configsize,xml.indexOf("</"+INACTIVEDATE+">"));
						mrsbean.setInActiveDate(xmlvalue);
					}
				}
				if(configname.equals(LASTNAME)){
					if(xml.indexOf("<"+LASTNAME+">")>-1){
						int configsize = LASTNAME.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+LASTNAME+">")+configsize,xml.indexOf("</"+LASTNAME+">"));
						mrsbean.setLastName(xmlvalue);
					}
				}
				if(configname.equals(NICKNAME)){
					if(xml.indexOf("<"+NICKNAME+">")>-1){
						int configsize = NICKNAME.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+NICKNAME+">")+configsize,xml.indexOf("</"+NICKNAME+">"));
						mrsbean.setNickname(xmlvalue);
					}
				}
				if(configname.equals(OFFICEPHONE)){
					if(xml.indexOf("<"+OFFICEPHONE+">")>-1){
						int configsize = OFFICEPHONE.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+OFFICEPHONE+">")+configsize,xml.indexOf("</"+OFFICEPHONE+">"));
						mrsbean.setOfficePhone(xmlvalue);
					}
				}
				if(configname.equals(OTHEREMAIL)){
					if(xml.indexOf("<"+OTHEREMAIL+">")>-1){
						int configsize = OTHEREMAIL.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+OTHEREMAIL+">")+configsize,xml.indexOf("</"+OTHEREMAIL+">"));
						mrsbean.setOtherEmail(xmlvalue);
					}
				}
				if(configname.equals(OTHERINFO)){
					if(xml.indexOf("<"+OTHERINFO+">")>-1){
						int configsize = OTHERINFO.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+OTHERINFO+">")+configsize,xml.indexOf("</"+OTHERINFO+">"));
						mrsbean.setOtherInfo(xmlvalue);
					}
				}
				if(configname.equals(OTHERPHONE)){
					if(xml.indexOf("<"+OTHERPHONE+">")>-1){
						int configsize = OTHERPHONE.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+OTHERPHONE+">")+configsize,xml.indexOf("</"+OTHERPHONE+">"));
						mrsbean.setOtherPhone(xmlvalue);
					}
				}
				if(configname.equals(RPASSWORD)){
					if(xml.indexOf("<"+RPASSWORD+">")>-1){
						int configsize = RPASSWORD.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+RPASSWORD+">")+configsize,xml.indexOf("</"+RPASSWORD+">"));
						mrsbean.setPassword(xmlvalue);
					}
				}
				if(configname.equals(UPOSTCODE)){
					if(xml.indexOf("<"+UPOSTCODE+">")>-1){
						int configsize = UPOSTCODE.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+UPOSTCODE+">")+configsize,xml.indexOf("</"+UPOSTCODE+">"));
						mrsbean.setPostcode(xmlvalue);
					}
				}
				if(configname.equals(PROVINCE)){
					if(xml.indexOf("<"+PROVINCE+">")>-1){
						int configsize = PROVINCE.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+PROVINCE+">")+configsize,xml.indexOf("</"+PROVINCE+">"));
						mrsbean.setProvince(xmlvalue);
					}
				}
				if(configname.equals(REPORTTO)){
					if(xml.indexOf("<"+REPORTTO+">")>-1){
						int configsize = REPORTTO.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+REPORTTO+">")+configsize,xml.indexOf("</"+REPORTTO+">"));
						mrsbean.setReportTo(xmlvalue);
					}
				}
				if(configname.equals(SITEID)){
					if(xml.indexOf("<"+SITEID+">")>-1){
						int configsize = SITEID.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+SITEID+">")+configsize,xml.indexOf("</"+SITEID+">"));
						mrsbean.setSiteId(xmlvalue);
					}
				}
				if(configname.equals(RUSERNAME)){
					if(xml.indexOf("<"+RUSERNAME+">")>-1){
						int configsize = RUSERNAME.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+RUSERNAME+">")+configsize,xml.indexOf("</"+RUSERNAME+">"));
						mrsbean.setUserName(xmlvalue);
					}
				}
				if(configname.equals(USERTYPE)){
					if(xml.indexOf("<"+USERTYPE+">")>-1){
						int configsize = USERTYPE.length()+2;
						String xmlvalue = xml.substring(xml.indexOf("<"+USERTYPE+">")+configsize,xml.indexOf("</"+USERTYPE+">"));
						mrsbean.setUserType(xmlvalue);
					}
				}
			}
			return mrsbean;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public MeetingReturnSysBean MeetingListXMLToValue(String returnxml) throws Exception{
		try{
			MeetingReturnSysBean mrsbean = new MeetingReturnSysBean();
			int configsized = EXCEPTIONID.length()+2;
			String exceptionID = returnxml.substring(returnxml.indexOf("<"+EXCEPTIONID+">")+configsized,returnxml.indexOf("</"+EXCEPTIONID+">"));
			mrsbean.setExceptionID(exceptionID);
			
			int configsizen = REASON.length()+2;
			String reason = returnxml.substring(returnxml.indexOf("<"+REASON+">")+configsizen,returnxml.indexOf("</"+REASON+">"));
			mrsbean.setReason(reason);
			
			int configsizet = RESULT.length()+2;
			String result = returnxml.substring(returnxml.indexOf("<"+RESULT+">")+configsizet,returnxml.indexOf("</"+RESULT+">"));
			mrsbean.setResult(result);
			
			List list = new ArrayList();
			while(returnxml.indexOf("<"+ORGANIZATION+">")>-1){
				MeetingReturnDetailSysBean mrdsbean = new MeetingReturnDetailSysBean();
				int consize = ORGANIZATION.length()+2;
				int econsize = ORGANIZATION.length()+3;
				String meetingxml = returnxml.substring(returnxml.indexOf("<"+ORGANIZATION+">")+consize,returnxml.indexOf("</"+ORGANIZATION+">"));
				
				if(meetingxml.indexOf("<"+ORGNAME+">")>-1){
					int nodesize = ORGNAME.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+ORGNAME+">")+nodesize,meetingxml.indexOf("</"+ORGNAME+">"));
					mrdsbean.setName(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+ORGCODE+">")>-1){
					int nodesize = ORGCODE.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+ORGCODE+">")+nodesize,meetingxml.indexOf("</"+ORGCODE+">"));
					mrdsbean.setOrgCode(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+ORGID+">")>-1){
					int nodesize = ORGID.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+ORGID+">")+nodesize,meetingxml.indexOf("</"+ORGID+">"));
					mrdsbean.setOrgId(rxmlvalue);
				}
				list.add(mrdsbean);
				returnxml = returnxml.substring(returnxml.indexOf("</"+ORGANIZATION+">")+econsize,returnxml.length());
			}
			mrsbean.setList(list);
			
			return mrsbean;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public MeetingReturnSysBean UserListXMLToValue(String returnxml) throws Exception{
		try{
			MeetingReturnSysBean mrsbean = new MeetingReturnSysBean();
			int configsized = EXCEPTIONID.length()+2;
			String exceptionID = returnxml.substring(returnxml.indexOf("<"+EXCEPTIONID+">")+configsized,returnxml.indexOf("</"+EXCEPTIONID+">"));
			mrsbean.setExceptionID(exceptionID);
			
			int configsizen = REASON.length()+2;
			String reason = returnxml.substring(returnxml.indexOf("<"+REASON+">")+configsizen,returnxml.indexOf("</"+REASON+">"));
			mrsbean.setReason(reason);
			
			int configsizet = RESULT.length()+2;
			String result = returnxml.substring(returnxml.indexOf("<"+RESULT+">")+configsizet,returnxml.indexOf("</"+RESULT+">"));
			mrsbean.setResult(result);
			
			List list = new ArrayList();
			while(returnxml.indexOf("<"+USER+">")>-1){
				MeetingReturnDetailSysBean mrdsbean = new MeetingReturnDetailSysBean();
				int consize = USER.length()+2;
				int econsize = USER.length()+3;
				String meetingxml = returnxml.substring(returnxml.indexOf("<"+USER+">")+consize,returnxml.indexOf("</"+USER+">"));
				
				if(meetingxml.indexOf("<"+RACTIVEDATE+">")>-1){
					int nodesize = RACTIVEDATE.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+RACTIVEDATE+">")+nodesize,meetingxml.indexOf("</"+RACTIVEDATE+">"));
					mrdsbean.setActiveDate(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+CELLPHONE+">")>-1){
					int nodesize = CELLPHONE.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+CELLPHONE+">")+nodesize,meetingxml.indexOf("</"+CELLPHONE+">"));
					mrdsbean.setCellphone(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+CITY+">")>-1){
					int nodesize = CITY.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+CITY+">")+nodesize,meetingxml.indexOf("</"+CITY+">"));
					mrdsbean.setCity(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+COMPANY+">")>-1){
					int nodesize = COMPANY.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+COMPANY+">")+nodesize,meetingxml.indexOf("</"+COMPANY+">"));
					mrdsbean.setCompany(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+COUNTRY+">")>-1){
					int nodesize = COUNTRY.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+COUNTRY+">")+nodesize,meetingxml.indexOf("</"+COUNTRY+">"));
					mrdsbean.setCountry(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+DEPTID+">")>-1){
					int nodesize = DEPTID.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+DEPTID+">")+nodesize,meetingxml.indexOf("</"+DEPTID+">"));
					mrdsbean.setDeptId(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+DUTY+">")>-1){
					int nodesize = DUTY.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+DUTY+">")+nodesize,meetingxml.indexOf("</"+DUTY+">"));
					mrdsbean.setDuty(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+EASYCALLUSED+">")>-1){
					int nodesize = EASYCALLUSED.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+EASYCALLUSED+">")+nodesize,meetingxml.indexOf("</"+EASYCALLUSED+">"));
					mrdsbean.setEasycallUsed(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+EIMUSED+">")>-1){
					int nodesize = EIMUSED.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+EIMUSED+">")+nodesize,meetingxml.indexOf("</"+EIMUSED+">"));
					mrdsbean.setEimUsed(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+ENABLED+">")>-1){
					int nodesize = ENABLED.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+ENABLED+">")+nodesize,meetingxml.indexOf("</"+ENABLED+">"));
					mrdsbean.setEnabled(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+FAX+">")>-1){
					int nodesize = FAX.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+FAX+">")+nodesize,meetingxml.indexOf("</"+FAX+">"));
					mrdsbean.setFax(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+FIRSTNAME+">")>-1){
					int nodesize = FIRSTNAME.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+FIRSTNAME+">")+nodesize,meetingxml.indexOf("</"+FIRSTNAME+">"));
					mrdsbean.setFirstName(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+GENDER+">")>-1){
					int nodesize = GENDER.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+GENDER+">")+nodesize,meetingxml.indexOf("</"+GENDER+">"));
					mrdsbean.setGender(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+ID+">")>-1){
					int nodesize = ID.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+ID+">")+nodesize,meetingxml.indexOf("</"+ID+">"));
					mrdsbean.setId(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+INACTIVEDATE+">")>-1){
					int nodesize = INACTIVEDATE.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+INACTIVEDATE+">")+nodesize,meetingxml.indexOf("</"+INACTIVEDATE+">"));
					mrdsbean.setInActiveDate(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+NICKNAME+">")>-1){
					int nodesize = NICKNAME.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+NICKNAME+">")+nodesize,meetingxml.indexOf("</"+NICKNAME+">"));
					mrdsbean.setNickname(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+OFFICEPHONE+">")>-1){
					int nodesize = OFFICEPHONE.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+OFFICEPHONE+">")+nodesize,meetingxml.indexOf("</"+OFFICEPHONE+">"));
					mrdsbean.setOfficePhone(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+OTHEREMAIL+">")>-1){
					int nodesize = OTHEREMAIL.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+OTHEREMAIL+">")+nodesize,meetingxml.indexOf("</"+OTHEREMAIL+">"));
					mrdsbean.setOtherEmail(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+OTHERINFO+">")>-1){
					int nodesize = OTHERINFO.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+OTHERINFO+">")+nodesize,meetingxml.indexOf("</"+OTHERINFO+">"));
					mrdsbean.setOtherInfo(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+OTHERPHONE+">")>-1){
					int nodesize = OTHERPHONE.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+OTHERPHONE+">")+nodesize,meetingxml.indexOf("</"+OTHERPHONE+">"));
					mrdsbean.setOtherPhone(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+RPASSWORD+">")>-1){
					int nodesize = RPASSWORD.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+RPASSWORD+">")+nodesize,meetingxml.indexOf("</"+RPASSWORD+">"));
					mrdsbean.setPassword(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+UPOSTCODE+">")>-1){
					int nodesize = UPOSTCODE.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+UPOSTCODE+">")+nodesize,meetingxml.indexOf("</"+UPOSTCODE+">"));
					mrdsbean.setPostcode(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+PROVINCE+">")>-1){
					int nodesize = PROVINCE.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+PROVINCE+">")+nodesize,meetingxml.indexOf("</"+PROVINCE+">"));
					mrdsbean.setProvince(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+REPORTTO+">")>-1){
					int nodesize = REPORTTO.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+REPORTTO+">")+nodesize,meetingxml.indexOf("</"+REPORTTO+">"));
					mrdsbean.setReportTo(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+SITEID+">")>-1){
					int nodesize = SITEID.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+SITEID+">")+nodesize,meetingxml.indexOf("</"+SITEID+">"));
					mrdsbean.setSiteId(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+RUSERNAME+">")>-1){
					int nodesize = RUSERNAME.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+RUSERNAME+">")+nodesize,meetingxml.indexOf("</"+RUSERNAME+">"));
					mrdsbean.setUserName(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+USERTYPE+">")>-1){
					int nodesize = USERTYPE.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+USERTYPE+">")+nodesize,meetingxml.indexOf("</"+USERTYPE+">"));
					mrdsbean.setUserType(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+RADDRESS+">")>-1){
					int nodesize = RADDRESS.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+RADDRESS+">")+nodesize,meetingxml.indexOf("</"+RADDRESS+">"));
					mrdsbean.setAddress(rxmlvalue);
				}
				if(meetingxml.indexOf("<"+EMAIL+">")>-1){
					int nodesize = EMAIL.length()+2;
					String rxmlvalue = meetingxml.substring(meetingxml.indexOf("<"+EMAIL+">")+nodesize,meetingxml.indexOf("</"+EMAIL+">"));
					mrdsbean.setEmail(rxmlvalue);
				}
				list.add(mrdsbean);
				returnxml = returnxml.substring(returnxml.indexOf("</"+USER+">")+econsize,returnxml.length());
			}
			mrsbean.setList(list);
			
			return mrsbean;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
