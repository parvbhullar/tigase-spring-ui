package com.ivyinfo.webcall.api.util;

import com.ivyinfo.framework.service.servlet.HttpRespons;
import com.ivyinfo.webcall.api.bean.CallReturnBean;
import com.ivyinfo.webcall.api.bean.CallSendBean;

public class WebCallSendUtil{
	
	public CallReturnBean StartCall(CallSendBean callSendBean) throws Exception{
		WebCallSendXML webCallSendXML = new WebCallSendXML();
		WebCallAnalysisXML webCallAnalysisXML = new WebCallAnalysisXML();
		
		//组装XML
		String startcallxml = webCallSendXML.StartCall(callSendBean);
		System.err.println("[StartCall] startcallxml:"+startcallxml);
		
		//http发送请求
		HttpSendWebCall httpSendWebCall = new HttpSendWebCall();
		HttpRespons hr = httpSendWebCall.HttpSendWebCall(startcallxml);
		
		//http返回
		String startcallRXML = hr.getContent();
		System.err.println("[StartCall] startcallRXML:"+startcallRXML);
		
		//解析返回的XML
		CallReturnBean callReturnBean = webCallAnalysisXML.StartCall(startcallRXML);
		return callReturnBean;
	}
	
	public CallReturnBean EndCall(CallSendBean callSendBean) throws Exception{
		WebCallSendXML webCallSendXML = new WebCallSendXML();
		WebCallAnalysisXML webCallAnalysisXML = new WebCallAnalysisXML();
		
		//组装XML
		String endcallxml = webCallSendXML.EndCall(callSendBean);
		System.err.println("[EndCall] endcallxml:"+endcallxml);
		
		//http发送请求
		HttpSendWebCall httpSendWebCall = new HttpSendWebCall();
		HttpRespons hr = httpSendWebCall.HttpSendWebCall(endcallxml);
		
		//http返回
		String startcallRXML = hr.getContent();
		System.err.println("[EndCall] startcallRXML:"+startcallRXML);
		
		//解析返回的XML
		CallReturnBean callReturnBean = webCallAnalysisXML.EndCall(startcallRXML);
		return callReturnBean;
	}
	
	public CallReturnBean CdrRequest(CallSendBean callSendBean) throws Exception{
		WebCallSendXML webCallSendXML = new WebCallSendXML();
		WebCallAnalysisXML webCallAnalysisXML = new WebCallAnalysisXML();
		
		//组装XML
		String cdrcallxml = webCallSendXML.CdrRequest(callSendBean);
		System.err.println("[CdrRequest] cdrcallxml:"+cdrcallxml);
		
		//http发送请求
		HttpSendWebCall httpSendWebCall = new HttpSendWebCall();
		HttpRespons hr = httpSendWebCall.HttpSendWebCall(cdrcallxml);
		
		//http返回
		String startcallRXML = hr.getContent();
		System.err.println("[CdrRequest] startcallRXML:"+startcallRXML);
		
		//解析返回的XML
		CallReturnBean callReturnBean = webCallAnalysisXML.CdrRequest(startcallRXML);
		return callReturnBean;
	}
	
	public CallReturnBean StatusRequest(CallSendBean callSendBean) throws Exception{
		WebCallSendXML webCallSendXML = new WebCallSendXML();
		WebCallAnalysisXML webCallAnalysisXML = new WebCallAnalysisXML();
		
		//组装XML
		String statuscallxml = webCallSendXML.StatusRequest(callSendBean);
		System.err.println("[StatusRequest] statuscallxml:"+statuscallxml);
		
		//http发送请求
		HttpSendWebCall httpSendWebCall = new HttpSendWebCall();
		HttpRespons hr = httpSendWebCall.HttpSendWebCall(statuscallxml);
		
		//http返回
		String startcallRXML = hr.getContent();
		System.err.println("[StatusRequest] startcallRXML:"+startcallRXML);
		
		//解析返回的XML
		CallReturnBean callReturnBean = webCallAnalysisXML.StatusRequest(startcallRXML);
		return callReturnBean;
	}
}
