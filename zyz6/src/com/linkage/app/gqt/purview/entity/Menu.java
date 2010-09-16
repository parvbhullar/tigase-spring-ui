package com.linkage.app.gqt.purview.entity;

import java.io.Serializable;

public class Menu implements Serializable{
	
	/*MENUID	NUMBER(16)	N			
	PARENTMENUID	NUMBER(16)	N	0		
	MENUNAME	VARCHAR2(50)	N			
	MENUORDER	NUMBER(16)	N			
	URL	VARCHAR2(255)	Y			
	MENUSTATE	NUMBER(1)	Y			
	MENUTYPE	NUMBER(1)	Y*/
	
	private static final long serialVersionUID = -8073586481016534121L;
	private long menuId;
	private long parentMenuId;
	private String menuName;
	private long menuOrder;
	private String url;
	private int menuState;
	
	private int level;
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public long getMenuId() {
		return menuId;
	}
	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}
	public long getParentMenuId() {
		return parentMenuId;
	}
	public void setParentMenuId(long parentMenuId) {
		this.parentMenuId = parentMenuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public long getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(long menuOrder) {
		this.menuOrder = menuOrder;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getMenuState() {
		return menuState;
	}
	public void setMenuState(int menuState) {
		this.menuState = menuState;
	}

}
