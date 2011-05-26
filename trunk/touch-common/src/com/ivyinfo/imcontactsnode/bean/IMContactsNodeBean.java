package com.ivyinfo.imcontactsnode.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class IMContactsNodeBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684292L;
	
	private String id;
	private String nid;
	private String parent_nid;
	private String uid;
	private String node;
	
	private String logname;

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

	public String getParent_nid() {
		return parent_nid;
	}

	public void setParent_nid(String parentNid) {
		parent_nid = parentNid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getLogname() {
		return logname;
	}

	public void setLogname(String logname) {
		this.logname = logname;
	}
}
