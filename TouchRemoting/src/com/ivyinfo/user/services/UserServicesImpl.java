package com.ivyinfo.user.services;

import java.util.List;
import java.util.Map;

import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.initializationtable.services.InitializationTableServices;
import com.ivyinfo.mail.bean.SetupMailBean;
import com.ivyinfo.mail.services.AuxiliaryMailServices;
import com.ivyinfo.purview.bean.PurviewBean;
import com.ivyinfo.user.bean.UserBean;
import com.ivyinfo.user.dao.UserDAO;
import com.ivyinfo.webtouch.conversion.UserConversion;

public class UserServicesImpl implements UserServices{

	public int UserListCount(String state,String orgid,String conname,String convalue) throws Exception{
		UserDAO userDAO =(UserDAO) SpringContextUtil.getBean("userDAO");
		return userDAO.UserListCount(state,orgid,conname,convalue);
	}
	
	public List AllIndex(int from, int len, String state,String orgid,String order,String orderby,String conname,String convalue) throws Exception{
		UserDAO userDAO =(UserDAO) SpringContextUtil.getBean("userDAO");
		return userDAO.AllIndex(from, len, state,orgid, order, orderby, conname, convalue);
	}
	
	public void AddSubmit(UserBean userBean,UserBean suserBean,SetupMailBean setupmailBean,String meetorgid) throws Exception{
		UserDAO userDAO =(UserDAO) SpringContextUtil.getBean("userDAO");
		UserConversion userConversion = (UserConversion) SpringContextUtil.getBean("userConversion");
		InitializationTableServices initializationtableServices = (InitializationTableServices) SpringContextUtil.getBean("initializationtableServices");
		AuxiliaryMailServices auxiliaryMailServices = (AuxiliaryMailServices) SpringContextUtil.getBean("auxiliaryMailServices");
		/**
		 * 对外通过接口保存会议用户信息
		 */
		Map map = userConversion.MeetAddUser(userBean,suserBean,meetorgid);
		String meetresult = String.valueOf(map.get("result"));
		/**
		 * meetreason返回值
		 * 成功：返回会议系统用户id  userid
		 * 错误：返回会议系统的错误信息
		 */
		String meetreason = String.valueOf(map.get("reason"));
		System.err.println("meetresult:"+meetresult);
		System.err.println("meetreason:"+meetreason);
		if("SUCCESS".equals(meetresult)){
			//将会议系统的用户id保存到用户信息表
			userBean.setMeetuserid(meetreason);
		}else if("FAILURE".equals(meetresult)){
			throw new Exception(meetreason);
		}
		
		/**
		 * 对外通过接口保存ftp用户信息
		 */
//		try{
//			userConversion.FtpAddUser(userBean);
//		}catch(Exception e){
//			//删除会议系统用户
//			userConversion.MeetDelUser(meetreason, suserBean);
//			throw new Exception(e.getMessage());
//		}
		
		/**
		 * 创建数据库表结构
		 */
//		try{
//			initializationtableServices.CreateUserTable(userBean.getLogname());
//		}catch(Exception e){
//			//删除会议系统用户
//			userConversion.MeetDelUser(meetreason, suserBean);
//			//删除ftp用户
////			userConversion.FtpDelUser(userBean);
//			throw new Exception(e.getMessage());
//		}
		
		/**
		 * 新增用户邮箱账号信息
		 */
//		try{
//			auxiliaryMailServices.AddMailSetup(setupmailBean);
//		}catch(Exception e){
//			//删除会议系统用户
//			userConversion.MeetDelUser(meetreason, suserBean);
//			//删除ftp用户
////			userConversion.FtpDelUser(userBean);
//			//删除用户数据库表结构
////			initializationtableServices.DropUserTable(userBean.getLogname());
//			throw new Exception(e.getMessage());
//		}
		
		/**
		 * 新增用户
		 */
		try{
			userDAO.AddSubmit(userBean);
		}catch(Exception e){
			//删除会议系统用户
			userConversion.MeetDelUser(meetreason, suserBean);
			//删除ftp用户
//			userConversion.FtpDelUser(userBean);
			//删除用户数据库表结构
//			initializationtableServices.DropUserTable(userBean.getLogname());
			//删除邮箱帐号信息
//			auxiliaryMailServices.DelMailSetup(setupmailBean);
			throw new Exception(e.getMessage());
		}
	}
	
	public void AddSubmitBasic(UserBean userBean) throws Exception{
		UserDAO userDAO =(UserDAO) SpringContextUtil.getBean("userDAO");
		userDAO.AddSubmitBasic(userBean);
	}
	
