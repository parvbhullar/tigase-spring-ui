package com.ivyinfo.controller;

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
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class UploadServlet extends HttpServlet{
	private static final Logger LOGGER = LoggerFactory.getLogger(UploadServlet.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
	    {
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
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
	            	File   filePath=new   File(request.getRealPath("")+"/temp/"); 
	            	if(!filePath.exists())
	            		filePath.mkdirs();
	                File uploadFile=new File(request.getRealPath("")+"/temp/",name);
	                
	                LOGGER.info("upload" + upload+";\nname="+name);
	                LOGGER.info("request.getRealPath('') " + request.getRealPath(""));
	                LOGGER.info("uploadFile=" + uploadFile);
	                
	                item.write(uploadFile);
	                
	                
	                jsonObj.put("name", name);                // 当前页
	    	        jsonObj.put("type",	item.getContentType());        // 总页数
	    	        jsonObj.put("size", item.getSize());        // 总记录数
	            }
	        }
	        response.getWriter().print(jsonObj);
	    } catch (Exception e) {
	        throw new ServletException(e);
	    }
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
 
}

}

