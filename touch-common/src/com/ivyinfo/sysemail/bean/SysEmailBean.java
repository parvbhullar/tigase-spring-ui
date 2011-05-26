package com.ivyinfo.sysemail.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class SysEmailBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684280L;
	
	private String id;						//id
	private String name;					//邮箱名称
	private String smtpadd;					//发送地址
	private String smtpport;				//发送端口
	private String popadd;					//接收地址
	private String popport;					//接收端口
	private String sslvalidation;			//SSL验证
	
	private String emailType;
	private String company;
	private String command;
	
	public String getEmailType() {
		return emailType;
	}
	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSmtpadd() {
		return smtpadd;
	}
	public void setSmtpadd(String smtpadd) {
		this.smtpadd = smtpadd;
	}
	public String getSmtpport() {
		return smtpport;
	}
	public void setSmtpport(String smtpport) {
		this.smtpport = smtpport;
	}
	public String getPopadd() {
		return popadd;
	}
	public void setPopadd(String popadd) {
		this.popadd = popadd;
	}
	public String getPopport() {
		return popport;
	}
	public void setPopport(String popport) {
		this.popport = popport;
	}
	public String getSslvalidation() {
		return sslvalidation;
	}
	public void setSslvalidation(String sslvalidation) {
		this.sslvalidation = sslvalidation;
	}
}
