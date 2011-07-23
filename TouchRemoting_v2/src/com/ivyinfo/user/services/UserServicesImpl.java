package com.ivyinfo.user.services;

import java.util.List;

import org.njdt.gg.ccl.datastructure.Dto;

import com.ivyinfo.framework.service.base.BaseService;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.user.bean.UserBean;
import com.ivyinfo.user.dao.UserDAO;

public class UserServicesImpl extends BaseService implements UserServices{

	public int UserListCount(String state,String orgid,String conname,String convalue) throws Exception{
		UserDAO userDAO =(UserDAO) SpringContextUtil.getBean("userDAO");
		return userDAO.UserListCount(state,orgid,conname,convalue);
	}
	
	public List AllIndex(int from, int len, String state,String orgid,String order,String orderby,String conname,String convalue) throws Exception{
		UserDAO userDAO =(UserDAO) SpringContextUtil.getBean("userDAO");
		return userDAO.AllIndex(from, len, state,orgid, order, orderby, conname, convalue);
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

	public void saveUserItem(Dto inDto) throws Exception {
		UserDAO userDAO =(UserDAO) SpringContextUtil.getBean("userDAO");
		this.iDao.insert("saveEausersubinfoItem", inDto);
//		userDAO.UpdCardCZJE(userid, czje);
	}
}
