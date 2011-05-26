package com.ivyinfo.webcall.bean;

import com.ivyinfo.framework.common.encryption.Base64;
import com.ivyinfo.framework.common.encryption.DES;
import com.ivyinfo.framework.common.encryption.MD5_WebCall;
import com.ivyinfo.framework.common.time.TimeTools;
import com.ivyinfo.framework.service.base.BaseBean;

public class RechargeSendBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684259L;
	
	private String sessionid;
	private String action;
	private String service;
	private String code;
	private String password;
	private String currency;
	private String amount;
	private String timestamp;
	private String authenticator;
	
	public String getSessionid() {
		return sessionid;
	}
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		action = "recharge";
		this.action = action;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		service = "webCall";
		this.service = service;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		code = new String(Base64.encode(DES.encrypt(code).getBytes()));
		this.code = code;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		password = new String(Base64.encode(DES.encrypt(password).getBytes()));
		this.password = password;
	}
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		currency = new String(Base64.encode(DES.encrypt(currency).getBytes()));
		this.currency = currency;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		amount = new String(Base64.encode(DES.encrypt(amount).getBytes()));
		this.amount = amount;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		timestamp = TimeTools.getDateTime_webCall();
		this.timestamp = timestamp;
	}
	public String getAuthenticator() {
		return authenticator;
	}
	public void setAuthenticator(String authenticator) {
		authenticator = new String(
				Base64.encode(
						MD5_WebCall.getMD5(sessionid + "$" + action +"$"+ service +"$"+ password +"$"+ timestamp).getBytes()));
		this.authenticator = authenticator;
	}
}
