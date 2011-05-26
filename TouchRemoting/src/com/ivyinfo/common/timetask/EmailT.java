package com.ivyinfo.common.timetask;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.sql.RowSet;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.mail.bean.ReceiveMailBean;
import com.ivyinfo.mail.bean.ReturnMailBean;
import com.ivyinfo.mail.bean.SetupMailBean;
import com.ivyinfo.mail.services.AuxiliaryMailServices;
import com.ivyinfo.mail.services.MailApiServices;
import com.ivyinfo.user.bean.UserBean;
import com.ivyinfo.user.services.UserServices;

public class EmailT implements Job {
	
	private HashMap isRunEmail = new HashMap();
	private Logger logger =  Logger.getLogger("EmailT");
	
	private MailApiServices mailApiServices = (MailApiServices) SpringContextUtil.getBean("mailApiServices");
	
	private SetupMailBean setupMailBean;
	
	private ReceiveMailBean remBean;
	
	public EmailT(){
	}

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run() {
		System.err.println("=============EmailT run========");
		//if (!isRunning) {
		//	isRunning = true;
		logger.log(Level.INFO, "start mail task");
		UserServices userServices = (UserServices) SpringContextUtil.getBean("userServices");
		AuxiliaryMailServices auxiliaryMailServices = (AuxiliaryMailServices) SpringContextUtil.getBean("auxiliaryMailServices");
//		try {
//			List list = userServices.AllIndex(0, 0, "2", null, null, null);
			
//			for(int i=0;i<list.size();i++){
//				UserBean userBean = (UserBean)list.get(i);
				
//				List setuplist = auxiliaryMailServices.AllMailSetup(userBean.getLogname());
//				for(int j=0;j<setuplist.size();j++){
//					SetupMailBean setupmailBean = (SetupMailBean)setuplist.get(j);
//					
//					String mailaddname = setupmailBean.getAddname();//"44306443@qq.com"; // 邮箱地址
//					mailaddname = (mailaddname == null)?"":mailaddname;
//					String maillogname = setupmailBean.getLogname();//"44306443"; // 邮箱用户名
//					maillogname = (maillogname == null)?"":maillogname;
//					String logpassword = setupmailBean.getLogpassword();//"mzq19810722lzm"; // 邮箱密码
//					logpassword = (logpassword == null)?"":logpassword;
//					String userid = userBean.getId(); // 用户ID
//					String receiveallmail = "0"; //是否收取全部邮件  0[收取全部] 1[当天] 2[1天内] 3[2天内] 4[3天内] 5[1周内] 6[2周内] 7[3周内] 8[1月内] 9[2月内] 10[3月内]
//					String logname = userBean.getLogname();// 系统登陆名
//					String logpass = userBean.getPassword();// 系统登陆密码
//					String contentsavelocal = "true";//内容是否保存本地
//					System.err.println("==========isRunEmail:"+isRunEmail.size());
//					
//					if(!"".equals(mailaddname) && !"".equals(maillogname) && !"".equals(logpassword)){
//						if(isRunEmail != null && isRunEmail.size() != 0){
//							if((isRunEmail.get(mailaddname)).equals(mailaddname)){
//								logger.log(Level.INFO, "==="+mailaddname+"==is running =========================");
//							}else{
//								Task(mailaddname, maillogname, logpassword, userid,receiveallmail,contentsavelocal,logname,logpass);
//							}
//						}else{
//							Task(mailaddname, maillogname, logpassword, userid,receiveallmail,contentsavelocal,logname,logpass);
//						}
//					}
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		logger.log(Level.INFO, "end mail task");

			//isRunning = false;
		//} else {
		//	System.err.println("mail is not end");
		//}
	}

	public void Task(String mailaddname, String maillogname, String logpassword, String userid,
			String receiveallmail,String contentsavelocal,String logname,String logpass) {
		try {
			isRunEmail.put(mailaddname, mailaddname);
			
			setupMailBean = new SetupMailBean();
        	setupMailBean.setAddname(mailaddname);
        	setupMailBean.setLogname(maillogname);
        	setupMailBean.setLogpassword(logpassword);
        	setupMailBean.setReceiveallmail(receiveallmail);
        	setupMailBean.setContentsavelocal(contentsavelocal);
        	
        	remBean = new ReceiveMailBean();
        	
        	remBean.setLevel("0");
        	remBean.setState("0");
        	remBean.setLogname(logname);
        	remBean.setFtpusername(logname);
        	remBean.setFtpuserpass(logpass);
    		
        	ReturnMailBean rmBean = mailApiServices.getAllMailCount(setupMailBean,logname);
        	
        	if(rmBean != null){
        		mailApiServices.getEmailList_ftp(remBean,setupMailBean,rmBean.getUid(),rmBean.getMsgno());
        	}
    		
			isRunEmail.remove(mailaddname);
		} catch (Exception e) {
			e.printStackTrace();
			isRunEmail.remove(mailaddname);
		}
	}
	
	/**
	 * 查询邮箱个人设定收件时间
	 */
//	private RowSet getemail_time() {
//		System.err.println("=========into getEmail_time=========");
//		try {
//			StringBuffer strSqlBuf = new StringBuffer();
//			strSqlBuf.append("select a.vc_hour,a.vc_minute,a.vc_add,a.vc_name,a.vc_password,a.vc_userid,a.vc_datesign,a.vc_forestalldate,b.vc_logname,a.vc_savecontent")
//				.append(" from oa_pub_email_setup a,oa_sys_user b")
//				.append(" where a.vc_userid=b.vc_id");
//
//		SqlCommand sql = new SqlCommand();
//		sql.setCommandText(strSqlBuf.toString());
//		strSqlBuf.delete(0, strSqlBuf.length());
//
//		RowSet rs = sql.executeReader();
//			rs.last();
//			return rs;
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//		//return null;
//	}
}