	public void AddSubmitContact(UserBean userBean) throws Exception{
		UserDAO userDAO =(UserDAO) SpringContextUtil.getBean("userDAO");
		userDAO.AddSubmitContact(userBean);
	}
	
	public void AddSubmitPhoto(UserBean userBean) throws Exception{
		UserDAO userDAO =(UserDAO) SpringContextUtil.getBean("userDAO");
		userDAO.AddSubmitPhoto(userBean);
	}
	
	public UserBean View(int id) throws Exception{
		UserDAO userDAO =(UserDAO) SpringContextUtil.getBean("userDAO");
		return userDAO.View(id);
	}
	
	public UserBean LogNameView(String logname) throws Exception{
		UserDAO userDAO =(UserDAO) SpringContextUtil.getBean("userDAO");
		return userDAO.LogNameView(logname);
	}
	
	public UserBean Upd(int id) throws Exception{
		UserDAO userDAO =(UserDAO) SpringContextUtil.getBean("userDAO");
		return userDAO.Upd(id);
	}
	
	public void UpdSubmit(UserBean userBean,UserBean suserBean,PurviewBean purviewBean) throws Exception{
		UserDAO userDAO =(UserDAO) SpringContextUtil.getBean("userDAO");
		UserConversion userConversion = (UserConversion) SpringContextUtil.getBean("userConversion");
		UserServices userServices = (UserServices) SpringContextUtil.getBean("userServices");
		
		UserBean olduserBean = userServices.View(Integer.parseInt(userBean.getId()));
		/**
		 * 修改会议系统用户
		 */
		Map map = userConversion.MeetUpdUser(userBean,suserBean,purviewBean);
		if("SUCCESS".equals(map.get("result"))){
			//将会议系统的用户id保存到用户信息表
			userBean.setMeetuserid(String.valueOf(map.get("reason")));
		}else if("FAILURE".equals(map.get("result"))){
			String errmessage = String.valueOf(map.get("reason"));
			System.err.println("errmessage:"+errmessage);
			throw new Exception(errmessage);
		}
		
		/**
		 * 对外通过接口保存ftp用户信息
		 */
//		try{
//			userConversion.FtpUpdUser(userBean);
//		}catch(Exception e){
//			//出错还原会议系统用户信息
//			userConversion.MeetUpdUser(olduserBean,suserBean,purviewBean);
//			throw new Exception(e.getMessage());
//		}
		
		try{
			userDAO.UpdSubmit(userBean);
		}catch(Exception e){
			//出错还原会议系统用户信息
			userConversion.MeetUpdUser(olduserBean,suserBean,purviewBean);
			//出错还原ftp用户信息
//			userConversion.FtpUpdUser(olduserBean);
			throw new Exception(e.getMessage());
		}
	}
	
	public void UpdSubmitBasic(UserBean userBean) throws Exception{
		UserDAO userDAO =(UserDAO) SpringContextUtil.getBean("userDAO");
		userDAO.UpdSubmitBasic(userBean);
	}
	
	public void UpdSubmitContact(UserBean userBean) throws Exception{
		UserDAO userDAO =(UserDAO) SpringContextUtil.getBean("userDAO");
		userDAO.UpdSubmitContact(userBean);
	}
	
	public void UpdSubmitPhoto(UserBean userBean) throws Exception{
		UserDAO userDAO =(UserDAO) SpringContextUtil.getBean("userDAO");
		userDAO.UpdSubmitPhoto(userBean);
	}
	
	public void UpdUserMail(UserBean userBean) throws Exception{
		UserDAO userDAO =(UserDAO) SpringContextUtil.getBean("userDAO");
		userDAO.UpdUserMail(userBean);
	}
	
