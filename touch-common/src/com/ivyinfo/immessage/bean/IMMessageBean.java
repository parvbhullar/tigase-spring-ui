package com.ivyinfo.immessage.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class IMMessageBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684286L;
	
	private String id;					//id
	private String senduser;			//发送用户的登录名
	private String touser;				//接收用户的登录名
	private String message;				//消息内容
	private String sendyyyy;			//发送时间(年)
	private String sendmm;				//发送时间(月)
	private String senddd;				//发送时间(日)
	private String sendhh;				//发送时间(小时)
	private String sendmin;				//发送时间(分钟)
	private String sendss;				//发送时间(秒)
	private String readyyyy;			//读取时间(年)
	private String readmm;				//读取时间(月)
	private String readdd;				//读取时间(日)
	private String readhh;				//读取时间(小时)
	private String readmin;				//读取时间(分钟)
	private String readss;				//读取时间(秒)
	private String readflag;			//读取标记  0：未读  1：已读
	private String nicesenduser;		//发送用户的昵称
	private String nicetouser;			//接收用户的昵称
	private String messagetype;			//消息类型  1：发送  2：接收
	private String savename;			//保存名称
	
	public String getSavename() {
		return savename;
	}
	public void setSavename(String savename) {
		this.savename = savename;
	}
	public String getReadflag() {
		return readflag;
	}
	public void setReadflag(String readflag) {
		this.readflag = readflag;
	}
	public String getMessagetype() {
		return messagetype;
	}
	public void setMessagetype(String messagetype) {
		this.messagetype = messagetype;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSenduser() {
		return senduser;
	}
	public void setSenduser(String senduser) {
		this.senduser = senduser;
	}
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSendyyyy() {
		return sendyyyy;
	}
	public void setSendyyyy(String sendyyyy) {
		this.sendyyyy = sendyyyy;
	}
	public String getSendmm() {
		return sendmm;
	}
	public void setSendmm(String sendmm) {
		this.sendmm = sendmm;
	}
	public String getSenddd() {
		return senddd;
	}
	public void setSenddd(String senddd) {
		this.senddd = senddd;
	}
	public String getSendhh() {
		return sendhh;
	}
	public void setSendhh(String sendhh) {
		this.sendhh = sendhh;
	}
	public String getSendmin() {
		return sendmin;
	}
	public void setSendmin(String sendmin) {
		this.sendmin = sendmin;
	}
	public String getSendss() {
		return sendss;
	}
	public void setSendss(String sendss) {
		this.sendss = sendss;
	}
	public String getReadyyyy() {
		return readyyyy;
	}
	public void setReadyyyy(String readyyyy) {
		this.readyyyy = readyyyy;
	}
	public String getReadmm() {
		return readmm;
	}
	public void setReadmm(String readmm) {
		this.readmm = readmm;
	}
	public String getReaddd() {
		return readdd;
	}
	public void setReaddd(String readdd) {
		this.readdd = readdd;
	}
	public String getReadhh() {
		return readhh;
	}
	public void setReadhh(String readhh) {
		this.readhh = readhh;
	}
	public String getReadmin() {
		return readmin;
	}
	public void setReadmin(String readmin) {
		this.readmin = readmin;
	}
	public String getReadss() {
		return readss;
	}
	public void setReadss(String readss) {
		this.readss = readss;
	}
	public String getNicesenduser() {
		return nicesenduser;
	}
	public void setNicesenduser(String nicesenduser) {
		this.nicesenduser = nicesenduser;
	}
	public String getNicetouser() {
		return nicetouser;
	}
	public void setNicetouser(String nicetouser) {
		this.nicetouser = nicetouser;
	}
}
