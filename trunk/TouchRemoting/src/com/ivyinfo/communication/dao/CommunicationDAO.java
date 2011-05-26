package com.ivyinfo.communication.dao;

import java.util.List;

import com.ivyinfo.communication.bean.CommunicationBean;
import com.ivyinfo.framework.service.base.IBaseDAO;

public interface CommunicationDAO extends IBaseDAO{
	public List AllIndex(int from, int len, String logname,String order,String orderby) throws Exception;
	
	public void AddSubmit(CommunicationBean communicationBean) throws Exception;
	
	public CommunicationBean View(int id) throws Exception;
	
	public void UpdSubmit(CommunicationBean communicationBean) throws Exception;
	
	public void Del(int id,String logname) throws Exception;
	
	public void CommunicationTable(String logname) throws Exception;
	
	public void DropCommunicationTable(String logname) throws Exception;
}
