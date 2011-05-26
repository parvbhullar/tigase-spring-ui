package com.ivyinfo.im.services;

import java.util.List;
import java.util.Map;

import tigase.db.AuthorizationException;
import tigase.db.DBInitException;
import tigase.db.TigaseDBException;
import tigase.db.UserExistsException;
import tigase.db.UserNotFoundException;
import tigase.xmpp.BareJID;

import com.ivyinfo.framework.service.base.BaseService;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.im.dao.IIMDAO;
import com.ivyinfo.im.meettouch.services.IIMServices;

public class IMServicesImpl extends BaseService implements IIMServices {

	public void addDataList(BareJID user, String subnode, String key,
			String[] list) throws UserNotFoundException, TigaseDBException {
		// TODO Auto-generated method stub
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		UserStatusDAO.addDataList(user, subnode, key, list);
	}

	public void addUser(BareJID user, String password)
			throws UserExistsException, TigaseDBException {
		// TODO Auto-generated method stub
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		UserStatusDAO.addUser(user, password);
	}

	public void addUser(BareJID user) throws UserExistsException,
			TigaseDBException {
		// TODO Auto-generated method stub
		System.out.println("imservice adduser!!");
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		UserStatusDAO.addUser(user);
	}

	public boolean digestAuth(BareJID user, String digest, String id, String alg)
			throws UserNotFoundException, TigaseDBException,
			AuthorizationException {
		// TODO Auto-generated method stub
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		return UserStatusDAO.digestAuth(user, digest, id, alg);
	}

	public String getData(BareJID user, String subnode, String key, String def)
			throws UserNotFoundException, TigaseDBException {
		//System.out.println("imservice getdata 1");
		// TODO Auto-generated method stub
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		return UserStatusDAO.getData(user, subnode, key, def);
	}

	public String getData(BareJID user, String subnode, String key)
			throws UserNotFoundException, TigaseDBException {
		System.out.println("imservice getdata 2");
		// TODO Auto-generated method stub
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		return UserStatusDAO.getData(user, subnode, key, null);
	}

	public String getData(BareJID user, String key)
			throws UserNotFoundException, TigaseDBException {
		System.out.println("imservice getdata 3");
		// TODO Auto-generated method stub
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		return UserStatusDAO.getData(user, key);
	}

	public String[] getDataList(BareJID user, String subnode, String key)
			throws UserNotFoundException, TigaseDBException {
		// TODO Auto-generated method stub
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		
		return UserStatusDAO.getDataList(user, subnode, key);
	}

	public String[] getKeys(BareJID user, String subnode)
			throws UserNotFoundException, TigaseDBException {
		// TODO Auto-generated method stub
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		return UserStatusDAO.getKeys(user, subnode);
	}

	public String[] getKeys(BareJID user) throws UserNotFoundException,
			TigaseDBException {
		// TODO Auto-generated method stub
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		return UserStatusDAO.getKeys(user);
	}

	public String getResourceUri() {
		// TODO Auto-generated method stub
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		return UserStatusDAO.getResourceUri();
	}

	public String[] getSubnodes(BareJID user, String subnode)
			throws UserNotFoundException, TigaseDBException {
		// TODO Auto-generated method stub
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		return UserStatusDAO.getSubnodes(user, subnode);
	}

	public String[] getSubnodes(BareJID user) throws UserNotFoundException,
			TigaseDBException {
		// TODO Auto-generated method stub
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		return UserStatusDAO.getSubnodes(user);
	}

	public long getUserUID(BareJID user) throws TigaseDBException {
		// TODO Auto-generated method stub
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		return UserStatusDAO.getUserUID(user);
	}

	public List<BareJID> getUsers() throws TigaseDBException {
		// TODO Auto-generated method stub
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		return UserStatusDAO.getUsers();
	}

	public long getUsersCount() {
		// TODO Auto-generated method stub
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		return UserStatusDAO.getUsersCount();
	}

	public long getUsersCount(String domain) {
		// TODO Auto-generated method stub
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		return UserStatusDAO.getUsersCount(domain);
	}

	public void initRepository(String resourceUri, Map<String, String> params)
			throws DBInitException {
		System.out.println("imservice init!!!");
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		UserStatusDAO.initRepository(resourceUri, params);
		// TODO Auto-generated method stub
		
	}

	public void logout(BareJID user) throws UserNotFoundException,
			TigaseDBException {
		// TODO Auto-generated method stub
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		UserStatusDAO.logout(user);
	}

