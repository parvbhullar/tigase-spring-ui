package com.ivyinfo.communication.dao;

import java.util.List;

import com.ivyinfo.communication.bean.CommunicationBean;
import com.ivyinfo.framework.service.base.IBaseDAO;

public interface CommunicationIMDAO extends IBaseDAO{
	public List AllIndexIM(String logname) throws Exception;
	
	public void AddSubmitIM(CommunicationBean communicationBean) throws Exception;
	
	public CommunicationBean ViewIM(int id,String logname) throws Exception;
	
	public void UpdSubmitIM(CommunicationBean communicationBean) throws Exception;
	
	public void DelIM(String id,String logname) throws Exception;
	
	public void CommunicationIMTable(String logname) throws Exception;
	
	public void DropCommunicationIMTable(String logname) throws Exception;
}
