package com.ivyinfo.orgpurview.services;

import java.util.List;

import com.ivyinfo.framework.service.base.IBaseService;
import com.ivyinfo.orgpurview.bean.OrgPurviewBean;

public interface OrgPurviewServices extends IBaseService{
	/**
	 * 根据机构id获取对应的权限信息
	 * @param orgid
	 * @return
	 * @throws Exception
	 */
	public List getOrgPurview(String orgid) throws Exception;
	
	/**
	 * 根据机构id删除机构的所有权限
	 * @param orgid
	 * @throws Exception
	 */
	public void DelOrgPurview(String orgid) throws Exception;
	
	/**
	 * 新增机构权限信息
	 * @param orgpurviewBean
	 * @throws Exception
	 */
	public void SaveOrgPurview(OrgPurviewBean orgpurviewBean) throws Exception;
}
