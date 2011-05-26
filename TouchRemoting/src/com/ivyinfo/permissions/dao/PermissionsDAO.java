package com.ivyinfo.permissions.dao;

import java.util.List;

import com.ivyinfo.framework.service.base.IBaseDAO;
import com.ivyinfo.permissions.bean.PermissionsBean;

public interface PermissionsDAO extends IBaseDAO{
	public List AllIndex() throws Exception;
	
	public void AddSubmit(PermissionsBean permissionsBean) throws Exception;
	
	public PermissionsBean View(String number) throws Exception;
	
	public void UpdSubmit(PermissionsBean permissionsBean) throws Exception;
	
	public void Del(String number) throws Exception;
}
