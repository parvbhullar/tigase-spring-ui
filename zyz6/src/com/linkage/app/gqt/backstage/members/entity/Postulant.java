package com.linkage.app.gqt.backstage.members.entity;

import java.util.Date;
/**
 * 志愿者po类
 *
 *
 * @author jiale.wang
 *
 * @create on 2009-8-12 下午02:07:56
 *
 * @version v1.0
 *
 * @Copyright (c) 2009 by linkaged.
 *
 * @see
 */
public class Postulant {
	private long id;
	private String loginName;
	private String name;
	//private String nickName;
	private String password;
	private String dn;//手机号码
	private String email;
	private int sex;
	private Date birthday;
	private String education;//学历
	private String profession;//职业
	private String hobby;//爱好特长
	//private String location;//所属社区ID
	private long communityid;//社区ID
	private String intention;//服务意向
	private String servetimes;//服务时间
	//private long volunorgid;//机构id
	private long orgid;//机构id
	private String orgname;//机构名称
	private String volunorgname;//组织机构名称
	private String credcode;//证件号码
	private String credtype;//证件类型
	private int isvalid;//是否有效0:无效 1:有效
	private int isverify;//审核状态 0:待审 1:审核通过 2:审核不通过
	private String verifydesc;//审核意见
	private Date createtime;//记录创建日期
	private String regip;//注册ip
	private Date regdate;//注册日期
	private String lastloginip;//最好登录ip
	private double totalpoints;//积分
	//private Date lastlognumbertime;
	private String communityname;//社区名称
	private Date deletetime;//删除日期
	private String phone;//电话
	private String address;//地址
	private String jn;//技能
	private String nation;//民族
	private String outresion;//退出原因
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDn() {
		return dn;
	}
	public void setDn(String dn) {
		this.dn = dn;
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
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public long getCommunityid() {
		return communityid;
	}
	public void setCommunityid(long communityid) {
		this.communityid = communityid;
	}
	public String getIntention() {
		return intention;
	}
	public void setIntention(String intention) {
		this.intention = intention;
	}
	public String getServetimes() {
		return servetimes;
	}
	public void setServetimes(String servetimes) {
		this.servetimes = servetimes;
	}
	public long getOrgid() {
		return orgid;
	}
	public void setOrgid(long orgid) {
		this.orgid = orgid;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getVolunorgname() {
		return volunorgname;
	}
	public void setVolunorgname(String volunorgname) {
		this.volunorgname = volunorgname;
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
	public String getRegip() {
		return regip;
	}
	public void setRegip(String regip) {
		this.regip = regip;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getLastloginip() {
		return lastloginip;
	}
	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
	}
	public double getTotalpoints() {
		return totalpoints;
	}
	public void setTotalpoints(double totalpoints) {
		this.totalpoints = totalpoints;
	}
	public String getCommunityname() {
		return communityname;
	}
	public void setCommunityname(String communityname) {
		this.communityname = communityname;
	}
	public Date getDeletetime() {
		return deletetime;
	}
	public void setDeletetime(Date deletetime) {
		this.deletetime = deletetime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getJn() {
		return jn;
	}
	public void setJn(String jn) {
		this.jn = jn;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getOutresion() {
		return outresion;
	}
	public void setOutresion(String outresion) {
		this.outresion = outresion;
	}
	
	
	
	
	
	
}
