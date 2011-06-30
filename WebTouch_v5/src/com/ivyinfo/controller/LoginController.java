package com.ivyinfo.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class LoginController {
	protected final Log logger = LogFactory.getLog(getClass());  
	  
    @RequestMapping("/login")  
    public ModelAndView helloWorld(){  
        logger.info("login");  
        return new ModelAndView("layout_tree.jsp");  
    }
}
