package com.ivyinfo.mail.services;

import com.ivyinfo.framework.service.base.IBaseService;
import com.ivyinfo.mail.bean.DeleteMailBean;
import com.ivyinfo.mail.bean.DraftMailBean;
import com.ivyinfo.mail.bean.ReceiveMailBean;
import com.ivyinfo.mail.bean.SendMailBean;

public interface SaveMailServices extends IBaseService{
	public void SaveSendMail(SendMailBean sendMailBean) throws Exception;
	
	public void SaveDeleteMail(DeleteMailBean dmBean,String logname) throws Exception;
	
	public void SaveReceiveMail(ReceiveMailBean receiveMailBean) throws Exception;
	
	public void SaveDraftMail(DraftMailBean draftMailBean) throws Exception;
}
