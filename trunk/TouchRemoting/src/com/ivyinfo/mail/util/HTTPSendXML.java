package com.ivyinfo.mail.util;

import com.ivyinfo.framework.common.xml.XMLTools;
import com.ivyinfo.mail.bean.MailXMLBean;
import com.ivyinfo.mail.bean.SendXMLBean;

public class HTTPSendXML implements MailXMLBean{
	private static final String XML_VERSION =
        "<?xml version=\"1.0\" encoding=\""
        + System.getProperty("file.encoding")
        + "\"?>";
	
	public String BeanToXML(SendXMLBean sxBean) throws Exception{
		try{
			StringBuffer stringbuf = new StringBuffer(XML_VERSION);
			
			stringbuf.append("<").append(MESSAGE).append(">");
			
			stringbuf.append(XMLTools.createTagFromString(COMMAND, sxBean.getCommand()));
			stringbuf.append(XMLTools.createTagFromString(ADDRESS, sxBean.getAddress()));
			stringbuf.append(XMLTools.createTagFromString(NAME, sxBean.getName()));
			stringbuf.append(XMLTools.createTagFromString(PASSWORD, sxBean.getPassword()));
			stringbuf.append(XMLTools.createTagFromString(COMPANY, sxBean.getCompany()));
			stringbuf.append(XMLTools.createTagFromString(UID, sxBean.getUid()));
			stringbuf.append(XMLTools.createTagFromString(MSGNO, sxBean.getMsgno()));
			stringbuf.append(XMLTools.createTagFromString(DATE, sxBean.getDate()));
			stringbuf.append(XMLTools.createTagFromString(FTPUSERNAME, sxBean.getFtpusername()));
			stringbuf.append(XMLTools.createTagFromString(FTPUSERPASS, sxBean.getFtpuserpass()));
			
			stringbuf.append("</").append(MESSAGE).append(">");
			
			return stringbuf.toString();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
