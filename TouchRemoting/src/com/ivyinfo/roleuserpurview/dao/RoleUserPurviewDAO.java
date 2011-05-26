package com.ivyinfo.roleuserpurview.dao;

import java.util.List;

import com.ivyinfo.framework.service.base.IBaseDAO;
import com.ivyinfo.roleuserpurview.bean.RoleUserPurviewBean;

public interface RoleUserPurviewDAO extends IBaseDAO{
	public List AllIndex(int from, int len) throws Exception;
	
	public void AddSubmit(RoleUserPurviewBean roleuserpurviewBean) throws Exception;
	
	public RoleUserPurviewBean View(String roleid) throws Exception;
	
	public void UpdSubmit(RoleUserPurviewBean roleuserpurviewBean) throws Exception;
	
	public void Del(String id) throws Exception;
}
