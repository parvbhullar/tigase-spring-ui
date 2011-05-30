package com.ivyinfo.imcontacts.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class IMContactsBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684289L;
	
	private String id;
	private String nid;
	private String uid;
	private String pkey;
	private String pval;
	private String logname;
	
	public String getLogname() {
		return logname;
	}
	public void setLogname(String logname) {
		this.logname = logname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPkey() {
		return pkey;
	}
	public void setPkey(String pkey) {
		this.pkey = pkey;
	}
	public String getPval() {
		return pval;
	}
	public void setPval(String pval) {
		this.pval = pval;
	}
}
