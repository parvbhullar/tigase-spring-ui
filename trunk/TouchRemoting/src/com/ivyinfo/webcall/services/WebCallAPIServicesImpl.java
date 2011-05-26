package com.ivyinfo.webcall.services;

import com.ivyinfo.framework.common.encryption.DES;
import com.ivyinfo.framework.common.file.ResouceLoader;
import com.ivyinfo.framework.common.time.TimeTools;
import com.ivyinfo.framework.service.servlet.HttpRequester;
import com.ivyinfo.framework.service.servlet.HttpRespons;
import com.ivyinfo.webcall.bean.ChangePasswordReceiveBean;
import com.ivyinfo.webcall.bean.ChangePasswordSendBean;
import com.ivyinfo.webcall.bean.ContactsReceiveBean;
import com.ivyinfo.webcall.bean.ContactsSendBean;
import com.ivyinfo.webcall.bean.ExitCallReceiveBean;
import com.ivyinfo.webcall.bean.ExitCallSendBean;
import com.ivyinfo.webcall.bean.RechargeReceiveBean;
import com.ivyinfo.webcall.bean.RechargeSendBean;
import com.ivyinfo.webcall.bean.SearchCardStatusReceiveBean;
import com.ivyinfo.webcall.bean.SearchCardStatusSendBean;
import com.ivyinfo.webcall.bean.SearchRecordReceiveBean;
import com.ivyinfo.webcall.bean.SearchRecordSendBean;
import com.ivyinfo.webcall.bean.SearchResidualAmountReceiveBean;
import com.ivyinfo.webcall.bean.SearchResidualAmountSendBean;
import com.ivyinfo.webcall.bean.UserLoginReceiveBean;
import com.ivyinfo.webcall.bean.UserLoginSendBean;
import com.ivyinfo.webcall.util.HttpInvokerReceiveXML;
import com.ivyinfo.webcall.util.HttpInvokerSendXML;

public class WebCallAPIServicesImpl implements IWebCallAPIServices{
	ResouceLoader resouceloader = new ResouceLoader();
	String propertiesPath = "/com/ivyinfo/framework/service/config/webmeettouch.properties";

