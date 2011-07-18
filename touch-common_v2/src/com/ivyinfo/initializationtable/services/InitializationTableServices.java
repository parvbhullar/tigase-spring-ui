package com.ivyinfo.initializationtable.services;

import com.ivyinfo.framework.service.base.IBaseService;

public interface InitializationTableServices extends IBaseService{
	/**
	 * 创建用户表
	 * @param logname
	 * @throws Exception
	 */
	public void CreateUserTable(String logname) throws Exception;
	
	/**
	 * 删除用户表
	 * @param logname
	 * @throws Exception
	 */
	public void DropUserTable(String logname) throws Exception;
}
