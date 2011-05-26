package com.ivyinfo.mail.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class SysMailBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684237L;
	
	private String name;				//邮箱名称  163、QQ、yahoo、gmail等
	private String smtpAdd;				//smtp地址  smtp.163.com
	private String smtpPort;			//smtp端口  25
	private String popAdd;				//pop地址  pop.163.com
	private String popPort;				//pop端口  110
	private String mailSSL;				//是否需要SSL验证  true、false
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSmtpAdd() {
		return smtpAdd;
	}
	public void setSmtpAdd(String smtpAdd) {
		this.smtpAdd = smtpAdd;
	}
	public String getSmtpPort() {
		return smtpPort;
	}
	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}
	public String getPopAdd() {
		return popAdd;
	}
	public void setPopAdd(String popAdd) {
		this.popAdd = popAdd;
	}
	public String getPopPort() {
		return popPort;
	}
	public void setPopPort(String popPort) {
		this.popPort = popPort;
	}
	public String getMailSSL() {
		return mailSSL;
	}
	public void setMailSSL(String mailSSL) {
		this.mailSSL = mailSSL;
	}
}
