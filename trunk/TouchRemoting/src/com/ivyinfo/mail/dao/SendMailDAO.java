package com.ivyinfo.mail.dao;

import java.util.List;

import com.ivyinfo.framework.service.base.IBaseDAO;
import com.ivyinfo.mail.bean.ReceiveMailBean;
import com.ivyinfo.mail.bean.SendMailBean;

public interface SendMailDAO extends IBaseDAO{
	public void SaveSendMail(SendMailBean sendMailBean) throws Exception;
	
	public int SendListCount(String logname) throws Exception;
	
	public List SendList(int from, int len, String logname,String order,String orderby) throws Exception;
	
	public SendMailBean SendDetail(int id,String logname) throws Exception;
	
	public void SendRemove(int id,String logname) throws Exception;
	
	public void SendTable(String logname) throws Exception;
	
	public void DropSendTable(String logname) throws Exception;
}
