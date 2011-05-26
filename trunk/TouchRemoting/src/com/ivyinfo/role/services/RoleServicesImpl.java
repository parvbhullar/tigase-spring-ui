package com.ivyinfo.role.services;

import java.util.List;

import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.role.bean.RoleBean;
import com.ivyinfo.role.dao.RoleDAO;
import com.ivyinfo.test.dao.ITestDAO;

public class RoleServicesImpl implements RoleServices{
	private RoleDAO roleDAO =(RoleDAO) SpringContextUtil.getBean("roleDAO");

	public List AllIndex(int from, int len) throws Exception{
		return roleDAO.AllIndex(from, len);
	}
	
	public void AddSubmit(RoleBean roleBean) throws Exception{
		roleDAO.AddSubmit(roleBean);
	}
	
	public RoleBean View(int id) throws Exception{
		return roleDAO.View(id);
	}
	
	public void UpdSubmit(RoleBean roleBean) throws Exception{
		roleDAO.UpdSubmit(roleBean);
	}
	
	public void Del(int id) throws Exception{
		roleDAO.Del(id);
	}
}
