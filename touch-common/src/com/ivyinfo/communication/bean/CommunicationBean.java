package com.ivyinfo.communication.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class CommunicationBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684250L;
	
	/**
	 * 通讯录   t_communication
	 */
	private String id;						//id
	private String name;					//姓名
	private String namepy;					//姓名拼音
	private String alias;					//别名
	private String sex;						//性别    0：保密  1：男  2：女
	private String birthday;				//生日
	private String email;					//常用邮箱
	private String companyname;				//公司名称
	private String companyaddress;			//公司地址
	private String companypostcode;			//公司邮编
	private String familyaddress;			//家庭地址
	private String familypostcode;			//家庭邮编
	private String phone;					//常用号码
	private String remark;					//备注
	private String imgroupid;				//通讯录组id
	private String isimtype;				//是否im类别   是：true  否：false
	private String ismailtype;				//是否mail类别   是：true  否：false
	private String iswebcalltype;			//是否webcall类别   是：true  否：false
	private String timestemp;				//修改时间
	
	/**
	 * 通讯录组   t_communication_im_group
	 */
	private String imgid;					//组id
	private String imgroupname;				//组名
	private String imsuperiorgroupid;		//上级组id
	private String imremark;				//备注
	private String imtimestemp;				//修改时间
	
	private String logname;
	
	public String getLogname() {
		logname = (logname == null || "null".equals(logname))?"":logname;
		return logname;
	}
	public void setLogname(String logname) {
		this.logname = logname;
	}
	public String getImgroupid() {
		imgroupid = (imgroupid == null || "null".equals(imgroupid))?"":imgroupid;
		return imgroupid;
	}
	public void setImgroupid(String imgroupid) {
		this.imgroupid = imgroupid;
	}
	public String getNamepy() {
		namepy = (namepy == null || "null".equals(namepy))?"":namepy;
		return namepy;
	}
	public void setNamepy(String namepy) {
		this.namepy = namepy;
	}
	public String getId() {
		id = (id == null || "null".equals(id))?"":id;
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		name = (name == null || "null".equals(name))?"":name;
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		alias = (alias == null || "null".equals(alias))?"":alias;
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getSex() {
		sex = (sex == null || "null".equals(sex))?"":sex;
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		birthday = (birthday == null || "null".equals(birthday))?"":birthday;
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		email = (email == null || "null".equals(email))?"":email;
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompanyname() {
		companyname = (companyname == null || "null".equals(companyname))?"":companyname;
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getCompanyaddress() {
		companyaddress = (companyaddress == null || "null".equals(companyaddress))?"":companyaddress;
		return companyaddress;
	}
	public void setCompanyaddress(String companyaddress) {
		this.companyaddress = companyaddress;
	}
	public String getCompanypostcode() {
		companypostcode = (companypostcode == null || "null".equals(companypostcode))?"":companypostcode;
		return companypostcode;
	}
	public void setCompanypostcode(String companypostcode) {
		this.companypostcode = companypostcode;
	}
	public String getFamilyaddress() {
		familyaddress = (familyaddress == null || "null".equals(familyaddress))?"":familyaddress;
		return familyaddress;
	}
	public void setFamilyaddress(String familyaddress) {
		this.familyaddress = familyaddress;
	}
	public String getFamilypostcode() {
		familypostcode = (familypostcode == null || "null".equals(familypostcode))?"":familypostcode;
		return familypostcode;
	}
	public void setFamilypostcode(String familypostcode) {
		this.familypostcode = familypostcode;
	}
	public String getRemark() {
		remark = (remark == null || "null".equals(remark))?"":remark;
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTimestemp() {
		timestemp = (timestemp == null || "null".equals(timestemp))?"":timestemp;
		return timestemp;
	}
	public void setTimestemp(String timestemp) {
		this.timestemp = timestemp;
	}
	public String getImremark() {
		imremark = (imremark == null || "null".equals(imremark))?"":imremark;
		return imremark;
	}
	public void setImremark(String imremark) {
		this.imremark = imremark;
	}
	public String getImtimestemp() {
		imtimestemp = (imtimestemp == null || "null".equals(imtimestemp))?"":imtimestemp;
		return imtimestemp;
	}
	public void setImtimestemp(String imtimestemp) {
		this.imtimestemp = imtimestemp;
	}
	public String getPhone() {
		phone = (phone == null || "null".equals(phone))?"":phone;
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIsimtype() {
		isimtype = (isimtype == null || "null".equals(isimtype))?"":isimtype;
		return isimtype;
	}
	public void setIsimtype(String isimtype) {
		this.isimtype = isimtype;
	}
	public String getIsmailtype() {
		ismailtype = (ismailtype == null || "null".equals(ismailtype))?"":ismailtype;
		return ismailtype;
	}
	public void setIsmailtype(String ismailtype) {
		this.ismailtype = ismailtype;
	}
	public String getIswebcalltype() {
		iswebcalltype = (iswebcalltype == null || "null".equals(iswebcalltype))?"":iswebcalltype;
		return iswebcalltype;
	}
	public void setIswebcalltype(String iswebcalltype) {
		this.iswebcalltype = iswebcalltype;
	}
	public String getImgid() {
		imgid = (imgid == null || "null".equals(imgid))?"":imgid;
		return imgid;
	}
	public void setImgid(String imgid) {
		this.imgid = imgid;
	}
	public String getImgroupname() {
		imgroupname = (imgroupname == null || "null".equals(imgroupname))?"":imgroupname;
		return imgroupname;
	}
	public void setImgroupname(String imgroupname) {
		this.imgroupname = imgroupname;
	}
	public String getImsuperiorgroupid() {
		imsuperiorgroupid = (imsuperiorgroupid == null || "null".equals(imsuperiorgroupid))?"":imsuperiorgroupid;
		return imsuperiorgroupid;
	}
	public void setImsuperiorgroupid(String imsuperiorgroupid) {
		this.imsuperiorgroupid = imsuperiorgroupid;
	}
}
