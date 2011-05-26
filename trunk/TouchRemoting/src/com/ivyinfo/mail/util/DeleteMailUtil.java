package com.ivyinfo.mail.util;

import com.ivyinfo.framework.common.time.TimeTools;
import com.ivyinfo.framework.service.sequence.Sequence;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.mail.bean.DeleteMailBean;
import com.ivyinfo.mail.bean.DraftMailBean;
import com.ivyinfo.mail.bean.ReceiveMailBean;
import com.ivyinfo.mail.bean.SendMailBean;
import com.ivyinfo.mail.services.SaveMailServices;

public class DeleteMailUtil {
	
	/**
	 * 收件箱删除
	 * @param rmBean
	 * @param type
	 * @throws Exception
	 */
	public void ReceiveDeleteMail(ReceiveMailBean rmBean,String type,String logname) throws Exception{
		DeleteMailBean dmBean = new DeleteMailBean();
		
		Sequence sequenceService = (Sequence) SpringContextUtil.getBean("Sequence"); 
		long id = sequenceService.getMaxId(logname+"_t_mail_delete");
		
		dmBean.setId(String.valueOf(id));
		dmBean.setOriginalid(rmBean.getId());
		dmBean.setSendname(rmBean.getSendname());
		dmBean.setReceivename(rmBean.getReceivename());
		dmBean.setCopyname(rmBean.getCopyname());
		dmBean.setSecretname(rmBean.getSecretname());
		dmBean.setSubject(rmBean.getSubject());
		dmBean.setContent(rmBean.getContent());
		dmBean.setFilename(rmBean.getFilename());
		dmBean.setDatetime(rmBean.getDatetime());
		dmBean.setLevel(rmBean.getLevel());
		dmBean.setType(type);
		dmBean.setState(rmBean.getState());
		dmBean.setMailId(rmBean.getMailId());
		dmBean.setMailnumber(rmBean.getMailnumber());
//		dmBean.setUserid(rmBean.getUserid());
//		dmBean.setUpduserid(rmBean.getUserid());
//		dmBean.setTimestemp(TimeTools.getString());
//		dmBean.setDirtyflag(TimeTools.getString()+TimeTools.getMilliSecond());
		
		SaveMailServices smServices = (SaveMailServices) SpringContextUtil.getBean("saveMailServices");
		smServices.SaveDeleteMail(dmBean,logname);
		
	}
	
	/**
	 * 发件箱删除
	 * @param rmBean
	 * @param type
	 * @throws Exception
	 */
	public void SendDeleteMail(SendMailBean sendmailBean,String type,String logname) throws Exception{
		DeleteMailBean dmBean = new DeleteMailBean();
		
		Sequence sequenceService = (Sequence) SpringContextUtil.getBean("Sequence"); 
		long id = sequenceService.getMaxId(logname+"_t_mail_delete");
		
		dmBean.setId(String.valueOf(id));
		dmBean.setOriginalid(sendmailBean.getId());
		dmBean.setSendname(sendmailBean.getSendname());
		dmBean.setReceivename(sendmailBean.getReceivename());
		dmBean.setCopyname(sendmailBean.getCopyname());
		dmBean.setSecretname(sendmailBean.getSecretname());
		dmBean.setSubject(sendmailBean.getSubject());
		dmBean.setContent(sendmailBean.getContent());
		dmBean.setFilename(sendmailBean.getFilename());
		dmBean.setDatetime(sendmailBean.getDatetime());
		dmBean.setLevel(sendmailBean.getLevel());
		dmBean.setType(type);
		dmBean.setState(sendmailBean.getState());
		dmBean.setMailId(sendmailBean.getMailId());
		dmBean.setMailnumber(sendmailBean.getMailnumber());
//		dmBean.setUserid(sendmailBean.getUserid());
//		dmBean.setUpduserid(sendmailBean.getUserid());
//		dmBean.setTimestemp(TimeTools.getString());
//		dmBean.setDirtyflag(TimeTools.getString()+TimeTools.getMilliSecond());
		
		SaveMailServices smServices = (SaveMailServices) SpringContextUtil.getBean("saveMailServices");
		smServices.SaveDeleteMail(dmBean,logname);
	}
	
	/**
	 * 草稿箱删除
	 * @param rmBean
	 * @param type
	 * @throws Exception
	 */
	public void DraftDeleteMail(DraftMailBean draftmailBean,String type,String logname) throws Exception{
		DeleteMailBean dmBean = new DeleteMailBean();
		
		Sequence sequenceService = (Sequence) SpringContextUtil.getBean("Sequence"); 
		long id = sequenceService.getMaxId(logname+"_t_mail_delete");
		
		dmBean.setId(String.valueOf(id));
		dmBean.setOriginalid(draftmailBean.getId());
		dmBean.setSendname(draftmailBean.getSendname());
		dmBean.setReceivename(draftmailBean.getReceivename());
		dmBean.setCopyname(draftmailBean.getCopyname());
		dmBean.setSecretname(draftmailBean.getSecretname());
		dmBean.setSubject(draftmailBean.getSubject());
		dmBean.setContent(draftmailBean.getContent());
		dmBean.setFilename(draftmailBean.getFilename());
		dmBean.setDatetime(draftmailBean.getDatetime());
		dmBean.setLevel(draftmailBean.getLevel());
		dmBean.setType(type);
		dmBean.setState(draftmailBean.getState());
		dmBean.setMailId(draftmailBean.getMailId());
		dmBean.setMailnumber(draftmailBean.getMailnumber());
//		dmBean.setUserid(draftmailBean.getUserid());
//		dmBean.setUpduserid(draftmailBean.getUserid());
//		dmBean.setTimestemp(TimeTools.getString());
//		dmBean.setDirtyflag(TimeTools.getString()+TimeTools.getMilliSecond());
		
		SaveMailServices smServices = (SaveMailServices) SpringContextUtil.getBean("saveMailServices");
		smServices.SaveDeleteMail(dmBean,logname);
	}
	
