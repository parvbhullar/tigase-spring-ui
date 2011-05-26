package com.ivyinfo.mail.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ivyinfo.dept.dao.DeptDAO;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.mail.bean.DeleteMailBean;
import com.ivyinfo.mail.bean.DraftMailBean;
import com.ivyinfo.mail.bean.FileBean;
import com.ivyinfo.mail.bean.ReceiveMailBean;
import com.ivyinfo.mail.bean.SendMailBean;
import com.ivyinfo.mail.bean.SetupMailBean;
import com.ivyinfo.mail.dao.AuxiliaryMailDAO;
import com.ivyinfo.mail.dao.DeleteMailDAO;
import com.ivyinfo.mail.dao.DraftMailDAO;
import com.ivyinfo.mail.dao.ReceiveMailDAO;
import com.ivyinfo.mail.dao.SendMailDAO;
import com.ivyinfo.mail.util.DeleteMailUtil;

public class AuxiliaryMailServicesImpl implements AuxiliaryMailServices{

	public String ComparisonMail(String userlogname,String uid,String msgno) throws Exception{
		AuxiliaryMailDAO auxiliaryMailDAO =(AuxiliaryMailDAO) SpringContextUtil.getBean("auxiliaryMailDAO");
		return auxiliaryMailDAO.AuxiliaryMail(userlogname,uid,msgno);
	}
	
	public int ReceiveListCount(String logname) throws Exception{
		ReceiveMailDAO receiveMailDAO =(ReceiveMailDAO) SpringContextUtil.getBean("receiveMailDAO");
		return receiveMailDAO.ReceiveListCount(logname);
	}
	
	public List ReceiveList(int from, int len, String logname,String order,String orderby) throws Exception{
		ReceiveMailDAO receiveMailDAO =(ReceiveMailDAO) SpringContextUtil.getBean("receiveMailDAO");
		return receiveMailDAO.ReceiveList(from, len, logname, order, orderby);
	}
	
	public ReceiveMailBean ReceiveDetail(int id, String logname) throws Exception{
		ReceiveMailDAO receiveMailDAO =(ReceiveMailDAO) SpringContextUtil.getBean("receiveMailDAO");
		return receiveMailDAO.ReceiveDetail(id, logname);
	}
	
	public void ReceiveSign(int id, String logname, String state) throws Exception{
		ReceiveMailDAO receiveMailDAO =(ReceiveMailDAO) SpringContextUtil.getBean("receiveMailDAO");
		receiveMailDAO.ReceiveSign(id, logname, state);
	}
	
	public void ReceiveRemove(int id, String logname) throws Exception{
		ReceiveMailDAO receiveMailDAO =(ReceiveMailDAO) SpringContextUtil.getBean("receiveMailDAO");
		ReceiveMailBean rmBean = ReceiveDetail(id, logname);
		
		DeleteMailUtil dmUtil = new DeleteMailUtil();
		dmUtil.ReceiveDeleteMail(rmBean,"1",logname);
		receiveMailDAO.ReceiveRemove(id, logname);
	}
	
	public int SendListCount(String logname) throws Exception{
		SendMailDAO sendMailDAO =(SendMailDAO) SpringContextUtil.getBean("sendMailDAO");
		return sendMailDAO.SendListCount(logname);
	}
	
	public List SendList(int from, int len, String logname,String order,String orderby) throws Exception{
		SendMailDAO sendMailDAO =(SendMailDAO) SpringContextUtil.getBean("sendMailDAO");
		return sendMailDAO.SendList(from, len, logname, order, orderby);
	}
	
	public SendMailBean SendDetail(int id,String logname) throws Exception{
		SendMailDAO sendMailDAO =(SendMailDAO) SpringContextUtil.getBean("sendMailDAO");
		return sendMailDAO.SendDetail(id,logname);
	}
	
	public void SendRemove(int id, String logname) throws Exception{
		SendMailDAO sendMailDAO =(SendMailDAO) SpringContextUtil.getBean("sendMailDAO");
		SendMailBean sendmailBean = SendDetail(id,logname);
		
		DeleteMailUtil dmUtil = new DeleteMailUtil();
		dmUtil.SendDeleteMail(sendmailBean,"2",logname);
		sendMailDAO.SendRemove(id,logname);
	}
	
	public int DraftListCount(String logname) throws Exception{
		DraftMailDAO draftMailDAO =(DraftMailDAO) SpringContextUtil.getBean("draftMailDAO");
		return draftMailDAO.DraftListCount(logname);
	}
	
	public List DraftList(int from, int len, String logname,String order,String orderby) throws Exception{
		DraftMailDAO draftMailDAO =(DraftMailDAO) SpringContextUtil.getBean("draftMailDAO");
		return draftMailDAO.DraftList(from, len, logname, order, orderby);
	}
	
	public DraftMailBean DraftDetail(int id,String logname) throws Exception{
		DraftMailDAO draftMailDAO =(DraftMailDAO) SpringContextUtil.getBean("draftMailDAO");
		return draftMailDAO.DraftDetail(id,logname);
	}
	
