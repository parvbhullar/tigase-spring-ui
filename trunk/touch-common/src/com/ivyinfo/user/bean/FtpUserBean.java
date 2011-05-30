package com.ivyinfo.user.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class FtpUserBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684276L;
	
	private String host;
	private String ftpusername;
	private String ftppassword;
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getFtpusername() {
		return ftpusername;
	}
	public void setFtpusername(String ftpusername) {
		this.ftpusername = ftpusername;
	}
	public String getFtppassword() {
		return ftppassword;
	}
	public void setFtppassword(String ftppassword) {
		this.ftppassword = ftppassword;
	}
}
