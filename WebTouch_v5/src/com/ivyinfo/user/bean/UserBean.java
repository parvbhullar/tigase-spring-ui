package com.ivyinfo.user.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class UserBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684248L;
	
	/**
	 * 用户登陆信息表  t_sys_user_login
	 */
	private String id;						//id
	private String logname;					//登陆名
	private String nickname;				//昵称
	private String password;				//密码
	private String type;					//用户类型    0：系统管理员    1：机构管理员     2：普通用户
	private String state;					//状态    0：未审批   1：审批未通过    2：在职    3：离职   4：已验证未审批   5：已验证审批未通过   6：未验证邮箱
	private String upduserid;				//修改用户id
	private String timestemp;				//修改时间
	private String dirtyflag;				//脏标记
	private String meetuserid;				//会议系统用户id
	
	/**
	 * 用户基本信息表  t_sys_user_basic
	 */
	private String basicid;					//id
	private String userid;					//用户id
	private String name;					//姓名
	private String sex;						//性别  0：保密  1：男  2：女
	private String usersort;				//用户类别  1：企业内部用户  2：代理商用户  3：供应商用户  4：客户用户
	private String organizationid;			//机构id
	private String deptid;					//部门id
	private String roleid;					//角色id
	private String job;						//职务
	
	/**
	 * 用户联系信息表  t_sys_user_contact
	 */
	private String contactid;				//id
	private String email;					//电子邮箱
	private String otheremail;				//其他邮箱
	private String officephone;				//办公电话
	private String otherphone;				//其他电话
	private String mobilephone;				//手机号码
	private String faxnumber;				//传真号码
	private String nationality;				//用户国籍
	private String province;				//省份
	private String city;					//城市
	private String detailedaddress;			//详细地址
	private String postcode;				//邮编
	private String othermessage;			//其他信息
	
	/**
	 * 用户照片信息表  t_sys_user_photo
	 */
	private String photoid;					//id
	private String photopath;				//照片路径
	private String photoname;				//照片名称
	
	/**
	 * 用户webcall冲值信息表  t_sys_user_chongzhi
	 */
	private String cardid;					//id
	private String cardnumber;				//卡号
	private String cardpassword;			//密码
	private String cardtimestemp;			//修改时间
	private String czje;					//冲值金额
	
	public String getCzje() {
		return czje;
	}
	public void setCzje(String czje) {
		this.czje = czje;
	}
	public String getMeetuserid() {
		return meetuserid;
	}
	public void setMeetuserid(String meetuserid) {
		this.meetuserid = meetuserid;
	}
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getCardnumber() {
		return cardnumber;
	}
	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}
	public String getCardpassword() {
		return cardpassword;
	}
	public void setCardpassword(String cardpassword) {
		this.cardpassword = cardpassword;
	}
	public String getCardtimestemp() {
		return cardtimestemp;
	}
	public void setCardtimestemp(String cardtimestemp) {
		this.cardtimestemp = cardtimestemp;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLogname() {
		return logname;
	}
	public void setLogname(String logname) {
		this.logname = logname;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getBasicid() {
		return basicid;
	}
	public void setBasicid(String basicid) {
		this.basicid = basicid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUsersort() {
		return usersort;
	}
	public void setUsersort(String usersort) {
		this.usersort = usersort;
	}
	public String getOrganizationid() {
		return organizationid;
	}
	public void setOrganizationid(String organizationid) {
		this.organizationid = organizationid;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getContactid() {
		return contactid;
	}
	public void setContactid(String contactid) {
		this.contactid = contactid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOtheremail() {
		return otheremail;
	}
	public void setOtheremail(String otheremail) {
		this.otheremail = otheremail;
	}
	public String getOfficephone() {
		return officephone;
	}
	public void setOfficephone(String officephone) {
		this.officephone = officephone;
	}
	public String getOtherphone() {
		return otherphone;
	}
	public void setOtherphone(String otherphone) {
		this.otherphone = otherphone;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
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
	public String getOthermessage() {
		return othermessage;
	}
	public void setOthermessage(String othermessage) {
		this.othermessage = othermessage;
	}
	public String getPhotoid() {
		return photoid;
	}
	public void setPhotoid(String photoid) {
		this.photoid = photoid;
	}
	public String getPhotopath() {
		return photopath;
	}
	public void setPhotopath(String photopath) {
		this.photopath = photopath;
	}
	public String getPhotoname() {
		return photoname;
	}
	public void setPhotoname(String photoname) {
		this.photoname = photoname;
	}
}
