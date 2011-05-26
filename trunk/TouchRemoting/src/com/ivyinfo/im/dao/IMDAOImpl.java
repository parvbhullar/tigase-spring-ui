package com.ivyinfo.im.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import tigase.db.AuthorizationException;
import tigase.db.DBInitException;
import tigase.db.TigaseDBException;
import tigase.db.UserExistsException;
import tigase.db.UserNotFoundException;
import tigase.util.Algorithms;
import tigase.xmpp.BareJID;
import com.ivyinfo.framework.common.time.TimeTools;
import com.ivyinfo.framework.service.sequence.SequenceInterface;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.imcontacts.bean.IMContactsBean;
import com.ivyinfo.imcontacts.services.IMContactsServices;
import com.ivyinfo.imcontactsnode.bean.IMContactsNodeBean;
import com.ivyinfo.imcontactsnode.services.IMContactsNodeServices;

public class IMDAOImpl extends SqlMapClientDaoSupport implements IIMDAO {
	//private AuthRepository auth = null;
	private boolean autoCreateUser = false;
	// Cache moved to connection pool
	private Map<String, Object> cache = null;
	
	/** Field description */
	public static final String DEF_NODES_TBL = "tig_nodes";
	
	/** Field description */
	public static final String DEF_ROOT_NODE = "root";
	
	private static final String PASSWORD_KEY = "password";
	
	//private static final String[] non_sasl_mechs = { "password", "digest" };
	//private static final String[] sasl_mechs = { "PLAIN", "DIGEST-MD5", "CRAM-MD5" };

	public void addUser(BareJID user, String password)
			throws UserExistsException, TigaseDBException {
		// TODO Auto-generated method stub
		System.out.println("imdao addUser : " + user.toString() + "password :" + password);
		addUser(user);
		updatePassword(user, password);
	}

	public boolean digestAuth(BareJID user, String digest, String id, String alg)
			throws UserNotFoundException, TigaseDBException,
			AuthorizationException {
		// TODO Auto-generated method stub
		final String db_password = getPassword(user);
		System.out.println("digestAuth : psw : " + db_password);
		try {
			final String digest_db_pass = Algorithms.hexDigest(id, db_password, alg);
			System.out.println("digestAuth : " + digest.equals(digest_db_pass));
			return digest.equals(digest_db_pass);
		} catch (NoSuchAlgorithmException e) {
			throw new AuthorizationException("No such algorithm.", e);
		}    // end of try-catch
	}

	public String getResourceUri() {
		// TODO Auto-generated method stub
		System.out.println("getResourceUri!");
		return null;
	}

	public long getUsersCount() {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		map.put("ucount", 0);
		try{
			Object o  = (this.getSqlMapClientTemplate().queryForObject("getUserCount"));
			long ucount = -1;
			if(o!=null){
				ucount = ((Long)o).longValue();
			}
			System.out.println("user count : " + ucount);
			return ucount;
		}catch( DataAccessException e){
				return -1;
			}
	}

	public long getUsersCount(String domain) {
		// TODO Auto-generated method stub
		long users = -1;
		String udomain = "%@" + domain;
		try{
			Object o = this.getSqlMapClientTemplate().queryForObject("getUserCountFromDomain",udomain);
			if(o!=null){
				users = ((Long)o).longValue();
				System.out.println("imdao ucount : " + users + " domain : " + domain);
			}
		}catch(DataAccessException e ){
			return -1;
		}
		return users;
	}

	public void initRepository(String connection_str, Map<String, String> params)
			throws DBInitException {
		// TODO Auto-generated method stub
		if (connection_str.contains("autoCreateUser=true")) {
			autoCreateUser = true;
		}    // end of if (db_conn.contains())

		if (connection_str.contains("cacheRepo=off")) {
			cache = Collections.synchronizedMap(new LinkedHashMap<String, Object>(0));
		} else {
			cache = Collections.synchronizedMap(new LinkedHashMap<String, Object>(1000));
		}
	}

	public void logout(BareJID user) throws UserNotFoundException,
			TigaseDBException {
		// TODO Auto-generated method stub
		System.out.println("logout! : " +user.toString());
	}

