package com.linkage.app.gqt.front.reg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.linkage.app.gqt.backstage.active.entity.IssueWorkSheet;
import com.linkage.app.gqt.backstage.active.entity.QueryIssueWorkSheetRowMapper;
import com.linkage.app.gqt.backstage.members.entity.Postulant;
import com.linkage.app.gqt.backstage.members.entity.PostulantRowMapper;
import com.linkage.app.gqt.backstage.org.entitys.Org;
import com.linkage.app.gqt.backstage.org.entitys.OrgRowMapper;
import com.linkage.app.gqt.front.reg.entitys.Members;
import com.linkage.app.gqt.front.reg.entitys.MembersRowMapper;
import com.linkage.app.gqt.purview.entity.User;
import com.linkage.framework.jdbc.BaseJdbcDaoImpl;

public class MembersDao extends BaseJdbcDaoImpl{
	final Logger logger = LoggerFactory.getLogger(MembersDao.class);
	
	/*
	 * 新增注册用户
	 */
	//String sql="INSERT INTO ZYZ_MEMBERS (UUID, USERNAME, NAME, NICKNAME, PASSWORD, CELLPHONE, EMAIL, SEX, BIRTHDAY, EDULEVEL, PROFESSION, HOBBY, LOCATION, INTENTION, SERVETIMES, VOLUNORGID, CREDCODE, CREDTYPE, ISVALID, ISVERIFY, VERIFYDESC, CREATETIME, REGIP, REGDATE, LASTLOGINIP, TOTALPOINTS, LASTLOGNUMBERTIME)"+ 
	//"values ( 1, username,name ,nickname, password, cellphone,email, sex, birthday,edulevel, profession,hobby ,location,intention ,servetimes ,volunorgid, credcode,credtype ,isvalid,isverify ,verifydesc ,createtime,regip ,regdate ,lastloginip,totalpoints ,lastlognumbertime )";
	
	public int insert(String username,String name,String nickname,String password,String cellphone,String email,String sex,String birthday,String edulevel,String profession,String hobby,String location,String intention,String servetimes,String volunorgid,String credcode,String credtype,String isvalid,String isverify,String verifydesc,String createtime,String regip,String regdate,String lastloginip,String totalpoints,String lastlognumbertime){
		logger.info("INSERT INTO ZYZ_MEMBERS (UUID, USERNAME, NAME, NICKNAME, PASSWORD, CELLPHONE, EMAIL, SEX, BIRTHDAY, EDULEVEL, PROFESSION, HOBBY, LOCATION, INTENTION, SERVETIMES, VOLUNORGID, CREDCODE, CREDTYPE, ISVALID, ISVERIFY, VERIFYDESC, CREATETIME, REGIP, REGDATE, LASTLOGINIP, TOTALPOINTS, LASTLOGNUMBERTIME)  VALUES(EC_WEBPREFERENTIAL_SHOPS_SEQ.NEXTVAL,{},{},{},{},{},{},{},SYSDATE,{},0,{})", new Object[]{username, name, nickname, password, cellphone, email, sex, birthday, edulevel, profession, hobby, location, intention, servetimes, volunorgid, credcode, credtype, isvalid, isverify, verifydesc, createtime, regip, regdate, lastloginip, totalpoints, lastlognumbertime});
		//logger.info("INSERT INTO EC_WEBPREFERENTIAL_COUPONS(ID,NAME,INTRODUCTION,HELP,STARTTIME,ENDTIME,CATEGORYID,SHOPID,CREATETIME,PIC,STATE,MESSAGE) VALUES(EC_WEBPREFERENTIAL_SHOPS_SEQ.NEXTVAL,{},{},{},{},{},{},{},SYSDATE,{},0,{})", new Object[]{name, introduction, help,startTime, endTime,categoryId, shopId, pic,message});
		//return this.getJdbcTemplate().update("INSERT INTO EC_WEBPREFERENTIAL_COUPONS(ID,NAME,INTRODUCTION,HELP,STARTTIME,ENDTIME,CATEGORYID,SHOPID,CREATETIME,PIC,STATE,MESSAGE) VALUES(EC_WEBPREFERENTIAL_SHOPS_SEQ.NEXTVAL,?,?,?,to_date(?,'YYYY-MM-DD HH24:MI:SS'),to_date(?,'YYYY-MM-DD HH24:MI:SS'),?,?,SYSDATE,?,0,?)", name, introduction, help,startTime, endTime,categoryId, shopId, pic,message);
		return this.getJdbcTemplate().update("INSERT INTO ZYZ_MEMBERS (UUID, USERNAME, NAME, NICKNAME, PASSWORD,CELLPHONE,EMAIL,SEX,BIRTHDAY,CREDCODE,CREDTYPE,CREATETIME,REGIP,REGDATE,volunorgid)values ( SEQ_ZYZ_MEMBERS_UUID.nextval, ?,? ,?, ?,?,?,?,to_date(?,'YYYY-MM-DD'),?,?,sysdate,?,sysdate,?)",username, name, nickname, password,cellphone,email,sex,birthday,credcode,credtype,regip,volunorgid);
	}	
	
