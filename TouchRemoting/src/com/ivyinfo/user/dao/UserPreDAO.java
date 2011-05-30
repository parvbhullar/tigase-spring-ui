package com.ivyinfo.user.dao;

import java.util.List;

import com.ivyinfo.framework.service.base.IBaseDAO;
import com.ivyinfo.user.bean.UserPreBean;

public interface UserPreDAO extends IBaseDAO{
	public List UserPreList(String logname,String keyword) throws Exception;
	
	public void UserPreAdd(UserPreBean userPreBean) throws Exception;
	
	public void UserPreUpd(UserPreBean userPreBean) throws Exception;
}
