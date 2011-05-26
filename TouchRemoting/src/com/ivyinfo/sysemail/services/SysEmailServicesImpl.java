package com.ivyinfo.sysemail.services;

import java.util.List;

import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.mail.util.SysMailUtil;
import com.ivyinfo.sysemail.bean.SysEmailBean;
import com.ivyinfo.sysemail.dao.SysEmailDAO;

public class SysEmailServicesImpl implements SysEmailServices{
	
	public int MailListCount() throws Exception{
		SysEmailDAO sysemailDAO =(SysEmailDAO) SpringContextUtil.getBean("sysemailDAO");
		return sysemailDAO.MailListCount();
	}
	
	public List AllIndex(int from, int len,String order,String orderby) throws Exception{
		SysEmailDAO sysemailDAO =(SysEmailDAO) SpringContextUtil.getBean("sysemailDAO");
		return sysemailDAO.AllIndex(from, len, order, orderby);
	}
	
	public SysEmailBean ViewSysMail(int id) throws Exception{
		SysEmailDAO sysemailDAO =(SysEmailDAO) SpringContextUtil.getBean("sysemailDAO");
		return sysemailDAO.ViewSysMail(id);
	}
	
	public void AddSysMail(SysEmailBean sysemailBean) throws Exception{
		SysEmailDAO sysemailDAO =(SysEmailDAO) SpringContextUtil.getBean("sysemailDAO");
		
		SysMailUtil sysmailUtil = new SysMailUtil();
		sysmailUtil.setEmailPort(sysemailBean);
		
		sysemailDAO.AddSysMail(sysemailBean);
	}
	
	public void UpdSysMail(SysEmailBean sysemailBean) throws Exception{
		SysEmailDAO sysemailDAO =(SysEmailDAO) SpringContextUtil.getBean("sysemailDAO");
		sysemailDAO.UpdSysMail(sysemailBean);
	}
	
	public void DelSysMail(int id) throws Exception{
		SysEmailDAO sysemailDAO =(SysEmailDAO) SpringContextUtil.getBean("sysemailDAO");
		sysemailDAO.DelSysMail(id);
	}
}
