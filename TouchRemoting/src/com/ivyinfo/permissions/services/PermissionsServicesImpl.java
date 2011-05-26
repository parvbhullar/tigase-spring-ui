package com.ivyinfo.permissions.services;

import java.util.List;

import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.permissions.bean.PermissionsBean;
import com.ivyinfo.permissions.dao.PermissionsDAO;
import com.ivyinfo.test.dao.ITestDAO;

public class PermissionsServicesImpl implements PermissionsServices{
	private PermissionsDAO permissionsDAO =(PermissionsDAO) SpringContextUtil.getBean("permissionsDAO");

	public List AllIndex() throws Exception{
		return permissionsDAO.AllIndex();
	}
	
	public void AddSubmit(PermissionsBean permissionsBean) throws Exception{
		permissionsDAO.AddSubmit(permissionsBean);
	}
	
	public PermissionsBean View(String number) throws Exception{
		return permissionsDAO.View(number);
	}
	
	public void UpdSubmit(PermissionsBean permissionsBean) throws Exception{
		permissionsDAO.UpdSubmit(permissionsBean);
	}
	
	public void Del(String number) throws Exception{
		permissionsDAO.Del(number);
	}
}
