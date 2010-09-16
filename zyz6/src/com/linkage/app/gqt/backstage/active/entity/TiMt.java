package com.linkage.app.gqt.backstage.active.entity;

public class TiMt {
	/**
	 * 
	 *GATEWAYID     VARCHAR2(10),
	  USERTABLENAME VARCHAR2(32),
	  DSTID         VARCHAR2(64) not null,
	  SRCID         VARCHAR2(64),
	  FEEID         VARCHAR2(64),
	  MSGCONTENT    VARCHAR2(140),
	  LINKID        VARCHAR2(20),
	  SERVICEID     VARCHAR2(10),
	  RECORDTYPE    NUMBER(2),
	  COUNTFEE      NUMBER(16),
	  FIXEDFEE      NUMBER(16),
	  MESSAGE_ID    VARCHAR2(50),
	  COMMITTIME    DATE,
	  RETRYTIMES    NUMBER(2)
	 */
	
	private String getewyId;
	private String userTableName;
	private String dstId;
	private String srcId;
	private String msgContent;
	private String linkId;
	private String serviceId;
	private long recordType;
	private long countFee;
	private long fixedFee;
	private String MessageId;
	private String committiem;
	private long retryTimes;
	public String getGetewyId() {
		return getewyId;
	}
	public void setGetewyId(String getewyId) {
		this.getewyId = getewyId;
	}
	public String getUserTableName() {
		return userTableName;
	}
	public void setUserTableName(String userTableName) {
		this.userTableName = userTableName;
	}
	public String getDstId() {
		return dstId;
	}
	public void setDstId(String dstId) {
		this.dstId = dstId;
	}
	public String getSrcId() {
		return srcId;
	}
	public void setSrcId(String srcId) {
		this.srcId = srcId;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public String getLinkId() {
		return linkId;
	}
	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public long getRecordType() {
		return recordType;
	}
	public void setRecordType(long recordType) {
		this.recordType = recordType;
	}
	public long getCountFee() {
		return countFee;
	}
	public void setCountFee(long countFee) {
		this.countFee = countFee;
	}
	public long getFixedFee() {
		return fixedFee;
	}
	public void setFixedFee(long fixedFee) {
		this.fixedFee = fixedFee;
	}
	public String getMessageId() {
		return MessageId;
	}
	public void setMessageId(String messageId) {
		MessageId = messageId;
	}
	public String getCommittiem() {
		return committiem;
	}
	public void setCommittiem(String committiem) {
		this.committiem = committiem;
	}
	public long getRetryTimes() {
		return retryTimes;
	}
	public void setRetryTimes(long retryTimes) {
		this.retryTimes = retryTimes;
	}
	

}
