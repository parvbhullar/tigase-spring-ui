package com.ivyinfo.mail.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class FileBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684274L;
	
	private String id;						//id
	private String mailid;					//邮件id
	private String filename;				//附件名称
	private String filepath;				//邮件附件在FTP上的路径
	
	private String logname;
	
	public String getLogname() {
		return logname;
	}
	public void setLogname(String logname) {
		this.logname = logname;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMailid() {
		return mailid;
	}
	public void setMailid(String mailid) {
		this.mailid = mailid;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
}