	 public KeyHolder insertActor(final String username,final String name,final String nickname,final String password,final String cellphone,final String email,final String sex,final String birthday,final String edulevel,final String profession,final String hobby,final String location,final String intention,final String servetimes,final String volunorgid,final String credcode,final String credtype,final String isvalid,final String isverify,final String verifydesc,final String createtime,final String regip,final String regdate,final String lastloginip,final String totalpoints,final String lastlognumbertime){
//         final String addSql = "INSERT INTO ZYZ_MEMBERS (UUID, USERNAME, NAME, NICKNAME, PASSWORD,CELLPHONE,EMAIL,SEX,
//		 BIRTHDAY,CREDCODE,CREDTYPE,CREATETIME,REGIP,REGDATE,volunorgid)values ( SEQ_ZYZ_MEMBERS_UUID.nextval, ?,? 
//				 ,?, ?,?,?,?,to_date(?,'YYYY-MM-DD'),?,?,sysdate,?,sysdate,?)";  
         final String addSql = "INSERT INTO ZYZ_MEMBERS (UUID, USERNAME,NAME, NICKNAME, PASSWORD,CELLPHONE,EMAIL,SEX,BIRTHDAY,EDULEVEL,CREDCODE,CREDTYPE,REGIP,VOLUNORGID,PROFESSION, HOBBY,LOCATION,REGDATE,INTENTION)values " +
         		"( SEQ_ZYZ_MEMBERS_UUID.nextval,?,?,?, ?,?,?,?,to_date(?,'YYYY-MM-DD'),?,?,?,?,?,?,?,?,sysdate,?)";
         KeyHolder keyHolder = new GeneratedKeyHolder();  
         this.getJdbcTemplate().update(
        		    new PreparedStatementCreator() {
        		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        		            PreparedStatement ps =
        		                connection.prepareStatement(addSql,new String[] {"uuid"});
        		            ps.setString(1, username);
        		            ps.setString(2, name);
        		            ps.setString(3,nickname);
        		            ps.setString(4,password);
        		            ps.setString(5,cellphone);
        		            ps.setString(6,email);
        		            ps.setInt(7, new Integer(sex).intValue());
        		            ps.setString(8, birthday);
        		            ps.setString(9, edulevel);
        		            ps.setString(10, credcode);
        		            ps.setString(11, credtype);
        		            ps.setString(12, regip);
        		            ps.setString(13, volunorgid);
        		            ps.setString(14, profession);
        		            ps.setString(15, hobby);
        		            ps.setString(16, location);
        		            ps.setString(17, intention);
        		            return ps;
        		        }
        		    },
        		    keyHolder);
         return keyHolder;  
     }
	
	
	public List<Members> showMembers()
	{
		String sql="SELECT * FROM ZYZ_MEMBERS where isvalid=1";
		logger.info(sql);
		List<Members> list=(List<Members>) this.getJdbcTemplate().query(sql, new MembersRowMapper());
		return list;
	}
	
	/**
	 * 根据ID取得用户
	 * @param id
	 * @return
	 */
	public Postulant getMembers(long id)
	{
		Object[] o=new Object[]{id};
		String sql="select * from zyz_members WHERE id= ?";
		return this.getJdbcTemplate().queryForObject(sql,o,new PostulantRowMapper());
	}
	
	/**
	 * 根据ID取得用户
	 * @param id
	 * @return
	 */
	public Postulant getMembersByDn(String dn)
	{
		Object[] o=new Object[]{dn};
		String sql="select * from zyz_members WHERE dn= ?";
		return this.getJdbcTemplate().queryForObject(sql,o,new PostulantRowMapper());
	}
	
	/**
	 * 根据用户名取得用户
	 * @param id
	 * @return
	 */
	public Members getMembersByUsername(String username)
	{
		Object[] o=new Object[]{username};
		String sql="select * from zyz_members WHERE username= ?";
		return this.getJdbcTemplate().queryForObject(sql,o,new MembersRowMapper());
	}
	
	/**
	 * 更新审核状态
	 * @param uuid
	 * @param isverify
	 * @return
	 */
	public int updateMembers(int uuid,int isverify)
	{
		Object[] o=new Object[]{isverify,uuid};
		String sql="UPDATE zyz_members SET isverify=? WHERE uuid = ?";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().update(sql,o);
	}
	
	/**
	 * 检查用户名是否重复
	 * @param username
	 * @return
	 */
	public int checkUsername(String username)
	{
		Object[] o=new Object[]{username};
		String sql="select count(*) from zyz_members WHERE loginname = ?";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().queryForInt(sql,o);
	}
	
	/**
	 * 检查身份证是否重复
	 * @param username
	 * @return
	 */
	public int checkCredcode(String credcode)
	{
		Object[] o=new Object[]{credcode};
		String sql="select count(*) from zyz_members WHERE CREDCODE = ?";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().queryForInt(sql,o);
	}
	
	
	/**
	 * 检查身份证是否重复
	 * @param username
	 * @return
	 */
	public int checkCredcode(String credcode,String id)
	{
		Object[] o=new Object[]{credcode,id};
		String sql="select count(*) from zyz_members WHERE CREDCODE = ? and id!= ?";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().queryForInt(sql,o);
	}
	
	
	/**
	 * 检查用户名是否重复
	 * @param username
	 * @return
	 */
	public int checkPassword(Postulant postulant)
	{
		Object[] o=new Object[]{postulant.getDn(),postulant.getPassword()};
		String sql="select count(*) from zyz_members WHERE dn = ? and password= ?";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().queryForInt(sql,o);
	}
	
	/**
	 * 检查后台管理员密码是否正确
	 * @param user
	 * @return
	 */
	public int checkUserPassword(User user)
	{
		Object[] o=new Object[]{user.getUserId(),user.getPassword()};
		String sql="select count(*) from zyz_user t where t.userid=? and t.loginpass=?";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().queryForInt(sql,o);
	}
	
	/**
	 * 更新志愿者密码
	 * @param username
	 * @return
	 */
	public int updatePassword(Postulant postulant)
	{
		Object[] o=new Object[]{postulant.getPassword(),postulant.getDn()};
		String sql="UPDATE zyz_members SET password=? WHERE dn = ?";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().update(sql,o);
	}
	
	/**
	 * 更新志愿者个人参数
	 * @param user
	 * @return
	 */
	public int updatePersonParam(Postulant postulant)
	{
		Object[] o=new Object[]{postulant.getServetimes(),postulant.getIsverify(),postulant.getDn()};
		String sql="UPDATE zyz_members SET SERVETIMES=?,ISVERIFY=? WHERE dn = ?";
		if("".equals(postulant.getIsverify()))
		{
			o=new Object[]{postulant.getServetimes(),postulant.getDn()};
			sql="UPDATE zyz_members SET SERVETIMES=? WHERE dn = ?";
		}
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().update(sql,o);
	}
	
	/**
	 * 更新管理员密码
	 * @param username
	 * @return
	 */
	public int updateUserPassword(User user)
	{
		Object[] o=new Object[]{user.getPassword(),user.getUserId()};
		String sql="update zyz_user t set t.loginpass=? where t.userid=?";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().update(sql,o);
	}
	
	
	
	
	/**
	 * 检查手机是否重复
	 * @param dn
	 * @return
	 */
	public int checkDn(String dn)
	{
		Object[] o=new Object[]{dn};
		String sql="select count(*) from zyz_members WHERE dn = ?";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().queryForInt(sql,o);
	}
	
	/**
	 * 编辑状态检查手机是否重复
	 * @param dn
	 * @return
	 */
	public int checkDn(String dn,String uuid)
	{
		Object[] o=new Object[]{dn,uuid};
		String sql="select count(*) from zyz_members WHERE dn = ? and id != ?";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().queryForInt(sql,o);
	}
	
	public List<Org> loadAreaOrg(int areaId)
	{
		Object[] o=new Object[]{areaId};
		String sql="select * from zyz_orgs t ";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().query(sql, new OrgRowMapper());
	}
	
	public Org getOrgById(long orgId)
	{
		Object[] o=new Object[]{orgId};
		String sql="select * from zyz_orgs t where t.orgid=?";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		Org org=this.getJdbcTemplate().queryForObject(sql, o, new OrgRowMapper());
		return org;
	}
	
	
	public int delMembers(int uuid)
	{
		Object[] o=new Object[]{uuid};
		String sql="UPDATE zyz_members SET ISVALID=0 WHERE uuid = ?";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().update(sql,o);
	}
	
	/**
	 * 志愿者登陆
	 * @param members
	 * @return
	 */
	public Postulant login(Members members)
	{
		Object[] o=new Object[]{members.getName(),members.getPassword()};
		String sql="select count(*) from zyz_members WHERE loginname = ? and password= ?";
		int i=this.getJdbcTemplate().queryForInt(sql,o);
		sql="select * from zyz_members WHERE loginname = ? and password= ? and ISVALID=1";
		if(i==1)
			return this.getJdbcTemplate().queryForObject(sql,o,new PostulantRowMapper());
		else
			return null;
	}
	
	public List<IssueWorkSheet> getIssueWorkSheetByVolunteerid(Long volunteerid)
	{
		Object[] o=new Object[]{volunteerid};
		String sql="select t.* from zyz_issue_worksheet t";
		sql += " left join zyz_activity a on a.actid = t.actid";
		sql += " where t.volunteerid = ? and a.tsstatus = 0 and a.actstatus = 0";
		return this.getJdbcTemplate().query(sql,o,new QueryIssueWorkSheetRowMapper());
	}
	
	public int logout(long id,String resion)
	{
		Object[] o=new Object[]{resion,id};
		String sql="UPDATE zyz_members SET OUTRESION=?,ISVERIFY=4 WHERE id = ?";
		logger.info(sql.replaceAll("\\?", "{}"), o);
		return this.getJdbcTemplate().update(sql,o);
	}
	
	
}
