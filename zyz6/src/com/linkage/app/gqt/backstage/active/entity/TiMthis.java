package com.linkage.app.gqt.backstage.active.entity;

import java.util.Date;

public class TiMthis implements java.io.Serializable {

	// Fields

	private String gatewayid;

	private String usertablename;

	private String dstid;

	private String srcid;

	private String feeid;

	private String msgcontent;

	private String linkid;

	private String serviceid;

	private Long recordtype;

	private Long countfee;

	private Long fixedfee;

	private String messageId;

	private Date committime;

	private Long retrytimes;

	private Long actid;
	
	private String actName;

	// Constructors

	/** default constructor */
	public TiMthis() {
	}

	/** minimal constructor */
	public TiMthis(String dstid) {
		this.dstid = dstid;
	}

	/** full constructor */
	public TiMthis(String gatewayid, String usertablename, String dstid,
			String srcid, String feeid, String msgcontent, String linkid,
			String serviceid, Long recordtype, Long countfee, Long fixedfee,
			String messageId, Date committime, Long retrytimes, Long actid) {
		this.gatewayid = gatewayid;
		this.usertablename = usertablename;
		this.dstid = dstid;
		this.srcid = srcid;
		this.feeid = feeid;
		this.msgcontent = msgcontent;
		this.linkid = linkid;
		this.serviceid = serviceid;
		this.recordtype = recordtype;
		this.countfee = countfee;
		this.fixedfee = fixedfee;
		this.messageId = messageId;
		this.committime = committime;
		this.retrytimes = retrytimes;
		this.actid = actid;
	}

	// Property accessors

	public String getGatewayid() {
		return this.gatewayid;
	}

	public void setGatewayid(String gatewayid) {
		this.gatewayid = gatewayid;
	}

	public String getUsertablename() {
		return this.usertablename;
	}

	public void setUsertablename(String usertablename) {
		this.usertablename = usertablename;
	}

	public String getDstid() {
		return this.dstid;
	}

	public void setDstid(String dstid) {
		this.dstid = dstid;
	}

	public String getSrcid() {
		return this.srcid;
	}

	public void setSrcid(String srcid) {
		this.srcid = srcid;
	}

	public String getFeeid() {
		return this.feeid;
	}

	public void setFeeid(String feeid) {
		this.feeid = feeid;
	}

	public String getMsgcontent() {
		return this.msgcontent;
	}

	public void setMsgcontent(String msgcontent) {
		this.msgcontent = msgcontent;
	}

