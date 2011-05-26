package com.ivyinfo.role.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ivyinfo.role.bean.RoleBean;

public class RoleDAOImpl extends SqlMapClientDaoSupport implements RoleDAO{
	
	public List AllIndex(int from, int len) throws Exception{
		Map map = new HashMap();
		String limit = String.valueOf(from) + "," + len;
		map.put("limit", limit);
		
		return this.getSqlMapClientTemplate().queryForList("findAllRole", map);
	}
	
	public void AddSubmit(RoleBean roleBean) throws Exception{
		this.getSqlMapClientTemplate().insert("saveRole", roleBean);
	}
	
	public RoleBean View(int id) throws Exception{
		Map map = new HashMap();
		map.put("id", id);
		return (RoleBean) this.getSqlMapClientTemplate().queryForObject("findAllRole", map);
	}
	
	public void UpdSubmit(RoleBean roleBean) throws Exception{
		this.getSqlMapClientTemplate().update("updateRole", roleBean);
	}
	
	public void Del(int id) throws Exception{
		this.getSqlMapClientTemplate().delete("deleteRole", id);
	}
}
