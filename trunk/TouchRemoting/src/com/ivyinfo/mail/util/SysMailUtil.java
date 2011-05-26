package com.ivyinfo.mail.util;

import com.ivyinfo.framework.common.file.ResouceLoader;
import com.ivyinfo.framework.service.servlet.HttpRequester;
import com.ivyinfo.framework.service.servlet.HttpRespons;
import com.ivyinfo.sysemail.bean.SysEmailBean;
import com.ivyinfo.sysemail.bean.SysMailXMLBean;

public class SysMailUtil implements SysMailXMLBean{
	
	public void setEmailPort(SysEmailBean sysemailBean) throws Exception{
		HttpRequester request = new HttpRequester();
		
		ResouceLoader resouceloader = new ResouceLoader();
		String mailURL = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.mail.api.mailUrl");
		
		sysemailBean.setCommand("setEmailPort");
		sysemailBean.setCompany("9997");
		sysemailBean.setEmailType("pop3");
		
		HTTPSysMailXML HTTPSysMailXML = new HTTPSysMailXML();
		String mailbeantoxml = HTTPSysMailXML.BeanToXML(sysemailBean);
		System.err.println("mailbeantoxml:"+mailbeantoxml);
		
		HttpRespons hr = request.sendPost(mailURL, mailbeantoxml);
		String mailxml = hr.getContent();
		System.err.println("mailxml:"+mailxml);
	}
}
