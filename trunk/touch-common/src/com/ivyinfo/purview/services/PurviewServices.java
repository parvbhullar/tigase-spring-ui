package com.ivyinfo.purview.services;

import com.ivyinfo.framework.service.base.IBaseService;
import com.ivyinfo.login.bean.LoginBean;
import com.ivyinfo.session.bean.SessionUserBean;

public interface PurviewServices extends IBaseService{
	public SessionUserBean setSessionValue(String userid) throws Exception;
	
	public LoginBean ValidationLogin(String logname,String password) throws Exception;
}
