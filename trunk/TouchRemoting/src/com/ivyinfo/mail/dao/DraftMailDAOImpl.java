package com.ivyinfo.mail.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ivyinfo.mail.bean.DraftMailBean;

public class DraftMailDAOImpl extends SqlMapClientDaoSupport implements DraftMailDAO{
	
	public void SaveDraftMail(DraftMailBean draftBean) throws Exception{
		String logname = draftBean.getLogname();
		if(logname.indexOf("_t_mail_draft")>-1){
			
		}else{
			draftBean.setLogname(logname+"_t_mail_draft");
		}
		this.getSqlMapClientTemplate().insert("saveDraftMail", draftBean);
	}
	
	public int DraftListCount(String logname) throws Exception{
		Map map = new HashMap();
		map.put("logname", logname+"_t_mail_draft");
		String s = (String)this.getSqlMapClientTemplate().queryForObject("draftcount",map);
		return Integer.parseInt(s);
	}
	
	public List DraftList(int from, int len, String logname,String order,String orderby) throws Exception{
		Map map = new HashMap();
		map.put("from", from);
		map.put("len", len);
		map.put("logname", logname+"_t_mail_draft");
		map.put("order", order);
		map.put("orderby", orderby);
		return this.getSqlMapClientTemplate().queryForList("findAllDraft", map);
	}
	
	public DraftMailBean DraftDetail(int id,String logname) throws Exception{
		Map map = new HashMap();
		map.put("id", id);
		map.put("logname", logname+"_t_mail_draft");
		return (DraftMailBean) this.getSqlMapClientTemplate().queryForObject(
				"findAllDraft", map);
	}
	
	public void DraftRemove(int id,String logname) throws Exception{
		Map map = new HashMap();
		map.put("id", id);
		map.put("logname", logname+"_t_mail_draft");
		this.getSqlMapClientTemplate().delete("draftRemove", map);
	}
	
	public void DraftTable(String logname) throws Exception{
		this.getSqlMapClientTemplate().update("DraftTable",logname);
	}
	
	public void DropDraftTable(String logname) throws Exception{
		this.getSqlMapClientTemplate().update("DropDraftTable",logname);
	}
}
