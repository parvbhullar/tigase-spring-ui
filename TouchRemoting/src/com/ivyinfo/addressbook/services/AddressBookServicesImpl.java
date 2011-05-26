package com.ivyinfo.addressbook.services;

import java.util.List;

import com.ivyinfo.addressbook.bean.AddressBookBean;
import com.ivyinfo.addressbook.dao.AddressBookDAO;
import com.ivyinfo.framework.service.server.SpringContextUtil;

public class AddressBookServicesImpl implements AddressBookServices{
	public List AllIndex(String logname,String id) throws Exception{
		AddressBookDAO addressBookDAO =(AddressBookDAO) SpringContextUtil.getBean("addressBookDAO");
		return addressBookDAO.AllIndex(logname, id);
	}
	
	public List AllIndexCondition(String logname,String fieldname,String fieldvalue) throws Exception{
		AddressBookDAO addressBookDAO =(AddressBookDAO) SpringContextUtil.getBean("addressBookDAO");
		return addressBookDAO.AllIndexCondition(logname, fieldname, fieldvalue);
	}
	
	public void AddSubmit(AddressBookBean addressBookBean) throws Exception{
		AddressBookDAO addressBookDAO =(AddressBookDAO) SpringContextUtil.getBean("addressBookDAO");
		addressBookDAO.AddSubmit(addressBookBean);
	}
	
	public void UpdSubmit(AddressBookBean addressBookBean) throws Exception{
		AddressBookDAO addressBookDAO =(AddressBookDAO) SpringContextUtil.getBean("addressBookDAO");
		addressBookDAO.UpdSubmit(addressBookBean);
	}
	
	public void Del(String id,String logname) throws Exception{
		AddressBookDAO addressBookDAO =(AddressBookDAO) SpringContextUtil.getBean("addressBookDAO");
		addressBookDAO.Del(id,logname);
	}
}