	public String getLinkid() {
		return this.linkid;
	}

	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}

	public String getServiceid() {
		return this.serviceid;
	}

	public void setServiceid(String serviceid) {
		this.serviceid = serviceid;
	}

	public Long getRecordtype() {
		return this.recordtype;
	}

	public void setRecordtype(Long recordtype) {
		this.recordtype = recordtype;
	}

	public Long getCountfee() {
		return this.countfee;
	}

	public void setCountfee(Long countfee) {
		this.countfee = countfee;
	}

	public Long getFixedfee() {
		return this.fixedfee;
	}

	public void setFixedfee(Long fixedfee) {
		this.fixedfee = fixedfee;
	}

	public String getMessageId() {
		return this.messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public Date getCommittime() {
		return this.committime;
	}

	public void setCommittime(Date committime) {
		this.committime = committime;
	}

	public Long getRetrytimes() {
		return this.retrytimes;
	}

	public void setRetrytimes(Long retrytimes) {
		this.retrytimes = retrytimes;
	}

	public Long getActid() {
		return this.actid;
	}

	public void setActid(Long actid) {
		this.actid = actid;
	}
	
	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TiMthis))
			return false;
		TiMthis castOther = (TiMthis) other;

		return ((this.getGatewayid() == castOther.getGatewayid()) || (this
				.getGatewayid() != null
				&& castOther.getGatewayid() != null && this.getGatewayid()
				.equals(castOther.getGatewayid())))
				&& ((this.getUsertablename() == castOther.getUsertablename()) || (this
						.getUsertablename() != null
						&& castOther.getUsertablename() != null && this
						.getUsertablename()
						.equals(castOther.getUsertablename())))
				&& ((this.getDstid() == castOther.getDstid()) || (this
						.getDstid() != null
						&& castOther.getDstid() != null && this.getDstid()
						.equals(castOther.getDstid())))
				&& ((this.getSrcid() == castOther.getSrcid()) || (this
						.getSrcid() != null
						&& castOther.getSrcid() != null && this.getSrcid()
						.equals(castOther.getSrcid())))
				&& ((this.getFeeid() == castOther.getFeeid()) || (this
						.getFeeid() != null
						&& castOther.getFeeid() != null && this.getFeeid()
						.equals(castOther.getFeeid())))
				&& ((this.getMsgcontent() == castOther.getMsgcontent()) || (this
						.getMsgcontent() != null
						&& castOther.getMsgcontent() != null && this
						.getMsgcontent().equals(castOther.getMsgcontent())))
				&& ((this.getLinkid() == castOther.getLinkid()) || (this
						.getLinkid() != null
						&& castOther.getLinkid() != null && this.getLinkid()
						.equals(castOther.getLinkid())))
				&& ((this.getServiceid() == castOther.getServiceid()) || (this
						.getServiceid() != null
						&& castOther.getServiceid() != null && this
						.getServiceid().equals(castOther.getServiceid())))
				&& ((this.getRecordtype() == castOther.getRecordtype()) || (this
						.getRecordtype() != null
						&& castOther.getRecordtype() != null && this
						.getRecordtype().equals(castOther.getRecordtype())))
				&& ((this.getCountfee() == castOther.getCountfee()) || (this
						.getCountfee() != null
						&& castOther.getCountfee() != null && this
						.getCountfee().equals(castOther.getCountfee())))
				&& ((this.getFixedfee() == castOther.getFixedfee()) || (this
						.getFixedfee() != null
						&& castOther.getFixedfee() != null && this
						.getFixedfee().equals(castOther.getFixedfee())))
				&& ((this.getMessageId() == castOther.getMessageId()) || (this
						.getMessageId() != null
						&& castOther.getMessageId() != null && this
						.getMessageId().equals(castOther.getMessageId())))
				&& ((this.getCommittime() == castOther.getCommittime()) || (this
						.getCommittime() != null
						&& castOther.getCommittime() != null && this
						.getCommittime().equals(castOther.getCommittime())))
				&& ((this.getRetrytimes() == castOther.getRetrytimes()) || (this
						.getRetrytimes() != null
						&& castOther.getRetrytimes() != null && this
						.getRetrytimes().equals(castOther.getRetrytimes())))
				&& ((this.getActid() == castOther.getActid()) || (this
						.getActid() != null
						&& castOther.getActid() != null && this.getActid()
						.equals(castOther.getActid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getGatewayid() == null ? 0 : this.getGatewayid().hashCode());
		result = 37
				* result
				+ (getUsertablename() == null ? 0 : this.getUsertablename()
						.hashCode());
		result = 37 * result
				+ (getDstid() == null ? 0 : this.getDstid().hashCode());
		result = 37 * result
				+ (getSrcid() == null ? 0 : this.getSrcid().hashCode());
		result = 37 * result
				+ (getFeeid() == null ? 0 : this.getFeeid().hashCode());
		result = 37
				* result
				+ (getMsgcontent() == null ? 0 : this.getMsgcontent()
						.hashCode());
		result = 37 * result
				+ (getLinkid() == null ? 0 : this.getLinkid().hashCode());
		result = 37 * result
				+ (getServiceid() == null ? 0 : this.getServiceid().hashCode());
		result = 37
				* result
				+ (getRecordtype() == null ? 0 : this.getRecordtype()
						.hashCode());
		result = 37 * result
				+ (getCountfee() == null ? 0 : this.getCountfee().hashCode());
		result = 37 * result
				+ (getFixedfee() == null ? 0 : this.getFixedfee().hashCode());
		result = 37 * result
				+ (getMessageId() == null ? 0 : this.getMessageId().hashCode());
		result = 37
				* result
				+ (getCommittime() == null ? 0 : this.getCommittime()
						.hashCode());
		result = 37
				* result
				+ (getRetrytimes() == null ? 0 : this.getRetrytimes()
						.hashCode());
		result = 37 * result
				+ (getActid() == null ? 0 : this.getActid().hashCode());
		return result;
	}

}