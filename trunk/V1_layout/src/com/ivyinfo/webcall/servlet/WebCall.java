package com.ivyinfo.webcall.servlet;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.meeting.services.MeetingServices;
import com.ivyinfo.user.bean.UserBean;
import com.ivyinfo.webcall.api.bean.CallSendBean;
import com.ivyinfo.webcall.api.services.WebCallServices;

public class WebCall {
	private static final Logger LOGGER = LoggerFactory.getLogger(WebCall.class);
	
	public String CallPhone(HttpServletRequest request,UserBean suserBean) throws Exception{
		WebCallServices webCallServices = (WebCallServices) SpringContextUtil.getBean("webCallServices");
		
		String mobilephone = request.getParameter("PNInput");
		System.err.println("mobilephone:"+mobilephone);
		
		CallSendBean callSendBean = new CallSendBean();
		callSendBean.setUserCardNo(suserBean.getCardnumber());//卡号
		callSendBean.setCaller(suserBean.getMobilephone());//主叫号码
		callSendBean.setCalled(mobilephone);//被叫号码
		callSendBean.setFeeTotal(100);//帐户余额
		
		return webCallServices.StartCall(callSendBean);
	}
}
