package com.ivyinfo.user.services;

import java.util.List;

import org.njdt.gg.ccl.datastructure.Dto;

import com.ivyinfo.framework.service.base.IBaseService;
import com.ivyinfo.user.bean.UserBean;

public interface UserServices extends IBaseService{
	/*
	 * 
	 */
	public Dto saveUserItem(Dto inDto) throws Exception;
	
	/**
	 * 用户登录
	 * @param inDto
	 * @throws Exception
	 */
	public Dto ValidationLogin(Dto inDto) throws Exception;
}
