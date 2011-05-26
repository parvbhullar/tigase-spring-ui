package com.ivyinfo.mail.dao;

import java.util.List;

import com.ivyinfo.framework.service.base.IBaseDAO;
import com.ivyinfo.mail.bean.DraftMailBean;

public interface DraftMailDAO extends IBaseDAO{
	public void SaveDraftMail(DraftMailBean draftBean) throws Exception;
	
	public int DraftListCount(String logname) throws Exception;
	
	public List DraftList(int from, int len, String logname,String order,String orderby) throws Exception;
	
	public DraftMailBean DraftDetail(int id,String logname) throws Exception;
	
	public void DraftRemove(int id,String logname) throws Exception;
	
	public void DraftTable(String logname) throws Exception;
	
	public void DropDraftTable(String logname) throws Exception;
}
