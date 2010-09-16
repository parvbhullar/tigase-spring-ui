package com.linkage.app.gqt.backstage.helping.service;

import java.util.List;

import com.linkage.app.gqt.backstage.helping.dao.HelpingDao;

import com.linkage.app.gqt.backstage.helping.entity.Worksheet;
import com.linkage.app.gqt.backstage.sysparam.entity.Sysparam;

import com.linkage.app.gqt.backstage.members.entity.Postulant;
import com.linkage.app.gqt.backstage.members.entity.Assist;
import com.linkage.app.gqt.backstage.org.entitys.Org;

import com.linkage.app.gqt.backstage.helping.entity.QueryAssistRowMapper;
import com.linkage.app.gqt.backstage.helping.entity.QueryMemberRowMapper;
import com.linkage.app.gqt.backstage.helping.entity.QueryWorksheetRowMapper;
import com.linkage.app.gqt.backstage.helping.entity.EditWorksheetRowMapper;
import com.linkage.app.gqt.backstage.helping.entity.QueryHelpingCountRowMapper;



public class HelpingService {
	private HelpingDao helpingDao;
	
	public HelpingDao getHelpingDao() {
		return helpingDao;
	}
	public void setHelpingDao(HelpingDao helpingDao) {
		this.helpingDao = helpingDao;
	}
	/**
	 * 作用：查询已发布的工单
	 * @return
	 */
	public List<Worksheet> showWorkSheet(int start, int end, long  currentOrgId){
		return this.helpingDao.showWorkSheet(start, end , currentOrgId);
	}

	
	/**
	 * 作用：查询已发布的协助
	 * @return
	 */
	public List<Worksheet> showWorkSheet2(int start, int end, long  currentOrgId){
		return this.helpingDao.showWorkSheet2(start, end , currentOrgId);
	}	
	
	
	/**
	 * 作用：查询系统志愿者类型
	 * @return
	 */
	public List<Sysparam> showType(){
		return this.helpingDao.showType();
	}	
	
	public List<Postulant> showMembers(int start,int end,Long Id,Long vorgId){
		return this.helpingDao.showMembers(start, end, Id, vorgId);
	}
	
	
	/**
	 * 作用：统计志愿者总数
	 * @return count 总数
	 */
	public int countPostulant(Long Id){
		return this.helpingDao.countPostulant(Id);
	}
	

	
	/**
	 * 作用：查询已发布的工单
	 * @return
	 */
	public List<Assist> showAssist(long  typeId , String name){
		return this.helpingDao.showAssist(typeId, name);
	}	
	
	/**
	 * 作用：统计已发布的工单数
	 * @return count 总数
	 */
	public int countAssist(long typeId,String name){
		return this.helpingDao.countAssist(typeId, name);
	}	
	
	
	
	/**
	 * 作用：统计已发布的工单数
	 * @return count 总数
	 */
	public int countWorksheet(long id){
		return this.helpingDao.countWorksheet(id);
	}
	

	/**
	 * 作用：统计已发布的工单数
	 * @return count 总数
	 */
	public int countWorksheet2(long id){
		return this.helpingDao.countWorksheet2(id);
	}	
	
	
	
	/**
	 * 作用：保存发布的工单
	 * @return saveNum 影响的记录数
	 */
	public int insertWorksheet(Worksheet worksheet){
		int saveNum;
		if("1".equals(worksheet.getEditFlag())){
			// 修改工单
			saveNum = helpingDao.updateWorksheet(worksheet);
		}else{
			// 发布工单
			saveNum = helpingDao.insertWorksheet(worksheet);
		}		
		return saveNum;
	}
	/**
	 * 作用：查询待修改的工单
	 * @return 待修改的工单信息
	 */
	public Worksheet findWorksheetById(long Id){
		return this.helpingDao.findWorksheetById(Id);
	}
	
	
	/**
	 * 作用：结束工单
	 * @param workid 工单ID
	 * @return updateNum 影响的记录数
	 */
	public int finishWorksheet(long workid){
		return this.helpingDao.finishWorksheet(workid);
	}
	
	/**
	 * 作用：删除工单
	 * @param workid ID
	 * @return updateNum 影响的记录数
	 */
	public int delWorksheet(long workid){
		
		return this.helpingDao.delWorksheet(workid);
	}
	
	
	public List<Org> helpingCount( long orgId,String startDate,String overDate){
		return this.helpingDao.helpingCount(orgId, startDate, overDate);
	}	
	
	
	public int counthelping (long orgId, String startDate,String overDate){
		
		return this.helpingDao.counthelping(orgId, startDate, overDate);
	}
	
	
	public List<Worksheet> findHelpingList(long orgid, String startdate, String enddate, int start, int end){
		return this.helpingDao.findHelpingList(orgid, startdate, enddate, start, end);
	}	
	
	public int countHelpingList(long orgid, String startdate, String enddate){
		return this.helpingDao.countHelpingList(orgid, startdate, enddate);
	}	
	
	public int querySysValue(long typeId){
		
		return this.helpingDao.querySysValue(typeId);
	}
	
	public int queryMemberId(long workid){
		
		return this.helpingDao.queryMemberId(workid);
	}
	
	public int queryMemberSysValue(long id){
		return this.helpingDao.queryMemberSysValue(id);
	}
	
	public int updateMemberValue(long id,long value){
		return this.helpingDao.updateMemberValue(id, value);
	}
	
	public int queryWorksheetValue(long workid){
		return this.helpingDao.queryWorksheetValue(workid);
	}
	
	/**
	 * 作用：获取帮扶ID
	 * @return worksheetId
	 */
	public long getWorksheetid(){
		return this.helpingDao.getWorksheetid();
	}
}