package com.ivyinfo.role.services;

import java.util.List;

import com.ivyinfo.role.bean.RoleBean;
import com.ivyinfo.framework.service.base.IBaseService;

public interface RoleServices extends IBaseService{
	public List AllIndex(int from, int len) throws Exception;
	
	public void AddSubmit(RoleBean roleBean) throws Exception;
	
	public RoleBean View(int id) throws Exception;
	
	public void UpdSubmit(RoleBean roleBean) throws Exception;
	
	public void Del(int id) throws Exception;
}
