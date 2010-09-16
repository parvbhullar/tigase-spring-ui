package com.linkage.app.gqt.backstage.active.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.linkage.app.gqt.backstage.active.entity.Active;
import com.linkage.app.gqt.backstage.active.entity.ActiveCountRowMapper;
import com.linkage.app.gqt.backstage.active.entity.EditActiveRowMapper;
import com.linkage.app.gqt.backstage.active.entity.IssueWorkSheet;
import com.linkage.app.gqt.backstage.active.entity.QueryActiveNumRowMapper;
import com.linkage.app.gqt.backstage.active.entity.QueryActiveRowMapper;
import com.linkage.app.gqt.backstage.active.entity.QueryActiveWorkSheetRowMapper;
import com.linkage.app.gqt.backstage.active.entity.QueryIssueWorkSheetRowMapper;
import com.linkage.app.gqt.backstage.active.entity.QueryPostulantRowMapper;
import com.linkage.app.gqt.backstage.members.entity.Postulant;
import com.linkage.app.gqt.init.entity.FwyxParamRowMapper;
import com.linkage.app.gqt.init.entity.SysParam;
import com.linkage.framework.jdbc.BaseJdbcDaoImpl;
 
public class ActiveDao extends BaseJdbcDaoImpl{
	final Logger logger = LoggerFactory.getLogger(ActiveDao.class);

	/**
	 * 作用：查询已发布的活动列表
	 * @return 活动列表
	 */
	public List<Active> showActive(long selectOrgId, int start, int end){
		StringBuffer SQL = new StringBuffer("SELECT * FROM("); 
		SQL.append(" SELECT ROWNUM AS rn, ACTID, ACTTITLE, STARTDATE, ENDDATE, ORGID, ORGNAME, INTENTION, ACTSTATUS, TSSTATUS FROM (");
		SQL.append(" SELECT");
		SQL.append(" ACTID,");
		SQL.append(" ACTTITLE,");
		SQL.append(" TO_CHAR(STARTDATE,'yyyy-MM-dd') AS STARTDATE,");
		SQL.append(" TO_CHAR(ENDDATE,'yyyy-MM-dd') AS ENDDATE,");
		SQL.append(" C.ORGID, ORGNAME, INTENTION, ACTSTATUS, TSSTATUS ");
		SQL.append(" FROM ZYZ_ACTIVITY C ");
		SQL.append(" INNER JOIN (SELECT A.ORGID FROM ZYZ_ORGS A START WITH A.ORGID = ");
		SQL.append(selectOrgId);
		SQL.append(" CONNECT BY PRIOR A.ORGID = A.PARENTORGID)B ON C.ORGID = B.ORGID ");
		SQL.append(" WHERE DELETEFLAG = 0 ORDER BY SENDTIME DESC) WHERE ROWNUM <= " + end + ")WHERE rn > " + start);
		logger.info(SQL.toString());
		List<Active> list=(List<Active>) this.getJdbcTemplate()
												.query(SQL.toString(), new QueryActiveRowMapper());
		return list;
	}
	
