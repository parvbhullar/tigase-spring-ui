package com.ivyinfo.dept.services;

import java.util.List;

import com.ivyinfo.dept.bean.DeptBean;
import com.ivyinfo.dept.dao.DeptDAO;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.test.dao.ITestDAO;

public class DeptServicesImpl implements DeptServices{
	private DeptDAO deptDAO =(DeptDAO) SpringContextUtil.getBean("deptDAO");

	public List AllIndex(int from, int len) throws Exception{
		return deptDAO.AllIndex(from, len);
	}
	
	public void AddSubmit(DeptBean deptBean) throws Exception{
		deptDAO.AddSubmit(deptBean);
	}
	
	public DeptBean View(int id) throws Exception{
		return deptDAO.View(id);
	}
	
	public void UpdSubmit(DeptBean deptBean) throws Exception{
		deptDAO.UpdSubmit(deptBean);
	}
	
	public void Del(int id) throws Exception{
		deptDAO.Del(id);
	}
}
