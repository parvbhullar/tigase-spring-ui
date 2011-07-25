package com.ivyinfo.user.services;

import org.njdt.gg.ccl.datastructure.Dto;

import com.ivyinfo.framework.service.base.IBaseService;
import com.ivyinfo.user.bean.UserBean;

public interface UserServices extends IBaseService{
	/**
	 * 保存用户
	 * @param userid
	 * @param czje
	 * @throws Exception
	 */
	public void saveUserItem(Dto inDto) throws Exception;
	
	/**
	 * 用户登录
	 * @param userBean
	 * @throws Exception
	 */
	public Dto ValidationLogin(Dto inDto) throws Exception;
}