	/*工作记录*/
	public List<Active> showRecord(long id){
		StringBuffer SQL = new StringBuffer(); 
		SQL.append("select a.actid,a.acttitle,a.startdate,a.enddate,a.intention,a.orgid, a.orgname,a.actstatus,a.tsstatus from (select * from zyz_issue_worksheet where volunteerid = "+id+") t,zyz_activity a where a.actid = t.actid");
		logger.info(SQL.toString());
		List<Active> list=(List<Active>) this.getJdbcTemplate()
												.query(SQL.toString(), new QueryActiveRowMapper());
		return list;
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
		StringBuffer SQL = new StringBuffer("SELECT * FROM("); 
		SQL.append(" SELECT ROWNUM AS rn, ACTID, ACTTITLE, STARTDATE, ENDDATE, ORGNAME, INTENTION, ACTSTATUS, TSSTATUS,STATUS FROM (");
		SQL.append(" SELECT");
		SQL.append(" C.ACTID,");
		SQL.append(" ACTTITLE,");
		SQL.append(" TO_CHAR(STARTDATE,'yyyy-MM-dd') AS STARTDATE,");
		SQL.append(" TO_CHAR(ENDDATE,'yyyy-MM-dd') AS ENDDATE,");
		SQL.append(" C.ORGNAME, C.INTENTION, ACTSTATUS, TSSTATUS ,ST.STATUS");
		SQL.append(" FROM ZYZ_ACTIVITY C ");
		SQL.append(" INNER JOIN (SELECT A.ORGID FROM ZYZ_ORGS A START WITH A.ORGID = ");
		SQL.append(selectOrgId);
		SQL.append(" CONNECT BY PRIOR A.ORGID = A.PARENTORGID)B ON C.ORGID = B.ORGID ");
		SQL.append("left JOIN (SELECT * FROM zyz_issue_worksheet WHERE  VOLUNTEERID="+id+") ST ON ST.ACTID=C.ACTID");
		SQL.append(" WHERE DELETEFLAG = 0 AND TSSTATUS = 0 AND ACTSTATUS="+actstatus+" ORDER BY SENDTIME DESC) WHERE ROWNUM <= " + end + ")WHERE rn > " + start);
		logger.info(SQL.toString());
		List<Active> list=(List<Active>) this.getJdbcTemplate()
												.query(SQL.toString(), new QueryActiveWorkSheetRowMapper());
		return list;
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
		StringBuffer SQL = new StringBuffer("SELECT * FROM("); 
		SQL.append(" SELECT ROWNUM AS rn, ACTID, ACTTITLE, STARTDATE, ENDDATE, ORGNAME, INTENTION, ACTSTATUS, TSSTATUS,STATUS FROM (");
		SQL.append(" SELECT");
		SQL.append(" C.ACTID,");
		SQL.append(" ACTTITLE,");
		SQL.append(" TO_CHAR(STARTDATE,'yyyy-MM-dd') AS STARTDATE,");
		SQL.append(" TO_CHAR(ENDDATE,'yyyy-MM-dd') AS ENDDATE,");
		SQL.append(" C.ORGNAME, C.INTENTION, ACTSTATUS, TSSTATUS ,ST.STATUS");
		SQL.append(" FROM ZYZ_ACTIVITY C ");
		SQL.append(" INNER JOIN (SELECT A.ORGID FROM ZYZ_ORGS A START WITH A.ORGID = ");
		SQL.append(selectOrgId);
		SQL.append(" CONNECT BY PRIOR A.ORGID = A.PARENTORGID)B ON C.ORGID = B.ORGID ");
		SQL.append("left JOIN (SELECT * FROM zyz_issue_worksheet WHERE  VOLUNTEERID="+id+") ST ON ST.ACTID=C.ACTID");
		SQL.append(" WHERE DELETEFLAG = 0 AND TSSTATUS = 0 AND ACTSTATUS="+actstatus+" ORDER BY SENDTIME DESC) WHERE ROWNUM <= " + end + ")WHERE rn > " + start);
		logger.info(SQL.toString());
		List<Active> list=(List<Active>) this.getJdbcTemplate()
												.query(SQL.toString(), new QueryActiveWorkSheetRowMapper());
		return list;
	}
	
	/**
	 * 作用：统计已发布的活动数
	 * @return count 总数
	 */
	public int countActive(long selectOrgId){
		StringBuffer SQL = new StringBuffer("SELECT COUNT(1)");
		SQL.append(" FROM ZYZ_ACTIVITY C");
		SQL.append(" INNER JOIN (SELECT A.ORGID FROM ZYZ_ORGS A START WITH A.ORGID = ");
		SQL.append(selectOrgId);
		SQL.append(" CONNECT BY PRIOR A.ORGID = A.PARENTORGID)B ON C.ORGID = B.ORGID ");
		SQL.append(" WHERE DELETEFLAG = 0");
		logger.info(SQL.toString());
		int count = this.getJdbcTemplate().queryForInt(SQL.toString());
		return count;
	}
	
