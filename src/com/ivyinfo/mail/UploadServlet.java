package com.ivyinfo.mail;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.session.bean.SessionUserBean;
import com.ivyinfo.user.bean.UserBean;
import com.ivyinfo.webdisk.bean.WebDiskBean;
import com.ivyinfo.webdisk.services.IWebDiskServices;

public class UploadServlet extends HttpServlet{
	private static final Logger LOGGER = LoggerFactory.getLogger(UploadServlet.class);
	private IWebDiskServices iWebDiskServices= (IWebDiskServices) SpringContextUtil
	.getBean("webDiskService");
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
	    {
		response.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		SessionUserBean sessionUserBean=(SessionUserBean)session.getAttribute("sessionUserBean");
		UserBean userBean=sessionUserBean.getUserBean();
		FileUploadListener listener = new FileUploadListener(request.getContentLength());
		session.setAttribute("FILE_UPLOAD_STATS", listener.getFileUploadStats());
	    try {
	    	ServletFileUpload upload=new ServletFileUpload(new DiskFileItemFactory());
	    	upload.setHeaderEncoding("UTF-8");
	        List<FileItem> items = upload.parseRequest(request);
	        JSONObject jsonObj = new JSONObject();
	        JSONArray rows = new JSONArray();
	        for (FileItem item : items) {
	            if (!item.isFormField()) {
	            	LOGGER.info("Name: " + item.getName());
	            	LOGGER.info("Type: " + item.getContentType());
	            	LOGGER.info("Size: " + item.getSize());
	            	String name=item.getName();
	            	LOGGER.info("name.lastIndexOf('\\''): " + name.lastIndexOf("\\"));
	            	if(name.lastIndexOf("\\")!=-1){
	            		name=item.getName().substring(name.lastIndexOf("\\"));
	            	}
	            	
	            	File   filePath=new   File(request.getRealPath("")+"//temp//"); 
	            	if(!filePath.exists())
	            		filePath.mkdirs();
	                File uploadFile=new File(request.getRealPath("")+"//temp//",name);
	                
	                LOGGER.info("upload" + upload+";\nname="+name);
	                LOGGER.info("request.getRealPath('') " + request.getRealPath(""));
	                LOGGER.info("uploadFile=" + uploadFile);
	                
	                item.write(uploadFile);
	                WebDiskBean webDiskBean=new WebDiskBean();
	                webDiskBean.setUpload(uploadFile);
	                webDiskBean.setFtpLoginName(userBean.getLogname());
	                webDiskBean.setFtpPassword(userBean.getPassword());
	                webDiskBean.setUploadFileName(item.getName());
	                String logname=sessionUserBean.getSetupmailBean().getLogname();
	                webDiskBean.setFtpDirType("//mail//"+logname);
	                LOGGER.info("WebDisk上传文件开始");
	                long beginTime=System.currentTimeMillis();
	                String filepath=iWebDiskServices.uploadFile(webDiskBean);
	                LOGGER.info("WebDisk上传文件结束  用时:"+(beginTime-System.currentTimeMillis())+";返回值filepath="+filepath);
	                LOGGER.info("filepath=" + filepath);
	                jsonObj.put("name", name);                			
	    	        jsonObj.put("type",	item.getContentType());        	
	    	        jsonObj.put("size", item.getSize());
	    	        jsonObj.put("filepath", filepath);
	            }
	        }
	        response.getWriter().print(jsonObj);
	    } catch (Exception e) {
	        throw new ServletException(e);
	    }
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		SessionUserBean sessionUserBean=(SessionUserBean)session.getAttribute("sessionUserBean");
		UserBean userBean=sessionUserBean.getUserBean();
		String fileName=request.getParameter("deleteFileName");
		LOGGER.info("fileName="+fileName);
		File uploadedFile=new File(request.getRealPath("")+"//temp//",fileName);
		uploadedFile.delete();
	}

}

