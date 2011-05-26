package com.ivyinfo.mail.dao;

import java.util.List;

import com.ivyinfo.framework.service.base.IBaseDAO;
import com.ivyinfo.mail.bean.ReceiveMailBean;

public interface ReceiveMailDAO extends IBaseDAO{
	public void SaveReceiveMail(ReceiveMailBean remBean) throws Exception;
	
	public int ReceiveListCount(String logname) throws Exception;
	
	public List ReceiveList(int from, int len, String logname,String order,String orderby) throws Exception;
	
	public ReceiveMailBean ReceiveDetail(int id, String logname) throws Exception;
	
	public void ReceiveSign(int id, String logname, String state) throws Exception;
	
	public void ReceiveRemove(int id, String logname) throws Exception;
	
	public void ReceiveTable(String logname) throws Exception;
	
	public void DropReceiveTable(String logname) throws Exception;
}
