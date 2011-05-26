package com.ivyinfo.mail.services;

import java.util.ArrayList;
import java.util.List;

import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.mail.bean.MailUtilBean;
import com.ivyinfo.mail.util.MailUtil;
import com.ivyinfo.sysemail.bean.SysEmailBean;
import com.ivyinfo.sysemail.dao.SysEmailDAO;

public class SendMailServicesImpl implements SendMailServices{
	
	public String SendMail(MailUtilBean MailUtilBean) throws Exception{
		System.out.println("SENDER-:/>"
                + "开始发送邮件...");
		SysEmailDAO sysemailDAO =(SysEmailDAO) SpringContextUtil.getBean("sysemailDAO");
		
		String from = MailUtilBean.getFrom();//发件人
		String mailpactname = from.substring(from.indexOf("@")+1,from.indexOf("."));
		System.err.println("邮箱协议名  mailpactname:"+mailpactname);
		SysEmailBean sysemailBean = sysemailDAO.ViewSysMailName(mailpactname);
		
		MailUtilBean.setHost(sysemailBean.getSmtpadd());
		MailUtilBean.setPort(Integer.parseInt(sysemailBean.getSmtpport()));
//    	MailUtilBean.setFrom(sendname);
//    	MailUtilBean.setUser(user);
//    	MailUtilBean.setPassword(password);
        // 创建邮件对象
        MailUtil MailUtil = new MailUtil();
        List to = new ArrayList();// 收件人邮箱
        String toname = MailUtilBean.getToname();
        if(toname != null || !"".equals(toname)){
        	if(toname.indexOf(";")<0){
        		toname = toname + ";";
        	}
        	if(!"".equals(toname.substring(toname.lastIndexOf(";")+1,toname.length()))){
        		toname = toname + ";";
        	}
        	while(toname.indexOf(";")>-1){
        		String toaddress = toname.substring(0,toname.indexOf(";"));
        		toname = toname.substring(toname.indexOf(";")+1,toname.length());
        		
        		to.add(toaddress);
        	}
        	MailUtil.addTo(to);
        }
        List cc = new ArrayList();
        String ccname = MailUtilBean.getCcname();
        if(ccname != null || !"".equals(ccname)){
        	if(ccname.indexOf(";")<0){
        		ccname = ccname + ";";
        	}
        	if(!"".equals(ccname.substring(ccname.lastIndexOf(";")+1,ccname.length()))){
        		ccname = ccname + ";";
        	}
        	while(ccname.indexOf(";")>-1){
        		String ccaddress = ccname.substring(0,ccname.indexOf(";"));
        		ccname = ccname.substring(ccname.indexOf(";")+1,ccname.length());
        		
        		cc.add(ccaddress);
        	}
        	MailUtil.addCc(cc);
        }
        List bcc = new ArrayList();
        String bccname = MailUtilBean.getBccname();
        if(bccname != null || !"".equals(bccname)){
        	if(bccname.indexOf(";")<0){
        		bccname = bccname + ";";
        	}
        	if(!"".equals(bccname.substring(bccname.lastIndexOf(";")+1,bccname.length()))){
        		bccname = bccname + ";";
        	}
        	while(bccname.indexOf(";")>-1){
        		String bccaddress = bccname.substring(0,bccname.indexOf(";"));
        		bccname = bccname.substring(bccname.indexOf(";")+1,bccname.length());
        		
        		bcc.add(bccaddress);
        	}
        	MailUtil.addBcc(bcc);
        }
        
        String fileaddress = MailUtilBean.getFileaddress();// 添加附件
        System.err.println("fileaddress:"+fileaddress);
//        String fileaddress = "D:\\测试附件.txt;D:\\测试附件1.txt;";
        if(fileaddress != null || !"".equals(fileaddress)){
        	if(fileaddress.indexOf(";")>-1){
        	}else{
        		fileaddress = fileaddress + ";";
        	}
        	if(!"".equals(fileaddress.substring(fileaddress.lastIndexOf(";")+1,fileaddress.length()))){
        		fileaddress = fileaddress + ";";
        	}
        	System.err.println("fileaddress:"+fileaddress);
        	
        	//添加附件
            while(fileaddress.indexOf(";")>-1){
        		String fileadd = fileaddress.substring(0,fileaddress.indexOf(";"));
        		fileaddress = fileaddress.substring(fileaddress.indexOf(";")+1,fileaddress.length());
        		
        		if(!"".equals(fileadd)){
        			MailUtil.addAttachment(fileadd);
        		}
        	}
        }
        
        return MailUtil.send(MailUtilBean); // 发送
	}
}
