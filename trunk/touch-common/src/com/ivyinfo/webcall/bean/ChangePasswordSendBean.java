package com.ivyinfo.webcall.bean;

import com.ivyinfo.framework.common.encryption.Base64;
import com.ivyinfo.framework.common.encryption.DES;
import com.ivyinfo.framework.common.encryption.MD5_WebCall;
import com.ivyinfo.framework.common.time.TimeTools;
import com.ivyinfo.framework.service.base.BaseBean;

public class ChangePasswordSendBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684252L;
	
	private String sessionid;
	private String action;
	private String service;
	private String code;
	private String oldpassword;
	private String newpassword;
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
		action = "updatePassword";
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
	public String getOldpassword() {
		return oldpassword;
	}
	public void setOldpassword(String oldpassword) {
		oldpassword = new String(Base64.encode(DES.encrypt(oldpassword).getBytes()));
		this.oldpassword = oldpassword;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		newpassword = new String(Base64.encode(DES.encrypt(newpassword).getBytes()));
		this.newpassword = newpassword;
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
						MD5_WebCall.getMD5(sessionid + "$" + action +"$"+ service +"$"+ code +"$"+ oldpassword +"$"+ newpassword +"$"+timestamp).getBytes()));
		this.authenticator = authenticator;
	}
}
