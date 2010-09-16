package com.linkage.app.gqt.backstage.active.entity;

import java.io.Serializable;
/**
 * 活动表
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class Active implements Serializable{
	
	/** 活动ID */
	private Long actid;
	/** 活动标题 */
	private String acttitle;
	/** 活动介绍 */
	private String actdesc;
	/** 活动性质 */
	private Long acttype;
	/** 开始日期 */
	private String startdate;
	/** 结束日期 */
	private String enddate;
	/** 时间储蓄 */
	private int acthours;
	/** 组织Id */
	private Long orgid;
	/** 组织名称 */
	private String orgname;
	/** 父组织Id */
	private Long parentorgid;
	/** 机构层级 */
	private int orglevel;
	/** 预设积分 */
	private Double defaultpoint;
	/** 活动地点 */
	private String actaddr;
	/** 活动人数 */
	private int actnum;
	/** 活动状态 */
	private Long actstatus;
	/** 服务意向 */
	private String intention;	
	/** 推送状态状态 */
	private String tsstatus;
	/** 发布时间 */
	private String sendtime;
	/** 活动摘要 */
	private String summary;
	/** 上传附件 */
	private String path;
	/** 活动系数 */
	private int ratio;
	/** 修改标志 */
	private String editFlag;
	/**工单状态*/
	private String status;
	/**用户是否加入此活动*/
	private boolean join;
	/** 活动数目 */
	private int activenum;
	/** 参加活动人数 */
	private int attendnum;

	/**
	 * 获取活动ID
	 * @return actid 活动ID
	 */
	public Long getActid() {
		return this.actid;
	}
	/**
	 * 设置活动ID
	 * @param actid 活动ID
	 */
	public void setActid(Long actid) {
		this.actid = actid;
	}
	/**
	 * 获取活动标题
	 * @return acttitle 活动标题
	 */
	public String getActtitle() {
		return this.acttitle;
	}
	/**
	 * 设置活动标题
	 * @param acttitle 活动标题
	 */
	public void setActtitle(String acttitle) {
		this.acttitle = acttitle;
	}
	/**
	 * 获取活动介绍
	 * @return actdesc 活动介绍
	 */
	public String getActdesc() {
		return this.actdesc;
	}
	/**
	 * 设置活动介绍
	 * @param actdesc 活动介绍
	 */
	public void setActdesc(String actdesc) {
		this.actdesc = actdesc;
	}
	/**
	 * 获取活动性质
	 * @return acttype 活动性质
	 */
	public Long getActtype() {
		return this.acttype;
	}
	/**
	 * 设置活动性质
	 * @param acttype 活动性质
	 */
	public void setActtype(Long acttype) {
		this.acttype = acttype;
	}
	/**
	 * 获取开始日期
	 * @return startdate 开始日期
	 */
	public String getStartdate() {
		return this.startdate;
	}
	/**
	 * 设置开始日期
	 * @param startdate 开始日期
	 */
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	/**
	 * 获取结束日期
	 * @return enddate 结束日期
	 */
	public String getEnddate() {
		return this.enddate;
	}
	/**
	 * 设置结束日期
	 * @param enddate 结束日期
	 */
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	/**
	 * 获取预设积分
	 * @return defaultpoint 预设积分
	 */
	public Double getDefaultpoint() {
		return this.defaultpoint;
	}
	/**
	 * 设置预设积分
	 * @param defaultpoint 预设积分
	 */
	public void setDefaultpoint(Double defaultpoint) {
		this.defaultpoint = defaultpoint;
	}
	/**
	 * 获取活动地点
	 * @return actaddr 活动地点
	 */
	public String getActaddr() {
		return this.actaddr;
	}
	/**
	 * 设置活动地点
	 * @param actaddr 活动地点
	 */
	public void setActaddr(String actaddr) {
		this.actaddr = actaddr;
	}
	/**
	 * 获取活动状态
	 * @return actstatus 活动状态
	 */ 
	public Long getActstatus() {
		return this.actstatus;
	}
	/**
	 * 设置活动状态
	 * @param actstatus 活动状态
	 */
	public void setActstatus(Long actstatus) {
		this.actstatus = actstatus;
	}
	/**
	 * 获取发布时间
	 * @return sendtime 发布时间
	 */
	public String getSendtime() {
		return this.sendtime;
	}
	/**
	 * 设置发布时间
	 * @param sendtime 发布时间
	 */
	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}
	/**
	 * 获取活动摘要
	 * @return summary 活动摘要
	 */
	public String getSummary() {
		return this.summary;
	}
	/**
	 * 设置活动摘要
	 * @param summary 活动摘要
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	/**
	 * 获取附件
	 * @return path 附件
	 */
	public String getPath() {
		return this.path;
	}
	/**
	 * 设置附件
	 * @param path 附件
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * 获取修改标志
	 * @return editFlag 修改标志
	 */
	public String getEditFlag() {
		return editFlag;
	}

	/**
	 * 设置修改标志
	 * @param editFlag 修改标志
	 */
	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}
	/**
	 * 获取活动人数
	 * @return actnum 活动人数
	 */
	public int getActnum() {
		return actnum;
	}
	/**
	 * 设置活动人数
	 * @param actnum 活动人数
	 */
	public void setActnum(int actnum) {
		this.actnum = actnum;
	}
	/**
	 * 获取推送状态
	 * @return tsstatus 推送状态
	 */
	public String getTsstatus() {
		return tsstatus;
	}
	/**
	 * 设置推送状态
	 * @param tsstatus 推送状态
	 */
	public void setTsstatus(String tsstatus) {
		this.tsstatus = tsstatus;
	}
	/**
	 * 获取活动系数
	 * @return ratio 活动系数
	 */
	public int getRatio() {
		return ratio;
	}
	/**
	 * 设置活动系数
	 * @param ratio 活动系数
	 */
	public void setRatio(int ratio) {
		this.ratio = ratio;
	}
	/**
	 * 获取组织Id
	 * @return orgid 组织Id
	 */
	public Long getOrgid() {
		return orgid;
	}
	/**
	 * 设置组织Id
	 * @param orgid 组织Id
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
	 * 获取组织名称
	 * @param orgname 组织名称
	 */
	public void setOrgname(String orgname) {
		this.orgname = orgname;
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
	 * 获取用户是否加入此活动
	 * @return join 用户是否加入此活动
	 */
	public boolean isJoin() {
		return join;
	}
	/**
	 * 设置用户是否加入此活动
	 * @param join 用户是否加入此活动
	 */ 
	public void setJoin(boolean join) {
		this.join = join;
	}
	/**
	 * 获取时间储蓄
	 * @return acthours 时间储蓄
	 */
	public int getActhours() {
		return acthours;
	}
	/**
	 * 设置时间储蓄
	 * @param acthours 时间储蓄
	 */
	public void setActhours(int acthours) {
		this.acthours = acthours;
	}
	/**
	 * 获取活动数目
	 * @return activenum 活动数目
	 */
	public int getActivenum() {
		return activenum;
	}
	/**
	 * 设置活动数目
	 * @param activenum 活动数目
	 */
	public void setActivenum(int activenum) {
		this.activenum = activenum;
	}
	/**
	 * 获取父组织Id
	 * @return parentorgid 父组织Id
	 */
	public Long getParentorgid() {
		return parentorgid;
	}
	/**
	 * 设置父组织Id
	 * @param parentorgid 父组织Id
	 */
	public void setParentorgid(Long parentorgid) {
		this.parentorgid = parentorgid;
	}
	/**
	 * 获取机构层级
	 * @return orglevel 机构层级
	 */
	public int getOrglevel() {
		return orglevel;
	}
	/**
	 * 设置机构层级
	 * @param orglevel 机构层级
	 */
	public void setOrglevel(int orglevel) {
		this.orglevel = orglevel;
	}
	/**
	 * 获取参加活动人数
	 * @return attendnum 参加活动人数
	 */
	public int getAttendnum() {
		return attendnum;
	}
	/**
	 * 设置参加活动人数
	 * @param attendnum 参加活动人数
	 */
	public void setAttendnum(int attendnum) {
		this.attendnum = attendnum;
	}
	
}
