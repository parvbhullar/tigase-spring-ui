package com.ivyinfo.role.dao;

import java.util.List;

import com.ivyinfo.role.bean.RoleBean;
import com.ivyinfo.framework.service.base.IBaseDAO;

public interface RoleDAO extends IBaseDAO{
	public List AllIndex(int from, int len) throws Exception;
	
	public void AddSubmit(RoleBean roleBean) throws Exception;
	
	public RoleBean View(int id) throws Exception;
	
	public void UpdSubmit(RoleBean roleBean) throws Exception;
	
	public void Del(int id) throws Exception;
}
