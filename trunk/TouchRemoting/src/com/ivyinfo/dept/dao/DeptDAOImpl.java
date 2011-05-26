package com.ivyinfo.dept.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ivyinfo.dept.bean.DeptBean;

public class DeptDAOImpl extends SqlMapClientDaoSupport implements DeptDAO{
	
	public List AllIndex(int from, int len) throws Exception{
		Map map = new HashMap();
		String limit = String.valueOf(from) + "," + len;
		map.put("limit", limit);
		
		return this.getSqlMapClientTemplate().queryForList("findAllDept", map);
	}
	
	public void AddSubmit(DeptBean deptBean) throws Exception{
		this.getSqlMapClientTemplate().insert("saveDept", deptBean);
	}
	
	public DeptBean View(int id) throws Exception{
		Map map = new HashMap();
		map.put("id", id);
		return (DeptBean) this.getSqlMapClientTemplate().queryForObject("findAllDept", map);
	}
	
	public void UpdSubmit(DeptBean deptBean) throws Exception{
		this.getSqlMapClientTemplate().update("updateDept", deptBean);
	}
	
	public void Del(int id) throws Exception{
		this.getSqlMapClientTemplate().delete("deleteDept", id);
	}
}
