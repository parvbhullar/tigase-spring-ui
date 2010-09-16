package com.linkage.app.gqt.backstage.active.entity;

import java.io.Serializable;
/**
 * 集体活动工单表
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class IssueWorkSheet implements Serializable{
	/** 工单ID */
	private Long id;
	
	/** 活动ID */
	private Long actid;
	
	/** 组织ID */
	private Long orgid;
	
	/** 组织名称 */
	private String orgname;
	
	/** 志愿者ID */
	private Long volunteerid;
	
	/** 志愿者姓名 */
	private String volunteername;
	
	/** 证件类型 */
	private String credtype;
	
	/** 证件号码 */
	private String credcode;
	
	/** 手机 */
	private String dn;
	
	/** 服务意向 */
	private String intention;
	
	/** 社区ID */
	private Long communityid;
	
	/** 社区名称 */
	private String communityname;
	
	/** 工单状态 */
	private String status;
	
	/** 创建时间 */
	private String createtime;
	
	/** 更新时间 */
	private String updatetime;

	/**
	 * 获取工单ID
	 * @return id 工单ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置工单ID
	 * @param id 工单ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取组织ID
	 * @return orgid 组织ID
	 */
	public Long getOrgid() {
		return orgid;
	}
	/**
	 * 设置组织ID
	 * @param orgid 组织ID
	 */
	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}
	/**
	 * 获取组织名称
	 * @return orgname 组织名称
	 */
	public String getOrgname() {
		return orgname;
	}
	/**
	 * 设置组织名称
	 * @param orgName 组织名称
	 */
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	/**
	 * 获取志愿者ID
	 * @return volunteerid 志愿者ID
	 */
	public Long getVolunteerid() {
		return volunteerid;
	}
	/**
	 * 设置志愿者ID
	 * @param volunteerid 志愿者ID
	 */
	public void setVolunteerid(Long volunteerid) {
		this.volunteerid = volunteerid;
	}
	/**
	 * 获取志愿者姓名
	 * @return volunteername 志愿者姓名
	 */
	public String getVolunteername() {
		return volunteername;
	}
	/**
	 * 设置志愿者姓名
	 * @param volunteername 志愿者姓名
	 */
	public void setVolunteername(String volunteername) {
		this.volunteername = volunteername;
	}
	/**
	 * 获取服务意向
	 * @return intention 服务意向
	 */
	public String getIntention() {
		return intention;
	}
	/**
	 * 设置服务意向
	 * @param intention 服务意向
	 */
	public void setIntention(String intention) {
		this.intention = intention;
	}
	/**
	 * 获取创建时间
	 * @return createtime 创建时间
	 */
	public String getCreatetime() {
		return createtime;
	}
	/**
	 * 设置创建时间
	 * @param createtime 创建时间
	 */
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取更新时间
	 * @return updatetime 更新时间
	 */
	public String getUpdatetime() {
		return updatetime;
	}
	/**
	 * 设置更新时间
	 * @param updatetime 更新时间
	 */
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	/**
	 * 获取活动Id
	 * @return actid 活动Id
	 */
	public Long getActid() {
		return actid;
	}
	/**
	 * 设置活动Id
	 * @param actid 活动Id
	 */
	public void setActid(Long actid) {
		this.actid = actid;
	}
	/**
	 * 获取工单状态
	 * @return status 工单状态
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置工单状态
	 * @param status 工单状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取证件类型
	 * @return credtype 证件类型
	 */
	public String getCredtype() {
		return credtype;
	}
	/**
	 * 设置证件类型
	 * @param credtype 证件类型
	 */
	public void setCredtype(String credtype) {
		this.credtype = credtype;
	}
	/**
	 * 获取证件号码
	 * @return credcode 证件号码
	 */
	public String getCredcode() {
		return credcode;
	}
	/**
	 * 获取手机
	 * @return dn 手机
	 */
	public String getDn() {
		return dn;
	}
	/**
	 * 设置手机
	 * @param dn 手机
	 */
	public void setDn(String dn) {
		this.dn = dn;
	}
	/**
	 * 获取社区ID
	 * @return communityid 社区ID
	 */
	public Long getCommunityid() {
		return communityid;
	}
	/**
	 * 设置社区ID
	 * @param communityid 社区ID
	 */
	public void setCommunityid(Long communityid) {
		this.communityid = communityid;
	}
	/**
	 * 获取社区名称
	 * @return communityname 社区名称
	 */
	public String getCommunityname() {
		return communityname;
	}
	/**
	 * 设置社区名称
	 * @param communityname 社区名称
	 */
	public void setCommunityname(String communityname) {
		this.communityname = communityname;
	}
	/**
	 * 设置证件号码
	 * @param credcode 证件号码
	 */
	public void setCredcode(String credcode) {
		this.credcode = credcode;
	}
}
