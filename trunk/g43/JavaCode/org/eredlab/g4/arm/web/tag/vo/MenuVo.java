package org.eredlab.g4.arm.web.tag.vo;

import org.eredlab.g4.ccl.datastructure.impl.BaseVo;

/**
 * 菜单值对象
 * @author XiongChun
 * @since 2010-01-22
 */
public class MenuVo extends BaseVo{
	
	private String menuid;
	private String menuname;
	private String iconcls;
	private String parentid;
	private String request;
	private String leaf;
	private String isNotLast;
	private String isRoot;
	private String expanded;
	private String menupath;
	private String icon;
	private String checked;
	
	public String getMenuid() {
		return menuid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getIsNotLast() {
		return isNotLast;
	}
	public void setIsNotLast(String isNotLast) {
		this.isNotLast = isNotLast;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}
	public String getParentid() {
		return parentid;
	}
	public String getRequest() {
		return request;
	}
	public String getIsRoot() {
		return isRoot;
	}
	public void setIsRoot(String isRoot) {
		this.isRoot = isRoot;
	}
	public String getIconcls() {
		return iconcls;
	}
	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
	}
	public String getLeaf() {
		return leaf;
	}
	public String getExpanded() {
		return expanded;
	}
	public void setExpanded(String expanded) {
		this.expanded = expanded;
	}
	public String getMenupath() {
		return menupath;
	}
	public void setMenupath(String menupath) {
		this.menupath = menupath;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	
}