	/**
	 * 作用：统计已发布的活动数
	 * @return count 总数
	 */
	public int countActive1(long selectOrgId){
		StringBuffer SQL = new StringBuffer("SELECT COUNT(1)");
		SQL.append(" FROM ZYZ_ACTIVITY C");
		SQL.append(" INNER JOIN (SELECT A.ORGID FROM ZYZ_ORGS A START WITH A.ORGID = ");
		SQL.append(selectOrgId);
		SQL.append(" CONNECT BY PRIOR A.ORGID = A.PARENTORGID)B ON C.ORGID = B.ORGID ");
		SQL.append(" WHERE DELETEFLAG = 0 AND TSSTATUS = 0 AND ACTSTATUS = 0");
		logger.info(SQL.toString());
		int count = this.getJdbcTemplate().queryForInt(SQL.toString());
		return count;
	}
	
	
	public int countRecord(long id){
		StringBuffer SQL = new StringBuffer(); 
		SQL.append("select count(1) from (select * from zyz_issue_worksheet where volunteerid = "+id+") t,zyz_activity a where a.actid = t.actid");
		logger.info(SQL.toString());
		int count = this.getJdbcTemplate().queryForInt(SQL.toString());
		return count;
	}
	
	/**
	 * 作用：保存发布的活动
	 * @param active 待发布的活动信息
	 * @return 插入的记录数
	 */
	public int insertActive(Active active){
		// 影响的条数
		int updateNum = 0;
		Object[] para= new Object[13];
		String SQL= "INSERT INTO ZYZ_ACTIVITY ";
		SQL = SQL + "(ACTID, ACTTITLE, ACTADDR, STARTDATE, ENDDATE, ACTHOURS, ACTNUM,SUMMARY, ACTDESC, PATH, RATIO, ORGID, ORGNAME, INTENTION, ACTSTATUS, SENDTIME)";
		SQL = SQL + " VALUES (SEQ_ZYZ_HD.NEXTVAL,?,?,TO_DATE(?,'YYYY-MM-DD'),TO_DATE(?,'YYYY-MM-DD'),?,?,?,?,?,?,?,?,?,0,SYSDATE)";
		// 活动主题
		para[0] = active.getActtitle();
		// 活动地点
		para[1] = active.getActaddr();
		// 开始日期
		para[2] = active.getStartdate();
		// 结束日期
		para[3] = active.getEnddate();
		// 时间储蓄
		para[4] = active.getActhours();
		// 活动人数
		para[5] = active.getActnum();
		// 活动摘要
		para[6] = active.getSummary();
		// 活动描述
		para[7] = active.getActdesc();
		// 附件路径
		para[8] = active.getPath();
		// 活动系数
		para[9] = active.getRatio();
		// 组织ID
		para[10] = active.getOrgid();
		// 组织名称
		para[11] = active.getOrgname();
		// 服务意向
		para[12] = active.getIntention();
		logger.info(SQL.replaceAll("\\?", "{}"), para);
		try{
			updateNum = this.getJdbcTemplate().update(SQL, para);
		}catch(Exception e){}
		return updateNum;
	}
	
	/**
	 * 作用：查询待修改的活动
	 * @param actId 活动ID
	 * @return 待修改的活动
	 */
	public Active findActiveByActId(long actId){
		Active active = null;
		String SQL = " SELECT ACTID, ACTTITLE, ACTADDR, TO_CHAR(STARTDATE,'yyyy-MM-dd') AS STARTDATE,";
		SQL = SQL + " TO_CHAR(ENDDATE,'yyyy-MM-dd') AS ENDDATE, ACTNUM, SUMMARY, ACTDESC, PATH, RATIO, INTENTION, ACTHOURS ";
		SQL = SQL + " FROM ZYZ_ACTIVITY WHERE ACTID =" + actId + " AND DELETEFLAG = 0 AND ROWNUM = 1";
		logger.info(SQL.toString());
		active = this.getJdbcTemplate().queryForObject(SQL, new EditActiveRowMapper());		
		return active;
	}
	
