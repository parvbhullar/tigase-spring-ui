package com.myorg.springmvctutorial.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ivyinfo.mail.FileUploadListener;

@Controller
public class HelloWorldController{
	protected final Log logger = LogFactory.getLog(getClass());

	@RequestMapping("/helloWorld")
	public ModelAndView helloWorld(){
		logger.info("Return View");
		return new ModelAndView("helloworld.jsp");
	}
	
	@RequestMapping("/getUploadStatus")
	public void getUploadStatus(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
	    HttpSession session = request.getSession();
	    FileUploadListener listener = null; 
	    StringBuffer buffy = new StringBuffer();
	    long bytesRead = 0,  contentLength = 0; 

	    if (session == null){
	      return;
	      } else if (session != null){
	        listener = (FileUploadListener)session.getAttribute("LISTENER");
	        
	        if (listener == null){
	          return;
	        } else {
	            bytesRead = listener.getBytesRead();
	            contentLength = listener.getContentLength();
	        }
	      }
	    logger.info("percentComplete="+contentLength);
	    long percentComplete = ((100 * bytesRead) / contentLength); 
	    logger.info("bytesRead="+bytesRead+";percentComplete="+percentComplete);
	    out.println(percentComplete);
	    out.flush();
	    out.close();
	}
}