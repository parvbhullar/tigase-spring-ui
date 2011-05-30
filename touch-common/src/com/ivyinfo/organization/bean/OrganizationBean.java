package com.ivyinfo.organization.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class OrganizationBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684243L;
	
	/**
	 * 机构信息表  t_sys_organization
	 */
	private String id;						//id
	private String name;					//机构名
	private String remark;					//备注
	private String upduserid;				//修改用户id
	private String timestemp;				//修改时间
	private String dirtyflag;				//脏标记
	private String meetorgid;				//会议系统组织id
	private String czze;					//冲值总额
	
	/**
	 * 机构联系信息表  t_sys_organization_contact
	 */
	private String contactid;				//id
	private String organizationid;			//机构id
	private String email;					//电子邮箱
	private String officephone;				//办公电话
	private String faxnumber;				//传真号码
	private String nationality;				//国籍
	private String province;				//省份
	private String city;					//城市
	private String detailedaddress;			//详细地址
	private String postcode;				//邮编
	private String contactremark;			//备注
	
	public String getCzze() {
		return czze;
	}
	public void setCzze(String czze) {
		this.czze = czze;
	}
	public String getMeetorgid() {
		return meetorgid;
	}
	public void setMeetorgid(String meetorgid) {
		this.meetorgid = meetorgid;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUpduserid() {
		return upduserid;
	}
	public void setUpduserid(String upduserid) {
		this.upduserid = upduserid;
	}
	public String getTimestemp() {
		return timestemp;
	}
	public void setTimestemp(String timestemp) {
		this.timestemp = timestemp;
	}
	public String getDirtyflag() {
		return dirtyflag;
	}
	public void setDirtyflag(String dirtyflag) {
		this.dirtyflag = dirtyflag;
	}
	public String getContactid() {
		return contactid;
	}
	public void setContactid(String contactid) {
		this.contactid = contactid;
	}
	public String getOrganizationid() {
		return organizationid;
	}
	public void setOrganizationid(String organizationid) {
		this.organizationid = organizationid;
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
	public String getContactremark() {
		return contactremark;
	}
	public void setContactremark(String contactremark) {
		this.contactremark = contactremark;
	}
}