	/**
	 * 作用：修改活动
	 * @param active 修改的活动信息
	 * @return
	 */
	public int updateActive(Active active){
		// 影响的条数
		int updateNum = 0;
		Object[] para= new Object[9];
		String SQL= "UPDATE ZYZ_ACTIVITY SET ";
		SQL = SQL + " ACTTITLE = ?, ACTADDR = ?, STARTDATE = TO_DATE(?,'YYYY-MM-DD'), ENDDATE = TO_DATE(?,'YYYY-MM-DD'), ACTNUM = ?, SUMMARY = ?, ACTDESC = ?, PATH = ? ";
		SQL = SQL + " WHERE ACTID = ?";
		// 活动主题
		para[0] = active.getActtitle();
		// 活动地点
		para[1] = active.getActaddr();
		// 开始日期
		para[2] = active.getStartdate();
		// 结束日期
		para[3] = active.getEnddate();
		// 活动人数
		para[4] = active.getActnum();
		// 活动摘要
		para[5] = active.getSummary();
		// 活动描述
		para[6] = active.getActdesc();
		// 附件路径
		para[7] = active.getPath();
		// 活动ID		
		para[8] = active.getActid();
		logger.info(SQL.replaceAll("\\?", "{}"), para);
		try{
			updateNum = this.getJdbcTemplate().update(SQL, para);
		}catch(Exception e){}
		return updateNum;
	}
	
	/**
	 * 作用：推送/停止推送 活动
	 * @param actid 活动ID
	 * @param tsStatus 推送状态
	 * @return
	 */
	public int tsActive(long actid, String tsStatus){
		// 影响的条数
		int updateNum = 0;
		Object[] para= new Object[2];
		String SQL= "UPDATE ZYZ_ACTIVITY SET ";
		SQL = SQL + " tsstatus = ? ";
		SQL = SQL + " WHERE ACTID = ?";
		// 推送状态(推送中)
		para[0] = 0;		
		// 活动ID		
		para[1] = actid;
		logger.info(SQL.replaceAll("\\?", "{}"), para);
		try{
			updateNum = this.getJdbcTemplate().update(SQL, para);
		}catch(Exception e){}
		return updateNum;
	}
	
	/**
	 * 作用：结束活动
	 * @param actid 活动ID
	 * @return updateNum 影响的记录数
	 */
	public int finishActive(long actid, int point){
		// 影响的条数
		int updateNum = 0;
		Object[] para= new Object[]{actid};
		String SQL= "UPDATE ZYZ_ACTIVITY SET ";
		SQL = SQL + " DEFAULTPOINT = " + point + ",";
		SQL = SQL + " ACTSTATUS = 1 ";
		SQL = SQL + " WHERE ACTID = ?";
		logger.info(SQL.replaceAll("\\?", "{}"), para);
		try{
			updateNum = this.getJdbcTemplate().update(SQL, para);
		}catch(Exception e){}
		return updateNum;
	}
	
	/**
	 * 作用：更新志愿者积分
	 * @param id 志愿者id
	 * @param point 本次活动的积分
	 * @return updateNum 影响的记录数
	 */
	public int updateMemberPoint(long id, int point){
		// 影响的条数
		int updateNum = 0;
		Object[] para= new Object[]{point, id};
		String SQL= "UPDATE ZYZ_MEMBERS SET ";
		SQL = SQL + " TOTALPOINTS = TOTALPOINTS + ?";
		SQL = SQL + " WHERE ID = ?";
		logger.info(SQL.replaceAll("\\?", "{}"), para);
		try{
			updateNum = this.getJdbcTemplate().update(SQL, para);
		}catch(Exception e){}
		return updateNum;
	}
	
	/**
	 * 作用：删除活动
	 * @param actid 活动ID
	 * @return updateNum 影响的记录数
	 */
	public int deleteActive(long actid){
		// 影响的条数
		int updateNum = 0;
		Object[] para= new Object[]{actid};
		String SQL= "UPDATE ZYZ_ACTIVITY SET ";
		SQL = SQL + " DELETEFLAG = 1 ";
		SQL = SQL + " WHERE ACTID = ?";
		logger.info(SQL.replaceAll("\\?", "{}"), para);
		try{
			updateNum = this.getJdbcTemplate().update(SQL, para);
		}catch(Exception e){}
		return updateNum;
	}
	
