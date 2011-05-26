package com.ivyinfo.sysemail.dao;

import java.util.List;

import com.ivyinfo.framework.service.base.IBaseDAO;
import com.ivyinfo.sysemail.bean.SysEmailBean;

public interface SysEmailDAO extends IBaseDAO{
	public int MailListCount() throws Exception;
	
	public List AllIndex(int from, int len,String order,String orderby) throws Exception;
	
	public SysEmailBean ViewSysMail(int id) throws Exception;
	
	public SysEmailBean ViewSysMailName(String name) throws Exception;
	
	public void AddSysMail(SysEmailBean sysemailBean) throws Exception;
	
	public void UpdSysMail(SysEmailBean sysemailBean) throws Exception;
	
	public void DelSysMail(int id) throws Exception;
}
