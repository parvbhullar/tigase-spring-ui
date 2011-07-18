package com.ivyinfo.user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ivyinfo.user.bean.UserBean;

public class UserDAOImpl extends SqlMapClientDaoSupport implements UserDAO{
	
	public int UserListCount(String state,String orgid,String conname,String convalue) throws Exception{
		Map map = new HashMap();
		map.put("type", "2");
		map.put("state", state);
		map.put("orgid", orgid);
		if(conname != null && convalue != null){
			String convalue1 = "like '%"+convalue+"%'";
			map.put("conname", conname);
			map.put("convalue", convalue1);
		}
		String s = (String)this.getSqlMapClientTemplate().queryForObject("usercount", map);
		return Integer.parseInt(s);
	}
	
	public List AllIndex(int from, int len, String state,String orgid,String order,String orderby,String conname,String convalue) throws Exception{
		Map map = new HashMap();
		String limit = String.valueOf(from) + "," + len;
		map.put("type", "2");
		map.put("state", state);
		map.put("orgid", orgid);
		if(order != null && orderby != null){
			map.put("order", order);
			map.put("orderby", orderby);
		}
		
		if(from > 0 && len > 0){
			map.put("from", from);
			map.put("len", len);
		}
		if(conname != null && convalue != null){
			String convalue1 = "like '%"+convalue+"%'";
			map.put("conname", conname);
			map.put("convalue", convalue1);
		}
		
		System.err.println("==========into AllIndex===============");
		
		return this.getSqlMapClientTemplate().queryForList("findAllUser", map);
	}
	
	public void AddSubmit(UserBean userBean) throws Exception{
		this.getSqlMapClientTemplate().insert("saveUser", userBean);
	}
	
	public void AddSubmitBasic(UserBean userBean) throws Exception{
		this.getSqlMapClientTemplate().insert("saveUserBasic", userBean);
	}
	
	public void AddSubmitContact(UserBean userBean) throws Exception{
		this.getSqlMapClientTemplate().insert("saveUserContact", userBean);
	}
	
	public void AddSubmitPhoto(UserBean userBean) throws Exception{
		this.getSqlMapClientTemplate().insert("saveUserPhoto", userBean);
	}
	
	public UserBean View(int id) throws Exception{
		Map map = new HashMap();
		map.put("id", id);
		return (UserBean) this.getSqlMapClientTemplate().queryForObject("findAllUser", map);
	}
	
	public UserBean LogNameView(String logname) throws Exception{
		Map map = new HashMap();
		map.put("logname", logname);
		return (UserBean) this.getSqlMapClientTemplate().queryForObject("findAllUser", map);
	}
	
	public UserBean Upd(int id) throws Exception{
		return (UserBean) this.getSqlMapClientTemplate().queryForObject("findUserById", id);
	}
	
	public void UpdSubmit(UserBean userBean) throws Exception{
		this.getSqlMapClientTemplate().update("updateUser", userBean);
	}
	
	public void UpdSubmitBasic(UserBean userBean) throws Exception{
		this.getSqlMapClientTemplate().update("updateUserBasic", userBean);
	}
	
	public void UpdSubmitContact(UserBean userBean) throws Exception{
		this.getSqlMapClientTemplate().update("updateUserContact", userBean);
	}
	
	public void UpdSubmitPhoto(UserBean userBean) throws Exception{
		this.getSqlMapClientTemplate().update("updateUserPhoto", userBean);
	}
	
	public void UpdUserMail(UserBean userBean) throws Exception{
		this.getSqlMapClientTemplate().update("updateUserEmail", userBean);
	}
	
	public void UpdState(int id) throws Exception{
		this.getSqlMapClientTemplate().update("updUserState", id);
	}
	
	public void Del(int id) throws Exception{
		//this.getSqlMapClientTemplate().delete("deleteUser", id);
		this.getSqlMapClientTemplate().update("deleteUserState", id);
	}
	
	public void DelBasic(String id) throws Exception{
		this.getSqlMapClientTemplate().delete("deleteUserBasic", id);
	}
	
	public void DelContact(String id) throws Exception{
		this.getSqlMapClientTemplate().delete("deleteUserContact", id);
	}
	
	public void DelPhoto(String id) throws Exception{
		this.getSqlMapClientTemplate().delete("deleteUserPhoto", id);
	}
	
	public void UpdPassword(UserBean userBean) throws Exception{
		this.getSqlMapClientTemplate().update("updateUserPassword", userBean);
	}
	
	public void ResetPassword(String id,String password) throws Exception{
		Map map = new HashMap();
		map.put("id", id);
		map.put("password", password);
		this.getSqlMapClientTemplate().update("ResetPassword", map);
	}
	
	public UserBean ViewCard(String userid) throws Exception{
		Map map = new HashMap();
		map.put("userid", userid);
		return (UserBean) this.getSqlMapClientTemplate().queryForObject("findUserCard", map);
	}
	
	public void AddCard(UserBean userBean) throws Exception{
		this.getSqlMapClientTemplate().insert("saveUserCard", userBean);
	}
	
	public void UpdCard(UserBean userBean) throws Exception{
		this.getSqlMapClientTemplate().update("updateUserCard", userBean);
	}
	
	public void DelCard(String id) throws Exception{
		this.getSqlMapClientTemplate().delete("deleteUserCard", id);
	}
	
	public int ValidationLogname(String logname) throws Exception{
		Map map = new HashMap();
		map.put("logname", logname);
		String s = (String)this.getSqlMapClientTemplate().queryForObject("ValidationLogname", map);
		return Integer.parseInt(s);
	}
	
	public int ValidationEmail(String email,String orgid,String id) throws Exception{
		Map map = new HashMap();
		map.put("email", email);
		map.put("orgid", orgid);
		map.put("id", id);
		String s = (String)this.getSqlMapClientTemplate().queryForObject("ValidationEmail", map);
		return Integer.parseInt(s);
	}
	
	public void UpdCardCZJE(String userid,String czje) throws Exception{
		Map map = new HashMap();
		map.put("userid", userid);
		map.put("czje", czje);
		this.getSqlMapClientTemplate().update("UpdCardCZJE", map);
	}
}
