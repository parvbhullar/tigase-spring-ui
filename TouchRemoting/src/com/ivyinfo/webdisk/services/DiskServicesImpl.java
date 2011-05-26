package com.ivyinfo.webdisk.services;

import java.util.List;

import com.ivyinfo.webdisk.bean.DiskBean;
import com.ivyinfo.webdisk.dao.DiskDAO;
import com.ivyinfo.framework.service.server.SpringContextUtil;

public class DiskServicesImpl implements DiskServices{
	private DiskDAO diskDAO =(DiskDAO) SpringContextUtil.getBean("diskDAO");

	public List AllIndex(String guserid) throws Exception{
		return diskDAO.AllIndex(guserid);
	}
	
	public void AddSubmit(DiskBean diskBean) throws Exception{
		diskDAO.AddSubmit(diskBean);
	}
	
	public DiskBean View(int id) throws Exception{
		return diskDAO.View(id);
	}
	
	public void UpdSubmit(DiskBean diskBean) throws Exception{
		diskDAO.UpdSubmit(diskBean);
	}
	
	public void Del(int id) throws Exception{
		diskDAO.Del(id);
	}
}
