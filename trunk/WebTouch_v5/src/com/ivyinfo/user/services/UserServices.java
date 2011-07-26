package com.ivyinfo.user.services;

import java.util.Map;

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
	public Dto saveUserItem(Dto inDto) throws Exception;
	
	/**
	 * 用户登录
	 * @param userBean
	 * @throws Exception
	 */
	public Dto ValidationLogin(Dto inDto) throws Exception;
	
	public Dto queryUserForManage(Map map)throws Exception;
}
