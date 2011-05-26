package com.ivyinfo.purview.dao;

import java.util.List;

import com.ivyinfo.framework.service.base.IBaseDAO;
import com.ivyinfo.purview.bean.PurviewBean;
import com.ivyinfo.user.bean.UserBean;

public interface PurviewDAO extends IBaseDAO{
	
	public UserBean ValidationLogin(String logname) throws Exception;
}
