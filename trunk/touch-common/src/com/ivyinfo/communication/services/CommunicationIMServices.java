package com.ivyinfo.communication.services;

import java.util.List;

import com.ivyinfo.communication.bean.CommunicationBean;
import com.ivyinfo.framework.service.base.IBaseService;

public interface CommunicationIMServices extends IBaseService{
	public List AllIndexIM(String logname) throws Exception;
	
	public void AddSubmitIM(CommunicationBean communicationBean) throws Exception;
	
//	public CommunicationBean ViewIM(int id,String logname) throws Exception;
	
	public void UpdSubmitIM(CommunicationBean communicationBean) throws Exception;
	
	public void DelIM(String id,String logname) throws Exception;
}
