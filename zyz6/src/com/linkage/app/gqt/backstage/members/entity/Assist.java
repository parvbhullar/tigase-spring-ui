package com.linkage.app.gqt.backstage.members.entity;

import java.util.Date;
/**
 * 帮扶对象po类
 *
 *
 * @author jiale.wang
 *
 * @create on 2010-8-14 上午10:59:56
 *
 * @version v1.0
 *
 * @Copyright (c) 2009 by linkaged.
 *
 * @see
 */
public class Assist {
	private long aid;
	private String userName;
	private String name;
	private String nickName;
	private String password;
	private String cellPhone;
	private String email;
	private int sex;
	private Date birthday;
	private String credcode;//证件号码
	private String credtype;//证件类型
	private int isvalid;//是否有效0:无效 1:有效
	private int isverify;//审核状态 0:待审 1:审核通过 2:审核不通过
	private String verifydesc;//审核意见
	private Date createtime;//记录创建日期
	private double totalpoints;//积分
	private long volunorgid;//机构id
	private String volunorgname;//组织机构名称
	private String hometel;//家庭电话
	private String address;//家庭地址
	
	public long getAid() {
		return aid;
	}
	public void setAid(long aid) {
		this.aid = aid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getCredcode() {
		return credcode;
	}
	public void setCredcode(String credcode) {
		this.credcode = credcode;
	}
	public String getCredtype() {
		return credtype;
	}
	public void setCredtype(String credtype) {
		this.credtype = credtype;
	}
	public int getIsvalid() {
		return isvalid;
	}
	public void setIsvalid(int isvalid) {
		this.isvalid = isvalid;
	}
	public int getIsverify() {
		return isverify;
	}
	public void setIsverify(int isverify) {
		this.isverify = isverify;
	}
	public String getVerifydesc() {
		return verifydesc;
	}
	public void setVerifydesc(String verifydesc) {
		this.verifydesc = verifydesc;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public double getTotalpoints() {
		return totalpoints;
	}
	public void setTotalpoints(double totalpoints) {
		this.totalpoints = totalpoints;
	}
	public long getVolunorgid() {
		return volunorgid;
	}
	public void setVolunorgid(long volunorgid) {
		this.volunorgid = volunorgid;
	}
	public String getVolunorgname() {
		return volunorgname;
	}
	public void setVolunorgname(String volunorgname) {
		this.volunorgname = volunorgname;
	}
	public String getHometel() {
		return hometel;
	}
	public void setHometel(String hometel) {
		this.hometel = hometel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