	/**
	 * 作用：统计已确认的志愿者数量
	 * @param  活动ID
	 * @return 已确认的志愿者数量
	 */
	public int countConfirmedMemberByActid(long actid){
		int total = 0;
		String SQL = " SELECT COUNT(1) ";
		SQL = SQL + " FROM ZYZ_ISSUE_WORKSHEET WHERE ACTID = " + actid + " AND STATUS != '0'";
		logger.info(SQL.toString());
		total = this.getJdbcTemplate().queryForInt(SQL);
		return total;
	}
	
	/**
	 * 作用：统计该活动的工单数
	 * @param  活动ID
	 * @return 该活动的工单数
	 */
	public int countWorksheetByActid(long actid){
		int total = 0;
		String SQL = " SELECT COUNT(1) ";
		SQL = SQL + " FROM ZYZ_ISSUE_WORKSHEET WHERE ACTID = " + actid;
		logger.info(SQL.toString());
		total = this.getJdbcTemplate().queryForInt(SQL);
		return total;
	}
	
	/**
	 * 作用：查询指定数量的志愿者信息
	 * @param  指定数量
	 * @return 志愿者列表
	 */
	public List<Postulant> findMemberInfoList(long num, long orgid, String intentions, long actId){
		List<Postulant> postulantList = null;
		String SQL = " SELECT ID, NAME, DN, INTENTION, T1.ORGID, ORGNAME, CREDTYPE, CREDCODE, COMMUNITYID, COMMUNITYNAME ";
		SQL = SQL + " FROM ZYZ_MEMBERS T1";
		SQL = SQL + " INNER JOIN (SELECT A.ORGID FROM ZYZ_ORGS A START WITH A.ORGID = " + orgid;
		SQL = SQL + " CONNECT BY PRIOR A.ORGID = A.PARENTORGID)B ON T1.COMMUNITYID = B.ORGID ";
		SQL = SQL + " WHERE NOT EXISTS (SELECT 1 FROM ZYZ_ISSUE_WORKSHEET T2 WHERE T2.VOLUNTEERID = T1.ID AND T2.ACTID = " + actId + ") ";
		if(!"0".equals(intentions)){
			SQL = SQL + " AND INTENTION IN (" + intentions +")";
		}
		SQL = SQL + " AND ISVALID = 1 AND ROWNUM <=" + num;
		logger.info(SQL.toString());
		postulantList = this.getJdbcTemplate().query(SQL, new QueryPostulantRowMapper());
		return postulantList;
	}
	
	/**
	 * 作用：查询该活动的所有工单列表(分页用)
	 * @param  活动ID
	 * @return 工单列表
	 */
	public List<IssueWorkSheet> findMemberByActid(long actid, int start, int end){
		List<IssueWorkSheet> issueWorkSheetList = null;
		String SQL = "SELECT ID, ACTID, ORGNAME, VOLUNTEERID, VOLUNTEERNAME, CREDTYPE, CREDCODE, DN, INTENTION, COMMUNITYID, COMMUNITYNAME, STATUS FROM (";
		SQL = SQL + " SELECT ROWNUM AS rn, ID, ACTID, ORGNAME, VOLUNTEERID, VOLUNTEERNAME, CREDTYPE, CREDCODE, DN, INTENTION, COMMUNITYID, COMMUNITYNAME, STATUS FROM (";
		SQL = SQL + " SELECT ID, ACTID, ORGNAME, VOLUNTEERID, VOLUNTEERNAME, CREDTYPE, CREDCODE, DN, INTENTION, COMMUNITYID, COMMUNITYNAME, STATUS ";
		SQL = SQL + " FROM ZYZ_ISSUE_WORKSHEET WHERE ACTID = " + actid + " ORDER BY CREATETIME DESC";
		SQL = SQL + ") WHERE ROWNUM <= " + end;
		SQL = SQL + ") WHERE rn > " + start;
		logger.info(SQL);
		issueWorkSheetList = this.getJdbcTemplate().query(SQL, new QueryIssueWorkSheetRowMapper());
		return issueWorkSheetList;
	}
	
