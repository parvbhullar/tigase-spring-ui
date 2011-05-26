package com.ivyinfo.mail.services;

import sun.misc.BASE64Decoder;

import com.ivyinfo.framework.common.file.ResouceLoader;
import com.ivyinfo.framework.common.time.TimeTools;
import com.ivyinfo.framework.service.sequence.ISequence;
import com.ivyinfo.framework.service.sequence.SequenceInterface;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.framework.service.servlet.HttpRequester;
import com.ivyinfo.framework.service.servlet.HttpRespons;
import com.ivyinfo.mail.bean.FileBean;
import com.ivyinfo.mail.bean.ReceiveMailBean;
import com.ivyinfo.mail.bean.ReturnMailBean;
import com.ivyinfo.mail.bean.SendXMLBean;
import com.ivyinfo.mail.bean.SetupMailBean;
import com.ivyinfo.mail.dao.ReceiveMailDAO;
import com.ivyinfo.mail.util.HTTPReturnXML;
import com.ivyinfo.mail.util.HTTPSendXML;

public class MailApiServicesImpl implements MailApiServices{

	public ReturnMailBean getAllMailCount(SetupMailBean SetupMailBean,String userlogname) throws Exception{
		
		HttpRequester request = new HttpRequester();
		
		
		ResouceLoader resouceloader = new ResouceLoader();
		String mailURL = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.mail.api.mailUrl");
		String mailorgid = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.mail.api.mailorgid");
		
		/**
		 * 发送的xml
		 */
		SendXMLBean sxBean = new SendXMLBean();
		String receiveAllMail = SetupMailBean.getReceiveallmail();
		int date = 0;
		if("0".equals(receiveAllMail)){
			sxBean.setCommand("getAllMailCount");
			sxBean.setAddress(SetupMailBean.getAddname());
			sxBean.setName(SetupMailBean.getLogname());
			sxBean.setPassword(SetupMailBean.getLogpassword());
			sxBean.setCompany(mailorgid);
		}else{
			if("1".equals(receiveAllMail)){
				date = 0;               //当天
			}
			if("2".equals(receiveAllMail)){
				date = 1;               //1天内
			}
			if("3".equals(receiveAllMail)){
				date = 2;               //2天内
			}
			if("4".equals(receiveAllMail)){
				date = 3;               //3天内
			}
			if("5".equals(receiveAllMail)){
				date = 7;               //1周内
			}
			if("6".equals(receiveAllMail)){
				date = 14;              //2周内
			}
			if("7".equals(receiveAllMail)){
				date = 21;               //3周内
			}
			if("8".equals(receiveAllMail)){
				date = 30;                //1月内
			}
			if("9".equals(receiveAllMail)){
				date = 60;               //2月内
			}
			if("10".equals(receiveAllMail)){
				date = 90;               //3月内
			}
			sxBean.setCommand("getDateMailCount");
			sxBean.setAddress(SetupMailBean.getAddname());
			sxBean.setName(SetupMailBean.getLogname());
			sxBean.setPassword(SetupMailBean.getLogpassword());
			sxBean.setCompany(mailorgid);
			sxBean.setDate(String.valueOf(date));
		}
		
		
		HTTPSendXML HTTPSendXML = new HTTPSendXML();
		
		String mailbeantoxml = HTTPSendXML.BeanToXML(sxBean);
//		System.err.println("mailbeantoxml:"+mailbeantoxml);
		
//		System.err.println("mailURL:"+mailURL);
		
		HttpRespons hr = request.sendPost(mailURL, mailbeantoxml);
		
		String mailxml = hr.getContent();
//		System.err.println("mailxml:"+mailxml);
		
		HTTPReturnXML HTTPReturnXML = new HTTPReturnXML();
		ReturnMailBean rmBean = HTTPReturnXML.XMLToValue(mailxml,userlogname);
		
		return rmBean;
	}
	
//	public void getEmailList(ReceiveMailBean remBean,SetupMailBean SetupMailBean,String mailid,String mailNo) throws Exception{
//		try{
//			ReceiveMailDAO receiveMailDAO =(ReceiveMailDAO) SpringContextUtil.getBean("receiveMailDAO");
//			
//			
//			SendXMLBean sxBean = new SendXMLBean();
//			BASE64Decoder dec=new BASE64Decoder();
//			HTTPReturnXML HTTPReturnXML = new HTTPReturnXML();
//			
//			ResouceLoader resouceloader = new ResouceLoader();
//			String mailURL = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.mail.api.mailUrl");
//			
//			String logname = remBean.getLogname();
//			remBean.setLogname(logname+"_t_mail_receive");
//			if(!"".equals(mailid)){
//				while(mailid.indexOf(",")>-1){
//					HttpRequester request = new HttpRequester();
//					String onemailid = mailid.substring(0,mailid.indexOf(","));
//					System.err.println("onemailid:"+onemailid);
//					mailid = mailid.substring(mailid.indexOf(",")+1,mailid.length());
//					
//					String onemailNo = mailNo.substring(0,mailNo.indexOf(","));
//					System.err.println("onemailNo:"+onemailNo);
//					mailNo = mailNo.substring(mailNo.indexOf(",")+1,mailNo.length());
//					
//					sxBean.setCommand("getMailList");
//					sxBean.setAddress(SetupMailBean.getAddname());
//					sxBean.setName(SetupMailBean.getLogname());
//					sxBean.setPassword(SetupMailBean.getLogpassword());
//					sxBean.setCompany("10002");
//					sxBean.setUid(onemailid);
//					sxBean.setMsgno(onemailNo);
//					
//					HTTPSendXML HTTPSendXML = new HTTPSendXML();
//					
//					String mailbeantoxml = HTTPSendXML.BeanToXML(sxBean);
////					System.err.println("mailbeantoxml:"+mailbeantoxml);
//					
//					HttpRespons hr = request.sendPost(mailURL, mailbeantoxml);
//					
//					String mailxml = hr.getContent();
////					System.err.println("mailxml:"+mailxml);
//					
//					ReturnMailBean rmBean = HTTPReturnXML.ReturnMailToValue(mailxml);
//					
//					
//					String sub = new String(dec.decodeBuffer(rmBean.getSubject()));	//标题
//					if(sub == "" || "".equals(sub)){
//						sub = "此邮件没有标题";
//					}
////					System.err.println("sub:"+sub);
//					remBean.setSubject(sub);
////					System.err.println("remBean:"+remBean.getSubject());
//					
//					
//					String from = new String(dec.decodeBuffer(rmBean.getFrom()));	//来源  发件人
//					remBean.setSendname(from);
//					
//					String to = new String(dec.decodeBuffer(rmBean.getTo()));	//发往  收件人
//					remBean.setReceivename(to);
//					
//					String sentdate = new String(dec.decodeBuffer(rmBean.getSentdate())); //发送时间
//					remBean.setDatetime(sentdate);
//					
//					String bcc = new String(dec.decodeBuffer(rmBean.getBcc()));	//密送
//					remBean.setSecretname(bcc);
//					
//					String cc = new String(dec.decodeBuffer(rmBean.getCc()));  //抄送
//					remBean.setCopyname(cc);
//					
//					String uid = rmBean.getUid();  //UID
//					remBean.setMailId(uid);
//					
//					String vc_savecontent = SetupMailBean.getContentsavelocal();  //内容是否保存本地
//					String content = "";
//					if("false".equals(vc_savecontent)){
//						
//					}else if("true".equals(vc_savecontent)){
//						content = new String(dec.decodeBuffer(rmBean.getContent()));	//邮件内容
//					}
//					remBean.setContent(content);
//					
//					String msgno = rmBean.getMsgno();	//msgno
//					remBean.setMailnumber(msgno);
//					
//					String filename = new String(dec.decodeBuffer(rmBean.getFilename()));	//附件名
//					filename = (filename == null)?"":filename;
//					remBean.setFilename(filename);
//					
//					System.err.println("remBean.getLogname():"+remBean.getLogname());
//					SequenceInterface sequenceService = (SequenceInterface) SpringContextUtil.getBean("Sequence");
//					long id = sequenceService.getMaxId(remBean.getLogname());
//					remBean.setId(String.valueOf(id));
//					
//					String datetime = TimeTools.getCurrentTime();
//		        	
//					remBean.setDatetime(datetime);
//					
//					receiveMailDAO.SaveReceiveMail(remBean);
//					
//					System.err.println("==========一条结束==============");
//				}
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
	
