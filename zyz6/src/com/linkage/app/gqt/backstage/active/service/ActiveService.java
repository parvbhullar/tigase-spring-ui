package com.linkage.app.gqt.backstage.active.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.linkage.app.gqt.backstage.active.dao.ActiveDao;
import com.linkage.app.gqt.backstage.active.entity.Active;
import com.linkage.app.gqt.backstage.active.entity.IssueWorkSheet;
import com.linkage.app.gqt.backstage.active.entity.TiMt;
import com.linkage.app.gqt.backstage.log.dao.LogDao;
import com.linkage.app.gqt.backstage.members.entity.Postulant;
import com.linkage.app.gqt.init.entity.SysParam;

public class ActiveService {
	/** 注入活动Dao */
	private ActiveDao activeDao;
	private LogDao logDao;
	/**
	 * 获取活动Dao
	 * @return activeDao 活动Dao
	 */
	public ActiveDao getActiveDao() {
		return activeDao;
	}
	/**
	 * 设置活动Dao
	 * @param activeDao 活动Dao
	 */
	public void setActiveDao(ActiveDao activeDao) {
		this.activeDao = activeDao;
	}
	public LogDao getLogDao() {
		return logDao;
	}
	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}
	/**
	 * 作用：查询已发布的活动
	 * @return
	 */
	public List<Active> showActive(long selectOrgId, int start, int end){
		return this.activeDao.showActive(selectOrgId, start, end);
	}
	
	/**
	 * 作用：根据 志愿者ID,推送状态 查询活动
	 * @param selectOrgId
	 * @param start
	 * @param end
	 * @param actstatus		活动推送状态
	 * @param id 			志愿者ID
	 * @return
	 */
	public List<Active> showActive(long selectOrgId, int start, int end,int actstatus,long id){
		return this.activeDao.showActive(selectOrgId, start, end,actstatus,id);
	}
	/**
	 * 作用：根据 志愿者ID,推送状态 查询活动
	 * @param selectOrgId
	 * @param start
	 * @param end
	 * @param actstatus		活动推送状态
	 * @param id 			志愿者ID
	 * @return
	 */
	public List<Active> showActive1(long selectOrgId, int start, int end,int actstatus,long id){
		return this.activeDao.showActive1(selectOrgId, start, end,actstatus,id);
	}
	
	/**
	 * 作用：根据 志愿者ID,推送状态 查询活动
	 * @param selectOrgId
	 * @param start
	 * @param end
	 * @param actstatus		活动推送状态
	 * @param id 			志愿者ID
	 * @return
	 */
	public List<Active> showRecord(long id){
		return this.activeDao.showRecord(id);
	}
	
	
	/**
	 * 作用：统计已发布的活动数
	 * @return count 总数
	 */
	public int countActive(long selectOrgId){
		return this.activeDao.countActive(selectOrgId);
	}
	/**
	 * 作用：统计已发布的活动数
	 * @return count 总数
	 */
	public int countActive1(long selectOrgId){
		return this.activeDao.countActive1(selectOrgId);
	}
	
	/**
	 * 作用：统计参加的活动总数
	 * @return count 总数
	 */
	public int countRecord(long selectOrgId){
		return this.activeDao.countRecord(selectOrgId);
	}
	
	/**
	 * 作用：保存发布的活动
	 * @param active 活动信息
	 * @return saveNum 影响的记录数
	 */
	public int insertActive(Active active){
		int saveNum;
		if("1".equals(active.getEditFlag())){
			// 修改活动
			saveNum = activeDao.updateActive(active);
		}else{
			// 发布活动
			saveNum = activeDao.insertActive(active);
		}		
		return saveNum;
	}
	/**
	 * 作用：查询待修改的活动
	 * @return 待修改的活动信息
	 */
	public Active findActiveById(long actId){
		return this.activeDao.findActiveByActId(actId);
	}
	
	/**
	 * 作用：推送/停止推送 活动
	 * @param actid 活动ID
	 * @param tsStatus 推送状态
	 * @return
	 */
	public int tsActive(long actid, String tsStatus){		
		return this.activeDao.tsActive(actid, tsStatus);
	}
	
	/**
	 * 作用：结束活动
	 * @param actid 活动ID
	 * @return updateNum 影响的记录数
	 */
	public int finishActive(long actid, int point){
		// 获取该活动的时间储蓄
		int acthours = 0;
		Active active = this.activeDao.findActiveByActId(actid);
		if(null != active){
			point = active.getActhours() * point;			
		}
		// 查看参加该活动的所有志愿者
		List<IssueWorkSheet> issueWorkSheetList = this.activeDao.findMember1ByActid(actid);
		IssueWorkSheet issueWorkSheet = null;
		if(null != issueWorkSheetList && issueWorkSheetList.size() > 0){
			int size = issueWorkSheetList.size();
			for(int i = 0; i < size; i++ ){
				issueWorkSheet = issueWorkSheetList.get(i);
				long volunteerid = issueWorkSheet.getVolunteerid();
				// 累计该志愿者积分
				this.activeDao.updateMemberPoint(volunteerid, point);
			}
		}
		return this.activeDao.finishActive(actid, point);
	}
	
	/**
	 * 作用：删除活动
	 * @param actid 活动ID
	 * @return updateNum 影响的记录数
	 */
	public int deleteActive(long actid){		
		return this.activeDao.deleteActive(actid);
	}
	
	/**
	 * 作用：统计已确认报名的志愿者数量
	 * @param  活动ID
	 * @return 已报名的志愿者数量
	 */
	public int countConfirmedMemberByActid(long actid){		
		return this.activeDao.countConfirmedMemberByActid(actid);
	}
	
	/**
	 * 作用：查询指定数量的志愿者信息
	 * @param  指定数量
	 * @return 志愿者列表
	 */
	public List<Postulant> findMemberInfoList(long num, long orgid, String intentions, long actId){
		List<Postulant> postulantList = new ArrayList<Postulant>();
		if(null != intentions){
			String[] arrIntention = intentions.split(",");
			if(arrIntention.length > 50){
				for(int i = 0; i < arrIntention.length; i = i + 50){
					StringBuffer str = new StringBuffer("");
					int end = i + 50;
					if(end > arrIntention.length){
						end  = arrIntention.length;
					}
					for(int j = i; j < end; j++){
						if(j == end - 1){
							// 循环的最后一个
							str.append(arrIntention[j]);
						}else{
							str.append(arrIntention[j]).append(",");
						}
					}
					List<Postulant> tempList = this.activeDao.findMemberInfoList(num, orgid, str.toString(), actId);
					if(tempList.size() >= num){
						break;
					}
					num = num - tempList.size();
					postulantList.addAll(tempList);
				}
			}else{
				postulantList = this.activeDao.findMemberInfoList(num, orgid, intentions, actId);
			}
		}
		return postulantList;
	}
	
	/**
	 * 个人报名
	 * @param postulant
	 * @param activeId
	 * @return
	 */
	public int insertIssueWorkSheet(Postulant postulant,long activeId){
		return this.activeDao.insertIssueWorkSheet(postulant, activeId);
	}
	
	/**
	 * 作用：插入工单
	 * @param issueWorkSheetList
	 * @param tiMtList
	 * @param tiMthisList
	 * @return
	 */
	public int sendMessage(List<IssueWorkSheet> issueWorkSheetList, 
			List<TiMt> tiMtList, Map sysparamMap){
		// 插入工单
		this.activeDao.batcInsert(issueWorkSheetList);
		int len = 0;
		if(null != tiMtList){
			len = tiMtList.size();
		}
		// 发送短信
		TiMt tiMt = null;
		for(int i = 0; i < len; i++){
			tiMt = tiMtList.get(i);
			try{
				logDao.insertMt(tiMt.getSrcId(), tiMt.getDstId(), tiMt.getMsgContent(), sysparamMap);
			}catch(Exception e){}
		}
		return 1;
	}
	
	/**
	 * 作用：查询该活动的所有工单列表
	 * @param  活动ID
	 * @return 工单列表
	 */
	public List<IssueWorkSheet> findMemberByActid(long actid, int start, int end){		
		return this.activeDao.findMemberByActid(actid, start, end);
	}
	
	/**
	 * 作用：查询该活动的所有工单件数
	 * @param  活动ID
	 * @return 工单件数
	 */
	public int countMemberByActid(long actid){		
		return this.activeDao.countMemberByActid(actid);
	}
	
	/**
	 * 作用：确认工单
	 * @param worksheetId 工单ID
	 * @return updateNum 影响的记录数
	 */
	public int doConfirm(long worksheetId){		
		return this.activeDao.doConfirm(worksheetId);
	}
	/**
	 * 作用：查询该活动的所有工单列表(Excel 导出)
	 * @param  活动ID
	 * @return 工单列表
	 */
	public List<IssueWorkSheet> findAllMemberByActid(long actid){
		return this.activeDao.findAllMemberByActid(actid);
	}
	/**
	 * 作用：创建活动统计树
	 * @return
	 */
	public List<Active> createActiveCountTree(Long currentOrgId, String startDate, String endDate){
		return this.activeDao.createActiveCountTree(currentOrgId, startDate, endDate);
	}
	
	/**
	 * 作用：查询某组织（不包含子组织）已发布的活动列表
	 * @return 活动列表
	 */
	public List<Active> findActiveList(long orgid, String startdate, String enddate, int start, int end){
		return this.activeDao.findActiveList(orgid, startdate, enddate, start, end);
	}
	
	/**
	 * 作用：统计某组织（不包含子组织）已发布的活动数
	 * @return count 总数
	 */
	public int countActiveList(long orgId, String startdate, String enddate){
		return this.activeDao.countActiveList(orgId, startdate, enddate);
	}
	
	/**
	 * 作用：查询该市下的服务意向列表
	 * @return 服务意向列表
	 */
	public List<SysParam> findIntention(long orgId, long orgType){
		List<SysParam> paramList = null;
		if(100 == orgType){
			paramList = this.activeDao.findIntention(orgId);
		}else if(200 == orgType){
			paramList = this.activeDao.findIntention1(orgId);
		}
		return paramList;
	}
	
	/**
	 * 作用：统计该活动的工单数
	 * @param  活动ID
	 * @return 该活动的工单数
	 */
	public int countWorksheetByActid(long actid){
		return this.activeDao.countWorksheetByActid(actid);
	}
}