	/**
	 * 作用：查询该活动的所有工单列表
	 * @param  活动ID
	 * @return 工单列表
	 */
	public List<IssueWorkSheet> findMember1ByActid(long actid){
		List<IssueWorkSheet> issueWorkSheetList = null;
		String SQL = " SELECT ID, ACTID, ORGNAME, VOLUNTEERID, VOLUNTEERNAME, CREDTYPE, CREDCODE, DN, INTENTION, COMMUNITYID, COMMUNITYNAME, STATUS ";
		SQL = SQL + " FROM ZYZ_ISSUE_WORKSHEET WHERE ACTID = " + actid + " AND STATUS != '0' ORDER BY CREATETIME DESC";
		logger.info(SQL);
		issueWorkSheetList = this.getJdbcTemplate().query(SQL, new QueryIssueWorkSheetRowMapper());
		return issueWorkSheetList;
	}
	
	/**
	 * 作用：查询该活动的工单总件数
	 * @param  活动ID
	 * @return 工单总件数
	 */
	public int countMemberByActid(long actid){
		int total = 0;
		String SQL = " SELECT COUNT(1) ";
		SQL = SQL + " FROM ZYZ_ISSUE_WORKSHEET WHERE ACTID = " + actid;
		logger.info(SQL.toString());
		total = this.getJdbcTemplate().queryForInt(SQL);
		return total;
	}
	
	/**
	 * 作用：批量插入工单表
	 */
	public void batcInsert(final List<IssueWorkSheet> issueWorkSheetList) {
		BatchPreparedStatementSetter setter=new BatchPreparedStatementSetter (){
	          public void setValues(PreparedStatement ps,int i) throws SQLException{
	        	  IssueWorkSheet issueWorkSheet=(IssueWorkSheet)issueWorkSheetList.get(i);
	        	  // 活动ID
	              ps.setLong(1, issueWorkSheet.getActid());
		          // 组织ID
	              ps.setLong(2, issueWorkSheet.getOrgid());
	              // 组织名称
	              ps.setString(3, issueWorkSheet.getOrgname());
	              // 志愿者ID
	              ps.setLong(4, issueWorkSheet.getVolunteerid());
	              // 志愿者姓名
	              ps.setString(5, issueWorkSheet.getVolunteername());
	              // 手机
	              ps.setString(6, issueWorkSheet.getDn());
	              // 证件类型
	              ps.setString(7, issueWorkSheet.getCredtype());
	              // 证件号码
	              ps.setString(8, issueWorkSheet.getCredcode());
	              // 服务意向
	              ps.setString(9, issueWorkSheet.getIntention());
	              // 社区Id
	              ps.setLong(10, issueWorkSheet.getCommunityid());
	              // 社区名称
	              ps.setString(11, issueWorkSheet.getCommunityname());
	          }
	          public int getBatchSize(){
	             return issueWorkSheetList.size();
	          }
	    };
		String SQL= "INSERT INTO ZYZ_ISSUE_WORKSHEET ";
		SQL = SQL + "(ID, ACTID, ORGID, ORGNAME, VOLUNTEERID, VOLUNTEERNAME, DN, CREDTYPE, CREDCODE, ";
		SQL = SQL + " INTENTION, COMMUNITYID, COMMUNITYNAME, STATUS, CREATETIME, UPDATETIME) ";
		SQL = SQL + " VALUES (SEQ_ZYZ_COMMON.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,0,SYSDATE,SYSDATE)";
	    getJdbcTemplate().batchUpdate(SQL, setter);
	}
	
	/**
	 * 作用：确认工单
	 * @param worksheetId 工单ID
	 * @return updateNum 影响的记录数
	 */
	public int doConfirm(long worksheetId){
		// 影响的条数
		int updateNum = 0;
		Object[] para= new Object[]{worksheetId};
		String SQL= "UPDATE ZYZ_ISSUE_WORKSHEET SET ";
		SQL = SQL + " STATUS = 2 ";
		SQL = SQL + " WHERE ID = ?";
		logger.info(SQL.replaceAll("\\?", "{}"), para);
		try{
			updateNum = this.getJdbcTemplate().update(SQL, para);
		}catch(Exception e){}
		return updateNum;
	}
	
