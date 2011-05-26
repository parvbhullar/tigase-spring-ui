package com.ivyinfo.mail.services;

import com.ivyinfo.framework.service.base.IBaseService;
import com.ivyinfo.mail.bean.ReceiveMailBean;
import com.ivyinfo.mail.bean.ReturnMailBean;
import com.ivyinfo.mail.bean.SetupMailBean;

public interface MailApiServices extends IBaseService{
	public ReturnMailBean getAllMailCount(SetupMailBean SetupMailBean,String userlogname) throws Exception;
	
//	public void getEmailList(ReceiveMailBean remBean,SetupMailBean SetupMailBean,String mailid,String mailNo) throws Exception;
	
	public void getEmailList_ftp(ReceiveMailBean remBean,SetupMailBean SetupMailBean,String mailid,String mailNo) throws Exception;
	
	public void deleteMail(SetupMailBean SetupMailBean, ReceiveMailBean MailBean) throws Exception;
}
