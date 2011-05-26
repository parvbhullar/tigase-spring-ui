package com.ivyinfo.webcall.api.util;

import com.ivyinfo.framework.common.xml.XMLTools;
import com.ivyinfo.webcall.api.bean.CallSendBean;
import com.ivyinfo.webcall.api.bean.CallSendXMLBean;

public class WebCallSendXML implements CallSendXMLBean{
	
	public String StartCall(CallSendBean callSendBean) throws Exception{
		StringBuffer stringbuf = new StringBuffer();
		
		stringbuf.append("<soapenv:Envelope xmlns:soapenv='").append(callSendBean.getSoapenv())
			.append("' xmlns:ctd='").append(callSendBean.getCtd()).append("'>");
		stringbuf.append("<soapenv:Header/>");
		stringbuf.append("<soapenv:Body>");
		stringbuf.append("<ctd:StartCall>");
		stringbuf.append("<req>");
		
		stringbuf.append(XMLTools.createTagFromString(USERCARDNO, callSendBean.getUserCardNo()));
		stringbuf.append(XMLTools.createTagFromString(CALLER, callSendBean.getCaller()));
		stringbuf.append(XMLTools.createTagFromString(CALLED, callSendBean.getCalled()));
		stringbuf.append(XMLTools.createTagFromString(FEETOTAL, String.valueOf(callSendBean.getFeeTotal())));
		
		stringbuf.append("</req>");
		stringbuf.append("</ctd:StartCall>");
		stringbuf.append("</soapenv:Body>");
		stringbuf.append("</soapenv:Envelope>");
		
		return stringbuf.toString();
	}
	
	public String EndCall(CallSendBean callSendBean) throws Exception{
		StringBuffer stringbuf = new StringBuffer();
		
		stringbuf.append("<soapenv:Envelope xmlns:soapenv='").append(callSendBean.getSoapenv())
			.append("' xmlns:ctd='").append(callSendBean.getCtd()).append("'>");
		stringbuf.append("<soapenv:Header/>");
		stringbuf.append("<soapenv:Body>");
		stringbuf.append("<ctd:EndCall>");
		stringbuf.append("<req>");
		
		stringbuf.append(XMLTools.createTagFromString(USERCARDNO, callSendBean.getUserCardNo()));
		stringbuf.append(XMLTools.createTagFromString(IDENTITYSEQUENCE, callSendBean.getIdentitySequence()));
		
		stringbuf.append("</req>");
		stringbuf.append("</ctd:EndCall>");
		stringbuf.append("</soapenv:Body>");
		stringbuf.append("</soapenv:Envelope>");
		
		return stringbuf.toString();
	}
	
	public String CdrRequest(CallSendBean callSendBean) throws Exception{
		StringBuffer stringbuf = new StringBuffer();
		
		stringbuf.append("<soapenv:Envelope xmlns:soapenv='").append(callSendBean.getSoapenv())
			.append("' xmlns:ctd='").append(callSendBean.getCtd()).append("'>");
		stringbuf.append("<soapenv:Header/>");
		stringbuf.append("<soapenv:Body>");
		stringbuf.append("<ctd:CdrRequest>");
		stringbuf.append("<req>");
		
		stringbuf.append(XMLTools.createTagFromString(USERCARDNO, callSendBean.getUserCardNo()));
		stringbuf.append(XMLTools.createTagFromString(CALLER, callSendBean.getCaller()));
		stringbuf.append(XMLTools.createTagFromString(CALLED, callSendBean.getCalled()));
		stringbuf.append(XMLTools.createTagFromString(STARTTIME, callSendBean.getStartTime()));
		stringbuf.append(XMLTools.createTagFromString(ENDTIME, callSendBean.getEndTime()));
		
		stringbuf.append("</req>");
		stringbuf.append("</ctd:CdrRequest>");
		stringbuf.append("</soapenv:Body>");
		stringbuf.append("</soapenv:Envelope>");
		
		return stringbuf.toString();
	}
	
	public String StatusRequest(CallSendBean callSendBean) throws Exception{
		StringBuffer stringbuf = new StringBuffer();
		
		stringbuf.append("<soapenv:Envelope xmlns:soapenv='").append(callSendBean.getSoapenv())
			.append("' xmlns:ctd='").append(callSendBean.getCtd()).append("'>");
		stringbuf.append("<soapenv:Header/>");
		stringbuf.append("<soapenv:Body>");
		stringbuf.append("<ctd:StatusRequest>");
		stringbuf.append("<req>");
		
		stringbuf.append(XMLTools.createTagFromString(USERCARDNO, callSendBean.getUserCardNo()));
		stringbuf.append(XMLTools.createTagFromString(IDENTITYSEQUENCE, callSendBean.getIdentitySequence()));
		
		stringbuf.append("</req>");
		stringbuf.append("</ctd:StatusRequest>");
		stringbuf.append("</soapenv:Body>");
		stringbuf.append("</soapenv:Envelope>");
		
		return stringbuf.toString();
	}

}
