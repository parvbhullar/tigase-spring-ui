package com.ivyinfo.webcall.bean;

import java.util.List;

import com.ivyinfo.framework.service.base.BaseBean;

public class SearchRecordReceiveBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684263L;
	
	private String sessionid;
	private String result;
	private String code;
	private String recordnum;
	private String timestamp;
	private String authenticator;
	private List<RecordsBean> records;
	private ErrorReceiveBean erBean;
	
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
	public String getRecordnum() {
		return recordnum;
	}
	public void setRecordnum(String recordnum) {
		this.recordnum = recordnum;
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
	public List<RecordsBean> getRecords() {
		return records;
	}
	public void setRecords(List<RecordsBean> records) {
		this.records = records;
	}
	public ErrorReceiveBean getErBean() {
		return erBean;
	}
	public void setErBean(ErrorReceiveBean erBean) {
		this.erBean = erBean;
	}
	
	
}
