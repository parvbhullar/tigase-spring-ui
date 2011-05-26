package com.ivyinfo.mail.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ivyinfo.mail.bean.DeleteMailBean;
import com.ivyinfo.mail.bean.SendMailBean;

public class DeleteMailDAOImpl extends SqlMapClientDaoSupport implements DeleteMailDAO{
	
	public void SaveDeleteMail(DeleteMailBean dmBean,String logname) throws Exception{
		dmBean.setLogname(logname+"_t_mail_delete");
		this.getSqlMapClientTemplate().insert("savedeleteMail", dmBean);
	}
	
	public int DeleteListCount(String logname) throws Exception{
		Map map = new HashMap();
		map.put("logname", logname+"_t_mail_delete");
		String s = (String)this.getSqlMapClientTemplate().queryForObject("deletecount",map);
		return Integer.parseInt(s);
	}
	
	public List DeleteList(int from, int len, String logname,String order,String orderby) throws Exception{
		Map map = new HashMap();
		map.put("from", from);
		map.put("len", len);
		map.put("logname", logname+"_t_mail_delete");
		map.put("order", order);
		map.put("orderby", orderby);
		return this.getSqlMapClientTemplate().queryForList("findAllDelete", map);
	}
	
	public DeleteMailBean DeleteDetail(int id,String logname) throws Exception{
		Map map = new HashMap();
		map.put("id", id);
		map.put("logname", logname+"_t_mail_delete");
		return (DeleteMailBean) this.getSqlMapClientTemplate().queryForObject(
				"findAllDelete", map);
	}
	
	public void DeleteRemove(int id,String logname) throws Exception{
		Map map = new HashMap();
		map.put("id", id);
		map.put("logname", logname+"_t_mail_delete");
		this.getSqlMapClientTemplate().delete("deleteRemove", map);
	}
	
	public void DeleteTable(String logname) throws Exception{
		this.getSqlMapClientTemplate().update("DeleteTable",logname);
	}
	
	public void DropDeleteTable(String logname) throws Exception{
		this.getSqlMapClientTemplate().update("DropDeleteTable",logname);
	}
}
