package com.ivyinfo.user.services;

import java.util.List;

import org.njdt.gg.ccl.datastructure.Dto;

import com.ivyinfo.framework.service.base.IBaseService;
import com.ivyinfo.purview.bean.PurviewBean;
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
	 * 
	 * @param inDto
	 * @throws Exception
	 */
	public Dto ValidationLogin(Dto inDto) throws Exception;
}
