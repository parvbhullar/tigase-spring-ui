package com.ivyinfo.communication.services;

import java.util.List;

import com.ivyinfo.communication.bean.CommunicationBean;
import com.ivyinfo.communication.dao.CommunicationDAO;
import com.ivyinfo.framework.service.server.SpringContextUtil;

public class CommunicationServicesImpl implements CommunicationServices{

	public List AllIndex(int from, int len, String logname,String order,String orderby) throws Exception{
		CommunicationDAO communicationDAO =(CommunicationDAO) SpringContextUtil.getBean("communicationDAO");
		return communicationDAO.AllIndex(from, len, logname, order, orderby);
	}
	
	public void AddSubmit(CommunicationBean communicationBean) throws Exception{
		CommunicationDAO communicationDAO =(CommunicationDAO) SpringContextUtil.getBean("communicationDAO");
		communicationDAO.AddSubmit(communicationBean);
	}
	
	public CommunicationBean View(int id) throws Exception{
		CommunicationDAO communicationDAO =(CommunicationDAO) SpringContextUtil.getBean("communicationDAO");
		return communicationDAO.View(id);
	}
	
	public void UpdSubmit(CommunicationBean communicationBean) throws Exception{
		CommunicationDAO communicationDAO =(CommunicationDAO) SpringContextUtil.getBean("communicationDAO");
		communicationDAO.UpdSubmit(communicationBean);
	}
	
	public void Del(int id,String logname) throws Exception{
		CommunicationDAO communicationDAO =(CommunicationDAO) SpringContextUtil.getBean("communicationDAO");
		communicationDAO.Del(id,logname);
	}
}
