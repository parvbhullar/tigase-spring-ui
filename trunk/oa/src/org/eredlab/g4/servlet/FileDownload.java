package org.eredlab.g4.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eredlab.g4.ccl.ftp.FtpHelper;
import org.eredlab.g4.ccl.util.G4Utils;

public class FileDownload extends HttpServlet{
	protected void service(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {

  // 服务器相对路径
  String path1=java.net.URLDecoder.decode(req.getParameter("path"),"utf-8");
  
  String upLoadType=req.getParameter("upLoadType");
  
  if(G4Utils.isEmpty(upLoadType))
  {
	  upLoadType="0";//普通文件上传的啊
  }
  
  if(upLoadType.equals("1"))
  {
	  int index = path1.lastIndexOf("/"); // 前提：传入的path字符串以“\”表示目录分隔符
	  String fileName = path1.substring(index + 1);
	  //ftp下载
	  FtpHelper ftpHelper = new FtpHelper();
	  try {
	   // 连接FTP服务器
	   // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
	   ftpHelper.createConnection("192.168.18.239", "anonymous", "", 21);
	   String l="E:/test";
	   File local=new File(l);
	   if(!local.exists())
	   {
		   local.mkdir();
	   }
	  
	   //ftpHelper.useWorkingDir("E:/text");
	   // 列出该目录下所有文件
	   ftpHelper.getFile("E:/test"+"/"+fileName, "C:/TEMP"+"/"+path1);
	   // 退出ftp
	 
	  } catch (Exception e) {
	   e.printStackTrace();
	  } finally {
	  
	    try {
	     ftpHelper.disconnect();
	    } catch (Exception ioe) {
	    }
	   
	  }
	
	  return ;
	  
	  
	  
  }
  // 服务器绝对路径
 String path = getServletContext().getRealPath("/") + path1;

  // 检查文件是否存在
  File obj = new File(path);
  if (!obj.exists()) {
	  Boolean success=false;
	  //去ftp上查找
	  int index = path1.lastIndexOf("/"); // 前提：传入的path字符串以“\”表示目录分隔符
	  String fileName = path1.substring(index + 1);
	  //ftp下载
	  FtpHelper ftpHelper = new FtpHelper();
	  try {
	   // 连接FTP服务器
	   // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
	   ftpHelper.createConnection("192.168.18.239", "anonymous", "", 21);
	   String l="E:/test";
	   File local=new File(l);
	   if(!local.exists())
	   {
		   local.mkdir();
	   }
	  
	   //ftpHelper.useWorkingDir("E:/text");
	   // 列出该目录下所有文件
	  success= ftpHelper.getFile("E:/test"+"/"+fileName, "C:/TEMP"+"/"+path1);
	   // 退出ftp
	 
	  } catch (Exception e) {
	   e.printStackTrace();
	  } finally {
	  
	    try {
	     ftpHelper.disconnect();
	    } catch (Exception ioe) {
	    }
	   
	  }
	
	  if(!success)
	  {
		  res.setContentType("text/html;charset=UTF-8");
		  res.getWriter().print("指定文件不存在！");  
		  return;
	  }
	  return ;
	  
  }

  // 读取文件名：用于设置客户端保存时指定默认文件名
  int index = path.lastIndexOf("\\"); // 前提：传入的path字符串以“\”表示目录分隔符
  String fileName = path.substring(index + 1);

  // 写流文件到前端浏览器
  ServletOutputStream out = res.getOutputStream();
  res.setHeader("Content-disposition", "attachment;filename=" +   new String(fileName.getBytes("gb2312"),"iso8859-1"));
  BufferedInputStream bis = null;
  BufferedOutputStream bos = null;
  try {
    bis = new BufferedInputStream(new FileInputStream(path));
    bos = new BufferedOutputStream(out);
    byte[] buff = new byte[2048];
    int bytesRead;
    while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
      bos.write(buff, 0, bytesRead);
    }
  } catch (IOException e) {
    throw e;
  } finally {
    if (bis != null)
      bis.close();
    if (bos != null)
      bos.close();
  }
}

}
