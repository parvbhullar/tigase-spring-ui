package com.ivyinfo.addressbook.dao;

import java.util.List;

import com.ivyinfo.addressbook.bean.AddressBookBean;
import com.ivyinfo.framework.service.base.IBaseDAO;

public interface AddressBookDAO extends IBaseDAO{
	public List AllIndex(String logname,String id) throws Exception;
	
	public List AllIndexCondition(String logname,String fieldname,String fieldvalue) throws Exception;
	
	public void AddSubmit(AddressBookBean addressBookBean) throws Exception;
	
//	public CommunicationBean View(int id) throws Exception;
	
	public void UpdSubmit(AddressBookBean addressBookBean) throws Exception;
	
	public void Del(String id,String logname) throws Exception;
//	
//	public void CommunicationTable(String logname) throws Exception;
//	
//	public void DropCommunicationTable(String logname) throws Exception;
}
