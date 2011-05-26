package com.ivyinfo.mail.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ivyinfo.framework.service.base.IBaseDAO;
import com.ivyinfo.mail.bean.FileBean;
import com.ivyinfo.mail.bean.ReceiveMailBean;
import com.ivyinfo.mail.bean.SetupMailBean;

public interface AuxiliaryMailDAO extends IBaseDAO{
	public String AuxiliaryMail(String userlogname,String uid,String msgno) throws Exception;
	
	public void SaveFile(FileBean fileBean) throws Exception;
	
	public List AllFile(String mailid,String logname) throws Exception;
	
	public void FileTable(String logname) throws Exception;
	
	public void DropFileTable(String logname) throws Exception;
	
	public void MailSetupTable(String logname) throws Exception;
	
	public void DropMailSetupTable(String logname) throws Exception;
	
//	public List AllMailSetup(String logname) throws Exception;
//	
//	public SetupMailBean ViewMailSetupEmail(String email,String logname) throws Exception;
//	
//	public void AddMailSetup(SetupMailBean setupmailBean) throws Exception;
//	
//	public void UpdMailSetup(SetupMailBean setupmailBean) throws Exception;
//	
//	public void UpdUserMailSetup(SetupMailBean setupmailBean) throws Exception;
//	
//	public void DelMailSetup(int id,String logname) throws Exception;
}