	public void Del(int id,UserBean suserBean) throws Exception{
		UserDAO userDAO =(UserDAO) SpringContextUtil.getBean("userDAO");
		UserConversion userConversion = (UserConversion) SpringContextUtil.getBean("userConversion");
		UserServices userServices =(UserServices) SpringContextUtil.getBean("userServices");
		InitializationTableServices initializationtableServices = (InitializationTableServices) SpringContextUtil.getBean("initializationtableServices");
		AuxiliaryMailServices auxiliaryMailServices = (AuxiliaryMailServices) SpringContextUtil.getBean("auxiliaryMailServices");
		
		UserBean userBean = userServices.View(id);
//		SetupMailBean setupmailBean = auxiliaryMailServices.ViewMailSetupEmail(userBean.getEmail(), userBean.getLogname());
		
		/**
		 * 对外通过接口保存ftp用户信息
		 */
//		try{
//			userConversion.FtpDelUser(userBean);
//		}catch(Exception e){
//			throw new Exception(e.getMessage());
//		}
		
		/**
		 * 创建数据库表结构
		 */
//		try{
//			initializationtableServices.DropUserTable(userBean.getLogname());
//		}catch(Exception e){
//			throw new Exception(e.getMessage());
//		}
		
		/**
		 * 新增用户邮箱账号信息
		 */
//		try{
//			auxiliaryMailServices.DelMailSetup(setupmailBean);
//		}catch(Exception e){
//			throw new Exception(e.getMessage());
//		}
		
		try{
			userDAO.Del(id);
		}catch(Exception e){
			throw new Exception(e.getMessage());
		}
		
		/**
		 * 删除会议系统用户信息
		 */
		Map map = userConversion.MeetDelUser(userBean.getMeetuserid(),suserBean);
		if("SUCCESS".equals(map.get("result"))){
		}else if("FAILURE".equals(map.get("result"))){
			userDAO.UpdState(id);
			String errmessage = String.valueOf(map.get("reason"));
			System.err.println("errmessage:"+errmessage);
			throw new Exception(errmessage);
		}
		
	}
	
	public void DelBasic(String id) throws Exception{
		UserDAO userDAO =(UserDAO) SpringContextUtil.getBean("userDAO");
		userDAO.DelBasic(id);
	}
	
	public void DelContact(String id) throws Exception{
		UserDAO userDAO =(UserDAO) SpringContextUtil.getBean("userDAO");
		userDAO.DelContact(id);
	}
	
	public void DelPhoto(String id,UserBean suserBean) throws Exception{
		UserDAO userDAO =(UserDAO) SpringContextUtil.getBean("userDAO");
		userDAO.DelPhoto(id);
	}
	
	public void UpdPassword(UserBean userBean) throws Exception{
		UserDAO userDAO =(UserDAO) SpringContextUtil.getBean("userDAO");
		userDAO.UpdPassword(userBean);
	}
	
	public void ResetPassword(UserBean userBean,UserBean suserBean,PurviewBean purviewBean) throws Exception{
		UserDAO userDAO =(UserDAO) SpringContextUtil.getBean("userDAO");
		UserConversion userConversion = (UserConversion) SpringContextUtil.getBean("userConversion");
		String password = "888888";
		
		userBean.setPassword(password);
		/**
		 * 修改会议系统用户
		 */
		Map map = userConversion.MeetUpdUser(userBean,suserBean,purviewBean);
		if("SUCCESS".equals(map.get("result"))){
			//将会议系统的用户id保存到用户信息表
		}else if("FAILURE".equals(map.get("result"))){
			String errmessage = String.valueOf(map.get("reason"));
			System.err.println("errmessage:"+errmessage);
			throw new Exception(errmessage);
		}
		
		userDAO.ResetPassword(userBean.getId(),password);
	}
	
	public UserBean ViewCard(String userid) throws Exception{
		UserDAO userDAO =(UserDAO) SpringContextUtil.getBean("userDAO");
		return userDAO.ViewCard(userid);
	}
	
	public void AddCard(UserBean userBean) throws Exception{
		UserDAO userDAO =(UserDAO) SpringContextUtil.getBean("userDAO");
		userDAO.AddCard(userBean);
	}
	
	public void UpdCard(UserBean userBean) throws Exception{
		UserDAO userDAO =(UserDAO) SpringContextUtil.getBean("userDAO");
		userDAO.UpdCard(userBean);
	}
	
	public void DelCard(String id) throws Exception{
		UserDAO userDAO =(UserDAO) SpringContextUtil.getBean("userDAO");
		userDAO.DelCard(id);
	}
	
	public int ValidationLogname(String logname) throws Exception{
		UserDAO userDAO =(UserDAO) SpringContextUtil.getBean("userDAO");
		return userDAO.ValidationLogname(logname);
	}
	
	public int ValidationEmail(String email,String orgid,String id) throws Exception{
		UserDAO userDAO =(UserDAO) SpringContextUtil.getBean("userDAO");
		return userDAO.ValidationEmail(email, orgid, id);
	}
	
	public void UpdCardCZJE(String userid,String czje) throws Exception{
		UserDAO userDAO =(UserDAO) SpringContextUtil.getBean("userDAO");
		userDAO.UpdCardCZJE(userid, czje);
	}
}
