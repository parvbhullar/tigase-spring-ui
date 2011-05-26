package com.ivyinfo.webcall.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class ErrorReceiveBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684255L;
	
	private String sessionid;
	private String result;
	private String code;
	private String reason;
	private String exceptionid;
	private String timestamp;
	private String authenticator;
	
	public String getSessionid() {
		return sessionid;
	}
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getExceptionid() {
		return exceptionid;
	}
	public void setExceptionid(String exceptionid) {
		this.exceptionid = exceptionid;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getAuthenticator() {
		return authenticator;
	}
	public void setAuthenticator(String authenticator) {
		this.authenticator = authenticator;
	}
}
