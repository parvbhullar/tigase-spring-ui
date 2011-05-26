package com.ivyinfo.immessage.dao;

import java.util.List;

import com.ivyinfo.framework.service.base.IBaseDAO;
import com.ivyinfo.immessage.bean.IMMessageBean;

public interface IMMessageDAO extends IBaseDAO{
	
	public void AddIMMessage(IMMessageBean imMessageBean) throws Exception;
	
	public void UpdIMMessage(IMMessageBean imMessageBean) throws Exception;
	
	public List AllIMMessage(String logname,String id) throws Exception;
	
	public List AllIMMessage_ReadORType(String logname,String fieldname,String value) throws Exception;
	
	public List AllIMMessage_ReadAndType(String logname,String typevalue,String readvalue) throws Exception;
}