	public boolean otherAuth(Map<String, Object> authProps)
			throws UserNotFoundException, TigaseDBException,
			AuthorizationException {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean plainAuth(BareJID user, String password)
			throws UserNotFoundException, TigaseDBException,
			AuthorizationException {
		// TODO Auto-generated method stub
		System.out.println("plainAuth!! : " + user + " psw :" + password);
		String db_password = getData(user, PASSWORD_KEY);
		return (password != null) && (db_password != null) && db_password.equals(password);
	}

	public void queryAuth(Map<String, Object> authProps) {
		// TODO Auto-generated method stub
	}

	public void removeUser(BareJID user) throws UserNotFoundException,
			TigaseDBException {
		// TODO Auto-generated method stub
		System.out.println("removeUser : " + user.toString());
		try {
			long uid = getUserUID(user, autoCreateUser);
			if(uid > 0){
				//从pair表中删除用户信息
				IMContactsServices imcService = (IMContactsServices) SpringContextUtil.getBean("imcontactsServices");
				IMContactsBean imcBean = new IMContactsBean();
				String logName = user.toString();
				if(user.toString().indexOf("@")>-1)
					logName =logName.substring(0,logName.indexOf("@"));
				imcBean.setLogname(logName);
				imcBean.setUid(String.valueOf(uid));
				try {
					imcService.DelIMContacts(imcBean);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new TigaseDBException("Error removing user from repository: " +  e);
				}					
				
				//删除node表中uid对应信息
				IMContactsNodeServices imcnService = (IMContactsNodeServices) SpringContextUtil.getBean("imcontactsNodeServices");
				IMContactsNodeBean imcnBean = new IMContactsNodeBean();
				imcnBean.setLogname(logName);
				imcnBean.setUid(String.valueOf(uid));
				try {
					imcnService.DelIMContactsNode(imcnBean);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new TigaseDBException("Error removing user from repository: " +  e);
				}
				//从用户登录表中删除用户信息
				this.getSqlMapClientTemplate().delete("deleteUserFromUserTb", uid);
				System.out.println("delete data OK");
			}
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new TigaseDBException("Error removing user from repository: " +  e);
		}catch(SQLException e ){
			throw new TigaseDBException("Error removing user from repository: " +  e);
		}
		finally{
			cache.remove(user.toString());
		}
	}

	public void updatePassword(BareJID user, String password)
			throws UserNotFoundException, TigaseDBException {
		// TODO Auto-generated method stub
		setData(user, PASSWORD_KEY, password);
	}

	public void addDataList(BareJID user, String subnode, String key,
			String[] list) throws UserNotFoundException, TigaseDBException {
		// TODO Auto-generated method stub
		for(int i = 0;i < list.length;i++)
			System.out.println("imdao addDataList : " + user + " subnode :" + subnode + " key :" + key + " list :" + list[i]);
	    if(!key.trim().equals("password")){
			long uid = -2;
			long nid = -2;
			try {
				uid = getUserUID(user, autoCreateUser);
				nid = getNodeNID(user,uid, subnode);
				if (nid < 0) {
					try {
						nid = createNodePath(user, subnode);
					} catch (Exception e) {
						// This may happen in cluster node, when 2 nodes at the same
						// time write data to the same location, like offline messages....
						// Let's try to get the nid again.
						nid = getNodeNID(user,uid, subnode);
					}
				}
				//insert the key and value
				IMContactsServices imcService = (IMContactsServices) SpringContextUtil.getBean("imcontactsServices");
				IMContactsBean imcBean = new IMContactsBean();
				String logName = user.toString();
				if(user.toString().indexOf("@")>-1)
					logName =logName.substring(0,logName.indexOf("@"));
				System.out.println(logName);
				imcBean.setLogname(logName);
				imcBean.setPkey(key);
				imcBean.setNid(String.valueOf(nid));
				imcBean.setUid(String.valueOf(uid));
				
				for(String val : list){
					imcBean.setPval(val);
						try {
							imcService.AddIMContacts(imcBean);
						} catch (Exception e) {
							e.printStackTrace();
							throw new TigaseDBException("Error adding data list, user_id: " + user + ", subnode: "
									+ subnode + ", key: " + key + ", uid: " + uid + ", nid: " + nid + ", list: "
										+ Arrays.toString(list), e);
						}
				}			
			}catch(SQLException e){
				throw new TigaseDBException("Error adding data list, user_id: " + user + ", subnode: "
						+ subnode + ", key: " + key + ", uid: " + uid + ", nid: " + nid + ", list: "
							+ Arrays.toString(list), e);
			}
		}
		else{
			addPassword(user,subnode,key,list);		
		}
		System.out.println("addDataList OK!!!!");
	}
	
	private void addPassword(BareJID user,String subnode, String key,String[] list)throws TigaseDBException{
		//添加密码
		String logName = user.toString();
		if(logName.indexOf("@")>-1)
			logName = logName.substring(0, logName.indexOf("@"));
		Map map = new HashMap();
		map.put("password", list[0]);
		map.put("logname", logName);
		try{
			this.getSqlMapClientTemplate().update("updateUserPSW", map);
		}catch (DataAccessException e) {
			throw new TigaseDBException("Error adding data list, user_id: " + user + ", subnode: "
					+ subnode + ", key: " + key  + ", list: "
						+ Arrays.toString(list), e);
		}
	}

	public void addUser(BareJID user) throws UserExistsException,
			TigaseDBException {
		// TODO Auto-generated method stub
		System.out.println("imdao adduser: " + user);
		try {
			addUserRepo(user);
		} catch (SQLException e) {
			throw new UserExistsException("Error adding user to repository: ", e);
		}
	}

	public String getData(BareJID user, String subnode, String key, String def)
			throws UserNotFoundException, TigaseDBException {
		// TODO Auto-generated method stub
		System.out.println("imdao getData1 : " +user + " subnode : " + subnode + " key :" + key + " def :" + def);
		try{
			String result = def;
			if(!key.trim().equals("password")){
				//若不是查询用户密码
				long nid = getNodeNID(user, subnode);
				System.out.println("getData1 nid :" + nid);
				if(nid > 0){					
					String logName = user.toString();
					if(user.toString().indexOf("@")>-1)
						logName =logName.substring(0,logName.indexOf("@"));
					IMContactsBean imcBean = new IMContactsBean();
					imcBean.setLogname(logName);
					imcBean.setNid(String.valueOf(nid));
					imcBean.setPkey(key);
					IMContactsServices imcService = (IMContactsServices) SpringContextUtil.getBean("imcontactsServices");
					List resultList = null;
					try {
						resultList = imcService.getListIMContacts(imcBean);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						throw new TigaseDBException("Error getting user data for: " + user + "/" + subnode + "/"
								+ key, e);
					}
					if(resultList!=null&&resultList.size()>0){
						imcBean = (IMContactsBean) resultList.get(0);
						result = imcBean.getPval();
					}
					System.out.println("getData1  result : " + result);
					return result;
				}
				else{
					System.out.println("getData : no result!!!");
					return def;
				}	
			}else{
				//查找密码
				return getPassword(user,subnode,key,def);
			}
			
		}catch(SQLException e){
			throw new TigaseDBException("Error getting user data for: " + user + "/" + subnode + "/"
					+ key, e);
		}
	}
	
	private String getPassword(BareJID user, String subnode, String key, String def)throws TigaseDBException{
		//若是查询用户密码，则到另一张表查询！！！
		String result = def;
		String userid = user.toString();
		if(userid.indexOf("@")>-1)
			userid = userid.substring(0, userid.indexOf("@"));
		try{
			Object o =  this.getSqlMapClientTemplate().queryForObject(
					  "getPSW", userid);
			if(o != null){
				result = (String)o;
				//若存在用户，则查找其是否存在根节点，不存在则添加
				long uid = getUserUID(user);
				long nid = getNodeNID(user,subnode);
				if(nid < 0 ){
					System.out.println("创建根节点！！！");
					this.addNode(user, uid, -1, "root");
				}
			}
		}catch(Exception e){
			throw new TigaseDBException("Error getting user data for: " + user + "/" + subnode + "/"
					+ key, e);
		}
		
		return result;
	}

	public String getData(BareJID user, String subnode, String key)
			throws UserNotFoundException, TigaseDBException {
		// TODO Auto-generated method stub
		System.out.println("getData2");
		return getData(user, subnode, key, null);
	}

	public String getData(BareJID user, String key)
			throws UserNotFoundException, TigaseDBException {
		// TODO Auto-generated method stub
		System.out.println("imdao getData3 :" + "userid : " + user.toString()+" key : " + key);
		return getData(user, null, key, null);
	}

	public String[] getDataList(BareJID user, String subnode, String key)
			throws UserNotFoundException, TigaseDBException {
		// TODO Auto-generated method stub
		System.out.println("getDataList!! : " + user + " subnode :" + subnode + " key: " + key);
		try {
			long nid = getNodeNID(user, subnode);
			if(nid > 0){
				List<String> results = new ArrayList<String>();
				String logName = user.toString().substring(0,user.toString().indexOf("@"));
				IMContactsBean imcBean = new IMContactsBean();
				imcBean.setLogname(logName);
				imcBean.setNid(String.valueOf(nid));
				imcBean.setPkey(key);
				IMContactsServices imcService = (IMContactsServices) SpringContextUtil.getBean("imcontactsServices");
				List resultList = null;
				try {
					resultList = imcService.getListIMContacts(imcBean);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new TigaseDBException("Error getting user data for: " + user + "/" + subnode + "/"
							+ key, e);
				}
				if(resultList != null&&resultList.size()>0){
					for(int i = 0;i < resultList.size();i++){
						imcBean = (IMContactsBean) resultList.get(i);
						String s = imcBean.getPval();
						results.add(s);
					}
				}
				String[] result = (results.size() == 0)
								? null : results.toArray(new String[results.size()]);
				
				if(result != null ){
					for(int i = 0;i < result.length;i++)
						System.out.println("getDataList result"+i + " :" + result[i]);
				}
				else
					System.out.println("getDataList result : no reuslt");
				return result;
			}
		} catch(SQLException e ){
			throw new TigaseDBException("Error getting data list for: " + user + "/" + subnode + "/"
					+ key, e);
		}
		return null;
	}

	public String[] getKeys(BareJID user, String subnode)
			throws UserNotFoundException, TigaseDBException {
		// TODO Auto-generated method stub
		System.out.println("getKeys!! " + user + "subnode : " + subnode);
		try {
			long nid = getNodeNID(user, subnode);
			if(nid > 0){
				List<String> results = new ArrayList<String>();
				List list = this.getSqlMapClientTemplate().queryForList("findKeys", nid);
				if(list!=null){
					Map map = null;
					for(int i = 0;i < list.size();i++){
						map = (Map)list.get(i);
						String key = (String) map.get("key");
						results.add(key);
					}
				}
				return (results.size() == 0) ? null : results.toArray(new String[results.size()]);
			}
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new TigaseDBException("Error getting subnodes list.", e);
		}catch(SQLException e ){
			throw new TigaseDBException("Error getting subnodes list.", e);
		}
		return null;
	}

	public String[] getKeys(BareJID user) throws UserNotFoundException,
			TigaseDBException {
		// TODO Auto-generated method stub
		System.out.println("getKey2!!! user : " + user);
		return getKeys(user, null);
	}

	public String[] getSubnodes(BareJID user, String subnode)
			throws UserNotFoundException, TigaseDBException {
		// TODO Auto-generated method stub
		System.out.println("imdao : subnodes : " + subnode + " user : " + user);
		try {
			long nid = getNodeNID(user, subnode);
			if(nid > 0){
				IMContactsNodeServices imcnService = (IMContactsNodeServices) SpringContextUtil.getBean("imcontactsNodeServices");
				IMContactsNodeBean imcnBean = new IMContactsNodeBean();
				List<String> results = new ArrayList<String>();
				//处理用户名
				String logName = user.toString();
				if(!logName.contains("-pc")&&logName.indexOf("@")>-1)
					logName=logName.substring(0,logName.indexOf("@"));
				
				imcnBean.setLogname(logName);
				imcnBean.setParent_nid(String.valueOf(nid));

				List list = null;
				try {
					list = imcnService.getListIMContactsNode_PidORNode(imcnBean);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new TigaseDBException("Error getting subnodes list.", e);
				}
				if(list != null){
					for(int i = 0;i < list.size();i++){
						imcnBean = (IMContactsNodeBean)list.get(i);
						results.add(imcnBean.getNode());
					}
				}
				
				String[] result = (results.size() == 0)
				? null : results.toArray(new String[results.size()]);
				
				if(result != null ){
					for(int i = 0;i < result.length;i++)
						System.out.println("getSubNodes result"+i + " :" + result[i]);
				}
				else
					System.out.println("getSubnodes result : no reuslt");
				
				return (results.size() == 0) ? null : results.toArray(new String[results.size()]);
			}else
				System.out.println("no subnode!!!!");
			
		}catch(SQLException e ){
			throw new TigaseDBException("Error getting subnodes list.", e);
		}
		return null;
	}

	public String[] getSubnodes(BareJID user) throws UserNotFoundException,
			TigaseDBException {
		// TODO Auto-generated method stub
		return getSubnodes(user,null);
	}

	public long getUserUID(BareJID user) throws TigaseDBException {
		// TODO Auto-generated method stub
		System.out.println("getUserUID1");
		Object cache_res =  cache.get(user.toString());

		if (cache_res != null) {
			System.out.println("getUserUID1: cash find");
			return ((Long)cache_res).longValue();
		}    // end of if (result != null)
		System.out.println("getUserUID1 : cash no find");
		
		long result = -1;
		String userid = user.toString();		
		//区分虚拟用户和真实用户
		String ustring = user.toString().trim();
		if(ustring.equals("vhost-manager")||ustring.equals("multi-user-chat")||ustring.startsWith("srecv@")){
			System.out.println("查询虚拟用户！！！");
			try{
				Object o = this.getSqlMapClientTemplate().queryForObject("getUid2", userid);
				if(o!=null){
					result = ((Long)o).longValue();	
					cache.put(user.toString(), Long.valueOf(result));
				}
			}catch(DataAccessException e ){
				throw new TigaseDBException("Error retrieving user UID from repository: ", e);
			}
		}
		else{
			System.out.println("查询普通用户！！");
			try{
				//处理用户名
				if(userid.indexOf("@")>-1)
					userid = userid.substring(0, userid.indexOf("@"));
				Object o = this.getSqlMapClientTemplate().queryForObject("getUid", userid);
				if(o!=null){
					result = ((Long)o).longValue();	
					cache.put(user.toString(), Long.valueOf(result));
				}
			}catch(DataAccessException e ){
				throw new TigaseDBException("Error retrieving user UID from repository: ", e);
			}
		}
		return result;
	}

	public List<BareJID> getUsers() throws TigaseDBException {
		// TODO Auto-generated method stub
		System.out.println("getAllUsers!!");
		try{
			List<BareJID> users = null;
			List list = this.getSqlMapClientTemplate().queryForList("getAllUsers");
			if(list!=null){
				users = new ArrayList<BareJID>(1000);
				Map m  = null;
				for(int i = 0;i < list.size();i++){
					m = (Map) list.get(i);
					users.add(BareJID.bareJIDInstanceNS(processLoginName((String)(m.get("userid")))));
				}
				return users;
			}
		}catch (DataAccessException e) {
			throw new TigaseDBException("Problem loading user list from repository", e);
		}
		return null;
	}
	
	private String processLoginName(String logName){
		//此处需要将加上服务器域名，待修改
		return logName + "@127.0.0.1";
	}

	public void removeData(BareJID user, String subnode, String key)
			throws UserNotFoundException, TigaseDBException {
		// TODO Auto-generated method stub
		System.out.println("removeData : " + user + " subnode : " + subnode + " key :" +key);
		if(!key.trim().equals("password"))	{
			try {
				long nid = getNodeNID(user, subnode);
				if(nid > 0){
					IMContactsServices imcService = (IMContactsServices) SpringContextUtil.getBean("imcontactsServices");
					IMContactsBean imcBean = new IMContactsBean();
					String logName = user.toString();
					if(user.toString().indexOf("@")>-1)
						logName =logName.substring(0,logName.indexOf("@"));
					imcBean.setLogname(logName);
					imcBean.setNid(String.valueOf(nid));
					imcBean.setPkey(key);
					try {
						imcService.DelIMContacts(imcBean);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						throw new TigaseDBException("Error getting subnodes list.", e);
					}					
					System.out.println("delete data OK");
				}else
					System.out.println("no delete because there is no node!!");
			} 
			catch(SQLException e ){
				throw new TigaseDBException("Error getting subnodes list.", e);
			}
		}
		System.out.println("removeData OK!!!");
	}
	
	public void removeData(BareJID user, String key)
			throws UserNotFoundException, TigaseDBException {
		// TODO Auto-generated method stub
		removeData(user, null, key);
	}

	public void removeSubnode(BareJID user, String subnode)
			throws UserNotFoundException, TigaseDBException {
		// TODO Auto-generated method stub
		System.out.println("removesubnode : " + user.toString() + " subnode :" + subnode);
		if(subnode == null)
			return;
		
		try {
			long nid = getNodeNID(user, subnode);
			if(nid > 0){
				deleteSubnode(user,nid);
				cache.remove(user + "/" + subnode);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new TigaseDBException("Error delete subnode .", e);
		}
		
	}

	public void setData(BareJID user, String subnode, String key, String value)
			throws UserNotFoundException, TigaseDBException {
		// TODO Auto-generated method stub
		System.out.println("setData1");
		setDataList(user, subnode, key, new String[] { value });
	}

	public void setData(BareJID user, String key, String value)
			throws UserNotFoundException, TigaseDBException {
		// TODO Auto-generated method stub
		System.out.println("setData2");
		setData(user, null, key, value);
	}

	public void setDataList(BareJID user, String subnode, String key,
			String[] list) throws UserNotFoundException, TigaseDBException {
		// TODO Auto-generated method stub
		removeData(user, subnode, key);
		addDataList(user, subnode, key, list);
	}

	public boolean userExists(BareJID user) {
		// TODO Auto-generated method stub
		System.out.println("userExists : " + user.toString());
		try {
			long f = getUserUID(user, false);
			if(f > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	private long addUserRepo(BareJID user_id) throws SQLException, TigaseDBException {
		ResultSet rs = null;
		long uid = -1;
		//先查找是否有该用户
		Map map = new HashMap();
		System.out.println("addUserRepo : " + user_id);
		map.put("userid",user_id.toString());
		map.put("uid", 0);
		long flag = getUserUID(user_id);
		//若该用户不存在
		if(flag==-1){
			try{
				String ustring = user_id.toString();
				if(ustring.equals("vhost-manager")||ustring.equals("multi-user-chat")||ustring.startsWith("srecv@")){
					System.out.println("增加虚拟用户");
					map = new HashMap();
					map.put("userid", user_id.toString());
					map.put("userid2", user_id.toString());
					map.put("psw", "");
					//执行SQL操作，获得返回值	
					this.getSqlMapClientTemplate().insert("addUser", map);
					uid = getUserUID(user_id);
					//为虚拟用户增加根节点
					this.addNode(user_id,uid, -1, "root");
				}
				else{
					//获得下一个添加的uid
					SequenceInterface sequenceService = (SequenceInterface) SpringContextUtil.getBean("Sequence");
					uid = sequenceService.getMaxId("tig_users");
					System.out.println("add_user user : " + uid);
					
					System.out.println("增加正常用户");
					map = new HashMap();
					map.put("uid", uid);
					//处理用户名，去掉后面的域名部分
					String loginName = user_id.toString();
					if(loginName.indexOf("@")>-1)
						loginName = loginName.substring(0, loginName.indexOf("@"));
					map.put("logname", loginName);
					map.put("nickname", "");
					map.put("psw", "");
					map.put("upduserid", "1");
					map.put("timestemp", TimeTools.getDateTime_webCall());
					map.put("dirtyflag", TimeTools.getTimestampMilliSecond());
					map.put("state", "2");
					map.put("type", "0");
					map.put("meetuserid", "1");
					this.getSqlMapClientTemplate().insert("addUser2", map);
					uid = getUserUID(user_id);
				}

			}catch(Exception e){
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new TigaseDBException("Propeblem adding new user to repository. "
						+ "The SP should return uid or fail");
			}

			cache.put(user_id.toString(), Long.valueOf(uid));
		}
		else{
			uid = flag;
			System.out.println("user exist!!! ");
			cache.put(user_id.toString(), Long.valueOf(uid));
			//抛出异常，通知服务器
			throw new SQLException();
		}
		return uid;
	}
	
	private long getNodeNID(BareJID user_id, String node_path)
			throws SQLException, UserNotFoundException, TigaseDBException {
		System.out.println("imdao getNodeNID2");
		Object cache_res =  cache.get(user_id + "/" + node_path);
		
		if (cache_res != null) {
			return ((Long)cache_res).longValue();
		}    // end of if (result != null)
		
		long uid = getUserUID(user_id, autoCreateUser);
		System.out.println("getNodeNID2 :　uid :" + uid);
		long result = getNodeNID(user_id,uid, node_path);
		System.out.println("getNodeNID2 : nid : " + result);
		if (result > 0) {
			cache.put(user_id + "/" + node_path, Long.valueOf(result));
		}    // end of if (result > 0)
		
		return result;
	}
	
	private long getNodeNID(BareJID userid ,long uid, String node_path)
			throws SQLException, TigaseDBException, UserNotFoundException {
		//System.out.println("getNodeNID1");
		long nid = -1;
		nid = buildNodeQuery(userid,uid, node_path);
		return nid;
	}
	
	private long buildNodeQuery(BareJID userid ,long uid, String node_path) throws SQLException {
		System.out.println("buildNodeQuery");		
		//获得root接点的nid
		String logName = userid.toString();
		if(!logName.contains("-pc")&&logName.indexOf("@")>-1)
			logName=logName.substring(0,logName.indexOf("@"));
		
		IMContactsNodeServices imcnService = (IMContactsNodeServices) SpringContextUtil.getBean("imcontactsNodeServices");
		IMContactsNodeBean imcnBean = new IMContactsNodeBean();
		imcnBean.setLogname(logName);
		imcnBean.setParent_nid("0");
		imcnBean.setNode("root");
		List nodes = null;
		long rootnid = -1;
		try{
			nodes = imcnService.getListIMContactsNode_PidORNode(imcnBean);
			if(nodes!=null&&nodes.size()>0){
				imcnBean = (IMContactsNodeBean) nodes.get(0);
				rootnid = Long.parseLong(imcnBean.getNid());
			}
			else{
				return -1;
			}
			System.out.println("build root nid : " + rootnid);
			if (node_path == null) {
				return rootnid;
			} else {
				//获得子节点
				StringTokenizer strtok = new StringTokenizer(node_path, "/", false);
				int cnt = 1;
				long nid = rootnid;
				while (strtok.hasMoreTokens()) {
					String token = strtok.nextToken();	
					imcnBean = new IMContactsNodeBean();
					imcnBean.setLogname(logName);
					imcnBean.setParent_nid(String.valueOf(nid));
					imcnBean.setNode(token);
					nodes = imcnService.getListIMContactsNode_PidORNode(imcnBean);
					if(nodes!=null&&nodes.size()>0){
						imcnBean = (IMContactsNodeBean) nodes.get(0);
						nid = Long.parseLong(imcnBean.getNid());
					}else{
						nid = -1;
						break;
					}
				}    // end of while (strtok.hasMoreTokens())
	
				return nid;
			}      // end of else
		}catch(DataAccessException e){
			throw new SQLException();
		}catch(Exception e){
			e.printStackTrace();
			throw new SQLException();
		}
	}
	
	private long getUserUID(BareJID user_id, boolean autoCreate)
		throws SQLException, UserNotFoundException, TigaseDBException {
		System.out.println("getUserUID2 ： autoCreate : " + autoCreate);
		long result = getUserUID(user_id);
		System.out.println("getUserUID2 : user_id : " + result);
		if (result <= 0) {
			if (autoCreate) {
				result = addUserRepo(user_id);
			} else {
				throw new UserNotFoundException("User does not exist: " + user_id);
			}    // end of if (autoCreate) else
		}      // end of if (isnext) else
		
		return result;
	}
	
	private long createNodePath(BareJID user_id, String node_path)
			throws SQLException, UserNotFoundException, TigaseDBException{
		System.out.println("createNodePath : " + user_id.toString() + " node_path :" + node_path);
		if (node_path == null) {
			// Or should I throw NullPointerException?
			return getNodeNID(user_id, null);
		}    // end of if (node_path == null)
		long uid = getUserUID(user_id, autoCreateUser);
		long nid = getNodeNID(user_id,uid, null);
		
		StringTokenizer strtok = new StringTokenizer(node_path, "/", false);
		StringBuilder built_path = new StringBuilder();
		
		while (strtok.hasMoreTokens()) {
			String token = strtok.nextToken();

			built_path.append("/").append(token);

			long cur_nid = getNodeNID(user_id,uid, built_path.toString());

			if (cur_nid > 0) {
				nid = cur_nid;
			} else {
				System.out.println("parent id :" + nid + " token :" + token);
				nid = addNode(user_id,uid, nid, token);
			}    // end of if (cur_nid > 0) else
		}     
		return nid;
	}
	
	private long addNode(BareJID user_id,long uid, long parent_nid, String node_name)
			throws SQLException, TigaseDBException {
		System.out.println("addNode!!");
		if(parent_nid < 0){
			parent_nid = 0;
		}
		long id = 0;
		String logName = user_id.toString();
		if(!logName.contains("-pc")&&logName.indexOf("@")>-1)
			logName=logName.substring(0,logName.indexOf("@"));
		
		IMContactsNodeServices imcnService = (IMContactsNodeServices) SpringContextUtil.getBean("imcontactsNodeServices");
		IMContactsNodeBean imcnBean = new IMContactsNodeBean();
		imcnBean.setLogname(logName);
		imcnBean.setUid(String.valueOf(uid));
		imcnBean.setParent_nid(String.valueOf(parent_nid));
		imcnBean.setNode(node_name);
		String nid = TimeTools.getTimestampMilliSecond();
		imcnBean.setNid(nid);
		try {
			imcnService.AddIMContactsNode(imcnBean);
			id = Long.parseLong(nid);
			
		}catch(Exception e){
			e.printStackTrace();
			throw new TigaseDBException("Propeblem adding new node. "
					+ "The SP should return nid or fail");
		}
		System.out.println("addNode!!return id :" + id);
		return id;
	}
	
	private void deleteSubnode(BareJID user , long nid) throws SQLException {
		System.out.println("deleteNode");
		//this.getSqlMapClientTemplate().delete("deleteNodeFromPair",nid);
		//删除pair表中的nid
		IMContactsServices imcService = (IMContactsServices) SpringContextUtil.getBean("imcontactsServices");
		IMContactsBean imcBean = new IMContactsBean();
		String logName = user.toString();
		if(user.toString().indexOf("@")>-1)
			logName =logName.substring(0,logName.indexOf("@"));
		imcBean.setLogname(logName);
		imcBean.setNid(String.valueOf(nid));
		try {
			imcService.DelIMContacts(imcBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SQLException();
		}	
		
		//删除Node表中的nid对应信息
		IMContactsNodeServices imcnService = (IMContactsNodeServices) SpringContextUtil.getBean("imcontactsNodeServices");
		IMContactsNodeBean imcnBean = new IMContactsNodeBean();
		imcnBean.setLogname(logName);
		imcnBean.setNid(String.valueOf(nid));
		try {
			imcnService.DelIMContactsNode(imcnBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SQLException();
		}
		System.out.println("delete nodes OK");
	}
	
	private String getPassword(BareJID user) throws UserNotFoundException, TigaseDBException {
		return getData(user, PASSWORD_KEY);
	}

	/*
	public void saveUserStatus(UserStatus userStatusBean) throws RuntimeException {
		this.getSqlMapClientTemplate().insert("saveUserStatus", userStatusBean);
	}

	public void deleteUserStatus(int id) throws RuntimeException {
		this.getSqlMapClientTemplate().delete("deleteUserStatus", id);
	}

	public void updateUserStatus(UserStatus userStatusBean) throws RuntimeException {
		this.getSqlMapClientTemplate().update("updateUserStatus", userStatusBean);
	}

	@SuppressWarnings("unchecked")
	public List findAllUserStatus() throws RuntimeException {
		return this.getSqlMapClientTemplate().queryForList("findAllUserStatus");
	}

	public UserStatus findUserStatusById(String user) throws RuntimeException {
		return (UserStatus) this.getSqlMapClientTemplate().queryForObject(
				"findUserStatusById", user);
	}

	public void deleteMeetTouchMessage(int id) throws RuntimeException {
		this.getSqlMapClientTemplate().delete("deleteMeetTouchMessage", id);
		
	}

	public List findAllMeetTouchMessage() throws RuntimeException {
		return this.getSqlMapClientTemplate().queryForList("findAllMeetTouchMessage");
	}

	public List findMeetTouchMessageById(
			MeetTouchMessage meetTouchMessageBean) throws RuntimeException {
		return this.getSqlMapClientTemplate().queryForList("findMeetTouchMessageById", meetTouchMessageBean);
	}

	public void saveMeetTouchMessage(MeetTouchMessage meetTouchMessageBean)
			throws RuntimeException {
		this.getSqlMapClientTemplate().insert("saveMeetTouchMessage", meetTouchMessageBean);
		
	}

	public void updateMeetTouchMessage(MeetTouchMessage meetTouchMessageBean)
			throws RuntimeException {
		this.getSqlMapClientTemplate().update("updateMeetTouchMessage", meetTouchMessageBean);
		
	}
	
	public void cretatIMUserMessageTable(String logname) throws Exception{
		//this.getSqlMapClientTemplate().update("cretatIMUserMessageTable",logname);
	}*/
}


