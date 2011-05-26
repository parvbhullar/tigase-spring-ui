package com.ivyinfo.meeting.util;

import java.util.HashMap;
import java.util.List;

import com.ivyinfo.meeting.bean.MeetingDetailListSysBean;
import com.ivyinfo.meeting.bean.MeetingDetailSysBean;
import com.ivyinfo.meeting.bean.MeetingSysBean;
import com.ivyinfo.meeting.bean.MeetingXMLSysBean;

public class JavaBeanToXMLSys implements MeetingXMLSysBean{
	
	private static final String XML_VERSION =
        "<?xml version=\"1.0\" encoding=\""
//		+ "UTF-8"
        + System.getProperty("file.encoding")
        + "\"?>";
	
	public String BeanToXMLSys(MeetingSysBean MSBean) throws Exception{
		try{
			StringBuffer stringbuf = new StringBuffer(XML_VERSION);
			
			stringbuf.append("<").append(MESSAGE).append(">");
			stringbuf.append("<").append(HEADER).append(">");
			
			stringbuf.append(MeetingToXML.createTagFromString(ACTION, MSBean.getAction()));
			stringbuf.append(MeetingToXML.createTagFromString(SERVICE, MSBean.getService()));
			stringbuf.append(MeetingToXML.createTagFromString(TYPE, MSBean.getType()));
			stringbuf.append(MeetingToXML.createTagFromString(SITENAME, MSBean.getSiteName()));
			stringbuf.append(MeetingToXML.createTagFromString(USERNAME, MSBean.getUserName()));
			stringbuf.append(MeetingToXML.createTagFromString(PASSWORD, MSBean.getPassword()));
			
			stringbuf.append("</").append(HEADER).append(">");
			stringbuf.append("<").append(BODY).append(">");
			
			MeetingDetailSysBean MDSBean = MSBean.getMDSBean();
			stringbuf.append(MeetingToXML.createTagFromString(CODE, MDSBean.getCode()));
			stringbuf.append(MeetingToXML.createTagFromString(NAME, MDSBean.getName()));
			stringbuf.append(MeetingToXML.createTagFromString(LINKMAN, MDSBean.getLinkman()));
			stringbuf.append(MeetingToXML.createTagFromString(PRINCIPAL, MDSBean.getPrincipal()));
			stringbuf.append(MeetingToXML.createTagFromString(PHONE, MDSBean.getPhone()));
			stringbuf.append(MeetingToXML.createTagFromString(EMAIL, MDSBean.getEmail()));
			stringbuf.append(MeetingToXML.createTagFromString(PARENTORGID, MDSBean.getParentOrgId()));
			stringbuf.append(MeetingToXML.createTagFromString(ADDRESS, MDSBean.getAddress()));
			stringbuf.append(MeetingToXML.createTagFromString(POSTCODE, MDSBean.getPostcode()));
			stringbuf.append(MeetingToXML.createTagFromString(DESCRIPTION, MDSBean.getDescription()));
			
			stringbuf.append(MeetingToXML.createTagFromString(LEVELFLAG, MDSBean.getLevelFlag()));
			
			stringbuf.append(MeetingToXML.createTagFromString(OID, MDSBean.getOrgId()));
			
			if(MDSBean.isOrgrolesign()){
				stringbuf.append(MeetingToXML.createTagFromString(SITE_ID, String.valueOf(MDSBean.getSite_id())));
				stringbuf.append(MeetingToXML.createTagFromString(PARENTORG, MDSBean.getParentorg()));
				stringbuf.append(MeetingToXML.createTagFromString(PARENTORGNAME, MDSBean.getParentorgName()));
				stringbuf.append(MeetingToXML.createTagFromString(SORGNAME, MDSBean.getOrgName()));
				stringbuf.append(MeetingToXML.createTagFromString(TELEPHONE, MDSBean.getTelephone()));
				stringbuf.append(MeetingToXML.createTagFromString(DUTYUSER, String.valueOf(MDSBean.getDutyuser())));
				stringbuf.append(MeetingToXML.createTagFromString(LINKUSER, String.valueOf(MDSBean.getLinkuser())));
				stringbuf.append(MeetingToXML.createTagFromString(CREATOR, MDSBean.getCreator()));
				stringbuf.append(MeetingToXML.createTagFromString(CREATETIME, MDSBean.getCreateTime()));
				stringbuf.append(MeetingToXML.createTagFromString(MODIFIER, MDSBean.getModifier()));
				stringbuf.append(MeetingToXML.createTagFromString(MODIFYTIME, MDSBean.getModifyTime()));
				stringbuf.append(MeetingToXML.createTagFromString(SERVERINFOID, String.valueOf(MDSBean.getServerInfoId())));
				stringbuf.append(MeetingToXML.createTagFromString(SERVERNAME, MDSBean.getServerName()));
				stringbuf.append(MeetingToXML.createTagFromString(SERVERNICKNAME, MDSBean.getServerNickname()));
				stringbuf.append(MeetingToXML.createTagFromString(CHECKED, String.valueOf(MDSBean.isChecked())));
				stringbuf.append(MeetingToXML.createTagFromString(ORGSEQUENCE, String.valueOf(MDSBean.getOrgSequence())));
				stringbuf.append(MeetingToXML.createTagFromString(ROLES, MDSBean.getRoles()));
				stringbuf.append(MeetingToXML.createTagFromString(OID, MDSBean.getId()));
				stringbuf.append(MeetingToXML.createTagFromString(ORGCODE, MDSBean.getOrgCode()));
			}
			
			if(MDSBean.isUsersign()){
				stringbuf.append(MeetingToXML.createTagFromString(SUSERNAME, MDSBean.getUserName()));
				stringbuf.append(MeetingToXML.createTagFromString(SNICKNAME, MDSBean.getNickname()));
				stringbuf.append(MeetingToXML.createTagFromString(SPASSWORD, MDSBean.getPassword()));
				stringbuf.append(MeetingToXML.createTagFromString(SFIRSTNAME, MDSBean.getFirstName()));
				stringbuf.append(MeetingToXML.createTagFromString(SLASTNAME, MDSBean.getLastName()));
				stringbuf.append(MeetingToXML.createTagFromString(SGENDER, String.valueOf(MDSBean.getGender())));
				stringbuf.append(MeetingToXML.createTagFromString(SCOMPANY, MDSBean.getCompany()));
				stringbuf.append(MeetingToXML.createTagFromString(SDEPTID, MDSBean.getDeptId()));
				stringbuf.append(MeetingToXML.createTagFromString(SGROUPIDS, String.valueOf(MDSBean.getGroupIds())));
				stringbuf.append(MeetingToXML.createTagFromString(SROLEIDS, String.valueOf(MDSBean.getRoleIds())));
				stringbuf.append(MeetingToXML.createTagFromString(SDUTY, MDSBean.getDuty()));
				stringbuf.append(MeetingToXML.createTagFromString(SREPORTTO, String.valueOf(MDSBean.getReportTo())));
				stringbuf.append(MeetingToXML.createTagFromString(SUSERTYPE, String.valueOf(MDSBean.getUserType())));
				stringbuf.append(MeetingToXML.createTagFromString(SEXTENSION, MDSBean.getExtension()));
				stringbuf.append(MeetingToXML.createTagFromString(SEIMUSED, String.valueOf(MDSBean.getEimUsed())));
				stringbuf.append(MeetingToXML.createTagFromString(SEASYCALLUSED, String.valueOf(MDSBean.getEasycallUsed())));
				stringbuf.append(MeetingToXML.createTagFromString(SEMAIL, MDSBean.getEmail()));
				stringbuf.append(MeetingToXML.createTagFromString(SOTHEREMAIL, MDSBean.getOtherEmail()));
				stringbuf.append(MeetingToXML.createTagFromString(SOFFICEPHONE, MDSBean.getOfficePhone()));
				stringbuf.append(MeetingToXML.createTagFromString(SOTHERPHONE, MDSBean.getOtherPhone()));
				stringbuf.append(MeetingToXML.createTagFromString(SCELLPHONE, MDSBean.getCellphone()));
				stringbuf.append(MeetingToXML.createTagFromString(SFAX, MDSBean.getFax()));
				stringbuf.append(MeetingToXML.createTagFromString(SCOUNTRY, MDSBean.getCountry()));
				stringbuf.append(MeetingToXML.createTagFromString(SPROVINCE, MDSBean.getProvince()));
				stringbuf.append(MeetingToXML.createTagFromString(SCITY, MDSBean.getCity()));
				stringbuf.append(MeetingToXML.createTagFromString(SADDRESS, MDSBean.getAddress()));
				stringbuf.append(MeetingToXML.createTagFromString(SPOSTCODE, MDSBean.getPostcode()));
				stringbuf.append(MeetingToXML.createTagFromString(SOTHERINFO, MDSBean.getOtherInfo()));
				stringbuf.append(MeetingToXML.createTagFromString(SENABLED, String.valueOf(MDSBean.getEnabled())));
				stringbuf.append(MeetingToXML.createTagFromString(SFORCECREATE, String.valueOf(MDSBean.getForceCreate())));
			}
			
			if(MDSBean.isParameterLists()){
				List parameterList = (List)MDSBean.getParameterList();
				if(MDSBean.isParameters()){
					stringbuf.append("<").append(PARAMETERS).append(">");
				}
				
				for(int i=0;i<parameterList.size();i++){
					MeetingDetailListSysBean MDLSBean = (MeetingDetailListSysBean)parameterList.get(i);
					
					stringbuf.append("<").append(PARAMETER).append(">");
					
					stringbuf.append(MeetingToXML.createTagFromString(TYPES, String.valueOf(MDLSBean.getType())));
					stringbuf.append(MeetingToXML.createTagFromString(VALUES, MDLSBean.getValue()));
					
					stringbuf.append("</").append(PARAMETER).append(">");
				}
				if(MDSBean.isParameters()){
					stringbuf.append("</").append(PARAMETERS).append(">");
				}
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
