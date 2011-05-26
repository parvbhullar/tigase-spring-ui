package com.ivyinfo.orgpurview.services;

import java.util.List;

import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.orgpurview.bean.OrgPurviewBean;
import com.ivyinfo.orgpurview.dao.OrgPurviewDAO;

public class OrgPurviewServicesImpl implements OrgPurviewServices{
	private OrgPurviewDAO orgpurviewDAO =(OrgPurviewDAO) SpringContextUtil.getBean("orgpurviewDAO");
	
	public List getOrgPurview(String orgid) throws Exception{
		return orgpurviewDAO.getOrgPurview(orgid);
	}
	
	public void DelOrgPurview(String orgid) throws Exception{
		orgpurviewDAO.DelOrgPurview(orgid);
	}
	
	public void SaveOrgPurview(OrgPurviewBean orgpurviewBean) throws Exception{
		orgpurviewDAO.SaveOrgPurview(orgpurviewBean);
	}
}
