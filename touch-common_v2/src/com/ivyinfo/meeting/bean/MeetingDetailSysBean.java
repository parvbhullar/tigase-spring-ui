package com.ivyinfo.meeting.bean;

import java.util.List;

import com.ivyinfo.framework.service.base.BaseBean;

public class MeetingDetailSysBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684271L;
	
	private String code;					//组织编号  根节点是：0001  下级节点：00010001  00010002
	private String name;					//组织名称
	private String linkman;					//联系人
	private String principal;				//负责人
	private String phone;					//电话
	private String email;					//邮箱
	private String parentOrgId;				//上级组织id
	private String address;					//地址
	private String postcode;				//邮编
	private String description;				//描述
	
	private String orgId;					//组织id
	
	private boolean parameterLists;
	private boolean parameters;
	private List parameterList;
	private boolean usersign;
	private boolean orgrolesign;
	
	private String userName;	//登陆名
	private String nickname;	//昵称
	private String password;	//密码
	private String firstName;	//名
	private String lastName;	//姓   、、空
	private Integer gender;			//性别 男：1 女：0 保密：2
	private String company;		//公司
	private String deptId;		//部门    、、固定值
	private List<String> groupIds;		//组（可多个）  、、空
	private List<String> roleIds;		//角色（可多个）   、、空
	private String duty;		//职务
	private Integer reportTo;		//汇报对象
	private Integer userType;		//用户类型（企业内部用户：1、代理商用户：2、供应商用户：3、客户用户：4）
	private String extension;		//分机号
	private Boolean eimUsed;	//是否应用于Eim   、、固定值
	private Boolean easycallUsed;	//是否应用于easycall   、、固定值
//	private String email;		//电子邮件
	private String otherEmail;	//其他电子邮件
	private String officePhone;	//办公电话
	private String otherPhone;	//其他电话
	private String cellphone;	//手机
	private String fax;			//传真
	private String country;		//国家
	private String province;	//省
	private String city;		//城市
//	private String address;		//地址
//	private String postcode;	//邮编
	private String otherInfo;	//其他信息
	private Boolean enabled;	//激活状态     、、固定值
	private Boolean forceCreate;	//是否强制创建，true：如果同名被删除，则还原帐户；false：不还原     、、固定值
	
	private String userid;
	
	private String id;
	private Integer site_id;
	private String parentorg;
	private String parentorgName;
	private String orgCode;
	private String orgName;
	private String telephone;
	private Integer dutyuser;
	private Integer linkuser;
	private String creator;
	private String createTime;
	private String modifier;
	private String modifyTime;
	private Integer serverInfoId;
	private String serverName;
	private String serverNickname;
	private boolean checked;
	private Integer orgSequence;
	private String roles;
	
	public boolean isOrgrolesign() {
		return orgrolesign;
	}
	public void setOrgrolesign(boolean orgrolesign) {
		this.orgrolesign = orgrolesign;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getSite_id() {
		return site_id;
	}
	public void setSite_id(Integer siteId) {
		site_id = siteId;
	}
	public String getParentorg() {
		return parentorg;
	}
	public void setParentorg(String parentorg) {
		this.parentorg = parentorg;
	}
	public String getParentorgName() {
		return parentorgName;
	}
	public void setParentorgName(String parentorgName) {
		this.parentorgName = parentorgName;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Integer getDutyuser() {
		return dutyuser;
	}
	public void setDutyuser(Integer dutyuser) {
		this.dutyuser = dutyuser;
	}
	public Integer getLinkuser() {
		return linkuser;
	}
	public void setLinkuser(Integer linkuser) {
		this.linkuser = linkuser;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Integer getServerInfoId() {
		return serverInfoId;
	}
	public void setServerInfoId(Integer serverInfoId) {
		this.serverInfoId = serverInfoId;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getServerNickname() {
		return serverNickname;
	}
	public void setServerNickname(String serverNickname) {
		this.serverNickname = serverNickname;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Integer getOrgSequence() {
		return orgSequence;
	}
	public void setOrgSequence(Integer orgSequence) {
		this.orgSequence = orgSequence;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public boolean isUsersign() {
		return usersign;
	}
	public void setUsersign(boolean usersign) {
		this.usersign = usersign;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public List<String> getGroupIds() {
		return groupIds;
	}
	public void setGroupIds(List<String> groupIds) {
		this.groupIds = groupIds;
	}
	public List<String> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(List<String> roleIds) {
		this.roleIds = roleIds;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public Integer getReportTo() {
		return reportTo;
	}
	public void setReportTo(Integer reportTo) {
		this.reportTo = reportTo;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public Boolean getEimUsed() {
		return eimUsed;
	}
	public void setEimUsed(Boolean eimUsed) {
		this.eimUsed = eimUsed;
	}
	public Boolean getEasycallUsed() {
		return easycallUsed;
	}
	public void setEasycallUsed(Boolean easycallUsed) {
		this.easycallUsed = easycallUsed;
	}
	public String getOtherEmail() {
		return otherEmail;
	}
	public void setOtherEmail(String otherEmail) {
		this.otherEmail = otherEmail;
	}
	public String getOfficePhone() {
		return officePhone;
	}
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	public String getOtherPhone() {
		return otherPhone;
	}
	public void setOtherPhone(String otherPhone) {
		this.otherPhone = otherPhone;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
	public String getOtherInfo() {
		return otherInfo;
	}
	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Boolean getForceCreate() {
		return forceCreate;
	}
	public void setForceCreate(Boolean forceCreate) {
		this.forceCreate = forceCreate;
	}
	public boolean isParameters() {
		return parameters;
	}
	public void setParameters(boolean parameters) {
		this.parameters = parameters;
	}
	private String levelFlag;
	
	public String getLevelFlag() {
		return levelFlag;
	}
	public void setLevelFlag(String levelFlag) {
		this.levelFlag = levelFlag;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public boolean isParameterLists() {
		return parameterLists;
	}
	public void setParameterLists(boolean parameterLists) {
		this.parameterLists = parameterLists;
	}
	public List getParameterList() {
		return parameterList;
	}
	public void setParameterList(List parameterList) {
		this.parameterList = parameterList;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getParentOrgId() {
		return parentOrgId;
	}
	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
