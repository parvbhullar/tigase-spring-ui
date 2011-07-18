package com.ivyinfo.webdisk.bean;

import java.io.File;

import com.ivyinfo.framework.service.base.BaseBean;

public class WebDiskBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684249L;

	private File upload;// 实际上传文件

	private String uploadContentType; // 文件的内容类型

	private String uploadFileName; // 上传文件名

	private String fileCaption;// 上传文件时的备注
	
	private String targetDirectory = "d://webmeettouchtemp"; //上传的文件夹名称
	
	private String ftpLoginName;
	
	private String ftpPassword;
	
	private String ftpDirType;		//FTP目录类型
	
	
	public String getTargetDirectory() {
		return targetDirectory;
	}

	public void setTargetDirectory(String targetDirectory) {
		this.targetDirectory = targetDirectory;
	}

	public String getFileCaption() {
		return fileCaption;
	}

	public void setFileCaption(String fileCaption) {
		this.fileCaption = fileCaption;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getFtpLoginName() {
		return ftpLoginName;
	}

	public void setFtpLoginName(String ftpLoginName) {
		this.ftpLoginName = ftpLoginName;
	}

	public String getFtpPassword() {
		return ftpPassword;
	}

	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}

	public String getFtpDirType() {
		return ftpDirType;
	}

	public void setFtpDirType(String ftpDirType) {
		this.ftpDirType = ftpDirType;
	}
}
