package com.ivyinfo.test.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class TestBean extends BaseBean implements java.io.Serializable{
	
	private static final long serialVersionUID = -4433034043604684227L;
	
	private String id;
	private String title;
	private int num;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	
}