	/**
	 * 作用：查询该活动的所有工单列表(Excel 导出)
	 * @param  活动ID
	 * @return 工单列表
	 */
	public List<IssueWorkSheet> findAllMemberByActid(long actid){
		List<IssueWorkSheet> issueWorkSheetList = null;
		String SQL = " SELECT ID, ACTID, ORGNAME, VOLUNTEERID, VOLUNTEERNAME, CREDTYPE, CREDCODE, DN, INTENTION, COMMUNITYID, COMMUNITYNAME, STATUS ";
		SQL = SQL + "  FROM ZYZ_ISSUE_WORKSHEET WHERE ACTID = " + actid + " ORDER BY CREATETIME DESC";
		logger.info(SQL);
		issueWorkSheetList = this.getJdbcTemplate().query(SQL, new QueryIssueWorkSheetRowMapper());
		return issueWorkSheetList;
	}
	
	/**
	 * 个人报名
	 * @param postulant
	 * @param activeId
	 * @return
	 */
	public int insertIssueWorkSheet(Postulant postulant,long activeId){
		String SQL= "INSERT INTO ZYZ_ISSUE_WORKSHEET ";
		SQL = SQL + "(ID, ACTID, ORGID, ORGNAME, VOLUNTEERID, VOLUNTEERNAME, DN, CREDTYPE, CREDCODE, ";
		SQL = SQL + " INTENTION, COMMUNITYID, COMMUNITYNAME, STATUS, CREATETIME, UPDATETIME) ";
		SQL = SQL + " VALUES (SEQ_ZYZ_COMMON.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,3,SYSDATE,SYSDATE)";
		return this.getJdbcTemplate().update(
				SQL,
				activeId,postulant.getOrgid(),postulant.getOrgname(),postulant.getId(),postulant.getName(),postulant.getDn(),postulant.getCredtype()
				,postulant.getCredcode(),postulant.getIntention(),postulant.getCommunityid(),postulant.getCommunityname());
	}
	/**
	 * 作用：创建活动统计树
	 * @return
	 */
	public List<Active> createActiveCountTree(Long currentOrgId, String startDate, String endDate){
		String SQL = " SELECT ORGID, ORGNAME, PARENTORGID, ORGLEVEL, ACTIVENUM FROM( SELECT DISTINCT B.ORGID, B.ORGNAME, B.PARENTORGID, ORGLEVEL,COUNT(T.ORGID) OVER(PARTITION BY B.ORGID) ACTIVENUM FROM ";
		SQL = SQL + " (SELECT ORGID,ORGNAME, PARENTORGID, LEVEL AS ORGLEVEL FROM ZYZ_ORGS A ";
		SQL = SQL + " START WITH A.ORGID = " + currentOrgId + " CONNECT BY PRIOR A.ORGID = A.PARENTORGID)B ";		
		SQL = SQL + " LEFT JOIN ZYZ_ACTIVITY T ON B.ORGID = T.ORGID ";
		if(null != startDate && !"".equals(startDate)){
			SQL = SQL + " AND T.STARTDATE >=TO_DATE('" + startDate + "','yyyy-MM-dd')";
		}
		if(null != endDate && !"".equals(endDate)){
			SQL = SQL + " AND T.STARTDATE <= TO_DATE('" + endDate + "','yyyy-MM-dd')";
		}
		SQL = SQL + " AND T.DELETEFLAG = 0) START WITH ORGID = " + currentOrgId + " CONNECT BY PRIOR ORGID = PARENTORGID";
		logger.info(SQL);
		List<Active> list=this.getJdbcTemplate().query(SQL, new ActiveCountRowMapper());
		return list;
	}
	
