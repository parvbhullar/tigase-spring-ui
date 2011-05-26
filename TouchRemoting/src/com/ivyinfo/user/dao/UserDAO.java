package com.ivyinfo.user.dao;

import java.util.List;

import com.ivyinfo.framework.service.base.IBaseDAO;
import com.ivyinfo.user.bean.UserBean;

public interface UserDAO extends IBaseDAO{
	public int UserListCount(String state,String orgid,String conname,String convalue) throws Exception;
	
	public List AllIndex(int from, int len, String state,String orgid,String order,String orderby,String conname,String convalue) throws Exception;
	
	public void AddSubmit(UserBean userBean) throws Exception;
	
	public void AddSubmitBasic(UserBean userBean) throws Exception;
	
	public void AddSubmitContact(UserBean userBean) throws Exception;
	
	public void AddSubmitPhoto(UserBean userBean) throws Exception;
	
	public UserBean View(int id) throws Exception;
	
	public UserBean LogNameView(String logname) throws Exception;
	
	public UserBean Upd(int id) throws Exception;
	
	public void UpdSubmit(UserBean userBean) throws Exception;
	
	public void UpdSubmitBasic(UserBean userBean) throws Exception;
	
	public void UpdSubmitContact(UserBean userBean) throws Exception;
	
	public void UpdSubmitPhoto(UserBean userBean) throws Exception;
	
	public void UpdUserMail(UserBean userBean) throws Exception;
	
	public void UpdState(int id) throws Exception;
	
	public void Del(int id) throws Exception;
	
	public void DelBasic(String id) throws Exception;
	
	public void DelContact(String id) throws Exception;
	
	public void DelPhoto(String id) throws Exception;
	
	public void UpdPassword(UserBean userBean) throws Exception;
	
	public void ResetPassword(String id,String password) throws Exception;
	
	public UserBean ViewCard(String userid) throws Exception;
	
	public void AddCard(UserBean userBean) throws Exception;
	
	public void UpdCard(UserBean userBean) throws Exception;
	
	public void DelCard(String id) throws Exception;
	
	public int ValidationLogname(String logname) throws Exception;
	
	public int ValidationEmail(String email,String orgid,String id) throws Exception;
	
	public void UpdCardCZJE(String userid,String czje) throws Exception;
}
