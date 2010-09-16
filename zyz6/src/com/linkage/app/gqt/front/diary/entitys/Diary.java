package com.linkage.app.gqt.front.diary.entitys;

import java.util.Date;


public class Diary implements java.io.Serializable {

	// Fields

	private Long recid;

	private String title;

	private String content;

	private Long senduid;

	private String senduname;

	private String sendnickname;

	private Date sendtime;

	private Long srcrecid;
	
	private String isverify;

	// Constructors

	/** default constructor */
	public Diary() {
	}

	/** full constructor */
	public Diary(String title, String content, Long senduid,
			String senduname, String sendnickname, Date sendtime, Long srcrecid) {
		this.title = title;
		this.content = content;
		this.senduid = senduid;
		this.senduname = senduname;
		this.sendnickname = sendnickname;
		this.sendtime = sendtime;
		this.srcrecid = srcrecid;
	}

	// Property accessors

	public Long getRecid() {
		return this.recid;
	}

	public void setRecid(Long recid) {
		this.recid = recid;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getSenduid() {
		return this.senduid;
	}

	public void setSenduid(Long senduid) {
		this.senduid = senduid;
	}

	public String getSenduname() {
		return this.senduname;
	}

	public void setSenduname(String senduname) {
		this.senduname = senduname;
	}

	public String getSendnickname() {
		return this.sendnickname;
	}

	public void setSendnickname(String sendnickname) {
		this.sendnickname = sendnickname;
	}

	public Date getSendtime() {
		return this.sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}

	public Long getSrcrecid() {
		return this.srcrecid;
	}

	public void setSrcrecid(Long srcrecid) {
		this.srcrecid = srcrecid;
	}

	public String getIsverify() {
		return isverify;
	}

	public void setIsverify(String isverify) {
		this.isverify = isverify;
	}
	
	

}