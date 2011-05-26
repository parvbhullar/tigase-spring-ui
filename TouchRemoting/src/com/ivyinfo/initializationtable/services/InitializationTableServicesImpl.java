package com.ivyinfo.initializationtable.services;

import com.ivyinfo.communication.dao.CommunicationDAO;
import com.ivyinfo.communication.dao.CommunicationIMDAO;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.im.dao.IIMDAO;
import com.ivyinfo.mail.dao.AuxiliaryMailDAO;
import com.ivyinfo.mail.dao.DeleteMailDAO;
import com.ivyinfo.mail.dao.DraftMailDAO;
import com.ivyinfo.mail.dao.ReceiveMailDAO;
import com.ivyinfo.mail.dao.SendMailDAO;
import com.ivyinfo.webdisk.dao.DiskDAO;

public class InitializationTableServicesImpl implements InitializationTableServices{
	
	public void CreateUserTable(String logname) throws Exception{
		ReceiveMailDAO receiveMailDAO =(ReceiveMailDAO) SpringContextUtil.getBean("receiveMailDAO");
		SendMailDAO sendMailDAO =(SendMailDAO) SpringContextUtil.getBean("sendMailDAO");
		DraftMailDAO draftMailDAO =(DraftMailDAO) SpringContextUtil.getBean("draftMailDAO");
		DeleteMailDAO deleteMailDAO =(DeleteMailDAO) SpringContextUtil.getBean("deleteMailDAO");
		AuxiliaryMailDAO auxiliaryMailDAO =(AuxiliaryMailDAO) SpringContextUtil.getBean("auxiliaryMailDAO");
		
		CommunicationDAO communicationDAO =(CommunicationDAO) SpringContextUtil.getBean("communicationDAO");
		CommunicationIMDAO communicationIMDAO =(CommunicationIMDAO) SpringContextUtil.getBean("communicationimDAO");
		
		DiskDAO diskDAO =(DiskDAO) SpringContextUtil.getBean("diskDAO");
		
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		
		//收件箱建表
//		receiveMailDAO.ReceiveTable(logname);
//		//发件箱建表
//		sendMailDAO.SendTable(logname);
//		//草稿箱建表
//		draftMailDAO.DraftTable(logname);
//		//已删除邮箱建表
//		deleteMailDAO.DeleteTable(logname);
//		//邮箱附件建表
//		auxiliaryMailDAO.FileTable(logname);
		//邮箱个人设置
		auxiliaryMailDAO.MailSetupTable(logname);
		
		//通讯录建表
//		communicationDAO.CommunicationTable(logname);
//		//通讯录组建表
//		communicationIMDAO.CommunicationIMTable(logname);
//		
//		//网络硬盘建表
//		diskDAO.DiskTable(logname);
		
		//创建IM_Message表
//		UserStatusDAO.cretatIMUserMessageTable(logname);
	}
	
	public void DropUserTable(String logname) throws Exception{
		ReceiveMailDAO receiveMailDAO =(ReceiveMailDAO) SpringContextUtil.getBean("receiveMailDAO");
		SendMailDAO sendMailDAO =(SendMailDAO) SpringContextUtil.getBean("sendMailDAO");
		DraftMailDAO draftMailDAO =(DraftMailDAO) SpringContextUtil.getBean("draftMailDAO");
		DeleteMailDAO deleteMailDAO =(DeleteMailDAO) SpringContextUtil.getBean("deleteMailDAO");
		AuxiliaryMailDAO auxiliaryMailDAO =(AuxiliaryMailDAO) SpringContextUtil.getBean("auxiliaryMailDAO");
		
		CommunicationDAO communicationDAO =(CommunicationDAO) SpringContextUtil.getBean("communicationDAO");
		CommunicationIMDAO communicationIMDAO =(CommunicationIMDAO) SpringContextUtil.getBean("communicationimDAO");
		
		DiskDAO diskDAO =(DiskDAO) SpringContextUtil.getBean("diskDAO");
		
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		
		//收件箱删除表
//		receiveMailDAO.DropReceiveTable(logname);
//		//发件箱删除表
//		sendMailDAO.DropSendTable(logname);
//		//草稿箱删除表
//		draftMailDAO.DropDraftTable(logname);
//		//已删除邮箱删除表
//		deleteMailDAO.DropDeleteTable(logname);
//		//邮箱附件删除表
//		auxiliaryMailDAO.DropFileTable(logname);
		//删除邮箱个人设置表
		auxiliaryMailDAO.DropMailSetupTable(logname);
		
		//通讯录删除表
//		communicationDAO.DropCommunicationTable(logname);
//		//通讯录组删除表
//		communicationIMDAO.DropCommunicationIMTable(logname);
//		
//		//网络硬盘删除表
//		diskDAO.DropDiskTable(logname);
		
		//删除IM_Message表
//		UserStatusDAO.DropIMUserMessageTable(logname);
	}
}
