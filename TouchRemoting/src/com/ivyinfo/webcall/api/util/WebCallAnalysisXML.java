package com.ivyinfo.webcall.api.util;

import com.ivyinfo.framework.common.xml.XMLTools;
import com.ivyinfo.webcall.api.bean.CallReturnBean;
import com.ivyinfo.webcall.api.bean.CallReturnXMLBean;

public class WebCallAnalysisXML implements CallReturnXMLBean{
	
	public CallReturnBean StartCall(String startcallRXML) throws Exception{
		CallReturnBean callReturnBean = null;
		if(startcallRXML != null){
			if(startcallRXML.indexOf("<req>")>-1){
				callReturnBean = new CallReturnBean();
				
				String result = XMLTools.getXMLValue(startcallRXML, RESULT);
				callReturnBean.setResult(result);
				String reason = XMLTools.getXMLValue(startcallRXML, REASON);
				callReturnBean.setReason(reason);
				String identitySequence = XMLTools.getXMLValue(startcallRXML, IDENTITYSEQUENCE);
				callReturnBean.setIdentitySequence(identitySequence);
				String userCardNo = XMLTools.getXMLValue(startcallRXML, USERCARDNO);
				callReturnBean.setUserCardNo(userCardNo);
				String caller = XMLTools.getXMLValue(startcallRXML, CALLER);
				callReturnBean.setCaller(caller);
				String called = XMLTools.getXMLValue(startcallRXML, CALLED);
				callReturnBean.setCalled(called);
			}
		}
		return callReturnBean;
	}
	
	public CallReturnBean EndCall(String startcallRXML) throws Exception{
		CallReturnBean callReturnBean = null;
		if(startcallRXML != null){
			if(startcallRXML.indexOf("<req>")>-1){
				callReturnBean = new CallReturnBean();
				
				String result = XMLTools.getXMLValue(startcallRXML, RESULT);
				callReturnBean.setResult(result);
				String reason = XMLTools.getXMLValue(startcallRXML, REASON);
				callReturnBean.setReason(reason);
				String identitySequence = XMLTools.getXMLValue(startcallRXML, IDENTITYSEQUENCE);
				callReturnBean.setIdentitySequence(identitySequence);
				String userCardNo = XMLTools.getXMLValue(startcallRXML, USERCARDNO);
				callReturnBean.setUserCardNo(userCardNo);
			}
		}
		return callReturnBean;
	}
	
	public CallReturnBean CdrRequest(String startcallRXML) throws Exception{
		CallReturnBean callReturnBean = null;
//		if(startcallRXML != null){
//			if(startcallRXML.indexOf("<req>")>-1){
//				callReturnBean = new CallReturnBean();
//				
//				String result = XMLTools.getXMLValue(startcallRXML, RESULT);
//				callReturnBean.setResult(result);
//				String reason = XMLTools.getXMLValue(startcallRXML, REASON);
//				callReturnBean.setReason(reason);
//				String identitySequence = XMLTools.getXMLValue(startcallRXML, IDENTITYSEQUENCE);
//				callReturnBean.setIdentitySequence(identitySequence);
//				String userCardNo = XMLTools.getXMLValue(startcallRXML, USERCARDNO);
//				callReturnBean.setUserCardNo(userCardNo);
//				String caller = XMLTools.getXMLValue(startcallRXML, CALLER);
//				callReturnBean.setCaller(caller);
//				String called = XMLTools.getXMLValue(startcallRXML, CALLED);
//				callReturnBean.setCalled(called);
//			}
//		}
		return callReturnBean;
	}
	
	public CallReturnBean StatusRequest(String startcallRXML) throws Exception{
		CallReturnBean callReturnBean = null;
		if(startcallRXML != null){
			if(startcallRXML.indexOf("<req>")>-1){
				callReturnBean = new CallReturnBean();
				
				String result = XMLTools.getXMLValue(startcallRXML, RESULT);
				callReturnBean.setResult(result);
				String status = XMLTools.getXMLValue(startcallRXML, STATUS);
				callReturnBean.setStatus(status);
				String identitySequence = XMLTools.getXMLValue(startcallRXML, IDENTITYSEQUENCE);
				callReturnBean.setIdentitySequence(identitySequence);
				String userCardNo = XMLTools.getXMLValue(startcallRXML, USERCARDNO);
				callReturnBean.setUserCardNo(userCardNo);
				String usedFee = XMLTools.getXMLValue(startcallRXML, USEDFEE);
				callReturnBean.setUsedFee(usedFee);
				String talkingTime = XMLTools.getXMLValue(startcallRXML, TALKINGTIME);
				callReturnBean.setTalkingTime(talkingTime);
			}
		}
		return callReturnBean;
	}
}
