package com.ivyinfo.user.services;

import java.util.List;
import java.util.Map;

import org.njdt.gg.bmf.base.IDao;
import org.njdt.gg.ccl.datastructure.Dto;

import com.ivyinfo.framework.service.base.IBaseService;
import com.ivyinfo.framework.service.server.SpringContextUtil;
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
	
	/**
	 * 
	 * @param inDto
	 * @return
	 * @throws Exception
	 */
	public Dto queryUserForManage(Map map)throws Exception;
	
	/**
	 * 删除用户
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto deleteUserItems(Dto pDto);
}