	/**
	 * 还原已删除邮箱邮件
	 * @param deleteMailBean
	 * @throws Exception
	 */
	public void RestoreDeleteMail(DeleteMailBean deleteMailBean,String logname) throws Exception{
		String type = deleteMailBean.getType();
		if("1".equals(type)){
			ReceiveMailBean receiveMailBean = new ReceiveMailBean();
			receiveMailBean.setId(deleteMailBean.getOriginalid());
			receiveMailBean.setSendname(deleteMailBean.getSendname());
			receiveMailBean.setReceivename(deleteMailBean.getReceivename());
			receiveMailBean.setCopyname(deleteMailBean.getCopyname());
			receiveMailBean.setSecretname(deleteMailBean.getSecretname());
			receiveMailBean.setSubject(deleteMailBean.getSubject());
			receiveMailBean.setContent(deleteMailBean.getContent());
			receiveMailBean.setFilename(deleteMailBean.getFilename());
			receiveMailBean.setDatetime(deleteMailBean.getDatetime());
			receiveMailBean.setLevel(deleteMailBean.getLevel());
			receiveMailBean.setState(deleteMailBean.getState());
			receiveMailBean.setMailId(deleteMailBean.getMailId());
			receiveMailBean.setMailnumber(deleteMailBean.getMailnumber());
			receiveMailBean.setLogname(logname+"_t_mail_receive");
//			receiveMailBean.setUserid(deleteMailBean.getUserid());
			
//			receiveMailBean.setUpduserid("2");
//			receiveMailBean.setTimestemp(TimeTools.getString());
//			receiveMailBean.setDirtyflag(TimeTools.getString()+TimeTools.getMilliSecond());
			
			SaveMailServices saveMailServices = (SaveMailServices) SpringContextUtil.getBean("saveMailServices");
			saveMailServices.SaveReceiveMail(receiveMailBean);
		}
		if("2".equals(type)){
			SendMailBean sendMailBean = new SendMailBean();
			sendMailBean.setId(deleteMailBean.getOriginalid());
			sendMailBean.setSendname(deleteMailBean.getSendname());
			sendMailBean.setReceivename(deleteMailBean.getReceivename());
			sendMailBean.setCopyname(deleteMailBean.getCopyname());
			sendMailBean.setSecretname(deleteMailBean.getSecretname());
			sendMailBean.setSubject(deleteMailBean.getSubject());
			sendMailBean.setContent(deleteMailBean.getContent());
			sendMailBean.setFilename(deleteMailBean.getFilename());
			sendMailBean.setDatetime(deleteMailBean.getDatetime());
			sendMailBean.setLevel(deleteMailBean.getLevel());
			sendMailBean.setState(deleteMailBean.getState());
			sendMailBean.setMailId(deleteMailBean.getMailId());
			sendMailBean.setMailnumber(deleteMailBean.getMailnumber());
			sendMailBean.setLogname(logname+"_t_mail_send");
//			sendMailBean.setUserid(deleteMailBean.getUserid());
			
//			sendMailBean.setUpduserid("2");
//			sendMailBean.setTimestemp(TimeTools.getString());
//			sendMailBean.setDirtyflag(TimeTools.getString()+TimeTools.getMilliSecond());
			
			SaveMailServices saveMailServices = (SaveMailServices) SpringContextUtil.getBean("saveMailServices");
			saveMailServices.SaveSendMail(sendMailBean);
		}
		if("3".equals(type)){
			DraftMailBean draftMailBean = new DraftMailBean();
			draftMailBean.setId(deleteMailBean.getOriginalid());
			draftMailBean.setSendname(deleteMailBean.getSendname());
			draftMailBean.setReceivename(deleteMailBean.getReceivename());
			draftMailBean.setCopyname(deleteMailBean.getCopyname());
			draftMailBean.setSecretname(deleteMailBean.getSecretname());
			draftMailBean.setSubject(deleteMailBean.getSubject());
			draftMailBean.setContent(deleteMailBean.getContent());
			draftMailBean.setFilename(deleteMailBean.getFilename());
			draftMailBean.setDatetime(deleteMailBean.getDatetime());
			draftMailBean.setLevel(deleteMailBean.getLevel());
			draftMailBean.setState(deleteMailBean.getState());
			draftMailBean.setMailId(deleteMailBean.getMailId());
			draftMailBean.setMailnumber(deleteMailBean.getMailnumber());
			draftMailBean.setLogname(logname+"_t_mail_draft");
//			draftMailBean.setUserid(deleteMailBean.getUserid());
			
//			draftMailBean.setUpduserid("2");
//			draftMailBean.setTimestemp(TimeTools.getString());
//			draftMailBean.setDirtyflag(TimeTools.getString()+TimeTools.getMilliSecond());
			
			SaveMailServices saveMailServices = (SaveMailServices) SpringContextUtil.getBean("saveMailServices");
			saveMailServices.SaveDraftMail(draftMailBean);
		}
	}
}
