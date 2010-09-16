package com.linkage.app.gqt.purview.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails{
	
	private static final long serialVersionUID = -613517057935325182L;
	/*USERID	NUMBER(16)	N			
	LOGINNAME	VARCHAR2(20)	N			
	LOGINPASS	VARCHAR2(20)	N			
	NICKNAME	VARCHAR2(40)	Y			
	DN	VARCHAR2(13)	Y			
	SEX	NUMBER(1)	Y	1		
	EMAIL	VARCHAR2(50)	Y			
	STATE	NUMBER(1)	Y	1		
	ORGID	NUMBER(16)	N			
	CREATEDATE	DATE	N	SYSDATE		
	UPDATEDATE	DATE	Y	SYSDATE*/
	
	private long userId;
	private String loginName;
	private String loginPass;
	private String nickName;
	private String dn;
	private int sex;
	private String email;
	private int state;
	private long orgId;
	private Date createDate;
	private Date updateDate;
	
	private Map<Long,List<Role>> orgId2roles;//用户的角色
	private List<Menu> menus;
	private List<Role> currentOrgToRoles;
	private long currentOrgId;
	private String ip;
	
	public long getCurrentOrgId() {
		return currentOrgId;
	}

	public void setCurrentOrgId(long currentOrgId) {
		this.currentOrgId = currentOrgId;
	}

	public List<Role> getCurrentOrgToRoles() {
		return currentOrgToRoles;
	}

	public void setCurrentOrgToRoles(List<Role> currentOrgToRoles) {
		this.currentOrgToRoles = currentOrgToRoles;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getDn() {
		return dn;
	}

	public void setDn(String dn) {
		this.dn = dn;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Map<Long,List<Role>> getOrgId2roles() {
		return orgId2roles;
	}

	public void setOrgId2roles(Map<Long,List<Role>> orgId2roles) {
		this.orgId2roles = orgId2roles;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Collection<GrantedAuthority> getAuthorities() { 
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		if(currentOrgToRoles!=null){
			for(Role role:currentOrgToRoles){
				grantedAuthorities.add(new GrantedAuthorityImpl(role.getRoleName()));
			}
		}
		return grantedAuthorities;
	} 

	public String getPassword() {
		return loginPass;
	}
	
	public void setPassword(String loginPass){
		this.loginPass=loginPass;
	}
	
	public String getUsername() {
		return loginName;
	}
	
	public void setUsername(String loginName){
		this.loginName=loginName;
	}
	
	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		if(state==1){
			return true;
		}
		return false;
	}
}
