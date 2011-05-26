package com.ivyinfo.mail.util;

import com.ivyinfo.framework.common.xml.XMLTools;
import com.ivyinfo.mail.bean.MailXMLBean;
import com.ivyinfo.mail.bean.SendXMLBean;
import com.ivyinfo.sysemail.bean.SysEmailBean;
import com.ivyinfo.sysemail.bean.SysMailXMLBean;

public class HTTPSysMailXML implements SysMailXMLBean{
	private static final String XML_VERSION =
        "<?xml version=\"1.0\" encoding=\""
        + System.getProperty("file.encoding")
        + "\"?>";
	
	public String BeanToXML(SysEmailBean sysemailBean) throws Exception{
		try{
			StringBuffer stringbuf = new StringBuffer(XML_VERSION);
			
			stringbuf.append("<").append(MESSAGE).append(">");
			
			stringbuf.append(XMLTools.createTagFromString(COMMAND, sysemailBean.getCommand()));
			stringbuf.append(XMLTools.createTagFromString(EMAILNAME, sysemailBean.getName()));
			stringbuf.append(XMLTools.createTagFromString(EMAILTYPE, sysemailBean.getEmailType()));
			stringbuf.append(XMLTools.createTagFromString(POP, sysemailBean.getPopadd()));
			stringbuf.append(XMLTools.createTagFromString(COMPANY, sysemailBean.getCompany()));
			stringbuf.append(XMLTools.createTagFromString(POPPORT, sysemailBean.getPopport()));
			stringbuf.append(XMLTools.createTagFromString(SMTP, sysemailBean.getSmtpadd()));
			stringbuf.append(XMLTools.createTagFromString(SMTPPORT, sysemailBean.getSmtpport()));
			stringbuf.append(XMLTools.createTagFromString(SSL, sysemailBean.getSslvalidation()));
			
			stringbuf.append("</").append(MESSAGE).append(">");
			
			return stringbuf.toString();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
