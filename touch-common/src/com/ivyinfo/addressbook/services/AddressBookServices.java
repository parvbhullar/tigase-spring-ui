package com.ivyinfo.addressbook.services;

import java.util.List;

import com.ivyinfo.addressbook.bean.AddressBookBean;
import com.ivyinfo.framework.service.base.IBaseService;

public interface AddressBookServices extends IBaseService{
	/**
	 * 根据id查询所有信息，或者详细信息
	 * @param logname 登陆人
	 * @param id 一条记录的id
	 * @return 根据返回的list得到总记录数
	 * @throws Exception
	 */
	public List AllIndex(String logname,String id) throws Exception;
	
	/**
	 * 根据一个条件查询通讯录所有信息
	 * @param logname
	 * @param fieldname
	 * @param fieldvalue
	 * @return
	 * @throws Exception
	 */
	public List AllIndexCondition(String logname,String fieldname,String fieldvalue) throws Exception;
	
	public void AddSubmit(AddressBookBean addressBookBean) throws Exception;
	
//	public CommunicationBean View(int id) throws Exception;
	
	public void UpdSubmit(AddressBookBean AaddressBookBean) throws Exception;
	
	public void Del(String id,String logname) throws Exception;
}
