package com.ivyinfo.communication.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ivyinfo.communication.bean.CommunicationBean;

public class CommunicationDAOImpl extends SqlMapClientDaoSupport implements CommunicationDAO{
	
	public List AllIndex(int from, int len, String logname,String order,String orderby) throws Exception{
		Map map = new HashMap();
		map.put("logname", logname);
		if(len != 0){
			map.put("from", from);
			map.put("len", len);
		}
		map.put("order", order);
		map.put("orderby", orderby);
		return this.getSqlMapClientTemplate().queryForList("findAllCommunication", map);
	}
	
	public void AddSubmit(CommunicationBean communicationBean) throws Exception{
		this.getSqlMapClientTemplate().insert("saveCommunication", communicationBean);
	}
	
	public CommunicationBean View(int id) throws Exception{
		Map map = new HashMap();
		map.put("id", id);
		return (CommunicationBean) this.getSqlMapClientTemplate().queryForObject("findAllCommunication", map);
	}
	
	public void UpdSubmit(CommunicationBean communicationBean) throws Exception{
		this.getSqlMapClientTemplate().update("updateCommunication", communicationBean);
	}
	
	public void Del(int id,String logname) throws Exception{
		Map map = new HashMap();
		map.put("id", id);
		map.put("logname", logname);
		this.getSqlMapClientTemplate().delete("deleteCommunication", map);
	}
	
	public void CommunicationTable(String logname) throws Exception{
		this.getSqlMapClientTemplate().update("CommunicationTable",logname);
	}
	
	public void DropCommunicationTable(String logname) throws Exception{
		this.getSqlMapClientTemplate().update("DropCommunicationTable",logname);
	}
}
