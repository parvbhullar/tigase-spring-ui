package com.ivyinfo.mail.services;

import com.ivyinfo.framework.service.base.IBaseService;
import com.ivyinfo.mail.bean.MailUtilBean;

public interface SendMailServices extends IBaseService{
	public String SendMail(MailUtilBean MailUtilBean) throws Exception;
}
