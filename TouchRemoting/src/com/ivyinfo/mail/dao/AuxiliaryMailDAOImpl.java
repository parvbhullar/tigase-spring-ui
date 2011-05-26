package com.ivyinfo.mail.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ivyinfo.mail.bean.FileBean;
import com.ivyinfo.mail.bean.ReceiveMailBean;
import com.ivyinfo.mail.bean.SetupMailBean;
import com.ivyinfo.test.bean.TestBean;

public class AuxiliaryMailDAOImpl extends SqlMapClientDaoSupport implements AuxiliaryMailDAO{
	
	public String AuxiliaryMail(String userlogname,String uid,String msgno) throws Exception{
		Map map = new HashMap();
		map.put("userlogname", userlogname+"_t_mail_receive");
		map.put("uid", "'"+uid+"'");
		map.put("msgno", msgno);
		String count = (String)this.getSqlMapClientTemplate().queryForObject("findcount",map);
		return count;
	}
	
	public void SaveFile(FileBean fileBean) throws Exception{
		fileBean.setLogname(fileBean.getLogname()+"_t_mail_file");
		this.getSqlMapClientTemplate().insert("savefile", fileBean);
	}
	
	public List AllFile(String mailid,String logname) throws Exception{
		Map map = new HashMap();
		map.put("userlogname", logname+"_t_mail_file");
		map.put("mailid", mailid);
		return this.getSqlMapClientTemplate().queryForList("findAllFile",map);
	}
	
	public List AllMailSetup(String logname) throws Exception{
		Map map = new HashMap();
		map.put("logname", logname);
		return this.getSqlMapClientTemplate().queryForList("findMailSetup",map);
	}
	
	public SetupMailBean ViewMailSetupEmail(String email,String logname) throws Exception{
		Map map = new HashMap();
		map.put("addname", email);
		map.put("logname", logname);
		return (SetupMailBean) this.getSqlMapClientTemplate().queryForObject("findMailSetup", map);
	}
	
	public void AddMailSetup(SetupMailBean setupmailBean) throws Exception{
		this.getSqlMapClientTemplate().insert("saveMailSetup", setupmailBean);
	}
	
	public void UpdMailSetup(SetupMailBean setupmailBean) throws Exception{
		this.getSqlMapClientTemplate().update("updateMailSetup", setupmailBean);
	}
	
	public void UpdUserMailSetup(SetupMailBean setupmailBean) throws Exception{
		this.getSqlMapClientTemplate().update("updateUserMailSetup", setupmailBean);
	}
	
	public void DelMailSetup(int id,String logname) throws Exception{
		Map map = new HashMap();
		map.put("id", id);
		map.put("logname", logname);
		this.getSqlMapClientTemplate().delete("DelMailSetup", map);
	}
	
	public void FileTable(String logname) throws Exception{
		this.getSqlMapClientTemplate().update("FileTable",logname);
	}
	
	public void DropFileTable(String logname) throws Exception{
		this.getSqlMapClientTemplate().update("DropFileTable",logname);
	}
	
	public void MailSetupTable(String logname) throws Exception{
		this.getSqlMapClientTemplate().update("MailSetupTable",logname);
	}
	
	public void DropMailSetupTable(String logname) throws Exception{
		this.getSqlMapClientTemplate().update("DropMailSetupTable",logname);
	}
}
