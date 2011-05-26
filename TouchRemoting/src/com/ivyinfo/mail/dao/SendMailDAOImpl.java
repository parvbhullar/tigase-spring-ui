package com.ivyinfo.mail.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ivyinfo.mail.bean.ReceiveMailBean;
import com.ivyinfo.mail.bean.SendMailBean;

public class SendMailDAOImpl extends SqlMapClientDaoSupport implements SendMailDAO{
	
	public void SaveSendMail(SendMailBean sendMailBean) throws Exception{
		String logname = sendMailBean.getLogname();
		if(logname.indexOf("_t_mail_send")>-1){
		}else{
			sendMailBean.setLogname(logname+"_t_mail_send");
		}
		this.getSqlMapClientTemplate().insert("saveSendMail", sendMailBean);
	}
	
	public int SendListCount(String logname) throws Exception{
		Map map = new HashMap();
		map.put("logname", logname+"_t_mail_send");
		String s = (String)this.getSqlMapClientTemplate().queryForObject("sendcount",map);
		return Integer.parseInt(s);
	}
	
	public List SendList(int from, int len, String logname,String order,String orderby) throws Exception{
		Map map = new HashMap();
		map.put("from", from);
		map.put("len", len);
		map.put("logname", logname+"_t_mail_send");
		map.put("order", order);
		map.put("orderby", orderby);
		return this.getSqlMapClientTemplate().queryForList("findAllSend", map);
	}
	
	public SendMailBean SendDetail(int id,String logname) throws Exception{
		Map map = new HashMap();
		map.put("id", id);
		map.put("logname", logname+"_t_mail_send");
		return (SendMailBean) this.getSqlMapClientTemplate().queryForObject(
				"findAllSend", map);
	}
	
	public void SendRemove(int id,String logname) throws Exception{
		Map map = new HashMap();
		map.put("id", id);
		map.put("logname", logname+"_t_mail_send");
		this.getSqlMapClientTemplate().delete("sendRemove", map);
	}
	
	public void SendTable(String logname) throws Exception{
		this.getSqlMapClientTemplate().update("SendTable",logname);
	}
	
	public void DropSendTable(String logname) throws Exception{
		this.getSqlMapClientTemplate().update("DropSendTable",logname);
	}
}
