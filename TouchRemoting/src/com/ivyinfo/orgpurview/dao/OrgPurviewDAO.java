package com.ivyinfo.orgpurview.dao;

import java.util.List;

import com.ivyinfo.framework.service.base.IBaseDAO;
import com.ivyinfo.orgpurview.bean.OrgPurviewBean;

public interface OrgPurviewDAO extends IBaseDAO{
	public List getOrgPurview(String orgid) throws Exception;
	
	public void DelOrgPurview(String orgid) throws Exception;
	
	public void SaveOrgPurview(OrgPurviewBean orgpurviewBean) throws Exception;
}
