package com.ivyinfo.framework.common.ftp;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import com.ftpclient.userManage.FtpUserManage;
import com.ftpclient.util.CompressingFtpUtil;

public class FtpTools {
	
	/**
	 * 建立ftp连接、登陆
	 * @param host ftp地址  192.168.1.11
	 * @param port ftp端口
	 * @param foldername ftp文件夹名称
	 * @param logname ftp登陆名称
	 * @param logpassword ftp登陆密码
	 * @throws Exception
	 */
	public boolean FtpConnectLogin(CompressingFtpUtil ftpUtil,String foldername,String logname,String logpassword) throws Exception{
		boolean returnvalue = false;
		try{
			ftpUtil.connect();
			
			returnvalue = ftpUtil.login(logname, logpassword);
			System.err.println("returnvalue:"+returnvalue);
			
			return returnvalue;
		}catch(Exception e){
			e.printStackTrace();
			return returnvalue;
		}
	}
	
	/**
	 * 上传文件
	 * @param fistream 待上传文件的输入流
	 * @param filename 待上传文件的文件名  1111.doc
	 * @param remotepath 在ftp端的保存路径
	 * @return  String(被上传的文件在ftp端的保存名称--时间戳)
	 * @throws Exception
	 */
	public String FtpUpload(CompressingFtpUtil ftpUtil,FileInputStream fistream,String filename,String remotepath) throws Exception{
		String returntime = "";
		try{
			
			returntime = ftpUtil.upload(fistream, filename, remotepath);
			System.err.println("returntime:"+returntime);
			
			return returntime;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 下载文件
	 * @param timestamp 时间戳，唯一标识ftp端的文件  本地ftpid
	 * @param localpath 本地存放目录
	 * @return boolean(true成功，false失败)
	 * @throws Exception
	 */
	public boolean FtpDownload(CompressingFtpUtil ftpUtil,String timestamp, String localpath) throws Exception{
		boolean returnvalue = false;
		try{
			
			returnvalue = ftpUtil.download(timestamp, localpath);
			System.err.println("returnvalue:"+returnvalue);
			
			return returnvalue;
		}catch(Exception e){
			e.printStackTrace();
			return returnvalue;
		}
	}
	
	/**
	 * ftp退出并断开连接
	 * @throws Exception
	 */
	public void FtpLogoutDisconnect(CompressingFtpUtil ftpUtil) throws Exception{
		try{
			
			ftpUtil.logout();//退出
			ftpUtil.disconnect();//断开连接
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除ftp端文件
	 * @param timestamp 时间戳，唯一标识ftp端的文件  本地ftpid
	 * @return boolean(true成功，false失败)
	 * @throws Exception
	 */
	public boolean FtpDeleteFile(CompressingFtpUtil ftpUtil,String timestamp) throws Exception{
		boolean returnvalue = false;
		try{
			
			returnvalue = ftpUtil.remove(timestamp);
			System.err.println("returnvalue:"+returnvalue);
			
			return returnvalue;
		}catch(Exception e){
			e.printStackTrace();
			return returnvalue;
		}
	}
	
}
