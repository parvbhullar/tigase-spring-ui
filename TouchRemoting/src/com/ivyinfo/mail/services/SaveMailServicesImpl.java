package com.ivyinfo.mail.services;

import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.mail.bean.DeleteMailBean;
import com.ivyinfo.mail.bean.DraftMailBean;
import com.ivyinfo.mail.bean.ReceiveMailBean;
import com.ivyinfo.mail.bean.SendMailBean;
import com.ivyinfo.mail.dao.DeleteMailDAO;
import com.ivyinfo.mail.dao.DraftMailDAO;
import com.ivyinfo.mail.dao.ReceiveMailDAO;
import com.ivyinfo.mail.dao.SendMailDAO;
import com.ivyinfo.test.dao.ITestDAO;

public class SaveMailServicesImpl implements SaveMailServices{
	
	public void SaveSendMail(SendMailBean sendMailBean) throws Exception{
		SendMailDAO sendMailDAO =(SendMailDAO) SpringContextUtil.getBean("sendMailDAO");
		sendMailDAO.SaveSendMail(sendMailBean);
	}
	
	public void SaveDeleteMail(DeleteMailBean dmBean,String logname) throws Exception{
		DeleteMailDAO deleteMailDAO =(DeleteMailDAO) SpringContextUtil.getBean("deleteMailDAO");
		deleteMailDAO.SaveDeleteMail(dmBean,logname);
	}
	
	public void SaveReceiveMail(ReceiveMailBean receiveMailBean) throws Exception{
		ReceiveMailDAO receiveMailDAO =(ReceiveMailDAO) SpringContextUtil.getBean("receiveMailDAO");
		receiveMailDAO.SaveReceiveMail(receiveMailBean);
	}
	
	public void SaveDraftMail(DraftMailBean draftMailBean) throws Exception{
		DraftMailDAO draftMailDAO =(DraftMailDAO) SpringContextUtil.getBean("draftMailDAO");
		draftMailDAO.SaveDraftMail(draftMailBean);
	}
}
