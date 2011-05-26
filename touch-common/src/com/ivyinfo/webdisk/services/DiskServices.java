package com.ivyinfo.webdisk.services;

import java.util.List;

import com.ivyinfo.webdisk.bean.DiskBean;
import com.ivyinfo.framework.service.base.IBaseService;

public interface DiskServices extends IBaseService{
	public List AllIndex(String guserid) throws Exception;
	
	public void AddSubmit(DiskBean diskBean) throws Exception;
	
	public DiskBean View(int id) throws Exception;
	
	public void UpdSubmit(DiskBean diskBean) throws Exception;
	
	public void Del(int id) throws Exception;
}
