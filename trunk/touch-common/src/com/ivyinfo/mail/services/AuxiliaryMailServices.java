package com.ivyinfo.mail.services;

import java.util.List;

import com.ivyinfo.framework.service.base.IBaseService;
import com.ivyinfo.mail.bean.DeleteMailBean;
import com.ivyinfo.mail.bean.DraftMailBean;
import com.ivyinfo.mail.bean.FileBean;
import com.ivyinfo.mail.bean.ReceiveMailBean;
import com.ivyinfo.mail.bean.SendMailBean;
import com.ivyinfo.mail.bean.SetupMailBean;

public interface AuxiliaryMailServices extends IBaseService{
	public String ComparisonMail(String userlogname,String uid,String msgno) throws Exception;
	
	public int ReceiveListCount(String logname) throws Exception;
	
	public List ReceiveList(int from, int len, String logname,String order,String orderby) throws Exception;
	
	public ReceiveMailBean ReceiveDetail(int id, String logname) throws Exception;
	
	/**
	 * 修改收件箱标记（已读未读）
	 * @return
	 * @throws Exception
	 */
	public void ReceiveSign(int id, String logname, String state) throws Exception;
	
	public void ReceiveRemove(int id, String logname) throws Exception;
	
	public int SendListCount(String logname) throws Exception;
	
	public List SendList(int from, int len, String logname,String order,String orderby) throws Exception;
	
	public SendMailBean SendDetail(int id,String logname) throws Exception;
	
	public void SendRemove(int id,String logname) throws Exception;
	
	public int DraftListCount(String logname) throws Exception;
	
	public List DraftList(int from, int len, String logname,String order,String orderby) throws Exception;
	
	public DraftMailBean DraftDetail(int id,String logname) throws Exception;
	
	public void DraftRemove(int id,String logname) throws Exception;
	
	public int DeleteListCount(String logname) throws Exception;
	
	public List DeleteList(int from, int len, String logname,String order,String orderby) throws Exception;
	
	public DeleteMailBean DeleteDetail(int id,String logname) throws Exception;
	
	public void DeleteRemove(int id,String logname) throws Exception;
	
	public void RestoreDeleteMail(int id,String logname) throws Exception;
	
	public List AllFile(String mailid,String logname) throws Exception;
	
	public void SaveFile(FileBean fileBean) throws Exception;
	
//	/**
//	 * 查询邮箱设置所有信息
//	 * @param logname
//	 * @return
//	 * @throws Exception
//	 */
//	public List AllMailSetup(String logname) throws Exception;
//	
//	/**
//	 * 根据邮箱查询用户邮箱设置信息
//	 * @param id
//	 * @return
//	 * @throws Exception
//	 */
//	public SetupMailBean ViewMailSetupEmail(String email,String logname) throws Exception;
//	
//	/**
//	 * 新增用户邮箱设置信息
//	 * @param setupmailBean
//	 * @throws Exception
//	 */
//	public void AddMailSetup(SetupMailBean setupmailBean) throws Exception;
//	
//	/**
//	 * 修改用户邮箱设置信息
//	 * @param setupmailBean
//	 * @throws Exception
//	 */
//	public void UpdMailSetup(SetupMailBean setupmailBean) throws Exception;
//	
//	/**
//	 * 修改用户邮箱帐号密码信息
//	 * @param setupmailBean
//	 * @throws Exception
//	 */
//	public void UpdUserMailSetup(SetupMailBean setupmailBean) throws Exception;
//	
//	/**
//	 * 删除邮箱个人设置信息
//	 * @param setupmailBean
//	 * @throws Exception
//	 */
//	public void DelMailSetup(SetupMailBean setupmailBean) throws Exception;
}
