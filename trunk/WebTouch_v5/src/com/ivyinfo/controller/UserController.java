package com.ivyinfo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
  
@Controller  
public class UserController{   
    protected final Log logger = LogFactory.getLog(getClass());   
  
    /**
     * 保存用户
     * @return
     * @throws IOException 
     */
    @RequestMapping("/user/saveUserItem")   
    public void saveUserItem(HttpServletRequest request, HttpServletResponse response) throws IOException{   
    	
        logger.info("Return View="+request.getParameter("logname"));   
        PrintWriter pw = response.getWriter();
		pw.print("1");   
    }   
    
}