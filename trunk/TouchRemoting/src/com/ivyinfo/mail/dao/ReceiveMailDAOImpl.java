package com.ivyinfo.mail.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ivyinfo.mail.bean.ReceiveMailBean;

public class ReceiveMailDAOImpl extends SqlMapClientDaoSupport implements ReceiveMailDAO{
	
	public void SaveReceiveMail(ReceiveMailBean remBean) throws Exception{
		this.getSqlMapClientTemplate().insert("saveReceiveMail", remBean);
	}
	
	public int ReceiveListCount(String logname) throws Exception{
		Map map = new HashMap();
		map.put("logname", logname+"_t_mail_receive");
		String s = (String)this.getSqlMapClientTemplate().queryForObject("receivecount",map);
		return Integer.parseInt(s);
	}
	
	public List ReceiveList(int from, int len, String logname,String order,String orderby) throws Exception{
		Map map = new HashMap();
		map.put("logname", logname+"_t_mail_receive");
		map.put("from", from);
		map.put("len", len);
		map.put("order", order);
		map.put("orderby", orderby);
		
		return this.getSqlMapClientTemplate().queryForList("findAllReceive",map);
	}
	
	public ReceiveMailBean ReceiveDetail(int id, String logname) throws Exception{
		Map map = new HashMap();
		map.put("logname", logname+"_t_mail_receive");
		map.put("id", id);
		return (ReceiveMailBean) this.getSqlMapClientTemplate().queryForObject(
				"findAllReceive", map);
	}
	
	public void ReceiveSign(int id, String logname, String state) throws Exception{
		Map map = new HashMap();
		map.put("logname", logname+"_t_mail_receive");
		map.put("id", id);
		map.put("state", state);
		this.getSqlMapClientTemplate().update("findReceiveSign", map);
	}
	
	public void ReceiveRemove(int id, String logname) throws Exception{
		Map map = new HashMap();
		map.put("logname", logname+"_t_mail_receive");
		map.put("id", id);
		this.getSqlMapClientTemplate().delete("receiveRemove", map);
	}
	
	public void ReceiveTable(String logname) throws Exception{
		this.getSqlMapClientTemplate().update("ReceiveTable",logname);
	}
	
	public void DropReceiveTable(String logname) throws Exception{
		this.getSqlMapClientTemplate().update("DropReceiveTable",logname);
	}
}
