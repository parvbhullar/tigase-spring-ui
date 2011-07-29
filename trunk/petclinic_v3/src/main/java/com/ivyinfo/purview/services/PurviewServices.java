package com.ivyinfo.purview.services;

import com.ivyinfo.framework.service.base.IBaseService;
import com.ivyinfo.login.bean.LoginBean;

public interface PurviewServices extends IBaseService{
	
	public LoginBean ValidationLogin(String logname,String password) throws Exception;
}
