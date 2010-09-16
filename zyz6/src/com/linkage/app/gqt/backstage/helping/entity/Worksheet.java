package com.linkage.app.gqt.backstage.helping.entity;

import java.util.Date;
import java.io.Serializable;
/**
 * 工单表
 *
 */

public class Worksheet implements java.io.Serializable {

	/** 工单ID  */
	private Long worksheetid;
	
	/** 工单号 */
	private String worksheetno;
	
	/** 被帮扶者姓名 */
	private String HName;

	/** 被帮扶者事由  */
	private String HTitle;

	/** 被帮扶者地点 */
	private String HAddress;

	/** 所属区域ID  */
	private Long HAreaid;

	/** 所属分类ID */
	private Long HTypeid;

	/** 帮扶开始时间  */
	private String HStartdate;

	/** 帮扶结束时间 */
	private String HOverdate;

	/** 帮扶事由简介 */
	private String HNote;

	/** 志愿者姓名  */
	private String VName;

	/** 志愿者联系电话  */
	private Long VTel;

	/** 志愿者所属区域ID  */
	private Long VAreaid;	
	
	
	/** 发布状态 0:发布 1:结束 */
	private Long state;
	
	/** 管理员ID  */
	private String adminid;
	
	/** 是否删除 */
	private long isDel;
	
	/** 是否积分 */
	private long sysValue;
	
	
	/** 志愿者ID */
	private long id;

	/** 修改标志 */
	private String editFlag;
	
	public Worksheet() {
	}

	/** full constructor */
	public Worksheet(String worksheetno, String HName, String HTitle,
			String HAddress, Long HAreaid, Long HTypeid, String HStartdate,
			String HOverdate, String HNote, String VName, Long VTel,
			Long state, String adminid, Long isDel, Long sysValue, Long id) {
		this.worksheetno = worksheetno;
		this.HName = HName;
		this.HTitle = HTitle;
		this.HAddress = HAddress;
		this.HAreaid = HAreaid;
		this.HTypeid = HTypeid;
		this.HStartdate = HStartdate;
		this.HOverdate = HOverdate;
		this.HNote = HNote;
		this.VName = VName;
		this.VTel = VTel;
		this.state = state;
		this.adminid = adminid;
		this.isDel = isDel;
		this.sysValue = sysValue;
		this.id = id;
		
	}


	public Long getWorksheetid() {
		return this.worksheetid;
	}

	public void setWorksheetid(Long worksheetid) {
		this.worksheetid = worksheetid;
	}

	public String getWorksheetno() {
		return this.worksheetno;
	}

	public void setWorksheetno(String worksheetno) {
		this.worksheetno = worksheetno;
	}

	public String getHName() {
		return this.HName;
	}

	public void setHName(String HName) {
		this.HName = HName;
	}

	public String getHTitle() {
		return this.HTitle;
	}

	public void setHTitle(String HTitle) {
		this.HTitle = HTitle;
	}

	public String getHAddress() {
		return this.HAddress;
	}

	public void setHAddress(String HAddress) {
		this.HAddress = HAddress;
	}

	public Long getHAreaid() {
		return this.HAreaid;
	}

	public void setHAreaid(Long HAreaid) {
		this.HAreaid = HAreaid;
	}

	public Long getHTypeid() {
		return this.HTypeid;
	}

	public void setHTypeid(Long HTypeid) {
		this.HTypeid = HTypeid;
	}

	public String getHStartdate() {
		return this.HStartdate;
	}

	public void setHStartdate(String HStartdate) {
		this.HStartdate = HStartdate;
	}

	public String getHOverdate() {
		return this.HOverdate;
	}

	public void setHOverdate(String HOverdate) {
		this.HOverdate = HOverdate;
	}

	public String getHNote() {
		return this.HNote;
	}

	public void setHNote(String HNote) {
		this.HNote = HNote;
	}

	public String getVName() {
		return this.VName;
	}

	public void setVName(String VName) {
		this.VName = VName;
	}



	public Long getVTel() {
		return this.VTel;
	}

	public void setVTel(Long VTel) {
		this.VTel = VTel;
	}

	public Long getVAreaid() {
		return this.VAreaid;
	}

	public void setVAreaid(Long VAreaid) {
		this.VAreaid = VAreaid;
	}	
	
	
	
	
	public Long getState() {
		return this.state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	public String getAdminid() {
		return this.adminid;
	}

	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}

	public Long getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Long isDel) {
		this.isDel = isDel;
	}	
	
	
	public Long getSysValue() {
		return this.sysValue;
	}

	public void setSysValue(Long sysValue) {
		this.sysValue = sysValue;
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	
}