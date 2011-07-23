package com.ivyinfo.user.services;

import java.util.List;

import com.ivyinfo.framework.service.base.IBaseService;
import com.ivyinfo.user.bean.UserCardRecycleBean;

public interface UserCardRecycleServices extends IBaseService{
	/**
	 * 获得用户号码回收表信息
	 * @param logname
	 * @return
	 * @throws Exception
	 */
	public List getListUserCardRecycle(String logname) throws Exception;
	
	/**
	 * 新增用户号码回收表信息
	 * @param userCardRecycleBean
	 * @throws Exception
	 */
	public void AddUserCardRecycle(UserCardRecycleBean userCardRecycleBean) throws Exception;
	
	/**
	 * 删除用户号码回收表信息
	 * @param cardnumber
	 * @throws Exception
	 */
	public void DelUserCardRecycle(String cardnumber) throws Exception;
}
