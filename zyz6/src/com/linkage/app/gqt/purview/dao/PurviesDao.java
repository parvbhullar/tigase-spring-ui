package com.linkage.app.gqt.purview.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linkage.app.gqt.purview.entity.Menu;
import com.linkage.app.gqt.purview.entity.MenuRowMapper;
import com.linkage.app.gqt.purview.entity.Resource;
import com.linkage.app.gqt.purview.entity.ResourceRowMapper;
import com.linkage.app.gqt.purview.entity.Role;
import com.linkage.app.gqt.purview.entity.RoleRowMapper;
import com.linkage.app.gqt.purview.entity.User;
import com.linkage.app.gqt.purview.entity.UserRowMapper;
import com.linkage.framework.jdbc.BaseJdbcDaoImpl;


public class PurviesDao  extends BaseJdbcDaoImpl{
	
	final Logger logger = LoggerFactory.getLogger(PurviesDao.class);
	
	public User selectUserByLoginName(String loginName){
		Object[] o=new Object[]{loginName};
		String sql="SELECT * FROM ZYZ_USER A WHERE A.STATE=1 AND A.LOGINNAME=?";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		User user=this.getJdbcTemplate().queryForObject(sql, o, new UserRowMapper());
		return user;
	}
	
	public List<Role> selectRolesByUserId(long userId){
		Object[] o=new Object[]{userId};
		String sql="SELECT A.*,B.ORGID FROM ZYZ_ROLE A,ZYZ_USER_ROLE B WHERE A.ROLEID=B.ROLEID AND A.ROLESTATE=1 AND B.USERID=? ORDER BY B.ORGID";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		List<Role> list=this.getJdbcTemplate().query(sql,o, new RoleRowMapper());
		return list;
	}
	
	public List<Menu> selectMenusByUserIdAndCurrentOrgId(long userId,long orgId){
		Object[] o=new Object[]{userId,orgId};
		String sql="SELECT AA.*,LEVEL FROM (SELECT A.* FROM ZYZ_MENU A,ZYZ_ROLE B,ZYZ_ROLE_MENU C,ZYZ_USER_ROLE D "+
				   "WHERE B.ROLEID=C.ROLEID AND C.MENUID=A.MENUID AND D.ROLEID=B.ROLEID AND A.MENUSTATE=1 "+
				   "AND B.ROLESTATE=1  AND C.STATUS = 1 AND D.USERID=? AND D.ORGID=? ) AA "+
                   "START WITH AA.PARENTMENUID = 0 CONNECT BY PRIOR AA.MENUID = AA.PARENTMENUID";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		List<Menu> list=this.getJdbcTemplate().query(sql,o, new MenuRowMapper());
		return list;
	}
	
	public List<Resource> selectResources(){
		Object[] o=new Object[]{};
		String sql="SELECT C.*,A.ROLEID,A.ROLENAME FROM ZYZ_ROLE A,ZYZ_ROLE_RESOURCE B,ZYZ_RESOURCE C "+
				   "WHERE A.ROLEID=B.ROLEID AND B.RESOURCEID=C.RESOURCEID AND A.ROLESTATE=1 "+
				   "AND C.RESOURCESTATE=1";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		List<Resource> list=this.getJdbcTemplate().query(sql, new ResourceRowMapper());
		return list;
	}
	
	public int insertUser(long userId,long orgId,String orgName){
		Object[] o=new Object[]{userId,userId,orgName+"管理员",orgId};
		String sql="INSERT INTO ZYZ_USER(USERID,LOGINNAME,LOGINPASS,NICKNAME,DN,SEX,EMAIL,STATE,ORGID,CREATEDATE,UPDATEDATE) VALUES (?,?,'1234',?,'',1,'',1,?,SYSDATE,SYSDATE)";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().update(sql,o);
	}
	
	//创建组织时默认创建的该组织的管理员
	public int insertUserRoleWithAdmin(long userId,long roleId,long orgId){
		Object[] o=new Object[]{userId,roleId,orgId};
		String sql="INSERT INTO ZYZ_USER_ROLE(USERID,ROLEID,ORGID) VALUES(?,?,?)";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().update(sql,o);
	} 
}
