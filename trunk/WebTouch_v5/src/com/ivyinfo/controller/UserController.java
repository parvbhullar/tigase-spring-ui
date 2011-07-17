package com.ivyinfo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.njdt.gg.ccl.datastructure.Dto;
import org.njdt.gg.ccl.datastructure.impl.BaseDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.user.services.UserServices;
  
@Controller  
public class UserController{   
    protected final Log logger = LogFactory.getLog(getClass());
    
    
    private UserServices userServices = (UserServices) SpringContextUtil
	.getBean("userServices");
  
    /**
     * 保存用户
     * @return
     * @throws IOException 
     */
    @RequestMapping("/user/saveUserItem")   
    public void saveUserItem(HttpServletRequest request, HttpServletResponse response) throws IOException{   
        logger.info("Return View="+request.getParameter("logname"));
        Dto inDto = new BaseDto();
        inDto.put("logname", request.getParameter("logname"));
        try {
			userServices.saveUserItem(inDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
        PrintWriter pw = response.getWriter();
		pw.print("1");   
    }   
    
}