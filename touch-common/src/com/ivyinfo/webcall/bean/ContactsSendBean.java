package com.ivyinfo.webcall.bean;

import com.ivyinfo.framework.common.encryption.Base64;
import com.ivyinfo.framework.common.encryption.DES;
import com.ivyinfo.framework.common.encryption.MD5_WebCall;
import com.ivyinfo.framework.common.time.TimeTools;
import com.ivyinfo.framework.service.base.BaseBean;

public class ContactsSendBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684254L;
	
	private String sessionid;
	private String action;
	private String service;
	private String code;
	private String password;
	private String cmd;
	private String contactid;
	private String name;
	private String phone;
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
		action = "contacts";
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
	
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getContactid() {
		return contactid;
	}
	public void setContactid(String contactid) {
		contactid = new String(Base64.encode(DES.encrypt(contactid).getBytes()));
		this.contactid = contactid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		name = new String(Base64.encode(DES.encrypt(name).getBytes()));
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		phone = new String(Base64.encode(DES.encrypt(phone).getBytes()));
		this.phone = phone;
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
						MD5_WebCall.getMD5(sessionid + "$" + action +"$"+ service +"$"+ code +"$"+ password +"$"+ cmd +"$"+ contactid +"$"+ name +"$"+ phone +"$"+timestamp).getBytes()));
		this.authenticator = authenticator;
	}
}
