package com.ivyinfo.webcall.util;

import com.ivyinfo.framework.common.xml.XMLTools;
import com.ivyinfo.webcall.bean.ChangePasswordSendBean;
import com.ivyinfo.webcall.bean.ContactsSendBean;
import com.ivyinfo.webcall.bean.ExitCallSendBean;
import com.ivyinfo.webcall.bean.RechargeSendBean;
import com.ivyinfo.webcall.bean.SearchCardStatusSendBean;
import com.ivyinfo.webcall.bean.SearchRecordSendBean;
import com.ivyinfo.webcall.bean.SearchResidualAmountSendBean;
import com.ivyinfo.webcall.bean.SendBean;
import com.ivyinfo.webcall.bean.UserLoginSendBean;


public class HttpInvokerSendXML implements SendBean{
	
	private static final String XML_VERSION =
        "<?xml version=\"1.0\" encoding=\""
        + System.getProperty("file.encoding")
        + "\"?>";
	
	public static String UserLoginSendBeanToXML(UserLoginSendBean sBean) throws Exception{
		try{
			StringBuffer stringbuf = new StringBuffer(XML_VERSION);
			
			stringbuf.append("<").append(MESSAGE).append(">");
			
			stringbuf.append(XMLTools.createTagFromString(SESSIONID, sBean.getSessionid()));
			stringbuf.append(XMLTools.createTagFromString(ACTION, sBean.getAction()));
			stringbuf.append(XMLTools.createTagFromString(SERVICE, sBean.getService()));
			stringbuf.append(XMLTools.createTagFromString(CODE, sBean.getCode()));
			stringbuf.append(XMLTools.createTagFromString(PASSWORD, sBean.getPassword()));
			stringbuf.append(XMLTools.createTagFromString(TIMESTAMP, sBean.getTimestamp()));
			stringbuf.append(XMLTools.createTagFromString(AUTHENTICATOR, sBean.getAuthenticator()));

			stringbuf.append("</").append(MESSAGE).append(">");
			
			return stringbuf.toString();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static String ChangePasswordSendBeanToXML(ChangePasswordSendBean sBean) throws Exception{
		try{
			StringBuffer stringbuf = new StringBuffer(XML_VERSION);
			
			stringbuf.append("<").append(MESSAGE).append(">");
			
			stringbuf.append(XMLTools.createTagFromString(SESSIONID, sBean.getSessionid()));
			stringbuf.append(XMLTools.createTagFromString(ACTION, sBean.getAction()));
			stringbuf.append(XMLTools.createTagFromString(SERVICE, sBean.getService()));
			stringbuf.append(XMLTools.createTagFromString(CODE, sBean.getCode()));
			stringbuf.append(XMLTools.createTagFromString(OLDPASSWORD, sBean.getOldpassword()));
			stringbuf.append(XMLTools.createTagFromString(NEWPASSWORD, sBean.getNewpassword()));
			stringbuf.append(XMLTools.createTagFromString(TIMESTAMP, sBean.getTimestamp()));
			stringbuf.append(XMLTools.createTagFromString(AUTHENTICATOR, sBean.getAuthenticator()));

			stringbuf.append("</").append(MESSAGE).append(">");
			
			return stringbuf.toString();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static String SearchResidualAmountSendBeanToXML(SearchResidualAmountSendBean sBean) throws Exception{
		try{
			StringBuffer stringbuf = new StringBuffer(XML_VERSION);
			
			stringbuf.append("<").append(MESSAGE).append(">");
			
			stringbuf.append(XMLTools.createTagFromString(SESSIONID, sBean.getSessionid()));
			stringbuf.append(XMLTools.createTagFromString(ACTION, sBean.getAction()));
			stringbuf.append(XMLTools.createTagFromString(SERVICE, sBean.getService()));
			stringbuf.append(XMLTools.createTagFromString(CODE, sBean.getCode()));
			stringbuf.append(XMLTools.createTagFromString(PASSWORD, sBean.getPassword()));
			stringbuf.append(XMLTools.createTagFromString(TIMESTAMP, sBean.getTimestamp()));
			stringbuf.append(XMLTools.createTagFromString(AUTHENTICATOR, sBean.getAuthenticator()));

			stringbuf.append("</").append(MESSAGE).append(">");
			
			return stringbuf.toString();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static String SearchCardStatusSendBeanToXML(SearchCardStatusSendBean sBean) throws Exception{
		try{
			StringBuffer stringbuf = new StringBuffer(XML_VERSION);
			
			stringbuf.append("<").append(MESSAGE).append(">");
			
			stringbuf.append(XMLTools.createTagFromString(SESSIONID, sBean.getSessionid()));
			stringbuf.append(XMLTools.createTagFromString(ACTION, sBean.getAction()));
			stringbuf.append(XMLTools.createTagFromString(SERVICE, sBean.getService()));
			stringbuf.append(XMLTools.createTagFromString(CODE, sBean.getCode()));
			stringbuf.append(XMLTools.createTagFromString(PASSWORD, sBean.getPassword()));
			stringbuf.append(XMLTools.createTagFromString(TIMESTAMP, sBean.getTimestamp()));
			stringbuf.append(XMLTools.createTagFromString(AUTHENTICATOR, sBean.getAuthenticator()));

			stringbuf.append("</").append(MESSAGE).append(">");
			
			return stringbuf.toString();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static String SearchRecordSendBeanToXML(SearchRecordSendBean sBean) throws Exception{
		try{
			StringBuffer stringbuf = new StringBuffer(XML_VERSION);
			
			stringbuf.append("<").append(MESSAGE).append(">");
			
			stringbuf.append(XMLTools.createTagFromString(SESSIONID, sBean.getSessionid()));
			stringbuf.append(XMLTools.createTagFromString(ACTION, sBean.getAction()));
			stringbuf.append(XMLTools.createTagFromString(SERVICE, sBean.getService()));
			stringbuf.append(XMLTools.createTagFromString(CODE, sBean.getCode()));
			stringbuf.append(XMLTools.createTagFromString(PASSWORD, sBean.getPassword()));
			
			stringbuf.append(XMLTools.createTagFromString(STARTDAY, sBean.getStartday()));
			stringbuf.append(XMLTools.createTagFromString(ENDDAY, sBean.getEndday()));
			stringbuf.append(XMLTools.createTagFromString(STARTRECORDNUM, sBean.getStartrecordnum()));
			stringbuf.append(XMLTools.createTagFromString(ENDRECORDNUM, sBean.getEndrecordnum()));
			
			stringbuf.append(XMLTools.createTagFromString(TIMESTAMP, sBean.getTimestamp()));
			stringbuf.append(XMLTools.createTagFromString(AUTHENTICATOR, sBean.getAuthenticator()));

			stringbuf.append("</").append(MESSAGE).append(">");
			
			return stringbuf.toString();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static String RechargeSendBeanToXML(RechargeSendBean sBean) throws Exception{
		try{
			StringBuffer stringbuf = new StringBuffer(XML_VERSION);
			
			stringbuf.append("<").append(MESSAGE).append(">");
			
			stringbuf.append(XMLTools.createTagFromString(SESSIONID, sBean.getSessionid()));
			stringbuf.append(XMLTools.createTagFromString(ACTION, sBean.getAction()));
			stringbuf.append(XMLTools.createTagFromString(SERVICE, sBean.getService()));
			//stringbuf.append(XMLTools.createTagFromString(CODE, sBean.getCode()));
			stringbuf.append(XMLTools.createTagFromString(VCPASSWORD, sBean.getPassword()));
			
//			stringbuf.append(XMLTools.createTagFromString(CURRENCY, sBean.getCurrency()));
//			stringbuf.append(XMLTools.createTagFromString(AMOUNT, sBean.getAmount()));
			
			stringbuf.append(XMLTools.createTagFromString(TIMESTAMP, sBean.getTimestamp()));
			stringbuf.append(XMLTools.createTagFromString(AUTHENTICATOR, sBean.getAuthenticator()));

			stringbuf.append("</").append(MESSAGE).append(">");
			
			return stringbuf.toString();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static String ContactsSendBeanToXML(ContactsSendBean sBean) throws Exception{
		try{
			StringBuffer stringbuf = new StringBuffer(XML_VERSION);
			
			stringbuf.append("<").append(MESSAGE).append(">");
			
			stringbuf.append(XMLTools.createTagFromString(SESSIONID, sBean.getSessionid()));
			stringbuf.append(XMLTools.createTagFromString(ACTION, sBean.getAction()));
			stringbuf.append(XMLTools.createTagFromString(SERVICE, sBean.getService()));
			stringbuf.append(XMLTools.createTagFromString(CODE, sBean.getCode()));
			stringbuf.append(XMLTools.createTagFromString(PASSWORD, sBean.getPassword()));
			
			stringbuf.append(XMLTools.createTagFromString(CMD, sBean.getCmd()));
			stringbuf.append(XMLTools.createTagFromString(CONTACTID, sBean.getContactid()));
			stringbuf.append(XMLTools.createTagFromString(NAME, sBean.getName()));
			stringbuf.append(XMLTools.createTagFromString(PHONE, sBean.getPhone()));
			
			stringbuf.append(XMLTools.createTagFromString(TIMESTAMP, sBean.getTimestamp()));
			stringbuf.append(XMLTools.createTagFromString(AUTHENTICATOR, sBean.getAuthenticator()));

			stringbuf.append("</").append(MESSAGE).append(">");
			
			return stringbuf.toString();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static String ExitCallSendBeanToXML(ExitCallSendBean sBean) throws Exception{
		try{
			StringBuffer stringbuf = new StringBuffer(XML_VERSION);
			
			stringbuf.append("<").append(MESSAGE).append(">");
			
			stringbuf.append(XMLTools.createTagFromString(SESSIONID, sBean.getSessionid()));
			stringbuf.append(XMLTools.createTagFromString(ACTION, sBean.getAction()));
			stringbuf.append(XMLTools.createTagFromString(SERVICE, sBean.getService()));
			stringbuf.append(XMLTools.createTagFromString(CODE, sBean.getCode()));
			stringbuf.append(XMLTools.createTagFromString(PASSWORD, sBean.getPassword()));
			stringbuf.append(XMLTools.createTagFromString(TIMESTAMP, sBean.getTimestamp()));
			stringbuf.append(XMLTools.createTagFromString(AUTHENTICATOR, sBean.getAuthenticator()));

			stringbuf.append("</").append(MESSAGE).append(">");
			
			return stringbuf.toString();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
