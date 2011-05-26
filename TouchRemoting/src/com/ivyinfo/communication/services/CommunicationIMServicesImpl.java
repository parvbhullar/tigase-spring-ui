package com.ivyinfo.communication.services;

import java.util.List;

import com.ivyinfo.communication.bean.CommunicationBean;
import com.ivyinfo.communication.dao.CommunicationDAO;
import com.ivyinfo.communication.dao.CommunicationIMDAO;
import com.ivyinfo.framework.service.server.SpringContextUtil;

public class CommunicationIMServicesImpl implements CommunicationIMServices{

	public List AllIndexIM(String logname) throws Exception{
		CommunicationIMDAO communicationimDAO =(CommunicationIMDAO) SpringContextUtil.getBean("communicationimDAO");
		return communicationimDAO.AllIndexIM(logname);
	}
	
	public void AddSubmitIM(CommunicationBean communicationBean) throws Exception{
		CommunicationIMDAO communicationimDAO =(CommunicationIMDAO) SpringContextUtil.getBean("communicationimDAO");
		communicationimDAO.AddSubmitIM(communicationBean);
	}
	
	public CommunicationBean ViewIM(int id,String logname) throws Exception{
		CommunicationIMDAO communicationimDAO =(CommunicationIMDAO) SpringContextUtil.getBean("communicationimDAO");
		return communicationimDAO.ViewIM(id,logname);
	}
	
	public void UpdSubmitIM(CommunicationBean communicationBean) throws Exception{
		CommunicationIMDAO communicationimDAO =(CommunicationIMDAO) SpringContextUtil.getBean("communicationimDAO");
		communicationimDAO.UpdSubmitIM(communicationBean);
	}
	
	public void DelIM(String id,String logname) throws Exception{
		CommunicationIMDAO communicationimDAO =(CommunicationIMDAO) SpringContextUtil.getBean("communicationimDAO");
		communicationimDAO.DelIM(id,logname);
	}
}
