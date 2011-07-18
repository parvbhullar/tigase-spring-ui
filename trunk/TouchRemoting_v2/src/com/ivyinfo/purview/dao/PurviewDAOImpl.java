package com.ivyinfo.purview.dao;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ivyinfo.user.bean.UserBean;

public class PurviewDAOImpl extends SqlMapClientDaoSupport implements PurviewDAO{
	
	public UserBean ValidationLogin(String logname) throws Exception{
		return (UserBean) this.getSqlMapClientTemplate().queryForObject("findUserLoginById", logname);
	}
}
