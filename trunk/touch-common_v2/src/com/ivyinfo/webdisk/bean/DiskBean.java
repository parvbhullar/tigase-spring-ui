package com.ivyinfo.webdisk.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class DiskBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684269L;
	
	/**
	 * 网络硬盘管理   t_disk
	 */
	private String id;							//id
	private String userid;						//用户id
	private String ftpid;						//ftpid
	private String filename;					//文件名
	private String filepath;					//文件路径
	private String filesuffix;					//文件后缀
	private String filesize;					//文件大小
	private String datetime;					//上传时间
	private String filegroupid;					//文件组id
	private String remark;						//备注
	private String upduserid;					//修改用户id
	private String timestemp;					//修改时间
	private String dirtyflag;					//脏标记
	private String saveRealPath;				//本地临时文件地址
	private String ftpusername;					//ftp登录名
	private String ftppassword;					//ftp登陆密码
	
	/**
	 * 网络硬盘组管理   t_disk_group
	 */
	private String groupid;						//id
	private String guserid;						//用户id
	private String groupname;					//组名
	private String superiorgroupid;				//上级组id
	private String gremark;						//备注
	private String gupduserid;					//修改用户id
	private String gtimestemp;					//修改时间
	private String gdirtyflag;					//脏标记
	
	
	public String getFtpusername() {
		return ftpusername;
	}
	public void setFtpusername(String ftpusername) {
		this.ftpusername = ftpusername;
	}
	public String getFtppassword() {
		return ftppassword;
	}
	public void setFtppassword(String ftppassword) {
		this.ftppassword = ftppassword;
	}
	public String getFtpid() {
		return ftpid;
	}
	public void setFtpid(String ftpid) {
		this.ftpid = ftpid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getFilesuffix() {
		return filesuffix;
	}
	public void setFilesuffix(String filesuffix) {
		this.filesuffix = filesuffix;
	}
	public String getFilesize() {
		return filesize;
	}
	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getFilegroupid() {
		return filegroupid;
	}
	public void setFilegroupid(String filegroupid) {
		this.filegroupid = filegroupid;
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
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getGuserid() {
		return guserid;
	}
	public void setGuserid(String guserid) {
		this.guserid = guserid;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getSuperiorgroupid() {
		return superiorgroupid;
	}
	public void setSuperiorgroupid(String superiorgroupid) {
		this.superiorgroupid = superiorgroupid;
	}
	public String getGremark() {
		return gremark;
	}
	public void setGremark(String gremark) {
		this.gremark = gremark;
	}
	public String getGupduserid() {
		return gupduserid;
	}
	public void setGupduserid(String gupduserid) {
		this.gupduserid = gupduserid;
	}
	public String getGtimestemp() {
		return gtimestemp;
	}
	public void setGtimestemp(String gtimestemp) {
		this.gtimestemp = gtimestemp;
	}
	public String getGdirtyflag() {
		return gdirtyflag;
	}
	public void setGdirtyflag(String gdirtyflag) {
		this.gdirtyflag = gdirtyflag;
	}
	public String getSaveRealPath() {
		return saveRealPath;
	}
	public void setSaveRealPath(String saveRealPath) {
		this.saveRealPath = saveRealPath;
	}
	
	
}
