package com.ivyinfo.mail.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class SetupMailBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684236L;
	
	private String id;							//id
	private String addname;						//地址名称
	private String logname;						//登陆名称
	private String logpassword;					//登陆密码
	private String defaultsendadd;				//是否默认发件  true:默认发件地址  false:不是默认发件地址
	private String receiveallmail;				//是否收取全部邮件  0[收取全部] 1[当天] 2[1天内] 3[2天内] 4[3天内] 5[1周内] 6[2周内] 7[3周内] 8[1月内] 9[2月内] 10[3月内]
	private String delmail;						//删除邮件时是否删除服务器邮件  0[保留服务器邮件]  1[删除服务器邮件]
	private String contentsavelocal;			//内容是否保留本地  true：保留本地  false：不保留本地
	private String timestemp;					//时间戳
	
	private String userlogname;					//系统用户登录名
	
	public String getUserlogname() {
		return userlogname;
	}
	public void setUserlogname(String userlogname) {
		this.userlogname = userlogname;
	}
	public String getTimestemp() {
		return timestemp;
	}
	public void setTimestemp(String timestemp) {
		this.timestemp = timestemp;
	}
	public String getAddname() {
		return addname;
	}
	public void setAddname(String addname) {
		this.addname = addname;
	}
	public String getLogname() {
		return logname;
	}
	public void setLogname(String logname) {
		this.logname = logname;
	}
	public String getLogpassword() {
		return logpassword;
	}
	public void setLogpassword(String logpassword) {
		this.logpassword = logpassword;
	}
	public String getDefaultsendadd() {
		return defaultsendadd;
	}
	public void setDefaultsendadd(String defaultsendadd) {
		this.defaultsendadd = defaultsendadd;
	}
	public String getReceiveallmail() {
		return receiveallmail;
	}
	public void setReceiveallmail(String receiveallmail) {
		this.receiveallmail = receiveallmail;
	}
	public String getDelmail() {
		return delmail;
	}
	public void setDelmail(String delmail) {
		this.delmail = delmail;
	}
	public String getContentsavelocal() {
		return contentsavelocal;
	}
	public void setContentsavelocal(String contentsavelocal) {
		this.contentsavelocal = contentsavelocal;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
