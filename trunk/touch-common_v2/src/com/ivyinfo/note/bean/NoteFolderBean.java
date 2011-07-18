package com.ivyinfo.note.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class NoteFolderBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684295L;
	
	private String id;
	private String username;
	private String folderName;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFolderName() {
		return folderName;
	}
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
}