	public UserLoginReceiveBean UserLoginService(UserLoginSendBean ulsbean) throws Exception {
		try{
			String webCallURL = resouceloader.getXMLUrl(propertiesPath, "com.ivyinfo.webcall.api.webCallUrl.userlogin");
			HttpRequester request = new HttpRequester();
			ulsbean.setAction("");
			ulsbean.setService("");
			ulsbean.setTimestamp("");
			ulsbean.setAuthenticator("");
			String SendXML = HttpInvokerSendXML.UserLoginSendBeanToXML(ulsbean);
			System.err.println("===SendXML:"+SendXML);
			System.err.println("===webCallURL:"+webCallURL);
            HttpRespons hr = request.sendPost(webCallURL, SendXML);
            return HttpInvokerReceiveXML.XMLToUserLoginReceiveBean(hr.getContent());
//			UserLoginReceiveBean testb = new UserLoginReceiveBean();
//			testb.setResult("1");
//			testb.setUrl("http://www.163.com");
//			return testb;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public ContactsReceiveBean ContactsService(ContactsSendBean sbean)throws Exception {
		try{
			String webCallURL = resouceloader.getXMLUrl(propertiesPath, "com.ivyinfo.webcall.api.webCallUrl.contacts");
			HttpRequester request = new HttpRequester();
			sbean.setAction("");
			sbean.setService("");
			sbean.setTimestamp("");
			sbean.setAuthenticator("");
			String SendXML = HttpInvokerSendXML.ContactsSendBeanToXML(sbean);
			System.err.println("===SendXML:"+SendXML);
			System.err.println("===webCallURL:"+webCallURL);
            HttpRespons hr = request.sendPost(webCallURL, SendXML);
            return HttpInvokerReceiveXML.XMLToContactsReceiveBean(hr.getContent());
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public ExitCallReceiveBean ExitCallService(ExitCallSendBean sbean)throws Exception {
		try{
			String webCallURL = resouceloader.getXMLUrl(propertiesPath, "com.ivyinfo.webcall.api.webCallUrl.exitCall");
			HttpRequester request = new HttpRequester();
			sbean.setAction("");
			sbean.setService("");
			sbean.setTimestamp("");
			sbean.setAuthenticator("");
			String SendXML = HttpInvokerSendXML.ExitCallSendBeanToXML(sbean);
			System.err.println("===SendXML:"+SendXML);
			System.err.println("===webCallURL:"+webCallURL);
            HttpRespons hr = request.sendPost(webCallURL, SendXML);
            return HttpInvokerReceiveXML.XMLToExitCallReceiveBean(hr.getContent());
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public RechargeReceiveBean RechargeService(RechargeSendBean sbean)throws Exception {
		try{
			String webCallURL = resouceloader.getXMLUrl(propertiesPath, "com.ivyinfo.webcall.api.webCallUrl.recharge");
			HttpRequester request = new HttpRequester();
			sbean.setAction("");
			sbean.setService("");
			sbean.setTimestamp("");
			sbean.setAuthenticator("");
			String SendXML = HttpInvokerSendXML.RechargeSendBeanToXML(sbean);
			System.err.println("===SendXML:"+SendXML);
			System.err.println("===webCallURL:"+webCallURL);
            HttpRespons hr = request.sendPost(webCallURL, SendXML);
            return HttpInvokerReceiveXML.XMLToRechargeReceiveBean(hr.getContent());
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public SearchCardStatusReceiveBean SearchCardStatusService(
			SearchCardStatusSendBean sbean) throws Exception {
		try{
			String webCallURL = resouceloader.getXMLUrl(propertiesPath, "com.ivyinfo.webcall.api.webCallUrl.searchCardStatus");
			HttpRequester request = new HttpRequester();
			sbean.setAction("");
			sbean.setService("");
			sbean.setTimestamp("");
			sbean.setAuthenticator("");
			String SendXML = HttpInvokerSendXML.SearchCardStatusSendBeanToXML(sbean);
			System.err.println("===SendXML:"+SendXML);
			System.err.println("===webCallURL:"+webCallURL);
            HttpRespons hr = request.sendPost(webCallURL, SendXML);
            return HttpInvokerReceiveXML.XMLToSearchCardStatusReceiveBean(hr.getContent());
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public SearchRecordReceiveBean SearchRecordService(
			SearchRecordSendBean sbean) throws Exception {
		try{
			String webCallURL = resouceloader.getXMLUrl(propertiesPath, "com.ivyinfo.webcall.api.webCallUrl.searchRecord");
			HttpRequester request = new HttpRequester();
			sbean.setAction("");
			sbean.setService("");
			sbean.setTimestamp("");
			sbean.setAuthenticator("");
			String SendXML = HttpInvokerSendXML.SearchRecordSendBeanToXML(sbean);
			System.err.println("===SendXML:"+SendXML);
			System.err.println("===webCallURL:"+webCallURL);
            HttpRespons hr = request.sendPost(webCallURL, SendXML);
            return HttpInvokerReceiveXML.XMLToSearchRecordReceiveBean(hr.getContent());
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public SearchResidualAmountReceiveBean SearchResidualAmountService(
			SearchResidualAmountSendBean sbean) throws Exception {
		try{
			String webCallURL = resouceloader.getXMLUrl(propertiesPath, "com.ivyinfo.webcall.api.webCallUrl.searchResidualAmount");
			HttpRequester request = new HttpRequester();
			sbean.setAction("");
			sbean.setService("");
			sbean.setTimestamp("");
			sbean.setAuthenticator("");
			String SendXML = HttpInvokerSendXML.SearchResidualAmountSendBeanToXML(sbean);
			System.err.println("===SendXML:"+SendXML);
			System.err.println("===webCallURL:"+webCallURL);
            HttpRespons hr = request.sendPost(webCallURL, SendXML);
            return HttpInvokerReceiveXML.XMLToSearchResidualAmountReceiveBean(hr.getContent());
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public ChangePasswordReceiveBean UpdatePasswordService(
			ChangePasswordSendBean sbean) throws Exception {
		try{
			String webCallURL = resouceloader.getXMLUrl(propertiesPath, "com.ivyinfo.webcall.api.webCallUrl.updatePassword");
			HttpRequester request = new HttpRequester();
			sbean.setAction("");
			sbean.setService("");
			sbean.setTimestamp("");
			sbean.setAuthenticator("");
			String SendXML = HttpInvokerSendXML.ChangePasswordSendBeanToXML(sbean);
			System.err.println("===SendXML:"+SendXML);
			System.err.println("===webCallURL:"+webCallURL);
            HttpRespons hr = request.sendPost(webCallURL, SendXML);
            return HttpInvokerReceiveXML.XMLToChangePasswordReceiveBean(hr.getContent());
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
