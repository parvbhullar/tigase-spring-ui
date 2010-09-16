package com.linkage.app.gqt.backstage.members.service;

import java.util.List;
import java.util.Map;

import com.linkage.app.gqt.backstage.members.dao.PostulantDao;
import com.linkage.app.gqt.backstage.members.entity.Postulant;
import com.linkage.app.gqt.backstage.members.entity.PostulantHis;
/**
 * 志愿者后台管理service类
 *
 *
 * @author jiale.wang
 *
 * @create on 2010-8-14 上午11:01:36
 *
 * @version v1.0
 *
 * @Copyright (c) 2009 by linkaged.
 *
 * @see
 */
public class PostulantService {
	private PostulantDao postulantDao;

	public PostulantDao getPostulantDao() {
		return postulantDao;
	}

	public void setPostulantDao(PostulantDao postulantDao) {
		this.postulantDao = postulantDao;
	}
	//条件查询总条数
	public int getPostulantTotal(Map args)
	{
		return this.postulantDao.getPostulantTotal(args);
	}
	//条件查询
	public List<Postulant> getPostulantList(Map args)
	{
		return this.postulantDao.getPostlantList(args);
	}
	//批量插入
	public int[] batchinsert(List<Object[]> list,long communityid,String communityname,long orgid,String orgname,String intention){
		return this.postulantDao.batchinsert(list, communityid, communityname, orgid, orgname, intention);
	}
	//根据id查询
	public Postulant selectById(long id)
	{
		return this.postulantDao.selectById(id);
	}
	
	//根据登录名查询
	public Postulant selectByUsername(String username)
	{
		return this.postulantDao.selectByUsername(username);
	}
	//审批
	public int verify(long id,int vid)
	{
		return this.postulantDao.verify(id,vid);
	}
	//注销审核
	public int verify1(long id,int vid){
		return this.postulantDao.verify1(id, vid);
	}
	//批量删除
	public int[] batchDelete(final List<Long> idList) 
	{
		return this.postulantDao.batchDelete(idList);
	}
	//根据id删除
	public int deleteById(long id)
	{
		return this.postulantDao.deleteById(id);
	}
	//新增记录
	public int insert(Postulant postulant)
	{
		try {
			return this.postulantDao.insert(postulant);
		} catch (Exception e) {

		}
		return 0;
	}
	
	//新增记录
	public int insertBack(Postulant postulant)
	{
		try {
			return this.postulantDao.insertBack(postulant);
		} catch (Exception e) {

		}
		return 0;
	}
	
	//更新
	public int update(Postulant postulant)
	{
		return this.postulantDao.updatePostulant(postulant);
	}
	
	//前台更新
	public int updateFront(Postulant postulant)
	{
		return this.postulantDao.updatePostulantFront(postulant);
	}
	/**
	 * 查询组织机构下志愿者列表
	 * @param orgid 机构ID
	 * @return List<Postulant>
	 */
	public List<Postulant> getPostlantListByOrgId(long orgid){
		return this.postulantDao.getPostlantListByOrgId(orgid);
	}
	
	//新增记录前台
	public int insertFornt(Postulant postulant)
	{
		return this.postulantDao.insertFornt(postulant);
	}
	//单条导入
	public int importData(Object[] obj,long cid,String inten,long orgid,String orgname,String cname,String jn){
		return this.postulantDao.importData(obj, cid, inten, orgid, orgname, cname,jn);
	}
	public int[] validate(String dn,String credcode){
		return this.postulantDao.validate(dn, credcode);
	}
	/**
	 * 注销
	 * @param id
	 * @return
	 */
	public int logout(long id){
		return this.postulantDao.logout(id);
	}
	//删除历史
	public List<PostulantHis> getPostlantHisList(Map args){
		return this.postulantDao.getPostlantHisList(args);
	}
}
