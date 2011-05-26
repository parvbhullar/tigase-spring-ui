package com.ivyinfo.user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ivyinfo.user.bean.UserPreBean;

public class UserPreDAOImpl extends SqlMapClientDaoSupport implements UserPreDAO{
	
	public List UserPreList(String logname,String keyword) throws Exception{
		Map map = new HashMap();
		map.put("logname", logname);
		if(keyword != null || !"".equals(keyword)){
			map.put("keyword", keyword);
		}
		return this.getSqlMapClientTemplate().queryForList("UserPreList", map);
	}
	
	public void UserPreAdd(UserPreBean userPreBean) throws Exception{
		this.getSqlMapClientTemplate().insert("UserPreAdd", userPreBean);
	}
	
	public void UserPreUpd(UserPreBean userPreBean) throws Exception{
		this.getSqlMapClientTemplate().update("UserPreUpd", userPreBean);
	}
}
