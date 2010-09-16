package com.linkage.app.gqt.front.reg.entitys;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.*;




public class Members implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Members";
	public static final String ALIAS_UUID = "uuid";
	public static final String ALIAS_USERNAME = "username";
	public static final String ALIAS_NAME = "name";
	public static final String ALIAS_NICKNAME = "nickname";
	public static final String ALIAS_PASSWORD = "password";
	public static final String ALIAS_CELLPHONE = "cellphone";
	public static final String ALIAS_EMAIL = "email";
	public static final String ALIAS_SEX = "sex";
	public static final String ALIAS_BIRTHDAY = "birthday";
	public static final String ALIAS_EDULEVEL = "edulevel";
	public static final String ALIAS_PROFESSION = "profession";
	public static final String ALIAS_HOBBY = "hobby";
	public static final String ALIAS_LOCATION = "location";
	public static final String ALIAS_INTENTION = "intention";
	public static final String ALIAS_SERVETIMES = "servetimes";
	public static final String ALIAS_VOLUNORGID = "volunorgid";
	public static final String ALIAS_CREDCODE = "credcode";
	public static final String ALIAS_CREDTYPE = "credtype";
	public static final String ALIAS_ISVALID = "isvalid";
	public static final String ALIAS_ISVERIFY = "isverify";
	public static final String ALIAS_VERIFYDESC = "verifydesc";
	public static final String ALIAS_CREATETIME = "createtime";
	public static final String ALIAS_REGIP = "regip";
	public static final String ALIAS_REGDATE = "regdate";
	public static final String ALIAS_LASTLOGINIP = "lastloginip";
	public static final String ALIAS_TOTALPOINTS = "totalpoints";
	public static final String ALIAS_LASTLOGNUMBERTIME = "lastlognumbertime";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Long uuid;
	private java.lang.String username;
	private java.lang.String name;
	private java.lang.String nickname;
	private java.lang.String password;
	private java.lang.String cellphone;
	private java.lang.String email;
	
	private java.lang.Boolean sex;
	
	private java.util.Date birthday;
	private java.lang.String edulevel;
	private java.lang.String profession;
	private java.lang.String hobby;
	private java.lang.String location;
	private java.lang.String intention;
	private java.lang.String servetimes;
	
	private java.lang.Long volunorgid;
	private java.lang.String credcode;
	private java.lang.String credtype;
	
	private java.lang.Boolean isvalid;
	
	private java.lang.Integer isverify;
	private java.lang.String verifydesc;
	
	private java.util.Date createtime;
	private java.lang.String regip;
	
	private java.util.Date regdate;
	private java.lang.String lastloginip;
	
	private Long totalpoints;
	
	private java.util.Date lastlognumbertime;
	//columns END

	public Members(){
	}

	public Members(
		java.lang.Long uuid
	){
		this.uuid = uuid;
	}

	public void setUuid(java.lang.Long value) {
		this.uuid = value;
	}
	
	public java.lang.Long getUuid() {
		return this.uuid;
	}
	public void setUsername(java.lang.String value) {
		this.username = value;
	}
	
	public java.lang.String getUsername() {
		return this.username;
	}
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	public void setNickname(java.lang.String value) {
		this.nickname = value;
	}
	
	public java.lang.String getNickname() {
		return this.nickname;
	}
	public void setPassword(java.lang.String value) {
		this.password = value;
	}
	
	public java.lang.String getPassword() {
		return this.password;
	}
	public void setCellphone(java.lang.String value) {
		this.cellphone = value;
	}
	
	public java.lang.String getCellphone() {
		return this.cellphone;
	}
	public void setEmail(java.lang.String value) {
		this.email = value;
	}
	
	public java.lang.String getEmail() {
		return this.email;
	}
	public void setSex(java.lang.Boolean value) {
		this.sex = value;
	}
	
	public java.lang.Boolean getSex() {
		return this.sex;
	}
