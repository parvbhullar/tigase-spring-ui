package com.ivyinfo.user.services;

import java.util.List;

import org.njdt.gg.ccl.datastructure.Dto;

import com.ivyinfo.framework.service.base.IBaseService;
import com.ivyinfo.user.bean.UserBean;

public interface UserServices extends IBaseService{
	public int UserListCount(String state,String orgid,String conname,String convalue) throws Exception;
	
	public List AllIndex(int from, int len, String state,String orgid,String order,String orderby,String conname,String convalue) throws Exception;
	
	
	public void AddSubmitBasic(UserBean userBean) throws Exception;
	
	public void AddSubmitContact(UserBean userBean) throws Exception;
	
	public void AddSubmitPhoto(UserBean userBean) throws Exception;
	
	public UserBean View(int id) throws Exception;
	
	/**
	 * 通过登录名查询用户详细信息
	 * @param logname
	 * @return
	 * @throws Exception
	 */
	public UserBean LogNameView(String logname) throws Exception;
	
	public UserBean Upd(int id) throws Exception;
	
	
	public void UpdSubmitBasic(UserBean userBean) throws Exception;
	
	public void UpdSubmitContact(UserBean userBean) throws Exception;
	
	public void UpdSubmitPhoto(UserBean userBean) throws Exception;
	
	/**
	 * 修改用户邮箱
	 * @param userBean
	 * @throws Exception
	 */
	public void UpdUserMail(UserBean userBean) throws Exception;
	
	public void DelBasic(String id) throws Exception;
	
	public void DelContact(String id) throws Exception;
	
	public void DelPhoto(String id,UserBean suserBean) throws Exception;
	
	public void UpdPassword(UserBean userBean) throws Exception;
	
	
	/**
	 * 查询用户webcall冲值信息
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public UserBean ViewCard(String userid) throws Exception;
	
	/**
	 * 新增用户webcall冲值信息
	 * @param userBean
	 * @throws Exception
	 */
	public void AddCard(UserBean userBean) throws Exception;
	
	/**
	 * 修改用户webcall冲值信息
	 * @param userBean
	 * @throws Exception
	 */
	public void UpdCard(UserBean userBean) throws Exception;
	
	/**
	 * 删除用户webcall冲值信息
	 * @param id
	 * @throws Exception
	 */
	public void DelCard(String id) throws Exception;
	
	/**
	 * 验证登录名重复
	 * @param logname
	 * @return 返回count数，0：不重复，1：重复
	 * @throws Exception
	 */
	public int ValidationLogname(String logname) throws Exception;
	
	/**
	 * 验证邮箱重复
	 * @param email
	 * @param orgid
	 * @return
	 * @throws Exception
	 */
	public int ValidationEmail(String email,String orgid,String id) throws Exception;
	
	/**
	 * 修改用户冲值金额
	 * @param userid
	 * @param czje
	 * @throws Exception
	 */
	public void UpdCardCZJE(String userid,String czje) throws Exception;
	
	/*
	 * 
	 */
	public void saveUserItem(Dto inDto) throws Exception;
}
