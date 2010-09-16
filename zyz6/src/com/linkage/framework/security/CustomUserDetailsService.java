package com.linkage.framework.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.linkage.app.gqt.purview.dao.PurviesDao;
import com.linkage.app.gqt.purview.entity.Role;
import com.linkage.app.gqt.purview.entity.User;

public class CustomUserDetailsService  implements UserDetailsService{
	
	final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	private PurviesDao purviesDao;
	
	public PurviesDao getPurviesDao() {
		return purviesDao;
	}

	public void setPurviesDao(PurviesDao purviesDao) {
		this.purviesDao = purviesDao;
	}

	public UserDetails loadUserByUsername(String loginName)throws UsernameNotFoundException, DataAccessException {
		String _loginName="";
		long _orgId=0;
		if(loginName.indexOf(":")!=-1&&!loginName.endsWith(":")){
			logger.info("用户[{}]切换组织验证开始......",loginName);
			String[] temp=loginName.split(":");
			if(temp!=null&&temp.length==2){
				_loginName=temp[0];
				_orgId=Long.parseLong(temp[1]);
			}
		}else{
			logger.info("用户[{}]登录验证开始......",loginName);
			_loginName=loginName;
		}
		User user=purviesDao.selectUserByLoginName(_loginName);
		if(user.getUserId()>0){
			logger.info("用户:{}存在!",loginName);
			List<Role> list=purviesDao.selectRolesByUserId(user.getUserId());
			Map<Long,List<Role>> orgId2RolesMap=new HashMap<Long,List<Role>>();
			List<Role> tempList=null;
			for(Role role:list){
				if (orgId2RolesMap.containsKey(new Long(role.getOrgId()))) {
					List<Role> atts = orgId2RolesMap.get(new Long(role.getOrgId()));
	            	atts.add(role);
	            	orgId2RolesMap.put(new Long(role.getOrgId()), atts);
	            } else {
	            	tempList=new ArrayList<Role>();
	            	tempList.add(role);
	            	orgId2RolesMap.put(new Long(role.getOrgId()), tempList);
	            }
			}
			if(orgId2RolesMap!=null&&orgId2RolesMap.size()>0){//用户存在角色
				user.setOrgId2roles(orgId2RolesMap);
				if(_orgId==0){
					List<Role> t=orgId2RolesMap.get(new Long(user.getOrgId()));
					if(t!=null&&t.size()>0){//优先选择用户归属组织上的角色
						user.setCurrentOrgId(user.getOrgId());
						user.setCurrentOrgToRoles(t);
					}else{
						long currentOrgId=orgId2RolesMap.keySet().iterator().next();
						user.setCurrentOrgId(currentOrgId);
						user.setCurrentOrgToRoles(orgId2RolesMap.get(currentOrgId));
					}
				}else{
					user.setCurrentOrgId(_orgId);
					user.setCurrentOrgToRoles(orgId2RolesMap.get(_orgId));
				}
			}else{//用户不存在角色
				logger.info("用户[{}]没有角色!",loginName);
				throw new UsernameNotFoundException("用户["+loginName+"]没有角色!");
			}
			user.setMenus(purviesDao.selectMenusByUserIdAndCurrentOrgId(user.getUserId(), user.getCurrentOrgId()));
		}else{
			logger.info("用户[{}]不存在!",loginName);
			throw new UsernameNotFoundException("没有用户[" + loginName+"]!" ); 
		}
		return user;
	}
}
