package com.ivyinfo.roleuserpurview.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ivyinfo.roleuserpurview.bean.RoleUserPurviewBean;

public class RoleUserPurviewDAOImpl extends SqlMapClientDaoSupport implements RoleUserPurviewDAO{
	
	public List AllIndex(int from, int len) throws Exception{
		Map map = new HashMap();
		String limit = String.valueOf(from) + "," + len;
		map.put("limit", limit);
		
		return this.getSqlMapClientTemplate().queryForList("findAllRoleUserPurview", map);
	}
	
	public void AddSubmit(RoleUserPurviewBean roleuserpurviewBean) throws Exception{
		this.getSqlMapClientTemplate().insert("saveRoleUserPurview", roleuserpurviewBean);
	}
	
	public RoleUserPurviewBean View(String roleid) throws Exception{
		Map map = new HashMap();
		map.put("roleid", roleid);
		return (RoleUserPurviewBean) this.getSqlMapClientTemplate().queryForObject("findAllRoleUserPurview", map);
	}
	
	public void UpdSubmit(RoleUserPurviewBean roleuserpurviewBean) throws Exception{
		this.getSqlMapClientTemplate().update("updateRoleUserPurview", roleuserpurviewBean);
	}
	
	public void Del(String id) throws Exception{
		this.getSqlMapClientTemplate().delete("deleteRoleUserPurview", id);
	}
}
