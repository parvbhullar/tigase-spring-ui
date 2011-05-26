package com.ivyinfo.dept.dao;

import java.util.List;

import com.ivyinfo.dept.bean.DeptBean;
import com.ivyinfo.framework.service.base.IBaseDAO;

public interface DeptDAO extends IBaseDAO{
	public List AllIndex(int from, int len) throws Exception;
	
	public void AddSubmit(DeptBean deptBean) throws Exception;
	
	public DeptBean View(int id) throws Exception;
	
	public void UpdSubmit(DeptBean deptBean) throws Exception;
	
	public void Del(int id) throws Exception;
}
