package com.ivyinfo.framework.common.ftp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import com.ftpclient.exception.NoEnoughSpaceException;
import com.ftpclient.exception.PropertiesFileReadingException;
import com.ftpclient.exception.RequiredAttributeNotFoundException;
import com.ftpclient.fileManage.FtpFileManage;
import com.ftpclient.userManage.FtpUserManage;
import com.ftpclient.util.CompressingFtpUtil;
import com.ftpclient.util.FtpUtil;
import com.ftpclient.util.RequestSender;
import com.ftpclient.util.SimpleFtpUtil;

public class Test {

	/**
	 * @param args
	 * @throws PropertiesFileReadingException 
	 * @throws PropertiesFileReadingException 
	 * @throws PropertiesFileReadingException 
	 * @throws IOException 
	 * @throws NoEnoughSpaceException 
	 * @throws RequiredAttributeNotFoundException 
	 */
	

	public static void main(String[] args) throws PropertiesFileReadingException {
		
		//CompressingFtpUtil使用默认的conf/fileManage.properties作为配置文件进行配置
		CompressingFtpUtil ftpUtil = new CompressingFtpUtil();
		
		//使用其他配置文件进行配置
		//CompressingFtpUtil ftpUtil = new CompressingFtpUtil("conf/otherPropFile.properties");
		
		boolean re = false;
		try {
			ftpUtil.connect(); //连接ftpServer
			
			System.err.println("loging.... ");
			re = ftpUtil.login("admin", "admin"); //以abc为用户名登陆
			
			System.err.println("login result: "+re);
			
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

//		//上传文件D:\webCall系统集成接口.doc到当前用户abc的/a/b目录下
//		File file = new File("D:\\1111.txt");
//		FileInputStream fin = null;
//		try {
//			fin = new FileInputStream(file);
//			
//			ftpUtil.upload(fin, "1111.txt", "/doc");
//			
//			fin.close();
//		} catch (NoEnoughSpaceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		ftpUtil.download("20110125183249857036", "E:\\");
		
//		ftpUtil.remove("20101231140137826238");
		
		ftpUtil.logout(); //退出登陆
		ftpUtil.disconnect(); //退出连接
		
	}

	
//	public static void main(String[] args) throws PropertiesFileReadingException, RequiredAttributeNotFoundException{
//		
//		try{
//		//使用默认的conf/userManage.properties配置文件进行配置
//		FtpUserManage manager = new FtpUserManage();
//		
//		//使用其他配置文件进行配置
//		//FtpUserManage manager = new FtpUserManage("conf/otherPropFile.properties");
//	
//		Map params = new HashMap();
//		params.put(manager.USER_NAME, "admin");
//		params.put(manager.PASSWORD, "admin");//修改用户密码
//		
//		boolean re;
////		re = manager.createNewUser(params);
//		re = manager.modifyUser(params);
////		re = manager.deleteUser("user");
//		
//		if(re == true)
//			System.out.println("SUCCESSFUL!!");
//		else
//			System.out.println("ERROR..");
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//	}
	
//	public static void main(String[] args) throws PropertiesFileReadingException, NoEnoughSpaceException, IOException{
//		
//		SimpleFtpUtil ftpUtil = new SimpleFtpUtil();
//		
//		boolean re = false;
//		try {
//			ftpUtil.connect(); //连接ftpServer
//			
//			re = ftpUtil.login("abc", "abc"); //以abc为用户名登陆
//			
//			System.err.println("login result: "+re);
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//			return;
//		}
//		
//		String path = "D:\\test.txt";
//		String remotePath = "test.txt";
//		
//		ftpUtil.upload(path, remotePath);
//	}
	
//	public static void main(String[] args){
//		
//		String path = "/meDir";
//		File dir = new File(path);
//		
//		boolean re = dir.mkdirs();
//		System.out.println(re);
//	}
}
