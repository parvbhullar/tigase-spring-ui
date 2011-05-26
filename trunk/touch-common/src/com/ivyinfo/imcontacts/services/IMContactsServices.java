package com.ivyinfo.imcontacts.services;

import java.util.List;

import com.ivyinfo.framework.service.base.IBaseService;
import com.ivyinfo.imcontacts.bean.IMContactsBean;

public interface IMContactsServices extends IBaseService{
	/**
	 * 新增IM通讯录
	 * nid
	 * uid
	 * pkey
	 * pval
	 * logname 用户登录名
	 * @param imcontactsBean
	 * @throws Exception
	 */
	public void AddIMContacts(IMContactsBean imcontactsBean) throws Exception;
	
	/**
	 * 删除IM通讯录
	 * nid
	 * pkey
	 * logname 用户登录名
	 * @param imcontactsBean
	 * @throws Exception
	 */
	public void DelIMContacts(IMContactsBean imcontactsBean) throws Exception;
	
	/**
	 * 查询IM通讯录
	 * nid
	 * pkey
	 * logname 用户登录名
	 * @param imcontactsBean
	 * @return
	 * @throws Exception
	 */
	public List getListIMContacts(IMContactsBean imcontactsBean) throws Exception;
}
