package com.ivyinfo.user.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ivyinfo.user.bean.UserCardRecycleBean;

public class UserCardRecycleDAOImpl extends SqlMapClientDaoSupport implements UserCardRecycleDAO{
	
	public List getListUserCardRecycle(String logname) throws Exception{
		HashMap map = new HashMap();
		if(logname != null && !"".equals(logname)){
			map.put("logname", logname);
		}
		return this.getSqlMapClientTemplate().queryForList("getListUserCardRecycle", map);
	}
	
	public void AddUserCardRecycle(UserCardRecycleBean userCardRecycleBean) throws Exception{
		this.getSqlMapClientTemplate().insert("AddUserCardRecycle", userCardRecycleBean);
	}
	
	public void DelUserCardRecycle(String cardnumber) throws Exception{
		this.getSqlMapClientTemplate().delete("DelUserCardRecycle", cardnumber);
	}
}
