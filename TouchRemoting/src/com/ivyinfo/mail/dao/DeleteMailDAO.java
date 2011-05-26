package com.ivyinfo.mail.dao;

import java.util.List;

import com.ivyinfo.framework.service.base.IBaseDAO;
import com.ivyinfo.mail.bean.DeleteMailBean;
import com.ivyinfo.mail.bean.SendMailBean;

public interface DeleteMailDAO extends IBaseDAO{
	public void SaveDeleteMail(DeleteMailBean dmBean,String logname) throws Exception;
	
	public int DeleteListCount(String logname) throws Exception;
	
	public List DeleteList(int from, int len, String logname,String order,String orderby) throws Exception;
	
	public DeleteMailBean DeleteDetail(int id,String logname) throws Exception;
	
	public void DeleteRemove(int id,String logname) throws Exception;
	
	public void DeleteTable(String logname) throws Exception;
	
	public void DropDeleteTable(String logname) throws Exception;
}