	public boolean otherAuth(Map<String, Object> authProps)
			throws UserNotFoundException, TigaseDBException,
			AuthorizationException {
		// TODO Auto-generated method stub
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		return UserStatusDAO.otherAuth(authProps);
	}

	public boolean plainAuth(BareJID user, String password)
			throws UserNotFoundException, TigaseDBException,
			AuthorizationException {
		// TODO Auto-generated method stub
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		return UserStatusDAO.plainAuth(user, password);
	}

	public void queryAuth(Map<String, Object> authProps) {
		// TODO Auto-generated method stub
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		UserStatusDAO.queryAuth(authProps);
	}

	public void removeData(BareJID user, String subnode, String key)
			throws UserNotFoundException, TigaseDBException {
		// TODO Auto-generated method stub
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		UserStatusDAO.removeData(user, subnode, key);
	}

	public void removeData(BareJID user, String key)
			throws UserNotFoundException, TigaseDBException {
		// TODO Auto-generated method stub
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		UserStatusDAO.removeData(user, key);
	}

	public void removeSubnode(BareJID user, String subnode)
			throws UserNotFoundException, TigaseDBException {
		// TODO Auto-generated method stub
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		UserStatusDAO.removeSubnode(user, subnode);
	}

	public void removeUser(BareJID user) throws UserNotFoundException,
			TigaseDBException {
		// TODO Auto-generated method stub
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		UserStatusDAO.removeUser(user);
	}

	public void setData(BareJID user, String subnode, String key, String value)
			throws UserNotFoundException, TigaseDBException {
		// TODO Auto-generated method stub
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		UserStatusDAO.setData(user, subnode, key, value);
	}

	public void setData(BareJID user, String key, String value)
			throws UserNotFoundException, TigaseDBException {
		// TODO Auto-generated method stub
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		UserStatusDAO.setData(user, key, value);
	}

	public void setDataList(BareJID user, String subnode, String key,
			String[] list) throws UserNotFoundException, TigaseDBException {
		// TODO Auto-generated method stub
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		UserStatusDAO.setDataList(user, subnode, key, list);
	}

	public void updatePassword(BareJID user, String password)
			throws UserNotFoundException, TigaseDBException {
		// TODO Auto-generated method stub
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		UserStatusDAO.updatePassword(user, password);
	}

	public boolean userExists(BareJID user) {
		// TODO Auto-generated method stub
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		return UserStatusDAO.userExists(user);
	}
	

	
	/*@SuppressWarnings("unchecked")
	public List getAllUserStatus() throws RuntimeException {
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		return UserStatusDAO.findAllUserStatus();
	}

	public void removeUserStatus(int id) throws RuntimeException {
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		UserStatusDAO.deleteUserStatus(id);
	}

	public void saveUserStatus(UserStatus UserStatusBean) throws RuntimeException {
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		UserStatusDAO.saveUserStatus(UserStatusBean);
	}

	public void updateUserStatus(UserStatus UserStatusBean) throws RuntimeException {
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		UserStatusDAO.updateUserStatus(UserStatusBean);
	}

	public UserStatus getUserStatusById(String user) throws RuntimeException {
		IIMDAO UserStatusDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		return (UserStatus)UserStatusDAO.findUserStatusById(user);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List findAllMeetTouchMessage() throws RuntimeException {
		IIMDAO MeetTouchMessageDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		return MeetTouchMessageDAO.findAllMeetTouchMessage();
	}

	public void deleteMeetTouchMessage(int id) throws RuntimeException {
		IIMDAO MeetTouchMessageDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		MeetTouchMessageDAO.deleteMeetTouchMessage(id);
	}

	public void saveMeetTouchMessage(MeetTouchMessage meetTouchMessageBean) throws RuntimeException {
		IIMDAO MeetTouchMessageDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		MeetTouchMessageDAO.saveMeetTouchMessage(meetTouchMessageBean);
	}

	public void updateMeetTouchMessage(MeetTouchMessage meetTouchMessageBean) throws RuntimeException {
		IIMDAO MeetTouchMessageDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		MeetTouchMessageDAO.updateMeetTouchMessage(meetTouchMessageBean);
	}

	public List findMeetTouchMessageById(MeetTouchMessage meetTouchMessageBean) throws RuntimeException {
		IIMDAO MeetTouchMessageDAO =(IIMDAO) SpringContextUtil.getBean("IMDAO");
		return MeetTouchMessageDAO.findMeetTouchMessageById(meetTouchMessageBean);
	}*/
	
}
