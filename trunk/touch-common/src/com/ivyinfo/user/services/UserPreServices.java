package com.ivyinfo.user.services;

import java.util.List;

import com.ivyinfo.framework.service.base.IBaseService;
import com.ivyinfo.user.bean.UserPreBean;

public interface UserPreServices extends IBaseService{
	
	public List UserPreList(String logname,String keyword) throws Exception;
	
	public void UserPreAdd(UserPreBean userPreBean) throws Exception;
	
	public void UserPreUpd(UserPreBean userPreBean) throws Exception;
}
