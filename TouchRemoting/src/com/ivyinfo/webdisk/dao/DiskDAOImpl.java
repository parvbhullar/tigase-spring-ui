package com.ivyinfo.webdisk.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ivyinfo.webdisk.bean.DiskBean;

public class DiskDAOImpl extends SqlMapClientDaoSupport implements DiskDAO{
	
	public List AllIndex(String guserid) throws Exception{
		return this.getSqlMapClientTemplate().queryForList("findAllDisk",guserid);
	}
	
	public void AddSubmit(DiskBean diskBean) throws Exception{
		this.getSqlMapClientTemplate().insert("saveDisk", diskBean);
	}
	
	public DiskBean View(int id) throws Exception{
		return (DiskBean) this.getSqlMapClientTemplate().queryForObject("findDiskById", id);
	}
	
	public void UpdSubmit(DiskBean diskBean) throws Exception{
		this.getSqlMapClientTemplate().update("updateDisk", diskBean);
	}
	
	public void Del(int id) throws Exception{
		this.getSqlMapClientTemplate().delete("deleteDisk", id);
	}
	
	public void DiskTable(String logname) throws Exception{
		this.getSqlMapClientTemplate().update("DiskTable",logname);
		this.getSqlMapClientTemplate().update("DiskGroupTable",logname);
	}
	
	public void DropDiskTable(String logname) throws Exception{
		this.getSqlMapClientTemplate().update("DropDiskTable",logname);
		this.getSqlMapClientTemplate().update("DropDiskGroupTable",logname);
	}
}
