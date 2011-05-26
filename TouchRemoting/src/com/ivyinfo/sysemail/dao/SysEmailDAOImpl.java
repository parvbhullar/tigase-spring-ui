package com.ivyinfo.sysemail.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ivyinfo.sysemail.bean.SysEmailBean;

public class SysEmailDAOImpl extends SqlMapClientDaoSupport implements SysEmailDAO{
	
	public int MailListCount() throws Exception{
		String s = (String)this.getSqlMapClientTemplate().queryForObject("SysMailCount");
		return Integer.parseInt(s);
	}
	
	public List AllIndex(int from, int len,String order,String orderby) throws Exception{
		Map map = new HashMap();
		map.put("order", order);
		map.put("orderby", orderby);
		if(len != 0){
			map.put("from", from);
			map.put("len", len);
		}
		return this.getSqlMapClientTemplate().queryForList("findAllSysMail", map);
	}
	
	public SysEmailBean ViewSysMail(int id) throws Exception{
		Map map = new HashMap();
		map.put("id", id);
		return (SysEmailBean) this.getSqlMapClientTemplate().queryForObject("findAllSysMail", map);
	}
	
	public SysEmailBean ViewSysMailName(String name) throws Exception{
		Map map = new HashMap();
		map.put("name", name);
		return (SysEmailBean) this.getSqlMapClientTemplate().queryForObject("findAllSysMail", map);
	}
	
	public void AddSysMail(SysEmailBean sysemailBean) throws Exception{
		this.getSqlMapClientTemplate().insert("saveSysMail", sysemailBean);
	}
	
	public void UpdSysMail(SysEmailBean sysemailBean) throws Exception{
		this.getSqlMapClientTemplate().update("updateSysMail", sysemailBean);
	}
	
	public void DelSysMail(int id) throws Exception{
		this.getSqlMapClientTemplate().delete("deleteSysMail", id);
	}
}
