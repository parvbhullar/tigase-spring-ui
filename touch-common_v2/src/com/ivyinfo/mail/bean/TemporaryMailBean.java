package com.ivyinfo.mail.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class TemporaryMailBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684238L;
	
	private String id;						//id
	private String mailid;					//邮件id
	private String mailno;					//邮件编号
	private String userid;					//用户id
	private String addname;					//邮箱地址
	
	public String getMailno() {
		return mailno;
	}
	public void setMailno(String mailno) {
		this.mailno = mailno;
	}
	public String getAddname() {
		return addname;
	}
	public void setAddname(String addname) {
		this.addname = addname;
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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
}
