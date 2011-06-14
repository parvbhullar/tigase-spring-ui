package com.ivyinfo.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.user.services.UserServices;

public class Mail {
	private static final Logger LOGGER = LoggerFactory.getLogger(Mail.class);
	
	
	private UserServices userServices = (UserServices) SpringContextUtil
	.getBean("userServices");
	
	
	
}
