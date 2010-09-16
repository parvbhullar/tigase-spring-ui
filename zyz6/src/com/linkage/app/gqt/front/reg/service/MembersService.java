package com.linkage.app.gqt.front.reg.service;

import java.util.List;

import org.springframework.jdbc.support.KeyHolder;

import com.linkage.app.gqt.backstage.active.entity.IssueWorkSheet;
import com.linkage.app.gqt.backstage.members.entity.Postulant;
import com.linkage.app.gqt.backstage.org.entitys.Org;
import com.linkage.app.gqt.front.reg.dao.MembersDao;
import com.linkage.app.gqt.front.reg.entitys.Members;
import com.linkage.app.gqt.purview.entity.User;

public class MembersService {
	private MembersDao membersDao;
	
	public MembersDao getMembersDao() {
		return membersDao;
	}
	public void setMembersDao(MembersDao membersDao) {
		this.membersDao = membersDao;
	}
	
	public int insertMember(String username,String name,String nickname,String password,String cellphone,String email,String sex,String birthday,String edulevel,String profession,String hobby,String location,String intention,String servetimes,String volunorgid,String credcode,String credtype,String isvalid,String isverify,String verifydesc,String createtime,String regip,String regdate,String lastloginip,String totalpoints,String lastlognumbertime)
	{
		//return membersDao.insert(username, name, nickname, password, cellphone, email, sex, birthday, edulevel, profession, hobby, location, intention, servetimes, volunorgid, credcode, credtype, isvalid, isverify, verifydesc, createtime, regip, regdate, lastloginip, totalpoints, lastlognumbertime);
		KeyHolder keyHolder=membersDao.insertActor(username, name, nickname, password, cellphone, email, sex, birthday, edulevel, profession, hobby, location, intention, servetimes, volunorgid, credcode, credtype, isvalid, isverify, verifydesc, createtime, regip, regdate, lastloginip, totalpoints, lastlognumbertime);
		Number number=keyHolder.getKey();
		 return number.intValue();
	}
	
	/**
	 * 显示 志愿者注册用户列表
	 * @return
	 */
	public List<Members> showMembers()
	{
		return this.membersDao.showMembers();
	}
	
	/**
	 * 更新志愿者信息
	 * @param uuid
	 * @param isverify
	 * @return
	 */
	public int updateMembers(int uuid,int isverify)
	{
		return this.membersDao.updateMembers(uuid,isverify);
	}
	
	/**
	 * 检查用户名是否重复
	 * @param username
	 * @return
	 */
	public int checkUsername(String username)
	{
		return this.membersDao.checkUsername(username);
	}
	
	/**
	 * 检查身份证是否重复
	 * @param username
	 * @return
	 */
	public int checkCredcode(String credcode)
	{
		return this.membersDao.checkCredcode(credcode);
	}
	
	/**
	 * 检查身份证是否重复
	 * @param username
	 * @return
	 */
	public int checkCredcode(String credcode,String id)
	{
		return this.membersDao.checkCredcode(credcode, id);
	}
	
	
	
	/**
	 * 检查用户名是否重复
	 * @param username
	 * @return
	 */
	public int checkPassword(Postulant postulant)
	{
		return this.membersDao.checkPassword(postulant);
	}
	
	/**
	 * 检查后台管理员密码
	 * @param username
	 * @return
	 */
	public int checkUserPassword(User user)
	{
		return this.membersDao.checkUserPassword(user);
	}
	
	/**
	 * 更新志愿者密码
	 * @param username
	 * @return
	 */
	public int updatePassword(Postulant postulant)
	{
		return this.membersDao.updatePassword(postulant);
	}
	
	/**
	 * 更新志愿者个人参数
	 * @param postulant
	 * @return
	 */
	public int updatePersonParam(Postulant postulant)
	{
		return this.membersDao.updatePersonParam(postulant);
	}
	
	/**
	 * 更新管理员密码
	 * @param username
	 * @return
	 */
	public int updateUserPassword(User user)
	{
		return this.membersDao.updateUserPassword(user);
	}
	
	
	
	
	
	
	
	/**
	 * 检查手机是否重复
	 * @param dn
	 * @return
	 */
	public int checkDn(String dn)
	{
		return this.membersDao.checkDn(dn);
	}
	
	/**
	 * 编辑状态检查手机是否重复
	 * @param dn
	 * @return
	 */
	public int checkDn(String dn,String uuid)
	{
		return this.membersDao.checkDn(dn,uuid);
	}
	
	/**
	 * 删除志愿者
	 * @param uuid
	 * @return
	 */
	public int delMembers(int uuid)
	{
		return this.membersDao.delMembers(uuid);
	}
	
	/**
	 * 志愿者用户登陆
	 * @param uuid
	 * @return
	 */
	public Postulant login(Members members)
	{
		return this.membersDao.login(members);
	}
	
	/**
	 * 根据ID取得注册用户信息
	 * @param id
	 * @return
	 */
	public Postulant getMembers(long id)
	{
		return this.membersDao.getMembers(id);
	}
	
	public Postulant getMembersByDn(String dn)
	{
		return this.membersDao.getMembersByDn(dn);
	}
	
	/**
	 * 根据用户名取得注册用户信息
	 * @param id
	 * @return
	 */
	public Members getMembersByUsername(String userName)
	{
		return this.membersDao.getMembersByUsername(userName);
	}
	
	/**
	 * 查找地区下的组织
	 * @param areaId
	 * @return
	 */
	public List<Org>loadAreaOrg(int areaId)
	{
		return this.membersDao.loadAreaOrg(areaId);
	}
	
	/**
	 * 
	 * @param areaId
	 * @return
	 */
	public Org getOrgById(int orgId)
	{
		return this.membersDao.getOrgById(orgId);
	}
	
	public List<IssueWorkSheet> getIssueWorkSheetByVolunteerid(Long volunteerid){
		return this.membersDao.getIssueWorkSheetByVolunteerid(volunteerid);
	}
	
	public int logout(long id,String resion)
	{
		return this.membersDao.logout(id, resion);
	}
	
}
