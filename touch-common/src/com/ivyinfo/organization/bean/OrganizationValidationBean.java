package com.ivyinfo.organization.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class OrganizationValidationBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684285L;
	
	private String id;					//id
	private String name;				//机构名
	private String email;				//电子邮箱
	private String officephone;			//办公电话
	private String faxnumber;			//传真号码
	private String nationality;			//国籍
	private String province;			//省份
	private String city;				//城市
	private String detailedaddress;		//详细地址
	private String postcode;			//邮编
	private String registertime;		//注册时间
	private String validationcode;		//验证码
	private String validationtime;		//验证发送时间
	private String logname;				//登录名
	private String logpassword;			//登陆密码
	
	public String getLogname() {
		return logname;
	}
	public void setLogname(String logname) {
		this.logname = logname;
	}
	public String getLogpassword() {
		return logpassword;
	}
	public void setLogpassword(String logpassword) {
		this.logpassword = logpassword;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOfficephone() {
		return officephone;
	}
	public void setOfficephone(String officephone) {
		this.officephone = officephone;
	}
	public String getFaxnumber() {
		return faxnumber;
	}
	public void setFaxnumber(String faxnumber) {
		this.faxnumber = faxnumber;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDetailedaddress() {
		return detailedaddress;
	}
	public void setDetailedaddress(String detailedaddress) {
		this.detailedaddress = detailedaddress;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getRegistertime() {
		return registertime;
	}
	public void setRegistertime(String registertime) {
		this.registertime = registertime;
	}
	public String getValidationcode() {
		return validationcode;
	}
	public void setValidationcode(String validationcode) {
		this.validationcode = validationcode;
	}
	public String getValidationtime() {
		return validationtime;
	}
	public void setValidationtime(String validationtime) {
		this.validationtime = validationtime;
	}
}
