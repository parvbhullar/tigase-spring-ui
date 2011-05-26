package com.ivyinfo.webdisk.dao;

import java.util.List;

import com.ivyinfo.webdisk.bean.DiskBean;
import com.ivyinfo.framework.service.base.IBaseDAO;

public interface DiskDAO extends IBaseDAO{
	public List AllIndex(String guserid) throws Exception;
	
	public void AddSubmit(DiskBean diskBean) throws Exception;
	
	public DiskBean View(int id) throws Exception;
	
	public void UpdSubmit(DiskBean diskBean) throws Exception;
	
	public void Del(int id) throws Exception;
	
	public void DiskTable(String logname) throws Exception;
	
	public void DropDiskTable(String logname) throws Exception;
}
