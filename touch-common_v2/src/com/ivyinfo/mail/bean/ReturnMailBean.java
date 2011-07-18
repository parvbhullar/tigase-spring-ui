package com.ivyinfo.mail.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class ReturnMailBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684233L;
	
	private String subject;
	private String from;
	private String to;
	private String sentdate;
	private String bcc;
	private String cc;
	private String uid;
	private String msgno;
	private String content;
	private String filename;
	private String fileAtt;
	
	private String deletemail;
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSentdate() {
		return sentdate;
	}
	public void setSentdate(String sentdate) {
		this.sentdate = sentdate;
	}
	public String getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFileAtt() {
		return fileAtt;
	}
	public void setFileAtt(String fileAtt) {
		this.fileAtt = fileAtt;
	}
	public String getDeletemail() {
		return deletemail;
	}
	public void setDeletemail(String deletemail) {
		this.deletemail = deletemail;
	}
	
}