	public void DraftRemove(int id,String logname) throws Exception{
		DraftMailDAO draftMailDAO =(DraftMailDAO) SpringContextUtil.getBean("draftMailDAO");
		DraftMailBean draftmailBean = DraftDetail(id,logname);
		
		DeleteMailUtil dmUtil = new DeleteMailUtil();
		dmUtil.DraftDeleteMail(draftmailBean,"3",logname);
		draftMailDAO.DraftRemove(id,logname);
	}
	
	public int DeleteListCount(String logname) throws Exception{
		DeleteMailDAO deleteMailDAO =(DeleteMailDAO) SpringContextUtil.getBean("deleteMailDAO");
		return deleteMailDAO.DeleteListCount(logname);
	}
	
	public List DeleteList(int from, int len, String logname,String order,String orderby) throws Exception{
		DeleteMailDAO deleteMailDAO =(DeleteMailDAO) SpringContextUtil.getBean("deleteMailDAO");
		return deleteMailDAO.DeleteList(from, len, logname, order, orderby);
	}
	
	public DeleteMailBean DeleteDetail(int id,String logname) throws Exception{
		DeleteMailDAO deleteMailDAO =(DeleteMailDAO) SpringContextUtil.getBean("deleteMailDAO");
		return deleteMailDAO.DeleteDetail(id,logname);
	}
	
	public void DeleteRemove(int id,String logname) throws Exception{
		DeleteMailDAO deleteMailDAO =(DeleteMailDAO) SpringContextUtil.getBean("deleteMailDAO");
		deleteMailDAO.DeleteRemove(id,logname);
	}
	
	public void RestoreDeleteMail(int id,String logname) throws Exception{
		DeleteMailDAO deleteMailDAO =(DeleteMailDAO) SpringContextUtil.getBean("deleteMailDAO");
		DeleteMailBean deleteMailBean = DeleteDetail(id,logname);
		
		DeleteMailUtil dmUtil = new DeleteMailUtil();
		dmUtil.RestoreDeleteMail(deleteMailBean,logname);
		
		deleteMailDAO.DeleteRemove(id,logname);
	}
	
	public List AllFile(String mailid,String logname) throws Exception{
		AuxiliaryMailDAO auxiliaryMailDAO =(AuxiliaryMailDAO) SpringContextUtil.getBean("auxiliaryMailDAO");
		return auxiliaryMailDAO.AllFile(mailid,logname);
	}
	
	public void SaveFile(FileBean fileBean) throws Exception{
		AuxiliaryMailDAO auxiliaryMailDAO =(AuxiliaryMailDAO) SpringContextUtil.getBean("auxiliaryMailDAO");
		auxiliaryMailDAO.SaveFile(fileBean);
	}
	
//	public List AllMailSetup(String logname) throws Exception{
//		AuxiliaryMailDAO auxiliaryMailDAO =(AuxiliaryMailDAO) SpringContextUtil.getBean("auxiliaryMailDAO");
//		return auxiliaryMailDAO.AllMailSetup(logname);
//	}
	
//	public SetupMailBean ViewMailSetupEmail(String email,String logname) throws Exception{
//		AuxiliaryMailDAO auxiliaryMailDAO =(AuxiliaryMailDAO) SpringContextUtil.getBean("auxiliaryMailDAO");
//		return auxiliaryMailDAO.ViewMailSetupEmail(email,logname);
//	}
	
//	public void AddMailSetup(SetupMailBean setupmailBean) throws Exception{
//		AuxiliaryMailDAO auxiliaryMailDAO =(AuxiliaryMailDAO) SpringContextUtil.getBean("auxiliaryMailDAO");
//		auxiliaryMailDAO.AddMailSetup(setupmailBean);
//	}
	
//	public void UpdMailSetup(SetupMailBean setupmailBean) throws Exception{
//		AuxiliaryMailDAO auxiliaryMailDAO =(AuxiliaryMailDAO) SpringContextUtil.getBean("auxiliaryMailDAO");
//		auxiliaryMailDAO.UpdMailSetup(setupmailBean);
//	}
	
//	public void UpdUserMailSetup(SetupMailBean setupmailBean) throws Exception{
//		AuxiliaryMailDAO auxiliaryMailDAO =(AuxiliaryMailDAO) SpringContextUtil.getBean("auxiliaryMailDAO");
//		auxiliaryMailDAO.UpdUserMailSetup(setupmailBean);
//	}
	
//	public void DelMailSetup(SetupMailBean setupmailBean) throws Exception{
//		AuxiliaryMailDAO auxiliaryMailDAO =(AuxiliaryMailDAO) SpringContextUtil.getBean("auxiliaryMailDAO");
//		auxiliaryMailDAO.DelMailSetup(Integer.parseInt(setupmailBean.getId()),setupmailBean.getLogname());
//	}
}
