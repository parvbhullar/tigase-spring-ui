package com.ivyinfo.user.services;

import java.util.List;

import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.user.bean.UserPreBean;
import com.ivyinfo.user.dao.UserPreDAO;

public class UserPreServicesImpl implements UserPreServices{
	
	public List UserPreList(String logname,String keyword) throws Exception{
		UserPreDAO userPreDAO =(UserPreDAO) SpringContextUtil.getBean("userPreDAO");
		return userPreDAO.UserPreList(logname,keyword);
	}
	
	public void UserPreAdd(UserPreBean userPreBean) throws Exception{
		UserPreDAO userPreDAO =(UserPreDAO) SpringContextUtil.getBean("userPreDAO");
		userPreDAO.UserPreAdd(userPreBean);
	}
	
	public void UserPreUpd(UserPreBean userPreBean) throws Exception{
		UserPreDAO userPreDAO =(UserPreDAO) SpringContextUtil.getBean("userPreDAO");
		userPreDAO.UserPreUpd(userPreBean);
	}
}
