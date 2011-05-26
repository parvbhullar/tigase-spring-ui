package com.ivyinfo.roleuserpurview.services;

import java.util.List;

import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.roleuserpurview.bean.RoleUserPurviewBean;
import com.ivyinfo.roleuserpurview.dao.RoleUserPurviewDAO;
import com.ivyinfo.test.dao.ITestDAO;

public class RoleUserPurviewServicesImpl implements RoleUserPurviewServices{
	private RoleUserPurviewDAO roleuserpurviewDAO =(RoleUserPurviewDAO) SpringContextUtil.getBean("roleuserpurviewDAO");

	public List AllIndex(int from, int len) throws Exception{
		return roleuserpurviewDAO.AllIndex(from, len);
	}
	
	public void AddSubmit(RoleUserPurviewBean roleuserpurviewBean) throws Exception{
		roleuserpurviewDAO.AddSubmit(roleuserpurviewBean);
	}
	
	public RoleUserPurviewBean View(String roleid) throws Exception{
		return roleuserpurviewDAO.View(roleid);
	}
	
	public void UpdSubmit(RoleUserPurviewBean roleuserpurviewBean) throws Exception{
		roleuserpurviewDAO.UpdSubmit(roleuserpurviewBean);
	}
	
	public void Del(String id) throws Exception{
		roleuserpurviewDAO.Del(id);
	}
}
