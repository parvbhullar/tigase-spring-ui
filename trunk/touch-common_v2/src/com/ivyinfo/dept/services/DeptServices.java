package com.ivyinfo.dept.services;

import java.util.List;

import com.ivyinfo.dept.bean.DeptBean;
import com.ivyinfo.framework.service.base.IBaseService;
import com.ivyinfo.organization.bean.OrganizationBean;

public interface DeptServices extends IBaseService{
	public List AllIndex(int from, int len) throws Exception;
	
	public void AddSubmit(DeptBean deptBean) throws Exception;
	
	public DeptBean View(int id) throws Exception;
	
	public void UpdSubmit(DeptBean deptBean) throws Exception;
	
	public void Del(int id) throws Exception;
}
