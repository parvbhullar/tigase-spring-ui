package com.ivyinfo.imcontacts.dao;

import java.util.List;

import com.ivyinfo.framework.service.base.IBaseDAO;
import com.ivyinfo.imcontacts.bean.IMContactsBean;

public interface IMContactsDAO extends IBaseDAO{
	public void AddIMContacts(IMContactsBean imcontactsBean) throws Exception;
	
	public void DelIMContacts(String id,String logname) throws Exception;
	
	public List getListIMContacts(IMContactsBean imcontactsBean) throws Exception;
}
