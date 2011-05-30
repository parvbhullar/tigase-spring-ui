package com.ivyinfo.user.services;

import java.util.List;

import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.user.bean.UserCardRecycleBean;
import com.ivyinfo.user.dao.UserCardRecycleDAO;

public class UserCardRecycleServicesImpl implements UserCardRecycleServices{
	
	public List getListUserCardRecycle(String logname) throws Exception{
		UserCardRecycleDAO userCardRecycleDAO =(UserCardRecycleDAO) SpringContextUtil.getBean("userCardRecycleDAO");
		return userCardRecycleDAO.getListUserCardRecycle(logname);
	}
	
	public void AddUserCardRecycle(UserCardRecycleBean userCardRecycleBean) throws Exception{
		UserCardRecycleDAO userCardRecycleDAO =(UserCardRecycleDAO) SpringContextUtil.getBean("userCardRecycleDAO");
		userCardRecycleDAO.AddUserCardRecycle(userCardRecycleBean);
	}
	
	public void DelUserCardRecycle(String cardnumber) throws Exception{
		UserCardRecycleDAO userCardRecycleDAO =(UserCardRecycleDAO) SpringContextUtil.getBean("userCardRecycleDAO");
		userCardRecycleDAO.DelUserCardRecycle(cardnumber);
	}
}
