package com.ivyinfo.mail.bean;

import com.ivyinfo.framework.service.base.BaseBean;

/**
 * 已删除邮箱
 * @author mzq
 *
 */
public class DeleteMailBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684229L;
	
	private String id;						//邮件id
	private String originalid;				//原邮件id
	private String sendname;				//发件人
	private String receivename;				//收件人
	private String copyname;				//抄送人
	private String secretname;				//密送人
	private String subject;					//邮件主题
	private String content;					//邮件内容
	private String filename;				//邮件附件
	private String datetime;				//邮件时间
	private String level;					//邮件级别  0：普通  1：紧急
	private String type;					//原邮箱类型  1：收件箱  2：发件箱  3：草稿箱
	private String state;					//邮件状态  0：未读  1：已读
	private String mailId;					//邮件ID
	private String mailnumber;				//邮件编号
	
	private String logname;					//用户登录名
	
	public String getLogname() {
		return logname;
	}
	public void setLogname(String logname) {
		this.logname = logname;
	}
	public String getOriginalid() {
		return originalid;
	}
	public void setOriginalid(String originalid) {
		this.originalid = originalid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSendname() {
		return sendname;
	}
	public void setSendname(String sendname) {
		this.sendname = sendname;
	}
	public String getReceivename() {
		return receivename;
	}
	public void setReceivename(String receivename) {
		this.receivename = receivename;
	}
	public String getCopyname() {
		return copyname;
	}
	public void setCopyname(String copyname) {
		this.copyname = copyname;
	}
	public String getSecretname() {
		return secretname;
	}
	public void setSecretname(String secretname) {
		this.secretname = secretname;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getMailnumber() {
		return mailnumber;
	}
	public void setMailnumber(String mailnumber) {
		this.mailnumber = mailnumber;
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
	
}
