package com.ivyinfo.mail.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class SendXMLBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684235L;
	
	private String command;
	private String address;
	private String name;
	private String password;
	private String company;
	private String uid;
	private String msgno;
	private String date;
	private String ftpusername;
	private String ftpuserpass;
	
	public String getFtpusername() {
		return ftpusername;
	}
	public void setFtpusername(String ftpusername) {
		this.ftpusername = ftpusername;
	}
	public String getFtpuserpass() {
		return ftpuserpass;
	}
	public void setFtpuserpass(String ftpuserpass) {
		this.ftpuserpass = ftpuserpass;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getMsgno() {
		return msgno;
	}
	public void setMsgno(String msgno) {
		this.msgno = msgno;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