	public void getEmailList_ftp(ReceiveMailBean remBean,SetupMailBean SetupMailBean,String mailid,String mailNo) throws Exception{
		try{
			ReceiveMailDAO receiveMailDAO =(ReceiveMailDAO) SpringContextUtil.getBean("receiveMailDAO");
			
			
			SendXMLBean sxBean = new SendXMLBean();
			BASE64Decoder dec=new BASE64Decoder();
			HTTPReturnXML HTTPReturnXML = new HTTPReturnXML();
			
			ResouceLoader resouceloader = new ResouceLoader();
			String mailURL = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.mail.api.mailUrl");
			String mailorgid = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.mail.api.mailorgid");
			
//			String logname = remBean.getLogname();
//			System.err.println("logname:"+logname);
			String userlogname = remBean.getLogname();
			remBean.setLogname(userlogname+"_t_mail_receive");
			if(!"".equals(mailid)){
				while(mailid.indexOf(",")>-1){
					System.err.println("userlogname=======:"+userlogname);
					
					HttpRequester request = new HttpRequester();
					String onemailid = mailid.substring(0,mailid.indexOf(","));
					System.err.println("onemailid:"+onemailid);
					mailid = mailid.substring(mailid.indexOf(",")+1,mailid.length());
					
					String onemailNo = mailNo.substring(0,mailNo.indexOf(","));
					System.err.println("onemailNo:"+onemailNo);
					mailNo = mailNo.substring(mailNo.indexOf(",")+1,mailNo.length());
					
					sxBean.setCommand("getMailList_ftp");
					sxBean.setAddress(SetupMailBean.getAddname());
					sxBean.setName(SetupMailBean.getLogname());
					sxBean.setPassword(SetupMailBean.getLogpassword());
					sxBean.setCompany(mailorgid);
					sxBean.setUid(onemailid);
					sxBean.setMsgno(onemailNo);
					sxBean.setFtpusername(remBean.getFtpusername());
					sxBean.setFtpuserpass(remBean.getFtpuserpass());
					
					HTTPSendXML HTTPSendXML = new HTTPSendXML();
					
					String mailbeantoxml = HTTPSendXML.BeanToXML(sxBean);
					System.err.println("mailbeantoxml:"+mailbeantoxml);
					
					HttpRespons hr = request.sendPost(mailURL, mailbeantoxml);
					
					String mailxml = hr.getContent();
					//System.err.println("mailxml:"+mailxml);
					
					ReturnMailBean rmBean = HTTPReturnXML.ReturnMailToValue(mailxml);
					
					
					String sub = new String(dec.decodeBuffer(rmBean.getSubject()));	//标题
					if(sub == "" || "".equals(sub)){
						sub = "此邮件没有标题";
					}
//					System.err.println("sub:"+sub);
					remBean.setSubject(sub);
//					System.err.println("remBean:"+remBean.getSubject());
					
					
					String from = new String(dec.decodeBuffer(rmBean.getFrom()));	//来源  发件人
					remBean.setSendname(from);
					
					String to = new String(dec.decodeBuffer(rmBean.getTo()));	//发往  收件人
					remBean.setReceivename(to);
					
					String sentdate = new String(dec.decodeBuffer(rmBean.getSentdate())); //发送时间
					remBean.setDatetime(sentdate);
					
					String bcc = new String(dec.decodeBuffer(rmBean.getBcc()));	//密送
					remBean.setSecretname(bcc);
					
					String cc = new String(dec.decodeBuffer(rmBean.getCc()));  //抄送
					remBean.setCopyname(cc);
					
					String uid = rmBean.getUid();  //UID
					remBean.setMailId(uid);
					
					String vc_savecontent = SetupMailBean.getContentsavelocal();  //内容是否保存本地
					System.err.println("vc_savecontent:"+vc_savecontent);
					String content = "";
					if("false".equals(vc_savecontent)){
						
					}else if("true".equals(vc_savecontent)){
						content = new String(dec.decodeBuffer(rmBean.getContent()));	//邮件内容
					}
					remBean.setContent(content);
					
					String msgno = rmBean.getMsgno();	//msgno
					remBean.setMailnumber(msgno);
					
					String ftpfilename = new String(dec.decodeBuffer(rmBean.getFilename()));	//附件名
					ftpfilename = (ftpfilename == null)?"":ftpfilename;
					System.err.println("ftpfilename:"+ftpfilename);
					
					
					String ftpfileAtt = rmBean.getFileAtt();	//附件内容
					ftpfileAtt = (ftpfileAtt == null)?"":ftpfileAtt;
					System.err.println("ftpfileAtt:"+ftpfileAtt);
					
					System.err.println("remBean.getLogname():"+remBean.getLogname());
					SequenceInterface sequenceService = (SequenceInterface) SpringContextUtil.getBean("Sequence");
					long id = sequenceService.getMaxId(userlogname+"_t_mail_receive");
					remBean.setId(String.valueOf(id));
					
					String datetime = TimeTools.getCurrentTime();
		        	
					remBean.setDatetime(datetime);
					
					String filename = "";
					FileBean fileBean = new FileBean();
					while(ftpfilename.indexOf(",")>-1){
						String ffname = ftpfilename.substring(0,ftpfilename.indexOf(","));
						ftpfilename = ftpfilename.substring(ftpfilename.indexOf(",")+1,ftpfilename.length());
						String filepath = ftpfileAtt.substring(0,ftpfileAtt.indexOf("[ivyinfoAtt]"));
						ftpfileAtt = ftpfileAtt.substring(ftpfileAtt.indexOf("[ivyinfoAtt]")+12,ftpfileAtt.length());
						
						System.err.println("userlogname:"+userlogname);
						long fileid = sequenceService.getMaxId(userlogname+"_t_mail_file");
						System.err.println("fileid:"+fileid);
						
						fileBean.setId(String.valueOf(fileid));
						fileBean.setFilename(ffname);
						fileBean.setFilepath(new String(dec.decodeBuffer(filepath)));
						fileBean.setMailid(String.valueOf(id));
						fileBean.setLogname(userlogname);
						
						/**
						 * 保存附件信息
						 */
						AuxiliaryMailServices amServices = (AuxiliaryMailServices) SpringContextUtil.getBean("auxiliaryMailServices");
						amServices.SaveFile(fileBean);
						
						filename = filename + "[" + String.valueOf(fileid) + "]" + ",";
					}
		        	
					remBean.setFilename(filename);
					
					receiveMailDAO.SaveReceiveMail(remBean);
					
					System.err.println("==========一条结束==============");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void deleteMail(SetupMailBean SetupMailBean, ReceiveMailBean MailBean) throws Exception{
		ResouceLoader resouceloader = new ResouceLoader();
		String mailURL = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.mail.api.mailUrl");
		String mailorgid = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/webmeettouch.properties", "com.ivyinfo.mail.api.mailorgid");
		
		HttpRequester request = new HttpRequester();
		
		SendXMLBean sxBean = new SendXMLBean();
		sxBean.setCommand("deleteMail");
		sxBean.setAddress(SetupMailBean.getAddname());
		sxBean.setName(SetupMailBean.getLogname());
		sxBean.setPassword(SetupMailBean.getLogpassword());
		sxBean.setUid(MailBean.getMailId());
		sxBean.setMsgno(MailBean.getMailnumber());
		sxBean.setCompany(mailorgid);
		
		HTTPSendXML HTTPSendXML = new HTTPSendXML();
		
		String mailbeantoxml = HTTPSendXML.BeanToXML(sxBean);
		System.err.println("mailbeantoxml:"+mailbeantoxml);
		
		HttpRespons hr = request.sendPost(mailURL, mailbeantoxml);
		
		String mailxml = hr.getContent();
		System.err.println("mailxml:"+mailxml);
		
		HTTPReturnXML HTTPReturnXML = new HTTPReturnXML();
		ReturnMailBean rmBean = HTTPReturnXML.ReturnMailToValue(mailxml);
		
		System.err.println("rmBean.getDeletemail():"+rmBean.getDeletemail());
	}
}
