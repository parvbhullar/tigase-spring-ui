package com.ivyinfo.mail.util;

import sun.misc.BASE64Decoder;

import com.ivyinfo.framework.common.xml.XMLTools;
import com.ivyinfo.framework.service.sequence.Sequence;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.mail.bean.MailXMLBean;
import com.ivyinfo.mail.bean.ReturnMailBean;
import com.ivyinfo.mail.services.AuxiliaryMailServices;

public class HTTPReturnXML implements MailXMLBean{
	
	public ReturnMailBean XMLToValue(String mailxml,String userlogname) throws Exception{
		BASE64Decoder dec=new BASE64Decoder();
		ReturnMailBean rmBean = new ReturnMailBean();
		
		String subject = "";
		String mailid = "";
		String mailNo = "";
		if(mailxml.indexOf("num")>-1){
			String count = mailxml.substring(mailxml.indexOf("<num>")+5,mailxml.indexOf("</num>"));
			if(!"0".equals(count)){
				while(mailxml.indexOf("<mail>")>-1){
					String onemail = mailxml.substring(mailxml.indexOf("<mail>")+6,mailxml.indexOf("</mail>"));
					mailxml = mailxml.substring(mailxml.indexOf("</mail>")+7,mailxml.length());
					
					rmBean = ReturnMailToValue(onemail);
					String sub = rmBean.getSubject();
					String uid = rmBean.getUid();
					String msgno = rmBean.getMsgno();
					sub = new String(dec.decodeBuffer(sub));
					
					/**
					 * 查询邮件是否重复
					 */
					AuxiliaryMailServices amServices = (AuxiliaryMailServices) SpringContextUtil.getBean("auxiliaryMailServices");
					String countmail = amServices.ComparisonMail(userlogname,uid,msgno);
					
					System.err.println("countmail:"+countmail);
					
					if (!"0".equals(countmail)) {
						count = String.valueOf(Integer.parseInt(count) - 1);
					} else {
						subject += sub + ",";
						mailid += uid + ",";
						mailNo += msgno + ",";
					}
					
					
				}
				rmBean.setSubject(subject);
				rmBean.setUid(mailid);
				rmBean.setMsgno(mailNo);
				System.err.println("subject:"+subject);
				System.err.println("mailid:"+mailid);
				System.err.println("mailNo:"+mailNo);
				
				
			}else{
				return null;
			}
		}
		return rmBean;
	}
	
	public ReturnMailBean ReturnMailToValue(String mailxml) throws Exception{
		ReturnMailBean rmBean = new ReturnMailBean();
		
		rmBean.setSubject(XMLTools.getXMLValue(mailxml,SUBJECT));
		rmBean.setFrom(XMLTools.getXMLValue(mailxml,FROM));
		rmBean.setTo(XMLTools.getXMLValue(mailxml,TO));
		rmBean.setSentdate(XMLTools.getXMLValue(mailxml,SENTDATE));
		rmBean.setBcc(XMLTools.getXMLValue(mailxml,BCC));
		rmBean.setCc(XMLTools.getXMLValue(mailxml,CC));
		rmBean.setContent(XMLTools.getXMLValue(mailxml,MAILPART));
		rmBean.setFilename(XMLTools.getXMLValue(mailxml,FILENAME));
		rmBean.setFileAtt(XMLTools.getXMLValue(mailxml,FILEATT));
		rmBean.setUid(XMLTools.getXMLValue(mailxml,UID));
		rmBean.setMsgno(XMLTools.getXMLValue(mailxml,MSGNO));
		rmBean.setDeletemail(XMLTools.getXMLValue(mailxml,DELETEMAIL));
		
		return rmBean;
	}
}
