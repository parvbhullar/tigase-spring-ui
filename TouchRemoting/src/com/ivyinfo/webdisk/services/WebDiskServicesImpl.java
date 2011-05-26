package com.ivyinfo.webdisk.services;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.FileUtils;

import com.ftpclient.util.CompressingFtpUtil;
import com.ivyinfo.framework.common.file.FileTools;
import com.ivyinfo.framework.common.ftp.FtpTools;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.webdisk.bean.DiskBean;
import com.ivyinfo.webdisk.bean.WebDiskBean;
import com.ivyinfo.webdisk.dao.IWebDiskDAO;

public class WebDiskServicesImpl implements IWebDiskServices{

	public String uploadFile(WebDiskBean webDiskBean) throws Exception {
		IWebDiskDAO webDiskDAO =(IWebDiskDAO) SpringContextUtil.getBean("webDiskDAO");
		String foldername = "/tempDir";
		String logname = webDiskBean.getFtpLoginName();
		String logpassword = webDiskBean.getFtpPassword();
		FtpTools ftpTools = new FtpTools();
		
		/**
		 * 建立ftp连接、登陆
		 */
		CompressingFtpUtil ftpUtil = new CompressingFtpUtil();
		
		boolean returnvalue = ftpTools.FtpConnectLogin(ftpUtil, foldername, logname, logpassword);
		System.err.println("returnvalue:"+returnvalue);
		
		System.err.println("getTargetDirectory:"+webDiskBean.getTargetDirectory());
		FileTools.mkdir(new File(webDiskBean.getTargetDirectory()));
		
		String targetFileName = webDiskBean.getUploadFileName();
		System.err.println("targetFileName:"+targetFileName);
		
		String filename = targetFileName.substring(targetFileName.indexOf("\\")+1,targetFileName.length());
		System.err.println("filename:"+filename);
		
		System.err.println("getUpload:"+webDiskBean.getUpload());
		
		System.err.println("getUploadContentType:"+webDiskBean.getUploadContentType());
		System.err.println("getUploadFileName:"+webDiskBean.getUploadFileName());
		
		File target = new File(webDiskBean.getTargetDirectory(), filename);
		System.err.println("target:"+target);
		
		FileUtils.copyFile(webDiskBean.getUpload(), target);
		
		System.err.println("getPath:"+target.getPath());
		webDiskBean.setUploadFileName(target.getPath());//保存文件的存放路径
		
		/**
		 * 读取文件流
		 */
		FileInputStream fistream = new FileInputStream(target);
		
		/**
		 * 开始上传文件到ftp 返回ftp的时间戳，本地的ftpid
		 */
		String returntime = ftpTools.FtpUpload(ftpUtil,fistream, filename, webDiskBean.getFtpDirType());
		System.err.println("returntime:"+returntime);
		
		/**
		 * 保存数据库
		 */
		
		
		/**
		 * 移除临时文件夹文件
		 */
		File file = new File(webDiskBean.getTargetDirectory(), filename);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if(file.exists() && file.isFile()) {
			if(file.delete()) {
				System.out.println("删除单个文件" + filename + "成功！");
			} else {
				System.out.println("删除单个文件" + filename + "失败！");
			}
		} else {
			System.out.println("删除单个文件失败：" + filename + "不存在！");
		}
		
		ftpTools.FtpLogoutDisconnect(ftpUtil);
		
		return returntime;
	}
	
	public void download(DiskBean diskBean)throws Exception{
		IWebDiskDAO webDiskDAO =(IWebDiskDAO) SpringContextUtil.getBean("webDiskDAO");
		String foldername = "/tempDir";
		String logname = diskBean.getFtpusername();
		String logpassword = diskBean.getFtppassword();
		FtpTools ftpTools = new FtpTools();
		
		/**
		 * 建立ftp连接、登陆
		 */
		CompressingFtpUtil ftpUtil = new CompressingFtpUtil();
		System.out.println("diskBean.getFtpid()="+diskBean.getFtpid()+";ftpUtil="+ftpUtil+";foldername="+foldername+";logname="+logname+";logpassword="+logpassword);
		boolean returnvalue = ftpTools.FtpConnectLogin(ftpUtil, foldername, logname, logpassword);
		System.err.println("returnvalue:"+returnvalue);
		
		boolean value = ftpTools.FtpDownload(ftpUtil,diskBean.getFtpid(), diskBean.getSaveRealPath());
		System.err.println("value:"+value);
		if(value){
			
		}else{
			
		}
		
		ftpTools.FtpLogoutDisconnect(ftpUtil);
	}
}
