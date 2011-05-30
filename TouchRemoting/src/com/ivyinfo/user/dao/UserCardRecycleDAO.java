package com.ivyinfo.user.dao;

import java.util.List;

import com.ivyinfo.framework.service.base.IBaseDAO;
import com.ivyinfo.user.bean.UserCardRecycleBean;

public interface UserCardRecycleDAO extends IBaseDAO{
	public List getListUserCardRecycle(String logname) throws Exception;
	
	public void AddUserCardRecycle(UserCardRecycleBean userCardRecycleBean) throws Exception;
	
	public void DelUserCardRecycle(String cardnumber) throws Exception;
}
