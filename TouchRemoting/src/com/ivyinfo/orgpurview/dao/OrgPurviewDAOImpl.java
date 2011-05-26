package com.ivyinfo.orgpurview.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ivyinfo.orgpurview.bean.OrgPurviewBean;

public class OrgPurviewDAOImpl extends SqlMapClientDaoSupport implements OrgPurviewDAO{
	
	public List getOrgPurview(String orgid) throws Exception{
		Map map = new HashMap();
		map.put("orgid", orgid);
		
		return this.getSqlMapClientTemplate().queryForList("findOrgPurview", map);
	}
	
	public void DelOrgPurview(String orgid) throws Exception{
		this.getSqlMapClientTemplate().delete("deleteOrgPurview", orgid);
	}
	
	public void SaveOrgPurview(OrgPurviewBean orgpurviewBean) throws Exception{
		this.getSqlMapClientTemplate().insert("saveOrgPurview", orgpurviewBean);
	}
}
