package com.linkage.app.gqt.backstage.members.service;

import java.util.List;
import java.util.Map;

import com.linkage.app.gqt.backstage.members.dao.AssistDao;
import com.linkage.app.gqt.backstage.members.entity.Assist;
/**
 * 帮扶对象后台管理类
 *
 *
 * @author jiale.wang
 *
 * @create on 2010-8-14 上午11:02:29
 *
 * @version v1.0
 *
 * @Copyright (c) 2009 by linkaged.
 *
 * @see
 */
public class AssistService {
	private AssistDao assistDao;

	public AssistDao getAssistDao() {
		return assistDao;
	}

	public void setAssistDao(AssistDao assistDao) {
		this.assistDao = assistDao;
	}
	//条件查询
	public List<Assist> getAssistList(Map args)
	{
		return this.assistDao.getAssistList(args);
	}
	//批量插入
	public int[] batchinsert(List<Object[]> list,long vid,String vname){
		return this.assistDao.batchinsert(list,vid,vname);
	}
	//保存记录
	public int insert(Assist assist){
		return this.assistDao.insert(assist);
	}
	//更新
	public int updateAssist(Assist assist){
		return this.assistDao.updateAssist(assist);
	}
	//条件查询返回总条数
	public int getAssistTotal(Map args){
		return this.assistDao.getAssistTotal(args);
	}
	//根据id删除
	public int deleteById(long id){
		return this.assistDao.deleteById(id);
	}
	//批量删除
	public int[] batchDelete(final List<Long> idList) {
		return this.assistDao.batchDelete(idList);
	}
	//审批
	public int verify(long id,int vid){
		return this.assistDao.verify(id, vid);
	}
	//根据id查询
	public Assist selectById(long id){
		return this.assistDao.selectById(id);
	}
	/**
	 * 查询组织机构下帮扶对象列表
	 * @param orgid 组织机构ID
	 * @return List<Assist>
	 */
	public List<Assist> getAssistListByOrgId(long orgid){
		return this.assistDao.getAssistListByOrgId(orgid);
	}
}
