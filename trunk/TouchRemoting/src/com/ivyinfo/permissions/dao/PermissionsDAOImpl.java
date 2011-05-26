package com.ivyinfo.permissions.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ivyinfo.permissions.bean.PermissionsBean;

public class PermissionsDAOImpl extends SqlMapClientDaoSupport implements PermissionsDAO{
	
	public List AllIndex() throws Exception{
		return this.getSqlMapClientTemplate().queryForList("findAllPermissions");
	}
	
	public void AddSubmit(PermissionsBean permissionsBean) throws Exception{
		this.getSqlMapClientTemplate().insert("savePermissions", permissionsBean);
	}
	
	public PermissionsBean View(String number) throws Exception{
		Map map = new HashMap();
		map.put("number", number);
		return (PermissionsBean) this.getSqlMapClientTemplate().queryForObject("findAllPermissions", map);
	}
	
	public void UpdSubmit(PermissionsBean permissionsBean) throws Exception{
		this.getSqlMapClientTemplate().update("updatePermissions", permissionsBean);
	}
	
	public void Del(String number) throws Exception{
		this.getSqlMapClientTemplate().delete("deletePermissions", number);
	}
}