	/**
	 * 作用：查询某组织（不包含子组织）已发布的活动列表
	 * @return 活动列表
	 */
	public List<Active> findActiveList(long orgid, String startdate, String enddate, int start, int end){
		StringBuffer SQL = new StringBuffer("SELECT * FROM("); 
		SQL.append(" SELECT ROWNUM AS rn, ACTID, ACTTITLE, STARTDATE, ENDDATE, ORGNAME, INTENTION, ACTSTATUS, TSSTATUS, ATTENDNUM FROM (");
		SQL.append(" SELECT");
		SQL.append(" ACTID,");
		SQL.append(" ACTTITLE,");
		SQL.append(" TO_CHAR(STARTDATE,'yyyy-MM-dd') AS STARTDATE,");
		SQL.append(" TO_CHAR(ENDDATE,'yyyy-MM-dd') AS ENDDATE,");
		SQL.append(" ORGNAME, INTENTION, ACTSTATUS, TSSTATUS, (SELECT COUNT(1) FROM ZYZ_ISSUE_WORKSHEET WHERE ACTID = C.ACTID) AS ATTENDNUM ");
		SQL.append(" FROM ZYZ_ACTIVITY C  ");
		SQL.append(" WHERE C.STARTDATE >= TO_DATE('").append(startdate).append("','yyyy-MM-dd')");
		SQL.append(" AND C.STARTDATE <= TO_DATE('").append(enddate).append("','yyyy-MM-dd')");
		SQL.append(" AND C.ORGID = ").append(orgid);
		SQL.append(" AND DELETEFLAG = 0 ORDER BY SENDTIME DESC) WHERE ROWNUM <= " + end + ")WHERE rn > " + start);
		logger.info(SQL.toString());
		List<Active> list=(List<Active>) this.getJdbcTemplate()
												.query(SQL.toString(), new QueryActiveNumRowMapper());
		return list;
	}
	
	/**
	 * 作用：统计某组织（不包含子组织）已发布的活动数
	 * @return count 总数
	 */
	public int countActiveList(long orgid, String startdate, String enddate){
		StringBuffer SQL = new StringBuffer("SELECT COUNT(1)");
		SQL.append(" FROM ZYZ_ACTIVITY C WHERE ");
		SQL.append(" C.STARTDATE >= TO_DATE('").append(startdate).append("','yyyy-MM-dd')");
		SQL.append(" AND C.STARTDATE <= TO_DATE('").append(enddate).append("','yyyy-MM-dd')");
		SQL.append(" AND C.ORGID = ").append(orgid);
		SQL.append(" AND DELETEFLAG = 0");
		logger.info(SQL.toString());
		int count = this.getJdbcTemplate().queryForInt(SQL.toString());
		return count;
	}
	
	/**
	 * 作用：查找某市(管理机构)的服务意向列表
	 * @param  组织ID
	 * @return 服务意向列表
	 */
	public List<SysParam> findIntention(long orgId){
		List<SysParam> fwyxList = null;
		String SQL = " SELECT param.PARAMKEY, param.PARAMVALUE, param.PARAMDESC, org.ORGNAME, org.ORGID ";
		SQL = SQL + "  FROM ZYZ_SYSPARAM param, ZYZ_ORGS org WHERE param.PARAMVALUE = org.BELONGORGID AND org.PARENTORGID = " + orgId + " AND org.ORGTYPEID = 200 AND param.PARAMCODE ='FWYX' AND param.PARAMSTATE = 1 ORDER BY param.PARAMORDER";
		logger.info(SQL);
		fwyxList = this.getJdbcTemplate().query(SQL, new FwyxParamRowMapper());
		return fwyxList;
	}
	
	/**
	 * 作用：查找某市(服务机构)的服务意向列表
	 * @param  组织ID
	 * @return 服务意向列表
	 */
	public List<SysParam> findIntention1(long orgId){
		List<SysParam> fwyxList = null;
		String SQL = " SELECT param.PARAMKEY, param.PARAMVALUE, param.PARAMDESC, org.ORGNAME, org.ORGID ";
		SQL = SQL + "  FROM ZYZ_SYSPARAM param, ZYZ_ORGS org WHERE param.PARAMVALUE = org.BELONGORGID AND org.ORGID = " + orgId + " AND org.ORGTYPEID = 200 AND param.PARAMCODE ='FWYX' AND param.PARAMSTATE = 1";
		logger.info(SQL);
		fwyxList = this.getJdbcTemplate().query(SQL, new FwyxParamRowMapper());
		return fwyxList;
	}
}