//	public String getBirthdayString() {
//		return DateConvertUtils.format(getBirthday(), FORMAT_BIRTHDAY);
//	}
//	public void setBirthdayString(String value) {
//		setBirthday(DateConvertUtils.parse(value, FORMAT_BIRTHDAY,java.util.Date.class));
//	}
	
	public void setBirthday(java.util.Date value) {
		this.birthday = value;
	}
	
	public java.util.Date getBirthday() {
		return this.birthday;
	}
	public void setEdulevel(java.lang.String value) {
		this.edulevel = value;
	}
	
	public java.lang.String getEdulevel() {
		return this.edulevel;
	}
	public void setProfession(java.lang.String value) {
		this.profession = value;
	}
	
	public java.lang.String getProfession() {
		return this.profession;
	}
	public void setHobby(java.lang.String value) {
		this.hobby = value;
	}
	
	public java.lang.String getHobby() {
		return this.hobby;
	}
	public void setLocation(java.lang.String value) {
		this.location = value;
	}
	
	public java.lang.String getLocation() {
		return this.location;
	}
	public void setIntention(java.lang.String value) {
		this.intention = value;
	}
	
	public java.lang.String getIntention() {
		return this.intention;
	}
	public void setServetimes(java.lang.String value) {
		this.servetimes = value;
	}
	
	public java.lang.String getServetimes() {
		return this.servetimes;
	}
	public void setVolunorgid(java.lang.Long value) {
		this.volunorgid = value;
	}
	
	public java.lang.Long getVolunorgid() {
		return this.volunorgid;
	}
	public void setCredcode(java.lang.String value) {
		this.credcode = value;
	}
	
	public java.lang.String getCredcode() {
		return this.credcode;
	}
	public void setCredtype(java.lang.String value) {
		this.credtype = value;
	}
	
	public java.lang.String getCredtype() {
		return this.credtype;
	}
	public void setIsvalid(java.lang.Boolean value) {
		this.isvalid = value;
	}
	
	public java.lang.Boolean getIsvalid() {
		return this.isvalid;
	}
	
	public java.lang.Integer getIsverify() {
		return isverify;
	}

	public void setIsverify(java.lang.Integer isverify) {
		this.isverify = isverify;
	}

	public void setVerifydesc(java.lang.String value) {
		this.verifydesc = value;
	}
	
	public java.lang.String getVerifydesc() {
		return this.verifydesc;
	}
//	public String getCreatetimeString() {
//		return DateConvertUtils.format(getCreatetime(), FORMAT_CREATETIME);
//	}
//	public void setCreatetimeString(String value) {
//		setCreatetime(DateConvertUtils.parse(value, FORMAT_CREATETIME,java.util.Date.class));
//	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	public void setRegip(java.lang.String value) {
		this.regip = value;
	}
	
	public java.lang.String getRegip() {
		return this.regip;
	}
//	public String getRegdateString() {
//		return DateConvertUtils.format(getRegdate(), FORMAT_REGDATE);
//	}
//	public void setRegdateString(String value) {
//		setRegdate(DateConvertUtils.parse(value, FORMAT_REGDATE,java.util.Date.class));
//	}
	
	public void setRegdate(java.util.Date value) {
		this.regdate = value;
	}
	
	public java.util.Date getRegdate() {
		return this.regdate;
	}
	public void setLastloginip(java.lang.String value) {
		this.lastloginip = value;
	}
	
	public java.lang.String getLastloginip() {
		return this.lastloginip;
	}
	public void setTotalpoints(Long value) {
		this.totalpoints = value;
	}
	
	public Long getTotalpoints() {
		return this.totalpoints;
	}
//	public String getLastlognumbertimeString() {
//		return DateConvertUtils.format(getLastlognumbertime(), FORMAT_LASTLOGNUMBERTIME);
//	}
//	public void setLastlognumbertimeString(String value) {
//		setLastlognumbertime(DateConvertUtils.parse(value, FORMAT_LASTLOGNUMBERTIME,java.util.Date.class));
//	}
	
	public void setLastlognumbertime(java.util.Date value) {
		this.lastlognumbertime = value;
	}
	
	public java.util.Date getLastlognumbertime() {
		return this.lastlognumbertime;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Uuid",getUuid())
			.append("Username",getUsername())
			.append("Name",getName())
			.append("Nickname",getNickname())
			.append("Password",getPassword())
			.append("Cellphone",getCellphone())
			.append("Email",getEmail())
			.append("Sex",getSex())
			.append("Birthday",getBirthday())
			.append("Edulevel",getEdulevel())
			.append("Profession",getProfession())
			.append("Hobby",getHobby())
			.append("Location",getLocation())
			.append("Intention",getIntention())
			.append("Servetimes",getServetimes())
			.append("Volunorgid",getVolunorgid())
			.append("Credcode",getCredcode())
			.append("Credtype",getCredtype())
			.append("Isvalid",getIsvalid())
			.append("Isverify",getIsverify())
			.append("Verifydesc",getVerifydesc())
			.append("Createtime",getCreatetime())
			.append("Regip",getRegip())
			.append("Regdate",getRegdate())
			.append("Lastloginip",getLastloginip())
			.append("Totalpoints",getTotalpoints())
			.append("Lastlognumbertime",getLastlognumbertime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUuid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Members == false) return false;
		if(this == obj) return true;
		Members other = (Members)obj;
		return new EqualsBuilder()
			.append(getUuid(),other.getUuid())
			.isEquals();
	}
}

